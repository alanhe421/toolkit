package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ChapterPayDBHandle */
public class k extends b {
    private static volatile k a = null;
    private static a b;

    /* compiled from: ChapterPayDBHandle */
    private class a extends c {
        final /* synthetic */ k a;

        public a(k kVar, String str, CursorFactory cursorFactory, int i) {
            this.a = kVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    public static k a(Context context) {
        if (a == null) {
            synchronized (k.class) {
                if (a == null) {
                    a = new k(context);
                }
            }
        }
        return a;
    }

    public static synchronized void b() {
        synchronized (k.class) {
            synchronized (k.class) {
                a = null;
            }
        }
    }

    private k(Context context) {
        b = new a(this, ao.n(context), null, 1);
    }

    public synchronized List<Integer> a(String str) {
        List<Integer> arrayList;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            arrayList = new ArrayList();
            Cursor query;
            try {
                query = b.a().query("chapterpay", new String[]{"chapterid"}, "bookid= '" + str.replace("'", "''") + "'", null, null, null, null);
                try {
                    if (query.moveToFirst()) {
                        do {
                            arrayList.add(Integer.valueOf(query.getInt(0)));
                        } while (query.moveToNext());
                    }
                    if (query != null) {
                        query.close();
                    }
                    if (b != null) {
                        b.c();
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", "ChapterPayDBHandle getWritableDatabase with exception : " + e.getMessage());
                        if (query != null) {
                            query.close();
                        }
                        if (b != null) {
                            b.c();
                        }
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = query;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (b != null) {
                            b.c();
                        }
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                f.a("DB", "ChapterPayDBHandle getWritableDatabase with exception : " + e.getMessage());
                if (query != null) {
                    query.close();
                }
                if (b != null) {
                    b.c();
                }
                return arrayList;
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                if (b != null) {
                    b.c();
                }
                throw th;
            }
        }
        return arrayList;
    }

    public synchronized void a(String str, List<Integer> list) {
        try {
            SQLiteDatabase a = b.a();
            if (a != null) {
                a.beginTransaction();
                try {
                    for (Integer intValue : list) {
                        int intValue2 = intValue.intValue();
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("bookchapter", str + "_" + intValue2);
                        contentValues.put("bookid", str);
                        contentValues.put("chapterid", Integer.valueOf(intValue2));
                        a.replace("chapterpay", null, contentValues);
                    }
                    a.setTransactionSuccessful();
                } catch (Exception e) {
                    f.a("DB", "ChapterPayDBHandle getWritableDatabase with exception : " + e.getMessage());
                } finally {
                    a.endTransaction();
                }
            }
            b.c();
        } catch (Exception e2) {
            f.a("DB", "ChapterPayDBHandle getWritableDatabase with exception : " + e2.getMessage());
            b.c();
        } catch (Throwable th) {
            b.c();
        }
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists chapterpay (_id integer primary key autoincrement,bookchapter text not null,bookid text not null,chapterid integer default 0 );");
        sQLiteDatabase.execSQL("create unique index if not exists idx on chapterpay (bookchapter);");
    }

    public void a() {
        a = null;
    }
}
