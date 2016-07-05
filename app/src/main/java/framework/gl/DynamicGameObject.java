package framework.gl;

import framework.math.Vector2;

/**
 * Created by SolarisD on 2016/05/17.
 */
public class DynamicGameObject extends GameObject {
    public final Vector2 velocity;
    public final Vector2 accel;

    public DynamicGameObject(float x, float y, float width, float height){
        super(x, y, width, height);
        velocity = new Vector2();
        accel = new Vector2();
    }
}
