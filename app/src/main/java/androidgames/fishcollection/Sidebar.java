package androidgames.fishcollection;

import javax.microedition.khronos.opengles.GL10;

import framework.base.Core;
import framework.gl.Camera2D;
import framework.gl.SpriteBatcher;
import framework.math.OverlapTester;
import framework.math.Rectangle;
import framework.math.Vector2;

/**
 * Created by SolarisD on 2016/05/24.
 */
public class Sidebar {

    SpriteBatcher batcher;
    Vector2 touchPoint;

    static final int SIDEBAR_CLOSE = 1;
    static final int SIDEBAR_OPEN = 10;
    static final int SIDEBAR_SHOP = 100;
    static final int SIDEBAR_EQUIP = 200;
    static final int SIDEBAR_SUPPOTER = 300;
    static final int SIDEBAR_ACHIEVEMENT = 400;
    static final int SIDEBAR_OPTION = 500;
    static final int SIDEBAR_SHOP_AITEM = 1001;
    static final int SIDEBAR_SHOP_BITEM = 1002;
    static final int SIDEBAR_SHOP_CITEM = 1003;
    static final int SIDEBAR_SHOP_DITEM = 1004;
    static final int SIDEBAR_ACHIEVEMENT_FISHGUIDE = 4001;
    static final int SIDEBAR_ACHIEVEMENT_ACHIEVE = 4002;
    static final int SIDEBAR_ACHIEVEMENT_ = 4003;

    Rectangle Sidebar_Open_Bounds;
    Rectangle A_Bounds;
    Rectangle B_Bounds;
    Rectangle C_Bounds;
    Rectangle D_Bounds;
    Rectangle E_Bounds;
    CulSupporter culSupporter;
    Core core;
    States states;
    int sidebarState;


    public Sidebar(SpriteBatcher batcher, Vector2 touchPoint, Core core){

        this.batcher = batcher;
        this.touchPoint = touchPoint;
        this.core = core;
        Sidebar_Open_Bounds = new Rectangle(640 - 128, 960 - 128, 128, 128);
        A_Bounds = new Rectangle(640 - 128, 960 - 128 * 2, 128, 128);
        B_Bounds = new Rectangle(640 - 128, 960 - 128 * 3, 128, 128);
        C_Bounds = new Rectangle(640 - 128, 960 - 128 * 4, 128, 128);
        D_Bounds = new Rectangle(640 - 128, 960 - 128 * 5, 128, 128);
        E_Bounds = new Rectangle(640 - 128, 960 - 128 * 6, 128, 128);
        culSupporter = new CulSupporter();
        states = new States();
        sidebarState = SIDEBAR_CLOSE;
    }

