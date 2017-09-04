package com.qq.reader.common.utils;

import android.os.CountDownTimer;

/* compiled from: ListenBookCountDownTimer */
public class q extends CountDownTimer {
    private a a;
    private int b;
    private long c = 0;
    private boolean d = false;

    /* compiled from: ListenBookCountDownTimer */
    public interface a {
        void a();

        void a(long j);
    }

    public q(long j, long j2, int i) {
        super(j, j2);
        this.b = i;
    }

    public void onTick(long j) {
        this.c = j;
        if (this.a != null) {
            this.a.a(j);
        }
    }

    public void onFinish() {
        if (this.a != null) {
            this.a.a();
        }
        this.c = 0;
        this.d = false;
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public boolean a() {
        return this.d;
    }

    public long b() {
        return this.c;
    }

    public int c() {
        return this.b;
    }
}
