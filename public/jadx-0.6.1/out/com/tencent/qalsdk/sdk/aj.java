package com.tencent.qalsdk.sdk;

import android.app.ActivityManager;
import android.app.ActivityManager.MemoryInfo;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;
import android.os.RemoteException;
import android.text.format.Formatter;
import com.pay.http.APPluginErrorCode;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.qalsdk.QALSDKManager;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.IBaseService;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.k;
import com.tencent.qalsdk.util.QLog;
import com.tencent.upload.log.trace.TracerConfig;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: RemoteServiceProxy */
public class aj {
    private static final String a = "MSF.D.RemoteServiceProxy";
    private static int b = 0;
    private static long c = -1;
    protected static ConcurrentLinkedQueue<ToServiceMsg> f = new ConcurrentLinkedQueue();
    protected static ConcurrentHashMap<Integer, ToServiceMsg> g = new ConcurrentHashMap();
    static final String m = "appTimeoutReq";
    private static boolean p = false;
    protected volatile IBaseService d;
    protected Object e = new Object();
    protected volatile Handler h;
    protected volatile long i = -1;
    protected volatile long j = -1;
    protected volatile int k = -1;
    AtomicInteger l = new AtomicInteger();
    String n;
    protected ServiceConnection o = new ak(this);

    /* compiled from: RemoteServiceProxy */
    protected class a implements Runnable {
        final /* synthetic */ aj a;
        private ToServiceMsg b;

        public a(aj ajVar, ToServiceMsg toServiceMsg) {
            this.a = ajVar;
            this.b = toServiceMsg;
        }

        public void run() {
            if ("LongConn.OffPicUp".equalsIgnoreCase(this.b.getServiceCmd()) || "ImgStore.GroupPicUp".equalsIgnoreCase(this.b.getServiceCmd())) {
                QLog.d(aj.a, 1, "enter MonitorTaskWrapper.run(), appseq is " + this.b.getAppSeq());
            }
            ToServiceMsg toServiceMsg = (ToServiceMsg) aj.g.get(Integer.valueOf(this.b.getAppSeq()));
            if (toServiceMsg != null && toServiceMsg.getAttribute(aj.m, Integer.valueOf(-1)) == this.b.getAttribute(aj.m, Integer.valueOf(-2)) && ((ToServiceMsg) aj.g.remove(Integer.valueOf(this.b.getAppSeq()))) != null) {
                QLog.d(aj.a, 1, "found timeout req, appseq is " + this.b.getAppSeq());
                this.a.a(this.b, this.a.a(this.b, this.b.getServiceName() + " timeout"));
            }
        }
    }

    public aj(String str) {
        this.n = str;
    }

    protected boolean h() {
        return this.d != null;
    }

    protected void d() {
        Thread alVar = new al(this);
        alVar.setName("handleWaitSendProxyMsgThread");
        alVar.start();
    }

    protected int a(ToServiceMsg toServiceMsg) throws RemoteException {
        return this.d.sendToServiceMsg(toServiceMsg);
    }

    protected FromServiceMsg a(ToServiceMsg toServiceMsg, String str) {
        FromServiceMsg a = k.a(toServiceMsg);
        a.setBusinessFail(com.tencent.qalsdk.base.a.c, str);
        return a;
    }

    protected void a(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        c(toServiceMsg, fromServiceMsg);
    }

