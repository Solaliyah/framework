package framework.gl;

/**フォント
 * Created by SolarisD on 2016/05/19.
 */
public class Font {
    public final Texture texture;
    public final int glypWidth;
    public final int glypHeight;
    public final TextureRegion[] glyphs = new TextureRegion[96];

    public Font(Texture texture, int offsetX,int offsetY,
                int glypsPerRow, int glypWidth, int glypHeight){
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

    public void drawText(SpriteBatcher batcher,String text, float x, float y){
        int len = text.length();
        for(int i = 0; i < len ; i++){
            int c = text.charAt(i) - ' ';
            if(c < 0 || c > glyphs.length - 1)
                continue;
            TextureRegion glyph = glyphs[c];
            batcher.drawSprite(x, y, glypWidth, glypHeight, glyph);
            x += glypWidth;
        }
    }
}
