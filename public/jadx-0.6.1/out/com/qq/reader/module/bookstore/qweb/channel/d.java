package com.qq.reader.module.bookstore.qweb.channel;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: WebColumnDatebaseHandle */
public class d {
    private static d b;
    private static c c;
    private String a = "WebColumnDatebaseHandle";
    private ArrayList<ColumnWebEntity> d;

    /* compiled from: WebColumnDatebaseHandle */
    private class a extends c {
        final /* synthetic */ d a;

        public a(d dVar, String str, CursorFactory cursorFactory, int i) {
            this.a = dVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    public static synchronized d a() {
        d dVar;
        synchronized (d.class) {
            if (b == null) {
                b = new d();
            }
            dVar = b;
        }
        return dVar;
    }

    private d() {
        c = new a(this, com.qq.reader.common.c.a.aB, null, 1);
        this.d = new ArrayList();
    }

    public void a(Context context) {
        File file = new File(com.qq.reader.common.c.a.aB);
        if (file.exists()) {
            file.delete();
        }
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(R.array.preWebColumnDB);
        ao.a(context, obtainTypedArray, com.qq.reader.common.c.a.l, "");
        f.a("copy channelconfig.db", "success");
        obtainTypedArray.recycle();
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table if not exists webcolumn (_id integer primary key autoincrement,column_id integer,column_web_id integer,column_type_id integer,column_name text not null,column_selected integer,column_link_url text not null);");
        } catch (Exception e) {
            f.a(this.a, "createTable : " + e.getMessage());
        }
    }

