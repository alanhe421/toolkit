package com.tencent.qalsdk.core;

import com.tencent.qalsdk.base.CloseConnReason;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import qalsdk.bf;

/* compiled from: EndpointKey */
public class c {
    public static final int a = 0;
    public static final int b = 1;
    public static final String c = "00000";
    public static final String d = "http";
    public static final String e = "socket";
    public byte f = (byte) 1;
    public byte g = (byte) 0;
    public String h = "";
    public AtomicInteger i = new AtomicInteger();
    long j = 0;
    public boolean k = false;
    private String l = e;
    private String m;
    private int n;
    private int o = 8000;
    private boolean p = false;

    public String toString() {
        return this.l + "://" + this.m + ":" + this.n + "#" + this.h + ":" + this.f + ":" + this.g + ":" + (this.o / 1000) + ":" + this.p;
    }

    public String a() {
        return this.m + ":" + this.n;
    }

    public static c a(bf bfVar, int i) {
        c cVar = new c();
        if (bfVar.e == (byte) 2 || bfVar.e == (byte) 3) {
            cVar.l = d;
        } else if (bfVar.e == (byte) 0 || bfVar.e == (byte) 1) {
            cVar.l = e;
        }
        cVar.m = bfVar.a;
        cVar.n = bfVar.b;
        cVar.h = "";
        cVar.f = (byte) 0;
        cVar.g = bfVar.d;
        if (bfVar.f > 20) {
            cVar.o = 20000;
        } else if (bfVar.f < 5) {
            cVar.o = 5000;
        } else {
            cVar.o = bfVar.f * 1000;
        }
        if (i == 0) {
            cVar.h = c;
            cVar.a(true);
        } else {
            cVar.a(false);
        }
        if (bfVar.g == (byte) 1) {
            cVar.p = true;
        }
        return cVar;
    }

    public static c a(String str) {
        CharSequence toLowerCase = str.toLowerCase();
        c cVar = new c();
        Matcher matcher = Pattern.compile("([a-zA-Z]+)://([a-zA-Z0-9.]+)(:([0-9]+))?(#([0-9_]*))?(:([0-9]+))?(:([0-9]+))?(:([0-9]+))?(:([a-zA-Z]+))?").matcher(toLowerCase);
        if (matcher.matches()) {
            if (matcher.group(1) != null) {
                cVar.l = matcher.group(1);
            }
            if (matcher.group(2) != null) {
                cVar.m = matcher.group(2);
            }
            if (matcher.group(4) != null) {
                cVar.n = Integer.parseInt(matcher.group(4));
            } else {
                cVar.n = 80;
            }
            if (matcher.group(6) != null) {
                cVar.h = matcher.group(6);
            }
            if (matcher.group(8) != null) {
                cVar.f = Byte.parseByte(matcher.group(8));
            }
            if (matcher.group(10) != null) {
                cVar.g = Byte.parseByte(matcher.group(10));
            }
            if (matcher.group(12) != null) {
                cVar.o = Integer.parseInt(matcher.group(12)) * 1000;
            }
            if (matcher.group(14) != null) {
                cVar.p = Boolean.parseBoolean(matcher.group(14));
            }
            cVar.a(cVar.h.equals(c));
        }
        return cVar;
    }

    public int hashCode() {
        return toString().hashCode();
    }

    public boolean equals(Object obj) {
        return (obj instanceof c) && ((c) obj).toString().equals(toString());
    }

    public String b() {
        return this.l;
    }

    public void b(String str) {
        this.l = str;
    }

    public String c() {
        return this.m;
    }

    public void c(String str) {
        this.m = str;
    }

    public int d() {
        return this.n;
    }

    public void a(int i) {
        this.n = i;
    }

    public int e() {
        return this.o;
    }

    public void b(int i) {
        this.o = i;
    }

    public void f() {
        this.j = System.currentTimeMillis();
    }

    public boolean a(CloseConnReason closeConnReason) {
        long currentTimeMillis = System.currentTimeMillis();
        if (closeConnReason == CloseConnReason.writeError || closeConnReason == CloseConnReason.readError) {
            if (this.j == 0 || currentTimeMillis - this.j > 600000) {
                this.j = currentTimeMillis;
                this.i.incrementAndGet();
            } else {
                this.i.addAndGet(10);
            }
        } else if (closeConnReason == CloseConnReason.continueWaitRspTimeout) {
            this.i.addAndGet(10);
        } else if (closeConnReason == CloseConnReason.closeByNetDetectFailed) {
            this.i.addAndGet(20);
        } else if (closeConnReason == CloseConnReason.invalidData || closeConnReason == CloseConnReason.connFull) {
            this.i.addAndGet(20);
        }
        if (this.i.get() <= 19) {
            return false;
        }
        this.i.set(0);
        return true;
    }

    public boolean g() {
        return this.k;
    }

    public void a(boolean z) {
        this.k = z;
    }

    public boolean h() {
        return this.p;
    }

    public void b(boolean z) {
        this.p = z;
    }
}
