package com.qq.reader.common.db.handle;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;

/* compiled from: ExtendedReminderEventDatabaseHelper */
public class q extends c {
    public q(String str, CursorFactory cursorFactory, int i) {
        super(str, cursorFactory, i);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table if not exists reminder_event_table_name (_id integer primary key autoincrement,book_id text,system_event_id text,start_time long);");
        } catch (Exception e) {
            f.a("ExtendedReminderEventDatabaseHelper", "createTable : " + e.getMessage());
        }
    }

    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }
}
