package com.danzhu.v2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.awt.*;
@Data
public class BaseItem {
    private Color color = Color.WHITE;
    private int x;
    private int y;
    private int width;
    private int height;
    private boolean alive = true;
   public Rectangle getRect() {
        return new Rectangle(x, y, width, height);
    }
    public void clear(){
        setAlive(false);
    }
}