    private synchronized void b() {
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = c.a();
            sQLiteDatabase.execSQL("drop table if exists webcolumn");
            a(sQLiteDatabase);
            if (sQLiteDatabase != null) {
                try {
                    c.c();
                } catch (Exception e) {
                }
            }
        } catch (Exception e2) {
            f.a("DB", "clear with exception : " + e2.getMessage());
            if (sQLiteDatabase != null) {
                try {
                    c.c();
                } catch (Exception e3) {
                }
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    c.c();
                } catch (Exception e4) {
                }
            }
        }
        return;
    }

    public synchronized void a(ArrayList<ColumnWebEntity> arrayList) {
        this.d.clear();
        this.d.addAll(arrayList);
        g.a().a(new WebColumnDatebaseHandle$2(this, arrayList));
    }

    public synchronized ArrayList<ColumnWebEntity> a(Boolean bool) {
        ArrayList<ColumnWebEntity> c;
        if (!bool.booleanValue() || this.d.size() <= 0) {
            c = c();
        } else {
            ArrayList<ColumnWebEntity> arrayList = new ArrayList();
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                ColumnWebEntity columnWebEntity = (ColumnWebEntity) it.next();
                if (columnWebEntity.getSelect() == 1) {
                    arrayList.add(columnWebEntity);
                }
            }
            c = arrayList;
        }
        return c;
    }

    private synchronized void a(int i, boolean z) {
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            int i2 = z ? 1 : 0;
            try {
                sQLiteDatabase = c.a();
                ContentValues contentValues = new ContentValues();
                contentValues.put("column_selected", Integer.valueOf(i2));
                sQLiteDatabase.update("webcolumn", contentValues, "column_web_id == " + i, null);
                if (sQLiteDatabase != null) {
                    try {
                        c.c();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                f.a("doSubscribed", "error in doSubscribed : " + e2);
                if (sQLiteDatabase != null) {
                    try {
                        c.c();
                    } catch (Exception e3) {
                    }
                }
            } catch (Throwable th) {
                if (sQLiteDatabase != null) {
                    try {
                        c.c();
                    } catch (Exception e4) {
                    }
                }
            }
        }
        return;
    }

    public synchronized boolean a(int i) {
        SQLiteDatabase sQLiteDatabase = null;
        boolean z = false;
        synchronized (this) {
            try {
                sQLiteDatabase = c.a();
                ContentValues contentValues = new ContentValues();
                contentValues.put("column_selected", Integer.valueOf(1));
                if (sQLiteDatabase.update("webcolumn", contentValues, "column_web_id == " + i, null) > 0) {
                    z = true;
                }
                if (sQLiteDatabase != null) {
                    try {
                        c.c();
                    } catch (Exception e) {
                    }
                }
                if (z) {
                    Iterator it = this.d.iterator();
                    while (it.hasNext()) {
                        ColumnWebEntity columnWebEntity = (ColumnWebEntity) it.next();
                        if (columnWebEntity.getWebid() == i) {
                            columnWebEntity.setSelected(1);
                        }
                    }
                }
            } catch (Exception e2) {
                f.a("doSubscribed", "error in doSubscribed : " + e2);
                if (sQLiteDatabase != null) {
                    try {
                        c.c();
                    } catch (Exception e3) {
                    }
                }
            } catch (Throwable th) {
                if (sQLiteDatabase != null) {
                    try {
                        c.c();
                    } catch (Exception e4) {
                    }
                }
            }
        }
        return z;
    }

    public synchronized int b(int i) {
        int i2;
        Object obj;
        Object obj2;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        synchronized (this) {
            try {
                SQLiteDatabase a = c.a();
                try {
                    cursor2 = a.query("webcolumn", new String[]{"column_selected"}, "column_web_id == " + i, null, null, null, null);
                    if (cursor2.moveToFirst()) {
                        int i3 = cursor2.getInt(0);
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (a != null) {
                            try {
                                c.c();
                            } catch (Exception e) {
                            }
                        }
                        i2 = i3;
                    } else {
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (a != null) {
                            try {
                                c.c();
                            } catch (Exception e2) {
                            }
                        }
                        i2 = -1;
                    }
                } catch (Exception e3) {
                    Exception exception = e3;
                    obj = a;
                    Exception exception2 = exception;
                    try {
                        f.a("DB", "getSubscribedWithWebId with exception: " + obj2);
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (cursor != null) {
                            try {
                                c.c();
                            } catch (Exception e4) {
                            }
                        }
                        i2 = -1;
                        return i2;
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (cursor != null) {
                            try {
                                c.c();
                            } catch (Exception e5) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    obj = a;
                    th = th4;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (cursor != null) {
                        c.c();
                    }
                    throw th;
                }
            } catch (Exception e6) {
                obj2 = e6;
                cursor = cursor2;
                f.a("DB", "getSubscribedWithWebId with exception: " + obj2);
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (cursor != null) {
                    c.c();
                }
                i2 = -1;
                return i2;
            } catch (Throwable th5) {
                th = th5;
                cursor = cursor2;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (cursor != null) {
                    c.c();
                }
                throw th;
            }
        }
        return i2;
    }

    public synchronized ArrayList<ColumnWebEntity> b(Boolean bool) {
        ArrayList<ColumnWebEntity> d;
        if (!bool.booleanValue() || this.d.size() <= 0) {
            d = d();
        } else {
            d = this.d;
        }
        return d;
    }

    private synchronized void b(ArrayList<ColumnWebEntity> arrayList) {
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            if (arrayList != null) {
                if (arrayList.size() > 0) {
                    b();
                    try {
                        sQLiteDatabase = c.a();
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.beginTransaction();
                            try {
                                Iterator it = arrayList.iterator();
                                while (it.hasNext()) {
                                    ColumnWebEntity columnWebEntity = (ColumnWebEntity) it.next();
                                    ContentValues contentValues = new ContentValues();
                                    contentValues.put("column_id", Integer.valueOf(columnWebEntity.getTitleid()));
                                    contentValues.put("column_web_id", Integer.valueOf(columnWebEntity.getWebid()));
                                    contentValues.put("column_type_id", Integer.valueOf(columnWebEntity.getTitleType()));
                                    contentValues.put("column_name", columnWebEntity.getTitleName());
                                    contentValues.put("column_selected", Integer.valueOf(columnWebEntity.getSelect()));
                                    contentValues.put("column_link_url", columnWebEntity.getLinkUrl());
                                    sQLiteDatabase.insert("webcolumn", null, contentValues);
                                }
                                sQLiteDatabase.setTransactionSuccessful();
                            } catch (Exception e) {
                                f.a("DB", "reInit exception : " + e.getMessage());
                            } finally {
                                sQLiteDatabase.endTransaction();
                            }
                        }
                        if (sQLiteDatabase != null) {
                            try {
                                c.c();
                            } catch (Exception e2) {
                            }
                        }
                    } catch (Exception e3) {
                        f.a("DB", "reInit  with exception : " + e3.getMessage());
                        if (sQLiteDatabase != null) {
                            try {
                                c.c();
                            } catch (Exception e4) {
                            }
                        }
                    } catch (Throwable th) {
                        if (sQLiteDatabase != null) {
                            try {
                                c.c();
                            } catch (Exception e5) {
                            }
                        }
                    }
                }
            }
        }
        return;
    }

    private synchronized ArrayList<ColumnWebEntity> c() {
        ArrayList<ColumnWebEntity> arrayList;
        Object obj;
        Object obj2;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        synchronized (this) {
            arrayList = new ArrayList();
            try {
                SQLiteDatabase a = c.a();
                try {
                    cursor2 = a.query("webcolumn", new String[]{"column_id", "column_web_id", "column_type_id", "column_name", "column_selected", "column_link_url"}, "column_selected !=0 ", null, null, null, "column_id ASC");
                    if (cursor2.moveToFirst()) {
                        do {
                            int i = cursor2.getInt(0);
                            int i2 = cursor2.getInt(1);
                            int i3 = cursor2.getInt(2);
                            String string = cursor2.getString(3);
                            int i4 = cursor2.getInt(4);
                            String string2 = cursor2.getString(5);
                            if (i > 0) {
                                arrayList.add(new ColumnWebEntity(i, i2, string, i3, i4, string2));
                            }
                        } while (cursor2.moveToNext());
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (a != null) {
                        try {
                            c.c();
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e2) {
                    Exception exception = e2;
                    obj = a;
                    Exception exception2 = exception;
                    try {
                        f.a("DB", "getAllSelected with exception: " + obj2);
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (cursor != null) {
                            try {
                                c.c();
                            } catch (Exception e3) {
                            }
                        }
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (cursor != null) {
                            try {
                                c.c();
                            } catch (Exception e4) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    obj = a;
                    th = th4;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (cursor != null) {
                        c.c();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                obj2 = e5;
                cursor = cursor2;
                f.a("DB", "getAllSelected with exception: " + obj2);
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (cursor != null) {
                    c.c();
                }
                return arrayList;
            } catch (Throwable th5) {
                th = th5;
                cursor = cursor2;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (cursor != null) {
                    c.c();
                }
                throw th;
            }
        }
        return arrayList;
    }

    private synchronized ArrayList<ColumnWebEntity> d() {
        ArrayList<ColumnWebEntity> arrayList;
        Object obj;
        Object obj2;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        synchronized (this) {
            arrayList = new ArrayList();
            try {
                SQLiteDatabase a = c.a();
                try {
                    cursor2 = a.query("webcolumn", new String[]{"column_id", "column_web_id", "column_type_id", "column_name", "column_selected", "column_link_url"}, null, null, null, null, null);
                    if (cursor2.moveToFirst()) {
                        do {
                            int i = cursor2.getInt(0);
                            int i2 = cursor2.getInt(1);
                            int i3 = cursor2.getInt(2);
                            String string = cursor2.getString(3);
                            int i4 = cursor2.getInt(4);
                            String string2 = cursor2.getString(5);
                            if (i > 0) {
                                arrayList.add(new ColumnWebEntity(i, i2, string, i3, i4, string2));
                            }
                        } while (cursor2.moveToNext());
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (a != null) {
                        try {
                            c.c();
                        } catch (Exception e) {
                        }
                    }
                } catch (Exception e2) {
                    Exception exception = e2;
                    obj = a;
                    Exception exception2 = exception;
                    try {
                        f.a("DB", "getAll with exception: " + obj2);
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (cursor != null) {
                            try {
                                c.c();
                            } catch (Exception e3) {
                            }
                        }
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (cursor != null) {
                            try {
                                c.c();
                            } catch (Exception e4) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    Throwable th4 = th3;
                    obj = a;
                    th = th4;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (cursor != null) {
                        c.c();
                    }
                    throw th;
                }
            } catch (Exception e5) {
                obj2 = e5;
                cursor = cursor2;
                f.a("DB", "getAll with exception: " + obj2);
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (cursor != null) {
                    c.c();
                }
                return arrayList;
            } catch (Throwable th5) {
                th = th5;
                cursor = cursor2;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (cursor != null) {
                    c.c();
                }
                throw th;
            }
        }
        return arrayList;
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
    }
}
