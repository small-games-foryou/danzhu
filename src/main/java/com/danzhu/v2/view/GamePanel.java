package com.danzhu.v2.view;


import com.danzhu.v2.GameContext;
import com.google.common.collect.Lists;
import com.danzhu.v2.consts.GameConsts;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@Data
public class GamePanel extends JPanel implements GameObserver{
    private List<Draw> items = Lists.newArrayList();
    private GameContext gameContext;
    public GamePanel() {
        setBackground(Color.BLACK);
        Dimension dimension = new Dimension(GameConsts.GAME_PANEL_WIDTH, GameConsts.GAME_PANEL_HEIGHT);
        setSize(dimension);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setLayout(null); // 如果需要手动控制子组件的位置和大小
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawMe(g);
    }

    public void drawMe(Graphics g) {
        for (Draw block : Optional.ofNullable(items).orElse(Lists.newArrayList())) {
            block.drawMe(g);
        }
        if (gameContext !=null) {
            int x = GameConsts.GAME_PANEL_WIDTH/2;
            int y = GameConsts.GAME_PANEL_HEIGHT/2;
            if(gameContext.isGameOver()){
                g.setColor(Color.RED);
                g.drawString("GAME OVER!",x,y);
            }
            if(gameContext.isWin()){
                g.setColor(Color.GREEN);
                g.drawString("YOU ARE THE WINNER!",x,y);
            }
        }
    }

    @Override
    public void update(GameContext gameContext) {
        repaint();
    }

    public <T extends Draw>  void addItem(T item) {
        items.add(item);
    }
    public void addItems(List<? extends Draw> item) {
        items.addAll(item);
    }
}
