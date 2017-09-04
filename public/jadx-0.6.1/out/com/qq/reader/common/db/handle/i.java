package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.BookShelfFragment;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.am;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.DownloadMark;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.framework.mark.UserMark;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubReplyCard;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qq.reader.module.comic.mark.ComicBookMark;
import com.qq.reader.qplugin.local.TingBookMark;
import com.qq.reader.view.metro.MetroItem;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import com.tencent.qalsdk.im_open.http;
import com.tencent.qalsdk.util.BaseApplication;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: BookmarkHandle */
public class i extends b {
    public static int a = 1;
    public static int b = 3;
    public static int c = 2;
    private static c f;
    private static volatile i g;
    int d = 8;
    private final String e = "BookmarkHandle";
    private d h;

    /* compiled from: BookmarkHandle */
    private class a extends c {
        final /* synthetic */ i a;

        public a(i iVar, String str, CursorFactory cursorFactory, int i) {
            this.a = iVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public synchronized SQLiteDatabase a() throws SQLiteException {
            return super.a();
        }

        public synchronized SQLiteDatabase b() {
            return super.b();
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    private void d(android.database.sqlite.SQLiteDatabase r5) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x0035 in list [B:8:0x0032]
	at jadx.core.utils.BlockUtils.getBlockByOffset(BlockUtils.java:42)
	at jadx.core.dex.instructions.IfNode.initBlocks(IfNode.java:60)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.initBlocksInIfNodes(BlockFinish.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockFinish.visit(BlockFinish.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r4 = this;
        r1 = 0;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r2 = "ALTER TABLE bookshelf ADD category integer default ";
        r0 = r0.append(r2);
        r2 = b;
        r0 = r0.append(r2);
        r0 = r0.toString();
        r5.execSQL(r0);
        r0 = "create table if not exists category (_id integer primary key autoincrement,category_name text not null);";
        r5.execSQL(r0);
        r0 = "select category_name from category where category.category_name= '全部'";	 Catch:{ all -> 0x005c }
        r2 = 0;	 Catch:{ all -> 0x005c }
        r1 = r5.rawQuery(r0, r2);	 Catch:{ all -> 0x005c }
        if (r1 == 0) goto L_0x0036;	 Catch:{ all -> 0x005c }
    L_0x002a:
        r0 = r1.getCount();	 Catch:{ all -> 0x005c }
        if (r0 <= 0) goto L_0x0036;
    L_0x0030:
        if (r1 == 0) goto L_0x0035;
    L_0x0032:
        r1.close();
    L_0x0035:
        return;
    L_0x0036:
        r0 = new android.content.ContentValues;	 Catch:{ all -> 0x005c }
        r0.<init>();	 Catch:{ all -> 0x005c }
        r2 = "category_name";	 Catch:{ all -> 0x005c }
        r3 = "全部";	 Catch:{ all -> 0x005c }
        r0.put(r2, r3);	 Catch:{ all -> 0x005c }
        r2 = "category";	 Catch:{ all -> 0x005c }
        r3 = 0;	 Catch:{ all -> 0x005c }
        r5.insert(r2, r3, r0);	 Catch:{ all -> 0x005c }
        r2 = "category_name";	 Catch:{ all -> 0x005c }
        r3 = "未分组";	 Catch:{ all -> 0x005c }
        r0.put(r2, r3);	 Catch:{ all -> 0x005c }
        r2 = "category";	 Catch:{ all -> 0x005c }
        r3 = 0;	 Catch:{ all -> 0x005c }
        r5.insert(r2, r3, r0);	 Catch:{ all -> 0x005c }
        goto L_0x0030;
    L_0x005c:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0062;
    L_0x005f:
        r1.close();
    L_0x0062:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.i.d(android.database.sqlite.SQLiteDatabase):void");
    }

    public void a() {
        synchronized (i.class) {
            g = null;
        }
    }

    public static void b() {
        synchronized (i.class) {
            g = null;
        }
    }

    public static i c() {
        if (g == null) {
            synchronized (i.class) {
                if (g == null) {
                    g = new i();
                }
            }
        }
        return g;
    }

    private i() {
        f = new a(this, com.qq.reader.common.c.a.bg, null, 12);
        this.h = new d();
        this.h.a(r());
    }

    public boolean a(String str, boolean z) {
        if (str == null || str.length() == 0) {
            return false;
        }
        String d = d(str, z);
        if (d == null || d.length() <= 0 || ao.b(d) == null) {
            return false;
        }
        return true;
    }

    public Mark[] a(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        List k = k(d(str, false));
        if (k == null || k.size() <= 0) {
            return null;
        }
        return (Mark[]) k.toArray(new Mark[k.size()]);
    }

    public synchronized List<Mark> a(long j, String str) {
        List<Mark> list;
        String str2 = "";
        if (j == 0 && (str == null || str.length() == 0)) {
            list = null;
        } else {
            if (j == 0) {
                str2 = "pathid='" + str + "'";
            } else {
                str2 = "pathid='" + str + "' or " + "book_id" + "=" + j;
            }
            list = j(str2);
        }
        return list;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.qq.reader.framework.mark.Mark> j(java.lang.String r25) {
        /*
        r24 = this;
        r11 = 0;
        r10 = 0;
        r3 = 0;
        r2 = f;	 Catch:{ Exception -> 0x009e, all -> 0x00ce }
        r2 = r2.a();	 Catch:{ Exception -> 0x009e, all -> 0x00ce }
        r4 = r24.s();	 Catch:{ Exception -> 0x00ed, all -> 0x00df }
        r3 = "usermark";
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = "addtime desc";
        r5 = r25;
        r19 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x00ed, all -> 0x00df }
        if (r19 == 0) goto L_0x0101;
    L_0x001e:
        r3 = r19.moveToFirst();	 Catch:{ Exception -> 0x00f2, all -> 0x00e5 }
        if (r3 == 0) goto L_0x0101;
    L_0x0024:
        r20 = new java.util.ArrayList;	 Catch:{ Exception -> 0x00f2, all -> 0x00e5 }
        r20.<init>();	 Catch:{ Exception -> 0x00f2, all -> 0x00e5 }
    L_0x0029:
        r3 = 0;
        r0 = r19;
        r22 = r0.getLong(r3);	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        r3 = 1;
        r0 = r19;
        r7 = r0.getString(r3);	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        r3 = 2;
        r0 = r19;
        r8 = r0.getString(r3);	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        r3 = 3;
        r0 = r19;
        r12 = r0.getLong(r3);	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        r3 = 4;
        r0 = r19;
        r9 = r0.getInt(r3);	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        r3 = 5;
        r0 = r19;
        r10 = r0.getLong(r3);	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        r3 = 6;
        r0 = r19;
        r17 = r0.getString(r3);	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        r3 = 7;
        r0 = r19;
        r15 = r0.getLong(r3);	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        r3 = 8;
        r0 = r19;
        r14 = r0.getInt(r3);	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        r3 = 9;
        r0 = r19;
        r5 = r0.getLong(r3);	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        r3 = 10;
        r0 = r19;
        r18 = r0.getString(r3);	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        r4 = new com.qq.reader.framework.mark.UserMark;	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        r4.<init>(r5, r7, r8, r9, r10, r12, r14, r15, r17, r18);	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        r0 = r22;
        r4.setDbId(r0);	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        r0 = r20;
        r0.add(r4);	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        r3 = r19.moveToNext();	 Catch:{ Exception -> 0x00f8, all -> 0x00e5 }
        if (r3 != 0) goto L_0x0029;
    L_0x008e:
        r3 = r20;
    L_0x0090:
        if (r19 == 0) goto L_0x0095;
    L_0x0092:
        r19.close();
    L_0x0095:
        if (r2 == 0) goto L_0x00ff;
    L_0x0097:
        r2 = f;
        r2.c();
        r2 = r3;
    L_0x009d:
        return r2;
    L_0x009e:
        r2 = move-exception;
        r4 = r3;
        r5 = r10;
        r3 = r2;
        r2 = r11;
    L_0x00a3:
        r6 = "BookmarkHandle";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00e9 }
        r7.<init>();	 Catch:{ all -> 0x00e9 }
        r8 = "BookmarkHandle getUserMarkByLocalId ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x00e9 }
        r3 = r3.toString();	 Catch:{ all -> 0x00e9 }
        r3 = r7.append(r3);	 Catch:{ all -> 0x00e9 }
        r3 = r3.toString();	 Catch:{ all -> 0x00e9 }
        com.qq.reader.common.monitor.f.a(r6, r3);	 Catch:{ all -> 0x00e9 }
        if (r5 == 0) goto L_0x00c6;
    L_0x00c3:
        r5.close();
    L_0x00c6:
        if (r4 == 0) goto L_0x009d;
    L_0x00c8:
        r3 = f;
        r3.c();
        goto L_0x009d;
    L_0x00ce:
        r2 = move-exception;
        r4 = r3;
        r19 = r10;
    L_0x00d2:
        if (r19 == 0) goto L_0x00d7;
    L_0x00d4:
        r19.close();
    L_0x00d7:
        if (r4 == 0) goto L_0x00de;
    L_0x00d9:
        r3 = f;
        r3.c();
    L_0x00de:
        throw r2;
    L_0x00df:
        r3 = move-exception;
        r4 = r2;
        r19 = r10;
        r2 = r3;
        goto L_0x00d2;
    L_0x00e5:
        r3 = move-exception;
        r4 = r2;
        r2 = r3;
        goto L_0x00d2;
    L_0x00e9:
        r2 = move-exception;
        r19 = r5;
        goto L_0x00d2;
    L_0x00ed:
        r3 = move-exception;
        r4 = r2;
        r5 = r10;
        r2 = r11;
        goto L_0x00a3;
    L_0x00f2:
        r3 = move-exception;
        r4 = r2;
        r5 = r19;
        r2 = r11;
        goto L_0x00a3;
    L_0x00f8:
        r3 = move-exception;
        r4 = r2;
        r5 = r19;
        r2 = r20;
        goto L_0x00a3;
    L_0x00ff:
        r2 = r3;
        goto L_0x009d;
    L_0x0101:
        r3 = r11;
        goto L_0x0090;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.i.j(java.lang.String):java.util.List<com.qq.reader.framework.mark.Mark>");
    }

    public synchronized int d() {
        Cursor cursor = null;
        int i = 0;
        synchronized (this) {
            try {
                cursor = f.a().rawQuery("select count(*) from bookshelf where book_id <= 0", null);
                if (cursor != null && cursor.moveToFirst()) {
                    i = cursor.getInt(0);
                }
                if (cursor != null) {
                    if (!cursor.isClosed()) {
                        cursor.close();
                    }
                }
                f.c();
            } catch (Exception e) {
                f.a("BookmarkHandle", "BookmarkHandle getUserImportedBookCount " + e.toString());
                if (cursor != null) {
                    if (!cursor.isClosed()) {
                        cursor.close();
                    }
                }
                f.c();
            } catch (Throwable th) {
                if (cursor != null) {
                    if (!cursor.isClosed()) {
                        cursor.close();
                    }
                }
                f.c();
            }
        }
        return i;
    }

    public synchronized int e() {
        return this.h.b();
    }

    public synchronized List<com.qq.reader.cservice.bookfollow.a> f() {
        List<com.qq.reader.cservice.bookfollow.a> arrayList;
        Exception e;
        Throwable th;
        arrayList = new ArrayList();
        Cursor query;
        try {
            query = f.a().query("bookshelf", new String[]{"path", "name", "book_id", "newcontent", "update_chapter", "update_time"}, "newcontent > 0 and book_id > 0", null, null, null, "update_time desc");
            try {
                if (query.moveToFirst()) {
                    do {
                        String string;
                        query.getString(0);
                        String string2 = query.getString(1);
                        long j = query.getLong(2);
                        if (query.getInt(3) == 0) {
                            string = query.getString(4);
                        } else {
                            string = query.getString(4);
                        }
                        if (string != null) {
                            arrayList.add(new com.qq.reader.cservice.bookfollow.a(j, string2, string));
                        }
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
                f.c();
            } catch (Exception e2) {
                e = e2;
                try {
                    f.a("DB", "getAllAutoMarkDB with exception: " + e.toString());
                    if (query != null) {
                        query.close();
                    }
                    f.c();
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    f.c();
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            f.a("DB", "getAllAutoMarkDB with exception: " + e.toString());
            if (query != null) {
                query.close();
            }
            f.c();
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            f.c();
            throw th;
        }
        return arrayList;
    }

    public synchronized List<Mark> g() {
        return this.h.a();
    }

    public synchronized List<Mark> h() {
        return this.h.b(u());
    }

    public synchronized List<Mark> i() {
        return this.h.b(t());
    }

    private List<Mark> a(List<Mark> list, String str) {
        if (10 >= list.size()) {
            return list;
        }
        a(10, str);
        return k(str);
    }

    public Mark b(String str, boolean z) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (z) {
            return this.h.c(str);
        }
        return c().f(str);
    }

    public synchronized boolean a(Mark mark, boolean z) {
        boolean z2;
        if (mark != null) {
            if (!(mark.getId() == null || mark.getId().length() == 0)) {
                this.h.a(mark);
                z2 = true;
            }
        }
        z2 = false;
        return z2;
    }

    public synchronized void b(String str) {
        this.h.b(str);
    }

    public synchronized void a(Mark mark) {
        this.h.b(mark);
    }

    public synchronized boolean b(long j, String str) {
        SQLiteDatabase sQLiteDatabase = null;
        boolean z = false;
        synchronized (this) {
            if (j > 0) {
                try {
                    sQLiteDatabase = f.a();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("path", str);
                    sQLiteDatabase.update("bookshelf", contentValues, "book_id=" + j, null);
                    z = true;
                    if (sQLiteDatabase != null) {
                        f.c();
                    }
                } catch (Exception e) {
                    f.a("BookmarkHandle", "error in changBookPath : " + e.toString());
                    if (sQLiteDatabase != null) {
                        f.c();
                    }
                } catch (Throwable th) {
                    if (sQLiteDatabase != null) {
                        f.c();
                    }
                }
            }
        }
        return z;
    }

    public synchronized boolean a(UserMark userMark) {
        SQLiteDatabase a;
        boolean a2;
        Throwable th;
        try {
            a = f.a();
            try {
                a2 = a(a, userMark);
                if (a != null) {
                    f.c();
                }
            } catch (Exception e) {
                a2 = false;
                if (a != null) {
                    f.c();
                }
                return a2;
            } catch (Throwable th2) {
                th = th2;
                if (a != null) {
                    f.c();
                }
                throw th;
            }
        } catch (Exception e2) {
            a = null;
            a2 = false;
            if (a != null) {
                f.c();
            }
            return a2;
        } catch (Throwable th3) {
            Throwable th4 = th3;
            a = null;
            th = th4;
            if (a != null) {
                f.c();
            }
            throw th;
        }
        return a2;
    }

    private boolean a(SQLiteDatabase sQLiteDatabase, UserMark userMark) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("pathid", userMark.getId());
            contentValues.put("book_id", Long.valueOf(userMark.getBookId()));
            contentValues.put("name", userMark.getBookName());
            contentValues.put("addtime", Long.valueOf(userMark.getOperateTime()));
            contentValues.put("type", Integer.valueOf(userMark.getType()));
            contentValues.put("percent", userMark.getPercentStr());
            contentValues.put("startpoint", Long.valueOf(userMark.getStartPoint()));
            contentValues.put("chapterid", Integer.valueOf(userMark.getChapterId()));
            contentValues.put("chapteroffset", Long.valueOf(userMark.getChapterOffset()));
            contentValues.put(SocialConstants.PARAM_COMMENT, userMark.getDescriptionStr());
            if (sQLiteDatabase.insert("usermark", null, contentValues) >= 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            f.a("DB", "error in insert UserBookmark: " + e.toString());
            return false;
        }
    }

    public boolean b(UserMark userMark) {
        Cursor cursor = null;
        boolean z = false;
        try {
            String str;
            SQLiteDatabase b = f.b();
            if (userMark.getBookId() == 0) {
                str = "pathid='" + userMark.getId() + "' and " + "startpoint" + "=" + userMark.getStartPoint();
            } else {
                str = "book_id=" + userMark.getBookId() + " and " + "chapterid" + "=" + userMark.getChapterId() + " and " + "chapteroffset" + "=" + userMark.getChapterOffset();
            }
            cursor = b.rawQuery("select count(*) from usermark where (" + str + ")", null);
            if (cursor != null && cursor.moveToFirst() && cursor.getInt(0) > 0) {
                z = true;
            }
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("BookMarkHandle", e.getMessage());
                }
            }
            f.c();
        } catch (Exception e2) {
            com.qq.reader.common.monitor.debug.c.e("BookMarkHandler", e2.getMessage());
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e22) {
                    com.qq.reader.common.monitor.debug.c.e("BookMarkHandle", e22.getMessage());
                }
            }
            f.c();
        } catch (Throwable th) {
            if (cursor != null) {
                try {
                    cursor.close();
                } catch (Exception e222) {
                    com.qq.reader.common.monitor.debug.c.e("BookMarkHandle", e222.getMessage());
                }
            }
            f.c();
        }
        return z;
    }

    public boolean a(String str, Mark[] markArr, boolean z) {
        if (str == null || str.length() == 0 || markArr == null || markArr.length == 0) {
            return false;
        }
        List list;
        Mark[] markArr2;
        String d = d(str, false);
        List k = k(d);
        if (k == null || k.size() <= http.Internal_Server_Error) {
            list = k;
        } else {
            list = a(k, d);
        }
        if (list == null || list.size() <= 0) {
            markArr2 = null;
        } else {
            Mark[] markArr3 = new Mark[list.size()];
            list.toArray(markArr3);
            markArr2 = markArr3;
        }
        if (markArr2 == null || markArr2.length == 0) {
            return a(d, markArr);
        }
        if (0 >= markArr.length) {
            return false;
        }
        Mark mark = markArr[0];
        int i = 0;
        while (i < markArr2.length) {
            if (markArr2[i].getDescriptionStr().equals(mark.getDescriptionStr()) && markArr2[i].getPercentStr().equals(mark.getPercentStr())) {
                markArr2[i] = mark;
                i = 1;
                break;
            }
            i++;
        }
        boolean z2 = false;
        if (i == 0) {
            return a(d, new Mark[]{mark});
        } else if (!z) {
            return true;
        } else {
            ao.a(new File(d));
            return a(d, markArr2);
        }
    }

    public synchronized boolean c(String str) {
        this.h.a(str);
        g.a().a(new BookmarkHandle$1(this, str));
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean a(java.util.List<com.qq.reader.framework.mark.Mark> r11) {
        /*
        r10 = this;
        r3 = 0;
        r2 = 1;
        r1 = 0;
        monitor-enter(r10);
        r0 = f;	 Catch:{ Exception -> 0x00aa, all -> 0x0097 }
        r3 = r0.a();	 Catch:{ Exception -> 0x00aa, all -> 0x0097 }
        r4 = r11.iterator();	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
    L_0x000e:
        r0 = r4.hasNext();	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        if (r0 == 0) goto L_0x008e;
    L_0x0014:
        r0 = r4.next();	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        r0 = (com.qq.reader.framework.mark.Mark) r0;	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        r5 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        r5.<init>();	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        r6 = "cloud_del_tag";
        r7 = 1;
        r7 = java.lang.Integer.valueOf(r7);	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        r5.put(r6, r7);	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        r6 = "bookshelf";
        r7 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        r7.<init>();	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        r8 = "book_id=";
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        r8 = r0.getBookId();	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        r7 = r7.append(r8);	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        r7 = r7.toString();	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        r8 = 0;
        r5 = r3.update(r6, r5, r7, r8);	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        r6 = (long) r5;
        r8 = 0;
        r5 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r5 >= 0) goto L_0x005b;
    L_0x0051:
        if (r3 == 0) goto L_0x0058;
    L_0x0053:
        r0 = f;	 Catch:{ Exception -> 0x00af }
        r0.c();	 Catch:{ Exception -> 0x00af }
    L_0x0058:
        r0 = r1;
    L_0x0059:
        monitor-exit(r10);
        return r0;
    L_0x005b:
        r5 = r10.h;	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        r6 = r0.getBookId();	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        r5.a(r6);	 Catch:{ Exception -> 0x0065, all -> 0x0097 }
        goto L_0x000e;
    L_0x0065:
        r0 = move-exception;
        r2 = r3;
    L_0x0067:
        r3 = "DB";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00a5 }
        r4.<init>();	 Catch:{ all -> 0x00a5 }
        r5 = "signCloudDelMark with exception : ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00a5 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x00a5 }
        r0 = r4.append(r0);	 Catch:{ all -> 0x00a5 }
        r0 = r0.toString();	 Catch:{ all -> 0x00a5 }
        com.qq.reader.common.monitor.f.a(r3, r0);	 Catch:{ all -> 0x00a5 }
        if (r2 == 0) goto L_0x008c;
    L_0x0087:
        r0 = f;	 Catch:{ Exception -> 0x00a8 }
        r0.c();	 Catch:{ Exception -> 0x00a8 }
    L_0x008c:
        r0 = r1;
        goto L_0x0059;
    L_0x008e:
        if (r3 == 0) goto L_0x0095;
    L_0x0090:
        r0 = f;	 Catch:{ Exception -> 0x00ad }
        r0.c();	 Catch:{ Exception -> 0x00ad }
    L_0x0095:
        r0 = r2;
        goto L_0x0059;
    L_0x0097:
        r0 = move-exception;
    L_0x0098:
        if (r3 == 0) goto L_0x009f;
    L_0x009a:
        r1 = f;	 Catch:{ Exception -> 0x00a3 }
        r1.c();	 Catch:{ Exception -> 0x00a3 }
    L_0x009f:
        throw r0;	 Catch:{ all -> 0x00a0 }
    L_0x00a0:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x00a3:
        r1 = move-exception;
        goto L_0x009f;
    L_0x00a5:
        r0 = move-exception;
        r3 = r2;
        goto L_0x0098;
    L_0x00a8:
        r0 = move-exception;
        goto L_0x008c;
    L_0x00aa:
        r0 = move-exception;
        r2 = r3;
        goto L_0x0067;
    L_0x00ad:
        r0 = move-exception;
        goto L_0x0095;
    L_0x00af:
        r0 = move-exception;
        goto L_0x0058;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.i.a(java.util.List):boolean");
    }

    public synchronized boolean b(List<Mark> list) {
        boolean z;
        int i;
        int size = list.size();
        for (i = 0; i < size; i++) {
            this.h.a(((Mark) list.get(i)).getId());
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("path in (");
        if (size > 0) {
            for (i = 0; i < size - 1; i++) {
                stringBuilder.append('\'');
                stringBuilder.append(((Mark) list.get(i)).getId());
                stringBuilder.append('\'');
                stringBuilder.append(",");
            }
            stringBuilder.append('\'');
            stringBuilder.append(((Mark) list.get(size - 1)).getId());
            stringBuilder.append('\'');
        }
        stringBuilder.append(");");
        try {
            if (f.a().delete("bookshelf", stringBuilder.toString(), null) > 0) {
                z = true;
            } else {
                z = false;
            }
            boolean z2 = z;
        } catch (Exception e) {
            f.a("DB", "delAutoMarkDB with exception : " + e.getMessage());
            f.c();
            z2 = false;
        }
        if (z2) {
            int size2 = list.size();
            if (list.size() <= 0) {
                z = z2;
            } else {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("pathid in (");
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append("book_id in (");
                if (size2 > 0) {
                    for (size = 0; size < size2 - 1; size++) {
                        String str;
                        String id = ((Mark) list.get(size)).getId();
                        long bookId = ((Mark) list.get(size)).getBookId();
                        if (TextUtils.isEmpty(id)) {
                            str = id;
                        } else {
                            str = id.replace("'", "''");
                        }
                        stringBuilder2.append('\'').append(str).append('\'').append(",");
                        if (bookId != 0) {
                            stringBuilder3.append(bookId).append(",");
                        }
                    }
                    stringBuilder2.append('\'').append(((Mark) list.get(size2 - 1)).getId()).append('\'');
                    stringBuilder3.append(((Mark) list.get(size2 - 1)).getBookId());
                }
                stringBuilder2.append(")");
                stringBuilder3.append(");");
                stringBuilder2.append(" or ").append(stringBuilder3.toString());
                try {
                    f.a().delete("usermark", stringBuilder2.toString(), null);
                } catch (Exception e2) {
                    f.a("DB", "clearUserMark with exception : " + e2.getMessage());
                    z = z2;
                    return z;
                } finally {
                    f.c();
                }
                z = z2;
            }
        } else {
            z = z2;
        }
        return z;
    }

    private boolean a(int i, String str) {
        RandomAccessFile randomAccessFile;
        Exception e;
        Throwable th;
        File file = new File(str);
        if (!file.exists()) {
            return true;
        }
        try {
            long[] b = b(i, str);
            randomAccessFile = new RandomAccessFile(file, "rw");
            if (b == null) {
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
                return false;
            }
            try {
                byte[] bArr;
                long j = b[1] + b[0];
                if (j < randomAccessFile.length()) {
                    randomAccessFile.seek(j);
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bArr = new byte[1024];
                    while (true) {
                        int read = randomAccessFile.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr, 0, read);
                    }
                    bArr = byteArrayOutputStream.toByteArray();
                } else {
                    bArr = null;
                }
                if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                ao.a(new File(str));
                try {
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(ao.c(str), "rw");
                    if (bArr != null) {
                        try {
                            randomAccessFile2.write(bArr);
                        } catch (Exception e4) {
                            e = e4;
                            randomAccessFile = randomAccessFile2;
                            try {
                                f.a("remove Exception : ", e.toString());
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (IOException e22) {
                                        e22.printStackTrace();
                                    }
                                }
                                return false;
                            } catch (Throwable th2) {
                                th = th2;
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (IOException e5) {
                                        e5.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            randomAccessFile = randomAccessFile2;
                            if (randomAccessFile != null) {
                                randomAccessFile.close();
                            }
                            throw th;
                        }
                    }
                    if (randomAccessFile2 == null) {
                        return true;
                    }
                    try {
                        randomAccessFile2.close();
                        return true;
                    } catch (IOException e52) {
                        e52.printStackTrace();
                        return true;
                    }
                } catch (Exception e6) {
                    e = e6;
                    f.a("remove Exception : ", e.toString());
                    if (randomAccessFile != null) {
                        randomAccessFile.close();
                    }
                    return false;
                }
            } catch (Exception e7) {
                e = e7;
                try {
                    f.a("remove Exception : ", e.toString());
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e222) {
                            e222.printStackTrace();
                        }
                    }
                    return false;
                } catch (Throwable th4) {
                    th = th4;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e522) {
                            e522.printStackTrace();
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e8) {
            e = e8;
            randomAccessFile = null;
            f.a("remove Exception : ", e.toString());
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            return false;
        } catch (Throwable th5) {
            th = th5;
            randomAccessFile = null;
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            throw th;
        }
    }

    public synchronized boolean c(UserMark userMark) {
        boolean z = false;
        synchronized (this) {
            int delete;
            try {
                String str;
                if (userMark.getBookId() == 0) {
                    str = "pathid='" + userMark.getId() + "' and " + "startpoint" + "=" + userMark.getStartPoint();
                } else {
                    str = "(book_id=" + userMark.getBookId() + " and " + "chapterid" + "=" + userMark.getChapterId() + " and " + "chapteroffset" + "=" + userMark.getChapterOffset() + ")";
                }
                SQLiteDatabase a = f.a();
                delete = a.delete("usermark", str, null);
                if (a != null) {
                    f.c();
                }
            } catch (Exception e) {
                f.a("DB", "delUserMark with exception : " + e.getMessage());
                if (null != null) {
                    f.c();
                    delete = 0;
                } else {
                    delete = 0;
                }
            } catch (Throwable th) {
                if (null != null) {
                    f.c();
                }
            }
            if (delete > 0) {
                z = true;
            }
        }
        return z;
    }

    public synchronized boolean c(long j, String str) {
        boolean z = false;
        synchronized (this) {
            int i;
            String str2;
            if (str != null) {
                try {
                    if (str.length() > 0) {
                        str = str.replace("'", "''");
                    }
                } catch (Exception e) {
                    f.a("DB", "clearUserMark with exception : " + e.getMessage());
                    f.c();
                    i = 0;
                } catch (Throwable th) {
                    f.c();
                }
            }
            if (j == 0) {
                str2 = "pathid='" + str + "'";
            } else {
                str2 = "pathid='" + str + "' or " + "book_id" + "=" + j;
            }
            i = f.a().delete("usermark", str2, null);
            f.c();
            if (i > 0) {
                z = true;
            }
        }
        return z;
    }

    public boolean c(String str, boolean z) {
        if (!z && (str == null || str.length() == 0)) {
            return false;
        }
        if (z) {
            j();
            return true;
        }
        try {
            ao.a(new File(d(str, z)));
            return true;
        } catch (Exception e) {
            f.a("BookmarkHandle", e.getMessage());
            return false;
        }
    }

    public void c(List<Mark> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            String id = ((Mark) list.get(i)).getId();
            if (!TextUtils.isEmpty(id)) {
                ao.a(new File(d(id, false)));
            }
        }
    }

    public LocalMark a(DownloadBookTask downloadBookTask) {
        String fullName = downloadBookTask.getFullName();
        Mark localMark = new LocalMark(fullName, downloadBookTask.getFilePath(), 0, 1, false);
        localMark.setAuthor(downloadBookTask.getAuthor());
        if (fullName != null && fullName.toLowerCase().endsWith(".txt")) {
            localMark.setEncoding(4);
        }
        localMark.setStartPoint(0);
        localMark.setStarPointStr(Mark.HEADPAGE_FLAG);
        localMark.setPercentStr("0.0%");
        localMark.setLastRead(false);
        localMark.setDescriptionStr(downloadBookTask.getDescription());
        localMark.setOperateTime(downloadBookTask.getCreateTime());
        localMark.setBookId(downloadBookTask.getId());
        localMark.setFinished(1);
        localMark.setCoverUrl(downloadBookTask.getImageURI());
        j.a().a(new com.qq.reader.common.monitor.a.a(String.valueOf(downloadBookTask.getId()), downloadBookTask.getNetChannelId()));
        a(localMark, true);
        return localMark;
    }

    private String d(String str, boolean z) {
        if (z) {
            return com.qq.reader.common.c.a.bg;
        }
        int hashCode = str.hashCode();
        String substring = str.substring(str.lastIndexOf("/") + 1);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(com.qq.reader.common.c.a.bi);
        stringBuffer.append(hashCode);
        stringBuffer.append(substring);
        if (!substring.endsWith(".m")) {
            stringBuffer.append(".m");
        }
        return stringBuffer.toString();
    }

    private byte[] b(Mark mark) {
        byte[] bytes;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ao.a(byteArrayOutputStream, 0);
            bytes = mark.getId().getBytes("UTF-8");
            ao.a(byteArrayOutputStream, bytes.length);
            byteArrayOutputStream.write(bytes);
            bytes = mark.getBookName().getBytes("UTF-8");
            ao.a(byteArrayOutputStream, bytes.length);
            byteArrayOutputStream.write(bytes);
            ao.a(byteArrayOutputStream, mark.getStartPoint());
            bytes = mark.getDataStr().getBytes("UTF-8");
            ao.a(byteArrayOutputStream, bytes.length);
            byteArrayOutputStream.write(bytes);
            bytes = mark.getPercentStr().getBytes("UTF-8");
            ao.a(byteArrayOutputStream, bytes.length);
            byteArrayOutputStream.write(bytes);
            bytes = mark.getDescriptionStr().getBytes("UTF-8");
            ao.a(byteArrayOutputStream, bytes.length);
            byteArrayOutputStream.write(bytes);
            bytes = mark.getAuthor().getBytes("UTF-8");
            ao.a(byteArrayOutputStream, bytes.length);
            byteArrayOutputStream.write(bytes);
            ao.a(byteArrayOutputStream, mark.getReadTime());
            ao.a(byteArrayOutputStream, mark.getEncoding());
            ao.a(byteArrayOutputStream, mark.getType());
            ao.a(byteArrayOutputStream, mark.getFileLength());
            ao.a(byteArrayOutputStream, mark.getChapterMarkLevel());
            bytes = byteArrayOutputStream.toByteArray();
            int length = bytes.length;
            bytes[0] = (byte) ((length >> 24) & 255);
            bytes[1] = (byte) ((length >> 16) & 255);
            bytes[2] = (byte) ((length >> 8) & 255);
            bytes[3] = (byte) (length & 255);
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            bytes = null;
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
            }
        } catch (Throwable th) {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e32) {
                    e32.printStackTrace();
                }
            }
        }
        return bytes;
    }

    private boolean a(String str, Mark[] markArr) {
        Throwable th;
        boolean z = false;
        if (!(str == null || str.length() == 0 || markArr == null)) {
            RandomAccessFile randomAccessFile = null;
            RandomAccessFile randomAccessFile2;
            try {
                File c = ao.c(str);
                if (c != null) {
                    randomAccessFile2 = new RandomAccessFile(c, "rw");
                    try {
                        for (Mark b : markArr) {
                            byte[] b2 = b(b);
                            if (b2 != null) {
                                randomAccessFile2.seek(randomAccessFile2.length());
                                randomAccessFile2.write(b2);
                            }
                        }
                        z = true;
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e2) {
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                        return z;
                    } catch (Throwable th2) {
                        th = th2;
                        if (randomAccessFile2 != null) {
                            try {
                                randomAccessFile2.close();
                            } catch (Exception e32) {
                                e32.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } else if (randomAccessFile != null) {
                    try {
                        randomAccessFile.close();
                    } catch (Exception e322) {
                        e322.printStackTrace();
                    }
                }
            } catch (Exception e4) {
                randomAccessFile2 = randomAccessFile;
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
                return z;
            } catch (Throwable th3) {
                th = th3;
                randomAccessFile2 = randomAccessFile;
                if (randomAccessFile2 != null) {
                    randomAccessFile2.close();
                }
                throw th;
            }
        }
        return z;
    }

    private List<Mark> k(String str) {
        List<Mark> list = null;
        if (!(str == null || str.length() == 0)) {
            try {
                list = a(new RandomAccessFile(ao.c(str), "r"));
            } catch (Exception e) {
                f.a("ReadBookmarks Exception : ", e.toString());
            }
        }
        return list;
    }

    private boolean a(int i, int i2) {
        if (i < i2) {
            return true;
        }
        ao.e();
        return false;
    }

    private final List<Mark> a(RandomAccessFile randomAccessFile) {
        List<Mark> arrayList = new ArrayList();
        if (randomAccessFile.length() == 0) {
            arrayList = null;
            if (randomAccessFile != null) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            int i = 1;
            while (true) {
                long filePointer = randomAccessFile.getFilePointer();
                int readInt = randomAccessFile.readInt();
                int readInt2 = randomAccessFile.readInt();
                if (a(readInt2, readInt)) {
                    int readInt3;
                    byte[] bArr = new byte[readInt2];
                    randomAccessFile.read(bArr, 0, readInt2);
                    String str = new String(bArr, "UTF-8");
                    readInt2 = randomAccessFile.readInt();
                    bArr = new byte[readInt2];
                    randomAccessFile.read(bArr, 0, readInt2);
                    String str2 = new String(bArr, "UTF-8");
                    long readLong = randomAccessFile.readLong();
                    readInt2 = randomAccessFile.readInt();
                    bArr = new byte[readInt2];
                    randomAccessFile.read(bArr, 0, readInt2);
                    String str3 = new String(bArr, "UTF-8");
                    readInt2 = randomAccessFile.readInt();
                    bArr = new byte[readInt2];
                    randomAccessFile.read(bArr, 0, readInt2);
                    String str4 = new String(bArr, "UTF-8");
                    readInt2 = randomAccessFile.readInt();
                    bArr = new byte[readInt2];
                    randomAccessFile.read(bArr, 0, readInt2);
                    str4 = new String(bArr, "UTF-8");
                    readInt2 = randomAccessFile.readInt();
                    bArr = new byte[readInt2];
                    randomAccessFile.read(bArr, 0, readInt2);
                    String str5 = new String(bArr, "UTF-8");
                    long readLong2 = randomAccessFile.readLong();
                    int readInt4 = randomAccessFile.readInt();
                    readInt2 = randomAccessFile.readInt();
                    long readLong3 = randomAccessFile.readLong();
                    if (randomAccessFile.getFilePointer() - filePointer == ((long) (readInt - 4))) {
                        readInt3 = randomAccessFile.readInt();
                    } else {
                        readInt3 = i;
                    }
                    randomAccessFile.seek(filePointer + ((long) readInt));
                    if (!(str == null || str2 == null || str3 == null)) {
                        switch (readInt2) {
                            case 0:
                                try {
                                    String str6 = str2;
                                    int i2 = readInt2;
                                    arrayList.add(new UserMark(0, str.substring(str.indexOf("/")), str6, 0, 0, readLong, i2, Long.parseLong(str.substring(0, str.indexOf("/"))), str4, str4));
                                    break;
                                } catch (Exception e2) {
                                    break;
                                }
                            case 1:
                            case 2:
                                try {
                                    arrayList.add(new LocalMark(readInt2, str, readLong3, str2, str3).setStartPoint(readLong).setPercentStr(str4).setDescriptionStr(str4).setAuthor(str5).setReadTime(readLong2).setEncoding(readInt4).setChapterMarkLevel(readInt3));
                                    break;
                                } catch (Exception e3) {
                                    f.a("readAll Exception : ", e3.toString());
                                    ao.e();
                                    arrayList = null;
                                    if (randomAccessFile != null) {
                                        try {
                                            randomAccessFile.close();
                                            break;
                                        } catch (IOException e4) {
                                            e4.printStackTrace();
                                            break;
                                        }
                                    }
                                } catch (Throwable th) {
                                    if (randomAccessFile != null) {
                                        try {
                                            randomAccessFile.close();
                                        } catch (IOException e42) {
                                            e42.printStackTrace();
                                        }
                                    }
                                }
                                break;
                            case 3:
                                arrayList.add(new DownloadMark(str, str2, false));
                                break;
                        }
                    }
                    if (randomAccessFile.getFilePointer() != randomAccessFile.length()) {
                        i = readInt3;
                    } else if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e422) {
                            e422.printStackTrace();
                        }
                    }
                } else {
                    arrayList = null;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (IOException e4222) {
                            e4222.printStackTrace();
                        }
                    }
                }
            }
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final long[] b(int r13, java.lang.String r14) {
        /*
        r12 = this;
        r4 = 0;
        r0 = 0;
        if (r13 > 0) goto L_0x0006;
    L_0x0005:
        return r0;
    L_0x0006:
        r2 = new java.io.RandomAccessFile;	 Catch:{ Exception -> 0x0051, all -> 0x0068 }
        r1 = com.qq.reader.common.utils.ao.c(r14);	 Catch:{ Exception -> 0x0051, all -> 0x0068 }
        r3 = "r";
        r2.<init>(r1, r3);	 Catch:{ Exception -> 0x0051, all -> 0x0068 }
        r6 = r4;
    L_0x0013:
        r1 = r13 + -1;
        if (r13 <= 0) goto L_0x003c;
    L_0x0017:
        r6 = r2.getFilePointer();	 Catch:{ Exception -> 0x0078 }
        r3 = r2.readInt();	 Catch:{ Exception -> 0x0078 }
        r4 = (long) r3;	 Catch:{ Exception -> 0x0078 }
        r8 = r6 + r4;
        r2.seek(r8);	 Catch:{ Exception -> 0x0078 }
        r8 = r2.getFilePointer();	 Catch:{ Exception -> 0x0078 }
        r10 = r2.length();	 Catch:{ Exception -> 0x0078 }
        r3 = (r8 > r10 ? 1 : (r8 == r10 ? 0 : -1));
        if (r3 != 0) goto L_0x007a;
    L_0x0031:
        if (r2 == 0) goto L_0x0005;
    L_0x0033:
        r2.close();	 Catch:{ IOException -> 0x0037 }
        goto L_0x0005;
    L_0x0037:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0005;
    L_0x003c:
        r1 = 2;
        r1 = new long[r1];	 Catch:{ Exception -> 0x0078 }
        r3 = 0;
        r1[r3] = r6;	 Catch:{ Exception -> 0x0078 }
        r3 = 1;
        r1[r3] = r4;	 Catch:{ Exception -> 0x0078 }
        if (r2 == 0) goto L_0x004a;
    L_0x0047:
        r2.close();	 Catch:{ IOException -> 0x004c }
    L_0x004a:
        r0 = r1;
        goto L_0x0005;
    L_0x004c:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x004a;
    L_0x0051:
        r1 = move-exception;
        r2 = r0;
    L_0x0053:
        r3 = "findPoint Exception : ";
        r1 = r1.toString();	 Catch:{ all -> 0x0076 }
        com.qq.reader.common.monitor.f.a(r3, r1);	 Catch:{ all -> 0x0076 }
        if (r2 == 0) goto L_0x0005;
    L_0x005f:
        r2.close();	 Catch:{ IOException -> 0x0063 }
        goto L_0x0005;
    L_0x0063:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0005;
    L_0x0068:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
    L_0x006b:
        if (r2 == 0) goto L_0x0070;
    L_0x006d:
        r2.close();	 Catch:{ IOException -> 0x0071 }
    L_0x0070:
        throw r0;
    L_0x0071:
        r1 = move-exception;
        r1.printStackTrace();
        goto L_0x0070;
    L_0x0076:
        r0 = move-exception;
        goto L_0x006b;
    L_0x0078:
        r1 = move-exception;
        goto L_0x0053;
    L_0x007a:
        r13 = r1;
        goto L_0x0013;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.i.b(int, java.lang.String):long[]");
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        File file = new File(com.qq.reader.common.c.a.ao);
        if (file.exists()) {
            for (File file2 : file.listFiles()) {
                if (file2.isFile() && file2.getAbsolutePath().endsWith(".m")) {
                    List<Mark> k = k(file2.getAbsolutePath());
                    if (k != null && k.size() > 0) {
                        Mark a = a(sQLiteDatabase, ((UserMark) k.get(0)).getId());
                        if (a != null) {
                            for (Mark mark : k) {
                                mark.setBookId(a.getBookId());
                                a(sQLiteDatabase, (UserMark) mark);
                            }
                        }
                    }
                    file2.delete();
                }
            }
        }
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists bookshelf (_id integer primary key autoincrement,path text not null,name text,startpoint text,date text,percent text,description text,author text,time text,add_time text,encoding integer default -1,type integer,length text, category integer default " + b + "," + "newcontent" + " integer default 0, " + "truepagebytes" + " float default 0," + "truepagefont" + " integer default 0," + "truepagecurpageindex" + " long default -1," + "book_id" + " long default 0," + "update_time" + " long default 0," + "update_chapter" + " text, " + "incloud" + " integer default 0," + "book_isfinished" + " integer default 0," + BaseApplication.DATA_KEY_CHANNEL_ID + " text," + "sortindex" + "  integer default 0," + "downloadinfo" + " text," + "cover_url" + " text," + "lastread_chapter" + " text," + "private_property" + " integer default 1," + "LimitFreeEndTime" + " text," + "cloud_del_tag" + " integer default 0," + "discount" + " integer default 100" + ");");
        sQLiteDatabase.execSQL("create table if not exists category (_id integer primary key autoincrement,category_name text not null);");
        ContentValues contentValues = new ContentValues();
        contentValues.put("category_name", BookShelfFragment.CATEGORY_ALL);
        a = (int) sQLiteDatabase.insert(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, null, contentValues);
        contentValues.put("category_name", "在线");
        c = (int) sQLiteDatabase.insert(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, null, contentValues);
        contentValues.put("category_name", "未分组");
        b = (int) sQLiteDatabase.insert(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, null, contentValues);
        c(sQLiteDatabase);
    }

    public ArrayList<Mark> d(String str) {
        ArrayList<Mark> arrayList = new ArrayList();
        File file = new File(am.b() + "/Tencent/ReaderZone/" + str + "/bkd/default.db");
        if (file.exists()) {
            List h = h();
            int size = h.size();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" where path not in (");
            if (size > 0) {
                for (int i = 0; i < size - 1; i++) {
                    stringBuilder.append('\'');
                    stringBuilder.append(((Mark) h.get(i)).getId());
                    stringBuilder.append('\'');
                    stringBuilder.append(",");
                }
                stringBuilder.append('\'');
                stringBuilder.append(((Mark) h.get(size - 1)).getId());
                stringBuilder.append('\'');
            }
            stringBuilder.append(");");
            SQLiteDatabase openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(file, null);
            Cursor rawQuery = openOrCreateDatabase.rawQuery("select * from bookshelf " + stringBuilder.toString(), null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.moveToFirst()) {
                        do {
                            String string = rawQuery.getString(rawQuery.getColumnIndex("path"));
                            String string2 = rawQuery.getString(rawQuery.getColumnIndex("name"));
                            String string3 = rawQuery.getString(rawQuery.getColumnIndex("startpoint"));
                            String string4 = rawQuery.getString(rawQuery.getColumnIndex(MessageKey.MSG_DATE));
                            String string5 = rawQuery.getString(rawQuery.getColumnIndex("percent"));
                            String string6 = rawQuery.getString(rawQuery.getColumnIndex(SocialConstants.PARAM_COMMENT));
                            String string7 = rawQuery.getString(rawQuery.getColumnIndex("author"));
                            String string8 = rawQuery.getString(rawQuery.getColumnIndex("time"));
                            String string9 = rawQuery.getString(rawQuery.getColumnIndex("add_time"));
                            int i2 = rawQuery.getInt(rawQuery.getColumnIndex("encoding"));
                            int i3 = rawQuery.getInt(rawQuery.getColumnIndex("type"));
                            String string10 = rawQuery.getString(rawQuery.getColumnIndex("length"));
                            int i4 = rawQuery.getInt(rawQuery.getColumnIndex(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE));
                            int i5 = rawQuery.getInt(rawQuery.getColumnIndex("newcontent"));
                            float f = rawQuery.getFloat(rawQuery.getColumnIndex("truepagebytes"));
                            int i6 = rawQuery.getInt(rawQuery.getColumnIndex("truepagefont"));
                            long j = rawQuery.getLong(rawQuery.getColumnIndex("truepagecurpageindex"));
                            long j2 = rawQuery.getLong(rawQuery.getColumnIndex("book_id"));
                            long j3 = rawQuery.getLong(rawQuery.getColumnIndex("update_time"));
                            String string11 = rawQuery.getString(rawQuery.getColumnIndex("update_chapter"));
                            String g = ao.g(rawQuery.getLong(rawQuery.getColumnIndex("book_id")));
                            LocalMark localMark = new LocalMark(i3, string, Long.valueOf(string10).longValue(), string2, string4);
                            localMark.setStarPointStr(string3);
                            localMark.setPercentStr(string5);
                            localMark.setDescriptionStr(string6);
                            localMark.setAuthor(string7);
                            localMark.setReadTime(Long.valueOf(string8).longValue());
                            localMark.setEncoding(i2);
                            localMark.setHasNewContent(i5 == 1);
                            localMark.setTurePageBytes(f);
                            localMark.setTurePageFont(i6);
                            localMark.setTurePageCurIndex(j);
                            localMark.setOperateTime(Long.valueOf(string9).longValue());
                            localMark.setBookId(j2);
                            localMark.setLastUpdateTime(j3);
                            localMark.setLastUpdateChapter(string11);
                            localMark.setCoverUrl(g);
                            localMark.setCategoryID(i4);
                            arrayList.add(localMark);
                        } while (rawQuery.moveToNext());
                    }
                } catch (Exception e) {
                    f.a("BookmarkHandle", "BookmarkHandle importBookShelfFromReaderZone " + e.toString());
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (openOrCreateDatabase != null) {
                        openOrCreateDatabase.close();
                    }
                } catch (Throwable th) {
                    if (rawQuery != null) {
                        rawQuery.close();
                    }
                    if (openOrCreateDatabase != null) {
                        openOrCreateDatabase.close();
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (openOrCreateDatabase != null) {
                openOrCreateDatabase.close();
            }
        }
        return arrayList;
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists usermark (_id integer primary key autoincrement,pathid text default '0',name text,startpoint int default 0,chapterid long default 0, chapteroffset long default 0 , percent text, description text, addtime long,type integer,book_id long default 0);");
    }

    private String[] s() {
        return new String[]{"_id", "pathid", "name", "startpoint", "chapterid", "chapteroffset", "percent", "addtime", "type", "book_id", SocialConstants.PARAM_COMMENT};
    }

    public synchronized boolean a(Mark[] markArr) {
        SQLiteDatabase sQLiteDatabase;
        Exception exception;
        Object obj;
        Throwable th;
        Mark mark;
        Map hashMap;
        boolean z;
        CharSequence c;
        Cursor cursor = null;
        Object obj2 = null;
        Object obj3;
        try {
            SQLiteDatabase a = f.a();
            try {
                int length = markArr.length;
                int i = 0;
                obj3 = null;
                Cursor cursor2 = cursor;
                while (i < length) {
                    long update;
                    CharSequence c2;
                    Mark mark2 = markArr[i];
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("path", mark2.getId());
                    contentValues.put("name", mark2.getBookName());
                    if (mark2.getStarPointStr() == null) {
                        contentValues.put("startpoint", String.valueOf(mark2.getStartPoint()));
                    } else {
                        try {
                            contentValues.put("startpoint", mark2.getStarPointStr());
                        } catch (Exception e) {
                            sQLiteDatabase = a;
                            cursor = cursor2;
                            exception = e;
                            obj = obj3;
                        } catch (Throwable th2) {
                            sQLiteDatabase = a;
                            th = th2;
                        }
                    }
                    contentValues.put(MessageKey.MSG_DATE, mark2.getDataStr());
                    contentValues.put("percent", mark2.getPercentStr());
                    contentValues.put(SocialConstants.PARAM_COMMENT, mark2.getDescriptionStr());
                    contentValues.put("author", mark2.getAuthor());
                    contentValues.put("time", String.valueOf(mark2.getReadTime()));
                    j.p = mark2.getReadTime();
                    contentValues.put("encoding", Integer.valueOf(mark2.getEncoding()));
                    contentValues.put("type", Integer.valueOf(mark2.getType()));
                    contentValues.put("length", String.valueOf(mark2.getFileLength()));
                    contentValues.put("newcontent", Integer.valueOf(mark2.hasNewContent() ? 1 : 0));
                    contentValues.put("truepagebytes", Float.valueOf(mark2.getTurePageBytes()));
                    contentValues.put("truepagefont", Integer.valueOf(mark2.getTurePageFont()));
                    contentValues.put("truepagecurpageindex", Long.valueOf(mark2.getTurePageCurIndex()));
                    contentValues.put("add_time", String.valueOf(mark2.getOperateTime()));
                    contentValues.put("book_id", String.valueOf(mark2.getBookId()));
                    if (mark2.getSynBook() == 1) {
                        contentValues.put("incloud", Integer.valueOf(1));
                    }
                    contentValues.put("downloadinfo", mark2.getDownloadInfo());
                    if (mark2.getLastUpdateTime() > 0) {
                        contentValues.put("update_time", Long.valueOf(mark2.getLastUpdateTime()));
                    }
                    String lastUpdateChapter = mark2.getLastUpdateChapter();
                    if (lastUpdateChapter != null && lastUpdateChapter.trim().length() > 0) {
                        contentValues.put("update_chapter", lastUpdateChapter);
                    }
                    contentValues.put("book_isfinished", Integer.valueOf(mark2.getIsFinish()));
                    contentValues.put(BaseApplication.DATA_KEY_CHANNEL_ID, mark2.getNetChannelId());
                    contentValues.put("sortindex", Integer.valueOf(mark2.getSortIndex()));
                    contentValues.put("cover_url", mark2.getImageURI());
                    contentValues.put("lastread_chapter", mark2.getLastReadChapterName());
                    contentValues.put("private_property", Integer.valueOf(mark2.getPrivateProperty()));
                    contentValues.put("LimitFreeEndTime", mark2.getLimitFreeEndTime());
                    contentValues.put("cloud_del_tag", Integer.valueOf(mark2.getCloudDelTag()));
                    contentValues.put("discount", Integer.valueOf(mark2.getDiscount()));
                    cursor = a.query("bookshelf", new String[]{"_id", "book_id"}, "path= '" + mark2.getId().replace("'", "''") + "'", null, null, null, null, null);
                    if (cursor != null) {
                        try {
                            if (cursor.getCount() > 0) {
                                cursor.moveToFirst();
                                int i2 = cursor.getInt(0);
                                String string = cursor.getString(1);
                                if (!"0".equals(string)) {
                                    contentValues.put("book_id", string);
                                }
                                update = (long) a.update("bookshelf", contentValues, "_id=" + i2, null);
                                obj2 = obj3;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                if (update >= 0) {
                                    if (a != null) {
                                        try {
                                            f.c();
                                        } catch (Exception e2) {
                                        }
                                    }
                                    if (obj2 != null) {
                                        com.qq.reader.common.exception.a.a().b(0);
                                    }
                                    c2 = com.qq.reader.common.login.c.c().c();
                                    if (!TextUtils.isEmpty(c2) && markArr.length > 0) {
                                        mark = markArr[0];
                                        if (mark.getBookId() > 0 && mark.getType() != 4) {
                                            hashMap = new HashMap();
                                            hashMap.put(Constants.SOURCE_QQ, c2);
                                            hashMap.put(BookClubReplyCard.BID, mark.getBookId() + "");
                                            hashMap.put("TYPE", "BookMark");
                                            com.qq.reader.common.monitor.i.a("event_save_read_mark_fail", hashMap, ReaderApplication.getApplicationImp());
                                        }
                                    }
                                    z = false;
                                } else {
                                    i++;
                                    obj3 = obj2;
                                    cursor2 = cursor;
                                }
                            }
                        } catch (Exception e3) {
                            sQLiteDatabase = a;
                            exception = e3;
                            obj = obj3;
                        } catch (Throwable th22) {
                            sQLiteDatabase = a;
                            th = th22;
                        }
                    }
                    obj2 = 1;
                    if (4 == mark2.getType()) {
                        contentValues.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, Integer.valueOf(c));
                    }
                    update = a.insert("bookshelf", null, contentValues);
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (update >= 0) {
                        i++;
                        obj3 = obj2;
                        cursor2 = cursor;
                    } else {
                        if (a != null) {
                            f.c();
                        }
                        if (obj2 != null) {
                            com.qq.reader.common.exception.a.a().b(0);
                        }
                        c2 = com.qq.reader.common.login.c.c().c();
                        mark = markArr[0];
                        hashMap = new HashMap();
                        hashMap.put(Constants.SOURCE_QQ, c2);
                        hashMap.put(BookClubReplyCard.BID, mark.getBookId() + "");
                        hashMap.put("TYPE", "BookMark");
                        com.qq.reader.common.monitor.i.a("event_save_read_mark_fail", hashMap, ReaderApplication.getApplicationImp());
                        z = false;
                    }
                }
                if (a != null) {
                    try {
                        f.c();
                    } catch (Exception e4) {
                    }
                }
                if (obj3 != null) {
                    com.qq.reader.common.exception.a.a().a(0);
                }
                z = true;
            } catch (Exception e32) {
                Exception exception2 = e32;
                obj = obj2;
                sQLiteDatabase = a;
                exception = exception2;
            } catch (Throwable th222) {
                obj3 = obj2;
                sQLiteDatabase = a;
                th = th222;
            }
        } catch (Exception e5) {
            exception = e5;
            sQLiteDatabase = null;
            obj = null;
            try {
                f.a("DB", "writeAutoMarkDB with exception : " + exception.getMessage());
                if (cursor != null) {
                    cursor.close();
                }
                z = false;
                if (sQLiteDatabase != null) {
                    try {
                        f.c();
                    } catch (Exception e6) {
                    }
                }
                if (obj != null) {
                    com.qq.reader.common.exception.a.a().b(0);
                }
                c = com.qq.reader.common.login.c.c().c();
                if (!TextUtils.isEmpty(c) && markArr.length > 0) {
                    mark = markArr[0];
                    if (mark.getBookId() > 0 && mark.getType() != 4) {
                        hashMap = new HashMap();
                        hashMap.put(Constants.SOURCE_QQ, c);
                        hashMap.put(BookClubReplyCard.BID, mark.getBookId() + "");
                        hashMap.put("TYPE", "BookMark");
                        com.qq.reader.common.monitor.i.a("event_save_read_mark_fail", hashMap, ReaderApplication.getApplicationImp());
                    }
                }
                return z;
            } catch (Throwable th3) {
                th = th3;
                obj3 = obj;
                if (sQLiteDatabase != null) {
                    try {
                        f.c();
                    } catch (Exception e7) {
                    }
                }
                if (obj3 != null) {
                    com.qq.reader.common.exception.a.a().b(0);
                }
                c = com.qq.reader.common.login.c.c().c();
                if (!TextUtils.isEmpty(c) && markArr.length > 0) {
                    mark = markArr[0];
                    if (mark.getBookId() > 0 && mark.getType() != 4) {
                        hashMap = new HashMap();
                        hashMap.put(Constants.SOURCE_QQ, c);
                        hashMap.put(BookClubReplyCard.BID, mark.getBookId() + "");
                        hashMap.put("TYPE", "BookMark");
                        com.qq.reader.common.monitor.i.a("event_save_read_mark_fail", hashMap, ReaderApplication.getApplicationImp());
                    }
                }
                throw th;
            }
        } catch (Throwable th4) {
            th = th4;
            obj3 = null;
            sQLiteDatabase = null;
            if (sQLiteDatabase != null) {
                f.c();
            }
            if (obj3 != null) {
                com.qq.reader.common.exception.a.a().b(0);
            }
            c = com.qq.reader.common.login.c.c().c();
            mark = markArr[0];
            hashMap = new HashMap();
            hashMap.put(Constants.SOURCE_QQ, c);
            hashMap.put(BookClubReplyCard.BID, mark.getBookId() + "");
            hashMap.put("TYPE", "BookMark");
            com.qq.reader.common.monitor.i.a("event_save_read_mark_fail", hashMap, ReaderApplication.getApplicationImp());
            throw th;
        }
        return z;
    }

    private synchronized boolean l(String str) {
        boolean z = false;
        synchronized (this) {
            int delete;
            try {
                delete = f.a().delete("bookshelf", "path= '" + str.replace("'", "''") + "'", null);
                f.c();
                com.qq.reader.common.exception.a.a().a(0);
            } catch (Exception e) {
                f.a("DB", "delAutoMarkDB with exception : " + e.getMessage());
                f.c();
                com.qq.reader.common.exception.a.a().b(0);
                delete = 0;
            } catch (Throwable th) {
                f.c();
                com.qq.reader.common.exception.a.a().b(0);
            }
            if (delete > 0) {
                z = true;
            }
        }
        return z;
    }

    public synchronized void j() {
        try {
            SQLiteDatabase a = f.a();
            a.execSQL("drop table if exists bookshelf");
            a(a);
            this.h.c();
            f.c();
        } catch (Exception e) {
            f.a("DB", "clearAllAutoMarkDB with exception : " + e.getMessage());
            f.c();
        } catch (Throwable th) {
            f.c();
        }
    }

    public synchronized Mark e(String str) {
        return this.h.d(str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    synchronized com.qq.reader.framework.mark.Mark a(android.database.sqlite.SQLiteDatabase r44, java.lang.String r45) {
        /*
        r43 = this;
        monitor-enter(r43);
        r11 = 0;
        r10 = 0;
        r2 = 30;
        r4 = new java.lang.String[r2];	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 0;
        r3 = "path";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 1;
        r3 = "name";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 2;
        r3 = "startpoint";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 3;
        r3 = "date";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 4;
        r3 = "percent";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 5;
        r3 = "description";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 6;
        r3 = "author";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 7;
        r3 = "time";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 8;
        r3 = "encoding";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 9;
        r3 = "type";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 10;
        r3 = "length";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 11;
        r3 = "category";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 12;
        r3 = "newcontent";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 13;
        r3 = "truepagebytes";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 14;
        r3 = "truepagefont";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 15;
        r3 = "truepagecurpageindex";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 16;
        r3 = "add_time";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 17;
        r3 = "book_id";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 18;
        r3 = "update_time";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 19;
        r3 = "update_chapter";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 20;
        r3 = "book_isfinished";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 21;
        r3 = "channel_id";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 22;
        r3 = "sortindex";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 23;
        r3 = "incloud";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 24;
        r3 = "downloadinfo";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 25;
        r3 = "lastread_chapter";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 26;
        r3 = "private_property";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 27;
        r3 = "LimitFreeEndTime";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 28;
        r3 = "cloud_del_tag";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = 29;
        r3 = "discount";
        r4[r2] = r3;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r3 = "bookshelf";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2.<init>();	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r5 = "path= '";
        r2 = r2.append(r5);	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r5 = "'";
        r6 = "''";
        r0 = r45;
        r5 = r0.replace(r5, r6);	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r2 = r2.append(r5);	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r5 = "'";
        r2 = r2.append(r5);	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r5 = r2.toString();	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r2 = r44;
        r10 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x038d, all -> 0x03b9 }
        if (r10 == 0) goto L_0x01da;
    L_0x0107:
        r2 = r10.getCount();	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        if (r2 <= 0) goto L_0x01da;
    L_0x010d:
        r10.moveToFirst();	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = 0;
        r5 = r10.getString(r2);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = 1;
        r8 = r10.getString(r2);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r6 = 0;
        r2 = 0;
        r3 = 2;
        r3 = r10.getString(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r6 = java.lang.Long.parseLong(r3);	 Catch:{ Exception -> 0x01e2, all -> 0x03b9 }
        r12 = r2;
        r14 = r6;
    L_0x0128:
        r2 = 3;
        r9 = r10.getString(r2);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = 4;
        r13 = r10.getString(r2);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = 5;
        r16 = r10.getString(r2);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = 6;
        r17 = r10.getString(r2);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = 7;
        r18 = r10.getLong(r2);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = 8;
        r20 = r10.getInt(r2);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = 9;
        r4 = r10.getInt(r2);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = 10;
        r6 = r10.getLong(r2);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = 11;
        r21 = r10.getInt(r2);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = 12;
        r2 = r10.getInt(r2);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        if (r2 != 0) goto L_0x01e7;
    L_0x0161:
        r2 = 0;
    L_0x0162:
        r3 = 13;
        r22 = r10.getFloat(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = 14;
        r23 = r10.getInt(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = 15;
        r24 = r10.getLong(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = 16;
        r26 = r10.getLong(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = 17;
        r28 = r10.getLong(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = 18;
        r30 = r10.getLong(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = 19;
        r32 = r10.getString(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = 20;
        r33 = r10.getInt(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = 21;
        r34 = r10.getString(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = 22;
        r35 = r10.getInt(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = 23;
        r36 = r10.getInt(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = 24;
        r37 = r10.getString(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = 25;
        r38 = r10.getString(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = "LimitFreeEndTime";
        r3 = r10.getColumnIndex(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r39 = r10.getString(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = "cloud_del_tag";
        r3 = r10.getColumnIndex(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r40 = r10.getInt(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = "discount";
        r3 = r10.getColumnIndex(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r41 = r10.getInt(r3);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        if (r5 == 0) goto L_0x01da;
    L_0x01d3:
        if (r8 == 0) goto L_0x01da;
    L_0x01d5:
        if (r9 == 0) goto L_0x01da;
    L_0x01d7:
        switch(r4) {
            case 1: goto L_0x0209;
            case 2: goto L_0x0209;
            case 3: goto L_0x01ea;
            case 4: goto L_0x0209;
            case 5: goto L_0x01da;
            case 6: goto L_0x01da;
            case 7: goto L_0x01da;
            case 8: goto L_0x029f;
            case 9: goto L_0x0316;
            default: goto L_0x01da;
        };
    L_0x01da:
        r2 = r11;
    L_0x01db:
        if (r10 == 0) goto L_0x01e0;
    L_0x01dd:
        r10.close();	 Catch:{ all -> 0x03b6 }
    L_0x01e0:
        monitor-exit(r43);
        return r2;
    L_0x01e2:
        r2 = move-exception;
        r12 = r3;
        r14 = r6;
        goto L_0x0128;
    L_0x01e7:
        r2 = 1;
        goto L_0x0162;
    L_0x01ea:
        r3 = new com.qq.reader.framework.mark.DownloadMark;	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = 0;
        r3.<init>(r5, r8, r2);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r21;
        r2 = r3.setCategoryID(r0);	 Catch:{ Exception -> 0x03c8, all -> 0x03b9 }
        r0 = r28;
        r2 = r2.setBookId(r0);	 Catch:{ Exception -> 0x03c8, all -> 0x03b9 }
        r0 = r34;
        r2 = r2.setNetChannel(r0);	 Catch:{ Exception -> 0x03c8, all -> 0x03b9 }
        r0 = r37;
        r2.setDownloadInfo(r0);	 Catch:{ Exception -> 0x03c8, all -> 0x03b9 }
        r2 = r3;
        goto L_0x01db;
    L_0x0209:
        r3 = new com.qq.reader.framework.mark.LocalMark;	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3.<init>(r4, r5, r6, r8, r9);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = r3.setStartPoint(r14);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = r3.setStarPointStr(r12);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3 = r3.setPercentStr(r13);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r16;
        r3 = r3.setDescriptionStr(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r17;
        r3 = r3.setAuthor(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r18;
        r3 = r3.setReadTime(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r20;
        r3 = r3.setEncoding(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r21;
        r3 = r3.setCategoryID(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = r3.setHasNewContent(r2);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r22;
        r2 = r2.setTurePageBytes(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r23;
        r2 = r2.setTurePageFont(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r24;
        r2 = r2.setTurePageCurIndex(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r26;
        r2 = r2.setOperateTime(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r28;
        r2 = r2.setBookId(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r30;
        r2 = r2.setLastUpdateTime(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r32;
        r2 = r2.setLastUpdateChapter(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r33;
        r2 = r2.setFinished(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r34;
        r2 = r2.setNetChannel(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r35;
        r2 = r2.setSortIndex(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r36;
        r2 = r2.setSynBook(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r37;
        r2 = r2.setDownloadInfo(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r38;
        r2 = r2.setLastReadChapterName(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r39;
        r2 = r2.setLimitFreeEndTime(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r41;
        r2 = r2.setDiscount(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r0 = r40;
        r11 = r2.setCloudDelTag(r0);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = r11;
        goto L_0x01db;
    L_0x029f:
        r3 = new com.qq.reader.qplugin.local.TingBookMark;	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r6 = java.lang.Long.parseLong(r5);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3.<init>(r6, r8);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = r3.setHasNewContent(r2);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r17;
        r2 = r2.setAuthor(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r18;
        r2 = r2.setReadTime(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r26;
        r2 = r2.setOperateTime(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r4 = java.lang.Long.parseLong(r5);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r2 = r2.setBookId(r4);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r26;
        r2 = r2.setOperateTime(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r28;
        r2 = r2.setBookId(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r30;
        r2 = r2.setLastUpdateTime(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r32;
        r2 = r2.setLastUpdateChapter(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r33;
        r2 = r2.setFinished(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r34;
        r2 = r2.setNetChannel(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r35;
        r2 = r2.setSortIndex(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r36;
        r2 = r2.setSynBook(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r37;
        r2 = r2.setDownloadInfo(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r38;
        r2 = r2.setLastReadChapterName(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r39;
        r2 = r2.setLimitFreeEndTime(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r41;
        r2 = r2.setDiscount(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r0 = r40;
        r2.setCloudDelTag(r0);	 Catch:{ Exception -> 0x03d0, all -> 0x03b9 }
        r2 = r3;
        goto L_0x01db;
    L_0x0316:
        r3 = new com.qq.reader.module.comic.mark.ComicBookMark;	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r6 = java.lang.Long.parseLong(r5);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r3.<init>(r6, r8);	 Catch:{ Exception -> 0x03c3, all -> 0x03b9 }
        r2 = r3.setHasNewContent(r2);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r17;
        r2 = r2.setAuthor(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r18;
        r2 = r2.setReadTime(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r26;
        r2 = r2.setOperateTime(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r4 = java.lang.Long.parseLong(r5);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r2 = r2.setBookId(r4);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r26;
        r2 = r2.setOperateTime(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r28;
        r2 = r2.setBookId(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r30;
        r2 = r2.setLastUpdateTime(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r32;
        r2 = r2.setLastUpdateChapter(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r33;
        r2 = r2.setFinished(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r34;
        r2 = r2.setNetChannel(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r35;
        r2 = r2.setSortIndex(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r36;
        r2 = r2.setSynBook(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r37;
        r2 = r2.setDownloadInfo(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r38;
        r2 = r2.setLastReadChapterName(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r39;
        r2 = r2.setLimitFreeEndTime(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r41;
        r2 = r2.setDiscount(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r0 = r40;
        r2.setCloudDelTag(r0);	 Catch:{ Exception -> 0x03d8, all -> 0x03b9 }
        r2 = r3;
        goto L_0x01db;
    L_0x038d:
        r2 = move-exception;
        r3 = r2;
        r4 = r10;
        r2 = r11;
    L_0x0391:
        r5 = "BookmarkHandle";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x03c0 }
        r6.<init>();	 Catch:{ all -> 0x03c0 }
        r7 = "BookmarkHandle getMarkByIdDB2 ";
        r6 = r6.append(r7);	 Catch:{ all -> 0x03c0 }
        r3 = r3.toString();	 Catch:{ all -> 0x03c0 }
        r3 = r6.append(r3);	 Catch:{ all -> 0x03c0 }
        r3 = r3.toString();	 Catch:{ all -> 0x03c0 }
        com.qq.reader.common.monitor.f.a(r5, r3);	 Catch:{ all -> 0x03c0 }
        if (r4 == 0) goto L_0x01e0;
    L_0x03b1:
        r4.close();	 Catch:{ all -> 0x03b6 }
        goto L_0x01e0;
    L_0x03b6:
        r2 = move-exception;
        monitor-exit(r43);
        throw r2;
    L_0x03b9:
        r2 = move-exception;
    L_0x03ba:
        if (r10 == 0) goto L_0x03bf;
    L_0x03bc:
        r10.close();	 Catch:{ all -> 0x03b6 }
    L_0x03bf:
        throw r2;	 Catch:{ all -> 0x03b6 }
    L_0x03c0:
        r2 = move-exception;
        r10 = r4;
        goto L_0x03ba;
    L_0x03c3:
        r2 = move-exception;
        r3 = r2;
        r4 = r10;
        r2 = r11;
        goto L_0x0391;
    L_0x03c8:
        r2 = move-exception;
        r4 = r10;
        r42 = r2;
        r2 = r3;
        r3 = r42;
        goto L_0x0391;
    L_0x03d0:
        r2 = move-exception;
        r4 = r10;
        r42 = r2;
        r2 = r3;
        r3 = r42;
        goto L_0x0391;
    L_0x03d8:
        r2 = move-exception;
        r4 = r10;
        r42 = r2;
        r2 = r3;
        r3 = r42;
        goto L_0x0391;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.i.a(android.database.sqlite.SQLiteDatabase, java.lang.String):com.qq.reader.framework.mark.Mark");
    }

    public synchronized Mark f(String str) {
        Mark mark;
        mark = null;
        try {
            mark = a(f.a(), str);
            f.c();
        } catch (Exception e) {
            f.a("BookmarkHandle", "BookmarkHandle getMarkByIdDB " + e.toString());
            f.c();
        } catch (Throwable th) {
            f.c();
        }
        return mark;
    }

    public synchronized boolean g(String str) {
        boolean z = false;
        synchronized (this) {
            if (!(str == null || str.length() == 0)) {
                try {
                    SQLiteDatabase a = f.a();
                    if (a != null) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("category_name", str.trim());
                        if (a.insert(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, null, contentValues) > 0) {
                            z = true;
                        }
                    }
                } catch (Exception e) {
                    f.a("DB", "error in insert category: " + e.toString());
                } finally {
                    f.c();
                }
            }
        }
        return z;
    }

    public synchronized int h(String str) {
        Cursor cursor = null;
        int i = -1;
        synchronized (this) {
            if (str != null) {
                if (str.length() != 0) {
                    try {
                        cursor = f.a().rawQuery("select _id from category where category_name= '" + str.replace("'", "''") + "'", null);
                        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                            i = cursor.getInt(0);
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        f.c();
                    } catch (Exception e) {
                        f.a("BookmarkHandle", "error in getCategoryIDByName : " + e.toString());
                        if (cursor != null) {
                            cursor.close();
                        }
                        f.c();
                    } catch (Throwable th) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        f.c();
                    }
                }
            }
        }
        return i;
    }

    public synchronized int a(int i) {
        Cursor cursor = null;
        int i2 = 0;
        synchronized (this) {
            if (i >= 0) {
                try {
                    cursor = f.a().rawQuery("select path from bookshelf where category=" + i, null);
                    if (cursor != null) {
                        i2 = cursor.getCount();
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    f.c();
                } catch (Exception e) {
                    f.a("BookmarkHandle", "error in getCategoryCountByID : " + e.toString());
                    if (cursor != null) {
                        cursor.close();
                    }
                    f.c();
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    f.c();
                }
            }
        }
        return i2;
    }

    public synchronized boolean i(String str) {
        boolean z;
        Cursor cursor = null;
        synchronized (this) {
            if (str != null) {
                if (str.length() != 0) {
                    try {
                        SQLiteDatabase a = f.a();
                        a.execSQL("update bookshelf set category = " + b + " where " + "bookshelf" + "." + ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE + " in " + " (select " + ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE + "." + "_id" + " from " + ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE + " where " + ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE + "." + "category_name" + "= '" + str.replace("'", "''") + "'" + ")");
                        z = a.delete(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, new StringBuilder().append("category_name= '").append(str.replace("'", "''")).append("'").toString(), null) > 0;
                        cursor = a.rawQuery("select _id from category where category_name= '" + str.replace("'", "''") + "'", null);
                        if (cursor != null && cursor.getCount() > 0 && cursor.moveToFirst()) {
                            this.h.a(cursor.getInt(0));
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        f.c();
                    } catch (Exception e) {
                        f.a("BookmarkHandle", "error in delete category: " + e.toString());
                        if (cursor != null) {
                            cursor.close();
                        }
                        f.c();
                        z = false;
                    } catch (Throwable th) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        f.c();
                    }
                }
            }
            z = false;
        }
        return z;
    }

    public synchronized boolean a(String str, String str2) {
        boolean z = false;
        synchronized (this) {
            if (!(str == null || str.length() == 0 || str2 == null || str2.length() == 0)) {
                try {
                    SQLiteDatabase a = f.a();
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("category_name", str2);
                    if (a.update(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, contentValues, "category_name= '" + str.replace("'", "''") + "'", null) > 0) {
                        z = true;
                    }
                } catch (Exception e) {
                    f.a("BookmarkHandle", "error in update : " + e.toString());
                } finally {
                    f.c();
                }
            }
        }
        return z;
    }

    public synchronized boolean a(String str, long j, String str2, boolean z, int i) {
        SQLiteDatabase a;
        boolean z2;
        Exception e;
        Throwable th;
        if (str != null) {
            if (str.length() != 0) {
                int i2 = z ? 1 : 0;
                SQLiteDatabase sQLiteDatabase = null;
                try {
                    a = f.a();
                    Object obj = null;
                    try {
                        ContentValues contentValues = new ContentValues();
                        long j2 = 0;
                        if (z) {
                            obj = 1;
                            contentValues.put("newcontent", Integer.valueOf(i2));
                            j2 = System.currentTimeMillis();
                            contentValues.put("add_time", Long.valueOf(j2));
                        }
                        Object obj2 = obj;
                        if (j > 0) {
                            obj2 = 1;
                            contentValues.put("update_time", Long.valueOf(j));
                        }
                        if (str2 != null && str2.trim().length() > 0) {
                            obj2 = 1;
                            contentValues.put("update_chapter", str2);
                        }
                        contentValues.put("book_isfinished", Integer.valueOf(i));
                        if (obj2 != null) {
                            a.update("bookshelf", contentValues, "path=" + str.replace("'", "''"), null);
                            this.h.a(str, j2, j, str2, z, i);
                        }
                        z2 = true;
                        if (a != null) {
                            f.c();
                        }
                    } catch (Exception e2) {
                        e = e2;
                        sQLiteDatabase = a;
                        try {
                            f.a("BookmarkHandle", "error in updateBookNewContentState : " + e.toString());
                            z2 = false;
                            if (sQLiteDatabase != null) {
                                f.c();
                            }
                            return z2;
                        } catch (Throwable th2) {
                            th = th2;
                            a = sQLiteDatabase;
                            if (a != null) {
                                f.c();
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (a != null) {
                            f.c();
                        }
                        throw th;
                    }
                } catch (Exception e3) {
                    e = e3;
                    f.a("BookmarkHandle", "error in updateBookNewContentState : " + e.toString());
                    z2 = false;
                    if (sQLiteDatabase != null) {
                        f.c();
                    }
                    return z2;
                } catch (Throwable th4) {
                    th = th4;
                    a = null;
                    if (a != null) {
                        f.c();
                    }
                    throw th;
                }
            }
        }
        z2 = false;
        return z2;
    }

    public synchronized int b(int i) {
        Cursor cursor = null;
        int i2 = 0;
        synchronized (this) {
            if (i >= 0) {
                try {
                    cursor = f.a().rawQuery("select path from bookshelf where sortindex=" + i, null);
                    if (cursor != null) {
                        i2 = cursor.getCount();
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    f.c();
                } catch (Exception e) {
                    f.a("BookmarkHandle", "error in getSortIndexCount : " + e.toString());
                    if (cursor != null) {
                        cursor.close();
                    }
                    f.c();
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    f.c();
                }
            }
        }
        return i2;
    }

    public synchronized boolean a(String str, int i) {
        boolean z;
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            try {
                sQLiteDatabase = f.a();
                ContentValues contentValues = new ContentValues();
                contentValues.put("sortindex", Integer.valueOf(i));
                sQLiteDatabase.update("bookshelf", contentValues, "path=" + ("'" + str.replace("'", "''") + "'"), null);
                z = true;
                if (sQLiteDatabase != null) {
                    f.c();
                }
            } catch (Exception e) {
                f.a("BookmarkHandle", "error in updateMarkSortIndex : " + e.toString());
                z = false;
                if (sQLiteDatabase != null) {
                    f.c();
                }
            } catch (Throwable th) {
                if (sQLiteDatabase != null) {
                    f.c();
                }
            }
        }
        return z;
    }

    public synchronized boolean b(String str, int i) {
        boolean z;
        SQLiteDatabase sQLiteDatabase = null;
        synchronized (this) {
            try {
                this.h.b(str, i);
                sQLiteDatabase = f.a();
                ContentValues contentValues = new ContentValues();
                contentValues.put("private_property", Integer.valueOf(i));
                sQLiteDatabase.update("bookshelf", contentValues, "path=" + ("'" + str.replace("'", "''") + "'"), null);
                z = true;
                if (sQLiteDatabase != null) {
                    f.c();
                }
            } catch (Exception e) {
                f.a("BookmarkHandle", "error in updateMarkSortIndex : " + e.toString());
                z = false;
                if (sQLiteDatabase != null) {
                    f.c();
                }
            } catch (Throwable th) {
                if (sQLiteDatabase != null) {
                    f.c();
                }
            }
        }
        return z;
    }

    public synchronized boolean c(String str, int i) {
        boolean z;
        if (str != null) {
            if (str.length() != 0) {
                this.h.a(str, i);
                g.a().a(new BookmarkHandle$2(this, str, i));
                z = true;
            }
        }
        z = false;
        return z;
    }

    public synchronized boolean d(String str, int i) {
        boolean z = false;
        synchronized (this) {
            if (!(str == null || str.length() == 0 || i < 0)) {
                try {
                    this.h.c(str, i);
                    f.a().execSQL("update bookshelf set category = " + i + " where " + "bookshelf" + "." + "path" + "= '" + str.replace("'", "''") + "'");
                    z = true;
                } catch (Exception e) {
                    f.a("BookmarkHandle", "error in updateBookMarkCategory : " + e.toString());
                } finally {
                    f.c();
                }
            }
        }
        return z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.qq.reader.view.metro.MetroItem k() {
        /*
        r5 = this;
        monitor-enter(r5);
        r1 = r5.l();	 Catch:{ all -> 0x004f }
        if (r1 == 0) goto L_0x004d;
    L_0x0007:
        r0 = r1.size();	 Catch:{ all -> 0x004f }
        if (r0 <= 0) goto L_0x004d;
    L_0x000d:
        r0 = 0;
        r0 = r1.get(r0);	 Catch:{ all -> 0x004f }
        r0 = (com.qq.reader.view.metro.MetroItem) r0;	 Catch:{ all -> 0x004f }
        r2 = r1.iterator();	 Catch:{ all -> 0x004f }
        r1 = r0;
    L_0x0019:
        r0 = r2.hasNext();	 Catch:{ all -> 0x004f }
        if (r0 == 0) goto L_0x0031;
    L_0x001f:
        r0 = r2.next();	 Catch:{ all -> 0x004f }
        r0 = (com.qq.reader.view.metro.MetroItem) r0;	 Catch:{ all -> 0x004f }
        r3 = r0.getId();	 Catch:{ all -> 0x004f }
        r4 = r1.getId();	 Catch:{ all -> 0x004f }
        if (r3 <= r4) goto L_0x0052;
    L_0x002f:
        r1 = r0;
        goto L_0x0019;
    L_0x0031:
        if (r1 == 0) goto L_0x004d;
    L_0x0033:
        r0 = r1.getId();	 Catch:{ all -> 0x004f }
        r2 = a;	 Catch:{ all -> 0x004f }
        if (r0 == r2) goto L_0x004d;
    L_0x003b:
        r0 = r1.getId();	 Catch:{ all -> 0x004f }
        r2 = b;	 Catch:{ all -> 0x004f }
        if (r0 == r2) goto L_0x004d;
    L_0x0043:
        r0 = r1.getId();	 Catch:{ all -> 0x004f }
        r2 = c;	 Catch:{ all -> 0x004f }
        if (r0 == r2) goto L_0x004d;
    L_0x004b:
        monitor-exit(r5);
        return r1;
    L_0x004d:
        r1 = 0;
        goto L_0x004b;
    L_0x004f:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x0052:
        r0 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.i.k():com.qq.reader.view.metro.MetroItem");
    }

    public synchronized ArrayList<MetroItem> l() {
        ArrayList<MetroItem> arrayList;
        Cursor query;
        SQLiteException e;
        Throwable th;
        arrayList = new ArrayList();
        try {
            query = f.a().query(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, new String[]{"_id", "category_name"}, null, null, null, null, null);
            if (query != null) {
                if (query.getCount() > 0 && query.moveToFirst()) {
                    do {
                        int i = query.getInt(0);
                        String trim = query.getString(1).trim();
                        if (trim.equalsIgnoreCase(BookShelfFragment.CATEGORY_ALL)) {
                            a = i;
                        } else if (trim.equalsIgnoreCase("未分组")) {
                            b = i;
                        } else {
                            try {
                                if (trim.equalsIgnoreCase("在线")) {
                                    c = i;
                                }
                            } catch (SQLiteException e2) {
                                e = e2;
                            }
                        }
                        arrayList.add(new MetroItem(i, trim));
                    } while (query.moveToNext());
                }
            }
            if (query != null) {
                query.close();
            }
            f.c();
        } catch (SQLiteException e3) {
            e = e3;
            query = null;
            try {
                f.a("DB", "getAllCategoryDB with exception : " + e.getMessage());
                if (query != null) {
                    query.close();
                }
                f.c();
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                f.c();
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            f.c();
            throw th;
        }
        return arrayList;
    }

    public synchronized List<Mark> c(int i) {
        List<Mark> arrayList;
        Throwable th;
        Exception e;
        Cursor cursor;
        arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            SQLiteDatabase a = f.a();
            String str = "category = " + i;
            if (i == a) {
                str = null;
            }
            cursor2 = a.query("bookshelf", new String[]{"path", "name", "startpoint", MessageKey.MSG_DATE, "percent", SocialConstants.PARAM_COMMENT, "author", "time", "encoding", "type", "length", ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, "newcontent", "truepagebytes", "truepagefont", "truepagecurpageindex", "add_time", "book_id", "update_time", "update_chapter", "book_isfinished", BaseApplication.DATA_KEY_CHANNEL_ID, "sortindex", "cover_url"}, str, null, null, null, "sortindex DESC,add_time DESC");
            if (cursor2.moveToFirst()) {
                do {
                    String str2;
                    long parseLong;
                    str = cursor2.getString(0);
                    String string = cursor2.getString(1);
                    long j = 0;
                    String string2 = cursor2.getString(2);
                    try {
                        str2 = null;
                        parseLong = Long.parseLong(string2);
                    } catch (Exception e2) {
                        str2 = string2;
                        parseLong = j;
                    } catch (Throwable th2) {
                        th = th2;
                        break;
                    }
                    String string3 = cursor2.getString(3);
                    String string4 = cursor2.getString(4);
                    String string5 = cursor2.getString(5);
                    String string6 = cursor2.getString(6);
                    long j2 = cursor2.getLong(7);
                    int i2 = cursor2.getInt(8);
                    int i3 = cursor2.getInt(9);
                    j = cursor2.getLong(10);
                    int i4 = cursor2.getInt(11);
                    boolean z = cursor2.getInt(12) != 0;
                    float f = cursor2.getFloat(13);
                    int i5 = cursor2.getInt(14);
                    long j3 = cursor2.getLong(15);
                    long j4 = cursor2.getLong(16);
                    long j5 = cursor2.getLong(17);
                    long j6 = cursor2.getLong(18);
                    String string7 = cursor2.getString(19);
                    int i6 = cursor2.getInt(20);
                    String string8 = cursor2.getString(21);
                    int i7 = cursor2.getInt(22);
                    String string9 = cursor2.getString(23);
                    if (!(str == null || string == null || string3 == null)) {
                        Mark tingBookMark;
                        switch (i3) {
                            case 1:
                            case 2:
                            case 4:
                                arrayList.add(new LocalMark(i3, str, j, string, string3).setStartPoint(parseLong).setStarPointStr(str2).setPercentStr(string4).setDescriptionStr(string5).setAuthor(string6).setReadTime(j2).setEncoding(i2).setCategoryID(i4).setHasNewContent(z).setTurePageBytes(f).setTurePageFont(i5).setTurePageCurIndex(j3).setOperateTime(j4).setBookId(j5).setLastUpdateTime(j6).setLastUpdateChapter(string7).setFinished(i6).setNetChannel(string8).setSortIndex(i7).setCoverUrl(string9));
                                break;
                            case 3:
                                Mark downloadMark = new DownloadMark(str, string, false);
                                downloadMark.setCategoryID(i4);
                                downloadMark.setBookId(j5);
                                downloadMark.setCoverUrl(string9);
                                downloadMark.setAuthor(string6);
                                downloadMark.setPercentStr(string4);
                                downloadMark.setReadTime(j2);
                                arrayList.add(downloadMark);
                                break;
                            case 8:
                                try {
                                    tingBookMark = new TingBookMark(Long.parseLong(str), string);
                                    tingBookMark.setHasNewContent(z).setAuthor(string6).setReadTime(j2).setOperateTime(j4).setBookId(Long.parseLong(str)).setCategoryID(i4).setLastUpdateTime(j6).setLastUpdateChapter(string7).setNetChannel(string8).setSortIndex(i7).setCoverUrl(string9);
                                    arrayList.add(tingBookMark);
                                    break;
                                } catch (Exception e3) {
                                    e = e3;
                                    cursor = cursor2;
                                    break;
                                } catch (Throwable th22) {
                                    th = th22;
                                    break;
                                }
                            case 9:
                                tingBookMark = new ComicBookMark(Long.parseLong(str), string);
                                tingBookMark.setHasNewContent(z).setAuthor(string6).setReadTime(j2).setOperateTime(j4).setBookId(Long.parseLong(str)).setCategoryID(i4).setLastUpdateTime(j6).setLastUpdateChapter(string7).setNetChannel(string8).setSortIndex(i7).setCoverUrl(string9);
                                arrayList.add(tingBookMark);
                                break;
                        }
                    }
                } while (cursor2.moveToNext());
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            f.c();
        } catch (Exception e4) {
            e = e4;
            cursor = cursor2;
            try {
                f.a("DB", "getAllAutoMarkDB with exception: " + e.toString());
                if (cursor != null) {
                    cursor.close();
                }
                f.c();
                return arrayList;
            } catch (Throwable th3) {
                th = th3;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                f.c();
                throw th;
            }
        } catch (Throwable th222) {
            th = th222;
            break;
        }
        return arrayList;
    }

    public synchronized void m() {
        try {
            f.a();
            f.c();
        } catch (Exception e) {
            f.a("DB", "BookmarkHandle checkDBUpdate with exception: " + e.toString());
            f.c();
        } catch (Throwable th) {
            f.c();
        }
    }

    public synchronized List<String> n() {
        List<String> arrayList;
        Exception e;
        Throwable th;
        arrayList = new ArrayList();
        Cursor query;
        try {
            query = f.a().query("bookshelf", new String[]{"path"}, "type = 4", null, null, null, "add_time DESC");
            try {
                if (query.moveToFirst()) {
                    do {
                        String string = query.getString(0);
                        if (string != null) {
                            arrayList.add(string);
                        }
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
                f.c();
            } catch (Exception e2) {
                e = e2;
                try {
                    f.a("DB", "getAllAutoMarkDB with exception: " + e.toString());
                    if (query != null) {
                        query.close();
                    }
                    f.c();
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    f.c();
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            f.a("DB", "getAllAutoMarkDB with exception: " + e.toString());
            if (query != null) {
                query.close();
            }
            f.c();
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            f.c();
            throw th;
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.ArrayList<com.qq.reader.framework.mark.Mark> o() {
        /*
        r38 = this;
        monitor-enter(r38);
        r13 = new java.util.ArrayList;	 Catch:{ all -> 0x0292 }
        r13.<init>();	 Catch:{ all -> 0x0292 }
        r10 = 0;
        r2 = f;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r2 = r2.a();	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 25;
        r4 = new java.lang.String[r3];	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 0;
        r5 = "path";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 1;
        r5 = "name";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 2;
        r5 = "startpoint";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 3;
        r5 = "date";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 4;
        r5 = "percent";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 5;
        r5 = "description";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 6;
        r5 = "author";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 7;
        r5 = "time";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 8;
        r5 = "encoding";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 9;
        r5 = "type";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 10;
        r5 = "length";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 11;
        r5 = "category";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 12;
        r5 = "newcontent";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 13;
        r5 = "truepagebytes";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 14;
        r5 = "truepagefont";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 15;
        r5 = "truepagecurpageindex";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 16;
        r5 = "add_time";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 17;
        r5 = "book_id";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 18;
        r5 = "update_time";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 19;
        r5 = "update_chapter";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 20;
        r5 = "book_isfinished";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 21;
        r5 = "channel_id";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 22;
        r5 = "sortindex";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 23;
        r5 = "cover_url";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = 24;
        r5 = "incloud";
        r4[r3] = r5;	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r3 = "bookshelf";
        r5 = "incloud = 1";
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = "add_time DESC";
        r10 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x02a4, all -> 0x0295 }
        r2 = r10.moveToFirst();	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        if (r2 == 0) goto L_0x01fa;
    L_0x00ce:
        r2 = 0;
        r5 = r10.getString(r2);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r2 = 1;
        r8 = r10.getString(r2);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r6 = 0;
        r2 = 0;
        r3 = 2;
        r3 = r10.getString(r3);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r6 = java.lang.Long.parseLong(r3);	 Catch:{ Exception -> 0x0206, all -> 0x0295 }
        r12 = r2;
        r14 = r6;
    L_0x00e6:
        r2 = 3;
        r9 = r10.getString(r2);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r2 = 4;
        r16 = r10.getString(r2);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r2 = 5;
        r17 = r10.getString(r2);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r2 = 6;
        r18 = r10.getString(r2);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r2 = 7;
        r20 = r10.getLong(r2);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r2 = 8;
        r19 = r10.getInt(r2);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r2 = 9;
        r4 = r10.getInt(r2);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r2 = 10;
        r6 = r10.getLong(r2);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r2 = 11;
        r22 = r10.getInt(r2);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r2 = 12;
        r2 = r10.getInt(r2);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        if (r2 != 0) goto L_0x020b;
    L_0x011f:
        r2 = 0;
        r11 = r2;
    L_0x0121:
        r2 = 13;
        r2 = r10.getFloat(r2);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = 14;
        r23 = r10.getInt(r3);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = 15;
        r24 = r10.getLong(r3);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = 16;
        r26 = r10.getLong(r3);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = 17;
        r28 = r10.getLong(r3);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = 18;
        r30 = r10.getLong(r3);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = 19;
        r32 = r10.getString(r3);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = 20;
        r33 = r10.getInt(r3);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = 21;
        r34 = r10.getString(r3);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = 22;
        r35 = r10.getInt(r3);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = 23;
        r36 = r10.getString(r3);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = 24;
        r37 = r10.getInt(r3);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        if (r5 == 0) goto L_0x01f4;
    L_0x016b:
        if (r8 == 0) goto L_0x01f4;
    L_0x016d:
        if (r9 == 0) goto L_0x01f4;
    L_0x016f:
        r3 = 8;
        if (r4 == r3) goto L_0x01f4;
    L_0x0173:
        switch(r4) {
            case 9: goto L_0x020f;
            default: goto L_0x0176;
        };	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
    L_0x0176:
        r3 = new com.qq.reader.framework.mark.LocalMark;	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3.<init>(r4, r5, r6, r8, r9);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = r3.setStartPoint(r14);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = r3.setStarPointStr(r12);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r16;
        r3 = r3.setPercentStr(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r17;
        r3 = r3.setDescriptionStr(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r18;
        r3 = r3.setAuthor(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r20;
        r3 = r3.setReadTime(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r19;
        r3 = r3.setEncoding(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r22;
        r3 = r3.setCategoryID(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = r3.setHasNewContent(r11);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r2 = r3.setTurePageBytes(r2);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r23;
        r2 = r2.setTurePageFont(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r24;
        r2 = r2.setTurePageCurIndex(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r26;
        r2 = r2.setOperateTime(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r28;
        r2 = r2.setBookId(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r30;
        r2 = r2.setLastUpdateTime(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r32;
        r2 = r2.setLastUpdateChapter(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r33;
        r2 = r2.setFinished(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r34;
        r2 = r2.setNetChannel(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r35;
        r2 = r2.setSortIndex(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r36;
        r2 = r2.setCoverUrl(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r37;
        r2 = r2.setSynBook(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
    L_0x01f1:
        r13.add(r2);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
    L_0x01f4:
        r2 = r10.moveToNext();	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        if (r2 != 0) goto L_0x00ce;
    L_0x01fa:
        if (r10 == 0) goto L_0x01ff;
    L_0x01fc:
        r10.close();	 Catch:{ all -> 0x0292 }
    L_0x01ff:
        r2 = f;	 Catch:{ all -> 0x0292 }
        r2.c();	 Catch:{ all -> 0x0292 }
    L_0x0204:
        monitor-exit(r38);
        return r13;
    L_0x0206:
        r2 = move-exception;
        r12 = r3;
        r14 = r6;
        goto L_0x00e6;
    L_0x020b:
        r2 = 1;
        r11 = r2;
        goto L_0x0121;
    L_0x020f:
        r2 = new com.qq.reader.module.comic.mark.ComicBookMark;	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r6 = java.lang.Long.parseLong(r5);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r2.<init>(r6, r8);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = r2.setHasNewContent(r11);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r18;
        r3 = r3.setAuthor(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r20;
        r3 = r3.setReadTime(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r26;
        r3 = r3.setOperateTime(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r4 = java.lang.Long.parseLong(r5);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r3 = r3.setBookId(r4);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r28;
        r3 = r3.setBookId(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r30;
        r3 = r3.setLastUpdateTime(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r32;
        r3 = r3.setLastUpdateChapter(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r22;
        r3 = r3.setCategoryID(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r33;
        r3 = r3.setFinished(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r34;
        r3 = r3.setNetChannel(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r35;
        r3 = r3.setSortIndex(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        r0 = r37;
        r3.setSynBook(r0);	 Catch:{ Exception -> 0x0266, all -> 0x0295 }
        goto L_0x01f1;
    L_0x0266:
        r2 = move-exception;
        r3 = r10;
    L_0x0268:
        r4 = "DB";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x02a1 }
        r5.<init>();	 Catch:{ all -> 0x02a1 }
        r6 = "getAllAutoMarkDB with exception: ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x02a1 }
        r2 = r2.toString();	 Catch:{ all -> 0x02a1 }
        r2 = r5.append(r2);	 Catch:{ all -> 0x02a1 }
        r2 = r2.toString();	 Catch:{ all -> 0x02a1 }
        com.qq.reader.common.monitor.f.a(r4, r2);	 Catch:{ all -> 0x02a1 }
        if (r3 == 0) goto L_0x028b;
    L_0x0288:
        r3.close();	 Catch:{ all -> 0x0292 }
    L_0x028b:
        r2 = f;	 Catch:{ all -> 0x0292 }
        r2.c();	 Catch:{ all -> 0x0292 }
        goto L_0x0204;
    L_0x0292:
        r2 = move-exception;
        monitor-exit(r38);
        throw r2;
    L_0x0295:
        r2 = move-exception;
    L_0x0296:
        if (r10 == 0) goto L_0x029b;
    L_0x0298:
        r10.close();	 Catch:{ all -> 0x0292 }
    L_0x029b:
        r3 = f;	 Catch:{ all -> 0x0292 }
        r3.c();	 Catch:{ all -> 0x0292 }
        throw r2;	 Catch:{ all -> 0x0292 }
    L_0x02a1:
        r2 = move-exception;
        r10 = r3;
        goto L_0x0296;
    L_0x02a4:
        r2 = move-exception;
        r3 = r10;
        goto L_0x0268;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.i.o():java.util.ArrayList<com.qq.reader.framework.mark.Mark>");
    }

    public synchronized ArrayList<Mark> p() {
        ArrayList<Mark> arrayList;
        Throwable th;
        Exception e;
        Cursor cursor;
        arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            cursor2 = f.a().query("bookshelf", new String[]{"path", "name", "startpoint", MessageKey.MSG_DATE, "percent", SocialConstants.PARAM_COMMENT, "author", "time", "encoding", "type", "length", ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, "newcontent", "truepagebytes", "truepagefont", "truepagecurpageindex", "add_time", "book_id", "update_time", "update_chapter", "book_isfinished", BaseApplication.DATA_KEY_CHANNEL_ID, "sortindex", "cover_url", "incloud"}, "type = 4 or type = 8 or type = 9", null, null, null, "add_time DESC");
            if (cursor2.moveToFirst()) {
                do {
                    String str;
                    String string = cursor2.getString(0);
                    String string2 = cursor2.getString(1);
                    String string3 = cursor2.getString(2);
                    try {
                        Long.parseLong(string3);
                        str = null;
                    } catch (Exception e2) {
                        str = string3;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    try {
                        String string4 = cursor2.getString(3);
                        String string5 = cursor2.getString(4);
                        String string6 = cursor2.getString(5);
                        String string7 = cursor2.getString(6);
                        long j = cursor2.getLong(7);
                        int i = cursor2.getInt(8);
                        int i2 = cursor2.getInt(9);
                        long j2 = cursor2.getLong(10);
                        int i3 = cursor2.getInt(11);
                        boolean z = cursor2.getInt(12) != 0;
                        float f = cursor2.getFloat(13);
                        int i4 = cursor2.getInt(14);
                        long j3 = cursor2.getLong(15);
                        long j4 = cursor2.getLong(16);
                        long j5 = cursor2.getLong(17);
                        long j6 = cursor2.getLong(18);
                        String string8 = cursor2.getString(19);
                        int i5 = cursor2.getInt(20);
                        String string9 = cursor2.getString(21);
                        int i6 = cursor2.getInt(22);
                        String string10 = cursor2.getString(23);
                        int i7 = cursor2.getInt(24);
                        if (!(string == null || string2 == null || string4 == null)) {
                            Mark comicBookMark;
                            switch (i2) {
                                case 9:
                                    comicBookMark = new ComicBookMark(Long.parseLong(string), string2);
                                    break;
                                default:
                                    comicBookMark = new LocalMark(i2, string, j2, string2, string4);
                                    break;
                            }
                            comicBookMark.setStarPointStr(str).setPercentStr(string5).setDescriptionStr(string6).setAuthor(string7).setReadTime(j).setEncoding(i).setCategoryID(i3).setHasNewContent(z).setTurePageBytes(f).setTurePageFont(i4).setTurePageCurIndex(j3).setOperateTime(j4).setBookId(j5).setLastUpdateTime(j6).setLastUpdateChapter(string8).setFinished(i5).setNetChannel(string9).setSortIndex(i6).setCoverUrl(string10).setSynBook(i7);
                            arrayList.add(comicBookMark);
                        }
                    } catch (Exception e3) {
                        e = e3;
                        cursor = cursor2;
                    } catch (Throwable th22) {
                        th = th22;
                    }
                } while (cursor2.moveToNext());
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            f.c();
        } catch (Exception e4) {
            e = e4;
            cursor = cursor2;
            try {
                f.a("DB", "getAllAutoMarkDB with exception: " + e.toString());
                if (cursor != null) {
                    cursor.close();
                }
                f.c();
                return arrayList;
            } catch (Throwable th3) {
                th = th3;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                f.c();
                throw th;
            }
        } catch (Throwable th222) {
            th = th222;
        }
        return arrayList;
    }

    private List<Mark> t() {
        Throwable th;
        Exception e;
        Cursor cursor;
        List arrayList = new ArrayList();
        Cursor cursor2 = null;
        try {
            cursor2 = f.a().query("bookshelf", new String[]{"path", "name", "startpoint", MessageKey.MSG_DATE, "percent", SocialConstants.PARAM_COMMENT, "author", "time", "encoding", "type", "length", ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, "newcontent", "truepagebytes", "truepagefont", "truepagecurpageindex", "add_time", "book_id", "update_time", "update_chapter", "book_isfinished", BaseApplication.DATA_KEY_CHANNEL_ID, "sortindex"}, "book_id <= 0", null, null, null, null);
            if (cursor2.moveToFirst()) {
                do {
                    String str;
                    long parseLong;
                    String string = cursor2.getString(0);
                    String string2 = cursor2.getString(1);
                    long j = 0;
                    String string3 = cursor2.getString(2);
                    try {
                        str = null;
                        parseLong = Long.parseLong(string3);
                    } catch (Exception e2) {
                        str = string3;
                        parseLong = j;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    try {
                        String string4 = cursor2.getString(3);
                        String string5 = cursor2.getString(4);
                        String string6 = cursor2.getString(5);
                        String string7 = cursor2.getString(6);
                        long j2 = cursor2.getLong(7);
                        int i = cursor2.getInt(8);
                        int i2 = cursor2.getInt(9);
                        j = cursor2.getLong(10);
                        int i3 = cursor2.getInt(11);
                        boolean z = cursor2.getInt(12) != 0;
                        float f = cursor2.getFloat(13);
                        int i4 = cursor2.getInt(14);
                        long j3 = cursor2.getLong(15);
                        long j4 = cursor2.getLong(16);
                        long j5 = cursor2.getLong(17);
                        long j6 = cursor2.getLong(18);
                        String string8 = cursor2.getString(19);
                        int i5 = cursor2.getInt(20);
                        String string9 = cursor2.getString(21);
                        int i6 = cursor2.getInt(22);
                        if (!(string == null || string2 == null || string4 == null)) {
                            switch (i2) {
                                case 1:
                                    arrayList.add(new LocalMark(i2, string, j, string2, string4).setStartPoint(parseLong).setStarPointStr(str).setPercentStr(string5).setDescriptionStr(string6).setAuthor(string7).setReadTime(j2).setEncoding(i).setCategoryID(i3).setHasNewContent(z).setTurePageBytes(f).setTurePageFont(i4).setTurePageCurIndex(j3).setOperateTime(j4).setBookId(j5).setLastUpdateTime(j6).setLastUpdateChapter(string8).setFinished(i5).setNetChannel(string9).setSortIndex(i6));
                                    break;
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        cursor = cursor2;
                    } catch (Throwable th22) {
                        th = th22;
                    }
                } while (cursor2.moveToNext());
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            f.c();
        } catch (Exception e4) {
            e = e4;
            cursor = cursor2;
            try {
                f.a("DB", "getAllAutoMarkDB with exception: " + e.toString());
                if (cursor != null) {
                    cursor.close();
                }
                f.c();
                return arrayList;
            } catch (Throwable th3) {
                th = th3;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                f.c();
                throw th;
            }
        } catch (Throwable th222) {
            th = th222;
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.qq.reader.framework.mark.Mark> u() {
        /*
        r37 = this;
        r14 = new java.util.ArrayList;
        r14.<init>();
        r10 = 0;
        r2 = f;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r2 = r2.a();	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 24;
        r4 = new java.lang.String[r3];	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 0;
        r5 = "path";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 1;
        r5 = "name";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 2;
        r5 = "startpoint";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 3;
        r5 = "date";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 4;
        r5 = "percent";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 5;
        r5 = "description";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 6;
        r5 = "author";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 7;
        r5 = "time";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 8;
        r5 = "encoding";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 9;
        r5 = "type";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 10;
        r5 = "length";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 11;
        r5 = "category";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 12;
        r5 = "newcontent";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 13;
        r5 = "truepagebytes";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 14;
        r5 = "truepagefont";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 15;
        r5 = "truepagecurpageindex";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 16;
        r5 = "add_time";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 17;
        r5 = "book_id";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 18;
        r5 = "update_time";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 19;
        r5 = "update_chapter";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 20;
        r5 = "book_isfinished";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 21;
        r5 = "channel_id";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 22;
        r5 = "sortindex";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = 23;
        r5 = "cover_url";
        r4[r3] = r5;	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r3 = "bookshelf";
        r5 = "book_id > 0";
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x0307, all -> 0x023d }
        r2 = r10.moveToFirst();	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        if (r2 == 0) goto L_0x0167;
    L_0x00c4:
        r2 = 0;
        r5 = r10.getString(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r2 = 1;
        r8 = r10.getString(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r6 = 0;
        r2 = 0;
        r3 = 2;
        r3 = r10.getString(r3);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r6 = java.lang.Long.parseLong(r3);	 Catch:{ Exception -> 0x0172, all -> 0x023d }
        r11 = r2;
        r12 = r6;
    L_0x00dc:
        r2 = 3;
        r9 = r10.getString(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r2 = 4;
        r15 = r10.getString(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r2 = 5;
        r16 = r10.getString(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r2 = 6;
        r17 = r10.getString(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r2 = 7;
        r18 = r10.getLong(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r2 = 8;
        r20 = r10.getInt(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r2 = 9;
        r4 = r10.getInt(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r2 = 10;
        r6 = r10.getLong(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r2 = 11;
        r21 = r10.getInt(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r2 = 12;
        r2 = r10.getInt(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        if (r2 != 0) goto L_0x0177;
    L_0x0115:
        r2 = 0;
    L_0x0116:
        r3 = 13;
        r22 = r10.getFloat(r3);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3 = 14;
        r23 = r10.getInt(r3);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3 = 15;
        r24 = r10.getLong(r3);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3 = 16;
        r26 = r10.getLong(r3);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3 = 17;
        r28 = r10.getLong(r3);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3 = 18;
        r30 = r10.getLong(r3);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3 = 19;
        r32 = r10.getString(r3);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3 = 20;
        r33 = r10.getInt(r3);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3 = 21;
        r34 = r10.getString(r3);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3 = 22;
        r35 = r10.getInt(r3);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3 = 23;
        r36 = r10.getString(r3);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        if (r5 == 0) goto L_0x0161;
    L_0x015a:
        if (r8 == 0) goto L_0x0161;
    L_0x015c:
        if (r9 == 0) goto L_0x0161;
    L_0x015e:
        switch(r4) {
            case 1: goto L_0x01c3;
            case 2: goto L_0x0161;
            case 3: goto L_0x0179;
            case 4: goto L_0x01bd;
            case 5: goto L_0x0161;
            case 6: goto L_0x0161;
            case 7: goto L_0x0161;
            case 8: goto L_0x0249;
            case 9: goto L_0x02a6;
            default: goto L_0x0161;
        };	 Catch:{ Exception -> 0x0192, all -> 0x023d }
    L_0x0161:
        r2 = r10.moveToNext();	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        if (r2 != 0) goto L_0x00c4;
    L_0x0167:
        if (r10 == 0) goto L_0x016c;
    L_0x0169:
        r10.close();
    L_0x016c:
        r2 = f;
        r2.c();
    L_0x0171:
        return r14;
    L_0x0172:
        r2 = move-exception;
        r11 = r3;
        r12 = r6;
        goto L_0x00dc;
    L_0x0177:
        r2 = 1;
        goto L_0x0116;
    L_0x0179:
        r2 = new com.qq.reader.framework.mark.DownloadMark;	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3 = 0;
        r2.<init>(r5, r8, r3);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r21;
        r2.setCategoryID(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r28;
        r2.setBookId(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r36;
        r2.setCoverUrl(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r14.add(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        goto L_0x0161;
    L_0x0192:
        r2 = move-exception;
        r3 = r10;
    L_0x0194:
        r4 = "DB";
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0303 }
        r5.<init>();	 Catch:{ all -> 0x0303 }
        r6 = "getAllAutoMarkDB with exception: ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x0303 }
        r2 = r2.toString();	 Catch:{ all -> 0x0303 }
        r2 = r5.append(r2);	 Catch:{ all -> 0x0303 }
        r2 = r2.toString();	 Catch:{ all -> 0x0303 }
        com.qq.reader.common.monitor.f.a(r4, r2);	 Catch:{ all -> 0x0303 }
        if (r3 == 0) goto L_0x01b7;
    L_0x01b4:
        r3.close();
    L_0x01b7:
        r2 = f;
        r2.c();
        goto L_0x0171;
    L_0x01bd:
        r3 = com.qq.reader.common.monitor.j.c;	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3 = r3 + 1;
        com.qq.reader.common.monitor.j.c = r3;	 Catch:{ Exception -> 0x0192, all -> 0x023d }
    L_0x01c3:
        r3 = new com.qq.reader.framework.mark.LocalMark;	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3.<init>(r4, r5, r6, r8, r9);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3 = r3.setStartPoint(r12);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3 = r3.setStarPointStr(r11);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r3 = r3.setPercentStr(r15);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r16;
        r3 = r3.setDescriptionStr(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r17;
        r3 = r3.setAuthor(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r18;
        r3 = r3.setReadTime(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r20;
        r3 = r3.setEncoding(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r21;
        r3 = r3.setCategoryID(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r2 = r3.setHasNewContent(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r22;
        r2 = r2.setTurePageBytes(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r23;
        r2 = r2.setTurePageFont(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r24;
        r2 = r2.setTurePageCurIndex(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r26;
        r2 = r2.setOperateTime(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r28;
        r2 = r2.setBookId(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r30;
        r2 = r2.setLastUpdateTime(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r32;
        r2 = r2.setLastUpdateChapter(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r33;
        r2 = r2.setFinished(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r34;
        r2 = r2.setNetChannel(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r35;
        r2 = r2.setSortIndex(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r36;
        r2 = r2.setCoverUrl(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r14.add(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        goto L_0x0161;
    L_0x023d:
        r2 = move-exception;
    L_0x023e:
        if (r10 == 0) goto L_0x0243;
    L_0x0240:
        r10.close();
    L_0x0243:
        r3 = f;
        r3.c();
        throw r2;
    L_0x0249:
        r3 = new com.qq.reader.qplugin.local.TingBookMark;	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r28;
        r3.<init>(r0, r8);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r2 = r3.setHasNewContent(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r17;
        r2 = r2.setAuthor(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r18;
        r2 = r2.setReadTime(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r26;
        r2 = r2.setOperateTime(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r28;
        r2 = r2.setBookId(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r36;
        r2 = r2.setCoverUrl(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r21;
        r2 = r2.setCategoryID(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r28;
        r2 = r2.setBookId(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r30;
        r2 = r2.setLastUpdateTime(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r32;
        r2 = r2.setLastUpdateChapter(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r33;
        r2 = r2.setFinished(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r34;
        r2 = r2.setNetChannel(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r35;
        r2 = r2.setSortIndex(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r36;
        r2.setCoverUrl(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r14.add(r3);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        goto L_0x0161;
    L_0x02a6:
        r3 = new com.qq.reader.module.comic.mark.ComicBookMark;	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r28;
        r3.<init>(r0, r8);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r2 = r3.setHasNewContent(r2);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r17;
        r2 = r2.setAuthor(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r18;
        r2 = r2.setReadTime(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r26;
        r2 = r2.setOperateTime(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r28;
        r2 = r2.setBookId(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r36;
        r2 = r2.setCoverUrl(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r21;
        r2 = r2.setCategoryID(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r28;
        r2 = r2.setBookId(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r30;
        r2 = r2.setLastUpdateTime(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r32;
        r2 = r2.setLastUpdateChapter(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r33;
        r2 = r2.setFinished(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r34;
        r2 = r2.setNetChannel(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r35;
        r2 = r2.setSortIndex(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r0 = r36;
        r2.setCoverUrl(r0);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        r14.add(r3);	 Catch:{ Exception -> 0x0192, all -> 0x023d }
        goto L_0x0161;
    L_0x0303:
        r2 = move-exception;
        r10 = r3;
        goto L_0x023e;
    L_0x0307:
        r2 = move-exception;
        r3 = r10;
        goto L_0x0194;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.i.u():java.util.List<com.qq.reader.framework.mark.Mark>");
    }

    public ArrayList<Mark> q() {
        return m("cloud_del_tag = 1");
    }

    ArrayList<Mark> r() {
        return m("cloud_del_tag = 0");
    }

    private ArrayList<Mark> m(String str) {
        Throwable th;
        Exception e;
        Cursor cursor;
        ArrayList<Mark> arrayList = new ArrayList();
        j.c = 0;
        Cursor cursor2 = null;
        try {
            cursor2 = f.a().query("bookshelf", new String[]{"path", "name", "startpoint", MessageKey.MSG_DATE, "percent", SocialConstants.PARAM_COMMENT, "author", "time", "encoding", "type", "length", ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, "newcontent", "truepagebytes", "truepagefont", "truepagecurpageindex", "add_time", "book_id", "update_time", "update_chapter", "book_isfinished", BaseApplication.DATA_KEY_CHANNEL_ID, "sortindex", "cover_url", "incloud", "downloadinfo", "lastread_chapter", "private_property", "LimitFreeEndTime", "cloud_del_tag", "discount"}, str, null, null, null, "add_time DESC");
            if (cursor2.moveToFirst()) {
                do {
                    String str2;
                    long parseLong;
                    String string = cursor2.getString(0);
                    String string2 = cursor2.getString(1);
                    long j = 0;
                    String string3 = cursor2.getString(2);
                    try {
                        str2 = null;
                        parseLong = Long.parseLong(string3);
                    } catch (Exception e2) {
                        str2 = string3;
                        parseLong = j;
                    } catch (Throwable th2) {
                        th = th2;
                    }
                    try {
                        String string4 = cursor2.getString(3);
                        String string5 = cursor2.getString(4);
                        String string6 = cursor2.getString(5);
                        String string7 = cursor2.getString(6);
                        long j2 = cursor2.getLong(7);
                        int i = cursor2.getInt(8);
                        int i2 = cursor2.getInt(9);
                        j = cursor2.getLong(10);
                        int i3 = cursor2.getInt(11);
                        boolean z = cursor2.getInt(12) != 0;
                        float f = cursor2.getFloat(13);
                        int i4 = cursor2.getInt(14);
                        long j3 = cursor2.getLong(15);
                        long j4 = cursor2.getLong(16);
                        long j5 = cursor2.getLong(17);
                        long j6 = cursor2.getLong(18);
                        String string8 = cursor2.getString(19);
                        int i5 = cursor2.getInt(20);
                        String string9 = cursor2.getString(21);
                        int i6 = cursor2.getInt(22);
                        String string10 = cursor2.getString(23);
                        int i7 = cursor2.getInt(24);
                        String string11 = cursor2.getString(25);
                        String string12 = cursor2.getString(26);
                        int i8 = cursor2.getInt(27);
                        String string13 = cursor2.getString(28);
                        int i9 = cursor2.getInt(cursor2.getColumnIndex("cloud_del_tag"));
                        int i10 = cursor2.getInt(cursor2.getColumnIndex("discount"));
                        if (!(string == null || string2 == null || string4 == null)) {
                            Mark downloadMark;
                            switch (i2) {
                                case 1:
                                case 2:
                                    break;
                                case 3:
                                    downloadMark = new DownloadMark(string, string2, false);
                                    downloadMark.setCategoryID(i3).setBookId(j5).setCoverUrl(string10).setDownloadInfo(string11).setStartPoint(parseLong).setStarPointStr(str2).setPercentStr(string5).setDescriptionStr(string6).setAuthor(string7).setReadTime(j2).setEncoding(i).setCategoryID(i3).setHasNewContent(z).setTurePageBytes(f).setTurePageFont(i4).setTurePageCurIndex(j3).setOperateTime(j4).setBookId(j5).setLastUpdateTime(j6).setLastUpdateChapter(string8).setFinished(i5).setNetChannel(string9).setSortIndex(i6).setCoverUrl(string10).setDownloadInfo(string11).setSynBook(i7).setLastReadChapterName(string12).setPrivateProperty(i8).setLimitFreeEndTime(string13).setDiscount(i10).setCloudDelTag(i9);
                                    arrayList.add(downloadMark);
                                    break;
                                case 4:
                                    j.c++;
                                    break;
                                case 8:
                                    downloadMark = new TingBookMark(j5, string2);
                                    downloadMark.setHasNewContent(z).setAuthor(string7).setReadTime(j2).setOperateTime(j4).setBookId(j5).setCategoryID(i3).setBookId(j5).setLastUpdateTime(j6).setLastUpdateChapter(string8).setFinished(i5).setNetChannel(string9).setSortIndex(i6).setCoverUrl(string10).setDownloadInfo(string11).setSynBook(i7).setLastReadChapterName(string12).setPrivateProperty(i8).setLimitFreeEndTime(string13).setDiscount(i10).setCloudDelTag(i9);
                                    arrayList.add(downloadMark);
                                    break;
                                case 9:
                                    downloadMark = new ComicBookMark(j5, string2);
                                    downloadMark.setHasNewContent(z).setAuthor(string7).setReadTime(j2).setOperateTime(j4).setBookId(j5).setCategoryID(i3).setBookId(j5).setLastUpdateTime(j6).setLastUpdateChapter(string8).setFinished(i5).setNetChannel(string9).setSortIndex(i6).setCoverUrl(string10).setDownloadInfo(string11).setSynBook(i7).setLastReadChapterName(string12).setPrivateProperty(i8).setLimitFreeEndTime(string13).setDiscount(i10).setCloudDelTag(i9);
                                    arrayList.add(downloadMark);
                                    break;
                            }
                            arrayList.add(new LocalMark(i2, string, j, string2, string4).setStartPoint(parseLong).setStarPointStr(str2).setPercentStr(string5).setDescriptionStr(string6).setAuthor(string7).setReadTime(j2).setEncoding(i).setCategoryID(i3).setHasNewContent(z).setTurePageBytes(f).setTurePageFont(i4).setTurePageCurIndex(j3).setOperateTime(j4).setBookId(j5).setLastUpdateTime(j6).setLastUpdateChapter(string8).setFinished(i5).setNetChannel(string9).setSortIndex(i6).setCoverUrl(string10).setDownloadInfo(string11).setSynBook(i7).setLastReadChapterName(string12).setPrivateProperty(i8).setLimitFreeEndTime(string13).setDiscount(i10).setCloudDelTag(i9));
                        }
                    } catch (Exception e3) {
                        e = e3;
                        cursor = cursor2;
                    } catch (Throwable th22) {
                        th = th22;
                    }
                } while (cursor2.moveToNext());
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            f.c();
            com.qq.reader.common.exception.a.a().a(0);
        } catch (Exception e4) {
            e = e4;
            cursor = cursor2;
            try {
                f.a("DB", "getAllAutoMarkDB with exception: " + e.toString());
                if (cursor != null) {
                    cursor.close();
                }
                f.c();
                com.qq.reader.common.exception.a.a().b(0);
                return arrayList;
            } catch (Throwable th3) {
                th = th3;
                cursor2 = cursor;
                if (cursor2 != null) {
                    cursor2.close();
                }
                f.c();
                com.qq.reader.common.exception.a.a().b(0);
                throw th;
            }
        } catch (Throwable th222) {
            th = th222;
        }
        return arrayList;
    }

    public synchronized boolean a(long j, boolean z) {
        boolean z2 = false;
        synchronized (this) {
            if (j >= 0) {
                int i = z ? 1 : 0;
                try {
                    f.a().execSQL("update bookshelf set incloud = " + i + " where " + "bookshelf" + "." + "book_id" + "=" + j);
                    this.h.a(j, i);
                    f.c();
                    z2 = true;
                } catch (Exception e) {
                    f.a("BookmarkHandle", "error in updateBookSynState : " + e.toString());
                    f.c();
                } catch (Throwable th) {
                    f.c();
                }
            }
        }
        return z2;
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        Exception e;
        ContentValues contentValues;
        Throwable th;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select newcontent from bookshelf", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(3);
                        rawQuery.close();
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update2To3 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD newcontent integer default 0");
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD truepagebytes float default 0");
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD truepagefont integer default 0");
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD truepagecurpageindex long default -1");
                        contentValues = new ContentValues();
                        contentValues.put("category_name", "在线");
                        c = (int) sQLiteDatabase.insert(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, cursor, contentValues);
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            f.a("DB", " update2To3 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD newcontent integer default 0");
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD truepagebytes float default 0");
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD truepagefont integer default 0");
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD truepagecurpageindex long default -1");
            contentValues = new ContentValues();
            contentValues.put("category_name", "在线");
            c = (int) sQLiteDatabase.insert(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, cursor, contentValues);
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD newcontent integer default 0");
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD truepagebytes float default 0");
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD truepagefont integer default 0");
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD truepagecurpageindex long default -1");
        contentValues = new ContentValues();
        contentValues.put("category_name", "在线");
        c = (int) sQLiteDatabase.insert(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, cursor, contentValues);
    }

    private void f(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select add_time from bookshelf", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(4);
                        rawQuery.close();
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update3To4 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD add_time long default 0");
                        sQLiteDatabase.execSQL("update bookshelf set add_time = time");
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            f.a("DB", " update3To4 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD add_time long default 0");
            sQLiteDatabase.execSQL("update bookshelf set add_time = time");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD add_time long default 0");
        sQLiteDatabase.execSQL("update bookshelf set add_time = time");
    }

    private void g(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Cursor cursor;
        List<Mark> arrayList;
        String string;
        String string2;
        long j;
        String string3;
        String str;
        long parseLong;
        String string4;
        String string5;
        String string6;
        String string7;
        long j2;
        int i;
        int i2;
        int i3;
        boolean z;
        float f;
        int i4;
        long j3;
        long j4;
        long j5;
        Mark downloadMark;
        List a;
        int type;
        int size;
        DownloadBookTask downloadBookTask;
        Cursor cursor2;
        ContentValues contentValues;
        int i5;
        String string8;
        long update;
        Throwable th;
        Cursor cursor3 = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select book_id from bookshelf", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(5);
                        rawQuery.close();
                        rawQuery = null;
                        if (rawQuery != null) {
                            rawQuery.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update4To5 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                            cursor = rawQuery;
                            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD book_id long default 0");
                            arrayList = new ArrayList();
                            cursor = sQLiteDatabase.query("bookshelf", new String[]{"path", "name", "startpoint", MessageKey.MSG_DATE, "percent", SocialConstants.PARAM_COMMENT, "author", "time", "encoding", "type", "length", ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, "newcontent", "truepagebytes", "truepagefont", "truepagecurpageindex", "add_time", "book_id"}, null, null, null, null, null);
                            if (!!cursor.moveToFirst()) {
                                do {
                                    string = cursor.getString(0);
                                    string2 = cursor.getString(1);
                                    j = 0;
                                    string3 = cursor.getString(2);
                                    try {
                                        str = null;
                                        parseLong = Long.parseLong(string3);
                                    } catch (Exception e3) {
                                        str = string3;
                                        parseLong = j;
                                    }
                                    string4 = cursor.getString(3);
                                    string5 = cursor.getString(4);
                                    string6 = cursor.getString(5);
                                    string7 = cursor.getString(6);
                                    j2 = cursor.getLong(7);
                                    i = cursor.getInt(8);
                                    i2 = cursor.getInt(9);
                                    j = cursor.getLong(10);
                                    i3 = cursor.getInt(11);
                                    z = cursor.getInt(12) == 0;
                                    f = cursor.getFloat(13);
                                    i4 = cursor.getInt(14);
                                    j3 = cursor.getLong(15);
                                    j4 = cursor.getLong(16);
                                    j5 = cursor.getLong(17);
                                    switch (i2) {
                                        case 1:
                                        case 2:
                                            break;
                                        case 3:
                                            downloadMark = new DownloadMark(string, string2, false);
                                            downloadMark.setCategoryID(i3);
                                            arrayList.add(downloadMark);
                                            break;
                                        case 4:
                                            try {
                                                j.c++;
                                                break;
                                            } catch (Throwable th2) {
                                                if (cursor != null) {
                                                    cursor.close();
                                                }
                                            }
                                    }
                                    arrayList.add(new LocalMark(i2, string, j, string2, string4).setStartPoint(parseLong).setStarPointStr(str).setPercentStr(string5).setDescriptionStr(string6).setAuthor(string7).setReadTime(j2).setEncoding(i).setCategoryID(i3).setHasNewContent(z).setTurePageBytes(f).setTurePageFont(i4).setTurePageCurIndex(j3).setOperateTime(j4).setBookId(j5));
                                } while (cursor.moveToNext());
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                            a = o.c().a();
                            for (Mark downloadMark2 : arrayList) {
                                type = downloadMark2.getType();
                                if (downloadMark2 == null) {
                                    if (type != 3) {
                                    }
                                    size = a.size();
                                    for (i2 = 0; i2 < size; i2++) {
                                        downloadBookTask = (DownloadBookTask) a.get(i2);
                                        if (downloadBookTask == null) {
                                        }
                                    }
                                }
                            }
                            cursor3 = null;
                            cursor2 = cursor3;
                            for (Mark mark : arrayList) {
                                try {
                                    contentValues = new ContentValues();
                                    contentValues.put("path", mark.getId());
                                    contentValues.put("name", mark.getBookName());
                                    if (mark.getStarPointStr() != null) {
                                        contentValues.put("startpoint", String.valueOf(mark.getStartPoint()));
                                    } else {
                                        contentValues.put("startpoint", mark.getStarPointStr());
                                    }
                                    contentValues.put(MessageKey.MSG_DATE, mark.getDataStr());
                                    contentValues.put("percent", mark.getPercentStr());
                                    contentValues.put(SocialConstants.PARAM_COMMENT, mark.getDescriptionStr());
                                    contentValues.put("author", mark.getAuthor());
                                    contentValues.put("time", String.valueOf(mark.getReadTime()));
                                    j.p = mark.getReadTime();
                                    contentValues.put("encoding", Integer.valueOf(mark.getEncoding()));
                                    contentValues.put("type", Integer.valueOf(mark.getType()));
                                    contentValues.put("length", String.valueOf(mark.getFileLength()));
                                    contentValues.put("newcontent", Integer.valueOf(mark.hasNewContent() ? 1 : 0));
                                    contentValues.put("truepagebytes", Float.valueOf(mark.getTurePageBytes()));
                                    contentValues.put("truepagefont", Integer.valueOf(mark.getTurePageFont()));
                                    contentValues.put("truepagecurpageindex", Long.valueOf(mark.getTurePageCurIndex()));
                                    contentValues.put("add_time", String.valueOf(mark.getOperateTime()));
                                    contentValues.put("book_id", String.valueOf(mark.getBookId()));
                                    cursor3 = sQLiteDatabase.query("bookshelf", new String[]{"_id", "book_id"}, "path= '" + mark.getId().replace("'", "''") + "'", null, null, null, null, null);
                                    if (cursor3 != null) {
                                        try {
                                            if (cursor3.getCount() > 0) {
                                                cursor3.moveToFirst();
                                                i5 = cursor3.getInt(0);
                                                string8 = cursor3.getString(1);
                                                if (!"0".equals(string8)) {
                                                    contentValues.put("book_id", string8);
                                                }
                                                update = (long) sQLiteDatabase.update("bookshelf", contentValues, "_id=" + i5, null);
                                                cursor3.close();
                                                cursor2 = cursor3;
                                            }
                                        } catch (Throwable th3) {
                                            th = th3;
                                        }
                                    }
                                    if (4 == mark.getType()) {
                                        contentValues.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, Integer.valueOf(c));
                                    }
                                    sQLiteDatabase.insert("bookshelf", null, contentValues);
                                    cursor3.close();
                                    cursor2 = cursor3;
                                } catch (Throwable th4) {
                                    th = th4;
                                    cursor3 = cursor2;
                                }
                            }
                            if (cursor2 != null) {
                                cursor2.close();
                                return;
                            }
                            return;
                        }
                        cursor = rawQuery;
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD book_id long default 0");
                        arrayList = new ArrayList();
                        cursor = sQLiteDatabase.query("bookshelf", new String[]{"path", "name", "startpoint", MessageKey.MSG_DATE, "percent", SocialConstants.PARAM_COMMENT, "author", "time", "encoding", "type", "length", ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, "newcontent", "truepagebytes", "truepagefont", "truepagecurpageindex", "add_time", "book_id"}, null, null, null, null, null);
                        if (!cursor.moveToFirst()) {
                            do {
                                string = cursor.getString(0);
                                string2 = cursor.getString(1);
                                j = 0;
                                string3 = cursor.getString(2);
                                str = null;
                                parseLong = Long.parseLong(string3);
                                string4 = cursor.getString(3);
                                string5 = cursor.getString(4);
                                string6 = cursor.getString(5);
                                string7 = cursor.getString(6);
                                j2 = cursor.getLong(7);
                                i = cursor.getInt(8);
                                i2 = cursor.getInt(9);
                                j = cursor.getLong(10);
                                i3 = cursor.getInt(11);
                                if (cursor.getInt(12) == 0) {
                                }
                                f = cursor.getFloat(13);
                                i4 = cursor.getInt(14);
                                j3 = cursor.getLong(15);
                                j4 = cursor.getLong(16);
                                j5 = cursor.getLong(17);
                                switch (i2) {
                                    case 1:
                                    case 2:
                                        break;
                                    case 3:
                                        downloadMark2 = new DownloadMark(string, string2, false);
                                        downloadMark2.setCategoryID(i3);
                                        arrayList.add(downloadMark2);
                                        break;
                                    case 4:
                                        j.c++;
                                        break;
                                }
                                arrayList.add(new LocalMark(i2, string, j, string2, string4).setStartPoint(parseLong).setStarPointStr(str).setPercentStr(string5).setDescriptionStr(string6).setAuthor(string7).setReadTime(j2).setEncoding(i).setCategoryID(i3).setHasNewContent(z).setTurePageBytes(f).setTurePageFont(i4).setTurePageCurIndex(j3).setOperateTime(j4).setBookId(j5));
                            } while (cursor.moveToNext());
                        } else if (cursor != null) {
                            cursor.close();
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        a = o.c().a();
                        for (Mark downloadMark22 : arrayList) {
                            type = downloadMark22.getType();
                            if (downloadMark22 == null) {
                                if (type != 3) {
                                }
                                size = a.size();
                                for (i2 = 0; i2 < size; i2++) {
                                    downloadBookTask = (DownloadBookTask) a.get(i2);
                                    if (downloadBookTask == null) {
                                    }
                                }
                            }
                        }
                        cursor3 = null;
                        cursor2 = cursor3;
                        for (Mark mark2 : arrayList) {
                            contentValues = new ContentValues();
                            contentValues.put("path", mark2.getId());
                            contentValues.put("name", mark2.getBookName());
                            if (mark2.getStarPointStr() != null) {
                                contentValues.put("startpoint", mark2.getStarPointStr());
                            } else {
                                contentValues.put("startpoint", String.valueOf(mark2.getStartPoint()));
                            }
                            contentValues.put(MessageKey.MSG_DATE, mark2.getDataStr());
                            contentValues.put("percent", mark2.getPercentStr());
                            contentValues.put(SocialConstants.PARAM_COMMENT, mark2.getDescriptionStr());
                            contentValues.put("author", mark2.getAuthor());
                            contentValues.put("time", String.valueOf(mark2.getReadTime()));
                            j.p = mark2.getReadTime();
                            contentValues.put("encoding", Integer.valueOf(mark2.getEncoding()));
                            contentValues.put("type", Integer.valueOf(mark2.getType()));
                            contentValues.put("length", String.valueOf(mark2.getFileLength()));
                            if (mark2.hasNewContent()) {
                            }
                            contentValues.put("newcontent", Integer.valueOf(mark2.hasNewContent() ? 1 : 0));
                            contentValues.put("truepagebytes", Float.valueOf(mark2.getTurePageBytes()));
                            contentValues.put("truepagefont", Integer.valueOf(mark2.getTurePageFont()));
                            contentValues.put("truepagecurpageindex", Long.valueOf(mark2.getTurePageCurIndex()));
                            contentValues.put("add_time", String.valueOf(mark2.getOperateTime()));
                            contentValues.put("book_id", String.valueOf(mark2.getBookId()));
                            cursor3 = sQLiteDatabase.query("bookshelf", new String[]{"_id", "book_id"}, "path= '" + mark2.getId().replace("'", "''") + "'", null, null, null, null, null);
                            if (cursor3 != null) {
                                if (cursor3.getCount() > 0) {
                                    cursor3.moveToFirst();
                                    i5 = cursor3.getInt(0);
                                    string8 = cursor3.getString(1);
                                    if ("0".equals(string8)) {
                                        contentValues.put("book_id", string8);
                                    }
                                    update = (long) sQLiteDatabase.update("bookshelf", contentValues, "_id=" + i5, null);
                                    cursor3.close();
                                    cursor2 = cursor3;
                                }
                            }
                            if (4 == mark2.getType()) {
                                contentValues.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, Integer.valueOf(c));
                            }
                            sQLiteDatabase.insert("bookshelf", null, contentValues);
                            cursor3.close();
                            cursor2 = cursor3;
                        }
                        if (cursor2 != null) {
                            cursor2.close();
                            return;
                        }
                        return;
                    } catch (Throwable th5) {
                        Throwable th6 = th5;
                        cursor3 = rawQuery;
                        th = th6;
                        if (cursor3 != null) {
                            cursor3.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
                cursor = rawQuery;
                sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD book_id long default 0");
                arrayList = new ArrayList();
                cursor = sQLiteDatabase.query("bookshelf", new String[]{"path", "name", "startpoint", MessageKey.MSG_DATE, "percent", SocialConstants.PARAM_COMMENT, "author", "time", "encoding", "type", "length", ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, "newcontent", "truepagebytes", "truepagefont", "truepagecurpageindex", "add_time", "book_id"}, null, null, null, null, null);
                if (!cursor.moveToFirst()) {
                    do {
                        string = cursor.getString(0);
                        string2 = cursor.getString(1);
                        j = 0;
                        string3 = cursor.getString(2);
                        str = null;
                        parseLong = Long.parseLong(string3);
                        string4 = cursor.getString(3);
                        string5 = cursor.getString(4);
                        string6 = cursor.getString(5);
                        string7 = cursor.getString(6);
                        j2 = cursor.getLong(7);
                        i = cursor.getInt(8);
                        i2 = cursor.getInt(9);
                        j = cursor.getLong(10);
                        i3 = cursor.getInt(11);
                        if (cursor.getInt(12) == 0) {
                        }
                        f = cursor.getFloat(13);
                        i4 = cursor.getInt(14);
                        j3 = cursor.getLong(15);
                        j4 = cursor.getLong(16);
                        j5 = cursor.getLong(17);
                        if (!(string == null || string2 == null || string4 == null)) {
                            switch (i2) {
                                case 1:
                                case 2:
                                    break;
                                case 3:
                                    downloadMark22 = new DownloadMark(string, string2, false);
                                    downloadMark22.setCategoryID(i3);
                                    arrayList.add(downloadMark22);
                                    break;
                                case 4:
                                    j.c++;
                                    break;
                            }
                            arrayList.add(new LocalMark(i2, string, j, string2, string4).setStartPoint(parseLong).setStarPointStr(str).setPercentStr(string5).setDescriptionStr(string6).setAuthor(string7).setReadTime(j2).setEncoding(i).setCategoryID(i3).setHasNewContent(z).setTurePageBytes(f).setTurePageFont(i4).setTurePageCurIndex(j3).setOperateTime(j4).setBookId(j5));
                        }
                    } while (cursor.moveToNext());
                }
                if (cursor != null) {
                    cursor.close();
                }
                a = o.c().a();
                for (Mark downloadMark222 : arrayList) {
                    type = downloadMark222.getType();
                    if (downloadMark222 == null) {
                        if (type != 3 || type == 1) {
                            size = a.size();
                            while (i2 < size) {
                                downloadBookTask = (DownloadBookTask) a.get(i2);
                                if (downloadBookTask == null && downloadBookTask.getFilePath().equals(downloadMark222.getId())) {
                                    downloadMark222.setBookId(downloadBookTask.getId());
                                } else {
                                }
                            }
                        } else if (type == 4) {
                            update = 0;
                            try {
                                update = Long.parseLong(downloadMark222.getId());
                            } catch (Exception e4) {
                                f.a("BookMarkHandle", "update4To5 error : " + e4.toString());
                                e4.printStackTrace();
                            }
                            downloadMark222.setBookId(update);
                        }
                    }
                }
                cursor3 = null;
                cursor2 = cursor3;
                for (Mark mark22 : arrayList) {
                    contentValues = new ContentValues();
                    contentValues.put("path", mark22.getId());
                    contentValues.put("name", mark22.getBookName());
                    if (mark22.getStarPointStr() != null) {
                        contentValues.put("startpoint", String.valueOf(mark22.getStartPoint()));
                    } else {
                        contentValues.put("startpoint", mark22.getStarPointStr());
                    }
                    contentValues.put(MessageKey.MSG_DATE, mark22.getDataStr());
                    contentValues.put("percent", mark22.getPercentStr());
                    contentValues.put(SocialConstants.PARAM_COMMENT, mark22.getDescriptionStr());
                    contentValues.put("author", mark22.getAuthor());
                    contentValues.put("time", String.valueOf(mark22.getReadTime()));
                    j.p = mark22.getReadTime();
                    contentValues.put("encoding", Integer.valueOf(mark22.getEncoding()));
                    contentValues.put("type", Integer.valueOf(mark22.getType()));
                    contentValues.put("length", String.valueOf(mark22.getFileLength()));
                    if (mark22.hasNewContent()) {
                    }
                    contentValues.put("newcontent", Integer.valueOf(mark22.hasNewContent() ? 1 : 0));
                    contentValues.put("truepagebytes", Float.valueOf(mark22.getTurePageBytes()));
                    contentValues.put("truepagefont", Integer.valueOf(mark22.getTurePageFont()));
                    contentValues.put("truepagecurpageindex", Long.valueOf(mark22.getTurePageCurIndex()));
                    contentValues.put("add_time", String.valueOf(mark22.getOperateTime()));
                    contentValues.put("book_id", String.valueOf(mark22.getBookId()));
                    cursor3 = sQLiteDatabase.query("bookshelf", new String[]{"_id", "book_id"}, "path= '" + mark22.getId().replace("'", "''") + "'", null, null, null, null, null);
                    if (cursor3 != null) {
                        if (cursor3.getCount() > 0) {
                            cursor3.moveToFirst();
                            i5 = cursor3.getInt(0);
                            string8 = cursor3.getString(1);
                            if ("0".equals(string8)) {
                                contentValues.put("book_id", string8);
                            }
                            update = (long) sQLiteDatabase.update("bookshelf", contentValues, "_id=" + i5, null);
                            cursor3.close();
                            cursor2 = cursor3;
                        }
                    }
                    if (4 == mark22.getType()) {
                        contentValues.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, Integer.valueOf(c));
                    }
                    sQLiteDatabase.insert("bookshelf", null, contentValues);
                    cursor3.close();
                    cursor2 = cursor3;
                }
                if (cursor2 != null) {
                    cursor2.close();
                    return;
                }
                return;
            }
        } catch (Exception e5) {
            Exception exception = e5;
            rawQuery = null;
            e4 = exception;
            f.a("DB", " update4To5 :" + e4.toString());
            if (rawQuery != null) {
                rawQuery.close();
                cursor = rawQuery;
                sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD book_id long default 0");
                arrayList = new ArrayList();
                cursor = sQLiteDatabase.query("bookshelf", new String[]{"path", "name", "startpoint", MessageKey.MSG_DATE, "percent", SocialConstants.PARAM_COMMENT, "author", "time", "encoding", "type", "length", ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, "newcontent", "truepagebytes", "truepagefont", "truepagecurpageindex", "add_time", "book_id"}, null, null, null, null, null);
                if (!cursor.moveToFirst()) {
                    do {
                        string = cursor.getString(0);
                        string2 = cursor.getString(1);
                        j = 0;
                        string3 = cursor.getString(2);
                        str = null;
                        parseLong = Long.parseLong(string3);
                        string4 = cursor.getString(3);
                        string5 = cursor.getString(4);
                        string6 = cursor.getString(5);
                        string7 = cursor.getString(6);
                        j2 = cursor.getLong(7);
                        i = cursor.getInt(8);
                        i2 = cursor.getInt(9);
                        j = cursor.getLong(10);
                        i3 = cursor.getInt(11);
                        if (cursor.getInt(12) == 0) {
                        }
                        f = cursor.getFloat(13);
                        i4 = cursor.getInt(14);
                        j3 = cursor.getLong(15);
                        j4 = cursor.getLong(16);
                        j5 = cursor.getLong(17);
                        switch (i2) {
                            case 1:
                            case 2:
                                break;
                            case 3:
                                downloadMark222 = new DownloadMark(string, string2, false);
                                downloadMark222.setCategoryID(i3);
                                arrayList.add(downloadMark222);
                                break;
                            case 4:
                                j.c++;
                                break;
                        }
                        arrayList.add(new LocalMark(i2, string, j, string2, string4).setStartPoint(parseLong).setStarPointStr(str).setPercentStr(string5).setDescriptionStr(string6).setAuthor(string7).setReadTime(j2).setEncoding(i).setCategoryID(i3).setHasNewContent(z).setTurePageBytes(f).setTurePageFont(i4).setTurePageCurIndex(j3).setOperateTime(j4).setBookId(j5));
                    } while (cursor.moveToNext());
                }
                if (cursor != null) {
                    cursor.close();
                }
                a = o.c().a();
                for (Mark downloadMark2222 : arrayList) {
                    type = downloadMark2222.getType();
                    if (downloadMark2222 == null) {
                        if (type != 3) {
                        }
                        size = a.size();
                        for (i2 = 0; i2 < size; i2++) {
                            downloadBookTask = (DownloadBookTask) a.get(i2);
                            if (downloadBookTask == null) {
                            }
                        }
                    }
                }
                cursor3 = null;
                cursor2 = cursor3;
                for (Mark mark222 : arrayList) {
                    contentValues = new ContentValues();
                    contentValues.put("path", mark222.getId());
                    contentValues.put("name", mark222.getBookName());
                    if (mark222.getStarPointStr() != null) {
                        contentValues.put("startpoint", String.valueOf(mark222.getStartPoint()));
                    } else {
                        contentValues.put("startpoint", mark222.getStarPointStr());
                    }
                    contentValues.put(MessageKey.MSG_DATE, mark222.getDataStr());
                    contentValues.put("percent", mark222.getPercentStr());
                    contentValues.put(SocialConstants.PARAM_COMMENT, mark222.getDescriptionStr());
                    contentValues.put("author", mark222.getAuthor());
                    contentValues.put("time", String.valueOf(mark222.getReadTime()));
                    j.p = mark222.getReadTime();
                    contentValues.put("encoding", Integer.valueOf(mark222.getEncoding()));
                    contentValues.put("type", Integer.valueOf(mark222.getType()));
                    contentValues.put("length", String.valueOf(mark222.getFileLength()));
                    if (mark222.hasNewContent()) {
                    }
                    contentValues.put("newcontent", Integer.valueOf(mark222.hasNewContent() ? 1 : 0));
                    contentValues.put("truepagebytes", Float.valueOf(mark222.getTurePageBytes()));
                    contentValues.put("truepagefont", Integer.valueOf(mark222.getTurePageFont()));
                    contentValues.put("truepagecurpageindex", Long.valueOf(mark222.getTurePageCurIndex()));
                    contentValues.put("add_time", String.valueOf(mark222.getOperateTime()));
                    contentValues.put("book_id", String.valueOf(mark222.getBookId()));
                    cursor3 = sQLiteDatabase.query("bookshelf", new String[]{"_id", "book_id"}, "path= '" + mark222.getId().replace("'", "''") + "'", null, null, null, null, null);
                    if (cursor3 != null) {
                        if (cursor3.getCount() > 0) {
                            cursor3.moveToFirst();
                            i5 = cursor3.getInt(0);
                            string8 = cursor3.getString(1);
                            if ("0".equals(string8)) {
                                contentValues.put("book_id", string8);
                            }
                            update = (long) sQLiteDatabase.update("bookshelf", contentValues, "_id=" + i5, null);
                            cursor3.close();
                            cursor2 = cursor3;
                        }
                    }
                    if (4 == mark222.getType()) {
                        contentValues.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, Integer.valueOf(c));
                    }
                    sQLiteDatabase.insert("bookshelf", null, contentValues);
                    cursor3.close();
                    cursor2 = cursor3;
                }
                if (cursor2 != null) {
                    cursor2.close();
                    return;
                }
                return;
            }
            cursor = rawQuery;
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD book_id long default 0");
            arrayList = new ArrayList();
            cursor = sQLiteDatabase.query("bookshelf", new String[]{"path", "name", "startpoint", MessageKey.MSG_DATE, "percent", SocialConstants.PARAM_COMMENT, "author", "time", "encoding", "type", "length", ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, "newcontent", "truepagebytes", "truepagefont", "truepagecurpageindex", "add_time", "book_id"}, null, null, null, null, null);
            if (!cursor.moveToFirst()) {
                do {
                    string = cursor.getString(0);
                    string2 = cursor.getString(1);
                    j = 0;
                    string3 = cursor.getString(2);
                    str = null;
                    parseLong = Long.parseLong(string3);
                    string4 = cursor.getString(3);
                    string5 = cursor.getString(4);
                    string6 = cursor.getString(5);
                    string7 = cursor.getString(6);
                    j2 = cursor.getLong(7);
                    i = cursor.getInt(8);
                    i2 = cursor.getInt(9);
                    j = cursor.getLong(10);
                    i3 = cursor.getInt(11);
                    if (cursor.getInt(12) == 0) {
                    }
                    f = cursor.getFloat(13);
                    i4 = cursor.getInt(14);
                    j3 = cursor.getLong(15);
                    j4 = cursor.getLong(16);
                    j5 = cursor.getLong(17);
                    switch (i2) {
                        case 1:
                        case 2:
                            break;
                        case 3:
                            downloadMark2222 = new DownloadMark(string, string2, false);
                            downloadMark2222.setCategoryID(i3);
                            arrayList.add(downloadMark2222);
                            break;
                        case 4:
                            j.c++;
                            break;
                    }
                    arrayList.add(new LocalMark(i2, string, j, string2, string4).setStartPoint(parseLong).setStarPointStr(str).setPercentStr(string5).setDescriptionStr(string6).setAuthor(string7).setReadTime(j2).setEncoding(i).setCategoryID(i3).setHasNewContent(z).setTurePageBytes(f).setTurePageFont(i4).setTurePageCurIndex(j3).setOperateTime(j4).setBookId(j5));
                } while (cursor.moveToNext());
            } else if (cursor != null) {
                cursor.close();
            }
            if (cursor != null) {
                cursor.close();
            }
            a = o.c().a();
            for (Mark downloadMark22222 : arrayList) {
                type = downloadMark22222.getType();
                if (downloadMark22222 == null) {
                    if (type != 3) {
                    }
                    size = a.size();
                    for (i2 = 0; i2 < size; i2++) {
                        downloadBookTask = (DownloadBookTask) a.get(i2);
                        if (downloadBookTask == null) {
                        }
                    }
                }
            }
            cursor3 = null;
            cursor2 = cursor3;
            for (Mark mark2222 : arrayList) {
                contentValues = new ContentValues();
                contentValues.put("path", mark2222.getId());
                contentValues.put("name", mark2222.getBookName());
                if (mark2222.getStarPointStr() != null) {
                    contentValues.put("startpoint", mark2222.getStarPointStr());
                } else {
                    contentValues.put("startpoint", String.valueOf(mark2222.getStartPoint()));
                }
                contentValues.put(MessageKey.MSG_DATE, mark2222.getDataStr());
                contentValues.put("percent", mark2222.getPercentStr());
                contentValues.put(SocialConstants.PARAM_COMMENT, mark2222.getDescriptionStr());
                contentValues.put("author", mark2222.getAuthor());
                contentValues.put("time", String.valueOf(mark2222.getReadTime()));
                j.p = mark2222.getReadTime();
                contentValues.put("encoding", Integer.valueOf(mark2222.getEncoding()));
                contentValues.put("type", Integer.valueOf(mark2222.getType()));
                contentValues.put("length", String.valueOf(mark2222.getFileLength()));
                if (mark2222.hasNewContent()) {
                }
                contentValues.put("newcontent", Integer.valueOf(mark2222.hasNewContent() ? 1 : 0));
                contentValues.put("truepagebytes", Float.valueOf(mark2222.getTurePageBytes()));
                contentValues.put("truepagefont", Integer.valueOf(mark2222.getTurePageFont()));
                contentValues.put("truepagecurpageindex", Long.valueOf(mark2222.getTurePageCurIndex()));
                contentValues.put("add_time", String.valueOf(mark2222.getOperateTime()));
                contentValues.put("book_id", String.valueOf(mark2222.getBookId()));
                cursor3 = sQLiteDatabase.query("bookshelf", new String[]{"_id", "book_id"}, "path= '" + mark2222.getId().replace("'", "''") + "'", null, null, null, null, null);
                if (cursor3 != null) {
                    if (cursor3.getCount() > 0) {
                        cursor3.moveToFirst();
                        i5 = cursor3.getInt(0);
                        string8 = cursor3.getString(1);
                        if ("0".equals(string8)) {
                            contentValues.put("book_id", string8);
                        }
                        update = (long) sQLiteDatabase.update("bookshelf", contentValues, "_id=" + i5, null);
                        cursor3.close();
                        cursor2 = cursor3;
                    }
                }
                if (4 == mark2222.getType()) {
                    contentValues.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, Integer.valueOf(c));
                }
                sQLiteDatabase.insert("bookshelf", null, contentValues);
                cursor3.close();
                cursor2 = cursor3;
            }
            if (cursor2 != null) {
                cursor2.close();
                return;
            }
            return;
        } catch (Throwable th7) {
            th = th7;
            if (cursor3 != null) {
                cursor3.close();
            }
            throw th;
        }
        cursor = rawQuery;
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD book_id long default 0");
        arrayList = new ArrayList();
        cursor = sQLiteDatabase.query("bookshelf", new String[]{"path", "name", "startpoint", MessageKey.MSG_DATE, "percent", SocialConstants.PARAM_COMMENT, "author", "time", "encoding", "type", "length", ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, "newcontent", "truepagebytes", "truepagefont", "truepagecurpageindex", "add_time", "book_id"}, null, null, null, null, null);
        if (!cursor.moveToFirst()) {
            do {
                string = cursor.getString(0);
                string2 = cursor.getString(1);
                j = 0;
                string3 = cursor.getString(2);
                str = null;
                parseLong = Long.parseLong(string3);
                string4 = cursor.getString(3);
                string5 = cursor.getString(4);
                string6 = cursor.getString(5);
                string7 = cursor.getString(6);
                j2 = cursor.getLong(7);
                i = cursor.getInt(8);
                i2 = cursor.getInt(9);
                j = cursor.getLong(10);
                i3 = cursor.getInt(11);
                if (cursor.getInt(12) == 0) {
                }
                f = cursor.getFloat(13);
                i4 = cursor.getInt(14);
                j3 = cursor.getLong(15);
                j4 = cursor.getLong(16);
                j5 = cursor.getLong(17);
                switch (i2) {
                    case 1:
                    case 2:
                        break;
                    case 3:
                        downloadMark22222 = new DownloadMark(string, string2, false);
                        downloadMark22222.setCategoryID(i3);
                        arrayList.add(downloadMark22222);
                        break;
                    case 4:
                        j.c++;
                        break;
                }
                arrayList.add(new LocalMark(i2, string, j, string2, string4).setStartPoint(parseLong).setStarPointStr(str).setPercentStr(string5).setDescriptionStr(string6).setAuthor(string7).setReadTime(j2).setEncoding(i).setCategoryID(i3).setHasNewContent(z).setTurePageBytes(f).setTurePageFont(i4).setTurePageCurIndex(j3).setOperateTime(j4).setBookId(j5));
            } while (cursor.moveToNext());
        } else if (cursor != null) {
            cursor.close();
        }
        if (cursor != null) {
            cursor.close();
        }
        a = o.c().a();
        for (Mark downloadMark222222 : arrayList) {
            type = downloadMark222222.getType();
            if (downloadMark222222 == null) {
                if (type != 3) {
                }
                size = a.size();
                for (i2 = 0; i2 < size; i2++) {
                    downloadBookTask = (DownloadBookTask) a.get(i2);
                    if (downloadBookTask == null) {
                    }
                }
            }
        }
        cursor3 = null;
        cursor2 = cursor3;
        for (Mark mark22222 : arrayList) {
            contentValues = new ContentValues();
            contentValues.put("path", mark22222.getId());
            contentValues.put("name", mark22222.getBookName());
            if (mark22222.getStarPointStr() != null) {
                contentValues.put("startpoint", mark22222.getStarPointStr());
            } else {
                contentValues.put("startpoint", String.valueOf(mark22222.getStartPoint()));
            }
            contentValues.put(MessageKey.MSG_DATE, mark22222.getDataStr());
            contentValues.put("percent", mark22222.getPercentStr());
            contentValues.put(SocialConstants.PARAM_COMMENT, mark22222.getDescriptionStr());
            contentValues.put("author", mark22222.getAuthor());
            contentValues.put("time", String.valueOf(mark22222.getReadTime()));
            j.p = mark22222.getReadTime();
            contentValues.put("encoding", Integer.valueOf(mark22222.getEncoding()));
            contentValues.put("type", Integer.valueOf(mark22222.getType()));
            contentValues.put("length", String.valueOf(mark22222.getFileLength()));
            if (mark22222.hasNewContent()) {
            }
            contentValues.put("newcontent", Integer.valueOf(mark22222.hasNewContent() ? 1 : 0));
            contentValues.put("truepagebytes", Float.valueOf(mark22222.getTurePageBytes()));
            contentValues.put("truepagefont", Integer.valueOf(mark22222.getTurePageFont()));
            contentValues.put("truepagecurpageindex", Long.valueOf(mark22222.getTurePageCurIndex()));
            contentValues.put("add_time", String.valueOf(mark22222.getOperateTime()));
            contentValues.put("book_id", String.valueOf(mark22222.getBookId()));
            cursor3 = sQLiteDatabase.query("bookshelf", new String[]{"_id", "book_id"}, "path= '" + mark22222.getId().replace("'", "''") + "'", null, null, null, null, null);
            if (cursor3 != null) {
                if (cursor3.getCount() > 0) {
                    cursor3.moveToFirst();
                    i5 = cursor3.getInt(0);
                    string8 = cursor3.getString(1);
                    if ("0".equals(string8)) {
                        contentValues.put("book_id", string8);
                    }
                    update = (long) sQLiteDatabase.update("bookshelf", contentValues, "_id=" + i5, null);
                    cursor3.close();
                    cursor2 = cursor3;
                }
            }
            if (4 == mark22222.getType()) {
                contentValues.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE, Integer.valueOf(c));
            }
            sQLiteDatabase.insert("bookshelf", null, contentValues);
            cursor3.close();
            cursor2 = cursor3;
        }
        if (cursor2 != null) {
            cursor2.close();
            return;
        }
        return;
        if (cursor3 != null) {
            cursor3.close();
        }
        throw th;
    }

    private void h(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select _id from usermark", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(6);
                        rawQuery.close();
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update5To6 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        c(sQLiteDatabase);
                        b(sQLiteDatabase);
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            f.a("DB", " update5To6 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            c(sQLiteDatabase);
            b(sQLiteDatabase);
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        c(sQLiteDatabase);
        b(sQLiteDatabase);
    }

    private void i(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Cursor cursor;
        HashMap hashMap;
        SQLiteDatabase sQLiteDatabase2;
        long longValue;
        int intValue;
        ContentValues contentValues;
        Throwable th;
        Cursor cursor2 = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select update_time from bookshelf", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(7);
                        rawQuery.close();
                        if (cursor2 != null) {
                            cursor2.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update6To7 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                            cursor = rawQuery;
                            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD update_time long default 0");
                            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD update_chapter  text");
                            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD book_isfinished integer default 0");
                            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD channel_id  text");
                            hashMap = new HashMap();
                            sQLiteDatabase2 = sQLiteDatabase;
                            cursor = sQLiteDatabase2.query("bookshelf", new String[]{"book_id", "book_isfinished"}, null, null, null, null, null);
                            if (!!cursor.moveToFirst()) {
                                do {
                                    hashMap.put(Long.valueOf(cursor.getLong(0)), Integer.valueOf(cursor.getInt(1)));
                                } while (cursor.moveToNext());
                            }
                            if (cursor != null) {
                                cursor.close();
                            }
                            for (OnlineTag onlineTag : v.b().c()) {
                                if (hashMap.containsKey(Long.valueOf(onlineTag.k()))) {
                                    hashMap.put(Long.valueOf(onlineTag.k()), Integer.valueOf(onlineTag.w()));
                                }
                            }
                            for (Long longValue2 : hashMap.keySet()) {
                                longValue = longValue2.longValue();
                                intValue = ((Integer) hashMap.get(Long.valueOf(longValue))).intValue();
                                contentValues = new ContentValues();
                                contentValues.put("book_isfinished", Integer.valueOf(intValue));
                                sQLiteDatabase.update("bookshelf", contentValues, "book_id=" + longValue, cursor2);
                            }
                        }
                        cursor = rawQuery;
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD update_time long default 0");
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD update_chapter  text");
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD book_isfinished integer default 0");
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD channel_id  text");
                        hashMap = new HashMap();
                        sQLiteDatabase2 = sQLiteDatabase;
                        cursor = sQLiteDatabase2.query("bookshelf", new String[]{"book_id", "book_isfinished"}, null, null, null, null, null);
                        if (!cursor.moveToFirst()) {
                            do {
                                hashMap.put(Long.valueOf(cursor.getLong(0)), Integer.valueOf(cursor.getInt(1)));
                            } while (cursor.moveToNext());
                        } else if (cursor != null) {
                            cursor.close();
                        }
                        if (cursor != null) {
                            cursor.close();
                        }
                        for (OnlineTag onlineTag2 : v.b().c()) {
                            if (hashMap.containsKey(Long.valueOf(onlineTag2.k()))) {
                                hashMap.put(Long.valueOf(onlineTag2.k()), Integer.valueOf(onlineTag2.w()));
                            }
                        }
                        while (r1.hasNext()) {
                            longValue = longValue2.longValue();
                            intValue = ((Integer) hashMap.get(Long.valueOf(longValue))).intValue();
                            contentValues = new ContentValues();
                            contentValues.put("book_isfinished", Integer.valueOf(intValue));
                            sQLiteDatabase.update("bookshelf", contentValues, "book_id=" + longValue, cursor2);
                        }
                    } catch (Throwable th2) {
                        cursor2 = rawQuery;
                        th = th2;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
                cursor = rawQuery;
                sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD update_time long default 0");
                sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD update_chapter  text");
                sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD book_isfinished integer default 0");
                sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD channel_id  text");
                hashMap = new HashMap();
                sQLiteDatabase2 = sQLiteDatabase;
                cursor = sQLiteDatabase2.query("bookshelf", new String[]{"book_id", "book_isfinished"}, null, null, null, null, null);
                if (!cursor.moveToFirst()) {
                    do {
                        hashMap.put(Long.valueOf(cursor.getLong(0)), Integer.valueOf(cursor.getInt(1)));
                    } while (cursor.moveToNext());
                }
                if (cursor != null) {
                    cursor.close();
                }
                for (OnlineTag onlineTag22 : v.b().c()) {
                    if (hashMap.containsKey(Long.valueOf(onlineTag22.k()))) {
                        hashMap.put(Long.valueOf(onlineTag22.k()), Integer.valueOf(onlineTag22.w()));
                    }
                }
                while (r1.hasNext()) {
                    longValue = longValue2.longValue();
                    intValue = ((Integer) hashMap.get(Long.valueOf(longValue))).intValue();
                    contentValues = new ContentValues();
                    contentValues.put("book_isfinished", Integer.valueOf(intValue));
                    sQLiteDatabase.update("bookshelf", contentValues, "book_id=" + longValue, cursor2);
                }
            }
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor2;
            f.a("DB", " update6To7 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
                cursor = rawQuery;
                sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD update_time long default 0");
                sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD update_chapter  text");
                sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD book_isfinished integer default 0");
                sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD channel_id  text");
                hashMap = new HashMap();
                sQLiteDatabase2 = sQLiteDatabase;
                cursor = sQLiteDatabase2.query("bookshelf", new String[]{"book_id", "book_isfinished"}, null, null, null, null, null);
                if (!cursor.moveToFirst()) {
                    do {
                        hashMap.put(Long.valueOf(cursor.getLong(0)), Integer.valueOf(cursor.getInt(1)));
                    } while (cursor.moveToNext());
                }
                if (cursor != null) {
                    cursor.close();
                }
                for (OnlineTag onlineTag222 : v.b().c()) {
                    if (hashMap.containsKey(Long.valueOf(onlineTag222.k()))) {
                        hashMap.put(Long.valueOf(onlineTag222.k()), Integer.valueOf(onlineTag222.w()));
                    }
                }
                while (r1.hasNext()) {
                    longValue = longValue2.longValue();
                    intValue = ((Integer) hashMap.get(Long.valueOf(longValue))).intValue();
                    contentValues = new ContentValues();
                    contentValues.put("book_isfinished", Integer.valueOf(intValue));
                    sQLiteDatabase.update("bookshelf", contentValues, "book_id=" + longValue, cursor2);
                }
            }
            cursor = rawQuery;
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD update_time long default 0");
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD update_chapter  text");
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD book_isfinished integer default 0");
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD channel_id  text");
            hashMap = new HashMap();
            sQLiteDatabase2 = sQLiteDatabase;
            cursor = sQLiteDatabase2.query("bookshelf", new String[]{"book_id", "book_isfinished"}, null, null, null, null, null);
            if (!cursor.moveToFirst()) {
                do {
                    hashMap.put(Long.valueOf(cursor.getLong(0)), Integer.valueOf(cursor.getInt(1)));
                } while (cursor.moveToNext());
            } else if (cursor != null) {
                cursor.close();
            }
            if (cursor != null) {
                cursor.close();
            }
            for (OnlineTag onlineTag2222 : v.b().c()) {
                if (hashMap.containsKey(Long.valueOf(onlineTag2222.k()))) {
                    hashMap.put(Long.valueOf(onlineTag2222.k()), Integer.valueOf(onlineTag2222.w()));
                }
            }
            while (r1.hasNext()) {
                longValue = longValue2.longValue();
                intValue = ((Integer) hashMap.get(Long.valueOf(longValue))).intValue();
                contentValues = new ContentValues();
                contentValues.put("book_isfinished", Integer.valueOf(intValue));
                sQLiteDatabase.update("bookshelf", contentValues, "book_id=" + longValue, cursor2);
            }
        } catch (Throwable th3) {
            th = th3;
            if (cursor2 != null) {
                cursor2.close();
            }
            throw th;
        }
        cursor = rawQuery;
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD update_time long default 0");
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD update_chapter  text");
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD book_isfinished integer default 0");
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD channel_id  text");
        hashMap = new HashMap();
        try {
            sQLiteDatabase2 = sQLiteDatabase;
            cursor = sQLiteDatabase2.query("bookshelf", new String[]{"book_id", "book_isfinished"}, null, null, null, null, null);
            if (!cursor.moveToFirst()) {
                do {
                    hashMap.put(Long.valueOf(cursor.getLong(0)), Integer.valueOf(cursor.getInt(1)));
                } while (cursor.moveToNext());
            } else if (cursor != null) {
                cursor.close();
            }
            if (cursor != null) {
                cursor.close();
            }
            for (OnlineTag onlineTag22222 : v.b().c()) {
                if (hashMap.containsKey(Long.valueOf(onlineTag22222.k()))) {
                    hashMap.put(Long.valueOf(onlineTag22222.k()), Integer.valueOf(onlineTag22222.w()));
                }
            }
            while (r1.hasNext()) {
                longValue = longValue2.longValue();
                intValue = ((Integer) hashMap.get(Long.valueOf(longValue))).intValue();
                contentValues = new ContentValues();
                contentValues.put("book_isfinished", Integer.valueOf(intValue));
                sQLiteDatabase.update("bookshelf", contentValues, "book_id=" + longValue, cursor2);
            }
        } catch (Throwable th4) {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private void j(SQLiteDatabase sQLiteDatabase) {
        Exception e;
        List<OnlineTag> c;
        ContentValues contentValues;
        Throwable th;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select cover_url from bookshelf", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(8);
                        rawQuery.close();
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update7To8 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD cover_url  text");
                        c = v.b().c();
                        contentValues = new ContentValues();
                        for (OnlineTag onlineTag : c) {
                            contentValues.put("cover_url", onlineTag.u());
                            sQLiteDatabase.update("bookshelf", contentValues, "book_id=" + onlineTag.k(), cursor);
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD sortindex integer default 0");
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            f.a("DB", " update7To8 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD cover_url  text");
            c = v.b().c();
            contentValues = new ContentValues();
            for (OnlineTag onlineTag2 : c) {
                contentValues.put("cover_url", onlineTag2.u());
                sQLiteDatabase.update("bookshelf", contentValues, "book_id=" + onlineTag2.k(), cursor);
            }
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD sortindex integer default 0");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD cover_url  text");
        c = v.b().c();
        contentValues = new ContentValues();
        for (OnlineTag onlineTag22 : c) {
            contentValues.put("cover_url", onlineTag22.u());
            sQLiteDatabase.update("bookshelf", contentValues, "book_id=" + onlineTag22.k(), cursor);
        }
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD sortindex integer default 0");
    }

    private void k(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select downloadinfo from bookshelf", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(9);
                        rawQuery.close();
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update8To9 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD downloadinfo text");
                        rawQuery = sQLiteDatabase.rawQuery("select incloud from bookshelf", null);
                        if (rawQuery != null) {
                            try {
                                if (rawQuery.getCount() > 0) {
                                    sQLiteDatabase.setVersion(9);
                                    rawQuery.close();
                                    if (cursor == null) {
                                        cursor.close();
                                        return;
                                    }
                                    return;
                                }
                            } catch (Exception e3) {
                                e = e3;
                                cursor = rawQuery;
                                try {
                                    f.a("DB", " update8To9 :" + e.toString());
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD incloud   integer default 0");
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (cursor != null) {
                                        cursor.close();
                                    }
                                    throw th;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                cursor = rawQuery;
                                if (cursor != null) {
                                    cursor.close();
                                }
                                throw th;
                            }
                        }
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD incloud   integer default 0");
                    } catch (Throwable th4) {
                        th = th4;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e4) {
            e = e4;
            rawQuery = cursor;
            f.a("DB", " update8To9 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD downloadinfo text");
            rawQuery = sQLiteDatabase.rawQuery("select incloud from bookshelf", null);
            if (rawQuery != null) {
                if (rawQuery.getCount() > 0) {
                    sQLiteDatabase.setVersion(9);
                    rawQuery.close();
                    if (cursor == null) {
                        cursor.close();
                        return;
                    }
                    return;
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD incloud   integer default 0");
        } catch (Throwable th5) {
            th = th5;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD downloadinfo text");
        try {
            rawQuery = sQLiteDatabase.rawQuery("select incloud from bookshelf", null);
            if (rawQuery != null) {
                if (rawQuery.getCount() > 0) {
                    sQLiteDatabase.setVersion(9);
                    rawQuery.close();
                    if (cursor == null) {
                        cursor.close();
                        return;
                    }
                    return;
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e5) {
            e = e5;
            f.a("DB", " update8To9 :" + e.toString());
            if (cursor != null) {
                cursor.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD incloud   integer default 0");
        }
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD incloud   integer default 0");
    }

    private void l(SQLiteDatabase sQLiteDatabase) {
        Exception e;
        Throwable th;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select lastread_chapter from bookshelf", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(10);
                        rawQuery.close();
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update9To10 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD lastread_chapter text");
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            f.a("DB", " update9To10 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD lastread_chapter text");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD lastread_chapter text");
    }

    private void m(SQLiteDatabase sQLiteDatabase) {
        Exception e;
        Throwable th;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select private_property from bookshelf", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(11);
                        rawQuery.close();
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update10To11 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD private_property integer default 1");
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            f.a("DB", " update10To11 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD private_property integer default 1");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD private_property integer default 1");
    }

    private void n(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select LimitFreeEndTime from bookshelf", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(12);
                        rawQuery.close();
                        if (cursor != null) {
                            cursor.close();
                            return;
                        }
                        return;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update11To12 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD LimitFreeEndTime text");
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD cloud_del_tag integer default 0");
                        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD discount integer default 100");
                    } catch (Throwable th2) {
                        th = th2;
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        throw th;
                    }
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            f.a("DB", " update11To12 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD LimitFreeEndTime text");
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD cloud_del_tag integer default 0");
            sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD discount integer default 100");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD LimitFreeEndTime text");
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD cloud_del_tag integer default 0");
        sQLiteDatabase.execSQL("ALTER TABLE bookshelf ADD discount integer default 100");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.database.sqlite.SQLiteDatabase r1, int r2) {
        /*
        r0 = this;
        switch(r2) {
            case 1: goto L_0x0004;
            case 2: goto L_0x0007;
            case 3: goto L_0x000a;
            case 4: goto L_0x000d;
            case 5: goto L_0x0010;
            case 6: goto L_0x0013;
            case 7: goto L_0x0016;
            case 8: goto L_0x0019;
            case 9: goto L_0x001c;
            case 10: goto L_0x001f;
            case 11: goto L_0x0022;
            default: goto L_0x0003;
        };
    L_0x0003:
        return;
    L_0x0004:
        r0.d(r1);
    L_0x0007:
        r0.e(r1);
    L_0x000a:
        r0.f(r1);
    L_0x000d:
        r0.g(r1);
    L_0x0010:
        r0.h(r1);
    L_0x0013:
        r0.i(r1);
    L_0x0016:
        r0.j(r1);
    L_0x0019:
        r0.k(r1);
    L_0x001c:
        r0.l(r1);
    L_0x001f:
        r0.m(r1);
    L_0x0022:
        r0.n(r1);
        goto L_0x0003;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.i.a(android.database.sqlite.SQLiteDatabase, int):void");
    }
}
