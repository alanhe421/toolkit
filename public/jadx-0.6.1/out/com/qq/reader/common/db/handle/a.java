package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookshelf.b;
import com.tencent.android.tpush.common.MessageKey;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: ActivateShelfHandler */
public class a extends com.qq.reader.common.db.a {
    protected static c b;
    private static a c;
    private ArrayList<b> d;
    private volatile b e = null;
    private final long f = 259200000;

    /* compiled from: ActivateShelfHandler */
    private class a extends c {
        final /* synthetic */ a a;

        public a(a aVar, String str, CursorFactory cursorFactory, int i) {
            this.a = aVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (c == null) {
                c = new a();
            }
            aVar = c;
        }
        return aVar;
    }

    private a() {
        b = new a(this, com.qq.reader.common.c.a.bL, null, 1);
        this.d = new ArrayList();
        e();
        d();
    }

    public synchronized void a(b bVar) {
        if (bVar != null) {
            if (d(bVar)) {
                this.d.add(bVar);
            }
        }
    }

    public void b() {
        int i;
        this.e = null;
        long bI = d.bI(ReaderApplication.getApplicationImp());
        long currentTimeMillis = System.currentTimeMillis();
        if (bI > currentTimeMillis) {
            i = 0;
        } else {
            i = 1;
        }
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (currentTimeMillis > bVar.c() && currentTimeMillis < bVar.d()) {
                if (i != 0 || bVar.a() == 1) {
                    if (bVar.a() == 1) {
                        i.a("event_A151", null, ReaderApplication.getApplicationImp());
                    }
                    this.e = bVar;
                    if (this.e != null && this.e.a() == 0) {
                        this.e.a(1);
                        c(this.e);
                        d.l(ReaderApplication.getApplicationImp(), 259200000 + currentTimeMillis);
                        return;
                    }
                }
            }
        }
        if (this.e != null) {
        }
    }

    public b c() {
        return this.e;
    }

    private synchronized void d() {
        long currentTimeMillis = System.currentTimeMillis();
        Iterator it = this.d.iterator();
        while (it.hasNext()) {
            b bVar = (b) it.next();
            if (bVar.d() < currentTimeMillis) {
                it.remove();
                b(bVar);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void e() {
        /*
        r12 = this;
        r8 = 0;
        monitor-enter(r12);
        r0 = b;	 Catch:{ Exception -> 0x009b, all -> 0x00c9 }
        r0 = r0.a();	 Catch:{ Exception -> 0x009b, all -> 0x00c9 }
        r1 = 8;
        r2 = new java.lang.String[r1];	 Catch:{ Exception -> 0x009b, all -> 0x00c9 }
        r1 = 0;
        r3 = "_id";
        r2[r1] = r3;	 Catch:{ Exception -> 0x009b, all -> 0x00c9 }
        r1 = 1;
        r3 = "b_net_id";
        r2[r1] = r3;	 Catch:{ Exception -> 0x009b, all -> 0x00c9 }
        r1 = 2;
        r3 = "start_time";
        r2[r1] = r3;	 Catch:{ Exception -> 0x009b, all -> 0x00c9 }
        r1 = 3;
        r3 = "end_time";
        r2[r1] = r3;	 Catch:{ Exception -> 0x009b, all -> 0x00c9 }
        r1 = 4;
        r3 = "link_url";
        r2[r1] = r3;	 Catch:{ Exception -> 0x009b, all -> 0x00c9 }
        r1 = 5;
        r3 = "image_url";
        r2[r1] = r3;	 Catch:{ Exception -> 0x009b, all -> 0x00c9 }
        r1 = 6;
        r3 = "content";
        r2[r1] = r3;	 Catch:{ Exception -> 0x009b, all -> 0x00c9 }
        r1 = 7;
        r3 = "showed";
        r2[r1] = r3;	 Catch:{ Exception -> 0x009b, all -> 0x00c9 }
        r1 = "activateshelf";
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = "_id ASC";
        r8 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x009b, all -> 0x00c9 }
        r0 = r8.moveToFirst();	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        if (r0 == 0) goto L_0x008f;
    L_0x0050:
        r0 = 1;
        r2 = r8.getLong(r0);	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        r0 = 2;
        r4 = r8.getLong(r0);	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        r0 = 3;
        r6 = r8.getLong(r0);	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        r0 = 4;
        r0 = r8.getString(r0);	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        r1 = 5;
        r9 = r8.getString(r1);	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        r1 = 6;
        r10 = r8.getString(r1);	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        r1 = 7;
        r11 = r8.getInt(r1);	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        r1 = new com.qq.reader.module.bookshelf.b;	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        r1.<init>(r2, r4, r6);	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        r1.a(r0);	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        r1.b(r9);	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        r1.c(r10);	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        r1.a(r11);	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        r0 = r12.d;	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        r0.add(r1);	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        r0 = r8.moveToNext();	 Catch:{ Exception -> 0x00d8, all -> 0x00c9 }
        if (r0 != 0) goto L_0x0050;
    L_0x008f:
        if (r8 == 0) goto L_0x0094;
    L_0x0091:
        r8.close();	 Catch:{ all -> 0x00c6 }
    L_0x0094:
        r0 = b;	 Catch:{ all -> 0x00c6 }
        r0.c();	 Catch:{ all -> 0x00c6 }
    L_0x0099:
        monitor-exit(r12);
        return;
    L_0x009b:
        r0 = move-exception;
        r1 = r8;
    L_0x009d:
        r2 = "DB";
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d5 }
        r3.<init>();	 Catch:{ all -> 0x00d5 }
        r4 = "getAllAutoMarkDB with exception: ";
        r3 = r3.append(r4);	 Catch:{ all -> 0x00d5 }
        r0 = r0.toString();	 Catch:{ all -> 0x00d5 }
        r0 = r3.append(r0);	 Catch:{ all -> 0x00d5 }
        r0 = r0.toString();	 Catch:{ all -> 0x00d5 }
        com.qq.reader.common.monitor.f.a(r2, r0);	 Catch:{ all -> 0x00d5 }
        if (r1 == 0) goto L_0x00c0;
    L_0x00bd:
        r1.close();	 Catch:{ all -> 0x00c6 }
    L_0x00c0:
        r0 = b;	 Catch:{ all -> 0x00c6 }
        r0.c();	 Catch:{ all -> 0x00c6 }
        goto L_0x0099;
    L_0x00c6:
        r0 = move-exception;
        monitor-exit(r12);
        throw r0;
    L_0x00c9:
        r0 = move-exception;
    L_0x00ca:
        if (r8 == 0) goto L_0x00cf;
    L_0x00cc:
        r8.close();	 Catch:{ all -> 0x00c6 }
    L_0x00cf:
        r1 = b;	 Catch:{ all -> 0x00c6 }
        r1.c();	 Catch:{ all -> 0x00c6 }
        throw r0;	 Catch:{ all -> 0x00c6 }
    L_0x00d5:
        r0 = move-exception;
        r8 = r1;
        goto L_0x00ca;
    L_0x00d8:
        r0 = move-exception;
        r1 = r8;
        goto L_0x009d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.a.e():void");
    }

    public synchronized boolean b(b bVar) {
        boolean z = false;
        synchronized (this) {
            if (bVar != null) {
                if (a(b, "activateshelf", "b_net_id= '" + bVar.b() + "'")) {
                    this.d.remove(bVar);
                    this.e = null;
                    z = true;
                }
            }
        }
        return z;
    }

    private synchronized boolean c(b bVar) {
        ContentValues contentValues;
        contentValues = new ContentValues();
        contentValues.put("showed", Integer.valueOf(bVar.a()));
        return a(b, "activateshelf", contentValues, "b_net_id=" + bVar.b());
    }

    private synchronized boolean d(b bVar) {
        ContentValues contentValues;
        contentValues = new ContentValues();
        contentValues.put("b_net_id", Long.valueOf(bVar.b()));
        contentValues.put("start_time", Long.valueOf(bVar.c()));
        contentValues.put("end_time", Long.valueOf(bVar.d()));
        contentValues.put("link_url", bVar.e());
        contentValues.put("image_url", bVar.f());
        contentValues.put(MessageKey.MSG_CONTENT, bVar.g());
        contentValues.put("showed", Integer.valueOf(bVar.a()));
        return a(b, "activateshelf", contentValues);
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists activateshelf (_id integer primary key autoincrement,b_net_id long default 0,start_time long default 0,end_time long default 0,link_url text not null,image_url text not null,content text not null,showed integer default 0);");
        sQLiteDatabase.execSQL("create unique index if not exists idx on activateshelf (b_net_id);");
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
    }
}
