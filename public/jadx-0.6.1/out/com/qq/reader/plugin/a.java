package com.qq.reader.plugin;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.qq.reader.common.login.c;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderNetTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.PluginNetTask;
import java.io.File;

/* compiled from: BasePluginHandler */
public abstract class a {
    protected Context a;
    protected String b = "";
    protected boolean c = false;
    protected boolean d = false;
    protected transient boolean e = false;
    protected transient boolean f = false;
    protected transient boolean g = false;
    protected transient boolean h = false;
    protected String i = null;
    protected l j;
    protected g k;
    Object l = new Object();
    protected Handler m = new Handler(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            boolean z = false;
            switch (message.what) {
                case 6104:
                    if (this.a.v.a(this.a.j.i(), (long) message.arg1, 3, null, 6)) {
                        this.a.j.b(3);
                        this.a.j.a((long) message.arg1);
                    }
                    z = true;
                    break;
                case 6105:
                    this.a.t = (long) message.arg1;
                    break;
                case 6106:
                    if (this.a.v.a(this.a.j.i(), 0, 2, null, 2)) {
                        this.a.d(false);
                        this.a.j.b(2);
                    }
                    z = true;
                    break;
                case 6107:
                    if (this.a.v.a(this.a.j.i(), 0, 1, null, 2)) {
                        this.a.d(true);
                        this.a.j.b(1);
                    }
                    z = true;
                    break;
                case 6108:
                    if (this.a.v.a(this.a.j.i(), 0, 4, null, 2)) {
                        this.a.j.b(4);
                    }
                    z = true;
                    break;
                case 6109:
                    if (this.a.j.d() != 4) {
                        if (this.a.v.a(this.a.j.i(), 0, 1, null, 6)) {
                            this.a.d(false);
                            this.a.t = 0;
                            this.a.j.a(0);
                            this.a.j.b(1);
                        }
                        if (this.a.k != null) {
                            this.a.k.a(this.a.j.i(), (String) message.obj);
                        }
                    }
                    z = true;
                    break;
                case 6110:
                    if (this.a.v.a(this.a.j.i(), 0, 1, null, 6)) {
                        this.a.j.b(1);
                        this.a.t = 0;
                    }
                    z = true;
                    break;
                case 6112:
                    if (this.a.v.a(this.a.j.i(), 0, 1, null, 2)) {
                        this.a.t = 0;
                        this.a.j.b(1);
                        if (this.a.k != null) {
                            this.a.k.a(this.a.j);
                        }
                    }
                    z = true;
                    break;
                case 6113:
                    if (this.a.v.a(this.a.j.i(), 0, 1, null, 2)) {
                        this.a.t = 0;
                        this.a.j.b(1);
                        if (this.a.k != null) {
                            this.a.k.a(this.a.j, (String) message.obj);
                        }
                    }
                    z = true;
                    break;
                case 6115:
                    if (this.a.v.a(this.a.j.i(), 0, 8, null, 2)) {
                        this.a.j.b(8);
                    }
                    z = true;
                    break;
                case 6117:
                    if (this.a.j.d() != 4) {
                        if (this.a.v.a(this.a.j.i(), 0, 1, null, 6)) {
                            this.a.d(false);
                            this.a.t = 0;
                            this.a.j.a(0);
                            this.a.j.b(1);
                        }
                        if (this.a.k != null) {
                            this.a.k.a(this.a.j, (Bundle) message.obj);
                        }
                    }
                    z = true;
                    break;
            }
            if (this.a.k != null) {
                this.a.k.a(this.a.j.i(), z);
            }
        }
    };
    private String n = "";
    private String o = "";
    private String p = "";
    private String q = "";
    private String r = "";
    private long s = 0;
    private long t = 0;
    private PluginNetTask u;
    private h v;

    protected abstract String a(l lVar);

    protected abstract void a(Bundle bundle);

    protected abstract void a(String str);

    protected abstract void a(String str, Context context);

    protected abstract void b(String str);

    public abstract boolean i();

    public abstract boolean j();

    public abstract boolean k();

    public abstract boolean l();

    public a(Context context, l lVar, h hVar) {
        this.a = context;
        this.j = lVar;
        this.i = a(lVar);
        this.v = hVar;
        if (this.j.d() != 0) {
            return;
        }
        if (this.i != null) {
            File file = new File(this.i);
            if (file.exists()) {
                long length = file.length();
                long f = lVar.f();
                if (f == 0) {
                    lVar.a(length);
                    this.v.a(this.j.i(), length, 0, null, 4);
                    this.j.b(4);
                    return;
                } else if (length > f) {
                    file.delete();
                    lVar.a(0);
                    this.v.a(this.j.i(), 0, 0, null, 4);
                    this.j.b(1);
                    return;
                } else if (length < lVar.f()) {
                    this.j.b(2);
                    this.t = length;
                    return;
                } else {
                    this.j.b(4);
                    this.t = f;
                    return;
                }
            }
            this.j.b(1);
        } else if (i()) {
            this.j.b(4);
        } else {
            this.j.b(1);
        }
    }

    public h a() {
        return this.v;
    }

    public l b() {
        return this.j;
    }

    public long c() {
        return this.j.f();
    }

    public long d() {
        return this.t;
    }

    public int e() {
        return this.j.d();
    }

    public String f() {
        return this.j.i();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void a(com.qq.reader.common.readertask.ordinal.ReaderNetTask r6, java.lang.String r7) {
        /*
        r5 = this;
        r2 = 1;
        r4 = 0;
        r5.c(r2);
        r0 = r5.s();
        if (r0 == 0) goto L_0x0012;
    L_0x000b:
        r5.d(r4);
        r5.c(r4);
    L_0x0011:
        return;
    L_0x0012:
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0088 }
        r0.<init>(r7);	 Catch:{ Exception -> 0x0088 }
        r1 = "plugin_code";
        r1 = r0.optInt(r1);	 Catch:{ Exception -> 0x0088 }
        if (r1 != 0) goto L_0x00b2;
    L_0x0020:
        r1 = "plugin_version";
        r1 = r0.optString(r1);	 Catch:{ Exception -> 0x0088 }
        r5.b = r1;	 Catch:{ Exception -> 0x0088 }
        r1 = "plugin_url";
        r1 = r0.optString(r1);	 Catch:{ Exception -> 0x0088 }
        r5.n = r1;	 Catch:{ Exception -> 0x0088 }
        r1 = "vaildtime";
        r2 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
        r0 = r0.optLong(r1, r2);	 Catch:{ Exception -> 0x0088 }
        r5.s = r0;	 Catch:{ Exception -> 0x0088 }
        r0 = r5.n;	 Catch:{ Exception -> 0x0088 }
        r0 = r0.length();	 Catch:{ Exception -> 0x0088 }
        if (r0 == 0) goto L_0x0050;
    L_0x0048:
        r0 = r5.b;	 Catch:{ Exception -> 0x0088 }
        r0 = r0.length();	 Catch:{ Exception -> 0x0088 }
        if (r0 != 0) goto L_0x0080;
    L_0x0050:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0088 }
        r0.<init>();	 Catch:{ Exception -> 0x0088 }
        r1 = "downloadUrl : ";
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x0088 }
        r1 = r5.n;	 Catch:{ Exception -> 0x0088 }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x0088 }
        r1 = " | server_plugin_version : ";
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x0088 }
        r1 = r5.b;	 Catch:{ Exception -> 0x0088 }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x0088 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0088 }
        com.qq.reader.common.monitor.j.c(r0);	 Catch:{ Exception -> 0x0088 }
        r0 = "服务器错误，请稍后再试。";
        r5.b(r0);	 Catch:{ Exception -> 0x0088 }
    L_0x007c:
        r5.c(r4);
        goto L_0x0011;
    L_0x0080:
        r0 = r5.n;	 Catch:{ Exception -> 0x0088 }
        r1 = r5.a;	 Catch:{ Exception -> 0x0088 }
        r5.a(r0, r1);	 Catch:{ Exception -> 0x0088 }
        goto L_0x007c;
    L_0x0088:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0180 }
        r1.<init>();	 Catch:{ all -> 0x0180 }
        r2 = "Exception : ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0180 }
        r2 = r0.toString();	 Catch:{ all -> 0x0180 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0180 }
        r1 = r1.toString();	 Catch:{ all -> 0x0180 }
        com.qq.reader.common.monitor.j.c(r1);	 Catch:{ all -> 0x0180 }
        com.qq.reader.common.monitor.b.a(r0);	 Catch:{ all -> 0x0180 }
        r0 = "服务器错误，请稍后再试。";
        r5.b(r0);	 Catch:{ all -> 0x0180 }
        r5.c(r4);
        goto L_0x0011;
    L_0x00b2:
        if (r1 != r2) goto L_0x00bf;
    L_0x00b4:
        r0 = "当前客户端版本不支持，请升级到最新版本。";
        r5.b(r0);	 Catch:{ Exception -> 0x0088 }
        r5.c(r4);
        goto L_0x0011;
    L_0x00bf:
        r2 = 2;
        if (r1 != r2) goto L_0x0102;
    L_0x00c2:
        r1 = r5.j;	 Catch:{ Exception -> 0x0088 }
        r1 = r1.u();	 Catch:{ Exception -> 0x0088 }
        if (r1 == 0) goto L_0x00f5;
    L_0x00ca:
        r1 = r5.j;	 Catch:{ Exception -> 0x0088 }
        r2 = "1";
        r1.e(r2);	 Catch:{ Exception -> 0x0088 }
        r1 = r5.j;	 Catch:{ Exception -> 0x0088 }
        r2 = "plugin_price";
        r0 = r0.getString(r2);	 Catch:{ Exception -> 0x0088 }
        r1.f(r0);	 Catch:{ Exception -> 0x0088 }
        r0 = r5.v;	 Catch:{ Exception -> 0x0088 }
        r1 = r5.j;	 Catch:{ Exception -> 0x0088 }
        r1 = r1.i();	 Catch:{ Exception -> 0x0088 }
        r2 = r5.j;	 Catch:{ Exception -> 0x0088 }
        r2 = r2.t();	 Catch:{ Exception -> 0x0088 }
        r3 = r5.j;	 Catch:{ Exception -> 0x0088 }
        r3 = r3.v();	 Catch:{ Exception -> 0x0088 }
        r0.a(r1, r2, r3);	 Catch:{ Exception -> 0x0088 }
    L_0x00f5:
        r0 = 0;
        r5.d(r0);	 Catch:{ Exception -> 0x0088 }
        r0 = 0;
        r5.a(r0);	 Catch:{ Exception -> 0x0088 }
        r5.c(r4);
        goto L_0x0011;
    L_0x0102:
        r2 = 3;
        if (r1 != r2) goto L_0x0111;
    L_0x0105:
        r0 = 0;
        r5.d(r0);	 Catch:{ Exception -> 0x0088 }
        r5.h();	 Catch:{ Exception -> 0x0088 }
        r5.c(r4);
        goto L_0x0011;
    L_0x0111:
        r2 = 4;
        if (r1 != r2) goto L_0x0185;
    L_0x0114:
        r1 = "message";
        r1 = r0.optString(r1);	 Catch:{ Exception -> 0x0088 }
        r5.p = r1;	 Catch:{ Exception -> 0x0088 }
        r1 = "buttonok";
        r1 = r0.optString(r1);	 Catch:{ Exception -> 0x0088 }
        r5.q = r1;	 Catch:{ Exception -> 0x0088 }
        r1 = "qurl";
        r1 = r0.optString(r1);	 Catch:{ Exception -> 0x0088 }
        r5.r = r1;	 Catch:{ Exception -> 0x0088 }
        r1 = "id";
        r0 = r0.optString(r1);	 Catch:{ Exception -> 0x0088 }
        r1 = r5.p;	 Catch:{ Exception -> 0x0088 }
        r1 = r1.length();	 Catch:{ Exception -> 0x0088 }
        if (r1 <= 0) goto L_0x0179;
    L_0x013e:
        r1 = r5.q;	 Catch:{ Exception -> 0x0088 }
        r1 = r1.length();	 Catch:{ Exception -> 0x0088 }
        if (r1 <= 0) goto L_0x0179;
    L_0x0146:
        r1 = r5.r;	 Catch:{ Exception -> 0x0088 }
        r1 = com.qq.reader.qurl.c.a(r1);	 Catch:{ Exception -> 0x0088 }
        if (r1 == 0) goto L_0x0179;
    L_0x014e:
        r1 = new android.os.Bundle;	 Catch:{ Exception -> 0x0088 }
        r1.<init>();	 Catch:{ Exception -> 0x0088 }
        r2 = "message";
        r3 = r5.p;	 Catch:{ Exception -> 0x0088 }
        r1.putString(r2, r3);	 Catch:{ Exception -> 0x0088 }
        r2 = "buttonok";
        r3 = r5.q;	 Catch:{ Exception -> 0x0088 }
        r1.putString(r2, r3);	 Catch:{ Exception -> 0x0088 }
        r2 = "qurl";
        r3 = r5.r;	 Catch:{ Exception -> 0x0088 }
        r1.putString(r2, r3);	 Catch:{ Exception -> 0x0088 }
        r2 = "id";
        r1.putString(r2, r0);	 Catch:{ Exception -> 0x0088 }
        r5.a(r1);	 Catch:{ Exception -> 0x0088 }
    L_0x0174:
        r5.c(r4);
        goto L_0x0011;
    L_0x0179:
        r0 = "下载失败。";
        r5.b(r0);	 Catch:{ Exception -> 0x0088 }
        goto L_0x0174;
    L_0x0180:
        r0 = move-exception;
        r5.c(r4);
        throw r0;
    L_0x0185:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0088 }
        r0.<init>();	 Catch:{ Exception -> 0x0088 }
        r2 = "plugin_code : ";
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x0088 }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x0088 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0088 }
        com.qq.reader.common.monitor.j.c(r0);	 Catch:{ Exception -> 0x0088 }
        r0 = "下载失败。";
        r5.b(r0);	 Catch:{ Exception -> 0x0088 }
        r5.c(r4);
        goto L_0x0011;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.plugin.a.a(com.qq.reader.common.readertask.ordinal.ReaderNetTask, java.lang.String):void");
    }

    public Context g() {
        return this.a;
    }

    protected void h() {
        this.m.sendEmptyMessage(6112);
    }

    public void a(boolean z) {
        this.c = z;
    }

    public boolean m() {
        return this.c;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public boolean n() {
        if (this.j.d() == 4 || this.j.d() == 7) {
            String m = this.j.m();
            String b = this.j.b();
            String c = this.j.c();
            if (!(m == null || b == null || c == null || b.length() <= 0 || c.length() <= 0 || m.equals(b) || c.indexOf(m) != -1)) {
                return false;
            }
        }
        return true;
    }

    public boolean o() {
        if (this.j.d() == 4 || this.j.d() == 7) {
            String m = this.j.m();
            String b = this.j.b();
            if (!(m == null || b == null || b.trim().length() <= 0 || m.trim().equals(b.trim()))) {
                return true;
            }
        }
        return false;
    }

    public synchronized void p() {
        if (c.b()) {
            k();
            r();
        } else {
            h();
        }
    }

    public PluginNetTask q() {
        return new PluginNetTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                this.a.a((ReaderNetTask) readerProtocolTask, str);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                this.a.b("网络连接失败，请稍后再试。");
            }
        }, this.j.i(), this.j.b());
    }

    public synchronized void r() {
        if (c.b()) {
            if (!t()) {
                this.u = q();
                g.a().a(this.u);
            }
            this.m.sendEmptyMessage(6106);
        } else {
            h();
        }
    }

    public void c(boolean z) {
        synchronized (this.l) {
            this.f = z;
        }
    }

    public boolean s() {
        boolean z;
        synchronized (this.l) {
            z = this.e;
        }
        return z;
    }

    public void d(boolean z) {
        synchronized (this.l) {
            this.e = z;
        }
    }

    public boolean t() {
        boolean z = false;
        synchronized (this.l) {
            if (this.f && this.e) {
                this.e = false;
                z = true;
            }
        }
        return z;
    }

    public synchronized void u() {
        this.m.sendEmptyMessage(6107);
    }

    public synchronized void a(g gVar) {
        this.k = gVar;
    }

    public synchronized void v() {
        this.k = null;
    }
}
