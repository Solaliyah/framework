package androidgames.fishcollection;

import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

import framework.base.Core;
import framework.base.Input.TouchEvent;
import framework.gl.Camera2D;
import framework.gl.FPSCounter;
import framework.gl.SpriteBatcher;
import framework.impl.GLScreen;
import framework.math.OverlapTester;
import framework.math.Rectangle;
import framework.math.Vector2;

/**
 * Created by SolarisD on 2016/05/20.
 */
public class GameScreen extends GLScreen {
    static final int GAME_READY = 0;
    static final int GAME_RUNNING = 1;

    static final int GACHA_FREE = 0;
    static final int GACHA_USE = 1;

    Random random;

    CulSupporter culSupporter;
    int gameState;
    int gachaState;
    Camera2D guiCam;
    Vector2 touchPoint;
    SpriteBatcher batcher;
    Rectangle soundsBounds;
    Rectangle gachaBounds;
    Rectangle supA_Bounds;
    Rectangle supB_Bounds;
    Rectangle supC_Bounds;
    Rectangle supD_Bounds;
    Rectangle shop_Bounds;
    Rectangle equip_Bounds;
    Rectangle supporter_Bounds;
    Rectangle x10_Bounds;
    float stateTime;
    Sidebar sidebar;
    String casheString;
    FPSCounter fpsCounter;
    States states;
    enum HoldState{
        Nothing,
        SupportA,
        SupportB,
        SupportC,
        SupportD,
        x10Switch
        }
    HoldState holdState = HoldState.Nothing;

    public GameScreen(Core core){
        super(core);
        random = new Random();
        states = new States();
        gameState = GAME_READY;
        gachaState = GACHA_FREE;
        guiCam = new Camera2D(glGraphics, 320, 480);
        touchPoint = new Vector2();
        batcher = new SpriteBatcher(glGraphics, 1000, Assets.items, 224, 0, 16, 16, 20);
        soundsBounds = new Rectangle(0, 0, 64, 64);
        gachaBounds = new Rectangle(320 - 96, 480 - 96, 192, 192);
        supA_Bounds = new Rectangle(640 - 128, 0, 128, 128);
        supB_Bounds = new Rectangle(640 - 128, 128, 128, 128);
        supC_Bounds = new Rectangle(640 - 128, 128 * 2, 128, 128);
        supD_Bounds = new Rectangle(640 - 128, 128 * 3, 128, 128);
        shop_Bounds = new Rectangle(0, 0, 128, 128);
        equip_Bounds = new Rectangle(128, 0, 128, 128);
        supporter_Bounds = new Rectangle(256, 0, 128, 128);
        culSupporter = new CulSupporter();
        stateTime = 0;
        sidebar = new Sidebar(batcher, touchPoint, core);
        casheString = "cache: ";
        fpsCounter = new FPSCounter();
    }

    @Override
    public void update(long startFrameTime, float deltaTime) {
        if (deltaTime > 0.1f)
            deltaTime = 0.1f;

        switch (gameState) {
            case GAME_READY:
                updateReady();
                break;
            case GAME_RUNNING:
                updateRunning(startFrameTime, deltaTime);
                break;
        }
    }

    private void updateReady() {
        gameState = GAME_RUNNING;
    }

