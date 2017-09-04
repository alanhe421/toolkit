package com.tencent.upload.network.a;

import com.tencent.av.config.ConfigBaseParser;
import com.tencent.qalsdk.core.c;
import java.io.Serializable;

public final class k implements Serializable {
    public String a;
    private String b;
    private int c;
    private String d;
    private int e;
    private int f;
    private int g;

    public k(String str, int i, int i2, int i3) {
        this(str, 80, null, 0, 1, i3);
    }

    private k(String str, int i, String str2, int i2, int i3, int i4) {
        this.b = str;
        this.c = i;
        this.d = str2;
        this.e = i2;
        this.f = i3;
        this.g = i4;
    }

    public final int a() {
        return this.g;
    }

    public final void a(int i) {
        this.g = 3;
    }

    public final String b() {
        return this.b;
    }

    public final void b(int i) {
        this.c = i;
    }

    public final int c() {
        return this.c;
    }

    public final void c(int i) {
        this.f = i;
    }

    public final /* synthetic */ Object clone() {
        return g();
    }

    public final String d() {
        return this.d;
    }

    public final int e() {
        return this.e;
    }

    public final int f() {
        return this.f;
    }

    public final k g() {
        return new k(this.b, this.c, this.d, this.e, this.f, this.g);
    }

    public final String toString() {
        String str;
        StringBuilder append = new StringBuilder("(ip:").append(this.b == null ? "null" : this.b).append(", port:").append(this.c).append(", pIp:").append(this.d == null ? "null" : this.d).append(", pPort:").append(this.e).append(", ");
        switch (this.f) {
            case 1:
                str = "tcp";
                break;
            case 2:
                str = c.d;
                break;
            default:
                str = "unknown ProtocolCategory";
                break;
        }
        append = append.append(str).append(", ");
        switch (this.g) {
            case 1:
                str = "optimum";
                break;
            case 2:
                str = "redirect";
                break;
            case 3:
                str = "recent";
                break;
            case 4:
                str = "host";
                break;
            case 5:
                str = "backup";
                break;
            case 6:
                str = "cdn";
                break;
            default:
                str = ConfigBaseParser.DEFAULT_VALUE;
                break;
        }
        return append.append(str).append(", resolveIP:").append(this.a).append(")").toString();
    }
}
