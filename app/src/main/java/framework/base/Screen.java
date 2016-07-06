package framework.base;

/**
※ 画面を管理するinterfaceです。
 * @auther Solaliyah
 */
public abstract class Screen {
    protected final Core core;

    public Screen(Core core) {
        this.core = core;
    }
/※※
※ ロジック、計算用のメソッドです
※@param startFrameTime 現在のフレームが始まった時点の時間です。
※@param deltaTime 前フレームから何ns経過したかの時間です。
※/
    public abstract void update(long startFrameTime, long deltaTime);
/※※
※ 描画用のメソッドです
※@param startFrameTime 現在のフレームが始まった時点の時間です。
※@param deltaTime 前フレームから何ns経過したかの時間です。
※/
    public abstract void present(long startFrameTime, long deltaTime);
/※※ TODO 修正
※ onPause時に呼ばれるabstractメソッドです。
※/
    public abstract void pause();
/※※
※ onResume時に呼ばれるabstractメソッドです。
※/
    public abstract void resume();
/※※
※ onPause時に呼ばれるabstractメソッドです。
※/
    public abstract void dispose();
}
