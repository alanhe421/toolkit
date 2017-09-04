package com.qrcomic.activity.reader;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseArray;
import com.qrcomic.a.h;
import com.qrcomic.a.j;
import com.qrcomic.activity.reader.a.d;
import com.qrcomic.activity.reader.b.e;
import com.qrcomic.activity.reader.startup.QRComicReadPageDirector;
import com.qrcomic.entity.ComicRecommendPageInfo;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.f;
import com.qrcomic.entity.i;
import com.qrcomic.manager.QRComicManager;
import com.qrcomic.manager.c;
import com.qrcomic.util.g;
import com.qrcomic.util.k;
import com.tencent.android.tpush.common.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: QRComicReadingState */
public class a extends Observable implements Callback {
    public static final String a = a.class.getSimpleName();
    public boolean A;
    public String B;
    public int C = -1;
    public String D = "";
    public int E = -1;
    public int F = -1;
    public int G = -1;
    public int H;
    public boolean I = false;
    public boolean J = false;
    public volatile boolean K = false;
    public ComicRecommendPageInfo L = null;
    protected Handler M = new k(Looper.getMainLooper(), this);
    public b N;
    protected c O = ((c) com.qrcomic.manager.b.a().b().a(3));
    public QRComicReadPageDirector P;
    public boolean Q = false;
    public long R = 0;
    public long S = 0;
    com.qrcomic.activity.reader.a.b T = new com.qrcomic.activity.reader.a.b(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void a(com.qrcomic.entity.a aVar, List<f> list, boolean z) {
            if (this.a.c(aVar)) {
                this.a.a(" onRequestSuccess ", Thread.currentThread().toString());
                this.a.d(aVar);
                this.a.a((List) list, z);
            }
        }
    };
    public AtomicBoolean U = new AtomicBoolean(false);
    private QRComicManager V = ((QRComicManager) com.qrcomic.manager.b.a().b().a(1));
    public long b;
    public long c;
    public long d;
    public int e;
    public int f;
    public int g;
    public boolean h = true;
    public com.qrcomic.entity.a i;
    public String j;
    public int k;
    public boolean l = false;
    public boolean m = false;
    public String n;
    public f o;
    public f p;
    public f q;
    public List<ComicSectionPicInfo> r;
    public List<ComicSectionPicInfo> s;
    public List<ComicSectionPicInfo> t;
    public List<String> u;
    public android.support.v4.util.a<String, Integer> v = new android.support.v4.util.a();
    public SparseArray<f> w;
    public android.support.v4.util.a<String, Integer> x = new android.support.v4.util.a();
    public List<String> y = Collections.synchronizedList(new LinkedList());
    public boolean z;

    /* compiled from: QRComicReadingState */
    private interface a {
        void a();

        void a(f fVar, List<ComicSectionPicInfo> list, boolean z);
    }

    /* compiled from: QRComicReadingState */
    public static class b {
        boolean a = false;
        int b;
        String c;
        f d = null;
        List<ComicSectionPicInfo> e = null;
    }

    public void a(ComicSectionPicInfo comicSectionPicInfo) {
        setChanged();
        Object obj = new Object[2];
        obj[0] = Integer.valueOf(9);
        obj[1] = new Object[]{comicSectionPicInfo};
        notifyObservers(obj);
    }

    public void a() {
        setChanged();
        notifyObservers(new Object[]{Integer.valueOf(7), new Object[0]});
        if (g.a()) {
            g.a("dataTime", g.d, "totalConsumedTime = " + this.g + " , ssoTime = " + this.e + " , picTime = " + this.f);
        }
    }

    public void a(h hVar, QRComicManager qRComicManager, com.qrcomic.manager.a aVar, boolean z) {
        this.b = System.currentTimeMillis();
        this.J = a(this.j);
        if (this.P == null) {
            this.P = new QRComicReadPageDirector(hVar, qRComicManager, aVar);
            this.P.d(this, z);
        }
    }

    public boolean a(com.qrcomic.entity.a aVar) {
        if (aVar == null) {
            if (!g.a()) {
                return false;
            }
            String str;
            String str2 = "comic_reader_startup";
            String str3 = g.d;
            if (("isValidComic : " + aVar) == null) {
                str = "reqComic == null";
            } else {
                str = "reqComic.sectionIdListAll == null";
            }
            g.a(str2, str3, str);
            return false;
        } else if (aVar.y != 0) {
            boolean z;
            if (g.a()) {
                g.a("comic_reader_startup", g.d, "isValidComic : comic is downshelf");
            }
            setChanged();
            if (aVar.y == 1004) {
                z = true;
            } else if (aVar.y == 1005) {
                z = false;
            } else {
                z = false;
            }
            Object obj = new Object[2];
            obj[0] = Integer.valueOf(5);
            obj[1] = new Object[]{Integer.valueOf(aVar.y), aVar.x, Boolean.valueOf(z)};
            notifyObservers(obj);
            return false;
        } else if (aVar.a() == null) {
            return false;
        } else {
            this.i = aVar;
            notifyObservers(new Object[]{Integer.valueOf(16), new Object[0]});
            return true;
        }
    }

