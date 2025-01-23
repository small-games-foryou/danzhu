package com.danzhu.v2.factory;

import com.danzhu.v2.dto.DanZhu;

import java.awt.*;

public class DanZhuFactory implements IDanZhuFactory {

    @Override
    public DanZhu createDanZhu(Color color, int x, int y, int width, int height) {
        return new DanZhu(color, x, y, width, height);
    }
}
