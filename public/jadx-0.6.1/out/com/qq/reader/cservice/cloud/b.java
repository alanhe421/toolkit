package com.qq.reader.cservice.cloud;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.qq.reader.common.db.handle.i;
import com.qq.reader.common.db.handle.l;
import com.qq.reader.common.db.handle.s;
import com.qq.reader.common.db.handle.v;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.cloud.a.c;
import com.qq.reader.cservice.cloud.a.f;
import com.qq.reader.cservice.cloud.a.g;
import com.qq.reader.cservice.onlineread.OnlineTag;
import com.qq.reader.framework.mark.LocalMark;
import com.qq.reader.framework.mark.Mark;
import com.qq.reader.module.comic.e.e;
import com.qq.reader.module.comic.mark.ComicBookMark;
import com.qq.reader.qplugin.local.TingBookMark;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: CloudActionManager */
public class b extends com.qq.reader.appconfig.a.b {
    public static final Comparator<g> a = new Comparator<g>() {
        public /* synthetic */ int compare(Object obj, Object obj2) {
            return a((g) obj, (g) obj2);
        }

        public int a(g gVar, g gVar2) {
            long c = gVar2.c();
            long c2 = gVar.c();
            if (c < c2) {
                return 1;
            }
            if (c > c2) {
                return -1;
            }
            return 0;
        }
    };
    private static volatile b c;
    private static Context h;
    private final long b = 5000;
    private Object d = new Object();
    private final List<g> e = new ArrayList();
    private long f = 1;
    private final Map<Long, List<g>> g = new HashMap();
    private List<g> i = new ArrayList();
    private a j = null;
    private volatile boolean k = false;

    /* compiled from: CloudActionManager */
    private class a extends Thread {
        volatile long a;
        final /* synthetic */ b b;

