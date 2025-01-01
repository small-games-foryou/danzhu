package com.tanzhu.dto;

import com.tanzhu.consts.GameConsts;
import com.tanzhu.consts.Level;
import com.tanzhu.service.Ctrl;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Data
public class ScorePanel extends JPanel {
    private GameConfig gameConfig;
    private JButton button;
    private JComboBox<String> comboBox;

    public ScorePanel() {
        this.setSize(GameConsts.SCORE_PANEL_WIDTH, GameConsts.GAME_PANEL_HEIGHT);
        button = new JButton("开始");
        button.setBounds(0, 0, 30, 20);
        this.add(button);
        comboBox = new JComboBox<>(Level.getLabels());
        add(comboBox);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
        g.drawString("分数：" + gameConfig.getScore(), 10, 200);

    }

    public void addScore() {
        gameConfig.setScore(gameConfig.getScore() + 10);
        this.repaint();
    }

    public Level getLevel() {
        return Level.of((String) comboBox.getSelectedItem());
    }

    public void bindKey(Ctrl ctrl) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (gameConfig != null) {
                    if (gameConfig.getRunning()) {
                        ctrl.gamePause();
                        button.setText("开始");
                    } else {
                        ctrl.gameStart();
                        button.setText("暂停");
                    }
                }
            }
        });

    }

    public void gameOver() {
        button.setText("开始");
    }
}
