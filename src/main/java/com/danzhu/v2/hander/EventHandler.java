package com.danzhu.v2.hander;

import com.danzhu.v2.consts.Level;
import com.danzhu.v2.dto.Che;


public interface EventHandler {
    void startGame();

    void cheMove(int keyCode, Che che);

    void gamePause();

    void levelChange(Level level);

    void endGame();

    void addScore();

    void win();
}
