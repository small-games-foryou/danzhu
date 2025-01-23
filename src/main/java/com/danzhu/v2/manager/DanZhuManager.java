package com.danzhu.v2.manager;

import com.danzhu.v2.GameContext;
import com.danzhu.v2.consts.Direction;
import com.danzhu.v2.consts.GameConsts;
import com.danzhu.v2.dto.Che;
import com.danzhu.v2.dto.DanZhu;
import com.danzhu.v2.hander.CollisionDetector;
import com.danzhu.v2.hander.EventHandler;

public class DanZhuManager {
    private static DanZhuManager instance;
    public static synchronized DanZhuManager getInstance() {
        if (instance == null) {
            instance = new DanZhuManager();
        }
        return instance;
    }
    public void changeXDirection(DanZhu danZhu) {
        AudioManager.playHit(1);
        Direction directionX = danZhu.getDirectionX();
        danZhu.setDirectionX(directionX.equals(Direction.RIGHT) ? Direction.LEFT : Direction.RIGHT);
    }

    public void changeYDirection(DanZhu danZhu) {
        AudioManager.playHit(1);
        Direction directionY = danZhu.getDirectionY();
        danZhu.setDirectionY(directionY.equals(Direction.DOWN) ? Direction.UP : Direction.DOWN);
    }

    public void move(Che che, DanZhu danZhu, EventHandler eventHandler) {
        moveVertically(danZhu, che, eventHandler);
        moveHorizontally(danZhu, che);
        checkCollision(danZhu, che);
    }

    private void moveVertically(DanZhu danZhu, Che che, EventHandler eventHandler) {
        Direction directionY = danZhu.getDirectionY();
        GameContext gameContext =GameContext.getInstance();
        int danZhuY = danZhu.getY();
        if (directionY != null) {
            int newY = danZhuY + (directionY.equals(Direction.DOWN) ? gameContext.getDanZhuSpeed() : -gameContext.getDanZhuSpeed());
            int i = GameConsts.GAME_PANEL_BOTTOM_Y - GameConsts.TAN_ZHU_WIDTH;
            if (newY >= i) {
                newY = i;
            } else if (newY <= 0) {
                newY = 0;
                changeYDirection(danZhu);
            }

            danZhu.setY(newY);
        }
    }

    private void moveHorizontally(DanZhu danZhu, Che che) {
        Direction directionX = danZhu.getDirectionX();
        int danZhuX = danZhu.getX();
        GameContext gameContext =GameContext.getInstance();
        if (directionX != null) {
            int newX = danZhuX + (directionX.equals(Direction.RIGHT) ? gameContext.getDanZhuSpeed() : -gameContext.getDanZhuSpeed());
            int i = GameConsts.GAME_PANEL_WIDTH - GameConsts.TAN_ZHU_WIDTH;
            if (newX >= i) {
                newX = i;
                changeXDirection(danZhu);
            } else if (newX <= 0) {
                newX = 0;
                changeXDirection(danZhu);
            }
            danZhu.setX(newX);

        }
    }

    private void checkCollision(DanZhu danZhu, Che che) {
        if (CollisionDetector.detectCollision(danZhu, che)) {
            changeYDirection(danZhu);
        }else{
            int i = GameConsts.GAME_PANEL_BOTTOM_Y - GameConsts.TAN_ZHU_WIDTH;
            if (danZhu.getY() >= i) {
                GameContext gameContext =GameContext.getInstance();
                gameContext.getEventHandler().endGame();
            }
        }
    }
}
