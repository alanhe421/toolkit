package com.xiaomi.push.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.tencent.android.tpush.common.MessageKey;
import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.channel.commonutils.c.l;
import com.xiaomi.push.service.module.b;
import java.util.ArrayList;
import java.util.Iterator;

public class at {
    private static volatile at a;
    private Context b;

    private at(Context context) {
        this.b = context;
    }

    private synchronized Cursor a(SQLiteDatabase sQLiteDatabase) {
        Cursor cursor = null;
        synchronized (this) {
            l.a(false);
            try {
                cursor = sQLiteDatabase.rawQuery("SELECT * FROM geoMessage", null);
            } catch (Exception e) {
                c.d(e.toString());
            }
        }
        return cursor;
    }

    public static at a(Context context) {
        if (a == null) {
            synchronized (at.class) {
                if (a == null) {
                    a = new at(context);
                }
            }
        }
        return a;
    }

    public synchronized int a(String str) {
        int i = 0;
        synchronized (this) {
            l.a(false);
            if (!TextUtils.isEmpty(str)) {
                try {
                    int delete = as.a(this.b).a().delete("geoMessage", "message_id = ?", new String[]{str});
                    as.a(this.b).b();
                    i = delete;
                } catch (Exception e) {
                    c.d(e.toString());
                }
            }
        }
        return i;
    }

    public synchronized ArrayList<b> a() {
        ArrayList<b> arrayList;
        l.a(false);
        try {
            Cursor a = a(as.a(this.b).a());
            arrayList = new ArrayList();
            if (a != null) {
                while (a.moveToNext()) {
                    b bVar = new b();
                    bVar.a(a.getString(a.getColumnIndex("message_id")));
                    bVar.b(a.getString(a.getColumnIndex("geo_id")));
                    bVar.a(a.getBlob(a.getColumnIndex(MessageKey.MSG_CONTENT)));
                    bVar.a(a.getInt(a.getColumnIndex("action")));
                    bVar.a(a.getLong(a.getColumnIndex("deadline")));
                    arrayList.add(bVar);
                }
                a.close();
            }
            as.a(this.b).b();
        } catch (Exception e) {
            c.d(e.toString());
            arrayList = null;
        }
        return arrayList;
    }

    public synchronized boolean a(ArrayList<ContentValues> arrayList) {
        boolean z;
        l.a(false);
        if (arrayList == null || arrayList.size() <= 0) {
            z = false;
        } else {
            try {
                SQLiteDatabase a = as.a(this.b).a();
                a.beginTransaction();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if (-1 == a.insert("geoMessage", null, (ContentValues) it.next())) {
                        z = false;
                        break;
                    }
                }
                z = true;
                if (z) {
                    a.setTransactionSuccessful();
                }
                a.endTransaction();
                as.a(this.b).b();
            } catch (Exception e) {
                c.d(e.toString());
                z = false;
            }
        }
        return z;
    }

    public synchronized int b(String str) {
        int i = 0;
        synchronized (this) {
            l.a(false);
            if (!TextUtils.isEmpty(str)) {
                try {
                    int delete = as.a(this.b).a().delete("geoMessage", "geo_id = ?", new String[]{str});
                    as.a(this.b).b();
                    i = delete;
                } catch (Exception e) {
                    c.d(e.toString());
                }
            }
        }
        return i;
    }

    public synchronized ArrayList<b> c(String str) {
        ArrayList<b> arrayList;
        l.a(false);
        if (TextUtils.isEmpty(str)) {
            arrayList = null;
        } else {
            try {
                ArrayList a = a();
                ArrayList<b> arrayList2 = new ArrayList();
                Iterator it = a.iterator();
                while (it.hasNext()) {
                    b bVar = (b) it.next();
                    if (TextUtils.equals(bVar.c(), str)) {
                        arrayList2.add(bVar);
                    }
                }
                arrayList = arrayList2;
            } catch (Exception e) {
                c.d(e.toString());
                arrayList = null;
            }
        }
        return arrayList;
    }
}
