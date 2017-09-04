package com.qq.reader.common.db.handle;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.qq.reader.common.db.a;
import com.qq.reader.common.db.c;
import com.qq.reader.module.question.b;
import com.qq.reader.module.question.data.AudioData;
import com.qq.reader.module.question.data.AudioData.AnswerData;
import com.tencent.android.tpush.common.MessageKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: BookQuestionHandle */
public class g extends a {
    protected static c b;
    protected static String c;
    private static int m = 0;
    private static g n;
    private List<AudioData> d;
    private List<AudioData> e;
    private List<AudioData> f;
    private com.qq.reader.module.question.data.a g;
    private int h = 3;
    private int i = 10;
    private int j = -1;
    private String k = "";
    private Map<String, AudioData> l;
    private int o = 0;
    private boolean p = false;
    private boolean q = false;
    private int r = -1;
    private int s = -1;
    private SimpleDateFormat t = new SimpleDateFormat("yyyyMMdd");

    public static synchronized g a() {
        g gVar;
        synchronized (g.class) {
            if (n == null) {
                n = new g();
            }
            if (b == null || !com.qq.reader.common.c.a.bs.equalsIgnoreCase(c)) {
                b = new w(com.qq.reader.common.c.a.bs, null, 1);
                c = com.qq.reader.common.c.a.bs;
            }
            gVar = n;
        }
        return gVar;
    }

    private g() {
    }

