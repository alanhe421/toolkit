package com.tencent.upload.b.a;

import FileCloud.stEnvironment;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.open.SocialConstants;
import com.tencent.upload.b.a.a.d;
import com.tencent.upload.b.a.a.e;
import com.tencent.upload.b.a.a.h;
import com.tencent.upload.common.Global;
import com.tencent.upload.impl.TaskManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;
import java.util.zip.GZIPOutputStream;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

final class c {
    private static int a = 100;
    private Random b;
    private Handler c;
    private boolean d;
    private HandlerThread e;
    private final Object f;
    private volatile boolean g;
    private HashMap<String, ArrayList<com.tencent.upload.b.a>> h;

    class a extends ArrayList<com.tencent.upload.b.a> {
        private String a = "";
        private /* synthetic */ c b;

        a(c cVar, String str) {
            this.b = cVar;
            this.a = str;
        }

        public final /* synthetic */ boolean add(Object obj) {
            com.tencent.upload.b.a aVar = (com.tencent.upload.b.a) obj;
            boolean z = "down".equals(this.a) ? aVar.h != 0 : false;
            if (z) {
                com.tencent.upload.common.a.a.b("UploadReportThread", this.a + " a file fail. need report");
                return super.add(aVar);
            }
            int b = c.a < 0 ? 5 : c.a > 100 ? 100 : c.a;
            if (b <= 0) {
                return false;
            }
            return this.b.b.nextInt(Math.round((float) (100 / b))) == 0 ? super.add(aVar) : false;
        }
    }

    class b implements Runnable {
        private int a;
        private String b;
        private String c;
        private ArrayList<com.tencent.upload.b.a> d;
        private int e = 0;
        private int f = 3;
        private /* synthetic */ c g;

        public b(c cVar, String str, ArrayList<com.tencent.upload.b.a> arrayList, int i, String str2) {
            this.g = cVar;
            this.c = str;
            this.d = arrayList;
            this.a = i;
            this.b = str2;
        }

