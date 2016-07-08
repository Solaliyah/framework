package framework.gl;

import javax.microedition.khronos.opengles.GL10;

import framework.base.Input.TouchEvent;
import framework.impl.GLGraphics;
import framework.math.Vector2;


/**
※ @auther Solaliyah
 * Created by SolarisD on 2016/04/22.
 */
public class Camera2D {
    public final Vector2 position;
    public float zoom;
    public final float frustmWidth;
    public final float frustmHeight;
    final GLGraphics glGraphics;

    public  Camera2D(GLGraphics glGraphics, float frustumWidth, float frustmHeight){
        this.glGraphics = glGraphics;
        this.frustmWidth = frustumWidth;
        this.frustmHeight = frustmHeight;
        this.position = new Vector2(frustumWidth, frustmHeight);
        this.zoom = 2.0f;
    }

    public void setViewportAndMatrices(){
        GL10 gl = glGraphics.getGL();
        gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
        gl.glMatrixMode(GL10.GL_PROJECTION);
        gl.glLoadIdentity();
        gl.glOrthof(position.x - frustmWidth * zoom / 2,
                    position.x + frustmWidth * zoom / 2,
                    position.y - frustmHeight * zoom / 2,
                    position.y + frustmHeight * zoom / 2, 1, -1);
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
/※
※画面上でのタッチ座標をワールド座標に変換し、touch.x,yに正しいタッチ座標を入れるメソッドです。
※@param touch 画面上でのタッチ座標
/
    public void touchToWorld(Vector2 touch){
        touch.x = (touch.x / (float) glGraphics.getWidth()) * frustmWidth * zoom;
        touch.y = (1 - touch.y / (float) glGraphics.getHeight()) * frustmHeight * zoom;
        touch.add(position).sub(frustmWidth * zoom / 2, frustmHeight * zoom /2);
    }

    public void distanceToWorld(TouchEvent touchEvent){
        touchEvent.distanceX = ( touchEvent.distanceX / (float) glGraphics.getWidth()) * frustmWidth * zoom;
        touchEvent.distanceY = ( touchEvent.distanceY / (float) glGraphics.getHeight()) * frustmHeight * zoom;
    }

}

