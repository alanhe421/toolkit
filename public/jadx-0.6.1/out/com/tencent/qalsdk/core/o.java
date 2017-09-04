package com.tencent.qalsdk.core;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.SystemClock;
import android.text.TextUtils;
import com.dynamicload.Lib.DLConstants;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.qalsdk.base.CloseConnReason;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.im_open.http;
import com.tencent.qalsdk.sdk.MsfCommand;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.sdk.ae;
import com.tencent.qalsdk.sdk.q;
import com.tencent.qalsdk.sdk.v;
import com.tencent.qalsdk.sdk.x;
import com.tencent.qalsdk.util.CodecWarpper;
import com.tencent.qalsdk.util.QLog;
import com.tencent.qalsdk.util.ZLibUtils;
import com.tencent.upload.log.trace.TracerConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import qalsdk.ai;
import qalsdk.d;
import qalsdk.m;

/* compiled from: Sender */
public class o {
    static int A = -1;
    static AtomicBoolean B = new AtomicBoolean();
    public static AtomicBoolean C = new AtomicBoolean();
    public static final String E = "GrayUinPro.Check";
    public static final String F = "res";
    public static final int I = -10008;
    public static final int J = -302;
    public static final int K = 302;
    static CopyOnWriteArraySet<String> L = new CopyOnWriteArraySet();
    private static final AtomicInteger U = new AtomicInteger();
    private static int X = 0;
    private static String Y = "";
    private static String[] Z = new String[]{"StreamSvr.UploadStreamMsg"};
    private static AtomicBoolean ad = new AtomicBoolean();
    public static final String b = "__extraTimeoutSeq";
    public static final String k = "MSF.C.NetConnTag";
    public static int l = 0;
    public static AtomicInteger m = new AtomicInteger(0);
    public static String p = ":";
    public static String q = "0";
    public static int r = 0;
    public static long s = -1;
    public static long y = 0;
    static int z = -1;
    HashSet<String> D = new HashSet();
    public int G = -1;
    public AtomicBoolean H = new AtomicBoolean();
    ArrayList<HashMap> M = new ArrayList();
    boolean N = false;
    private ConcurrentHashMap<Integer, ToServiceMsg> O = new ConcurrentHashMap();
    private ConcurrentHashMap<Integer, ArrayList<Integer>> P = new ConcurrentHashMap();
    private ConcurrentHashMap<Integer, ArrayList<ToServiceMsg>> Q = new ConcurrentHashMap();
    private ae<ToServiceMsg> R = new ae(1000);
    private ae<ToServiceMsg> S = new ae();
    private Handler T;
    private int V = 0;
    private String W = "0";
    public qalsdk.o a;
    private byte[] aa = null;
    private long ab = 0;
    private Random ac = new Random(System.currentTimeMillis());
    public int c = 10;
    public int d = 3072;
    public int e = 0;
    public int f = http.Internal_Server_Error;
    public int g = 60000;
    public long h = 0;
    public boolean i = false;
    public volatile boolean j = false;
    a n = new a(this);
    j o;
    b t = new b(this);
    AtomicBoolean u = new AtomicBoolean();
    int v = 0;
    long w = 0;
    long x = 0;

    /* compiled from: Sender */
    class a extends CodecWarpper {
        final /* synthetic */ o a;

        a(o oVar) {
            this.a = oVar;
        }

        public void onResponse(int i, Object obj, int i2) {
            if (obj != null) {
                if (o.z != -1) {
                    o.z = -1;
                }
                this.a.x = System.currentTimeMillis();
                o.y = this.a.x;
                FromServiceMsg fromServiceMsg = (FromServiceMsg) obj;
                int length = fromServiceMsg.getWupBuffer().length;
                if (fromServiceMsg.getServiceCmd().equals(com.tencent.qalsdk.base.a.J)) {
                    fromServiceMsg.setRequestSsoSeq(fromServiceMsg.getAppSeq());
                    if (!fromServiceMsg.isSuccess()) {
                        QLog.d("MSF.C.NetConnTag", 1, "failed merge netRecv ssoSeq:" + fromServiceMsg.getRequestSsoSeq() + " scmd: " + fromServiceMsg.getServiceCmd() + " len: " + fromServiceMsg.getWupBuffer().length);
                        ArrayList arrayList = (ArrayList) this.a.P.remove(Integer.valueOf(fromServiceMsg.getRequestSsoSeq()));
                        if (fromServiceMsg.getBusinessFailCode() == com.tencent.qalsdk.base.a.u) {
                            if (this.a.j) {
                                this.a.j = false;
                            }
                            QLog.d("MSF.C.NetConnTag", 1, "merge not support, " + Arrays.toString(arrayList.toArray()) + "resend, close merge.");
                        }
                        if (arrayList != null) {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                ToServiceMsg a = this.a.o.d.a(((Integer) it.next()).intValue());
                                if (a != null) {
                                    this.a.c(a);
                                }
                            }
                            return;
                        }
                    }
                }
                Object wupBuffer;
                try {
                    if ((fromServiceMsg.getFlag() & 1) != 0) {
                        wupBuffer = fromServiceMsg.getWupBuffer();
                        if (wupBuffer.length > 4) {
                            length = (((((wupBuffer[0] & 255) << 24) | 0) | ((wupBuffer[1] & 255) << 16)) | ((wupBuffer[2] & 255) << 8)) | (wupBuffer[3] & 255);
                            Object obj2 = new byte[length];
                            System.arraycopy(wupBuffer, 4, obj2, 0, length - 4);
                            Object decompress = ZLibUtils.decompress(obj2);
                            obj2 = new byte[(decompress.length + 4)];
                            obj2[0] = (byte) (((decompress.length + 4) >> 24) & 255);
                            obj2[1] = (byte) (((decompress.length + 4) >> 16) & 255);
                            obj2[2] = (byte) (((decompress.length + 4) >> 8) & 255);
                            obj2[3] = (byte) ((decompress.length + 4) & 255);
                            System.arraycopy(decompress, 0, obj2, 4, decompress.length);
                            fromServiceMsg.putWupBuffer(obj2);
                        } else if (!fromServiceMsg.getServiceCmd().equals(com.tencent.qalsdk.base.a.J)) {
                            onInvalidData(-6, wupBuffer.length);
                            return;
                        } else {
                            return;
                        }
                    }
                    a(fromServiceMsg, i2);
                } catch (Exception e) {
                    if (QLog.isColorLevel()) {
                        QLog.d(tag, 2, "uncompress data failed " + e);
                    }
                    if (!fromServiceMsg.getServiceCmd().equals(com.tencent.qalsdk.base.a.J)) {
                        onInvalidData(-7, wupBuffer.length);
                    }
                } catch (Throwable th) {
                    if (QLog.isColorLevel()) {
                        QLog.d(tag, 2, "handleSsoResp " + obj + " error " + th.toString(), th);
                    }
                }
            }
        }

