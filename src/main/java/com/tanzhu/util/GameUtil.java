package com.tanzhu.util;

import com.tanzhu.consts.GameConsts;
import com.tanzhu.dto.BaseItem;

import java.awt.*;
import java.util.Random;

public class GameUtil {
    public static Color randomColor(){
        int r  = new Random().nextInt(256);
        int g  = new Random().nextInt(256);
        int b  = new Random().nextInt(256);
        return new Color(r,g,b);
    }

    /**
     * o2是否触摸到了o1
     *
     * @param o2
     * @param o1
     * @return
     */
    public static boolean touch(BaseItem o2,BaseItem o1) {
        if (o1==null||o2==null) {
            return false;
        }
        return o2.getY()>=o1.getY()&&o2.getY()<= (o1.getY()+ o1.getHeight())&&o2.getX()>=o1.getX()&&o2.getX()<=(o1.getX()+o1.getWidth());
    }
}
