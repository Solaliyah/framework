package framework.impl;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import framework.base.Graphics;


/**
 * Created by SolarisD on 2016/03/22.
 */
public class AndroidFastRenderView extends SurfaceView implements Runnable{
    AndroidGame game;
    Bitmap framebuffer;
    Thread renderThread = null;
    SurfaceHolder holder;
    volatile boolean running = false;

    public AndroidFastRenderView(AndroidGame game, Bitmap framebuffer){
        super(game);
        Log.d("Creat","AndroidFastRenderView");
        this.game = game;
        this.framebuffer = framebuffer;
        this.holder = getHolder();
    }

    public void resume(){
        running = true;
        renderThread = new Thread(this);
        renderThread.start();
    }

    public void run(){
        Rect dstRect = new Rect();
        Graphics g = game.getGraphics();
        long startTime = System.nanoTime();
        while(running){
            if(!holder.getSurface().isValid())
                continue;

            long deltaTime = System.nanoTime() - startTime;

            startTime = System.nanoTime();

            g.clear(Color.BLACK);
            game.getCurrentScreen().update(0,deltaTime);
            game.getCurrentScreen().present(0,deltaTime);

            Canvas canvas = holder.lockCanvas();
            canvas.getClipBounds(dstRect);
            canvas.drawBitmap(framebuffer, null, dstRect, null);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    public void pause(){
        running = false;
        while(true){
            try{
                renderThread.join();
                break;
            }catch(InterruptedException e){
                //リトライ
            }
        }
    }

}
