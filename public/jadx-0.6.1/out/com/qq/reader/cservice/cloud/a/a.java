package com.qq.reader.cservice.cloud.a;

import com.qq.reader.framework.mark.Mark;

/* compiled from: BatDelBookInfo */
public class a {
    private long a;
    private int b;

    public long a() {
        return this.a;
    }

    public void a(long j) {
        this.a = j;
    }

    public int b() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public static int a(Mark mark) {
        if (mark == null) {
            return 0;
        }
        switch (mark.getType()) {
            case 8:
                return 2;
            case 9:
                return 3;
            default:
                return 1;
        }
    }
}
