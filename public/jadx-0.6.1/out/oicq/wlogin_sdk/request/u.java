package oicq.wlogin_sdk.request;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import com.tencent.android.tpush.common.Constants;
import com.tencent.smtt.sdk.WebView;
import java.net.Socket;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import oicq.wlogin_sdk.b.au;
import oicq.wlogin_sdk.report.report_t;
import oicq.wlogin_sdk.report.report_t1;
import oicq.wlogin_sdk.sharemem.WloginLoginInfo;
import oicq.wlogin_sdk.sharemem.WloginSigInfo;
import oicq.wlogin_sdk.sharemem.WloginSimpleInfo;
import oicq.wlogin_sdk.tools.MD5;
import oicq.wlogin_sdk.tools.util;
import tencent.tls.tools.I18nMsg;

/* compiled from: request_global */
public class u {
    public static byte[] A = new byte[0];
    static byte[] B = new byte[0];
    public static byte[] C = new byte[0];
    public static int D = 0;
    public static byte[] E = new byte[0];
    public static byte[] F = new byte[0];
    public static byte[] G = new byte[0];
    public static byte[] H = new byte[0];
    public static byte[] I = new byte[0];
    public static byte[] J = new byte[0];
    public static byte[] K = "android".getBytes();
    static byte[] L = new byte[0];
    static byte[] M = new byte[0];
    static byte[] N = new byte[0];
    static byte[] O = new byte[0];
    static byte[] P = new byte[0];
    static byte[] Q = new byte[0];
    static byte[] R = new byte[0];
    static byte[] S = new byte[0];
    static int T = 0;
    static int U = 0;
    static int V = 0;
    static int W = 0;
    static int X = 0;
    public static int Y = 0;
    public static int Z = 0;
    public static SecureRandom a = m();
    static byte[] aa = new byte[0];
    static long ab = 0;
    public static long ac = 0;
    static byte[] ad = new byte[4];
    static int ae = 0;
    static int af = 0;
    public static boolean ag = false;
    public static d aj = null;
    protected static String ak = "";
    public static report_t1 al = new report_t1();
    public static long am = 0;
    public static TreeMap<Long, async_context> an = new TreeMap();
    public static boolean ap = false;
    public static boolean aq = true;
    private static Object ar = new Object();
    static Boolean e = Boolean.valueOf(false);
    public static Context t = null;
    public static int u = I18nMsg.ZH_CN;
    public static String v = "";
    static int w = 0;
    static int x = 1;
    static int y = 0;
    static int z = 0;
    Socket ah = null;
    Socket ai = null;
    public int ao;
    public byte[] b = null;
    public byte[] c = new byte[16];
    public au d = null;
    public long f = 0;
    public String g = "";
    public long h = 0;
    public int i = 0;
    public WFastLoginInfo j;
    public int k = 0;
    public int l = 5000;
    public int m = 0;
    public byte[] n = new byte[16];
    public byte[] o = new byte[16];
    public byte[] p = new byte[16];
    public byte[] q = new byte[0];
    public byte[] r = new byte[0];
    public int s = 0;

    private static SecureRandom m() {
        try {
            return new SecureRandom();
        } catch (Throwable th) {
            return null;
        }
    }

    public u(Context context) {
    }

    public u a(long j) {
        u uVar = new u(null);
        uVar.k = this.k;
        uVar.l = this.l;
        if (this.c != null) {
            uVar.c = (byte[]) this.c.clone();
        }
        if (!(this.n == null || this.p == null)) {
            uVar.n = (byte[]) this.n.clone();
            uVar.p = (byte[]) this.p.clone();
        }
        if (j <= 0) {
            uVar.h = a();
        } else {
            uVar.h = j;
        }
        return uVar;
    }

    public static synchronized long a() {
        long j;
        synchronized (u.class) {
            if (am > 200) {
                am = 0;
            }
            j = am + 1;
            am = j;
        }
        return j;
    }

    public static async_context b(long j) {
        async_context oicq_wlogin_sdk_request_async_context;
        synchronized (ar) {
            async_context oicq_wlogin_sdk_request_async_context2 = (async_context) an.get(Long.valueOf(j));
            if (oicq_wlogin_sdk_request_async_context2 == null) {
                try {
                    oicq_wlogin_sdk_request_async_context = new async_context();
                    try {
                        an.put(Long.valueOf(j), oicq_wlogin_sdk_request_async_context);
                    } catch (Exception e) {
                        Exception e2 = e;
                        util.printException(e2, "");
                        return oicq_wlogin_sdk_request_async_context;
                    }
                } catch (Exception e3) {
                    Exception exception = e3;
                    oicq_wlogin_sdk_request_async_context = oicq_wlogin_sdk_request_async_context2;
                    e2 = exception;
                    util.printException(e2, "");
                    return oicq_wlogin_sdk_request_async_context;
                }
            }
            oicq_wlogin_sdk_request_async_context = oicq_wlogin_sdk_request_async_context2;
        }
        return oicq_wlogin_sdk_request_async_context;
    }

