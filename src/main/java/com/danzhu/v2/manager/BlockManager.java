package com.danzhu.v2.manager;

import com.danzhu.v2.dto.Block;
import com.danzhu.v2.dto.DanZhu;
import com.danzhu.v2.hander.EventHandler;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BlockManager {
    private static BlockManager instance;
    public static synchronized BlockManager getInstance() {
        if (instance == null) {
            instance = new BlockManager();
        }
        return instance;
    }
    public void killBlocks(DanZhu danZhu,List<Block> blocks, EventHandler eventHandler) {
        for (Block block : Optional.ofNullable(blocks).orElse(Collections.emptyList()).stream().filter(Block::isAlive).collect(Collectors.toList()))        {
            if (block.getRect().intersects(danZhu.getRect())) {
                DanZhuManager danZhuManager = DanZhuManager.getInstance();
                danZhuManager.changeXDirection(danZhu);
                danZhuManager.changeYDirection(danZhu);
                block.clear();
                eventHandler.addScore();
                AudioManager.playExplosion(1);

            }
        }
        if(blocks!=null){
            if(Optional.of(blocks).orElse(Collections.emptyList()).stream().noneMatch(Block::isAlive)){
                eventHandler.win();

            }
        }
    }
}
