package framework.impl;

import android.view.View;

import framework.base.Input.TouchEvent;


/**
 * Created by SolarisD on 2016/03/21.
 */
public interface TouchHandler extends View.OnTouchListener{
    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public TouchEvent getTouchEvents();
}
