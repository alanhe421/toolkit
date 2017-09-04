package qalsdk;

import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.mobileqq.pb.InvalidProtocolBufferMicroException;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.c;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.core.l;
import com.tencent.qalsdk.core.m;
import com.tencent.qalsdk.im_open.mobroute.MobRouteSSOList;
import com.tencent.qalsdk.im_open.mobroute.MobRouteSSOListInfo;
import com.tencent.qalsdk.util.QLog;
import com.tencent.upload.log.trace.TracerConfig;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: ConfigManager */
public class d {
    public static final String a = "ConfigService.ClientReq";
    static final String b = "MSF.C.ConfigManager";
    public static final String c = "__loginSdk_mobilessotime";
    public static final String d = "__loginSdk_wifissotime";
    public static final String e = "__loginSdk_checkmobilessotime";
    public static final String f = "__loginSdk_checkwifissotime";
    public static final String g = "_msf_isBootingKey";
    static ConcurrentHashMap<String, String> h = new ConcurrentHashMap();
    public static boolean i = true;
    static AtomicBoolean k = new AtomicBoolean();
    static long l = 0;
    static long m = 0;
    static long n = 0;
    static long o = 0;
    public static final int p = 100;
    public static long q = 0;
    public static long r = 0;
    private static final byte[] s = new byte[]{(byte) -16, (byte) 68, (byte) 31, (byte) 95, (byte) -12, (byte) 45, (byte) -91, (byte) -113, (byte) -36, (byte) -9, (byte) -108, (byte) -102, (byte) -70, (byte) 98, (byte) -44, (byte) 17};
    j j;

    public d(j jVar) {
        this.j = jVar;
    }

    public void a() {
        b();
    }

    protected void b() {
        String config = l.a().getConfig(e);
        if (config == null || config.length() == 0) {
            config = "0";
        }
        l = Long.parseLong(config);
        config = l.a().getConfig(f);
        if (config == null || config.length() == 0) {
            config = "0";
        }
        m = Long.parseLong(config);
        config = l.a().getConfig(c);
        if (config == null || config.length() == 0) {
            config = "0";
        }
        n = Long.parseLong(config);
        config = l.a().getConfig(d);
        if (config == null || config.length() == 0) {
            config = "0";
        }
        o = Long.parseLong(config);
        config = l.a().getConfig(g);
        if (config == null || config.length() == 0) {
            config = "false";
        }
        a(Boolean.parseBoolean(config));
        z();
    }

    private void z() {
        if (h.containsKey("msf_AnyPacketAsPushHB")) {
            try {
                String str = (String) h.get("msf_AnyPacketAsPushHB");
                i = !str.equals("0");
                if (QLog.isColorLevel()) {
                    QLog.d(b, 2, "config useAnyPacketAsPushHB " + str);
                }
            } catch (Exception e) {
                if (QLog.isColorLevel()) {
                    QLog.d(b, 2, "set msf_noReportRdmEvent error " + e);
                }
            }
        }
    }

    public static String[] c() {
        if (h.containsKey("MultiPkgPara")) {
            String[] split = ((String) h.get("MultiPkgPara")).replaceAll("\\|", ",").split(",");
            if (split.length == 4) {
                return split;
            }
        }
        QLog.d(b, 1, "login merge configuration not be found.");
        return null;
    }

    public static String d() {
        if (h.containsKey("msf_locallogtime")) {
            return (String) h.get("msf_locallogtime");
        }
        return "3";
    }

    public static String e() {
        if (h.containsKey("msf_checkSsoIntervtime")) {
            return (String) h.get("msf_checkSsoIntervtime");
        }
        return "300000";
    }

