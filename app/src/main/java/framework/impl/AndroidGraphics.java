package framework.impl;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;

import java.io.IOException;
import java.io.InputStream;

import framework.base.Graphics;
import framework.base.Pixmap;


/**
 * Created by SolarisD on 2016/03/22.
 */
public class AndroidGraphics implements Graphics {
    AssetManager assets;
    Bitmap frameBuffer;
    Canvas canvas;
    Paint paint;
    Rect srcRect = new Rect();
    Rect dstRect = new Rect();

    public AndroidGraphics(AssetManager assets, Bitmap frameBuffer){
        this.assets = assets;
        this.frameBuffer = frameBuffer;
        this.canvas = new Canvas(frameBuffer);
        this.paint = new Paint();
    }

    @Override
    public Pixmap newPixmap(String fileName, PixmapFormat format) {
        Config config = null;
        if(format == PixmapFormat.RGB565)
            config = config.RGB_565;
        else if(format == PixmapFormat.ARGB4444)
            config = config.ARGB_4444;
        else
            config = config.ARGB_8888;

        Options options = new Options();
        options.inPreferredConfig = config;

        InputStream in = null;
        Bitmap bitmap = null;
        try{
            in = assets.open(fileName);
            bitmap = BitmapFactory.decodeStream(in);
            if(bitmap == null)
                throw new RuntimeException("Couldn't load bitmap from asset '"
                                            + fileName + "'");
        }catch(IOException e){
            throw new RuntimeException("Couldn't load bitmap from asset '"
                    + fileName + "'");
        }finally {
            if(in != null){
                try{
                    in.close();
                }catch (IOException e){
                    throw new RuntimeException("Couldn't close file '"
                            + fileName + "'");
                }
            }
        }
        if(bitmap.getConfig() == Config.RGB_565)
            format = PixmapFormat.RGB565;
        else if(bitmap.getConfig() == Config.ARGB_4444)
            format = PixmapFormat.ARGB4444;
        else
            format = PixmapFormat.ARGB8888;

        return new AndroidPixmap(bitmap, format);
    }

    @Override
    public void clear(int color){
        canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
                        (color & 0xff));
    }

    @Override
    public void drawPixel(int x, int y, int color){
        paint.setColor(color);
        canvas.drawPoint(x, y, paint);
    }

    @Override
    public void drawLine(int x,int y, int x2, int y2, int color){
        paint.setColor(color);
        canvas.drawLine(x, y, x2, y2, paint);
    }

    @Override
    public void drawRect(int x, int y, int width, int height,int color,boolean styleFill){
        paint.setColor(color);
        if(styleFill)
            paint.setStyle(Paint.Style.FILL);
        else
            paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(x, y, x + width - 1, y + height - 1, paint);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
                            int srcWidth, int srcHeight){
        srcRect.left = srcX;
        srcRect.top = srcY;
        srcRect.right = srcX + srcWidth - 1;
        srcRect.bottom = srcY + srcHeight - 1;

        dstRect.left = x;
        dstRect.top = y;
        dstRect.right = x + srcWidth - 1;
        dstRect.bottom = y + srcHeight - 1;

        canvas.drawBitmap(((AndroidPixmap) pixmap).bitmap, srcRect, dstRect,
                null);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y){
        canvas.drawBitmap(((AndroidPixmap)pixmap).bitmap, x, y, null);
    }

    @Override
    public void drawText(String str, int x, int y, int color, int size){
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(size);
        paint.setColor(color);
        canvas.drawText(str, x, y, paint);
    }

    @Override
    public void moveTo(Path path, float x, float y, int color){
        this.paint.setColor(color);
        this.paint.setStyle(Paint.Style.STROKE);
        path.moveTo(x, y);
    }

    @Override
    public void lineTo(Path path, float x, float y){
        path.lineTo(x, y);
    }

    @Override
    public void close(Path path){
        path.close();
    }

    @Override
    public void drawPath(Path path){
        canvas.drawPath(path, this.paint);
    }

    @Override
    public int getWidth(){
        return frameBuffer.getWidth();
    }

    @Override
    public int getHeight(){
        return frameBuffer.getHeight();
    }
}
