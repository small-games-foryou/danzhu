package com.danzhu.v2.manager;

import com.danzhu.v2.consts.GameConsts;
import com.danzhu.v2.dto.Che;

public class CheManager {
    private static CheManager instance;
    public static synchronized CheManager getInstance() {
        if (instance == null) {
            instance = new CheManager();
        }
        return instance;
    }
    public void moveLeft(Che che) {
        int i = che.getX() -  che.getWidth();
        i = Math.max(i, 0);
        che.setX(i);
        AudioManager.playMove(1);
    }

    public void moveRight(Che che) {
        int i =  che.getX() +  che.getHeight();
        i = Math.min(i, (GameConsts.BLOCK_MAX_X - 2) * GameConsts.BLOCK_WIDTH);
        che.setX(i);
        AudioManager.playMove(1);
    }
}
