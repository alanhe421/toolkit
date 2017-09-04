package com.qq.reader.cservice.adv;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.db.c;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderDownloadTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.FeedDataTask;
import com.qq.reader.common.readertask.protocol.GetAdvTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ab;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: AdvertisementHandle */
public class b {
    public static boolean a = false;
    public static boolean b = false;
    public static boolean[] c = new boolean[4];
    public static int d = 4;
    private static c e;
    private static b f;
    private static List<a> j = null;
    private Context g;
    private boolean h = true;
    private Map<String, List<a>> i = null;

    /* compiled from: AdvertisementHandle */
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

    public synchronized void a(java.util.List<com.qq.reader.cservice.adv.a> r9) {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxRuntimeException: Can't find immediate dominator for block B:36:0x013e in {7, 17, 26, 30, 35, 38, 40, 42, 43, 44, 45, 46} preds:[]
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.computeDominators(BlockProcessor.java:129)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.processBlocksTree(BlockProcessor.java:48)
	at jadx.core.dex.visitors.blocksmaker.BlockProcessor.rerun(BlockProcessor.java:44)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:57)
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
        r8 = this;
        monitor-enter(r8);
        if (r9 == 0) goto L_0x0009;
    L_0x0003:
        r0 = r9.size();	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        if (r0 != 0) goto L_0x000b;
    L_0x0009:
        monitor-exit(r8);
        return;
    L_0x000b:
        r0 = e;	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        r1 = r0.a();	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        if (r1 == 0) goto L_0x00fe;	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
    L_0x0013:
        r1.beginTransaction();	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        r0 = "adv";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2 = 0;	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r3 = 0;	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r1.delete(r0, r2, r3);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2 = new android.content.ContentValues;	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2.<init>();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r3 = r9.iterator();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
    L_0x0027:
        r0 = r3.hasNext();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        if (r0 == 0) goto L_0x0111;	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
    L_0x002d:
        r0 = r3.next();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r0 = (com.qq.reader.cservice.adv.a) r0;	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = "dbid";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r6 = r0.d();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r5 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = "dbtype";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r5 = r0.f();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = "dbcategory";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r5 = r0.b();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = "dbtitle";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r5 = r0.e();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = "dbdescription";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r5 = r0.i();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = "dbcount";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r5 = r0.x();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = "dblinkurl";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r5 = r0.h();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = "dbjason";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r5 = r0.c();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = "dbimageurl";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r5 = r0.g();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = "dboutofdate";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r6 = r0.j();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r5 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = "dbstate";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r5 = r0.l();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = "dbstartofdate";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r6 = r0.k();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r5 = java.lang.Long.valueOf(r6);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = "dbextinfo";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r5 = r0.m();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2.put(r4, r5);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = "dbvaluetype";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r0 = r0.y();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r0 = java.lang.Integer.valueOf(r0);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r2.put(r4, r0);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r0 = "adv";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = 0;	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r1.insert(r0, r4, r2);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        goto L_0x0027;
    L_0x00dc:
        r0 = move-exception;
        r2 = "AdvertisementHandle";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r3 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r3.<init>();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r4 = "addRecommendBooks with exception : ";	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r3 = r3.append(r4);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r0 = r3.append(r0);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        com.qq.reader.common.monitor.f.a(r2, r0);	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r1.endTransaction();
    L_0x00fe:
        r0 = e;
        r0.c();
    L_0x0103:
        r0 = j;	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        if (r0 == 0) goto L_0x0009;	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
    L_0x0107:
        r0 = j;	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        r0.clear();	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        goto L_0x0009;
    L_0x010e:
        r0 = move-exception;
        monitor-exit(r8);
        throw r0;
    L_0x0111:
        r1.setTransactionSuccessful();	 Catch:{ Exception -> 0x00dc, all -> 0x013d }
        r1.endTransaction();
        goto L_0x00fe;
    L_0x0118:
        r0 = move-exception;
        r1 = "AdvertisementHandle";	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        r2.<init>();	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        r3 = "addRecommendBooks getWritableDatabase with exception : ";	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        r2 = r2.append(r3);	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        r0 = r0.getMessage();	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        r0 = r2.append(r0);	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        com.qq.reader.common.monitor.f.a(r1, r0);	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        r0 = e;
        r0.c();
        goto L_0x0103;
    L_0x013d:
        r0 = move-exception;
        r1.endTransaction();	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        throw r0;	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
    L_0x0142:
        r0 = move-exception;
        r1 = e;	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        r1.c();	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        throw r0;	 Catch:{ Exception -> 0x0118, all -> 0x0142 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.cservice.adv.b.a(java.util.List):void");
    }

