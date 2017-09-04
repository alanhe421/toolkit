package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.cservice.cloud.g;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: CloudTagListDBHandle */
public class l extends b {
    private static volatile l c;
    private static c d;
    private final int a = 1;
    private final int b = 3;
    private String[] e = null;

    /* compiled from: CloudTagListDBHandle */
    private class a extends c {
        final /* synthetic */ l a;

        public a(l lVar, String str, CursorFactory cursorFactory, int i) {
            this.a = lVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    public static l b() {
        if (c == null) {
            synchronized (l.class) {
                if (c == null) {
                    c = new l();
                }
            }
        }
        return c;
    }

    private l() {
        d = new a(this, com.qq.reader.common.c.a.bp, null, 3);
    }

    public synchronized boolean c() {
        boolean z;
        try {
            SQLiteDatabase a = d.a();
            a.execSQL("drop table if exists cloud_tag_shelf");
            a(a);
            z = true;
            if (d != null) {
                d.c();
            }
        } catch (Exception e) {
            f.a("DB", "clearCloudDownloadTasks with exception : " + e.getMessage());
            z = false;
            if (d != null) {
                d.c();
            }
        } catch (Throwable th) {
            if (d != null) {
                d.c();
            }
        }
        return z;
    }

    public synchronized boolean a(long j) {
        int delete;
        Exception e;
        boolean z = false;
        synchronized (this) {
            try {
                delete = d.a().delete("cloud_tag_shelf", "bookid= '" + j + "'", null);
                if (delete > 0) {
                    try {
                        j.a().a(String.valueOf(j));
                    } catch (Exception e2) {
                        e = e2;
                        try {
                            f.a("DB", "delCloudDownloadTask with exception : " + e.getMessage());
                            if (d != null) {
                                d.c();
                            }
                            if (delete > 0) {
                                z = true;
                            }
                            return z;
                        } catch (Throwable th) {
                            if (d != null) {
                                d.c();
                            }
                        }
                    }
                }
                if (d != null) {
                    d.c();
                }
            } catch (Exception e3) {
                e = e3;
                delete = 0;
                f.a("DB", "delCloudDownloadTask with exception : " + e.getMessage());
                if (d != null) {
                    d.c();
                }
                if (delete > 0) {
                    z = true;
                }
                return z;
            }
            if (delete > 0) {
                z = true;
            }
        }
        return z;
    }

    private ContentValues b(g gVar) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("bookid", Long.valueOf(gVar.f()));
        contentValues.put("cloud_updatetime", Long.valueOf(gVar.g()));
        contentValues.put("cloud_local_filepath", gVar.e());
        contentValues.put("chapterid", Long.valueOf(gVar.h()));
        contentValues.put("chapteroffset", Integer.valueOf(gVar.i()));
        contentValues.put("name", gVar.j());
        contentValues.put("author", gVar.k());
        contentValues.put("urlobj", gVar.l());
        contentValues.put("iconurl", gVar.m());
        contentValues.put("format", gVar.n());
        contentValues.put("drmflag", Integer.valueOf(gVar.o()));
        contentValues.put("isfinish", Integer.valueOf(gVar.u()));
        contentValues.put("isdownloadable", Integer.valueOf(gVar.p()));
        contentValues.put("cloud_chaptertitle", gVar.q());
        contentValues.put("cloud_maxchapter", Integer.valueOf(gVar.r()));
        contentValues.put("cloud_sourcetype", Integer.valueOf(gVar.s()));
        contentValues.put("cloud_last_chapter_title", gVar.d());
        contentValues.put("cloud_last_upload_time", Long.valueOf(gVar.c()));
        contentValues.put("cloud_downloadinfo", gVar.b());
        contentValues.put("cloud_restype", Integer.valueOf(gVar.w()));
        contentValues.put("cloud_comic_offset", gVar.t());
        return contentValues;
    }

