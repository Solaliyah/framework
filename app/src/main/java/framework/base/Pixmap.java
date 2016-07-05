package framework.base;

import framework.base.Graphics.PixmapFormat;

/**
 * Created by SolarisD on 2016/05/16.
 */
public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public PixmapFormat getFormat();

    public void dispose();
}
