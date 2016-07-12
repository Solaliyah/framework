package framework.gl;

import android.util.Log;

/**
 * FPSのカウントと表示用class
 * @auther Solaliyah
 */
public class FPSCounter {
        // ナノ秒を秒に調整する
    long startFPSTime = System.nanoTime() / 1000000000 * 1000000000;
    int frames = 0;
    int FPS = 0;
    public void logFrame(long startFrameTime){
        frames++;
        if(startFrameTime - startFPSTime >= 1000000000){
            Log.d("FPSCounter", "fps: " + frames);
            frames = 0;
            startFPSTime = startFrameTime / 1000000000 * 1000000000;
        }
    }
    /**
     * 前の秒のFPSを得る
     */
    public int getFPS() {
        return FPS;
    }
}