    public synchronized boolean a(g gVar) {
        boolean z;
        Exception e;
        Throwable th;
        Cursor query;
        try {
            long update;
            SQLiteDatabase a = d.a();
            ContentValues b = b(gVar);
            query = a.query("cloud_tag_shelf", new String[]{"_id"}, "bookid= '" + gVar.f() + "'", null, null, null, null, null);
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        query.moveToFirst();
                        update = (long) a.update("cloud_tag_shelf", b, "_id=" + query.getInt(0), null);
                        if (update >= 0) {
                            if (query != null) {
                                query.close();
                            }
                            if (d != null) {
                                d.c();
                            }
                            z = false;
                        } else {
                            if (query != null) {
                                query.close();
                            }
                            if (d != null) {
                                d.c();
                            }
                            z = true;
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", "CloudTagListDBHandle put with exception : " + e.getMessage());
                        if (query != null) {
                            query.close();
                        }
                        if (d != null) {
                            d.c();
                        }
                        z = false;
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        if (d != null) {
                            d.c();
                        }
                        throw th;
                    }
                }
            }
            update = a.insert("cloud_tag_shelf", null, b);
            if (update >= 0) {
                if (query != null) {
                    query.close();
                }
                if (d != null) {
                    d.c();
                }
                z = true;
            } else {
                if (query != null) {
                    query.close();
                }
                if (d != null) {
                    d.c();
                }
                z = false;
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            f.a("DB", "CloudTagListDBHandle put with exception : " + e.getMessage());
            if (query != null) {
                query.close();
            }
            if (d != null) {
                d.c();
            }
            z = false;
            return z;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            if (d != null) {
                d.c();
            }
            throw th;
        }
        return z;
    }

    public boolean a(ArrayList<g> arrayList) {
        b((ArrayList) arrayList);
        if (c()) {
            return c((ArrayList) arrayList);
        }
        return false;
    }

