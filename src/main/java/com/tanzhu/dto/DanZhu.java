package com.tanzhu.dto;

import com.tanzhu.consts.Direction;
import com.tanzhu.consts.GameConsts;
import com.tanzhu.service.Draw;
import com.tanzhu.util.GameUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;

@Data
@EqualsAndHashCode
public class DanZhu extends BaseItem implements Draw {
    private Color color = Color.RED;
    private Direction directionX;
    private Direction directionY;
    private GameConfig gameConfig;
    private Che che;
    private int startX;
    private int startY;
    public DanZhu(Color red, int x, int y, Che che) {
        this.color = red;
        this.setWidth(GameConsts.TAN_ZHU_WIDTH);
        this.setHeight(GameConsts.TAN_ZHU_WIDTH);
        this.setX(x*getWidth());
        this.setY(y*getHeight());
        setDirectionX(Direction.RIGHT);
        setDirectionY(Direction.UP);
        this.che = che;
        startX = getX();
        startY = getY();
    }

    @Override
    public void drawMe(Graphics g) {
        g.setColor(color);
        g.fillRoundRect(this.getX(), this.getY(), GameConsts.TAN_ZHU_WIDTH, GameConsts.TAN_ZHU_WIDTH, GameConsts.TAN_ZHU_WIDTH, GameConsts.TAN_ZHU_WIDTH);
    }


    public void changeXDirection() {
        if (directionX.equals(Direction.RIGHT)) {
            setDirectionX(Direction.LEFT);
        } else {
            setDirectionX(Direction.RIGHT);
        }
    }

    public void changeYDirection() {
        if (directionY.equals(Direction.DOWN)) {
            setDirectionY(Direction.UP);
        } else {
            setDirectionY(Direction.DOWN);
        }

    }

    public void move() {
        if (directionY != null) {
            if (directionY.equals(Direction.DOWN)) {
                int y = getY() + GameConsts.TAN_ZHU_WIDTH;
                int i = GameConsts.GAME_PANEL_BOTTOM_HEIGHT;
                if (y >= i) {
                    y = getY();
                    gameConfig.setGameOver(true);
                }
                this.setY(y);
            }
            if (directionY.equals(Direction.UP)) {
                boolean isOverBorder = false;
                int y = getY() - GameConsts.TAN_ZHU_WIDTH;
                if (y <= 0) {
                    y = 0;
                    isOverBorder = true;
                }

                this.setY(y);
                if (isOverBorder) {
                    changeYDirection();
                }
            }
        }
        if (directionX != null) {
            if (directionX.equals(Direction.RIGHT)) {
                boolean isOverBorder = false;
                int x = getX() + GameConsts.TAN_ZHU_WIDTH;
                int i = GameConsts.BLOCK_MAX_X * GameConsts.BLOCK_WIDTH;
                if (x >= i) {
                    x = getX();
                    isOverBorder = true;
                }
                this.setX(x);
                if (isOverBorder) {
                    changeXDirection();
                }
            }
            if (directionX.equals(Direction.LEFT)) {
                int x = getX() - GameConsts.TAN_ZHU_WIDTH;
                boolean isOverBorder = false;
                if (x <= 0) {
                    x = 0;
                    isOverBorder = true;
                }
                this.setX(x);
                if (isOverBorder) {
                    changeXDirection();
                }
            }
        }
        if(touchChe()){
            changeYDirection();
        }
    }

    private boolean touchChe() {
        return GameUtil.touch(this,che);
    }

    public void restart() {
        this.setX(startX);
        this.setY(startY);
    }
}
