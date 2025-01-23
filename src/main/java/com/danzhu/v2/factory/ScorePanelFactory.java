package com.danzhu.v2.factory;

import com.danzhu.v2.GameContext;
import com.danzhu.v2.view.ScorePanel;

public class ScorePanelFactory implements IScorePanel {
    @Override
    public ScorePanel createScorePanel(GameContext gameContext) {
        ScorePanel scorePanel = new ScorePanel();
        scorePanel.setGameContext(gameContext);
        return scorePanel;
    }
}
