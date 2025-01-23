package com.danzhu.v2;

import com.danzhu.v2.consts.Level;
import com.danzhu.v2.dto.Block;
import com.danzhu.v2.dto.Che;
import com.danzhu.v2.dto.DanZhu;
import com.danzhu.v2.hander.GameEventHandler;
import lombok.Data;
import lombok.val;

import java.util.List;


@Data
public class GameContext {
    private static GameContext instance;
    private volatile boolean running;
    private volatile boolean gameOver;
    private volatile boolean win;
    private volatile int score;
    private volatile boolean threadRun;
    private volatile int speed = Level.SIMPLE.getSpeed();
    private GameEventHandler eventHandler;
    private DanZhu danZhu;
    private Che che;
    private List<Block> blocks;
    private volatile  int rate;
    private GameContext() {
    }

    public static synchronized GameContext getInstance() {
        if (instance == null) {
            instance = new GameContext();
        }
        return instance;
    }


    public void init() {
        this.setRunning(true);
        this.setGameOver(false);
        this.setWin(false);
        this.setScore(0);
    }

    public void resetItems() {
        this.getDanZhu().restart();
        this.getChe().restart();
        for (Block block : this.getBlocks()) {
            block.setAlive(true);
        }
    }

    public int getDanZhuSpeed() {
      return this.getSpeed() + this.getRate();
    }
}
  