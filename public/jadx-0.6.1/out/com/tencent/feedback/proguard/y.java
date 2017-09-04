package com.tencent.feedback.proguard;

import android.content.Context;
import com.tencent.feedback.common.a;
import com.tencent.feedback.common.b;
import com.tencent.feedback.common.c;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.feedback.upload.e;
import com.tencent.util.TimeFormatterUtils;
import java.util.Calendar;
import java.util.Date;

/* compiled from: RQDSRC */
public final class y implements Runnable {
    private static y a;
    private Context b;
    private b c;
    private e d;
    private boolean e = false;
    private long f = BuglyBroadcastRecevier.UPLOADLIMITED;
    private int g = 10;
    private boolean h = true;
    private final String i;
    private int j = 0;
    private long k = 0;

    private y(Context context, b bVar, e eVar) {
        this.b = context;
        this.c = bVar;
        this.d = eVar;
        this.i = c.a(context).E();
        this.k = e();
    }

    public static synchronized y a(Context context, b bVar, e eVar) {
        y yVar;
        synchronized (y.class) {
            if (a == null) {
                if (context != null) {
                    Context applicationContext = context.getApplicationContext();
                    if (applicationContext != null) {
                        context = applicationContext;
                    }
                }
                a = new y(context, bVar, eVar);
            }
            yVar = a;
        }
        return yVar;
    }

    public static synchronized y a() {
        y yVar;
        synchronized (y.class) {
            yVar = a;
        }
        return yVar;
    }

    public final synchronized boolean b() {
        return this.e;
    }

    public final synchronized boolean a(long j) {
        long j2 = BuglyBroadcastRecevier.UPLOADLIMITED;
        boolean z = true;
        synchronized (this) {
            if (this.c != null) {
                if (BuglyBroadcastRecevier.UPLOADLIMITED <= 
/*
Method generation error in method: com.tencent.feedback.proguard.y.a(long):boolean
jadx.core.utils.exceptions.JadxRuntimeException: Unknown type in literalToString: void
	at jadx.core.codegen.TypeGen.literalToString(TypeGen.java:83)
	at jadx.core.codegen.TypeGen.literalToString(TypeGen.java:37)
	at jadx.core.codegen.InsnGen.lit(InsnGen.java:135)
	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:98)
	at jadx.core.codegen.ConditionGen.addCompare(ConditionGen.java:134)
	at jadx.core.codegen.ConditionGen.add(ConditionGen.java:60)
	at jadx.core.codegen.ConditionGen.add(ConditionGen.java:49)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:116)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:118)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeSynchronizedRegion(RegionGen.java:227)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:65)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:183)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:328)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:265)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:228)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:118)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:83)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:19)
	at jadx.core.ProcessClass.process(ProcessClass.java:43)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)

*/

                private synchronized boolean i() {
                    boolean z = false;
                    synchronized (this) {
                        if (a(2)) {
                            com.tencent.feedback.common.e.b("rqdp{ resume state record true}", new Object[0]);
                            z = true;
                        } else {
                            com.tencent.feedback.common.e.c("rqdp{ resume record fail}", new Object[0]);
                        }
                    }
                    return z;
                }

                public final synchronized boolean c() {
                    return b.b().a(new Runnable(this) {
                        private /* synthetic */ y a;

                        {
                            this.a = r1;
                        }

                        public final void run() {
                            com.tencent.feedback.common.e.b("rqdp{ login state record %b}", Boolean.valueOf(this.a.a(4)));
                        }
                    });
                }

                private synchronized boolean j() {
                    com.tencent.feedback.common.e.b("rqdp{ launch state record %b}", Boolean.valueOf(a(1)));
                    return a(1);
                }

                public final synchronized boolean d() {
                    com.tencent.feedback.common.e.b("rqdp{ next day state record %b}", Boolean.valueOf(a(3)));
                    return a(3);
                }

                protected final synchronized boolean a(int i) {
                    boolean z = true;
                    synchronized (this) {
                        c a = c.a(this.b);
                        if (a != null) {
                            q qVar = new q();
                            qVar.a(i);
                            qVar.a(new Date().getTime());
                            qVar.a(this.i);
                            qVar.b(a.g());
                            if (a.a(this.b, new q[]{qVar}) > 0) {
                                if (b(this.i) >= this.g) {
                                    com.tencent.feedback.common.e.b("rqdp{ state max upload}", new Object[0]);
                                    b g = g();
                                    if (g != null) {
                                        if (0 <= 0) {
                                            g.a(new Runnable(this, (byte) 2) {
                                                private /* synthetic */ y b;

                                                public final void run() {
                                                    e h = this.b.h();
                                                    if (h != null) {
                                                        h.a(new A(this.b.b, y.a(), (byte) 2));
                                                    }
                                                }
                                            });
                                        } else if (0 > 0) {
                                            g.a(new Runnable(this, (byte) 2) {
                                                private /* synthetic */ y b;

                                                public final void run() {
                                                    e h = this.b.h();
                                                    if (h != null) {
                                                        h.a(new A(this.b.b, y.a(), (byte) 2));
                                                    }
                                                }
                                            }, 0);
                                        }
                                    }
                                }
                            }
                        }
                        z = false;
                    }
                    return z;
                }

                public final synchronized q[] a(String str) {
                    return a.a(this.b, str);
                }

                public final synchronized int a(q[] qVarArr) {
                    return a.b(this.b, qVarArr);
                }

                public final synchronized int b(String str) {
                    return a.b(this.b, str);
                }

                public final synchronized long e() {
                    long time;
                    try {
                        Calendar instance = Calendar.getInstance();
                        instance.set(11, 0);
                        instance.set(12, 0);
                        instance.set(13, 0);
                        instance.add(6, 1);
                        time = instance.getTime().getTime();
                    } catch (Throwable th) {
                        if (!com.tencent.feedback.common.e.a(th)) {
                            th.printStackTrace();
                        }
                        time = new Date().getTime() + TimeFormatterUtils.ONE_DAY;
                    }
                    return time;
                }

                private synchronized int k() {
                    return this.j;
                }

                private synchronized void b(int i) {
                    this.j = i;
                }

                public final synchronized long f() {
                    return this.k;
                }

                protected final synchronized void b(long j) {
                    this.k = j;
                }

                public final synchronized b g() {
                    return this.c;
                }

                public final synchronized e h() {
                    return this.d;
                }

                public final void run() {
                    int k = k() + 1;
                    b(k);
                    if (k == 1) {
                        this.h = a.b(this.b, this.i);
                        j();
                        return;
                    }
                    boolean b = a.b(this.b, this.i);
                    synchronized (this) {
                        if (b != this.h) {
                            this.h = b;
                            if (b) {
                                k = 1;
                            }
                        }
                        k = 0;
                    }
                    if (k != 0) {
                        com.tencent.feedback.common.e.b("process:%s is resumed!", this.i);
                        i();
                    }
                }
            }
