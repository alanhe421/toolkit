package com.tencent.feedback.proguard;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.feedback.common.d;
import com.tencent.feedback.common.e;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: RQDSRC */
public final class n extends SQLiteOpenHelper {
    private Context a;

    public n(Context context) {
        super(context, "eup_db", null, 14);
        this.a = context;
    }

    public final synchronized void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (this) {
            if (sQLiteDatabase != null) {
                if (m.a != null) {
                    for (Object[] objArr : m.a) {
                        e.b("rqdp{  table:}%s %s", objArr[0], r1[r0][1]);
                        sQLiteDatabase.execSQL(objArr[1]);
                    }
                }
            }
        }
    }

    public final synchronized void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        e.b("rqdp{  upgrade a db }[%s]rqdp{   from v}%d rqdp{  to v}%d rqdp{  , deleted all tables!}", "eup_db", Integer.valueOf(i), Integer.valueOf(i2));
        if (a(sQLiteDatabase)) {
            e.b("rqdp{  drop all success recreate!}", new Object[0]);
            onCreate(sQLiteDatabase);
        } else {
            e.d("rqdp{  drop all fail try deleted file,may next time will success!}", new Object[0]);
            File databasePath = this.a.getDatabasePath("eup_db");
            if (databasePath != null && databasePath.canWrite()) {
                databasePath.delete();
            }
        }
    }

    @TargetApi(11)
    public final synchronized void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        d.a(this.a);
        if (Integer.parseInt(d.c()) >= 11) {
            e.b("rqdp{  downgrade a db} [%s] rqdp{  from v}%d rqdp{  to} v%d rqdp{  , deleted all tables!}", "eup_db", Integer.valueOf(i), Integer.valueOf(i2));
            if (a(sQLiteDatabase)) {
                e.b("rqdp{  drop all success recreate!}", new Object[0]);
                onCreate(sQLiteDatabase);
            } else {
                e.d("rqdp{  drop all fail try deleted file,may next time will success!}", new Object[0]);
                File databasePath = this.a.getDatabasePath("eup_db");
                if (databasePath != null && databasePath.canWrite()) {
                    databasePath.delete();
                }
            }
        }
    }

    private synchronized boolean a(SQLiteDatabase sQLiteDatabase) {
        Throwable th;
        boolean z;
        Cursor query;
        try {
            List<String> arrayList = new ArrayList();
            SQLiteDatabase sQLiteDatabase2 = sQLiteDatabase;
            query = sQLiteDatabase2.query("sqlite_master", new String[]{"name"}, "type = 'table'", null, null, null, null);
            if (query != null) {
                while (query.moveToNext()) {
                    try {
                        arrayList.add(query.getString(0));
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            }
            String str = "drop table if exists %s";
            if (arrayList.size() > 0) {
                for (String str2 : arrayList) {
                    sQLiteDatabase.execSQL(String.format(Locale.US, str, new Object[]{str2}));
                    e.b("rqdp{  drop }%s", str2);
                }
            }
            if (query != null) {
                if (!query.isClosed()) {
                    query.close();
                }
            }
            z = true;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                if (query.isClosed()) {
                    query.close();
                }
            }
            throw th;
        }
        return z;
    }

    public final synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        int i = 0;
        synchronized (this) {
            sQLiteDatabase = null;
            while (sQLiteDatabase == null && i < 5) {
                i++;
                try {
                    sQLiteDatabase = super.getReadableDatabase();
                } catch (Throwable th) {
                    e.c("rqdp{  getReadableDatabase error count} %d", Integer.valueOf(i));
                    if (i == 5) {
                        e.d("rqdp{  error get DB failed}", new Object[0]);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        if (!e.a(th)) {
                            th.printStackTrace();
                        }
                    }
                }
            }
        }
        return sQLiteDatabase;
    }

    public final synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        int i = 0;
        synchronized (this) {
            sQLiteDatabase = null;
            while (sQLiteDatabase == null && i < 5) {
                i++;
                try {
                    sQLiteDatabase = super.getWritableDatabase();
                } catch (Throwable e) {
                    e.c("rqdp{  getWritableDatabase error count} %d", Integer.valueOf(i));
                    if (i == 5) {
                        e.d("rqdp{  error get DB failed}", new Object[0]);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e2) {
                        if (!e.a(e)) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return sQLiteDatabase;
    }
}
