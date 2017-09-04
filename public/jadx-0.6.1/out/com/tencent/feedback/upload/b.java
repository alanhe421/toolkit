package com.tencent.feedback.upload;

import com.tencent.feedback.common.e;

/* compiled from: RQDSRC */
public class b {
    private long a = 0;
    private long b = 0;
    private int c = 0;

    public synchronized void a(String str, long j, String str2) {
        e.b("rqdp{  send:}%s rqdp{  sz:}%d rqdp{  apn:}%s", str, Long.valueOf(j), str2);
        this.c++;
        this.a += j;
    }

    public synchronized void a(long j) {
        e.b("rqdp{  recevied:}%d", Long.valueOf(j));
        this.b += j;
    }

    public synchronized long a() {
        return this.a;
    }

    public synchronized long b() {
        return this.b;
    }
}