    public static b a(Context context) {
        if (f == null) {
            f = new b(context);
        }
        return f;
    }

    private b(Context context) {
        e = new a(this, com.qq.reader.common.c.a.aw, null, d);
        this.g = context;
    }

    public void a() {
        ReaderTask getAdvTask = new GetAdvTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                com.qq.reader.common.monitor.debug.c.a("adv", "onConnectionRecieveData  " + str);
                this.a.a(readerProtocolTask, str);
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                i.a("event_A256", null, ReaderApplication.getApplicationImp());
            }
        }, this);
        getAdvTask.setPriority(1);
        g.a().a(getAdvTask);
        i.a("event_A254", null, ReaderApplication.getApplicationImp());
    }

    private void a(ReaderProtocolTask readerProtocolTask, String str) {
        InputStream inputStream = null;
        Object obj = null;
        JSONObject jSONObject = new JSONObject(str);
        if (jSONObject.getInt("code") != 0) {
            i.a("event_A256", null, ReaderApplication.getApplicationImp());
            if (inputStream != null) {
                try {
                    inputStream.close();
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }
            }
            return;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("adinfo");
        int length = optJSONArray.length();
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, String.valueOf(length));
        i.a("event_A255", hashMap, ReaderApplication.getApplicationImp());
        List arrayList = new ArrayList();
        if (length > 0) {
            String str2 = "";
            str2 = "";
            str2 = "";
            str2 = "";
            str2 = "";
            str2 = "";
            str2 = "";
            str2 = "";
            str2 = "";
            str2 = "";
            long j = 0;
            for (int i = 0; i < length; i++) {
                jSONObject = (JSONObject) optJSONArray.get(i);
                if (!jSONObject.getBoolean("needupdate")) {
                    break;
                }
                String string = jSONObject.getString(ComicStoreExclusiveItemCard.NET_AD_ATTR_VERSION);
                JSONArray jSONArray = jSONObject.getJSONArray("ads");
                if (jSONArray == null) {
                    break;
                }
                int i2 = 0;
                while (i2 < jSONArray.length()) {
                    jSONObject = (JSONObject) jSONArray.get(i2);
                    long longValue = Long.valueOf(jSONObject.getString("id")).longValue();
                    String string2 = jSONObject.getString("type");
                    int optInt = jSONObject.optInt("valuetype", 5);
                    String string3 = jSONObject.getString(ComicStoreExclusiveItemCard.NET_AD_ATTR_CATE);
                    String string4 = jSONObject.getString("title");
                    String string5 = jSONObject.getString(ComicStoreExclusiveItemCard.NET_AD_ATTR_DES);
                    String string6 = jSONObject.getString(ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT);
                    String string7 = jSONObject.getString("link_url");
                    String string8 = jSONObject.getString("read_online");
                    String string9 = jSONObject.getString("image_url");
                    long longValue2 = Long.valueOf(jSONObject.getString("expire_date")).longValue();
                    try {
                        j = Long.valueOf(jSONObject.getString("start_date")).longValue();
                    } catch (Exception e2) {
                    }
                    try {
                        Object obj2;
                        a aVar = new a(longValue, string2);
                        aVar.b(optInt);
                        aVar.a(string3);
                        aVar.d(string4);
                        aVar.g(string5);
                        aVar.b(string6);
                        aVar.f(a(this.g.getApplicationContext(), string7));
                        aVar.c(string8);
                        aVar.e(string9);
                        aVar.a(longValue2);
                        aVar.b(j);
                        aVar.i(string);
                        jSONObject = jSONObject.optJSONObject(ComicStoreExclusiveItemCard.NET_AD_ATTR_EXTINFO);
                        if (jSONObject != null) {
                            aVar.h(jSONObject.toString());
                        }
                        arrayList.add(aVar);
                        f(aVar);
                        if (aVar.q() == 2) {
                            c(aVar);
                        }
                        if (string2.equalsIgnoreCase("102668")) {
                            obj2 = 1;
                            d.O(this.g, string7);
                        } else {
                            obj2 = obj;
                        }
                        i2++;
                        obj = obj2;
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                                return;
                            } catch (IOException e4) {
                                e4.printStackTrace();
                                return;
                            }
                        }
                        return;
                    } catch (Throwable th) {
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                    }
                }
            }
            a((ArrayList) arrayList);
            b();
            a(arrayList);
            d.h(this.g.getApplicationContext(), false);
            g();
            Intent intent = new Intent();
            intent.setAction(com.qq.reader.common.c.a.cs);
            this.g.sendBroadcast(intent);
            if (obj != null) {
                intent.setAction(com.qq.reader.common.c.a.di);
                this.g.sendBroadcast(intent);
            }
        } else if (length <= 0) {
            b();
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e42) {
                e42.printStackTrace();
            }
        }
    }

    public static String a(a aVar) {
        return com.qq.reader.common.imageloader.a.a.a.c + aVar.f() + "/" + aVar.d() + "/";
    }

    public static boolean b(a aVar) {
        return new File(a(aVar) + "0").exists();
    }

    public static void c(final a aVar) {
        String str = com.qq.reader.common.imageloader.a.a.a.c + aVar.f() + "/" + aVar.d();
        final File file = new File(str + ".zip");
        final String a = a(aVar);
        final File file2 = new File(str + "/0");
        if (!b(aVar)) {
            ReaderTask readerDownloadTask = new ReaderDownloadTask(ReaderApplication.getApplicationImp(), file.getPath(), aVar.h());
            readerDownloadTask.setStrongRefListener(new com.qq.reader.common.readertask.ordinal.b() {
                public void a(boolean z) {
                    boolean c;
                    if (file.exists()) {
                        try {
                            ao.f(file.getPath(), a);
                            c = ab.c(file2);
                            ab.a(file, true);
                            Map hashMap = new HashMap();
                            hashMap.put(s.ORIGIN, String.valueOf(aVar.d()));
                            i.a("event_A160", hashMap, ReaderApplication.getApplicationImp());
                            StatisticsManager.a().a("event_A160", hashMap);
                        } catch (Exception e) {
                            c = false;
                        }
                    } else {
                        c = false;
                    }
                    if (!c) {
                        Map hashMap2 = new HashMap();
                        hashMap2.put(s.ORIGIN, String.valueOf(aVar.d()));
                        i.a("event_A157", hashMap2, ReaderApplication.getApplicationImp());
                        StatisticsManager.a().a("event_A157", hashMap2);
                    }
                }
            });
            g.a().a(readerDownloadTask);
        }
    }

    public void a(boolean z) {
        this.h = z;
    }

    public synchronized void a(ArrayList<a> arrayList) {
        ArrayList h = h();
        byte[] bArr = new byte[h.size()];
        if (!(arrayList == null || h == null)) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                int l;
                Object obj;
                Map hashMap;
                Object obj2 = (a) it.next();
                int i = 0;
                while (i < h.size()) {
                    a aVar = (a) h.get(i);
                    if (aVar.d() == obj2.d()) {
                        com.qq.reader.common.monitor.debug.c.a("AdvertisementHandle", "isNewAdv = false");
                        l = aVar.l();
                        obj2.a(l);
                        if (l == 1) {
                            bArr[i] = (byte) 1;
                            obj = null;
                        } else {
                            obj = null;
                        }
                        if (obj == null) {
                            if ("100126".equals(obj2.f())) {
                                hashMap = new HashMap();
                                hashMap.put(s.ORIGIN, String.valueOf(obj2.d()));
                                i.a("event_A159", hashMap, ReaderApplication.getApplicationImp());
                                StatisticsManager.a().a("event_A159", hashMap);
                            }
                            c.a(obj2, true);
                        }
                    } else {
                        i++;
                    }
                }
                l = 1;
                if (obj == null) {
                    if ("100126".equals(obj2.f())) {
                        hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, String.valueOf(obj2.d()));
                        i.a("event_A159", hashMap, ReaderApplication.getApplicationImp());
                        StatisticsManager.a().a("event_A159", hashMap);
                    }
                    c.a(obj2, true);
                }
            }
        }
    }

    private synchronized void b(a aVar, boolean z) {
        String f = aVar.f();
        if (this.i != null) {
            List list = (List) this.i.get(f);
            if (list != null) {
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    a aVar2 = (a) it.next();
                    if (aVar2.d() == aVar.d()) {
                        if (z) {
                            it.remove();
                        } else {
                            aVar2.a(0);
                        }
                    }
                }
            }
        }
    }

    public synchronized boolean d(a aVar) {
        return a(aVar, true);
    }

    public synchronized boolean a(a aVar, boolean z) {
        boolean z2;
        if (aVar != null) {
            if (aVar.d() != -1) {
                b(aVar, z);
                g.a().a(new AdvertisementHandle$3(this, aVar));
                z2 = true;
            }
        }
        z2 = false;
        return z2;
    }

    private synchronized boolean e(a aVar) {
        boolean z = false;
        synchronized (this) {
            try {
                SQLiteDatabase a = e.a();
                ContentValues contentValues = new ContentValues();
                contentValues.put("dbstate", Integer.valueOf(aVar.l()));
                if (a.update("adv", contentValues, "dbid= '" + aVar.d() + "'", null) > 0) {
                    z = true;
                }
                if (e != null) {
                    e.c();
                }
            } catch (Exception e) {
                f.a("AdvHandle", "error in updateAdv : " + e.toString());
                if (e != null) {
                    e.c();
                }
            } catch (Throwable th) {
                if (e != null) {
                    e.c();
                }
            }
        }
        return z;
    }

    private void g() {
        if (this.i != null) {
            this.i.clear();
        }
    }

    public String a(String str) {
        return str + "_" + d(str) + VoiceWakeuperAidl.PARAMS_SEPARATE;
    }

    private String d(String str) {
        List b = b(str);
        if (b == null || b.size() <= 0) {
            return "0";
        }
        return ((a) b.get(0)).x();
    }

    public synchronized List<a> b(String str) {
        return a(str, true);
    }

    public synchronized List<a> c(String str) {
        return a(str, false);
    }

    private synchronized List<a> a(String str, boolean z) {
        Cursor cursor;
        Exception e;
        List<a> list;
        Iterator it;
        long currentTimeMillis;
        a aVar;
        if (this.i == null) {
            this.i = Collections.synchronizedMap(new HashMap());
        }
        List<a> list2 = (List) this.i.get(str);
        if (list2 == null) {
            cursor = null;
            ArrayList arrayList = new ArrayList();
            Cursor query;
            try {
                SQLiteDatabase a = e.a();
                String[] strArr = new String[]{"dbid", "dbtype", "dbcategory", "dbtitle", "dbdescription", "dbcount", "dblinkurl", "dbjason", "dbimageurl", "dboutofdate", "dbstate", "dbstartofdate", "dbextinfo", "dbvaluetype"};
                if (z) {
                    query = a.query("adv", strArr, "dbtype = '" + str + "' and " + "dbstate" + " = 1", null, null, null, null);
                } else {
                    query = a.query("adv", strArr, "dbtype = '" + str + "'", null, null, null, null);
                }
                try {
                    if (query.moveToFirst()) {
                        do {
                            long j = query.getLong(0);
                            String string = query.getString(1);
                            String string2 = query.getString(2);
                            String string3 = query.getString(3);
                            String string4 = query.getString(4);
                            String string5 = query.getString(5);
                            String string6 = query.getString(6);
                            String string7 = query.getString(7);
                            String string8 = query.getString(8);
                            long j2 = query.getLong(9);
                            int i = query.getInt(10);
                            query.getLong(11);
                            String string9 = query.getString(12);
                            int i2 = query.getInt(13);
                            System.currentTimeMillis();
                            a aVar2 = new a(j, string);
                            aVar2.a(string2);
                            aVar2.d(string3);
                            aVar2.g(string4);
                            aVar2.b(string5);
                            aVar2.f(string6);
                            aVar2.c(string7);
                            aVar2.e(string8);
                            aVar2.a(j2);
                            aVar2.a(i);
                            aVar2.h(string9);
                            if (string9 != null) {
                                aVar2.a(new JSONObject(string9).optInt("showFlag") == 1);
                            }
                            aVar2.b(i2);
                            arrayList.add(aVar2);
                        } while (query.moveToNext());
                    }
                    if (query != null) {
                        query.close();
                    }
                    if (e != null) {
                        e.c();
                    }
                } catch (Exception e2) {
                    e = e2;
                    try {
                        f.a("AdvertisementHandle", "getRecommendBooks with exception: " + e.toString());
                        if (query != null) {
                            query.close();
                        }
                        if (e != null) {
                            e.c();
                        }
                        this.i.put(str, arrayList);
                        list = arrayList;
                        it = list.iterator();
                        currentTimeMillis = System.currentTimeMillis();
                        while (it.hasNext()) {
                            aVar = (a) it.next();
                            it.remove();
                        }
                        return list;
                    } catch (Throwable th) {
                        Throwable th2 = th;
                        cursor = query;
                        if (cursor != null) {
                            cursor.close();
                        }
                        if (e != null) {
                            e.c();
                        }
                        throw th2;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                query = null;
                f.a("AdvertisementHandle", "getRecommendBooks with exception: " + e.toString());
                if (query != null) {
                    query.close();
                }
                if (e != null) {
                    e.c();
                }
                this.i.put(str, arrayList);
                list = arrayList;
                it = list.iterator();
                currentTimeMillis = System.currentTimeMillis();
                while (it.hasNext()) {
                    aVar = (a) it.next();
                    it.remove();
                }
                return list;
            } catch (Throwable th3) {
                th2 = th3;
                if (cursor != null) {
                    cursor.close();
                }
                if (e != null) {
                    e.c();
                }
                throw th2;
            }
            try {
                this.i.put(str, arrayList);
                list = arrayList;
            } catch (Exception e4) {
                j.c(e4.getMessage());
                Object obj = arrayList;
            }
        } else {
            list = list2;
        }
        it = list.iterator();
        currentTimeMillis = System.currentTimeMillis();
        while (it.hasNext()) {
            aVar = (a) it.next();
            if (aVar.d() == -1 || ((aVar.j() > 0 && aVar.j() < currentTimeMillis) || (aVar.k() > 0 && aVar.k() > currentTimeMillis))) {
                it.remove();
            }
        }
        return list;
    }

    private synchronized ArrayList<a> h() {
        ArrayList<a> arrayList;
        Exception e;
        Throwable th;
        arrayList = new ArrayList();
        Cursor query;
        try {
            query = e.a().query("adv", new String[]{"dbid", "dbtype", "dbcategory", "dbtitle", "dbdescription", "dbcount", "dblinkurl", "dbjason", "dbimageurl", "dboutofdate", "dbstate", "dbstartofdate", "dbextinfo", "dbvaluetype"}, null, null, null, null, null);
            try {
                if (query.moveToFirst()) {
                    do {
                        long j = query.getLong(0);
                        String string = query.getString(1);
                        String string2 = query.getString(2);
                        String string3 = query.getString(3);
                        String string4 = query.getString(4);
                        String string5 = query.getString(5);
                        String string6 = query.getString(6);
                        String string7 = query.getString(7);
                        String string8 = query.getString(8);
                        long j2 = query.getLong(9);
                        int i = query.getInt(10);
                        long j3 = query.getLong(11);
                        String string9 = query.getString(12);
                        int i2 = query.getInt(13);
                        if (j != -1) {
                            a aVar = new a(j, string);
                            aVar.a(string2);
                            aVar.d(string3);
                            aVar.g(string4);
                            aVar.b(string5);
                            aVar.f(string6);
                            aVar.c(string7);
                            aVar.e(string8);
                            aVar.a(j2);
                            aVar.a(i);
                            aVar.b(j3);
                            aVar.h(string9);
                            aVar.b(i2);
                            arrayList.add(aVar);
                        }
                    } while (query.moveToNext());
                }
                if (query != null) {
                    query.close();
                }
                if (e != null) {
                    e.c();
                }
            } catch (Exception e2) {
                e = e2;
                try {
                    f.a("AdvertisementHandle", "getRecommendBooks with exception: " + e.toString());
                    if (query != null) {
                        query.close();
                    }
                    if (e != null) {
                        e.c();
                    }
                    return arrayList;
                } catch (Throwable th2) {
                    th = th2;
                    if (query != null) {
                        query.close();
                    }
                    if (e != null) {
                        e.c();
                    }
                    throw th;
                }
            }
        } catch (Exception e3) {
            e = e3;
            query = null;
            f.a("AdvertisementHandle", "getRecommendBooks with exception: " + e.toString());
            if (query != null) {
                query.close();
            }
            if (e != null) {
                e.c();
            }
            return arrayList;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            if (e != null) {
                e.c();
            }
            throw th;
        }
        return arrayList;
    }

    public void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("create table if not exists adv (_id integer primary key autoincrement,dbid long default 0,dbtype text,dbcategory text,dbtitle text,dbdescription text,dbcount text,dblinkurl text,dbjason text,dbimageurl text,dboutofdate long default 0,dbstate int default 1,dbstartofdate long default 0,dbextinfo text,dbvaluetype int default 5)");
    }

    private void a(SQLiteDatabase sQLiteDatabase, int i) {
        switch (i) {
            case 1:
                e(sQLiteDatabase);
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                return;
        }
        d(sQLiteDatabase);
        c(sQLiteDatabase);
    }

    private void c(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select dbvaluetype from adv", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(4);
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
                        f.a("DB", " update3To4 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE adv ADD dbvaluetype int default 5");
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
            f.a("DB", " update3To4 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE adv ADD dbvaluetype int default 5");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE adv ADD dbvaluetype int default 5");
    }

    private void d(SQLiteDatabase sQLiteDatabase) {
        Exception e;
        Throwable th;
        Cursor cursor = null;
        Cursor rawQuery;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select dbextinfo from adv", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(3);
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
                        f.a("DB", " update2To3 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE adv ADD dbextinfo text");
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
            f.a("DB", " update2To3 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE adv ADD dbextinfo text");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE adv ADD dbextinfo text");
    }

    private void e(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        Exception e;
        Throwable th;
        Cursor cursor = null;
        try {
            rawQuery = sQLiteDatabase.rawQuery("select dbstartofdate from adv", null);
            if (rawQuery != null) {
                try {
                    if (rawQuery.getCount() > 0) {
                        sQLiteDatabase.setVersion(2);
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
                        f.a("DB", " update1To2 :" + e.toString());
                        if (rawQuery != null) {
                            rawQuery.close();
                        }
                        sQLiteDatabase.execSQL("ALTER TABLE adv ADD dbstartofdate long default 0");
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
            f.a("DB", " update1To2 :" + e.toString());
            if (rawQuery != null) {
                rawQuery.close();
            }
            sQLiteDatabase.execSQL("ALTER TABLE adv ADD dbstartofdate long default 0");
        } catch (Throwable th3) {
            th = th3;
            rawQuery = cursor;
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
        sQLiteDatabase.execSQL("ALTER TABLE adv ADD dbstartofdate long default 0");
    }

    private String a(Context context, String str) {
        if (str == null || str.length() <= 0) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        if (str.indexOf("?") != -1) {
            stringBuffer.append("&");
        } else {
            stringBuffer.append("?");
        }
        stringBuffer.append("timi=");
        stringBuffer.append(d.z(context));
        stringBuffer.append(FeedDataTask.MS_SEX);
        stringBuffer.append(d.aU(context));
        return stringBuffer.toString();
    }

    private void f(a aVar) {
        aVar.b(ReaderApplication.getApplicationImp());
    }

    public synchronized boolean b() {
        boolean z;
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = e.a();
            b(sQLiteDatabase);
            z = true;
            if (sQLiteDatabase != null) {
                e.c();
            }
        } catch (Exception e) {
            f.a("PlugInDB", "clearPluginDatebase exception : " + e.getMessage());
            if (sQLiteDatabase != null) {
                e.c();
            }
            z = false;
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                e.c();
            }
        }
        return z;
    }

    public void b(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL("delete from adv");
        } catch (Exception e) {
            f.a("AdvertisementHandle", "clearTable : " + e.getMessage());
        }
    }

    public void c() {
        d.j(this.g, 0);
        d.J(this.g, 0);
    }

    public void d() {
        int i = 30;
        switch (d.bl(this.g)) {
            case 0:
                i = 1;
                break;
            case 1:
                i = 3;
                break;
            case 3:
                i = 7;
                break;
            case 7:
            case 30:
                break;
            default:
                i = 0;
                break;
        }
        d.J(this.g, i);
        d.j(this.g, Calendar.getInstance().getTimeInMillis());
    }

    public boolean e() {
        int bl = d.bl(this.g);
        Date time = Calendar.getInstance().getTime();
        long bm = d.bm(this.g);
        if (bm == 0) {
            return true;
        }
        if (!new Date(bm + ((long) ((((bl * 24) * 60) * 60) * 1000))).before(time)) {
            return false;
        }
        c();
        return true;
    }

    public static void a(int i, boolean z) {
        c[i] = z;
        com.qq.reader.common.monitor.debug.c.d("ROOKIE", "set Main tab fragment resume state index = " + i + " isResume = " + z);
    }

    public static boolean f() {
        for (boolean z : c) {
            if (z) {
                return true;
            }
        }
        return false;
    }
}
