package com.tencent.qalsdk.sdk;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.b;
import com.qq.taf.jce.d;
import com.qq.taf.jce.e;
import java.util.ArrayList;

/* compiled from: CommandCallbackerInfo */
public final class c extends JceStruct implements Cloneable {
    static ArrayList<String> c;
    static final /* synthetic */ boolean d = (!c.class.desiredAssertionStatus());
    public String a = "";
    public ArrayList<String> b = null;

    public String a() {
        return "clientPushInfo.CommandCallbackerInfo";
    }

    public String b() {
        return "clientPushInfo.CommandCallbackerInfo";
    }

    public String c() {
        return this.a;
    }

    public void a(String str) {
        this.a = str;
    }

    public ArrayList<String> d() {
        return this.b;
    }

    public void a(ArrayList<String> arrayList) {
        this.b = arrayList;
    }

    public c() {
        a(this.a);
        a(this.b);
    }

    public c(String str, ArrayList<String> arrayList) {
        a(str);
        a((ArrayList) arrayList);
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        c cVar = (c) obj;
        if (e.a(this.a, cVar.a) && e.a(this.b, cVar.b)) {
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
            if (!d) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    public void writeTo(d dVar) {
        dVar.a(this.a, 1);
        dVar.a(this.b, 2);
    }

    public void readFrom(com.qq.taf.jce.c cVar) {
        a(cVar.a(1, true));
        if (c == null) {
            c = new ArrayList();
            c.add("");
        }
        a((ArrayList) cVar.a(c, 2, true));
    }

    public void display(StringBuilder stringBuilder, int i) {
        b bVar = new b(stringBuilder, i);
        bVar.a(this.a, "uin");
        bVar.a(this.b, "cmds");
    }
}
