package com.tencent.qalsdk.sdk;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.b;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import com.qq.taf.jce.e;
import java.util.ArrayList;

/* compiled from: PushRegisterInfo */
public final class ah extends JceStruct implements Cloneable {
    static ArrayList<Long> g;
    static final /* synthetic */ boolean h = (!ah.class.desiredAssertionStatus());
    public String a = "";
    public ArrayList<Long> b = null;
    public int c = 0;
    public byte d = (byte) 0;
    public byte e = (byte) 0;
    public long f = 0;

    public String a() {
        return "clientPushInfo.PushRegisterInfo";
    }

    public String b() {
        return "clientPushInfo.PushRegisterInfo";
    }

    public String c() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public ArrayList<Long> d() {
        return this.b;
    }

    public void a(ArrayList<Long> arrayList) {
        this.b = arrayList;
    }

    public int e() {
        return this.c;
    }

    public void a(int i) {
        this.c = i;
    }

    public byte f() {
        return this.d;
    }

    public void a(byte b) {
        this.d = b;
    }

    public byte g() {
        return this.e;
    }

    public void b(byte b) {
        this.e = b;
    }

    public long h() {
        return this.f;
    }

    public void a(long j) {
        this.f = j;
    }

    public ah() {
        a(this.a);
        a(this.b);
        a(this.c);
        a(this.d);
        b(this.e);
        a(this.f);
    }

    public ah(String str, ArrayList<Long> arrayList, int i, byte b, byte b2, long j) {
        a(str);
        a((ArrayList) arrayList);
        a(i);
        a(b);
        b(b2);
        a(j);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        ah ahVar = (ah) obj;
        if (e.a(this.a, ahVar.a) && e.a(this.b, ahVar.b) && e.a(this.c, ahVar.c) && e.a(this.d, ahVar.d) && e.a(this.e, ahVar.e) && e.a(this.f, ahVar.f)) {
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
            if (!h) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    public void writeTo(d dVar) {
        dVar.a(this.a, 1);
        dVar.a(this.b, 2);
        dVar.a(this.c, 3);
        dVar.b(this.d, 4);
        dVar.b(this.e, 5);
        dVar.a(this.f, 6);
    }

    public void readFrom(c cVar) {
        a(cVar.a(1, true));
        if (g == null) {
            g = new ArrayList();
            g.add(Long.valueOf(0));
        }
        a((ArrayList) cVar.a(g, 2, true));
        a(cVar.a(this.c, 3, true));
        a(cVar.a(this.d, 4, true));
        b(cVar.a(this.e, 5, true));
        a(cVar.a(this.f, 6, true));
    }

    public void display(StringBuilder stringBuilder, int i) {
        b bVar = new b(stringBuilder, i);
        bVar.a(this.a, "uin");
        bVar.a(this.b, "pushIds");
        bVar.a(this.c, "iStatus");
        bVar.a(this.d, "bKikPC");
        bVar.a(this.e, "bKikWeak");
        bVar.a(this.f, "timeStamp");
    }
}
