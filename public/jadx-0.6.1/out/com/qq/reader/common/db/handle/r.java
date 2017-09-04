package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.g;
import com.tencent.qalsdk.im_open.http;
import java.util.ArrayList;
import java.util.List;

/* compiled from: HistoryInfoHandler */
public class r {
    private static b a;
    private static r b;

    /* compiled from: HistoryInfoHandler */
    public static class a {
        public String a;
        public int b;
        public String c;
        public long d;
    }

    /* compiled from: HistoryInfoHandler */
    private class b extends c {
        final /* synthetic */ r a;

        public b(r rVar, String str, CursorFactory cursorFactory, int i) {
            this.a = rVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        }
    }

    public static synchronized r a() {
        r rVar;
        synchronized (r.class) {
            if (b == null) {
                b = new r();
            }
            rVar = b;
        }
        return rVar;
    }

    private r() {
        a = new b(this, com.qq.reader.common.c.a.aW, null, 1);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table if not exists historyinfo (_id integer primary key autoincrement,his_id  text not null,type integer default 0,name  text not null,time  long default 0,extra  text);");
        } catch (Exception e) {
            f.a("DB", "createTable channel db with exception : " + e.getMessage());
        }
    }

    public synchronized boolean b() {
        boolean z;
        try {
            SQLiteDatabase a = a.a();
            a.execSQL("drop table if exists historyinfo");
            a(a);
            a.c();
            z = true;
        } catch (Exception e) {
            f.a("DB", "clear with exception : " + e.getMessage());
            z = false;
            a.c();
        } catch (Throwable th) {
            a.c();
        }
        return z;
    }

    public synchronized boolean a(int i, String str) {
        boolean z = false;
        synchronized (this) {
            int delete;
            try {
                delete = a.a().delete("historyinfo", "type=" + i + " and " + "his_id" + "='" + str.replace("'", "''") + "'", null);
                a.c();
            } catch (Exception e) {
                f.a("DB", "removeHistoryByBookId with exception : " + e.getMessage());
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

    private synchronized void b(int i, String str, String str2) {
        Cursor cursor = null;
        synchronized (this) {
            if (str != null) {
                if (str.length() > 0 && str2 != null && str2.length() > 0) {
                    a(i, str);
                    try {
                        SQLiteDatabase a = a.a();
                        cursor = a.rawQuery("select count(*) from historyinfo", null);
                        if (cursor != null && cursor.moveToFirst() && cursor.getInt(0) >= http.Internal_Server_Error) {
                            a.delete("historyinfo", "_id in (select _id from historyinfo order by _id LIMIT 10)", null);
                        }
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("his_id", str);
                        contentValues.put("type", Integer.valueOf(i));
                        contentValues.put("name", str2);
                        contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
                        a.replace("historyinfo", null, contentValues);
                        if (cursor != null) {
                            cursor.close();
                        }
                        a.c();
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (cursor != null) {
                            cursor.close();
                        }
                        a.c();
                    } catch (Throwable th) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        a.c();
                    }
                }
            }
        }
    }

    public void a(int i, String str, String str2) {
        g.a().a(new HistoryInfoHandler$1(this, i, str, str2));
    }

    public synchronized List<a> c() {
        List<a> arrayList;
        Cursor query;
        Exception e;
        Throwable th;
        arrayList = new ArrayList();
        try {
            query = a.a().query("historyinfo", new String[]{"_id", "his_id", "type", "name", "time"}, null, null, null, null, null);
            try {
                if (query.moveToLast()) {
                    int i = 0;
                    do {
                        query.getInt(0);
                        String string = query.getString(1);
                        int i2 = query.getInt(2);
                        String string2 = query.getString(3);
                        long j = query.getLong(4);
                        a aVar = new a();
                        aVar.a = string;
                        aVar.b = i2;
                        aVar.c = string2;
                        aVar.d = j;
                        arrayList.add(aVar);
                        i++;
                        if (i >= http.Internal_Server_Error) {
                            break;
                        }
                    } while (query.moveToPrevious());
                }
                if (query != null) {
                    query.close();
                }
                a.c();
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                f.a("DB", "getAllHistoryCount with exception: " + e.toString());
                if (query != null) {
                    query.close();
                }
                a.c();
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                a.c();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            a.c();
            throw th;
        }
        return arrayList;
    }
}