        private static String a(ArrayList<com.tencent.upload.b.a> arrayList) {
            if (arrayList == null) {
                return null;
            }
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT, arrayList.size());
                JSONArray jSONArray = new JSONArray();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    jSONArray.put(((com.tencent.upload.b.a) it.next()).b());
                }
                jSONObject.put("data", jSONArray);
                return jSONObject.toString();
            } catch (Throwable e) {
                com.tencent.upload.log.b.c("UploadReportThread", "JSONException when uploadReport.", e);
                return null;
            } catch (Throwable th) {
                return null;
            }
        }

        public final void run() {
            Throwable e;
            boolean z = true;
            String a = a(this.d);
            if (!TextUtils.isEmpty(this.c) && !TextUtils.isEmpty(a)) {
                this.g.d = true;
                com.tencent.upload.common.a.a.b("UploadReportThread", "start report thread.");
                try {
                    com.tencent.upload.common.a.a.b("UploadReportThread", "url:" + this.c);
                    com.tencent.upload.common.a.a.b("UploadReportThread", "body:" + a);
                    HttpResponse a2 = a.a(null, this.c, new StringEntity(a));
                    if (a2.getStatusLine().getStatusCode() == 200) {
                        try {
                            com.tencent.upload.common.a.a.b("UploadReportThread", "report success.");
                        } catch (Exception e2) {
                            e = e2;
                            this.e++;
                            com.tencent.upload.log.b.c("UploadReportThread", "exception when report", e);
                            if (!z) {
                            }
                            this.g.d = false;
                            if (!this.g.a(this.a, this.b)) {
                                this.g.b(this.a, this.b);
                                com.tencent.upload.common.a.a.c("UploadReportThread", "uploadReportImmediately by ReportRunnable");
                                return;
                            }
                            return;
                        }
                    }
                    this.e++;
                    com.tencent.upload.common.a.a.d("UploadReportThread", "HttpStatus error when report : " + a2.getStatusLine().getStatusCode());
                    z = false;
                } catch (Exception e3) {
                    e = e3;
                    z = false;
                    this.e++;
                    com.tencent.upload.log.b.c("UploadReportThread", "exception when report", e);
                    if (z) {
                    }
                    this.g.d = false;
                    if (!this.g.a(this.a, this.b)) {
                        this.g.b(this.a, this.b);
                        com.tencent.upload.common.a.a.c("UploadReportThread", "uploadReportImmediately by ReportRunnable");
                        return;
                    }
                    return;
                }
                if (z || this.e >= this.f) {
                    this.g.d = false;
                    if (!this.g.a(this.a, this.b)) {
                        this.g.b(this.a, this.b);
                        com.tencent.upload.common.a.a.c("UploadReportThread", "uploadReportImmediately by ReportRunnable");
                        return;
                    }
                    return;
                }
                this.g.a((Runnable) this, (long) TaskManager.IDLE_PROTECT_TIME);
            }
        }
    }

    class c implements Runnable {
        private Date a;
        private String b;
        private String c;
        private String d;
        private int e = 0;
        private int f = 1;
        private boolean g = true;
        private /* synthetic */ c h;

        public c(c cVar, String str, Date date) {
            this.h = cVar;
            this.a = date;
            this.b = str;
            this.d = "http://web.imagetest.myqcloud.com/oss/cgi_log_report";
        }

        public final void run() {
            Throwable e;
            Object obj = 1;
            if (TextUtils.isEmpty(this.c)) {
                this.c = com.tencent.upload.log.b.a(this.a);
                if (TextUtils.isEmpty(this.c)) {
                    return;
                }
            }
            File a = this.g ? c.a(new File(this.c)) : new File(this.c);
            if (a != null && a.exists()) {
                com.tencent.upload.common.a.a.b("UploadReportThread", "start report thread. logfile:" + this.c + " url:" + this.d);
                try {
                    String name = a.getName();
                    name = name.substring(0, name.indexOf("."));
                    stEnvironment env = Global.getEnv();
                    e[] eVarArr = new e[12];
                    eVarArr[0] = new h("qua", env.qua);
                    eVarArr[1] = new h("type", "upload");
                    eVarArr[2] = new h("refer", env.refer);
                    eVarArr[3] = new h("begin_date", name);
                    eVarArr[4] = new h("end_date", name);
                    eVarArr[5] = new h("device", env.device);
                    eVarArr[6] = new h("operators", env.operators);
                    eVarArr[7] = new h("appid", this.b);
                    eVarArr[8] = new h("net", Integer.toString(env.net));
                    eVarArr[9] = new h(SocialConstants.PARAM_SOURCE, Integer.toString(env.source));
                    eVarArr[10] = new h("gzip", Integer.toString(this.g ? 1 : 0));
                    eVarArr[11] = new com.tencent.upload.b.a.a.a("data", a.getName(), a);
                    HttpResponse a2 = a.a(null, this.d, new d(eVarArr));
                    if (a2.getStatusLine().getStatusCode() == 200) {
                        try {
                            com.tencent.upload.common.a.a.b("UploadReportThread", "report success.");
                        } catch (Exception e2) {
                            e = e2;
                            this.e++;
                            com.tencent.upload.log.b.c("UploadReportThread", "exception when report", e);
                            if (obj != null) {
                            }
                            if (obj == null) {
                            }
                            a.delete();
                        }
                    }
                    this.e++;
                    com.tencent.upload.common.a.a.d("UploadReportThread", "HttpStatus error when report : " + a2.getStatusLine().getStatusCode());
                    obj = null;
                } catch (Exception e3) {
                    e = e3;
                    obj = null;
                    this.e++;
                    com.tencent.upload.log.b.c("UploadReportThread", "exception when report", e);
                    if (obj != null) {
                    }
                    if (obj == null) {
                    }
                    a.delete();
                }
                if (obj != null && this.e < this.f) {
                    this.h.a((Runnable) this, (long) BuglyBroadcastRecevier.UPLOADLIMITED);
                } else if (obj == null || this.e >= this.f) {
                    a.delete();
                }
            }
        }
    }

    public c(String str) {
        this.b = null;
        this.c = null;
        this.d = false;
        this.e = null;
        this.g = false;
        this.h = null;
        this.b = new Random();
        this.f = new Object();
        HandlerThread handlerThread = new HandlerThread(str, 10);
        this.e = handlerThread;
        handlerThread.start();
        this.c = new Handler(this.e.getLooper());
    }

    public static File a(File file) {
        GZIPOutputStream gZIPOutputStream;
        FileInputStream fileInputStream;
        Exception exception;
        if (file == null || !file.exists()) {
            return null;
        }
        File file2 = new File(file.getAbsolutePath() + ".gz");
        try {
            FileInputStream fileInputStream2;
            GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(new FileOutputStream(file2));
            try {
                fileInputStream2 = new FileInputStream(file);
            } catch (Exception e) {
                Exception exception2 = e;
                gZIPOutputStream = gZIPOutputStream2;
                fileInputStream = null;
                exception = exception2;
                exception.printStackTrace();
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        return file2;
                    }
                }
                if (gZIPOutputStream != null) {
                    return file2;
                }
                gZIPOutputStream.finish();
                gZIPOutputStream.close();
                return file2;
            }
            try {
                byte[] bArr = new byte[1024];
                while (true) {
                    int read = fileInputStream2.read(bArr);
                    if (read > 0) {
                        gZIPOutputStream2.write(bArr, 0, read);
                    } else {
                        fileInputStream2.close();
                        gZIPOutputStream2.finish();
                        gZIPOutputStream2.close();
                        return file2;
                    }
                }
            } catch (Exception e3) {
                exception = e3;
                GZIPOutputStream gZIPOutputStream3 = gZIPOutputStream2;
                fileInputStream = fileInputStream2;
                gZIPOutputStream = gZIPOutputStream3;
                exception.printStackTrace();
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                if (gZIPOutputStream != null) {
                    return file2;
                }
                gZIPOutputStream.finish();
                gZIPOutputStream.close();
                return file2;
            }
        } catch (Exception e4) {
            fileInputStream = null;
            exception = e4;
            gZIPOutputStream = null;
            exception.printStackTrace();
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (gZIPOutputStream != null) {
                return file2;
            }
            gZIPOutputStream.finish();
            gZIPOutputStream.close();
            return file2;
        }
    }

    private ArrayList<com.tencent.upload.b.a> a(String str) {
        return SocialConstants.PARAM_SEND_MSG.equals(str) ? new a(this, SocialConstants.PARAM_SEND_MSG) : "upload".equals(str) ? new a(this, "upload") : "down".equals(str) ? new a(this, "down") : new ArrayList();
    }

    public static void a(int i) {
        a = i;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean a(int r6, java.lang.String r7) {
        /*
        r5 = this;
        r2 = 1;
        r1 = 0;
        if (r7 != 0) goto L_0x0006;
    L_0x0004:
        r0 = r1;
    L_0x0005:
        return r0;
    L_0x0006:
        r0 = r5.d;
        if (r0 != r2) goto L_0x000c;
    L_0x000a:
        r0 = r1;
        goto L_0x0005;
    L_0x000c:
        r3 = r5.h;
        monitor-enter(r3);
        r0 = r5.h;	 Catch:{ all -> 0x0036 }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0036 }
        r4.<init>();	 Catch:{ all -> 0x0036 }
        r4 = r4.append(r6);	 Catch:{ all -> 0x0036 }
        r4 = r4.append(r7);	 Catch:{ all -> 0x0036 }
        r4 = r4.toString();	 Catch:{ all -> 0x0036 }
        r0 = r0.get(r4);	 Catch:{ all -> 0x0036 }
        r0 = (java.util.ArrayList) r0;	 Catch:{ all -> 0x0036 }
        if (r0 == 0) goto L_0x0030;
    L_0x002a:
        r0 = r0.isEmpty();	 Catch:{ all -> 0x0036 }
        if (r0 == 0) goto L_0x0033;
    L_0x0030:
        monitor-exit(r3);	 Catch:{ all -> 0x0036 }
        r0 = r1;
        goto L_0x0005;
    L_0x0033:
        monitor-exit(r3);
        r0 = r2;
        goto L_0x0005;
    L_0x0036:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.upload.b.a.c.a(int, java.lang.String):boolean");
    }

    private boolean a(Runnable runnable) {
        return this.c.post(runnable);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean b(int r7, java.lang.String r8) {
        /*
        r6 = this;
        r0 = 1;
        r1 = r6.h;
        monitor-enter(r1);
        r2 = r6.h;	 Catch:{ all -> 0x0054 }
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0054 }
        r3.<init>();	 Catch:{ all -> 0x0054 }
        r3 = r3.append(r7);	 Catch:{ all -> 0x0054 }
        r3 = r3.append(r8);	 Catch:{ all -> 0x0054 }
        r3 = r3.toString();	 Catch:{ all -> 0x0054 }
        r3 = r2.get(r3);	 Catch:{ all -> 0x0054 }
        r3 = (java.util.ArrayList) r3;	 Catch:{ all -> 0x0054 }
        if (r3 == 0) goto L_0x0025;
    L_0x001f:
        r2 = r3.isEmpty();	 Catch:{ all -> 0x0054 }
        if (r2 == 0) goto L_0x0027;
    L_0x0025:
        monitor-exit(r1);	 Catch:{ all -> 0x0054 }
    L_0x0026:
        return r0;
    L_0x0027:
        r2 = r6.a(r8);	 Catch:{ all -> 0x0054 }
        r4 = r6.h;	 Catch:{ all -> 0x0054 }
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0054 }
        r5.<init>();	 Catch:{ all -> 0x0054 }
        r5 = r5.append(r7);	 Catch:{ all -> 0x0054 }
        r5 = r5.append(r8);	 Catch:{ all -> 0x0054 }
        r5 = r5.toString();	 Catch:{ all -> 0x0054 }
        r4.put(r5, r2);	 Catch:{ all -> 0x0054 }
        monitor-exit(r1);	 Catch:{ all -> 0x0054 }
        if (r0 != r7) goto L_0x0057;
    L_0x0044:
        r2 = "http://p.store.qq.com/qcloudfile?op=all";
    L_0x0047:
        r0 = new com.tencent.upload.b.a.c$b;
        r1 = r6;
        r4 = r7;
        r5 = r8;
        r0.<init>(r1, r2, r3, r4, r5);
        r0 = r6.a(r0);
        goto L_0x0026;
    L_0x0054:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x0057:
        r0 = 4;
        if (r0 != r7) goto L_0x005e;
    L_0x005a:
        r2 = "http://p.store.qq.com/qcloudvideo?op=all";
        goto L_0x0047;
    L_0x005e:
        r2 = "http://p.store.qq.com/qcloudimage?op=all";
        goto L_0x0047;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.upload.b.a.c.b(int, java.lang.String):boolean");
    }

    private boolean b(com.tencent.upload.b.a aVar) {
        String str = aVar.d;
        int a = aVar.a();
        synchronized (this.h) {
            ArrayList arrayList = (ArrayList) this.h.get(a + str);
            if (arrayList == null) {
                arrayList = a(str);
                this.h.put(a + str, arrayList);
            }
            arrayList.add(aVar);
        }
        return a(a, str) ? b(a, str) : true;
    }

    public final void a(com.tencent.upload.b.a aVar) {
        b(aVar);
    }

    public final boolean a() {
        boolean z = true;
        if (!this.g) {
            synchronized (this.f) {
                if (this.g) {
                } else {
                    this.g = true;
                    this.h = new HashMap();
                    if (this.h == null) {
                        com.tencent.upload.common.a.a.a("UploadReportThread", " BusinessReport init failed");
                        z = false;
                    } else {
                        com.tencent.upload.common.a.a();
                        com.tencent.upload.common.a.a("UploadReportThread", this.c, new d(this));
                        com.tencent.upload.common.a.a.a("UploadReportThread", " BusinessReport init success");
                    }
                }
            }
        }
        return z;
    }

    public final boolean a(Runnable runnable, long j) {
        return this.c.postDelayed(runnable, j);
    }

    public final boolean a(String str, Date date) {
        return a(new c(this, str, date));
    }
}
