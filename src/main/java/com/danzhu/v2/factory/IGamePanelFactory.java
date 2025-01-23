package com.danzhu.v2.factory;

import com.danzhu.v2.GameContext;
import com.danzhu.v2.dto.Block;
import com.danzhu.v2.dto.Che;
import com.danzhu.v2.dto.DanZhu;
import com.danzhu.v2.view.GamePanel;

import java.util.List;

public interface IGamePanelFactory {
    GamePanel createGamePanel(GameContext gameContext, Che che, DanZhu danZhu, List<Block> blocks);
}
