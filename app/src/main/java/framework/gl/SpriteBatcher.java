package framework.gl;

import android.util.Log;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import androidgames.fishcollection.Assets;
import framework.impl.GLGraphics;
import framework.math.Vector2;

/**
 * Created by SolarisD on 2016/04/23.
 */
public class SpriteBatcher {
    final float[] verticesBuffer;
    int bufferIndex;
    final Vertices vertices;
    int numSprites;
    ArrayList<DynamicGraphicData> dynamicGraphicDatas;
    public final Texture texture;
    public final int glypWidth;
    public final int glypHeight;
    public final TextureRegion[] glyphs = new TextureRegion[96];

    public SpriteBatcher(GLGraphics glGraphics, int maxSprites){
        this.verticesBuffer = new float[maxSprites * 4 * 4];
        this.vertices = new Vertices(glGraphics, maxSprites * 4, maxSprites * 6, false, true);
        this.bufferIndex = 0;
        this.numSprites = 0;

        short[] indices = new short[maxSprites * 6];
        int len = indices.length;
        short j = 0;
        for(int i = 0;i < len; i += 6, j += 4){
            indices[i + 0] = (short)(j + 0);
            indices[i + 1] = (short)(j + 1);
            indices[i + 2] = (short)(j + 2);
            indices[i + 3] = (short)(j + 2);
            indices[i + 4] = (short)(j + 3);
            indices[i + 5] = (short)(j + 0);
        }
        vertices.setIndices(indices, 0, indices.length);
        dynamicGraphicDatas = new ArrayList<DynamicGraphicData>();
        this.texture = Assets.items;
        this.glypWidth = 0;
        this.glypHeight = 0;
    }

    public SpriteBatcher(GLGraphics glGraphics, int maxSprites,Texture texture, int offsetX,int offsetY,
                         int glypsPerRow, int glypWidth, int glypHeight){
        this.verticesBuffer = new float[maxSprites * 4 * 4];
        this.vertices = new Vertices(glGraphics, maxSprites * 4, maxSprites * 6, false, true);
        this.bufferIndex = 0;
        this.numSprites = 0;

        short[] indices = new short[maxSprites * 6];
        int len = indices.length;
        short j = 0;
        for(int i = 0;i < len; i += 6, j += 4){
            indices[i + 0] = (short)(j + 0);
            indices[i + 1] = (short)(j + 1);
            indices[i + 2] = (short)(j + 2);
            indices[i + 3] = (short)(j + 2);
            indices[i + 4] = (short)(j + 3);
            indices[i + 5] = (short)(j + 0);
        }
        vertices.setIndices(indices, 0, indices.length);
        dynamicGraphicDatas = new ArrayList<DynamicGraphicData>();
        this.texture = texture;
        this.glypWidth = glypWidth;
        this.glypHeight = glypHeight;
        int x = offsetX;
        int y = offsetY;
        for(int i = 0; i < 96; i ++){
            glyphs[i] = new TextureRegion(texture, x, y, glypWidth, glypHeight);
            x += glypWidth;
            if(x == offsetX + glypsPerRow * glypWidth){
                x = offsetX;
                y += glypHeight;
            }
        }
    }

    public void beginBatch(Texture texture){
        texture.bind();
        numSprites = 0;
        bufferIndex = 0;
    }

    public void endBatch(){
        vertices.setVertices(verticesBuffer, 0, bufferIndex);
        vertices.bind();
        vertices.draw(GL10.GL_TRIANGLES, 0, numSprites * 6);
        vertices.unbind();
    }

    public void drawSprite(float x, float y, float width, float height, TextureRegion region){
        float halfWidth = width / 2;
        float halfHeight = height / 2;
        float x1 = x - halfWidth;
        float y1 = y - halfHeight;
        float x2 = x + halfWidth;
        float y2 = y + halfHeight;

        verticesBuffer[bufferIndex++] = x1;
        verticesBuffer[bufferIndex++] = y1;
        verticesBuffer[bufferIndex++] = region.u1;
        verticesBuffer[bufferIndex++] = region.v2;

        verticesBuffer[bufferIndex++] = x2;
        verticesBuffer[bufferIndex++] = y1;
        verticesBuffer[bufferIndex++] = region.u2;
        verticesBuffer[bufferIndex++] = region.v2;

        verticesBuffer[bufferIndex++] = x2;
        verticesBuffer[bufferIndex++] = y2;
        verticesBuffer[bufferIndex++] = region.u2;
        verticesBuffer[bufferIndex++] = region.v1;

        verticesBuffer[bufferIndex++] = x1;
        verticesBuffer[bufferIndex++] = y2;
        verticesBuffer[bufferIndex++] = region.u1;
        verticesBuffer[bufferIndex++] = region.v1;

        numSprites++;
    }

