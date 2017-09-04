package com.tencent.feedback.eup;

import android.content.Context;
import android.os.Process;
import com.tencent.feedback.common.e;
import com.tencent.feedback.proguard.a;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: RQDSRC */
public final class c implements UncaughtExceptionHandler {
    private static c c = null;
    private UncaughtExceptionHandler a = null;
    private Context b = null;

    public static synchronized c a(Context context) {
        c cVar;
        synchronized (c.class) {
            if (c == null && context != null) {
                c = new c(context);
            }
            cVar = c;
        }
        return cVar;
    }

    private c(Context context) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
        }
        this.b = context;
    }

    public final synchronized void a() {
        e.a("rqdp{ eup regist}", new Object[0]);
        c defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != this) {
            this.a = defaultUncaughtExceptionHandler;
            Thread.setDefaultUncaughtExceptionHandler(this);
        }
    }

    public final synchronized void b() {
        e.a("rqdp{ eup unregister}", new Object[0]);
        if (Thread.getDefaultUncaughtExceptionHandler() == this) {
            Thread.setDefaultUncaughtExceptionHandler(this.a);
            this.a = null;
        }
    }

    private synchronized void a(Thread thread, Throwable th) {
        if (this.a != null) {
            e.b("rqdp{ sys crhandle!}", new Object[0]);
            this.a.uncaughtException(thread, th);
        } else {
            e.b("rqdp{ kill!}", new Object[0]);
            Process.killProcess(Process.myPid());
            System.exit(1);
        }
    }

    private static void c() {
        Thread anonymousClass1 = new Thread() {
            public final void run() {
                f.n();
            }
        };
        anonymousClass1.setName("ImmediateEUP");
        anonymousClass1.start();
        try {
            anonymousClass1.join(3000);
        } catch (Throwable e) {
            if (!e.a(e)) {
                e.printStackTrace();
            }
        }
    }

    private int a(List<e> list, int i, boolean z) {
        Context context = this.b;
        if (list == null || i <= 0) {
            return 0;
        }
        List arrayList = new ArrayList();
        Collections.sort(list, new Comparator<e>() {
            public final /* synthetic */ int compare(Object obj, Object obj2) {
                int i = 1;
                e eVar = (e) obj;
                e eVar2 = (e) obj2;
                int i2 = eVar.b() ? 1 : 0;
                if (!eVar2.b()) {
                    i = 0;
                }
                return i2 == i ? (int) (eVar.i() - eVar2.i()) : i2 - i;
            }
        });
        Iterator it = list.iterator();
        while (it.hasNext() && i > arrayList.size()) {
            e eVar = (e) it.next();
            if (eVar.b() && !z) {
                break;
            }
            arrayList.add(eVar);
            it.remove();
        }
        return arrayList.size() > 0 ? b.a(context, arrayList) : 0;
    }

    public final boolean a(e eVar, CrashStrategyBean crashStrategyBean) {
        if (eVar == null && crashStrategyBean == null) {
            e.c("handler exception data params error", new Object[0]);
            return false;
        }
        e eVar2;
        if (crashStrategyBean.isMerged()) {
            Context context = this.b;
            if (eVar == null) {
                eVar2 = null;
            } else {
                String c = a.c((eVar.P() + "\n" + eVar.e() + "\n" + eVar.h()).getBytes());
                if (c == null) {
                    e.c("rqdp{  md5 error!}", new Object[0]);
                    eVar2 = null;
                } else {
                    eVar.g(c);
                    eVar.a(true);
                    eVar.b(1);
                    eVar.a(0);
                    List a = b.a(context, 1, SocialConstants.PARAM_APP_DESC, -1, c, -1, -1, -1, -1, -1, -1, null);
                    if (a == null || a.size() <= 0) {
                        e.b("rqdp{  new one ,no merged!}", new Object[0]);
                        eVar2 = null;
                    } else {
                        eVar2 = (e) a.get(0);
                        if (eVar2.l() == null || !eVar2.l().contains(eVar.i())) {
                            boolean z;
                            eVar2.b(eVar2.m() + 1);
                            if (eVar2.l() == null) {
                                eVar2.f("");
                            }
                            eVar2.f(eVar2.l() + eVar.i() + "\n");
                            e.b("rqdp{  EUPDAO.insertOrUpdateEUP() start}", new Object[0]);
                            if (context == null || eVar2 == null) {
                                e.c("rqdp{  context == null || bean == null,pls check}", new Object[0]);
                                z = false;
                            } else {
                                List arrayList = new ArrayList();
                                arrayList.add(eVar2);
                                z = b.b(context, arrayList);
                            }
                            if (z) {
                                e.a("rqdp{  eupMeg update success} %b , c:%d , at:%s up:%d", Boolean.valueOf(z), Integer.valueOf(eVar2.m()), eVar2.l(), Integer.valueOf(eVar2.j()));
                                if (eVar.p() != null) {
                                    File file = new File(eVar.p());
                                    if (file.exists() && file.isFile()) {
                                        file.delete();
                                    }
                                }
                            }
                        } else {
                            e.b("rqdp{ already merged} %d", Long.valueOf(eVar.i()));
                        }
                    }
                }
            }
            if (eVar2 != null) {
                e.a("merge success return", new Object[0]);
                if (!eVar2.w() && eVar2.m() >= 2) {
                    e.a("rqdp{ may be crash too frequent! do immediate upload in merge!}", new Object[0]);
                    c();
                }
                return true;
            }
        }
        int maxStoredNum = crashStrategyBean.getMaxStoredNum();
        List<e> a2 = b.a(this.b, maxStoredNum + 1, "asc", -1, null, -1, -1, -1, -1, -1, -1, null);
        if (a2 != null && a2.size() > 0) {
            maxStoredNum = (a2.size() - maxStoredNum) + 1;
            if (maxStoredNum > 0 && a(a2, maxStoredNum, eVar.b()) < maxStoredNum) {
                e.c("can't add more eup!", new Object[0]);
                return false;
            }
        }
        if (a2 != null && a2.size() > 1) {
            eVar2 = (e) a2.get(0);
            e eVar3 = eVar2;
            for (e eVar22 : a2) {
                if (eVar3.i() >= eVar22.i() || !eVar22.b()) {
                    eVar22 = eVar3;
                }
                eVar3 = eVar22;
            }
            if (eVar3.b() && eVar.i() - eVar3.i() < BuglyBroadcastRecevier.UPLOADLIMITED) {
                e.c("rqdp{ may be crash too frequent! do immediate upload in not merge!}", new Object[0]);
                c();
            }
        }
        b.a(this.b, eVar, crashStrategyBean);
        if (com.tencent.feedback.common.a.e(this.b)) {
            e.b("save log", new Object[0]);
            eVar.a(a.a(crashStrategyBean.getOnlyLogTag(), crashStrategyBean.getMaxLogRow()));
        } else {
            eVar.a(null);
        }
        e.a("store new eup pn:%s, isMe:%b , isNa:%b , res:%b ", eVar.q(), Boolean.valueOf(eVar.c()), Boolean.valueOf(eVar.b()), Boolean.valueOf(b.a(this.b, eVar)));
        return b.a(this.b, eVar);
    }

    public final boolean a(String str, Throwable th, String str2, byte[] bArr, boolean z) {
        CrashHandleListener crashHandleListener;
        byte[] crashExtraData;
        String str3;
        com.tencent.feedback.common.c a;
        e a2;
        Map b;
        boolean onCrashSaving;
        String E = com.tencent.feedback.common.c.a(this.b).E();
        String str4 = "";
        String str5 = "";
        String message = th != null ? th.getMessage() : "";
        String name = th != null ? th.getClass().getName() : "";
        f l = f.l();
        if (l == null) {
            e.c("rqdp{  instance == null}", new Object[0]);
            crashHandleListener = null;
        } else {
            crashHandleListener = l.r();
        }
        long time = new Date().getTime();
        try {
            str4 = b.a(th, CrashReport.getCrashRuntimeStrategy());
        } catch (Throwable th2) {
            e.d("create stack from throw fail!", new Object[0]);
            if (!e.a(th2)) {
                th2.printStackTrace();
            }
        }
        if (str4 != null && str4.contains("\n")) {
            str5 = str4.substring(0, str4.indexOf("\n"));
        }
        e.b("rqdp{ stack:}%s", str4);
        if (z && crashHandleListener != null) {
            e.b("get crash extra...", new Object[0]);
            if (crashHandleListener != null) {
                try {
                    e.a("your crmsg", new Object[0]);
                    str2 = crashHandleListener.getCrashExtraMessage(false, name, str5, str4, -10000, time);
                } catch (Throwable th22) {
                    e.d("rqdp{ get extra msg error} %s", th22.toString());
                    if (!e.a(th22)) {
                        th22.printStackTrace();
                    }
                }
            }
            if (crashHandleListener != null) {
                try {
                    e.a("your crdata", new Object[0]);
                    crashExtraData = crashHandleListener.getCrashExtraData(false, name, str5, str4, -10000, time);
                    str3 = str2;
                } catch (Throwable th222) {
                    e.d("rqdp{ get extra msg error} %s", th222.toString());
                    if (!e.a(th222)) {
                        th222.printStackTrace();
                    }
                }
                a = com.tencent.feedback.common.c.a(this.b);
                a2 = b.a(this.b, a.g(), a.o(), a.j(), a.y(), E, str, str5, name, message, str4, time, str3, crashExtraData);
                a2.a(z ? (byte) 0 : (byte) 1);
                b = a.b();
                if (b != null) {
                    a2.C().putAll(b);
                    if (str != null && str.trim().length() > 0) {
                        a2.C().remove(str);
                    }
                }
                if (z && crashHandleListener != null) {
                    e.a("your ask2save", new Object[0]);
                    if (message != null && message.trim().length() > 0) {
                        name = name + ":" + message;
                    }
                    onCrashSaving = crashHandleListener.onCrashSaving(false, name, str5, str4, -10000, time, a2.k(), a2.D(), a2.v());
                    if (z) {
                        BuglyBroadcastRecevier.brocastCrash(this.b, a2);
                    }
                    if (!onCrashSaving) {
                        return a(a2, CrashReport.getCrashRuntimeStrategy());
                    }
                    e.c("not to save", new Object[0]);
                    return false;
                }
                onCrashSaving = true;
                if (z) {
                    BuglyBroadcastRecevier.brocastCrash(this.b, a2);
                }
                if (!onCrashSaving) {
                    return a(a2, CrashReport.getCrashRuntimeStrategy());
                }
                e.c("not to save", new Object[0]);
                return false;
            }
        }
        crashExtraData = bArr;
        str3 = str2;
        a = com.tencent.feedback.common.c.a(this.b);
        a2 = b.a(this.b, a.g(), a.o(), a.j(), a.y(), E, str, str5, name, message, str4, time, str3, crashExtraData);
        if (z) {
        }
        a2.a(z ? (byte) 0 : (byte) 1);
        try {
            b = a.b();
            if (b != null) {
                a2.C().putAll(b);
                a2.C().remove(str);
            }
        } catch (Throwable th2222) {
            e.d("get all threads stack fail", new Object[0]);
            if (!e.a(th2222)) {
                th2222.printStackTrace();
            }
        }
        try {
            e.a("your ask2save", new Object[0]);
            name = name + ":" + message;
            onCrashSaving = crashHandleListener.onCrashSaving(false, name, str5, str4, -10000, time, a2.k(), a2.D(), a2.v());
        } catch (Throwable th3) {
            e.d("rqdp{ get extra msg error} %s", th3.toString());
            if (!e.a(th3)) {
                th3.printStackTrace();
            }
        }
        if (z) {
            BuglyBroadcastRecevier.brocastCrash(this.b, a2);
        }
        if (!onCrashSaving) {
            return a(a2, CrashReport.getCrashRuntimeStrategy());
        }
        e.c("not to save", new Object[0]);
        return false;
    }

    public final synchronized void uncaughtException(Thread thread, Throwable th) {
        CrashHandleListener crashHandleListener;
        if (th != null) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
        }
        f l = f.l();
        if (l == null) {
            e.c("rqdp{  instance == null}", new Object[0]);
            crashHandleListener = null;
        } else {
            crashHandleListener = l.r();
        }
        if (crashHandleListener != null) {
            try {
                e.a("your crhandler start", new Object[0]);
                crashHandleListener.onCrashHandleStart(false);
            } catch (Throwable th2) {
                e.d("rqdp{ handle start error} %s", th2.toString());
                if (!e.a(th2)) {
                    th2.printStackTrace();
                }
            }
        }
        boolean a = a(thread == null ? "" : thread.getName(), th, null, null, true);
        e.b("rqdp{ handle eup result} %b", Boolean.valueOf(a));
        if (crashHandleListener != null) {
            try {
                e.a("your crhandler end", new Object[0]);
                a = crashHandleListener.onCrashHandleEnd(false);
            } catch (Throwable th22) {
                e.d("rqdp{ your crash handle end error} %s", th22.toString());
                if (!e.a(th22)) {
                    th22.printStackTrace();
                }
            }
            if (a) {
                a(thread, th);
            }
        }
        a = true;
        if (a) {
            a(thread, th);
        }
    }
}
