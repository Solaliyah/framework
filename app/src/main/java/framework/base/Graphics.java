package framework.base;

import android.graphics.Path;

/**
 * 画面の塗りつぶし、簡単な図形、viewの縦横幅を返すメソッドを持つinterfaceです。
 *colorクラスのconvertを使うと簡単に引数のcolorを渡せる。
 * @author Solaliyah
 */
public interface Graphics {
    public static enum PixmapFormat {
        ARGB8888, ARGB4444, RGB565
    }

    public Pixmap newPixmap(String fileName, PixmapFormat format);
    /*
    * 画面全体をcolor色で塗りつぶす
    * @param color 塗りつぶす色を渡します。
    */
    public void clear(int color);

    public void drawPixel(int x, int y, int color);

    public void drawLine(int x, int y, int x2, int y2, int color);

    public void drawRect(int x, int y, int width, int height, int color, boolean styleFill);

    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
                           int srcWidth, int srcHeight);

    public void drawPixmap(Pixmap pixmap, int x, int y);

    public void drawText(String str, int x, int y, int color, int size);

    public void moveTo(Path path, float x, float y, int color);

    public void lineTo(Path path, float x, float y);

    public void close(Path path);

    public void drawPath(Path path);

    public int getWidth();

    public int getHeight();
}