        private void a(FromServiceMsg fromServiceMsg, int i) {
            long j;
            fromServiceMsg.addAttribute(com.tencent.qalsdk.base.a.aL, Long.valueOf(System.currentTimeMillis()));
            fromServiceMsg.addAttribute(com.tencent.qalsdk.base.a.aM, Long.valueOf(SystemClock.elapsedRealtime()));
            byte[] msgCookie = fromServiceMsg.getMsgCookie();
            if (msgCookie != null && msgCookie.length > 0) {
                this.a.o.d.a(msgCookie);
            }
            if (!fromServiceMsg.isSuccess() && fromServiceMsg.getBusinessFailCode() == -10008) {
                o.a(fromServiceMsg.getUin(), false);
            }
            fromServiceMsg.setRequestSsoSeq(fromServiceMsg.getAppSeq());
            if (m.k()) {
                m.b(false);
            }
            ToServiceMsg a = this.a.o.d.a(fromServiceMsg.getRequestSsoSeq());
            if (a == null || a.getAttribute(com.tencent.qalsdk.base.a.aK) == null || fromServiceMsg == null || fromServiceMsg.getAttribute(com.tencent.qalsdk.base.a.aL) == null) {
                j = 0;
            } else {
                j = ((Long) fromServiceMsg.getAttribute(com.tencent.qalsdk.base.a.aL)).longValue() - ((Long) a.getAttribute(com.tencent.qalsdk.base.a.aK)).longValue();
                if (j < 0) {
                    j = 0;
                }
                if (j > 2147483647L) {
                    j = 0;
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            if (QLog.isDevelopLevel()) {
                String valueOf;
                if (j == 0) {
                    valueOf = String.valueOf(j);
                } else {
                    valueOf = j + NetConnInfoCenter.getSignalStrengthsLog();
                }
                if (fromServiceMsg.getResultCode() != 1000) {
                    QLog.e("MSF.C.NetConnTag", 1, stringBuilder.append("netRecv ssoSeq:").append(fromServiceMsg.getRequestSsoSeq()).append(" uin:").append(MsfSdkUtils.getShortUin(fromServiceMsg.getUin())).append(" cmd:").append(fromServiceMsg.getServiceCmd()).append(" len:").append(i).append(" cost time:").append(valueOf).append(" code:").append(fromServiceMsg.getResultCode()).append(" failMsg:").append(fromServiceMsg.getBusinessFailMsg()).toString());
                } else {
                    QLog.i("MSF.C.NetConnTag", 1, stringBuilder.append("netRecv ssoSeq:").append(fromServiceMsg.getRequestSsoSeq()).append(" uin:").append(MsfSdkUtils.getShortUin(fromServiceMsg.getUin())).append(" cmd:").append(fromServiceMsg.getServiceCmd()).append(" len:").append(i).append(" cost time:").append(valueOf).toString());
                }
            } else if (fromServiceMsg.getResultCode() != 1000) {
                QLog.e("MSF.C.NetConnTag", 1, stringBuilder.append("netRecv ssoSeq:").append(fromServiceMsg.getRequestSsoSeq()).append(" uin:").append(MsfSdkUtils.getShortUin(fromServiceMsg.getUin())).append(" cmd:").append(fromServiceMsg.getServiceCmd()).append(" " + (fromServiceMsg.getRequestSsoSeq() + i)).append(" code:").append(fromServiceMsg.getResultCode()).append(" failMsg:").append(fromServiceMsg.getBusinessFailMsg()).toString());
            } else {
                QLog.i("MSF.C.NetConnTag", 1, stringBuilder.append("netRecv ssoSeq:").append(fromServiceMsg.getRequestSsoSeq()).append(" uin:").append(MsfSdkUtils.getShortUin(fromServiceMsg.getUin())).append(" cmd:").append(fromServiceMsg.getServiceCmd()).append(" " + (fromServiceMsg.getRequestSsoSeq() + i)).toString());
            }
            fromServiceMsg.addAttribute(com.tencent.qalsdk.base.a.ak, o.p + DLConstants.DEPENDENCY_PACKAGE_DIV + fromServiceMsg.getRequestSsoSeq() + DLConstants.DEPENDENCY_PACKAGE_DIV + fromServiceMsg.getServiceCmd() + DLConstants.DEPENDENCY_PACKAGE_DIV);
            fromServiceMsg.addAttribute(com.tencent.qalsdk.base.a.al, o.p);
            fromServiceMsg.addAttribute(com.tencent.qalsdk.base.a.an, o.q);
            if (a != null) {
                try {
                    this.a.a.a(fromServiceMsg, a);
                } catch (Throwable e) {
                    this.a.a.d();
                    QLog.d(tag, 1, "call firstResponseGetted error " + e, e);
                }
            }
            if (this.a.o.m.get() && this.a.o.d.a()) {
                this.a.a.o().a(CloseConnReason.pushNeedReConn);
                this.a.o.m.set(false);
            }
            if (a != null) {
                fromServiceMsg.setAppSeq(a.getAppSeq());
                fromServiceMsg.setMsfCommand(a.getMsfCommand());
                fromServiceMsg.setAppId(a.getAppId());
                if (!fromServiceMsg.isSuccess()) {
                    int businessFailCode = fromServiceMsg.getBusinessFailCode();
                    if (businessFailCode == 302) {
                        fromServiceMsg.setBusinessFail(o.J);
                        businessFailCode = o.J;
                    }
                    if (businessFailCode == o.J) {
                        this.a.a.o().a(CloseConnReason.connFull);
                        this.a.o.g(a);
                        return;
                    } else if (businessFailCode == -10008) {
                        if (x.a(a)) {
                            QLog.d(tag, 1, "This msg has already resend by -10008, won't resend again!");
                        } else {
                            a.getAttributes().put(com.tencent.qalsdk.base.a.bc, Boolean.valueOf(true));
                            this.a.o.g(a);
                            return;
                        }
                    }
                }
            }
            if (o.ad.get()) {
                QLog.e(tag, 1, "invalidSign, " + fromServiceMsg + " is droped.");
            } else if (fromServiceMsg.getMsfCommand() == MsfCommand._msf_HeartbeatAlive) {
                this.a.a(fromServiceMsg, a);
            } else {
                o.m.set(0);
                this.a.o.g().a(a, fromServiceMsg);
            }
            NetConnInfoCenter.impl.p();
        }

        public void onInvalidSign() {
            QLog.d(tag, 1, "MSF.C.CodecWarpper onInvalidSign");
            FromServiceMsg fromServiceMsg = new FromServiceMsg(this.a.o.i(), j.f(), "0", com.tencent.qalsdk.base.a.Y);
            fromServiceMsg.setBusinessFail(com.tencent.qalsdk.base.a.r, "onInvalidSign");
            fromServiceMsg.setMsfCommand(MsfCommand.onInvalidSign);
            MsfSdkUtils.addFromMsgProcessName(v.n, fromServiceMsg);
            this.a.o.a(null, fromServiceMsg);
            o.ad.set(true);
        }

        public void onInvalidData(int i, int i2) {
            QLog.d(tag, 1, "MSF.C.CodecWarpper onInvalidData " + i + " size is " + i2 + ", try to closeConn");
            this.a.a.o().a(a(i));
            try {
                this.a.a.f();
            } catch (Exception e) {
                QLog.d(tag, 1, "call findResponseDataError error " + e);
            }
        }

        private CloseConnReason a(int i) {
            switch (i) {
                case -8:
                    return CloseConnReason.closeByPbUnpackFailInLoginMerge;
                case -7:
                    return CloseConnReason.closeByZlibUncompressException;
                case -6:
                    return CloseConnReason.closeByZlibDataLengthTooShort;
                case -5:
                    return CloseConnReason.invalidData;
                case -4:
                    return CloseConnReason.closeByDecryptFailEmpty;
                case -3:
                    return CloseConnReason.closeByDecryptFailTwice;
                case -2:
                    return CloseConnReason.closeByDecryptFailOnce;
                default:
                    return CloseConnReason.closeForOtherReason;
            }
        }
    }

    /* compiled from: Sender */
    class b extends Thread {
        long a = 0;
        final /* synthetic */ o b;

        b(o oVar) {
            this.b = oVar;
        }

        public void run() {
            while (true) {
                ToServiceMsg toServiceMsg;
                try {
                    toServiceMsg = (ToServiceMsg) this.b.R.k();
                    if (toServiceMsg != null) {
                        String str;
                        byte[] bArr;
                        String serviceCmd = toServiceMsg.getServiceCmd();
                        int indexOf = serviceCmd.indexOf("#");
                        if (indexOf != -1) {
                            String str2 = serviceCmd.substring(indexOf + 1, serviceCmd.length()) + "_new";
                            serviceCmd = serviceCmd.substring(0, indexOf);
                            str = str2;
                        } else {
                            str = null;
                        }
                        long longValue = ((Long) toServiceMsg.getAttribute(com.tencent.qalsdk.base.a.aO, Long.valueOf(0))).longValue();
                        long currentTimeMillis = System.currentTimeMillis();
                        this.b.o.f.a(toServiceMsg, currentTimeMillis);
                        if (toServiceMsg.getWupBuffer() != null) {
                            byte[] wupBuffer;
                            byte b = (byte) 0;
                            if (m.e()) {
                                b = (byte) 1;
                            } else if (m.f()) {
                                int j = m.j() + 100;
                                if (j > 254) {
                                    j = 254;
                                    if (QLog.isColorLevel()) {
                                        QLog.d("MSF.C.NetConnTag", 2, "error,netWorkType is " + 254);
                                    }
                                }
                                b = (byte) j;
                            } else if (this.a == 0 || currentTimeMillis - this.a > BuglyBroadcastRecevier.UPLOADLIMITED) {
                                this.a = currentTimeMillis;
                                try {
                                    NetConnInfoCenter.checkConnInfo(j.a().u, true);
                                } catch (Exception e) {
                                    QLog.d("MSF.C.NetConnTag", 1, "checkConnInfo " + e);
                                }
                            }
                            String str3 = "";
                            if (toServiceMsg.getMsfCommand() == MsfCommand.qal_Hello) {
                                wupBuffer = toServiceMsg.getWupBuffer();
                            } else {
                                wupBuffer = CodecWarpper.encodeRequest(toServiceMsg.getRequestSsoSeq(), k.a(), k.c(), k.d(), str3, serviceCmd, this.b.b(), toServiceMsg.getAppId(), o.l, toServiceMsg.getUin(), toServiceMsg.getUinType(), b, toServiceMsg.getWupBuffer());
                            }
                            bArr = wupBuffer;
                        } else {
                            bArr = new byte[0];
                        }
                        if (bArr == null) {
                            this.b.a(toServiceMsg.getRequestSsoSeq());
                        } else {
                            boolean z = false;
                            while (!z) {
                                long currentTimeMillis2 = System.currentTimeMillis();
                                if (currentTimeMillis2 - longValue > toServiceMsg.getTimeout()) {
                                    QLog.i("MSF.C.NetConnTag", toServiceMsg.getServiceCmd() + " [is already sendTimeout,break.]");
                                    break;
                                }
                                if (o.y != 0 && currentTimeMillis2 - o.y > ((long) d.n()) && this.b.a.a()) {
                                    if (o.y >= this.b.a.o().f()) {
                                        o.y = 0;
                                        if (QLog.isColorLevel()) {
                                            QLog.d("MSF.C.NetConnTag", 2, "lastMessageTimeTooLong Close socket now. System.currentTimeMillis() - lastRecvSsoRespTime :" + (System.currentTimeMillis() - o.y) + " getNetIdleTimeInterval()" + d.n());
                                        }
                                        this.b.a(CloseConnReason.lastMessageTimeTooLong);
                                    } else {
                                        o.y = 0;
                                    }
                                }
                                if (o.y != 0 && currentTimeMillis2 - o.y > 360000 && this.b.a.a() && o.y >= this.b.a.o().f() && !this.b.u.get()) {
                                    if (QLog.isColorLevel()) {
                                        QLog.d("MSF.C.NetConnTag", 2, "no receive data for 5 minutes, start fast net detect now.");
                                    }
                                    this.b.p();
                                }
                                try {
                                    toServiceMsg.addAttribute(com.tencent.qalsdk.base.a.aI, Long.valueOf(SystemClock.elapsedRealtime()));
                                    toServiceMsg.addAttribute(com.tencent.qalsdk.base.a.aK, Long.valueOf(0));
                                    toServiceMsg.addAttribute(com.tencent.qalsdk.base.a.al, o.p);
                                    toServiceMsg.addAttribute(com.tencent.qalsdk.base.a.an, o.q);
                                    z = this.b.a.a(toServiceMsg.getAppId(), toServiceMsg.getAppSeq(), serviceCmd, str, toServiceMsg.getMsfCommand(), toServiceMsg.getUin(), toServiceMsg.getRequestSsoSeq(), bArr);
                                } catch (Throwable th) {
                                    if (QLog.isColorLevel()) {
                                        QLog.d("MSF.C.NetConnTag", 2, "send msg error " + th, th);
                                    }
                                }
                                if (z) {
                                    toServiceMsg.addAttribute(com.tencent.qalsdk.base.a.aK, Long.valueOf(System.currentTimeMillis()));
                                    if (toServiceMsg.getServiceCmd().startsWith("SharpSvr.c2s")) {
                                        o.s = SystemClock.elapsedRealtime();
                                    }
                                    if (this.b.H.get() && this.b.G == -1) {
                                        this.b.G = toServiceMsg.getRequestSsoSeq();
                                        QLog.d("MSF.C.NetConnTag", 1, "set afterReloadD2SendSeq " + this.b.G);
                                    }
                                } else {
                                    toServiceMsg.getAttributes().remove(com.tencent.qalsdk.base.a.aI);
                                    toServiceMsg.getAttributes().remove(com.tencent.qalsdk.base.a.aK);
                                    toServiceMsg.getAttributes().remove(com.tencent.qalsdk.base.a.al);
                                    toServiceMsg.getAttributes().remove(com.tencent.qalsdk.base.a.an);
                                    this.b.a.a(CloseConnReason.writeError);
                                    try {
                                        Thread.sleep(com.tencent.qalsdk.base.a.as);
                                    } catch (Throwable th2) {
                                        if (QLog.isColorLevel()) {
                                            QLog.d("MSF.C.NetConnTag", 2, th2.toString(), th2);
                                        }
                                    }
                                }
                            }
                            if (toServiceMsg.getMsfCommand() == MsfCommand._msf_kickedAndCleanTokenResp) {
                                try {
                                    FromServiceMsg fromServiceMsg = (FromServiceMsg) toServiceMsg.getAttribute(toServiceMsg.getServiceCmd());
                                    fromServiceMsg.setBusinessFail(com.tencent.qalsdk.base.a.p, fromServiceMsg.getBusinessFailMsg());
                                    z = false;
                                    if (toServiceMsg.getAttributes().containsKey(com.tencent.qalsdk.base.a.aR)) {
                                        z = ((Boolean) toServiceMsg.getAttribute(com.tencent.qalsdk.base.a.aR)).booleanValue();
                                    }
                                    if (toServiceMsg.getAttributes().containsKey(com.tencent.qalsdk.base.a.aS)) {
                                        fromServiceMsg.addAttribute(com.tencent.qalsdk.base.a.aS, toServiceMsg.getAttribute(com.tencent.qalsdk.base.a.aS));
                                    }
                                    if (toServiceMsg.getAttributes().containsKey(com.tencent.qalsdk.base.a.aT)) {
                                        fromServiceMsg.addAttribute(com.tencent.qalsdk.base.a.aT, toServiceMsg.getAttribute(com.tencent.qalsdk.base.a.aT));
                                    }
                                    fromServiceMsg.addAttribute(com.tencent.qalsdk.base.a.aR, Boolean.valueOf(z));
                                    MsfSdkUtils.addFromMsgProcessName(v.n, fromServiceMsg);
                                    this.b.o.a(null, fromServiceMsg);
                                    this.b.a.a(CloseConnReason.appCall);
                                } catch (Exception e2) {
                                    if (QLog.isColorLevel()) {
                                        QLog.e("MSF.C.NetConnTag", 2, "send offlineMsg to app error " + e2.toString());
                                    }
                                }
                            }
                        }
                    }
                } catch (Throwable th22) {
                    if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, th22.toString(), th22);
                    }
                }
                Thread.sleep(10);
            }
        }