    public void b() {
        this.u = this.i.a();
        if (this.v == null) {
            this.v = new android.support.v4.util.a();
        }
        int size = this.u.size();
        for (int i = 0; i < size; i++) {
            this.v.put(this.u.get(i), Integer.valueOf(i));
            if (this.j.equals(this.u.get(i))) {
                this.E = i;
            }
        }
        if (this.E == -1) {
            this.j = (String) this.u.get(0);
            this.E = 0;
        }
        this.F = this.E;
        this.G = this.E;
    }

    public boolean c() {
        this.u = this.i.a();
        if (this.v == null) {
            this.v = new android.support.v4.util.a();
        }
        int i = this.E;
        if (TextUtils.isEmpty(this.j) && this.u != null && this.u.size() > 0) {
            this.j = (String) this.u.get(0);
            this.E = 0;
            i = 0;
        }
        int size = this.u.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.v.put(this.u.get(i2), Integer.valueOf(i2));
            if (this.j.equals(this.u.get(i2))) {
                this.E = i2;
            }
        }
        this.F = this.E;
        this.G = this.E;
        if (i != this.E) {
            return false;
        }
        return true;
    }

    public void a(List<f> list, boolean z) {
        if (list != null && list.size() > 0) {
            if (this.w == null) {
                this.w = new SparseArray();
            }
            for (f fVar : list) {
                f fVar2;
                this.w.put(fVar2.f, fVar2);
                if (z) {
                    int i = fVar2.f;
                    if (i == this.E) {
                        this.o = (f) this.w.get(i);
                        this.o.t = this.k;
                        if (!a(this.o) && this.k == 0) {
                            Object obj;
                            if (b(this.o)) {
                                this.o.t = 1;
                                setChanged();
                                obj = new Object[2];
                                obj[0] = Integer.valueOf(1);
                                obj[1] = new Object[]{null, Integer.valueOf(1)};
                                notifyObservers(obj);
                            } else {
                                setChanged();
                                obj = new Object[2];
                                obj[0] = Integer.valueOf(12);
                                obj[1] = new Object[]{null, Integer.valueOf(1)};
                                notifyObservers(obj);
                            }
                        }
                    } else if (i == this.E - 1) {
                        fVar2 = (f) this.w.get(i);
                        if (!this.J || a(fVar2.b)) {
                            this.p = (f) this.w.get(i);
                        }
                    } else if (i == this.E + 1) {
                        fVar2 = (f) this.w.get(i);
                        if (!this.J || a(fVar2.b)) {
                            this.q = (f) this.w.get(i);
                        }
                    }
                }
            }
            if (z) {
                e.b(3, this);
            }
        }
    }

    public boolean a(List<f> list) {
        if (list == null || list.size() <= 0) {
            return true;
        }
        if (this.w == null) {
            this.w = new SparseArray();
        }
        boolean z = true;
        for (f fVar : list) {
            boolean z2;
            this.w.put(fVar.f, fVar);
            int i = fVar.f;
            if (i == this.E) {
                this.o = (f) this.w.get(i);
                this.o.t = this.k;
                if (!a(this.o) && this.k == 0) {
                    Object obj;
                    if (b(this.o)) {
                        this.o.t = 1;
                        setChanged();
                        obj = new Object[2];
                        obj[0] = Integer.valueOf(1);
                        obj[1] = new Object[]{null, Integer.valueOf(1)};
                        notifyObservers(obj);
                    } else {
                        setChanged();
                        obj = new Object[2];
                        obj[0] = Integer.valueOf(12);
                        obj[1] = new Object[]{null, Integer.valueOf(1)};
                        notifyObservers(obj);
                    }
                    z2 = false;
                }
                z2 = z;
            } else if (i == this.E - 1) {
                this.p = (f) this.w.get(i);
                z2 = z;
            } else {
                if (i == this.E + 1) {
                    this.q = (f) this.w.get(i);
                }
                z2 = z;
            }
            z = z2;
        }
        return z;
    }

    public boolean a(f fVar) {
        int i = 1;
        if (fVar == null) {
            return false;
        }
        if (this.z) {
            return true;
        }
        if (g.a()) {
            g.a(a, g.d, "话别ID " + fVar.b + ", 话别索引 " + fVar.f + ", 对应的用户身份相关的话别权限为null,但是该话别若是下载过的,那就没关系了");
        }
        if ((this.i == null || this.i.f != 2) && fVar.l != 2 && ((this.i == null || !this.i.d()) && ((this.i == null || !this.i.b()) && ((this.y == null || !this.y.contains(fVar.b)) && ((this.i == null || !this.i.c()) && !fVar.b()))))) {
            i = 0;
        }
        boolean z = i | false;
        if (!g.a()) {
            return z;
        }
        a(a, "   这个话别观看状态为： " + z);
        return z;
    }

    public boolean b(f fVar) {
        boolean z = true;
        if (fVar == null || this.z) {
            return false;
        }
        if (g.a()) {
            g.a(a, g.d, "话别ID " + fVar.b + ", 话别索引 " + fVar.f + ", 对应的用户身份相关的话别权限为null");
        }
        if (fVar.l != 1) {
            z = false;
        }
        return z;
    }

    public boolean a(int i) {
        if (this.u == null || i >= this.u.size()) {
            return false;
        }
        return a((String) this.u.get(i));
    }

    public boolean a(String str) {
        i a = this.V.a(this.n, str, com.qrcomic.manager.b.a().b().a());
        return a != null && a.d == 104;
    }

    public boolean c(f fVar) {
        return fVar != null && fVar.s && fVar.r != null && fVar.r.d == 104;
    }

    public boolean d(f fVar) {
        if (fVar == null) {
            return false;
        }
        if (a(fVar) || (this.A && b(fVar))) {
            return true;
        }
        return false;
    }

    public f b(String str) {
        if (!this.v.containsKey(str)) {
            return null;
        }
        return (f) this.w.get(((Integer) this.v.get(str)).intValue());
    }

    public int d() {
        int i = 0;
        if (this.w == null || this.w.size() == 0) {
            return 0;
        }
        int i2 = Constants.ERRORCODE_UNKNOWN;
        while (i < this.w.size()) {
            if (this.w.keyAt(i) < i2) {
                i2 = this.w.keyAt(i);
            }
            i++;
        }
        return i2;
    }

    public int e() {
        int i = 0;
        if (this.w == null || this.w.size() == 0) {
            return 0;
        }
        int i2 = 0;
        while (i < this.w.size()) {
            if (this.w.keyAt(i) > i2) {
                i2 = this.w.keyAt(i);
            }
            i++;
        }
        return i2;
    }

    public void f() {
        if (this.o == null || this.r == null || this.r.size() <= 0) {
            if (g.a()) {
                g.a(a, g.d, "currentSection of comicPicInfoList is null");
            }
            setChanged();
            notifyObservers(new Object[]{Integer.valueOf(10), new Object[0]});
            return;
        }
        this.c = System.currentTimeMillis();
        this.e = (int) (this.c - this.b);
        for (ComicSectionPicInfo comicSectionPicInfo : this.r) {
            if (this.B.equals(comicSectionPicInfo.picId)) {
                d(comicSectionPicInfo.index);
                break;
            }
        }
        if (this.C == -1) {
            if (g.a()) {
                g.a(a, g.d, "invalid picId :" + this.B);
            }
            d(0);
            this.B = ((ComicSectionPicInfo) this.r.get(0)).picId;
        }
        if (this.C < 0 || this.C >= this.r.size()) {
            if (g.a()) {
                g.a(a, g.d, "currentPicIndex is invalid value, currentPicIndex = " + this.C + ", totalPicNum in currentSection =" + this.r.size());
            }
            setChanged();
            notifyObservers(new Object[]{Integer.valueOf(10), new Object[0]});
            return;
        }
        e.b(4, this);
    }

    public void g() {
        try {
            if (this.o == null || this.r == null || this.r.size() <= 0) {
                setChanged();
                notifyObservers(new Object[]{Integer.valueOf(10), new Object[0]});
                if (g.a()) {
                    g.a(a, g.d, "currentSection of comicPicInfoList is null");
                    return;
                }
                return;
            }
            this.c = System.currentTimeMillis();
            this.e = (int) (this.c - this.b);
            this.f = 0;
            this.g = this.e;
            if (TextUtils.isEmpty(this.B)) {
                this.B = ((ComicSectionPicInfo) this.r.get(0)).picId;
            }
            for (ComicSectionPicInfo comicSectionPicInfo : this.r) {
                if (this.B.equals(comicSectionPicInfo.picId)) {
                    d(comicSectionPicInfo.index);
                    break;
                }
            }
            if (this.C < 0) {
                if (g.a()) {
                    g.a(a, g.d, "invalid picId :" + this.B);
                }
                d(0);
                this.B = ((ComicSectionPicInfo) this.r.get(0)).picId;
            }
            if (this.C < 0 || this.C >= this.r.size()) {
                setChanged();
                notifyObservers(new Object[]{Integer.valueOf(10), new Object[0]});
                if (g.a()) {
                    g.a(a, g.d, "currentPicIndex is invalid value, currentPicIndex = " + this.C + ", totalPicNum in currentSection =" + this.r.size());
                    return;
                }
                return;
            }
            boolean z;
            a();
            this.S = System.currentTimeMillis();
            if (g.a()) {
                g.a(a, g.d, "cost time = " + (this.S - this.R));
            }
            if (this.p != null) {
                if (this.C == 0) {
                    z = true;
                } else {
                    z = false;
                }
                Message.obtain(this.M, 0, new Object[]{this.p, Integer.valueOf(0), Boolean.valueOf(z)}).sendToTarget();
            }
            if (this.q != null) {
                if (this.C == this.r.size() - 1) {
                    z = true;
                } else {
                    z = false;
                }
                Message.obtain(this.M, 1, new Object[]{this.q, Integer.valueOf(1), Boolean.valueOf(z)}).sendToTarget();
            }
        } catch (Exception e) {
            setChanged();
            notifyObservers(new Object[]{Integer.valueOf(14), new Object[0]});
        }
    }

    public void a(Throwable th) {
        setChanged();
        Object obj = new Object[2];
        obj[0] = Integer.valueOf(14);
        obj[1] = new Object[]{th};
        notifyObservers(obj);
    }

    private void n() {
        if (this.p.e < 2 && this.p.f > 0) {
            this.O.a(this.n, (String) this.u.get(this.p.f - 1), Boolean.valueOf(false), this.J, new d(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(List<ComicSectionPicInfo> list, String str, String str2) {
                    if (this.a.w != null) {
                        if (this.a.d((f) this.a.w.get(this.a.p.f - 1))) {
                            this.a.setChanged();
                            a aVar = this.a;
                            Object obj = new Object[2];
                            obj[0] = Integer.valueOf(0);
                            obj[1] = new Object[]{list};
                            aVar.notifyObservers(obj);
                        }
                    }
                }

                public void a(String str, String str2) {
                }
            });
        }
    }

    public boolean b(int i) {
        switch (i) {
            case 0:
                if (this.p.f != this.E - 1) {
                    return false;
                }
                return true;
            case 1:
                if (this.q.f != this.E + 1) {
                    return false;
                }
                return true;
            default:
                return false;
        }
    }

    public void a(final c cVar, final com.qrcomic.activity.reader.a.f fVar) {
        if (g.a()) {
            g.a(a, g.d, " 准备开始拉取PRE上一话。。。。不一定马上回来 " + cVar.toString() + "  current " + this.E);
        }
        Object obj;
        if (this.E <= 0) {
            setChanged();
            obj = new Object[2];
            obj[0] = Integer.valueOf(8);
            obj[1] = new Object[]{Integer.valueOf(0)};
            notifyObservers(obj);
        } else if (!this.m && !a((String) this.u.get(this.E - 1)) && (this.p == null || this.s == null || !b(0))) {
            setChanged();
            obj = new Object[2];
            obj[0] = Integer.valueOf(3);
            obj[1] = new Object[]{fVar};
            notifyObservers(obj);
        } else if (this.p == null || this.s == null || !b(0)) {
            this.M.post(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    h b = com.qrcomic.manager.b.a().b();
                    b.f().d().a(b.b(), "加载中，请稍候...", 0);
                }
            });
            a(0, new a(this) {
                final /* synthetic */ a c;

                public void a(f fVar, List<ComicSectionPicInfo> list, boolean z) {
                    this.c.p = fVar;
                    this.c.s = list;
                    this.c.a(0, cVar, fVar, false);
                }

                public void a() {
                    this.c.setChanged();
                    a aVar = this.c;
                    Object obj = new Object[2];
                    obj[0] = Integer.valueOf(3);
                    obj[1] = new Object[]{fVar, Integer.valueOf(0)};
                    aVar.notifyObservers(obj);
                }
            });
        } else {
            if (!cVar.c()) {
                n();
            }
            a(0, cVar, fVar, false);
        }
    }

    public void b(final c cVar, final com.qrcomic.activity.reader.a.f fVar) {
        if (g.a()) {
            g.a(a, g.d, " 准备开始拉取NEXT下一话。。。。不一定马上回来 " + cVar.toString() + "  current " + this.E);
        }
        Object obj;
        if (this.J || this.E < this.u.size() - 1) {
            if (!this.m) {
                if (this.E >= this.u.size() - 1) {
                    setChanged();
                    obj = new Object[2];
                    obj[0] = Integer.valueOf(3);
                    obj[1] = new Object[]{fVar};
                    notifyObservers(obj);
                    return;
                } else if (!a((String) this.u.get(this.E + 1)) && (this.q == null || this.t == null || !b(1))) {
                    setChanged();
                    obj = new Object[2];
                    obj[0] = Integer.valueOf(3);
                    obj[1] = new Object[]{fVar};
                    notifyObservers(obj);
                    return;
                }
            }
            if (this.q == null || this.t == null || !b(1)) {
                this.M.post(new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        h b = com.qrcomic.manager.b.a().b();
                        b.f().d().a(b.b(), "加载中，请稍候...", 0);
                    }
                });
                a(1, new a(this) {
                    final /* synthetic */ a c;

                    public void a(f fVar, List<ComicSectionPicInfo> list, boolean z) {
                        this.c.q = fVar;
                        this.c.t = list;
                        this.c.a(1, cVar, fVar, z);
                    }

                    public void a() {
                        this.c.setChanged();
                        a aVar = this.c;
                        Object obj = new Object[2];
                        obj[0] = Integer.valueOf(3);
                        obj[1] = new Object[]{fVar, Integer.valueOf(1)};
                        aVar.notifyObservers(obj);
                    }
                });
                return;
            }
            a(1, cVar, fVar, false);
            return;
        }
        setChanged();
        obj = new Object[2];
        obj[0] = Integer.valueOf(8);
        obj[1] = new Object[]{Integer.valueOf(1)};
        notifyObservers(obj);
    }

    private void a(int i, c cVar, com.qrcomic.activity.reader.a.f fVar, boolean z) {
        if (g.a()) {
            g.a(a, g.d, " 有漫画数据回来了。。。。开始处理中。。。。");
        }
        f fVar2 = null;
        if (i == 0) {
            if (this.p != null && this.s != null) {
                fVar2 = this.p;
            } else {
                return;
            }
        } else if (i == 1) {
            if (this.q != null && this.t != null) {
                fVar2 = this.q;
            } else {
                return;
            }
        }
        Object obj;
        if (a(fVar2)) {
            setChanged();
            obj = new Object[2];
            obj[0] = Integer.valueOf(2);
            obj[1] = new Object[]{Integer.valueOf(i), cVar, fVar, Boolean.valueOf(z)};
            notifyObservers(obj);
        } else if (b(fVar2)) {
            setChanged();
            Object obj2 = new Object[2];
            obj2[0] = Integer.valueOf(1);
            obj2[1] = new Object[]{fVar, Integer.valueOf(0)};
            notifyObservers(obj2);
            if (this.A) {
                if (!(fVar2 == null || fVar2.t == 2 || this.i.g != 1)) {
                    if (g.a()) {
                        g.a(a, g.d, "处理当前话别的时候增加购买逻辑");
                    }
                    fVar2.t = 1;
                    if (!(this.N == null || this.N.isInterrupted())) {
                        this.N.a(fVar2.b);
                    }
                }
                setChanged();
                obj = new Object[2];
                obj[0] = Integer.valueOf(2);
                obj[1] = new Object[]{Integer.valueOf(i), cVar, fVar, Boolean.valueOf(z)};
                notifyObservers(obj);
            } else if (fVar2 != null) {
                fVar2.t = 1;
                if (cVar.d()) {
                    setChanged();
                    obj2 = new Object[2];
                    obj2[0] = Integer.valueOf(11);
                    obj2[1] = new Object[]{Integer.valueOf(i), fVar2, Boolean.valueOf(z)};
                    notifyObservers(obj2);
                }
            }
        } else {
            setChanged();
            obj = new Object[2];
            obj[0] = Integer.valueOf(12);
            obj[1] = new Object[]{fVar, Integer.valueOf(0)};
            notifyObservers(obj);
        }
    }

    public void h() {
        setChanged();
        Object obj = new Object[2];
        obj[0] = Integer.valueOf(12);
        obj[1] = new Object[]{null, Integer.valueOf(0)};
        notifyObservers(obj);
    }

    private void a(int i, final a aVar) {
        int i2;
        int min;
        final b bVar = new b();
        if (i == 0) {
            bVar.b = this.E - 1;
            bVar.e = this.s;
            i2 = 2;
            min = Math.min(bVar.b, 3);
        } else {
            boolean z;
            bVar.b = this.E + 1;
            bVar.e = this.t;
            if (bVar.b >= this.u.size()) {
                z = true;
            } else {
                z = false;
            }
            bVar.a = z;
            min = Math.min(3, this.u.size() - bVar.b);
            i2 = 1;
        }
        if (!bVar.a) {
            bVar.c = (String) this.u.get(bVar.b);
            if (this.w.get(bVar.b) != null) {
                bVar.d = (f) this.w.get(bVar.b);
            }
        }
        if (bVar.d == null || bVar.a) {
            this.O.a(this.i, bVar.c, bVar.b, min, i2, new com.qrcomic.activity.reader.a.b(this) {
                final /* synthetic */ a c;

                public void a(com.qrcomic.entity.a aVar, List<f> list, boolean z) {
                    if (!this.c.c(aVar)) {
                        return;
                    }
                    if (aVar != null && list != null && list.size() > 0) {
                        if (!bVar.a) {
                            for (f fVar : list) {
                                if (fVar.f == bVar.b) {
                                    bVar.d = fVar;
                                    break;
                                }
                            }
                        }
                        this.c.b(aVar);
                        bVar.d = (f) list.get(0);
                        if (bVar.d == null) {
                            if (g.a()) {
                                g.a(a.a, g.d, "reqData.reqSection = null");
                            }
                            aVar.a();
                            return;
                        }
                        this.c.w.put(bVar.d.f, bVar.d);
                        if (bVar.e == null) {
                            this.c.O.a(this.c.n, bVar.d.b, Boolean.valueOf(true), this.c.J, new d(this) {
                                final /* synthetic */ AnonymousClass9 a;

                                {
                                    this.a = r1;
                                }

                                public void a(List<ComicSectionPicInfo> list, String str, String str2) {
                                    aVar.a(bVar.d, list, bVar.a);
                                }

                                public void a(String str, String str2) {
                                    aVar.a();
                                }
                            });
                        } else {
                            aVar.a(bVar.d, bVar.e, bVar.a);
                        }
                    } else if (bVar.a) {
                        if (g.a()) {
                            g.a(a.a, g.d, "section is null, section Index is :" + (bVar.b - 1));
                        }
                        this.c.J = false;
                        this.c.setChanged();
                        a aVar2 = this.c;
                        Object obj = new Object[2];
                        obj[0] = Integer.valueOf(8);
                        obj[1] = new Object[]{Integer.valueOf(1), Boolean.valueOf(true)};
                        aVar2.notifyObservers(obj);
                    } else {
                        aVar.a();
                    }
                }
            }, bVar.b, false, this.J);
        } else {
            this.O.a(this.n, bVar.c, Boolean.valueOf(true), this.J, new d(this) {
                final /* synthetic */ a c;

                public void a(List<ComicSectionPicInfo> list, String str, String str2) {
                    aVar.a(bVar.d, list, bVar.a);
                }

                public void a(String str, String str2) {
                    aVar.a();
                }
            });
        }
    }

    public void b(com.qrcomic.entity.a aVar) {
        int i = 0;
        if (aVar != null) {
            this.i = aVar;
            this.J = false;
            this.u = aVar.o;
            int indexOf = this.u.indexOf(this.o.b);
            if (indexOf >= 0) {
                this.E = indexOf;
                this.D = this.o.b;
                this.o.f = this.E;
            }
            this.w.clear();
            this.w.put(this.o.f, this.o);
            this.v.clear();
            indexOf = this.u.size();
            while (i < indexOf) {
                this.v.put(this.u.get(i), Integer.valueOf(i));
                i++;
            }
        }
    }

    public boolean handleMessage(Message message) {
        final Object[] objArr;
        String str;
        switch (message.what) {
            case 0:
                if (message.obj instanceof Object[]) {
                    objArr = (Object[]) message.obj;
                    f fVar = (f) objArr[0];
                    str = fVar.b;
                    final int intValue = ((Integer) objArr[1]).intValue();
                    if (this.m || a(str) || a(fVar)) {
                        this.O.a(this.n, str, Boolean.valueOf(false), this.J, new d(this) {
                            final /* synthetic */ a c;

                            public void a(List<ComicSectionPicInfo> list, String str, String str2) {
                                this.c.s = list;
                                boolean a = this.c.a(this.c.p);
                                if (this.c.d(this.c.p)) {
                                    if (this.c.A && !a) {
                                        this.c.p.t = 1;
                                    }
                                    this.c.setChanged();
                                    a aVar;
                                    Object obj;
                                    if (objArr.length >= 3) {
                                        aVar = this.c;
                                        obj = new Object[2];
                                        obj[0] = Integer.valueOf(4);
                                        obj[1] = new Object[]{Integer.valueOf(intValue), objArr[2]};
                                        aVar.notifyObservers(obj);
                                        return;
                                    }
                                    aVar = this.c;
                                    obj = new Object[2];
                                    obj[0] = Integer.valueOf(4);
                                    obj[1] = new Object[]{Integer.valueOf(intValue)};
                                    aVar.notifyObservers(obj);
                                }
                            }

                            public void a(String str, String str2) {
                            }
                        });
                        break;
                    }
                }
                break;
            case 1:
                objArr = (Object[]) message.obj;
                final f fVar2 = (f) objArr[0];
                str = fVar2.b;
                final int intValue2 = ((Integer) objArr[1]).intValue();
                if (this.m || a(str) || a(fVar2)) {
                    this.O.a(this.n, str, Boolean.valueOf(false), this.J, new d(this) {
                        final /* synthetic */ a d;

                        public void a(List<ComicSectionPicInfo> list, String str, String str2) {
                            this.d.t = list;
                            boolean a = this.d.a(this.d.q);
                            if (this.d.d(this.d.q)) {
                                if (this.d.A && !a) {
                                    if (g.a()) {
                                        g.a(a.a, g.d, "预加载下一话图片信息的时候增加购买逻辑");
                                    }
                                    if (fVar2.t != 2 && this.d.i.g == 1) {
                                        fVar2.t = 1;
                                        if (!(this.d.N == null || this.d.N.isInterrupted())) {
                                            this.d.N.a(fVar2.b);
                                        }
                                    }
                                }
                                this.d.setChanged();
                                a aVar;
                                Object obj;
                                if (objArr.length >= 3) {
                                    aVar = this.d;
                                    obj = new Object[2];
                                    obj[0] = Integer.valueOf(4);
                                    obj[1] = new Object[]{Integer.valueOf(intValue2), objArr[2]};
                                    aVar.notifyObservers(obj);
                                    return;
                                }
                                aVar = this.d;
                                obj = new Object[2];
                                obj[0] = Integer.valueOf(4);
                                obj[1] = new Object[]{Integer.valueOf(intValue2)};
                                aVar.notifyObservers(obj);
                            }
                        }

                        public void a(String str, String str2) {
                        }
                    });
                    break;
                }
            case 2:
                this.O.a(this, d(), this.J, this.T);
                break;
            case 3:
                this.O.b(this, e(), this.J, this.T);
                if (g.a()) {
                    g.a(a, g.d, "开始预加载下几话的话别信息 sectionIndex = " + this.E);
                    break;
                }
                break;
        }
        return true;
    }

    public boolean c(final com.qrcomic.entity.a aVar) {
        if (aVar == null || aVar.y == 0) {
            return true;
        }
        boolean z;
        if (g.a()) {
            g.a("comic_reader_startup", g.d, "isValidComic : comic is downshelf");
        }
        if (aVar.y == 1004) {
            z = true;
        } else if (aVar.y == 1005) {
            z = false;
        } else {
            z = false;
        }
        setChanged();
        Object obj = new Object[2];
        obj[0] = Integer.valueOf(5);
        obj[1] = new Object[]{Integer.valueOf(aVar.y), aVar.x, Boolean.valueOf(z)};
        notifyObservers(obj);
        if (aVar.y != 1004) {
            return false;
        }
        j.a().a(new com.qrcomic.a.d(this) {
            final /* synthetic */ a b;

            public void run() {
                this.b.V.a(aVar.a, true);
            }
        }, null);
        return false;
    }

    private void d(com.qrcomic.entity.a aVar) {
        a("CHECK NEW ", "comic = " + aVar);
        if (aVar != null && aVar.a() != null) {
            List a = aVar.a();
            if (a != null && a.size() > this.u.size()) {
                a("CHECK NEW ", "新的comic数据和内存的数据id列表长度不一样");
                if (this.u.size() > 0) {
                    String str = (String) this.u.get(this.u.size() - 1);
                    int lastIndexOf = a.lastIndexOf(str);
                    a("CHECK NEW ", "内存中最后一个id=" + str + "  lastIndex=" + lastIndexOf);
                    if (lastIndexOf > 0) {
                        List arrayList = new ArrayList();
                        arrayList.addAll(a.subList(lastIndexOf + 1, a.size()));
                        for (int i = 0; i < arrayList.size(); i++) {
                            a("CHECK NEW ", "把多余的数据添加到内存中。。。检查是否包含 ： " + this.u.contains(arrayList.get(i)));
                            if (!this.u.contains(arrayList.get(i))) {
                                this.u.add(arrayList.get(i));
                            }
                        }
                    }
                }
            }
        }
    }

    public void c(int i) {
        if (i == 0) {
            if (this.p != null && this.s != null) {
                this.q = this.o;
                this.t = this.r;
                this.o = this.p;
                this.r = this.s;
                this.E = this.o.f;
                this.D = this.o.b;
                d(this.r.size() - 1);
                if (this.C >= 0) {
                    this.B = ((ComicSectionPicInfo) this.r.get(this.C)).picId;
                }
                this.p = null;
                this.s = null;
                if (this.E >= 1) {
                    this.p = (f) this.w.get(this.E - 1);
                    if (this.p != null) {
                        Message.obtain(this.M, 0, new Object[]{this.p, Integer.valueOf(i)}).sendToTarget();
                    }
                }
                Message.obtain(this.M, 2).sendToTarget();
            }
        } else if (this.q != null && this.t != null) {
            this.p = this.o;
            this.s = this.r;
            this.o = this.q;
            this.r = this.t;
            this.E = this.o.f;
            this.D = this.o.b;
            d(0);
            if (this.r.size() > 0) {
                this.B = ((ComicSectionPicInfo) this.r.get(this.C)).picId;
            }
            this.q = null;
            this.t = null;
            if (this.E < this.u.size() - 1) {
                this.q = (f) this.w.get(this.E + 1);
                if (this.q != null) {
                    Message.obtain(this.M, 1, new Object[]{this.q, Integer.valueOf(i)}).sendToTarget();
                }
            }
            if (this.E > this.G) {
                this.G = this.E;
            }
            Message.obtain(this.M, 3).sendToTarget();
        }
    }

    public ComicSectionPicInfo i() {
        if (this.L == null) {
            return null;
        }
        ComicSectionPicInfo comicSectionPicInfo = new ComicSectionPicInfo();
        comicSectionPicInfo.mComicRecommendPageInfo = this.L;
        return comicSectionPicInfo;
    }

    public void j() {
        setChanged();
        notifyObservers(new Object[]{Integer.valueOf(15), new Object[0]});
    }

    public void k() {
        if (this.L == null && this.u != null && this.E + 1 >= this.u.size()) {
            l();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void l() {
        /*
        r5 = this;
        r0 = r5.V;
        r1 = r5.n;
        r0 = r0.f(r1);
        if (r0 == 0) goto L_0x0026;
    L_0x000a:
        r1 = r5.U;
        monitor-enter(r1);
        r2 = r5.U;	 Catch:{ all -> 0x0047 }
        r2 = r2.get();	 Catch:{ all -> 0x0047 }
        if (r2 == 0) goto L_0x0027;
    L_0x0015:
        r0 = com.qrcomic.util.g.a();	 Catch:{ all -> 0x0047 }
        if (r0 == 0) goto L_0x0025;
    L_0x001b:
        r0 = a;	 Catch:{ all -> 0x0047 }
        r2 = com.qrcomic.util.g.d;	 Catch:{ all -> 0x0047 }
        r3 = "in process of loading ComciRecommendPageInfo";
        com.qrcomic.util.g.a(r0, r2, r3);	 Catch:{ all -> 0x0047 }
    L_0x0025:
        monitor-exit(r1);	 Catch:{ all -> 0x0047 }
    L_0x0026:
        return;
    L_0x0027:
        r2 = r5.U;	 Catch:{ all -> 0x0047 }
        r3 = 1;
        r2.set(r3);	 Catch:{ all -> 0x0047 }
        r2 = com.qrcomic.util.g.a();	 Catch:{ all -> 0x0047 }
        if (r2 == 0) goto L_0x003d;
    L_0x0033:
        r2 = a;	 Catch:{ all -> 0x0047 }
        r3 = com.qrcomic.util.g.d;	 Catch:{ all -> 0x0047 }
        r4 = "getComicRecommendPageInfo";
        com.qrcomic.util.g.a(r2, r3, r4);	 Catch:{ all -> 0x0047 }
    L_0x003d:
        monitor-exit(r1);	 Catch:{ all -> 0x0047 }
        r1 = new com.qrcomic.activity.reader.a$4;
        r1.<init>(r5);
        r0.b(r1);
        goto L_0x0026;
    L_0x0047:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0047 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qrcomic.activity.reader.a.l():void");
    }

    public void m() {
        this.T = null;
        this.P = null;
        this.O.a();
        if (this.N != null) {
            this.N.a();
        }
    }

    public void d(int i) {
        this.C = i;
        if (!g.a()) {
            return;
        }
        if (this.o == null || this.r == null || i < 0 || i >= this.r.size()) {
            if (g.a()) {
                g.b(a, g.d, "onPageChanged: error data");
            }
        } else if (g.a()) {
            g.b(a, g.d, "onPageChanged: " + this.n + "-" + this.o.b + "-" + ((ComicSectionPicInfo) this.r.get(this.C)).picId);
        }
    }

    private void a(String str, String str2) {
        if (g.a()) {
            g.a(a, g.d, str + "  ::  " + str2);
        }
    }
}
