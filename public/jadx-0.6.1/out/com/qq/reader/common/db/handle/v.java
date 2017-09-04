package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.common.db.c;
import com.qq.reader.common.download.task.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.am;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.module.bookstore.qnative.card.impl.BookClubReplyCard;
import com.tencent.connect.common.Constants;
import com.tencent.open.SocialConstants;
import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: OnlineTagHandle */
public class v extends b {
    private static int a = -1;
    private static int b = -1;
    private static int c = -1;
    private static int d = -1;
    private static int e = -1;
    private static int f = -1;
    private static int g = -1;
    private static int h = -1;
    private static String i = "";
    private static volatile v j;
    private static a k;

    /* compiled from: OnlineTagHandle */
    private class a extends c {
        final /* synthetic */ v a;
        private int b;

        public a(v vVar, String str, CursorFactory cursorFactory, int i) {
            this.a = vVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.b = i;
            this.a.a(sQLiteDatabase, i);
        }
    }

    private void f(android.database.sqlite.SQLiteDatabase r4) {
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
        r3 = this;
        r2 = 0;
        r0 = "select sosourl from online";	 Catch:{ Exception -> 0x0029, all -> 0x0036 }
        r1 = 0;	 Catch:{ Exception -> 0x0029, all -> 0x0036 }
        r0 = r4.rawQuery(r0, r1);	 Catch:{ Exception -> 0x0029, all -> 0x0036 }
        if (r0 == 0) goto L_0x000e;
    L_0x000b:
        r0.close();
    L_0x000e:
        r0 = "select completestate from online";	 Catch:{ Exception -> 0x003d, all -> 0x004a }
        r1 = 0;	 Catch:{ Exception -> 0x003d, all -> 0x004a }
        r0 = r4.rawQuery(r0, r1);	 Catch:{ Exception -> 0x003d, all -> 0x004a }
        if (r0 == 0) goto L_0x001b;
    L_0x0018:
        r0.close();
    L_0x001b:
        r0 = "select autopay from online";	 Catch:{ Exception -> 0x0051, all -> 0x005e }
        r1 = 0;	 Catch:{ Exception -> 0x0051, all -> 0x005e }
        r0 = r4.rawQuery(r0, r1);	 Catch:{ Exception -> 0x0051, all -> 0x005e }
        if (r0 == 0) goto L_0x0028;
    L_0x0025:
        r0.close();
    L_0x0028:
        return;
    L_0x0029:
        r0 = move-exception;
        r0 = "ALTER TABLE online ADD sosourl text";	 Catch:{ Exception -> 0x0029, all -> 0x0036 }
        r4.execSQL(r0);	 Catch:{ Exception -> 0x0029, all -> 0x0036 }
        if (r2 == 0) goto L_0x000e;
    L_0x0032:
        r2.close();
        goto L_0x000e;
    L_0x0036:
        r0 = move-exception;
        if (r2 == 0) goto L_0x003c;
    L_0x0039:
        r2.close();
    L_0x003c:
        throw r0;
    L_0x003d:
        r0 = move-exception;
        r0 = "ALTER TABLE online ADD completestate integer default 0";	 Catch:{ Exception -> 0x003d, all -> 0x004a }
        r4.execSQL(r0);	 Catch:{ Exception -> 0x003d, all -> 0x004a }
        if (r2 == 0) goto L_0x001b;
    L_0x0046:
        r2.close();
        goto L_0x001b;
    L_0x004a:
        r0 = move-exception;
        if (r2 == 0) goto L_0x0050;
    L_0x004d:
        r2.close();
    L_0x0050:
        throw r0;
    L_0x0051:
        r0 = move-exception;
        r0 = "ALTER TABLE online ADD autopay integer default 0";	 Catch:{ Exception -> 0x0051, all -> 0x005e }
        r4.execSQL(r0);	 Catch:{ Exception -> 0x0051, all -> 0x005e }
        if (r2 == 0) goto L_0x0028;
    L_0x005a:
        r2.close();
        goto L_0x0028;
    L_0x005e:
        r0 = move-exception;
        if (r2 == 0) goto L_0x0064;
    L_0x0061:
        r2.close();
    L_0x0064:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.v.f(android.database.sqlite.SQLiteDatabase):void");
    }

