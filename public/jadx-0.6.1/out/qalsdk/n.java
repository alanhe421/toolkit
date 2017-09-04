package qalsdk;

import android.os.Process;
import android.os.SystemClock;
import com.dynamicload.Lib.DLConstants;
import com.tencent.qalsdk.base.CloseConnReason;
import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.core.NetConnInfoCenter;
import com.tencent.qalsdk.core.c;
import com.tencent.qalsdk.core.j;
import com.tencent.qalsdk.core.m;
import com.tencent.qalsdk.core.o;
import com.tencent.qalsdk.sdk.MsfCommand;
import com.tencent.qalsdk.sdk.MsfSdkUtils;
import com.tencent.qalsdk.util.CodecWarpper;
import com.tencent.qalsdk.util.MsfSocketInputBuffer;
import com.tencent.qalsdk.util.QLog;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: SocketEngine */
public class n {
    public static final int a = -100;
    public static final int b = -200;
    public static final int f = 30000;
    public static ArrayList<String> m = new ArrayList();
    private AtomicInteger A = new AtomicInteger();
    InetSocketAddress c;
    c d;
    int e;
    public Socket g;
    OutputStream h = null;
    i i;
    a j;
    MsfSocketInputBuffer k = null;
    public int l = 0;
    AtomicLong n = new AtomicLong();
    AtomicLong o = new AtomicLong();
    long p = 0;
    boolean q = false;
    AtomicBoolean r = new AtomicBoolean();
    AtomicBoolean s = new AtomicBoolean(false);
    AtomicBoolean t = new AtomicBoolean(true);
    AtomicBoolean u = new AtomicBoolean();
    ReentrantLock v = new ReentrantLock();
    boolean w;
    j x;
    public c y;
    private String z = "";

    public boolean a() {
        return this.q;
    }

    public boolean b() {
        return this.r.get();
    }

    public boolean c() {
        return this.s.get();
    }

    public boolean d() {
        return this.t.get();
    }

    public n(j jVar, boolean z) {
        this.x = jVar;
        this.w = z;
    }

    public synchronized String e() {
        return this.z;
    }

    private synchronized void a(String str) {
        this.z = str;
    }

