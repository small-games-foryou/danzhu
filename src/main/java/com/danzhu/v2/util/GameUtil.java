package com.danzhu.v2.util;

import com.danzhu.v2.dto.BaseItem;

import java.awt.*;
import java.util.Random;

public class GameUtil {
    public static Color randomColor(){
        int r  = new Random().nextInt(256);
        int g  = new Random().nextInt(256);
        int b  = new Random().nextInt(256);
        return new Color(r,g,b);
    }




    public static int getItemRealLoc(int x, int d) {
        return x*d;
    }
}
