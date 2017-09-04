package com.qq.reader.cservice.bookfollow;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.monitor.f;

/* compiled from: OrderBookDBHandle */
public class c {
    private static a a = new a(com.qq.reader.common.c.a.ba, null, 1);

    /* compiled from: OrderBookDBHandle */
    private static class a extends com.qq.reader.common.db.c {
        public a(String str, CursorFactory cursorFactory, int i) {
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            c.b(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    static {
        synchronized (c.class) {
            if (a == null) {
            }
        }
    }

    private static void b(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists orderbook (_id integer primary key autoincrement,orderid text not null);");
        sQLiteDatabase.execSQL("create unique index if not exists idx on orderbook (orderid);");
    }

    public static synchronized boolean a(String str) {
        int count;
        Exception e;
        boolean z;
        Throwable th;
        Cursor cursor = null;
        synchronized (c.class) {
            Cursor query;
            try {
                query = a.a().query("orderbook", new String[]{"orderid"}, "orderid= '" + str.replace("'", "''") + "'", null, null, null, null);
                try {
                    count = query.getCount();
                    if (query != null) {
                        query.close();
                    }
                    a.c();
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", "isOrdered with exception : " + e.getMessage());
                        if (query != null) {
                            query.close();
                        }
                        a.c();
                        count = 0;
                        if (count > 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = query;
                        if (cursor != null) {
                            cursor.close();
                        }
                        a.c();
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                f.a("DB", "isOrdered with exception : " + e.getMessage());
                if (query != null) {
                    query.close();
                }
                a.c();
                count = 0;
                if (count > 0) {
                    z = false;
                } else {
                    z = true;
                }
                return z;
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                a.c();
                throw th;
            }
            if (count > 0) {
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    public static synchronized void b(String str) {
        synchronized (c.class) {
            try {
                SQLiteDatabase a = a.a();
                ContentValues contentValues = new ContentValues();
                contentValues.put("orderid", str);
                a.replace("orderbook", null, contentValues);
                a.c();
            } catch (Exception e) {
                f.a("DB", "addOrder with exception : " + e.getMessage());
                a.c();
            } catch (Throwable th) {
                a.c();
            }
        }
    }
}
