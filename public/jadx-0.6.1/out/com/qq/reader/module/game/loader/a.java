package com.qq.reader.module.game.loader;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.db.c;

/* compiled from: GameCouponSDDatabaseHelper */
public class a extends c {
    private final String a = "GameCoupon";

    public a(String str, CursorFactory cursorFactory, int i) {
        super(str, cursorFactory, i);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            c(sQLiteDatabase);
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("GameCoupon", "createTable : " + e.getMessage());
        }
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists game_coupon_table (_id integer primary key autoincrement,couponid text not null,time long default 0,json text not null);");
        sQLiteDatabase.execSQL("create unique index if not exists idx on game_coupon_table (couponid);");
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
    }

    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        a(sQLiteDatabase, i);
    }
}
