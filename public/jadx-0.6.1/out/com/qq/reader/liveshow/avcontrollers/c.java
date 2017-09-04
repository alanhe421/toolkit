package com.qq.reader.liveshow.avcontrollers;

import android.content.Context;
import android.view.View;
import com.qq.reader.liveshow.utils.SxbLog;
import com.tencent.av.sdk.AVContext;
import com.tencent.av.sdk.AVRoomMulti;
import java.util.ArrayList;

/* compiled from: QavsdkControl */
public class c {
    private static c c = null;
    private static Context d;
    private static AVRoomMulti e;
    private a a = null;
    private AVUIControl b = null;
    private ArrayList<String> f = new ArrayList();

    /* compiled from: QavsdkControl */
    public interface a {
        void a();

        void b();

        void c();

        void d();
    }

    public static c a() {
        if (c == null) {
            c = new c(d);
        }
        return c;
    }

    public ArrayList<String> b() {
        return this.f;
    }

    public static void a(Context context) {
        d = context;
    }

    private c(Context context) {
        this.a = new a(context);
        SxbLog.c("QavsdkControl", "WL_DEBUG QavsdkControl");
    }

    public void c() {
        this.f.clear();
    }

    public int d() {
        if (this.a == null) {
            return -99999998;
        }
        return this.a.a();
    }

    public void a(int i, String str, String str2, String str3) {
        if (this.a != null) {
            this.a.a(i, str, str2, str3);
        }
    }

    public void e() {
        if (this.a != null) {
            this.a.b();
        }
    }

    public String f() {
        if (this.a == null) {
            return null;
        }
        return this.a.e();
    }

    public boolean g() {
        if (this.a == null) {
            return false;
        }
        a aVar = this.a;
        return a.f();
    }

    public void a(boolean z) {
        SxbLog.c("QavsdkControl", "setMirror SelfIdentifier:" + f() + "/" + z);
        if (this.b != null) {
            this.b.a(z, f());
        }
    }

    public AVContext h() {
        if (this.a == null) {
            return null;
        }
        return this.a.d();
    }

    public void a(AVRoomMulti aVRoomMulti) {
        e = aVRoomMulti;
    }

    public AVRoomMulti i() {
        return e;
    }

    public void a(Context context, View view) {
        this.b = new AVUIControl(context, view);
    }

    public void a(a aVar) {
        if (this.b != null) {
            this.b.a(aVar);
        }
    }

    public void j() {
        if (this.b != null) {
            this.b.i();
        }
    }

    public void k() {
        if (this.b != null) {
            this.b.a();
        }
    }

    public void l() {
        if (this.b != null) {
            this.b.b();
        }
    }

    public void m() {
        if (this.b != null) {
            this.b.c();
            this.b = null;
        }
    }

    public void a(boolean z, String str) {
        if (this.b != null) {
            this.b.a(z, false, str);
        }
    }

    public void a(boolean z, String str, int i) {
        SxbLog.b("QavsdkControl", "setRemoteHasVideo : " + str);
        if (this.b != null) {
            this.b.a(z, str, i);
        }
    }

    public void a(String str) {
        if (this.b != null) {
            this.b.a(str);
        }
    }
}
