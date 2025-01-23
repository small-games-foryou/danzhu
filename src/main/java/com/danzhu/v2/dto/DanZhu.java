package com.danzhu.v2.dto;

import com.danzhu.v2.GameContext;
import com.danzhu.v2.consts.Direction;
import com.danzhu.v2.consts.GameConsts;
import com.danzhu.v2.view.Draw;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.*;

@Data
@EqualsAndHashCode
public class DanZhu extends BaseItem implements Draw {
    private Direction directionX;
    private Direction directionY;
    private GameContext gameContext;
    private int startX;
    private int startY;
    public DanZhu(Color red, int x, int y, int width, int height) {
        this.setColor(red);
        this.setX(x);
        this.setY(y);
        setWidth(width);
        setHeight(height);
        setDirectionX(Direction.RIGHT);
        setDirectionY(Direction.UP);
        startX = getX();
        startY = getY();
    }

    @Override
    public void drawMe(Graphics g) {
        g.setColor(getColor());
        g.fillRoundRect(this.getX(), this.getY(), GameConsts.TAN_ZHU_WIDTH, GameConsts.TAN_ZHU_WIDTH,GameConsts.TAN_ZHU_WIDTH,GameConsts.TAN_ZHU_WIDTH);
    }




    public void restart() {
        this.setX(startX);
        this.setY(startY);
    }
}
