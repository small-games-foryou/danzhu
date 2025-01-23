package com.danzhu.v2.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AudioType {
   BG("bg","bg.wav"),END("end","end.wav"),HIT("hit","hit.wav"),WIN("win","win.wav"),EXPLOSION("explosion","explosion.wav"),MOVE("move","move.wav") ;
    private final String fileName;
    private final String filePath;
}
