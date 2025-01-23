package com.danzhu.v2.factory;

import com.danzhu.v2.view.MainFrame;

import javax.swing.*;

public abstract class FrameAbstractFactory {
     public MainFrame initFrame(String title) {
        // 确保一个漂亮的外观风格
        JFrame.setDefaultLookAndFeelDecorated(true);
         return new MainFrame(title);
    }
}
