package com.qq.reader.module.redpacket.square.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.redpacket.model.RedPacket;
import java.util.ArrayList;

/* compiled from: RedPacketSquareDBHandler */
public class c extends com.qq.reader.appconfig.a.b {
    private static com.qq.reader.common.db.c a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final String g;
    private final String h;
    private final String i;
    private final String j;
    private final String k;
    private final String l;
    private final String m;
    private long n;

    /* compiled from: RedPacketSquareDBHandler */
    public static class a {
        public static c a = new c();
    }

    /* compiled from: RedPacketSquareDBHandler */
    private class b extends com.qq.reader.common.db.c {
        final /* synthetic */ c a;

        public b(c cVar, String str, CursorFactory cursorFactory, int i) {
            this.a = cVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    private c() {
        this.b = "packet_id";
        this.c = "packet_create_time";
        this.d = "packet_expire_time";
        this.e = "packet_des";
        this.f = "packet_type";
        this.g = "packet_is_offical";
        this.h = "packet_book_name";
        this.i = "packet_book_id";
        this.j = "packet_sender_icon";
        this.k = "packet_sender_name";
        this.l = "packet_cbid";
        this.m = "packet_online";
        a = new b(this, com.qq.reader.common.c.a.bO, null, 1);
    }

    public static c b() {
        return a.a;
    }

    public void a() {
        this.n = -1;
    }

    public void a(long j) {
        this.n = j;
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists redPacketSquare (packet_id long default 0,packet_create_time long default 0,packet_expire_time long default 0,packet_type int default 0,packet_is_offical int default 0,packet_des text not null,packet_book_id long default 0,packet_book_name text not null,packet_sender_icon text not null,packet_sender_name text not null,packet_cbid long default 0,packet_online int default 1);");
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
    }

    public synchronized void a(ArrayList<RedPacket> arrayList) {
        SQLiteDatabase sQLiteDatabase;
        Exception exception;
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            if (arrayList != null) {
                if (arrayList.size() > 0) {
                    Cursor cursor2;
                    try {
                        SQLiteDatabase a = a.a();
                        int i = 0;
                        cursor2 = null;
                        while (i < arrayList.size()) {
                            try {
                                RedPacket redPacket = (RedPacket) arrayList.get(i);
                                ContentValues contentValues = new ContentValues();
                                contentValues.put("packet_id", Long.valueOf(redPacket.d()));
                                contentValues.put("packet_book_id", Long.valueOf(redPacket.e()));
                                contentValues.put("packet_book_name", redPacket.h());
                                contentValues.put("packet_create_time", Long.valueOf(redPacket.f()));
                                contentValues.put("packet_expire_time", Long.valueOf(redPacket.g()));
                                contentValues.put("packet_des", redPacket.i());
                                contentValues.put("packet_type", Integer.valueOf(redPacket.j()));
                                contentValues.put("packet_is_offical", Integer.valueOf(redPacket.o()));
                                contentValues.put("packet_sender_icon", redPacket.m());
                                contentValues.put("packet_sender_name", redPacket.l());
                                contentValues.put("packet_cbid", Long.valueOf(redPacket.u()));
                                contentValues.put("packet_online", Integer.valueOf(redPacket.v()));
                                Cursor query = a.query("redPacketSquare", new String[]{"packet_id"}, "packet_id= '" + redPacket.d() + "'", null, null, null, null);
                                if (query != null) {
                                    try {
                                        if (query.getCount() > 0) {
                                            i++;
                                            cursor2 = query;
                                        }
                                    } catch (Exception e) {
                                        Exception exception2 = e;
                                        cursor = query;
                                        sQLiteDatabase = a;
                                        exception = exception2;
                                    } catch (Throwable th2) {
                                        cursor2 = query;
                                        sQLiteDatabase = a;
                                        th = th2;
                                    }
                                }
                                if (a.insert("redPacketSquare", null, contentValues) < 0) {
                                    f.a("DB", "saveAndDelRepeatMessage with Error");
                                }
                                i++;
                                cursor2 = query;
                            } catch (Exception e2) {
                                sQLiteDatabase = a;
                                exception = e2;
                                cursor = cursor2;
                            } catch (Throwable th22) {
                                sQLiteDatabase = a;
                                th = th22;
                            }
                        }
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (a != null) {
                            try {
                                a.c();
                            } catch (Exception e3) {
                            }
                        }
                    } catch (Exception e4) {
                        exception = e4;
                        Object obj = null;
                        try {
                            f.a("DB", "saveAndDelRepeatMessage with exception : " + exception.getMessage());
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (sQLiteDatabase != null) {
                                try {
                                    a.c();
                                } catch (Exception e5) {
                                }
                            }
                            return;
                        } catch (Throwable th3) {
                            th = th3;
                            cursor2 = cursor;
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            if (sQLiteDatabase != null) {
                                try {
                                    a.c();
                                } catch (Exception e6) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        cursor2 = null;
                        sQLiteDatabase = null;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (sQLiteDatabase != null) {
                            a.c();
                        }
                        throw th;
                    }
                }
            }
        }
        return;
    }

    public synchronized long c() {
        long j;
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            j = -1;
            SQLiteDatabase a;
            try {
                a = a.a();
                try {
                    cursor = a.rawQuery("select * from redPacketSquare order by packet_id desc", new String[0]);
                    if (cursor.moveToNext()) {
                        j = cursor.getLong(0);
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (a != null) {
                        try {
                            a.c();
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e2) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (a != null) {
                        try {
                            a.c();
                        } catch (Exception e3) {
                        }
                    }
                    return j;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (a != null) {
                        try {
                            a.c();
                        } catch (Exception e4) {
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                a = cursor;
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
                return j;
            } catch (Throwable th3) {
                th = th3;
                a = cursor;
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
                throw th;
            }
        }
        return j;
    }

    public synchronized ArrayList<RedPacket> a(int i) {
        ArrayList<RedPacket> arrayList;
        SQLiteDatabase a;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            arrayList = new ArrayList();
            try {
                a = a.a();
                try {
                    if (!TextUtils.isEmpty(d.bL(ReaderApplication.getApplicationImp().getApplicationContext()))) {
                        i--;
                    }
                    cursor = a.rawQuery("select * from redPacketSquare where packet_create_time >? order by packet_id desc limit ?", new String[]{this.n + "", i + ""});
                    if (cursor.moveToNext()) {
                        do {
                            arrayList.add(a(cursor));
                        } while (cursor.moveToNext());
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (a != null) {
                        try {
                            a.c();
                        } catch (Exception e2) {
                        }
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        f.a("DB", "getLatestRedPackets with exception: " + e.toString());
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (a != null) {
                            try {
                                a.c();
                            } catch (Exception e4) {
                            }
                        }
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (a != null) {
                            try {
                                a.c();
                            } catch (Exception e5) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e6) {
                e = e6;
                a = cursor;
                f.a("DB", "getLatestRedPackets with exception: " + e.toString());
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
                return arrayList;
            } catch (Throwable th3) {
                th = th3;
                a = cursor;
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
                throw th;
            }
        }
        return arrayList;
    }

    public synchronized ArrayList<RedPacket> a(long j, int i) {
        ArrayList<RedPacket> arrayList;
        Cursor cursor = null;
        synchronized (this) {
            arrayList = new ArrayList();
            SQLiteDatabase a;
            try {
                a = a.a();
                try {
                    cursor = a.rawQuery("select * from redPacketSquare where packet_id <? and packet_create_time >? order by packet_id desc limit ?", new String[]{j + "", this.n + "", i + ""});
                    if (cursor.moveToNext()) {
                        do {
                            arrayList.add(a(cursor));
                        } while (cursor.moveToNext());
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (a != null) {
                        try {
                            a.c();
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e2) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (a != null) {
                        try {
                            a.c();
                        } catch (Exception e3) {
                        }
                    }
                    return arrayList;
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (a != null) {
                        try {
                            a.c();
                        } catch (Exception e4) {
                        }
                    }
                    return arrayList;
                }
            } catch (Exception e5) {
                a = cursor;
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
                return arrayList;
            } catch (Throwable th2) {
                a = cursor;
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
                return arrayList;
            }
        }
        return arrayList;
    }

    private RedPacket a(Cursor cursor) {
        RedPacket redPacket = new RedPacket();
        redPacket.b(cursor.getLong(0));
        redPacket.d(cursor.getLong(1));
        redPacket.e(cursor.getLong(2));
        redPacket.b(cursor.getInt(3));
        redPacket.e(cursor.getInt(4));
        redPacket.b(cursor.getString(5));
        redPacket.c(cursor.getLong(6));
        redPacket.a(cursor.getString(7));
        redPacket.d(cursor.getString(8));
        redPacket.c(cursor.getString(9));
        redPacket.f(cursor.getLong(10));
        redPacket.i(cursor.getInt(11));
        return redPacket;
    }

    public synchronized void b(long j) {
        if (j != -1) {
            try {
                a.a().delete("redPacketSquare", "packet_id<? or packet_id=?", new String[]{j + "", j + ""});
            } catch (Exception e) {
            } finally {
                a.c();
            }
        }
    }

    public synchronized void d() {
        if (this.n != -1) {
            try {
                a.a().delete("redPacketSquare", "packet_create_time <?", new String[]{this.n + ""});
            } catch (Exception e) {
            } finally {
                a.c();
            }
        }
    }
}
