package com.qq.reader.common.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import com.qq.reader.common.monitor.b;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ab;
import java.io.File;

/* compiled from: SDSQLiteOpenHelper */
public abstract class c {
    private static final String a = c.class.getSimpleName();
    private final String b;
    private final CursorFactory c;
    private final int d;
    private SQLiteDatabase e = null;
    private boolean f = false;

    public abstract void a(SQLiteDatabase sQLiteDatabase);

    public abstract void a(SQLiteDatabase sQLiteDatabase, int i, int i2);

    public c(String str, CursorFactory cursorFactory, int i) {
        if (i < 1) {
            throw new IllegalArgumentException("Version must be >= 1, was " + i);
        }
        this.b = str;
        this.c = cursorFactory;
        this.d = i;
    }

    public synchronized SQLiteDatabase a() throws SQLiteException {
        SQLiteDatabase sQLiteDatabase;
        Throwable e;
        String str;
        Exception sQLiteException;
        if (this.e != null && this.e.isOpen() && !this.e.isReadOnly()) {
            sQLiteDatabase = this.e;
        } else if (this.f) {
            throw new IllegalStateException("getWritableDatabase called recursively");
        } else {
            Exception exception = null;
            try {
                this.f = true;
                if (this.b == null) {
                    sQLiteDatabase = SQLiteDatabase.create(null);
                } else {
                    sQLiteDatabase = SQLiteDatabase.openDatabase(a(this.b).getPath(), this.c, 268435472);
                }
                try {
                    int version = sQLiteDatabase.getVersion();
                    if (version != this.d) {
                        sQLiteDatabase.beginTransaction();
                        if (version == 0) {
                            a(sQLiteDatabase);
                        } else if (version < this.d) {
                            a(sQLiteDatabase, version, this.d);
                        }
                        if (version < this.d) {
                            sQLiteDatabase.setVersion(this.d);
                        }
                        sQLiteDatabase.setTransactionSuccessful();
                        sQLiteDatabase.endTransaction();
                    }
                    b(sQLiteDatabase);
                    this.f = false;
                    if (this.e != null) {
                        try {
                            this.e.close();
                        } catch (Exception e2) {
                        }
                    }
                    this.e = sQLiteDatabase;
                } catch (Exception e3) {
                    e = e3;
                    try {
                        f.a("DB", "getWritableDatabase with exception : " + e.getMessage());
                        b.a(e);
                        this.f = false;
                        if (sQLiteDatabase != null) {
                            try {
                                sQLiteDatabase.close();
                            } catch (Exception e4) {
                            }
                        }
                        str = "getWritableDatabase ERROR";
                        if (e != null) {
                            str = e.toString();
                        }
                        sQLiteException = new SQLiteException(str);
                        com.qq.reader.common.monitor.c.a().a(sQLiteException);
                        throw sQLiteException;
                    } catch (Throwable th) {
                        this.f = false;
                        if (sQLiteDatabase != null) {
                            try {
                                sQLiteDatabase.close();
                            } catch (Exception e5) {
                            }
                        }
                        str = "getWritableDatabase ERROR";
                        if (null != null) {
                            str = exception.toString();
                        }
                        sQLiteException = new SQLiteException(str);
                        com.qq.reader.common.monitor.c.a().a(sQLiteException);
                        throw sQLiteException;
                    }
                } catch (Throwable th2) {
                    sQLiteDatabase.endTransaction();
                }
            } catch (Throwable e6) {
                e = e6;
                sQLiteDatabase = null;
                f.a("DB", "getWritableDatabase with exception : " + e.getMessage());
                b.a(e);
                this.f = false;
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                str = "getWritableDatabase ERROR";
                if (e != null) {
                    str = e.toString();
                }
                sQLiteException = new SQLiteException(str);
                com.qq.reader.common.monitor.c.a().a(sQLiteException);
                throw sQLiteException;
            } catch (Throwable th3) {
                sQLiteDatabase = null;
                this.f = false;
                if (sQLiteDatabase != null) {
                    sQLiteDatabase.close();
                }
                str = "getWritableDatabase ERROR";
                if (null != null) {
                    str = exception.toString();
                }
                sQLiteException = new SQLiteException(str);
                com.qq.reader.common.monitor.c.a().a(sQLiteException);
                throw sQLiteException;
            }
        }
        return sQLiteDatabase;
    }

    public synchronized SQLiteDatabase b() {
        SQLiteDatabase sQLiteDatabase;
        SQLiteDatabase sQLiteDatabase2;
        boolean version;
        if (this.e != null && this.e.isOpen()) {
            sQLiteDatabase = this.e;
        } else if (this.f) {
            throw new IllegalStateException("getReadableDatabase called recursively");
        } else {
            try {
                sQLiteDatabase = a();
            } catch (SQLiteException e) {
                boolean e2 = e;
                if (this.b == null) {
                    throw e2;
                }
                sQLiteDatabase2 = null;
                this.f = e2;
                String path = a(this.b).getPath();
                sQLiteDatabase2 = SQLiteDatabase.openDatabase(path, this.c, 268435473);
                version = sQLiteDatabase2.getVersion();
                if (version != this.d) {
                    throw new SQLiteException("Can't upgrade read-only database from version " + sQLiteDatabase2.getVersion() + " to " + this.d + ": " + path);
                }
                b(sQLiteDatabase2);
                this.e = sQLiteDatabase2;
                sQLiteDatabase = this.e;
                this.f = version;
                if (!(sQLiteDatabase2 == null || sQLiteDatabase2 == this.e)) {
                    sQLiteDatabase2.close();
                }
            } finally {
                version = false;
                this.f = false;
                if (!(sQLiteDatabase2 == null || sQLiteDatabase2 == this.e)) {
                    sQLiteDatabase2.close();
                }
            }
        }
        return sQLiteDatabase;
    }

    public synchronized void c() {
        if (this.f) {
            throw new IllegalStateException("Closed during initialization");
        } else if (this.e != null && this.e.isOpen()) {
            this.e.close();
            this.e = null;
        }
    }

    public File a(String str) {
        File file = new File(str);
        ab.a(file.getParentFile());
        return file;
    }

    public void b(SQLiteDatabase sQLiteDatabase) {
    }
}
