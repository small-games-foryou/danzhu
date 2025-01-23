package com.danzhu.v2.manager;

import com.danzhu.v2.audio.Audio;
import com.danzhu.v2.consts.AudioType;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AudioManager {
//    private static final Audio bg = new Audio("audio/bg.wav");
//    private static final Audio end = new Audio("audio/end.wav");
//    private static final Audio hit = new Audio("audio/hit.wav");
//    private static final Audio explosion = new Audio("audio/explosion.wav");
//    private static final Audio win = new Audio("audio/win.wav");
//    private static final Audio move = new Audio("audio/move.wav");
    public static ExecutorService executorService = Executors.newFixedThreadPool(100);

    public static void playBg(int loopTime) {
        play(AudioType.BG, loopTime);
    }

    public static void playEnd(int loopTime) {
        play(AudioType.END, loopTime);
    }

    public static void playHit(int loopTime) {
        play(AudioType.HIT, loopTime);
    }

    public static void playExplosion(int loopTime) {
        play(AudioType.EXPLOSION, loopTime);
    }

    public static void playWin(int loopTime) {
        play(AudioType.WIN, loopTime);
    }

    public static void playMove(int loopTime) {
        play(AudioType.MOVE, loopTime);
    }

    public static void play(AudioType audioType, int loopTime) {
        Audio audio = new Audio("audio/"+audioType.getFilePath());
//        if (loopTime>1) {
//            executorService.submit(()->{audio.loop(loopTime);});
//            return;
//        }
        executorService.submit(audio::play);
    }

    public static void close() {
        executorService.shutdown();
    }




}