    private void updateRunning(long startFrameTime, float deltaTime){
        long upgradeCost;
        holdState = HoldState.Nothing;
        states.addCashe(culSupporter.update(states));
        TouchEvent touchEvent = core.getInput().getTouchEvents();

        if(gachaState == GACHA_USE)
            return;

                TouchEvent event = touchEvent;

                if (event.type == TouchEvent.TOUCH_LONG_HOLD || event.type == TouchEvent.TOUCH_HOLD) {

                    touchPoint.set(event.x, event.y);
                    guiCam.touchToWorld(touchPoint);

                    if (OverlapTester.pointInRectangle(supA_Bounds, touchPoint)) {
                        //Assets.playSound(Assets.clickSound);
                        holdState = HoldState.SupportA;
                        return;
                    }
                    if (OverlapTester.pointInRectangle(supB_Bounds, touchPoint)) {
                        //Assets.playSound(Assets.clickSound);
                        holdState = HoldState.SupportB;
                        return;
                    }
                    if (OverlapTester.pointInRectangle(supC_Bounds, touchPoint)) {
                        //Assets.playSound(Assets.clickSound);
                        holdState = HoldState.SupportC;
                        return;
                    }
                    if (OverlapTester.pointInRectangle(supD_Bounds, touchPoint)) {
                        //Assets.playSound(Assets.clickSound);
                        holdState = HoldState.SupportD;
                        return;
                    }
                }

                if (event.type == TouchEvent.TOUCH_DRAGGED ) {
                    touchPoint.set(event.x, event.y);
                    guiCam.touchToWorld(touchPoint);
                    guiCam.distanceToWorld(touchEvent);
                    if(guiCam.position.x + touchEvent.distanceX > 640 + 320) {
                        touchEvent.distanceX = 0;
                        guiCam.position.x = 640 + 320;
                    }
                    if(guiCam.position.x + touchEvent.distanceX < 0 - 320) {
                        touchEvent.distanceX = 0;
                        guiCam.position.x = -320;
                    }
                    if(guiCam.position.y - touchEvent.distanceY > 960 + 480) {
                        touchEvent.distanceY = 0;
                        guiCam.position.y = 960 + 480;
                    }
                    if(guiCam.position.y - touchEvent.distanceY <  0 - 480) {
                        touchEvent.distanceY = 0;
                        guiCam.position.y = -480;
                    }

                    guiCam.position.add( touchEvent.distanceX, -touchEvent.distanceY);
                }
                if (event.type == TouchEvent.TOUCH_SINGLETAP_UP) {

                    touchPoint.set(event.x, event.y);
                    guiCam.touchToWorld(touchPoint);
                    float positioningX = guiCam.position.x - 320;
                    float positioningY = guiCam.position.y - 480;
                    soundsBounds.setAll(positioningX , positioningY, 128, 128);

                    if(sidebar.Update(states, guiCam, startFrameTime))
                        return;

                    if (OverlapTester.pointInRectangle(gachaBounds, touchPoint)) {
                        Assets.playSound(Assets.clickSound);
                        gachaState = GACHA_USE;
                        return;
                    }



                    if (OverlapTester.pointInRectangle(soundsBounds, touchPoint)) {
                        Assets.playSound(Assets.clickSound);
                        Settings.soundEnabled = !Settings.soundEnabled;
                        if (Settings.soundEnabled)
                            Assets.music.play();
                        else
                            Assets.music.pause();
                    }
                }


    }

