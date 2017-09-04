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

/* compiled from: CloudTagListDBHandleBig */
public class m extends b {
    private static volatile m d;
    private final int a = 1;
    private final int b = 2;
    private final int c = 2;
    private String[] e = null;

    /* compiled from: CloudTagListDBHandleBig */
    private class a extends c {
        final /* synthetic */ m a;

        public a(m mVar, String str, CursorFactory cursorFactory, int i) {
            this.a = mVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    public static m b() {
        if (d == null) {
            synchronized (m.class) {
                if (d == null) {
                    d = new m();
                }
            }
        }
        return d;
    }

    private m() {
    }

    private c e() {
        return new a(this, com.qq.reader.common.c.a.bo, null, 2);
    }

    public synchronized boolean c() {
        boolean z;
        c e = e();
        try {
            SQLiteDatabase a = e.a();
            a.execSQL("drop table if exists cloud_tag");
            a(a);
            z = true;
            e.c();
        } catch (Exception e2) {
            f.a("DB", "clearCloudDownloadTasks with exception : " + e2.getMessage());
            z = false;
            e.c();
        } catch (Throwable th) {
            e.c();
        }
        return z;
    }

    public synchronized boolean a(long j) {
        Exception e;
        boolean z = false;
        synchronized (this) {
            int delete;
            c e2 = e();
            try {
                delete = e2.a().delete("cloud_tag", "bookid= '" + j + "'", null);
                if (delete > 0) {
                    try {
                        j.a().a(String.valueOf(j));
                    } catch (Exception e3) {
                        e = e3;
                        try {
                            f.a("DB", "delCloudDownloadTask with exception : " + e.getMessage());
                            e2.c();
                            if (delete > 0) {
                                z = true;
                            }
                            return z;
                        } catch (Throwable th) {
                            e2.c();
                        }
                    }
                }
                e2.c();
            } catch (Exception e4) {
                e = e4;
                delete = 0;
                f.a("DB", "delCloudDownloadTask with exception : " + e.getMessage());
                e2.c();
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

    private ContentValues a(g gVar) {
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
        contentValues.put("cloud_comic_offset", gVar.t());
        contentValues.put("cloud_restype", Integer.valueOf(gVar.w()));
        return contentValues;
    }

    public boolean a(ArrayList<g> arrayList) {
        b((ArrayList) arrayList);
        if (c()) {
            return c(arrayList);
        }
        return false;
    }

    public void b(ArrayList<g> arrayList) {
        ArrayList d = d();
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                g gVar = (g) it.next();
                Iterator it2 = d.iterator();
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
        c e = e();
        try {
            SQLiteDatabase a = e.a();
            for (int size = arrayList.size() - 1; size >= 0; size--) {
                if (a.insert("cloud_tag", null, a((g) arrayList.get(size))) < 0) {
                    e.c();
                    z = false;
                    break;
                }
            }
            e.c();
            z = true;
        } catch (Exception e2) {
            f.a("DB", "CloudTagListDBHandle adds with exception : " + e2.getMessage());
            e.c();
            z = false;
        } catch (Throwable th) {
            e.c();
        }
        return z;
    }

    public synchronized ArrayList<g> d() {
        ArrayList<g> arrayList;
        Exception e;
        Cursor cursor;
        Throwable th;
        arrayList = new ArrayList();
        c e2 = e();
        Cursor query;
        try {
            query = e2.a().query("cloud_tag", f(), null, null, null, null, null);
            try {
                if (query.moveToFirst()) {
                    do {
                        long j = query.getLong(0);
                        long j2 = query.getLong(1);
                        String string = query.getString(2);
                        long j3 = (long) query.getInt(3);
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
                        String string10 = query.getString(query.getColumnIndex("cloud_comic_offset"));
                        int i7 = query.getInt(query.getColumnIndex("cloud_restype"));
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
                        gVar.j(string10);
                        gVar.g(i7);
                        arrayList.add(gVar);
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
                e2.c();
            } catch (Exception e3) {
                e = e3;
                cursor = query;
                try {
                    f.b("DB", "getAllCloudTags with exception: " + e.toString());
                    if (cursor != null) {
                        cursor.close();
                    }
                    e2.c();
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    query = cursor;
                    if (query != null) {
                        query.close();
                    }
                    e2.c();
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                if (query != null) {
                    query.close();
                }
                e2.c();
                throw th;
            }
        } catch (Exception e4) {
            e = e4;
            cursor = null;
            f.b("DB", "getAllCloudTags with exception: " + e.toString());
            if (cursor != null) {
                cursor.close();
            }
            e2.c();
            return arrayList;
        } catch (Throwable th4) {
            th = th4;
            query = null;
            if (query != null) {
                query.close();
            }
            e2.c();
            throw th;
        }
        return arrayList;
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
        switch (i) {
            case 1:
                b(sQLiteDatabase);
                return;
            default:
                return;
        }
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Cursor rawQuery2;
        Exception e2;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select cloud_comic_offset from cloud_tag", null);
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
                } catch (Exception e3) {
                    e = e3;
                    try {
                        f.a("DB", " update1To2 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE cloud_tag ADD cloud_comic_offset text");
                        rawQuery2 = sQLiteDatabase.rawQuery("select cloud_restype from cloud_tag", null);
                        if (rawQuery2 != null) {
                            try {
                                if (rawQuery2.getCount() > 0) {
                                    sQLiteDatabase.setVersion(2);
                                    rawQuery2.close();
                                    if (cursor == null) {
                                        cursor.close();
                                        return;
                                    }
                                    return;
                                }
                            } catch (Exception e4) {
                                e2 = e4;
                                try {
                                    f.a("DB", " update1To2 :" + e2.toString());
                                    if (rawQuery2 != null) {
                                        rawQuery2.close();
                                    }
                                    sQLiteDatabase.execSQL("ALTER TABLE cloud_tag ADD cloud_restype integer default 0");
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (rawQuery2 != null) {
                                        rawQuery2.close();
                                    }
                                    throw th;
                                }
                            }
                        }
                        if (rawQuery2 != null) {
                            rawQuery2.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE cloud_tag ADD cloud_restype integer default 0");
                    } catch (Throwable th3) {
                        cursor = rawQuery;
                        th = th3;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e22) {
            e = e22;
            rawQuery = cursor;
            f.a("DB", " update1To2 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE cloud_tag ADD cloud_comic_offset text");
            rawQuery2 = sQLiteDatabase.rawQuery("select cloud_restype from cloud_tag", null);
            if (rawQuery2 != null) {
                if (rawQuery2.getCount() > 0) {
                    sQLiteDatabase.setVersion(2);
                    rawQuery2.close();
                    if (cursor == null) {
                        cursor.close();
                        return;
                    }
                    return;
                }
            }
            if (rawQuery2 != null) {
                rawQuery2.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE cloud_tag ADD cloud_restype integer default 0");
        } catch (Throwable th4) {
            th = th4;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE cloud_tag ADD cloud_comic_offset text");
        try {
            rawQuery2 = sQLiteDatabase.rawQuery("select cloud_restype from cloud_tag", null);
            if (rawQuery2 != null) {
                if (rawQuery2.getCount() > 0) {
                    sQLiteDatabase.setVersion(2);
                    rawQuery2.close();
                    if (cursor == null) {
                        cursor.close();
                        return;
                    }
                    return;
                }
            }
            if (rawQuery2 != null) {
                rawQuery2.close();
            }
        } catch (Exception e5) {
            Exception exception = e5;
            rawQuery2 = rawQuery;
            e22 = exception;
            f.a("DB", " update1To2 :" + e22.toString());
            if (rawQuery2 != null) {
                rawQuery2.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE cloud_tag ADD cloud_restype integer default 0");
        } catch (Throwable th32) {
            Throwable th5 = th32;
            rawQuery2 = rawQuery;
            th = th5;
            if (rawQuery2 != null) {
                rawQuery2.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE cloud_tag ADD cloud_restype integer default 0");
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists cloud_tag (_id integer primary key autoincrement,bookid text not null,cloud_updatetime text ,cloud_local_filepath text ,chapterid integer default 0 ,chapteroffset integer default 0 ,name text ,author text ,urlobj text ,iconurl text ,format text ,drmflag integer default 0,isfinish  integer default 0,isdownloadable  integer default 0,cloud_chaptertitle text ,cloud_maxchapter integer default 0,cloud_sourcetype integer default 0,cloud_last_chapter_title text ,cloud_last_upload_time  long default 0 ,cloud_downloadinfo text,cloud_comic_offset text,cloud_restype integer default 0);");
    }

    private String[] f() {
        if (this.e == null) {
            this.e = new String[]{"bookid", "cloud_updatetime", "cloud_local_filepath", "chapterid", "chapteroffset", "name", "author", "urlobj", "iconurl", "format", "drmflag", "isfinish", "isdownloadable", "cloud_chaptertitle", "cloud_maxchapter", "cloud_sourcetype", "cloud_last_chapter_title", "cloud_last_upload_time", "cloud_downloadinfo", "cloud_comic_offset", "cloud_restype"};
        }
        return this.e;
    }

    public void a() {
        synchronized (m.class) {
            d = null;
        }
    }
}
