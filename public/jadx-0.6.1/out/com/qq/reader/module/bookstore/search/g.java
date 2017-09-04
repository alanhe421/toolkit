package com.qq.reader.module.bookstore.search;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

/* compiled from: SearchHistoryHandle */
public class g {
    public static int a = 4;
    private static c b;
    private static g d = null;
    private Context c;

    /* compiled from: SearchHistoryHandle */
    private class a extends c {
        final /* synthetic */ g a;

        public a(g gVar, String str, CursorFactory cursorFactory, int i) {
            this.a = gVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    public synchronized void a(com.qq.reader.module.bookstore.search.SearchHistory r7, com.qq.reader.module.bookstore.search.ISearchParamCollection r8) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:29:0x00ba in {3, 13, 16, 18, 23, 28, 31, 33, 35, 37, 38} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.rerun(BlockProcessor.java:44)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:57)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
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
        r0 = com.qq.reader.common.utils.ao.a(r8);	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        r1 = b;	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        r1 = r1.a();	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        if (r1 == 0) goto L_0x0067;	 Catch:{ Exception -> 0x0093, all -> 0x00be }
    L_0x0011:
        r1.beginTransaction();	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        if (r1 == 0) goto L_0x0061;
    L_0x0016:
        r2 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r2.<init>();	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r3 = "searchkeyword";	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r4 = r7.getWord();	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r4 = r4.trim();	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r2.put(r3, r4);	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r3 = "searchkeytime";	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r4 = r7.getTime();	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r4 = java.lang.Long.valueOf(r4);	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r2.put(r3, r4);	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r3 = "searchkeytype";	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r4 = r7.getType();	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r2.put(r3, r4);	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r3 = "searchkeyid";	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r4 = r7.getId();	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r2.put(r3, r4);	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r3 = "searchkeyqurl";	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r4 = r7.getQurl();	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r2.put(r3, r4);	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r0 = r0.getSearchHistoryTableName();	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r3 = 0;	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r1.replace(r0, r3, r2);	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
    L_0x0061:
        r1.setTransactionSuccessful();	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r1.endTransaction();	 Catch:{ Exception -> 0x0093, all -> 0x00be }
    L_0x0067:
        r0 = b;
        r0.c();
        goto L_0x0003;
    L_0x006d:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x0070:
        r0 = move-exception;
        r2 = "SearchHistoryHandle";	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r3.<init>();	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r4 = "addRecommendBooks with exception : ";	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        com.qq.reader.common.monitor.f.a(r2, r0);	 Catch:{ Exception -> 0x0070, all -> 0x00b9 }
        r1.endTransaction();
        goto L_0x0067;
    L_0x0093:
        r0 = move-exception;
        r1 = "SearchHistoryHandle";	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        r2.<init>();	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        r3 = "addRecommendBooks getWritableDatabase with exception : ";	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        r0 = r2.append(r0);	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        com.qq.reader.common.monitor.f.a(r1, r0);	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        r0 = b;
        r0.c();
        goto L_0x0003;
    L_0x00b9:
        r0 = move-exception;
        r1.endTransaction();	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        throw r0;	 Catch:{ Exception -> 0x0093, all -> 0x00be }
    L_0x00be:
        r0 = move-exception;
        r1 = b;	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        r1.c();	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        throw r0;	 Catch:{ Exception -> 0x0093, all -> 0x00be }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.bookstore.search.g.a(com.qq.reader.module.bookstore.search.SearchHistory, com.qq.reader.module.bookstore.search.ISearchParamCollection):void");
    }

    private g(Context context) {
        b = new a(this, com.qq.reader.common.c.a.av, null, a);
        this.c = context;
    }

    public static g a(Context context) {
        if (d == null) {
            d = new g(context);
        }
        return d;
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists searchhistory (searchkeyword text not null primary key,searchkeytype integer default 0,searchkeyid text, searchkeytime long default 0,searchkeyqurl text)");
        sQLiteDatabase.execSQL("create table if not exists audiosearchhistory (searchkeyword text not null primary key,searchkeytype integer default 0,searchkeyid text, searchkeytime long default 0,searchkeyqurl text)");
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
        switch (i) {
            case 2:
                if (!ao.c.a(sQLiteDatabase, "searchkeytype", "searchhistory")) {
                    sQLiteDatabase.execSQL("ALTER TABLE searchhistory ADD COLUMN searchkeytype INTEGER DEFAULT 0");
                }
                if (!ao.c.a(sQLiteDatabase, "searchkeyid", "searchhistory")) {
                    sQLiteDatabase.execSQL("ALTER TABLE searchhistory ADD COLUMN searchkeyid TEXT");
                }
                sQLiteDatabase.execSQL("create table if not exists audiosearchhistory (searchkeyword text not null primary key,searchkeytype integer default 0,searchkeyid text, searchkeytime long default 0,searchkeyqurl text)");
                break;
            case 3:
                break;
            default:
                return;
        }
        if (!ao.c.a(sQLiteDatabase, "searchkeyqurl", "searchhistory")) {
            sQLiteDatabase.execSQL("ALTER TABLE searchhistory ADD COLUMN searchkeyqurl TEXT");
        }
        sQLiteDatabase.execSQL("create table if not exists audiosearchhistory (searchkeyword text not null primary key,searchkeytype integer default 0,searchkeyid text, searchkeytime long default 0,searchkeyqurl text)");
    }