        private a(b bVar) {
            this.b = bVar;
            this.a = 5000;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r10 = this;
            r2 = 0;
        L_0x0002:
            r0 = r10.b;
            r0 = r0.k;
            if (r0 == 0) goto L_0x0097;
        L_0x000a:
            r0 = r10.b;	 Catch:{ Exception -> 0x0084 }
            r4 = r0.d;	 Catch:{ Exception -> 0x0084 }
            monitor-enter(r4);	 Catch:{ Exception -> 0x0084 }
            r0 = r10.b;	 Catch:{ all -> 0x0081 }
            r0 = r0.i;	 Catch:{ all -> 0x0081 }
            if (r0 == 0) goto L_0x0025;
        L_0x0019:
            r0 = r10.b;	 Catch:{ all -> 0x0081 }
            r0 = r0.i;	 Catch:{ all -> 0x0081 }
            r0 = r0.size();	 Catch:{ all -> 0x0081 }
            if (r0 != 0) goto L_0x002e;
        L_0x0025:
            r0 = r10.b;	 Catch:{ all -> 0x0081 }
            r0 = r0.d;	 Catch:{ all -> 0x0081 }
            r0.wait();	 Catch:{ all -> 0x0081 }
        L_0x002e:
            r0 = r10.b;	 Catch:{ all -> 0x0081 }
            r0 = r0.i;	 Catch:{ all -> 0x0081 }
            if (r0 == 0) goto L_0x009d;
        L_0x0036:
            r0 = r10.b;	 Catch:{ all -> 0x0081 }
            r0 = r0.i;	 Catch:{ all -> 0x0081 }
            r0 = r0.size();	 Catch:{ all -> 0x0081 }
            if (r0 <= 0) goto L_0x009d;
        L_0x0042:
            r0 = r10.b;	 Catch:{ all -> 0x0081 }
            r0 = r0.i;	 Catch:{ all -> 0x0081 }
            r1 = 0;
            r0 = r0.get(r1);	 Catch:{ all -> 0x0081 }
            r0 = (com.qq.reader.cservice.cloud.a.g) r0;	 Catch:{ all -> 0x0081 }
            r0 = r0.c();	 Catch:{ all -> 0x0081 }
            if (r0 != 0) goto L_0x0098;
        L_0x0055:
            r0 = r10.a;	 Catch:{ all -> 0x0081 }
            r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r0 <= 0) goto L_0x008a;
        L_0x005b:
            r0 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0081 }
            r5 = r10.b;	 Catch:{ all -> 0x0081 }
            r5 = r5.d;	 Catch:{ all -> 0x0081 }
            r6 = r10.a;	 Catch:{ all -> 0x0081 }
            r5.wait(r6);	 Catch:{ all -> 0x0081 }
            r6 = r10.a;	 Catch:{ all -> 0x0081 }
            r8 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x0081 }
            r0 = r8 - r0;
            r0 = r6 - r0;
            r10.a = r0;	 Catch:{ all -> 0x0081 }
            r0 = r10.a;	 Catch:{ all -> 0x0081 }
            r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
            if (r0 >= 0) goto L_0x0087;
        L_0x007c:
            r0 = r2;
        L_0x007d:
            r10.a = r0;	 Catch:{ all -> 0x0081 }
            monitor-exit(r4);	 Catch:{ all -> 0x0081 }
            goto L_0x0002;
        L_0x0081:
            r0 = move-exception;
            monitor-exit(r4);	 Catch:{ all -> 0x0081 }
            throw r0;	 Catch:{ Exception -> 0x0084 }
        L_0x0084:
            r0 = move-exception;
            goto L_0x0002;
        L_0x0087:
            r0 = r10.a;	 Catch:{ all -> 0x0081 }
            goto L_0x007d;
        L_0x008a:
            r0 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
            r10.a = r0;	 Catch:{ all -> 0x0081 }
        L_0x008e:
            r0 = r10.b;	 Catch:{ all -> 0x0081 }
            r0 = r0.k;	 Catch:{ all -> 0x0081 }
            if (r0 != 0) goto L_0x00a0;
        L_0x0096:
            monitor-exit(r4);	 Catch:{ all -> 0x0081 }
        L_0x0097:
            return;
        L_0x0098:
            r0 = 5000; // 0x1388 float:7.006E-42 double:2.4703E-320;
            r10.a = r0;	 Catch:{ all -> 0x0081 }
            goto L_0x008e;
        L_0x009d:
            monitor-exit(r4);	 Catch:{ all -> 0x0081 }
            goto L_0x0002;
        L_0x00a0:
            r0 = new java.util.ArrayList;	 Catch:{ all -> 0x0081 }
            r1 = r10.b;	 Catch:{ all -> 0x0081 }
            r1 = r1.i;	 Catch:{ all -> 0x0081 }
            r1 = r1.size();	 Catch:{ all -> 0x0081 }
            r0.<init>(r1);	 Catch:{ all -> 0x0081 }
            r1 = r10.b;	 Catch:{ all -> 0x0081 }
            r1 = r1.i;	 Catch:{ all -> 0x0081 }
            r0.addAll(r1);	 Catch:{ all -> 0x0081 }
            r1 = r10.b;	 Catch:{ all -> 0x0081 }
            r1 = r1.i;	 Catch:{ all -> 0x0081 }
            r1.clear();	 Catch:{ all -> 0x0081 }
            monitor-exit(r4);	 Catch:{ all -> 0x0081 }
            r1 = r10.b;	 Catch:{ Exception -> 0x0084 }
            r1.b(r0);	 Catch:{ Exception -> 0x0084 }
            goto L_0x0002;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.cservice.cloud.b.a.run():void");
        }
    }

    private b(Context context) {
        h = context;
        this.j = new a();
        this.k = true;
        this.j.start();
    }

    public static b a(Context context) {
        if (c == null) {
            synchronized (b.class) {
                if (c == null) {
                    c = new b(context);
                }
            }
        }
        return c;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.qq.reader.cservice.cloud.a.g r6, boolean r7, com.qq.reader.cservice.cloud.a r8) {
        /*
        r5 = this;
        r2 = -1;
        r3 = r5.d;
        monitor-enter(r3);
        r0 = r5.e;	 Catch:{ all -> 0x0081 }
        r0 = r0.contains(r6);	 Catch:{ all -> 0x0081 }
        if (r0 != 0) goto L_0x007f;
    L_0x000c:
        r1 = 0;
    L_0x000d:
        r0 = r5.e;	 Catch:{ all -> 0x0081 }
        r0 = r0.size();	 Catch:{ all -> 0x0081 }
        if (r1 >= r0) goto L_0x0092;
    L_0x0015:
        r0 = r5.e;	 Catch:{ all -> 0x0081 }
        r0 = r0.get(r1);	 Catch:{ all -> 0x0081 }
        r0 = (com.qq.reader.cservice.cloud.a.g) r0;	 Catch:{ all -> 0x0081 }
        r4 = r0.a(r6);	 Catch:{ all -> 0x0081 }
        if (r4 == 0) goto L_0x007c;
    L_0x0023:
        r0 = r0.g();	 Catch:{ all -> 0x0081 }
        r4 = com.qq.reader.cservice.cloud.CloudActionEnum.Prepared;	 Catch:{ all -> 0x0081 }
        if (r0 != r4) goto L_0x007c;
    L_0x002b:
        r0 = r1;
    L_0x002c:
        if (r0 == r2) goto L_0x0033;
    L_0x002e:
        r1 = r5.e;	 Catch:{ all -> 0x0081 }
        r1.remove(r0);	 Catch:{ all -> 0x0081 }
    L_0x0033:
        r6.a(r8);	 Catch:{ all -> 0x0081 }
        r0 = r5.e;	 Catch:{ all -> 0x0081 }
        r0.add(r6);	 Catch:{ all -> 0x0081 }
        monitor-exit(r3);	 Catch:{ all -> 0x0081 }
        r0 = com.qq.reader.appconfig.b.d;
        if (r0 == 0) goto L_0x0074;
    L_0x0040:
        r0 = "-START-";
        com.qq.reader.common.monitor.j.a(r0);	 Catch:{ Exception -> 0x0090 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0090 }
        r0.<init>();	 Catch:{ Exception -> 0x0090 }
        r1 = r6.getClass();	 Catch:{ Exception -> 0x0090 }
        r1 = r1.getSimpleName();	 Catch:{ Exception -> 0x0090 }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x0090 }
        r1 = "[";
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x0090 }
        r2 = r6.b();	 Catch:{ Exception -> 0x0090 }
        r0 = r0.append(r2);	 Catch:{ Exception -> 0x0090 }
        r1 = "]";
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x0090 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0090 }
        com.qq.reader.common.monitor.j.a(r0);	 Catch:{ Exception -> 0x0090 }
    L_0x0074:
        r0 = r6 instanceof com.qq.reader.cservice.cloud.a.h;
        if (r0 == 0) goto L_0x0084;
    L_0x0078:
        r5.a(r6);
    L_0x007b:
        return;
    L_0x007c:
        r1 = r1 + 1;
        goto L_0x000d;
    L_0x007f:
        monitor-exit(r3);	 Catch:{ all -> 0x0081 }
        goto L_0x007b;
    L_0x0081:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0081 }
        throw r0;
    L_0x0084:
        r0 = r6 instanceof com.qq.reader.cservice.cloud.a.e;
        if (r0 == 0) goto L_0x008c;
    L_0x0088:
        r5.c(r6);
        goto L_0x007b;
    L_0x008c:
        r5.a(r6, r7);
        goto L_0x007b;
    L_0x0090:
        r0 = move-exception;
        goto L_0x0074;
    L_0x0092:
        r0 = r2;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.cservice.cloud.b.a(com.qq.reader.cservice.cloud.a.g, boolean, com.qq.reader.cservice.cloud.a):void");
    }

    private void c() {
        synchronized (this.d) {
            Iterator it = this.e.iterator();
            while (it.hasNext()) {
                g gVar = (g) it.next();
                if (gVar.g() == CloudActionEnum.Finished) {
                    gVar.a(null);
                    it.remove();
                }
            }
        }
    }

    private void a(f fVar) {
        g b = l.b().b(fVar.c);
        if (b == null) {
            b = fVar.j();
            if (b != null) {
                b.d(fVar.j().h());
                b.i(fVar.j().q());
                b.a(fVar.j().i());
                b.j(fVar.j().t());
            }
        }
        g gVar = b;
        if (gVar != null) {
            gVar.b(fVar.j().d());
            gVar.a(fVar.j().c());
            gVar.j(fVar.j().t());
            gVar.c(fVar.d);
            if (!"delete".equals(fVar.a())) {
                if (fVar.a != 0 || fVar.e != 2) {
                    if (fVar.a != 1 || !"update".equals(fVar.a()) || fVar.e() != 101) {
                        l.b().a(gVar);
                        Mark e = i.c().e(String.valueOf(gVar.f()));
                        if (e != null) {
                            i.c().a(String.valueOf(gVar.f()), gVar.c(), gVar.d(), false, gVar.u());
                        } else if ("add".equals(fVar.a())) {
                            Intent intent = new Intent();
                            intent.setAction(com.qq.reader.common.c.a.cw);
                            intent.putExtra(com.qq.reader.common.c.a.cE, gVar.f());
                            h.sendBroadcast(intent);
                        }
                        if (e != null && e.getSynBook() == 0) {
                            i.c().a(gVar.f(), true);
                        }
                    }
                }
            }
        }
    }

    public void a(int i, a aVar) {
        if (this.e != null) {
            try {
                for (g gVar : this.e) {
                    if (gVar.d() == i) {
                        gVar.a(aVar);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void a(int i) {
        if (this.e != null) {
            for (g gVar : this.e) {
                if (gVar.d() == i) {
                    gVar.a(null);
                }
            }
        }
    }

    private void b(f fVar) {
        List<g> list = (List) this.g.get(Long.valueOf(fVar.b));
        this.g.remove(Long.valueOf(fVar.b));
        if (list != null) {
            g gVar;
            a e;
            switch (fVar.a) {
                case 0:
                    for (g gVar2 : list) {
                        gVar2.a(CloudActionEnum.Finished);
                        if (fVar.e == 1 || fVar.e == 0) {
                            a(fVar);
                            if (gVar2.e() != null) {
                                gVar2.e().a(fVar, true);
                            }
                        } else if (fVar.e == 2) {
                            fVar.a(gVar2.b());
                            a(fVar);
                        } else if (fVar.e == 100 && gVar2.e() != null) {
                            gVar2.e().a(fVar, true);
                        }
                    }
                    break;
                case 1:
                    if (!"batdel".equals(fVar.a())) {
                        if (fVar.e != 0 && fVar.e != 101) {
                            if (fVar.e >= 0) {
                                if (fVar.e == 100) {
                                    for (g gVar22 : list) {
                                        if (gVar22.e() != null) {
                                            gVar22.e().a(fVar, true);
                                        }
                                    }
                                    break;
                                }
                            }
                            for (Object obj : list) {
                                if (obj.b() == fVar.c) {
                                    if ("update".equals(obj.n())) {
                                        obj.a(CloudActionEnum.Finished);
                                        fVar.a(obj.n());
                                        if (obj.e() != null) {
                                            obj.e().a(fVar, false);
                                            break;
                                        }
                                    }
                                    for (g gVar3 : this.e) {
                                        if (gVar3.a(obj) && gVar3.g() == CloudActionEnum.Prepared) {
                                            if (gVar3 == null) {
                                                obj.a(CloudActionEnum.Finished);
                                                break;
                                            } else {
                                                obj.a(CloudActionEnum.Prepared);
                                                break;
                                            }
                                        }
                                    }
                                    g gVar32 = null;
                                    if (gVar32 == null) {
                                        obj.a(CloudActionEnum.Finished);
                                    } else {
                                        obj.a(CloudActionEnum.Prepared);
                                    }
                                }
                            }
                            break;
                        }
                        for (g gVar222 : list) {
                            if (gVar222.b() == fVar.c) {
                                gVar222.a(CloudActionEnum.Finished);
                                fVar.a(gVar222.n());
                                a(fVar);
                                if (gVar222.e() != null) {
                                    gVar222.e().a(fVar, true);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    List<a> arrayList = new ArrayList();
                    for (g gVar2222 : list) {
                        if (gVar2222 instanceof c) {
                            gVar2222.a(CloudActionEnum.Finished);
                            e = gVar2222.e();
                            if (!(e == null || arrayList.contains(e))) {
                                arrayList.add(e);
                            }
                            for (a e2 : arrayList) {
                                e2.a(fVar, true);
                            }
                            break;
                        }
                    }
                    while (r1.hasNext()) {
                        e2.a(fVar, true);
                    }
                    break;
                case 2:
                    gVar2222 = (g) list.get(0);
                    gVar2222.a(CloudActionEnum.Finished);
                    e2 = gVar2222.e();
                    switch (fVar.e) {
                        case 0:
                        case 1:
                            if (e2 != null) {
                                e2.a(fVar, true);
                                break;
                            }
                            break;
                        default:
                            if (e2 != null) {
                                e2.a(fVar, false);
                                break;
                            }
                            break;
                    }
            }
            c();
        }
    }

    private boolean a(g gVar) {
        ReaderTask cloudListUpdateTask = new CloudListUpdateTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    com.qq.reader.common.monitor.debug.c.a("cloud", "Response OK.." + str);
                    if (com.qq.reader.appconfig.b.d) {
                        try {
                            j.a("OK : " + str);
                        } catch (Exception e) {
                        }
                    }
                    this.a.b(str);
                } catch (Exception e2) {
                    e2.printStackTrace();
                } finally {
                    this.a.g.remove(Long.valueOf(readerProtocolTask.getTid()));
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                com.qq.reader.common.monitor.debug.c.a("cloud", "Response ERROR..");
                if (com.qq.reader.appconfig.b.d) {
                    try {
                        j.a("ERROR : " + exception.toString());
                    } catch (Exception e) {
                    }
                }
                this.a.a(2, readerProtocolTask, exception);
            }
        }, b(gVar));
        if (com.qq.reader.appconfig.b.d) {
            try {
                j.a("url : " + cloudListUpdateTask.getUrl() + "\n" + "header : " + cloudListUpdateTask.getHeaderPrintString() + "\n" + "body : " + cloudListUpdateTask.getRequest());
            } catch (Exception e) {
            }
        }
        com.qq.reader.common.monitor.debug.c.a("cloud", "Request:  url : " + cloudListUpdateTask.getUrl() + " /  body : " + cloudListUpdateTask.getRequest());
        com.qq.reader.common.readertask.g.a().a(cloudListUpdateTask);
        return true;
    }

    private long b(g gVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(gVar);
        long j = this.f;
        this.g.put(Long.valueOf(j), arrayList);
        this.f++;
        return j;
    }

    private boolean c(g gVar) {
        ReaderTask cloudSynUpdateBookTask = new CloudSynUpdateBookTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    if (com.qq.reader.appconfig.b.d) {
                        try {
                            j.a("OK : " + str);
                        } catch (Exception e) {
                        }
                    }
                    this.a.c(str);
                } catch (Exception e2) {
                    e2.printStackTrace();
                } finally {
                    this.a.g.remove(Long.valueOf(readerProtocolTask.getTid()));
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                if (com.qq.reader.appconfig.b.d) {
                    try {
                        j.a("ERROR : " + exception.toString());
                    } catch (Exception e) {
                    }
                }
                this.a.a(0, readerProtocolTask, exception);
            }
        }, gVar.b(), gVar.f(), d(gVar), gVar.j(), gVar.k(), gVar.m());
        if (com.qq.reader.appconfig.b.d) {
            try {
                j.a("url : " + cloudSynUpdateBookTask.getUrl() + "\n" + "header : " + cloudSynUpdateBookTask.getHeaderPrintString() + "\n" + "body : " + cloudSynUpdateBookTask.getRequest());
            } catch (Exception e) {
            }
        }
        com.qq.reader.common.readertask.g.a().a(cloudSynUpdateBookTask);
        return true;
    }

    private long d(g gVar) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(gVar);
        long j = this.f;
        this.g.put(Long.valueOf(j), arrayList);
        this.f++;
        return j;
    }

    private boolean a(g gVar, boolean z) {
        if (gVar instanceof c) {
            List arrayList = new ArrayList();
            arrayList.add(gVar);
            b(arrayList);
        } else {
            synchronized (this.d) {
                if (((gVar instanceof f) || (gVar instanceof c) || (gVar instanceof com.qq.reader.cservice.cloud.a.b)) && gVar.g() == CloudActionEnum.Prepared) {
                    a(this.i, gVar);
                    this.d.notifyAll();
                }
            }
        }
        return true;
    }

    private g a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String str = "";
        jSONObject.optInt("code");
        long optLong = jSONObject.optLong("bookid");
        long optLong2 = jSONObject.optLong("updatetime");
        long optLong3 = jSONObject.optLong("chapterid");
        int optInt = jSONObject.optInt("offset");
        String optString = jSONObject.optString("title");
        String optString2 = jSONObject.optString("author");
        int optInt2 = jSONObject.optInt("resType");
        if (optInt2 == 3) {
            str = ao.h(optLong);
        } else {
            str = ao.g(optLong);
        }
        String optString3 = jSONObject.optString("format");
        String optString4 = jSONObject.optString("chaptertitle");
        int optInt3 = jSONObject.optInt("type");
        int optInt4 = jSONObject.optInt("isfinished");
        int optInt5 = jSONObject.optInt("drm");
        int optInt6 = jSONObject.optInt("maxchapter");
        int optInt7 = jSONObject.optInt("downloadable");
        long optLong4 = jSONObject.optLong("lastuploadtime");
        String optString5 = jSONObject.optString("lastcname");
        String optString6 = jSONObject.optString("downloadinfo");
        String optString7 = jSONObject.optString("pictureid");
        g gVar = new g(optLong, optLong2, optInt2);
        gVar.a(optString, optString3);
        gVar.a(optString6);
        gVar.d(optString);
        gVar.h(optString3);
        gVar.e(optString2);
        gVar.g(str);
        gVar.d(optLong3);
        gVar.a(optInt);
        gVar.i(optString4);
        gVar.d(optInt6);
        gVar.f(optInt4);
        gVar.g(optInt2);
        gVar.c(optInt7);
        gVar.b(optInt5);
        gVar.e(optInt3);
        gVar.a(optLong4);
        gVar.b(optString5);
        gVar.j(optString7);
        return gVar;
    }

    private void a(int i, String str) throws JSONException {
        com.qq.reader.common.monitor.debug.c.a("cloud", "Response: " + str);
        if (str != null && str.length() > 0) {
            String str2 = "";
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("code");
            long optLong = jSONObject.optLong("tid");
            f fVar;
            if (i == 0) {
                a(jSONObject.optString("note"), optLong);
                if (optInt == 1) {
                    g a = a(jSONObject.optJSONObject("book"));
                    fVar = new f(0, optLong, optInt, a.f(), a.h(), a.i(), a.g(), a.t());
                    fVar.a(a);
                    b(fVar);
                } else if (optInt == 0 || optInt == 2) {
                    b(new f(0, optLong, optInt, 0, (long) null, 0, 0, str2));
                } else if (optInt == 100) {
                    b(new f(0, optLong, optInt, 0, (long) null, 0, 0, str2));
                } else {
                    b(new f(0, optLong, optInt, 0, (long) null, 0, 0, str2));
                }
            } else if (i == 1 && optInt == 0) {
                JSONObject optJSONObject = jSONObject.optJSONObject("bookbatdel");
                if (optJSONObject == null) {
                    JSONArray optJSONArray = jSONObject.optJSONArray("books");
                    int length = optJSONArray.length();
                    if (length > 0) {
                        for (int i2 = 0; i2 < length; i2++) {
                            jSONObject = (JSONObject) optJSONArray.opt(i2);
                            if (jSONObject != null) {
                                g a2 = a(jSONObject);
                                fVar = new f(i, optLong, jSONObject.optInt("code"), a2.f(), a2.h(), a2.i(), a2.g(), a2.t());
                                fVar.b(a2.j());
                                fVar.c(a2.k());
                                fVar.d(a2.m());
                                fVar.e(a2.n());
                                fVar.f(a2.q());
                                fVar.d(a2.s());
                                fVar.e(a2.u());
                                fVar.a(a2.o());
                                fVar.c(a2.r());
                                fVar.b(a2.p());
                                fVar.a(a2);
                                b(fVar);
                            }
                        }
                    }
                } else if (optJSONObject.optInt("code") == 0) {
                    fVar = new f(i, optLong, 0, 0, 0, 0, 0, null);
                    fVar.a("batdel");
                    b(fVar);
                }
            }
        }
    }

    private void a(String str) {
        try {
            a(1, str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void a(String str, long j) {
        if (str != null || str.length() != 0) {
            d parseNoteGetReceiveData = CloudNoteSynGetTask.parseNoteGetReceiveData(str);
            if (parseNoteGetReceiveData != null) {
                boolean z;
                if (parseNoteGetReceiveData.b >= 0) {
                    z = false;
                    for (com.qq.reader.framework.a.b bVar : parseNoteGetReceiveData.c) {
                        boolean z2;
                        com.qq.reader.framework.a.b b = s.a().b(bVar.h(), bVar.a());
                        if (b == null) {
                            s.a().e(bVar.h(), bVar.a());
                            if (bVar.a(s.a())) {
                                s.a().a(bVar.h(), bVar.a(), (long) bVar.d(), bVar.g(), true);
                            }
                            z2 = true;
                        } else if (b.d() == bVar.d() && b.g() != 0) {
                            z2 = z;
                        } else if ((b.d() >= bVar.d() || b.b() <= bVar.b()) && (b.d() <= bVar.d() || b.b() >= bVar.b())) {
                            s.a().e(bVar.h(), bVar.a());
                            if (bVar.a(s.a())) {
                                s.a().a(bVar.h(), bVar.a(), (long) bVar.d(), bVar.g(), true);
                            }
                            z2 = true;
                        } else {
                            z2 = true;
                        }
                        z = z2;
                    }
                } else {
                    z = false;
                }
                if (z) {
                    List<g> list = (List) this.g.get(Long.valueOf(j));
                    if (list != null) {
                        for (g gVar : list) {
                            if (gVar.e() != null) {
                                gVar.e().a(parseNoteGetReceiveData);
                            }
                        }
                    }
                }
            }
        }
    }

    private void b(String str) {
        long optLong;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("code");
            optLong = jSONObject.optLong("tid");
            try {
                long optLong2 = jSONObject.optLong("latestversion");
                f fVar = new f(2, optLong, optInt, optLong2);
                if (1 == optInt) {
                    List a = a(h, optLong2, jSONObject.getJSONArray("books"));
                    fVar.a(a);
                    a(a);
                }
                Object optString = jSONObject.optString("booksecretinfo");
                if (!TextUtils.isEmpty(optString)) {
                    String[] split = optString.split("\\|");
                    if (split != null && split.length > 0) {
                        for (int i = 0; i < split.length; i++) {
                            com.qq.reader.common.readertask.g.a().a(new CloudActionManager$3(this, split[i].split(":")[0], Integer.parseInt(split[i].split(":")[1])));
                        }
                    }
                }
                b(fVar);
            } catch (Exception e) {
                b(new f(2, optLong, -1, 0));
            }
        } catch (Exception e2) {
            optLong = 0;
            b(new f(2, optLong, -1, 0));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<com.qq.reader.cservice.cloud.g> a(android.content.Context r33, long r34, org.json.JSONArray r36) {
        /*
        r9 = new java.util.ArrayList;
        r9.<init>();
        r13 = new java.util.ArrayList;
        r13.<init>();
        r2 = r36.length();	 Catch:{ JSONException -> 0x0170 }
        if (r2 <= 0) goto L_0x015d;
    L_0x0010:
        r2 = r2 + -1;
        r12 = r2;
    L_0x0013:
        if (r12 < 0) goto L_0x0158;
    L_0x0015:
        r0 = r36;
        r2 = r0.get(r12);	 Catch:{ JSONException -> 0x0170 }
        r2 = (org.json.JSONObject) r2;	 Catch:{ JSONException -> 0x0170 }
        r3 = "bookid";
        r4 = r2.optLong(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = "title";
        r14 = r2.optString(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = "updatetime";
        r16 = r2.optLong(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = "author";
        r15 = r2.optString(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = "restype";
        r8 = r2.optInt(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = 3;
        if (r8 != r3) goto L_0x013e;
    L_0x0043:
        r3 = com.qq.reader.common.utils.ao.h(r4);	 Catch:{ JSONException -> 0x0170 }
        r11 = r3;
    L_0x0048:
        r3 = "offset";
        r18 = r2.optInt(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = "chapterid";
        r20 = r2.optLong(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = "maxchapter";
        r19 = r2.optInt(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = "isfinished";
        r22 = r2.optInt(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = "downloadable";
        r23 = r2.optInt(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = "type";
        r24 = r2.optInt(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = "lastuploadtime";
        r26 = r2.optLong(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = "lastcname";
        r25 = r2.optString(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = "origin";
        r28 = r2.optString(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = "follow";
        r2.optInt(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = "downloadinfo";
        r29 = r2.optString(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = "pictureid";
        r30 = r2.optString(r3);	 Catch:{ JSONException -> 0x0170 }
        r3 = new com.qq.reader.module.bookstore.qnative.b.b;	 Catch:{ Exception -> 0x0145 }
        r3.<init>();	 Catch:{ Exception -> 0x0145 }
        r0 = r29;
        r3.a(r0);	 Catch:{ Exception -> 0x0145 }
        r6 = r3.e();	 Catch:{ Exception -> 0x0145 }
        r3 = r3.f();	 Catch:{ Exception -> 0x0145 }
        if (r6 == 0) goto L_0x00b8;
    L_0x00af:
        r7 = r6.length();	 Catch:{ Exception -> 0x0145 }
        if (r7 == 0) goto L_0x00b8;
    L_0x00b5:
        r7 = -1;
        if (r3 != r7) goto L_0x017a;
    L_0x00b8:
        r3 = "drm";
        r3 = r2.optInt(r3);	 Catch:{ Exception -> 0x0145 }
        r6 = "format";
        r2 = r2.optString(r6);	 Catch:{ Exception -> 0x0145 }
        r31 = r3;
        r3 = r2;
        r2 = r31;
    L_0x00cb:
        r10 = r3;
    L_0x00cc:
        r3 = new com.qq.reader.cservice.cloud.g;	 Catch:{ JSONException -> 0x0170 }
        r6 = 0;
        r3.<init>(r4, r6, r8);	 Catch:{ JSONException -> 0x0170 }
        r3.a(r14, r10);	 Catch:{ JSONException -> 0x0170 }
        r0 = r29;
        r3.a(r0);	 Catch:{ JSONException -> 0x0170 }
        r3.d(r14);	 Catch:{ JSONException -> 0x0170 }
        r3.h(r10);	 Catch:{ JSONException -> 0x0170 }
        r3.e(r15);	 Catch:{ JSONException -> 0x0170 }
        r3.g(r11);	 Catch:{ JSONException -> 0x0170 }
        r0 = r20;
        r3.d(r0);	 Catch:{ JSONException -> 0x0170 }
        r0 = r18;
        r3.a(r0);	 Catch:{ JSONException -> 0x0170 }
        r0 = r19;
        r3.d(r0);	 Catch:{ JSONException -> 0x0170 }
        r0 = r22;
        r3.f(r0);	 Catch:{ JSONException -> 0x0170 }
        r0 = r23;
        r3.c(r0);	 Catch:{ JSONException -> 0x0170 }
        r3.b(r2);	 Catch:{ JSONException -> 0x0170 }
        r0 = r24;
        r3.e(r0);	 Catch:{ JSONException -> 0x0170 }
        r3.g(r8);	 Catch:{ JSONException -> 0x0170 }
        r0 = r16;
        r3.c(r0);	 Catch:{ JSONException -> 0x0170 }
        r0 = r26;
        r3.a(r0);	 Catch:{ JSONException -> 0x0170 }
        r0 = r25;
        r3.b(r0);	 Catch:{ JSONException -> 0x0170 }
        r3.h(r10);	 Catch:{ JSONException -> 0x0170 }
        r0 = r30;
        r3.j(r0);	 Catch:{ JSONException -> 0x0170 }
        r9.add(r3);	 Catch:{ JSONException -> 0x0170 }
        r2 = r28.length();	 Catch:{ JSONException -> 0x0170 }
        if (r2 <= 0) goto L_0x0139;
    L_0x012b:
        r2 = new com.qq.reader.common.monitor.a.a;	 Catch:{ JSONException -> 0x0170 }
        r3 = java.lang.String.valueOf(r4);	 Catch:{ JSONException -> 0x0170 }
        r0 = r28;
        r2.<init>(r3, r0);	 Catch:{ JSONException -> 0x0170 }
        r13.add(r2);	 Catch:{ JSONException -> 0x0170 }
    L_0x0139:
        r2 = r12 + -1;
        r12 = r2;
        goto L_0x0013;
    L_0x013e:
        r3 = com.qq.reader.common.utils.ao.g(r4);	 Catch:{ JSONException -> 0x0170 }
        r11 = r3;
        goto L_0x0048;
    L_0x0145:
        r3 = move-exception;
        r3 = "drm";
        r3 = r2.optInt(r3);	 Catch:{ JSONException -> 0x0170 }
        r6 = "format";
        r2 = r2.optString(r6);	 Catch:{ JSONException -> 0x0170 }
        r10 = r2;
        r2 = r3;
        goto L_0x00cc;
    L_0x0158:
        r2 = a;	 Catch:{ JSONException -> 0x0170 }
        java.util.Collections.sort(r9, r2);	 Catch:{ JSONException -> 0x0170 }
    L_0x015d:
        com.qq.reader.cservice.cloud.h.a(r34);
        r2 = com.qq.reader.common.db.handle.l.b();
        r2.a(r9);
        r2 = com.qq.reader.common.db.handle.j.a();
        r2.a(r13);
        r2 = r9;
    L_0x016f:
        return r2;
    L_0x0170:
        r2 = move-exception;
        r2.printStackTrace();
        r2 = new java.util.ArrayList;
        r2.<init>();
        goto L_0x016f;
    L_0x017a:
        r2 = r3;
        r3 = r6;
        goto L_0x00cb;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.cservice.cloud.b.a(android.content.Context, long, org.json.JSONArray):java.util.ArrayList<com.qq.reader.cservice.cloud.g>");
    }

    private void c(String str) {
        try {
            a(0, str);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void a(int i, ReaderProtocolTask readerProtocolTask, Exception exception) {
        List<g> list = (List) this.g.get(Long.valueOf(readerProtocolTask.getTid()));
        this.g.remove(Long.valueOf(readerProtocolTask.getTid()));
        if (list != null) {
            for (g gVar : list) {
                gVar.a(CloudActionEnum.Finished);
                a e = gVar.e();
                if (!(gVar instanceof f)) {
                    f fVar;
                    if ("batdel".equals(gVar.n())) {
                        fVar = new f(i, readerProtocolTask.getTid(), -1, gVar.b(), 1, 0, 0, "");
                        fVar.a("batdel");
                        if (e != null) {
                            e.a(fVar, false);
                        }
                    } else {
                        fVar = new f(i, readerProtocolTask.getTid(), -1, gVar.b(), 1, 0, 0, "");
                        if (e != null) {
                            e.a(fVar, false);
                        }
                    }
                }
            }
            c();
        }
    }

    private void a(List<g> list, g gVar) {
        int i;
        List arrayList = new ArrayList();
        if (gVar instanceof c) {
            arrayList.addAll(((c) gVar).g_());
        } else {
            arrayList.add(Long.valueOf(gVar.b()));
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            g gVar2 = (g) it.next();
            if (gVar2 instanceof c) {
                List g_ = ((c) gVar2).g_();
                if (g_ != null && g_.containsAll(arrayList)) {
                    i = 0;
                    break;
                }
            } else if (arrayList.contains(Long.valueOf(gVar2.b()))) {
                it.remove();
            }
        }
        i = 1;
        gVar.a(CloudActionEnum.Started);
        if (i != 0) {
            list.add(0, gVar);
        }
    }

    private void b(List<g> list) {
        long j = this.f;
        this.g.put(Long.valueOf(j), list);
        this.f++;
        ReaderTask cloudSynCommitBookTask = new CloudSynCommitBookTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                try {
                    if (com.qq.reader.appconfig.b.d) {
                        try {
                            j.a("OK : " + str);
                        } catch (Exception e) {
                        }
                    }
                    this.a.a(str);
                } catch (Exception e2) {
                    e2.printStackTrace();
                } finally {
                    this.a.g.remove(Long.valueOf(readerProtocolTask.getTid()));
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                if (com.qq.reader.appconfig.b.d) {
                    try {
                        j.a("ERROR : " + exception.toString());
                    } catch (Exception e) {
                    }
                }
                this.a.a(1, readerProtocolTask, exception);
            }
        }, j, list, h.a());
        if (com.qq.reader.appconfig.b.d) {
            try {
                j.a("url : " + cloudSynCommitBookTask.getUrl() + "\n" + "header : " + cloudSynCommitBookTask.getHeaderPrintString() + "\n" + "body : " + cloudSynCommitBookTask.getRequest());
            } catch (Exception e) {
            }
        }
        com.qq.reader.common.readertask.g.a().a(cloudSynCommitBookTask);
    }

    public void a(List<g> list) {
        if (list != null) {
            Map hashMap = new HashMap();
            for (g gVar : list) {
                hashMap.put(Long.valueOf(gVar.f()), gVar);
            }
            List arrayList = new ArrayList();
            Collection arrayList2 = new ArrayList();
            for (Mark mark : i.c().g()) {
                if (mark != null) {
                    long bookId = mark.getBookId();
                    if (hashMap.containsKey(Long.valueOf(bookId))) {
                        arrayList2.add((g) hashMap.get(Long.valueOf(bookId)));
                    } else if (mark.getSynBook() == 1) {
                        arrayList.add(mark);
                    }
                }
            }
            list.removeAll(arrayList2);
            a(arrayList, (List) list);
        }
    }

    private void a(List<Mark> list, List<g> list2) {
        c((List) list2);
        d((List) list);
    }

    private void c(List<g> list) {
        for (g gVar : list) {
            Mark a;
            String valueOf = String.valueOf(gVar.f());
            String j = gVar.j();
            String k = gVar.k();
            String str = "";
            String q = gVar.q();
            long h = gVar.h();
            int r = gVar.r();
            String n = gVar.n();
            String m = gVar.m();
            int o = gVar.o();
            int u = gVar.u();
            int i = gVar.i();
            long c = gVar.c();
            String d = gVar.d();
            String b = gVar.b();
            com.qq.reader.module.bookstore.qnative.b.b bVar = new com.qq.reader.module.bookstore.qnative.b.b();
            bVar.a(b);
            if (bVar.a()) {
                a = com.qq.reader.framework.mark.a.a(gVar.f(), j, k, b);
            } else {
                String str2;
                int w = gVar.w();
                if (w == 2) {
                    a = new TingBookMark(gVar.f(), j);
                    str2 = "mp3";
                } else if (w == 3) {
                    Mark comicBookMark = new ComicBookMark(gVar.f(), j);
                    String[] a2 = e.a(gVar.t());
                    if (a2 != null) {
                        long parseLong = Long.parseLong(a2[0]);
                        int parseInt = Integer.parseInt(a2[1]);
                        ((ComicBookMark) comicBookMark).setSectionIndex(Integer.parseInt(a2[2]));
                        ((ComicBookMark) comicBookMark).setPicId(parseLong);
                        ((ComicBookMark) comicBookMark).setPicOffset(parseInt);
                    }
                    int i2 = gVar.i();
                    ((ComicBookMark) comicBookMark).setSectionId(gVar.h());
                    ((ComicBookMark) comicBookMark).setPicIndex(i2);
                    com.qq.reader.common.readertask.g.a().a(new CloudActionManager$5(this, comicBookMark));
                    a = comicBookMark;
                    str2 = n;
                } else {
                    a = new LocalMark(j, "", 0, 4, false);
                    str2 = n;
                }
                a.setId(valueOf);
                a.setAuthor(k);
                a.setPercentStr("0.0%");
                a.setHasNewContent(false);
                a.setBookId(gVar.f());
                a.setDownloadInfo(b);
                OnlineTag onlineTag = new OnlineTag(valueOf, "", 0);
                onlineTag.a(j).e(k).f(str).b(q).e(0).d(r).f(0).h(m).k(str2).i(o).h(u).a((long) i).j(w).b(System.currentTimeMillis());
                if (w == 3) {
                    onlineTag.c(((ComicBookMark) a).getSectionIndex());
                    onlineTag.d(r + 1);
                } else {
                    onlineTag.c((int) h);
                }
                v.b().b(onlineTag);
            }
            if (h == 1 && i == 0) {
                a.setStarPointStr(Mark.HEADPAGE_FLAG);
            }
            a.setCoverUrl(m);
            a.setSynBook(1);
            a.setLastUpdateTime(c);
            a.setLastUpdateChapter(d);
            a.setReadTime(0);
            i.c().a(a, true);
        }
    }

    private void d(List<Mark> list) {
        if (list != null && list.size() != 0) {
            a((List) list, true);
        }
    }

    private void a(List<Mark> list, boolean z) {
        if (i.c().b((List) list)) {
            com.qq.reader.common.readertask.g.a().a(new CloudActionManager$7(this, list));
        }
    }

    public void a() {
        synchronized (b.class) {
            c = null;
        }
    }
}