    @Override
    public void present(long startFrameTime, float deltaTime){
        GL10 gl = glGraphics.getGL();
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        gl.glEnable(GL10.GL_TEXTURE_2D);
        guiCam.setViewportAndMatrices();
        float positioningX = guiCam.position.x - 320;
        float positioningY = guiCam.position.y - 480;

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        batcher.addDynamicGraphicData(320, 480, 1920, 2880, 0,Assets.background, Assets.backgroundRegion, startFrameTime, 0);

        sidebar.preset(states, gl, guiCam, startFrameTime);

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        if(gachaState == GACHA_USE) {
            stateTime += deltaTime;

            if(stateTime <= 0.20f)
                batcher.addDynamicGraphicData(320, 480, 256, 256, stateTime * 360,Assets.fishing, Assets.fishingRegion, startFrameTime, 1);
            else if(stateTime <= 0.75f){
                batcher.addDynamicGraphicData(320, 480, 256, 256, -(stateTime - 0.20f )* 360 + 72,Assets.fishing, Assets.fishingRegion, startFrameTime, 1);
            }
            if(gachaState == GACHA_USE && stateTime >= 0.75f) {
                int randomFish = random.nextInt(100);
                int addCashe = 0;
                if(randomFish <= 30){
                    addCashe = 30;
                    states.addFishGuide(0, 1);
                }else if(randomFish <= 60){
                    addCashe = 60;
                    states.addFishGuide(1, 1);
                }else if(randomFish <= 100){
                    addCashe = 100;
                    states.addFishGuide(2, 1);
                }

                gachaState = GACHA_FREE;
                batcher.addDynamicGraphicData(320, 320, 0, String.valueOf(addCashe), 0, 2, 0, startFrameTime, 2000000000, 5);
                states.addCashe(addCashe);
                stateTime = 0;

            }
        }else{
            batcher.addDynamicGraphicData(320, 480, 256, 256, 0,Assets.fishing, Assets.fishingRegion, startFrameTime, 4);
        }
        gl.glDisable(GL10.GL_BLEND);

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
        batcher.addDynamicGraphicData(positioningX + 64, positioningY + 64, 64, 64, 0, Assets.items, Settings.soundEnabled ? Assets.soundOn : Assets.soundoff, startFrameTime, 5);

        for(int suppoterNum_i = 0; suppoterNum_i < 4; suppoterNum_i++) {
            for (int line_i = 0; line_i < States.supporter[suppoterNum_i] % 10; line_i++) {
                switch (suppoterNum_i) {
                    case 0:
                        batcher.addDynamicGraphicData(-576 + line_i % 5 * 64, -640 - line_i / 5 * 128, 128, 128, 0, Assets.suppoter, Assets.suppoterA_Region, startFrameTime, 1);
                        break;
                    case 1:
                        batcher.addDynamicGraphicData(-576 + 320 + line_i % 5 * 64, -640 - line_i / 5 * 128, 128, 128, 0, Assets.suppoter, Assets.suppoterB_Region, startFrameTime, 1);
                        break;
                    case 2:
                        batcher.addDynamicGraphicData(-576 + 640 + line_i % 5 * 64, -640 - line_i / 5 * 128, 128, 128, 0, Assets.suppoter, Assets.suppoterC_Region, startFrameTime, 1);
                        break;
                    case 3:
                        batcher.addDynamicGraphicData(-576 + 960 + line_i % 5 * 64, -640 - line_i / 5 * 128, 128, 128, 0, Assets.suppoter, Assets.suppoterD_Region, startFrameTime, 1);
                        break;
                    default:
                        break;
                }
            }
            for (int line_i = 0; line_i < States.supporter[suppoterNum_i] / 10; line_i++) {
                switch (suppoterNum_i) {
                    case 0:
                        batcher.addDynamicGraphicData(-576 + line_i % 5 * 64, -320 - line_i / 5 * 128, 128, 128, 0, Assets.suppoter, Assets.suppoterA_Region, startFrameTime, 1);
                        break;
                    case 1:
                        batcher.addDynamicGraphicData(-576 + 320 + line_i % 5 * 64, -320 - line_i / 5 * 128, 128, 128, 0, Assets.suppoter, Assets.suppoterB_Region, startFrameTime, 1);
                        break;
                    case 2:
                        batcher.addDynamicGraphicData(-576 + 640 + line_i % 5 * 64, -320 - line_i / 5 * 128, 128, 128, 0, Assets.suppoter, Assets.suppoterC_Region, startFrameTime, 1);
                        break;
                    case 3:
                        batcher.addDynamicGraphicData(-576 + 960 + line_i % 5 * 64, -320 - line_i / 5 * 128, 128, 128, 0, Assets.suppoter, Assets.suppoterD_Region, startFrameTime, 1);
                        break;
                    default:
                        break;
                }
            }
        }

        switch (gameState){
            case GAME_READY:
                presentReady(startFrameTime);
                break;
            case GAME_RUNNING:
                presentRunning(startFrameTime);
                break;
        }

        batcher.dynamicGraphicData_Update(startFrameTime);

        gl.glDisable(GL10.GL_BLEND);

        fpsCounter.logFrame();
    }

    private void presentReady(long startFrameTime){
        batcher.addDynamicGraphicData(320, 480, 192, 32, 0, Assets.items, Assets.readyRegion, startFrameTime, 5);
    }

    private void presentRunning(long startFrameTime){
        batcher.addDynamicGraphicData(guiCam.position.x - 320 + 16, guiCam.position.y - 480 + 960 - 20, 0,casheString + String.valueOf(states.getCashe()), startFrameTime, 4);
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

