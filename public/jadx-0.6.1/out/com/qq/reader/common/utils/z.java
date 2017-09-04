package com.qq.reader.common.utils;

import java.util.LinkedList;
import java.util.Random;

/* compiled from: RandomNoRepeat */
public class z {
    private LinkedList<Integer> a = new LinkedList();
    private int b = 0;
    private int c = 0;
    private Random d = new Random();

    public z(int i) {
        int i2 = 0;
        this.c = i;
        while (i2 < i) {
            this.a.add(Integer.valueOf(i2));
            i2++;
        }
    }

    public int a() {
        int intValue = ((Integer) this.a.remove(this.d.nextInt(this.c - this.b))).intValue();
        this.a.add(Integer.valueOf(intValue));
        this.b++;
        if (this.b == this.c) {
            this.b = 0;
        }
        return intValue;
    }
}
