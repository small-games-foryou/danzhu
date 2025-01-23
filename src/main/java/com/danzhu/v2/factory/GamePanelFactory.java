package com.danzhu.v2.factory;

import com.danzhu.v2.GameContext;
import com.danzhu.v2.dto.Block;
import com.danzhu.v2.dto.Che;
import com.danzhu.v2.dto.DanZhu;
import com.danzhu.v2.view.GamePanel;

import java.util.List;

public class GamePanelFactory implements IGamePanelFactory {
    @Override
    public GamePanel createGamePanel(GameContext gameContext, Che che, DanZhu danZhu, List<Block> blocks) {
        GamePanel gamePanel = new GamePanel();
        gamePanel.setGameContext(gameContext);
        gamePanel.addItem(danZhu);
        gamePanel.addItem(che);
        gamePanel.addItems(blocks);
        return gamePanel;
    }
}