    public void a(ISearchParamCollection iSearchParamCollection) {
        ISearchParamCollection a = ao.a(iSearchParamCollection);
        String str = "DELETE FROM " + a.getSearchHistoryTableName() + " where " + "searchkeyword" + " NOT IN (SELECT " + "searchkeyword" + " from " + a.getSearchHistoryTableName() + " ORDER BY " + "searchkeytime" + " DESC LIMIT " + 10 + ")";
        try {
            SQLiteDatabase a2 = b.a();
            if (a2 != null) {
                a2.beginTransaction();
                try {
                    a2.execSQL(str);
                    a2.setTransactionSuccessful();
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("SearchHistoryHandle", "delete history with max size fail");
                    b.c();
                    return;
                } finally {
                    a2.endTransaction();
                }
            }
            b.c();
            return;
        } catch (Exception e2) {
            e2.printStackTrace();
            return;
        } catch (Throwable th) {
        }
        b.c();
    }

    public void b(ISearchParamCollection iSearchParamCollection) {
        ISearchParamCollection a = ao.a(iSearchParamCollection);
        try {
            SQLiteDatabase a2 = b.a();
            if (a2 != null) {
                a2.beginTransaction();
                try {
                    a2.delete(a.getSearchHistoryTableName(), null, null);
                    a2.setTransactionSuccessful();
                } catch (Exception e) {
                    f.a("SearchHistoryHandle", "addRecommendBooks with exception : " + e.getMessage());
                    b.c();
                    return;
                } finally {
                    a2.endTransaction();
                }
            }
            b.c();
            return;
        } catch (Exception e2) {
            f.a("SearchHistoryHandle", "addRecommendBooks getWritableDatabase with exception : " + e2.getMessage());
            return;
        } catch (Throwable th) {
        }
        b.c();
    }

    public synchronized boolean a(String str, int i, int i2) {
        boolean z = false;
        synchronized (this) {
            if (str == null) {
                b.c();
            } else {
                int delete;
                try {
                    delete = b.a().delete(a(i2), "searchkeytype = " + i + " AND " + "searchkeyword" + "= '" + str.replace("'", "''") + "'", null);
                    b.c();
                } catch (Exception e) {
                    f.a("DB", "removeBookChannelByBookId with exception : " + e.getMessage());
                    b.c();
                    delete = 0;
                } catch (Throwable th) {
                    b.c();
                }
                if (delete > 0) {
                    z = true;
                }
            }
        }
        return z;
    }

    public synchronized boolean a(String str, int i) {
        boolean z = true;
        synchronized (this) {
            boolean a = a(str, i, 1);
            boolean a2 = a(str, i, 2);
            if (!(a || a2)) {
                z = false;
            }
        }
        return z;
    }

    public ArrayList<SearchHistory> c(ISearchParamCollection iSearchParamCollection) {
        Exception e;
        Throwable th;
        Cursor cursor = null;
        ArrayList<SearchHistory> arrayList = new ArrayList();
        ISearchParamCollection a = ao.a(iSearchParamCollection);
        SQLiteDatabase a2;
        try {
            a2 = b.a();
            try {
                cursor = a2.rawQuery("select * from " + a.getSearchHistoryTableName() + " order by " + "searchkeytime" + " desc", null);
                int i = 0;
                if (cursor.moveToFirst()) {
                    do {
                        String string = cursor.getString(cursor.getColumnIndex("searchkeyword"));
                        int i2 = cursor.getInt(cursor.getColumnIndex("searchkeytype"));
                        long j = cursor.getLong(cursor.getColumnIndex("searchkeytime"));
                        String string2 = cursor.getString(cursor.getColumnIndex("searchkeyid"));
                        SearchHistory searchHistory = new SearchHistory(j, string, i2);
                        searchHistory.setQurl(cursor.getString(cursor.getColumnIndex("searchkeyqurl")));
                        searchHistory.setId(string2);
                        arrayList.add(searchHistory);
                        i++;
                        if (i >= 10) {
                            break;
                        }
                    } while (cursor.moveToNext());
                }
                if (arrayList.size() > 0) {
                    arrayList.add(new SearchHistory(ReaderApplication.getApplicationImp().getString(R.string.search_clear_history), 16));
                }
                if (a2 != null) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    b.c();
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Exception e3) {
            e = e3;
            a2 = null;
            try {
                f.a("SearchHistoryDB", "getHistorys with exception : " + e.getMessage());
                if (a2 != null) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    b.c();
                }
                return arrayList;
            } catch (Throwable th2) {
                th = th2;
                if (a2 != null) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    b.c();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            a2 = null;
            if (a2 != null) {
                if (cursor != null) {
                    cursor.close();
                }
                b.c();
            }
            throw th;
        }
        return arrayList;
    }

    private String a(int i) {
        switch (i) {
            case 2:
                return "audiosearchhistory";
            default:
                return "searchhistory";
        }
    }
}
