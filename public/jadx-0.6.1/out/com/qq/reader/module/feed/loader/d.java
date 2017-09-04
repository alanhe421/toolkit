package com.qq.reader.module.feed.loader;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.b;
import com.qq.reader.common.imageloader.a.a.a.a.c;
import com.qq.reader.common.imageloader.a.a.b.a;
import com.qq.reader.common.imageloader.a.a.b.e;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.protocol.FeedDataTask;
import com.qq.reader.module.feed.card.FeedRookieEntranceCard;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/* compiled from: FeedDataStyleBLoader */
public class d extends b {
    public static ArrayList<ArrayList<b>> a;
    private static volatile d b = null;
    private final int c = 2;
    private final int d = 1;
    private final int e = 300;
    private c f;
    private final int g = 100;
    private final int h = 101;
    private ArrayList<String> i;
    private boolean j = false;
    private int k;
    private int l;
    private int m;
    private String n = null;

    private d() {
        i();
    }

    public static d b() {
        if (b == null) {
            synchronized (d.class) {
                if (b == null) {
                    b = new d();
                }
            }
        }
        return b;
    }

    private void i() {
        try {
            this.f = (c) a.a(ReaderApplication.getApplicationImp(), new e(), 52428800, 0, new File(com.qq.reader.common.c.a.ai).getAbsolutePath());
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
        eVar.b(k() + "99");
        b(weakReference, eVar);
        this.n = null;
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
                a = g.a(g, i);
            } else {
                a = g;
            }
            File b = b(a);
            if (b == null || !b.exists()) {
                if (i2 == 0) {
                    break;
                } else if (c < 0) {
                    g = g.c(g);
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
        eVar.b(g);
        a((WeakReference) weakReference, eVar, arrayList);
    }

    public String c() {
        j();
        if (this.i == null || this.i.size() == 0) {
            return "";
        }
        try {
            File a = this.f.a((String) this.i.get(0));
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            org.apache.commons.compress.a.b.a(new BufferedInputStream(new FileInputStream(a)), byteArrayOutputStream);
            String str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
            this.i.remove(0);
            return str;
        } catch (Exception e) {
            return "";
        }
    }

    private void j() {
        if (!this.j) {
            this.j = true;
            Object arrayList = new ArrayList();
            try {
                File file = new File(com.qq.reader.common.c.a.ai);
                if (file != null && file.exists()) {
                    File[] listFiles = file.listFiles();
                    String k = k();
                    for (File name : listFiles) {
                        String name2 = name.getName();
                        if (!(name2.startsWith("journal") || name2.substring(0, 8).equals(k))) {
                            arrayList.add(name2.substring(0, 10));
                        }
                    }
                    if (arrayList != null && arrayList.size() > 0) {
                        Collections.sort(arrayList, new a());
                        this.i = arrayList;
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    private List<File> a(String str) {
        List<File> arrayList = new ArrayList();
        int i = 0;
        while (true) {
            i++;
            File b = b(g.a(str, i));
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
                a = g.a(f, i);
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
                f = g.b(f);
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
        if (TextUtils.isEmpty(f) || g.a(f) > 3600) {
            return false;
        }
        String str;
        boolean z2;
        if (this.n == null) {
            this.n = eVar.f();
        }
        String str2 = this.n;
        int i = 0;
        while (i < 720) {
            i++;
            str2 = g.d(str2);
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
            this.n = str;
            eVar.a(true);
        } else {
            z = false;
        }
        return z;
    }

    private void a(WeakReference<Handler> weakReference, com.qq.reader.module.feed.data.impl.e eVar, ArrayList<File> arrayList) {
        ReaderTask feedLoadDiskDataTask = new FeedLoadDiskDataTask(eVar, arrayList);
        feedLoadDiskDataTask.setLoadListener(new 1(this, eVar, weakReference));
        g.a().a(feedLoadDiskDataTask);
    }

    private void e(WeakReference<Handler> weakReference, com.qq.reader.module.feed.data.impl.e eVar) {
        d(weakReference, eVar);
        eVar.d("B");
        ReaderTask feedDataTask = new FeedDataTask(eVar);
        feedDataTask.registerNetTaskListener(new 2(this, weakReference, eVar));
        g.a().a(feedDataTask);
    }

    private synchronized void a(WeakReference<Handler> weakReference, com.qq.reader.module.feed.data.impl.e eVar, boolean z) {
        int i = 1;
        synchronized (this) {
            com.qq.reader.appconfig.a.d.h(ReaderApplication.getApplicationImp(), System.currentTimeMillis());
            Handler handler = (Handler) weakReference.get();
            if (handler != null) {
                Message obtain;
                String[] e = eVar.e();
                if (eVar.j() == 1 && !eVar.a()) {
                    if (g.a(e[0], g.b(eVar.f()))) {
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
                ArrayList h = eVar.h();
                if (!(eVar.b() || h == null || h.size() <= 0)) {
                    com.qq.reader.appconfig.a.d.K(ReaderApplication.getApplicationImp(), com.qq.reader.appconfig.a.d.bw(ReaderApplication.getApplicationImp()) + eVar.h().size());
                }
                handler.sendMessage(obtain);
            }
        }
    }

    private synchronized void a(WeakReference<Handler> weakReference, com.qq.reader.module.feed.data.impl.e eVar, int i) {
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
            File a = this.f.a(str);
            if (a == null || z) {
                if (a != null) {
                    this.f.b(str);
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
                            z2 = this.f.a(str, byteArrayInputStream, null);
                        }
                    } else {
                        z2 = this.f.a(str, byteArrayInputStream, null);
                    }
                    com.qq.reader.common.monitor.debug.c.e("FeedTimeStyleBUtil", str + " save OK...");
                    if (z2) {
                        if (d > 0) {
                            this.f.a(gVar.b(), new ByteArrayInputStream(new byte[0]), null);
                        }
                        c = com.qq.reader.appconfig.a.d.bB(ReaderApplication.getApplicationImp());
                        com.qq.reader.common.monitor.debug.c.e("FeedTimeStyleBUtil", str + "--------" + c);
                        if (g.a(str, c)) {
                            com.qq.reader.appconfig.a.d.z(ReaderApplication.getApplicationImp(), str);
                        }
                    } else {
                        com.qq.reader.common.monitor.debug.c.e("FeedTimeStyleBUtil", str + " save ERROR...");
                    }
                }
            } else {
                com.qq.reader.common.monitor.debug.c.e("FeedTimeStyleBUtil", str + " had exit...");
            }
        }
    }

    private File b(String str) {
        return this.f.a(str);
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
        synchronized (d.class) {
            b = null;
        }
    }

    public void d() {
        this.j = false;
        a = null;
        this.k = 0;
        this.l = 0;
        this.m = 0;
    }

    private String k() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public void a(ArrayList<ArrayList<b>> arrayList) {
        a = arrayList;
    }

    public ArrayList<ArrayList<b>> e() {
        return a;
    }

    public void a(int i) {
        this.k = i;
    }

    public int f() {
        return this.k;
    }

    public void b(int i) {
        this.l = i;
    }

    public int g() {
        return this.l;
    }

    public void c(int i) {
        this.m = i;
    }

    public int h() {
        return this.m;
    }
}
