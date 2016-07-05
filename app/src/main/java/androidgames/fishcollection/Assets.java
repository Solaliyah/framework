package androidgames.fishcollection;

import framework.base.Music;
import framework.base.Sound;
import framework.gl.Font;
import framework.gl.Texture;
import framework.gl.TextureRegion;
import framework.impl.GLGame;

/**
 * Created by SolarisD on 2016/05/19.
 */
public class Assets {
    public static Texture background;
    public static TextureRegion backgroundRegion;
    public static Texture fishing;
    public static Texture suppoter;
    public static Texture frame;
    public static Texture sidebar;
    public static TextureRegion fishingRegion;
    public static TextureRegion suppoterA_Region;
    public static TextureRegion suppoterB_Region;
    public static TextureRegion suppoterC_Region;
    public static TextureRegion suppoterD_Region;
    public static TextureRegion leveeRegion;
    public static TextureRegion frameRegion;
    public static TextureRegion sidebarRegion;
    public static Texture items;
    public static TextureRegion pauseMenu;
    public static TextureRegion readyRegion;
    public static TextureRegion pause;
    public static TextureRegion mainMenu;
    public static TextureRegion soundOn;
    public static TextureRegion soundoff;
    public static Font font;
    public static Music music;
    public static Sound clickSound;

    public static void load(GLGame game){
        background = new Texture(game, "background.png");
        fishing = new Texture(game, "fishing.png");
        suppoter = new Texture(game, "Suppoter.png");
        frame = new Texture(game, "frame.png");
        sidebar = new Texture(game, "sidebar.png");
        items = new Texture(game, "items.png");

        backgroundRegion = new TextureRegion(background, 0, 0, 1920, 2880);
        fishingRegion = new TextureRegion(fishing, 0, 0, 256, 256);
        suppoterA_Region = new TextureRegion(suppoter, 0, 0, 128, 128);
        suppoterB_Region = new TextureRegion(suppoter, 128, 0, 128, 128);
        suppoterC_Region = new TextureRegion(suppoter, 0, 128, 128, 128);
        suppoterD_Region = new TextureRegion(suppoter, 128, 128, 128, 128);
        frameRegion = new TextureRegion(frame, 0, 0, 128, 128);
        sidebarRegion = new TextureRegion(sidebar, 0, 0, 128, 128);
        pauseMenu = new TextureRegion(items, 224, 128, 192, 96);
        readyRegion = new TextureRegion(items, 320, 224, 192, 32);
        pause = new TextureRegion(items, 64, 64, 64, 64);
        mainMenu = new TextureRegion(items, 0, 224, 300, 110);
        soundOn = new TextureRegion(items, 64, 0, 64, 64);
        soundoff = new TextureRegion(items, 0, 0, 64, 64);

        font = new Font(items, 224, 0, 16, 16, 20);

        music = game.getAudio().newMusic("music.mp3");
        music.setLooping(true);
        music.setVolume(0.5f);

        clickSound = game.getAudio().newSound("click.ogg");
    }

    public static void reload(){
        background.reload();
        items.reload();
        fishing.reload();
        suppoter.reload();
        frame.reload();
        sidebar.reload();
        if(Settings.soundEnabled)
            music.play();
    }

    public static void playSound(Sound sound){
        if(Settings.soundEnabled)
            sound.play(1);
    }
}
