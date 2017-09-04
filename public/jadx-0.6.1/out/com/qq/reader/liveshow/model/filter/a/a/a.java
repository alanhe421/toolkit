package com.qq.reader.liveshow.model.filter.a.a;

import com.qq.reader.liveshow.utils.SxbLog;

/* compiled from: DefaultRateStrategy */
public class a implements com.qq.reader.liveshow.model.filter.a.a {
    private int a;

    public a(int i) {
        this.a = i;
    }

    public void a(Thread thread) {
        SxbLog.c("rate", "-----pool resleep");
        if (this.a > 0) {
            try {
                Thread.sleep((long) this.a);
            } catch (InterruptedException e) {
                e.printStackTrace();
                SxbLog.c("rate", e.toString());
            }
            SxbLog.c("rate", "-----pool aftersleep");
        }
    }
}
