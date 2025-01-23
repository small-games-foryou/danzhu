package com.danzhu.v2.factory;

import com.danzhu.v2.dto.Block;

import java.awt.*;
import java.util.List;

public interface IBlockFactory {
    List<Block> createBlock(Color color, int width, int height);
}
