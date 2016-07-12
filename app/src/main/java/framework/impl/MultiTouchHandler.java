package framework.impl;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import framework.base.Input.TouchEvent;
import framework.gl.Camera2D;
import framework.math.Vector2;
    /**
     * マルチタッチの管理をするclassです。
     * @auther Solaliyah
     * Created by SolarisD on 2016/03/21.
     */
public class MultiTouchHandler implements TouchHandler {
        /*
        * maxTouchNumはタッチの最大数を格納しています
        * 最大数の変更はsetMaxTouch(int)メソッドで1~10の間で変更できます。
        */
    private static int maxTouch = 1;
    public class BeforeTouchEvent{
        int type = -1;
        int x = 0;
        int y = 0;
        int pointer = 0;
    };
    int touchX;
    int touchY;
    float scaleX;
    float scaleY;
    GestureDetector gestureDetector;
    TouchEvent touchEvent;
    BeforeTouchEvent beforeTouchEvent;



    public MultiTouchHandler(View view, Context context, float scaleX, float scaleY){
        view.setOnTouchListener(this);
        this.scaleX = scaleX;
        this.scaleY = scaleY;
        touchEvent = new TouchEvent();
        gestureDetector = new GestureDetector(context, simpleOnGestureListener);
        beforeTouchEvent = new BeforeTouchEvent();
    }

