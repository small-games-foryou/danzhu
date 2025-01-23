package com.danzhu.v2.factory;

import com.danzhu.v2.GameContext;
import com.danzhu.v2.view.MainFrame;

public interface IFrameFactory {
    MainFrame createFrame(GameContext gameContext);
}
