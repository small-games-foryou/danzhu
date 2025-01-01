package com.tanzhu.dto;

import com.tanzhu.consts.GameConsts;
import com.tanzhu.service.Draw;
import lombok.Data;

import java.awt.*;
@Data
public class Block extends BaseItem implements Draw {
    private Color color = Color.YELLOW;
    private boolean alive = true;

    public Block(Color color, int x, int y) {
        this.color = color;
        this.setWidth(GameConsts.BLOCK_WIDTH);
        this.setHeight(GameConsts.BLOCK_HEIGHT);
        this.setX(x*getWidth());
        this.setY(y*getHeight());
    }


    @Override
    public void drawMe(Graphics g) {
        if(alive){
            g.setColor(color);
            g.fillRect(this.getX(), this.getY(), getWidth(), getHeight());
        }
    }
    public void clear(){
        setAlive(false);
    }
}
