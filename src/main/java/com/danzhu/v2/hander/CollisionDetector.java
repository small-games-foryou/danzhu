package com.danzhu.v2.hander;

import com.danzhu.v2.dto.BaseItem;

public class CollisionDetector {
    public  static boolean detectCollision(BaseItem item1, BaseItem item2) {
        return item1.getRect().intersects(item2.getRect());
    }
}
