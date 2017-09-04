package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.db.c;
import com.qq.reader.common.download.task.g;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.framework.mark.DownloadMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/* compiled from: DownloadHandle */
public class o {
    private static c a;
    private static volatile o b;

    /* compiled from: DownloadHandle */
    private class a extends c {
        final /* synthetic */ o a;

        public a(o oVar, String str, CursorFactory cursorFactory, int i) {
            this.a = oVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    private void b(android.database.sqlite.SQLiteDatabase r6) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1439)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1461)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
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
        r4 = 0;
        r0 = "select newversion from downloadlist";	 Catch:{ Exception -> 0x0029, all -> 0x003c }
        r1 = 0;	 Catch:{ Exception -> 0x0029, all -> 0x003c }
        r0 = r6.rawQuery(r0, r1);	 Catch:{ Exception -> 0x0029, all -> 0x003c }
        if (r0 == 0) goto L_0x000e;
    L_0x000b:
        r0.close();
    L_0x000e:
        r0 = "select createtasktime from downloadlist";	 Catch:{ Exception -> 0x0043, all -> 0x006f }
        r1 = 0;	 Catch:{ Exception -> 0x0043, all -> 0x006f }
        r0 = r6.rawQuery(r0, r1);	 Catch:{ Exception -> 0x0043, all -> 0x006f }
        if (r0 == 0) goto L_0x001b;
    L_0x0018:
        r0.close();
    L_0x001b:
        r0 = "select fromtype from downloadlist";	 Catch:{ Exception -> 0x0076, all -> 0x0089 }
        r1 = 0;	 Catch:{ Exception -> 0x0076, all -> 0x0089 }
        r0 = r6.rawQuery(r0, r1);	 Catch:{ Exception -> 0x0076, all -> 0x0089 }
        if (r0 == 0) goto L_0x0028;
    L_0x0025:
        r0.close();
    L_0x0028:
        return;
    L_0x0029:
        r0 = move-exception;
        r0 = "ALTER TABLE downloadlist ADD newversion integer default 0 ";	 Catch:{ Exception -> 0x0029, all -> 0x003c }
        r6.execSQL(r0);	 Catch:{ Exception -> 0x0029, all -> 0x003c }
        r0 = "update downloadlist set newversion = downloadlist.version";	 Catch:{ Exception -> 0x0029, all -> 0x003c }
        r6.execSQL(r0);	 Catch:{ Exception -> 0x0029, all -> 0x003c }
        if (r4 == 0) goto L_0x000e;
    L_0x0038:
        r4.close();
        goto L_0x000e;
    L_0x003c:
        r0 = move-exception;
        if (r4 == 0) goto L_0x0042;
    L_0x003f:
        r4.close();
    L_0x0042:
        throw r0;
    L_0x0043:
        r0 = move-exception;
        r0 = "ALTER TABLE downloadlist ADD createtasktime";	 Catch:{ Exception -> 0x0043, all -> 0x006f }
        r6.execSQL(r0);	 Catch:{ Exception -> 0x0043, all -> 0x006f }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0043, all -> 0x006f }
        r0.<init>();	 Catch:{ Exception -> 0x0043, all -> 0x006f }
        r1 = "update downloadlist set createtasktime = ";	 Catch:{ Exception -> 0x0043, all -> 0x006f }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x0043, all -> 0x006f }
        r2 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0043, all -> 0x006f }
        r1 = java.lang.String.valueOf(r2);	 Catch:{ Exception -> 0x0043, all -> 0x006f }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x0043, all -> 0x006f }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0043, all -> 0x006f }
        r6.execSQL(r0);	 Catch:{ Exception -> 0x0043, all -> 0x006f }
        if (r4 == 0) goto L_0x001b;
    L_0x006b:
        r4.close();
        goto L_0x001b;
    L_0x006f:
        r0 = move-exception;
        if (r4 == 0) goto L_0x0075;
    L_0x0072:
        r4.close();
    L_0x0075:
        throw r0;
    L_0x0076:
        r0 = move-exception;
        r0 = "ALTER TABLE downloadlist ADD fromtype integer default 0 ";	 Catch:{ Exception -> 0x0076, all -> 0x0089 }
        r6.execSQL(r0);	 Catch:{ Exception -> 0x0076, all -> 0x0089 }
        r0 = "ALTER TABLE downloadlist ADD onlyicon integer default 0 ";	 Catch:{ Exception -> 0x0076, all -> 0x0089 }
        r6.execSQL(r0);	 Catch:{ Exception -> 0x0076, all -> 0x0089 }
        if (r4 == 0) goto L_0x0028;
    L_0x0085:
        r4.close();
        goto L_0x0028;
    L_0x0089:
        r0 = move-exception;
        if (r4 == 0) goto L_0x008f;
    L_0x008c:
        r4.close();
    L_0x008f:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.o.b(android.database.sqlite.SQLiteDatabase):void");
    }

    public boolean a(DownloadBookTask downloadBookTask) {
        if (downloadBookTask == null || downloadBookTask.getName() == null || downloadBookTask.getObjectURI() == null) {
            return false;
        }
        boolean a = a(new DownloadBookTask[]{downloadBookTask});
        if (!a) {
            return a;
        }
        return i.c().a(c(downloadBookTask), true);
    }

    public boolean b(DownloadBookTask downloadBookTask) {
        if (downloadBookTask == null || downloadBookTask.getName() == null) {
            return false;
        }
        return a(new DownloadBookTask[]{downloadBookTask});
    }

    public List<g> a() {
        return b();
    }

    public synchronized boolean a(long j) {
        boolean b;
        if (new File(d()).exists()) {
            b = b(j);
        } else {
            b = true;
        }
        return b;
    }

    public DownloadBookTask a(String str) {
        return c(str);
    }

    public File b(String str) {
        return ao.b(ao.d(str));
    }

    private String d() {
        return com.qq.reader.common.c.a.bl;
    }

    private void a(DownloadBookTask downloadBookTask, long j) {
        TaskStateEnum state = downloadBookTask.getState();
        if (state == TaskStateEnum.InstallCompleted || state == TaskStateEnum.Installing || state == TaskStateEnum.Finished) {
            downloadBookTask.setCurrentSize(j);
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(com.qq.reader.common.c.a.bk);
        stringBuffer.append("/");
        stringBuffer.append(downloadBookTask.getFullName());
        stringBuffer.append(".zip");
        File c = ao.c(stringBuffer.toString());
        if (c != null) {
            downloadBookTask.setCurrentSize(c.length());
        }
    }

    private Mark c(DownloadBookTask downloadBookTask) {
        String filePath = downloadBookTask.getFilePath();
        if (filePath == null || downloadBookTask.getFromType() == 1) {
            return null;
        }
        Mark downloadMark = new DownloadMark(filePath, downloadBookTask.getFullName(), true);
        downloadMark.setBookId(downloadBookTask.getId());
        downloadMark.setAuthor(downloadBookTask.getAuthor());
        downloadMark.setCoverUrl(downloadBookTask.getImageURI());
        j.a().a(new com.qq.reader.common.monitor.a.a(String.valueOf(downloadBookTask.getId()), downloadBookTask.getNetChannelId()));
        downloadMark.setFinished(1);
        return downloadMark;
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists downloadlist (_id integer primary key autoincrement,bookid text not null,version integer default 0,objecturl text,state integer default 0,size text,progress integer default 0,name text,author text,description text,iconurl text,iconstate boolean,icontimes integer default 0,format text,drmflag integer default 0,pathcode integer default -1,createtasktime text,newversion integer default 0 ,fromtype integer default 0 ,onlyicon integer default 0);");
    }

    public synchronized boolean a(DownloadBookTask[] downloadBookTaskArr) {
        boolean z;
        try {
            SQLiteDatabase a = a.a();
            if (a != null) {
                a.beginTransaction();
                for (DownloadBookTask downloadBookTask : downloadBookTaskArr) {
                    long insert;
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("bookid", Long.valueOf(downloadBookTask.getId()));
                    contentValues.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, Integer.valueOf(downloadBookTask.getVersion()));
                    contentValues.put("objecturl", downloadBookTask.getObjectURI());
                    contentValues.put("state", Integer.valueOf(downloadBookTask.getState().ordinal()));
                    contentValues.put("size", Long.valueOf(downloadBookTask.getSize()));
                    contentValues.put("progress", Integer.valueOf(downloadBookTask.getProgress()));
                    contentValues.put("name", downloadBookTask.getName());
                    contentValues.put("author", downloadBookTask.getAuthor());
                    contentValues.put(SocialConstants.PARAM_COMMENT, downloadBookTask.getDescription());
                    contentValues.put("iconurl", downloadBookTask.getImageURI());
                    contentValues.put("icontimes", Integer.valueOf(downloadBookTask.getIconDownloadedTimes()));
                    contentValues.put("format", downloadBookTask.getBookFormat());
                    contentValues.put("drmflag", Integer.valueOf(downloadBookTask.getDrmflag()));
                    contentValues.put("pathcode", Integer.valueOf(downloadBookTask.getFilePath().hashCode()));
                    contentValues.put("createtasktime", String.valueOf(downloadBookTask.getCreateTime()));
                    if (downloadBookTask.getNewVerison() == 0) {
                        downloadBookTask.setNewVersion(downloadBookTask.getVersion());
                    }
                    contentValues.put("newversion", Integer.valueOf(downloadBookTask.getNewVerison()));
                    contentValues.put("fromtype", Integer.valueOf(downloadBookTask.getFromType()));
                    if (downloadBookTask.getIsOnlyDownLoadIcon()) {
                        contentValues.put("onlyicon", Integer.valueOf(1));
                    } else {
                        contentValues.put("onlyicon", Integer.valueOf(0));
                    }
                    Cursor query = a.query("downloadlist", new String[]{"_id"}, "bookid= '" + downloadBookTask.getId() + "'", null, null, null, null, null);
                    if (query == null || query.getCount() <= 0) {
                        try {
                            insert = a.insert("downloadlist", null, contentValues);
                        } catch (Exception e) {
                            e.printStackTrace();
                            a.endTransaction();
                            a.c();
                            z = false;
                        } catch (Throwable th) {
                            a.endTransaction();
                        }
                    } else {
                        query.moveToFirst();
                        insert = (long) a.update("downloadlist", contentValues, "_id=" + query.getInt(0), null);
                    }
                    query.close();
                    if (insert < 0) {
                        a.endTransaction();
                        a.c();
                        z = false;
                        break;
                    }
                }
                a.setTransactionSuccessful();
                a.endTransaction();
            }
            a.c();
            z = true;
        } catch (Exception e2) {
            try {
                e2.printStackTrace();
                z = false;
            } finally {
                a.c();
            }
        }
        return z;
    }

    public boolean b(long j) {
        int delete;
        Exception e;
        try {
            delete = a.a().delete("downloadlist", "bookid= '" + j + "'", null);
            try {
                a.c();
            } catch (Exception e2) {
                e = e2;
                f.a("DB", "delDownloadTask with exception : " + e.getMessage());
                if (delete > 0) {
                    return true;
                }
                return false;
            }
        } catch (Exception e3) {
            e = e3;
            delete = 0;
            f.a("DB", "delDownloadTask with exception : " + e.getMessage());
            if (delete > 0) {
                return false;
            }
            return true;
        }
        if (delete > 0) {
            return true;
        }
        return false;
    }

    public DownloadBookTask c(String str) {
        DownloadBookTask downloadBookTask;
        Exception e;
        try {
            Cursor query = a.a().query("downloadlist", new String[]{"bookid", ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, "objecturl", "state", "size", "progress", "name", "author", SocialConstants.PARAM_COMMENT, "iconurl", "iconstate", "icontimes", "format", "drmflag", "newversion", "createtasktime", "fromtype", "onlyicon"}, "pathcode= '" + str.hashCode() + "'", null, null, null, null);
            if (query == null || query.getCount() <= 0) {
                downloadBookTask = null;
            } else {
                int i;
                String string;
                int i2;
                int i3;
                long j;
                int i4;
                boolean z;
                query.moveToFirst();
                long j2 = query.getLong(0);
                int i5 = query.getInt(1);
                String string2 = query.getString(2);
                int i6 = query.getInt(3);
                long j3 = query.getLong(4);
                int i7 = query.getInt(5);
                String string3 = query.getString(6);
                String string4 = query.getString(7);
                String string5 = query.getString(8);
                String string6 = query.getString(9);
                if (query.getInt(10) == 1) {
                    i = query.getInt(11);
                    string = query.getString(12);
                    i2 = query.getInt(13);
                    i3 = query.getInt(14);
                    j = query.getLong(15);
                    i4 = query.getInt(16);
                } else {
                    i = query.getInt(11);
                    string = query.getString(12);
                    i2 = query.getInt(13);
                    i3 = query.getInt(14);
                    j = query.getLong(15);
                    i4 = query.getInt(16);
                }
                if (query.getInt(17) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                downloadBookTask = new DownloadBookTask(j2, string3, string4, string2, string6, i5, string, i2, j);
                try {
                    downloadBookTask.setState(i6);
                    downloadBookTask.setSize(j3);
                    downloadBookTask.setProgress(i7);
                    downloadBookTask.setDescription(string5);
                    downloadBookTask.setIconDownloadedTimes(i);
                    downloadBookTask.setNewVersion(i3);
                    a(downloadBookTask, j3);
                    downloadBookTask.setFromType(i4);
                    downloadBookTask.setIsOnlyDownLoadIcon(z);
                } catch (Exception e2) {
                    e = e2;
                    f.a("DB", "getDownloadTaskByFilePath with exception : " + e.getMessage());
                    return downloadBookTask;
                }
            }
            query.close();
            a.c();
        } catch (Exception e3) {
            e = e3;
            downloadBookTask = null;
            f.a("DB", "getDownloadTaskByFilePath with exception : " + e.getMessage());
            return downloadBookTask;
        }
        return downloadBookTask;
    }

    public synchronized DownloadBookTask c(long j) {
        DownloadBookTask downloadBookTask;
        Exception e;
        try {
            Cursor query = a.a().query("downloadlist", new String[]{"bookid", ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, "objecturl", "state", "size", "progress", "name", "author", SocialConstants.PARAM_COMMENT, "iconurl", "iconstate", "icontimes", "format", "drmflag", "newversion", "createtasktime", "fromtype", "onlyicon"}, "bookid= '" + j + "'", null, null, null, null);
            if (query == null || query.getCount() <= 0) {
                downloadBookTask = null;
            } else {
                int i;
                String string;
                int i2;
                int i3;
                long j2;
                int i4;
                boolean z;
                query.moveToFirst();
                long j3 = query.getLong(0);
                int i5 = query.getInt(1);
                String string2 = query.getString(2);
                int i6 = query.getInt(3);
                long j4 = query.getLong(4);
                int i7 = query.getInt(5);
                String string3 = query.getString(6);
                String string4 = query.getString(7);
                String string5 = query.getString(8);
                String string6 = query.getString(9);
                if (query.getInt(10) == 1) {
                    i = query.getInt(11);
                    string = query.getString(12);
                    i2 = query.getInt(13);
                    i3 = query.getInt(14);
                    j2 = query.getLong(15);
                    i4 = query.getInt(16);
                } else {
                    i = query.getInt(11);
                    string = query.getString(12);
                    i2 = query.getInt(13);
                    i3 = query.getInt(14);
                    j2 = query.getLong(15);
                    i4 = query.getInt(16);
                }
                if (query.getInt(17) == 1) {
                    z = true;
                } else {
                    z = false;
                }
                downloadBookTask = new DownloadBookTask(j3, string3, string4, string2, string6, i5, string, i2, j2);
                try {
                    downloadBookTask.setState(i6);
                    downloadBookTask.setSize(j4);
                    downloadBookTask.setProgress(i7);
                    downloadBookTask.setDescription(string5);
                    downloadBookTask.setIconDownloadedTimes(i);
                    downloadBookTask.setNewVersion(i3);
                    a(downloadBookTask, j4);
                    downloadBookTask.setFromType(i4);
                    downloadBookTask.setIsOnlyDownLoadIcon(z);
                } catch (Exception e2) {
                    e = e2;
                    f.a("DB", "getDownloadTaskById with exception : " + e.getMessage());
                    return downloadBookTask;
                }
            }
            query.close();
            a.c();
        } catch (Exception e3) {
            e = e3;
            downloadBookTask = null;
            f.a("DB", "getDownloadTaskById with exception : " + e.getMessage());
            return downloadBookTask;
        }
        return downloadBookTask;
    }

    public synchronized List<g> b() {
        List arrayList;
        arrayList = new ArrayList();
        try {
            Cursor query = a.a().query("downloadlist", new String[]{"bookid", ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION, "objecturl", "state", "size", "progress", "name", "author", SocialConstants.PARAM_COMMENT, "iconurl", "iconstate", "icontimes", "format", "drmflag", "newversion", "createtasktime", "fromtype", "onlyicon"}, null, null, null, null, null);
            if (query.moveToFirst()) {
                do {
                    int i;
                    String string;
                    int i2;
                    int i3;
                    long j;
                    int i4;
                    boolean z;
                    long j2 = query.getLong(0);
                    int i5 = query.getInt(1);
                    String string2 = query.getString(2);
                    int i6 = query.getInt(3);
                    long j3 = query.getLong(4);
                    int i7 = query.getInt(5);
                    String string3 = query.getString(6);
                    String string4 = query.getString(7);
                    String string5 = query.getString(8);
                    String string6 = query.getString(9);
                    if (query.getInt(10) == 1) {
                        i = query.getInt(11);
                        string = query.getString(12);
                        i2 = query.getInt(13);
                        i3 = query.getInt(14);
                        j = query.getLong(15);
                        i4 = query.getInt(16);
                    } else {
                        i = query.getInt(11);
                        string = query.getString(12);
                        i2 = query.getInt(13);
                        i3 = query.getInt(14);
                        j = query.getLong(15);
                        i4 = query.getInt(16);
                    }
                    if (query.getInt(17) == 1) {
                        z = true;
                    } else {
                        z = false;
                    }
                    if (string3 != null && j2 > 0) {
                        DownloadBookTask downloadBookTask = new DownloadBookTask(j2, string3, string4, string2, string6, i5, string, i2, j);
                        downloadBookTask.setState(i6);
                        downloadBookTask.setSize(j3);
                        downloadBookTask.setProgress(i7);
                        downloadBookTask.setDescription(string5);
                        downloadBookTask.setIconDownloadedTimes(i);
                        downloadBookTask.setNewVersion(i3);
                        a(downloadBookTask, j3);
                        downloadBookTask.setFromType(i4);
                        downloadBookTask.setIsOnlyDownLoadIcon(z);
                        arrayList.add(downloadBookTask);
                    }
                } while (query.moveToNext());
            }
            query.close();
            a.c();
        } catch (Exception e) {
            f.a("DB", "getAllDownloadTasks with exception: " + e.toString());
        }
        return arrayList;
    }

    private o() {
        if (a == null) {
            a = new a(this, com.qq.reader.common.c.a.bl, null, 5);
        }
    }

    public static o c() {
        if (b == null) {
            synchronized (o.class) {
                if (b == null) {
                    b = new o();
                }
            }
        }
        return b;
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
            default: goto L_0x0003;
        };
    L_0x0003:
        return;
    L_0x0004:
        r0.e(r1);
    L_0x0007:
        r0.d(r1);
    L_0x000a:
        r0.c(r1);
    L_0x000d:
        r0.b(r1);
        goto L_0x0003;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.o.a(android.database.sqlite.SQLiteDatabase, int):void");
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select fromtype from downloadlist", null);
            if (rawQuery != null) {
                try {
                    sQLiteDatabase.setVersion(4);
                    rawQuery.close();
                    if (cursor != null) {
                        cursor.close();
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update3To4 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE downloadlist ADD fromtype integer default 0 ");
                        sQLiteDatabase.execSQL("ALTER TABLE downloadlist ADD onlyicon integer default 0 ");
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
            sQLiteDatabase.execSQL("ALTER TABLE downloadlist ADD fromtype integer default 0 ");
            sQLiteDatabase.execSQL("ALTER TABLE downloadlist ADD onlyicon integer default 0 ");
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            f.a("DB", " update3To4 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE downloadlist ADD fromtype integer default 0 ");
            sQLiteDatabase.execSQL("ALTER TABLE downloadlist ADD onlyicon integer default 0 ");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    private void d(SQLiteDatabase sQLiteDatabase) {
        Exception e;
        Throwable th;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select createtasktime from downloadlist", null);
            if (rawQuery != null) {
                try {
                    sQLiteDatabase.setVersion(3);
                    rawQuery.close();
                    if (cursor != null) {
                        cursor.close();
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("DB", " update2To3 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE downloadlist ADD createtasktime");
                        sQLiteDatabase.execSQL("update downloadlist set createtasktime = " + String.valueOf(System.currentTimeMillis()));
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
            sQLiteDatabase.execSQL("ALTER TABLE downloadlist ADD createtasktime");
            sQLiteDatabase.execSQL("update downloadlist set createtasktime = " + String.valueOf(System.currentTimeMillis()));
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            f.a("DB", " update2To3 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE downloadlist ADD createtasktime");
            sQLiteDatabase.execSQL("update downloadlist set createtasktime = " + String.valueOf(System.currentTimeMillis()));
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            sQLiteDatabase.execSQL("ALTER TABLE downloadlist ADD newversion integer default 0 ");
            sQLiteDatabase.execSQL("update downloadlist set newversion = downloadlist.version");
        }
    }
}
