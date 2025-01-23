package com.danzhu.v2.factory;

import com.danzhu.v2.dto.DanZhu;

import java.awt.*;

public interface IDanZhuFactory {
    DanZhu createDanZhu(Color color, int x, int y, int width, int height);
}
