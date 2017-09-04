package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.text.TextUtils;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.bookchapter.online.i;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* compiled from: BookReadPermissionHandler */
public class h extends b {
    private static a a;
    private static h b;

    /* compiled from: BookReadPermissionHandler */
    private class a extends c {
        final /* synthetic */ h a;

        public a(h hVar, String str, CursorFactory cursorFactory, int i) {
            this.a = hVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    public static synchronized h b() {
        h hVar;
        synchronized (h.class) {
            if (b == null) {
                b = new h();
            }
            hVar = b;
        }
        return hVar;
    }

    private h() {
        a = new a(this, com.qq.reader.common.c.a.aT, null, 2);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists limitbook (_id integer primary key autoincrement,bid text not null,limitfreeend_time  text,pay_check_mode integer default 5);");
        sQLiteDatabase.execSQL("create table if not exists limitchapter (_id integer primary key autoincrement,bid text not null,chapterid  integer);");
        sQLiteDatabase.execSQL("CREATE UNIQUE INDEX unique_index_bid ON limitbook (bid);");
    }

    public synchronized void a(i iVar) {
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            if (iVar != null) {
                try {
                    sQLiteDatabase = a.a();
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.beginTransaction();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("bid", iVar.b());
                        contentValues.put("limitfreeend_time", Long.valueOf(iVar.c()));
                        contentValues.put("pay_check_mode", Integer.valueOf(iVar.d()));
                        sQLiteDatabase.replace("limitbook", null, contentValues);
                        List a = iVar.a();
                        int size = a.size();
                        if (size > 0) {
                            ContentValues contentValues2 = new ContentValues();
                            for (int i = 0; i < size; i++) {
                                contentValues2.clear();
                                int intValue = ((Integer) a.get(i)).intValue();
                                contentValues2.put("bid", iVar.b());
                                contentValues2.put("chapterid", Integer.valueOf(intValue));
                                sQLiteDatabase.insert("limitchapter", null, contentValues2);
                            }
                        }
                        sQLiteDatabase.setTransactionSuccessful();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    a.c();
                } catch (Exception e) {
                    e.printStackTrace();
                    j.c("BookReadPermissionHandler.addNoPayBookForPermission Exception: " + e.getMessage());
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    a.c();
                } catch (Throwable th) {
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.endTransaction();
                    }
                    a.c();
                }
            }
        }
    }

