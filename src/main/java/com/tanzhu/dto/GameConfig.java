package com.tanzhu.dto;

import lombok.Data;

@Data
public class GameConfig {
    private volatile Boolean threadRun = false;
    private volatile Boolean running = false;
    private volatile Boolean gameOver = false;
    private volatile Boolean win = false;
    private long speed = 450L;
    private int score = 0;

}
