package com.danzhu.v2.manager;

import com.danzhu.v2.GameContext;
import com.danzhu.v2.view.MainFrame;
import com.danzhu.v2.hander.GameEventHandler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class GameSetupManager {


    public GameSetupManager() {


    }


    public void setup(MainFrame frame, GameContext gameContext, GameEventHandler eventHandler) {
        addFrameListeners(frame, eventHandler);
        initEventHandler( frame,eventHandler);
        initGameContext(gameContext,frame, eventHandler);
    }

    private void initEventHandler(MainFrame frame, GameEventHandler eventHandler) {
        ObserverManager observerManager = new ObserverManager();
        observerManager.addObserver(frame);
        observerManager.addObserver(frame.getScore());
        observerManager.addObserver(frame.getGamePanel());
        eventHandler.setObserverManager(observerManager);
    }

    private void initGameContext(GameContext gameContext, MainFrame frame, GameEventHandler eventHandler) {
        gameContext.setEventHandler(eventHandler);
        gameContext.setChe(frame.getChe());
        gameContext.setDanZhu(frame.getDanZhu());
        gameContext.setBlocks(frame.getBlocks());
    }

    private void addFrameListeners(MainFrame mainFrame, GameEventHandler eventHandler) {
      Set<Integer> pressedKeys = new HashSet<>();
        mainFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
               pressedKeys.remove(e.getKeyCode());
            }
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                pressedKeys.add(e.getKeyCode());
                for (Integer pressedKey : pressedKeys) {
                    eventHandler.cheMove(pressedKey, mainFrame.getChe());
                }

            }
        });
    }
}
