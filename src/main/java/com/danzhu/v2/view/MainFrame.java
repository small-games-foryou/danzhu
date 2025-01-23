package com.danzhu.v2.view;


import com.danzhu.v2.GameContext;
import com.danzhu.v2.dto.Block;
import com.danzhu.v2.dto.Che;
import com.danzhu.v2.dto.DanZhu;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class MainFrame extends JFrame implements GameObserver {
    private ScorePanel score;
    private GamePanel gamePanel;
    private List<Block> blocks;
    private Che che;
    private DanZhu danZhu;

    public MainFrame(String title) throws HeadlessException {
        super(title);
    }

    @Override
    public void update(GameContext gameContext) {
        requestFocus();
    }
}
