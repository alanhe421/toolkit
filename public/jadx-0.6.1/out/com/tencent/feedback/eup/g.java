package com.tencent.feedback.eup;

import android.content.Context;
import com.tencent.feedback.common.PlugInInfo;
import com.tencent.feedback.common.c;
import com.tencent.feedback.common.e;
import com.tencent.feedback.proguard.I;
import com.tencent.feedback.proguard.K;
import com.tencent.feedback.proguard.L;
import com.tencent.feedback.proguard.M;
import com.tencent.feedback.proguard.N;
import com.tencent.feedback.proguard.a;
import com.tencent.feedback.proguard.j;
import com.tencent.feedback.proguard.l;
import com.tencent.feedback.proguard.o;
import com.tencent.feedback.upload.AbstractUploadDatas;
import com.tencent.open.SocialConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: RQDSRC */
public final class g extends AbstractUploadDatas {
    private static g d = null;
    private N e = null;
    private List<e> f = null;

    public static synchronized g a(Context context) {
        g gVar;
        synchronized (g.class) {
            if (context != null) {
                if (d == null) {
                    d = new g(context);
                }
            }
            gVar = d;
        }
        return gVar;
    }

    private g(Context context) {
        super(context, 3, 530);
    }

    public final synchronized N a() {
        N n = null;
        synchronized (this) {
            if (this.e != null) {
                n = this.e;
            } else {
                f l = f.l();
                if (l != null && l.a()) {
                    try {
                        CrashStrategyBean s = f.l().s();
                        this.f = null;
                        int maxUploadNum_Wifi;
                        try {
                            boolean isMerged = s.isMerged();
                            maxUploadNum_Wifi = com.tencent.feedback.common.g.a(this.c) ? s.getMaxUploadNum_Wifi() : s.getMaxUploadNum_GPRS();
                            if (isMerged) {
                                e.b("rqdp{  in merge:}", new Object[0]);
                                this.f = a(this.c, maxUploadNum_Wifi);
                            } else {
                                e.b("rqdp{  not merge:}", new Object[0]);
                                this.f = b(this.c, maxUploadNum_Wifi);
                            }
                            if (this.f == null || this.f.size() <= 0) {
                                e.c("rqdp{  empty eup!}", new Object[0]);
                            } else {
                                e.b("rqdp{  pack n:}%d ,isM:%b", Integer.valueOf(this.f.size()), Boolean.valueOf(isMerged));
                                j a = a(this.c, this.f);
                                if (a != null) {
                                    byte[] a2 = a.a(a);
                                    if (a2 == null) {
                                        e.c("rqdp{  empty edatas!}", new Object[0]);
                                    } else {
                                        this.e = AbstractUploadDatas.a(this.c, this.a, a2);
                                        n = this.e;
                                    }
                                }
                            }
                        } catch (Throwable th) {
                            if (!e.a(th)) {
                                th.printStackTrace();
                            }
                            if (this.f != null && this.f.size() > 0) {
                                maxUploadNum_Wifi = b.a(this.c, this.f);
                                e.c("rqdp{ eup error remove} %d", Integer.valueOf(maxUploadNum_Wifi));
                                this.f = null;
                            }
                        }
                    } catch (Throwable th2) {
                        if (!e.a(th2)) {
                            th2.printStackTrace();
                        }
                        e.d("rqdp{  imposiable} %s", th2.toString());
                    }
                }
            }
        }
        return n;
    }

