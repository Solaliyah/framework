package androidgames.fishcollection;

import android.util.Log;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import framework.base.Color;
import framework.base.Screen;
import framework.impl.GLGame;

/**
 * Created by SolarisD on 2016/05/19.
 */
public class FishCollection extends GLGame {
    boolean firstTimeCreate = true;

    @Override
    public Screen getStartScreen(){
        return new MainMenuScreen(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config){
        super.onSurfaceCreated(gl, config);
        if(firstTimeCreate){
            Settings.load(getFileIO());
            Assets.load(this);
            firstTimeCreate = false;
        }else {
            Assets.reload();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
        if(Settings.soundEnabled)
            Assets.music.pause();
    }
}
