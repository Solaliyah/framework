package framework.base;

/**
 * Created by SolarisD on 2016/05/16.
 */
public abstract class Screen {
    protected final Core core;

    public Screen(Core core) {
        this.core = core;
    }

    public abstract void update(long startFrameTime, float deltaTime);

    public abstract void present(long startFrameTime, float deltaTime);

    public abstract void pause();

    public abstract void resume();

    public abstract void dispose();
}
