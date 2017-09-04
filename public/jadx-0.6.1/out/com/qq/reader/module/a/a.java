package com.qq.reader.module.a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.common.db.c;
import com.tencent.android.tpush.common.Constants;

/* compiled from: ActDBHandler */
public class a extends b {
    private static c a;
    private static volatile a b;

    /* compiled from: ActDBHandler */
    private class a extends c {
        final /* synthetic */ a a;

        public a(a aVar, String str, CursorFactory cursorFactory, int i) {
            this.a = aVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            if (i == 1 && i2 == 2) {
                sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN showTimeINTEGER DEFAULT 0");
                sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN activated_todayINTEGER DEFAULT 0");
                sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN activity_clickedINTEGER DEFAULT 0");
                sQLiteDatabase.execSQL("ALTER TABLE activity ADD COLUMN activity_notify_onceINTEGER DEFAULT 0");
            }
        }
    }

    public static a b() {
        if (b == null) {
            synchronized (a.class) {
                if (b == null) {
                    b = new a();
                }
            }
        }
        return b;
    }

    public void a(b bVar) {
        Object obj;
        Exception exception;
        Cursor cursor;
        Throwable th;
        Throwable th2;
        Object obj2;
        Cursor cursor2 = null;
        if (bVar != null) {
            try {
                SQLiteDatabase a = a.a();
                try {
                    long update;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("actId", bVar.a);
                    contentValues.put("startTime", Long.valueOf(bVar.b));
                    contentValues.put("endTime", Long.valueOf(bVar.c));
                    contentValues.put("showTime", Integer.valueOf(bVar.d));
                    contentValues.put("activity_notify_once", Boolean.valueOf(bVar.e));
                    contentValues.put("activated_today", Integer.valueOf(1));
                    contentValues.put("activity_clicked", Integer.valueOf(0));
                    Cursor query = a.query(Constants.FLAG_ACTIVITY_NAME, new String[]{"actId"}, "actId= '" + bVar.a + "'", null, null, null, null);
                    if (query != null) {
                        try {
                            if (query.getCount() > 0) {
                                update = (long) a.update(Constants.FLAG_ACTIVITY_NAME, contentValues, "actId= '" + bVar.a + "'", null);
                                if (update < 0) {
                                    Log.e("DB", "saveAndDelRepeatMessage with Error");
                                }
                                if (query != null) {
                                    query.close();
                                }
                                if (a != null) {
                                    try {
                                        a.c();
                                    } catch (Exception e) {
                                        return;
                                    }
                                }
                            }
                        } catch (Exception e2) {
                            obj = a;
                            exception = e2;
                            cursor = query;
                            try {
                                Log.e("DB", "saveAndDelRepeatMessage with exception : " + exception.getMessage());
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (cursor2 == null) {
                                    try {
                                        a.c();
                                    } catch (Exception e3) {
                                        return;
                                    }
                                }
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
                    update = a.insert(Constants.FLAG_ACTIVITY_NAME, null, contentValues);
                    if (update < 0) {
                        Log.e("DB", "saveAndDelRepeatMessage with Error");
                    }
                    if (query != null) {
                        query.close();
                    }
                    if (a != null) {
                        a.c();
                    }
                } catch (Exception e22) {
                    Exception exception2 = e22;
                    cursor = null;
                    obj = a;
                    exception = exception2;
                    Log.e("DB", "saveAndDelRepeatMessage with exception : " + exception.getMessage());
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (cursor2 == null) {
                        a.c();
                    }
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
            } catch (Exception e5) {
                exception = e5;
                cursor = null;
                Log.e("DB", "saveAndDelRepeatMessage with exception : " + exception.getMessage());
                if (cursor != null) {
                    cursor.close();
                }
                if (cursor2 == null) {
                    a.c();
                }
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
        }
    }

    private a() {
        a = new a(this, com.qq.reader.common.c.a.bN, null, 2);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists activity (actId text not null,startTime long default 0,endTime long default 0,showTime integer default 0,activated_today integer default 1,activity_clicked integer default 0,activity_notify_once integer default 0);");
    }

    public void a() {
        synchronized (a.class) {
            b = null;
        }
    }
}
