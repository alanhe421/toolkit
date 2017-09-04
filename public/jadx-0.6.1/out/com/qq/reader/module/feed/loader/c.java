package com.qq.reader.module.feed.loader;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.conn.http.HttpResponseException;
import com.qq.reader.common.imageloader.a.a.b.a;
import com.qq.reader.common.imageloader.a.a.b.e;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.FeedDataTask;
import com.qq.reader.common.utils.m;
import com.qq.reader.module.feed.card.FeedRookieEntranceCard;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FeedDataLoader */
public class c extends b {
    private static volatile c a = null;
    private final int b = 2;
    private final int c = 1;
    private final int d = 300;
    private com.qq.reader.common.imageloader.a.a.a.a.c e;
    private final int f = 100;
    private final int g = 101;
    private int h = 0;
    private int i = 0;
    private String j = null;

    private c() {
        c();
    }

    public static synchronized c b() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c();
            }
            cVar = a;
        }
        return cVar;
    }

    private void c() {
        try {
            this.e = (com.qq.reader.common.imageloader.a.a.a.a.c) a.a(ReaderApplication.getApplicationImp(), new e(), 52428800, 0, new File(com.qq.reader.common.c.a.ah).getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void a(com.qq.reader.module.feed.data.impl.e eVar, Handler handler) {
        if (eVar != null && handler != null) {
            WeakReference weakReference = new WeakReference(handler);
            if (eVar.j() == 2) {
                a(weakReference, eVar);
            } else if (eVar.j() == 0) {
                b(weakReference, eVar);
            } else {
                c(weakReference, eVar);
            }
        }
    }

    private void a(WeakReference<Handler> weakReference, com.qq.reader.module.feed.data.impl.e eVar) {
        String bB = d.bB(ReaderApplication.getApplicationImp());
        int indexOf = bB.indexOf("-");
        if (indexOf != -1) {
            bB = bB.substring(0, indexOf);
        }
        eVar.b(h.b(bB));
        b(weakReference, eVar);
        this.j = null;
    }

    private void b(WeakReference<Handler> weakReference, com.qq.reader.module.feed.data.impl.e eVar) {
        String g = eVar.g();
        ArrayList arrayList = new ArrayList();
        int c = eVar.c();
        com.qq.reader.common.monitor.debug.c.e("FeedDataLoader", "========loadDataUp begin========\nendTimeLine:" + g + " | endSliceOrder:" + c);
        int i = 0;
        int i2 = 1;
        while (i <= 300) {
            String a;
            int i3 = i + 1;
            if (i2 != 0) {
                i = c - 1;
                c = i;
                a = h.a(g, i);
            } else {
                a = g;
            }
            File b = b(a);
            if (b == null || !b.exists()) {
                if (i2 == 0) {
                    break;
                } else if (c < 0) {
                    g = h.c(g);
                    List a2 = a(g);
                    if (a2.size() == 0) {
                        i2 = 0;
                    } else {
                        c = a2.size() + 1;
                    }
                } else {
                    i2 = 0;
                }
            } else if (b.length() != 0 || i2 != 0) {
                if (b.length() != 0) {
                    arrayList.add(b);
                }
                if (arrayList.size() >= 2) {
                    break;
                }
                eVar.b(g);
                eVar.a(c);
                if (i2 == 0) {
                    i2 = 1;
                    c = 0;
                }
            } else {
                eVar.b(g);
                eVar.a(0);
                i = i3;
                i2 = 1;
                c = 0;
            }
            i = i3;
        }
        com.qq.reader.common.monitor.debug.c.e("FeedDataLoader", "========loadDataUp end========");
        a((WeakReference) weakReference, eVar, arrayList);
    }

    private List<File> a(String str) {
        List<File> arrayList = new ArrayList();
        int i = 0;
        while (true) {
            i++;
            File b = b(h.a(str, i));
            if (b == null || !b.exists()) {
                return arrayList;
            }
            arrayList.add(b);
        }
        return arrayList;
    }

    private void c(WeakReference<Handler> weakReference, com.qq.reader.module.feed.data.impl.e eVar) {
        String f = eVar.f();
        int c = eVar.c();
        com.qq.reader.common.monitor.debug.c.e("FeedDataLoader", "========loadDataDown========\nstarttimeLine:" + f + " | startSliceOrder:" + c);
        ArrayList arrayList = new ArrayList();
        Object obj = 1;
        while (true) {
            int i;
            String a;
            if (obj != null) {
                i = c + 1;
                a = h.a(f, i);
            } else {
                i = c;
                a = f;
            }
            File b = b(a);
            Object obj2;
            if (b == null || !b.exists()) {
                if (obj == null) {
                    break;
                }
                f = h.b(f);
                obj2 = null;
                obj = obj2;
                c = i;
            } else {
                eVar.a(f);
                if (obj == null) {
                    obj2 = 1;
                    i = 0;
                } else {
                    obj2 = obj;
                }
                eVar.a(i);
                if (b.length() > 0) {
                    arrayList.add(b);
                }
                if (arrayList.size() >= 1) {
                    break;
                }
                obj = obj2;
                c = i;
            }
        }
        com.qq.reader.common.monitor.debug.c.e("FeedDataLoader", "========loadDataDown end========");
        a((WeakReference) weakReference, eVar, arrayList);
    }

    private boolean d(WeakReference<Handler> weakReference, com.qq.reader.module.feed.data.impl.e eVar) {
        boolean z = true;
        if (1 != eVar.j()) {
            return false;
        }
        Object f = eVar.f();
        if (TextUtils.isEmpty(f)) {
            return false;
        }
        String str;
        boolean z2;
        if (h.a(f) > 3600) {
            eVar.a(h.a());
            eVar.a(0);
        }
        if (this.j == null) {
            this.j = eVar.f();
        }
        String str2 = this.j;
        int i = 0;
        while (i < 720) {
            i++;
            str2 = h.d(str2);
            File b = b(str2);
            if (b != null) {
                if (!b.exists()) {
                }
            }
            eVar.c(str2);
            str = str2;
            z2 = true;
        }
        str = str2;
        z2 = false;
        if (z2 <= false) {
            this.j = str;
            eVar.a(true);
        } else {
            z = false;
        }
        return z;
    }

    private void a(final WeakReference<Handler> weakReference, final com.qq.reader.module.feed.data.impl.e eVar, ArrayList<File> arrayList) {
        ReaderTask feedLoadDiskDataTask = new FeedLoadDiskDataTask(eVar, arrayList);
        feedLoadDiskDataTask.setLoadListener(new com.qq.reader.module.bookstore.qnative.storage.disk.a(this) {
            final /* synthetic */ c c;

            public void onLoadSucess(Object obj) {
                com.qq.reader.common.monitor.debug.c.e("FeedTimeUtil", "loadDataWithDisk OK...");
                Iterator it;
                if (eVar.j() == 2) {
                    boolean a;
                    String str;
                    int i;
                    com.qq.reader.common.monitor.debug.c.e("FeedTimeUtil", "FeedDataPackage.OPT_ENTER ...... AUTO..... DOWN");
                    if (eVar.h().size() > 0) {
                        it = eVar.h().iterator();
                        while (it.hasNext()) {
                            ((FeedBaseCard) it.next()).mIsFromNet = false;
                        }
                        a = m.a(ReaderApplication.getApplicationImp());
                        if (a) {
                            eVar.c(false);
                        }
                        this.c.a(weakReference, eVar, false);
                        if (a) {
                            return;
                        }
                    }
                    f i2 = eVar.i();
                    if (i2 != null) {
                        str = i2.a;
                        i = i2.b;
                    } else {
                        str = null;
                        a = false;
                    }
                    com.qq.reader.module.feed.data.impl.e eVar = new com.qq.reader.module.feed.data.impl.e(str, 1);
                    eVar.a(i);
                    this.c.a(eVar, (Handler) weakReference.get());
                } else if (eVar.h().size() == 0) {
                    this.c.e(weakReference, eVar);
                } else {
                    this.c.a(eVar, "DISK");
                    it = eVar.h().iterator();
                    while (it.hasNext()) {
                        ((FeedBaseCard) it.next()).mIsFromNet = false;
                    }
                    this.c.a(weakReference, eVar, false);
                }
            }

            public void onLoadFailed(Object obj) {
                com.qq.reader.common.monitor.debug.c.e("FeedTimeUtil", "loadDataWithDisk ERROR...");
                this.c.b(weakReference, eVar, 0);
            }
        });
        g.a().a(feedLoadDiskDataTask);
    }

    private boolean a(int i) {
        if (!m.a(ReaderApplication.getApplicationImp())) {
            return true;
        }
        if (i == 1) {
            if (this.h >= 5) {
                return false;
            }
            this.h++;
            return true;
        } else if (i != 0) {
            return true;
        } else {
            if (this.i >= 5) {
                return false;
            }
            this.i++;
            return true;
        }
    }

    private synchronized void a(WeakReference<Handler> weakReference, com.qq.reader.module.feed.data.impl.e eVar, int i) {
        Message obtain = Message.obtain();
        obtain.what = 8000011;
        obtain.obj = eVar;
        obtain.arg1 = i;
        if (weakReference.get() != null) {
            ((Handler) weakReference.get()).sendMessageDelayed(obtain, 1000);
        }
    }

    private void e(final WeakReference<Handler> weakReference, final com.qq.reader.module.feed.data.impl.e eVar) {
        if (a(eVar.j())) {
            d(weakReference, eVar);
            eVar.d("A");
            ReaderTask feedDataTask = new FeedDataTask(eVar);
            feedDataTask.registerNetTaskListener(new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ c c;

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    try {
                        com.qq.reader.common.monitor.debug.c.e("FeedTimeUtil", "loadDataWithNet OK...");
                        JSONArray optJSONArray = new JSONObject(str).optJSONArray("areas");
                        if (optJSONArray == null || optJSONArray.length() != 0) {
                            int c = eVar.c();
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                String jSONObject = optJSONArray.optJSONObject(i).toString();
                                com.qq.reader.module.feed.data.impl.g gVar = new com.qq.reader.module.feed.data.impl.g();
                                if (eVar.a()) {
                                    c++;
                                    gVar.a(c);
                                }
                                if (eVar.a() || eVar.b()) {
                                    gVar.a(eVar.f());
                                }
                                gVar.b(jSONObject);
                                if (eVar.a()) {
                                    eVar.a(0, gVar);
                                } else {
                                    eVar.a(gVar);
                                }
                                this.c.a(gVar, false);
                            }
                            this.c.a(eVar, "NET");
                            Iterator it = eVar.h().iterator();
                            while (it.hasNext()) {
                                ((FeedBaseCard) it.next()).mIsFromNet = true;
                            }
                            this.c.a(weakReference, eVar, true);
                            return;
                        }
                        this.c.b(weakReference, eVar, -2);
                    } catch (Exception e) {
                        e.printStackTrace();
                        this.c.b(weakReference, eVar, -1);
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    com.qq.reader.common.monitor.debug.c.e("FeedTimeUtil", "loadDataWithNet ERROR...");
                    if (!(exception instanceof HttpResponseException)) {
                        this.c.b(weakReference, eVar, -1);
                    } else if (((HttpResponseException) exception).getStateCode() == 206) {
                        this.c.b(weakReference, eVar, -3);
                    } else {
                        this.c.b(weakReference, eVar, -1);
                    }
                }
            });
            g.a().a(feedDataTask);
            return;
        }
        a((WeakReference) weakReference, eVar, 0);
    }

    private synchronized void a(WeakReference<Handler> weakReference, com.qq.reader.module.feed.data.impl.e eVar, boolean z) {
        int i = 1;
        synchronized (this) {
            d.h(ReaderApplication.getApplicationImp(), System.currentTimeMillis());
            Handler handler = (Handler) weakReference.get();
            if (handler != null) {
                Message obtain;
                String[] e = eVar.e();
                if (eVar.j() == 1 && !eVar.a()) {
                    if (h.a(e[0], h.b(eVar.f()))) {
                        obtain = Message.obtain();
                        obtain.what = 8000005;
                        obtain.obj = eVar;
                        handler.sendMessage(obtain);
                    }
                }
                obtain = Message.obtain();
                obtain.what = 8000001;
                obtain.obj = eVar;
                if (eVar.h().size() <= 0 || !(eVar.h().get(0) instanceof FeedRookieEntranceCard)) {
                    i = 0;
                }
                obtain.arg1 = eVar.h().size() - i;
                if (z) {
                    obtain.arg2 = 100;
                } else {
                    obtain.arg2 = 101;
                }
                handler.sendMessage(obtain);
            }
        }
    }

    private synchronized void b(WeakReference<Handler> weakReference, com.qq.reader.module.feed.data.impl.e eVar, int i) {
        Message obtain = Message.obtain();
        obtain.what = 8000002;
        obtain.obj = eVar;
        obtain.arg1 = i;
        if (weakReference.get() != null) {
            ((Handler) weakReference.get()).sendMessage(obtain);
        }
    }

    public void a(com.qq.reader.module.feed.data.impl.g gVar) {
        a(gVar, false);
    }

    public void b(com.qq.reader.module.feed.data.impl.g gVar) {
        a(gVar, true);
    }

    private synchronized void a(com.qq.reader.module.feed.data.impl.g gVar, boolean z) {
        String c = gVar.c();
        String e = gVar.e();
        int d = gVar.d();
        if (c.length() > 0) {
            String str;
            if (d > 0) {
                try {
                    str = c + "-" + d;
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } else {
                str = c;
            }
            File a = this.e.a(str);
            if (a == null || z) {
                if (a != null) {
                    this.e.b(str);
                    a.delete();
                }
                if (!(e == null || e.length() == 0)) {
                    boolean z2;
                    InputStream byteArrayInputStream = new ByteArrayInputStream(e.getBytes());
                    ArrayList a2 = gVar.a();
                    if (a2 == null || a2.size() == 0) {
                        byteArrayInputStream = new ByteArrayInputStream(new byte[0]);
                        if (d > 0) {
                            z2 = true;
                        } else {
                            z2 = this.e.a(str, byteArrayInputStream, null);
                        }
                    } else {
                        z2 = this.e.a(str, byteArrayInputStream, null);
                    }
                    com.qq.reader.common.monitor.debug.c.e("FeedTimeUtil", str + " save OK...");
                    if (z2) {
                        if (d > 0) {
                            this.e.a(gVar.b(), new ByteArrayInputStream(new byte[0]), null);
                        }
                        c = d.bB(ReaderApplication.getApplicationImp());
                        com.qq.reader.common.monitor.debug.c.e("FeedTimeUtil", str + "--------" + c);
                        if (h.a(str, c)) {
                            d.z(ReaderApplication.getApplicationImp(), str);
                        }
                    } else {
                        com.qq.reader.common.monitor.debug.c.e("FeedTimeUtil", str + " save ERROR...");
                    }
                }
            } else {
                com.qq.reader.common.monitor.debug.c.e("FeedTimeUtil", str + " had exit...");
            }
        }
    }

    private File b(String str) {
        return this.e.a(str);
    }

    private void a(com.qq.reader.module.feed.data.impl.e eVar, String str) {
        if (com.qq.reader.appconfig.b.f) {
            try {
                com.qq.reader.common.monitor.debug.c.e("FeedPackageDate", "==========================FROM : " + str + "==========================");
                eVar.l();
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("FeedPackageDate", e.toString());
            }
        }
    }

    public void a() {
        synchronized (c.class) {
            a = null;
        }
    }
}
