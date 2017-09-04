package com.tencent.beacon.b.a;

import android.content.Context;
import com.tencent.beacon.e.a;

/* compiled from: ProGuard */
public final class c {
    private static int a = -22876499;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List<com.tencent.beacon.b.a.b> a(android.content.Context r12) {
        /*
        r9 = 1;
        r10 = 0;
        r8 = 0;
        r11 = com.tencent.beacon.b.a.e.a;
        monitor-enter(r11);
        r0 = " AppUseListDAO.queryAppUseData() start";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x00ab }
        com.tencent.beacon.e.a.b(r0, r1);	 Catch:{ all -> 0x00ab }
        if (r12 != 0) goto L_0x001d;
    L_0x0011:
        r0 = " AppUseListDAO.queryAppUseData() context null ";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x00ab }
        com.tencent.beacon.e.a.d(r0, r1);	 Catch:{ all -> 0x00ab }
        monitor-exit(r11);	 Catch:{ all -> 0x00ab }
        r0 = r8;
    L_0x001c:
        return r0;
    L_0x001d:
        r0 = com.tencent.beacon.b.a.e.a(r12);	 Catch:{ Exception -> 0x00f8, all -> 0x00df }
        r0 = r0.getWritableDatabase();	 Catch:{ Exception -> 0x00f8, all -> 0x00df }
        r3 = "_d = 0 ";
        r1 = "t_apple";
        r2 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x00f8, all -> 0x00df }
        r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x00fb, all -> 0x00f6 }
        r0.<init>();	 Catch:{ Exception -> 0x00fb, all -> 0x00f6 }
    L_0x0039:
        r2 = r1.moveToNext();	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        if (r2 == 0) goto L_0x00b0;
    L_0x003f:
        r3 = new com.tencent.beacon.b.a.b;	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r3.<init>();	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r2 = "_id";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r2 = r1.getInt(r2);	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r4 = (long) r2;	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r3.a = r4;	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r2 = new java.lang.String;	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r4 = "_a";
        r4 = r1.getColumnIndex(r4);	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r4 = r1.getBlob(r4);	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r5 = 1;
        r6 = "*^@K#K@!";
        r4 = com.tencent.beacon.net.a.b(r4, r5, r6);	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r2.<init>(r4);	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r3.b = r2;	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r2 = "_b";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r2 = r1.getInt(r2);	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r4 = a;	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r2 = r2 ^ r4;
        r3.c = r2;	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r2 = "_c";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r2 = r1.getInt(r2);	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        if (r2 != r9) goto L_0x00ae;
    L_0x0089:
        r2 = r9;
    L_0x008a:
        r3.d = r2;	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r0.add(r3);	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        goto L_0x0039;
    L_0x0090:
        r2 = move-exception;
        r8 = r0;
        r0 = r1;
    L_0x0093:
        if (r0 == 0) goto L_0x009e;
    L_0x0095:
        r1 = r0.isClosed();	 Catch:{ all -> 0x00ab }
        if (r1 != 0) goto L_0x009e;
    L_0x009b:
        r0.close();	 Catch:{ all -> 0x00ab }
    L_0x009e:
        r0 = " AppUseListDAO.queryAppUseData() end";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x00ab }
        com.tencent.beacon.e.a.b(r0, r1);	 Catch:{ all -> 0x00ab }
        r0 = r8;
    L_0x00a8:
        monitor-exit(r11);	 Catch:{ all -> 0x00ab }
        goto L_0x001c;
    L_0x00ab:
        r0 = move-exception;
        monitor-exit(r11);
        throw r0;
    L_0x00ae:
        r2 = r10;
        goto L_0x008a;
    L_0x00b0:
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r3 = "queryAppUseData: ";
        r2.<init>(r3);	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r3 = r0.size();	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        com.tencent.beacon.e.a.a(r2, r3);	 Catch:{ Exception -> 0x0090, all -> 0x00f6 }
        if (r1 == 0) goto L_0x00d5;
    L_0x00cc:
        r2 = r1.isClosed();	 Catch:{ all -> 0x00ab }
        if (r2 != 0) goto L_0x00d5;
    L_0x00d2:
        r1.close();	 Catch:{ all -> 0x00ab }
    L_0x00d5:
        r1 = " AppUseListDAO.queryAppUseData() end";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x00ab }
        com.tencent.beacon.e.a.b(r1, r2);	 Catch:{ all -> 0x00ab }
        goto L_0x00a8;
    L_0x00df:
        r0 = move-exception;
        r1 = r8;
    L_0x00e1:
        if (r1 == 0) goto L_0x00ec;
    L_0x00e3:
        r2 = r1.isClosed();	 Catch:{ all -> 0x00ab }
        if (r2 != 0) goto L_0x00ec;
    L_0x00e9:
        r1.close();	 Catch:{ all -> 0x00ab }
    L_0x00ec:
        r1 = " AppUseListDAO.queryAppUseData() end";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x00ab }
        com.tencent.beacon.e.a.b(r1, r2);	 Catch:{ all -> 0x00ab }
        throw r0;	 Catch:{ all -> 0x00ab }
    L_0x00f6:
        r0 = move-exception;
        goto L_0x00e1;
    L_0x00f8:
        r0 = move-exception;
        r0 = r8;
        goto L_0x0093;
    L_0x00fb:
        r0 = move-exception;
        r0 = r1;
        goto L_0x0093;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.beacon.b.a.c.a(android.content.Context):java.util.List<com.tencent.beacon.b.a.b>");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r11, java.util.List<com.tencent.beacon.b.a.b> r12, boolean r13) {
        /*
        r0 = 0;
        r3 = 1;
        r1 = 0;
        r4 = com.tencent.beacon.b.a.e.a;
        monitor-enter(r4);
        r2 = " insertAppUseData start";
        r5 = 0;
        r5 = new java.lang.Object[r5];	 Catch:{ all -> 0x009f }
        com.tencent.beacon.e.a.b(r2, r5);	 Catch:{ all -> 0x009f }
        if (r11 == 0) goto L_0x0019;
    L_0x0011:
        if (r12 == 0) goto L_0x0019;
    L_0x0013:
        r2 = r12.size();	 Catch:{ all -> 0x009f }
        if (r2 != 0) goto L_0x001c;
    L_0x0019:
        monitor-exit(r4);	 Catch:{ all -> 0x009f }
        r0 = r1;
    L_0x001b:
        return r0;
    L_0x001c:
        r2 = com.tencent.beacon.b.a.e.a(r11);	 Catch:{ Throwable -> 0x00cd, all -> 0x00b7 }
        r2 = r2.getWritableDatabase();	 Catch:{ Throwable -> 0x00cd, all -> 0x00b7 }
        r2.beginTransaction();	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r5 = r12.iterator();	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
    L_0x002b:
        r0 = r5.hasNext();	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        if (r0 == 0) goto L_0x00a6;
    L_0x0031:
        r0 = r5.next();	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r0 = (com.tencent.beacon.b.a.b) r0;	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r6 = new android.content.ContentValues;	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r6.<init>();	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r7 = "_a";
        r8 = r0.b;	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r8 = r8.getBytes();	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r9 = 1;
        r10 = "*^@K#K@!";
        r8 = com.tencent.beacon.net.a.a(r8, r9, r10);	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r6.put(r7, r8);	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r7 = "_b";
        r8 = r0.c;	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r9 = a;	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r8 = r8 ^ r9;
        r8 = java.lang.Integer.valueOf(r8);	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r6.put(r7, r8);	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r7 = "_c";
        r0 = r0.d;	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        if (r0 == 0) goto L_0x00a2;
    L_0x0066:
        r0 = r3;
    L_0x0067:
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r6.put(r7, r0);	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r7 = "_d";
        if (r13 == 0) goto L_0x00a4;
    L_0x0073:
        r0 = r3;
    L_0x0074:
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r6.put(r7, r0);	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r0 = "t_apple";
        r7 = 0;
        r8 = 4;
        r2.insertWithOnConflict(r0, r7, r6, r8);	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        goto L_0x002b;
    L_0x0084:
        r0 = move-exception;
        r0 = r2;
    L_0x0086:
        r2 = "insertAppUseData failure!";
        r3 = 0;
        r3 = new java.lang.Object[r3];	 Catch:{ all -> 0x00c9 }
        com.tencent.beacon.e.a.b(r2, r3);	 Catch:{ all -> 0x00c9 }
        r0.endTransaction();	 Catch:{ all -> 0x009f }
        r0 = "insertAppUseData end";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x009f }
        com.tencent.beacon.e.a.b(r0, r2);	 Catch:{ all -> 0x009f }
        r0 = r1;
    L_0x009c:
        monitor-exit(r4);	 Catch:{ all -> 0x009f }
        goto L_0x001b;
    L_0x009f:
        r0 = move-exception;
        monitor-exit(r4);
        throw r0;
    L_0x00a2:
        r0 = r1;
        goto L_0x0067;
    L_0x00a4:
        r0 = r1;
        goto L_0x0074;
    L_0x00a6:
        r2.setTransactionSuccessful();	 Catch:{ Throwable -> 0x0084, all -> 0x00c7 }
        r2.endTransaction();	 Catch:{ all -> 0x009f }
        r0 = "insertAppUseData end";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x009f }
        com.tencent.beacon.e.a.b(r0, r1);	 Catch:{ all -> 0x009f }
        r0 = r3;
        goto L_0x009c;
    L_0x00b7:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x00ba:
        r2.endTransaction();	 Catch:{ all -> 0x009f }
        r1 = "insertAppUseData end";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x009f }
        com.tencent.beacon.e.a.b(r1, r2);	 Catch:{ all -> 0x009f }
        throw r0;	 Catch:{ all -> 0x009f }
    L_0x00c7:
        r0 = move-exception;
        goto L_0x00ba;
    L_0x00c9:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x00ba;
    L_0x00cd:
        r2 = move-exception;
        goto L_0x0086;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.beacon.b.a.c.a(android.content.Context, java.util.List, boolean):boolean");
    }

    public static boolean b(Context context) {
        boolean z = false;
        synchronized (e.a) {
            a.b(" updateAppUseData start", new Object[0]);
            if (context == null) {
            } else {
                try {
                    e.a(context).getWritableDatabase().execSQL("update t_apple set _d = 1 where _d = 0");
                    z = true;
                    a.b("updateAppUseData end", new Object[0]);
                } catch (Throwable th) {
                    a.b("updateAppUseData end", new Object[0]);
                }
            }
        }
        return z;
    }

    public static boolean c(Context context) {
        boolean z = false;
        synchronized (e.a) {
            a.b(" deleteAppUseData start", new Object[0]);
            if (context == null) {
                a.a(" delete() context is null arg", new Object[0]);
            } else {
                try {
                    int delete = e.a(context).getWritableDatabase().delete("t_apple", null, null);
                    a.b(" deleted num: " + delete, new Object[0]);
                    if (delete > 0) {
                        z = true;
                    }
                    a.b(" deleteAppUseData end", new Object[0]);
                } catch (Throwable th) {
                    a.b(" deleteAppUseData end", new Object[0]);
                }
            }
        }
        return z;
    }
}