    public void a(c cVar, int i, int i2, i iVar, boolean z, g gVar) {
        if ((this.u.get() || !this.r.get()) && this.u.get()) {
            this.u.set(false);
            a(CloseConnReason.closeLastOpened);
        }
        this.d = cVar;
        this.i = iVar;
        this.q = z;
        this.x.d.j = false;
        v vVar = v.connStarting;
        String str = "";
        this.p = 0;
        if (this.v.tryLock(3000, TimeUnit.MILLISECONDS)) {
            long j;
            long elapsedRealtime = SystemClock.elapsedRealtime();
            long currentTimeMillis = System.currentTimeMillis();
            System.currentTimeMillis();
            o oVar;
            try {
                this.c = new InetSocketAddress(cVar.c(), cVar.d());
                StringBuilder stringBuilder = new StringBuilder();
                this.t.set(false);
                QLog.d("MSF.C.NetConnTag", 1, stringBuilder.append("try open Conn ").append(this.c).toString());
                currentTimeMillis = System.currentTimeMillis();
                this.g = new Socket();
                this.g.setSoTimeout(i2);
                this.g.setTcpNoDelay(true);
                this.g.setKeepAlive(true);
                this.g.connect(this.c, cVar.e());
                gVar.d = true;
                gVar.j++;
                this.p = System.currentTimeMillis();
                long j2 = this.p - currentTimeMillis;
                oVar = this.x.d.a;
                oVar.n += j2;
                if (j2 < 0) {
                    j2 = 0;
                }
                o.y = this.p;
                this.n.set(0);
                this.o.set(0);
                this.h = this.g.getOutputStream();
                if (!m.contains(this.h.toString())) {
                    m.add(this.h.toString());
                }
                this.k = new MsfSocketInputBuffer(this.g, i, "US-ASCII", -1);
                this.j = new a(this);
                this.j.setName("MsfCoreSocketReader");
                this.j.start();
                this.r.set(true);
                this.s.set(false);
                vVar = v.connSucc;
                str = "conSucc";
                this.y = cVar;
                o.g();
                o.p = this.y.c() + ":" + this.y.d();
                o.q = this.g.getLocalSocketAddress() + DLConstants.DEPENDENCY_PACKAGE_DIV + this.g.getLocalPort();
                if (m.e()) {
                    o.r = 1;
                } else if (m.f()) {
                    o.r = m.j() + 100;
                }
                this.y.f();
                stringBuilder.delete(0, stringBuilder.length());
                a(cVar.c());
                this.l = this.g.getPort();
                QLog.d("MSF.C.NetConnTag", 1, stringBuilder.append("open conn at ").append(this.c).append(" costTime:").append(j2).append(" configTimeout: ").append(cVar.e()).append(" localSocket:").append(this.g.getLocalAddress().getHostAddress()).append(":").append(this.g.getLocalPort()).toString());
                o.C.set(false);
                this.A.set(0);
                this.v.unlock();
                if (vVar != v.connSucc) {
                    gVar.d = false;
                    gVar.k++;
                }
                gVar.e = vVar;
                gVar.f = str;
                gVar.a = SystemClock.elapsedRealtime() - elapsedRealtime;
                j = j2;
            } catch (Throwable th) {
                currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                oVar = this.x.d.a;
                oVar.n += currentTimeMillis;
                str = th.toString().toLowerCase();
                if (m.n() == 0) {
                    vVar = v.connError_unreachable;
                    str = vVar.toString();
                    gVar.h = 5000;
                    this.s.set(true);
                } else if (str.indexOf("illegal") > -1) {
                    vVar = v.connError_illegalargument;
                    str = vVar.toString();
                    this.s.set(false);
                } else if (str.indexOf("route to host") > -1) {
                    vVar = v.connError_noroute;
                    str = vVar.toString();
                    this.s.set(true);
                } else if (str.indexOf("unreachable") > -1) {
                    vVar = v.connError_unreachable;
                    str = vVar.toString();
                    gVar.h = 5000;
                    this.s.set(true);
                } else if (str.indexOf("permission") > -1) {
                    vVar = v.connError_permission;
                    str = vVar.toString();
                    this.s.set(true);
                } else if (str.indexOf("refused") > -1) {
                    vVar = v.connError_refused;
                    str = vVar.toString();
                    this.s.set(true);
                } else if (str.indexOf("reset") > -1) {
                    vVar = v.connError_reset;
                    str = vVar.toString();
                    this.s.set(false);
                } else if (str.indexOf("timeoutexception") > -1 || str.indexOf(") after") > -1) {
                    vVar = v.connError_timeout;
                    str = vVar.toString();
                    this.s.set(false);
                } else if (str.indexOf("unknownhost") > -1) {
                    vVar = v.connError_unknownhost;
                    str = vVar.toString();
                    this.s.set(true);
                } else if (str.indexOf("unresolved") > -1) {
                    vVar = v.connError_unresolved;
                    str = vVar.toString();
                    this.s.set(true);
                } else if (str.indexOf("enotsock") > -1) {
                    vVar = v.connError_enotsock;
                    str = vVar.toString();
                    this.s.set(false);
                } else if (str.indexOf("enobufs") > -1) {
                    vVar = v.connError_enobufs;
                    str = vVar.toString();
                    this.s.set(false);
                } else if (str.indexOf("ebadf") > -1) {
                    vVar = v.connError_ebadFileNum;
                    str = vVar.toString();
                    this.s.set(false);
                } else if (str.indexOf("operation") > -1) {
                    vVar = v.connError_timeout;
                    str = vVar.toString();
                    this.s.set(false);
                } else if (str.indexOf("invalid") > -1) {
                    vVar = v.connError_invalidArgument;
                    str = vVar.toString();
                } else {
                    vVar = v.connError_unknown;
                    str = a(th);
                    if (str.length() > 200) {
                        str = str.substring(0, 200);
                    }
                }
                try {
                    j = currentTimeMillis;
                } catch (InterruptedException e) {
                    gVar.d = false;
                    gVar.k++;
                    gVar.e = v.interrupted;
                    gVar.f = e.toString();
                    gVar.a = 0;
                    return;
                }
            } finally {
                this.v.unlock();
                if (vVar != v.connSucc) {
                    gVar.d = false;
                    gVar.k++;
                }
                gVar.e = vVar;
                gVar.f = str;
                currentTimeMillis = SystemClock.elapsedRealtime() - elapsedRealtime;
                gVar.a = currentTimeMillis;
            }
            if (vVar == v.connSucc && this.w) {
                if (!m.b()) {
                    m.a(true);
                }
                this.x.d.a(null);
                this.x.d.c();
                o.m.set(0);
                if (this.g != null) {
                    this.x.v = this.g.getLocalAddress().getHostAddress() + ":" + this.g.getLocalPort();
                    NetConnInfoCenter.onConnOpened(this.c.toString(), this.x.v);
                    this.x.d.k();
                }
            } else if (vVar != v.connSucc) {
                QLog.d("MSF.C.NetConnTag", 1, "open " + this.c + " failed " + gVar.f + " costTime: " + j + " configTimeout: " + cVar.e());
            }
        }
    }

