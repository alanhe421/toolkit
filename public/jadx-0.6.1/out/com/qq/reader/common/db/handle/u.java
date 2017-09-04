package com.qq.reader.common.db.handle;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;

/* compiled from: NoteSDDatabaseHelper */
public class u extends c {
    private final String a = "SELECTION";

    public u(String str, CursorFactory cursorFactory, int i) {
        super(str, cursorFactory, i);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table if not exists note_table_name (_id integer primary key autoincrement,book_id long default -1,book_name text not null,start_point text not null,end_point text not null,select_text text not null,remarks text,book_date text not null,bookrealid long default 0, note_type integer default 0,start_chapter integer default 0,start_offset integer default 0,end_chapter integer default 0,end_offset integer default 0);");
            e(sQLiteDatabase);
            f(sQLiteDatabase);
        } catch (Exception e) {
            f.a("SELECTION", "createTable : " + e.getMessage());
        }
    }

    private static void c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE note_table_name ADD bookrealid text");
    }

    private static void d(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("ALTER TABLE note_table_name ADD note_type integer default 0 ");
        sQLiteDatabase.execSQL("ALTER TABLE note_table_name ADD start_chapter integer default 0 ");
        sQLiteDatabase.execSQL("ALTER TABLE note_table_name ADD start_offset integer default 0 ");
        sQLiteDatabase.execSQL("ALTER TABLE note_table_name ADD end_chapter integer default 0 ");
        sQLiteDatabase.execSQL("ALTER TABLE note_table_name ADD end_offset integer default 0 ");
        e(sQLiteDatabase);
        f(sQLiteDatabase);
    }

    private static void e(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists note_table_book (_id integer primary key autoincrement,bookrealid integer,version integer default 0,timestamp integer default 0 );");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void f(android.database.sqlite.SQLiteDatabase r20) {
        /*
        r2 = 2;
        r4 = new java.lang.String[r2];	 Catch:{ Exception -> 0x00ee }
        r2 = 0;
        r3 = "_id";
        r4[r2] = r3;	 Catch:{ Exception -> 0x00ee }
        r2 = 1;
        r3 = "book_date";
        r4[r2] = r3;	 Catch:{ Exception -> 0x00ee }
        r10 = 0;
        r3 = "note_table_name";
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r2 = r20;
        r2 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00de, all -> 0x00e7 }
        if (r2 == 0) goto L_0x006e;
    L_0x0020:
        r3 = r2.moveToFirst();	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        if (r3 == 0) goto L_0x006e;
    L_0x0026:
        r3 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        r3.<init>();	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        r4 = 0;
        r4 = r2.getInt(r4);	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        r5 = 1;
        r5 = r2.getString(r5);	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        r6 = com.qq.reader.common.utils.ao.g(r5);	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        r5 = "book_date";
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        r3.put(r5, r6);	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        r5 = "note_table_name";
        r6 = "_id=?";
        r7 = 1;
        r7 = new java.lang.String[r7];	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        r8 = 0;
        r9 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        r9.<init>();	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        r4 = r9.append(r4);	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        r9 = "";
        r4 = r4.append(r9);	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        r4 = r4.toString();	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        r7[r8] = r4;	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        r0 = r20;
        r0.update(r5, r3, r6, r7);	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        r3 = r2.moveToNext();	 Catch:{ Exception -> 0x0431, all -> 0x042c }
        if (r3 != 0) goto L_0x0026;
    L_0x006e:
        if (r2 == 0) goto L_0x0434;
    L_0x0070:
        r2.close();	 Catch:{ Exception -> 0x00ee }
        r10 = r2;
    L_0x0074:
        r2 = com.qq.reader.common.db.handle.i.c();	 Catch:{ Exception -> 0x00ee }
        r2 = r2.g();	 Catch:{ Exception -> 0x00ee }
        r4 = r2.iterator();	 Catch:{ Exception -> 0x00ee }
    L_0x0080:
        r2 = r4.hasNext();	 Catch:{ Exception -> 0x00ee }
        if (r2 == 0) goto L_0x013f;
    L_0x0086:
        r2 = r4.next();	 Catch:{ Exception -> 0x00ee }
        r2 = (com.qq.reader.framework.mark.Mark) r2;	 Catch:{ Exception -> 0x00ee }
        r5 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00ee }
        r5.<init>();	 Catch:{ Exception -> 0x00ee }
        r3 = "bookrealid";
        r6 = r2.getBookId();	 Catch:{ Exception -> 0x00ee }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x00ee }
        r5.put(r3, r6);	 Catch:{ Exception -> 0x00ee }
        r3 = 0;
        r6 = r2.getType();	 Catch:{ Exception -> 0x00ee }
        r7 = 4;
        if (r6 != r7) goto L_0x00f3;
    L_0x00a7:
        r3 = 1;
    L_0x00a8:
        r6 = "note_type";
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ Exception -> 0x00ee }
        r5.put(r6, r3);	 Catch:{ Exception -> 0x00ee }
        r3 = "note_table_name";
        r6 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0133 }
        r6.<init>();	 Catch:{ Exception -> 0x0133 }
        r7 = "book_name='";
        r6 = r6.append(r7);	 Catch:{ Exception -> 0x0133 }
        r2 = r2.getBookShortName();	 Catch:{ Exception -> 0x0133 }
        r2 = r6.append(r2);	 Catch:{ Exception -> 0x0133 }
        r6 = "'";
        r2 = r2.append(r6);	 Catch:{ Exception -> 0x0133 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0133 }
        r6 = 0;
        r0 = r20;
        r2 = r0.update(r3, r5, r2, r6);	 Catch:{ Exception -> 0x0133 }
        if (r2 <= 0) goto L_0x0080;
    L_0x00dd:
        goto L_0x0080;
    L_0x00de:
        r2 = move-exception;
        r2 = r10;
    L_0x00e0:
        if (r2 == 0) goto L_0x0434;
    L_0x00e2:
        r2.close();	 Catch:{ Exception -> 0x00ee }
        r10 = r2;
        goto L_0x0074;
    L_0x00e7:
        r2 = move-exception;
    L_0x00e8:
        if (r10 == 0) goto L_0x00ed;
    L_0x00ea:
        r10.close();	 Catch:{ Exception -> 0x00ee }
    L_0x00ed:
        throw r2;	 Catch:{ Exception -> 0x00ee }
    L_0x00ee:
        r2 = move-exception;
        r2.printStackTrace();
    L_0x00f2:
        return;
    L_0x00f3:
        r6 = r2.getType();	 Catch:{ Exception -> 0x00ee }
        r7 = 1;
        if (r6 != r7) goto L_0x00a8;
    L_0x00fa:
        r6 = r2.getBookName();	 Catch:{ Exception -> 0x00ee }
        r7 = ".txt";
        r6 = r6.endsWith(r7);	 Catch:{ Exception -> 0x00ee }
        if (r6 != 0) goto L_0x0114;
    L_0x0107:
        r6 = r2.getBookName();	 Catch:{ Exception -> 0x00ee }
        r7 = ".umd";
        r6 = r6.endsWith(r7);	 Catch:{ Exception -> 0x00ee }
        if (r6 == 0) goto L_0x0116;
    L_0x0114:
        r3 = 1;
        goto L_0x00a8;
    L_0x0116:
        r6 = r2.getBookName();	 Catch:{ Exception -> 0x00ee }
        r7 = ".epub";
        r6 = r6.endsWith(r7);	 Catch:{ Exception -> 0x00ee }
        if (r6 != 0) goto L_0x0130;
    L_0x0123:
        r6 = r2.getBookName();	 Catch:{ Exception -> 0x00ee }
        r7 = ".teb";
        r6 = r6.endsWith(r7);	 Catch:{ Exception -> 0x00ee }
        if (r6 == 0) goto L_0x00a8;
    L_0x0130:
        r3 = 2;
        goto L_0x00a8;
    L_0x0133:
        r2 = move-exception;
        r2 = "NoteSDDatabaseHelper";
        r3 = "update note_table_name set noteType Error";
        com.qq.reader.common.monitor.f.a(r2, r3);	 Catch:{ Exception -> 0x00ee }
        goto L_0x0080;
    L_0x013f:
        r2 = 2;
        r4 = new java.lang.String[r2];	 Catch:{ Exception -> 0x00ee }
        r2 = 0;
        r3 = "bookrealid";
        r4[r2] = r3;	 Catch:{ Exception -> 0x00ee }
        r2 = 1;
        r3 = "book_date";
        r4[r2] = r3;	 Catch:{ Exception -> 0x00ee }
        r3 = "note_table_name";
        r5 = "bookrealid!=0";
        r6 = 0;
        r7 = "bookrealid";
        r8 = 0;
        r9 = "book_date";
        r2 = r20;
        r2 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0346, all -> 0x034f }
        if (r2 == 0) goto L_0x019c;
    L_0x0164:
        r3 = r2.moveToFirst();	 Catch:{ Exception -> 0x0429, all -> 0x0424 }
        if (r3 == 0) goto L_0x019c;
    L_0x016a:
        r3 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0429, all -> 0x0424 }
        r3.<init>();	 Catch:{ Exception -> 0x0429, all -> 0x0424 }
        r4 = 0;
        r4 = r2.getInt(r4);	 Catch:{ Exception -> 0x0429, all -> 0x0424 }
        r5 = "bookrealid";
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x0429, all -> 0x0424 }
        r3.put(r5, r4);	 Catch:{ Exception -> 0x0429, all -> 0x0424 }
        r4 = 1;
        r4 = r2.getLong(r4);	 Catch:{ Exception -> 0x0429, all -> 0x0424 }
        r6 = "timestamp";
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ Exception -> 0x0429, all -> 0x0424 }
        r3.put(r6, r4);	 Catch:{ Exception -> 0x0429, all -> 0x0424 }
        r4 = "note_table_book";
        r5 = 0;
        r0 = r20;
        r0.insert(r4, r5, r3);	 Catch:{ Exception -> 0x0429, all -> 0x0424 }
        r3 = r2.moveToNext();	 Catch:{ Exception -> 0x0429, all -> 0x0424 }
        if (r3 != 0) goto L_0x016a;
    L_0x019c:
        if (r2 == 0) goto L_0x01a1;
    L_0x019e:
        r2.close();	 Catch:{ Exception -> 0x00ee }
    L_0x01a1:
        r2 = 6;
        r4 = new java.lang.String[r2];	 Catch:{ Exception -> 0x00ee }
        r2 = 0;
        r3 = "_id";
        r4[r2] = r3;	 Catch:{ Exception -> 0x00ee }
        r2 = 1;
        r3 = "book_id";
        r4[r2] = r3;	 Catch:{ Exception -> 0x00ee }
        r2 = 2;
        r3 = "book_name";
        r4[r2] = r3;	 Catch:{ Exception -> 0x00ee }
        r2 = 3;
        r3 = "start_point";
        r4[r2] = r3;	 Catch:{ Exception -> 0x00ee }
        r2 = 4;
        r3 = "end_point";
        r4[r2] = r3;	 Catch:{ Exception -> 0x00ee }
        r2 = 5;
        r3 = "bookrealid";
        r4[r2] = r3;	 Catch:{ Exception -> 0x00ee }
        r3 = "note_table_name";
        r5 = "bookrealid!=0";
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r2 = r20;
        r3 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00ee }
        if (r3 == 0) goto L_0x02d1;
    L_0x01da:
        r2 = r3.moveToFirst();	 Catch:{ Exception -> 0x00ee }
        if (r2 == 0) goto L_0x02d1;
    L_0x01e0:
        r2 = com.qq.reader.ReaderApplication.getApplicationImp();	 Catch:{ Exception -> 0x00ee }
        r4 = r2.getCacheDir();	 Catch:{ Exception -> 0x00ee }
    L_0x01e8:
        r2 = 0;
        r5 = r3.getInt(r2);	 Catch:{ Exception -> 0x00ee }
        r2 = 1;
        r6 = r3.getLong(r2);	 Catch:{ Exception -> 0x00ee }
        r2 = 2;
        r2 = r3.getString(r2);	 Catch:{ Exception -> 0x00ee }
        r8 = 3;
        r8 = r3.getLong(r8);	 Catch:{ Exception -> 0x00ee }
        r10 = 4;
        r10 = r3.getLong(r10);	 Catch:{ Exception -> 0x00ee }
        r12 = 5;
        r12 = r3.getLong(r12);	 Catch:{ Exception -> 0x00ee }
        r14 = new java.lang.StringBuffer;	 Catch:{ Exception -> 0x00ee }
        r14.<init>();	 Catch:{ Exception -> 0x00ee }
        r15 = com.qq.reader.common.c.a.bm;	 Catch:{ Exception -> 0x00ee }
        r14.append(r15);	 Catch:{ Exception -> 0x00ee }
        r14.append(r12);	 Catch:{ Exception -> 0x00ee }
        r14.append(r2);	 Catch:{ Exception -> 0x00ee }
        r2 = new java.io.File;	 Catch:{ Exception -> 0x00ee }
        r14 = r14.toString();	 Catch:{ Exception -> 0x00ee }
        r2.<init>(r14);	 Catch:{ Exception -> 0x00ee }
        r14 = r2.exists();	 Catch:{ Exception -> 0x00ee }
        if (r14 == 0) goto L_0x02cb;
    L_0x0225:
        r14 = r2.isDirectory();	 Catch:{ Exception -> 0x00ee }
        if (r14 == 0) goto L_0x02cb;
    L_0x022b:
        r14 = r2.listFiles();	 Catch:{ Exception -> 0x00ee }
        r14 = r14.length;	 Catch:{ Exception -> 0x00ee }
        if (r14 <= 0) goto L_0x02cb;
    L_0x0232:
        r14 = r2.listFiles();	 Catch:{ Exception -> 0x00ee }
        r15 = r14.length;	 Catch:{ Exception -> 0x00ee }
        r2 = 0;
    L_0x0238:
        if (r2 >= r15) goto L_0x02cb;
    L_0x023a:
        r16 = r14[r2];	 Catch:{ Exception -> 0x00ee }
        r17 = r16.getAbsolutePath();	 Catch:{ Exception -> 0x00ee }
        r18 = ".txt";
        r17 = r17.endsWith(r18);	 Catch:{ Exception -> 0x00ee }
        if (r17 == 0) goto L_0x0356;
    L_0x0249:
        r17 = r16.getAbsolutePath();	 Catch:{ Exception -> 0x00ee }
        r17 = r17.hashCode();	 Catch:{ Exception -> 0x00ee }
        r17 = java.lang.Math.abs(r17);	 Catch:{ Exception -> 0x00ee }
        r0 = r17;
        r0 = (long) r0;
        r18 = r0;
        r17 = (r18 > r6 ? 1 : (r18 == r6 ? 0 : -1));
        if (r17 != 0) goto L_0x041a;
    L_0x025e:
        r2 = r16.getName();	 Catch:{ Exception -> 0x0421 }
        r6 = 0;
        r7 = 5;
        r2 = r2.substring(r6, r7);	 Catch:{ Exception -> 0x0421 }
        r6 = java.lang.Long.parseLong(r2);	 Catch:{ Exception -> 0x0421 }
        r2 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0421 }
        r2.<init>();	 Catch:{ Exception -> 0x0421 }
        r14 = "start_chapter";
        r15 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x0421 }
        r2.put(r14, r15);	 Catch:{ Exception -> 0x0421 }
        r14 = "end_chapter";
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x0421 }
        r2.put(r14, r6);	 Catch:{ Exception -> 0x0421 }
        r6 = "start_offset";
        r7 = java.lang.Long.valueOf(r8);	 Catch:{ Exception -> 0x0421 }
        r2.put(r6, r7);	 Catch:{ Exception -> 0x0421 }
        r6 = "end_offset";
        r7 = java.lang.Long.valueOf(r10);	 Catch:{ Exception -> 0x0421 }
        r2.put(r6, r7);	 Catch:{ Exception -> 0x0421 }
        r6 = "bookrealid";
        r7 = java.lang.Long.valueOf(r12);	 Catch:{ Exception -> 0x0421 }
        r2.put(r6, r7);	 Catch:{ Exception -> 0x0421 }
        r6 = "note_type";
        r7 = 1;
        r7 = java.lang.Integer.valueOf(r7);	 Catch:{ Exception -> 0x0421 }
        r2.put(r6, r7);	 Catch:{ Exception -> 0x0421 }
        r6 = "note_table_name";
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0421 }
        r7.<init>();	 Catch:{ Exception -> 0x0421 }
        r8 = "_id=";
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x0421 }
        r5 = r7.append(r5);	 Catch:{ Exception -> 0x0421 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x0421 }
        r7 = 0;
        r0 = r20;
        r0.update(r6, r2, r5, r7);	 Catch:{ Exception -> 0x0421 }
    L_0x02cb:
        r2 = r3.moveToNext();	 Catch:{ Exception -> 0x00ee }
        if (r2 != 0) goto L_0x01e8;
    L_0x02d1:
        r3.close();	 Catch:{ Exception -> 0x00ee }
        r2 = 3;
        r4 = new java.lang.String[r2];	 Catch:{ Exception -> 0x00ee }
        r2 = 0;
        r3 = "_id";
        r4[r2] = r3;	 Catch:{ Exception -> 0x00ee }
        r2 = 1;
        r3 = "book_id";
        r4[r2] = r3;	 Catch:{ Exception -> 0x00ee }
        r2 = 2;
        r3 = "bookrealid";
        r4[r2] = r3;	 Catch:{ Exception -> 0x00ee }
        r3 = "note_table_name";
        r5 = "bookrealid!=0";
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r2 = r20;
        r2 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00ee }
        if (r2 == 0) goto L_0x0341;
    L_0x02fb:
        r3 = r2.moveToFirst();	 Catch:{ Exception -> 0x00ee }
        if (r3 == 0) goto L_0x0341;
    L_0x0301:
        r3 = 0;
        r3 = r2.getInt(r3);	 Catch:{ Exception -> 0x00ee }
        r4 = 1;
        r2.getLong(r4);	 Catch:{ Exception -> 0x00ee }
        r4 = 2;
        r4 = r2.getLong(r4);	 Catch:{ Exception -> 0x00ee }
        r6 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00ee }
        r6.<init>();	 Catch:{ Exception -> 0x00ee }
        r7 = "book_id";
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ Exception -> 0x00ee }
        r6.put(r7, r4);	 Catch:{ Exception -> 0x00ee }
        r4 = "note_table_name";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x041e }
        r5.<init>();	 Catch:{ Exception -> 0x041e }
        r7 = "_id=";
        r5 = r5.append(r7);	 Catch:{ Exception -> 0x041e }
        r3 = r5.append(r3);	 Catch:{ Exception -> 0x041e }
        r3 = r3.toString();	 Catch:{ Exception -> 0x041e }
        r5 = 0;
        r0 = r20;
        r0.update(r4, r6, r3, r5);	 Catch:{ Exception -> 0x041e }
    L_0x033b:
        r3 = r2.moveToNext();	 Catch:{ Exception -> 0x00ee }
        if (r3 != 0) goto L_0x0301;
    L_0x0341:
        r2.close();	 Catch:{ Exception -> 0x00ee }
        goto L_0x00f2;
    L_0x0346:
        r2 = move-exception;
        r2 = r10;
    L_0x0348:
        if (r2 == 0) goto L_0x01a1;
    L_0x034a:
        r2.close();	 Catch:{ Exception -> 0x00ee }
        goto L_0x01a1;
    L_0x034f:
        r2 = move-exception;
    L_0x0350:
        if (r10 == 0) goto L_0x0355;
    L_0x0352:
        r10.close();	 Catch:{ Exception -> 0x00ee }
    L_0x0355:
        throw r2;	 Catch:{ Exception -> 0x00ee }
    L_0x0356:
        r17 = r16.getAbsolutePath();	 Catch:{ Exception -> 0x00ee }
        r18 = "deb";
        r17 = r17.endsWith(r18);	 Catch:{ Exception -> 0x00ee }
        if (r17 == 0) goto L_0x041a;
    L_0x0363:
        r17 = r16.getName();	 Catch:{ Exception -> 0x00ee }
        r18 = 0;
        r19 = r16.getName();	 Catch:{ Exception -> 0x00ee }
        r19 = r19.length();	 Catch:{ Exception -> 0x00ee }
        r19 = r19 + -4;
        r17 = r17.substring(r18, r19);	 Catch:{ Exception -> 0x00ee }
        r18 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ee }
        r18.<init>();	 Catch:{ Exception -> 0x00ee }
        r19 = r4.getAbsolutePath();	 Catch:{ Exception -> 0x00ee }
        r18 = r18.append(r19);	 Catch:{ Exception -> 0x00ee }
        r19 = "/";
        r18 = r18.append(r19);	 Catch:{ Exception -> 0x00ee }
        r0 = r18;
        r1 = r17;
        r17 = r0.append(r1);	 Catch:{ Exception -> 0x00ee }
        r17 = r17.toString();	 Catch:{ Exception -> 0x00ee }
        r17 = r17.hashCode();	 Catch:{ Exception -> 0x00ee }
        r17 = java.lang.Math.abs(r17);	 Catch:{ Exception -> 0x00ee }
        r0 = r17;
        r0 = (long) r0;
        r18 = r0;
        r17 = (r18 > r6 ? 1 : (r18 == r6 ? 0 : -1));
        if (r17 != 0) goto L_0x041a;
    L_0x03a8:
        r2 = r16.getName();	 Catch:{ Exception -> 0x0417 }
        r6 = 0;
        r7 = 5;
        r2 = r2.substring(r6, r7);	 Catch:{ Exception -> 0x0417 }
        r6 = java.lang.Long.parseLong(r2);	 Catch:{ Exception -> 0x0417 }
        r2 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0417 }
        r2.<init>();	 Catch:{ Exception -> 0x0417 }
        r14 = "start_chapter";
        r15 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x0417 }
        r2.put(r14, r15);	 Catch:{ Exception -> 0x0417 }
        r14 = "end_chapter";
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x0417 }
        r2.put(r14, r6);	 Catch:{ Exception -> 0x0417 }
        r6 = "start_offset";
        r7 = java.lang.Long.valueOf(r8);	 Catch:{ Exception -> 0x0417 }
        r2.put(r6, r7);	 Catch:{ Exception -> 0x0417 }
        r6 = "end_offset";
        r7 = java.lang.Long.valueOf(r10);	 Catch:{ Exception -> 0x0417 }
        r2.put(r6, r7);	 Catch:{ Exception -> 0x0417 }
        r6 = "bookrealid";
        r7 = java.lang.Long.valueOf(r12);	 Catch:{ Exception -> 0x0417 }
        r2.put(r6, r7);	 Catch:{ Exception -> 0x0417 }
        r6 = "note_type";
        r7 = 1;
        r7 = java.lang.Integer.valueOf(r7);	 Catch:{ Exception -> 0x0417 }
        r2.put(r6, r7);	 Catch:{ Exception -> 0x0417 }
        r6 = "note_table_name";
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0417 }
        r7.<init>();	 Catch:{ Exception -> 0x0417 }
        r8 = "_id=";
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x0417 }
        r5 = r7.append(r5);	 Catch:{ Exception -> 0x0417 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x0417 }
        r7 = 0;
        r0 = r20;
        r0.update(r6, r2, r5, r7);	 Catch:{ Exception -> 0x0417 }
        goto L_0x02cb;
    L_0x0417:
        r2 = move-exception;
        goto L_0x02cb;
    L_0x041a:
        r2 = r2 + 1;
        goto L_0x0238;
    L_0x041e:
        r3 = move-exception;
        goto L_0x033b;
    L_0x0421:
        r2 = move-exception;
        goto L_0x02cb;
    L_0x0424:
        r3 = move-exception;
        r10 = r2;
        r2 = r3;
        goto L_0x0350;
    L_0x0429:
        r3 = move-exception;
        goto L_0x0348;
    L_0x042c:
        r3 = move-exception;
        r10 = r2;
        r2 = r3;
        goto L_0x00e8;
    L_0x0431:
        r3 = move-exception;
        goto L_0x00e0;
    L_0x0434:
        r10 = r2;
        goto L_0x0074;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.u.f(android.database.sqlite.SQLiteDatabase):void");
    }

    private static void a(SQLiteDatabase sQLiteDatabase, int i) {
        if (i == 1) {
            try {
                c(sQLiteDatabase);
                d(sQLiteDatabase);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (i == 2) {
            d(sQLiteDatabase);
        }
    }

    public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        a(sQLiteDatabase, i);
    }
}
