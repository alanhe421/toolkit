package qalsdk;

import android.content.Context;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.qalsdk.base.CloseConnReason;
import com.tencent.qalsdk.base.remote.FromServiceMsg;
import com.tencent.qalsdk.base.remote.ToServiceMsg;
import com.tencent.qalsdk.core.NetConnInfoCenter;
import com.tencent.qalsdk.core.c;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.core.m;
import com.tencent.qalsdk.sdk.MsfCommand;
import com.tencent.qalsdk.util.QLog;
import com.tencent.upload.impl.TaskManager;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: SocketEngineFactory */
public class o {
    public static String C = "";
    private static String E = "UTF-8";
    public static final String a = "MSF.C.NetConnTag";
    public static final int b = -10008;
    public static final int c = 300000;
    public static final int e = 302;
    boolean A = false;
    long B = 0;
    private n D = null;
    Runnable d = null;
    j f;
    public ag g;
    String h = "";
    String[] i = null;
    String[] j = null;
    long k = 0;
    ArrayList<g> l = new ArrayList();
    long m = 0;
    long n = 0;
    long o = 0;
    public AtomicBoolean p = new AtomicBoolean(false);
    public int q = 2;
    ArrayList<a> r = new ArrayList();
    ArrayList<a> s = new ArrayList();
    Thread t = null;
    long u = 0;
    boolean v = false;
    long w = 0;
    AtomicBoolean x = new AtomicBoolean(false);
    int y = 0;
    boolean z = false;

    /* compiled from: SocketEngineFactory */
    public class a {
        public String a = "";
        public byte b = (byte) 1;
        public String c = "";
        final /* synthetic */ o d;

        public a(o oVar) {
            this.d = oVar;
        }

