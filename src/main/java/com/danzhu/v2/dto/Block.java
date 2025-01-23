package com.danzhu.v2.dto;

import com.danzhu.v2.view.Draw;
import lombok.Data;

import java.awt.*;
@Data
public class Block extends BaseItem implements Draw {

    public Block(Color color, int x, int y,int width,int height) {
        super();
        this.setColor(color);
        this.setWidth(width);
        this.setHeight(height);
        this.setX(x);
        this.setY(y);
    }


    @Override
    public void drawMe(Graphics g) {
        if(isAlive()){
            g.setColor(getColor());
            g.fillRect(this.getX(), this.getY(), getWidth(), getHeight());
        }
    }

}
