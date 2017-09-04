package com.qq.reader.plugin.audiobook;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.db.c;

/* compiled from: MusicMarkHandle */
class i$a extends c {
    final /* synthetic */ i a;

    public i$a(i iVar, String str, CursorFactory cursorFactory, int i) {
        this.a = iVar;
        super(str, cursorFactory, i);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        this.a.a(sQLiteDatabase);
    }

    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        i.a(this.a, sQLiteDatabase, i);
    }
}