    public void a(String str, String str2, boolean z) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.getInt("code") == 0) {
                this.q = jSONObject.optBoolean("isActive", false);
                com.qq.reader.common.monitor.debug.c.a("BookQuestionHandle", "isActive is " + this.q);
                int optInt = jSONObject.optInt("showTimes", -1);
                if (optInt > 0) {
                    this.h = optInt;
                    if (this.j < 0) {
                        this.j = this.h;
                    }
                }
                optInt = jSONObject.optInt("chapterGap", -1);
                if (optInt > 0) {
                    this.i = optInt;
                }
                JSONArray optJSONArray = jSONObject.optJSONArray("qanodes");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    if (this.d == null) {
                        this.d = new ArrayList();
                    }
                    if (this.e == null) {
                        this.e = new ArrayList();
                    }
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                        AudioData audioData = new AudioData();
                        audioData.a(jSONObject2);
                        if (a(audioData.b())) {
                            com.qq.reader.common.monitor.debug.c.a("BookQuestionHandle", "have new limit " + audioData.b().f());
                            this.e.add(audioData);
                        } else if (this.d.size() < this.j) {
                            this.d.add(audioData);
                        }
                        a(0, audioData.b().f(), audioData.a().g(), jSONObject2);
                    }
                }
                JSONObject optJSONObject = jSONObject.optJSONObject("author");
                if (optJSONObject != null) {
                    long optLong = optJSONObject.optLong("authorId");
                    if (this.g == null || this.g.d() != optLong) {
                        this.g = new com.qq.reader.module.question.data.a();
                        a(optLong, this.t.format(new Date()), this.h, optJSONObject.toString());
                        a(optLong, str2);
                    } else {
                        b(optLong, optJSONObject.toString());
                    }
                    this.g.a(optJSONObject);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void f() {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.d.clear();
        if (this.e == null) {
            this.e = new ArrayList();
        }
        this.e.clear();
        if (this.f == null) {
            this.f = new ArrayList();
        }
        this.f.clear();
        if (this.l == null) {
            this.l = new HashMap();
        }
        this.l.clear();
        this.g = null;
        this.j = -1;
        this.k = "";
        this.r = -1;
        this.s = -1;
        this.o = 0;
        this.p = false;
        this.q = false;
        m = 0;
    }

    public synchronized String a(long j) {
        String str;
        f();
        if (j <= 0) {
            str = "";
        } else {
            c(j);
            str = b(j);
        }
        return str;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.String b(long r14) {
        /*
        r13 = this;
        r10 = 1;
        r11 = 0;
        r8 = 0;
        monitor-enter(r13);
        r9 = "";
        r0 = b;	 Catch:{ Exception -> 0x0135, all -> 0x012f }
        r0 = r0.a();	 Catch:{ Exception -> 0x0135, all -> 0x012f }
        if (r0 == 0) goto L_0x0142;
    L_0x000f:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0135, all -> 0x012f }
        r1.<init>();	 Catch:{ Exception -> 0x0135, all -> 0x012f }
        r2 = "aid= ";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0135, all -> 0x012f }
        r1 = r1.append(r14);	 Catch:{ Exception -> 0x0135, all -> 0x012f }
        r3 = r1.toString();	 Catch:{ Exception -> 0x0135, all -> 0x012f }
        r1 = 2;
        r2 = new java.lang.String[r1];	 Catch:{ Exception -> 0x0135, all -> 0x012f }
        r1 = 0;
        r4 = "showed";
        r2[r1] = r4;	 Catch:{ Exception -> 0x0135, all -> 0x012f }
        r1 = 1;
        r4 = "json";
        r2[r1] = r4;	 Catch:{ Exception -> 0x0135, all -> 0x012f }
        r1 = "question_table_name";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r1 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x0135, all -> 0x012f }
        r0 = r1.getCount();	 Catch:{ Exception -> 0x013a, all -> 0x011f }
        r13.o = r0;	 Catch:{ Exception -> 0x013a, all -> 0x011f }
        r0 = "BookQuestionHandle";
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x013a, all -> 0x011f }
        r2.<init>();	 Catch:{ Exception -> 0x013a, all -> 0x011f }
        r3 = "whole list count is ";
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x013a, all -> 0x011f }
        r3 = r13.o;	 Catch:{ Exception -> 0x013a, all -> 0x011f }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x013a, all -> 0x011f }
        r2 = r2.toString();	 Catch:{ Exception -> 0x013a, all -> 0x011f }
        com.qq.reader.common.monitor.debug.c.a(r0, r2);	 Catch:{ Exception -> 0x013a, all -> 0x011f }
        r0 = r1.moveToFirst();	 Catch:{ Exception -> 0x013a, all -> 0x011f }
        if (r0 == 0) goto L_0x013f;
    L_0x0065:
        r2 = r9;
    L_0x0066:
        r0 = 0;
        r0 = r1.getInt(r0);	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        if (r0 == 0) goto L_0x00c8;
    L_0x006d:
        r0 = r10;
    L_0x006e:
        r3 = 1;
        r3 = r1.getString(r3);	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r4 = new com.qq.reader.module.question.data.AudioData;	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r4.<init>();	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r5 = new org.json.JSONObject;	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r5.<init>(r3);	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r4.a(r5);	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        if (r0 == 0) goto L_0x00ca;
    L_0x0082:
        r0 = r13.f;	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r0 = r0.size();	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r3 = r13.j;	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        if (r0 >= r3) goto L_0x0091;
    L_0x008c:
        r0 = r13.f;	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r0.add(r4);	 Catch:{ Exception -> 0x00da, all -> 0x011f }
    L_0x0091:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r0.<init>();	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r3 = r4.a();	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r3 = r3.g();	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r0 = r0.append(r3);	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r3 = ",";
        r0 = r0.append(r3);	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r2 = r0.toString();	 Catch:{ Exception -> 0x00da, all -> 0x011f }
    L_0x00b1:
        r0 = r1.moveToNext();	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        if (r0 != 0) goto L_0x0066;
    L_0x00b7:
        r0 = r2;
    L_0x00b8:
        if (r1 == 0) goto L_0x00bd;
    L_0x00ba:
        r1.close();	 Catch:{ all -> 0x010c }
    L_0x00bd:
        r1 = b;	 Catch:{ all -> 0x010c }
        if (r1 == 0) goto L_0x00c6;
    L_0x00c1:
        r1 = b;	 Catch:{ all -> 0x010c }
        r1.c();	 Catch:{ all -> 0x010c }
    L_0x00c6:
        monitor-exit(r13);
        return r0;
    L_0x00c8:
        r0 = r11;
        goto L_0x006e;
    L_0x00ca:
        r0 = r4.b();	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r0 = r13.a(r0);	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        if (r0 == 0) goto L_0x010f;
    L_0x00d4:
        r0 = r13.e;	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r0.add(r4);	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        goto L_0x00b1;
    L_0x00da:
        r0 = move-exception;
        r12 = r0;
        r0 = r2;
        r2 = r1;
        r1 = r12;
    L_0x00df:
        r3 = "BookQuestionHandle";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0132 }
        r4.<init>();	 Catch:{ all -> 0x0132 }
        r5 = " buildAudioDataFromDB with exception : ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x0132 }
        r1 = r1.getMessage();	 Catch:{ all -> 0x0132 }
        r1 = r4.append(r1);	 Catch:{ all -> 0x0132 }
        r1 = r1.toString();	 Catch:{ all -> 0x0132 }
        com.qq.reader.common.monitor.debug.c.e(r3, r1);	 Catch:{ all -> 0x0132 }
        if (r2 == 0) goto L_0x0102;
    L_0x00ff:
        r2.close();	 Catch:{ all -> 0x010c }
    L_0x0102:
        r1 = b;	 Catch:{ all -> 0x010c }
        if (r1 == 0) goto L_0x00c6;
    L_0x0106:
        r1 = b;	 Catch:{ all -> 0x010c }
        r1.c();	 Catch:{ all -> 0x010c }
        goto L_0x00c6;
    L_0x010c:
        r0 = move-exception;
        monitor-exit(r13);
        throw r0;
    L_0x010f:
        r0 = r13.d;	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r0 = r0.size();	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r3 = r13.j;	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        if (r0 >= r3) goto L_0x00b1;
    L_0x0119:
        r0 = r13.d;	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        r0.add(r4);	 Catch:{ Exception -> 0x00da, all -> 0x011f }
        goto L_0x00b1;
    L_0x011f:
        r0 = move-exception;
    L_0x0120:
        if (r1 == 0) goto L_0x0125;
    L_0x0122:
        r1.close();	 Catch:{ all -> 0x010c }
    L_0x0125:
        r1 = b;	 Catch:{ all -> 0x010c }
        if (r1 == 0) goto L_0x012e;
    L_0x0129:
        r1 = b;	 Catch:{ all -> 0x010c }
        r1.c();	 Catch:{ all -> 0x010c }
    L_0x012e:
        throw r0;	 Catch:{ all -> 0x010c }
    L_0x012f:
        r0 = move-exception;
        r1 = r8;
        goto L_0x0120;
    L_0x0132:
        r0 = move-exception;
        r1 = r2;
        goto L_0x0120;
    L_0x0135:
        r0 = move-exception;
        r1 = r0;
        r2 = r8;
        r0 = r9;
        goto L_0x00df;
    L_0x013a:
        r0 = move-exception;
        r2 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x00df;
    L_0x013f:
        r0 = r9;
        goto L_0x00b8;
    L_0x0142:
        r1 = r8;
        r0 = r9;
        goto L_0x00b8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.g.b(long):java.lang.String");
    }

    public synchronized void c(long j) {
        Exception e;
        Throwable th;
        Cursor query;
        try {
            SQLiteDatabase a = b.a();
            if (a != null) {
                String[] strArr = new String[]{MessageKey.MSG_DATE, "remain", "json"};
                query = a.query("question_author_table_name", strArr, "aid= " + j, null, null, null, null);
                try {
                    if (query.moveToFirst()) {
                        do {
                            this.k = query.getString(0);
                            this.j = query.getInt(1);
                            String string = query.getString(2);
                            if (this.g == null) {
                                this.g = new com.qq.reader.module.question.data.a();
                            }
                            this.g.a(new JSONObject(string));
                            string = this.t.format(new Date());
                            if (!this.k.equals(string)) {
                                this.k = string;
                                this.j = this.h;
                                a(this.g.d(), this.k, this.h);
                            }
                        } while (query.moveToNext());
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } else {
                query = null;
            }
            if (query != null) {
                query.close();
            }
            if (b != null) {
                b.c();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            try {
                com.qq.reader.common.monitor.debug.c.e("BookQuestionHandle", " buildAuthorDataFromDB with exception : " + e.getMessage());
                if (query != null) {
                    query.close();
                }
                if (b != null) {
                    b.c();
                }
            } catch (Throwable th2) {
                th = th2;
                if (query != null) {
                    query.close();
                }
                if (b != null) {
                    b.c();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            if (b != null) {
                b.c();
            }
            throw th;
        }
    }

    private synchronized void a(int i, long j, String str, JSONObject jSONObject) {
        if (this.o >= 30) {
            a(j, (this.o - 30) + 1);
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("showed", Integer.valueOf(i));
        contentValues.put("aid", Long.valueOf(j));
        contentValues.put("qid", str);
        contentValues.put("json", jSONObject.toString());
        a(b, "question_table_name", contentValues);
    }

    public synchronized void a(long j, String str, int i, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("aid", Long.valueOf(j));
        contentValues.put(MessageKey.MSG_DATE, str);
        contentValues.put("remain", Integer.valueOf(i));
        contentValues.put("json", str2);
        a(b, "question_author_table_name", contentValues);
    }

    private synchronized void a(long j, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("aid", Long.valueOf(j));
        contentValues.put("bid", str);
        a(b, "question_author_book_table_name", contentValues);
    }

    private synchronized void a(long j, int i) {
        Exception e;
        Throwable th;
        Cursor query;
        try {
            SQLiteDatabase a = b.a();
            if (a != null) {
                String[] strArr = new String[]{"qid"};
                query = a.query("question_table_name", strArr, "aid= " + j, null, null, null, null);
                try {
                    if (query.moveToFirst()) {
                        while (i > 0) {
                            if (((long) a.delete("question_table_name", "qid= " + query.getString(0), null)) > 0) {
                                this.o--;
                            }
                            i--;
                            if (!query.moveToNext()) {
                                break;
                            }
                        }
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        com.qq.reader.common.monitor.debug.c.e("BookQuestionHandle", " delAudioDataItem with exception : " + e.getMessage());
                        if (query != null) {
                            query.close();
                        }
                        if (b != null) {
                            b.c();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        if (b != null) {
                            b.c();
                        }
                        throw th;
                    }
                }
            }
            query = null;
            if (query != null) {
                query.close();
            }
            if (b != null) {
                b.c();
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            com.qq.reader.common.monitor.debug.c.e("BookQuestionHandle", " delAudioDataItem with exception : " + e.getMessage());
            if (query != null) {
                query.close();
            }
            if (b != null) {
                b.c();
            }
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            if (b != null) {
                b.c();
            }
            throw th;
        }
    }

    private synchronized void a(String str, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("showed", Integer.valueOf(i));
        a(b, "question_table_name", contentValues, "qid= '" + str + "'");
    }

    private synchronized void b(long j, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("aid", Long.valueOf(j));
        contentValues.put("json", str);
        a(b, "question_author_table_name", contentValues, "aid= " + j);
    }

    private synchronized void a(long j, String str, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("aid", Long.valueOf(j));
        contentValues.put(MessageKey.MSG_DATE, str);
        contentValues.put("remain", Integer.valueOf(i));
        a(b, "question_author_table_name", contentValues, "aid= " + j);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized long a(java.lang.String r13) {
        /*
        r12 = this;
        r8 = 0;
        monitor-enter(r12);
        r10 = 0;
        r0 = b;	 Catch:{ Exception -> 0x005c }
        r0 = r0.a();	 Catch:{ Exception -> 0x005c }
        if (r0 == 0) goto L_0x00af;
    L_0x000c:
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x005c }
        r1.<init>();	 Catch:{ Exception -> 0x005c }
        r2 = "bid= '";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x005c }
        r1 = r1.append(r13);	 Catch:{ Exception -> 0x005c }
        r2 = "'";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x005c }
        r3 = r1.toString();	 Catch:{ Exception -> 0x005c }
        r1 = 1;
        r2 = new java.lang.String[r1];	 Catch:{ Exception -> 0x005c }
        r1 = 0;
        r4 = "aid";
        r2[r1] = r4;	 Catch:{ Exception -> 0x005c }
        r1 = "question_author_book_table_name";
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r0 = r0.query(r1, r2, r3, r4, r5, r6, r7);	 Catch:{ Exception -> 0x005c }
        r1 = r0.moveToFirst();	 Catch:{ Exception -> 0x00a2, all -> 0x009e }
        if (r1 == 0) goto L_0x00ad;
    L_0x0041:
        r1 = 0;
        r2 = r0.getLong(r1);	 Catch:{ Exception -> 0x00a2, all -> 0x009e }
        r1 = r0.moveToNext();	 Catch:{ Exception -> 0x00a7, all -> 0x009e }
        if (r1 != 0) goto L_0x00ab;
    L_0x004c:
        if (r0 == 0) goto L_0x0051;
    L_0x004e:
        r0.close();	 Catch:{ all -> 0x008b }
    L_0x0051:
        r0 = b;	 Catch:{ all -> 0x008b }
        if (r0 == 0) goto L_0x005a;
    L_0x0055:
        r0 = b;	 Catch:{ all -> 0x008b }
        r0.c();	 Catch:{ all -> 0x008b }
    L_0x005a:
        monitor-exit(r12);
        return r2;
    L_0x005c:
        r0 = move-exception;
        r2 = r10;
    L_0x005e:
        r1 = "BookQuestionHandle";
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x008e }
        r4.<init>();	 Catch:{ all -> 0x008e }
        r5 = " getAuthorIdByBid with exception : ";
        r4 = r4.append(r5);	 Catch:{ all -> 0x008e }
        r0 = r0.getMessage();	 Catch:{ all -> 0x008e }
        r0 = r4.append(r0);	 Catch:{ all -> 0x008e }
        r0 = r0.toString();	 Catch:{ all -> 0x008e }
        com.qq.reader.common.monitor.debug.c.e(r1, r0);	 Catch:{ all -> 0x008e }
        if (r8 == 0) goto L_0x0081;
    L_0x007e:
        r8.close();	 Catch:{ all -> 0x008b }
    L_0x0081:
        r0 = b;	 Catch:{ all -> 0x008b }
        if (r0 == 0) goto L_0x005a;
    L_0x0085:
        r0 = b;	 Catch:{ all -> 0x008b }
        r0.c();	 Catch:{ all -> 0x008b }
        goto L_0x005a;
    L_0x008b:
        r0 = move-exception;
        monitor-exit(r12);
        throw r0;
    L_0x008e:
        r0 = move-exception;
    L_0x008f:
        if (r8 == 0) goto L_0x0094;
    L_0x0091:
        r8.close();	 Catch:{ all -> 0x008b }
    L_0x0094:
        r1 = b;	 Catch:{ all -> 0x008b }
        if (r1 == 0) goto L_0x009d;
    L_0x0098:
        r1 = b;	 Catch:{ all -> 0x008b }
        r1.c();	 Catch:{ all -> 0x008b }
    L_0x009d:
        throw r0;	 Catch:{ all -> 0x008b }
    L_0x009e:
        r1 = move-exception;
        r8 = r0;
        r0 = r1;
        goto L_0x008f;
    L_0x00a2:
        r1 = move-exception;
        r8 = r0;
        r2 = r10;
        r0 = r1;
        goto L_0x005e;
    L_0x00a7:
        r1 = move-exception;
        r8 = r0;
        r0 = r1;
        goto L_0x005e;
    L_0x00ab:
        r10 = r2;
        goto L_0x0041;
    L_0x00ad:
        r2 = r10;
        goto L_0x004c;
    L_0x00af:
        r0 = r8;
        r2 = r10;
        goto L_0x004c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.db.handle.g.a(java.lang.String):long");
    }

    public int b() {
        if (this.d == null || this.d.size() <= 0) {
            return 0;
        }
        if (this.j <= 0 || this.d.size() < this.j) {
            return this.d.size();
        }
        return this.h;
    }

    public AudioData a(int i) {
        if (!this.q) {
            return null;
        }
        if (this.r < 0 && i > 0) {
            this.r = i;
        }
        if (!this.l.containsKey(String.valueOf(this.r)) && !a(false)) {
            return null;
        }
        AudioData audioData = (AudioData) this.l.get(String.valueOf(this.r));
        if (audioData == null) {
            return null;
        }
        String g = audioData.a().g();
        com.qq.reader.common.monitor.debug.c.a("BookQuestionHandle", "show question " + g + " in chapter " + this.r + " remain is " + this.j);
        com.qq.reader.common.readertask.g.a().a(new BookQuestionHandle$1(this, g));
        return audioData;
    }

    public com.qq.reader.module.question.data.a c() {
        if (!this.q || !this.p) {
            return null;
        }
        com.qq.reader.common.readertask.g.a().a(new BookQuestionHandle$2(this));
        return this.g;
    }

    public synchronized boolean a(boolean z) {
        boolean z2 = false;
        synchronized (this) {
            this.p = false;
            if (this.q) {
                if (this.l.containsKey(String.valueOf(this.r))) {
                    this.p = true;
                    if (this.l.get(String.valueOf(this.r)) != null) {
                        z2 = true;
                    }
                } else if (this.s > this.r) {
                    this.s = this.r;
                    com.qq.reader.common.monitor.debug.c.a("BookQuestionHandle", "floor chapter is " + this.s);
                } else if (this.r % this.i == m) {
                    if (z) {
                        com.qq.reader.common.monitor.debug.c.a("BookQuestionHandle", "delay at chapter " + this.r);
                        m++;
                        if (m % this.i == 0) {
                            m = 0;
                        }
                    } else if (this.e != null && this.e.size() > 0) {
                        this.l.put(String.valueOf(this.r), this.e.get(0));
                        this.e.remove(0);
                        com.qq.reader.common.monitor.debug.c.a("BookQuestionHandle", "limit list size is " + this.e.size());
                        this.p = true;
                        z2 = true;
                    } else if (this.d == null || this.d.size() <= 0) {
                        if (this.f == null || this.f.size() <= 0) {
                            if (this.g != null && this.g.j() <= 0 && this.j > 0) {
                                this.l.put(String.valueOf(this.r), null);
                                this.p = true;
                                this.j--;
                                com.qq.reader.common.monitor.debug.c.a("BookQuestionHandle", "only author data remain =" + this.j);
                            }
                        } else if (this.j > 0) {
                            this.l.put(String.valueOf(this.r), this.f.get(0));
                            this.f.remove(0);
                            com.qq.reader.common.monitor.debug.c.a("BookQuestionHandle", "showed list size is " + this.f.size());
                            this.j--;
                            this.p = true;
                            z2 = true;
                        }
                    } else if (this.j > 0) {
                        this.l.put(String.valueOf(this.r), this.d.get(0));
                        this.d.remove(0);
                        com.qq.reader.common.monitor.debug.c.a("BookQuestionHandle", "normal list size is " + this.d.size());
                        this.j--;
                        this.p = true;
                        z2 = true;
                    }
                }
            }
        }
        return z2;
    }

    public synchronized int a(int i, boolean z) {
        int i2;
        if (this.r != i) {
            this.r = i;
            if (this.s < 0) {
                this.s = this.r;
            }
        }
        i2 = -1;
        if (a(z)) {
            i2 = 1;
        } else if (this.p) {
            i2 = 2;
        }
        return i2;
    }

    private boolean a(AnswerData answerData) {
        return answerData.e() == 1 && !b.d(answerData.m());
    }

    public boolean d() {
        return this.r > 0 && a(this.r, false) > 0;
    }

    public int e() {
        return this.r;
    }
}
