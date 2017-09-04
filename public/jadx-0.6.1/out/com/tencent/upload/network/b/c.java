package com.tencent.upload.network.b;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import com.tencent.upload.Const.FileType;
import com.tencent.upload.Const.RetCode;
import com.tencent.upload.Const.ServerEnv;
import com.tencent.upload.common.Global;
import com.tencent.upload.common.e;
import com.tencent.upload.common.j;
import com.tencent.upload.network.a.f;
import com.tencent.upload.network.a.k;
import java.util.ArrayList;
import java.util.List;

public final class c implements b {
    public int a = RetCode.SUCCEED.getCode();
    public String b = RetCode.SUCCEED.getDesc();
    private boolean c = false;
    private Handler d;
    private FileType e;
    private a f;
    private f g;
    private volatile a h = a.UNINITED;
    private List<a> i = new ArrayList();

    public enum a {
        UNINITED(0, "未初始化"),
        DETECTING(1, "探测中"),
        UNAVAILABLE(2, "不可用"),
        AVAILABLE(3, "可用");
        
        private int e;
        private String f;

        static {
            UNINITED = new a("UNINITED", 0, 0, "未初始化");
            DETECTING = new a("DETECTING", 1, 1, "探测中");
            UNAVAILABLE = new a("UNAVAILABLE", 2, 2, "不可用");
            AVAILABLE = new a("AVAILABLE", 3, 3, "可用");
            a[] aVarArr = new a[]{UNINITED, DETECTING, UNAVAILABLE, AVAILABLE};
        }

        private a(int i, String str) {
            this.e = i;
            this.f = str;
        }

        public final String toString() {
            return "[" + this.e + "," + this.f + "]";
        }
    }

    public c(FileType fileType, ServerEnv serverEnv) {
        this.e = fileType;
        this.g = j.a(fileType, serverEnv);
        this.g.c();
        HandlerThread handlerThread = new HandlerThread("thread_session_creator_" + this.e);
        handlerThread.start();
        this.d = new Handler(handlerThread.getLooper());
    }

    private static String a(k[] kVarArr) {
        if (kVarArr == null || kVarArr.length <= 0) {
            return "N/A";
        }
        int length = kVarArr.length;
        String str = "";
        int i = 0;
        while (i < length) {
            k kVar = kVarArr[i];
            i++;
            str = str + " -- " + (kVar != null ? kVar.toString() : "(NULL)");
        }
        return str;
    }

    private void a(int i, String str) {
        this.a = i;
        this.b = str;
        if (this.a != RetCode.SUCCEED.getCode()) {
            a(a.UNAVAILABLE);
        } else {
            a(a.AVAILABLE);
        }
    }

    private void a(a aVar) {
        if (this.h != aVar) {
            com.tencent.upload.common.a.a.c(f(), "state change: " + this.h.toString() + " -> " + aVar.toString());
            this.h = aVar;
        }
    }

    private boolean a(boolean z) {
        if (z) {
            this.g.c();
        }
        k[] d = this.g.d();
        com.tencent.upload.common.a.a.c(f(), "start detect routes: " + a(d) + " reset:" + z);
        if (d == null || d.length == 0) {
            if (z) {
                a(RetCode.NO_ROUTE.getCode(), RetCode.NO_ROUTE.getDesc());
            } else if (this.i.size() == 0) {
                if (this.a != RetCode.SUCCEED.getCode()) {
                    a(this.a, this.b);
                } else {
                    a(RetCode.SESSION_ALL_ROUTE_FAILED.getCode(), RetCode.SESSION_ALL_ROUTE_FAILED.getDesc());
                }
            }
            return false;
        }
        for (k kVar : d) {
            a hVar = new h(this.e, true, this.d.getLooper(), this);
            if (hVar.a(kVar)) {
                this.i.add(hVar);
            }
        }
        if (this.i.size() != 0) {
            return true;
        }
        a(false);
        return true;
    }

    private String f() {
        return "SessionCreator_" + this.e;
    }

    public final void a(k kVar) {
        if (this.g != null) {
            this.g.a(kVar);
        }
    }

    public final void a(a aVar) {
        if (aVar != null) {
            com.tencent.upload.common.a.a.c(f(), "open session succeed. sid=" + aVar.hashCode() + " route:" + (aVar.c() != null ? aVar.c().toString() : "N/A") + " redictRoute:" + (aVar.d() != null ? aVar.d().toString() : "N/A"));
            this.d.post(new d(this, aVar));
        }
    }

    public final void a(a aVar, int i, String str) {
        if (aVar != null) {
            boolean b = e.b(Global.context);
            com.tencent.upload.common.a.a.d(f(), " fail to open sesison. sid=" + aVar.hashCode() + " ret=" + i + " msg=" + str + " network=" + b);
            this.d.post(new e(this, aVar, i, str, b));
        }
    }

    public final boolean a() {
        return this.c;
    }

    public final Looper b() {
        return this.d.getLooper();
    }

    public final void b(a aVar) {
        com.tencent.upload.common.a.a.d(f(), " sesison close. sid=" + aVar.hashCode() + " network=" + e.b(Global.context));
    }

    public final void b(a aVar, int i, String str) {
        com.tencent.upload.common.a.a.d(f(), " sesison error. sid=" + aVar.hashCode() + " ret=" + i + " msg=" + str + " network=" + e.b(Global.context));
    }

    public final a c() {
        return this.f;
    }

    public final boolean d() {
        this.c = true;
        boolean b = e.b(Global.context);
        com.tencent.upload.common.a.a.c(f(), "start create session......" + hashCode() + " network=" + b);
        a(a.DETECTING);
        if (b) {
            this.a = RetCode.SUCCEED.getCode();
            this.b = RetCode.SUCCEED.getDesc();
            this.f = null;
            this.i.clear();
            return a(true);
        }
        a(RetCode.NETWORK_NOT_AVAILABLE.getCode(), RetCode.NETWORK_NOT_AVAILABLE.getDesc());
        return false;
    }

    public final a e() {
        return this.h;
    }
}
