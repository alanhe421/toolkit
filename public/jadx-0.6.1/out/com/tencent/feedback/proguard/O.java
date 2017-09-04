package com.tencent.feedback.proguard;

/* compiled from: RQDSRC */
public final class o {
    private long a;
    private int b;
    private String c;
    private long d;
    private long e;
    private String f;
    private String g;

    public final void a(long j) {
        this.a = j;
    }

    public final String a() {
        return this.c;
    }

    public final void a(String str) {
        this.c = str;
    }

    public final long b() {
        return this.d;
    }

    public final void b(long j) {
        this.d = j;
    }

    public final long c() {
        return this.e;
    }

    public final void c(long j) {
        this.e = j;
    }

    public final String d() {
        return this.f;
    }

    public final void b(String str) {
        this.f = str;
    }

    public final int e() {
        return this.b;
    }

    public final void a(int i) {
        this.b = i;
    }

    public final String f() {
        return this.g;
    }

    public final void c(String str) {
        this.g = str;
    }

    public final String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("rqdp{   fT:}");
        stringBuffer.append(this.b);
        stringBuffer.append("rqdp{   cid:}");
        stringBuffer.append(this.a);
        stringBuffer.append("rqdp{   fN:}");
        stringBuffer.append(this.c);
        stringBuffer.append("rqdp{   UT:}");
        stringBuffer.append(this.d);
        stringBuffer.append("rqdp{   size:}");
        stringBuffer.append(this.e);
        stringBuffer.append("rqdp{   SID:}");
        stringBuffer.append(this.f);
        stringBuffer.append("rqdp{   ARCH:}");
        stringBuffer.append(this.g);
        String stringBuffer2 = stringBuffer.toString();
        stringBuffer.setLength(0);
        return stringBuffer2;
    }
}