    private void g(android.database.sqlite.SQLiteDatabase r4) {
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
        r3 = this;
        r2 = 0;
        r0 = "select online_book_is_show_on_history from online";	 Catch:{ Exception -> 0x000f, all -> 0x001c }
        r1 = 0;	 Catch:{ Exception -> 0x000f, all -> 0x001c }
        r0 = r4.rawQuery(r0, r1);	 Catch:{ Exception -> 0x000f, all -> 0x001c }
        if (r0 == 0) goto L_0x000e;
    L_0x000b:
        r0.close();
    L_0x000e:
        return;
    L_0x000f:
        r0 = move-exception;
        r0 = "ALTER TABLE online ADD online_book_is_show_on_history integer default 1";	 Catch:{ Exception -> 0x000f, all -> 0x001c }
        r4.execSQL(r0);	 Catch:{ Exception -> 0x000f, all -> 0x001c }
        if (r2 == 0) goto L_0x000e;
    L_0x0018:
        r2.close();
        goto L_0x000e;
    L_0x001c:
        r0 = move-exception;
        if (r2 == 0) goto L_0x0022;
    L_0x001f:
        r2.close();
    L_0x0022:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.v.g(android.database.sqlite.SQLiteDatabase):void");
    }

    public void a() {
        synchronized (v.class) {
            j = null;
        }
    }

    public static v b() {
        if (j == null) {
            synchronized (v.class) {
                if (j == null) {
                    j = new v();
                }
            }
        }
        return j;
    }

    private v() {
        k = new a(this, com.qq.reader.common.c.a.bn, null, 10);
    }

    public synchronized List<OnlineTag> c() {
        return e();
    }

    public synchronized OnlineTag a(String str) {
        return f.a().a(str);
    }

    public synchronized File a(OnlineTag onlineTag) {
        File file;
        if (onlineTag == null) {
            file = null;
        } else {
            if (onlineTag.s() <= 0) {
                OnlineTag a = a(onlineTag.k());
                if (a == null) {
                    onlineTag.c(1);
                    onlineTag.g(1);
                } else {
                    onlineTag.c(a.g());
                    onlineTag.g(a.g());
                    onlineTag.b(a.h());
                    onlineTag.a(a.i());
                    if (onlineTag.g() <= 0) {
                        onlineTag.c(1);
                    }
                    if (onlineTag.s() <= 0) {
                        onlineTag.g(1);
                    }
                }
            }
            file = f(onlineTag);
        }
        return file;
    }

    public synchronized boolean b(OnlineTag onlineTag) {
        boolean z;
        if (onlineTag != null) {
            if (!onlineTag.k().equals("")) {
                f.a().a(onlineTag);
                z = true;
            }
        }
        z = false;
        return z;
    }

    public synchronized boolean c(OnlineTag onlineTag) {
        boolean z = false;
        synchronized (this) {
            if (onlineTag != null) {
                d(onlineTag);
                if (g(onlineTag)) {
                    f.a().b(onlineTag);
                    z = true;
                }
            }
        }
        return z;
    }

    public synchronized void d(OnlineTag onlineTag) {
        if (onlineTag != null) {
            String c = c(onlineTag.k());
            if (c != null && c.length() > 0) {
                File file = new File(c);
                if (file.exists()) {
                    File file2 = new File(c + "del");
                    if (!file.renameTo(file2)) {
                        file2 = file;
                    }
                    ao.b(file2);
                }
            }
        }
    }

    public synchronized void b(String str) {
        if (str != null) {
            String c = c(str);
            if (c != null && c.length() > 0) {
                File file = new File(c);
                if (file.exists()) {
                    File file2 = new File(c + "del");
                    if (!file.renameTo(file2)) {
                        file2 = file;
                    }
                    ao.b(file2);
                }
            }
        }
    }

    public synchronized void a(String str, List<String> list) {
        if (!(str == null || list == null)) {
            if (list.size() > 0) {
                List arrayList = new ArrayList();
                for (String a : list) {
                    arrayList.add(a(str, a));
                }
                g.a().a(new OnlineTagHandle$1(this, arrayList));
            }
        }
    }

