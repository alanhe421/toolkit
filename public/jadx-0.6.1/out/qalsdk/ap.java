package qalsdk;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: MonitorDBHelper */
public class ap extends SQLiteOpenHelper {
    private static final String a = "qal_monitor.db";
    private static int b = 6;
    private static String c = "create table conntime(_id INTEGER PRIMARY KEY,timestamp INTEGER NOT NULL,cost_time INTEGER NOT NULL,result CHAR NOT NULL,os CHAR(20) ,os_version CHAR(20) ,sdk_version INTEGER NOT NULL,process_status INTEGER NOT NULL,server_ip_port CHAR(100) ,gateway_ip CHAR(20) ,errcode INTEGER DEFAULT 0 ,net_info VAECHAR(500) )";

    public ap(Context context) {
        super(context, a, null, b);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(c);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("drop table conntime");
        sQLiteDatabase.execSQL(c);
    }
}
