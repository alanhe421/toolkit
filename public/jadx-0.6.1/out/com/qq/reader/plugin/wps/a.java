package com.qq.reader.plugin.wps;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;

/* compiled from: WPSDBhandle */
public class a {
    private static c a;
    private static a b;

    public static a a() {
        if (b == null) {
            b = new a();
        }
        return b;
    }

    private a() {
        a = new a(this, com.qq.reader.common.c.a.at, null, 1);
    }

    public void a(String str, float f, float f2, int i, int i2) {
        c replace;
        if (str != null && str.length() != 0 && f >= 1.0f) {
            try {
                SQLiteDatabase a = a.a();
                ContentValues contentValues = new ContentValues();
                contentValues.put("bookid", str);
                contentValues.put("scale", Float.valueOf(f));
                contentValues.put("progress", Float.valueOf(f2));
                contentValues.put("scollx", Integer.valueOf(i));
                contentValues.put("scolly", Integer.valueOf(i2));
                replace = (int) a.replace("pdf_properties", null, contentValues);
            } catch (Exception e) {
                replace = e;
                replace = "error in updateBookNewContentState : " + replace.toString();
                f.a("BookmarkHandle", replace);
            } finally {
                c cVar = replace;
                a.c();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.qq.reader.plugin.wps.a.b a(java.lang.String r11) {
        /*
        r10 = this;
        r8 = 0;
        r0 = a;	 Catch:{ Exception -> 0x0090, all -> 0x00bf }
        r0 = r0.a();	 Catch:{ Exception -> 0x0090, all -> 0x00bf }
        r1 = 4;
        r2 = new java.lang.String[r1];	 Catch:{ Exception -> 0x00dd, all -> 0x00ce }
        r1 = 0;
        r3 = "scale";
        r2[r1] = r3;	 Catch:{ Exception -> 0x00dd, all -> 0x00ce }
        r1 = 1;
        r3 = "progress";
        r2[r1] = r3;	 Catch:{ Exception -> 0x00dd, all -> 0x00ce }
        r1 = 2;
        r3 = "scollx";
        r2[r1] = r3;	 Catch:{ Exception -> 0x00dd, all -> 0x00ce }
        r1 = 3;
        r3 = "scolly";
        r2[r1] = r3;	 Catch:{ Exception -> 0x00dd, all -> 0x00ce }
        r1 = "pdf_properties";
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00dd, all -> 0x00ce }
        r3.<init>();	 Catch:{ Exception -> 0x00dd, all -> 0x00ce }
        r4 = "bookid='";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00dd, all -> 0x00ce }
        r4 = "'";
        r5 = "''";
        r4 = r11.replace(r4, r5);	 Catch:{ Exception -> 0x00dd, all -> 0x00ce }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00dd, all -> 0x00ce }
        r4 = "'";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00dd, all -> 0x00ce }
        r3 = r3.toString();	 Catch:{ Exception -> 0x00dd, all -> 0x00ce }
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r2 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x00dd, all -> 0x00ce }
        if (r2 == 0) goto L_0x00ee;
    L_0x0054:
        r1 = r2.getCount();	 Catch:{ Exception -> 0x00e3, all -> 0x00d3 }
        if (r1 <= 0) goto L_0x00ee;
    L_0x005a:
        r1 = r2.moveToFirst();	 Catch:{ Exception -> 0x00e3, all -> 0x00d3 }
        if (r1 == 0) goto L_0x00ee;
    L_0x0060:
        r3 = new com.qq.reader.plugin.wps.a$b;	 Catch:{ Exception -> 0x00e3, all -> 0x00d3 }
        r3.<init>();	 Catch:{ Exception -> 0x00e3, all -> 0x00d3 }
        r1 = 0;
        r1 = r2.getFloat(r1);	 Catch:{ Exception -> 0x00e8, all -> 0x00d3 }
        r3.a = r1;	 Catch:{ Exception -> 0x00e8, all -> 0x00d3 }
        r1 = 1;
        r1 = r2.getFloat(r1);	 Catch:{ Exception -> 0x00e8, all -> 0x00d3 }
        r3.b = r1;	 Catch:{ Exception -> 0x00e8, all -> 0x00d3 }
        r1 = 2;
        r1 = r2.getInt(r1);	 Catch:{ Exception -> 0x00e8, all -> 0x00d3 }
        r3.c = r1;	 Catch:{ Exception -> 0x00e8, all -> 0x00d3 }
        r1 = 3;
        r1 = r2.getInt(r1);	 Catch:{ Exception -> 0x00e8, all -> 0x00d3 }
        r3.d = r1;	 Catch:{ Exception -> 0x00e8, all -> 0x00d3 }
        r1 = r3;
    L_0x0082:
        if (r0 == 0) goto L_0x00ec;
    L_0x0084:
        if (r2 == 0) goto L_0x0089;
    L_0x0086:
        r2.close();
    L_0x0089:
        r0 = a;
        r0.c();
        r0 = r1;
    L_0x008f:
        return r0;
    L_0x0090:
        r0 = move-exception;
        r1 = r0;
        r2 = r8;
        r0 = r8;
    L_0x0094:
        r3 = "PlugInDB";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00d9 }
        r4.<init>();	 Catch:{ all -> 0x00d9 }
        r5 = "getPluginAsType with exception : ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x00d9 }
        r1 = r1.getMessage();	 Catch:{ all -> 0x00d9 }
        r1 = r4.append(r1);	 Catch:{ all -> 0x00d9 }
        r1 = r1.toString();	 Catch:{ all -> 0x00d9 }
        com.qq.reader.common.monitor.f.a(r3, r1);	 Catch:{ all -> 0x00d9 }
        if (r8 == 0) goto L_0x008f;
    L_0x00b4:
        if (r2 == 0) goto L_0x00b9;
    L_0x00b6:
        r2.close();
    L_0x00b9:
        r1 = a;
        r1.c();
        goto L_0x008f;
    L_0x00bf:
        r0 = move-exception;
        r1 = r8;
    L_0x00c1:
        if (r1 == 0) goto L_0x00cd;
    L_0x00c3:
        if (r8 == 0) goto L_0x00c8;
    L_0x00c5:
        r8.close();
    L_0x00c8:
        r1 = a;
        r1.c();
    L_0x00cd:
        throw r0;
    L_0x00ce:
        r1 = move-exception;
        r9 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x00c1;
    L_0x00d3:
        r1 = move-exception;
        r8 = r2;
        r9 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x00c1;
    L_0x00d9:
        r0 = move-exception;
        r1 = r8;
        r8 = r2;
        goto L_0x00c1;
    L_0x00dd:
        r1 = move-exception;
        r2 = r8;
        r9 = r0;
        r0 = r8;
        r8 = r9;
        goto L_0x0094;
    L_0x00e3:
        r1 = move-exception;
        r9 = r0;
        r0 = r8;
        r8 = r9;
        goto L_0x0094;
    L_0x00e8:
        r1 = move-exception;
        r8 = r0;
        r0 = r3;
        goto L_0x0094;
    L_0x00ec:
        r0 = r1;
        goto L_0x008f;
    L_0x00ee:
        r1 = r8;
        goto L_0x0082;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.plugin.wps.a.a(java.lang.String):com.qq.reader.plugin.wps.a$b");
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists pdf_properties (_id integer primary key autoincrement, bookid text not null, scale float default 1.0, progress float default 0.0, scollx int default 0,scolly int default 0);");
        sQLiteDatabase.execSQL("create unique index if not exists idx on pdf_properties (bookid);");
    }
}