    public static long f() {
        try {
            if (h.containsKey("msf_heartBeatTimeout")) {
                return (long) Integer.parseInt((String) h.get("msf_heartBeatTimeout"));
            }
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.d(b, 2, "getHeartBeatTimeout error" + e);
            }
        }
        return a.ap;
    }

    public static int g() {
        try {
            if (h.containsKey("msf_heartBeatRetrycount")) {
                return Integer.parseInt((String) h.get("msf_heartBeatRetrycount"));
            }
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.d(b, 2, "getHeartBeatRetryCount error" + e);
            }
        }
        return 1;
    }

    public static int h() {
        try {
            if (h.containsKey("msf_busPacketTimeoutMaxNum")) {
                return Integer.parseInt((String) h.get("msf_busPacketTimeoutMaxNum"));
            }
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.d(b, 2, "getBusPacketTimeoutMaxNum error" + e);
            }
        }
        return 10;
    }

    public static long i() {
        try {
            if (h.containsKey("msf_busCheckServerTimeInterval")) {
                return Long.parseLong((String) h.get("msf_busCheckServerTimeInterval"));
            }
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.d(b, 2, "getBusPacketTimeoutMaxNum error" + e);
            }
        }
        return 5000;
    }

    public static long j() {
        try {
            if (h.containsKey("msf_checkUpdateServerTimeInterval")) {
                return Long.parseLong((String) h.get("msf_checkUpdateServerTimeInterval"));
            }
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.d(b, 2, "getUpdateServerTimePacketTimeoutMaxNum error" + e);
            }
        }
        return 72000000;
    }

    public static long k() {
        try {
            if (h.containsKey("msf_checkUpdateServerTimeExtraInterval")) {
                return Long.parseLong((String) h.get("msf_checkUpdateServerTimeExtraInterval"));
            }
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.d(b, 2, "getUpdateServerTimePacketTimeoutExtraInterval error" + e);
            }
        }
        return 7200000;
    }

    public static long l() {
        try {
            if (h.containsKey("msf_checkServerTimeCompareInterval")) {
                return Long.parseLong((String) h.get("msf_checkServerTimeCompareInterval"));
            }
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.d(b, 2, "getCheckServerTimeCompareInterval error" + e);
            }
        }
        return 7200000;
    }

    public static int m() {
        try {
            if (h.containsKey("msf_heartBeatTimeInterval")) {
                if (QLog.isColorLevel()) {
                    QLog.d(b, 2, "msf_heartBeatTimeInterval = " + ((String) h.get("msf_heartBeatTimeInterval")));
                }
                return (Integer.parseInt((String) h.get("msf_heartBeatTimeInterval")) * 60) * 1000;
            }
        } catch (Exception e) {
            if (QLog.isDevelopLevel()) {
                QLog.d(b, 4, "getHeartBeatTimeInterval error" + e);
            }
        }
        return 60000;
    }

    public static int n() {
        try {
            if (h.containsKey("msf_netIdleTimeInterval")) {
                if (QLog.isColorLevel()) {
                    QLog.d(b, 2, "msf_netIdleTimeInterval = " + ((String) h.get("msf_netIdleTimeInterval")));
                }
                return (Integer.parseInt((String) h.get("msf_netIdleTimeInterval")) * 60) * 1000;
            }
        } catch (Exception e) {
            if (QLog.isDevelopLevel()) {
                QLog.d(b, 4, "getNetIdleTimeInterval error" + e);
            }
        }
        return 1680000;
    }

    public static String o() {
        try {
            if (h.containsKey("TcpdumpSSOVip_new")) {
                return (String) h.get("TcpdumpSSOVip_new");
            }
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.d(b, 2, "getTcpdumpSSOVip error" + e);
            }
        }
        return null;
    }

    public static int p() {
        try {
            if (h.containsKey("TcpdumpSSOTime")) {
                if (QLog.isColorLevel()) {
                    QLog.d(b, 2, "TcpdumpSSOTime = " + ((String) h.get("TcpdumpSSOTime")));
                }
                return Integer.parseInt((String) h.get("TcpdumpSSOTime"));
            }
        } catch (Exception e) {
            if (QLog.isDevelopLevel()) {
                QLog.d(b, 4, "getTcpdumpSSOTime error" + e);
            }
        }
        return 0;
    }

    public static int q() {
        try {
            if (h.containsKey("msf_netWeakTimeInterval")) {
                if (QLog.isColorLevel()) {
                    QLog.d(b, 2, "msf_netWeakTimeInterval = " + ((String) h.get("msf_netWeakTimeInterval")));
                }
                return (Integer.parseInt((String) h.get("msf_netWeakTimeInterval")) * 60) * 1000;
            }
        } catch (Exception e) {
            if (QLog.isDevelopLevel()) {
                QLog.d(b, 4, "getNetWeakTimeInterval error" + e);
            }
        }
        return 180000;
    }

    public static int r() {
        try {
            if (h.containsKey("msf_netWeakExceptionCount")) {
                if (QLog.isColorLevel()) {
                    QLog.d(b, 2, "msf_netWeakExceptionCount = " + ((String) h.get("msf_netWeakExceptionCount")));
                }
                return Integer.parseInt((String) h.get("msf_netWeakExceptionCount"));
            }
        } catch (Exception e) {
            if (QLog.isDevelopLevel()) {
                QLog.d(b, 4, "getNetWeakExceptionCount error" + e);
            }
        }
        return 3;
    }

    public static long s() {
        if (h.containsKey("msf_CallQQIntervTimeOnBoot")) {
            try {
                return Long.parseLong(((String) h.get("msf_CallQQIntervTimeOnBoot")).trim());
            } catch (NumberFormatException e) {
                if (QLog.isColorLevel()) {
                    QLog.d(b, 2, " get CallQQIntervTimeOnBoot error " + e);
                }
            }
        }
        return TracerConfig.LOG_FLUSH_DURATION;
    }

    public static boolean a(String str) {
        if (!h.containsKey(str + "_isAutoBoot")) {
            return true;
        }
        try {
            return Boolean.parseBoolean(((String) h.get(str + "_isAutoBoot")).trim());
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.d(b, 2, str + " set isAutoBoot error " + e);
            }
            return false;
        }
    }

    public static boolean t() {
        return k.get();
    }

    public static synchronized void a(boolean z) {
        synchronized (d.class) {
            if (QLog.isColorLevel()) {
                QLog.d(b, 2, "msfCore setAutoStaring " + z);
            }
            k.set(z);
            try {
                if (l.a() != null) {
                    l.a().setConfig(g, String.valueOf(z));
                }
                if (QLog.isColorLevel()) {
                    QLog.d(b, 2, "storeAutoStaring " + z);
                }
            } catch (UnsatisfiedLinkError e) {
                QLog.e(b, "setAutoStaring exception:" + e.getMessage());
            }
        }
    }

    public boolean u() {
        long currentTimeMillis = System.currentTimeMillis();
        if (m.f()) {
            if (l == 0) {
                return true;
            }
            if (n == 0) {
                if (currentTimeMillis - l < 43200000) {
                    return false;
                }
                return true;
            } else if (currentTimeMillis < n) {
                return false;
            } else {
                return true;
            }
        } else if (!m.e()) {
            return false;
        } else {
            if (m == 0) {
                return true;
            }
            if (o == 0) {
                if (currentTimeMillis - m < 43200000) {
                    return false;
                }
                return true;
            } else if (currentTimeMillis < o) {
                return false;
            } else {
                return true;
            }
        }
    }

    public void a(ToServiceMsg toServiceMsg, long j) {
        if (u()) {
            if (m.f()) {
                l = j;
                l.a().n_setConfig(e, String.valueOf(l));
                n = l + 3600000;
                v();
            } else if (m.e()) {
                m = j;
                l.a().n_setConfig(f, String.valueOf(m));
                o = m + 3600000;
                w();
            }
            try {
                a(toServiceMsg.getAppId(), toServiceMsg.getUin(), BuglyBroadcastRecevier.UPLOADLIMITED, m.e(), "");
            } catch (Throwable e) {
                if (QLog.isColorLevel()) {
                    QLog.d(b, 2, e.toString(), e);
                }
            }
        }
    }

    public void v() {
        l.a().n_setConfig(c, String.valueOf(n));
        if (QLog.isColorLevel()) {
            QLog.d(b, 2, "save next get mobile sso time is " + this.j.n.format(Long.valueOf(n)));
        }
    }

    public void w() {
        l.a().n_setConfig(d, String.valueOf(o));
        if (QLog.isColorLevel()) {
            QLog.d(b, 2, "save next get Wifi sso time is " + this.j.n.format(Long.valueOf(o)));
        }
    }

    public void a(int i, String str, long j, boolean z, String str2) {
        Thread eVar = new e(this, str, z, str2);
        eVar.setName("checkSsoByHttpThread");
        eVar.start();
    }

    public static boolean a(byte[] bArr) {
        boolean e = m.e();
        if (bArr.length < 14) {
            QLog.e(b, 1, "invalid rsp pkg.len");
            return false;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.get();
        int i = wrap.getInt();
        if (i != bArr.length) {
            QLog.e(b, 1, "invalid rsp pkg.len");
            return false;
        }
        byte[] bArr2 = new byte[(i - 14)];
        wrap.getShort();
        wrap.getShort();
        wrap.getInt();
        wrap.get(bArr2);
        wrap.get();
        MobRouteSSOList mobRouteSSOList = new MobRouteSSOList();
        try {
            mobRouteSSOList.mergeFrom(bArr2);
            if (mobRouteSSOList.vec_tcplist.get() != null && mobRouteSSOList.vec_tcplist.get().size() > 0) {
                ArrayList arrayList = new ArrayList();
                StringBuffer stringBuffer = new StringBuffer();
                bf bfVar;
                c a;
                if (e) {
                    for (MobRouteSSOListInfo mobRouteSSOListInfo : mobRouteSSOList.vec_tcplist.get()) {
                        bfVar = new bf();
                        bfVar.e = (byte) 0;
                        bfVar.f = mobRouteSSOList.uint32_timeout.get();
                        bfVar.a = mobRouteSSOListInfo.string_ip.get();
                        bfVar.b = mobRouteSSOListInfo.uint32_port.get();
                        a = c.a(bfVar, 0);
                        a.h = c.c;
                        arrayList.add(a);
                        stringBuffer.append(a.toString() + VoiceWakeuperAidl.PARAMS_SEPARATE);
                    }
                    QLog.d(b, 1, "recv wifi sso list " + stringBuffer.toString());
                    j.a().h().a(arrayList, false, false);
                } else {
                    for (MobRouteSSOListInfo mobRouteSSOListInfo2 : mobRouteSSOList.vec_tcplist.get()) {
                        bfVar = new bf();
                        bfVar.e = (byte) 0;
                        bfVar.f = mobRouteSSOList.uint32_timeout.get();
                        bfVar.a = mobRouteSSOListInfo2.string_ip.get();
                        bfVar.b = mobRouteSSOListInfo2.uint32_port.get();
                        a = c.a(bfVar, 1);
                        arrayList.add(a);
                        stringBuffer.append(a.toString() + VoiceWakeuperAidl.PARAMS_SEPARATE);
                    }
                    QLog.d(b, 1, "recv xg sso list " + stringBuffer.toString());
                    j.a().h().b(arrayList, false, false);
                }
            }
            return true;
        } catch (InvalidProtocolBufferMicroException e2) {
            QLog.e(b, 1, "ssolist pb parsing failed");
            e2.printStackTrace();
            return false;
        }
    }

    public static int x() {
        if (h.containsKey("msf_RetryStartProcTimes")) {
            try {
                return Integer.parseInt(((String) h.get("msf_RetryStartProcTimes")).trim());
            } catch (Exception e) {
                if (QLog.isColorLevel()) {
                    QLog.d(b, 2, " set getRetryStartProcessTimes error " + e);
                }
            }
        }
        return 100;
    }
}
