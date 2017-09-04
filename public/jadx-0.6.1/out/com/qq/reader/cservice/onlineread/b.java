package com.qq.reader.cservice.onlineread;

import android.content.Context;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ao;
import java.io.File;
import java.io.IOException;

/* compiled from: OnlineFileParser */
public class b {
    public static void a(OnlineTag onlineTag, int i) throws IOException {
        String b = onlineTag.b(i);
        ao.b(b, onlineTag.a(i));
        File file = new File(b);
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    public static String a(Context context, OnlineTag onlineTag) {
        String a = onlineTag.a(onlineTag.s());
        String a2 = a(context, onlineTag.k(), onlineTag.s());
        try {
            a(a, a2);
            return a2;
        } catch (IOException e) {
            a2 = e;
            a2.printStackTrace();
            return "";
        } finally {
            c(context, onlineTag);
            d(context, onlineTag);
        }
    }

    private static String a(Context context, String str, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(context.getFilesDir().getAbsolutePath());
        stringBuffer.append("/");
        stringBuffer.append(str);
        ab.a(new File(stringBuffer.toString()));
        stringBuffer.append("/");
        stringBuffer.append(i);
        stringBuffer.append(".deb");
        return stringBuffer.toString();
    }

    public static void b(Context context, OnlineTag onlineTag) {
        if (onlineTag != null && context != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(context.getFilesDir().getAbsolutePath());
            stringBuffer.append("/");
            stringBuffer.append(onlineTag.k());
            ao.c(new File(stringBuffer.toString()));
        }
    }

    private static void c(Context context, OnlineTag onlineTag) {
        ao.a(new File(a(context, onlineTag.k(), onlineTag.s() - 5)));
    }

    private static void d(Context context, OnlineTag onlineTag) {
        ao.a(new File(a(context, onlineTag.k(), onlineTag.s() + 5)));
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.lang.String r10, java.lang.String r11) throws java.io.IOException {
        /*
        r2 = 0;
        r3 = new java.io.File;	 Catch:{ Exception -> 0x00f3, all -> 0x00d5 }
        r3.<init>(r10);	 Catch:{ Exception -> 0x00f3, all -> 0x00d5 }
        r1 = new java.io.File;	 Catch:{ Exception -> 0x00fb, all -> 0x00d5 }
        r1.<init>(r11);	 Catch:{ Exception -> 0x00fb, all -> 0x00d5 }
        if (r1 == 0) goto L_0x0016;
    L_0x000d:
        r0 = r1.exists();	 Catch:{ Exception -> 0x0105, all -> 0x00d5 }
        if (r0 == 0) goto L_0x0016;
    L_0x0013:
        r1.delete();	 Catch:{ Exception -> 0x0105, all -> 0x00d5 }
    L_0x0016:
        r4 = r3.length();	 Catch:{ Exception -> 0x0105, all -> 0x00d5 }
        r0 = (int) r4;	 Catch:{ Exception -> 0x0105, all -> 0x00d5 }
        r0 = new byte[r0];	 Catch:{ Exception -> 0x0105, all -> 0x00d5 }
        r5 = new java.io.DataInputStream;	 Catch:{ Exception -> 0x0105, all -> 0x00d5 }
        r4 = new java.io.FileInputStream;	 Catch:{ Exception -> 0x0105, all -> 0x00d5 }
        r4.<init>(r3);	 Catch:{ Exception -> 0x0105, all -> 0x00d5 }
        r5.<init>(r4);	 Catch:{ Exception -> 0x0105, all -> 0x00d5 }
        r5.readFully(r0);	 Catch:{ Exception -> 0x010e, all -> 0x00db }
        r0 = com.qq.reader.common.utils.a.b.b(r0);	 Catch:{ Exception -> 0x010e, all -> 0x00db }
        r4 = new java.io.BufferedInputStream;	 Catch:{ Exception -> 0x010e, all -> 0x00db }
        r6 = new java.io.ByteArrayInputStream;	 Catch:{ Exception -> 0x010e, all -> 0x00db }
        r6.<init>(r0);	 Catch:{ Exception -> 0x010e, all -> 0x00db }
        r4.<init>(r6);	 Catch:{ Exception -> 0x010e, all -> 0x00db }
        r7 = new java.io.FileOutputStream;	 Catch:{ Exception -> 0x0117, all -> 0x00e1 }
        r7.<init>(r1);	 Catch:{ Exception -> 0x0117, all -> 0x00e1 }
        r6 = new java.io.BufferedOutputStream;	 Catch:{ Exception -> 0x0120, all -> 0x00e7 }
        r6.<init>(r7);	 Catch:{ Exception -> 0x0120, all -> 0x00e7 }
        r0 = 1050; // 0x41a float:1.471E-42 double:5.19E-321;
        r0 = new byte[r0];	 Catch:{ Exception -> 0x0052, all -> 0x00ed }
    L_0x0046:
        r2 = r4.read(r0);	 Catch:{ Exception -> 0x0052, all -> 0x00ed }
        r8 = -1;
        if (r2 == r8) goto L_0x00c0;
    L_0x004d:
        r8 = 0;
        r6.write(r0, r8, r2);	 Catch:{ Exception -> 0x0052, all -> 0x00ed }
        goto L_0x0046;
    L_0x0052:
        r0 = move-exception;
        r2 = r3;
        r3 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r7;
    L_0x0058:
        if (r2 == 0) goto L_0x0063;
    L_0x005a:
        r7 = r2.exists();	 Catch:{ all -> 0x00aa }
        if (r7 == 0) goto L_0x0063;
    L_0x0060:
        r2.delete();	 Catch:{ all -> 0x00aa }
    L_0x0063:
        if (r1 == 0) goto L_0x006e;
    L_0x0065:
        r2 = r1.exists();	 Catch:{ all -> 0x00aa }
        if (r2 == 0) goto L_0x006e;
    L_0x006b:
        r1.delete();	 Catch:{ all -> 0x00aa }
    L_0x006e:
        r1 = "Utility";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00aa }
        r2.<init>();	 Catch:{ all -> 0x00aa }
        r7 = "decryptToCache failed ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x00aa }
        r7 = r0.getMessage();	 Catch:{ all -> 0x00aa }
        r2 = r2.append(r7);	 Catch:{ all -> 0x00aa }
        r2 = r2.toString();	 Catch:{ all -> 0x00aa }
        com.qq.reader.common.monitor.f.a(r1, r2);	 Catch:{ all -> 0x00aa }
        r1 = new java.io.IOException;	 Catch:{ all -> 0x00aa }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00aa }
        r2.<init>();	 Catch:{ all -> 0x00aa }
        r7 = "onlineUnZip failed ";
        r2 = r2.append(r7);	 Catch:{ all -> 0x00aa }
        r0 = r0.getMessage();	 Catch:{ all -> 0x00aa }
        r0 = r2.append(r0);	 Catch:{ all -> 0x00aa }
        r0 = r0.toString();	 Catch:{ all -> 0x00aa }
        r1.<init>(r0);	 Catch:{ all -> 0x00aa }
        throw r1;	 Catch:{ all -> 0x00aa }
    L_0x00aa:
        r0 = move-exception;
    L_0x00ab:
        if (r4 == 0) goto L_0x00b0;
    L_0x00ad:
        r4.close();
    L_0x00b0:
        if (r5 == 0) goto L_0x00b5;
    L_0x00b2:
        r5.close();
    L_0x00b5:
        if (r6 == 0) goto L_0x00ba;
    L_0x00b7:
        r6.close();
    L_0x00ba:
        if (r3 == 0) goto L_0x00bf;
    L_0x00bc:
        r3.close();
    L_0x00bf:
        throw r0;
    L_0x00c0:
        if (r5 == 0) goto L_0x00c5;
    L_0x00c2:
        r5.close();
    L_0x00c5:
        if (r6 == 0) goto L_0x00ca;
    L_0x00c7:
        r6.close();
    L_0x00ca:
        if (r7 == 0) goto L_0x00cf;
    L_0x00cc:
        r7.close();
    L_0x00cf:
        if (r4 == 0) goto L_0x00d4;
    L_0x00d1:
        r4.close();
    L_0x00d4:
        return;
    L_0x00d5:
        r0 = move-exception;
        r3 = r2;
        r4 = r2;
        r5 = r2;
        r6 = r2;
        goto L_0x00ab;
    L_0x00db:
        r0 = move-exception;
        r3 = r2;
        r4 = r5;
        r6 = r2;
        r5 = r2;
        goto L_0x00ab;
    L_0x00e1:
        r0 = move-exception;
        r3 = r4;
        r6 = r2;
        r4 = r5;
        r5 = r2;
        goto L_0x00ab;
    L_0x00e7:
        r0 = move-exception;
        r3 = r4;
        r6 = r7;
        r4 = r5;
        r5 = r2;
        goto L_0x00ab;
    L_0x00ed:
        r0 = move-exception;
        r3 = r4;
        r4 = r5;
        r5 = r6;
        r6 = r7;
        goto L_0x00ab;
    L_0x00f3:
        r0 = move-exception;
        r1 = r2;
        r3 = r2;
        r4 = r2;
        r5 = r2;
        r6 = r2;
        goto L_0x0058;
    L_0x00fb:
        r0 = move-exception;
        r1 = r2;
        r4 = r2;
        r5 = r2;
        r6 = r2;
        r9 = r2;
        r2 = r3;
        r3 = r9;
        goto L_0x0058;
    L_0x0105:
        r0 = move-exception;
        r4 = r2;
        r5 = r2;
        r6 = r2;
        r9 = r2;
        r2 = r3;
        r3 = r9;
        goto L_0x0058;
    L_0x010e:
        r0 = move-exception;
        r4 = r5;
        r6 = r2;
        r5 = r2;
        r9 = r3;
        r3 = r2;
        r2 = r9;
        goto L_0x0058;
    L_0x0117:
        r0 = move-exception;
        r6 = r2;
        r9 = r4;
        r4 = r5;
        r5 = r2;
        r2 = r3;
        r3 = r9;
        goto L_0x0058;
    L_0x0120:
        r0 = move-exception;
        r6 = r7;
        r9 = r4;
        r4 = r5;
        r5 = r2;
        r2 = r3;
        r3 = r9;
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.cservice.onlineread.b.a(java.lang.String, java.lang.String):void");
    }

    public static void a(String str) {
        ao.a(new File(str));
    }

    public static String a(Context context, String str, long j, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(context.getCacheDir().getAbsolutePath());
        stringBuffer.append("/");
        stringBuffer.append(j);
        ab.a(new File(stringBuffer.toString()));
        stringBuffer.append("/");
        stringBuffer.append(i);
        stringBuffer.append(".s");
        String stringBuffer2 = stringBuffer.toString();
        try {
            a(str, stringBuffer2);
            return stringBuffer2;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
