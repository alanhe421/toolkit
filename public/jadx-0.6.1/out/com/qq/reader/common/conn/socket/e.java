package com.qq.reader.common.conn.socket;

import android.net.NetworkInfo;

/* compiled from: PushNetWorkInfo */
public class e {
    private boolean a = true;
    private String b = "";
    private String c = "";

    public e(NetworkInfo networkInfo) {
        if (networkInfo != null) {
            this.a = networkInfo.isAvailable();
            this.b = networkInfo.getTypeName() == null ? "" : networkInfo.getTypeName();
            this.c = networkInfo.getExtraInfo() == null ? "" : networkInfo.getExtraInfo();
        }
    }

    public boolean a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }

    public void a(e eVar) {
        if (eVar != null) {
            this.a = eVar.a();
            this.b = eVar.b() == null ? "" : eVar.b();
            this.c = eVar.c() == null ? "" : eVar.c();
            return;
        }
        this.a = false;
        this.b = "";
        this.c = "";
    }

    public boolean b(e eVar) {
        if (eVar != null && this.a == eVar.a() && this.b.equals(eVar.b()) && this.c.equals(eVar.c())) {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("network isAvailable:").append(this.a).append(", network:").append(this.b).append(",extraInfo:").append(this.c);
        return stringBuffer.toString();
    }
}