    public static void c(long j) {
        synchronized (ar) {
            try {
                an.remove(Long.valueOf(j));
            } catch (Exception e) {
                util.printException(e, "");
            }
        }
    }

    public static void b() {
        String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
        if (format.compareTo(v) != 0) {
            v = format;
            new h(t).start();
        }
    }

    public static void c() {
        byte[] bArr = util.get_mac_addr(t);
        if (bArr != null && bArr.length > 0) {
            bArr = MD5.toMD5Byte(bArr);
        }
        byte[] bArr2 = util.get_imei_id(t);
        if (bArr2 != null && bArr2.length > 0) {
            bArr2 = MD5.toMD5Byte(bArr2);
        }
        byte[] generateGuid = util.generateGuid(t);
        if (generateGuid != null && generateGuid.length > 0) {
            generateGuid = MD5.toMD5Byte(generateGuid);
        }
        if (util.get_last_flag(t) != 0) {
            byte[] bArr3 = util.get_last_mac(t);
            byte[] bArr4 = util.get_last_imei(t);
            byte[] bArr5 = util.get_last_guid(t);
            if (!Arrays.equals(bArr, bArr3)) {
                X |= 1;
            }
            if (!Arrays.equals(bArr2, bArr4)) {
                X |= 2;
            }
            if (!Arrays.equals(generateGuid, bArr5)) {
                X |= 4;
            }
        }
        util.save_cur_flag(t, 1);
        util.save_cur_mac(t, bArr);
        util.save_cur_imei(t, bArr2);
        util.save_cur_guid(t, generateGuid);
    }

    public static void d() {
        int i;
        util.LOGI("init start", "");
        int i2 = util.get_saved_network_type(t);
        Y = 0;
        Object guidFromFile = util.getGuidFromFile(t);
        byte[] generateGuid = util.generateGuid(t);
        if (guidFromFile == null || guidFromFile.length <= 0) {
            Object bytes;
            if (generateGuid == null || generateGuid.length <= 0) {
                bytes = new String("%4;7t>;28<fc.5*6").getBytes();
                U = 0;
                W = 20;
            } else {
                U = 1;
                W = 17;
            }
            util.saveGuidToFile(t, bytes);
            V = 0;
            T = 1;
            guidFromFile = bytes;
        } else {
            if (generateGuid == null || generateGuid.length <= 0) {
                generateGuid = new String("%4;7t>;28<fc.5*6").getBytes();
            }
            if (Arrays.equals(generateGuid, guidFromFile)) {
                V = 0;
            } else {
                V = 1;
            }
            U = 1;
            T = 0;
            W = 1;
        }
        c();
        Y |= (W << 24) & WebView.NIGHT_MODE_COLOR;
        Y |= (X << 8) & 65280;
        A = (byte[]) guidFromFile.clone();
        B = (byte[]) guidFromFile.clone();
        M = util.get_imei_id(t);
        if (M != null && M.length > 0) {
            M = MD5.toMD5Byte(M);
        }
        ae = VERSION.SDK_INT;
        N = util.get_mac_addr(t);
        if (N != null && N.length > 0) {
            N = MD5.toMD5Byte(N);
        }
        R = util.get_bssid_addr(t);
        if (R != null && R.length > 0) {
            R = MD5.toMD5Byte(R);
        }
        S = util.get_ssid_addr(t);
        O = util.get_android_id(t);
        if (O != null && O.length > 0) {
            O = MD5.toMD5Byte(O);
        }
        L = util.get_IMSI(t);
        if (L != null && L.length > 0) {
            L = MD5.toMD5Byte(L);
        }
        C = util.get_sim_operator_name(t);
        D = util.get_network_type(t);
        if (i2 != D) {
            util.set_net_retry_type(t, 0);
            util.save_network_type(t, D);
        }
        F = util.get_apn_string(t).getBytes();
        aa = util.get_ksid(t);
        E = util.get_apk_id(t);
        G = util.get_apk_v(t, new String(E));
        H = util.getPkgSigFromApkName(t, t.getPackageName());
        Q = util.getAppName(t);
        String str = VERSION.RELEASE;
        if (str == null) {
            str = "";
        }
        J = str.getBytes();
        str = Build.MODEL;
        if (str == null) {
            I = new byte[0];
        } else {
            I = str.getBytes();
        }
        str = Build.BRAND;
        if (str == null) {
            P = new byte[0];
        } else {
            P = str.getBytes();
        }
        if (util.isFileExist("/system/bin/su") || util.isFileExist("/system/xbin/su") || util.isFileExist("/sbin/su")) {
            i = 1;
        } else {
            i = 0;
        }
        if (i == 1) {
            i = 1;
        } else {
            i = 0;
        }
        Z = i;
        aj = new d(t);
        al = report_t.read_fromfile(t);
        if (al == null) {
            al = new report_t1();
        }
        util.LOGI("init done", "");
    }