    public void drawSprite(float x, float y, float width, float height, float angle, TextureRegion region){
        float halfWidth = width / 2;
        float halfHeight = height / 2;

        float rad = angle * Vector2.TO_RADIANS;
        float cos = (float)Math.cos(rad);
        float sin = (float)Math.sin(rad);

        float x1 = -halfWidth * cos - (-halfHeight) * sin;
        float y1 = -halfWidth * sin + (-halfHeight) * cos;
        float x2 = halfWidth * cos - (-halfHeight) * sin;
        float y2 = halfWidth * sin + (-halfHeight) * cos;
        float x3 = halfWidth * cos - halfHeight * sin;
        float y3 = halfWidth * sin + halfHeight * cos;
        float x4 = -halfWidth * cos - halfHeight * sin;
        float y4 = -halfWidth * sin + halfHeight * cos;

        x1 += x;
        y1 += y;
        x2 += x;
        y2 += y;
        x3 += x;
        y3 += y;
        x4 += x;
        y4 += y;

        verticesBuffer[bufferIndex++] = x1;
        verticesBuffer[bufferIndex++] = y1;
        verticesBuffer[bufferIndex++] = region.u1;
        verticesBuffer[bufferIndex++] = region.v2;

        verticesBuffer[bufferIndex++] = x2;
        verticesBuffer[bufferIndex++] = y2;
        verticesBuffer[bufferIndex++] = region.u2;
        verticesBuffer[bufferIndex++] = region.v2;

        verticesBuffer[bufferIndex++] = x3;
        verticesBuffer[bufferIndex++] = y3;
        verticesBuffer[bufferIndex++] = region.u2;
        verticesBuffer[bufferIndex++] = region.v1;

        verticesBuffer[bufferIndex++] = x4;
        verticesBuffer[bufferIndex++] = y4;
        verticesBuffer[bufferIndex++] = region.u1;
        verticesBuffer[bufferIndex++] = region.v1;

        numSprites++;
    }

    public void addDynamicGraphicData(float x, float y, float width, float height, float angle,Texture texture, TextureRegion region,
                                   int moveSpeedX, int moveSpeedY, int rotateSpeed, long startFrameTime, long lifeTime, int layer){
        dynamicGraphicDatas.add(new DynamicGraphicData(x, y, width, height, angle,texture , region, moveSpeedX, moveSpeedY, rotateSpeed, startFrameTime, lifeTime, layer));
    }

    public void addDynamicGraphicData(float x, float y, float width, float height, float angle,Texture texture, TextureRegion region, long startFrameTime, int layer){
        dynamicGraphicDatas.add(new DynamicGraphicData(x, y, width, height, angle,texture , region, 0, 0, 0, startFrameTime, 0, layer));
    }

    public void addDynamicGraphicData(float x, float y, float angle, String text,
                                      int moveSpeedX, int moveSpeedY, int rotateSpeed, long startFrameTime, long lifeTime, int layer){
        int len = text.length();
        float textGraphicLen = len * glypWidth;
        float X = x + (textGraphicLen / 2);
        for(int i = 0; i < len ; i++){
            int c = text.charAt(i) - ' ';
            if(c < 0 || c > glyphs.length - 1)
                continue;

            int textX = (int)(X -(X - x) * Math.cos(angle));
            int textY = (int)(y - (X - x)  * Math.sin(angle));

            dynamicGraphicDatas.add(new DynamicGraphicData(textX, textY, glypWidth, glypHeight, angle, Assets.items, glyphs[c], moveSpeedX, moveSpeedY, rotateSpeed, startFrameTime, lifeTime, layer));
            x += glypWidth;
        }
    }

    public void addDynamicGraphicData(float x, float y, float angle, String text, long startFrameTime, int layer){
        int len = text.length();
        float textGraphicLen = len * glypWidth;
        float X = x + (textGraphicLen / 2);
        for(int i = 0; i < len ; i++){
            int c = text.charAt(i) - ' ';
            if(c < 0 || c > glyphs.length - 1)
                continue;

            int textX = (int)(X -(X - x) * Math.cos(angle));
            int textY = (int)(y - (X - x)  * Math.sin(angle));

            dynamicGraphicDatas.add(new DynamicGraphicData(textX, textY, glypWidth, glypHeight, angle, Assets.items, glyphs[c], 0, 0, 0, startFrameTime, 0, layer));
            x += glypWidth;
        }
    }

    public void dynamicGraphicData_Update(long startFrameTime){
        for(int layer_i = 0; layer_i <= 5; layer_i ++) {
            for (int array_i = 0; array_i < dynamicGraphicDatas.size(); array_i++) {
                if(dynamicGraphicDatas.get(array_i).layer == layer_i) {
                    if (dynamicGraphicDatas.get(array_i).update(startFrameTime)) {
                        beginBatch(dynamicGraphicDatas.get(array_i).texture);
                        drawSprite(dynamicGraphicDatas.get(array_i).x, dynamicGraphicDatas.get(array_i).y, dynamicGraphicDatas.get(array_i).width, dynamicGraphicDatas.get(array_i).height,
                                dynamicGraphicDatas.get(array_i).angle, dynamicGraphicDatas.get(array_i).region);
                        endBatch();
                    } else {
                        dynamicGraphicDatas.remove(array_i);
                        array_i--;
                    }
                }
            }
        }
    }
}
