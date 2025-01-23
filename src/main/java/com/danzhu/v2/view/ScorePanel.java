package com.danzhu.v2.view;

import com.danzhu.v2.GameContext;
import com.danzhu.v2.consts.GameConsts;
import com.danzhu.v2.consts.Level;
import com.danzhu.v2.hander.EventHandler;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@EqualsAndHashCode(callSuper = true)
@Data
public class ScorePanel extends JPanel implements GameObserver {
    private GameContext gameContext;
    private JButton button;
    private JComboBox<String> comboBox;

    public ScorePanel() {
        Dimension dimension = new Dimension(GameConsts.SCORE_PANEL_WIDTH, GameConsts.GAME_PANEL_HEIGHT);
        setSize(dimension);
        setPreferredSize(dimension);
        setMinimumSize(dimension);
        setMaximumSize(dimension);
        setBackground(Color.GRAY);
        button = new JButton("开始");
        button.setBounds(0, 0, 30, 20);
        this.add(button);
        comboBox = new JComboBox<>(Level.getLabels());
        add(comboBox);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (gameContext == null) {
                    throw new RuntimeException("gameConfig  is null");
                }
                EventHandler eventHandler = gameContext.getEventHandler();
                if (eventHandler == null) {
                    throw new RuntimeException("event handler is null");
                }

                if (gameContext.isRunning()) {
                    eventHandler.gamePause();
                    button.setText("开始");
                } else {
                    eventHandler.startGame();
                    button.setText("暂停");
                }

            }
        });
        comboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (gameContext == null) {
                    throw new RuntimeException("gameConfig  is null");
                }
                EventHandler eventHandler = gameContext.getEventHandler();
                if (eventHandler == null) {
                    throw new RuntimeException("event handler is null");
                }
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    eventHandler.levelChange(getLevel());
                }
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.GREEN);
        g.drawString("分数：" + gameContext.getScore(), 10, GameConsts.GAME_PANEL_HEIGHT/2);

    }

    public Level getLevel() {
        return Level.of((String) comboBox.getSelectedItem());
    }

    @Override
    public void update(GameContext gameContext) {
        if (gameContext.isGameOver()|| gameContext.isWin()) {
            button.setText("开始");
        }
        repaint();
    }
}
