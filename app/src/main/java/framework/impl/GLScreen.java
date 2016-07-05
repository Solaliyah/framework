package framework.impl;

import framework.base.Core;
import framework.base.Screen;

/**
 * Created by SolarisD on 2016/05/19.
 */
public abstract class GLScreen extends Screen {
    protected final GLGraphics glGraphics;
    protected final GLGame glGame;

    public GLScreen(Core core){
        super(core);
        glGame = (GLGame)core;
        glGraphics = ((GLGame)core).getGLGraphics();
    }
}
