package com.qq.reader.common.db.handle;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;

/* compiled from: QuestionSDDatabaseHelper */
public class w extends c {
    private final String a = "QuestionSDDatabaseHelper";

    public w(String str, CursorFactory cursorFactory, int i) {
        super(str, cursorFactory, i);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            c(sQLiteDatabase);
            d(sQLiteDatabase);
            e(sQLiteDatabase);
        } catch (Exception e) {
            f.a("QuestionSDDatabaseHelper", "createTable : " + e.getMessage());
        }
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists question_table_name (_id integer primary key autoincrement,showed integer default 0,aid long default -1,qid text not null,json text not null);");
        sQLiteDatabase.execSQL("create unique index if not exists idx on question_table_name (qid);");
    }

    private void d(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists question_author_table_name (_id integer primary key autoincrement,aid long default -1,date text not null,remain integer default 0,json text not null);");
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists question_author_book_table_name (_id integer primary key autoincrement,aid long default -1,bid text not null);");
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
    }

    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        a(sQLiteDatabase, i);
    }
}
