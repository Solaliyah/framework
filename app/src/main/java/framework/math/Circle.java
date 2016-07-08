package framework.math;

/**円のx,y,半径の格納用classです。
※@auther Solaliyah
 * Created by SolarisD on 2016/04/22.
 */
public class Circle {
    public final Vector2 center = new Vector2();
    public float radius;

    public Circle(float x, float y, float radius){
        this.center.set(x, y);
        this.radius = radius;
    }

    public setAll(float x, float y, float radius){
        this.center.set(x, y);
        this.radius = radius;
    }
}