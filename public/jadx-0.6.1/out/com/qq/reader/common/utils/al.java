package com.qq.reader.common.utils;

import android.os.Build.VERSION;
import android.os.StatFs;
import com.qq.reader.common.monitor.debug.c;

/* compiled from: StorageUtils */
public class al {
    private static final String a = al.class.getSimpleName();

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<com.qq.reader.common.utils.StorageBean> a(android.content.Context r18) {
        /*
        r2 = "storage";
        r0 = r18;
        r2 = r0.getSystemService(r2);
        r2 = (android.os.storage.StorageManager) r2;
        r3 = r2.getClass();	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r4 = "getVolumeList";
        r5 = 0;
        r5 = new java.lang.Class[r5];	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r5 = r3.getMethod(r4, r5);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r3 = "android.os.storage.StorageVolume";
        r3 = java.lang.Class.forName(r3);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r4 = "getPath";
        r6 = 0;
        r6 = new java.lang.Class[r6];	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r11 = r3.getMethod(r4, r6);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r4 = "isRemovable";
        r6 = 0;
        r6 = new java.lang.Class[r6];	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r12 = r3.getMethod(r4, r6);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r4 = 0;
        r6 = android.os.Build.VERSION.SDK_INT;	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r7 = 19;
        if (r6 <= r7) goto L_0x0141;
    L_0x003b:
        r6 = "getState";
        r7 = 0;
        r7 = new java.lang.Class[r7];	 Catch:{ NoSuchMethodException -> 0x013d, ClassNotFoundException -> 0x017f, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r3 = r3.getMethod(r6, r7);	 Catch:{ NoSuchMethodException -> 0x013d, ClassNotFoundException -> 0x017f, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r10 = r3;
    L_0x0046:
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r13 = r5.invoke(r2, r3);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r14 = java.lang.reflect.Array.getLength(r13);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r4 = new java.util.ArrayList;	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r4.<init>();	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r2 = 0;
        r5 = r2;
    L_0x0058:
        if (r5 >= r14) goto L_0x0189;
    L_0x005a:
        r6 = java.lang.reflect.Array.get(r13, r5);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r2 = r11.invoke(r6, r2);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r2 = (java.lang.String) r2;	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r3 = r12.invoke(r6, r3);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r3 = (java.lang.Boolean) r3;	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r15 = r3.booleanValue();	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        if (r10 == 0) goto L_0x0144;
    L_0x0076:
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r3 = r10.invoke(r6, r3);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r3 = (java.lang.String) r3;	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
    L_0x007f:
        r8 = 0;
        r6 = 0;
        r16 = "mounted";
        r0 = r16;
        r16 = r0.equals(r3);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        if (r16 == 0) goto L_0x0096;
    L_0x008e:
        r8 = a(r2);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r6 = b(r2);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
    L_0x0096:
        r16 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r16.<init>();	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r17 = "path==";
        r16 = r16.append(r17);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r0 = r16;
        r16 = r0.append(r2);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r17 = " ,removable==";
        r16 = r16.append(r17);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r0 = r16;
        r16 = r0.append(r15);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r17 = ",state==";
        r16 = r16.append(r17);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r0 = r16;
        r16 = r0.append(r3);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r17 = ",total size==";
        r16 = r16.append(r17);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r0 = r16;
        r16 = r0.append(r8);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r17 = "(";
        r16 = r16.append(r17);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r17 = a(r8);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r16 = r16.append(r17);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r17 = ")";
        r16 = r16.append(r17);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r17 = ",availale size==";
        r16 = r16.append(r17);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r0 = r16;
        r16 = r0.append(r6);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r17 = "(";
        r16 = r16.append(r17);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r17 = a(r6);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r16 = r16.append(r17);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r17 = ")";
        r16 = r16.append(r17);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r16 = r16.toString();	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r17 = a;	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r0 = r17;
        r1 = r16;
        com.qq.reader.common.monitor.debug.c.e(r0, r1);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r16 = new com.qq.reader.common.utils.StorageBean;	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r16.<init>();	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r0 = r16;
        r0.b(r6);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r0 = r16;
        r0.a(r8);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r0 = r16;
        r0.b(r3);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r0 = r16;
        r0.a(r2);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r0 = r16;
        r0.a(r15);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r0 = r16;
        r4.add(r0);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r2 = r5 + 1;
        r5 = r2;
        goto L_0x0058;
    L_0x013d:
        r3 = move-exception;
        r3.printStackTrace();	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
    L_0x0141:
        r10 = r4;
        goto L_0x0046;
    L_0x0144:
        r3 = android.os.Build.VERSION.SDK_INT;	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r6 = 19;
        if (r3 < r6) goto L_0x0155;
    L_0x014a:
        r3 = new java.io.File;	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r3.<init>(r2);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r3 = android.os.Environment.getStorageState(r3);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        goto L_0x007f;
    L_0x0155:
        if (r15 == 0) goto L_0x0185;
    L_0x0157:
        r3 = new java.io.File;	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r3.<init>(r2);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r3 = android.support.v4.os.a.a(r3);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
    L_0x0160:
        r6 = android.os.Environment.getExternalStorageDirectory();	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r7 = a;	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r8 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r8.<init>();	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r9 = "externalStorageDirectory==";
        r8 = r8.append(r9);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r6 = r8.append(r6);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        r6 = r6.toString();	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        com.qq.reader.common.monitor.debug.c.e(r7, r6);	 Catch:{ ClassNotFoundException -> 0x017f, NoSuchMethodException -> 0x018b, InvocationTargetException -> 0x0190, IllegalAccessException -> 0x0195, Exception -> 0x019a }
        goto L_0x007f;
    L_0x017f:
        r2 = move-exception;
        r2.printStackTrace();
    L_0x0183:
        r2 = 0;
    L_0x0184:
        return r2;
    L_0x0185:
        r3 = "mounted";
        goto L_0x0160;
    L_0x0189:
        r2 = r4;
        goto L_0x0184;
    L_0x018b:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0183;
    L_0x0190:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0183;
    L_0x0195:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0183;
    L_0x019a:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0183;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.utils.al.a(android.content.Context):java.util.ArrayList<com.qq.reader.common.utils.StorageBean>");
    }

    public static long a(String str) {
        try {
            long blockSizeLong;
            long blockCountLong;
            StatFs statFs = new StatFs(str);
            if (VERSION.SDK_INT >= 18) {
                blockSizeLong = statFs.getBlockSizeLong();
                blockCountLong = statFs.getBlockCountLong();
            } else {
                blockSizeLong = (long) statFs.getBlockSize();
                blockCountLong = (long) statFs.getBlockCount();
            }
            return blockCountLong * blockSizeLong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static long b(String str) {
        try {
            long blockSizeLong;
            long availableBlocksLong;
            StatFs statFs = new StatFs(str);
            if (VERSION.SDK_INT >= 18) {
                blockSizeLong = statFs.getBlockSizeLong();
                availableBlocksLong = statFs.getAvailableBlocksLong();
            } else {
                blockSizeLong = (long) statFs.getBlockSize();
                availableBlocksLong = (long) statFs.getAvailableBlocks();
            }
            return availableBlocksLong * blockSizeLong;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public static String a(long j) {
        if (j <= 0) {
            return "0";
        }
        if (((double) j) / 1.073741824E9d >= 1.0d) {
            return String.format("%.2fGB", new Object[]{Double.valueOf(((double) j) / 1.073741824E9d)});
        }
        double d = ((double) j) / 1048576.0d;
        c.e("GB", "gbvalue=" + d);
        if (d >= 1.0d) {
            return String.format("%.2fMB", new Object[]{Double.valueOf(d)});
        }
        return String.format("%.2fKB", new Object[]{Double.valueOf((double) (j / 1024))});
    }
}
