package com.tencent.feedback.upload;

import android.content.Context;
import com.tencent.feedback.common.e;
import com.tencent.feedback.proguard.N;
import com.tencent.feedback.proguard.O;
import com.tencent.feedback.proguard.a;
import com.tencent.feedback.proguard.d;
import com.tencent.feedback.proguard.t;
import java.util.ArrayList;
import java.util.List;

/* compiled from: RQDSRC */
public final class f implements e {
    private static f a = null;
    private List<d> b = new ArrayList(2);
    private List<UploadHandleListener> c = new ArrayList(5);
    private c d;
    private Context e = null;
    private boolean f = true;
    private int g = 0;

    public static synchronized f a(Context context) {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                a = new f(context, true);
                e.b("rqdp{  create uphandler up:true}", new Object[0]);
            }
            fVar = a;
        }
        return fVar;
    }

    public static synchronized f a(Context context, boolean z) {
        f fVar;
        synchronized (f.class) {
            if (a == null) {
                a = new f(context, z);
                e.b("rqdp{  create uphandler up:}%b", Boolean.valueOf(z));
            }
            if (a.e() != z) {
                a.a(z);
                e.b("rqdp{  change uphandler up:}%b", Boolean.valueOf(z));
            }
            fVar = a;
        }
        return fVar;
    }

    private f(Context context, boolean z) {
        if (context != null) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext != null) {
                context = applicationContext;
            }
        }
        this.e = context;
        this.f = z;
        this.d = c.a(this.e);
    }

    public final synchronized boolean a(d dVar) {
        boolean z;
        if (dVar == null) {
            z = false;
        } else {
            if (!this.b.contains(dVar)) {
                this.b.add(dVar);
            }
            z = true;
        }
        return z;
    }

    public final synchronized boolean a(UploadHandleListener uploadHandleListener) {
        boolean z;
        if (uploadHandleListener == null) {
            z = false;
        } else {
            if (!this.c.contains(uploadHandleListener)) {
                this.c.add(uploadHandleListener);
            }
            z = true;
        }
        return z;
    }

    private synchronized int a() {
        return this.g;
    }

    private synchronized void a(int i) {
        this.g = i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(com.tencent.feedback.upload.AbstractUploadDatas r15) {
        /*
        r14 = this;
        r10 = 2;
        r3 = -1;
        r0 = 1;
        r4 = 0;
        r8 = 0;
        r1 = r14.e();
        if (r1 != 0) goto L_0x002d;
    L_0x000c:
        r1 = r15.b;
        r2 = 1111; // 0x457 float:1.557E-42 double:5.49E-321;
        if (r1 == r2) goto L_0x0025;
    L_0x0012:
        r1 = "rqdp{   Not UpProc not req }%d";
        r0 = new java.lang.Object[r0];
        r2 = r15.b();
        r2 = java.lang.Integer.valueOf(r2);
        r0[r8] = r2;
        com.tencent.feedback.common.e.b(r1, r0);
    L_0x0024:
        return;
    L_0x0025:
        r1 = "rqdp{   NotUpProc com req start}";
        r2 = new java.lang.Object[r8];
        com.tencent.feedback.common.e.b(r1, r2);
    L_0x002d:
        r1 = r14.e;
        r1 = com.tencent.feedback.common.g.b(r1);
        if (r1 != 0) goto L_0x003e;
    L_0x0035:
        r0 = "rqdp{  doUpload network is disabled!}";
        r1 = new java.lang.Object[r8];
        com.tencent.feedback.common.e.c(r0, r1);
        goto L_0x0024;
    L_0x003e:
        r1 = r14.e;
        r1 = com.tencent.feedback.common.f.a(r1);
        r14.a(r1);
        if (r15 != 0) goto L_0x0052;
    L_0x0049:
        r0 = "rqdp{  upData == null?}";
        r1 = new java.lang.Object[r8];
        com.tencent.feedback.common.e.d(r0, r1);
        goto L_0x0024;
    L_0x0052:
        r2 = r15.b();
        r6 = r14.b();
        if (r6 == 0) goto L_0x0068;
    L_0x005c:
        r7 = r6.length;
        r1 = r8;
    L_0x005e:
        if (r1 >= r7) goto L_0x0068;
    L_0x0060:
        r9 = r6[r1];
        r9.onUploadStart(r2);
        r1 = r1 + 1;
        goto L_0x005e;
    L_0x0068:
        r1 = r15.c();
        if (r1 != 0) goto L_0x007f;
    L_0x006e:
        r0 = "rqdp{  url error}";
        r1 = new java.lang.Object[r8];
        com.tencent.feedback.common.e.d(r0, r1);
        r9 = "url error";
        r1 = r14;
        r6 = r4;
        r1.a(r2, r3, r4, r6, r8, r9);
        goto L_0x0024;
    L_0x007f:
        r6 = "rqdp{  start upload cmd:}%d rqdp{  url:}%s rqdp{  datas:}%s";
        r7 = 3;
        r7 = new java.lang.Object[r7];
        r9 = java.lang.Integer.valueOf(r2);
        r7[r8] = r9;
        r7[r0] = r1;
        r9 = r15.getClass();
        r9 = r9.toString();
        r7[r10] = r9;
        com.tencent.feedback.common.e.b(r6, r7);
        r6 = b(r15);
        if (r6 != 0) goto L_0x00b2;
    L_0x00a0:
        r0 = "rqdp{  sData error}";
        r1 = new java.lang.Object[r8];
        com.tencent.feedback.common.e.d(r0, r1);
        r9 = "sData error";
        r1 = r14;
        r6 = r4;
        r1.a(r2, r3, r4, r6, r8, r9);
        goto L_0x0024;
    L_0x00b2:
        r7 = r14.c();
        if (r7 != 0) goto L_0x00ca;
    L_0x00b8:
        r0 = "rqdp{  reqH error}";
        r1 = new java.lang.Object[r8];
        com.tencent.feedback.common.e.d(r0, r1);
        r9 = "reqH error";
        r1 = r14;
        r6 = r4;
        r1.a(r2, r3, r4, r6, r8, r9);
        goto L_0x0024;
    L_0x00ca:
        r9 = r14.e;
        r9 = com.tencent.feedback.common.c.a(r9);
        r10 = new java.util.HashMap;
        r10.<init>();
        r11 = "pid";
        r12 = r9.b();
        r10.put(r11, r12);
        r11 = "bid";
        r12 = r9.b();
        r10.put(r11, r12);
        r11 = "pver";
        r9 = r9.B();
        r10.put(r11, r9);
        r9 = new com.tencent.feedback.upload.b;	 Catch:{ Throwable -> 0x014e, all -> 0x01d9 }
        r9.<init>();	 Catch:{ Throwable -> 0x014e, all -> 0x01d9 }
        r1 = r7.a(r1, r6, r9, r10);	 Catch:{ Throwable -> 0x014e, all -> 0x01d9 }
        if (r1 != 0) goto L_0x0149;
    L_0x00fe:
        r6 = r14.a();	 Catch:{ Throwable -> 0x014e, all -> 0x01d9 }
        r6 = r6 + 1;
        r14.a(r6);	 Catch:{ Throwable -> 0x014e, all -> 0x01d9 }
    L_0x0107:
        r10 = r9.a();	 Catch:{ Throwable -> 0x014e, all -> 0x01d9 }
        r6 = r9.b();	 Catch:{ Throwable -> 0x01f5, all -> 0x01d9 }
        r1 = r14.a(r1);	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        if (r1 == 0) goto L_0x013a;
    L_0x0115:
        r3 = r1.b;	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r4 = r1.a;	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        if (r4 != 0) goto L_0x011c;
    L_0x011b:
        r8 = r0;
    L_0x011c:
        r0 = "[req] request finish: result=%b requestCmd=%d, responseCmd=%d";
        r4 = 3;
        r4 = new java.lang.Object[r4];	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r5 = 0;
        r9 = java.lang.Boolean.valueOf(r8);	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r4[r5] = r9;	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r5 = 1;
        r9 = java.lang.Integer.valueOf(r2);	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r4[r5] = r9;	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r5 = 2;
        r9 = java.lang.Integer.valueOf(r3);	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r4[r5] = r9;	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        com.tencent.feedback.common.e.b(r0, r4);	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
    L_0x013a:
        if (r15 == 0) goto L_0x013e;
    L_0x013c:
        if (r1 != 0) goto L_0x0178;
    L_0x013e:
        r9 = 0;
        r1 = r14;
        r4 = r10;
        r1.a(r2, r3, r4, r6, r8, r9);	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r15.done(r8);
        goto L_0x0024;
    L_0x0149:
        r6 = 0;
        r14.a(r6);	 Catch:{ Throwable -> 0x014e, all -> 0x01d9 }
        goto L_0x0107;
    L_0x014e:
        r0 = move-exception;
        r10 = r8;
        r6 = r4;
    L_0x0151:
        r1 = com.tencent.feedback.common.e.a(r0);	 Catch:{ all -> 0x01f2 }
        if (r1 != 0) goto L_0x015a;
    L_0x0157:
        r0.printStackTrace();	 Catch:{ all -> 0x01f2 }
    L_0x015a:
        r8 = 0;
        r9 = r0.toString();	 Catch:{ all -> 0x01f2 }
        r1 = r14;
        r1.a(r2, r3, r4, r6, r8, r9);	 Catch:{ all -> 0x01f2 }
        r1 = "rqdp{  req error} %s";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x01f2 }
        r3 = 0;
        r0 = r0.toString();	 Catch:{ all -> 0x01f2 }
        r2[r3] = r0;	 Catch:{ all -> 0x01f2 }
        com.tencent.feedback.common.e.d(r1, r2);	 Catch:{ all -> 0x01f2 }
        r15.done(r10);
        goto L_0x0024;
    L_0x0178:
        r0 = r14.e;	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r0 = com.tencent.feedback.common.c.a(r0);	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        if (r0 == 0) goto L_0x01b0;
    L_0x0180:
        r4 = r1.d;	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r0.c(r4);	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r4 = new java.util.Date;	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r4.<init>();	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r12 = r1.e;	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r4 = r4.getTime();	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r4 = r12 - r4;
        r0.a(r4);	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r4 = "rqdp{  fix ip:}%s rqdp{  tmgap:}%d";
        r5 = 2;
        r5 = new java.lang.Object[r5];	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r9 = 0;
        r12 = r0.i();	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r5[r9] = r12;	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r9 = 1;
        r12 = r0.j();	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r0 = java.lang.Long.valueOf(r12);	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        r5[r9] = r0;	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        com.tencent.feedback.common.e.a(r4, r5);	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
    L_0x01b0:
        r0 = r1.c;	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        if (r0 != 0) goto L_0x01c2;
    L_0x01b4:
        r0 = "rqdp{  no response}";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        com.tencent.feedback.common.e.c(r0, r1);	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        goto L_0x013e;
    L_0x01be:
        r0 = move-exception;
        r4 = r10;
        r10 = r8;
        goto L_0x0151;
    L_0x01c2:
        r4 = r14.d();	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        if (r4 == 0) goto L_0x01ce;
    L_0x01c8:
        r5 = r4.size();	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        if (r5 > 0) goto L_0x01de;
    L_0x01ce:
        r0 = "rqdp{  no handler!}";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        com.tencent.feedback.common.e.b(r0, r1);	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        goto L_0x013e;
    L_0x01d9:
        r0 = move-exception;
    L_0x01da:
        r15.done(r8);
        throw r0;
    L_0x01de:
        r1 = r1.b;	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        if (r1 != 0) goto L_0x01ed;
    L_0x01e2:
        r0 = "rqdp{  response no datas}";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        com.tencent.feedback.common.e.a(r0, r1);	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        goto L_0x013e;
    L_0x01ed:
        a(r4, r1, r0);	 Catch:{ Throwable -> 0x01be, all -> 0x01d9 }
        goto L_0x013e;
    L_0x01f2:
        r0 = move-exception;
        r8 = r10;
        goto L_0x01da;
    L_0x01f5:
        r0 = move-exception;
        r6 = r4;
        r4 = r10;
        r10 = r8;
        goto L_0x0151;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.feedback.upload.f.a(com.tencent.feedback.upload.AbstractUploadDatas):void");
    }

    private static byte[] b(AbstractUploadDatas abstractUploadDatas) {
        if (abstractUploadDatas != null) {
            try {
                N a = abstractUploadDatas.a();
                if (a != null) {
                    e.b("rqdp{  [pid:}%s rqdp{  \nbid:}%s_%s rqdp{  \nsv:}%s \n]", a.c, a.c, a.d, a.f);
                    d dVar = new d();
                    dVar.e();
                    dVar.a("utf-8");
                    dVar.a(1);
                    dVar.d("RqdServer");
                    dVar.e("sync");
                    dVar.a("detail", a);
                    return dVar.a();
                }
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
                AbstractUploadDatas.d();
            }
        }
        return null;
    }

    private O a(byte[] bArr) {
        if (bArr != null) {
            try {
                O o;
                d dVar = new d();
                dVar.e();
                dVar.a("utf-8");
                dVar.a(bArr);
                Object b = dVar.b("detail", new O());
                if (O.class.isInstance(b)) {
                    e.b("rqdp{  covert to ResponsePackage}", new Object[0]);
                    o = (O) O.class.cast(b);
                } else {
                    o = null;
                }
                if (o == null || o.c == null || o.c.length <= 0) {
                    return o;
                }
                e.b("resp buf %d", Integer.valueOf(o.c.length));
                o.c = a.b(o.c, 2, 1, t.a(this.e).b().e());
                if (o.c != null) {
                    return o;
                }
                e.d("resp sbuffer error!", new Object[0]);
                return null;
            } catch (Throwable th) {
                if (!e.a(th)) {
                    th.printStackTrace();
                }
            }
        }
        return null;
    }

    private void a(int i, int i2, long j, long j2, boolean z, String str) {
        UploadHandleListener[] b = b();
        if (b != null) {
            for (UploadHandleListener onUploadEnd : b) {
                onUploadEnd.onUploadEnd(i, i2, j, j2, z, str);
            }
        }
    }

    private synchronized UploadHandleListener[] b() {
        UploadHandleListener[] uploadHandleListenerArr;
        if (this.c == null || this.c.size() <= 0) {
            uploadHandleListenerArr = null;
        } else {
            uploadHandleListenerArr = (UploadHandleListener[]) this.c.toArray(new UploadHandleListener[0]);
        }
        return uploadHandleListenerArr;
    }

    private synchronized c c() {
        return this.d;
    }

    private synchronized List<d> d() {
        List<d> list;
        if (this.b == null || this.b.size() <= 0) {
            list = null;
        } else {
            list = new ArrayList(this.b);
        }
        return list;
    }

    private static void a(List<d> list, int i, byte[] bArr) {
        if (list != null && bArr != null) {
            for (d dVar : list) {
                if (dVar != null) {
                    try {
                        e.b("rqdp{  key:}%d rqdp{  handler: }%s", Integer.valueOf(i), dVar.getClass().toString());
                        dVar.a(i, bArr, true);
                    } catch (Throwable th) {
                        if (!e.a(th)) {
                            th.printStackTrace();
                        }
                        e.d("rqdp{  handle error key:}%d", Integer.valueOf(i));
                    }
                }
            }
        }
    }

    private synchronized boolean e() {
        return this.f;
    }

    private synchronized void a(boolean z) {
        this.f = z;
    }
}
