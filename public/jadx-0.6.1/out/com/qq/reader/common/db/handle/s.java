package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.qq.reader.common.c.a;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.readengine.model.b;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* compiled from: LocalNoteDBHandle */
public class s extends t {
    private static s b = null;
    private static c c;
    private static String d;
    String a = "NoteDatebaseHandle";

    public boolean a(java.lang.String r7, long r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find block by offset: 0x004e in list []
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
        r6 = this;
        r1 = 0;
        r0 = 0;
        r2 = c;
        r2 = r2.b();
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0042, all -> 0x0054 }
        r3.<init>();	 Catch:{ Exception -> 0x0042, all -> 0x0054 }
        r4 = "select count(*) from note_table_book where bookrealid=";	 Catch:{ Exception -> 0x0042, all -> 0x0054 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0042, all -> 0x0054 }
        r3 = r3.append(r8);	 Catch:{ Exception -> 0x0042, all -> 0x0054 }
        r3 = r3.toString();	 Catch:{ Exception -> 0x0042, all -> 0x0054 }
        r4 = 0;	 Catch:{ Exception -> 0x0042, all -> 0x0054 }
        r1 = r2.rawQuery(r3, r4);	 Catch:{ Exception -> 0x0042, all -> 0x0054 }
        if (r1 == 0) goto L_0x0031;	 Catch:{ Exception -> 0x0042, all -> 0x0054 }
    L_0x0023:
        r2 = r1.moveToFirst();	 Catch:{ Exception -> 0x0042, all -> 0x0054 }
        if (r2 == 0) goto L_0x0031;	 Catch:{ Exception -> 0x0042, all -> 0x0054 }
    L_0x0029:
        r2 = 0;	 Catch:{ Exception -> 0x0042, all -> 0x0054 }
        r2 = r1.getInt(r2);	 Catch:{ Exception -> 0x0042, all -> 0x0054 }
        if (r2 <= 0) goto L_0x0031;
    L_0x0030:
        r0 = 1;
    L_0x0031:
        if (r1 == 0) goto L_0x003c;
    L_0x0033:
        r2 = r1.isClosed();
        if (r2 != 0) goto L_0x003c;
    L_0x0039:
        r1.close();
    L_0x003c:
        r1 = c;
        r1.c();
    L_0x0041:
        return r0;
    L_0x0042:
        r2 = move-exception;
        if (r1 == 0) goto L_0x004e;
    L_0x0045:
        r2 = r1.isClosed();
        if (r2 != 0) goto L_0x004e;
    L_0x004b:
        r1.close();
    L_0x004e:
        r1 = c;
        r1.c();
        goto L_0x0041;
    L_0x0054:
        r0 = move-exception;
        if (r1 == 0) goto L_0x0060;
    L_0x0057:
        r2 = r1.isClosed();
        if (r2 != 0) goto L_0x0060;
    L_0x005d:
        r1.close();
    L_0x0060:
        r1 = c;
        r1.c();
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.s.a(java.lang.String, long):boolean");
    }

    public static synchronized s a() {
        s sVar;
        synchronized (s.class) {
            if (b == null) {
                b = new s();
            }
            if (c == null || !a.br.equalsIgnoreCase(d)) {
                c = new u(a.br, null, 3);
                d = a.br;
            }
            sVar = b;
        }
        return sVar;
    }

    public synchronized com.qq.reader.framework.a.c b() {
        Cursor query;
        com.qq.reader.framework.a.c cVar;
        SQLiteDatabase sQLiteDatabase;
        Exception exception;
        Cursor cursor;
        Throwable th;
        try {
            SQLiteDatabase a = c.a();
            try {
                query = a.query("note_table_name", new String[]{"_id", "book_id", "book_name", "select_text", "remarks", "book_date", "start_chapter", "start_offset", "end_chapter", "end_offset", "bookrealid", "note_type", "start_point", "end_point"}, null, null, null, null, "_id DESC");
                try {
                    List<com.qq.reader.framework.a.a> arrayList = new ArrayList();
                    HashMap hashMap = new HashMap();
                    if (query.moveToFirst()) {
                        do {
                            Object obj;
                            int i = query.getInt(0);
                            long j = query.getLong(1);
                            String string = query.getString(2);
                            long j2 = (long) i;
                            b bVar = new b(j2, j, string, query.getString(12), query.getString(13), query.getString(3), query.getString(4), query.getLong(5), query.getInt(6), query.getLong(7), query.getInt(8), query.getLong(9), query.getLong(10), query.getInt(11));
                            bVar.d(j);
                            bVar.d(string);
                            for (com.qq.reader.framework.a.a aVar : arrayList) {
                                if (!aVar.a().contains(Long.valueOf(j))) {
                                    if (aVar.c().equals(string)) {
                                        bVar.a(aVar);
                                        ((List) hashMap.get(aVar)).add(bVar);
                                        aVar.a(j);
                                        aVar.b();
                                        obj = 1;
                                        break;
                                    }
                                }
                                bVar.a(aVar);
                                ((List) hashMap.get(aVar)).add(bVar);
                                aVar.b();
                                obj = 1;
                                break;
                            }
                            obj = null;
                            if (obj == null) {
                                com.qq.reader.framework.a.a aVar2 = new com.qq.reader.framework.a.a(j, string);
                                arrayList.add(aVar2);
                                List arrayList2;
                                if (hashMap.get(aVar2) == null) {
                                    arrayList2 = new ArrayList();
                                    bVar.a(aVar2);
                                    arrayList2.add(bVar);
                                    hashMap.put(aVar2, arrayList2);
                                } else {
                                    arrayList2 = (List) hashMap.get(aVar2);
                                    bVar.a(aVar2);
                                    arrayList2.add(bVar);
                                }
                            }
                        } while (query.moveToNext());
                    }
                    com.qq.reader.framework.a.c cVar2 = new com.qq.reader.framework.a.c(arrayList, hashMap);
                    if (a != null) {
                        if (query != null) {
                            query.close();
                        }
                        c.c();
                    }
                    cVar = cVar2;
                } catch (Exception e) {
                    sQLiteDatabase = a;
                    exception = e;
                    cursor = query;
                } catch (Throwable th2) {
                    sQLiteDatabase = a;
                    th = th2;
                }
            } catch (Exception e2) {
                sQLiteDatabase = a;
                exception = e2;
                cursor = null;
                try {
                    f.a(this.a, "initOneNoteCountList : " + exception.getMessage());
                    cVar = null;
                    if (sQLiteDatabase != null) {
                        if (cursor != null) {
                            cursor.close();
                        }
                        c.c();
                    }
                    return cVar;
                } catch (Throwable th3) {
                    th = th3;
                    query = cursor;
                    if (sQLiteDatabase != null) {
                        if (query != null) {
                            query.close();
                        }
                        c.c();
                    }
                    throw th;
                }
            } catch (Throwable th22) {
                query = null;
                sQLiteDatabase = a;
                th = th22;
                if (sQLiteDatabase != null) {
                    if (query != null) {
                        query.close();
                    }
                    c.c();
                }
                throw th;
            }
        } catch (Exception e3) {
            exception = e3;
            sQLiteDatabase = null;
            cursor = null;
            f.a(this.a, "initOneNoteCountList : " + exception.getMessage());
            cVar = null;
            if (sQLiteDatabase != null) {
                if (cursor != null) {
                    cursor.close();
                }
                c.c();
            }
            return cVar;
        } catch (Throwable th4) {
            th = th4;
            query = null;
            sQLiteDatabase = null;
            if (sQLiteDatabase != null) {
                if (query != null) {
                    query.close();
                }
                c.c();
            }
            throw th;
        }
        return cVar;
    }

    public synchronized int a(long j, long j2, long j3, long j4, long j5, String str, String str2, long j6) {
        int i;
        i = 0;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = c.a();
            ContentValues contentValues = new ContentValues();
            contentValues.put("book_id", Long.valueOf(j));
            contentValues.put("start_chapter", Long.valueOf(j2));
            contentValues.put("start_offset", Long.valueOf(j3));
            contentValues.put("end_chapter", Long.valueOf(j4));
            contentValues.put("end_offset", Long.valueOf(j5));
            contentValues.put("start_point", str);
            contentValues.put("end_point", str2);
            i = sQLiteDatabase.update("note_table_name", contentValues, "_id= '" + j6 + "'", null);
            if (sQLiteDatabase != null) {
                c.c();
            }
        } catch (Exception e) {
            if (sQLiteDatabase != null) {
                c.c();
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                c.c();
            }
        }
        return i;
    }

    public int[] c() {
        Cursor query;
        Exception e;
        Throwable th;
        if (c == null) {
            c = new u(a.br, null, 3);
        }
        SQLiteDatabase a = c.a();
        int[] iArr = new int[2];
        try {
            query = a.query("note_table_name", new String[]{"remarks"}, null, null, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        do {
                            String string = query.getString(0);
                            if (string == null || string.trim().length() <= 0) {
                                iArr[0] = iArr[0] + 1;
                            } else {
                                iArr[1] = iArr[1] + 1;
                            }
                        } while (query.moveToNext());
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            }
            if (query != null) {
                query.close();
            }
            if (a != null) {
                c.c();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                f.a(this.a, "getRemarksCount : " + e.getMessage());
                if (query != null) {
                    query.close();
                }
                if (a != null) {
                    c.c();
                }
                return iArr;
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                if (a != null) {
                    c.c();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            if (a != null) {
                c.c();
            }
            throw th;
        }
        return iArr;
    }

    public c a(String str) {
        if (c == null) {
            c = new u(a.br, null, 3);
        }
        return c;
    }
}
