package com.tencent.qalsdk.sdk;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.b;
import com.qq.taf.jce.c;
import com.qq.taf.jce.e;

/* compiled from: ConnInfo */
public final class d extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean e = (!d.class.desiredAssertionStatus());
    public String a = "";
    public int b = 0;
    public String c = "";
    public String d = "";

    public String a() {
        return "QALConn.ConnInfo";
    }

    public String b() {
        return "QALConn.ConnInfo";
    }

    public String c() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public int d() {
        return this.b;
    }

    public void a(int i) {
        this.b = i;
    }

    public String e() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public String f() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public d(String str, int i, String str2, String str3) {
        this.a = str;
        this.b = i;
        this.c = str2;
        this.d = str3;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        d dVar = (d) obj;
        if (e.a(this.a, dVar.a) && e.a(this.b, dVar.b) && e.a(this.c, dVar.c) && e.a(this.d, dVar.d)) {
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
            if (!e) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    public void writeTo(com.qq.taf.jce.d dVar) {
        dVar.a(this.a, 1);
        dVar.a(this.b, 2);
        dVar.a(this.c, 3);
        dVar.a(this.d, 4);
    }

    public void readFrom(c cVar) {
        this.a = cVar.a(1, true);
        this.b = cVar.a(this.b, 2, true);
        this.c = cVar.a(3, true);
        this.d = cVar.a(4, true);
    }

    public void display(StringBuilder stringBuilder, int i) {
        b bVar = new b(stringBuilder, i);
        bVar.a(this.a, "apn");
        bVar.a(this.b, "radioType");
        bVar.a(this.c, "serverIP");
        bVar.a(this.d, "gateIP");
    }
}
