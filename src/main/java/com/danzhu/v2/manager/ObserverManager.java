package com.danzhu.v2.manager;

import com.danzhu.v2.GameContext;
import com.danzhu.v2.view.GameObserver;

import java.util.ArrayList;
import java.util.List;

public class ObserverManager {
    private List<GameObserver> OBSERVERS = new ArrayList<>();


    public void addObserver(GameObserver score) {
        OBSERVERS.add(score);
    }
    public void notifyObservers(GameContext gameContext) {
        for (GameObserver observer : OBSERVERS) {
            observer.update(gameContext);
        }
    }
}
