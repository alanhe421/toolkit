package qalsdk;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.b;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import com.qq.taf.jce.e;
import java.util.ArrayList;

/* compiled from: NetInfoParamArray */
public final class ar extends JceStruct implements Cloneable {
    static ArrayList<aq> b;
    static final /* synthetic */ boolean c = (!ar.class.desiredAssertionStatus());
    public ArrayList<aq> a = null;

    public String a() {
        return "QALNetInfoParam.NetInfoParamArray";
    }

    public String b() {
        return "com.qq.QALNetInfoParam.NetInfoParamArray";
    }

    public ArrayList<aq> c() {
        return this.a;
    }

    public void a(ArrayList<aq> arrayList) {
        this.a = arrayList;
    }

    public ar(ArrayList<aq> arrayList) {
        this.a = arrayList;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        return e.a(this.a, ((ar) obj).a);
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
            if (!c) {
                throw new AssertionError();
            }
        }
        return obj;
    }

    public void writeTo(d dVar) {
        dVar.a(this.a, 1);
    }

    public void readFrom(c cVar) {
        if (b == null) {
            b = new ArrayList();
            b.add(new aq());
        }
        this.a = (ArrayList) cVar.a(b, 1, true);
    }

    public void display(StringBuilder stringBuilder, int i) {
        new b(stringBuilder, i).a(this.a, "netInfos");
    }
}
