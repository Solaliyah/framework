package framework.impl;

import android.content.Context;
import android.view.View;

import java.util.List;

import framework.base.Input;


/**
 * Created by SolarisD on 2016/03/21.
 */
public class AndroidInput implements Input {
    AccelerometerHandler accelHandler;
    KeyboardHandler keyHandler;
    TouchHandler touchHandler;

    public AndroidInput(Context context, View view,
                        float scaleX, float scaleY){
        accelHandler = new AccelerometerHandler(context);
        keyHandler = new KeyboardHandler(view);
        touchHandler = new MultiTouchHandler(view, context, scaleX, scaleY);
    }

    @Override
    public boolean isKeyPressed(int keyCode){
        return keyHandler.isKeyPressed(keyCode);
    }

    @Override
    public int getTouchX(int pointer){
        return touchHandler.getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer){
        return touchHandler.getTouchY(pointer);
    }

    @Override
    public float getAccelX(){
        return accelHandler.getAccelX();
    }

    @Override
    public float getAccelY(){
        return accelHandler.getAccelY();
    }

    @Override
    public float getAccelZ(){
        return accelHandler.getAccelZ();
    }

    @Override
    public TouchEvent getTouchEvents(){
        return touchHandler.getTouchEvents();
    }

    @Override
    public List<KeyEvent> getKeyEvents(){
        return keyHandler.getKeyEvents();
    }

}
