package framework.base;

/**
 * 中心となるclassのためのinterface
 * Input,FileIO,Graphics,Audioのゲッターと
 * Screenのセッター、ゲッターを実装させます。
 * @author Solaliyah
 */
public interface Core {
    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public Audio getAudio();
    /**
     * 新しいScreenに切り替えます。
     * @param screen    切り替えたいScreen
     */
    public void setScreen(Screen screen);
    /**
     * 現在セットされているScreenを返します。
     * @return  現在のScreen
     */
    public Screen getCurrentScreen();
    /**
     * 最初にScreenをセットする時に使います。
     * @return  最初のScreen
     */
    public Screen getStartScreen();
}
