package qalsdk;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.common.download.task.f;
import com.qq.taf.jce.d;
import com.tencent.qalsdk.base.CloseConnReason;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.b;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.core.l;
import com.tencent.qalsdk.core.m;
import com.tencent.qalsdk.sdk.MsfCommand;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.sdk.ah;
import com.tencent.qalsdk.sdk.ai;
import com.tencent.qalsdk.sdk.c;
import com.tencent.qalsdk.sdk.o;
import com.tencent.qalsdk.sdk.v;
import com.tencent.qalsdk.sdk.x;
import com.tencent.qalsdk.service.g;
import com.tencent.qalsdk.util.QLog;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: PushManager */
public class aj extends BroadcastReceiver {
    public static final String a = "app_push_info_";
    static final String e = "MSF.C.PushManager";
    static SimpleDateFormat f = new SimpleDateFormat("dd HH:mm:ss");
    public static boolean m = false;
    public static String n = "0";
    static final String o = "im_open_status.stat_queryhb";
    private static final int[] s = new int[]{1, 1, 1, 5, 5, 10, 10, 30, 30, 60, 60, f.SUCCESS, f.SUCCESS};
    private static int t = 0;
    private static AtomicInteger y = new AtomicInteger();
    private long A = 0;
    j b;
    String c = "";
    String d = "";
    public ConcurrentHashMap<String, ah> g = new ConcurrentHashMap();
    public ConcurrentHashMap<String, Boolean> h = new ConcurrentHashMap();
    public ai i;
    AlarmManager j;
    a k = new a(this);
    boolean l = false;
    volatile Object p = new Object();
    final long q = 270000;
    long r = 0;
    private final int u = 1;
    private ConcurrentHashMap<String, PendingIntent> v = new ConcurrentHashMap();
    private Context w = null;
    private String x = null;
    private Handler z = new ak(this);

    /* compiled from: PushManager */
    class a extends Thread {
        final /* synthetic */ aj a;

        a(aj ajVar) {
            this.a = ajVar;
        }

        public void run() {
            while (true) {
                synchronized (this.a.p) {
                    try {
                        this.a.p.wait();
                        this.a.c(this.a.x);
                    } catch (Throwable e) {
                        if (QLog.isColorLevel()) {
                            QLog.w(aj.e, 2, e.toString(), e);
                        }
                    }
                }
            }
        }
    }

    public aj(j jVar) {
        this.b = jVar;
        this.i = new ai(this);
        this.k.setName("MsfCorePushManager");
    }