    public void b(ArrayList<g> arrayList) {
        ArrayList e = e();
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                g gVar = (g) it.next();
                Iterator it2 = e.iterator();
                while (it2.hasNext()) {
                    g gVar2 = (g) it2.next();
                    if (gVar.f() == gVar2.f()) {
                        gVar.c(gVar2.g());
                        gVar.c(gVar2.e());
                        break;
                    }
                }
            }
        }
    }

    private synchronized boolean c(ArrayList<g> arrayList) {
        boolean z;
        try {
            SQLiteDatabase a = d.a();
            int size = arrayList.size() - 1;
            while (size >= 0) {
                if (a.insert("cloud_tag_shelf", null, b((g) arrayList.get(size))) < 0) {
                    if (d != null) {
                        d.c();
                    }
                    z = false;
                } else {
                    size--;
                }
            }
            if (d != null) {
                d.c();
            }
            z = true;
        } catch (Exception e) {
            f.a("DB", "CloudTagListDBHandle adds with exception : " + e.getMessage());
            if (d != null) {
                d.c();
            }
            z = false;
        } catch (Throwable th) {
            if (d != null) {
                d.c();
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.qq.reader.cservice.cloud.g b(long r32) {
        /*
        r31 = this;
        monitor-enter(r31);
        r11 = 0;
        r10 = 0;
        r2 = d;	 Catch:{ Exception -> 0x0122, all -> 0x0156 }
        r2 = r2.a();	 Catch:{ Exception -> 0x0122, all -> 0x0156 }
        r4 = r31.f();	 Catch:{ Exception -> 0x0122, all -> 0x0156 }
        r3 = "cloud_tag_shelf";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0122, all -> 0x0156 }
        r5.<init>();	 Catch:{ Exception -> 0x0122, all -> 0x0156 }
        r6 = "bookid= '";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0122, all -> 0x0156 }
        r0 = r32;
        r5 = r5.append(r0);	 Catch:{ Exception -> 0x0122, all -> 0x0156 }
        r6 = "'";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x0122, all -> 0x0156 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x0122, all -> 0x0156 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r9 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0122, all -> 0x0156 }
        r2 = r9.getCount();	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        if (r2 <= 0) goto L_0x0179;
    L_0x003b:
        r9.moveToFirst();	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r2 = 0;
        r4 = r9.getLong(r2);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r2 = 1;
        r6 = r9.getLong(r2);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r2 = 2;
        r2 = r9.getString(r2);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 3;
        r12 = r9.getLong(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 4;
        r10 = r9.getInt(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 5;
        r14 = r9.getString(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 6;
        r15 = r9.getString(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 7;
        r16 = r9.getString(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 8;
        r17 = r9.getString(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 9;
        r18 = r9.getString(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 10;
        r19 = r9.getInt(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 11;
        r20 = r9.getInt(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 12;
        r21 = r9.getInt(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 13;
        r22 = r9.getString(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 14;
        r23 = r9.getInt(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 15;
        r24 = r9.getInt(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 16;
        r25 = r9.getString(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 17;
        r26 = r9.getLong(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 18;
        r28 = r9.getString(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = 19;
        r8 = r9.getInt(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = "cloud_comic_offset";
        r3 = r9.getColumnIndex(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r29 = r9.getString(r3);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3 = new com.qq.reader.cservice.cloud.g;	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3.<init>(r4, r6, r8);	 Catch:{ Exception -> 0x016c, all -> 0x0167 }
        r3.c(r2);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r3.d(r12);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r3.a(r10);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r3.d(r14);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r3.e(r15);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r0 = r16;
        r3.f(r0);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r0 = r17;
        r3.g(r0);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r0 = r18;
        r3.h(r0);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r0 = r19;
        r3.b(r0);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r0 = r20;
        r3.f(r0);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r0 = r21;
        r3.c(r0);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r0 = r22;
        r3.i(r0);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r0 = r23;
        r3.d(r0);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r0 = r24;
        r3.e(r0);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r0 = r25;
        r3.b(r0);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r0 = r26;
        r3.a(r0);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r0 = r28;
        r3.a(r0);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r3.g(r8);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r0 = r29;
        r3.j(r0);	 Catch:{ Exception -> 0x0171, all -> 0x0167 }
        r2 = r3;
    L_0x0112:
        if (r9 == 0) goto L_0x0117;
    L_0x0114:
        r9.close();	 Catch:{ all -> 0x0153 }
    L_0x0117:
        r3 = d;	 Catch:{ all -> 0x0153 }
        if (r3 == 0) goto L_0x0120;
    L_0x011b:
        r3 = d;	 Catch:{ all -> 0x0153 }
        r3.c();	 Catch:{ all -> 0x0153 }
    L_0x0120:
        monitor-exit(r31);
        return r2;
    L_0x0122:
        r2 = move-exception;
        r3 = r2;
        r4 = r10;
        r2 = r11;
    L_0x0126:
        r5 = "DB";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0169 }
        r6.<init>();	 Catch:{ all -> 0x0169 }
        r7 = "getCloudTagByID with exception: ";
        r6 = r6.append(r7);	 Catch:{ all -> 0x0169 }
        r3 = r3.toString();	 Catch:{ all -> 0x0169 }
        r3 = r6.append(r3);	 Catch:{ all -> 0x0169 }
        r3 = r3.toString();	 Catch:{ all -> 0x0169 }
        com.qq.reader.common.monitor.f.a(r5, r3);	 Catch:{ all -> 0x0169 }
        if (r4 == 0) goto L_0x0149;
    L_0x0146:
        r4.close();	 Catch:{ all -> 0x0153 }
    L_0x0149:
        r3 = d;	 Catch:{ all -> 0x0153 }
        if (r3 == 0) goto L_0x0120;
    L_0x014d:
        r3 = d;	 Catch:{ all -> 0x0153 }
        r3.c();	 Catch:{ all -> 0x0153 }
        goto L_0x0120;
    L_0x0153:
        r2 = move-exception;
        monitor-exit(r31);
        throw r2;
    L_0x0156:
        r2 = move-exception;
        r9 = r10;
    L_0x0158:
        if (r9 == 0) goto L_0x015d;
    L_0x015a:
        r9.close();	 Catch:{ all -> 0x0153 }
    L_0x015d:
        r3 = d;	 Catch:{ all -> 0x0153 }
        if (r3 == 0) goto L_0x0166;
    L_0x0161:
        r3 = d;	 Catch:{ all -> 0x0153 }
        r3.c();	 Catch:{ all -> 0x0153 }
    L_0x0166:
        throw r2;	 Catch:{ all -> 0x0153 }
    L_0x0167:
        r2 = move-exception;
        goto L_0x0158;
    L_0x0169:
        r2 = move-exception;
        r9 = r4;
        goto L_0x0158;
    L_0x016c:
        r2 = move-exception;
        r3 = r2;
        r4 = r9;
        r2 = r11;
        goto L_0x0126;
    L_0x0171:
        r2 = move-exception;
        r4 = r9;
        r30 = r2;
        r2 = r3;
        r3 = r30;
        goto L_0x0126;
    L_0x0179:
        r2 = r11;
        goto L_0x0112;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.l.b(long):com.qq.reader.cservice.cloud.g");
    }

    public synchronized int d() {
        int i;
        Cursor cursor = null;
        int i2 = 0;
        synchronized (this) {
            try {
                cursor = d.a().rawQuery("SELECT COUNT(*) FROM cloud_tag_shelf", null);
                if (cursor.moveToFirst()) {
                    do {
                        i2 = cursor.getInt(0);
                    } while (cursor.moveToNext());
                }
                i = i2;
                if (cursor != null) {
                    cursor.close();
                }
                if (d != null) {
                    d.c();
                }
            } catch (Exception e) {
                Exception exception = e;
                i = 0;
                f.b("DB", "getCloudTagsDbCount with exception: " + exception.toString());
                if (cursor != null) {
                    cursor.close();
                }
                if (d != null) {
                    d.c();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                if (d != null) {
                    d.c();
                }
            }
        }
        return i;
    }

    public synchronized ArrayList<g> e() {
        ArrayList<g> arrayList;
        Cursor query;
        Exception e;
        Cursor cursor;
        Throwable th;
        arrayList = new ArrayList();
        try {
            query = d.a().query("cloud_tag_shelf", f(), null, null, null, null, null);
            try {
                if (query.moveToFirst()) {
                    do {
                        long j = query.getLong(0);
                        long j2 = query.getLong(1);
                        String string = query.getString(2);
                        long j3 = query.getLong(3);
                        int i = query.getInt(4);
                        String string2 = query.getString(5);
                        String string3 = query.getString(6);
                        String string4 = query.getString(7);
                        String string5 = query.getString(8);
                        String string6 = query.getString(9);
                        int i2 = query.getInt(10);
                        int i3 = query.getInt(11);
                        int i4 = query.getInt(12);
                        String string7 = query.getString(13);
                        int i5 = query.getInt(14);
                        int i6 = query.getInt(15);
                        String string8 = query.getString(16);
                        long j4 = query.getLong(17);
                        String string9 = query.getString(18);
                        int i7 = query.getInt(19);
                        String string10 = query.getString(query.getColumnIndex("cloud_comic_offset"));
                        g gVar = new g(j, j2, i7);
                        gVar.c(string);
                        gVar.d(j3);
                        gVar.a(i);
                        gVar.d(string2);
                        gVar.e(string3);
                        gVar.f(string4);
                        gVar.g(string5);
                        gVar.h(string6);
                        gVar.b(i2);
                        gVar.f(i3);
                        gVar.c(i4);
                        gVar.i(string7);
                        gVar.d(i5);
                        gVar.e(i6);
                        gVar.b(string8);
                        gVar.a(j4);
                        gVar.a(string9);
                        gVar.g(i7);
                        gVar.j(string10);
                        arrayList.add(gVar);
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
                if (d != null) {
                    d.c();
                }
            } catch (Exception e2) {
                e = e2;
                cursor = query;
                try {
                    f.b("DB", "getAllCloudTags with exception: " + e.toString());
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (d != null) {
                        d.c();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    query = cursor;
                    if (query != null) {
                        query.close();
                    }
                    if (d != null) {
                        d.c();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (query != null) {
                    query.close();
                }
                if (d != null) {
                    d.c();
                }
                throw th;
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
            f.b("DB", "getAllCloudTags with exception: " + e.toString());
            if (cursor != null) {
                cursor.close();
            }
            if (d != null) {
                d.c();
            }
            return arrayList;
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            if (d != null) {
                d.c();
            }
            throw th;
        }
        return arrayList;
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        Exception e;
        Throwable th;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select cloud_restype from cloud_tag_shelf", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(2);
                        rawQuery.close();
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update2To3 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE cloud_tag_shelf ADD cloud_restype integer default 0");
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            f.a("DB", " update2To3 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE cloud_tag_shelf ADD cloud_restype integer default 0");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE cloud_tag_shelf ADD cloud_restype integer default 0");
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select cloud_comic_offset from cloud_tag_shelf", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(3);
                        rawQuery.close();
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update2To3 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE cloud_tag_shelf ADD cloud_comic_offset text");
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            f.a("DB", " update2To3 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE cloud_tag_shelf ADD cloud_comic_offset text");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE cloud_tag_shelf ADD cloud_comic_offset text");
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
        switch (i) {
            case 1:
                b(sQLiteDatabase);
                break;
            case 2:
                break;
            default:
                return;
        }
        c(sQLiteDatabase);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists cloud_tag_shelf (_id integer primary key autoincrement,bookid text not null,cloud_updatetime text ,cloud_local_filepath text ,chapterid integer default 0 ,chapteroffset integer default 0 ,name text ,author text ,urlobj text ,iconurl text ,format text ,drmflag integer default 0,isfinish  integer default 0,isdownloadable  integer default 0,cloud_chaptertitle text ,cloud_maxchapter integer default 0,cloud_sourcetype integer default 0,cloud_last_chapter_title text ,cloud_last_upload_time  long default 0 ,cloud_downloadinfo text,cloud_restype integer default 0,cloud_comic_offset text);");
    }

    private String[] f() {
        if (this.e == null) {
            this.e = new String[]{"bookid", "cloud_updatetime", "cloud_local_filepath", "chapterid", "chapteroffset", "name", "author", "urlobj", "iconurl", "format", "drmflag", "isfinish", "isdownloadable", "cloud_chaptertitle", "cloud_maxchapter", "cloud_sourcetype", "cloud_last_chapter_title", "cloud_last_upload_time", "cloud_downloadinfo", "cloud_restype", "cloud_comic_offset"};
        }
        return this.e;
    }

    public void a() {
        synchronized (l.class) {
            c = null;
        }
    }
}
