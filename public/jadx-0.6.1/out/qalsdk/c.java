package qalsdk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.tencent.connect.common.Constants;
import com.tencent.qalsdk.QALSDKManager;
import com.tencent.qalsdk.util.QLog;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.CRC32;
import qalsdk.a.a;
import qalsdk.b.b;

/* compiled from: SqliteBasedCache */
public class c implements a {
    private static final String b = "SqliteBasedCache";
    private static c i = null;
    SQLiteDatabase a;
    private Context c;
    private b d;
    private long e = 0;
    private File f;
    private boolean g = false;
    private boolean h = false;

    private c() {
    }

    public static synchronized c b() {
        c cVar;
        synchronized (c.class) {
            if (i == null) {
                i = new c();
            }
            cVar = i;
        }
        return cVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Context r5) {
        /*
        r4 = this;
        r0 = 1;
        monitor-enter(r4);
        r1 = r4.h;	 Catch:{ all -> 0x005a }
        if (r1 == 0) goto L_0x0008;
    L_0x0006:
        monitor-exit(r4);	 Catch:{ all -> 0x005a }
    L_0x0007:
        return;
    L_0x0008:
        r1 = 1;
        r4.h = r1;	 Catch:{ all -> 0x005a }
        monitor-exit(r4);	 Catch:{ all -> 0x005a }
        r4.c = r5;
        if (r5 != 0) goto L_0x0019;
    L_0x0010:
        r1 = "SqliteBasedCache";
        r2 = "context is null!";
        com.tencent.qalsdk.util.QLog.e(r1, r2);
    L_0x0019:
        r1 = new java.io.File;
        r2 = r4.c;
        r2 = r2.getCacheDir();
        r3 = "QalHttpCacheV2";
        r1.<init>(r2, r3);
        r4.f = r1;
        r1 = r4.f;
        r1 = r1.exists();
        if (r1 != 0) goto L_0x005d;
    L_0x0031:
        r1 = r4.f;
        r1 = r1.mkdirs();
        if (r1 != 0) goto L_0x005d;
    L_0x0039:
        r0 = "SqliteBasedCache";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "failed to create dir: ";
        r1 = r1.append(r2);
        r2 = r4.f;
        r2 = r2.getAbsolutePath();
        r1 = r1.append(r2);
        r1 = r1.toString();
        com.tencent.qalsdk.util.QLog.e(r0, r1);
        goto L_0x0007;
    L_0x005a:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x005a }
        throw r0;
    L_0x005d:
        r1 = new qalsdk.b;
        r2 = r4.c;
        r1.<init>(r2);
        r4.d = r1;
        r1 = r4.d;
        r1 = r1.getWritableDatabase();
        r4.a = r1;
        r1 = r4.a;
        if (r1 == 0) goto L_0x0075;
    L_0x0072:
        r4.g = r0;
        goto L_0x0007;
    L_0x0075:
        r0 = 0;
        goto L_0x0072;
        */
        throw new UnsupportedOperationException("Method not decompiled: qalsdk.c.a(android.content.Context):void");
    }

    public void a(long j) {
        this.e = j;
    }