        private void a(ToServiceMsg toServiceMsg, String str, String str2) {
            this.b.a(toServiceMsg.getRequestSsoSeq());
        }
    }

    public o(j jVar) {
        this.o = jVar;
        HandlerThread handlerThread = new HandlerThread("mergehandle", 5);
        handlerThread.start();
        this.T = new Handler(handlerThread.getLooper());
        this.V = jVar.i();
        this.a = new qalsdk.o(jVar);
        this.t.setName("MsfCoreMsgSender");
        try {
            String[] c = d.c();
            if (c != null) {
                this.c = Integer.parseInt(c[0]);
                this.d = Integer.parseInt(c[1]);
                this.g = Integer.parseInt(c[2]);
                this.f = Integer.parseInt(c[3]);
                if (QLog.isColorLevel()) {
                    QLog.d("MSF.C.NetConnTag", 2, "maxDelaySize: " + this.d + " mergeDuration: " + this.g + " globaldelayTime: " + this.f + " delayWaitSendCount: " + this.c);
                }
            }
        } catch (Throwable e) {
            QLog.e("MSF.C.NetConnTag", 1, " " + e, e);
        }
    }

    public boolean a(Context context) {
        try {
            if (this.o.o() == 1) {
                b(context);
            }
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "loadTestServerConfig error " + e);
            }
        }
        try {
            this.n.init(context, false);
        } catch (UnsatisfiedLinkError e2) {
            QLog.e("MSF.C.NetConnTag", "sender init codecWrapperImpl init exception:" + e2.getMessage());
        }
        this.t.start();
        try {
            String config = l.a().getConfig(q.e);
            if (config != null && config.length() > 0) {
                String config2 = l.a().getConfig(q.f);
                if (config2 == null || config2.length() <= 0) {
                    l.a().setConfig(q.f, String.valueOf(System.currentTimeMillis()));
                    c(config);
                } else {
                    if (System.currentTimeMillis() - Long.parseLong(config2) > 259200000) {
                        Thread pVar = new p(this);
                        pVar.setName("resetLogLevelThread");
                        pVar.start();
                    } else {
                        c(config);
                    }
                }
            }
        } catch (Exception e3) {
            QLog.e("MSF.C.NetConnTag", 1, "set logLevel error " + e3);
        }
        return true;
    }

    private void c(Context context) {
        Exception e;
        Throwable th;
        if (com.tencent.qalsdk.base.a.au != null && com.tencent.qalsdk.base.a.au.length > 0) {
            this.a.a(com.tencent.qalsdk.base.a.au);
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "set BaseConstants loginMergeTSAddress " + Arrays.toString(com.tencent.qalsdk.base.a.au));
            }
        } else if (com.tencent.qalsdk.base.a.av == null || com.tencent.qalsdk.base.a.av.length <= 0) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/tencent/" + context.getPackageName().replace(".", "/") + "/lmtestserver.txt");
            Properties properties = new Properties();
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    properties.load(fileInputStream);
                    String property = properties.getProperty("wifiserver");
                    String property2 = properties.getProperty("xgserver");
                    com.tencent.qalsdk.base.a.au = property.split("\\|");
                    com.tencent.qalsdk.base.a.av = property2.split("\\|");
                    this.a.a(com.tencent.qalsdk.base.a.au);
                    if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, "set socketEngineFactory LmassignedSsoAddress " + Arrays.toString(com.tencent.qalsdk.base.a.au));
                    }
                    this.a.b(com.tencent.qalsdk.base.a.av);
                    if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, "set socketEngineFactory LmassignedSsoAddress " + Arrays.toString(com.tencent.qalsdk.base.a.av));
                    }
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        e.printStackTrace();
                        try {
                            fileInputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            fileInputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                        throw th;
                    }
                }
            } catch (Exception e5) {
                e = e5;
                fileInputStream = null;
                e.printStackTrace();
                fileInputStream.close();
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                fileInputStream.close();
                throw th;
            }
        } else {
            this.a.b(com.tencent.qalsdk.base.a.av);
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "set BaseConstants loginMergeTSAddress " + Arrays.toString(com.tencent.qalsdk.base.a.au));
            }
        }
    }

    public void b(Context context) {
        FileInputStream fileInputStream;
        Exception e;
        Throwable th;
        String str = com.tencent.qalsdk.base.a.bd;
        c a;
        if (str == null || str.equals("")) {
            File file = new File(Environment.getExternalStorageDirectory().getPath() + "/tencent/" + context.getPackageName().replace(".", "/") + "/testserver");
            Properties properties = new Properties();
            FileOutputStream fileOutputStream = null;
            try {
                if (file.exists()) {
                    fileInputStream = new FileInputStream(file);
                    try {
                        properties.load(fileInputStream);
                        fileInputStream.close();
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            e.printStackTrace();
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                            if (null != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e32) {
                                    e32.printStackTrace();
                                }
                            }
                            str = properties.getProperty("server");
                            if (str != null) {
                                return;
                            }
                            return;
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            if (null != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e42) {
                                    e42.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                }
                fileInputStream = null;
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e322) {
                        e322.printStackTrace();
                    }
                }
                if (null != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3222) {
                        e3222.printStackTrace();
                    }
                }
            } catch (Exception e5) {
                e = e5;
                fileInputStream = null;
                e.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (null != null) {
                    fileOutputStream.close();
                }
                str = properties.getProperty("server");
                if (str != null) {
                    return;
                }
                return;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (null != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
            str = properties.getProperty("server");
            if (str != null && str.trim().length() > 0) {
                a = c.a(str);
                if (a.c() != null && a.d() > 0) {
                    str = str.trim().replace(" ", "");
                    this.a.a(str);
                    if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, "set TestServer Address " + str);
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        a = c.a(str);
        if (a.c() != null && a.d() > 0) {
            this.a.a(str);
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "set BaseConstantsTestServer Address " + str);
            }
        }
    }

    private void c(String str) {
    }

    public boolean a() {
        return this.O.isEmpty();
    }

    public int a(ToServiceMsg toServiceMsg) {
        if (toServiceMsg.getRequestSsoSeq() == -1) {
            toServiceMsg.setRequestSsoSeq(j.f());
        }
        toServiceMsg.addAttribute(com.tencent.qalsdk.base.a.aO, Long.valueOf(System.currentTimeMillis()));
        if (toServiceMsg.isNeedCallback()) {
            Object a;
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "add " + toServiceMsg + " to send");
            }
            this.O.put(Integer.valueOf(toServiceMsg.getRequestSsoSeq()), toServiceMsg);
            toServiceMsg.getAttributes().put(b, Integer.valueOf(U.incrementAndGet()));
            if (toServiceMsg.getTimeout() == -1) {
                toServiceMsg.setTimeout(com.tencent.qalsdk.base.a.ap);
                a = this.o.l.a(toServiceMsg, toServiceMsg.getTimeout());
            } else {
                a = this.o.l.a(toServiceMsg, toServiceMsg.getTimeout());
            }
            toServiceMsg.addAttribute(v.j, a);
        }
        try {
            this.R.a((Object) toServiceMsg);
        } catch (Throwable e) {
            QLog.d("MSF.C.NetConnTag", 1, "inset heartbeatMsg error. " + e, e);
        }
        return toServiceMsg.getRequestSsoSeq();
    }

    public byte[] b() {
        return this.aa;
    }

    public void a(byte[] bArr) {
        this.aa = bArr;
    }

    public void c() {
    }

    public boolean a(ToServiceMsg toServiceMsg, FromServiceMsg fromServiceMsg) {
        long longValue;
        if (toServiceMsg != null && toServiceMsg.isNeedRemindSlowNetwork()) {
            m.a(qalsdk.m.b.MessageTimeout);
        }
        toServiceMsg.addAttribute(com.tencent.qalsdk.base.a.am, qalsdk.o.C);
        fromServiceMsg.addAttribute(com.tencent.qalsdk.base.a.am, qalsdk.o.C);
        if (toServiceMsg.getAttributes().containsKey(com.tencent.qalsdk.base.a.al)) {
            fromServiceMsg.addAttribute(com.tencent.qalsdk.base.a.al, toServiceMsg.getAttribute(com.tencent.qalsdk.base.a.al));
        }
        if (toServiceMsg.getAttributes().containsKey(com.tencent.qalsdk.base.a.aK)) {
            longValue = ((Long) toServiceMsg.getAttribute(com.tencent.qalsdk.base.a.aK)).longValue();
        } else {
            longValue = 0;
        }
        long currentTimeMillis = System.currentTimeMillis();
        long j = 0;
        if (toServiceMsg.getAttributes().containsKey(com.tencent.qalsdk.base.a.aO)) {
            j = ((Long) toServiceMsg.getAttribute(com.tencent.qalsdk.base.a.aO)).longValue();
        }
        if (j == 0 || currentTimeMillis - j <= toServiceMsg.getTimeout() || currentTimeMillis - j <= this.o.e.e()) {
            if (longValue != 0 && longValue <= this.a.o().f()) {
                QLog.d("MSF.C.NetConnTag", 1, "found timeout msg " + toServiceMsg + " before connSucc.");
            } else if (toServiceMsg.getMsfCommand() == MsfCommand._msf_HeartbeatAlive) {
                QLog.d("MSF.C.NetConnTag", 1, "found timeout heartalive msg " + toServiceMsg.getRequestSsoSeq());
                a(fromServiceMsg, toServiceMsg);
                return false;
            } else {
                synchronized (this) {
                    if (!this.u.get() || System.currentTimeMillis() - this.w >= ((long) (d.m() + 120000))) {
                        j = System.currentTimeMillis();
                        if (j - this.w > ((long) d.m())) {
                            if (toServiceMsg.getRequestSsoSeq() == A) {
                                A = -1;
                                QLog.i("MSF.C.NetConnTag", 1, "[Sender] found conn  first msg timeout ,wifi may fake" + toServiceMsg);
                                this.o.d.a.g.c();
                                this.o.d.a.h();
                            }
                            if (toServiceMsg.getRequestSsoSeq() == z) {
                                z = -1;
                                QLog.d("MSF.C.NetConnTag", 1, "[Sender]found first msg screen on timeout try close conn " + toServiceMsg);
                                this.o.d.a.a(CloseConnReason.closeForScrrenOnFirstMsgTimeout);
                                q();
                                return true;
                            }
                            o();
                        } else {
                            QLog.d("MSF.C.NetConnTag", 1, "found timeout msg check time: timenow = " + j + " firstSendHeartBeatTime = " + this.w + " ConfigManager.getHeartBeatTimeInterval() = " + d.m());
                        }
                    } else if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, "sendingHeartBeat: " + this.u + "net detect has started, no need sendHeartbeat");
                    }
                }
            }
            if (toServiceMsg.getMsfCommand() == MsfCommand._msf_HeartbeatAlive) {
                return false;
            }
            try {
                if (this.a.a()) {
                    Object obj = 1;
                    for (Object obj2 : Z) {
                        if (!TextUtils.isEmpty(obj2) && toServiceMsg != null && obj2.equals(toServiceMsg.getServiceCmd())) {
                            obj = null;
                            break;
                        }
                    }
                    if (obj != null && m.incrementAndGet() >= d.h()) {
                        QLog.d("MSF.C.NetConnTag", 1, "Continue wait resp for bus packets ,try close conn");
                        m.set(0);
                        this.o.d.a.a(CloseConnReason.continueWaitRspTimeout);
                        q();
                    }
                }
            } catch (Exception e) {
                QLog.d("MSF.C.NetConnTag", 1, " Continue wait resp for bus packets ,try close conn failed: " + e);
            }
            return true;
        } else if (j < this.a.o().f()) {
            return true;
        } else {
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "xiaomi 2s: " + toServiceMsg.getRequestSsoSeq() + " msg timeout , addtoqueueTime is " + (currentTimeMillis - j) + ",try close conn");
            }
            this.o.d.a.a(CloseConnReason.closeByNetDetectTooLongForPhoneSleep);
            q();
            return true;
        }
    }

    private void o() {
        if (this.a.a()) {
            byte[] bArr = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 4};
            ToServiceMsg toServiceMsg = new ToServiceMsg("", "0", com.tencent.qalsdk.base.a.X);
            toServiceMsg.setMsfCommand(MsfCommand._msf_HeartbeatAlive);
            toServiceMsg.setRequestSsoSeq(j.f());
            toServiceMsg.setAppId(this.o.i());
            toServiceMsg.putWupBuffer(bArr);
            toServiceMsg.setTimeout(d.f());
            a(toServiceMsg);
            this.v++;
            if (QLog.isDevelopLevel()) {
                QLog.d("MSF.C.NetConnTag", 4, "send Heartbeat msg ok");
            }
            this.w = System.currentTimeMillis();
            this.u.set(true);
            return;
        }
        if (QLog.isDevelopLevel()) {
            QLog.d("MSF.C.NetConnTag", 4, "conn is also closed.not need send heartbeat msg");
        }
        q();
    }

    private void p() {
        if (this.a.a()) {
            byte[] bArr = new byte[]{(byte) 0, (byte) 0, (byte) 0, (byte) 4};
            ToServiceMsg toServiceMsg = new ToServiceMsg("", "0", com.tencent.qalsdk.base.a.X);
            toServiceMsg.setMsfCommand(MsfCommand._msf_HeartbeatAlive);
            toServiceMsg.setRequestSsoSeq(j.f());
            toServiceMsg.setAppId(this.o.i());
            toServiceMsg.putWupBuffer(bArr);
            toServiceMsg.setTimeout(TracerConfig.LOG_FLUSH_DURATION);
            a(toServiceMsg);
            this.v = d.g();
            if (QLog.isDevelopLevel()) {
                QLog.d("MSF.C.NetConnTag", 4, "send fast net detect Heartbeat msg ok");
            }
            this.w = System.currentTimeMillis();
            this.u.set(true);
            return;
        }
        if (QLog.isDevelopLevel()) {
            QLog.d("MSF.C.NetConnTag", 4, "conn is also closed.not need send heartbeat msg");
        }
        q();
    }

    public void a(FromServiceMsg fromServiceMsg, ToServiceMsg toServiceMsg) {
        if (fromServiceMsg.getBusinessFailCode() == 1002) {
            QLog.i("MSF.C.NetConnTag", 4, "handleHeartbeat wait " + toServiceMsg + " timeout.");
            if (!this.a.a()) {
                if (QLog.isColorLevel()) {
                    QLog.d("MSF.C.NetConnTag", 2, "conn is also closed.");
                }
                q();
                return;
            } else if (this.v >= d.g()) {
                try {
                    if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, "Heartbeat continueTimeoutCount is " + this.v + ",try close conn");
                    }
                    this.o.d.a.a(CloseConnReason.closeByNetDetectFailed);
                    q();
                    return;
                } catch (Throwable e) {
                    if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, e.toString(), e);
                        return;
                    }
                    return;
                }
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - this.w > ((long) d.g()) * d.f()) {
                    if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, "timenow - firstSendHeartBeatTime is " + (currentTimeMillis - this.w) + ",try close conn");
                    }
                    this.o.d.a.a(CloseConnReason.continueWaitRspTimeout);
                    q();
                    return;
                }
                o();
                return;
            }
        }
        if (QLog.isColorLevel()) {
            QLog.d("MSF.C.NetConnTag", 2, "recv heart resp.now conn is alive.");
        }
        q();
    }

    private void q() {
        this.v = 0;
        this.u.set(false);
    }

    public int b(ToServiceMsg toServiceMsg) {
        if (toServiceMsg == null) {
            return -1;
        }
        FromServiceMsg a;
        if (this.o.l()) {
            a = k.a(toServiceMsg);
            a.setBusinessFail(com.tencent.qalsdk.base.a.n, "MSF is suspeded.");
            this.o.a(toServiceMsg, a);
            return toServiceMsg.getRequestSsoSeq();
        } else if (toServiceMsg.getAppId() <= 0 && toServiceMsg.getMsfCommand() != MsfCommand.openConn) {
            a = k.a(toServiceMsg);
            a.setBusinessFail(1007, "msg appid is " + toServiceMsg.getAppId());
            this.o.a(toServiceMsg, a);
            return toServiceMsg.getRequestSsoSeq();
        } else if (ad.get()) {
            a = k.a(toServiceMsg);
            a.setBusinessFail(com.tencent.qalsdk.base.a.r, "error");
            this.o.a(toServiceMsg, a);
            return toServiceMsg.getRequestSsoSeq();
        } else {
            if (this.V != toServiceMsg.getAppId()) {
                this.V = toServiceMsg.getAppId();
            }
            if (toServiceMsg.getRequestSsoSeq() == -1) {
                toServiceMsg.setRequestSsoSeq(j.f());
            }
            if (!toServiceMsg.getAttributes().containsKey(com.tencent.qalsdk.base.a.aH)) {
                toServiceMsg.addAttribute(com.tencent.qalsdk.base.a.aH, Long.valueOf(System.currentTimeMillis()));
            }
            toServiceMsg.addAttribute(com.tencent.qalsdk.base.a.aO, Long.valueOf(System.currentTimeMillis()));
            if (!this.i && this.j) {
                this.h = SystemClock.elapsedRealtime();
                this.i = true;
            }
            if ((toServiceMsg.getServiceCmd().equals(com.tencent.qalsdk.base.a.M) || toServiceMsg.getServiceCmd().equals(ai.e)) && toServiceMsg.getMsfCommand().equals(MsfCommand._msf_RegPush) && this.j) {
                this.h = SystemClock.elapsedRealtime();
                this.i = true;
            }
            return c(toServiceMsg);
        }
    }

    public static byte[] b(byte[] bArr) {
        int length = bArr.length;
        ByteBuffer allocate = ByteBuffer.allocate(length + 4);
        allocate.putInt(length + 4).put(bArr).flip();
        return allocate.array();
    }

    private int c(ToServiceMsg toServiceMsg) {
        if (toServiceMsg.isNeedCallback()) {
            Object a;
            this.O.put(Integer.valueOf(toServiceMsg.getRequestSsoSeq()), toServiceMsg);
            toServiceMsg.getAttributes().put(b, Integer.valueOf(U.incrementAndGet()));
            if (toServiceMsg.getTimeout() == -1) {
                toServiceMsg.setTimeout(com.tencent.qalsdk.base.a.ap);
                a = this.o.l.a(toServiceMsg, toServiceMsg.getTimeout());
            } else {
                a = this.o.l.a(toServiceMsg, toServiceMsg.getTimeout());
            }
            toServiceMsg.addAttribute(v.j, a);
        } else if (toServiceMsg.getServiceCmd().equals(com.tencent.qalsdk.base.a.J)) {
            this.o.l.a(toServiceMsg.getRequestSsoSeq(), 30000);
        }
        if (this.R.offer(toServiceMsg)) {
            if (this.Q.contains(Integer.valueOf(toServiceMsg.getRequestSsoSeq()))) {
                this.Q.remove(Integer.valueOf(toServiceMsg.getRequestSsoSeq()));
            }
            if (QLog.isDevelopLevel()) {
                QLog.d("MSF.C.NetConnTag", 4, "add " + toServiceMsg + " to sendQueue");
            }
        } else {
            QLog.d("MSF.C.NetConnTag", 1, "error, add " + toServiceMsg + " to send queue is full! size: " + this.R.size());
            if (toServiceMsg.getServiceCmd().equals(com.tencent.qalsdk.base.a.J)) {
                ArrayList arrayList = (ArrayList) this.Q.remove(Integer.valueOf(toServiceMsg.getRequestSsoSeq()));
                if (arrayList != null) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        ToServiceMsg toServiceMsg2 = (ToServiceMsg) it.next();
                        FromServiceMsg a2 = k.a(toServiceMsg2);
                        a2.setBusinessFail(1008, "send queue is full!");
                        this.o.a(toServiceMsg2, a2);
                    }
                }
            } else {
                FromServiceMsg a3 = k.a(toServiceMsg);
                a3.setBusinessFail(1008, "send queue is full!");
                this.o.a(toServiceMsg, a3);
            }
        }
        if (!B.get() && e.b) {
            B.set(true);
            z = toServiceMsg.getRequestSsoSeq();
        }
        if (!C.get()) {
            C.set(true);
            A = toServiceMsg.getRequestSsoSeq();
        }
        return toServiceMsg.getRequestSsoSeq();
    }

    public ToServiceMsg d() {
        return null;
    }

    public void a(String str) {
        CodecWarpper.nateiveRemoveAccountKey(str);
    }

    public void a(CloseConnReason closeConnReason) {
        this.a.a(closeConnReason);
    }

    public ToServiceMsg a(int i) {
        ToServiceMsg toServiceMsg = (ToServiceMsg) this.O.get(Integer.valueOf(i));
        if (toServiceMsg != null) {
            Runnable runnable = (Runnable) toServiceMsg.getAttributes().remove(v.j);
            if (!toServiceMsg.isHttpReq()) {
                this.O.remove(Integer.valueOf(i));
                this.o.l.b().removeCallbacks(runnable);
            }
        }
        return toServiceMsg;
    }

    public ConcurrentHashMap<Integer, ToServiceMsg> e() {
        return this.O;
    }

    public ConcurrentHashMap<Integer, ArrayList<Integer>> f() {
        return this.P;
    }

    public static void a(String str, boolean z) {
        if (z) {
            L.add(str);
        } else {
            L.remove(str);
        }
        CodecWarpper.setUseSimpleHead(str, z);
    }

    public static void g() {
        Iterator it = L.iterator();
        while (it.hasNext()) {
            CodecWarpper.setUseSimpleHead((String) it.next(), false);
        }
        L.clear();
    }

    public void c(byte[] bArr) {
        this.n.onReceData(bArr);
    }

    public String h() {
        return this.W;
    }

    public void i() {
        this.W = "0";
    }

    public int j() {
        return this.V;
    }

    public void a(HashMap<String, ArrayList<byte[]>> hashMap) {
        this.M.add(hashMap);
        this.N = true;
        if (QLog.isColorLevel()) {
            QLog.d("MSF.C.NetConnTag", 2, "add waitReportData " + this.M.size());
        }
    }

    public void k() {
        Iterator it = this.O.entrySet().iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (entry != null) {
                ToServiceMsg toServiceMsg = (ToServiceMsg) entry.getValue();
                if (!(toServiceMsg == null || this.R.contains(toServiceMsg))) {
                    Object obj;
                    if (toServiceMsg.isFastResendEnabled()) {
                        obj = 1;
                    } else {
                        int i;
                        for (Object equals : com.tencent.qalsdk.base.a.aG) {
                            if (toServiceMsg.getServiceCmd().equals(equals)) {
                                i = 1;
                                break;
                            }
                        }
                        obj = null;
                    }
                    if (obj != null) {
                        FromServiceMsg constructResponse = MsfSdkUtils.constructResponse(toServiceMsg, com.tencent.qalsdk.base.a.t, "", null);
                        constructResponse.setBusinessFail(com.tencent.qalsdk.base.a.t);
                        this.o.a(toServiceMsg, constructResponse);
                        it.remove();
                    }
                }
            }
        }
    }

    public static int l() {
        return X;
    }

    public static void b(int i) {
        X = i;
    }

    public static String m() {
        return Y;
    }

    public static void b(String str) {
        Y = str;
    }
}
