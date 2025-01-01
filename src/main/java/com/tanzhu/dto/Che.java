package com.tanzhu.dto;

import com.tanzhu.consts.GameConsts;
import com.tanzhu.service.Draw;
import lombok.Data;

import java.awt.*;

@Data
public class Che extends BaseItem implements Draw {
    private Color color = Color.WHITE;
    private int startX;
    private int startY;

    public Che(Color orange, int x, int y) {
        this.color = orange;
        this.setWidth(GameConsts.CHE_WIDTH);
        this.setHeight(GameConsts.CHE_HEIGHT);
        this.setX(x * getWidth());
        this.setY(y * getHeight());
        startX = getX();
        startY = getY();
    }

    public void moveLeft() {
        int i = getX() - getWidth();
        i = Math.max(i, 0);
        this.setX(i);
    }

    public void moveRight() {
        int i = getX() + getHeight();
        i = Math.min(i, (GameConsts.BLOCK_MAX_X - 2) * GameConsts.BLOCK_WIDTH);
        this.setX(i);
    }

    @Override
    public void drawMe(Graphics g) {
        g.setColor(color);
        g.fillRect(this.getX(), this.getY(), getWidth(), getHeight());
    }

    public void restart() {
        this.setX(startX);
        this.setY(startY);
    }
}
