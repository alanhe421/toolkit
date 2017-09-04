package com.qrcomic.a;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.widget.Toast;
import com.qqcomic.bitmaphelper.b;
import com.qrcomic.downloader.a.d;
import com.qrcomic.downloader.a.d.a;
import com.qrcomic.entity.g;
import com.qrcomic.manager.QRComicManager;
import com.qrcomic.manager.c;
import com.qrcomic.util.f;
import com.qrcomic.util.j;
import java.io.File;
import java.util.List;
import java.util.Vector;
import org.greenrobot.greendao.database.Database;

/* compiled from: QRComicPluginRuntime */
public class h {
    public static boolean a = true;
    public static boolean b = false;
    public static boolean c = true;
    public static int e = 0;
    private static final String k = h.class.getSimpleName();
    public com.qrcomic.c.h d;
    public final List<a> f = new Vector();
    public final List<a> g = new Vector();
    public volatile d<a, b> h;
    public volatile d<a, b> i;
    private com.qrcomic.e.a[] j = new com.qrcomic.e.a[2];
    private com.qrcomic.manager.d[] l = new com.qrcomic.manager.d[9];
    private String m = null;
    private Context n;
    private int[] o = new int[2];
    private g p;
    private com.qrcomic.entity.h q;
    private Database r;

    public String a() {
        if (this.d == null || this.d.a() == null) {
            return "";
        }
        return this.d.a().b(this.n);
    }

    public Context b() {
        return this.n;
    }

    public com.qrcomic.manager.d a(int i) {
        com.qrcomic.manager.d dVar = i < this.l.length ? this.l[i] : null;
        if (dVar == null) {
            synchronized (this.l) {
                if (i < this.l.length) {
                    dVar = this.l[i];
                } else {
                    dVar = null;
                }
                if (dVar == null) {
                    switch (i) {
                        case 1:
                            dVar = new QRComicManager(this);
                            break;
                        case 3:
                            dVar = new c(this);
                            break;
                    }
                }
            }
            return dVar;
        }
        if (dVar != null) {
            a(i, dVar);
        }
        return dVar;
    }

    protected void a(int i, com.qrcomic.manager.d dVar) {
        if (this.l[i] == null) {
            this.l[i] = dVar;
        }
    }

    public synchronized void a(Context context, com.qrcomic.c.h hVar) throws Exception {
        this.d = (com.qrcomic.c.h) j.a((Object) hVar);
        this.n = (Context) j.a((Object) context);
        com.qrcomic.f.b.c(context);
        String str = f.b(context) + "db/comic-db";
        File file = new File(str);
        if (file.exists()) {
            file.setWritable(true);
        }
        try {
            if (this.q == null) {
                this.r = new com.qrcomic.b.a(context, str).getWritableDb();
                this.q = new g(this.r).a();
            }
            a(context);
        } catch (Throwable e) {
            e.printStackTrace();
            Toast.makeText(context, "请检查内存卡是否已满\n或者没有授予读写权限", 0).show();
            throw new RuntimeException(e);
        }
    }

    public void a(Context context) {
        b(context);
        j.a().a(new d(this) {
            final /* synthetic */ h a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.h();
                this.a.g();
            }
        }, null, false);
    }

    private void g() {
        if (!f.c(b())) {
        }
    }

    private void h() {
        b().getApplicationContext().getSharedPreferences("vip_comic_file", 4).edit().remove("restore_flag").remove("base_activity").remove("activity_task_id").remove("active_comid_id").commit();
    }

    public com.qrcomic.entity.h c() {
        return this.q;
    }

    public Database d() {
        return this.r;
    }

    public void a(a aVar) {
        a(aVar, false);
    }

    public void a(a aVar, boolean z) {
        if (z) {
            if (!this.g.contains(aVar)) {
                this.g.add(aVar);
            }
        } else if (!this.f.contains(aVar)) {
            this.f.add(aVar);
        }
    }

    public void b(a aVar) {
        this.f.remove(aVar);
        this.g.remove(aVar);
    }

    public void b(Context context) {
        if (this.h != null) {
            this.h.e();
        }
        if (this.i != null) {
            this.i.e();
        }
        long a = com.qrcomic.util.c.c.a(context);
        int i = 0;
        int min = Math.min(18874368, (int) (a / 4));
        if (VERSION.SDK_INT >= 11) {
            i = Math.min(21841920, (int) ((a - ((long) Math.max(min, 6291456))) / 4));
        }
        this.h = new d((long) Math.max(min, 6291456));
        this.i = new d((long) Math.max(i, 6291456));
        if (com.qrcomic.util.g.a()) {
            com.qrcomic.util.g.b(k, com.qrcomic.util.g.d, "init memory cache size:" + min);
        }
    }

    public com.qrcomic.e.a b(int i) {
        com.qrcomic.e.a aVar = this.j[i];
        if (aVar == null) {
            synchronized (this.j) {
                aVar = c(i);
                if (aVar != null) {
                    this.j[i] = aVar;
                }
            }
        }
        return aVar;
    }

    private com.qrcomic.e.a c(int i) {
        switch (i) {
            case 0:
                return new com.qrcomic.e.d(this);
            case 1:
                return new com.qrcomic.e.b(this);
            default:
                return null;
        }
    }

    public g e() {
        g gVar;
        synchronized (this) {
            if (this.p == null) {
                this.p = new g(this);
            }
            gVar = this.p;
        }
        return gVar;
    }

    public synchronized void a(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            if (str.equals(this.m)) {
                if (!z) {
                    this.m = null;
                }
            } else if (z) {
                this.m = str;
            }
        }
    }

    public boolean b(a aVar, boolean z) {
        if (z) {
            return this.g.contains(aVar);
        }
        return this.f.contains(aVar);
    }

    public com.qrcomic.c.h f() {
        return this.d;
    }

    public int[] c(Context context) {
        if (this.o[0] <= 0 || this.o[1] <= 0) {
            this.o = com.qrcomic.util.c.d.a(context);
        }
        return this.o;
    }
}
