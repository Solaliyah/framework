package framework.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import framework.base.Audio;
import framework.base.Core;
import framework.base.FileIO;
import framework.base.Graphics;
import framework.base.Input;
import framework.base.Screen;

;

/**
 * Created by SolarisD on 2016/04/08.
 */
public abstract class GLGame extends Activity implements Core, Renderer{
    enum GLGameState{
        Initialized,
        Running,
        Paused,
        Finished,
        Idle
    }

    GLSurfaceView glView;
    GLGraphics glGraphics;
    Audio audio;
    Input input;
    FileIO fileIO;
    Screen screen;
    GLGameState state = GLGameState.Initialized;
    Object stateChanged = new Object();
    long startFrameTime = System.nanoTime();
    WakeLock wakeLock;

    @Override
    public void onActivityResult( int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        glView = new GLSurfaceView(this);
        glView.setRenderer(this);
        setContentView(glView);

        glGraphics = new GLGraphics(glView);
        fileIO = new AndroidFileIO(getAssets());
        audio = new AndroidAudio(this);
        input = new AndroidInput(this, glView, 1, 1);
        PowerManager powerManager = (PowerManager)
                                    getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");

    }

    public void onResume(){
        super.onResume();
        glView.onResume();
        wakeLock.acquire();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        glGraphics.setGL(gl);

        synchronized (stateChanged){
            if(state == GLGameState.Initialized)
                screen = getStartScreen();
            state = GLGameState.Running;
            screen.resume();
            startFrameTime = System.nanoTime();
        }
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height){

    }

    @Override
    public void onDrawFrame(GL10 gl){
        GLGameState state = null;
        synchronized (stateChanged){
            state = this.state;
        }

        if(state == GLGameState.Running){
            long deltaTime = System.nanoTime() - startFrameTime;
            startFrameTime = System.nanoTime();

            screen.update(startFrameTime, deltaTime);
            screen.present(startFrameTime, deltaTime);
        }

        if(state == GLGameState.Paused){
            screen.pause();
            synchronized (stateChanged){
                this.state = GLGameState.Idle;
                stateChanged.notifyAll();
            }
        }

        if(state == GLGameState.Finished){
            screen.pause();
            screen.dispose();
            synchronized (stateChanged){
                this.state = GLGameState.Idle;
                stateChanged.notifyAll();
            }
        }
    }

    @Override
    public void onPause(){
        synchronized (stateChanged) {
            if (isFinishing())
                state = GLGameState.Finished;
            else
                state = GLGameState.Paused;
            while (true) {
                try {
                    stateChanged.wait();
                    break;
                } catch (InterruptedException e) {

                }
            }
            wakeLock.release();
            glView.onPause();
            super.onPause();
        }
    }

    public GLGraphics getGLGraphics(){
        return glGraphics;
    }

    @Override
    public Input getInput(){
        return input;
    }

    @Override
    public FileIO getFileIO(){
        return fileIO;
    }

    @Override
    public Graphics getGraphics(){
        throw new IllegalStateException("We are using OpenGL!");
    }

    @Override
    public Audio getAudio(){
        return audio;
    }

    @Override
    public void setScreen(Screen screen){
        if(screen == null)
            throw new IllegalArgumentException("Screen must not be null");

        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(startFrameTime, 0);
        this.screen = screen;
    }

    @Override
    public Screen getCurrentScreen(){
        return screen;
    }
}
