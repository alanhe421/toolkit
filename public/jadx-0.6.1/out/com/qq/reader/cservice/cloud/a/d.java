package com.qq.reader.cservice.cloud.a;

/* compiled from: CloudBatDelBookActionBig */
public class d extends g {
    long a;
    String b;

    public d(long j, String str, int i) {
        super(-1, 1, 0, 0, i);
        this.l = "delete";
        this.a = j;
        this.b = str;
        this.m = 1;
    }

    public static String a(int i) {
        switch (i) {
            case 1:
                return "txt";
            case 2:
                return "listen";
            case 3:
                return "comic";
            default:
                return "txt";
        }
    }

    public String a() {
        return this.b;
    }

    public long b() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof d)) {
            return false;
        }
        boolean z = this.l.equals(((g) obj).n()) && this.e == ((g) obj).h() && this.f == ((g) obj).i();
        return z;
    }
}
