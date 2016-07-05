package framework.gl;

import android.util.Log;

/**
 * Created by SolarisD on 2016/04/20.
 */
public class FPSCounter {
    long startTime = System.nanoTime();
    int frames = 0;

    public void logFrame(){
        frames++;
        if(System.nanoTime() - startTime >= 1000000000){
            Log.d("FPSCounter", "fps: " + frames);
            frames = 0;
            startTime = System.nanoTime();
        }
    }
}
