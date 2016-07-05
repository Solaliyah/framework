package framework.math;

/**
 * Created by SolarisD on 2016/04/22.
 */
public class Rectangle {
    public final Vector2 lowerLeft;
    public float width, height;

    public Rectangle(float x, float y, float width, float height){
        this.lowerLeft = new Vector2(x, y);
        this.width = width;
        this.height = height;
    }

    public void setAll(float x, float y, float width, float height){
        this.lowerLeft.set(x, y);
        this.width = width;
        this.height = height;
    }
}