    private synchronized File f(OnlineTag onlineTag) {
        File file = null;
        synchronized (this) {
            String k = onlineTag.k();
            String b = onlineTag.b();
            int s = onlineTag.s();
            String h = onlineTag.h();
            if (b != null && s > 0) {
                String c = c(k);
                if (c != null) {
                    File file2;
                    file = new File(c);
                    if (!file.exists()) {
                        file2 = new File(b(k, b));
                        if (file2.exists()) {
                            file2.renameTo(file);
                        } else {
                            ab.a(file);
                        }
                    }
                    file = new File(c(k, s) + ".qct");
                    if (!file.exists()) {
                        file2 = new File(c(k, s) + ".qrt");
                        if (!(file2 == null || file2.exists())) {
                            File file3 = new File(a(k, b, s, h) + ".txt");
                            if (file3.exists()) {
                                file3.renameTo(file2);
                            }
                        }
                        if (file2.exists()) {
                            try {
                                a(file2, file);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
        return file;
    }

    private void a(File file, File file2) throws IOException {
        File file3;
        Throwable th;
        DataOutputStream dataOutputStream;
        FileOutputStream fileOutputStream;
        Object obj;
        BufferedInputStream bufferedInputStream = null;
        byte[] bArr = new byte[1050];
        try {
            file3 = new File(file2.getAbsolutePath() + f.DOWNLOAD_FILE_TMP);
            try {
                ao.a(file3);
                file3.createNewFile();
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(new FileInputStream(file));
                try {
                    OutputStream fileOutputStream2 = new FileOutputStream(file3);
                    try {
                        DataOutputStream dataOutputStream2 = new DataOutputStream(fileOutputStream2);
                        while (true) {
                            try {
                                int read = bufferedInputStream2.read(bArr);
                                if (read == -1) {
                                    break;
                                }
                                dataOutputStream2.write(com.qq.reader.common.utils.a.b.a(bArr), 0, read);
                            } catch (Throwable th2) {
                                th = th2;
                                bufferedInputStream = bufferedInputStream2;
                                dataOutputStream = dataOutputStream2;
                                fileOutputStream = fileOutputStream2;
                            }
                        }
                        file3.renameTo(file2);
                        file.delete();
                        if (bufferedInputStream2 != null) {
                            bufferedInputStream2.close();
                        }
                        if (dataOutputStream2 != null) {
                            dataOutputStream2.close();
                        }
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                        if (file3 != null) {
                            ao.a(file3);
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        OutputStream outputStream = fileOutputStream2;
                        bufferedInputStream = bufferedInputStream2;
                        obj = null;
                        if (bufferedInputStream != null) {
                            bufferedInputStream.close();
                        }
                        if (dataOutputStream != null) {
                            dataOutputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        if (file3 != null) {
                            ao.a(file3);
                        }
                        throw th;
                    }
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                    bufferedInputStream = bufferedInputStream2;
                    obj = null;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    if (dataOutputStream != null) {
                        dataOutputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    if (file3 != null) {
                        ao.a(file3);
                    }
                    throw th;
                }
            } catch (Throwable th5) {
                th = th5;
                dataOutputStream = null;
                fileOutputStream = null;
                if (bufferedInputStream != null) {
                    bufferedInputStream.close();
                }
                if (dataOutputStream != null) {
                    dataOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                if (file3 != null) {
                    ao.a(file3);
                }
                throw th;
            }
        } catch (Throwable th6) {
            th = th6;
            file3 = null;
            dataOutputStream = null;
            fileOutputStream = null;
            if (bufferedInputStream != null) {
                bufferedInputStream.close();
            }
            if (dataOutputStream != null) {
                dataOutputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            if (file3 != null) {
                ao.a(file3);
            }
            throw th;
        }
    }

    public static String a(String str, String str2, int i, String str3) {
        if (str.length() <= 0 || str2 == null || i <= 0 || str3 == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(com.qq.reader.common.c.a.bm);
        stringBuffer.append(str);
        stringBuffer.append(str2);
        stringBuffer.append("/");
        stringBuffer.append(a(i));
        stringBuffer.append(d(str3));
        return stringBuffer.toString();
    }

    private static String c(String str, int i) {
        if (str == null || str.length() <= 0 || i <= 0) {
            return null;
        }
        return c(str) + File.separator + i;
    }

    public static String a(String str, int i) {
        if (str == null || str.length() <= 0 || i <= 0) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(c(str));
        stringBuilder.append(File.separator);
        stringBuilder.append(i);
        stringBuilder.append(".qct");
        return stringBuilder.toString();
    }

    public static String a(String str, String str2) {
        if (str == null || str.length() <= 0 || TextUtils.isEmpty(str2)) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(c(str));
        stringBuilder.append(File.separator);
        stringBuilder.append(str2);
        stringBuilder.append(".qct");
        return stringBuilder.toString();
    }

    public static String b(String str, int i) {
        if (str == null || str.length() <= 0 || i <= 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        if (!str.endsWith("/")) {
            stringBuffer.append("/");
        }
        stringBuffer.append(i);
        stringBuffer.append(".qct");
        return stringBuffer.toString();
    }

    public static String b(String str, String str2) {
        if (str.length() <= 0 || str2 == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(com.qq.reader.common.c.a.bm);
        stringBuffer.append(File.separator);
        stringBuffer.append(str);
        stringBuffer.append(ao.a(new StringBuffer(str2)));
        return stringBuffer.toString();
    }

    public static String c(String str) {
        if (str.length() <= 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(com.qq.reader.common.c.a.bm);
        stringBuffer.append(File.separator);
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public static String a(int i) {
        String str = "" + i;
        StringBuffer stringBuffer = new StringBuffer();
        for (int length = str.length(); length < com.tencent.qalsdk.core.c.c.length(); length++) {
            stringBuffer.append(0);
        }
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    public static String d(String str) {
        return ao.a(new StringBuffer(str.replaceAll("\r|\n", " ")));
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("create table if not exists online (_id integer primary key autoincrement,onlineid text not null,curtime text,downloadurl text,coverurl text,bookname text,author text,chapternums integer default 0,chapterid integer default 0,chaptername text,lastpoint text,description text,timestr text,bookurl text,source integer default 0,isrealurl integer default 0,format text,drmflag  integer default 0,sosourl text,completestate integer default 0,online_book_is_show_on_history integer default 1,autopay integer default 0,last_time long default 0,resType integer default 1);");
            sQLiteDatabase.execSQL("create unique index if not exists idx on online (onlineid);");
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a("DB", "createTable downloadlist with exception : " + e.getMessage());
        }
    }

    public boolean e(String str) {
        File file = new File(am.b() + "/Tencent/ReaderZone/" + str + "/Online/online.db");
        if (file.exists()) {
            int i;
            List e = e();
            int size = e.size();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(" where onlineid not in (");
            if (size > 0) {
                for (i = 0; i < size - 1; i++) {
                    stringBuilder.append(((OnlineTag) e.get(i)).k() + ",");
                }
                stringBuilder.append(((OnlineTag) e.get(size - 1)).k());
            }
            stringBuilder.append(");");
            SQLiteDatabase openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(file, null);
            List arrayList = new ArrayList();
            Cursor rawQuery = openOrCreateDatabase.rawQuery("select * from online " + stringBuilder.toString(), null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.moveToFirst()) {
                        while (true) {
                            String string = rawQuery.getString(rawQuery.getColumnIndex("onlineid"));
                            String string2 = rawQuery.getString(rawQuery.getColumnIndex("curtime"));
                            String string3 = rawQuery.getString(rawQuery.getColumnIndex("downloadurl"));
                            String string4 = rawQuery.getString(rawQuery.getColumnIndex("coverurl"));
                            String string5 = rawQuery.getString(rawQuery.getColumnIndex("bookname"));
                            String string6 = rawQuery.getString(rawQuery.getColumnIndex("author"));
                            int i2 = rawQuery.getInt(rawQuery.getColumnIndex("chapternums"));
                            int i3 = rawQuery.getInt(rawQuery.getColumnIndex("chapterid"));
                            String string7 = rawQuery.getString(rawQuery.getColumnIndex("chaptername"));
                            String string8 = rawQuery.getString(rawQuery.getColumnIndex("lastpoint"));
                            String string9 = rawQuery.getString(rawQuery.getColumnIndex(SocialConstants.PARAM_COMMENT));
                            rawQuery.getString(rawQuery.getColumnIndex("timestr"));
                            String string10 = rawQuery.getString(rawQuery.getColumnIndex("bookurl"));
                            int i4 = rawQuery.getInt(rawQuery.getColumnIndex(SocialConstants.PARAM_SOURCE));
                            int i5 = rawQuery.getInt(rawQuery.getColumnIndex("isrealurl"));
                            String string11 = rawQuery.getString(rawQuery.getColumnIndex("format"));
                            int i6 = rawQuery.getInt(rawQuery.getColumnIndex("drmflag"));
                            String string12 = rawQuery.getString(rawQuery.getColumnIndex("sosourl"));
                            int i7 = rawQuery.getInt(rawQuery.getColumnIndex("completestate"));
                            int i8 = rawQuery.getInt(rawQuery.getColumnIndex("autopay"));
                            int i9 = rawQuery.getInt(rawQuery.getColumnIndex("resType"));
                            OnlineTag onlineTag = new OnlineTag(string, string3, Long.valueOf(string2).longValue());
                            onlineTag.h(string4);
                            onlineTag.a(string5);
                            onlineTag.e(string6);
                            onlineTag.d(i2);
                            onlineTag.c(i3);
                            onlineTag.b(string7);
                            onlineTag.a(Long.valueOf(string8).longValue());
                            onlineTag.c(string9);
                            onlineTag.f(string10);
                            onlineTag.e(i4);
                            onlineTag.f(i5);
                            onlineTag.k(string11);
                            onlineTag.i(i6);
                            onlineTag.g(string12);
                            onlineTag.h(i7);
                            onlineTag.c(i8 == 1);
                            onlineTag.j(i9);
                            arrayList.add(onlineTag);
                            if (!rawQuery.moveToNext()) {
                                break;
                            }
                        }
                    }
                } catch (Exception e2) {
                    com.qq.reader.common.monitor.f.a("OnlineTagHandle", "OnlineTagHandle importOnlineTagFromReaderZone " + e2.toString());
                    return false;
                } finally {
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
            int size2 = arrayList.size();
            for (i = 0; i < size2; i++) {
                b((OnlineTag) arrayList.get(i));
            }
        }
        return true;
    }

    protected synchronized boolean e(OnlineTag onlineTag) {
        boolean z = true;
        synchronized (this) {
            try {
                SQLiteDatabase a = k.a();
                ContentValues contentValues = new ContentValues();
                contentValues.put("onlineid", onlineTag.k());
                contentValues.put("curtime", Long.valueOf(onlineTag.m()));
                contentValues.put("downloadurl", onlineTag.l());
                contentValues.put("coverurl", onlineTag.u());
                contentValues.put("bookname", onlineTag.b());
                contentValues.put("author", onlineTag.o());
                contentValues.put("chapternums", Integer.valueOf(onlineTag.n()));
                contentValues.put("chapterid", Integer.valueOf(onlineTag.g()));
                contentValues.put("chaptername", onlineTag.h());
                contentValues.put("lastpoint", Long.valueOf(onlineTag.i()));
                contentValues.put(SocialConstants.PARAM_COMMENT, onlineTag.j());
                contentValues.put("timestr", ao.c("-", ":"));
                contentValues.put("bookurl", onlineTag.p());
                contentValues.put(SocialConstants.PARAM_SOURCE, Integer.valueOf(onlineTag.q()));
                contentValues.put("isrealurl", Integer.valueOf(onlineTag.t()));
                contentValues.put("format", onlineTag.B());
                contentValues.put("drmflag", Integer.valueOf(onlineTag.A()));
                contentValues.put("sosourl", onlineTag.v());
                contentValues.put("completestate", Integer.valueOf(onlineTag.w()));
                contentValues.put("online_book_is_show_on_history", Integer.valueOf(1));
                contentValues.put("last_time", Long.valueOf(onlineTag.H()));
                contentValues.put("autopay", Integer.valueOf(onlineTag.x() ? 1 : 0));
                contentValues.put("resType", Integer.valueOf(onlineTag.F()));
                a.replace("online", null, contentValues);
                k.c();
            } catch (Throwable e) {
                e.printStackTrace();
                com.qq.reader.common.monitor.b.a(e);
                CharSequence c = com.qq.reader.common.login.c.c().c();
                if (!(TextUtils.isEmpty(c) || TextUtils.isEmpty(onlineTag.k()))) {
                    Map hashMap = new HashMap();
                    hashMap.put(Constants.SOURCE_QQ, c);
                    hashMap.put(BookClubReplyCard.BID, onlineTag.k() + "");
                    hashMap.put("TYPE", "OnlineTag");
                    i.a("event_save_read_mark_fail", hashMap, ReaderApplication.getApplicationImp());
                }
                k.c();
                z = false;
            } catch (Throwable th) {
                k.c();
            }
        }
        return z;
    }

    private boolean g(OnlineTag onlineTag) {
        int delete;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = k.a();
            delete = sQLiteDatabase.delete("online", "onlineid= '" + onlineTag.k().replace("'", "''") + "'", null);
            if (sQLiteDatabase != null) {
                k.c();
            }
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.a("DB", "delOnlineTag with exception : " + e.getMessage());
            if (sQLiteDatabase != null) {
                k.c();
                delete = 0;
            } else {
                delete = 0;
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                k.c();
            }
        }
        if (delete > 0) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized com.qq.reader.cservice.onlineread.OnlineTag f(java.lang.String r33) {
        /*
        r32 = this;
        monitor-enter(r32);
        r2 = android.text.TextUtils.isEmpty(r33);	 Catch:{ all -> 0x020c }
        if (r2 == 0) goto L_0x000a;
    L_0x0007:
        r2 = 0;
    L_0x0008:
        monitor-exit(r32);
        return r2;
    L_0x000a:
        r11 = 0;
        r10 = 0;
        r3 = 0;
        r2 = k;	 Catch:{ Exception -> 0x01d8, all -> 0x020f }
        r2 = r2.a();	 Catch:{ Exception -> 0x01d8, all -> 0x020f }
        r3 = 22;
        r4 = new java.lang.String[r3];	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 0;
        r5 = "onlineid";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 1;
        r5 = "curtime";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 2;
        r5 = "downloadurl";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 3;
        r5 = "coverurl";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 4;
        r5 = "bookname";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 5;
        r5 = "author";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 6;
        r5 = "chapternums";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 7;
        r5 = "chapterid";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 8;
        r5 = "chaptername";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 9;
        r5 = "lastpoint";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 10;
        r5 = "description";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 11;
        r5 = "timestr";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 12;
        r5 = "bookurl";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 13;
        r5 = "source";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 14;
        r5 = "isrealurl";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 15;
        r5 = "format";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 16;
        r5 = "drmflag";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 17;
        r5 = "sosourl";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 18;
        r5 = "completestate";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 19;
        r5 = "autopay";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 20;
        r5 = "last_time";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = 21;
        r5 = "resType";
        r4[r3] = r5;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r3 = "online";
        r5 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r5.<init>();	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r6 = "onlineid= '";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r6 = "'";
        r7 = "''";
        r0 = r33;
        r6 = r0.replace(r6, r7);	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r6 = "'";
        r5 = r5.append(r6);	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r5 = r5.toString();	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x022a, all -> 0x021e }
        if (r4 == 0) goto L_0x023f;
    L_0x00dd:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        if (r3 <= 0) goto L_0x023f;
    L_0x00e3:
        r4.moveToFirst();	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 0;
        r6 = r4.getString(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 1;
        r8 = r4.getLong(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 2;
        r7 = r4.getString(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 3;
        r10 = r4.getString(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 4;
        r12 = r4.getString(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 5;
        r13 = r4.getString(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 6;
        r14 = r4.getInt(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 7;
        r15 = r4.getInt(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 8;
        r16 = r4.getString(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 9;
        r18 = r4.getLong(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 10;
        r17 = r4.getString(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 11;
        r20 = r4.getString(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 12;
        r21 = r4.getString(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 13;
        r22 = r4.getInt(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 14;
        r23 = r4.getInt(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 15;
        r24 = r4.getString(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 16;
        r25 = r4.getInt(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 17;
        r26 = r4.getString(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 18;
        r27 = r4.getInt(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r3 = 19;
        r3 = r4.getInt(r3);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r5 = 1;
        if (r3 != r5) goto L_0x01d6;
    L_0x0159:
        r3 = 1;
    L_0x015a:
        r5 = 20;
        r28 = r4.getLong(r5);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r5 = "resType";
        r5 = r4.getColumnIndex(r5);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r30 = r4.getInt(r5);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r5 = new com.qq.reader.cservice.onlineread.OnlineTag;	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r5.<init>(r6, r7, r8);	 Catch:{ Exception -> 0x022f, all -> 0x0222 }
        r5.h(r10);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r5.a(r12);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r5.e(r13);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r5.d(r14);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r5.c(r15);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r0 = r16;
        r5.b(r0);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r5.g(r15);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r0 = r18;
        r5.a(r0);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r0 = r17;
        r5.c(r0);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r0 = r20;
        r5.d(r0);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r0 = r21;
        r5.f(r0);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r0 = r22;
        r5.e(r0);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r0 = r23;
        r5.f(r0);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r0 = r24;
        r5.k(r0);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r0 = r25;
        r5.i(r0);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r0 = r26;
        r5.g(r0);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r0 = r27;
        r5.h(r0);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r5.c(r3);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r0 = r28;
        r5.d(r0);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r0 = r30;
        r5.j(r0);	 Catch:{ Exception -> 0x0234, all -> 0x0222 }
        r3 = r5;
    L_0x01c7:
        if (r4 == 0) goto L_0x01cc;
    L_0x01c9:
        r4.close();	 Catch:{ all -> 0x020c }
    L_0x01cc:
        if (r2 == 0) goto L_0x023c;
    L_0x01ce:
        r2 = k;	 Catch:{ all -> 0x020c }
        r2.c();	 Catch:{ all -> 0x020c }
        r2 = r3;
        goto L_0x0008;
    L_0x01d6:
        r3 = 0;
        goto L_0x015a;
    L_0x01d8:
        r2 = move-exception;
        r4 = r3;
        r5 = r10;
        r3 = r2;
        r2 = r11;
    L_0x01dd:
        r6 = "OnlineHandle";
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0227 }
        r7.<init>();	 Catch:{ all -> 0x0227 }
        r8 = "OnlineHandle getOnlineTagById ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0227 }
        r8 = r3.toString();	 Catch:{ all -> 0x0227 }
        r7 = r7.append(r8);	 Catch:{ all -> 0x0227 }
        r7 = r7.toString();	 Catch:{ all -> 0x0227 }
        com.qq.reader.common.monitor.f.a(r6, r7);	 Catch:{ all -> 0x0227 }
        com.qq.reader.common.monitor.b.a(r3);	 Catch:{ all -> 0x0227 }
        if (r5 == 0) goto L_0x0203;
    L_0x0200:
        r5.close();	 Catch:{ all -> 0x020c }
    L_0x0203:
        if (r4 == 0) goto L_0x0008;
    L_0x0205:
        r3 = k;	 Catch:{ all -> 0x020c }
        r3.c();	 Catch:{ all -> 0x020c }
        goto L_0x0008;
    L_0x020c:
        r2 = move-exception;
        monitor-exit(r32);
        throw r2;
    L_0x020f:
        r2 = move-exception;
        r4 = r3;
    L_0x0211:
        if (r10 == 0) goto L_0x0216;
    L_0x0213:
        r10.close();	 Catch:{ all -> 0x020c }
    L_0x0216:
        if (r4 == 0) goto L_0x021d;
    L_0x0218:
        r3 = k;	 Catch:{ all -> 0x020c }
        r3.c();	 Catch:{ all -> 0x020c }
    L_0x021d:
        throw r2;	 Catch:{ all -> 0x020c }
    L_0x021e:
        r3 = move-exception;
        r4 = r2;
        r2 = r3;
        goto L_0x0211;
    L_0x0222:
        r3 = move-exception;
        r10 = r4;
        r4 = r2;
        r2 = r3;
        goto L_0x0211;
    L_0x0227:
        r2 = move-exception;
        r10 = r5;
        goto L_0x0211;
    L_0x022a:
        r3 = move-exception;
        r4 = r2;
        r5 = r10;
        r2 = r11;
        goto L_0x01dd;
    L_0x022f:
        r3 = move-exception;
        r5 = r4;
        r4 = r2;
        r2 = r11;
        goto L_0x01dd;
    L_0x0234:
        r3 = move-exception;
        r31 = r2;
        r2 = r5;
        r5 = r4;
        r4 = r31;
        goto L_0x01dd;
    L_0x023c:
        r2 = r3;
        goto L_0x0008;
    L_0x023f:
        r3 = r11;
        goto L_0x01c7;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.v.f(java.lang.String):com.qq.reader.cservice.onlineread.OnlineTag");
    }

    private List<OnlineTag> e() {
        Cursor query;
        SQLiteDatabase sQLiteDatabase;
        Exception exception;
        Throwable th;
        List<OnlineTag> arrayList = new ArrayList();
        SQLiteDatabase sQLiteDatabase2 = null;
        try {
            SQLiteDatabase a = k.a();
            try {
                query = a.query("online", new String[]{"onlineid", "curtime", "downloadurl", "coverurl", "bookname", "author", "chapternums", "chapterid", "chaptername", "lastpoint", SocialConstants.PARAM_COMMENT, "timestr", "bookurl", SocialConstants.PARAM_SOURCE, "isrealurl", "format", "drmflag", "sosourl", "completestate", "autopay", "last_time", "resType"}, null, null, null, null, "curtime DESC");
            } catch (Exception e) {
                query = null;
                sQLiteDatabase = a;
                exception = e;
                sQLiteDatabase2 = sQLiteDatabase;
                try {
                    com.qq.reader.common.monitor.f.b("DB", "getAllOnlineTags with exception: " + exception.toString());
                    if (query != null) {
                        query.close();
                    }
                    if (sQLiteDatabase2 != null) {
                        k.c();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    if (sQLiteDatabase2 != null) {
                        k.c();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                query = null;
                sQLiteDatabase = a;
                th = th3;
                sQLiteDatabase2 = sQLiteDatabase;
                if (query != null) {
                    query.close();
                }
                if (sQLiteDatabase2 != null) {
                    k.c();
                }
                throw th;
            }
            try {
                if (query.moveToFirst()) {
                    do {
                        String string = query.getString(0);
                        long j = query.getLong(1);
                        String string2 = query.getString(2);
                        String string3 = query.getString(3);
                        String string4 = query.getString(4);
                        String string5 = query.getString(5);
                        int i = query.getInt(6);
                        int i2 = query.getInt(7);
                        String string6 = query.getString(8);
                        long j2 = query.getLong(9);
                        String string7 = query.getString(10);
                        String string8 = query.getString(11);
                        String string9 = query.getString(12);
                        int i3 = query.getInt(13);
                        int i4 = query.getInt(14);
                        String string10 = query.getString(15);
                        int i5 = query.getInt(16);
                        String string11 = query.getString(17);
                        int i6 = query.getInt(18);
                        boolean z = query.getInt(19) == 1;
                        long j3 = query.getLong(20);
                        int i7 = query.getInt(query.getColumnIndex("resType"));
                        OnlineTag onlineTag = new OnlineTag(string, string2, j);
                        onlineTag.h(string3);
                        onlineTag.a(string4);
                        onlineTag.e(string5);
                        onlineTag.d(i);
                        onlineTag.c(i2);
                        onlineTag.b(string6);
                        onlineTag.a(j2);
                        onlineTag.c(string7);
                        onlineTag.d(string8);
                        onlineTag.f(string9);
                        onlineTag.e(i3);
                        onlineTag.f(i4);
                        onlineTag.k(string10);
                        onlineTag.i(i5);
                        onlineTag.g(string11);
                        onlineTag.h(i6);
                        onlineTag.c(z);
                        onlineTag.d(j3);
                        onlineTag.j(i7);
                        arrayList.add(onlineTag);
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
                if (a != null) {
                    k.c();
                }
            } catch (Exception e2) {
                Exception exception2 = e2;
                sQLiteDatabase2 = a;
                exception = exception2;
                com.qq.reader.common.monitor.f.b("DB", "getAllOnlineTags with exception: " + exception.toString());
                if (query != null) {
                    query.close();
                }
                if (sQLiteDatabase2 != null) {
                    k.c();
                }
                return arrayList;
            } catch (Throwable th32) {
                Throwable th4 = th32;
                sQLiteDatabase2 = a;
                th = th4;
                if (query != null) {
                    query.close();
                }
                if (sQLiteDatabase2 != null) {
                    k.c();
                }
                throw th;
            }
        } catch (Exception e3) {
            exception = e3;
            query = null;
            com.qq.reader.common.monitor.f.b("DB", "getAllOnlineTags with exception: " + exception.toString());
            if (query != null) {
                query.close();
            }
            if (sQLiteDatabase2 != null) {
                k.c();
            }
            return arrayList;
        } catch (Throwable th5) {
            th = th5;
            query = null;
            if (query != null) {
                query.close();
            }
            if (sQLiteDatabase2 != null) {
                k.c();
            }
            throw th;
        }
        return arrayList;
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select sosourl from online", null);
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
                        com.qq.reader.common.monitor.f.b("DB", " update2To3 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE online ADD sosourl text");
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
            sQLiteDatabase.execSQL("ALTER TABLE online ADD sosourl text");
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            com.qq.reader.common.monitor.f.b("DB", " update2To3 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE online ADD sosourl text");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select completestate from online", null);
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
                        com.qq.reader.common.monitor.f.b("DB", " update3To4 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE online ADD completestate integer default 0");
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
            sQLiteDatabase.execSQL("ALTER TABLE online ADD completestate integer default 0");
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            com.qq.reader.common.monitor.f.b("DB", " update3To4 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE online ADD completestate integer default 0");
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
            rawQuery = sQLiteDatabase.rawQuery("select autopay from online", null);
            if (rawQuery != null) {
                try {
                    sQLiteDatabase.setVersion(5);
                    rawQuery.close();
                    if (cursor != null) {
                        cursor.close();
                        return;
                    }
                    return;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        com.qq.reader.common.monitor.f.b("DB", " update4To5 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE online ADD autopay integer default 0");
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
            sQLiteDatabase.execSQL("ALTER TABLE online ADD autopay integer default 0");
        } catch (Exception e3) {
            e = e3;
            rawQuery = cursor;
            com.qq.reader.common.monitor.f.b("DB", " update4To5 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE online ADD autopay integer default 0");
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
        sQLiteDatabase.execSQL("create unique index if not exists idx on online (onlineid);");
    }

    private void h(SQLiteDatabase sQLiteDatabase) {
        Exception e;
        Throwable th;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select last_time from online", null);
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
                        com.qq.reader.common.monitor.f.a("DB", " update8To9 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE online ADD last_time long default 0");
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
            com.qq.reader.common.monitor.f.a("DB", " update8To9 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE online ADD last_time long default 0");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE online ADD last_time long default 0");
    }

    private void i(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select resType from online", null);
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
                        com.qq.reader.common.monitor.f.a("DB", " update9To10 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE online ADD resType integer default 1");
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
            com.qq.reader.common.monitor.f.a("DB", " update9To10 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE online ADD resType integer default 1");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE online ADD resType integer default 1");
    }

    public synchronized void d() {
        try {
            k.a();
            k.c();
        } catch (Exception e) {
            com.qq.reader.common.monitor.f.b("DB", "OnlineHandle checkDBUpdate with exception: " + e.toString());
            k.c();
        } catch (Throwable th) {
            k.c();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.database.sqlite.SQLiteDatabase r1, int r2) {
        /*
        r0 = this;
        switch(r2) {
            case 1: goto L_0x0004;
            case 2: goto L_0x0004;
            case 3: goto L_0x0007;
            case 4: goto L_0x000a;
            case 5: goto L_0x000d;
            case 6: goto L_0x0010;
            case 7: goto L_0x0013;
            case 8: goto L_0x0016;
            case 9: goto L_0x0019;
            default: goto L_0x0003;
        };
    L_0x0003:
        return;
    L_0x0004:
        r0.b(r1);
    L_0x0007:
        r0.c(r1);
    L_0x000a:
        r0.d(r1);
    L_0x000d:
        r0.e(r1);
    L_0x0010:
        r0.f(r1);
    L_0x0013:
        r0.g(r1);
    L_0x0016:
        r0.h(r1);
    L_0x0019:
        r0.i(r1);
        goto L_0x0003;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.v.a(android.database.sqlite.SQLiteDatabase, int):void");
    }
}
