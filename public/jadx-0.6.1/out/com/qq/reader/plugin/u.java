package com.qq.reader.plugin;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;

/* compiled from: SkinDatabaseHelper */
public class u extends c {
    private final String a = "SKIN_DB_HELP";

    public u(String str, CursorFactory cursorFactory, int i) {
        super(str, cursorFactory, i);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table if not exists plugin_table_name (_id integer primary key autoincrement,plugin_id text not null,plugin_type_id text not null,plugin_name text not null,plugin_version text not null,plugin_info text not null,plugin_str_size text not null,icon_url text not null,image_url text not null,plugin_free text not null,plugin_price text not null,plugin_enable text not null,plugin_max_size long default -1,plugin_download_status long default 0,plugin_can_download long default 0,plugin_skin_color text not null,plugin_latest_version text not null,plugin_all_version text not null);");
        } catch (Exception e) {
            f.a("SKIN_DB_HELP", "createTable : " + e.getMessage());
        }
    }

    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        switch (i) {
            case 11:
                d(sQLiteDatabase);
                return;
            case 12:
                e(sQLiteDatabase);
                return;
            default:
                return;
        }
    }

    private void d(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select plugin_skin_color from plugin_table_name", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(13);
                        rawQuery.close();
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update1To2 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE plugin_table_name ADD plugin_skin_color text not null default 0");
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            f.a("DB", " update1To2 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE plugin_table_name ADD plugin_skin_color text not null default 0");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE plugin_table_name ADD plugin_skin_color text not null default 0");
        } catch (Exception e4) {
            f.a("DB", " update1To2 :" + e4.toString());
        }
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select plugin_skin_color from plugin_table_name", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(13);
                        rawQuery.close();
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update2To3 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE plugin_table_name ALTER COLUMN plugin_skin_color text not null ");
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            f.a("DB", " update2To3 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE plugin_table_name ALTER COLUMN plugin_skin_color text not null ");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        try {
            sQLiteDatabase.execSQL("ALTER TABLE plugin_table_name ALTER COLUMN plugin_skin_color text not null ");
        } catch (Exception e4) {
            f.a("DB", " update2To3 :" + e4.toString());
        }
    }

    public void c(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("delete from plugin_table_name");
        } catch (Exception e) {
            f.a("SKIN_DB_HELP", "clearTable : " + e.getMessage());
        }
    }
}
