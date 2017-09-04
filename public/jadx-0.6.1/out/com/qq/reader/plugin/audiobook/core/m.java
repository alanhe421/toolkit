package com.qq.reader.plugin.audiobook.core;

import java.util.Random;

/* compiled from: QQMusicUtil */
public class m {
    private static Random a;

    public static synchronized int a(int i, int i2) {
        int min;
        synchronized (m.class) {
            if (a == null) {
                a = new Random();
            }
            min = Math.min(i, i2) + a.nextInt(Math.abs(i2 - i) + 1);
        }
        return min;
    }

    public static synchronized int[] a(int i) {
        int[] iArr;
        synchronized (m.class) {
            iArr = new int[i];
            a(iArr, 0, 0, i - 1);
        }
        return iArr;
    }

    private static void a(int[] iArr, int i, int i2, int i3) {
        if (i3 > i2) {
            int a = a(i2, i3);
            iArr[i] = a;
            int i4 = i + 1;
            if (a(0, 1) == 0) {
                a(iArr, i4, i2, a - 1);
                a(iArr, i4 + (a - i2), a + 1, i3);
                return;
            }
            a(iArr, i4, a + 1, i3);
            a(iArr, i4 + (i3 - a), i2, a - 1);
        } else if (i3 == i2) {
            iArr[i] = i2;
        }
    }

    public static String a(long j) {
        long j2 = j / 60;
        long j3 = j % 60;
        StringBuffer stringBuffer = new StringBuffer();
        if (j2 < 10) {
            stringBuffer.append("0");
        }
        stringBuffer.append(j2);
        stringBuffer.append(":");
        if (j3 < 10) {
            stringBuffer.append("0");
        }
        stringBuffer.append(j3);
        return stringBuffer.toString();
    }
}