        /**
         * タッチジェスチャー時の設定をしています。
         * pointerIndexからpointerIDを得る。pointerIDは実際にタッチしている指のIDです。
         * pointerIDがmaxTouchより少なかった場合のみtouchEventに情報を渡す。
         * TODO returnはtrueとfalseどちらがよいのか？
         */
    private final GestureDetector.SimpleOnGestureListener simpleOnGestureListener
                                    = new GestureDetector.SimpleOnGestureListener() {

        @Override
        public boolean onDown(MotionEvent event) {//画面を押した時
            int pointerIndex =
                    (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >>
                            MotionEvent.ACTION_POINTER_INDEX_SHIFT;
            int pointerId = event.getPointerId(pointerIndex);
            if(pointerId < maxTouch) {
                touchEvent.type = TouchEvent.TOUCH_DOWN;
                touchEvent.pointer = pointerId;
                touchEvent.x = touchX = (int) (event.getX(pointerIndex) * scaleX);
                touchEvent.y = touchY = (int) (event.getY(pointerIndex) * scaleY);
                Log.d("TouchEvent", "onDown");
            }
            return false;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent event) {//1タップで画面から指が離れた
            int pointerIndex =
                    (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >>
                            MotionEvent.ACTION_POINTER_INDEX_SHIFT;
            int pointerId = event.getPointerId(pointerIndex);
            if(pointerId < maxTouch) {
                touchEvent.type = TouchEvent.TOUCH_SINGLETAP_UP;
                touchEvent.pointer = pointerId;
                touchEvent.x = touchX = (int) (event.getX(pointerIndex) * scaleX);
                touchEvent.y = touchY = (int) (event.getY(pointerIndex) * scaleY);
                Log.d("TouchEvent", "onSingleTapUp");
            }
            return false;
        }

        @Override
        public void onShowPress(MotionEvent event) {//画面を長押しした
            int pointerIndex =
                    (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >>
                            MotionEvent.ACTION_POINTER_INDEX_SHIFT;
            int pointerId = event.getPointerId(pointerIndex);
            if (pointerId < maxTouch) {
                touchEvent.type = TouchEvent.TOUCH_SHORT_HOLD;
                touchEvent.pointer = pointerId;
                touchEvent.x = touchX = (int) (event.getX(pointerIndex) * scaleX);
                touchEvent.y = touchY = (int) (event.getY(pointerIndex) * scaleY);
                Log.d("TouchEvent", "onShowPress");
            }
        }

        @Override
        public void onLongPress(MotionEvent event) {//同じ箇所を長押しした時の処理
            int pointerIndex =
                    (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >>
                            MotionEvent.ACTION_POINTER_INDEX_SHIFT;
            int pointerId = event.getPointerId(pointerIndex);
            if (pointerId < maxTouch) {
                touchEvent.type = TouchEvent.TOUCH_LONG_HOLD;
                touchEvent.pointer = pointerId;
                touchEvent.x = touchX = (int) (event.getX(pointerIndex) * scaleX);
                touchEvent.y = touchY = (int) (event.getY(pointerIndex) * scaleY);
                Log.d("TouchEvent", "onLongPress");
            }
        }

        @Override
        public boolean onScroll(MotionEvent event, MotionEvent event2, float distanceX, float distanceY) {
            int pointerIndex =
                    (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >>
                            MotionEvent.ACTION_POINTER_INDEX_SHIFT;
            int pointerId = event.getPointerId(pointerIndex);
            if(pointerId < maxTouch) {
                touchEvent.type = TouchEvent.TOUCH_DRAGGED;
                touchEvent.pointer = pointerId;
                touchEvent.distanceX = distanceX;
                touchEvent.distanceY = distanceY;
                touchEvent.x = touchX = (int) (event2.getX(pointerIndex)  * scaleX);
                touchEvent.y = touchY = (int) (event2.getY(pointerIndex) * scaleY);
                Log.d("TouchEvent", "onScroll");

            }
            return false;
        }

        @Override
        public boolean onFling(MotionEvent event, MotionEvent e2, float velocityX, float velocityY) {
            int pointerIndex =
                    (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >>
                            MotionEvent.ACTION_POINTER_INDEX_SHIFT;
            int pointerId = event.getPointerId(pointerIndex);
            if(pointerId < maxTouch) {
                touchEvent.type = TouchEvent.TOUCH_Fling;
                touchEvent.pointer = pointerId;
                touchEvent.x = touchX = (int) (event.getX(pointerIndex) * scaleX);
                touchEvent.y = touchY = (int) (event.getY(pointerIndex) * scaleY);
                touchEvent.distanceX = velocityX;
                touchEvent.distanceY = velocityY;

                Log.d("TouchEvent", "onFling");
            }
            return false;
        }
    };
    @Override
    public boolean onTouch(View v, MotionEvent event){
        Log.d("TouchEvent","onTouch");
        synchronized (this) {
            int action = event.getAction() & MotionEvent.ACTION_MASK;
            int pointerIndex =
                    (event.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) >>
                            MotionEvent.ACTION_POINTER_INDEX_SHIFT;
            int pointerId = event.getPointerId(pointerIndex);
            if(pointerId < maxTouch) {
                gestureDetector.onTouchEvent(event);

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                    case MotionEvent.ACTION_POINTER_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:
                    case MotionEvent.ACTION_POINTER_UP:
                    case MotionEvent.ACTION_CANCEL:
                        if(touchEvent.type != TouchEvent.TOUCH_SINGLETAP_UP) {
                            touchEvent.type = TouchEvent.TOUCH_UP;
                            touchEvent.pointer = pointerId;
                            touchEvent.x = touchX = (int) (event.getX(pointerIndex) * scaleX);
                            touchEvent.y = touchY = (int) (event.getY(pointerIndex) * scaleY);
                            Log.d("TOUCH_UP",String.valueOf(touchEvent.x) + " ; " + String.valueOf(touchEvent.y) );
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        break;
                    default:
                        break;
                }
            }
            return true;
        }
    }

    @Override
    public int getTouchX(int pointer){
        synchronized (this){
            if(pointer < 0 || pointer >= 20)
                return 0;
            else
                return touchX;
        }
    }

    @Override
    public int getTouchY(int pointer){
        synchronized (this){
            if(pointer < 0 || pointer >= 20)
                return 0;
            else
                return touchY;
        }
    }

    @Override
    public TouchEvent getTouchEvents(){
        synchronized (this){
            touchEventChecker();
            return touchEvent;
        }
    }
        /**
         *  前フレームのTouchEventにより現在のTouchEventを修正した後、beforeTouchEventに現在のTouchEventを入れます。
         */
    private void touchEventChecker(){
        //  TODO　この部分の処理の意味がわからない
        if(beforeTouchEvent.type == TouchEvent.TOUCH_UP || beforeTouchEvent.type == TouchEvent.TOUCH_SINGLETAP_UP)
            if(touchEvent.type == TouchEvent.TOUCH_UP || touchEvent.type == TouchEvent.TOUCH_SINGLETAP_UP) {
                touchEvent.type = TouchEvent.NON_TOUCH;
                Log.d("touchEventChecker", "NON_TOUCH");
            }
        /**
         * 前フレームでドラッグをしていて現在のフレームと座標が変わらない場合
         * ホールドとみなす。
         */
        if(beforeTouchEvent.type == TouchEvent.TOUCH_DRAGGED && touchEvent.type == TouchEvent.TOUCH_DRAGGED)
            if(beforeTouchEvent.x == touchEvent.x && beforeTouchEvent.y == touchEvent.y) {
                touchEvent.type = TouchEvent.TOUCH_HOLD;
                Log.d("touchEventChecker", "TOUCH_HOLD");
            }

        beforeTouchEvent.type = touchEvent.type;
        beforeTouchEvent.x = touchEvent.x;
        beforeTouchEvent.y = touchEvent.y;
        beforeTouchEvent.pointer = touchEvent.pointer;

    }

        public void setMaxTouch(int maxNum){
            maxTouch = maxNum;
        }

}
