package com.tencent.beacon.b.a;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* compiled from: ProGuard */
public final class a {
    private long a = -1;
    private int b = -1;
    private int c = -1;
    private long d = -1;
    private byte[] e = null;
    private long f = 0;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.List a(android.content.Context r11) {
        /*
        r9 = 0;
        r10 = com.tencent.beacon.b.a.e.a;
        monitor-enter(r10);
        r0 = " AnalyticsDAO.queryReqData() start";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x00b6 }
        com.tencent.beacon.e.a.b(r0, r1);	 Catch:{ all -> 0x00b6 }
        if (r11 != 0) goto L_0x001b;
    L_0x000f:
        r0 = " AnalyticsDAO.queryReqData() context null ";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x00b6 }
        com.tencent.beacon.e.a.d(r0, r1);	 Catch:{ all -> 0x00b6 }
        monitor-exit(r10);	 Catch:{ all -> 0x00b6 }
        r0 = r9;
    L_0x001a:
        return r0;
    L_0x001b:
        r0 = com.tencent.beacon.b.a.e.a(r11);	 Catch:{ Exception -> 0x00b9, all -> 0x00d1 }
        r0 = r0.getWritableDatabase();	 Catch:{ Exception -> 0x00b9, all -> 0x00d1 }
        r1 = "t_req_data";
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = "_time DESC ";
        r8 = "1";
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ Exception -> 0x00b9, all -> 0x00d1 }
        r0 = r1.moveToNext();	 Catch:{ Exception -> 0x00ea, all -> 0x00e8 }
        if (r0 == 0) goto L_0x0093;
    L_0x003b:
        r0 = new java.util.ArrayList;	 Catch:{ Exception -> 0x00ea, all -> 0x00e8 }
        r0.<init>();	 Catch:{ Exception -> 0x00ea, all -> 0x00e8 }
        r2 = "_id";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r2 = r1.getInt(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r0.add(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r2 = "_rid";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r2 = r1.getString(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r0.add(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r2 = "_time";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r2 = r1.getLong(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r2 = java.lang.Long.valueOf(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r0.add(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r2 = "_datas";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r2 = r1.getBlob(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r0.add(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r2 = "_cnt";
        r2 = r1.getColumnIndex(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r2 = r1.getInt(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r0.add(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e8 }
        r9 = r0;
    L_0x0093:
        if (r9 == 0) goto L_0x009e;
    L_0x0095:
        r0 = "queryReqData: 1";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ Exception -> 0x00ea, all -> 0x00e8 }
        com.tencent.beacon.e.a.a(r0, r2);	 Catch:{ Exception -> 0x00ea, all -> 0x00e8 }
    L_0x009e:
        if (r1 == 0) goto L_0x00a9;
    L_0x00a0:
        r0 = r1.isClosed();	 Catch:{ all -> 0x00b6 }
        if (r0 != 0) goto L_0x00a9;
    L_0x00a6:
        r1.close();	 Catch:{ all -> 0x00b6 }
    L_0x00a9:
        r0 = " AnalyticsDAO.queryReqData() end";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x00b6 }
        com.tencent.beacon.e.a.b(r0, r1);	 Catch:{ all -> 0x00b6 }
        r0 = r9;
    L_0x00b3:
        monitor-exit(r10);	 Catch:{ all -> 0x00b6 }
        goto L_0x001a;
    L_0x00b6:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x00b9:
        r0 = move-exception;
        r0 = r9;
    L_0x00bb:
        if (r0 == 0) goto L_0x00c6;
    L_0x00bd:
        r1 = r0.isClosed();	 Catch:{ all -> 0x00b6 }
        if (r1 != 0) goto L_0x00c6;
    L_0x00c3:
        r0.close();	 Catch:{ all -> 0x00b6 }
    L_0x00c6:
        r0 = " AnalyticsDAO.queryReqData() end";
        r1 = 0;
        r1 = new java.lang.Object[r1];	 Catch:{ all -> 0x00b6 }
        com.tencent.beacon.e.a.b(r0, r1);	 Catch:{ all -> 0x00b6 }
        r0 = r9;
        goto L_0x00b3;
    L_0x00d1:
        r0 = move-exception;
        r1 = r9;
    L_0x00d3:
        if (r1 == 0) goto L_0x00de;
    L_0x00d5:
        r2 = r1.isClosed();	 Catch:{ all -> 0x00b6 }
        if (r2 != 0) goto L_0x00de;
    L_0x00db:
        r1.close();	 Catch:{ all -> 0x00b6 }
    L_0x00de:
        r1 = " AnalyticsDAO.queryReqData() end";
        r2 = 0;
        r2 = new java.lang.Object[r2];	 Catch:{ all -> 0x00b6 }
        com.tencent.beacon.e.a.b(r1, r2);	 Catch:{ all -> 0x00b6 }
        throw r0;	 Catch:{ all -> 0x00b6 }
    L_0x00e8:
        r0 = move-exception;
        goto L_0x00d3;
    L_0x00ea:
        r0 = move-exception;
        r0 = r1;
        goto L_0x00bb;
    L_0x00ed:
        r2 = move-exception;
        r9 = r0;
        r0 = r1;
        goto L_0x00bb;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.beacon.b.a.a.a(android.content.Context):java.util.List");
    }

    public a(int i, int i2, long j, byte[] bArr) {
        this.b = i;
        this.c = i2;
        this.d = j;
        this.e = bArr;
        if (bArr != null) {
            this.f = (long) bArr.length;
        }
    }

    public final long a() {
        return this.a;
    }

    public final a a(long j) {
        this.a = j;
        return this;
    }

    public static boolean a(Context context, byte[] bArr, String str, int i) {
        boolean z = false;
        synchronized (e.a) {
            com.tencent.beacon.e.a.b(" AnalyticsDAO.insertReqData() start", new Object[0]);
            if (context == null || bArr == null || str == null) {
                com.tencent.beacon.e.a.d(" AnalyticsDAO.insertReqData() have null args", new Object[0]);
            } else {
                try {
                    SQLiteDatabase writableDatabase = e.a(context).getWritableDatabase();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("_rid", str);
                    contentValues.put("_time", Long.valueOf(new Date().getTime()));
                    contentValues.put("_cnt", Integer.valueOf(i));
                    contentValues.put("_datas", bArr);
                    if (writableDatabase.insert("t_req_data", null, contentValues) < 0) {
                        com.tencent.beacon.e.a.d(" AnalyticsDAO.insertReqData() failure! return", new Object[0]);
                    } else {
                        z = true;
                    }
                    com.tencent.beacon.e.a.b(" AnalyticsDAO.insertReqData() end", new Object[0]);
                } catch (Throwable th) {
                    com.tencent.beacon.e.a.b(" AnalyticsDAO.insertReqData() end", new Object[0]);
                }
            }
        }
        return z;
    }

    public final byte[] b() {
        return this.e;
    }

    public static boolean a(Context context, String str) {
        boolean z = false;
        synchronized (e.a) {
            com.tencent.beacon.e.a.b(" AnalyticsDAO.deleteReqData() start", new Object[0]);
            if (context == null) {
                com.tencent.beacon.e.a.a(" delete() context is null arg", new Object[0]);
            } else if (str == null || str.trim().equals("")) {
            } else {
                String str2 = "_rid = '" + str + "' ";
                com.tencent.beacon.e.a.b(" delete where: " + str2, new Object[0]);
                try {
                    int delete = e.a(context).getWritableDatabase().delete("t_req_data", str2, null);
                    com.tencent.beacon.e.a.b(" deleted num: " + delete, new Object[0]);
                    if (delete == 1) {
                        z = true;
                    }
                    com.tencent.beacon.e.a.b(" AnalyticsDAO.deleteReqData() end", new Object[0]);
                } catch (Throwable th) {
                    com.tencent.beacon.e.a.b(" AnalyticsDAO.deleteReqData() end", new Object[0]);
                }
            }
        }
        return z;
    }

    public static boolean a(Context context, a aVar) {
        boolean z = false;
        synchronized (e.a) {
            if (context == null || aVar == null) {
                com.tencent.beacon.e.a.a("AnalyticsDAO.insert() have null args", new Object[0]);
            } else {
                List arrayList = new ArrayList();
                arrayList.add(aVar);
                z = a(context, arrayList);
            }
        }
        return z;
    }

    public static boolean a(Context context, List<a> list) {
        boolean z;
        Throwable th;
        synchronized (e.a) {
            com.tencent.beacon.e.a.b(" AnalyticsDAO.insert() start", new Object[0]);
            if (context == null || list == null) {
                com.tencent.beacon.e.a.d(" AnalyticsDAO.insert() have null args", new Object[0]);
                return false;
            } else if (list.size() <= 0) {
                com.tencent.beacon.e.a.b(" list size == 0 return true", new Object[0]);
                return true;
            } else {
                SQLiteDatabase sQLiteDatabase = null;
                SQLiteDatabase writableDatabase;
                try {
                    writableDatabase = e.a(context).getWritableDatabase();
                    try {
                        writableDatabase.beginTransaction();
                        for (int i = 0; i < list.size(); i++) {
                            a aVar = (a) list.get(i);
                            long insert = writableDatabase.insert("t_event", "_id", a(aVar));
                            if (insert < 0) {
                                com.tencent.beacon.e.a.d(" AnalyticsDAO.insert() failure! return", new Object[0]);
                            }
                            aVar.a = insert;
                        }
                        writableDatabase.setTransactionSuccessful();
                        writableDatabase.endTransaction();
                        com.tencent.beacon.e.a.b(" AnalyticsDAO.insert() end", new Object[0]);
                        z = true;
                    } catch (Throwable th2) {
                        th = th2;
                        writableDatabase.endTransaction();
                        com.tencent.beacon.e.a.b(" AnalyticsDAO.insert() end", new Object[0]);
                        throw th;
                    }
                } catch (Throwable th3) {
                    writableDatabase = null;
                    th = th3;
                    writableDatabase.endTransaction();
                    com.tencent.beacon.e.a.b(" AnalyticsDAO.insert() end", new Object[0]);
                    throw th;
                }
            }
        }
    }

    public static int a(Context context, int[] iArr, long j) {
        int i = -1;
        synchronized (e.a) {
            com.tencent.beacon.e.a.b(" AnalyticsDAO.delete() start", new Object[0]);
            if (context == null) {
                com.tencent.beacon.e.a.a(" delete() context is null arg", new Object[0]);
                return -1;
            } else if (-1 > j) {
                return 0;
            } else {
                String str;
                String str2 = "_time >= -1 and _time <= " + j;
                if (iArr == null || iArr.length <= 0) {
                    str = str2;
                } else {
                    str = "";
                    for (int i2 : iArr) {
                        str = str + " or _type = " + i2;
                    }
                    str = str2 + " and ( " + str.substring(4) + " )";
                }
                com.tencent.beacon.e.a.b(" delete where: " + str, new Object[0]);
                try {
                    int delete = e.a(context).getWritableDatabase().delete("t_event", str, null);
                    com.tencent.beacon.e.a.b(" deleted num: " + delete, new Object[0]);
                    com.tencent.beacon.e.a.b(" AnalyticsDAO.delete() end", new Object[0]);
                    i = delete;
                } catch (Throwable th) {
                    com.tencent.beacon.e.a.b(" AnalyticsDAO.delete() end", new Object[0]);
                }
            }
        }
    }

    public static int a(Context context, Long[] lArr) {
        int i;
        Throwable th;
        int i2 = 0;
        synchronized (e.a) {
            com.tencent.beacon.e.a.b(" AnalyticsDAO.deleteList() start!", new Object[0]);
            if (context == null) {
                com.tencent.beacon.e.a.d(" deleteList() have null args!", new Object[0]);
                i2 = -1;
            } else if (lArr == null || lArr.length <= 0) {
            } else {
                try {
                    SQLiteDatabase writableDatabase = e.a(context).getWritableDatabase();
                    StringBuffer stringBuffer = new StringBuffer();
                    i = 0;
                    while (i < lArr.length) {
                        stringBuffer.append(" or  _id = " + lArr[i].longValue());
                        if (i > 0 && i % 25 == 0) {
                            com.tencent.beacon.e.a.b(" current " + i, new Object[0]);
                            i2 += writableDatabase.delete("t_event", stringBuffer.substring(4), null);
                            stringBuffer.setLength(0);
                            com.tencent.beacon.e.a.b(" current deleteNum: " + i2, new Object[0]);
                        }
                        i++;
                    }
                    if (stringBuffer.length() > 0) {
                        i = writableDatabase.delete("t_event", stringBuffer.substring(4), null) + i2;
                        try {
                            stringBuffer.setLength(0);
                        } catch (Throwable th2) {
                            th = th2;
                            try {
                                com.tencent.beacon.e.a.d(th.getMessage(), new Object[0]);
                                com.tencent.beacon.e.a.b(" AnalyticsDAO.deleteList() end!", new Object[0]);
                                i2 = i;
                                return i2;
                            } catch (Throwable th3) {
                                com.tencent.beacon.e.a.b(" AnalyticsDAO.deleteList() end!", new Object[0]);
                            }
                        }
                    } else {
                        i = i2;
                    }
                    com.tencent.beacon.e.a.a(" total deleteNum: " + i, new Object[0]);
                    com.tencent.beacon.e.a.b(" AnalyticsDAO.deleteList() end!", new Object[0]);
                    i2 = i;
                } catch (Throwable th4) {
                    Throwable th5 = th4;
                    i = 0;
                    th = th5;
                    com.tencent.beacon.e.a.d(th.getMessage(), new Object[0]);
                    com.tencent.beacon.e.a.b(" AnalyticsDAO.deleteList() end!", new Object[0]);
                    i2 = i;
                    return i2;
                }
            }
        }
        return i2;
    }

    public static List<a> a(Context context, int[] iArr, int i) {
        List<a> a;
        synchronized (e.a) {
            a = a(context, iArr, 1, 2, i, -1);
        }
        return a;
    }

    private static List<a> a(Cursor cursor) {
        List<a> list;
        synchronized (e.a) {
            com.tencent.beacon.e.a.b(" in AnalyticsDAO.paserCursor() start", new Object[0]);
            if (cursor == null) {
                list = null;
            } else {
                list = new ArrayList();
                int columnIndex = cursor.getColumnIndex("_id");
                int columnIndex2 = cursor.getColumnIndex("_prority");
                int columnIndex3 = cursor.getColumnIndex("_time");
                int columnIndex4 = cursor.getColumnIndex("_type");
                int columnIndex5 = cursor.getColumnIndex("_datas");
                int columnIndex6 = cursor.getColumnIndex("_length");
                while (cursor.moveToNext()) {
                    a aVar = new a();
                    aVar.a = cursor.getLong(columnIndex);
                    aVar.e = cursor.getBlob(columnIndex5);
                    aVar.c = cursor.getInt(columnIndex2);
                    aVar.d = cursor.getLong(columnIndex3);
                    aVar.b = cursor.getInt(columnIndex4);
                    aVar.f = cursor.getLong(columnIndex6);
                    list.add(aVar);
                }
                com.tencent.beacon.e.a.b(" in AnalyticsDAO.paserCursor() end", new Object[0]);
            }
        }
        return list;
    }

    public static int a(Context context, int[] iArr) {
        Throwable th;
        int i = 0;
        synchronized (e.a) {
            com.tencent.beacon.e.a.b(" AnalyticsDAO.querySum() start", new Object[0]);
            if (context == null) {
                com.tencent.beacon.e.a.a(" querySum() context is null arg", new Object[0]);
                return -1;
            } else if (-1 > Long.MAX_VALUE) {
                return 0;
            } else {
                String str = "_time >= -1 and _time <= 9223372036854775807";
                if (iArr != null && iArr.length > 0) {
                    String str2 = "";
                    while (i < iArr.length) {
                        str2 = str2 + " or _type = " + iArr[i];
                        i++;
                    }
                    str = str + " and ( " + str2.substring(4) + " )";
                }
                com.tencent.beacon.e.a.b(" query where: " + str, new Object[0]);
                Cursor query;
                try {
                    query = e.a(context).getWritableDatabase().query("t_event", new String[]{"count(*) as sum"}, str, null, null, null, null);
                    try {
                        query.moveToNext();
                        i = query.getInt(query.getColumnIndex("sum"));
                        com.tencent.beacon.e.a.b(" query sum: " + i, new Object[0]);
                        if (query != null) {
                            if (!query.isClosed()) {
                                query.close();
                            }
                        }
                        com.tencent.beacon.e.a.b(" AnalyticsDAO.querySum() end", new Object[0]);
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            com.tencent.beacon.e.a.b(th.getMessage(), new Object[0]);
                            if (query != null) {
                                if (!query.isClosed()) {
                                    query.close();
                                }
                            }
                            com.tencent.beacon.e.a.b(" AnalyticsDAO.querySum() end", new Object[0]);
                            i = -1;
                            return i;
                        } catch (Throwable th3) {
                            th = th3;
                            if (!(query == null || query.isClosed())) {
                                query.close();
                            }
                            com.tencent.beacon.e.a.b(" AnalyticsDAO.querySum() end", new Object[0]);
                            throw th;
                        }
                    }
                } catch (Throwable th4) {
                    th = th4;
                    query = null;
                    query.close();
                    com.tencent.beacon.e.a.b(" AnalyticsDAO.querySum() end", new Object[0]);
                    throw th;
                }
            }
        }
    }

    public static List<a> b(Context context, int[] iArr) {
        List<a> b;
        synchronized (e.a) {
            b = b(context, iArr, -1, -1, 1, -1);
        }
        return b;
    }

    public static boolean b(Context context, List<a> list) {
        boolean z;
        synchronized (e.a) {
            com.tencent.beacon.e.a.b(" insertOrUpdate alyticsBeans start!", new Object[0]);
            if (context == null || list == null || list.size() <= 0) {
                com.tencent.beacon.e.a.d(" context == null || list == null|| list.size() <= 0 ? pls check!", new Object[0]);
                return false;
            }
            boolean z2 = true;
            try {
                SQLiteDatabase writableDatabase = e.a(context).getWritableDatabase();
                for (a aVar : list) {
                    ContentValues a = a(aVar);
                    if (a != null) {
                        long replace = writableDatabase.replace("t_event", "_id", a);
                        if (replace > 0) {
                            com.tencent.beacon.e.a.b(" result id:" + replace, new Object[0]);
                            aVar.a = replace;
                        }
                    }
                    z2 = false;
                }
                com.tencent.beacon.e.a.b(" insertOrUpdate alyticsBeans end", new Object[0]);
                z = z2;
            } catch (Throwable th) {
                com.tencent.beacon.e.a.b(" insertOrUpdate alyticsBeans end", new Object[0]);
            }
        }
    }

    private static ContentValues a(a aVar) {
        ContentValues contentValues = new ContentValues();
        if (aVar.a > 0) {
            contentValues.put("_id", Long.valueOf(aVar.a));
        }
        contentValues.put("_prority", Integer.valueOf(aVar.c));
        contentValues.put("_time", Long.valueOf(aVar.d));
        contentValues.put("_type", Integer.valueOf(aVar.b));
        contentValues.put("_datas", aVar.e);
        contentValues.put("_length", Long.valueOf(aVar.f));
        return contentValues;
    }

    public static List<a> a(Context context, int[] iArr, int i, int i2, int i3, long j) {
        List<a> b;
        synchronized (e.a) {
            b = b(context, iArr, i, i2, i3, j);
        }
        return b;
    }

    private static List<a> b(Context context, int[] iArr, int i, int i2, int i3, long j) {
        List<a> list;
        Throwable th;
        synchronized (e.a) {
            com.tencent.beacon.e.a.b(" in AnalyticsDAO.query() start", new Object[0]);
            if (context == null || (j > 0 && -1 > j)) {
                com.tencent.beacon.e.a.d(" query() args context == null || timeStart > timeEnd || miniCount > maxCount || miniUploadCount > maxUploadCount ,pls check", new Object[0]);
                list = null;
            } else {
                String str = "";
                if (iArr != null && iArr.length > 0) {
                    String str2 = "";
                    for (int i4 : iArr) {
                        str2 = str2 + " or _type = " + i4;
                    }
                    str = str + str2.substring(4);
                }
                String str3 = str.length() > 0 ? " ( " + str + " ) " : "";
                if (-1 >= 0) {
                    str3 = str3 + (str3.length() > 0 ? " and " : "") + "_time >= -1 ";
                }
                if (j >= 0) {
                    str3 = str3 + (str3.length() > 0 ? " and " : "") + "_time <= " + j + " ";
                }
                String str4 = "";
                switch (i) {
                    case 1:
                        str4 = str4 + "_prority ASC , ";
                        break;
                    case 2:
                        str4 = str4 + "_prority DESC , ";
                        break;
                }
                switch (i2) {
                    case 1:
                        str4 = str4 + "_time ASC ";
                        break;
                    case 2:
                        str4 = str4 + "_time DESC ";
                        break;
                }
                if (str4.endsWith(" , ")) {
                    str4 = str4.substring(0, str4.length() - 3);
                }
                com.tencent.beacon.e.a.b(" query:%s", str3);
                Cursor query;
                List<a> a;
                try {
                    query = e.a(context).getWritableDatabase().query("t_event", null, str3, null, null, null, str4, i3 >= 0 ? i3 : null);
                    try {
                        com.tencent.beacon.e.a.a("result num: " + query.getCount(), new Object[0]);
                        if (query.getCount() > 0) {
                            a = a(query);
                        } else {
                            a = null;
                        }
                        if (a != null) {
                            try {
                                com.tencent.beacon.e.a.a(" total num: " + a.size(), new Object[0]);
                            } catch (Throwable th2) {
                                th = th2;
                                try {
                                    com.tencent.beacon.e.a.b(th.getMessage(), new Object[0]);
                                    if (query != null) {
                                        if (!query.isClosed()) {
                                            query.close();
                                        }
                                    }
                                    com.tencent.beacon.e.a.b(" in AnalyticsDAO.query() end", new Object[0]);
                                    list = a;
                                    return list;
                                } catch (Throwable th3) {
                                    th = th3;
                                    if (!(query == null || query.isClosed())) {
                                        query.close();
                                    }
                                    com.tencent.beacon.e.a.b(" in AnalyticsDAO.query() end", new Object[0]);
                                    throw th;
                                }
                            }
                        }
                        if (query != null) {
                            if (!query.isClosed()) {
                                query.close();
                            }
                        }
                        com.tencent.beacon.e.a.b(" in AnalyticsDAO.query() end", new Object[0]);
                        list = a;
                    } catch (Throwable th4) {
                        th = th4;
                        a = null;
                        com.tencent.beacon.e.a.b(th.getMessage(), new Object[0]);
                        if (query != null) {
                            if (query.isClosed()) {
                                query.close();
                            }
                        }
                        com.tencent.beacon.e.a.b(" in AnalyticsDAO.query() end", new Object[0]);
                        list = a;
                        return list;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    query = null;
                    query.close();
                    com.tencent.beacon.e.a.b(" in AnalyticsDAO.query() end", new Object[0]);
                    throw th;
                }
            }
        }
        return list;
    }
}
