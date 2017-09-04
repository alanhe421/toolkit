package com.tencent.beacon.b.a;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.tencent.beacon.b.f;
import com.tencent.beacon.e.a;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* compiled from: ProGuard */
public final class e extends SQLiteOpenHelper {
    public static Object a = new Object();
    private static SQLiteDatabase c = null;
    private Context b;

    private e(Context context) {
        super(context, "beacon_db", null, 21);
        this.b = context;
    }

    public static synchronized e a(Context context) {
        e eVar;
        synchronized (e.class) {
            eVar = new e(context);
        }
        return eVar;
    }

    public final synchronized void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (this) {
            if (sQLiteDatabase != null) {
                if (d.a != null) {
                    for (Object[] objArr : d.a) {
                        a.g("table:%s", objArr[0]);
                        sQLiteDatabase.execSQL(objArr[1]);
                    }
                }
            }
        }
    }

    public final synchronized void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        a.g("upgrade a db  [%s] from v %d to v %d , deleted all tables!", "beacon_db", Integer.valueOf(i), Integer.valueOf(i2));
        if (a(sQLiteDatabase)) {
            a.g("drop all success recreate!", new Object[0]);
            onCreate(sQLiteDatabase);
        } else {
            a.d("drop all fail try deleted file,may next time will success!", new Object[0]);
            File databasePath = this.b.getDatabasePath("beacon_db");
            if (databasePath != null && databasePath.canWrite()) {
                databasePath.delete();
            }
        }
    }

    @TargetApi(11)
    public final synchronized void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        f.a(this.b);
        if (Integer.parseInt(f.d()) >= 11) {
            a.g("downgrade a db  [%s] from v %d to  v%d , deleted all tables!", "beacon_db", Integer.valueOf(i), Integer.valueOf(i2));
            if (a(sQLiteDatabase)) {
                a.g("drop all success recreate!", new Object[0]);
                onCreate(sQLiteDatabase);
            } else {
                a.d("drop all fail try deleted file,may next time will success!", new Object[0]);
                File databasePath = this.b.getDatabasePath("beacon_db");
                if (databasePath != null && databasePath.canWrite()) {
                    databasePath.delete();
                }
            }
        }
    }

    private synchronized boolean a(SQLiteDatabase sQLiteDatabase) {
        Cursor query;
        Throwable th;
        boolean z;
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
                    if (!(str2.equals("sqlite_sequence") || str2.equals("android_metadata"))) {
                        sQLiteDatabase.execSQL(String.format(Locale.US, str, new Object[]{str2}));
                        a.g("drop %s", str2);
                    }
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

    public final synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase sQLiteDatabase;
        int i = 0;
        synchronized (this) {
            while (c == null && i < 5) {
                i++;
                try {
                    c = super.getWritableDatabase();
                } catch (Exception e) {
                    a.c("getWritableDatabase error count %d", Integer.valueOf(i));
                    if (i == 5) {
                        a.d("error get DB failed", new Object[0]);
                    }
                    try {
                        Thread.sleep(200);
                    } catch (Throwable e2) {
                        a.a(e2);
                    }
                }
            }
            sQLiteDatabase = c;
        }
        return sQLiteDatabase;
    }
}