    public a a(String str) {
        if (this.e == 0 || str == null || str.length() == 0) {
            return null;
        }
        if (!this.h) {
            a(QALSDKManager.getInstance().getContext());
        }
        if (!this.g) {
            return null;
        }
        String a = a(str.getBytes());
        try {
            String[] strArr = new String[]{"ttl", b.a.e, b.a.g, b.a.h, b.a.i};
            String[] strArr2 = new String[]{a};
            Cursor query = this.a.query(b.a.a, strArr, "key=?", strArr2, null, null, null);
            if (query.moveToFirst()) {
                long j = query.getLong(2);
                String string = query.getString(3);
                String string2 = query.getString(4);
                query.close();
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    File d = d(a);
                    if (d.length() == 0) {
                        QLog.d(b, "file does not exist!");
                        return null;
                    }
                    FileInputStream fileInputStream = new FileInputStream(d);
                    byte[] bArr = new byte[((int) d.length())];
                    fileInputStream.read(bArr);
                    fileInputStream.close();
                    if (j != b(bArr)) {
                        QLog.d(b, "chksum not match!");
                        return null;
                    }
                    ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bArr));
                    a aVar = (a) objectInputStream.readObject();
                    objectInputStream.close();
                    QLog.d(b, "reading cost: " + (System.currentTimeMillis() - currentTimeMillis));
                    if ((string == null || string.equals("") || string.equals(aVar.k)) && (string2 == null || string2.equals("") || string2.equals(aVar.j))) {
                        return aVar;
                    }
                    QLog.d(b, "etag or last modified not match");
                    return null;
                } catch (IOException e) {
                    QLog.d(b, "stream is broken" + e.getMessage());
                    return null;
                } catch (ClassNotFoundException e2) {
                    QLog.d(b, "class not found exception");
                    return null;
                }
            }
            QLog.d(b, "Key: " + a + " is not found in the database");
            query.close();
            return null;
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void a(String str, a aVar) {
        if (this.e != 0 && str != null && str.length() != 0 && aVar != null) {
            if (!this.h) {
                a(QALSDKManager.getInstance().getContext());
            }
            if (!this.g) {
                QLog.e(b, "failed to init cache!!");
            } else if (aVar.s == null || ((double) aVar.s.length) < ((double) this.e) * 0.8d) {
                String a = a(str.getBytes());
                if (aVar.a != 304 || (aVar.p <= 0 && aVar.q <= 0)) {
                    try {
                        long j;
                        b((long) aVar.s.length);
                        long c = c();
                        aVar.t = (aVar.p < 0 ? 0 : aVar.p) + (System.currentTimeMillis() / 1000);
                        long currentTimeMillis = System.currentTimeMillis() / 1000;
                        if (aVar.q < 0) {
                            j = 0;
                        } else {
                            j = aVar.q;
                        }
                        aVar.u = j + currentTimeMillis;
                        String str2 = "key=?";
                        String[] strArr = new String[]{a};
                        Cursor query = this.a.query(b.a.a, new String[]{b.a.f}, str2, strArr, null, null, null);
                        long j2 = 0;
                        Object obj = null;
                        if (query.moveToFirst()) {
                            j2 = query.getLong(query.getColumnIndex(b.a.f));
                            obj = 1;
                        }
                        query.close();
                        if (!(aVar.d == null || aVar.d.isEmpty())) {
                            aVar.d.clear();
                        }
                        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                        objectOutputStream.writeObject(aVar);
                        objectOutputStream.close();
                        byte[] toByteArray = byteArrayOutputStream.toByteArray();
                        long b = b(toByteArray);
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("key", a);
                        contentValues.put(b.a.c, Long.valueOf(System.currentTimeMillis() / 1000));
                        contentValues.put("ttl", Long.valueOf(aVar.t));
                        contentValues.put(b.a.e, Long.valueOf(aVar.u));
                        contentValues.put(b.a.f, Integer.valueOf(aVar.s.length));
                        contentValues.put(b.a.g, Long.valueOf(b));
                        if (!(aVar.k == null || aVar.k.equals(""))) {
                            contentValues.put(b.a.h, aVar.k);
                        }
                        if (!(aVar.j == null || aVar.j.equals(""))) {
                            contentValues.put(b.a.i, aVar.j);
                        }
                        if (obj == null) {
                            if (this.a.insert(b.a.a, null, contentValues) < 0) {
                                QLog.e(b, "failed to insert database for key: " + a);
                            }
                        } else if (this.a.update(b.a.a, contentValues, str2, strArr) <= 0) {
                            QLog.e(b, "failed to update database for key: " + a);
                        }
                        currentTimeMillis = (((long) aVar.s.length) - j2) + c;
                        c(currentTimeMillis);
                        FileOutputStream fileOutputStream = new FileOutputStream(d(a));
                        fileOutputStream.write(toByteArray);
                        fileOutputStream.close();
                        QLog.d(b, "CurrentSize: " + currentTimeMillis);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                Cursor query2 = this.a.query(b.a.a, new String[]{b.a.h, b.a.i}, "key=?", new String[]{a}, null, null, null);
                if (query2.moveToFirst()) {
                    String string = query2.getString(0);
                    String string2 = query2.getString(1);
                    if ((string == null || string.equals("") || string.equals(aVar.k)) && (string2 == null || string2.equals("") || string2.equals(aVar.j))) {
                        aVar.t = (System.currentTimeMillis() / 1000) + aVar.p;
                        aVar.u = (System.currentTimeMillis() / 1000) + aVar.q;
                        ContentValues contentValues2 = new ContentValues();
                        contentValues2.put("ttl", Long.valueOf(aVar.t));
                        contentValues2.put(b.a.e, Long.valueOf(aVar.u));
                        contentValues2.put(b.a.c, Long.valueOf(System.currentTimeMillis() / 1000));
                        QLog.d(b, "Rows updated: " + this.a.update(b.a.a, contentValues2, "key=?", new String[]{a}));
                        return;
                    }
                    QLog.d(b, "etag or last_modified not match");
                    query2.close();
                    return;
                }
                QLog.d(b, "cache entry not found for updating expired time");
                query2.close();
            } else {
                QLog.i(b, "entry is too large to put in the cache!");
            }
        }
    }

    public void b(String str) {
        if (this.e != 0 && str != null && str.length() != 0) {
            if (!this.h) {
                a(QALSDKManager.getInstance().getContext());
            }
            if (this.g) {
                String a = a(str.getBytes());
                String[] strArr = new String[]{a};
                QLog.d(b, "update access: " + a);
                ContentValues contentValues = new ContentValues();
                contentValues.put(b.a.c, Long.valueOf(System.currentTimeMillis() / 1000));
                if (this.a.update(b.a.a, contentValues, "key=?", strArr) != 1) {
                    QLog.e(b, "cannot update key: " + a);
                    return;
                }
                return;
            }
            QLog.e(b, "cache not initialized!");
        }
    }

    public void c(String str) {
        if (this.e != 0 && str != null && str.length() != 0) {
            if (!this.h) {
                a(QALSDKManager.getInstance().getContext());
            }
            if (this.g) {
                File d = d(a(str.getBytes()));
                if (d.exists()) {
                    d.delete();
                }
                String[] strArr = new String[]{r0};
                this.a.delete(b.a.a, "key=?", strArr);
                return;
            }
            QLog.e(b, "cache not initialized!");
        }
    }

    public void a() {
        if (this.g && this.e != 0) {
            QLog.d(b, "rows deleted from database: " + this.a.delete(b.a.a, null, null));
            for (File file : this.f.listFiles()) {
                QLog.d(b, "delete " + file.getName());
                file.delete();
            }
            c(0);
        }
    }

    private void b(long j) {
        long j2;
        long c = c();
        Cursor query = this.a.query(b.a.a, new String[]{"key", b.a.f}, null, null, null, null, "accessTime ASC", Constants.VIA_REPORT_TYPE_SHARE_TO_QQ);
        if (query.moveToFirst()) {
            j2 = c;
            while (j2 + j >= this.e) {
                String string = query.getString(0);
                long j3 = query.getLong(1);
                File d = d(string);
                String[] strArr = new String[]{string};
                this.a.delete(b.a.a, "key=?", strArr);
                if (d.exists()) {
                    QLog.d(b, "delete key: " + string);
                    d.delete();
                    j2 -= j3;
                }
                if (!query.moveToNext()) {
                    QLog.d(b, "reach the end of the cursor");
                    break;
                }
            }
        }
        j2 = c;
        query.close();
        c(j2);
    }

    private File d(String str) {
        return new File(this.f, str);
    }

    private String a(byte[] bArr) {
        try {
            byte[] digest = MessageDigest.getInstance("MD5").digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                stringBuffer.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    private long b(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        CRC32 crc32 = new CRC32();
        crc32.update(bArr);
        return crc32.getValue();
    }

    private long c() {
        long j;
        String[] strArr = new String[]{b.c};
        String[] strArr2 = new String[]{"1"};
        Cursor query = this.a.query(b.a, strArr, "id=?", strArr2, null, null, null, null);
        if (query.moveToFirst()) {
            j = query.getLong(0);
        } else {
            j = 0;
        }
        query.close();
        return j;
    }

    private void c(long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(1));
        contentValues.put(b.c, Long.valueOf(j));
        if (this.a.replace(b.a, null, contentValues) < 0) {
            QLog.e(b, "failed to replace");
        }
    }
}
