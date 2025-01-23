package com.danzhu.v2.factory;

import com.danzhu.v2.GameContext;
import com.danzhu.v2.consts.GameConsts;
import com.danzhu.v2.dto.Block;
import com.danzhu.v2.dto.Che;
import com.danzhu.v2.dto.DanZhu;
import com.danzhu.v2.view.MainFrame;
import com.danzhu.v2.view.GamePanel;
import com.danzhu.v2.view.ScorePanel;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.List;

@Data
public class MainFrameFactory extends FrameAbstractFactory implements IFrameFactory {
    private IGamePanelFactory gamePanelFactory;
    private IScorePanel scorePanelFactory;
    private static MainFrameFactory mainFrameFactory;
    private IBlockFactory blockFactory;
    private ICheFactory cheFactory;
    private IDanZhuFactory danZhuFactory;


    public static synchronized MainFrameFactory getInstance() {
        if (mainFrameFactory == null) {
            MainFrameFactory factory = new MainFrameFactory();
            IGamePanelFactory gamePanelFactory = new GamePanelFactory();
            IScorePanel scorePanelFactory = new ScorePanelFactory();
            factory.setGamePanelFactory(gamePanelFactory);
            factory.setScorePanelFactory(scorePanelFactory);
            IBlockFactory blockFactory = new BlockFactory();
            ICheFactory cheFactory = new CheFactory();
            IDanZhuFactory danZhuFactory = new DanZhuFactory();
            factory.setBlockFactory(blockFactory);
            factory.setCheFactory(cheFactory);
            factory.setDanZhuFactory(danZhuFactory);
            return factory;
        }
        return mainFrameFactory;
    }

    @Override
    public MainFrame createFrame(GameContext gameContext) {
        MainFrame mainFrame = initFrame("弹珠游戏");
        ScorePanel score = scorePanelFactory.createScorePanel(gameContext);
        List<Block> blocks = blockFactory.createBlock(Color.GRAY, GameConsts.BLOCK_WIDTH, GameConsts.BLOCK_HEIGHT);
        Che che = cheFactory.createChe(Color.WHITE, 0, GameConsts.GAME_PANEL_BOTTOM_Y-GameConsts.CHE_HEIGHT, GameConsts.CHE_WIDTH, GameConsts.CHE_HEIGHT);
        DanZhu danZhu = danZhuFactory.createDanZhu(Color.RED, 0, GameConsts.GAME_PANEL_BOTTOM_Y- GameConsts.TAN_ZHU_WIDTH-GameConsts.CHE_HEIGHT, GameConsts.TAN_ZHU_WIDTH, GameConsts.TAN_ZHU_WIDTH);
        GamePanel gamePanel = gamePanelFactory.createGamePanel(gameContext,che,danZhu,blocks);
        BorderLayout borderLayout = new BorderLayout();
        initFrame(mainFrame, gamePanel, score, borderLayout);
        mainFrame.setChe(che);
        mainFrame.setGamePanel(gamePanel);
        mainFrame.setScore(score);
        mainFrame.setDanZhu(danZhu);
        mainFrame.setBlocks(blocks);
        return mainFrame;
    }


    private static void initFrame(JFrame frame, GamePanel gamePanel, ScorePanel score, BorderLayout gridLayout) {
        frame.setLayout(gridLayout);
        frame.add(gamePanel, BorderLayout.CENTER);
        frame.add(score, BorderLayout.EAST);
        frame.setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dimension = new Dimension(gamePanel.getWidth()+score.getWidth() , gamePanel.getHeight());
        frame.setPreferredSize(dimension);
        frame.setMaximumSize(dimension);
        frame.setMinimumSize(dimension);
        gamePanel.setBorder(BorderFactory.createEmptyBorder());
        score.setBorder(BorderFactory.createEmptyBorder());
        frame.getRootPane().setBorder(BorderFactory.createEmptyBorder());
        frame.pack();
        frame.setFocusable(true);
        int windowWidth = frame.getWidth(); // 获得窗口宽
        int windowHeight = frame.getHeight(); // 获得窗口高
        Toolkit kit = Toolkit.getDefaultToolkit(); // 定义工具包
        Dimension screenSize = kit.getScreenSize(); // 获取屏幕的尺寸
        int screenWidth = screenSize.width; // 获取屏幕的宽
        int screenHeight = screenSize.height; // 获取屏幕的高
        frame.setLocation(screenWidth / 2 - windowWidth / 2, screenHeight / 2 - windowHeight / 2);// 设置窗口居中显示
        frame.setVisible(true);
        frame.setResizable(false);
    }


}
