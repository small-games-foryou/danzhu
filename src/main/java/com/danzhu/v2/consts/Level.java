package com.danzhu.v2.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Arrays;
import java.util.Objects;

@AllArgsConstructor
@Getter
@ToString
public enum Level {
    SIMPLE("简单",5),ZHONG("中等",10),NAN("超难",15);

    private final String label;
    private final int speed;

    public static String[] getLabels() {
        return Arrays.stream(values()).map(Level::getLabel).toArray(String[]::new);
    }

    public static Level of(String selectedItem) {
        for (Level value : values()) {
            if (Objects.equals(value.getLabel(),selectedItem)) {
                return value;
            }
        }
        return null;
    }
}
