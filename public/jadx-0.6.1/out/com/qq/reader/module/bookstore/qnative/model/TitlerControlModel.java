package com.qq.reader.module.bookstore.qnative.model;

import java.io.Serializable;

public class TitlerControlModel implements Serializable {
    public static int POSITION_MODE = 0;
    public static int POSITION_Y_MODE = 2;
    public static int Y_MODE = 1;
    public boolean enable = false;
    public int hideDuration = 1200;
    public int mode;
    public boolean needImmerseMode = true;
    public int showDuration = 1200;
    public int startPosition;
    public int startY;
    public boolean withTitle;
}
