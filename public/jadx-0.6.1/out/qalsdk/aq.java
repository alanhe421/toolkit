package qalsdk;

import com.qq.taf.jce.JceStruct;
import com.qq.taf.jce.b;
import com.qq.taf.jce.c;
import com.qq.taf.jce.d;
import com.qq.taf.jce.e;

/* compiled from: NetInfoParam */
public final class aq extends JceStruct implements Cloneable {
    static final /* synthetic */ boolean h = (!aq.class.desiredAssertionStatus());
    public String a = "";
    public String b = "";
    public String c = "";
    public String d = "";
    public int e = 0;
    public int f = 0;
    public int g = 0;

    public String a() {
        return "QALNetInfoParam.NetInfoParam";
    }

    public String b() {
        return "com.qq.QALNetInfoParam.NetInfoParam";
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

    public String e() {
        return this.c;
    }

    public void c(String str) {
        this.c = str;
    }

    public String f() {
        return this.d;
    }

    public void d(String str) {
        this.d = str;
    }

    public int g() {
        return this.e;
    }

    public void a(int i) {
        this.e = i;
    }

    public int h() {
        return this.f;
    }

    public void b(int i) {
        this.f = i;
    }

    public int i() {
        return this.g;
    }

    public void c(int i) {
        this.g = i;
    }

    public aq(String str, String str2, String str3, String str4, int i, int i2, int i3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = i;
        this.f = i2;
        this.g = i3;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        aq aqVar = (aq) obj;
        if (e.a(this.a, aqVar.a) && e.a(this.b, aqVar.b) && e.a(this.c, aqVar.c) && e.a(this.d, aqVar.d) && e.a(this.e, aqVar.e) && e.a(this.f, aqVar.f) && e.a(this.g, aqVar.g)) {
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
        dVar.a(this.d, 4);
        dVar.a(this.e, 5);
        dVar.a(this.f, 6);
        dVar.a(this.g, 7);
    }

    public void readFrom(c cVar) {
        this.a = cVar.a(1, true);
        this.b = cVar.a(2, true);
        this.c = cVar.a(3, true);
        this.d = cVar.a(4, true);
        this.e = cVar.a(this.e, 5, true);
        this.f = cVar.a(this.f, 6, true);
        this.g = cVar.a(this.g, 7, true);
    }

    public void display(StringBuilder stringBuilder, int i) {
        b bVar = new b(stringBuilder, i);
        bVar.a(this.a, "apn");
        bVar.a(this.b, "wifi_supplicant_state");
        bVar.a(this.c, "wifi_ssid");
        bVar.a(this.d, "wifi_bssid");
        bVar.a(this.e, "wifi_rssi");
        bVar.a(this.f, "rat");
        bVar.a(this.g, "rat_ss");
    }
}
