package com.tencent.qalsdk.sdk;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.b;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import com.qq.taf.jce.e;

/* compiled from: AccountInfo */
public final class a extends JceStruct implements Cloneable {
    static byte[] i;
    static byte[] j;
    static byte[] k;
    static final /* synthetic */ boolean l = (!a.class.desiredAssertionStatus());
    public String a = "";
    public String b = "";
    public byte[] c = null;
    public byte[] d = null;
    public byte[] e = null;
    public int f = 0;
    public byte g = (byte) 0;
    public long h = 0;

    public String a() {
        return "Account.AccountInfo";
    }

    public String b() {
        return "com.qq.Account.AccountInfo";
    }

    public String c() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public String d() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public byte[] e() {
        return this.c;
    }

    public void a(byte[] bArr) {
        this.c = bArr;
    }

    public byte[] f() {
        return this.d;
    }

    public void b(byte[] bArr) {
        this.d = bArr;
    }

    public byte[] g() {
        return this.e;
    }

    public void c(byte[] bArr) {
        this.e = bArr;
    }

    public int h() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
    }

    public byte i() {
        return this.g;
    }

    public void a(byte b) {
        this.g = b;
    }

    public long j() {
        return this.h;
    }

    public void a(long j) {
        this.h = j;
    }

    public a(String str, String str2, byte[] bArr, byte[] bArr2, byte[] bArr3, int i, byte b, long j) {
        this.a = str;
        this.b = str2;
        this.c = bArr;
        this.d = bArr2;
        this.e = bArr3;
        this.f = i;
        this.g = b;
        this.h = j;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        a aVar = (a) obj;
        if (e.a(this.a, aVar.a) && e.a(this.b, aVar.b) && e.a(this.c, aVar.c) && e.a(this.d, aVar.d) && e.a(this.e, aVar.e) && e.a(this.f, aVar.f) && e.a(this.g, aVar.g) && e.a(this.h, aVar.h)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        try {
            throw new Exception("Need define key first!");
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public Object clone() {
        Object obj = null;
        try {
            obj = super.clone();
        } catch (CloneNotSupportedException e) {
            if (!l) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    public void writeTo(d dVar) {
        dVar.a(this.a, 1);
        dVar.a(this.b, 2);
        dVar.a(this.c, 3);
        dVar.a(this.d, 4);
        dVar.a(this.e, 5);
        dVar.a(this.f, 6);
        dVar.b(this.g, 7);
        dVar.a(this.h, 8);
    }

    public void readFrom(c cVar) {
        this.a = cVar.a(1, true);
        this.b = cVar.a(2, true);
        if (i == null) {
            i = new byte[1];
            i[0] = (byte) 0;
        }
        this.c = cVar.a(i, 3, true);
        if (j == null) {
            j = new byte[1];
            j[0] = (byte) 0;
        }
        this.d = cVar.a(j, 4, true);
        if (k == null) {
            k = new byte[1];
            k[0] = (byte) 0;
        }
        this.e = cVar.a(k, 5, true);
        this.f = cVar.a(this.f, 6, true);
        this.g = cVar.a(this.g, 7, true);
        this.h = cVar.a(this.h, 8, true);
    }

    public void display(StringBuilder stringBuilder, int i) {
        b bVar = new b(stringBuilder, i);
        bVar.a(this.a, "userID");
        bVar.a(this.b, "tinyID");
        bVar.a(this.c, "a2");
        bVar.a(this.d, "d2");
        bVar.a(this.e, "d2Key");
        bVar.a(this.f, "sdkAppID");
        bVar.a(this.g, "bRegister");
        bVar.a(this.h, "lastSendPackTime");
    }
}
