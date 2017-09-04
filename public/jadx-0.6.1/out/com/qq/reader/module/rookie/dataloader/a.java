package com.qq.reader.module.rookie.dataloader;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.db.c;

/* compiled from: RookieGiftSDDatabaseHelper */
public class a extends c {
    private final String a = "RookieGift";

    public a(String str, CursorFactory cursorFactory, int i) {
        super(str, cursorFactory, i);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            c(sQLiteDatabase);
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("RookieGift", "createTable : " + e.getMessage());
        }
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists rookie_gift_table (_id integer primary key autoincrement,giftid integer default -1,status integer default 0,json text not null,show_status text not null,show_date text not null);");
        sQLiteDatabase.execSQL("create unique index if not exists idx on rookie_gift_table (giftid);");
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
    }

    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        a(sQLiteDatabase, i);
    }
}
