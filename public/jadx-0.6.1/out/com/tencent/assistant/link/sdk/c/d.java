package com.tencent.assistant.link.sdk.c;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/* compiled from: ProGuard */
public class d {
    private SQLiteDatabase a;

    public d(SQLiteDatabase sQLiteDatabase) {
        this.a = sQLiteDatabase;
    }

    public Cursor a(String str, String[] strArr) {
        Cursor cursor = null;
        try {
            cursor = this.a.rawQuery(str, strArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return cursor;
    }

    public int a(String str, String str2, String[] strArr) {
        int i = 0;
        try {
            i = this.a.delete(str, str2, strArr);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return i;
    }
}
