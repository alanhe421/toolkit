package com.qq.reader.module.qmessage.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.module.qmessage.data.impl.MessageBaseCard;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* compiled from: MessageDBHandler */
public class b extends com.qq.reader.appconfig.a.b {
    private static c a;
    private static volatile b b;

    /* compiled from: MessageDBHandler */
    private class a extends c {
        final /* synthetic */ b a;

        public a(b bVar, String str, CursorFactory cursorFactory, int i) {
            this.a = bVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    public static b b() {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    b = new b();
                }
            }
        }
        return b;
    }

    public void a(int i) {
        try {
            a.a().delete("qmessage", "type= '" + i + "'", null);
        } catch (Exception e) {
            f.a("DB", "delMessage with exception : " + e.getMessage());
        } finally {
            a.c();
        }
    }

    public boolean a(long j) {
        int delete;
        try {
            delete = a.a().delete("qmessage", "messageid= '" + j + "'", null);
        } catch (Exception e) {
            f.a("DB", "delMessage with exception : " + e.getMessage());
            delete = 0;
            if (delete <= 0) {
                return false;
            }
            return true;
        } finally {
            delete = a;
            delete.c();
        }
        if (delete <= 0) {
            return true;
        }
        return false;
    }

    public boolean b(long j) {
        Object obj;
        Exception exception;
        Cursor cursor;
        Throwable th;
        Throwable th2;
        Object obj2;
        Cursor cursor2 = null;
        try {
            SQLiteDatabase a = a.a();
            try {
                Cursor query = a.query("qmessage", new String[]{"time"}, "time= '" + j + "'", null, null, null, null);
                if (query != null) {
                    try {
                        if (query.getCount() > 0) {
                            if (query != null) {
                                query.close();
                            }
                            if (a != null) {
                                try {
                                    a.c();
                                } catch (Exception e) {
                                }
                            }
                            return true;
                        }
                    } catch (Exception e2) {
                        obj = a;
                        exception = e2;
                        cursor = query;
                        try {
                            f.a("DB", "saveAndDelRepeatMessage with exception : " + exception.getMessage());
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (cursor2 != null) {
                                try {
                                    a.c();
                                } catch (Exception e3) {
                                }
                            }
                            return false;
                        } catch (Throwable th3) {
                            th = th3;
                            Cursor cursor3 = cursor;
                            cursor = cursor2;
                            cursor2 = cursor3;
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            if (cursor != null) {
                                try {
                                    a.c();
                                } catch (Exception e4) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        cursor2 = query;
                        th2 = th4;
                        obj2 = a;
                        th = th2;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (cursor != null) {
                            a.c();
                        }
                        throw th;
                    }
                }
                if (query != null) {
                    query.close();
                }
                if (a != null) {
                    try {
                        a.c();
                    } catch (Exception e5) {
                    }
                }
            } catch (Exception e22) {
                Exception exception2 = e22;
                cursor = null;
                obj = a;
                exception = exception2;
                f.a("DB", "saveAndDelRepeatMessage with exception : " + exception.getMessage());
                if (cursor != null) {
                    cursor.close();
                }
                if (cursor2 != null) {
                    a.c();
                }
                return false;
            } catch (Throwable th42) {
                th2 = th42;
                obj2 = a;
                th = th2;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (cursor != null) {
                    a.c();
                }
                throw th;
            }
        } catch (Exception e6) {
            exception = e6;
            cursor = null;
            f.a("DB", "saveAndDelRepeatMessage with exception : " + exception.getMessage());
            if (cursor != null) {
                cursor.close();
            }
            if (cursor2 != null) {
                a.c();
            }
            return false;
        } catch (Throwable th5) {
            th = th5;
            cursor = null;
            if (cursor2 != null) {
                cursor2.close();
            }
            if (cursor != null) {
                a.c();
            }
            throw th;
        }
        return false;
    }

    public void a(c cVar) {
        Cursor cursor;
        Exception exception;
        Cursor cursor2;
        Exception exception2;
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        List e = cVar.f().e();
        if (e != null && e.size() > 0) {
            try {
                SQLiteDatabase a = a.a();
                try {
                    Iterator it = e.iterator();
                    cursor = null;
                    while (it.hasNext()) {
                        try {
                            MessageBaseCard messageBaseCard = (MessageBaseCard) it.next();
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("messageid", Long.valueOf(messageBaseCard.getMessageId()));
                            contentValues.put("type", Integer.valueOf(messageBaseCard.getMessageType()));
                            contentValues.put("time", Long.valueOf(messageBaseCard.getMessageTime()));
                            contentValues.put("json", messageBaseCard.getOrginCardJsonOjb().toString());
                            if (cVar.c() == 1) {
                                contentValues.put("isnew", Integer.valueOf(1));
                                messageBaseCard.setIsNewMessage(true);
                            }
                            Cursor query = a.query("qmessage", new String[]{"messageid"}, "messageid= '" + messageBaseCard.getMessageId() + "'", null, null, null, null);
                            if (query != null) {
                                try {
                                    if (query.getCount() > 0) {
                                        it.remove();
                                        cursor = query;
                                    }
                                } catch (Exception e2) {
                                    exception = e2;
                                    cursor2 = query;
                                    sQLiteDatabase = a;
                                    exception2 = exception;
                                } catch (Throwable th2) {
                                    cursor = query;
                                    sQLiteDatabase = a;
                                    th = th2;
                                }
                            }
                            if (a.insert("qmessage", null, contentValues) < 0) {
                                f.a("DB", "saveAndDelRepeatMessage with Error");
                                cursor = query;
                            } else {
                                cursor = query;
                            }
                        } catch (Exception e22) {
                            sQLiteDatabase = a;
                            exception2 = e22;
                            cursor2 = cursor;
                        } catch (Throwable th22) {
                            sQLiteDatabase = a;
                            th = th22;
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (a != null) {
                        try {
                            a.c();
                        } catch (Exception e3) {
                        }
                    }
                } catch (Exception e222) {
                    exception = e222;
                    cursor2 = null;
                    sQLiteDatabase = a;
                    exception2 = exception;
                    try {
                        f.a("DB", "saveAndDelRepeatMessage with exception : " + exception2.getMessage());
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (sQLiteDatabase != null) {
                            try {
                                a.c();
                            } catch (Exception e4) {
                            }
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        cursor = cursor2;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                            try {
                                a.c();
                            } catch (Exception e5) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th222) {
                    cursor = null;
                    sQLiteDatabase = a;
                    th = th222;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase != null) {
                        a.c();
                    }
                    throw th;
                }
            } catch (Exception e6) {
                exception2 = e6;
                cursor2 = null;
                f.a("DB", "saveAndDelRepeatMessage with exception : " + exception2.getMessage());
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (sQLiteDatabase != null) {
                    a.c();
                }
            } catch (Throwable th4) {
                th = th4;
                cursor = null;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    a.c();
                }
                throw th;
            }
        }
    }

    public ArrayList<String> a(int i, long j, int i2) {
        Cursor query;
        SQLiteDatabase sQLiteDatabase;
        Exception exception;
        Throwable th;
        ArrayList<String> arrayList = new ArrayList();
        Cursor cursor = null;
        SQLiteDatabase sQLiteDatabase2 = null;
        try {
            SQLiteDatabase a = a.a();
            try {
                query = a.query("qmessage", new String[]{"json", "type", "time"}, "time < " + j + " and " + "type" + "= '" + i + "'", null, null, null, "time DESC LIMIT " + i2);
            } catch (Exception e) {
                query = null;
                sQLiteDatabase = a;
                exception = e;
                sQLiteDatabase2 = sQLiteDatabase;
                try {
                    f.a("DB", "getLatestMessage with exception: " + exception.toString());
                    if (query != null) {
                        query.close();
                    }
                    if (sQLiteDatabase2 != null) {
                        try {
                            a.c();
                        } catch (Exception e2) {
                        }
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    cursor = query;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (sQLiteDatabase2 != null) {
                        try {
                            a.c();
                        } catch (Exception e3) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                sQLiteDatabase2 = a;
                th = th4;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase2 != null) {
                    a.c();
                }
                throw th;
            }
            try {
                if (query.moveToFirst()) {
                    do {
                        arrayList.add(query.getString(0));
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
                if (a != null) {
                    try {
                        a.c();
                    } catch (Exception e4) {
                    }
                }
            } catch (Exception e5) {
                Exception exception2 = e5;
                sQLiteDatabase2 = a;
                exception = exception2;
                f.a("DB", "getLatestMessage with exception: " + exception.toString());
                if (query != null) {
                    query.close();
                }
                if (sQLiteDatabase2 != null) {
                    a.c();
                }
                return arrayList;
            } catch (Throwable th32) {
                cursor = query;
                sQLiteDatabase = a;
                th = th32;
                sQLiteDatabase2 = sQLiteDatabase;
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase2 != null) {
                    a.c();
                }
                throw th;
            }
        } catch (Exception e6) {
            exception = e6;
            query = null;
            f.a("DB", "getLatestMessage with exception: " + exception.toString());
            if (query != null) {
                query.close();
            }
            if (sQLiteDatabase2 != null) {
                a.c();
            }
            return arrayList;
        } catch (Throwable th5) {
            th = th5;
            if (cursor != null) {
                cursor.close();
            }
            if (sQLiteDatabase2 != null) {
                a.c();
            }
            throw th;
        }
        return arrayList;
    }

    public ArrayList<String> a(int i, int i2) {
        Object obj;
        Exception exception;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        ArrayList<String> arrayList = new ArrayList();
        try {
            SQLiteDatabase a = a.a();
            try {
                cursor2 = a.query("qmessage", new String[]{"json", "type", "time"}, "type= '" + i + "'", null, null, null, "time DESC LIMIT " + i2);
                if (cursor2.moveToFirst()) {
                    do {
                        arrayList.add(cursor2.getString(0));
                    } while (cursor2.moveToNext());
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (a != null) {
                    try {
                        a.c();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                Exception exception2 = e2;
                obj = a;
                exception = exception2;
                try {
                    f.a("DB", "getLatestMessage with exception: " + exception.toString());
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (cursor != null) {
                        try {
                            a.c();
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
                            a.c();
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
                    a.c();
                }
                throw th;
            }
        } catch (Exception e5) {
            exception = e5;
            cursor = cursor2;
            f.a("DB", "getLatestMessage with exception: " + exception.toString());
            if (cursor2 != null) {
                cursor2.close();
            }
            if (cursor != null) {
                a.c();
            }
            return arrayList;
        } catch (Throwable th5) {
            th = th5;
            cursor = cursor2;
            if (cursor2 != null) {
                cursor2.close();
            }
            if (cursor != null) {
                a.c();
            }
            throw th;
        }
        return arrayList;
    }

    private b() {
        a = new a(this, com.qq.reader.common.c.a.bM, null, 1);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists qmessage (messageid text not null,type int default 0,time long default 0,json text not null,isnew int default 0);");
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
    }

    public void a() {
        synchronized (b.class) {
            b = null;
        }
    }
}
