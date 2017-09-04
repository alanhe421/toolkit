package com.tencent.qalsdk.sdk;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.b;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import com.qq.taf.jce.e;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: NotifyRegisterInfo */
public final class ag extends JceStruct implements Cloneable {
    static ArrayList<Long> d;
    static Map<Long, String> e;
    static final /* synthetic */ boolean f = (!ag.class.desiredAssertionStatus());
    public String a = "";
    public ArrayList<Long> b = null;
    public Map<Long, String> c = null;

    public String a() {
        return "clientPushInfo.NotifyRegisterInfo";
    }

    public String b() {
        return "clientPushInfo.NotifyRegisterInfo";
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

    public Map<Long, String> e() {
        return this.c;
    }

    public void a(Map<Long, String> map) {
        this.c = map;
    }

    public ag() {
        a(this.a);
        a(this.b);
        a(this.c);
    }

    public ag(String str, ArrayList<Long> arrayList, Map<Long, String> map) {
        a(str);
        a((ArrayList) arrayList);
        a((Map) map);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        ag agVar = (ag) obj;
        if (e.a(this.a, agVar.a) && e.a(this.b, agVar.b) && e.a(this.c, agVar.c)) {
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
            if (!f) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    public void writeTo(d dVar) {
        dVar.a(this.a, 1);
        dVar.a(this.b, 2);
        dVar.a(this.c, 3);
    }

    public void readFrom(c cVar) {
        a(cVar.a(1, true));
        if (d == null) {
            d = new ArrayList();
            d.add(Long.valueOf(0));
        }
        a((ArrayList) cVar.a(d, 2, true));
        if (e == null) {
            e = new HashMap();
            e.put(Long.valueOf(0), "");
        }
        a((Map) cVar.a(e, 3, true));
    }

    public void display(StringBuilder stringBuilder, int i) {
        b bVar = new b(stringBuilder, i);
        bVar.a(this.a, "uin");
        bVar.a(this.b, "notifyIds");
        bVar.a(this.c, "notifyProperties");
    }
}
