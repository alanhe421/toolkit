package qalsdk;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/* compiled from: CacheDbHelper */
public class b extends SQLiteOpenHelper {
    private static final int a = 2;
    private static final String b = "qalcache.sqlite";
    private static final String c = "CREATE TABLE IF NOT EXISTS t_cache_meta(key CHARACTOR(32) PRIMARY KEY, accessTime BIGINT DEFAULT 0, ttl BIGINT DEFAULT 0, softTtl BIGINT DEFAULT 0, entrySize INT DEFAULT 0,checksum INT DEFAULT 0,etag CHARACTOR(64) DEFAULT NULL,lastModified CHARACTOR(64) DEFAULT NULL,isPartial INT DEFAULT 0)";
    private static final String d = "CREATE TABLE IF NOT EXISTS t_cache_summary(id BIGINT PRIMARY KEY,currentSize BIGINT DEFAULT 0)";
    private static final String e = "CREATE INDEX IF NOT EXISTS idx_acctime ON t_cache_meta(accessTime)";
    private static final String f = "PRAGMA temp_store = MEMORY;PRAGMA synchronous = OFF;PRAGMA journal_mode = MEMORY;";

    /* compiled from: CacheDbHelper */
    public static abstract class a implements BaseColumns {
        public static final String a = "t_cache_meta";
        public static final String b = "key";
        public static final String c = "accessTime";
        public static final String d = "ttl";
        public static final String e = "softTtl";
        public static final String f = "entrySize";
        public static final String g = "checksum";
        public static final String h = "etag";
        public static final String i = "lastModified";
        public static final String j = "isPartial";
    }

    /* compiled from: CacheDbHelper */
    public static abstract class b implements BaseColumns {
        public static final String a = "t_cache_summary";
        public static final String b = "id";
        public static final String c = "currentSize";
    }

    public b(Context context) {
        super(context, b, null, 2);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(c);
            sQLiteDatabase.execSQL(d);
            sQLiteDatabase.execSQL(f);
            sQLiteDatabase.execSQL(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        try {
            sQLiteDatabase.execSQL(c);
            sQLiteDatabase.execSQL(d);
            sQLiteDatabase.execSQL(f);
            sQLiteDatabase.execSQL(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        try {
            sQLiteDatabase.execSQL("drop table t_cache_meta");
            sQLiteDatabase.execSQL("drop table t_cache_summary");
            sQLiteDatabase.execSQL(c);
            sQLiteDatabase.execSQL(d);
            sQLiteDatabase.execSQL(f);
            sQLiteDatabase.execSQL(e);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
