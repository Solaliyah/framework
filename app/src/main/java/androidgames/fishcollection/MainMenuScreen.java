package androidgames.fishcollection;

import javax.microedition.khronos.opengles.GL10;

import framework.base.Core;
import framework.base.Input.TouchEvent;
import framework.gl.Camera2D;
import framework.gl.SpriteBatcher;
import framework.impl.GLScreen;
import framework.math.OverlapTester;
import framework.math.Rectangle;
import framework.math.Vector2;

/**
 * Created by SolarisD on 2016/05/19.
 */
public class MainMenuScreen extends GLScreen {
    Camera2D guiCam;
    SpriteBatcher batcher;
    Rectangle soundsBounds;
    Rectangle playBounds;
    Rectangle networkLoadBounds;
    Rectangle helpBounds;
    Rectangle creditBounds;
    Vector2 touchPoint;

    public MainMenuScreen(Core core){
        super(core);
        guiCam = new Camera2D(glGraphics, 320, 480);
        batcher = new SpriteBatcher(glGraphics, 100);
        soundsBounds = new Rectangle(0, 0, 64, 64);
        playBounds = new Rectangle(320 - 150, 400 + 18, 300, 36);
        networkLoadBounds = new Rectangle(320 - 150, 400 - 18, 300, 36);
        helpBounds = new Rectangle(320 - 150, 400 - 18 - 36, 300, 36);
        creditBounds = new Rectangle(320 - 150, 400 - 18 - 72, 300, 36);
        touchPoint = new Vector2();
    }

    @Override
    public void update(long startFrameTime, long deltaTime){
       TouchEvent touchEvent = core.getInput().getTouchEvents();
        core.getInput().getKeyEvents();

            if(touchEvent.type == TouchEvent.TOUCH_SINGLETAP_UP){
                touchPoint.set(touchEvent.x,touchEvent.y);
                guiCam.touchToWorld(touchPoint);

                if(OverlapTester.pointInRectangle(playBounds, touchPoint)){
                    Assets.playSound(Assets.clickSound);
                    core.setScreen(new GameScreen(core));
                    return;
                }

                if(OverlapTester.pointInRectangle(soundsBounds, touchPoint)){
                    Assets.playSound(Assets.clickSound);
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if(Settings.soundEnabled)
                        Assets.music.play();
                    else
                        Assets.music.pause();
                }

        }
    }

    @Override
    public void present(long startFrameTime, long deltaTime){
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        batcher.beginBatch(Assets.background);
        batcher.drawSprite(320, 480, 640, 960, Assets.backgroundRegion);
        batcher.endBatch();

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        batcher.beginBatch(Assets.items);

        batcher.drawSprite(320, 400, 300, 110, Assets.mainMenu);
        batcher.drawSprite(32, 32, 64, 64, Settings.soundEnabled ? Assets.soundOn : Assets.soundoff);

        batcher.endBatch();

        gl.glDisable(GL10.GL_BLEND);
    }

    @Override
    public void pause(){
    }

    @Override
    public void resume(){
    }

    @Override
    public void dispose(){
    }
}
