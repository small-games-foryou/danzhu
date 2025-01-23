package com.danzhu.v2.hander;

import com.danzhu.v2.GameContext;
import com.danzhu.v2.consts.Level;
import com.danzhu.v2.dto.Block;
import com.danzhu.v2.dto.Che;
import com.danzhu.v2.dto.DanZhu;
import com.danzhu.v2.manager.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
@Data
@EqualsAndHashCode(callSuper = false)
public class GameEventHandler implements EventHandler {
    private static final ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
    private final DanZhuManager danZhuManager = DanZhuManager.getInstance();
    private final BlockManager blockManager =BlockManager.getInstance();
    private final CheManager cheManager = CheManager.getInstance();
    private ObserverManager observerManager;
    private ScheduledFuture<?> gameTaskFuture;

    public GameEventHandler() {
    }


    @Override
    public void startGame() {
        GameContext gameContext = GameContext.getInstance();
        if(gameContext.isThreadRun()){
            gameContext.setRunning(true);
            notifyObservers();
            return;
        }
        if(gameTaskFuture!=null&&gameTaskFuture.isDone()){
            gameContext.resetItems();
            gameContext.init();
            startThread(gameContext);
        }else{
            gameContext.init();
            startThread(gameContext);
        }
        AudioManager.playBg(2);

    }

    private void startThread(GameContext gameContext) {
        // 检查线程池是否已关闭
        if (executor.isShutdown() || executor.isTerminated()) {
            throw new IllegalStateException("线程池已关闭，无法调度新任务");
        }
        // 获取游戏速度并检查其有效性
        long speed = gameContext.getSpeed();
        if (speed <= 0) {
            throw new IllegalArgumentException("游戏速度必须大于零");
        }
        EventHandler eventHandler = this;
        gameTaskFuture = executor.scheduleAtFixedRate(() -> {
            if (!gameContext.isRunning()) {
                notifyObservers();
                return;
            }
            gameContext.setThreadRun(true);
            danZhuManager.move(gameContext.getChe(), gameContext.getDanZhu(), eventHandler);
            blockManager.killBlocks(gameContext.getDanZhu(),  gameContext.getBlocks(), eventHandler);
            notifyObservers();
        }, 0, 100L, java.util.concurrent.TimeUnit.MILLISECONDS);
    }

    public void win() {
        AudioManager.playWin(1);
        GameContext gameContext = GameContext.getInstance();
        gameContext.setWin(true);
        gameTaskFuture.cancel(true);
        gameContext.setThreadRun(false);
        gameContext.setRunning(false);
    }


    @Override
    public void cheMove(int keyCode, Che che) {
        switch (keyCode) {
            case 37:
                cheManager.moveLeft(che);
                break;
            case 39:
                cheManager.moveRight(che);
                break;
            case 32:
                GameContext gameContext = GameContext.getInstance();
                gameContext.setRate(gameContext.getSpeed()+10);
                executor.schedule(()->{
                    gameContext.setRate(0);
                },3000,java.util.concurrent.TimeUnit.MILLISECONDS);
            break;
        }
        notifyObservers();
    }

    @Override
    public void gamePause() {
        GameContext gameContext = GameContext.getInstance();
        gameContext.setRunning(false);
        notifyObservers();
    }


    @Override
    public void levelChange(Level level) {
        GameContext gameContext = GameContext.getInstance();
        gameContext.setSpeed(level.getSpeed());
    }

    @Override
    public void endGame() {
        AudioManager.playEnd(1);
        GameContext gameContext = GameContext.getInstance();
        gameContext.setGameOver(true);
        gameContext.setWin(false);
        gameTaskFuture.cancel(true);
        gameContext.setThreadRun(false);
        gameContext.setRunning(false);
        notifyObservers();
    }

    private void notifyObservers() {
        GameContext gameContext = GameContext.getInstance();
        observerManager.notifyObservers(gameContext);
    }

    @Override
    public void addScore() {
        GameContext gameContext = GameContext.getInstance();
        gameContext.setScore(gameContext.getScore() + 1);
    }
}
