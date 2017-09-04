package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.framework.a.b;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import java.util.ArrayList;
import java.util.List;

/* compiled from: NoteBaseDBHandler */
public abstract class t {
    private String a = "NoteBaseDBHandler";

    public abstract c a(String str);

    public abstract boolean a(String str, long j);

    public synchronized void b(java.lang.String r6) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Exception block dominator not found, method:com.qq.reader.common.db.handle.t.b(java.lang.String):void. bs: [B:6:0x0008, B:28:0x003e]
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:86)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
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
        r5 = this;
        monitor-enter(r5);
        r1 = r5.a(r6);	 Catch:{ all -> 0x0012 }
        r1.a();	 Catch:{ Exception -> 0x0015 }
        r1.c();	 Catch:{ Throwable -> 0x000d }
    L_0x000b:
        monitor-exit(r5);
        return;
    L_0x000d:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0012 }
        goto L_0x000b;
    L_0x0012:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x0015:
        r0 = move-exception;
        r2 = "DB";	 Catch:{ all -> 0x003d }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x003d }
        r3.<init>();	 Catch:{ all -> 0x003d }
        r4 = "BookmarkHandle checkDBUpdate with exception: ";	 Catch:{ all -> 0x003d }
        r3 = r3.append(r4);	 Catch:{ all -> 0x003d }
        r0 = r0.toString();	 Catch:{ all -> 0x003d }
        r0 = r3.append(r0);	 Catch:{ all -> 0x003d }
        r0 = r0.toString();	 Catch:{ all -> 0x003d }
        com.qq.reader.common.monitor.f.a(r2, r0);	 Catch:{ all -> 0x003d }
        r1.c();	 Catch:{ Throwable -> 0x0038 }
        goto L_0x000b;
    L_0x0038:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0012 }
        goto L_0x000b;
    L_0x003d:
        r0 = move-exception;
        r1.c();	 Catch:{ Throwable -> 0x0042 }
    L_0x0041:
        throw r0;	 Catch:{ all -> 0x0012 }
    L_0x0042:
        r1 = move-exception;	 Catch:{ all -> 0x0012 }
        r1.printStackTrace();	 Catch:{ all -> 0x0012 }
        goto L_0x0041;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.t.b(java.lang.String):void");
    }

    public synchronized boolean a(String str, long j, long j2, long j3, boolean z) {
        boolean z2;
        z2 = false;
        if (j != 0) {
            if (a(str, j)) {
                z2 = b(str, j, j2, j3, z);
            } else if (a(str, j, j2, j3)) {
                z2 = true;
            } else {
                z2 = b(str, j, j2, j3, z);
            }
        }
        return z2;
    }

    private synchronized boolean a(String str, long j, long j2, long j3) {
        boolean z = false;
        synchronized (this) {
            c a = a(str);
            try {
                SQLiteDatabase a2 = a.a();
                ContentValues contentValues = new ContentValues();
                contentValues.put("bookrealid", Long.valueOf(j));
                contentValues.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, Long.valueOf(j2));
                contentValues.put("timestamp", Long.valueOf(j3));
                if (a2.insert("note_table_book", null, contentValues) >= 0) {
                    z = true;
                }
                a.c();
            } catch (Exception e) {
                f.a("DB", "NoteBaseDBHandler insertBookNoteInfo with exception : " + e.getMessage());
                a.c();
            } catch (Throwable th) {
                a.c();
            }
        }
        return z;
    }

    public b b(String str, long j) {
        b c = c(str, j);
        if (c != null) {
            List d = d(str, j);
            c.a(d);
            c.a(d.size());
        }
        return c;
    }

    public synchronized b c(String str, long j) {
        Cursor query;
        b bVar;
        Exception e;
        Throwable th;
        c a = a(str);
        try {
            query = a.a().query("note_table_book", new String[]{ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, "timestamp"}, "bookrealid=" + j, null, null, null, null);
            if (query != null) {
                try {
                    if (query.getCount() > 0) {
                        query.moveToFirst();
                        bVar = new b();
                        bVar.a(j);
                        bVar.c(query.getInt(0));
                        bVar.b(query.getLong(1));
                    } else {
                        bVar = null;
                    }
                    query.close();
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", "NoteBaseDBHandler getBookNoteInfoExcludeNotes with exception : " + e.getMessage());
                        if (query != null) {
                            if (!query.isClosed()) {
                                query.close();
                            }
                        }
                        a.c();
                        bVar = null;
                        return bVar;
                    } catch (Throwable th2) {
                        th = th2;
                        if (!(query == null || query.isClosed())) {
                            query.close();
                        }
                        a.c();
                        throw th;
                    }
                }
            }
            bVar = null;
            if (query != null) {
                if (!query.isClosed()) {
                    query.close();
                }
            }
            a.c();
        } catch (Exception e3) {
            e = e3;
            query = null;
            f.a("DB", "NoteBaseDBHandler getBookNoteInfoExcludeNotes with exception : " + e.getMessage());
            if (query != null) {
                if (query.isClosed()) {
                    query.close();
                }
            }
            a.c();
            bVar = null;
            return bVar;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            query.close();
            a.c();
            throw th;
        }
        return bVar;
    }

    public List<com.qq.reader.readengine.model.b> a(long j, int i) {
        return a(null, "book_id= '" + j + "' and " + "note_type" + "=" + i);
    }

    private synchronized List<com.qq.reader.readengine.model.b> a(String str, String str2) {
        List arrayList;
        Exception e;
        Cursor cursor;
        Throwable th;
        arrayList = new ArrayList();
        c a = a(str);
        Cursor cursor2 = null;
        try {
            Cursor query;
            SQLiteDatabase a2 = a.a();
            if (a2 != null) {
                query = a2.query("note_table_name", new String[]{"_id", "book_id", "book_name", "start_point", "end_point", "select_text", "remarks", "book_date", "start_chapter", "start_offset", "end_chapter", "end_offset", "bookrealid", "note_type"}, str2, null, null, null, null);
                try {
                    if (query.moveToFirst()) {
                        do {
                            List list = arrayList;
                            list.add(new com.qq.reader.readengine.model.b((long) query.getInt(0), query.getLong(1), query.getString(2), query.getString(3), query.getString(4), query.getString(5), query.getString(6), query.getLong(7), query.getInt(8), query.getLong(9), query.getInt(10), query.getLong(11), query.getLong(12), query.getInt(13)));
                        } while (query.moveToNext());
                    }
                } catch (Exception e2) {
                    e = e2;
                    cursor = query;
                    try {
                        f.a("DB", "NoteBaseDBHandler getWritableDatabase with exception : " + e.getMessage());
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (a != null) {
                            a.c();
                        }
                        return arrayList;
                    } catch (Throwable th2) {
                        th = th2;
                        cursor2 = cursor;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (a != null) {
                            a.c();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    cursor2 = query;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (a != null) {
                        a.c();
                    }
                    throw th;
                }
            }
            query = null;
            if (query != null) {
                query.close();
            }
            if (a != null) {
                a.c();
            }
        } catch (Exception e3) {
            e = e3;
            cursor = null;
            f.a("DB", "NoteBaseDBHandler getWritableDatabase with exception : " + e.getMessage());
            if (cursor != null) {
                cursor.close();
            }
            if (a != null) {
                a.c();
            }
            return arrayList;
        } catch (Throwable th4) {
            th = th4;
            if (cursor2 != null) {
                cursor2.close();
            }
            if (a != null) {
                a.c();
            }
            throw th;
        }
        return arrayList;
    }

    public List<com.qq.reader.readengine.model.b> a(String str, long j, int i) {
        return a(str, "bookrealid= '" + j + "' and " + "note_type" + "= '" + i + "'");
    }

    public List<com.qq.reader.readengine.model.b> b(String str, long j, int i) {
        return a(str, "bookrealid= '" + j + "' and " + "note_type" + "= '" + i + "'");
    }

    public List<com.qq.reader.readengine.model.b> d(String str, long j) {
        return a(str, "bookrealid= '" + j + "'");
    }

    private synchronized boolean b(String str, long j, long j2, long j3, boolean z) {
        boolean z2;
        c a = a(str);
        try {
            SQLiteDatabase a2 = a.a();
            ContentValues contentValues = new ContentValues();
            if (z) {
                contentValues.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, Long.valueOf(j2));
            }
            contentValues.put("timestamp", Long.valueOf(j3));
            if (((long) a2.update("note_table_book", contentValues, "bookrealid=" + j, null)) > 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            a.c();
        } catch (Exception e) {
            f.a("DB", "NoteBaseDBHandler updateBookNoteInfo with exception : " + e.getMessage());
            z2 = false;
            a.c();
        } catch (Throwable th) {
            a.c();
        }
        return z2;
    }

    public synchronized boolean a(String str, long j, long j2) {
        boolean z;
        c a = a(str);
        try {
            if (a.a().delete("note_table_name", "_id= '" + j + "'", null) > 0) {
                z = true;
                a.c();
            } else {
                a.c();
                z = false;
            }
        } catch (Exception e) {
            f.a("DB", "NoteBaseDBHandler del with exception : " + e.getMessage());
            a.c();
        } catch (Throwable th) {
            a.c();
        }
        return z;
    }

    public synchronized long a(String str, com.qq.reader.readengine.model.b bVar) {
        long j;
        long j2;
        long j3 = 0;
        long j4 = 0;
        try {
            if (!(bVar.g() == null || bVar.g().equals(""))) {
                j3 = Long.parseLong(bVar.g());
            }
            if (!(bVar.h() == null || bVar.h().equals(""))) {
                j4 = Long.parseLong(bVar.h());
            }
            j = j3;
            j2 = j4;
        } catch (Exception e) {
            f.a(this.a, e.getMessage());
            j = 0;
            j2 = 0;
        }
        return a(str, j, j2, bVar.d(), bVar.e(), bVar.k(), bVar.c(), bVar.i(), (long) bVar.m(), bVar.n(), (long) bVar.o(), bVar.p(), bVar.q(), bVar.r());
    }

    public synchronized long a(String str, long j, long j2, String str2, String str3, long j3, String str4, long j4, long j5, long j6, long j7, long j8, long j9, int i) {
        long insert;
        c a = a(str);
        try {
            SQLiteDatabase a2 = a.a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("book_id", Long.valueOf(j3));
            contentValues.put("book_name", str4);
            contentValues.put("start_point", "" + j);
            contentValues.put("end_point", "" + j2);
            contentValues.put("bookrealid", Long.valueOf(j9));
            if (j5 != 0) {
                contentValues.put("start_chapter", Long.valueOf(j5));
            }
            if (j6 != 0) {
                contentValues.put("start_offset", Long.valueOf(j6));
            }
            if (j7 != 0) {
                contentValues.put("end_chapter", Long.valueOf(j7));
            }
            if (j8 != 0) {
                contentValues.put("end_offset", Long.valueOf(j8));
            }
            if (str2 == null) {
                contentValues.put("select_text", "");
            } else {
                contentValues.put("select_text", str2);
            }
            if (str3 == null) {
                contentValues.put("remarks", "");
            } else {
                contentValues.put("remarks", str3);
            }
            contentValues.put("book_date", Long.valueOf(j4));
            contentValues.put("note_type", Integer.valueOf(i));
            insert = a2.insert("note_table_name", null, contentValues);
            if (a != null) {
                a.c();
            }
        } catch (Exception e) {
            f.a("DB", "delAutoMarkDB with exception : " + e.getMessage());
            a.c();
            insert = -1;
            if (a != null) {
                a.c();
            }
        } catch (Throwable th) {
            if (a != null) {
                a.c();
            }
        }
        return insert;
    }

    public synchronized int a(String str, String str2, String str3, long j, long j2) {
        int update;
        c a = a(str);
        try {
            SQLiteDatabase a2 = a.a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("book_date", str3);
            contentValues.put("remarks", str2);
            update = a2.update("note_table_name", contentValues, "_id= '" + j + "'", null);
            a.c();
        } catch (Exception e) {
            f.a("DB", "NoteBaseDBHandler update with exception : " + e.getMessage());
            update = 0;
        }
        return update;
    }

    public synchronized boolean e(String str, long j) {
        boolean z;
        c a = a(str);
        try {
            int delete = a.a().delete("note_table_name", "bookrealid= '" + j + "'", null);
            a.c();
            if (delete > 0) {
                z = true;
            }
        } catch (Exception e) {
            f.a("DB", "NoteBaseDBHandler delBookNotes with exception : " + e.getMessage());
        }
        z = false;
        return z;
    }
}