    public synchronized i a(String str) {
        Cursor query;
        i iVar;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            try {
                query = a.a().query("limitbook", new String[]{"limitfreeend_time", "pay_check_mode"}, "bid= " + str + " and " + "pay_check_mode" + "= " + 5 + "", null, null, null, null);
                try {
                    if (query.moveToFirst()) {
                        iVar = new i(str);
                        iVar.b(5);
                        iVar.a(query.getLong(0));
                    } else {
                        iVar = null;
                    }
                    if (query != null) {
                        query.close();
                    }
                    if (a != null) {
                        a.c();
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.b("DB", "isBookLimitFree with exception: " + e.toString());
                        j.c("BookReadPermissionHandler.getBookLimitFree Exception: " + e.getMessage());
                        if (query != null) {
                            query.close();
                        }
                        if (a == null) {
                            a.c();
                            iVar = null;
                        } else {
                            iVar = null;
                        }
                        return iVar;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = query;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (a != null) {
                            a.c();
                        }
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                f.b("DB", "isBookLimitFree with exception: " + e.toString());
                j.c("BookReadPermissionHandler.getBookLimitFree Exception: " + e.getMessage());
                if (query != null) {
                    query.close();
                }
                if (a == null) {
                    iVar = null;
                } else {
                    a.c();
                    iVar = null;
                }
                return iVar;
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
                throw th;
            }
        }
        return iVar;
    }

    public synchronized boolean b(String str) {
        Cursor cursor = null;
        boolean z = false;
        synchronized (this) {
            try {
                SQLiteDatabase a = a.a();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("select ").append("bid").append(" from ").append("limitbook").append(" where ").append("bid").append(" = ").append(str).append(" and ").append("limitfreeend_time").append(" <= ").append(System.currentTimeMillis()).append(" and ").append("pay_check_mode").append(" = ").append(5).append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                cursor = a.rawQuery(stringBuilder.toString(), null);
                if (cursor.moveToFirst()) {
                    z = true;
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
            } catch (Exception e) {
                f.b("DB", "isBookLimitFree with exception: " + e.toString());
                j.c("BookReadPermissionHandler.isBookLimitFree Exception: " + e.getMessage());
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
            }
        }
        return z;
    }

    public synchronized boolean c(String str) {
        Cursor cursor = null;
        boolean z = false;
        synchronized (this) {
            try {
                SQLiteDatabase a = a.a();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("select ").append("bid").append(" from ").append("limitbook").append(" where ").append("bid").append(" = ").append(str).append(" and ").append("pay_check_mode").append(" = ").append(4).append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                cursor = a.rawQuery(stringBuilder.toString(), null);
                if (cursor.moveToFirst()) {
                    z = true;
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
            } catch (Exception e) {
                f.b("DB", "isBookLimitFree with exception: " + e.toString());
                j.c("BookReadPermissionHandler.isBookInDiscountBag Exception: " + e.getMessage());
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
            }
        }
        return z;
    }

    public synchronized boolean d(String str) {
        Cursor cursor = null;
        boolean z = false;
        synchronized (this) {
            try {
                SQLiteDatabase a = a.a();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("select ").append("bid").append(" from ").append("limitbook").append(" where ").append("bid").append(" = ").append(str).append(" and ").append("pay_check_mode").append(" = ").append(1).append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                cursor = a.rawQuery(stringBuilder.toString(), null);
                if (cursor.moveToFirst()) {
                    z = true;
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
            } catch (Exception e) {
                f.b("DB", "isBookLimitFree with exception: " + e.toString());
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
            }
        }
        return z;
    }

    public synchronized List<Integer> e(String str) {
        List<Integer> arrayList;
        Cursor cursor = null;
        synchronized (this) {
            arrayList = new ArrayList();
            try {
                SQLiteDatabase a = a.a();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("select ").append("chapterid").append(" from ").append("limitchapter").append(" where ").append("bid").append(" = ").append(str).append(" order by ").append("chapterid").append(" asc ").append(VoiceWakeuperAidl.PARAMS_SEPARATE);
                cursor = a.rawQuery(stringBuilder.toString(), null);
                if (cursor.moveToFirst()) {
                    do {
                        arrayList.add(Integer.valueOf(cursor.getInt(0)));
                    } while (cursor.moveToNext());
                }
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
            } catch (Exception e) {
                f.b("DB", "getLimitFreeChapterIds with exception: " + e.toString());
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
            }
        }
        return arrayList;
    }

    private synchronized void f(String str) {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = a.a();
            sQLiteDatabase.beginTransaction();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("delete from ").append("limitchapter").append(" where ").append("bid").append(" = ").append(str).append(VoiceWakeuperAidl.PARAMS_SEPARATE);
            sQLiteDatabase.execSQL(stringBuilder.toString());
            stringBuilder = new StringBuilder();
            stringBuilder.append("delete from ").append("limitbook").append(" where ").append("bid").append(" = ").append(str).append(VoiceWakeuperAidl.PARAMS_SEPARATE);
            sQLiteDatabase.execSQL(stringBuilder.toString());
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e(h.class.getSimpleName(), e.getMessage());
                }
            }
            if (a != null) {
                a.c();
            }
        } catch (Exception e2) {
            f.b("DB", "delLimitFreeBook with exception: " + e2.toString());
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e22) {
                    com.qq.reader.common.monitor.debug.c.e(h.class.getSimpleName(), e22.getMessage());
                }
            }
            if (a != null) {
                a.c();
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.endTransaction();
                } catch (Exception e3) {
                    com.qq.reader.common.monitor.debug.c.e(h.class.getSimpleName(), e3.getMessage());
                }
            }
            if (a != null) {
                a.c();
            }
        }
    }

    public static void a(List<Integer> list, OnlineTag onlineTag, com.qq.reader.module.bookchapter.online.f fVar) {
        if (fVar != null && fVar.s() != 0) {
            Object c = v.c(onlineTag.k());
            if (!TextUtils.isEmpty(c)) {
                File file = new File(c);
                if (file.exists()) {
                    List<File> arrayList = new ArrayList();
                    Set hashSet = new HashSet();
                    hashSet.addAll(list);
                    File[] listFiles = file.listFiles();
                    int k = fVar.y().k();
                    if (listFiles != null) {
                        for (File file2 : listFiles) {
                            String name = file2.getName();
                            if (name.endsWith(".qct")) {
                                int indexOf = name.indexOf(46);
                                if (indexOf != -1) {
                                    try {
                                        int parseInt = Integer.parseInt(name.substring(0, indexOf));
                                        if (!(hashSet.contains(Integer.valueOf(parseInt)) || parseInt == onlineTag.g() || parseInt <= k)) {
                                            File file3 = new File(file2.getAbsolutePath() + ".del");
                                            file2.renameTo(file3);
                                            arrayList.add(file3);
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                        for (File file22 : arrayList) {
                            ao.a(file22);
                        }
                    }
                }
            }
        }
    }

    public void a(String str, int i) {
        if (i > 0) {
            try {
                ao.a(new File(v.a(str, i + 1)));
            } catch (Throwable th) {
                th.printStackTrace();
                j.c("BookReadPermissionHandler.delLimitFreeBookRecord Exception: " + th.getMessage());
                return;
            } finally {
                f(str);
            }
        }
        List e = b().e(str);
        int size = e.size();
        for (int i2 = 0; i2 < size; i2++) {
            int intValue = ((Integer) e.get(i2)).intValue();
            if (i != intValue) {
                ao.a(new File(v.a(str, intValue)));
            }
        }
        f(str);
    }

    public static boolean a(int i) {
        if (i == 5 || i == 4 || i == 1) {
            return true;
        }
        return false;
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
        sQLiteDatabase.execSQL("ALTER TABLE limitbook ADD pay_check_mode integer default 5");
    }

    public void a() {
        synchronized (h.class) {
            b = null;
        }
    }
}