    public void a(Context context, boolean z) {
        this.w = context;
        this.c = Build.MODEL + DLConstants.DEPENDENCY_PACKAGE_DIV + VERSION.RELEASE;
        this.d = MsfSdkUtils.getProcessName(context) + "_" + getClass().hashCode();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.d);
        context.registerReceiver(this, intentFilter);
        this.j = (AlarmManager) context.getSystemService("alarm");
        a(z);
    }

    public synchronized void a() {
        if (!this.l) {
            this.k.start();
            this.l = true;
        }
    }

    public boolean b() {
        for (String str : this.g.keySet()) {
            ah ahVar = (ah) this.g.get(str);
            if (ahVar != null && ahVar.k != null && ahVar.c != 0) {
                return true;
            }
        }
        return false;
    }

    public int a(String str) {
        for (String str2 : this.g.keySet()) {
            ah ahVar = (ah) this.g.get(str2);
            if (ahVar != null && ahVar.k != null && ahVar.k.c().equals(str)) {
                if (ahVar.c == 0) {
                    return -2;
                }
                return ahVar.k.c;
            }
        }
        return -1;
    }

    public void a(ToServiceMsg toServiceMsg) {
        String str = g.b(toServiceMsg) + toServiceMsg.getUin();
        if (this.g.containsKey(str)) {
            this.i.a((ah) this.g.get(str), toServiceMsg, false, am.setAppQuit);
        }
    }

    public void a(ToServiceMsg toServiceMsg, am amVar) {
        if (this.z.hasMessages(1)) {
            if (QLog.isColorLevel()) {
                QLog.d(e, 2, "remove message handler mLoadPushInfoHandler");
            }
            this.z.removeMessages(1);
        }
        String b = g.b(toServiceMsg);
        String str = b + toServiceMsg.getUin();
        if (!this.g.containsKey(str)) {
            this.g.putIfAbsent(str, new ah(b));
        }
        QLog.d(e, "recv regPush:" + b + ":" + str);
        ah b2 = ai.b(toServiceMsg);
        if (QLog.isColorLevel()) {
            QLog.d(e, 2, "recv processName:" + str + " recv regPush ");
        }
        ah ahVar = (ah) this.g.get(str);
        if (ahVar != null && ahVar.k != null && ahVar.c != 0 && ahVar.k.a.equals(b2.a) && ahVar.k.c == b2.c && ahVar.k.d == b2.d && ahVar.k.e == b2.e && ahVar.k.f == b2.f) {
            QLog.i(e, 2, "handlerPush also register Push iStatus:" + b2.c);
            FromServiceMsg a = g.a(toServiceMsg);
            a.setMsgSuccess();
            this.b.a(toServiceMsg, a);
            return;
        }
        if (QLog.isColorLevel()) {
            QLog.d(e, 2, "handlerPush not found the same register Push  iStatus:" + b2.c);
        }
        ahVar.k = b2;
        ahVar.a = toServiceMsg.getAppId();
        Iterator it = b2.b.iterator();
        while (it.hasNext()) {
            ahVar.c = ((Long) it.next()).longValue() | ahVar.c;
        }
        e(str);
        this.i.a(ahVar, toServiceMsg, false, amVar);
    }

    public synchronized void b(String str) {
        String packageName = this.w.getPackageName();
        String str2 = packageName + str;
        if (QLog.isColorLevel()) {
            QLog.d(e, 2, "recv processName:" + packageName + " recv KickedMsg ");
        }
        ah ahVar = (ah) this.g.get(str2);
        if (!(ahVar == null || ahVar.k == null || !ahVar.k.a.equals(str))) {
            ahVar.c = 0;
            ahVar.k = null;
            e(str2);
        }
    }

    public synchronized void a(ah ahVar, ToServiceMsg toServiceMsg) {
        String b = g.b(toServiceMsg);
        String str = b + toServiceMsg.getUin();
        QLog.d(e, "recv unRegisterPush:" + b + ":" + str);
        if (!this.g.containsKey(str)) {
            this.g.putIfAbsent(str, new ah(b));
        }
        ((ah) this.g.get(str)).k = ahVar;
        ((ah) this.g.get(str)).a = toServiceMsg.getAppId();
        ((ah) this.g.get(str)).c = 0;
        this.i.a((ah) this.g.get(str), toServiceMsg, true, am.appRegister);
        if (((ah) this.g.get(str)).c == 0) {
            ((ah) this.g.get(str)).k = null;
        }
        f(str);
    }

    public synchronized void a(c cVar, ToServiceMsg toServiceMsg) {
        String b = g.b(toServiceMsg);
        if (QLog.isColorLevel()) {
            QLog.d(e, 2, "recv processName:" + b + " appCmdCallbacker " + cVar);
        }
        String str = b + toServiceMsg.getUin();
        if (!this.g.containsKey(str)) {
            this.g.putIfAbsent(str, new ah(b));
        }
        ((ah) this.g.get(str)).m = cVar;
        FromServiceMsg a = g.a(toServiceMsg);
        a.setMsgSuccess();
        this.b.a(toServiceMsg, a);
        e(str);
    }

    public synchronized void b(c cVar, ToServiceMsg toServiceMsg) {
        String b = g.b(toServiceMsg);
        if (QLog.isColorLevel()) {
            QLog.d(e, 2, "recv processName:" + b + " recv unRegisterCmdCall ");
        }
        String str = b + toServiceMsg.getUin();
        if (!this.g.containsKey(str)) {
            this.g.putIfAbsent(str, new ah(b));
        }
        ((ah) this.g.get(str)).m = cVar;
        FromServiceMsg a = g.a(toServiceMsg);
        a.setMsgSuccess();
        this.b.a(toServiceMsg, a);
        e(str);
    }

    private void e(String str) {
        ah ahVar = (ah) this.g.get(str);
        ahVar.n = String.valueOf(b.b(this.w));
        d dVar = new d();
        ahVar.writeTo(dVar);
        try {
            l.a().setConfig(a + str, com.qq.taf.jce.a.a(dVar.b()));
        } catch (UnsatisfiedLinkError e) {
            QLog.e(e, "saveAppPushInfo exception,setConfig not find:" + e.getMessage());
        }
    }

    private void f(String str) {
        if (str != null && str.length() > 0) {
            l.a().removeConfig(a + str);
            QLog.i(e, "remove AppPushInfo:" + str);
        }
    }

    private boolean a(String str, String str2) {
        return str.equals(str2) || str.equals(str2 + ":openmsf") || str.equals(str2 + ":QQ");
    }

    public void a(boolean z) {
        String[] configList;
        try {
            configList = l.a().getConfigList(a);
        } catch (Exception e) {
            QLog.e(e, "removeAccount exception:" + e.getMessage());
            configList = null;
        }
        if (true == z && r2 == null && t < s.length) {
            if (QLog.isColorLevel()) {
                QLog.d(e, 2, "loop to loadAppPushInfo with time " + s[t] + " seconds");
            }
            Message obtainMessage = this.z.obtainMessage();
            obtainMessage.what = 1;
            obtainMessage.obj = Boolean.valueOf(z);
            this.z.sendMessageDelayed(obtainMessage, (long) (s[t] * 1000));
            t++;
        }
        int b = b.b(this.w);
        for (String a : r2) {
            byte[] a2 = com.qq.taf.jce.a.a(a);
            ah ahVar = new ah();
            ahVar.readFrom(new com.qq.taf.jce.c(a2));
            ahVar.o = (byte) 0;
            QLog.d(e, "load AppPushInfo:" + ahVar.b + ":" + ahVar.k + ":" + b);
            if (ahVar.k != null && ahVar.c > 0) {
                boolean z2 = false;
                if (b == -1) {
                    z2 = true;
                    if (QLog.isColorLevel()) {
                        QLog.d(e, 2, "nVersionCode:" + b + " sendMsgPushRegister now");
                    }
                } else {
                    String valueOf = String.valueOf(b);
                    if (ahVar.n != null) {
                        if (ahVar.n.equals(valueOf)) {
                            z2 = true;
                            if (QLog.isColorLevel()) {
                                QLog.d(e, 2, "info.installVersion :" + ahVar.n + " strVersionCode:" + valueOf + " sendMsgPushRegister now");
                            }
                        } else if (z) {
                            z2 = true;
                            if (QLog.isColorLevel()) {
                                QLog.d(e, 2, "info.installVersion :" + ahVar.n + " bBoot:" + z + " strVersionCode:" + valueOf + " sendMsgPushRegister now");
                            }
                        } else if (QLog.isColorLevel()) {
                            QLog.d(e, 2, "info.installVersion :" + ahVar.n + " strVersionCode:" + valueOf + " no need sendMsgPushRegister");
                        }
                    } else if (z) {
                        z2 = true;
                        if (QLog.isColorLevel()) {
                            QLog.d(e, 2, "info.installVersion :" + ahVar.n + " bBoot:" + z + " sendMsgPushRegister now");
                        }
                    } else if (QLog.isColorLevel()) {
                        QLog.d(e, 2, "info.installVersion :" + ahVar.n + " bBoot:" + z + " no need sendMsgPushRegister");
                    }
                }
                QLog.i(e, "load AppPushInfo:" + ahVar.b + ":" + ahVar.k.a + ":" + z + ":" + z2);
                if (z2) {
                    this.g.put(ahVar.b + ahVar.k.a, ahVar);
                    this.i.a(ahVar, null, false, am.msfBoot);
                } else {
                    ahVar.c = 0;
                }
            }
        }
    }

    public void c() {
        QLog.i(e, "onConnResumed doQueryMsgPush");
        c(null);
    }

    private void f() {
        for (String put : this.h.keySet()) {
            this.h.put(put, Boolean.valueOf(false));
        }
    }

    public void a(CloseConnReason closeConnReason) {
        QLog.i(e, "onConnClosed send open conn,net is ok: " + m.b());
        f();
        this.r = 0;
        if (closeConnReason == CloseConnReason.unRegisterPush) {
            QLog.i(e, "onConnClosed by" + closeConnReason + ",no need auto retry conn");
        } else if (closeConnReason == CloseConnReason.readError && m.e() && ac.f.get()) {
            QLog.i(e, "onConnClosed by " + closeConnReason + " need wifi auth,no need auto retry conn");
        } else {
            Thread alVar = new al(this);
            alVar.setName("onConnClosedPushThread");
            alVar.start();
        }
    }

    private void g() {
        for (String str : this.v.keySet()) {
            PendingIntent pendingIntent = (PendingIntent) this.v.get(str);
            if (pendingIntent != null) {
                this.j.cancel(pendingIntent);
                QLog.e("alarm", "conn reopen,cancel alarm in map:" + str);
            }
            this.v.remove(str);
        }
    }

    public void d() {
        g();
        f();
        QLog.i(e, "onConnOpened doQueryMsgPush");
        c(null);
    }

    public void onReceive(Context context, Intent intent) {
        if (QLog.isColorLevel()) {
            QLog.d(e, 2, "alarm receive: " + intent.getStringExtra("appInfoKey"));
        }
        synchronized (this.p) {
            this.x = intent.getStringExtra("appInfoKey");
            if (this.v.containsKey(this.x)) {
                this.v.remove(this.x);
                QLog.d("alarm", "remove alarm in map:" + this.x);
            }
            this.p.notify();
        }
    }

    public void a(long j, String str) {
        if (this.v.containsKey(str)) {
            QLog.e("alarm", "doRegistertAlarm hello repeat:" + str);
            return;
        }
        if (j < 30000) {
            if (QLog.isColorLevel()) {
                QLog.d(e, 2, "queryPushIntervTime less than 30000,change to 30000.");
                j = 30000;
            } else {
                j = 30000;
            }
        } else if (j > 3600000) {
            if (QLog.isColorLevel()) {
                QLog.d(e, 2, "queryPushIntervTime greater than 600000 ,change to 600000.");
            }
            j = 3600000;
        }
        Intent intent = new Intent(this.d);
        intent.setAction(this.d);
        intent.putExtra("appInfoKey", str);
        PendingIntent broadcast = PendingIntent.getBroadcast(this.w, y.incrementAndGet(), intent, 0);
        this.v.put(str, broadcast);
        QLog.d("alarm", "put alarm in map:" + str);
        this.j = (AlarmManager) this.w.getSystemService("alarm");
        this.j.set(0, System.currentTimeMillis() + j, broadcast);
        if (QLog.isColorLevel()) {
            QLog.d(e, 2, "register " + str + ":" + this.d + " alarm alive send at " + f.format(Long.valueOf(System.currentTimeMillis() + j)));
        }
    }

    public void a(FromServiceMsg fromServiceMsg) {
        if (fromServiceMsg.getServiceCmd().equals(com.tencent.qalsdk.base.a.L)) {
            new o().a(fromServiceMsg);
            return;
        }
        Object obj = null;
        for (String str : this.g.keySet()) {
            Object obj2;
            ah ahVar = (ah) this.g.get(str);
            if (ahVar != null) {
                if (fromServiceMsg.getServiceCmd().equals(o)) {
                    if (d.i) {
                        ahVar.i = this.b.v;
                        ahVar.f = System.currentTimeMillis();
                        a(ahVar, am.serverPush);
                        obj2 = 1;
                    } else if (QLog.isColorLevel()) {
                        QLog.d(e, 2, "recv push im_open_status.stat_queryhb, but do not support useAnyPacketAsPushHB ");
                        obj2 = obj;
                    }
                    obj = obj2;
                } else if (fromServiceMsg.isSuccess()) {
                    if (a(ahVar, fromServiceMsg)) {
                        int i = 1;
                        obj = obj2;
                    }
                } else if (QLog.isColorLevel()) {
                    QLog.d(e, 2, "recv error onRecvPushMsg FromServiceMsg  " + fromServiceMsg);
                }
            }
            obj2 = obj;
            obj = obj2;
        }
        if (obj == null && QLog.isColorLevel()) {
            QLog.w(e, 2, "found not handle push msg " + fromServiceMsg);
        }
    }

    public boolean a(ah ahVar, FromServiceMsg fromServiceMsg) {
        if (ahVar.m == null || !ahVar.m.a.equals(fromServiceMsg.getUin())) {
            return false;
        }
        Iterator it = ahVar.m.b.iterator();
        boolean z = false;
        while (it.hasNext()) {
            boolean z2;
            if (((String) it.next()).equals(fromServiceMsg.getServiceCmd())) {
                fromServiceMsg.addAttribute(v.m, Integer.valueOf(1));
                MsfSdkUtils.addFromMsgProcessName(ahVar.b, fromServiceMsg);
                fromServiceMsg.setMsfCommand(MsfCommand.onRecvPushMsg);
                this.b.a(null, fromServiceMsg);
                QLog.d(e, 2, "recv push " + ahVar.b + " " + fromServiceMsg);
                z2 = true;
            } else {
                z2 = z;
            }
            z = z2;
        }
        if (!fromServiceMsg.getServiceCmd().equals(com.tencent.qalsdk.base.a.cq)) {
            return z;
        }
        ah ahVar2 = new ah();
        ahVar2.d = (byte) 0;
        ahVar2.e = (byte) 0;
        ArrayList arrayList = new ArrayList();
        arrayList.add(Long.valueOf(1));
        ahVar2.b = arrayList;
        ahVar2.f = 0;
        ahVar2.a = fromServiceMsg.getUin();
        ToServiceMsg toServiceMsg = new ToServiceMsg("", ahVar2.a, com.tencent.qalsdk.base.a.M);
        toServiceMsg.setMsfCommand(MsfCommand.unRegisterPush);
        toServiceMsg.setAppId(com.tencent.qalsdk.base.a.bm);
        toServiceMsg.setTimeout(com.tencent.qalsdk.base.a.ap);
        MsfSdkUtils.addToMsgProcessName(ahVar.b, toServiceMsg);
        QLog.i(e, "service recv force_offline,send unregister." + ahVar.b + ":" + ahVar2.a);
        a(ahVar2, toServiceMsg);
        return true;
    }

    public long e() {
        if (this.r == 0) {
            return 270000;
        }
        return this.r;
    }

    private void a(String str, ah ahVar) {
        if (ahVar != null && ahVar.k != null && ahVar.c != 0) {
            try {
                if (((Boolean) this.h.get(ahVar.k.c())).booleanValue() && ahVar.i != null && this.b.v != null && ahVar.i.equals(this.b.v)) {
                    this.i.a(str, ahVar, false);
                } else if (this.b.v == null) {
                    QLog.i(e, "doQueryMsgPush no conn,send open conn");
                    ToServiceMsg a = x.a("");
                    MsfSdkUtils.addToMsgProcessName("", a);
                    this.b.a(a);
                } else {
                    a(ahVar, am.msfByNetChange);
                }
            } catch (Throwable e) {
                if (QLog.isColorLevel()) {
                    QLog.w(e, 2, e.toString(), e);
                }
            }
        }
    }

    void c(String str) {
        if (str == null) {
            for (String str2 : this.g.keySet()) {
                a(str2, (ah) this.g.get(str2));
            }
            return;
        }
        ah ahVar = (ah) this.g.get(str);
        if (ahVar != null) {
            a(str, ahVar);
        }
    }

    public void a(ah ahVar, am amVar) {
        if (ahVar.c > 0) {
            if (QLog.isColorLevel()) {
                QLog.d(e, 2, "send " + ahVar.b + " push register, pushId is " + ahVar.c);
            }
            this.i.a(ahVar, null, false, amVar);
            return;
        }
        QLog.d(e, 2, ahVar.b + " queryPushId is " + ahVar.c + " ,skip register.");
    }

    public void a(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        this.i.a(toServiceMsg, fromServiceMsg);
    }

    public void b(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        this.i.b(toServiceMsg, fromServiceMsg);
    }

    public void a(long j) {
        this.A = j;
    }

    public void a(String str, long j) {
        this.b.b().a(str, j);
    }

    public long d(String str) {
        return this.b.b().d(str);
    }
}
