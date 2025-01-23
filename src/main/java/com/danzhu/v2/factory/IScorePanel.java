package com.danzhu.v2.factory;

import com.danzhu.v2.GameContext;
import com.danzhu.v2.view.ScorePanel;

public interface IScorePanel {

    ScorePanel createScorePanel(GameContext gameContext);
}