    protected void c(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        try {
            if (toServiceMsg.getActionListener() != null) {
                toServiceMsg.getActionListener().onRecvFromMsg(fromServiceMsg);
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public int c(ToServiceMsg toServiceMsg) {
        int i = -1;
        Object obj = 1;
        if (toServiceMsg != null) {
            if (toServiceMsg.getAppSeq() < 0) {
                toServiceMsg.setAppSeq(MsfSdkUtils.getNextAppSeq());
            }
            try {
                synchronized (this.e) {
                    if (this.h == null) {
                        HandlerThread handlerThread = new HandlerThread("Timeout-Checker", 5);
                        handlerThread.start();
                        this.h = new Handler(handlerThread.getLooper());
                    }
                    if (!h()) {
                        obj = null;
                    }
                }
                if (toServiceMsg.getTimeout() == -1) {
                    toServiceMsg.setTimeout(com.tencent.qalsdk.base.a.ap);
                }
                if (toServiceMsg.isNeedCallback()) {
                    toServiceMsg.addAttribute(m, Integer.valueOf(this.l.incrementAndGet()));
                    g.put(Integer.valueOf(toServiceMsg.getAppSeq()), toServiceMsg);
                    Runnable aVar = new a(this, toServiceMsg);
                    if ("LongConn.OffPicUp".equalsIgnoreCase(toServiceMsg.getServiceCmd()) || "ImgStore.GroupPicUp".equalsIgnoreCase(toServiceMsg.getServiceCmd())) {
                        this.h.postDelayed(aVar, toServiceMsg.getTimeout() + 20000);
                        QLog.d(a, 1, "PicUpMsg timer start, appSeq: " + toServiceMsg.getAppSeq() + ", delayMillis: " + String.valueOf(toServiceMsg.getTimeout() + 20000));
                    } else {
                        this.h.postDelayed(aVar, toServiceMsg.getTimeout() + 2000);
                    }
                }
                Editor edit;
                if (obj != null) {
                    if (b > 0) {
                        long currentTimeMillis = System.currentTimeMillis();
                        if (-1 != c && currentTimeMillis < c + 120000) {
                            edit = QALSDKManager.getInstance().getContext().getSharedPreferences("pull_msf_succ" + k(), 0).edit();
                            edit.putString("uin", toServiceMsg.getUin());
                            a(edit);
                            edit.commit();
                            QLog.d(a, 1, "succ to pull msf service.");
                        }
                        l();
                    }
                    i = a(toServiceMsg);
                } else {
                    d(toServiceMsg);
                    synchronized (this.e) {
                        i();
                    }
                    if (b > 10 && System.currentTimeMillis() > c + BuglyBroadcastRecevier.UPLOADLIMITED && !p) {
                        edit = QALSDKManager.getInstance().getContext().getSharedPreferences("pull_msf" + k(), 0).edit();
                        edit.putString("uin", toServiceMsg.getUin());
                        a(edit);
                        edit.commit();
                        QLog.d(a, 1, "cannot pull msf service.");
                        p = true;
                    }
                }
            } catch (DeadObjectException e) {
                d(toServiceMsg);
            } catch (Exception e2) {
                if (this.d == null) {
                    d(toServiceMsg);
                } else {
                    e2.printStackTrace();
                }
            }
        }
        return i;
    }

    public void i() {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.i == -1 || currentTimeMillis - this.i > TracerConfig.LOG_FLUSH_DURATION) {
            this.i = currentTimeMillis;
            a();
            b();
            return;
        }
        QLog.d(a, 1, "wait start " + this.n + " service result, skiped...");
        try {
            int i = VERSION.SDK_INT;
            long j;
            if (i >= 21) {
                QLog.d(a, 1, "sdk version:" + i);
                j = currentTimeMillis - this.j;
                if (this.j == -1 || j > TracerConfig.LOG_FLUSH_DURATION) {
                    if (b == 0) {
                        c = currentTimeMillis;
                    }
                    b++;
                    this.j = currentTimeMillis;
                    if (b % 3 == 2) {
                        c();
                        g();
                    }
                    QALSDKManager.getInstance().getContext().sendBroadcast(new Intent("com.tencent.mobileqq.msf.startmsf"));
                    this.k = 1;
                    QLog.d(a, 1, "start MsfService through Broadcast");
                }
                if (this.j != -1 && j > ((long) (this.k * APPluginErrorCode.ERROR_APP_SYSTEM))) {
                    b();
                    this.k++;
                    QLog.d(a, 1, "delay binding MSF Service");
                    return;
                }
                return;
            }
            j = currentTimeMillis - this.j;
            if (this.j == -1 || j > TracerConfig.LOG_FLUSH_DURATION) {
                if (b == 0) {
                    c = currentTimeMillis;
                }
                b++;
                this.j = currentTimeMillis;
                if (b % 3 == 2) {
                    c();
                    g();
                }
            }
        } catch (Exception e) {
            QLog.d(a, 1, "start MsfService exception " + e.toString());
        }
    }

    void a() {
        try {
            ComponentName componentName = new ComponentName(QALSDKManager.getInstance().getContext().getPackageName(), this.n);
            Intent intent = new Intent();
            intent.setComponent(componentName);
            QALSDKManager.getInstance().getContext().startService(intent);
            if (QLog.isColorLevel()) {
                QLog.d(a, 2, " start service finish");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean b() {
        boolean bindService;
        Exception e;
        try {
            ComponentName componentName = new ComponentName(QALSDKManager.getInstance().getContext().getPackageName(), this.n);
            Intent intent = new Intent();
            intent.setComponent(componentName);
            bindService = QALSDKManager.getInstance().getContext().bindService(intent, this.o, 1);
            try {
                if (QLog.isColorLevel()) {
                    QLog.d(a, 2, " bind " + this.n + " service finished " + bindService);
                }
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return bindService;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            bindService = false;
            e = exception;
            e.printStackTrace();
            return bindService;
        }
        return bindService;
    }

    public void g() {
        try {
            QALSDKManager.getInstance().getContext().unbindService(this.o);
            this.d = null;
            QLog.i(a, " unbindService service finished");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean c() {
        try {
            ComponentName componentName = new ComponentName(QALSDKManager.getInstance().getContext(), this.n);
            Intent intent = new Intent();
            intent.setComponent(componentName);
            boolean stopService = QALSDKManager.getInstance().getContext().stopService(intent);
            if (!QLog.isColorLevel()) {
                return stopService;
            }
            QLog.d(a, 2, " stopService " + this.n + " service finished " + stopService);
            return stopService;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    protected void d(ToServiceMsg toServiceMsg) {
        f.add(toServiceMsg);
    }

    private String e() {
        try {
            ActivityManager activityManager = (ActivityManager) QALSDKManager.getInstance().getContext().getSystemService(Constants.FLAG_ACTIVITY_NAME);
            MemoryInfo memoryInfo = new MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            QLog.d(a, 1, "Property get avail memory:" + memoryInfo.availMem);
            return Formatter.formatFileSize(QALSDKManager.getInstance().getContext(), memoryInfo.availMem);
        } catch (Exception e) {
            QLog.d(a, 1, "failed to get avail memory");
            return null;
        }
    }

    private String f() {
        try {
            ActivityManager activityManager = (ActivityManager) QALSDKManager.getInstance().getContext().getSystemService(Constants.FLAG_ACTIVITY_NAME);
            MemoryInfo memoryInfo = new MemoryInfo();
            activityManager.getMemoryInfo(memoryInfo);
            QLog.d(a, 1, "Property get low memory feature:" + memoryInfo.lowMemory);
            return String.valueOf(memoryInfo.lowMemory);
        } catch (Exception e) {
            QLog.d(a, 1, "failed to get low memory feature");
            return null;
        }
    }

    private String j() {
        boolean z = false;
        try {
            RunningServiceInfo runningServiceInfo = null;
            int i = 0;
            for (RunningServiceInfo runningServiceInfo2 : ((ActivityManager) QALSDKManager.getInstance().getContext().getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningServices(256)) {
                RunningServiceInfo runningServiceInfo22;
                boolean z2;
                int i2 = i + 1;
                if (runningServiceInfo22.process.trim().equals("com.tencent.mobileqq:MSF")) {
                    z2 = true;
                } else {
                    runningServiceInfo22 = runningServiceInfo;
                    z2 = z;
                }
                z = z2;
                runningServiceInfo = runningServiceInfo22;
                i = i2;
            }
            QLog.d(a, 1, "isMsfAlive:" + z + ", cur service process count:" + i);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("countService_").append(i).append(":");
            if (!z || runningServiceInfo == null) {
                stringBuilder.append("alive_").append(z);
            } else {
                stringBuilder.append("alive_").append(z).append(":");
                stringBuilder.append("activeSince_").append(runningServiceInfo.activeSince).append(":");
                stringBuilder.append("clientCount_").append(runningServiceInfo.clientCount).append(":");
                stringBuilder.append("clientLabel_").append(runningServiceInfo.clientLabel).append(":");
                stringBuilder.append("clientPkg_").append(runningServiceInfo.clientPackage).append(":");
                stringBuilder.append("crashCount_").append(runningServiceInfo.crashCount).append(":");
                stringBuilder.append("flags_").append(runningServiceInfo.flags).append(":");
                stringBuilder.append("foreground_").append(runningServiceInfo.foreground).append(":");
                stringBuilder.append("lastActivityTime_").append(runningServiceInfo.lastActivityTime).append(":");
                stringBuilder.append("pid_").append(runningServiceInfo.pid).append(":");
                stringBuilder.append("process_").append(runningServiceInfo.process).append(":");
                stringBuilder.append("restarting_").append(runningServiceInfo.restarting).append(":");
                stringBuilder.append("started_").append(runningServiceInfo.started).append(":");
                stringBuilder.append("uid_").append(runningServiceInfo.uid);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            QLog.d(a, 1, "failed to getServiceState");
            return null;
        }
    }

    private String k() {
        try {
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) QALSDKManager.getInstance().getContext().getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    return runningAppProcessInfo.processName;
                }
            }
        } catch (Exception e) {
            QLog.d(a, 1, "failed to get current process name");
        }
        return null;
    }

    private void a(Editor editor) {
        editor.putString("availMem", e());
        editor.putString("lowMem", f());
        editor.putString("time", new Date().toString());
        editor.putString("process", k());
        editor.putString("state", j());
    }

    private void l() {
        b = 0;
        this.j = -1;
        c = -1;
        p = false;
    }
}
