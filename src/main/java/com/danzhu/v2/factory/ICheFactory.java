package com.danzhu.v2.factory;

import com.danzhu.v2.dto.Che;

import java.awt.*;

public interface ICheFactory {
    Che createChe(Color color, int x, int y, int width, int height);
}