    public boolean Update(States states, Camera2D guiCam, long startFrameTime){
        long upgradeCost;
        float positioningX = guiCam.position.x - 320;
        float positioningY = guiCam.position.y - 480;
        Sidebar_Open_Bounds.setAll(positioningX + 640 - 128,positioningY + 960 - 128, 128, 128);
        A_Bounds.setAll(positioningX + 640 - 128,positioningY + 960 - 128 * 2, 128, 128);
        B_Bounds.setAll(positioningX + 640 - 128,positioningY + 960 - 128 * 3, 128, 128);
        C_Bounds.setAll(positioningX + 640 - 128,positioningY + 960 - 128 * 4, 128, 128);
        D_Bounds.setAll(positioningX + 640 - 128,positioningY + 960 - 128 * 5, 128, 128);
        E_Bounds.setAll(positioningX + 640 - 128,positioningY + 960 - 128 * 6, 128, 128);

        switch (sidebarState) {
            case SIDEBAR_CLOSE:
                if (OverlapTester.pointInRectangle(Sidebar_Open_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState = SIDEBAR_OPEN;
                    return true;
                }
                if (OverlapTester.pointInRectangle(D_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    guiCam.zoom = 3.0f;
                    return true;
                }
                if (OverlapTester.pointInRectangle(E_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    guiCam.zoom = 2.0f;
                    return true;
                }
                break;
            case SIDEBAR_OPEN:
                if (OverlapTester.pointInRectangle(A_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState = SIDEBAR_SHOP;
                    return true;
                }
                if (OverlapTester.pointInRectangle(B_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState = SIDEBAR_EQUIP;
                    return true;
                }
                if (OverlapTester.pointInRectangle(C_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState = SIDEBAR_SUPPOTER;
                    return true;
                }
                if (OverlapTester.pointInRectangle(D_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState = SIDEBAR_ACHIEVEMENT;
                    return true;
                }
                if (OverlapTester.pointInRectangle(E_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState = SIDEBAR_OPTION;
                    return true;
                }
                break;
            case SIDEBAR_SHOP:
                if (OverlapTester.pointInRectangle(A_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState = SIDEBAR_SHOP_AITEM;
                    return true;
                }
                if (OverlapTester.pointInRectangle(B_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState = SIDEBAR_SHOP_BITEM;
                    return true;
                }
                if (OverlapTester.pointInRectangle(C_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState =  SIDEBAR_SHOP_CITEM;
                    return true;
                }
                if (OverlapTester.pointInRectangle(D_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState =  SIDEBAR_SHOP_DITEM;
                    return true;
                }
                break;
            case SIDEBAR_EQUIP:
                if (OverlapTester.pointInRectangle(A_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    batcher.addDynamicGraphicData(128, 128, 128, 128, 45, Assets.sidebar, Assets.sidebarRegion, 2, 2, 2, startFrameTime, 2000000000, 5);
                    sidebarState =  SIDEBAR_EQUIP;
                    return true;
                }
                if (OverlapTester.pointInRectangle(B_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    batcher.addDynamicGraphicData(128, 128, 45, "TESTtestTEST", 2, 2, 0, startFrameTime, 2000000000, 5);
                    sidebarState =  SIDEBAR_EQUIP;
                    return true;
                }
                if (OverlapTester.pointInRectangle(C_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState =  SIDEBAR_EQUIP;
                    return true;
                }
                if (OverlapTester.pointInRectangle(D_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState =  SIDEBAR_EQUIP;
                    return true;
                }
                break;
            case SIDEBAR_SUPPOTER:
                if (OverlapTester.pointInRectangle(A_Bounds, touchPoint)) {
                    upgradeCost = culSupporter.culCost(0, states.getSupporter());
                    if (states.getCashe() >= upgradeCost) {
                        states.getSupporter()[0]++;
                        states.addCashe( -upgradeCost);
                    }
                    Assets.playSound(Assets.clickSound);
                    sidebarState =  SIDEBAR_SUPPOTER;
                    return true;
                }
                if (OverlapTester.pointInRectangle(B_Bounds, touchPoint)) {
                    upgradeCost = culSupporter.culCost(1, states.getSupporter());
                    if (states.getCashe() >= upgradeCost) {
                        states.getSupporter()[1]++;
                        states.addCashe( -upgradeCost);
                    }
                    Assets.playSound(Assets.clickSound);
                    sidebarState =  SIDEBAR_SUPPOTER;
                    return true;
                }
                if (OverlapTester.pointInRectangle(C_Bounds, touchPoint)) {
                    upgradeCost = culSupporter.culCost(2, states.getSupporter());
                    if (states.getCashe() >= upgradeCost) {
                        states.getSupporter()[2]++;
                        states.addCashe( -upgradeCost);
                    }
                    Assets.playSound(Assets.clickSound);
                    sidebarState =  SIDEBAR_SUPPOTER;
                    return true;
                }
                if (OverlapTester.pointInRectangle(D_Bounds, touchPoint)) {
                    upgradeCost = culSupporter.culCost(3, states.getSupporter());
                    if (states.getCashe() >= upgradeCost) {
                        states.getSupporter()[3]++;
                        states.addCashe( -upgradeCost);
                    }
                    Assets.playSound(Assets.clickSound);
                    sidebarState =  SIDEBAR_SUPPOTER;
                    return true;
                }
                break;
            case SIDEBAR_ACHIEVEMENT:
                if (OverlapTester.pointInRectangle(A_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState =  SIDEBAR_ACHIEVEMENT_FISHGUIDE;
                    return true;
                }
                if (OverlapTester.pointInRectangle(B_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState =  SIDEBAR_ACHIEVEMENT_ACHIEVE;
                    return true;
                }
                if (OverlapTester.pointInRectangle(C_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState =  SIDEBAR_ACHIEVEMENT;
                    return true;
                }
                if (OverlapTester.pointInRectangle(D_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    sidebarState =  SIDEBAR_ACHIEVEMENT;
                    return true;
                }
                break;
            case SIDEBAR_OPTION:
                if (OverlapTester.pointInRectangle(A_Bounds, touchPoint)) {
                    Settings.save(core.getFileIO());
                    Assets.playSound(Assets.clickSound);
                    sidebarState =  SIDEBAR_OPTION;
                    return true;
                }
                if (OverlapTester.pointInRectangle(B_Bounds, touchPoint)) {
                    Settings.load(core.getFileIO());
                    Assets.playSound(Assets.clickSound);
                    sidebarState =  SIDEBAR_OPTION;
                    return true;
                }
                if (OverlapTester.pointInRectangle(C_Bounds, touchPoint)) {
                    Settings.dataClear(core.getFileIO());
                    Assets.playSound(Assets.clickSound);
                    sidebarState =  SIDEBAR_OPTION;
                    return true;
                }
                break;
            case SIDEBAR_SHOP_AITEM:
                if (OverlapTester.pointInRectangle(A_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    if(!States.shopItem[0] && States.cashe >= 100) {
                        States.shopItem[0] = true;
                        States.cashe -= 100;
                    }
                    return true;
                }
                if (OverlapTester.pointInRectangle(B_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    if(States.shopItem[0]) {
                        States.shopItem[0] = false;
                        States.cashe += 100;
                    }
                    return true;
                }
                break;
            case SIDEBAR_SHOP_BITEM:
                if (OverlapTester.pointInRectangle(A_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    if(!States.shopItem[1] && States.cashe >= 1000) {
                        States.shopItem[1] = true;
                        States.cashe -= 1000;
                    }
                    return true;
                }
                if (OverlapTester.pointInRectangle(B_Bounds, touchPoint)) {
                    Assets.playSound(Assets.clickSound);
                    if(States.shopItem[1]) {
                        States.shopItem[1] = false;
                        States.cashe += 1000;
                    }
                    return true;
                }
                break;
        }

        if (sidebarState != SIDEBAR_CLOSE && OverlapTester.pointInRectangle(Sidebar_Open_Bounds, touchPoint)) {
            int backState = (int)Math.log10(sidebarState) + 1;
            Assets.playSound(Assets.clickSound);
            sidebarState =  (int)(Math.pow(10, backState - 1) * (sidebarState / Math.pow(10, backState)));
            if(backState == 2)
                sidebarState =  1;
            if(backState == 3)
                sidebarState =  10;

            return true;
        }

        return false;
    }

    public void preset(States states, GL10 gl, Camera2D guiCam, long startFrameTime){
        float positioningX = guiCam.position.x - 320;
        float positioningY = guiCam.position.y - 480;


        switch (sidebarState){
            case SIDEBAR_CLOSE:
                //  フレーム
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 4, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 5, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                //　ボタンテキスト
                batcher.addDynamicGraphicData(positioningX + 640 - 120, positioningY + 960 -64 - 128 * 4, 0, "PLUS", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 104, positioningY + 960 -64 - 128 * 5, 0, "MINUS", startFrameTime, 5);
                //　sidebarバックボタン
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64, 128, 128, 270,
                        Assets.sidebar, Assets.sidebarRegion, startFrameTime, 4);

                break;
            case SIDEBAR_OPEN:
                //  フレーム
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 , 128, 128, 0,
                                                Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 2, 128, 128, 0,
                                                Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 3, 128, 128, 0,
                                                Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 4, 128, 128, 0,
                                                Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 5, 128, 128, 0,
                                                Assets.frame, Assets.frameRegion, startFrameTime, 4);
                //　ボタンテキスト
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128, 0, "SHOP", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128 * 2, 0, "EQUIP", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 112, positioningY + 960 -64 - 128 * 3, 0, "SUPPOTER", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 120, positioningY + 960 -64 - 128 * 4, 0, "ACHIEVEMENT", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 104, positioningY + 960 -64 - 128 * 5, 0, "OPTION", startFrameTime, 5);
                //　sidebarバックボタン
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64, 128, 128, 270,
                        Assets.sidebar, Assets.sidebarRegion, startFrameTime, 4);
                break;
            case SIDEBAR_SHOP:
                //  フレーム
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 , 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 2, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 3, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 4, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                //　ボタンテキスト
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128, 0, "ITEMA", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128 * 2, 0, "ITEMB", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128 * 3, 0, "ITEMC", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128 * 4, 0, "ITEMD", startFrameTime, 5);
                //　sidebarバックボタン
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64, 128, 128, 270,
                        Assets.sidebar, Assets.sidebarRegion, startFrameTime, 0);
                break;
            case SIDEBAR_EQUIP:
                //  フレーム
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 , 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 2, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 3, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 4, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                //　sidebarバックボタン
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64, 128, 128, 270,
                        Assets.sidebar, Assets.sidebarRegion, startFrameTime, 4);
                break;
            case SIDEBAR_SUPPOTER:
                //  フレーム
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 , 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 2, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 3, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 4, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                //　ボタングラフィック
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 , 128, 128, 0,
                        Assets.suppoter, Assets.suppoterA_Region, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 2, 128, 128, 0,
                        Assets.suppoter, Assets.suppoterB_Region, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 3, 128, 128, 0,
                        Assets.suppoter, Assets.suppoterC_Region, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 4, 128, 128, 0,
                        Assets.suppoter, Assets.suppoterD_Region, startFrameTime, 4);
                //　ボタンテキスト
                for(int sup_i = 0; sup_i < 4; sup_i ++) {
                    batcher.addDynamicGraphicData(positioningX + 640 - 118, positioningY + 960 - 224 - 128 * sup_i, 0, String.valueOf(states.getSupporter()[sup_i]), startFrameTime, 5);
                    batcher.addDynamicGraphicData(positioningX + 640 - 118, positioningY + 960 - 160 - 128 * sup_i, 0, String.valueOf(culSupporter.culCost(sup_i, states.getSupporter())), startFrameTime, 5);
                    batcher.addDynamicGraphicData(positioningX + 640 - 118 + 68, positioningY + 960 - 160 - 128 * sup_i, 0, String.valueOf(culSupporter.supporterPPS[sup_i]), startFrameTime, 5);
                }
                //　sidebarバックボタン
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64, 128, 128, 270,
                        Assets.sidebar, Assets.sidebarRegion, startFrameTime, 4);
                break;
            case SIDEBAR_ACHIEVEMENT:
                //  フレーム
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 , 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 2, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 3, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                //　ボタンテキスト
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128, 0, "FISH", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 112, positioningY + 960 -64 - 128 * 2, 0, "ACHIEVEMENT", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128 * 3, 0, "OTHER", startFrameTime, 5);
                //　sidebarバックボタン
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64, 128, 128, 270,
                        Assets.sidebar, Assets.sidebarRegion, startFrameTime, 4);
                break;
            case SIDEBAR_OPTION:
                //  フレーム
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 , 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 2, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 3, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                //　ボタンテキスト
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128, 0, "SAVE", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128 * 2, 0, "LOAD", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 112, positioningY + 960 -64 - 128 * 3, 0, "DATA CLEAR", startFrameTime, 5);
                //　sidebarバックボタン
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64, 128, 128, 270,
                        Assets.sidebar, Assets.sidebarRegion, startFrameTime, 4);
                break;
            case SIDEBAR_SHOP_AITEM:
                //  フレーム
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 , 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 2, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                //　ボタンテキスト
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128, 0, "Buy", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128 * 2, 0, "Sell", startFrameTime, 5);
                //　アイテム説明
                batcher.addDynamicGraphicData(positioningX + 120, positioningY + 960 -64 - 128, 0, "ExplanationAITEM", startFrameTime, 5);
                //　sidebarバックボタン
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64, 128, 128, 270,
                        Assets.sidebar, Assets.sidebarRegion, startFrameTime, 4);
                break;
            case SIDEBAR_SHOP_BITEM:
                //  フレーム
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 , 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 2, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                //　ボタンテキスト
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128, 0, "Buy", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128 * 2, 0, "Sell", startFrameTime, 5);
                //　アイテム説明
                batcher.addDynamicGraphicData(positioningX + 120, positioningY + 960 -64 - 128, 0, "ExplanationBITEM", startFrameTime, 5);
                //　sidebarバックボタン
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64, 128, 128, 270,
                        Assets.sidebar, Assets.sidebarRegion, startFrameTime, 4);
                break;
            case SIDEBAR_SHOP_CITEM:
                //  フレーム
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 , 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 2, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                //　ボタンテキスト
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128, 0, "Buy", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128 * 2, 0, "Sell", startFrameTime, 5);
                //　アイテム説明
                batcher.addDynamicGraphicData(positioningX + 120, positioningY + 960 -64 - 128, 0, "ExplanationCITEM", startFrameTime, 5);
                //　sidebarバックボタン
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64, 128, 128, 270,
                        Assets.sidebar, Assets.sidebarRegion, startFrameTime, 4);
                break;
            case SIDEBAR_SHOP_DITEM:
                //  フレーム
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 , 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 2, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                //　ボタンテキスト
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128, 0, "Buy", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128 * 2, 0, "Sell", startFrameTime, 5);
                //　アイテム説明
                batcher.addDynamicGraphicData(positioningX + 120, positioningY + 960 -64 - 128, 0, "ExplanationDITEM", startFrameTime, 5);
                //　sidebarバックボタン
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64, 128, 128, 270,
                        Assets.sidebar, Assets.sidebarRegion, startFrameTime, 4);
                break;
            case SIDEBAR_ACHIEVEMENT_FISHGUIDE:
                //  フレーム
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 , 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 2, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 3, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64 - 128 * 4, 128, 128, 0,
                        Assets.frame, Assets.frameRegion, startFrameTime, 4);
                //　ボタンテキスト
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128, 0, "~16", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 96, positioningY + 960 -64 - 128 * 2, 0, "~32", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 112, positioningY + 960 -64 - 128 * 3, 0, "~48", startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 112, positioningY + 960 -64 - 128 * 4, 0, "~64", startFrameTime, 5);
                //　fishGuideの表示
                batcher.addDynamicGraphicData(positioningX + 640 - 120 - 120, positioningY + 960 -64 - 128, 0, String.valueOf(states.getFishGuide(0)), startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 120 - 120, positioningY + 960 -64 - 128 * 2, 0, String.valueOf(states.getFishGuide(1)), startFrameTime, 5);
                batcher.addDynamicGraphicData(positioningX + 640 - 120 - 120, positioningY + 960 -64 - 128 * 3, 0,String.valueOf(states.getFishGuide(2)), startFrameTime, 5);

                //　sidebarバックボタン
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64, 128, 128, 270,
                        Assets.sidebar, Assets.sidebarRegion, startFrameTime, 4);

                break;
            default:
                batcher.addDynamicGraphicData(positioningX + 640 - 64, positioningY + 960 -64, 128, 128, 0, Assets.sidebar, Assets.sidebarRegion, startFrameTime, 0);
        }
    }

}
