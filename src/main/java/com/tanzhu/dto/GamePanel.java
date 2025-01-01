package com.tanzhu.dto;

import com.google.common.collect.Lists;
import com.tanzhu.consts.GameConsts;
import com.tanzhu.service.Draw;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

@Data
public class GamePanel extends JPanel implements Draw{
    private String bgColor;
    private List<Block> blocks = Lists.newArrayList();
    private DanZhu danZhu;
    private Che che;
    private GameConfig gameConfig;
    public GamePanel() {
        this.setSize(GameConsts.GAME_PANEL_WIDTH, GameConsts.GAME_PANEL_HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawMe(g);
    }

    @Override
    public void drawMe(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        for (Block block : Optional.ofNullable(blocks).orElse(Lists.newArrayList())) {
            block.drawMe(g);
        }
        if (che != null) {
            che.drawMe(g);
        }
        if (danZhu != null) {
            danZhu.drawMe(g);
        }
        if (gameConfig!=null) {
            if(gameConfig.getGameOver()){
                g.setColor(Color.RED);
                g.drawString("GAME OVER!",100,200);
            }
            if(gameConfig.getWin()){
                g.setColor(Color.GREEN);
                g.drawString("YOU ARE THE WINNER!",100,200);
            }
        }

    }

}
