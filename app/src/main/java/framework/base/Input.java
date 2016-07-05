package framework.base;

import java.util.List;

/**
 * @author Solaliyah
 */
public interface Input {
    public static class KeyEvent {
        public static final int KEY_DOWN = 0;
        public static final int KEY_UP = 1;

        public int type;
        public int keyCode;
        public char keyChar;

    }

    public static class TouchEvent {
        public static final int NON_TOUCH = -1;
        public static final int TOUCH_DOWN = 0;
        public static final int TOUCH_UP = 1;
        public static final int TOUCH_DRAGGED = 2;
        public static final int TOUCH_SHORT_HOLD = 3;
        public static final int TOUCH_LONG_HOLD = 4;
        public static final int TOUCH_Fling = 5;
        public static final int TOUCH_SINGLETAP_UP= 6;
        public static final int TOUCH_HOLD= 7;
        public int type;
        public int x, y;
        public float distanceX, distanceY;
        public int pointer;
    }

    public boolean isKeyPressed(int keyCode);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public float getAccelX();

    public float getAccelY();

    public float getAccelZ();

    public List<KeyEvent> getKeyEvents();

    public TouchEvent getTouchEvents();
}