        public void a() {
            this.b = (byte) 1;
            this.c = "";
            String str = this.a;
            if (str != null) {
                String trim = str.split(":")[0].trim();
                int parseInt = Integer.parseInt(str.split(":")[1].trim());
                this.b = (byte) 2;
                try {
                    if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, "start send checkNetConnectByConnectSSO server:" + trim + " port:" + parseInt);
                    }
                    Socket socket = new Socket();
                    SocketAddress inetSocketAddress = new InetSocketAddress(trim, parseInt);
                    socket.setSoTimeout(Constants.ERRORCODE_UNKNOWN);
                    socket.setTcpNoDelay(true);
                    socket.setKeepAlive(true);
                    socket.connect(inetSocketAddress, Constants.ERRORCODE_UNKNOWN);
                    this.b = (byte) 3;
                    if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, "checkNetConnectByConnectSSO connect server:" + trim + " port:" + parseInt + " success");
                    }
                    socket.close();
                } catch (Throwable th) {
                    if (th != null) {
                        this.c = th.toString().toLowerCase();
                    }
                    if (this.c.indexOf("timeoutexception") > -1 || this.c.indexOf(") after") > -1) {
                        this.b = (byte) 5;
                    } else {
                        this.b = (byte) 4;
                    }
                    if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, "checkNetConnectByConnectSSO Throwable connect server:" + trim + " port:" + parseInt + " failed" + th, th);
                    }
                }
            }
        }
    }

    public o(j jVar) {
        this.f = jVar;
        this.D = new n(jVar, true);
        this.g = new ac(jVar);
    }

    private int b(int i, int i2, String str, String str2, MsfCommand msfCommand, String str3, int i3, byte[] bArr) throws Exception {
        if (!this.D.r.get()) {
            g();
        }
        return this.D.a(i, i2, i3, str3, str, str2, msfCommand, bArr);
    }

    public boolean a() {
        return this.D.r.get();
    }

    public boolean b() {
        return this.D.s.get();
    }

    public boolean c() {
        return this.D.t.get();
    }

    public void a(String str) {
        if (str != null) {
            this.h = str;
            com.tencent.qalsdk.base.a.at = true;
        }
    }

    public void a(String[] strArr) {
        if (strArr != null) {
            this.i = strArr;
            com.tencent.qalsdk.base.a.at = false;
        }
    }

    public void b(String[] strArr) {
        if (strArr != null) {
            this.j = strArr;
            com.tencent.qalsdk.base.a.at = false;
        }
    }

    public void a(FromServiceMsg fromServiceMsg, ToServiceMsg toServiceMsg) {
        if (this.m != 0) {
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "firstResponseGetted getted Report now");
            }
            long j = -1;
            if (this.n >= this.m) {
                j = this.n - this.m;
            }
            if (fromServiceMsg.getAttribute(com.tencent.qalsdk.base.a.aL) == null) {
                if (QLog.isColorLevel()) {
                    QLog.d("MSF.C.NetConnTag", 2, "from msg TIMESTAMP_NET2MSF not find return");
                }
                d();
                return;
            }
            long longValue = ((Long) fromServiceMsg.getAttribute(com.tencent.qalsdk.base.a.aL)).longValue() - this.n;
            if (toServiceMsg.getAttribute(com.tencent.qalsdk.base.a.aK) != null) {
                longValue = ((Long) toServiceMsg.getAttribute(com.tencent.qalsdk.base.a.aK)).longValue();
                long longValue2 = ((Long) fromServiceMsg.getAttribute(com.tencent.qalsdk.base.a.aL)).longValue() - longValue;
                if ((longValue2 < 0 || longValue == 0) && QLog.isColorLevel()) {
                    QLog.d("MSF.C.NetConnTag", 2, "nFirstPacketTimeElapse:" + longValue2 + " reset 0 now TIMESTAMP_NET2MSF:" + this.f.n.format(fromServiceMsg.getAttribute(com.tencent.qalsdk.base.a.aL)) + " TIMESTAMP_MSF2NET:" + this.f.n.format(toServiceMsg.getAttribute(com.tencent.qalsdk.base.a.aK)));
                }
            } else if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "to msg TIMESTAMP_MSF2NET not find set nFirstPacketTimeElapse to 0");
            }
            if (fromServiceMsg.getServiceCmd().equals(com.tencent.qalsdk.base.a.G)) {
            }
            if (this.l.size() > 0) {
                g gVar = (g) this.l.get(this.l.size() - 1);
                gVar.m = 1;
                gVar.l = 1;
                this.f.d().a(1, j, this.l, this.q);
            }
            d();
            NetConnInfoCenter.onRecvFirstResp();
        }
    }

    public void d() {
        this.o = 0;
        this.m = 0;
        this.n = 0;
        this.l.clear();
        this.q = 2;
        this.p.set(false);
        this.f.d().c();
        if (this.d != null) {
            this.f.m().a(this.d);
            this.d = null;
        }
    }

    public void e() {
        int i = 2;
        if (this.m != 0) {
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "loginConnectTimeOut Report now");
            }
            long j = -1;
            if (this.n >= this.m) {
                if (this.o == 0) {
                    if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, "loginConnectTimeOut. m_firstPacketSendTimeAfterConnect == 0");
                    }
                    d();
                    return;
                }
                j = this.n - this.m;
                this.f.d.a.g.c();
            }
            if (this.q != 1) {
                i = 3;
            }
            this.f.d().a(i, j, this.l, this.q);
            d();
            return;
        }
        if (QLog.isColorLevel()) {
            QLog.d("MSF.C.NetConnTag", 2, "loginConnectTimeOut m_startConnectTime : " + this.m + "no need Report");
        }
        d();
    }

    public void f() {
        if (this.l.size() > 0) {
            g gVar = (g) this.l.get(this.l.size() - 1);
            if (gVar != null) {
                gVar.e = v.recvSsoDataError;
                gVar.d = false;
                gVar.l = 0;
                gVar.m = 1;
            }
            h();
        }
    }

    protected boolean g() throws Exception {
        boolean a;
        if ((this.i != null && this.i.length > 0) || (this.j != null && this.j.length > 0)) {
            try {
                if (QLog.isColorLevel()) {
                    QLog.d("MSF.C.NetConnTag", 2, "start to connect lmassigned server " + Arrays.toString(this.i) + " " + Arrays.toString(this.j));
                }
                this.m = System.currentTimeMillis();
                this.n = this.m;
                CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
                CopyOnWriteArrayList copyOnWriteArrayList2 = new CopyOnWriteArrayList();
                if (this.i != null) {
                    for (String a2 : this.i) {
                        copyOnWriteArrayList2.add(c.a(a2));
                    }
                }
                if (this.j != null) {
                    for (String a22 : this.j) {
                        copyOnWriteArrayList.add(c.a(a22));
                    }
                }
                a = a(copyOnWriteArrayList, copyOnWriteArrayList2, new ArrayList(), false);
                if (a) {
                    return a;
                }
            } catch (Throwable e) {
                if (QLog.isColorLevel()) {
                    QLog.d("MSF.C.NetConnTag", 2, "conn assigned server " + Arrays.toString(this.i) + " " + Arrays.toString(this.j) + " error " + e, e);
                }
            }
        }
        if (this.h.length() > 0) {
            try {
                long elapsedRealtime = SystemClock.elapsedRealtime();
                this.m = System.currentTimeMillis();
                this.n = this.m;
                g gVar = new g();
                gVar.b = this.h;
                gVar.g = m.n();
                gVar.j = 0;
                gVar.k = 0;
                gVar.l = 0;
                gVar.m = 0;
                a(c.a(this.h), true, gVar);
                new ArrayList().add(gVar);
                elapsedRealtime = SystemClock.elapsedRealtime() - elapsedRealtime;
                return true;
            } catch (Throwable e2) {
                if (QLog.isColorLevel()) {
                    QLog.d("MSF.C.NetConnTag", 2, "conn assigned server " + this.h + " error " + e2, e2);
                }
                return false;
            }
        }
        long elapsedRealtime2 = SystemClock.elapsedRealtime();
        ArrayList arrayList = new ArrayList();
        this.m = System.currentTimeMillis();
        this.n = this.m;
        this.f.d().b();
        Object obj;
        if (this.f.h().k().size() > 0 || this.f.h().l().size() > 0) {
            long elapsedRealtime3 = SystemClock.elapsedRealtime() - elapsedRealtime2;
            a = a(this.f.h().k(), this.f.h().l(), arrayList, false);
            obj = 1;
        } else {
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "sso list is null.");
            }
            a = false;
            obj = null;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            g gVar2 = (g) it.next();
            if (gVar2.e == v.connError_unreachable || gVar2.e == v.connError_refused || gVar2.e == v.connError_permission || gVar2.e == v.connError_unresolved || gVar2.e == v.connError_unknownhost || gVar2.e == v.connError_noroute || gVar2.e == v.recvSsoDataError) {
                d();
            } else {
                if (this.m == 0) {
                    this.m = gVar2.i;
                    this.n = this.m;
                }
                if (this.d == null) {
                    this.d = this.f.m().a(this, (long) TaskManager.IDLE_PROTECT_TIME);
                }
                this.l.add(gVar2);
            }
        }
        if (a) {
            this.f.d().c();
            NetConnInfoCenter.checkConnInfo(this.f.u, true);
            if (!m.d()) {
                m.a();
            }
            return true;
        }
        elapsedRealtime2 = SystemClock.elapsedRealtime() - elapsedRealtime2;
        if (m.d() && r1 != null) {
            h();
        }
        NetConnInfoCenter.onOepnConnAllFailed();
        elapsedRealtime = System.currentTimeMillis();
        StringBuffer stringBuffer;
        c cVar;
        if (m.e() || (!m.d() && m.l() == null)) {
            if (elapsedRealtime - d.q >= BuglyBroadcastRecevier.UPLOADLIMITED) {
                return a;
            }
            stringBuffer = new StringBuffer();
            it = this.f.h().k().iterator();
            while (it.hasNext()) {
                cVar = (c) it.next();
                if (cVar != null) {
                    stringBuffer.append(cVar.toString() + VoiceWakeuperAidl.PARAMS_SEPARATE);
                }
            }
            d.q = 0;
            return a;
        } else if (elapsedRealtime - d.r >= BuglyBroadcastRecevier.UPLOADLIMITED) {
            return a;
        } else {
            stringBuffer = new StringBuffer();
            it = this.f.h().l().iterator();
            while (it.hasNext()) {
                cVar = (c) it.next();
                if (cVar != null) {
                    stringBuffer.append(cVar.toString() + VoiceWakeuperAidl.PARAMS_SEPARATE);
                }
            }
            d.r = 0;
            return a;
        }
    }

    public void h() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (this.k == 0 || elapsedRealtime - this.k > Long.parseLong(d.e())) {
            this.k = elapsedRealtime;
            StringBuffer stringBuffer = new StringBuffer();
            Iterator it;
            c cVar;
            if (m.e()) {
                it = this.f.h().k().iterator();
                while (it.hasNext()) {
                    cVar = (c) it.next();
                    stringBuffer.append(cVar.b() + "//" + cVar.c() + ":" + cVar.d() + ",");
                }
            } else {
                it = this.f.h().l().iterator();
                while (it.hasNext()) {
                    cVar = (c) it.next();
                    stringBuffer.append(cVar.b() + "//" + cVar.c() + ":" + cVar.d() + ",");
                }
            }
            try {
                this.f.f.a(this.f.d.j(), this.f.j, BuglyBroadcastRecevier.UPLOADLIMITED, m.e(), stringBuffer.toString());
            } catch (Throwable e) {
                if (QLog.isColorLevel()) {
                    QLog.d("MSF.C.NetConnTag", 2, e.toString(), e);
                }
            }
        }
    }

    private String t() {
        if (VERSION.SDK_INT >= 14) {
            return System.getProperty("http.proxyHost");
        }
        return Proxy.getDefaultHost();
    }

    public void a(String str, int i) {
        Thread pVar = new p(this, str, i);
        pVar.setName("checkNetConnectBySocketThread");
        pVar.start();
    }

    public void i() {
        if (this.t == null && d.p() != 0) {
            String o = d.o();
            if (o != null) {
                this.r.clear();
                for (String str : o.split(VoiceWakeuperAidl.PARAMS_SEPARATE)) {
                    a aVar = new a(this);
                    aVar.a = str;
                    aVar.b = (byte) 1;
                    aVar.c = "";
                    this.r.add(aVar);
                }
                this.t = new q(this);
                this.t.setName("checkNetConnectByConnectSSOThread");
                this.t.start();
            } else if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "TcpdumpSSOVip is empty");
            }
        }
    }

    public void b(String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.u > 180000) {
            m();
            this.u = currentTimeMillis;
            n();
            a("3gimg.qq.com", 80);
            a("120.33.50.146", 80);
            i();
            c(str.split(":")[0].trim());
        }
    }

    private static void b(Context context) throws IOException {
        InputStream open;
        Throwable e;
        InputStream inputStream;
        OutputStream outputStream = null;
        if (QLog.isColorLevel()) {
            QLog.d("MSF.C.NetConnTag", 2, "copy tcpdump file...");
        }
        OutputStream fileOutputStream;
        try {
            open = context.getAssets().open("tcpdump");
            try {
                fileOutputStream = new FileOutputStream(context.getFilesDir().getParent() + "/txlib/tcpdump");
                try {
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = open.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    if (open != null) {
                        open.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                } catch (Exception e2) {
                    e = e2;
                    inputStream = open;
                    try {
                        if (QLog.isColorLevel()) {
                            QLog.d("MSF.C.NetConnTag", 2, "tcpdump: copyTcpdumpFile exception" + e, e);
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (fileOutputStream == null) {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        e = th;
                        open = inputStream;
                        outputStream = fileOutputStream;
                        if (open != null) {
                            open.close();
                        }
                        if (outputStream != null) {
                            outputStream.close();
                        }
                        throw e;
                    }
                } catch (Throwable th2) {
                    e = th2;
                    outputStream = fileOutputStream;
                    if (open != null) {
                        open.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    throw e;
                }
            } catch (Exception e3) {
                e = e3;
                fileOutputStream = null;
                inputStream = open;
                if (QLog.isColorLevel()) {
                    QLog.d("MSF.C.NetConnTag", 2, "tcpdump: copyTcpdumpFile exception" + e, e);
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                if (fileOutputStream == null) {
                    fileOutputStream.close();
                }
            } catch (Throwable th3) {
                e = th3;
                if (open != null) {
                    open.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
                throw e;
            }
        } catch (Exception e4) {
            e = e4;
            fileOutputStream = null;
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "tcpdump: copyTcpdumpFile exception" + e, e);
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream == null) {
                fileOutputStream.close();
            }
        } catch (Throwable th4) {
            e = th4;
            open = null;
            if (open != null) {
                open.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            throw e;
        }
    }

    public static boolean j() {
        return k() != null;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String k() {
        /*
        r8 = 2;
        r0 = 0;
        r1 = java.lang.Runtime.getRuntime();	 Catch:{ IOException -> 0x00b4 }
        r2 = "ps";
        r1 = r1.exec(r2);	 Catch:{ IOException -> 0x00b4 }
        r4 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x00b4 }
        r2 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x00b4 }
        r1 = r1.getInputStream();	 Catch:{ IOException -> 0x00b4 }
        r2.<init>(r1);	 Catch:{ IOException -> 0x00b4 }
        r4.<init>(r2);	 Catch:{ IOException -> 0x00b4 }
        r1 = 1;
        r2 = r4.readLine();	 Catch:{ IOException -> 0x00b4 }
        r3 = "\\s+";
        r5 = r2.split(r3);	 Catch:{ IOException -> 0x00b4 }
        r6 = r5.length;	 Catch:{ IOException -> 0x00b4 }
        r2 = r0;
        r9 = r0;
        r0 = r1;
        r1 = r9;
    L_0x002c:
        if (r2 >= r6) goto L_0x0041;
    L_0x002e:
        r3 = r5[r2];	 Catch:{ IOException -> 0x00b4 }
        r7 = "PID";
        r3 = r3.equalsIgnoreCase(r7);	 Catch:{ IOException -> 0x00b4 }
        if (r3 == 0) goto L_0x003a;
    L_0x0039:
        r0 = r1;
    L_0x003a:
        r3 = r1 + 1;
        r1 = r2 + 1;
        r2 = r1;
        r1 = r3;
        goto L_0x002c;
    L_0x0041:
        r1 = r4.readLine();	 Catch:{ IOException -> 0x00b4 }
        if (r1 == 0) goto L_0x00af;
    L_0x0047:
        r2 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00b4 }
        r2.<init>();	 Catch:{ IOException -> 0x00b4 }
        r3 = com.tencent.qalsdk.core.j.a();	 Catch:{ IOException -> 0x00b4 }
        r3 = r3.u;	 Catch:{ IOException -> 0x00b4 }
        r3 = r3.getFilesDir();	 Catch:{ IOException -> 0x00b4 }
        r3 = r3.getParent();	 Catch:{ IOException -> 0x00b4 }
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x00b4 }
        r3 = "/txlib/";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x00b4 }
        r3 = "tcpdump";
        r2 = r2.append(r3);	 Catch:{ IOException -> 0x00b4 }
        r2 = r2.toString();	 Catch:{ IOException -> 0x00b4 }
        r2 = r1.contains(r2);	 Catch:{ IOException -> 0x00b4 }
        if (r2 == 0) goto L_0x0041;
    L_0x0076:
        r2 = "sh -c";
        r2 = r1.contains(r2);	 Catch:{ IOException -> 0x00b4 }
        if (r2 != 0) goto L_0x0041;
    L_0x007f:
        r2 = "\\s+";
        r1 = r1.split(r2);	 Catch:{ IOException -> 0x00b4 }
        r2 = com.tencent.qalsdk.util.QLog.isColorLevel();	 Catch:{ IOException -> 0x00b4 }
        if (r2 == 0) goto L_0x00a9;
    L_0x008c:
        r2 = "MSF.C.NetConnTag";
        r3 = 2;
        r5 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x00b4 }
        r5.<init>();	 Catch:{ IOException -> 0x00b4 }
        r6 = "tcpdump uid:";
        r5 = r5.append(r6);	 Catch:{ IOException -> 0x00b4 }
        r6 = r1[r0];	 Catch:{ IOException -> 0x00b4 }
        r5 = r5.append(r6);	 Catch:{ IOException -> 0x00b4 }
        r5 = r5.toString();	 Catch:{ IOException -> 0x00b4 }
        com.tencent.qalsdk.util.QLog.d(r2, r3, r5);	 Catch:{ IOException -> 0x00b4 }
    L_0x00a9:
        r4.close();	 Catch:{ IOException -> 0x00b4 }
        r0 = r1[r0];	 Catch:{ IOException -> 0x00b4 }
    L_0x00ae:
        return r0;
    L_0x00af:
        r4.close();	 Catch:{ IOException -> 0x00b4 }
    L_0x00b2:
        r0 = 0;
        goto L_0x00ae;
    L_0x00b4:
        r0 = move-exception;
        r1 = com.tencent.qalsdk.util.QLog.isColorLevel();
        if (r1 == 0) goto L_0x00b2;
    L_0x00bb:
        r1 = "MSF.C.NetConnTag";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "Error killing tcpdump, msg=";
        r2 = r2.append(r3);
        r0 = r0.getMessage();
        r0 = r2.append(r0);
        r0 = r0.toString();
        com.tencent.qalsdk.util.QLog.d(r1, r8, r0);
        goto L_0x00b2;
        */
        throw new UnsupportedOperationException("Method not decompiled: qalsdk.o.k():java.lang.String");
    }

    public static void l() {
        if (QLog.isColorLevel()) {
            QLog.d("MSF.C.NetConnTag", 2, "tcpdump: stopTCPDump begin");
        }
        try {
            if (k() != null) {
                String readLine;
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec(new String[]{"su", "-c", "kill " + readLine}).getErrorStream()));
                while (true) {
                    readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        bufferedReader.close();
                        return;
                    } else if (QLog.isColorLevel()) {
                        QLog.d("MSF.C.NetConnTag", 2, "tcpdump kill error=" + readLine);
                    }
                }
            } else if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "tcpdump: stopTCPDump pid not find");
            }
        } catch (IOException e) {
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "tcpdump Error killing tcpdump, msg=" + e.getMessage());
            }
        }
    }

    public void m() {
        if (j()) {
            if (System.currentTimeMillis() - this.w > 120000) {
                l();
            }
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "tcpdump is running return");
                return;
            }
            return;
        }
        Thread rVar = new r(this);
        rVar.setName("tcpDumpThread");
        rVar.start();
    }

    public void c(String str) {
        Thread sVar = new s(this, str);
        sVar.setName("pingServerThread");
        sVar.start();
    }

    public void n() {
        Thread tVar = new t(this);
        tVar.setName("checkNetConnectByHttpThread");
        tVar.start();
        this.x.set(true);
    }

    private boolean u() {
        return m.e() || (!m.d() && m.l() == null);
    }

    private boolean a(CopyOnWriteArrayList<c> copyOnWriteArrayList, CopyOnWriteArrayList<c> copyOnWriteArrayList2, ArrayList<g> arrayList, boolean z) {
        boolean z2;
        Throwable e;
        if (QLog.isColorLevel()) {
            QLog.d("MSF.C.NetConnTag", 2, "NetChanged selectAndConnect...");
        }
        boolean z3 = true;
        while (z3) {
            if (QLog.isColorLevel()) {
                QLog.d("MSF.C.NetConnTag", 2, "NetChanged start connect...");
            }
            int i;
            int i2;
            g a;
            int i3;
            if (u()) {
                i = 0;
                i2 = 0;
                while (i < copyOnWriteArrayList.size()) {
                    a = a((ArrayList) arrayList, z, (c) copyOnWriteArrayList.get(i));
                    if (a.e != v.connSucc) {
                        if (a.e == v.connError_unreachable) {
                            i3 = i - 1;
                            i = i2 + 1;
                            if (i > 2) {
                                this.x.set(false);
                                return false;
                            }
                        }
                        if ((a.e == v.connError_timeout || a.e == v.connError_noroute) && i == 0 && !this.x.get()) {
                            b(a.b);
                        }
                        i3 = i;
                        i = i2;
                        if (a.h > 0) {
                            Thread.sleep((long) a.h);
                            if (!u()) {
                                if (QLog.isColorLevel()) {
                                    QLog.d("MSF.C.NetConnTag", 2, "NetChanged from wifi to mobile connect again");
                                }
                                z3 = true;
                            }
                        }
                        i2 = i;
                        i = i3 + 1;
                    } else if (!this.x.get()) {
                        return true;
                    } else {
                        this.x.set(false);
                        return true;
                    }
                }
            } else if (t() != null) {
                i = 0;
                i2 = 0;
                while (i < copyOnWriteArrayList2.size()) {
                    c cVar = (c) copyOnWriteArrayList2.get(i);
                    if (cVar.g == (byte) 1) {
                        a = a((ArrayList) arrayList, z, cVar);
                        if (a.e != v.connSucc) {
                            try {
                                if (a.e == v.connError_unreachable) {
                                    i--;
                                    i2++;
                                    if (i2 > 2) {
                                        z2 = false;
                                        break;
                                    }
                                } else if ((a.e == v.connError_timeout || a.e == v.connError_noroute) && i == 0 && !this.x.get()) {
                                    b(a.b);
                                }
                                i3 = i;
                                i = i2;
                                if (a.h > 0) {
                                    Thread.sleep((long) a.h);
                                    if (u()) {
                                        if (QLog.isColorLevel()) {
                                            QLog.d("MSF.C.NetConnTag", 2, "NetChanged from mobile to wifi connect again");
                                        }
                                        z2 = true;
                                    }
                                } else {
                                    continue;
                                }
                            } catch (Exception e2) {
                                e = e2;
                                z2 = false;
                            }
                        } else if (!this.x.get()) {
                            return true;
                        } else {
                            this.x.set(false);
                            return true;
                        }
                    }
                    i3 = i;
                    i = i2;
                    i2 = i;
                    i = i3 + 1;
                }
                z2 = false;
                i2 = 0;
                int i4 = 0;
                while (i2 < copyOnWriteArrayList2.size()) {
                    try {
                        g a2 = a((ArrayList) arrayList, z, (c) copyOnWriteArrayList2.get(i2));
                        if (a2.e != v.connSucc) {
                            if (a2.e == v.connError_unreachable) {
                                i3 = i2 - 1;
                                i2 = i4 + 1;
                                if (i2 > 2) {
                                    this.x.set(false);
                                    return false;
                                }
                            }
                            if ((a2.e == v.connError_timeout || a2.e == v.connError_noroute) && i2 == 0 && !this.x.get()) {
                                b(a2.b);
                            }
                            i3 = i2;
                            i2 = i4;
                            if (a2.h > 0) {
                                Thread.sleep((long) a2.h);
                                if (u()) {
                                    try {
                                        if (QLog.isColorLevel()) {
                                            QLog.d("MSF.C.NetConnTag", 2, "NetChanged from mobile to wifi connect again");
                                            z2 = true;
                                        } else {
                                            z2 = true;
                                        }
                                        z3 = z2;
                                    } catch (Exception e3) {
                                        e = e3;
                                        z2 = true;
                                        if (QLog.isColorLevel()) {
                                            QLog.d("MSF.C.NetConnTag", 2, "selectAndConnect error " + e, e);
                                        }
                                        z3 = z2;
                                    }
                                }
                            }
                            i4 = i2;
                            i2 = i3 + 1;
                        } else if (!this.x.get()) {
                            return true;
                        } else {
                            this.x.set(false);
                            return true;
                        }
                    } catch (Exception e4) {
                        e = e4;
                    }
                }
                z3 = z2;
            } else {
                i = 0;
                i2 = 0;
                while (i < copyOnWriteArrayList2.size()) {
                    a = a((ArrayList) arrayList, z, (c) copyOnWriteArrayList2.get(i));
                    if (a.e != v.connSucc) {
                        if (a.e == v.connError_unreachable) {
                            i3 = i - 1;
                            i = i2 + 1;
                            if (i > 2) {
                                this.x.set(false);
                                return false;
                            }
                        }
                        if ((a.e == v.connError_timeout || a.e == v.connError_noroute) && i == 0 && !this.x.get()) {
                            b(a.b);
                        }
                        i3 = i;
                        i = i2;
                        if (a.h > 0) {
                            Thread.sleep((long) a.h);
                            if (u()) {
                                if (QLog.isColorLevel()) {
                                    QLog.d("MSF.C.NetConnTag", 2, "NetChanged from mobile to wifi connect again");
                                }
                                z3 = true;
                            }
                        }
                        i2 = i;
                        i = i3 + 1;
                    } else if (!this.x.get()) {
                        return true;
                    } else {
                        this.x.set(false);
                        return true;
                    }
                }
            }
            z3 = false;
        }
        if (this.x.get() && this.z && !this.A && this.s.size() > 0) {
            this.x.set(false);
            this.z = false;
            this.A = false;
            this.s.clear();
        }
        return false;
    }

    private g a(ArrayList<g> arrayList, boolean z, c cVar) {
        g gVar = new g();
        gVar.i = System.currentTimeMillis();
        gVar.b = cVar.a();
        gVar.g = m.n();
        gVar.j = 0;
        gVar.k = 0;
        gVar.l = 0;
        gVar.m = 0;
        a(cVar, z, gVar);
        arrayList.add(gVar);
        return gVar;
    }

    private void a(c cVar, boolean z, g gVar) {
        if (cVar.b().equalsIgnoreCase(c.d)) {
            this.D.a(cVar, 2048, n.f, new h(this.f), z, gVar);
        } else {
            this.D.a(cVar, 2048, n.f, new u(this.f), z, gVar);
        }
        C = gVar.f;
    }

    public boolean a(int i, int i2, String str, String str2, MsfCommand msfCommand, String str3, int i3, byte[] bArr) throws Exception {
        int b = b(i, i2, str, str2, msfCommand, str3, i3, bArr);
        if (b == -100) {
            g();
            if (this.D.a(i, i2, i3, str3, str, str2, msfCommand, bArr) == -100) {
                return false;
            }
            if (!(this.o != 0 || this.m == 0 || this.n == 0)) {
                this.o = System.currentTimeMillis();
            }
            return true;
        } else if (b == n.b) {
            return true;
        } else {
            if (!(this.o != 0 || this.m == 0 || this.n == 0)) {
                this.o = System.currentTimeMillis();
            }
            return true;
        }
    }

    public void a(CloseConnReason closeConnReason) {
        this.D.a(closeConnReason);
    }

    public n o() {
        return this.D;
    }

    public String p() {
        return this.h;
    }

    public String[] q() {
        return this.i;
    }

    public String[] r() {
        return this.j;
    }
}
