package framework.gl;

import android.provider.Settings;
import android.util.Log;

/**
 * Created by SolarisD on 2016/06/01.
 */
public class DynamicGraphicData {
    float x;
    float y;
    float width;
    float height;
    float angle;
    Texture texture;
    TextureRegion region;
    int moveSpeedX;
    int moveSpeedY;
    float rotateSpeed;
    long lastTime;
    long endLifeTime;
    int layer;

    public DynamicGraphicData(float x, float y, float width, float height, float angle, Texture texture, TextureRegion region,
                       int moveSpeedX, int moveSpeedY, float rotateSpeed, long startFrameTime,long lifeTime, int layer){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.angle = angle;
        this.texture = texture;
        this.region = region;
        this.moveSpeedX = moveSpeedX;
        this.moveSpeedY = moveSpeedY;
        this.rotateSpeed = rotateSpeed;
        endLifeTime = startFrameTime + lifeTime;
        lastTime = startFrameTime;
        this.layer = layer;
    }

    public boolean update(long startFrameTime) {
        if(endLifeTime < startFrameTime)
            return false;

        x += moveSpeedX;
        y += moveSpeedY;
        angle += rotateSpeed;
        lastTime = startFrameTime;
        return true;
    }

}