    public boolean e() {
        return this.k != 0;
    }

    public static long f() {
        return System.currentTimeMillis() / 1000;
    }

    public static long g() {
        return (System.currentTimeMillis() / 1000) + ac;
    }

    public void a(byte[] bArr, byte[] bArr2) {
        ab = (((long) util.buf_to_int32(bArr, 0)) & 4294967295L) - (System.currentTimeMillis() / 1000);
        ac = ab;
        ad = bArr2;
    }

    public void a(Context context) {
        t = context;
        Object obj = util.get_rand_16byte(a);
        System.arraycopy(obj, 0, this.c, 0, obj.length);
    }

    public void h() {
        util.LOGD("close_connect", "close_connect");
        if (this.ah != null) {
            try {
                util.LOGD("close_connect", this.ah.toString());
                this.ah.close();
            } catch (Exception e) {
                util.printException(e, "");
            }
            this.ah = null;
        }
    }

    public void i() {
        util.LOGD("close_transport_connect", "close_transport_connect");
        if (this.ai != null) {
            try {
                util.LOGD("close_transport_connect", this.ai.toString());
                this.ai.close();
            } catch (Exception e) {
                util.printException(e, "");
            }
            this.ai = null;
        }
    }

    public synchronized int a(long j, long j2, long j3, long j4, byte[] bArr, byte[] bArr2) {
        return aj.a(j, j2, j3, j4, bArr, bArr2);
    }

    public synchronized WloginSigInfo a(long j, long j2) {
        WloginSigInfo c;
        util.LOGI("get_siginfo appid=" + j2, j + "");
        c = aj.c(j, j2);
        if (c != null) {
        }
        return c;
    }

    public synchronized void j() {
        aj.a();
    }

    public synchronized WloginSimpleInfo d(long j) {
        WloginSimpleInfo b;
        b = aj.b(j);
        if (b != null) {
        }
        return b;
    }

    public synchronized void a(String str) {
        aj.a(str);
    }

    public synchronized long b(String str) {
        long j = 0;
        synchronized (this) {
            UinInfo a = aj.a(str, true);
            if (!(a == null || a._uin.longValue() == 0)) {
                j = a._uin.longValue();
            }
        }
        return j;
    }

    public synchronized UinInfo c(String str) {
        return aj.a(str, false);
    }

    public synchronized String e(long j) {
        return aj.b(Long.valueOf(j));
    }

    public synchronized List<WloginLoginInfo> k() {
        return aj.a(true);
    }

    public synchronized int a(long j, long j2, byte[][] bArr, long j3, long j4, long j5, long j6, long j7, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, byte[][] bArr6, byte[] bArr7, byte[] bArr8, byte[] bArr9, byte[] bArr10, byte[] bArr11, byte[] bArr12, byte[] bArr13, byte[] bArr14, byte[] bArr15, byte[] bArr16, byte[] bArr17, byte[] bArr18, byte[][] bArr19, long[] jArr, int i) {
        aj.e = this.ao;
        return aj.a(j, j2, bArr, j3, j4, j5, j6, j7, bArr2, bArr3, bArr4, bArr5, bArr6, bArr7, bArr8, bArr9, bArr10, bArr11, bArr12, bArr13, bArr14, bArr15, bArr16, bArr17, bArr18, bArr19, jArr, i);
    }

    public synchronized int a(long j, long j2, byte[] bArr) {
        return aj.a(j, j2, bArr);
    }

    public synchronized int b(long j, long j2) {
        return aj.a(j, j2);
    }

    public void c(long j, long j2) {
        aj.b(j, j2);
    }

    public synchronized void d(long j, long j2) {
        aj.a(Long.valueOf(j), Long.valueOf(j2));
    }

    public synchronized void a(String str, Long l) {
        aj.a(str, l, true);
    }

    public synchronized void a(String str, Long l, boolean z) {
        aj.a(str, l, z);
    }

    public synchronized void d(String str) {
        aj.b(str);
    }

    @SuppressLint({"NewApi"})
    public static String l() {
        if (ak != null && ak.length() > 0) {
            return ak;
        }
        try {
            int myPid = Process.myPid();
            for (RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) t.getSystemService(Constants.FLAG_ACTIVITY_NAME)).getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == myPid) {
                    ak = runningAppProcessInfo.processName;
                    return ak;
                }
            }
        } catch (Throwable th) {
            util.printThrowable(th, "");
        }
        return "";
    }
}
