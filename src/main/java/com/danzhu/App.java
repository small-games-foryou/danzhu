package com.danzhu;


import com.danzhu.v2.GameContext;
import com.danzhu.v2.factory.MainFrameFactory;
import com.danzhu.v2.view.MainFrame;
import com.danzhu.v2.hander.GameEventHandler;
import com.danzhu.v2.manager.GameSetupManager;

public class App {
    public static void main(String[] args) {
        MainFrameFactory factory = MainFrameFactory.getInstance();
        GameContext gameContext = GameContext.getInstance();
        MainFrame frame = factory.createFrame(gameContext);
        GameSetupManager gameSetupManager = new GameSetupManager();
        GameEventHandler eventHandler = new GameEventHandler();
        gameSetupManager.setup(frame, gameContext,eventHandler);
    }


}
