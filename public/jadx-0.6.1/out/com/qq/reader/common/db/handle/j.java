package com.qq.reader.common.db.handle;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.text.TextUtils;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.readertask.g;
import com.tencent.qalsdk.util.BaseApplication;

/* compiled from: ChannelHandler */
public class j {
    private static a a;
    private static j b;

    /* compiled from: ChannelHandler */
    private class a extends c {
        final /* synthetic */ j a;

        public a(j jVar, String str, CursorFactory cursorFactory, int i) {
            this.a = jVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    public synchronized void a(java.util.List<com.qq.reader.common.monitor.a.a> r7) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:31:0x009d in {3, 15, 21, 25, 30, 33, 35, 37, 38, 39, 40, 41} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.rerun(BlockProcessor.java:44)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:57)
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
        r6 = this;
        monitor-enter(r6);
        if (r7 != 0) goto L_0x0005;
    L_0x0003:
        monitor-exit(r6);
        return;
    L_0x0005:
        r6.b();	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        r0 = a;	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        r1 = r0.a();	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        if (r1 == 0) goto L_0x0066;	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
    L_0x0010:
        r1.beginTransaction();	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        r2 = r7.iterator();	 Catch:{ Exception -> 0x0044, all -> 0x009c }
    L_0x0017:
        r0 = r2.hasNext();	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        if (r0 == 0) goto L_0x006f;	 Catch:{ Exception -> 0x0044, all -> 0x009c }
    L_0x001d:
        r0 = r2.next();	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r0 = (com.qq.reader.common.monitor.a.a) r0;	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r3 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r3.<init>();	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r4 = "onlineid";	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r5 = r0.a();	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r3.put(r4, r5);	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r4 = "channel_id";	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r0 = r0.b();	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r3.put(r4, r0);	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r0 = "channel";	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r4 = 0;	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r1.insert(r0, r4, r3);	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        goto L_0x0017;
    L_0x0044:
        r0 = move-exception;
        r2 = "DB";	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r3.<init>();	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r4 = "addAll with exception : ";	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        com.qq.reader.common.monitor.f.a(r2, r0);	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r1.endTransaction();
    L_0x0066:
        r0 = a;
        r0.c();
        goto L_0x0003;
    L_0x006c:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x006f:
        r1.setTransactionSuccessful();	 Catch:{ Exception -> 0x0044, all -> 0x009c }
        r1.endTransaction();
        goto L_0x0066;
    L_0x0076:
        r0 = move-exception;
        r1 = "DB";	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        r2.<init>();	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        r3 = "addAll  with exception : ";	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        r0 = r2.append(r0);	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        com.qq.reader.common.monitor.f.a(r1, r0);	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        r0 = a;
        r0.c();
        goto L_0x0003;
    L_0x009c:
        r0 = move-exception;
        r1.endTransaction();	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        throw r0;	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
    L_0x00a1:
        r0 = move-exception;
        r1 = a;	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        r1.c();	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        throw r0;	 Catch:{ Exception -> 0x0076, all -> 0x00a1 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.j.a(java.util.List):void");
    }

    public static synchronized j a() {
        j jVar;
        synchronized (j.class) {
            if (b == null) {
                b = new j();
            }
            jVar = b;
        }
        return jVar;
    }

    private j() {
        a = new a(this, com.qq.reader.common.c.a.aS, null, 1);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table if not exists channel (_id integer primary key autoincrement,onlineid text not null,channel_id  text);");
        } catch (Exception e) {
            f.a("DB", "createTable channel db with exception : " + e.getMessage());
        }
    }

    public synchronized boolean b() {
        boolean z;
        try {
            SQLiteDatabase a = a.a();
            a.execSQL("drop table if exists channel");
            a(a);
            a.c();
            z = true;
        } catch (Exception e) {
            f.a("DB", "clear with exception : " + e.getMessage());
            z = false;
            a.c();
        } catch (Throwable th) {
            a.c();
        }
        return z;
    }

    public synchronized boolean a(String str) {
        boolean z = false;
        synchronized (this) {
            int delete;
            try {
                delete = a.a().delete("channel", "onlineid= '" + str.replace("'", "''") + "'", null);
                a.c();
            } catch (Exception e) {
                f.a("DB", "removeBookChannelByBookId with exception : " + e.getMessage());
                a.c();
                delete = 0;
            } catch (Throwable th) {
                a.c();
            }
            if (delete > 0) {
                z = true;
            }
        }
        return z;
    }

    public synchronized com.qq.reader.common.monitor.a.a b(String str) {
        com.qq.reader.common.monitor.a.a aVar;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        synchronized (this) {
            Cursor query;
            try {
                query = a.a().query("channel", new String[]{"onlineid", BaseApplication.DATA_KEY_CHANNEL_ID}, "onlineid= '" + str.replace("'", "''") + "'", null, null, null, null);
                try {
                    if (query.moveToFirst()) {
                        aVar = new com.qq.reader.common.monitor.a.a(query.getString(0), query.getString(1));
                        if (query != null) {
                            query.close();
                        }
                        if (a != null) {
                            a.c();
                        }
                    } else {
                        if (query != null) {
                            query.close();
                        }
                        if (a != null) {
                            a.c();
                        }
                        aVar = null;
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.b("DB", "getBookChannelWithBookId with exception: " + e.toString());
                        if (query != null) {
                            query.close();
                        }
                        if (a != null) {
                            a.c();
                        }
                        aVar = null;
                        return aVar;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor = query;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (a != null) {
                            a.c();
                        }
                        throw th;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                f.b("DB", "getBookChannelWithBookId with exception: " + e.toString());
                if (query != null) {
                    query.close();
                }
                if (a != null) {
                    a.c();
                }
                aVar = null;
                return aVar;
            } catch (Throwable th3) {
                th = th3;
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    a.c();
                }
                throw th;
            }
        }
        return aVar;
    }

    public synchronized void a(com.qq.reader.common.monitor.a.a aVar) {
        if (!(TextUtils.isEmpty(aVar.a()) || TextUtils.isEmpty(aVar.b()))) {
            g.a().a(new ChannelHandler$1(this, aVar));
        }
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
    }
}
