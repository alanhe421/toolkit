package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.export.external.DexLoader;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.smtt.utils.TbsLog;

class aq {
    private static aq a;
    private ar b;
    private boolean c;
    private boolean d;

    private aq() {
    }

    public static synchronized aq a() {
        aq aqVar;
        synchronized (aq.class) {
            if (a == null) {
                a = new aq();
            }
            aqVar = a;
        }
        return aqVar;
    }

    public ar a(boolean z) {
        return z ? this.b : c();
    }

    public synchronized void a(Context context, y yVar) {
        String str;
        Object obj;
        f a = f.a(true);
        a.a(context, false, false, yVar);
        if (yVar != null) {
            yVar.a("init_x5_core", (byte) 1);
        }
        if (a.b()) {
            if (!this.d) {
                if (yVar != null) {
                    yVar.a = true;
                }
                this.b = new ar(a.a().a());
                try {
                    this.c = this.b.a();
                    obj = null;
                } catch (NoSuchMethodException e) {
                    this.c = true;
                    obj = null;
                } catch (Throwable th) {
                    obj = th;
                    this.c = false;
                }
            }
            obj = null;
        } else {
            this.c = false;
            obj = null;
        }
        if (!this.c) {
            TbsLog.e("X5CoreEngine", "mCanUseX5 is false --> report");
            if (a.b() && obj == null) {
                String str2;
                Throwable th2;
                try {
                    DexLoader a2 = a.a().a();
                    Object invokeStaticMethod = a2 != null ? a2.invokeStaticMethod("com.tencent.tbs.tbsshell.TBSShell", "getLoadFailureDetails", new Class[0], new Object[0]) : null;
                    if (invokeStaticMethod instanceof Throwable) {
                        th2 = (Throwable) invokeStaticMethod;
                        str = "#" + th2.getMessage() + "; cause: " + th2.getCause() + "; th: " + th2;
                    } else {
                        str = null;
                    }
                    try {
                        str2 = invokeStaticMethod instanceof String ? (String) invokeStaticMethod : str;
                    } catch (Throwable th3) {
                        Throwable th4 = th3;
                        str2 = str;
                        th2 = th4;
                        th2.printStackTrace();
                        TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CAN_NOT_LOAD_X5, new Throwable("X5CoreEngine::init, mCanUseX5=false, available true, details: " + str2));
                        this.d = true;
                        if (yVar != null) {
                            yVar.a("init_x5_core", (byte) 2);
                        }
                    }
                } catch (Throwable th5) {
                    th2 = th5;
                    str2 = null;
                    th2.printStackTrace();
                    TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CAN_NOT_LOAD_X5, new Throwable("X5CoreEngine::init, mCanUseX5=false, available true, details: " + str2));
                    this.d = true;
                    if (yVar != null) {
                        yVar.a("init_x5_core", (byte) 2);
                    }
                }
                TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CAN_NOT_LOAD_X5, new Throwable("X5CoreEngine::init, mCanUseX5=false, available true, details: " + str2));
            } else {
                TbsCoreLoadStat.getInstance().a(context, ErrorCode.INFO_CAN_NOT_LOAD_X5, new Throwable("X5CoreEngine::init, mCanUseX5=false, available: " + a.b() + ", reason: " + obj));
            }
        }
        this.d = true;
        if (yVar != null) {
            yVar.a("init_x5_core", (byte) 2);
        }
    }

    public boolean b() {
        return QbSdk.a ? false : this.c;
    }

    public ar c() {
        return QbSdk.a ? null : this.b;
    }
}
