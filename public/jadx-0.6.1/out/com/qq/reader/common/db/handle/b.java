package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.os.Handler;
import android.os.Message;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.QueryChapterMoreInfoTask;
import com.qq.reader.module.bookchapter.online.OnlineChapter;
import com.qq.reader.module.bookchapter.online.OnlineChapterComment;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: BookChapterInfoHandle */
public class b {
    private static a a;
    private static b b;
    private Map<String, OnlineChapter> c = new HashMap();

    /* compiled from: BookChapterInfoHandle */
    private class a extends c {
        final /* synthetic */ b a;

        public a(b bVar, String str, CursorFactory cursorFactory, int i) {
            this.a = bVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    public static synchronized b a() {
        b bVar;
        synchronized (b.class) {
            if (b == null) {
                b = new b();
            }
            bVar = b;
        }
        return bVar;
    }

    private b() {
        a = new a(this, com.qq.reader.common.c.a.aU, null, 2);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists chapterinfo (_id integer primary key autoincrement,bookchapter text not null,bid text not null,chapterid text not null,authorwords  text,commentnum  integer default 0,readcount  integer default 0,hotreply  blob);");
        sQLiteDatabase.execSQL("create unique index if not exists idx on chapterinfo (bookchapter);");
    }

    public synchronized void a(String str, int i, boolean z, Handler handler) {
        g.a().a(new BookChapterInfoHandle$1(this, str, i));
        List arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i));
        ReaderTask queryChapterMoreInfoTask = new QueryChapterMoreInfoTask(str, arrayList);
        final String str2 = str;
        final int i2 = i;
        final Handler handler2 = handler;
        final boolean z2 = z;
        queryChapterMoreInfoTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ b e;

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.optInt("code") == 0) {
                        List arrayList = new ArrayList();
                        int optInt = jSONObject.optInt("replycount", 0);
                        int optInt2 = jSONObject.optInt("readcount", 0);
                        int optInt3 = jSONObject.optInt("rAmount", 0);
                        OnlineChapter onlineChapter = new OnlineChapter();
                        onlineChapter.setBookId(Long.parseLong(str2));
                        onlineChapter.setChapterId(i2);
                        onlineChapter.setCommentCount(optInt);
                        onlineChapter.setReadCount(optInt2);
                        onlineChapter.setRedPacketAmount(optInt3);
                        arrayList.add(onlineChapter);
                        this.e.a(str2, arrayList);
                        if (handler2 != null && !z2) {
                            Message obtain = Message.obtain();
                            obtain.what = 21016;
                            obtain.arg1 = 1;
                            obtain.obj = onlineChapter;
                            handler2.sendMessage(obtain);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
            }
        });
        g.a().a(queryChapterMoreInfoTask);
    }

    public synchronized OnlineChapter a(String str, int i) {
        return (OnlineChapter) this.c.get(str + "_" + i);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized com.qq.reader.module.bookchapter.online.OnlineChapter b(java.lang.String r11, int r12) {
        /*
        r10 = this;
        r8 = 0;
        monitor-enter(r10);
        r0 = a;	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r0 = r0.a();	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r1 = 4;
        r2 = new java.lang.String[r1];	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r1 = 0;
        r3 = "authorwords";
        r2[r1] = r3;	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r1 = 1;
        r3 = "commentnum";
        r2[r1] = r3;	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r1 = 2;
        r3 = "readcount";
        r2[r1] = r3;	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r1 = 3;
        r3 = "hotreply";
        r2[r1] = r3;	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r1.<init>();	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r1 = r1.append(r11);	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r3 = "_";
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r1 = r1.append(r12);	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r3 = r1.toString();	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r1 = "chapterinfo";
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r4.<init>();	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r5 = "bookchapter= '";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r3 = r4.append(r3);	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r4 = "'";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x00a3, all -> 0x00d7 }
        r0 = r1.moveToFirst();	 Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
        if (r0 == 0) goto L_0x00f8;
    L_0x0067:
        r0 = 0;
        r0 = r1.getString(r0);	 Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
        r2 = 1;
        r3 = r1.getInt(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
        r2 = 2;
        r4 = r1.getInt(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
        r2 = 3;
        r1.getBlob(r2);	 Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
        r2 = new com.qq.reader.module.bookchapter.online.OnlineChapter;	 Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
        r2.<init>();	 Catch:{ Exception -> 0x00ed, all -> 0x00e7 }
        r6 = java.lang.Long.parseLong(r11);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r2.setBookId(r6);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r2.setChapterId(r12);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r2.setAuthorWords(r0);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r2.setCommentCount(r3);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r2.setReadCount(r4);	 Catch:{ Exception -> 0x00f2, all -> 0x00e7 }
        r0 = r2;
    L_0x0093:
        if (r1 == 0) goto L_0x0098;
    L_0x0095:
        r1.close();	 Catch:{ all -> 0x00d4 }
    L_0x0098:
        r1 = a;	 Catch:{ all -> 0x00d4 }
        if (r1 == 0) goto L_0x00a1;
    L_0x009c:
        r1 = a;	 Catch:{ all -> 0x00d4 }
        r1.c();	 Catch:{ all -> 0x00d4 }
    L_0x00a1:
        monitor-exit(r10);
        return r0;
    L_0x00a3:
        r0 = move-exception;
        r1 = r0;
        r2 = r8;
        r0 = r8;
    L_0x00a7:
        r3 = "DB";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ea }
        r4.<init>();	 Catch:{ all -> 0x00ea }
        r5 = "BookChapterInfoHandle getChapterMoreInfo with exception : ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00ea }
        r1 = r1.getMessage();	 Catch:{ all -> 0x00ea }
        r1 = r4.append(r1);	 Catch:{ all -> 0x00ea }
        r1 = r1.toString();	 Catch:{ all -> 0x00ea }
        com.qq.reader.common.monitor.f.a(r3, r1);	 Catch:{ all -> 0x00ea }
        if (r2 == 0) goto L_0x00ca;
    L_0x00c7:
        r2.close();	 Catch:{ all -> 0x00d4 }
    L_0x00ca:
        r1 = a;	 Catch:{ all -> 0x00d4 }
        if (r1 == 0) goto L_0x00a1;
    L_0x00ce:
        r1 = a;	 Catch:{ all -> 0x00d4 }
        r1.c();	 Catch:{ all -> 0x00d4 }
        goto L_0x00a1;
    L_0x00d4:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x00d7:
        r0 = move-exception;
    L_0x00d8:
        if (r8 == 0) goto L_0x00dd;
    L_0x00da:
        r8.close();	 Catch:{ all -> 0x00d4 }
    L_0x00dd:
        r1 = a;	 Catch:{ all -> 0x00d4 }
        if (r1 == 0) goto L_0x00e6;
    L_0x00e1:
        r1 = a;	 Catch:{ all -> 0x00d4 }
        r1.c();	 Catch:{ all -> 0x00d4 }
    L_0x00e6:
        throw r0;	 Catch:{ all -> 0x00d4 }
    L_0x00e7:
        r0 = move-exception;
        r8 = r1;
        goto L_0x00d8;
    L_0x00ea:
        r0 = move-exception;
        r8 = r2;
        goto L_0x00d8;
    L_0x00ed:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        r0 = r8;
        goto L_0x00a7;
    L_0x00f2:
        r0 = move-exception;
        r9 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r9;
        goto L_0x00a7;
    L_0x00f8:
        r0 = r8;
        goto L_0x0093;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.b.b(java.lang.String, int):com.qq.reader.module.bookchapter.online.OnlineChapter");
    }

    public byte[] a(List<OnlineChapterComment> list) {
        byte[] toByteArray;
        Exception e;
        if (list == null || list.size() == 0) {
            return new byte[0];
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(list);
            objectOutputStream.flush();
            toByteArray = byteArrayOutputStream.toByteArray();
            try {
                objectOutputStream.close();
                byteArrayOutputStream.close();
                return toByteArray;
            } catch (Exception e2) {
                e = e2;
                e.printStackTrace();
                return toByteArray;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            toByteArray = null;
            e = exception;
            e.printStackTrace();
            return toByteArray;
        }
    }

    private synchronized boolean a(String str, List<OnlineChapter> list) {
        Cursor cursor;
        Exception e;
        Exception exception;
        boolean z;
        SQLiteDatabase a;
        if (str != null) {
            if (!(str.length() == 0 || list == null || list.size() == 0)) {
                SQLiteDatabase sQLiteDatabase = null;
                cursor = null;
                try {
                    a = a.a();
                    if (a != null) {
                        try {
                            a.beginTransaction();
                            try {
                                Cursor cursor2 = cursor;
                                for (OnlineChapter onlineChapter : list) {
                                    try {
                                        String authorWords = onlineChapter.getAuthorWords();
                                        int commentCount = onlineChapter.getCommentCount();
                                        int chapterId = onlineChapter.getChapterId();
                                        int readCount = onlineChapter.getReadCount();
                                        ContentValues contentValues = new ContentValues();
                                        if (authorWords != null && authorWords.trim().length() > 0) {
                                            contentValues.put("authorwords", authorWords);
                                        }
                                        contentValues.put("commentnum", Integer.valueOf(commentCount));
                                        contentValues.put("hotreply", a(onlineChapter.getHotCommentList()));
                                        contentValues.put("readcount", Integer.valueOf(readCount));
                                        String[] strArr = new String[]{"authorwords", "commentnum"};
                                        String str2 = str + "_" + chapterId;
                                        cursor = a.query("chapterinfo", strArr, "bookchapter= '" + str2 + "'", null, null, null, null);
                                        if (cursor.getCount() > 0) {
                                            a.update("chapterinfo", contentValues, "bookchapter= '" + str2 + "'", null);
                                        } else {
                                            contentValues.put("bid", str);
                                            contentValues.put("chapterid", Integer.valueOf(chapterId));
                                            contentValues.put("bookchapter", str2);
                                            a.insert("chapterinfo", null, contentValues);
                                        }
                                        this.c.put(str2, onlineChapter);
                                        cursor.close();
                                        cursor2 = cursor;
                                    } catch (Exception e2) {
                                        e = e2;
                                        cursor = cursor2;
                                    }
                                }
                                a.setTransactionSuccessful();
                                a.endTransaction();
                            } catch (Exception e3) {
                                e = e3;
                            }
                        } catch (Exception e4) {
                            Exception exception2 = e4;
                            sQLiteDatabase = a;
                            exception = exception2;
                            try {
                                f.a("BookmarkHandle", "error in updateBookNewContentState : " + exception.toString());
                                z = false;
                                if (sQLiteDatabase != null) {
                                    a.c();
                                }
                                return z;
                            } catch (Throwable th) {
                                Throwable th2 = th;
                                if (sQLiteDatabase != null) {
                                    a.c();
                                }
                                throw th2;
                            }
                        } catch (Throwable th3) {
                            Throwable th4 = th3;
                            sQLiteDatabase = a;
                            th2 = th4;
                            if (sQLiteDatabase != null) {
                                a.c();
                            }
                            throw th2;
                        }
                    }
                    if (a != null) {
                        a.c();
                    }
                    z = true;
                } catch (Exception e5) {
                    exception = e5;
                    f.a("BookmarkHandle", "error in updateBookNewContentState : " + exception.toString());
                    z = false;
                    if (sQLiteDatabase != null) {
                        a.c();
                    }
                    return z;
                }
            }
        }
        z = false;
        return z;
        f.a("DB", "ChapterPayDBHandle getWritableDatabase with exception : " + e4.getMessage());
        if (cursor != null) {
            cursor.close();
        }
        a.endTransaction();
        if (a != null) {
            a.c();
        }
        z = true;
        return z;
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Cursor rawQuery2;
        Exception e2;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select readcount from chapterinfo", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(2);
                        rawQuery.close();
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e3) {
                    e = e3;
                    try {
                        f.a("DB", " update1To2 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE chapterinfo ADD readcount int default 0");
                        rawQuery2 = sQLiteDatabase.rawQuery("select hotreply from chapterinfo", null);
                        if (rawQuery2 != null) {
                            try {
                                if (rawQuery2.getCount() > 0) {
                                    sQLiteDatabase.setVersion(2);
                                    rawQuery2.close();
                                    if (cursor == null) {
                                        cursor.close();
                                        return;
                                    }
                                    return;
                                }
                            } catch (Exception e4) {
                                e2 = e4;
                                try {
                                    f.a("DB", " update1To2 :" + e2.toString());
                                    if (rawQuery2 != null) {
                                        rawQuery2.close();
                                    }
                                    sQLiteDatabase.execSQL("ALTER TABLE chapterinfo ADD hotreply blob");
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (rawQuery2 != null) {
                                        rawQuery2.close();
                                    }
                                    throw th;
                                }
                            }
                        }
                        if (rawQuery2 != null) {
                            rawQuery2.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE chapterinfo ADD hotreply blob");
                    } catch (Throwable th3) {
                        cursor = rawQuery;
                        th = th3;
                        if (cursor != null) {
                            cursor.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e22) {
            e = e22;
            rawQuery = cursor;
            f.a("DB", " update1To2 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE chapterinfo ADD readcount int default 0");
            rawQuery2 = sQLiteDatabase.rawQuery("select hotreply from chapterinfo", null);
            if (rawQuery2 != null) {
                if (rawQuery2.getCount() > 0) {
                    sQLiteDatabase.setVersion(2);
                    rawQuery2.close();
                    if (cursor == null) {
                        cursor.close();
                        return;
                    }
                    return;
                }
            }
            if (rawQuery2 != null) {
                rawQuery2.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE chapterinfo ADD hotreply blob");
        } catch (Throwable th4) {
            th = th4;
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE chapterinfo ADD readcount int default 0");
        try {
            rawQuery2 = sQLiteDatabase.rawQuery("select hotreply from chapterinfo", null);
            if (rawQuery2 != null) {
                if (rawQuery2.getCount() > 0) {
                    sQLiteDatabase.setVersion(2);
                    rawQuery2.close();
                    if (cursor == null) {
                        cursor.close();
                        return;
                    }
                    return;
                }
            }
            if (rawQuery2 != null) {
                rawQuery2.close();
            }
        } catch (Exception e5) {
            Exception exception = e5;
            rawQuery2 = rawQuery;
            e22 = exception;
            f.a("DB", " update1To2 :" + e22.toString());
            if (rawQuery2 != null) {
                rawQuery2.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE chapterinfo ADD hotreply blob");
        } catch (Throwable th32) {
            Throwable th5 = th32;
            rawQuery2 = rawQuery;
            th = th5;
            if (rawQuery2 != null) {
                rawQuery2.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE chapterinfo ADD hotreply blob");
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
        switch (i) {
            case 1:
                b(sQLiteDatabase);
                return;
            default:
                return;
        }
    }
}
