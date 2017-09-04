package com.qq.reader.module.question.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import java.util.ArrayList;

/* compiled from: FamousAuthorSayDBHandler */
public class b extends com.qq.reader.appconfig.a.b {
    private static c a;
    private static volatile b b;

    /* compiled from: FamousAuthorSayDBHandler */
    private class a extends c {
        final /* synthetic */ b a;

        public a(b bVar, String str, CursorFactory cursorFactory, int i) {
            this.a = bVar;
            super(str, cursorFactory, i);
        }

        public void a(SQLiteDatabase sQLiteDatabase) {
            this.a.a(sQLiteDatabase);
        }

        public void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            this.a.a(sQLiteDatabase, i);
        }
    }

    public static b b() {
        if (b == null) {
            synchronized (b.class) {
                if (b == null) {
                    b = new b();
                }
            }
        }
        return b;
    }

    public void a(long j) {
        try {
            a.a().delete("question_data", "qatime<" + j + " and " + "type" + "= '" + "normalList" + "'", null);
        } catch (Exception e) {
            f.a("FamousAuthorSay", "clear with exception : " + e.getMessage());
        } finally {
            a.c();
        }
    }

    public boolean b(long j) {
        Object obj;
        Exception exception;
        Cursor cursor;
        Throwable th;
        Throwable th2;
        Object obj2;
        Cursor cursor2 = null;
        try {
            SQLiteDatabase a = a.a();
            try {
                Cursor query = a.query("question_data", new String[]{"qatime"}, "qatime>= '" + j + "'", null, null, null, null);
                if (query != null) {
                    try {
                        if (query.getCount() > 0) {
                            if (query != null) {
                                query.close();
                            }
                            if (a != null) {
                                try {
                                    a.c();
                                } catch (Exception e) {
                                }
                            }
                            return true;
                        }
                    } catch (Exception e2) {
                        obj = a;
                        exception = e2;
                        cursor = query;
                        try {
                            f.a("FamousAuthorSay", "saveAndDelRepeat with exception : " + exception.getMessage());
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (cursor2 != null) {
                                try {
                                    a.c();
                                } catch (Exception e3) {
                                }
                            }
                            return false;
                        } catch (Throwable th3) {
                            th = th3;
                            Cursor cursor3 = cursor;
                            cursor = cursor2;
                            cursor2 = cursor3;
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            if (cursor != null) {
                                try {
                                    a.c();
                                } catch (Exception e4) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        cursor2 = query;
                        th2 = th4;
                        obj2 = a;
                        th = th2;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (cursor != null) {
                            a.c();
                        }
                        throw th;
                    }
                }
                if (query != null) {
                    query.close();
                }
                if (a != null) {
                    try {
                        a.c();
                    } catch (Exception e5) {
                    }
                }
            } catch (Exception e22) {
                Exception exception2 = e22;
                cursor = null;
                obj = a;
                exception = exception2;
                f.a("FamousAuthorSay", "saveAndDelRepeat with exception : " + exception.getMessage());
                if (cursor != null) {
                    cursor.close();
                }
                if (cursor2 != null) {
                    a.c();
                }
                return false;
            } catch (Throwable th42) {
                th2 = th42;
                obj2 = a;
                th = th2;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (cursor != null) {
                    a.c();
                }
                throw th;
            }
        } catch (Exception e6) {
            exception = e6;
            cursor = null;
            f.a("FamousAuthorSay", "saveAndDelRepeat with exception : " + exception.getMessage());
            if (cursor != null) {
                cursor.close();
            }
            if (cursor2 != null) {
                a.c();
            }
            return false;
        } catch (Throwable th5) {
            th = th5;
            cursor = null;
            if (cursor2 != null) {
                cursor2.close();
            }
            if (cursor != null) {
                a.c();
            }
            throw th;
        }
        return false;
    }

    public boolean a(com.qq.reader.module.question.a.a aVar) {
        Object obj;
        Exception exception;
        Cursor cursor;
        Throwable th;
        Throwable th2;
        Object obj2;
        Cursor cursor2 = null;
        try {
            SQLiteDatabase a = a.a();
            try {
                Cursor query = a.query("question_data", new String[]{"qatime"}, "qatime> '" + aVar.f() + "'" + " and " + "qatime" + "< '" + aVar.e() + "'" + " and " + "type" + " != '" + "normalList" + "'", null, null, null, null);
                if (query != null) {
                    try {
                        if (query.getCount() > 0) {
                            if (query != null) {
                                query.close();
                            }
                            if (a != null) {
                                try {
                                    a.c();
                                } catch (Exception e) {
                                }
                            }
                            return true;
                        }
                    } catch (Exception e2) {
                        obj = a;
                        exception = e2;
                        cursor = query;
                        try {
                            f.a("FamousAuthorSay", "saveAndDelRepeat with exception : " + exception.getMessage());
                            if (cursor != null) {
                                cursor.close();
                            }
                            if (cursor2 != null) {
                                try {
                                    a.c();
                                } catch (Exception e3) {
                                }
                            }
                            return false;
                        } catch (Throwable th3) {
                            th = th3;
                            Cursor cursor3 = cursor;
                            cursor = cursor2;
                            cursor2 = cursor3;
                            if (cursor2 != null) {
                                cursor2.close();
                            }
                            if (cursor != null) {
                                try {
                                    a.c();
                                } catch (Exception e4) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th4) {
                        cursor2 = query;
                        th2 = th4;
                        obj2 = a;
                        th = th2;
                        if (cursor2 != null) {
                            cursor2.close();
                        }
                        if (cursor != null) {
                            a.c();
                        }
                        throw th;
                    }
                }
                if (query != null) {
                    query.close();
                }
                if (a != null) {
                    try {
                        a.c();
                    } catch (Exception e5) {
                    }
                }
            } catch (Exception e22) {
                Exception exception2 = e22;
                cursor = null;
                obj = a;
                exception = exception2;
                f.a("FamousAuthorSay", "saveAndDelRepeat with exception : " + exception.getMessage());
                if (cursor != null) {
                    cursor.close();
                }
                if (cursor2 != null) {
                    a.c();
                }
                return false;
            } catch (Throwable th42) {
                th2 = th42;
                obj2 = a;
                th = th2;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (cursor != null) {
                    a.c();
                }
                throw th;
            }
        } catch (Exception e6) {
            exception = e6;
            cursor = null;
            f.a("FamousAuthorSay", "saveAndDelRepeat with exception : " + exception.getMessage());
            if (cursor != null) {
                cursor.close();
            }
            if (cursor2 != null) {
                a.c();
            }
            return false;
        } catch (Throwable th5) {
            th = th5;
            cursor = null;
            if (cursor2 != null) {
                cursor2.close();
            }
            if (cursor != null) {
                a.c();
            }
            throw th;
        }
        return false;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.qq.reader.module.question.data.c r16) {
        /*
        r15 = this;
        r13 = 1;
        r4 = 0;
        r2 = r16.e();
        r3 = r2.g();
        if (r3 == 0) goto L_0x010e;
    L_0x000c:
        r2 = r3.size();
        if (r2 <= 0) goto L_0x010e;
    L_0x0012:
        r2 = a;	 Catch:{ Exception -> 0x010f, all -> 0x013e }
        r2 = r2.a();	 Catch:{ Exception -> 0x010f, all -> 0x013e }
        r12 = r3.iterator();	 Catch:{ Exception -> 0x015f, all -> 0x014f }
        r11 = r4;
    L_0x001d:
        r3 = r12.hasNext();	 Catch:{ Exception -> 0x016b, all -> 0x0154 }
        if (r3 == 0) goto L_0x0102;
    L_0x0023:
        r3 = r12.next();	 Catch:{ Exception -> 0x016b, all -> 0x0154 }
        r0 = r3;
        r0 = (com.qq.reader.module.question.card.FamousAuthorSayBaseCard) r0;	 Catch:{ Exception -> 0x016b, all -> 0x0154 }
        r10 = r0;
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x016b, all -> 0x0154 }
        r3.<init>();	 Catch:{ Exception -> 0x016b, all -> 0x0154 }
        r4 = "qaid= '";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x016b, all -> 0x0154 }
        r4 = r10.getId();	 Catch:{ Exception -> 0x016b, all -> 0x0154 }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x016b, all -> 0x0154 }
        r4 = "'";
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x016b, all -> 0x0154 }
        r5 = r3.toString();	 Catch:{ Exception -> 0x016b, all -> 0x0154 }
        r3 = "question_data";
        r4 = 1;
        r4 = new java.lang.String[r4];	 Catch:{ Exception -> 0x016b, all -> 0x0154 }
        r6 = 0;
        r7 = "qaid";
        r4[r6] = r7;	 Catch:{ Exception -> 0x016b, all -> 0x0154 }
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r4 = r2.query(r3, r4, r5, r6, r7, r8, r9);	 Catch:{ Exception -> 0x016b, all -> 0x0154 }
        if (r4 == 0) goto L_0x00b3;
    L_0x0060:
        r3 = r4.getCount();	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        if (r3 <= 0) goto L_0x00b3;
    L_0x0066:
        r3 = r10.getType();	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r6 = "ad";
        r6 = r6.equalsIgnoreCase(r3);	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        if (r6 != 0) goto L_0x007c;
    L_0x0073:
        r6 = "recommend";
        r3 = r6.equalsIgnoreCase(r3);	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        if (r3 == 0) goto L_0x00a7;
    L_0x007c:
        r3 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r3.<init>();	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r6 = "qatime";
        r8 = r10.getDisplayTime();	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r7 = java.lang.Long.valueOf(r8);	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r3.put(r6, r7);	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r6 = "qajson";
        r7 = r10.getOrginCardJsonOjb();	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r7 = r7.toString();	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r3.put(r6, r7);	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r6 = "question_data";
        r7 = 0;
        r2.update(r6, r3, r5, r7);	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r11 = r4;
        goto L_0x001d;
    L_0x00a7:
        r3 = r16.c();	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        if (r3 == r13) goto L_0x0172;
    L_0x00ad:
        r12.remove();	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r11 = r4;
        goto L_0x001d;
    L_0x00b3:
        r3 = new android.content.ContentValues;	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r3.<init>();	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r5 = "qaid";
        r6 = r10.getId();	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r3.put(r5, r6);	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r5 = "type";
        r6 = r10.getType();	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r3.put(r5, r6);	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r5 = "qatime";
        r6 = r10.getDisplayTime();	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r6 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r3.put(r5, r6);	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r5 = "qajson";
        r6 = r10.getOrginCardJsonOjb();	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r6 = r6.toString();	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r3.put(r5, r6);	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r5 = "question_data";
        r6 = 0;
        r6 = r2.insert(r5, r6, r3);	 Catch:{ Exception -> 0x0165, all -> 0x014f }
        r8 = 0;
        r3 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r3 >= 0) goto L_0x00ff;
    L_0x00f6:
        r3 = "FamousAuthorSay";
        r5 = "saveAndDelRepeat with Error";
        com.qq.reader.common.monitor.f.a(r3, r5);	 Catch:{ Exception -> 0x0165, all -> 0x014f }
    L_0x00ff:
        r11 = r4;
        goto L_0x001d;
    L_0x0102:
        if (r11 == 0) goto L_0x0107;
    L_0x0104:
        r11.close();
    L_0x0107:
        if (r2 == 0) goto L_0x010e;
    L_0x0109:
        r2 = a;	 Catch:{ Exception -> 0x0170 }
        r2.c();	 Catch:{ Exception -> 0x0170 }
    L_0x010e:
        return;
    L_0x010f:
        r2 = move-exception;
        r3 = r4;
    L_0x0111:
        r5 = "FamousAuthorSay";
        r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x015a }
        r6.<init>();	 Catch:{ all -> 0x015a }
        r7 = "saveAndDelRepeat with exception : ";
        r6 = r6.append(r7);	 Catch:{ all -> 0x015a }
        r2 = r2.getMessage();	 Catch:{ all -> 0x015a }
        r2 = r6.append(r2);	 Catch:{ all -> 0x015a }
        r2 = r2.toString();	 Catch:{ all -> 0x015a }
        com.qq.reader.common.monitor.f.a(r5, r2);	 Catch:{ all -> 0x015a }
        if (r3 == 0) goto L_0x0134;
    L_0x0131:
        r3.close();
    L_0x0134:
        if (r4 == 0) goto L_0x010e;
    L_0x0136:
        r2 = a;	 Catch:{ Exception -> 0x013c }
        r2.c();	 Catch:{ Exception -> 0x013c }
        goto L_0x010e;
    L_0x013c:
        r2 = move-exception;
        goto L_0x010e;
    L_0x013e:
        r2 = move-exception;
        r3 = r4;
    L_0x0140:
        if (r4 == 0) goto L_0x0145;
    L_0x0142:
        r4.close();
    L_0x0145:
        if (r3 == 0) goto L_0x014c;
    L_0x0147:
        r3 = a;	 Catch:{ Exception -> 0x014d }
        r3.c();	 Catch:{ Exception -> 0x014d }
    L_0x014c:
        throw r2;
    L_0x014d:
        r3 = move-exception;
        goto L_0x014c;
    L_0x014f:
        r3 = move-exception;
        r14 = r3;
        r3 = r2;
        r2 = r14;
        goto L_0x0140;
    L_0x0154:
        r3 = move-exception;
        r4 = r11;
        r14 = r3;
        r3 = r2;
        r2 = r14;
        goto L_0x0140;
    L_0x015a:
        r2 = move-exception;
        r14 = r3;
        r3 = r4;
        r4 = r14;
        goto L_0x0140;
    L_0x015f:
        r3 = move-exception;
        r14 = r3;
        r3 = r4;
        r4 = r2;
        r2 = r14;
        goto L_0x0111;
    L_0x0165:
        r3 = move-exception;
        r14 = r3;
        r3 = r4;
        r4 = r2;
        r2 = r14;
        goto L_0x0111;
    L_0x016b:
        r3 = move-exception;
        r4 = r2;
        r2 = r3;
        r3 = r11;
        goto L_0x0111;
    L_0x0170:
        r2 = move-exception;
        goto L_0x010e;
    L_0x0172:
        r11 = r4;
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.module.question.data.b.a(com.qq.reader.module.question.data.c):void");
    }

    public ArrayList<String> a(long j, int i) {
        Object obj;
        Exception exception;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        ArrayList<String> arrayList = new ArrayList();
        try {
            SQLiteDatabase a = a.a();
            try {
                cursor2 = a.query("question_data", new String[]{"qajson", "qatime"}, "qatime < " + j, null, null, null, "qatime DESC LIMIT " + i);
                if (cursor2.moveToFirst()) {
                    do {
                        arrayList.add(cursor2.getString(0));
                    } while (cursor2.moveToNext());
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (a != null) {
                    try {
                        a.c();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                Exception exception2 = e2;
                obj = a;
                exception = exception2;
                try {
                    f.a("FamousAuthorSay", "getLatestMessage with exception: " + exception.toString());
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (cursor != null) {
                        try {
                            a.c();
                        } catch (Exception e3) {
                        }
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (cursor != null) {
                        try {
                            a.c();
                        } catch (Exception e4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                obj = a;
                th = th4;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (cursor != null) {
                    a.c();
                }
                throw th;
            }
        } catch (Exception e5) {
            exception = e5;
            cursor = cursor2;
            f.a("FamousAuthorSay", "getLatestMessage with exception: " + exception.toString());
            if (cursor2 != null) {
                cursor2.close();
            }
            if (cursor != null) {
                a.c();
            }
            return arrayList;
        } catch (Throwable th5) {
            th = th5;
            cursor = cursor2;
            if (cursor2 != null) {
                cursor2.close();
            }
            if (cursor != null) {
                a.c();
            }
            throw th;
        }
        return arrayList;
    }

    public ArrayList<String> a(int i) {
        Object obj;
        Exception exception;
        Cursor cursor;
        Throwable th;
        Cursor cursor2 = null;
        ArrayList<String> arrayList = new ArrayList();
        try {
            SQLiteDatabase a = a.a();
            try {
                cursor2 = a.query("question_data", new String[]{"qajson", "qatime"}, null, null, null, null, "qatime DESC LIMIT " + i);
                if (cursor2.moveToFirst()) {
                    do {
                        arrayList.add(cursor2.getString(0));
                    } while (cursor2.moveToNext());
                }
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (a != null) {
                    try {
                        a.c();
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
                Exception exception2 = e2;
                obj = a;
                exception = exception2;
                try {
                    f.a("FamousAuthorSay", "getLatestMessage with exception: " + exception.toString());
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (cursor != null) {
                        try {
                            a.c();
                        } catch (Exception e3) {
                        }
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    if (cursor != null) {
                        try {
                            a.c();
                        } catch (Exception e4) {
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                obj = a;
                th = th4;
                if (cursor2 != null) {
                    cursor2.close();
                }
                if (cursor != null) {
                    a.c();
                }
                throw th;
            }
        } catch (Exception e5) {
            exception = e5;
            cursor = cursor2;
            f.a("FamousAuthorSay", "getLatestMessage with exception: " + exception.toString());
            if (cursor2 != null) {
                cursor2.close();
            }
            if (cursor != null) {
                a.c();
            }
            return arrayList;
        } catch (Throwable th5) {
            th = th5;
            cursor = cursor2;
            if (cursor2 != null) {
                cursor2.close();
            }
            if (cursor != null) {
                a.c();
            }
            throw th;
        }
        return arrayList;
    }

    public void a(String str, String str2) {
        Exception e;
        Throwable th;
        Cursor cursor = null;
        SQLiteDatabase a;
        try {
            a = a.a();
            try {
                String str3 = "qaid= '" + str + "'";
                ContentValues contentValues = new ContentValues();
                contentValues.put("qajson", str2);
                a.update("question_data", contentValues, str3, null);
                if (cursor != null) {
                    cursor.close();
                }
                if (a != null) {
                    try {
                        a.c();
                    } catch (Exception e2) {
                    }
                }
            } catch (Exception e3) {
                e = e3;
                try {
                    f.a("FamousAuthorSay", "reSaveJson with exception : " + e.getMessage());
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (a != null) {
                        try {
                            a.c();
                        } catch (Exception e4) {
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (a != null) {
                        try {
                            a.c();
                        } catch (Exception e5) {
                        }
                    }
                    throw th;
                }
            }
        } catch (Exception e6) {
            e = e6;
            a = cursor;
            f.a("FamousAuthorSay", "reSaveJson with exception : " + e.getMessage());
            if (cursor != null) {
                cursor.close();
            }
            if (a != null) {
                a.c();
            }
        } catch (Throwable th3) {
            th = th3;
            a = cursor;
            if (cursor != null) {
                cursor.close();
            }
            if (a != null) {
                a.c();
            }
            throw th;
        }
    }

    private b() {
        a = new a(this, com.qq.reader.common.c.a.bP, null, 1);
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists question_data (qaid text not null,type text not null,qatime long default 0,qajson text not null);");
        sQLiteDatabase.execSQL("create unique index if not exists idx on question_data (qaid);");
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
    }

    public void a() {
        synchronized (b.class) {
            b = null;
        }
    }
}
