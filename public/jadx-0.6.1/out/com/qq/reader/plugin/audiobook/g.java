package com.qq.reader.plugin.audiobook;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import java.util.ArrayList;
import java.util.List;

/* compiled from: MusicDownLoadTaskHandle */
public class g {
    private static c a;
    private static g b;

    /* compiled from: MusicDownLoadTaskHandle */
    private class a extends c {
        final /* synthetic */ g a;

        public a(g gVar, String str, CursorFactory cursorFactory, int i) {
            this.a = gVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    public static g a() {
        if (b == null) {
            b = new g();
        }
        return b;
    }

    private g() {
        a = new a(this, com.qq.reader.common.c.a.aP, null, 1);
    }

    public synchronized boolean a(MusicDownloadTask musicDownloadTask) {
        boolean z;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            try {
                SQLiteDatabase a = a.a();
                if (a != null) {
                    a.beginTransaction();
                    Cursor query;
                    try {
                        long update;
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("book_task_name", musicDownloadTask.getName());
                        contentValues.put("all_id", Long.valueOf(musicDownloadTask.getBookId()));
                        contentValues.put("chapter_id", Long.valueOf(musicDownloadTask.getChapterId()));
                        contentValues.put("objecturl", musicDownloadTask.getObjectURI());
                        contentValues.put("state", Integer.valueOf(musicDownloadTask.getState().ordinal()));
                        contentValues.put("cur_size", Long.valueOf(musicDownloadTask.getCurSize()));
                        contentValues.put("size", Long.valueOf(musicDownloadTask.getSize()));
                        contentValues.put("progress", Integer.valueOf(musicDownloadTask.getProgress()));
                        contentValues.put("format", musicDownloadTask.getBookFormat());
                        contentValues.put("drm_flag", Integer.valueOf(musicDownloadTask.getDrmflag()));
                        query = a.query("musicbook_downloadlist", new String[]{"all_id", "chapter_id"}, "all_id= '" + musicDownloadTask.getBookId() + "' and " + "chapter_id" + "= '" + musicDownloadTask.getChapterId() + "'", null, null, null, null);
                        if (query != null) {
                            try {
                                if (query.getCount() > 0) {
                                    query.moveToFirst();
                                    update = (long) a.update("musicbook_downloadlist", contentValues, "all_id= '" + musicDownloadTask.getBookId() + "' and " + "chapter_id" + "= '" + musicDownloadTask.getChapterId() + "'", null);
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
                        update = a.insert("musicbook_downloadlist", null, contentValues);
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

    public boolean b(MusicDownloadTask musicDownloadTask) {
        return a(musicDownloadTask);
    }

    public synchronized boolean a(long j, long j2) {
        int delete;
        Exception e;
        boolean z = false;
        synchronized (this) {
            if (j >= 0 && j2 >= 0) {
                try {
                    delete = a.a().delete("musicbook_downloadlist", "all_id= '" + j + "' and " + "chapter_id" + "= '" + j2 + "'", null);
                    try {
                        a.c();
                    } catch (Exception e2) {
                        e = e2;
                        f.a("MusicMarkDB", "del with exception : " + e.getMessage());
                        if (delete > 0) {
                            z = true;
                        }
                        return z;
                    }
                } catch (Exception e3) {
                    e = e3;
                    delete = 0;
                    f.a("MusicMarkDB", "del with exception : " + e.getMessage());
                    if (delete > 0) {
                        z = true;
                    }
                    return z;
                }
                if (delete > 0) {
                    z = true;
                }
            }
        }
        return z;
    }

    public synchronized boolean a(String str) {
        Cursor query;
        boolean z;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            if (str != null) {
                if (str.length() != 0) {
                    try {
                        SQLiteDatabase a = a.a();
                        if (a != null) {
                            a.beginTransaction();
                            try {
                                query = a.query("musicbook_downloadlist", new String[]{"book_task_name"}, "book_task_name= '" + str + "'", null, null, null, null);
                                try {
                                    a.setTransactionSuccessful();
                                    if (query == null || query.getCount() <= 0) {
                                        a.endTransaction();
                                        if (query != null) {
                                            query.close();
                                        }
                                    } else {
                                        a.endTransaction();
                                        if (query != null) {
                                            query.close();
                                        }
                                        if (a != null) {
                                            a.c();
                                        }
                                        z = true;
                                    }
                                } catch (Exception e2) {
                                    e = e2;
                                    try {
                                        f.a("MusicMarkDB", "exit with exception : " + e.getMessage());
                                        a.endTransaction();
                                        if (query != null) {
                                            query.close();
                                        }
                                        if (a != null) {
                                            a.c();
                                        }
                                        z = false;
                                        return z;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        cursor = query;
                                        a.endTransaction();
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                        throw th;
                                    }
                                }
                            } catch (Exception e3) {
                                e = e3;
                                query = null;
                                f.a("MusicMarkDB", "exit with exception : " + e.getMessage());
                                a.endTransaction();
                                if (query != null) {
                                    query.close();
                                }
                                if (a != null) {
                                    a.c();
                                }
                                z = false;
                                return z;
                            } catch (Throwable th3) {
                                th = th3;
                                a.endTransaction();
                                if (cursor != null) {
                                    cursor.close();
                                }
                                throw th;
                            }
                        }
                        if (a != null) {
                            a.c();
                        }
                    } catch (Exception e4) {
                        f.a("MusicMarkDB", "exit getWritableDatabase with exception : " + e4.getMessage());
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
            }
            z = false;
        }
        return z;
    }

    public synchronized List<com.qq.reader.common.download.task.g> b() {
        List<com.qq.reader.common.download.task.g> arrayList;
        Exception e;
        Cursor cursor;
        Throwable th;
        arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            SQLiteDatabase a = a.a();
            if (a != null) {
                a.beginTransaction();
                try {
                    Cursor query = a.query("musicbook_downloadlist", new String[]{"book_task_name", "all_id", "chapter_id", "objecturl", "state", "cur_size", "size", "progress", "format", "drm_flag"}, null, null, null, null, null);
                    try {
                        if (query.moveToFirst()) {
                            do {
                                String string = query.getString(0);
                                long j = query.getLong(1);
                                long j2 = query.getLong(2);
                                String string2 = query.getString(3);
                                int i = query.getInt(4);
                                long j3 = query.getLong(5);
                                long j4 = query.getLong(6);
                                MusicDownloadTask musicDownloadTask = new MusicDownloadTask(string, string2, j, j2, query.getInt(7), query.getString(8), query.getInt(9));
                                musicDownloadTask.setState(i);
                                musicDownloadTask.setSize(j3);
                                musicDownloadTask.setCurrentSize(j4);
                                arrayList.add(musicDownloadTask);
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
                            f.a("MusicMarkDB", "getAllDownLoadTask with exception : " + e.getMessage());
                            a.endTransaction();
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (a != null) {
                                a.c();
                            }
                            return arrayList;
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
                    f.a("MusicMarkDB", "getAllDownLoadTask with exception : " + e.getMessage());
                    a.endTransaction();
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (a != null) {
                        a.c();
                    }
                    return arrayList;
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
            f.a("MusicMarkDB", "getAllDownLoadTask getWritableDatabase with exception : " + e4.getMessage());
            if (a != null) {
                a.c();
            }
        } catch (Throwable th5) {
            if (a != null) {
                a.c();
            }
        }
        return arrayList;
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists musicbook_downloadlist (_id integer primary key autoincrement,book_task_name text not null,all_id long default 0,chapter_id  long default 0,objecturl text,state long default 0,cur_size long,size integer,progress integer default 0,format text not null,drm_flag integer default 2);");
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
    }
}
