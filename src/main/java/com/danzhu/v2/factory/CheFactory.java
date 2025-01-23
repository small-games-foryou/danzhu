package com.danzhu.v2.factory;

import com.danzhu.v2.dto.Che;

import java.awt.*;

public class CheFactory implements ICheFactory {

    @Override
    public Che createChe(Color color, int x, int y, int width, int height) {
        return new Che(color, x, y,width,height);
    }
}