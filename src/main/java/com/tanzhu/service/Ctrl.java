package com.tanzhu.service;

import com.google.common.collect.Lists;
import com.tanzhu.consts.GameConsts;
import com.tanzhu.consts.Level;
import com.tanzhu.dto.*;
import com.tanzhu.util.GameUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.*;

@Data
@EqualsAndHashCode
public class Ctrl {
    private JFrame frame;
    private List<Block> blocks = Lists.newArrayList();
    private DanZhu danZhu;
    private Che che;
    private ScorePanel score;
    private GamePanel gamePanel;
    private GameConfig gameConfig;
    private ScheduledExecutorService poolExecutor;

    public Ctrl() {
        init();
    }

    public void doCtrl() {
        bindKeyEvent();
    }

    private void startThread() {
        gameConfig.setThreadRun(true);
        gameConfig.setRunning(true);
        poolExecutor = Executors.newScheduledThreadPool(2);
        Level level = score.getLevel();
        poolExecutor.scheduleAtFixedRate(() -> {
            if (!gameConfig.getRunning()) {
                return;
            }
            danZhu.move();
            clearBlocks();
            gamePanel.repaint();
            if (gameConfig.getGameOver() || gameConfig.getWin()) {
                gameConfig.setThreadRun(false);
                poolExecutor.shutdown();
                score.gameOver();
            }
        }, 0, level == null ? gameConfig.getSpeed() : level.getSpeed(), TimeUnit.MILLISECONDS);
    }

    private void bindKeyEvent() {
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                super.keyReleased(e);
                if (gameConfig.getGameOver()) {
                    return;
                }
                int keyCode = e.getKeyCode();
                if (keyCode == 37) {
                    che.moveLeft();
                }
                if (keyCode == 39) {
                    che.moveRight();
                }
            }
        });
        score.bindKey(this);
    }


    private void clearBlocks() {
        for (Block block : blocks) {
            if (GameUtil.touch(danZhu, block)) {
                block.clear();
                score.addScore();
            }
        }
        if (blocks.stream().noneMatch(Block::isAlive)) {
            gameConfig.setWin(true);
        }
    }

    private void init() {
        score = new ScorePanel();
        gamePanel = new GamePanel();
        gameConfig = new GameConfig();
        che = new Che(Color.ORANGE, 0, GameConsts.GAME_PANEL_BOTTOM);
        danZhu = new DanZhu(Color.RED, 0, GameConsts.GAME_PANEL_BOTTOM - 1, che);
        gamePanel.setChe(che);
        gamePanel.setDanZhu(danZhu);
        gamePanel.setGameConfig(gameConfig);
        score.setGameConfig(gameConfig);
        danZhu.setGameConfig(gameConfig);
        frame = initFrame(gamePanel, score);
        resetGame();
    }

    private void resetGame() {
        for (int y = 0; y < GameConsts.BLOCK_MAX_Y; y++) {
            for (int x = 0; x < GameConsts.BLOCK_MAX_X; x++) {
                Block block = new Block(GameUtil.randomColor(), x, y);
                blocks.add(block);
            }
        }
        gamePanel.setBlocks(blocks);
        danZhu.restart();
        che.restart();
        gameConfig.setRunning(false);
        gameConfig.setGameOver(false);
        gameConfig.setWin(false);
        gameConfig.setScore(0);
        gameConfig.setThreadRun(false);
    }

    private JFrame initFrame(GamePanel gamePanel, ScorePanel score) {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("弹珠游戏");
        BorderLayout borderLayout = new BorderLayout();
        initFrame(frame, gamePanel, score, borderLayout);

        return frame;
    }

    private static void initFrame(JFrame frame, GamePanel gamePanel, ScorePanel score, BorderLayout gridLayout) {
        // 创建 JSplitPane 并添加左右面板
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamePanel, score);
        splitPane.setResizeWeight(0.5); // 设置左右两部分的比例
        splitPane.setDividerLocation(200); // 设置分割线初始位置
        // 禁止调整比例
        splitPane.setOneTouchExpandable(false);
        splitPane.setEnabled(false); // 锁定分隔条
        frame.add(splitPane);
        frame.setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(gamePanel.getWidth() + score.getWidth() - GameConsts.BLOCK_WIDTH - 5, gamePanel.getHeight());
        frame.setFocusable(true);
        frame.setVisible(true);
        frame.setResizable(false);
    }


    public void gamePause() {
        gameConfig.setRunning(false);
        frame.requestFocus();
    }

    public void gameStart() {
        gameConfig.setRunning(true);
        if (!gameConfig.getThreadRun()) {
            resetGame();

            startThread();
        }
        frame.requestFocus();

    }
}
