package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.qq.reader.common.c.a;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;

/* compiled from: ExtendedReminderEventDBHandle */
public class p {
    private static p a;
    private static c b;

    public static p a() {
        if (a == null) {
            synchronized (p.class) {
                if (a == null) {
                    a = new p();
                }
            }
        }
        return a;
    }

    private p() {
        b = new q(a.bv, null, 1);
    }

    public synchronized String a(String str, long j) {
        String str2;
        Object obj;
        Exception exception;
        Cursor cursor;
        Throwable th;
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor2 = null;
        synchronized (this) {
            if (b == null) {
                b = new q(a.bv, null, 1);
            }
            try {
                String[] strArr = new String[]{"system_event_id"};
                SQLiteDatabase a = b.a();
                try {
                    String string;
                    r2 = a.query("reminder_event_table_name", strArr, "book_id='" + str + "' AND " + "start_time" + "=" + j, null, null, null, null);
                    if (r2 != null) {
                        try {
                            r2.moveToFirst();
                            if (r2.getCount() > 0) {
                                string = r2.getString(r2.getColumnIndex("system_event_id"));
                                if (r2 != null) {
                                    r2.close();
                                }
                                if (a != null) {
                                    b.c();
                                }
                                str2 = string;
                            }
                        } catch (Exception e) {
                            Exception exception2 = e;
                            obj = a;
                            exception = exception2;
                            try {
                                f.a("ExtendedReminderEventDBHandle", "getEventIdByBookId*" + exception.getLocalizedMessage());
                                if (r2 != null) {
                                    r2.close();
                                }
                                if (cursor != null) {
                                    b.c();
                                }
                                str2 = null;
                                return str2;
                            } catch (Throwable th2) {
                                th = th2;
                                cursor2 = r2;
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                if (cursor != null) {
                                    b.c();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            cursor2 = r2;
                            sQLiteDatabase = a;
                            th = th3;
                            obj = sQLiteDatabase;
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            if (cursor != null) {
                                b.c();
                            }
                            throw th;
                        }
                    }
                    obj = null;
                    if (r2 != null) {
                        r2.close();
                    }
                    if (a != null) {
                        b.c();
                    }
                    str2 = string;
                } catch (Exception e2) {
                    r2 = null;
                    sQLiteDatabase = a;
                    exception = e2;
                    obj = sQLiteDatabase;
                    f.a("ExtendedReminderEventDBHandle", "getEventIdByBookId*" + exception.getLocalizedMessage());
                    if (r2 != null) {
                        r2.close();
                    }
                    if (cursor != null) {
                        b.c();
                    }
                    str2 = null;
                    return str2;
                } catch (Throwable th32) {
                    Throwable th4 = th32;
                    obj = a;
                    th = th4;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (cursor != null) {
                        b.c();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                exception = e3;
                cursor = null;
                r2 = null;
                f.a("ExtendedReminderEventDBHandle", "getEventIdByBookId*" + exception.getLocalizedMessage());
                Cursor query;
                if (query != null) {
                    query.close();
                }
                if (cursor != null) {
                    b.c();
                }
                str2 = null;
                return str2;
            } catch (Throwable th5) {
                th = th5;
                cursor = null;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (cursor != null) {
                    b.c();
                }
                throw th;
            }
        }
        return str2;
    }

    public synchronized boolean a(String str, String str2, long j) {
        boolean z = true;
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            if (b == null) {
                b = new q(a.bv, null, 1);
            }
            try {
                ContentValues contentValues = new ContentValues();
                contentValues.put("system_event_id", str2);
                contentValues.put("book_id", str);
                contentValues.put("start_time", Long.valueOf(j));
                sQLiteDatabase = b.a();
                long insert = sQLiteDatabase.insert("reminder_event_table_name", null, contentValues);
                if (sQLiteDatabase != null) {
                    b.c();
                }
                if (insert <= 0) {
                    z = false;
                }
            } catch (Exception e) {
                f.a("ExtendedReminderEventDBHandle", "insertEventRecord*" + e.getLocalizedMessage());
                if (sQLiteDatabase != null) {
                    b.c();
                }
                z = false;
            } catch (Throwable th) {
                if (sQLiteDatabase != null) {
                    b.c();
                }
            }
        }
        return z;
    }

    public synchronized boolean b(String str, long j) {
        boolean z = true;
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            if (b == null) {
                b = new q(a.bv, null, 1);
            }
            try {
                sQLiteDatabase = b.a();
                long delete = (long) sQLiteDatabase.delete("reminder_event_table_name", "book_id='" + str + "'" + " AND " + "start_time" + "=" + j, null);
                if (sQLiteDatabase != null) {
                    b.c();
                }
                if (delete <= 0) {
                    z = false;
                }
            } catch (Exception e) {
                f.a("ExtendedReminderEventDBHandle", "deleteEventRecord*" + e.getLocalizedMessage());
                if (sQLiteDatabase != null) {
                    b.c();
                }
                z = false;
            } catch (Throwable th) {
                if (sQLiteDatabase != null) {
                    b.c();
                }
            }
        }
        return z;
    }
}
