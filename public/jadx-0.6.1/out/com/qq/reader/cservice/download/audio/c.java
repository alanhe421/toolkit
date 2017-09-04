package com.qq.reader.cservice.download.audio;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.common.monitor.f;
import java.util.Vector;

/* compiled from: AudioDownloadDBHandler */
public class c extends b {
    private static com.qq.reader.common.db.c a;
    private static volatile c b;

    /* compiled from: AudioDownloadDBHandler */
    private class a extends com.qq.reader.common.db.c {
        final /* synthetic */ c a;

        public a(c cVar, String str, CursorFactory cursorFactory, int i) {
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

    public static c b() {
        if (b == null) {
            synchronized (c.class) {
                if (b == null) {
                    b = new c();
                }
            }
        }
        return b;
    }

    private c() {
        a = new a(this, com.qq.reader.common.c.a.aN, null, 1);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists audio_book_downloadInfo (_id integer primary key autoincrement,book_task_name text not null,all_id long default 0,chapter_id  integer default 0,objecturl text,dir text,file_name text,state long default 0,cur_size long,size integer,progress integer default 0,format text not null,drm_flag integer default 2);");
    }

    public synchronized boolean a(b bVar) {
        Cursor query;
        boolean z;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            try {
                SQLiteDatabase a = a.a();
                if (a != null) {
                    a.beginTransaction();
                    try {
                        long update;
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("book_task_name", bVar.b());
                        contentValues.put("all_id", Long.valueOf(bVar.a));
                        contentValues.put("chapter_id", Integer.valueOf(bVar.b));
                        contentValues.put("objecturl", bVar.g());
                        contentValues.put("state", Integer.valueOf(bVar.j()));
                        contentValues.put("cur_size", Long.valueOf(bVar.l()));
                        contentValues.put("size", Long.valueOf(bVar.m()));
                        contentValues.put("dir", bVar.h);
                        contentValues.put("format", "mp3");
                        contentValues.put("file_name", bVar.i());
                        query = a.query("audio_book_downloadInfo", new String[]{"all_id", "chapter_id"}, "all_id= '" + bVar.a + "' and " + "chapter_id" + "= '" + bVar.b + "'", null, null, null, null);
                        if (query != null) {
                            try {
                                if (query.getCount() > 0) {
                                    query.moveToFirst();
                                    update = (long) a.update("audio_book_downloadInfo", contentValues, "all_id= '" + bVar.a + "' and " + "chapter_id" + "= '" + bVar.b + "'", null);
                                    a.setTransactionSuccessful();
                                    if (update <= 0) {
                                        if (query != null) {
                                            query.close();
                                        }
                                        a.endTransaction();
                                        if (a != null) {
                                            a.c();
                                        }
                                        z = true;
                                    } else {
                                        if (query != null) {
                                            query.close();
                                        }
                                        a.endTransaction();
                                    }
                                }
                            } catch (Exception e2) {
                                e = e2;
                                try {
                                    f.a("MusicMarkDB", "add with exception : " + e.getMessage());
                                    if (query != null) {
                                        query.close();
                                    }
                                    a.endTransaction();
                                    if (a != null) {
                                        a.c();
                                    }
                                    z = false;
                                    return z;
                                } catch (Throwable th2) {
                                    th = th2;
                                    cursor = query;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    a.endTransaction();
                                    throw th;
                                }
                            }
                        }
                        update = a.insert("audio_book_downloadInfo", null, contentValues);
                        a.setTransactionSuccessful();
                        if (update <= 0) {
                            if (query != null) {
                                query.close();
                            }
                            a.endTransaction();
                        } else {
                            if (query != null) {
                                query.close();
                            }
                            a.endTransaction();
                            if (a != null) {
                                a.c();
                            }
                            z = true;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        query = null;
                        f.a("MusicMarkDB", "add with exception : " + e.getMessage());
                        if (query != null) {
                            query.close();
                        }
                        a.endTransaction();
                        if (a != null) {
                            a.c();
                        }
                        z = false;
                        return z;
                    } catch (Throwable th3) {
                        th = th3;
                        if (cursor != null) {
                            cursor.close();
                        }
                        a.endTransaction();
                        throw th;
                    }
                }
                if (a != null) {
                    a.c();
                }
            } catch (Exception e4) {
                f.a("MusicMarkDB", "add getWritableDatabase with exception : " + e4.getMessage());
                if (a != null) {
                    a.c();
                }
            } catch (Throwable th4) {
                if (a != null) {
                    a.c();
                }
            }
            z = false;
        }
        return z;
    }

    public synchronized Vector<com.qq.reader.cservice.download.a.b> c() {
        Vector<com.qq.reader.cservice.download.a.b> vector;
        Exception e;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        synchronized (this) {
            vector = new Vector();
            try {
                SQLiteDatabase a = a.a();
                if (a != null) {
                    a.beginTransaction();
                    try {
                        Cursor query = a.query("audio_book_downloadInfo", new String[]{"book_task_name", "all_id", "chapter_id", "objecturl", "state", "cur_size", "size", "progress", "format", "drm_flag", "dir", "file_name"}, null, null, null, null, null);
                        try {
                            if (query.moveToFirst()) {
                                do {
                                    String string = query.getString(0);
                                    long j = query.getLong(1);
                                    int i = query.getInt(2);
                                    String string2 = query.getString(3);
                                    int i2 = query.getInt(4);
                                    query.getLong(5);
                                    query.getLong(6);
                                    query.getInt(7);
                                    query.getString(8);
                                    query.getInt(9);
                                    vector.add(new b(j, i, string, query.getString(10), query.getString(11), string2, i2, 1));
                                } while (query.moveToNext());
                            }
                            a.setTransactionSuccessful();
                            a.endTransaction();
                            if (query != null) {
                                query.close();
                            }
                        } catch (Exception e2) {
                            e = e2;
                            cursor = query;
                            try {
                                f.a("MusicMarkDB", "getDownLoadTask with exception : " + e.getMessage());
                                a.endTransaction();
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (a != null) {
                                    a.c();
                                }
                                return vector;
                            } catch (Throwable th2) {
                                th = th2;
                                cursor2 = cursor;
                                a.endTransaction();
                                if (cursor2 != null) {
                                    cursor2.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            cursor2 = query;
                            a.endTransaction();
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            throw th;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        cursor = null;
                        f.a("MusicMarkDB", "getDownLoadTask with exception : " + e.getMessage());
                        a.endTransaction();
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (a != null) {
                            a.c();
                        }
                        return vector;
                    } catch (Throwable th4) {
                        th = th4;
                        a.endTransaction();
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                }
                if (a != null) {
                    a.c();
                }
            } catch (Exception e4) {
                f.a("MusicMarkDB", "getDownLoadTask getWritableDatabase with exception : " + e4.getMessage());
                if (a != null) {
                    a.c();
                }
            } catch (Throwable th5) {
                if (a != null) {
                    a.c();
                }
            }
        }
        return vector;
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
    }

    public void a() {
        synchronized (c.class) {
            b = null;
            a.a().b();
        }
    }
}