    private List<e> b(Context context, int i) {
        e.b("rqdp{  get MN:}%d", Integer.valueOf(i));
        if (context == null || i <= 0) {
            e.c("rqdp{  params!}", new Object[0]);
            return null;
        }
        try {
            List a = b.a(context, i, SocialConstants.PARAM_APP_DESC, 1, null, -1, -1, -1, 3, -1, -1, null);
            if (a == null) {
                a = new ArrayList();
            }
            if (a.size() < i) {
                Collection a2 = b.a(context, i - a.size(), SocialConstants.PARAM_APP_DESC, 2, null, -1, -1, -1, 3, -1, -1, null);
                if (a2 != null && a2.size() > 0) {
                    a.addAll(a2);
                }
            }
            a(a);
            b.b(context, a);
            return a;
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private M a(Context context, List<e> list) {
        if (context == null || list == null || list.size() <= 0) {
            e.c("rqdp{  params!}", new Object[0]);
            return null;
        }
        try {
            ArrayList arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            for (e eVar : list) {
                if (c.a(context).B().equals(eVar.N())) {
                    L a = a(context, eVar);
                    if (a != null) {
                        arrayList.add(a);
                    } else {
                        arrayList2.add(eVar);
                    }
                } else {
                    e.c("updated drop it", new Object[0]);
                    arrayList2.add(eVar);
                }
            }
            M m = new M();
            m.a = arrayList;
            if (arrayList2.size() <= 0) {
                return m;
            }
            e.c("rqdp{ delete error eup} %d", Integer.valueOf(b.a(context, arrayList2)));
            list.removeAll(arrayList2);
            return m;
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private L a(Context context, e eVar) {
        if (eVar == null) {
            e.c("rqdp{  params!}", new Object[0]);
            return null;
        }
        K a;
        e.b("rqdp{  pack n:}%s , rqdp{  iM:}%b , rqdp{  tp:}%d", eVar.f(), Boolean.valueOf(eVar.c()), Byte.valueOf(eVar.P()));
        String str = eVar.d() ? eVar.c() ? "201" : "101" : eVar.x() ? eVar.c() ? "203" : "103" : eVar.b() ? eVar.c() ? "200" : "100" : eVar.c() ? "202" : "102";
        L l = new L();
        l.a = str;
        l.b = eVar.i();
        l.c = eVar.e();
        l.d = eVar.f();
        l.e = eVar.g();
        l.h = eVar.C();
        l.g = eVar.h();
        l.i = eVar.v();
        l.k = eVar.k();
        l.l = eVar.D();
        l.f = eVar.r();
        Map u = eVar.u();
        String str2 = "plugin size :%d";
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(u == null ? 0 : u.size());
        e.b(str2, objArr);
        if (u != null && u.size() > 0) {
            l.n = new ArrayList();
            for (Entry entry : u.entrySet()) {
                I i = new I();
                i.a = ((PlugInInfo) entry.getValue()).plugInId;
                i.c = ((PlugInInfo) entry.getValue()).plugInUUID;
                i.d = ((PlugInInfo) entry.getValue()).plugInVersion;
                l.n.add(i);
            }
        }
        if (eVar.c() && eVar.m() > 1) {
            l.j = eVar.m();
            if (eVar.l() != null && eVar.l().length() > 0) {
                if (l.o == null) {
                    l.o = new ArrayList();
                }
                l.o.add(new K((byte) 1, "alltimes.txt", eVar.l().getBytes("utf-8")));
            }
        }
        if (eVar.n() != null) {
            if (l.o == null) {
                l.o = new ArrayList();
            }
            l.o.add(new K((byte) 1, "log.txt", eVar.n()));
        }
        if (eVar.s() != null) {
            a = a(eVar.s().getBytes("utf8"), "extraMessage.txt");
            if (a != null) {
                if (l.o == null) {
                    l.o = new ArrayList();
                }
                e.b("rqdp{  attach extra msg}", new Object[0]);
                l.o.add(a);
            }
        }
        if (eVar.t() != null) {
            a = a(eVar.t(), "extraDatas.txt");
            if (a != null) {
                e.b("rqdp{  attach extra datas}", new Object[0]);
                if (l.o == null) {
                    l.o = new ArrayList();
                }
                l.o.add(a);
            }
        }
        if (eVar.x() && eVar.p() != null) {
            if (l.o == null) {
                l.o = new ArrayList();
            }
            try {
                l.o.add(new K((byte) 1, "anrMessage.txt", eVar.s().getBytes("utf8")));
                e.b("attach anr message", new Object[0]);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                l.o = null;
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
                return null;
            }
            a = a("trace.zip", context, eVar.p());
            if (a != null) {
                e.b("attach traces", new Object[0]);
                l.o.add(a);
            }
        }
        c a2 = c.a(context);
        l.p = new HashMap();
        l.p.put("A9", eVar.F());
        l.p.put("A11", eVar.G());
        l.p.put("A10", eVar.H());
        l.p.put("A23", eVar.N());
        l.p.put("A7", a2.d());
        l.p.put("A6", a2.w());
        l.p.put("A5", a2.v());
        l.p.put("A22", a2.h());
        l.p.put("A2", eVar.J());
        l.p.put("A1", eVar.I());
        l.p.put("A24", a2.f());
        l.p.put("A17", eVar.K());
        l.p.put("A3", a2.o());
        l.p.put("A16", a2.q());
        l.p.put("A25", a2.r());
        l.p.put("A14", a2.p());
        l.p.put("A15", a2.z());
        l.p.put("A13", a2.C());
        l.p.put("A34", eVar.q());
        l.p.put("A30", eVar.B());
        l.p.put("productIdentify", a2.k().trim().length() > 0 ? a2.k() : a2.l());
        if (eVar.d()) {
            l.p.put("A27", eVar.A());
            l.p.put("A28", eVar.z());
            l.p.put("A29", eVar.E());
        }
        ArrayList b = b(context);
        if (b != null && b.size() > 0) {
            l.m = b;
        }
        try {
            l.p.put("A26", URLEncoder.encode(eVar.y(), "utf-8"));
            if (eVar.a >= 0) {
                l.p.put("C01", eVar.a);
            }
            if (eVar.b >= 0) {
                l.p.put("C02", eVar.b);
            }
            if (eVar.c != null && eVar.c.size() > 0) {
                for (Entry entry2 : eVar.c.entrySet()) {
                    l.p.put("C03_" + ((String) entry2.getKey()), entry2.getValue());
                }
            }
            if (eVar.d != null && eVar.d.size() > 0) {
                for (Entry entry3 : eVar.d.entrySet()) {
                    l.p.put("C04_" + ((String) entry3.getKey()), entry3.getValue());
                }
            }
            return l;
        } catch (Throwable th2) {
            if (!e.a(th2)) {
                th2.printStackTrace();
            }
            return null;
        }
    }

    private static K a(byte[] bArr, String str) {
        if (bArr == null || bArr.length <= 0 || str == null || str.trim().length() <= 0) {
            return null;
        }
        try {
            K k = new K();
            k.a = (byte) 1;
            k.b = str;
            k.c = bArr;
            return k;
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    public final synchronized void done(boolean z) {
        String str = "rqdp{  eupdone :} %s";
        Object[] objArr = new Object[1];
        objArr[0] = z ? "SUCC" : "FAIL";
        e.b(str, objArr);
        if (this.f != null && z) {
            boolean isMerged;
            try {
                isMerged = f.l().s().isMerged();
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
                isMerged = false;
            }
            if (isMerged) {
                long c = a.c();
                Iterator it = this.f.iterator();
                List arrayList = new ArrayList();
                while (it.hasNext()) {
                    e eVar = (e) it.next();
                    if (eVar.i() > c) {
                        eVar.b(true);
                        eVar.a(0);
                        arrayList.add(eVar);
                        it.remove();
                    }
                }
                isMerged = b.b(this.c, arrayList);
                e.b("rqdp{  merge update today eup n:}%d , res:%b", Integer.valueOf(this.f.size()), Boolean.valueOf(isMerged));
                int a = b.a(this.c, this.f);
                e.b("rqdp{  me rm num:}%d", Integer.valueOf(a));
            } else {
                e.b("rqdp{  rm n:}" + b.a(this.c, this.f), new Object[0]);
            }
        }
        this.e = null;
        this.f = null;
    }

    protected final List<e> a(Context context, int i) {
        e.b("rqdp{  getEupInMe}", new Object[0]);
        if (context == null || i <= 0) {
            e.d("rqdp{  params!}", new Object[0]);
            return null;
        }
        try {
            long c = a.c();
            List arrayList = new ArrayList();
            Object a = b.a(context, i, SocialConstants.PARAM_APP_DESC, -1, null, -1, -1, -1, 3, c, -1, Boolean.valueOf(false));
            if (a != null && a.size() > 0) {
                e.b("rqdp{  tdeup ge c=1, n:}%d", Integer.valueOf(a.size()));
                arrayList.addAll(a);
                a.clear();
            }
            if (arrayList.size() < i) {
                a = b.a(context, i, SocialConstants.PARAM_APP_DESC, -1, null, 2, -1, -1, 3, -1, c, null);
                if (a != null && a.size() > 0) {
                    e.b("rqdp{  yeseup c>=2,n:}%d" + a.size(), new Object[0]);
                    arrayList.addAll(a);
                    a.clear();
                }
            }
            if (arrayList.size() < i) {
                a = b.a(context, i, SocialConstants.PARAM_APP_DESC, -1, null, 0, 1, -1, -1, 3, c, Boolean.valueOf(false));
                if (a != null && a.size() > 0) {
                    e.b("rqdp{  yeseup c>=2,n:}%d" + a.size(), new Object[0]);
                    arrayList.addAll(a);
                    a.clear();
                }
            }
            a(arrayList);
            b.b(context, arrayList);
            return arrayList;
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            return null;
        }
    }

    private static K a(String str, Context context, String str2) {
        Throwable e;
        Throwable th;
        if (str2 == null || context == null) {
            e.c("rqdp{  createZipAttachment sourcePath == null || context == null ,pls check}", new Object[0]);
            return null;
        }
        e.b("rqdp{  zp}%s", str2);
        File file = new File(str2);
        File file2 = new File(context.getCacheDir(), str);
        if (a.a(file, file2, 5000)) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            FileInputStream fileInputStream;
            try {
                fileInputStream = new FileInputStream(file2);
                try {
                    byte[] bArr = new byte[1000];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read <= 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                        byteArrayOutputStream.flush();
                    }
                    e.b("rqdp{  re sz:}%d", Integer.valueOf(byteArrayOutputStream.toByteArray().length));
                    K k = new K((byte) 2, file2.getName(), bArr);
                    try {
                        fileInputStream.close();
                    } catch (Throwable e2) {
                        if (!e.a(e2)) {
                            e2.printStackTrace();
                        }
                    }
                    if (file2.exists()) {
                        e.b("rqdp{  del tmp}", new Object[0]);
                        file2.delete();
                    }
                    return k;
                } catch (Throwable th2) {
                    th = th2;
                    try {
                        if (!e.a(th)) {
                            th.printStackTrace();
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th3) {
                                if (!e.a(th3)) {
                                    th3.printStackTrace();
                                }
                            }
                        }
                        if (file2.exists()) {
                            return null;
                        }
                        e.b("rqdp{  del tmp}", new Object[0]);
                        file2.delete();
                        return null;
                    } catch (Throwable th4) {
                        e2 = th4;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable th32) {
                                if (!e.a(th32)) {
                                    th32.printStackTrace();
                                }
                            }
                        }
                        if (file2.exists()) {
                            e.b("rqdp{  del tmp}", new Object[0]);
                            file2.delete();
                        }
                        throw e2;
                    }
                }
            } catch (Throwable th322) {
                fileInputStream = null;
                e2 = th322;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (file2.exists()) {
                    e.b("rqdp{  del tmp}", new Object[0]);
                    file2.delete();
                }
                throw e2;
            }
        }
        e.c("rqdp{  fail!}", new Object[0]);
        return null;
    }

    private static ArrayList<I> b(Context context) {
        try {
            List A = c.a(context).A();
            if (A == null) {
                e.b("no setted solist from db", new Object[0]);
                A = l.a(context, null, 1, 50);
            }
            if (r0 != null && r0.size() > 0) {
                ArrayList<I> arrayList = new ArrayList();
                for (o oVar : r0) {
                    I i = new I();
                    i.b = oVar.f();
                    i.a = oVar.a();
                    i.c = oVar.d();
                    arrayList.add(i);
                    e.b("up %s %s %s", i.b, i.a, i.c);
                }
                return arrayList;
            }
        } catch (Throwable th) {
            if (!e.a(th)) {
                th.printStackTrace();
            }
            e.d("rqdp{  Error: lb pack fail!}", new Object[0]);
        }
        return null;
    }

    private static void a(List<e> list) {
        if (list != null) {
            for (e eVar : list) {
                eVar.a(eVar.j() + 1);
            }
        }
    }
}
