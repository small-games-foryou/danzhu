package com.danzhu.v2.factory;

import com.danzhu.v2.consts.GameConsts;
import com.danzhu.v2.dto.Block;
import com.danzhu.v2.util.GameUtil;
import com.google.common.collect.Lists;

import java.awt.*;
import java.util.List;

public class BlockFactory implements IBlockFactory {

    @Override
    public List<Block> createBlock(Color color, int width, int height) {
        List<Block> blocks = Lists.newArrayList();
        for (int j = 0; j < GameConsts.BLOCK_MAX_Y; j++) {
            for (int k = 0; k < GameConsts.BLOCK_MAX_X; k++) {
                Block block = new Block(GameUtil.randomColor(), GameUtil.getItemRealLoc(k, GameConsts.BLOCK_WIDTH), GameUtil.getItemRealLoc(j, GameConsts.BLOCK_HEIGHT),GameConsts.BLOCK_WIDTH, GameConsts.BLOCK_HEIGHT);
                blocks.add(block);
            }
        }
        return blocks;
    }
}