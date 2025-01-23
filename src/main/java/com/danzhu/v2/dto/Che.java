package com.danzhu.v2.dto;

import com.danzhu.v2.view.Draw;
import lombok.Data;

import java.awt.*;

@Data
public class Che extends BaseItem implements Draw {
    private int startX;
    private int startY;

    public Che(Color orange, int x, int y,int width,int height) {
        this.setColor(orange);
        this.setWidth(width);
        this.setHeight(height);
        this.setX(x);
        this.setY(y);
        this.startX = x;
        this.startY = y;
    }



    @Override
    public void drawMe(Graphics g) {
        g.setColor(getColor());
        g.fillRect(this.getX(), this.getY(), getWidth(), getHeight());
    }

    public void restart() {
        this.setX(startX);
        this.setY(startY);
    }
}
