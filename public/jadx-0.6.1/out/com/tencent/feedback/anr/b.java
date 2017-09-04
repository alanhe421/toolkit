package com.tencent.feedback.anr;

import android.app.ActivityManager.ProcessErrorStateInfo;
import android.content.Context;
import com.tencent.av.config.ConfigBaseParser;
import com.tencent.feedback.common.c;
import com.tencent.feedback.common.e;
import com.tencent.feedback.eup.CrashStrategyBean;
import com.tencent.feedback.eup.jni.d;
import com.tencent.feedback.proguard.a;
import com.tencent.feedback.proguard.t;
import com.tencent.upload.log.trace.TracerConfig;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: RQDSRC */
public final class b {
    private static b d = null;
    private AtomicInteger a = new AtomicInteger(0);
    private long b = -1;
    private Context c;

    private b(Context context) {
        this.c = context;
        Context context2 = context;
        t.a(context).a(new d(context2, context.getFilesDir().getAbsolutePath() + "/eup", a.c() - 604800, 10, "tomb_", ".txt"));
    }

    public static synchronized b a(Context context) {
        b bVar;
        synchronized (b.class) {
            if (d == null) {
                d = new b(context);
            }
            bVar = d;
        }
        return bVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(java.lang.String r12) {
        /*
        r11 = this;
        r8 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r4 = -1;
        r1 = 0;
        monitor-enter(r11);
        r0 = r11.a;	 Catch:{ all -> 0x0037 }
        r0 = r0.get();	 Catch:{ all -> 0x0037 }
        if (r0 == 0) goto L_0x0019;
    L_0x000e:
        r0 = "anr started return ";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x0037 }
        com.tencent.feedback.common.e.b(r0, r1);	 Catch:{ all -> 0x0037 }
        monitor-exit(r11);	 Catch:{ all -> 0x0037 }
    L_0x0018:
        return;
    L_0x0019:
        r0 = r11.a;	 Catch:{ all -> 0x0037 }
        r2 = 1;
        r0.set(r2);	 Catch:{ all -> 0x0037 }
        monitor-exit(r11);	 Catch:{ all -> 0x0037 }
        r0 = r11.c;	 Catch:{ Throwable -> 0x01dd }
        r6 = com.tencent.feedback.common.c.a(r0);	 Catch:{ Throwable -> 0x01dd }
        if (r6 != 0) goto L_0x003a;
    L_0x0028:
        r0 = "impossiable not inited?";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01dd }
        com.tencent.feedback.common.e.d(r0, r2);	 Catch:{ Throwable -> 0x01dd }
        r0 = r11.a;
        r0.set(r1);
        goto L_0x0018;
    L_0x0037:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x003a:
        r7 = com.tencent.feedback.eup.CrashReport.getCrashRuntimeStrategy();	 Catch:{ Throwable -> 0x01dd }
        if (r7 == 0) goto L_0x004a;
    L_0x0040:
        r0 = com.tencent.feedback.eup.f.l();	 Catch:{ Throwable -> 0x01dd }
        r0 = r0.a();	 Catch:{ Throwable -> 0x01dd }
        if (r0 != 0) goto L_0x0059;
    L_0x004a:
        r0 = "impossiable crash close?";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01dd }
        com.tencent.feedback.common.e.d(r0, r2);	 Catch:{ Throwable -> 0x01dd }
        r0 = r11.a;
        r0.set(r1);
        goto L_0x0018;
    L_0x0059:
        r0 = "read trace first dump for create time!";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01dd }
        com.tencent.feedback.common.e.b(r0, r2);	 Catch:{ Throwable -> 0x01dd }
        r0 = 0;
        r0 = com.tencent.feedback.anr.a.a(r12, r0);	 Catch:{ Throwable -> 0x01dd }
        if (r0 == 0) goto L_0x0209;
    L_0x0069:
        r2 = r0.c;	 Catch:{ Throwable -> 0x01dd }
    L_0x006b:
        r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r0 != 0) goto L_0x0081;
    L_0x006f:
        r0 = "trace dump fail could not get time!";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01dd }
        com.tencent.feedback.common.e.c(r0, r2);	 Catch:{ Throwable -> 0x01dd }
        r0 = new java.util.Date;	 Catch:{ Throwable -> 0x01dd }
        r0.<init>();	 Catch:{ Throwable -> 0x01dd }
        r2 = r0.getTime();	 Catch:{ Throwable -> 0x01dd }
    L_0x0081:
        r4 = r11.b;	 Catch:{ Throwable -> 0x01dd }
        r4 = r2 - r4;
        r4 = java.lang.Math.abs(r4);	 Catch:{ Throwable -> 0x01dd }
        r0 = (r4 > r8 ? 1 : (r4 == r8 ? 0 : -1));
        if (r0 >= 0) goto L_0x00a6;
    L_0x008d:
        r0 = "should not process ANR too Fre in %d";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01dd }
        r3 = 0;
        r4 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Throwable -> 0x01dd }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x01dd }
        com.tencent.feedback.common.e.c(r0, r2);	 Catch:{ Throwable -> 0x01dd }
        r0 = r11.a;
        r0.set(r1);
        goto L_0x0018;
    L_0x00a6:
        r11.b = r2;	 Catch:{ Throwable -> 0x01dd }
        r0 = r11.a;	 Catch:{ Throwable -> 0x01dd }
        r4 = 1;
        r0.set(r4);	 Catch:{ Throwable -> 0x01dd }
        r0 = com.tencent.feedback.proguard.a.b();	 Catch:{ Throwable -> 0x00ca }
        if (r0 == 0) goto L_0x00ba;
    L_0x00b4:
        r4 = r0.size();	 Catch:{ Throwable -> 0x01dd }
        if (r4 > 0) goto L_0x00de;
    L_0x00ba:
        r0 = "can't get all thread skip this anr";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01dd }
        com.tencent.feedback.common.e.c(r0, r2);	 Catch:{ Throwable -> 0x01dd }
        r0 = r11.a;
        r0.set(r1);
        goto L_0x0018;
    L_0x00ca:
        r0 = move-exception;
        com.tencent.feedback.common.e.a(r0);	 Catch:{ Throwable -> 0x01dd }
        r0 = "get all thread stack fail!";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01dd }
        com.tencent.feedback.common.e.d(r0, r2);	 Catch:{ Throwable -> 0x01dd }
        r0 = r11.a;
        r0.set(r1);
        goto L_0x0018;
    L_0x00de:
        r4 = r11.c;	 Catch:{ Throwable -> 0x01dd }
        r8 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r4 = a(r4, r8);	 Catch:{ Throwable -> 0x01dd }
        if (r4 != 0) goto L_0x00f8;
    L_0x00e8:
        r0 = "proc error state is unvisiable , may not be mine or just wait to be killed";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01dd }
        com.tencent.feedback.common.e.b(r0, r2);	 Catch:{ Throwable -> 0x01dd }
        r0 = r11.a;
        r0.set(r1);
        goto L_0x0018;
    L_0x00f8:
        r5 = r4.pid;	 Catch:{ Throwable -> 0x01dd }
        r8 = android.os.Process.myPid();	 Catch:{ Throwable -> 0x01dd }
        if (r5 == r8) goto L_0x0115;
    L_0x0100:
        r0 = "found other process %s visiable anr error , not mine just return";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01dd }
        r3 = 0;
        r4 = r4.processName;	 Catch:{ Throwable -> 0x01dd }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x01dd }
        com.tencent.feedback.common.e.b(r0, r2);	 Catch:{ Throwable -> 0x01dd }
        r0 = r11.a;
        r0.set(r1);
        goto L_0x0018;
    L_0x0115:
        r5 = "current proc occur visiable anr start to handle";
        r8 = 0;
        r8 = new java.lang.Object[r8];	 Catch:{ Throwable -> 0x01dd }
        com.tencent.feedback.common.e.a(r5, r8);	 Catch:{ Throwable -> 0x01dd }
        r5 = r11.c;	 Catch:{ Throwable -> 0x01dd }
        r8 = new java.io.File;	 Catch:{ Throwable -> 0x01dd }
        r5 = r5.getFilesDir();	 Catch:{ Throwable -> 0x01dd }
        r9 = new java.lang.StringBuilder;	 Catch:{ Throwable -> 0x01dd }
        r10 = "eup/eup_trace_";
        r9.<init>(r10);	 Catch:{ Throwable -> 0x01dd }
        r9 = r9.append(r2);	 Catch:{ Throwable -> 0x01dd }
        r9 = r9.toString();	 Catch:{ Throwable -> 0x01dd }
        r8.<init>(r5, r9);	 Catch:{ Throwable -> 0x01dd }
        r5 = new com.tencent.feedback.anr.a;	 Catch:{ Throwable -> 0x01dd }
        r5.<init>();	 Catch:{ Throwable -> 0x01dd }
        r5.c = r2;	 Catch:{ Throwable -> 0x01dd }
        r2 = r8.getAbsolutePath();	 Catch:{ Throwable -> 0x01dd }
        r5.d = r2;	 Catch:{ Throwable -> 0x01dd }
        r2 = r4.processName;	 Catch:{ Throwable -> 0x01dd }
        r5.a = r2;	 Catch:{ Throwable -> 0x01dd }
        r2 = r4.shortMsg;	 Catch:{ Throwable -> 0x01dd }
        r5.f = r2;	 Catch:{ Throwable -> 0x01dd }
        r2 = r4.longMsg;	 Catch:{ Throwable -> 0x01dd }
        r5.e = r2;	 Catch:{ Throwable -> 0x01dd }
        r5.b = r0;	 Catch:{ Throwable -> 0x01dd }
        r2 = "anr tm:%d\ntr:%s\nproc:%s\nsMsg:%s\n lMsg:%s\n threads:%d";
        r0 = 6;
        r3 = new java.lang.Object[r0];	 Catch:{ Throwable -> 0x01dd }
        r0 = 0;
        r8 = r5.c;	 Catch:{ Throwable -> 0x01dd }
        r4 = java.lang.Long.valueOf(r8);	 Catch:{ Throwable -> 0x01dd }
        r3[r0] = r4;	 Catch:{ Throwable -> 0x01dd }
        r0 = 1;
        r4 = r5.d;	 Catch:{ Throwable -> 0x01dd }
        r3[r0] = r4;	 Catch:{ Throwable -> 0x01dd }
        r0 = 2;
        r4 = r5.a;	 Catch:{ Throwable -> 0x01dd }
        r3[r0] = r4;	 Catch:{ Throwable -> 0x01dd }
        r0 = 3;
        r4 = r5.f;	 Catch:{ Throwable -> 0x01dd }
        r3[r0] = r4;	 Catch:{ Throwable -> 0x01dd }
        r0 = 4;
        r4 = r5.e;	 Catch:{ Throwable -> 0x01dd }
        r3[r0] = r4;	 Catch:{ Throwable -> 0x01dd }
        r4 = 5;
        r0 = r5.b;	 Catch:{ Throwable -> 0x01dd }
        if (r0 != 0) goto L_0x019e;
    L_0x017c:
        r0 = r1;
    L_0x017d:
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Throwable -> 0x01dd }
        r3[r4] = r0;	 Catch:{ Throwable -> 0x01dd }
        com.tencent.feedback.common.e.b(r2, r3);	 Catch:{ Throwable -> 0x01dd }
        r0 = r11.c;	 Catch:{ Throwable -> 0x01dd }
        r0 = a(r0, r5, r6, r7);	 Catch:{ Throwable -> 0x01dd }
        if (r0 != 0) goto L_0x01a5;
    L_0x018e:
        r0 = "record anrinfo fail";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01dd }
        com.tencent.feedback.common.e.d(r0, r2);	 Catch:{ Throwable -> 0x01dd }
        r0 = r11.a;
        r0.set(r1);
        goto L_0x0018;
    L_0x019e:
        r0 = r5.b;	 Catch:{ Throwable -> 0x01dd }
        r0 = r0.size();	 Catch:{ Throwable -> 0x01dd }
        goto L_0x017d;
    L_0x01a5:
        r0 = r11.a;	 Catch:{ Throwable -> 0x01dd }
        r2 = 3;
        r0.set(r2);	 Catch:{ Throwable -> 0x01dd }
        r0 = r5.d;	 Catch:{ Throwable -> 0x01dd }
        r2 = r5.a;	 Catch:{ Throwable -> 0x01dd }
        r0 = a(r12, r0, r2);	 Catch:{ Throwable -> 0x01dd }
        if (r0 != 0) goto L_0x01c8;
    L_0x01b5:
        r0 = "dump trace fail! %s";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01dd }
        r3 = 0;
        r2[r3] = r12;	 Catch:{ Throwable -> 0x01dd }
        com.tencent.feedback.common.e.c(r0, r2);	 Catch:{ Throwable -> 0x01dd }
        r0 = r11.a;
        r0.set(r1);
        goto L_0x0018;
    L_0x01c8:
        r0 = "backup trace success %s";
        r2 = 1;
        r2 = new java.lang.Object[r2];	 Catch:{ Throwable -> 0x01dd }
        r3 = 0;
        r4 = r5.d;	 Catch:{ Throwable -> 0x01dd }
        r2[r3] = r4;	 Catch:{ Throwable -> 0x01dd }
        com.tencent.feedback.common.e.a(r0, r2);	 Catch:{ Throwable -> 0x01dd }
        r0 = r11.a;
        r0.set(r1);
        goto L_0x0018;
    L_0x01dd:
        r0 = move-exception;
        r2 = com.tencent.feedback.common.e.a(r0);	 Catch:{ all -> 0x0202 }
        if (r2 != 0) goto L_0x01e7;
    L_0x01e4:
        r0.printStackTrace();	 Catch:{ all -> 0x0202 }
    L_0x01e7:
        r2 = "handle anr error %s";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x0202 }
        r4 = 0;
        r0 = r0.getClass();	 Catch:{ all -> 0x0202 }
        r0 = r0.toString();	 Catch:{ all -> 0x0202 }
        r3[r4] = r0;	 Catch:{ all -> 0x0202 }
        com.tencent.feedback.common.e.d(r2, r3);	 Catch:{ all -> 0x0202 }
        r0 = r11.a;
        r0.set(r1);
        goto L_0x0018;
    L_0x0202:
        r0 = move-exception;
        r2 = r11.a;
        r2.set(r1);
        throw r0;
    L_0x0209:
        r2 = r4;
        goto L_0x006b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.feedback.anr.b.a(java.lang.String):void");
    }

    private static ProcessErrorStateInfo a(Context context, long j) {
        long j2 = TracerConfig.LOG_FLUSH_DURATION < 
/*
Method generation error in method: com.tencent.feedback.anr.b.a(android.content.Context, long):android.app.ActivityManager$ProcessErrorStateInfo
jadx.core.utils.exceptions.CodegenException: Error generate insn: ?: TERNARY(r2_2 'j2' long) = (0 long), (wrap: long
  0x004a: SGET  (r2_5 long) =  com.tencent.upload.log.trace.TracerConfig.LOG_FLUSH_DURATION long) in method: com.tencent.feedback.anr.b.a(android.content.Context, long):android.app.ActivityManager$ProcessErrorStateInfo
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:203)
	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:100)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:50)
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
Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Unknown type in literalToString: void
	at jadx.core.codegen.TypeGen.literalToString(TypeGen.java:83)
	at jadx.core.codegen.TypeGen.literalToString(TypeGen.java:37)
	at jadx.core.codegen.InsnGen.lit(InsnGen.java:135)
	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:98)
	at jadx.core.codegen.ConditionGen.addCompare(ConditionGen.java:134)
	at jadx.core.codegen.ConditionGen.add(ConditionGen.java:60)
	at jadx.core.codegen.ConditionGen.wrap(ConditionGen.java:87)
	at jadx.core.codegen.ConditionGen.wrap(ConditionGen.java:53)
	at jadx.core.codegen.InsnGen.makeTernary(InsnGen.java:786)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:440)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 16 more

*/

        private static boolean a(Context context, a aVar, c cVar, CrashStrategyBean crashStrategyBean) {
            if (context == null || aVar == null || cVar == null || crashStrategyBean == null) {
                e.d("args error %s %s %s %s ", new Object[]{context, aVar, cVar, crashStrategyBean});
                return true;
            } else if (aVar.b == null || aVar.b.get("main") == null) {
                e.d("error can't get stacks of anr %s ,drop this anr error", new Object[]{aVar.b});
                return false;
            } else {
                try {
                    String str = (String) aVar.b.get("main");
                    int indexOf = str.indexOf("\n");
                    com.tencent.feedback.eup.e a = com.tencent.feedback.eup.b.a(context, cVar.g(), cVar.h(), cVar.j(), cVar.x(), aVar.a, "main", indexOf > 0 ? str.substring(0, indexOf) : ConfigBaseParser.DEFAULT_VALUE, "ANR_RQD_EXCEPTION", aVar.f, str, aVar.c, aVar.e, null);
                    a.C().putAll(aVar.b);
                    a.a((byte) 3);
                    a.h(aVar.d);
                    boolean a2 = com.tencent.feedback.eup.c.a(context).a(a, crashStrategyBean);
                    e.b("sha1:%s %d", new Object[]{a.o(), Integer.valueOf(a.m())});
                    e.b("handle anr %b", new Object[]{Boolean.valueOf(a2)});
                    return a2;
                } catch (Throwable th) {
                    if (!e.a(th)) {
                        th.printStackTrace();
                    }
                    e.d("recordANR error %s", new Object[]{th.getClass().getName() + ":" + th.getMessage()});
                    return false;
                }
            }
        }

        private static boolean a(String str, String str2, String str3) {
            BufferedWriter bufferedWriter;
            Throwable e;
            f a = a.a(str3, str, true);
            if (a == null || a.d == null || a.d.size() <= 0) {
                e.d("not found trace dump for %s", new Object[]{str3});
                return false;
            }
            File file = new File(str2);
            try {
                if (!file.exists()) {
                    if (!file.getParentFile().exists()) {
                        file.getParentFile().mkdirs();
                    }
                    file.createNewFile();
                }
                if (file.exists() && file.canWrite()) {
                    BufferedWriter bufferedWriter2 = null;
                    try {
                        bufferedWriter = new BufferedWriter(new FileWriter(file, false));
                        try {
                            String[] strArr = (String[]) a.d.get("main");
                            if (strArr != null && strArr.length >= 3) {
                                String str4 = strArr[0];
                                bufferedWriter.write("\"main\" tid=" + strArr[2] + " :\n" + str4 + "\n" + strArr[1] + "\n\n");
                                bufferedWriter.flush();
                            }
                            for (Entry entry : a.d.entrySet()) {
                                if (!(((String) entry.getKey()).equals("main") || entry.getValue() == null || ((String[]) entry.getValue()).length < 3)) {
                                    String str5 = ((String[]) entry.getValue())[0];
                                    bufferedWriter.write("\"" + ((String) entry.getKey()) + "\" tid=" + ((String[]) entry.getValue())[2] + " :\n" + str5 + "\n" + ((String[]) entry.getValue())[1] + "\n\n");
                                    bufferedWriter.flush();
                                }
                            }
                            try {
                                bufferedWriter.close();
                            } catch (Throwable e2) {
                                if (!e.a(e2)) {
                                    e2.printStackTrace();
                                }
                            }
                            return true;
                        } catch (IOException e3) {
                            e2 = e3;
                            bufferedWriter2 = bufferedWriter;
                            try {
                                if (!e.a(e2)) {
                                    e2.printStackTrace();
                                }
                                e.d("dump trace fail %s", new Object[]{e2.getClass().getName() + ":" + e2.getMessage()});
                                if (bufferedWriter2 != null) {
                                    try {
                                        bufferedWriter2.close();
                                    } catch (Throwable e22) {
                                        if (!e.a(e22)) {
                                            e22.printStackTrace();
                                        }
                                    }
                                }
                                return false;
                            } catch (Throwable th) {
                                e22 = th;
                                bufferedWriter = bufferedWriter2;
                                if (bufferedWriter != null) {
                                    try {
                                        bufferedWriter.close();
                                    } catch (Throwable e4) {
                                        if (!e.a(e4)) {
                                            e4.printStackTrace();
                                        }
                                    }
                                }
                                throw e22;
                            }
                        } catch (Throwable th2) {
                            e22 = th2;
                            if (bufferedWriter != null) {
                                bufferedWriter.close();
                            }
                            throw e22;
                        }
                    } catch (IOException e5) {
                        e22 = e5;
                        if (e.a(e22)) {
                            e22.printStackTrace();
                        }
                        e.d("dump trace fail %s", new Object[]{e22.getClass().getName() + ":" + e22.getMessage()});
                        if (bufferedWriter2 != null) {
                            bufferedWriter2.close();
                        }
                        return false;
                    } catch (Throwable th3) {
                        e22 = th3;
                        bufferedWriter = null;
                        if (bufferedWriter != null) {
                            bufferedWriter.close();
                        }
                        throw e22;
                    }
                }
                e.d("backup file create fail %s", new Object[]{str2});
                return false;
            } catch (Throwable e222) {
                if (!e.a(e222)) {
                    e222.printStackTrace();
                }
                e.d("backup file create error! %s  %s", new Object[]{e222.getClass().getName() + ":" + e222.getMessage(), str2});
                return false;
            }
        }

        public final boolean a() {
            return this.a.get() != 0;
        }
    }
