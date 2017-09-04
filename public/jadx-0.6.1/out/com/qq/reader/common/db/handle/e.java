package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;

/* compiled from: BookMoreInfoHandler */
public class e {
    private static b a;
    private static e b;
    private static int[] c = null;

    /* compiled from: BookMoreInfoHandler */
    public static class a {
        public long a;
        public int b;
        public int c;
    }

    /* compiled from: BookMoreInfoHandler */
    private class b extends c {
        final /* synthetic */ e a;

        public b(e eVar, String str, CursorFactory cursorFactory, int i) {
            this.a = eVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    public static synchronized e a() {
        e eVar;
        synchronized (e.class) {
            if (b == null) {
                b = new e();
            }
            eVar = b;
        }
        return eVar;
    }

    public boolean a(Context context, long j) {
        if (c == null) {
            c = context.getResources().getIntArray(R.array.ttsbid);
        }
        if (c != null) {
            for (int i : c) {
                if (j == ((long) i)) {
                    return false;
                }
            }
        }
        return true;
    }

    private e() {
        a = new b(this, com.qq.reader.common.c.a.aV, null, 2);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table if not exists bookmoreinfo (_id integer primary key autoincrement,onlineid text not null,commentcount  integer default 0,lastnewadd  integer default 0,cp_info  text);");
        } catch (Exception e) {
            f.a("DB", "createTable channel db with exception : " + e.getMessage());
        }
    }

    public synchronized boolean a(String str) {
        boolean z = false;
        synchronized (this) {
            int delete;
            try {
                delete = a.a().delete("bookmoreinfo", "onlineid= '" + str.replace("'", "''") + "'", null);
                a.c();
            } catch (Exception e) {
                f.a("DB", "removeBookChannelByBookId with exception : " + e.getMessage());
                a.c();
                delete = 0;
            } catch (Throwable th) {
                a.c();
            }
            if (delete > 0) {
                z = true;
            }
        }
        return z;
    }

    public synchronized String b(String str) {
        Cursor query;
        String string;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            try {
                query = a.a().query("bookmoreinfo", new String[]{"onlineid", "cp_info"}, "onlineid= '" + str.replace("'", "''") + "'", null, null, null, null);
                try {
                    if (query.moveToFirst()) {
                        string = query.getString(1);
                        if (query != null) {
                            query.close();
                        }
                        if (a != null) {
                            a.c();
                        }
                    } else {
                        if (query != null) {
                            query.close();
                        }
                        if (a != null) {
                            a.c();
                        }
                        string = null;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.b("DB", "getCpInfoByBookId with exception: " + e.toString());
                        if (query != null) {
                            query.close();
                        }
                        if (a != null) {
                            a.c();
                        }
                        string = null;
                        return string;
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
                f.b("DB", "getCpInfoByBookId with exception: " + e.toString());
                if (query != null) {
                    query.close();
                }
                if (a != null) {
                    a.c();
                }
                string = null;
                return string;
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
        return string;
    }

    public synchronized void a(String str, String str2) {
        if (str != null) {
            if (str.length() > 0 && str2 != null && str2.length() > 0) {
                a(str);
                try {
                    SQLiteDatabase a = a.a();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("onlineid", str);
                    contentValues.put("cp_info", str2);
                    a.replace("bookmoreinfo", null, contentValues);
                    a.c();
                } catch (Exception e) {
                    e.printStackTrace();
                    a.c();
                } catch (Throwable th) {
                    a.c();
                }
            }
        }
    }

    public synchronized HashMap<Long, a> b() {
        HashMap<Long, a> hashMap;
        Cursor query;
        Exception e;
        Throwable th;
        hashMap = new HashMap();
        try {
            query = a.a().query("bookmoreinfo", new String[]{"onlineid", "commentcount", "lastnewadd"}, null, null, null, null, null);
            try {
                if (query.moveToFirst()) {
                    do {
                        long longValue = Long.valueOf(query.getString(0)).longValue();
                        int i = query.getInt(1);
                        int i2 = query.getInt(2);
                        a aVar = new a();
                        aVar.a = longValue;
                        aVar.b = i;
                        aVar.c = i2;
                        hashMap.put(Long.valueOf(longValue), aVar);
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
                a.c();
            } catch (Exception e2) {
                e = e2;
                try {
                    f.a("DB", "getAllCommentCount with exception: " + e.toString());
                    if (query != null) {
                        query.close();
                    }
                    a.c();
                    return hashMap;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    a.c();
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            f.a("DB", "getAllCommentCount with exception: " + e.toString());
            if (query != null) {
                query.close();
            }
            a.c();
            return hashMap;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            a.c();
            throw th;
        }
        return hashMap;
    }

    public synchronized boolean a(String str, int i, int i2) {
        boolean z = false;
        synchronized (this) {
            if (str != null && i >= 0 && i2 >= 0) {
                try {
                    SQLiteDatabase a = a.a();
                    a.delete("bookmoreinfo", "onlineid='" + str.replace("'", "''") + "'", null);
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("onlineid", str);
                    contentValues.put("commentcount", Integer.valueOf(i));
                    contentValues.put("lastnewadd", Integer.valueOf(i2));
                    if (a.replace("bookmoreinfo", null, contentValues) > 0) {
                        z = true;
                    }
                } catch (Exception e) {
                    f.a("BookmarkHandle", "error in update : " + e.toString());
                } finally {
                    a.c();
                }
            }
        }
        return z;
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select commentcount from bookmoreinfo", null);
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
                        f.a("DB", " update1To2 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE bookmoreinfo ADD commentcount integer default 0");
                        sQLiteDatabase.execSQL("ALTER TABLE bookmoreinfo ADD lastnewadd integer default 0");
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
            f.a("DB", " update1To2 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE bookmoreinfo ADD commentcount integer default 0");
            sQLiteDatabase.execSQL("ALTER TABLE bookmoreinfo ADD lastnewadd integer default 0");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE bookmoreinfo ADD commentcount integer default 0");
        sQLiteDatabase.execSQL("ALTER TABLE bookmoreinfo ADD lastnewadd integer default 0");
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
}
