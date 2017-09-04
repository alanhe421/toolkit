package com.tencent.upload.log.a;

import java.util.Calendar;

public final class a {
    private long a;
    private long b;
    private long c;
    private int d;
    private int e;
    private int f;
    private int g = 0;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k;

    private int a(int i, int i2, int i3) {
        int i4 = 0;
        int i5 = i2 + i;
        this.k = 0;
        if (i5 >= i3) {
            this.k = i5 / i3;
            return i5 - (this.k * i3);
        } else if (i5 >= 0) {
            return i5;
        } else {
            this.k = (-i5) / i3;
            i5 += (this.k + 1) * i3;
            if (i5 != i3) {
                this.k++;
                i4 = i5;
            }
            this.k = -this.k;
            return i4;
        }
    }

    public final void a(long j) {
        if (this.a == 0 || j >= this.b || j <= this.c) {
            Calendar instance = Calendar.getInstance();
            instance.setTimeInMillis(j);
            this.a = j;
            this.d = instance.get(1);
            this.e = instance.get(2);
            this.f = instance.get(5);
            this.g = instance.get(11);
            this.h = instance.get(12);
            this.i = instance.get(13);
            this.j = instance.get(14);
            instance.set(this.d, this.e, this.f, 0, 0, 0);
            instance.set(14, 0);
            this.c = instance.getTimeInMillis();
            instance.add(5, 1);
            this.b = instance.getTimeInMillis();
            return;
        }
        this.j = a((int) (j - this.a), this.j, 1000);
        if (this.k != 0) {
            this.i = a(this.k, this.i, 60);
            if (this.k != 0) {
                this.h = a(this.k, this.h, 60);
                if (this.k != 0) {
                    this.g = a(this.k, this.g, 60);
                }
            }
        }
        this.a = j;
    }

    public final void a(StringBuilder stringBuilder) {
        stringBuilder.append(this.d).append("-");
        if (this.e < 9) {
            stringBuilder.append(0);
        }
        stringBuilder.append(this.e + 1).append("-");
        if (this.f < 10) {
            stringBuilder.append(0);
        }
        stringBuilder.append(this.f).append(" ");
        if (this.g < 10) {
            stringBuilder.append(0);
        }
        stringBuilder.append(this.g).append(":");
        if (this.h < 10) {
            stringBuilder.append(0);
        }
        stringBuilder.append(this.h).append(":");
        if (this.i < 10) {
            stringBuilder.append(0);
        }
        stringBuilder.append(this.i).append(".");
        if (this.j < 10) {
            stringBuilder.append("00");
        } else if (this.j < 100) {
            stringBuilder.append(0);
        }
        stringBuilder.append(this.j);
    }
}
