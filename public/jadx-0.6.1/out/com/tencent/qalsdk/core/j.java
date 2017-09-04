package com.tencent.qalsdk.core;

import android.content.Context;
import com.qq.taf.jce.JceStruct;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.sdk.MsfCommand;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.sdk.ai;
import com.tencent.qalsdk.sdk.v;
import com.tencent.qalsdk.sdk.w;
import com.tencent.qalsdk.service.QalService;
import com.tencent.qalsdk.service.c;
import com.tencent.qalsdk.util.QLog;
import com.tencent.tesla.soload.SoLoadCore;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import qalsdk.aj;
import qalsdk.am;
import qalsdk.as;
import qalsdk.d;
import qalsdk.f;
import tencent.tls.account.acc_request;

/* compiled from: MsfCore */
public class j {
    private static final String B = "MSF.C.MsfCore";
    private static final String C = "_key_set_log_level";
    private static final char[] E = "0123456789abcdef".toCharArray();
    private static final AtomicInteger F = new AtomicInteger(new Random().nextInt(100000));
    public static AtomicBoolean a = new AtomicBoolean(false);
    static j b = new j();
    public static final int r = 1;
    public static final int s = 0;
    public static String t = null;
    public static final String x = "__key_sso_server_env";
    private q A;
    private int D = 0;
    l c;
    public o d;
    public aj e;
    public d f;
    f g;
    public String h = null;
    String i = null;
    public String j = null;
    LinkedBlockingQueue<w> k = new LinkedBlockingQueue();
    i l;
    public AtomicBoolean m = new AtomicBoolean();
    public SimpleDateFormat n = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.getDefault());
    AtomicBoolean o = new AtomicBoolean();
    public as p;
    public NetConnInfoCenter q;
    public Context u = null;
    public volatile String v;
    AtomicBoolean w = new AtomicBoolean(false);
    private a y;
    private int z = a.bm;

    static {
        try {
            System.loadLibrary("qalcodecwrapper");
            System.loadLibrary("qalmsfboot");
            a.set(true);
            QLog.e(B, "system load so library succ!");
        } catch (UnsatisfiedLinkError e) {
            QLog.e(B, "system load so library error:" + e.getMessage());
            long loadSo = (long) SoLoadCore.loadSo(QalService.context, "qalcodecwrapper");
            if ((loadSo & 2) == 0 && (loadSo & 262144) == 0) {
                QLog.e("MsfCore", "soload lib qalcodecwrapper.so failed ,ret = " + loadSo);
            } else {
                QLog.e("MsfCore", "soload lib qalcodecwrapper.so succ");
                loadSo = (long) SoLoadCore.loadSo(QalService.context, "qalmsfboot");
                if ((loadSo & 2) == 0 && (loadSo & 262144) == 0) {
                    QLog.e("MsfCore", "soload lib qalmsfboot.so failed ,ret = " + loadSo);
                } else {
                    QLog.e("MsfCore", "soload lib qalmsfboot.so succ");
                    a.set(true);
                }
            }
        }
    }

    public static j a() {
        return b;
    }

    public boolean a(Context context, boolean z) {
        QLog.d(B, 1, "MsfCore init begin.");
        this.u = context;
        this.h = context.getFilesDir().getAbsolutePath() + "/tencent/qalsdk";
        this.i = this.h + "/imei";
        d.a(context);
        try {
            File file = new File(this.h);
            if (!file.exists()) {
                file.mkdirs();
            }
        } catch (Exception e) {
            QLog.e(B, 1, "File operation error " + e);
        }
        k.a(context);
        try {
            this.c = new l();
            if (this.c.a(context)) {
                p();
                q();
                this.y = new a();
                this.y.a();
                this.A = new q(this);
                try {
                    this.l = new i(this);
                    this.l.a();
                } catch (Exception e2) {
                    QLog.e(B, 1, "msfAlarmer init failed " + e2);
                }
                try {
                    this.f = new d(this);
                    this.f.a();
                } catch (Exception e22) {
                    QLog.e(B, 1, "configManager init failed " + e22);
                }
                try {
                    this.g = new f(this);
                    this.g.a();
                } catch (Exception e222) {
                    QLog.e(B, 1, "SsoListManager init failed " + e222);
                }
                try {
                    this.q = new NetConnInfoCenter();
                    NetConnInfoCenter.init(this);
                    NetConnInfoCenter.checkConnInfo(context, true);
                } catch (Throwable e3) {
                    QLog.e(B, 1, "MsfCore init netConnInfoCenter error " + e3, e3);
                }
                this.p = new as();
                try {
                    this.d = new o(this);
                    if (this.d.a(context)) {
                        QLog.d(B, 1, "Sender init succ");
                        try {
                            this.e = new aj(this);
                            this.e.a(context, z);
                        } catch (Exception e4) {
                            QLog.e(B, 1, "PushManager init failed " + e4.getStackTrace());
                        }
                        this.o.set(true);
                        QLog.d(B, 1, "MsfCore init finished.");
                        return true;
                    }
                    QLog.e(B, 1, "Sender init fail");
                    return false;
                } catch (Exception e2222) {
                    QLog.e(B, 1, "Sender init failed " + e2222);
                    return false;
                }
            }
            QLog.e(B, 1, "MsfStore init fail,so init:" + a.get());
            return false;
        } catch (Exception e22222) {
            QLog.e(B, 1, "MsfStore init error " + e22222);
            return false;
        }
    }

    public void a(int i) {
        try {
            l.a().setConfig(C, String.valueOf(i));
        } catch (UnsatisfiedLinkError e) {
            QLog.e(B, "save logLevel exception" + e.getMessage());
        }
    }

    private void p() {
        try {
            String config = l.a().getConfig(C);
            if (config != null) {
                int parseInt = Integer.parseInt(config);
                if (parseInt >= 1 && parseInt <= 5) {
                    QLog.setOutputLogLevel(parseInt);
                    QLog.i(B, "set saved log level:" + parseInt);
                }
            }
        } catch (UnsatisfiedLinkError e) {
            QLog.e(B, "MsfCore setLogLevel UnsatisfiedLinkError. so init:" + a.get());
        }
    }

    public a b() {
        return this.y;
    }

    public byte[] c() {
        return d.a();
    }

    public static String a(byte[] bArr) {
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            cArr[i * 2] = E[i2 >>> 4];
            cArr[(i * 2) + 1] = E[i2 & 15];
        }
        return new String(cArr);
    }

    public static byte a(char c) {
        switch (c) {
            case '1':
                return (byte) 1;
            case '2':
                return (byte) 2;
            case '3':
                return (byte) 3;
            case '4':
                return (byte) 4;
            case '5':
                return (byte) 5;
            case '6':
                return (byte) 6;
            case '7':
                return (byte) 7;
            case '8':
                return (byte) 8;
            case '9':
                return (byte) 9;
            case 'A':
            case 'a':
                return (byte) 10;
            case 'B':
            case 'b':
                return JceStruct.STRUCT_END;
            case acc_request.CMD_RST_CM /*67*/:
            case 'c':
                return JceStruct.ZERO_TAG;
            case Opcodes.AGET /*68*/:
            case 'd':
                return JceStruct.SIMPLE_LIST;
            case 'E':
            case 'e':
                return (byte) 14;
            case Opcodes.AGET_OBJECT /*70*/:
            case 'f':
                return (byte) 15;
            default:
                return (byte) 0;
        }
    }

    public static byte[] a(String str) {
        int i = 0;
        int length = str.length() / 2;
        byte[] bArr = new byte[length];
        int i2 = 0;
        while (i < length) {
            byte a = a(str.charAt(i * 2));
            int i3 = i2 + 1;
            bArr[i2] = (byte) ((a << 4) | a(str.charAt((i * 2) + 1)));
            i++;
            i2 = i3;
        }
        return bArr;
    }

    public void a(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        if (toServiceMsg != null) {
            String str;
            if (toServiceMsg.getAttributes().containsKey(v.d)) {
                str = (String) toServiceMsg.getAttribute(v.d);
                fromServiceMsg.addAttribute(v.d, fromServiceMsg.getUin());
                toServiceMsg.setUin(str);
            }
            if (toServiceMsg.getAttributes().containsKey(v.i)) {
                str = (String) toServiceMsg.getAttribute(v.i);
                toServiceMsg.setServiceCmd(str);
                fromServiceMsg.setServiceCmd(str);
            }
        }
        if (toServiceMsg == null && fromServiceMsg.isSuccess() && !fromServiceMsg.getUin().equals("0") && null != null) {
            fromServiceMsg.addAttribute(a.ar, null);
        }
        if (toServiceMsg != null) {
            toServiceMsg.getAttributes().remove(v.j);
        }
        fromServiceMsg.addAttribute(a.aN, Long.valueOf(System.currentTimeMillis()));
        QLog.d(B, 4, "recv msg.ssoCmd:" + fromServiceMsg.getServiceCmd() + " ssoSeq:" + fromServiceMsg.getRequestSsoSeq());
        this.k.add(new w(toServiceMsg, fromServiceMsg));
    }

    public int a(ToServiceMsg toServiceMsg) {
        if (toServiceMsg == null) {
            return -1;
        }
        this.d.b(toServiceMsg);
        return toServiceMsg.getRequestSsoSeq();
    }

    public void b(ToServiceMsg toServiceMsg) {
        com.tencent.qalsdk.sdk.a c = ai.c(toServiceMsg);
        this.y.a(c);
        this.j = c.b;
        this.e.a(toServiceMsg, am.appRegister);
    }

    public void c(ToServiceMsg toServiceMsg) {
        this.e.a(ai.b(toServiceMsg), toServiceMsg);
        this.y.a(toServiceMsg.getUin());
    }

    public void d(ToServiceMsg toServiceMsg) {
        this.e.a(toServiceMsg);
    }

    public void e(ToServiceMsg toServiceMsg) {
        this.e.b(ai.a(toServiceMsg), toServiceMsg);
    }

    public void f(ToServiceMsg toServiceMsg) {
        this.e.a(ai.a(toServiceMsg), toServiceMsg);
    }

    public int b(String str) {
        return this.e.a(str);
    }

    public as d() {
        return this.p;
    }

    public LinkedBlockingQueue<w> e() {
        return this.k;
    }

    public static synchronized int f() {
        int incrementAndGet;
        synchronized (j.class) {
            incrementAndGet = F.incrementAndGet();
            if (incrementAndGet > 1000000) {
                F.set(new Random().nextInt(100000) + 60000);
            }
        }
        return incrementAndGet;
    }

    public q g() {
        return this.A;
    }

    public void g(ToServiceMsg toServiceMsg) {
        this.d.b(toServiceMsg);
    }

    public f h() {
        return this.g;
    }

    public int i() {
        return this.z;
    }

    public void j() {
        this.w.set(true);
    }

    public void k() {
        this.w.set(false);
    }

    public boolean l() {
        return this.w.get();
    }

    public i m() {
        return this.l;
    }

    private void q() {
        try {
            String config = l.a().getConfig(x);
            QLog.d(B, "load sso server env:" + config);
            if (config != null) {
                this.D = Integer.parseInt(config);
                QLog.i(B, "load sso server env:" + this.D);
            }
        } catch (Exception e) {
            QLog.e(B, "setServerEnv exception" + e.getMessage());
        }
    }

    public void n() {
        FromServiceMsg fromServiceMsg = new FromServiceMsg(i(), f(), "0", a.cj);
        fromServiceMsg.setMsgSuccess();
        fromServiceMsg.setMsfCommand(MsfCommand.qal_setServerEnv);
        fromServiceMsg.addAttribute(v.N, Integer.valueOf(this.D));
        MsfSdkUtils.addFromMsgProcessName(v.n, fromServiceMsg);
        QLog.i(B, "send to sdk server env:" + this.D);
        a(null, fromServiceMsg);
    }

    public void b(int i) {
        if (i == 0 || i == 1) {
            QLog.i(B, "save sso server env:" + i);
            try {
                l.a().setConfig(x, String.valueOf(i));
                return;
            } catch (UnsatisfiedLinkError e) {
                QLog.e(B, "saveServerEnv exception:" + e.getMessage());
                return;
            }
        }
        QLog.e(B, "save sso server env value error:" + i);
    }

    public int o() {
        return this.D;
    }

    public static void a(String str, int i) {
        FromServiceMsg fromServiceMsg = new FromServiceMsg("0", a.ag);
        fromServiceMsg.setAppId(i);
        fromServiceMsg.setMsfCommand(MsfCommand.pushSetConfig);
        MsfSdkUtils.addFromMsgProcessName(str, fromServiceMsg);
        fromServiceMsg.addAttribute(a.aU, Integer.valueOf(NetConnInfoCenter.socketConnState));
        fromServiceMsg.addAttribute(a.aV, Long.valueOf(NetConnInfoCenter.servetTimeSecondInterv));
        fromServiceMsg.setMsgSuccess();
        c.a(v.n, null, fromServiceMsg);
    }

    public static byte[] a(FromServiceMsg fromServiceMsg) {
        Object wupBuffer = fromServiceMsg.getWupBuffer();
        if (wupBuffer.length - 4 < 0) {
            return null;
        }
        Object obj = new byte[(wupBuffer.length - 4)];
        System.arraycopy(wupBuffer, 4, obj, 0, wupBuffer.length - 4);
        return obj;
    }
}
