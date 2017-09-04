package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.db.c;
import com.qq.reader.common.download.task.g;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.download.game.d;
import com.tencent.tinker.loader.shareutil.ShareConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: DownloadGameDBHandler */
public class n {
    private static n a;
    private a b = new a(this, com.qq.reader.common.c.a.bj + "downloadGame.db", null, 2);

    /* compiled from: DownloadGameDBHandler */
    private class a extends c {
        final /* synthetic */ n a;

        public a(n nVar, String str, CursorFactory cursorFactory, int i) {
            this.a = nVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    private n() {
    }

    public static n a() {
        if (a == null) {
            synchronized (n.class) {
                if (a == null) {
                    a = new n();
                }
            }
        }
        return a;
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists downloadGameList (_id integer primary key autoincrement,iconUrl text,name text,objecturl text,state integer default 0,display_name text,size integer default 0,jumpBackUrl text,progress integer default 0,gId long default 0);");
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
        List c = c(sQLiteDatabase);
        switch (i) {
            case 1:
                try {
                    a(sQLiteDatabase, c);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    a(c);
                    b(sQLiteDatabase);
                    return;
                }
            default:
                return;
        }
    }

    private synchronized List<String> c(SQLiteDatabase sQLiteDatabase) {
        List<String> arrayList;
        arrayList = new ArrayList();
        String[] strArr = new String[1];
        if (ao.c.a(sQLiteDatabase, "display_name", "downloadGameList")) {
            strArr[0] = "display_name";
        } else {
            strArr[0] = "name";
        }
        Cursor query = sQLiteDatabase.query("downloadGameList", strArr, null, null, null, null, null);
        if (query != null && query.moveToFirst()) {
            do {
                arrayList.add(query.getString(0));
            } while (query.moveToNext());
        }
        return arrayList;
    }

    private void a(SQLiteDatabase sQLiteDatabase, List<String> list) {
        a((List) list);
        b(sQLiteDatabase);
    }

    public synchronized d a(long j) {
        d dVar;
        Exception exception;
        try {
            Cursor query = this.b.a().query("downloadGameList", new String[]{"objecturl", "iconUrl", "name", "state", "size", "progress", "jumpBackUrl", "display_name"}, "gId= '" + j + "'", null, null, null, null);
            if (query == null || query.getCount() <= 0) {
                dVar = null;
            } else {
                query.moveToFirst();
                String string = query.getString(0);
                String string2 = query.getString(1);
                String string3 = query.getString(2);
                int i = query.getInt(3);
                long j2 = query.getLong(4);
                int i2 = query.getInt(5);
                d dVar2 = new d(query.getString(7), string2, string, query.getString(6));
                try {
                    dVar2.setState(i);
                    dVar2.setSize(j2);
                    dVar2.setProgress(i2);
                    dVar2.a(string3);
                    dVar2.setId(j);
                    dVar = dVar2;
                } catch (Exception e) {
                    Exception exception2 = e;
                    dVar = dVar2;
                    exception = exception2;
                    f.a("DB", "getDownloadTaskById with exception : " + exception.getMessage());
                    return dVar;
                }
            }
            if (query != null) {
                try {
                    query.close();
                } catch (Exception e2) {
                    exception = e2;
                    f.a("DB", "getDownloadTaskById with exception : " + exception.getMessage());
                    return dVar;
                }
            }
            this.b.c();
        } catch (Exception e3) {
            exception = e3;
            dVar = null;
            f.a("DB", "getDownloadTaskById with exception : " + exception.getMessage());
            return dVar;
        }
        return dVar;
    }

    public synchronized boolean b(long j) {
        int delete;
        Exception e;
        boolean z = false;
        synchronized (this) {
            try {
                delete = this.b.a().delete("downloadGameList", "gId= '" + j + "'", null);
                try {
                    this.b.c();
                } catch (Exception e2) {
                    e = e2;
                    f.a("DB", "delDownloadTask with exception : " + e.getMessage());
                    if (delete > 0) {
                        z = true;
                    }
                    return z;
                }
            } catch (Exception e3) {
                e = e3;
                delete = 0;
                f.a("DB", "delDownloadTask with exception : " + e.getMessage());
                if (delete > 0) {
                    z = true;
                }
                return z;
            }
            if (delete > 0) {
                z = true;
            }
        }
        return z;
    }

    public synchronized List<g> b() {
        List<g> arrayList;
        arrayList = new ArrayList();
        try {
            Cursor query = this.b.a().query("downloadGameList", new String[]{"iconUrl", "objecturl", "name", "state", "size", "progress", "_id", "jumpBackUrl", "gId", "display_name"}, null, null, null, null, null);
            if (query.moveToFirst()) {
                do {
                    String string = query.getString(0);
                    String string2 = query.getString(1);
                    String string3 = query.getString(2);
                    int i = query.getInt(3);
                    long j = query.getLong(4);
                    int i2 = query.getInt(5);
                    long j2 = (long) query.getInt(6);
                    String string4 = query.getString(7);
                    long j3 = query.getLong(8);
                    d dVar = new d(query.getString(9), string2, string, string4);
                    dVar.setState(i);
                    dVar.setSize(j);
                    dVar.setProgress(i2);
                    dVar.setId(j3);
                    dVar.a(j2);
                    dVar.a(string3);
                    arrayList.add(dVar);
                } while (query.moveToNext());
            }
            query.close();
            this.b.c();
        } catch (Exception e) {
            f.a("DB", "getAllDownloadTasks with exception: " + e.toString());
        }
        return arrayList;
    }

    public synchronized boolean a(d dVar) {
        boolean z = false;
        synchronized (this) {
            if (dVar != null) {
                if (!(dVar.getName() == null || dVar.getObjectURI() == null)) {
                    z = a(new d[]{dVar});
                }
            }
        }
        return z;
    }

    public synchronized boolean b(d dVar) {
        boolean z = false;
        synchronized (this) {
            if (dVar != null) {
                if (!(dVar.getName() == null || dVar.getObjectURI() == null)) {
                    z = a(new d[]{dVar});
                }
            }
        }
        return z;
    }

    public synchronized boolean a(d[] dVarArr) {
        boolean z;
        try {
            SQLiteDatabase a = this.b.a();
            if (a != null) {
                a.beginTransaction();
                try {
                    for (d dVar : dVarArr) {
                        long insert;
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("gId", Long.valueOf(dVar.getId()));
                        contentValues.put("iconUrl", dVar.c());
                        contentValues.put("objecturl", dVar.getObjectURI());
                        contentValues.put("state", Integer.valueOf(dVar.getState().ordinal()));
                        contentValues.put("size", Long.valueOf(dVar.getSize()));
                        contentValues.put("progress", Integer.valueOf(dVar.getProgress()));
                        contentValues.put("jumpBackUrl", dVar.a());
                        contentValues.put("display_name", dVar.getName());
                        contentValues.put("name", dVar.d());
                        Cursor query = a.query("downloadGameList", new String[]{"gId"}, "gId= '" + dVar.getId() + "'", null, null, null, null, null);
                        if (query == null || query.getCount() <= 0) {
                            insert = a.insert("downloadGameList", null, contentValues);
                        } else {
                            query.moveToFirst();
                            insert = (long) a.update("downloadGameList", contentValues, "gId=" + query.getInt(0), null);
                        }
                        if (query != null) {
                            query.close();
                        }
                        if (insert < 0) {
                            a.endTransaction();
                            this.b.c();
                            z = false;
                            break;
                        }
                    }
                    a.setTransactionSuccessful();
                    a.endTransaction();
                } catch (Exception e) {
                    e.printStackTrace();
                    a.endTransaction();
                    this.b.c();
                    z = false;
                } catch (Throwable th) {
                    a.endTransaction();
                }
            }
            this.b.c();
            z = true;
        } catch (Exception e2) {
            try {
                e2.printStackTrace();
                z = false;
            } finally {
                this.b.c();
            }
        }
        return z;
    }

    public synchronized boolean b(SQLiteDatabase sQLiteDatabase) {
        boolean z;
        try {
            sQLiteDatabase.execSQL("drop table if exists downloadGameList");
            a(sQLiteDatabase);
            z = true;
        } catch (Exception e) {
            e.printStackTrace();
            z = false;
        }
        return z;
    }

    private synchronized void a(List<String> list) {
        if (list != null) {
            String str = com.qq.reader.common.c.a.bj;
            for (String str2 : list) {
                File file = new File(str, str2 + com.qq.reader.common.download.task.f.DOWNLOAD_FILE_TMP);
                if (file.exists()) {
                    file.delete();
                }
                file = new File(str, str2 + ShareConstants.PATCH_SUFFIX);
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }

    public synchronized List<Long> a(Context context) {
        List<Long> arrayList;
        Exception exception;
        Cursor cursor;
        Throwable th;
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            arrayList = new ArrayList();
            Cursor query;
            try {
                SQLiteDatabase a = this.b.a();
                try {
                    query = a.query("downloadGameList", new String[]{"name", "display_name", "gId"}, "progress=?", new String[]{"100"}, null, null, null);
                    try {
                        String str = com.qq.reader.common.c.a.bj;
                        if (query == null || !query.moveToFirst()) {
                            if (arrayList.size() > 0) {
                                for (Long l : arrayList) {
                                    a.execSQL("DELETE FROM downloadGameList WHERE gId = " + l);
                                }
                            }
                            if (query != null) {
                                query.close();
                            }
                            if (a != null) {
                                a.close();
                            }
                        } else {
                            do {
                                String string = query.getString(0);
                                File file = new File(str, query.getString(1) + ShareConstants.PATCH_SUFFIX);
                                if (!file.exists()) {
                                    arrayList.add(Long.valueOf(query.getLong(2)));
                                } else if (com.qq.reader.common.utils.ao.a.a(context, string)) {
                                    file.delete();
                                    arrayList.add(Long.valueOf(query.getLong(2)));
                                }
                            } while (query.moveToNext());
                            if (arrayList.size() > 0) {
                                while (r3.hasNext()) {
                                    a.execSQL("DELETE FROM downloadGameList WHERE gId = " + l);
                                }
                            }
                            if (query != null) {
                                query.close();
                            }
                            if (a != null) {
                                a.close();
                            }
                        }
                    } catch (Exception e) {
                        sQLiteDatabase = a;
                        exception = e;
                        cursor = query;
                    } catch (Throwable th2) {
                        sQLiteDatabase = a;
                        th = th2;
                    }
                } catch (Exception e2) {
                    Exception exception2 = e2;
                    cursor = null;
                    sQLiteDatabase = a;
                    exception = exception2;
                    try {
                        exception.printStackTrace();
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                        return arrayList;
                    } catch (Throwable th3) {
                        th = th3;
                        query = cursor;
                        if (query != null) {
                            query.close();
                        }
                        if (sQLiteDatabase != null) {
                            sQLiteDatabase.close();
                        }
                        throw th;
                    }
                } catch (Throwable th22) {
                    query = null;
                    sQLiteDatabase = a;
                    th = th22;
                    if (query != null) {
                        query.close();
                    }
                    if (sQLiteDatabase != null) {
                        sQLiteDatabase.close();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                exception = e3;
                cursor = null;
                exception.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                return arrayList;
            } catch (Throwable th4) {
                th = th4;
                query = null;
                if (query != null) {
                    query.close();
                }
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                throw th;
            }
        }
        return arrayList;
    }
}
