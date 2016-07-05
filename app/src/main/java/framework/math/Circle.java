package framework.math;

/**
 * Created by SolarisD on 2016/04/22.
 */
public class Circle {
    public final Vector2 center = new Vector2();
    public float radius;

    public Circle(float x, float y, float radius){
        this.center.set(x, y);
        this.radius = radius;
    }
}