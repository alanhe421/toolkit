package com.tencent.assistant.link.sdk.c;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.SystemClock;
import android.util.Log;

/* compiled from: ProGuard */
public abstract class e extends SQLiteOpenHelper {
    private static final String a = e.class.getSimpleName();

    public abstract Class<?>[] a();

    public abstract int b();

    public e(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    @Deprecated
    public synchronized SQLiteDatabase getWritableDatabase() {
        SQLiteDatabase writableDatabase;
        writableDatabase = super.getWritableDatabase();
        while (true) {
            if (writableDatabase.isDbLockedByCurrentThread() || writableDatabase.isDbLockedByOtherThreads()) {
                SystemClock.sleep(10);
            }
        }
        return writableDatabase;
    }

    @Deprecated
    public synchronized SQLiteDatabase getReadableDatabase() {
        SQLiteDatabase readableDatabase;
        readableDatabase = super.getReadableDatabase();
        while (true) {
            if (readableDatabase.isDbLockedByCurrentThread() || readableDatabase.isDbLockedByOtherThreads()) {
                SystemClock.sleep(10);
            }
        }
        return readableDatabase;
    }

    public synchronized d c() {
        SQLiteDatabase sQLiteDatabase;
        sQLiteDatabase = null;
        try {
            sQLiteDatabase = e();
        } catch (Throwable th) {
            Log.w("download_db", "get write db final fail." + th);
            th.printStackTrace();
        }
        return new d(sQLiteDatabase);
    }

    private SQLiteDatabase e() {
        SQLiteDatabase writableDatabase = super.getWritableDatabase();
        while (true) {
            if (!writableDatabase.isDbLockedByCurrentThread() && !writableDatabase.isDbLockedByOtherThreads()) {
                return writableDatabase;
            }
            SystemClock.sleep(10);
        }
    }

    public synchronized d d() {
        SQLiteDatabase sQLiteDatabase;
        sQLiteDatabase = null;
        try {
            sQLiteDatabase = f();
        } catch (Throwable th) {
            Log.w("download_db", "get read db final fail." + th);
            th.printStackTrace();
        }
        return new d(sQLiteDatabase);
    }

    private SQLiteDatabase f() {
        SQLiteDatabase readableDatabase = super.getReadableDatabase();
        while (true) {
            if (!readableDatabase.isDbLockedByCurrentThread() && !readableDatabase.isDbLockedByOtherThreads()) {
                return readableDatabase;
            }
            SystemClock.sleep(10);
        }
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        int version = sQLiteDatabase.getVersion();
        if (version != 0) {
            if (version < b()) {
                onUpgrade(sQLiteDatabase, version, b());
            } else if (version > b()) {
                onDowngrade(sQLiteDatabase, version, b());
            }
        }
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        while (i < i2) {
            for (Class newInstance : a()) {
                try {
                    c cVar = (c) newInstance.newInstance();
                    cVar.a(i, i + 1, sQLiteDatabase);
                    String[] a = cVar.a(i, i + 1);
                    if (a != null) {
                        for (String execSQL : a) {
                            sQLiteDatabase.execSQL(execSQL);
                        }
                    }
                    cVar.b(i, i + 1, sQLiteDatabase);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            i++;
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        b(sQLiteDatabase);
        a(sQLiteDatabase);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        for (Class newInstance : a()) {
            String b;
            try {
                b = ((c) newInstance.newInstance()).b();
            } catch (Exception e) {
                e.printStackTrace();
                b = null;
            }
            if (b != null && b.length() > 0) {
                sQLiteDatabase.execSQL(b);
            }
        }
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        for (Class newInstance : a()) {
            try {
                sQLiteDatabase.delete(((c) newInstance.newInstance()).a(), null, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