    public void a(CloseConnReason closeConnReason) {
        Throwable th;
        boolean z;
        Throwable th2;
        String str;
        boolean z2;
        this.x.d.i = false;
        this.x.d.j = false;
        this.x.d.h = 0;
        if (this.g == null) {
            QLog.d("MSF.C.NetConnTag", 1, "conn is already closed on " + closeConnReason);
            return;
        }
        String str2 = "";
        try {
            boolean z3;
            String str3;
            str2 = this.g.toString();
            if (this.v.tryLock(3000, TimeUnit.MILLISECONDS)) {
                try {
                    this.A.set(0);
                    this.x.d.a(null);
                    QLog.i("MSF.C.NetConnTag", "begin close socket by " + closeConnReason);
                    z3 = this.r.get();
                    try {
                        if (this.j != null) {
                            this.j.a.set(false);
                        }
                        try {
                            if (this.g.getInputStream() != null) {
                                this.g.getInputStream().close();
                            }
                        } catch (Throwable th3) {
                        }
                        if (this.h != null) {
                            try {
                                m.remove(this.h.toString());
                                this.h.close();
                            } catch (Exception e) {
                            }
                        }
                        this.j = null;
                        if (this.g != null) {
                            try {
                                this.g.close();
                                m.clear();
                            } catch (Throwable th4) {
                            }
                        }
                        this.g = null;
                        this.r.set(false);
                        this.i = null;
                    } catch (Throwable th5) {
                        Throwable th6 = th5;
                        z = z3;
                        th2 = th6;
                        try {
                            this.v.unlock();
                            throw th2;
                        } catch (Throwable th22) {
                            th6 = th22;
                            str = str2;
                            z2 = z;
                            th5 = th6;
                            QLog.d("MSF.C.NetConnTag", 1, "closeConn Throwable " + th5, th5);
                            QLog.d("MSF.C.NetConnTag", "close info:" + this.u.get() + ":" + z2 + ":" + this.w);
                            if (this.p > 0) {
                                this.p = 0;
                                this.x.d.a.d();
                            }
                            QLog.d("MSF.C.NetConnTag", 1, "close " + str + " by " + closeConnReason);
                            this.x.v = null;
                            this.d = null;
                            this.n.set(0);
                            this.o.set(0);
                            try {
                                CodecWarpper.onConnClose();
                            } catch (Throwable th222) {
                                QLog.d("MSF.C.NetConnTag", 1, "codec onConnClose exception " + th222, th222);
                            }
                            NetConnInfoCenter.onConnClosed(closeConnReason);
                            this.x.h().a(this.y);
                            this.y = null;
                            o.p = ":";
                            o.q = "0";
                        }
                    }
                } catch (Throwable th7) {
                    th222 = th7;
                    z = true;
                    this.v.unlock();
                    throw th222;
                }
                try {
                    this.v.unlock();
                } catch (Throwable th8) {
                    th5 = th8;
                    str3 = str2;
                    z2 = z3;
                    str = str3;
                    QLog.d("MSF.C.NetConnTag", 1, "closeConn Throwable " + th5, th5);
                    QLog.d("MSF.C.NetConnTag", "close info:" + this.u.get() + ":" + z2 + ":" + this.w);
                    if (this.p > 0) {
                        this.p = 0;
                        this.x.d.a.d();
                    }
                    QLog.d("MSF.C.NetConnTag", 1, "close " + str + " by " + closeConnReason);
                    this.x.v = null;
                    this.d = null;
                    this.n.set(0);
                    this.o.set(0);
                    CodecWarpper.onConnClose();
                    NetConnInfoCenter.onConnClosed(closeConnReason);
                    this.x.h().a(this.y);
                    this.y = null;
                    o.p = ":";
                    o.q = "0";
                }
            }
            if (this.j != null) {
                this.j.a.set(false);
            }
            QLog.d("MSF.C.NetConnTag", 1, "can not get lock for closeConn.");
            this.u.set(true);
            if (this.A.addAndGet(1) == 10) {
                Process.killProcess(Process.myPid());
            }
            z3 = true;
            str3 = str2;
            z2 = z3;
            str = str3;
        } catch (Throwable th2222) {
            th5 = th2222;
            str = str2;
            z2 = true;
            QLog.d("MSF.C.NetConnTag", 1, "closeConn Throwable " + th5, th5);
            QLog.d("MSF.C.NetConnTag", "close info:" + this.u.get() + ":" + z2 + ":" + this.w);
            if (this.p > 0) {
                this.p = 0;
                this.x.d.a.d();
            }
            QLog.d("MSF.C.NetConnTag", 1, "close " + str + " by " + closeConnReason);
            this.x.v = null;
            this.d = null;
            this.n.set(0);
            this.o.set(0);
            CodecWarpper.onConnClose();
            NetConnInfoCenter.onConnClosed(closeConnReason);
            this.x.h().a(this.y);
            this.y = null;
            o.p = ":";
            o.q = "0";
        }
        QLog.d("MSF.C.NetConnTag", "close info:" + this.u.get() + ":" + z2 + ":" + this.w);
        if (!this.u.get() && z2 && this.w) {
            if (this.p > 0) {
                this.p = 0;
                this.x.d.a.d();
            }
            QLog.d("MSF.C.NetConnTag", 1, "close " + str + " by " + closeConnReason);
            this.x.v = null;
            this.d = null;
            this.n.set(0);
            this.o.set(0);
            CodecWarpper.onConnClose();
            NetConnInfoCenter.onConnClosed(closeConnReason);
        }
        try {
            if (this.y != null && this.y.a(closeConnReason)) {
                this.x.h().a(this.y);
            }
            this.y = null;
            o.p = ":";
            o.q = "0";
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public int a(int i, int i2, int i3, String str, String str2, String str3, MsfCommand msfCommand, byte[] bArr) throws IOException {
        if (this.u.get() || !this.r.get() || this.i == null) {
            return -100;
        }
        if (msfCommand != MsfCommand.openConn) {
            byte[] a = this.i.a(this.d, str, str2, bArr);
            if (!str2.equals(a.J) || this.x.d.j) {
                this.h.write(a);
                this.h.flush();
                this.n.addAndGet((long) a.length);
                StringBuilder stringBuilder = new StringBuilder();
                if (QLog.isDevelopLevel()) {
                    QLog.d("MSF.C.NetConnTag", 1, stringBuilder.append("netSend appid:").append(i).append(" appSeq:").append(i2).append(" ssoSeq:").append(i3).append(" uin:").append(MsfSdkUtils.getShortUin(str)).append(" cmd:").append(str2).append(" len:").append(bArr.length).toString());
                } else {
                    QLog.i("MSF.C.NetConnTag", 1, stringBuilder.append("netSend ssoSeq:").append(i3).append(" appSeq:").append(i2).append(" uin:").append(MsfSdkUtils.getShortUin(str)).append(" cmd:").append(str2).append(" " + (bArr.length + i3)).toString());
                }
                if (str2.equals(a.J)) {
                    return bArr.length;
                }
            }
            ArrayList arrayList = (ArrayList) this.x.d.f().remove(Integer.valueOf(i3));
            QLog.d("MSF.C.NetConnTag", 1, "NetChanged devide merge package, " + Arrays.toString(arrayList.toArray()) + " resend.");
            if (arrayList != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    this.x.d.b(this.x.d.a(((Integer) it.next()).intValue()));
                }
            }
            return b;
        }
        return bArr.length;
    }

    public long f() {
        return this.p;
    }

    public static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        for (Throwable th2 = th; th2 != null; th2 = th2.getCause()) {
            if (th2 instanceof UnknownHostException) {
                return "";
            }
        }
        Writer stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        th.printStackTrace(printWriter);
        printWriter.flush();
        return stringWriter.toString();
    }
}
