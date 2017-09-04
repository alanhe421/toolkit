package com.hmt.analytics.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseOpenHelper extends SQLiteOpenHelper {
    private static String a = "hmt_analytics";
    private static int b = 1;
    private static DataBaseOpenHelper c;

    public DataBaseOpenHelper(Context context) {
        super(context, a, null, b);
    }

    public static synchronized DataBaseOpenHelper getInstance(Context context) {
        DataBaseOpenHelper dataBaseOpenHelper;
        synchronized (DataBaseOpenHelper.class) {
            if (c == null) {
                c = new DataBaseOpenHelper(context);
            }
            dataBaseOpenHelper = c;
        }
        return dataBaseOpenHelper;
    }

    public DataBaseOpenHelper(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS hmtInfo (id integer primary key autoincrement, type varchar(64), info text)");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS reqInfo (id integer primary key autoincrement, type varchar(64), info text)");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
