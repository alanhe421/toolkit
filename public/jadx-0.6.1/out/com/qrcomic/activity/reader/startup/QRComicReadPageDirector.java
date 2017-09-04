package com.qrcomic.activity.reader.startup;

import android.os.Bundle;
import android.text.TextUtils;
import com.qrcomic.a.h;
import com.qrcomic.a.j;
import com.qrcomic.e.b.d;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.QRComicBuyReqInfo;
import com.qrcomic.entity.f;
import com.qrcomic.entity.l;
import com.qrcomic.entity.n;
import com.qrcomic.manager.QRComicManager;
import com.qrcomic.util.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import rx.e;

public class QRComicReadPageDirector {
    AtomicInteger a = new AtomicInteger(0);
    private h b;
    private QRComicManager c;
    private com.qrcomic.manager.a d;
    private c e;

    protected static class StartUpException extends Exception {
        public Object mInfo;

        public StartUpException(String str) {
            this(str, null);
        }

        public StartUpException(String str, Object obj) {
            super(str);
            this.mInfo = obj;
        }
    }

    public static class QueryComicAndSectionListInfoException extends StartUpException {
        public QueryComicAndSectionListInfoException(String str) {
            super(str);
        }

        public QueryComicAndSectionListInfoException(String str, Object obj) {
            super(str, obj);
        }
    }

    public static class QuerySectionListInfoException extends StartUpException {
        public QuerySectionListInfoException(String str) {
            super(str);
        }

        public QuerySectionListInfoException(String str, Object obj) {
            super(str, obj);
        }
    }

    public static class QuerySectionPicInfoException extends StartUpException {
        public QuerySectionPicInfoException(String str) {
            super(str);
        }

        public QuerySectionPicInfoException(String str, Object obj) {
            super(str, obj);
        }
    }

    public static class QueryUserBuyInfoException extends StartUpException {
        public QueryUserBuyInfoException(String str) {
            super(str);
        }

        public QueryUserBuyInfoException(String str, Object obj) {
            super(str, obj);
        }
    }

    public static class a {
        public com.qrcomic.entity.a a;
        public List<f> b;
    }

    static class b {
        public List<ComicSectionPicInfo> a;
        public String b;
        public String c;

        b() {
        }
    }

    static class c {
        public a a;
        public d b;
        public b c;

        c() {
        }

        public String toString() {
            return "comic = " + this.a;
        }
    }

    public QRComicReadPageDirector(h hVar, QRComicManager qRComicManager, com.qrcomic.manager.a aVar) {
        this.b = hVar;
        this.c = qRComicManager;
        this.d = aVar;
    }

    public rx.a<d> a(final com.qrcomic.activity.reader.a aVar, final boolean z) {
        return rx.a.a(new rx.a.a<d>(this) {
            final /* synthetic */ QRComicReadPageDirector c;

            public /* synthetic */ void call(Object obj) {
                a((e) obj);
            }

            public void a(final e<? super d> eVar) {
                if (TextUtils.isEmpty(aVar.n)) {
                    eVar.onError(new QueryUserBuyInfoException("param comicId error , it is " + aVar.n));
                    return;
                }
                final int incrementAndGet = this.c.a.incrementAndGet();
                Bundle bundle = new Bundle();
                bundle.putInt("serial", incrementAndGet);
                ArrayList arrayList = new ArrayList();
                arrayList.add(new QRComicBuyReqInfo(aVar.n));
                this.c.b.a(new com.qrcomic.e.c(this) {
                    final /* synthetic */ AnonymousClass1 c;

                    public void a(Object obj) {
                        int i = -1;
                        if (g.a()) {
                            g.a("QRComicReadPageDirector", g.d, "onQueryUserBuyInfoSuccess, but data error is null , data is " + obj);
                        }
                        if (obj == null || !(obj instanceof d)) {
                            eVar.onError(new QueryUserBuyInfoException("onQueryUserBuyInfoSuccess , but data error", obj));
                            this.c.c.b.b((com.qrcomic.a.a) this);
                            return;
                        }
                        d dVar = (d) obj;
                        if (dVar.c != null) {
                            i = dVar.c.getInt("serial", -1);
                        }
                        if (incrementAndGet == i || dVar.c == null) {
                            this.c.c.b.b((com.qrcomic.a.a) this);
                            eVar.onNext(dVar);
                            eVar.onCompleted();
                            return;
                        }
                        eVar.onError(new QueryUserBuyInfoException("onQueryUserBuyInfoSuccess , but data error serialNum = " + incrementAndGet + ", tempSerialNum = " + i + ", userBuyInfoPac.transBundle = " + dVar.c, obj));
                        this.c.c.b.b((com.qrcomic.a.a) this);
                    }

                    public void b(Object obj) {
                        int i = -1;
                        if (obj == null || !(obj instanceof com.qrcomic.e.b.c)) {
                            eVar.onError(new QueryUserBuyInfoException("onQueryUserBuyInfoFailure , data is " + obj));
                            this.c.c.b.b((com.qrcomic.a.a) this);
                            if (g.a()) {
                                g.a("QRComicReadPageDirector", g.d, "onLoadPayedSectionsListenerFail, QueryUserBuyInfoError is " + obj);
                                return;
                            }
                            return;
                        }
                        com.qrcomic.e.b.c cVar = (com.qrcomic.e.b.c) obj;
                        if (cVar.c != null) {
                            i = cVar.c.getInt("serial", -1);
                        }
                        if (incrementAndGet == i || cVar.c == null) {
                            this.c.c.b.b((com.qrcomic.a.a) this);
                            eVar.onError(new QueryUserBuyInfoException("onQueryUserBuyInfoFailure", obj));
                            try {
                                if (g.a()) {
                                    StringBuilder stringBuilder = new StringBuilder();
                                    com.qrcomic.e.b.c cVar2 = (com.qrcomic.e.b.c) obj;
                                    stringBuilder.append("onQueryUserBuyInfoFailure, errorcode is " + cVar2.b + ", comicId is ");
                                    if (cVar2.a != null) {
                                        Iterator it = cVar2.a.iterator();
                                        while (it.hasNext()) {
                                            stringBuilder.append(((QRComicBuyReqInfo) it.next()).a + ",");
                                        }
                                        g.a("QRComicReadPageDirector", g.d, stringBuilder.toString());
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, true);
                this.c.c.a(arrayList, bundle, z | 0);
            }
        }).b(rx.e.d.b());
    }

    public void a(final com.qrcomic.activity.reader.a aVar, final ArrayList<QRComicBuyReqInfo> arrayList) {
        if (com.qrcomic.util.f.a(this.b.b()) && !aVar.l) {
            j.a().a(new com.qrcomic.a.d(this) {
                final /* synthetic */ QRComicReadPageDirector c;

                public void run() {
                    this.c.b.a(new com.qrcomic.e.c(this) {
                        final /* synthetic */ AnonymousClass12 a;

                        {
                            this.a = r1;
                        }

                        public void a(Object obj) {
                            super.a(obj);
                            if (g.a()) {
                                g.a("QRComicReadPageDirector", g.d, " onQueryUserBuyInfoSuccess 同步服务器的漫画 ..... 同步服务器付费信息");
                            }
                            if (obj != null && (obj instanceof d)) {
                                this.a.c.a(aVar, (d) obj);
                            }
                            this.a.c.b.b((com.qrcomic.a.a) this);
                        }

                        public void f(Object obj) {
                            super.f(obj);
                            if (g.a()) {
                                g.a("QRComicReadPageDirector", g.d, " onQueryCollectComicFailure 同步服务器的漫画 ..... 同步服务器付费信息失败");
                            }
                            this.a.c.b.b((com.qrcomic.a.a) this);
                        }
                    }, false);
                    this.c.c.a(arrayList, true);
                }
            }, null, false);
        }
    }

    public rx.a<List<f>> a(final com.qrcomic.activity.reader.a aVar) {
        return rx.a.a(new rx.a.a<List<f>>(this) {
            final /* synthetic */ QRComicReadPageDirector b;

            public /* synthetic */ void call(Object obj) {
                a((e) obj);
            }

            public void a(final e<? super List<f>> eVar) {
                Object obj;
                int i = 2;
                int i2 = aVar.E;
                int size = aVar.u.size();
                String str = aVar.j;
                if (aVar.J) {
                    if (i2 <= 0 || !aVar.a(i2 - 1)) {
                        i = 0;
                    }
                    if (i2 + 1 < size && aVar.a(i2 + 1)) {
                        i |= 1;
                    }
                } else {
                    if (i2 < 1) {
                        i = 0;
                    }
                    if (i2 < size - 1) {
                        i |= 1;
                    }
                }
                final int[] iArr = new int[]{0};
                final List arrayList = new ArrayList();
                QRComicManager b = this.b.c;
                if (aVar.i == null) {
                    obj = aVar.n;
                } else {
                    obj = aVar.i;
                }
                b.a(obj, str, i2, 1, i, this.b.b.a(), true, i2, true, new com.qrcomic.manager.QRComicManager.c(this) {
                    final /* synthetic */ AnonymousClass13 d;

                    public void a(com.qrcomic.entity.a aVar, int i) {
                        if (g.a()) {
                            g.a("QRComicReadPageDirector", g.d, " getSectionList 因为本地数据校验失败，所以需要重新服务器拉取漫画信息 success");
                        }
                        int[] iArr = iArr;
                        iArr[0] = iArr[0] + 1;
                        if (!(aVar == null || aVar.p == null || aVar.p.size() <= 0)) {
                            arrayList.addAll(aVar.p);
                        }
                        if (iArr[0] != i) {
                            return;
                        }
                        if (arrayList.size() > 0) {
                            eVar.onNext(arrayList);
                            eVar.onCompleted();
                            return;
                        }
                        eVar.onError(new QuerySectionListInfoException("section list size = 0", arrayList));
                    }

                    public void a(List<ComicSectionPicInfo> list, String str, String str2) {
                        if (g.a()) {
                            g.a("QRComicReadPageDirector", g.d, " onSectionPicInfo 因为本地数据校验失败，所以需要重新服务器拉取漫画信息 fail");
                        }
                    }
                }, true);
            }
        });
    }

    public rx.a<a> b(final com.qrcomic.activity.reader.a aVar, final boolean z) {
        return rx.a.a(new rx.a.a<a>(this) {
            final /* synthetic */ QRComicReadPageDirector c;

            public /* synthetic */ void call(Object obj) {
                a((e) obj);
            }

            public void a(final e<? super a> eVar) {
                List a;
                com.qrcomic.entity.a aVar = null;
                int i = 3;
                if (aVar.J) {
                    com.qrcomic.entity.a a2 = this.c.c.a(aVar.n);
                    if (a2 != null) {
                        aVar = a2;
                        a = a2.a();
                    } else {
                        aVar = a2;
                        Object obj = null;
                    }
                } else {
                    a = null;
                }
                if (aVar.E < 0 && !TextUtils.isEmpty(aVar.j)) {
                    if (g.a()) {
                        g.a("QRComicReadPageDirector", g.d, "not have sectionIndex  ,  currentSectionIndex is " + aVar.E);
                    }
                    aVar.E = this.c.c.a(aVar, aVar.n, aVar.j);
                }
                int i2 = aVar.E;
                String str = aVar.j;
                int i3 = aVar.E;
                if (!aVar.J) {
                    if (i2 <= 0) {
                        i = 1;
                    }
                    i |= 1;
                } else if (a == null || a.size() <= 0) {
                    i = 1;
                } else {
                    if (i2 <= 0 || !aVar.a((String) a.get(i2 - 1))) {
                        i = 1;
                    }
                    if (i2 + 1 < a.size() && aVar.a((String) a.get(i2 + 1))) {
                        i |= 1;
                    }
                }
                final int[] iArr = new int[]{0};
                final List arrayList = new ArrayList();
                this.c.c.a(aVar.n, str, i3, 1, i, this.c.b.a(), true, i2, aVar.J, new com.qrcomic.manager.QRComicManager.c(this) {
                    final /* synthetic */ AnonymousClass14 d;

                    public void a(com.qrcomic.entity.a aVar, int i) {
                        int[] iArr = iArr;
                        iArr[0] = iArr[0] + 1;
                        if (!(aVar == null || aVar.p == null || aVar.p.size() <= 0)) {
                            arrayList.addAll(aVar.p);
                        }
                        if (iArr[0] == i) {
                            a aVar2 = new a();
                            aVar2.a = aVar;
                            aVar2.b = arrayList;
                            if (TextUtils.isEmpty(aVar.j) && arrayList.size() > 0) {
                                aVar.j = ((f) arrayList.get(0)).b;
                                aVar.E = 0;
                            }
                            if (aVar == null || arrayList.size() <= 0) {
                                eVar.onError(new QueryComicAndSectionListInfoException("comic info is " + aVar + "， respSectionList size = " + arrayList.size(), aVar2));
                            } else {
                                eVar.onNext(aVar2);
                                eVar.onCompleted();
                            }
                        }
                        if (g.a()) {
                            g.a("QRComicReadPageDirector", g.d, " getComicAndSectionList 开始的时候拉取漫画信息  islocal = " + (aVar != null ? String.valueOf(aVar.E) : " null "));
                        }
                    }

                    public void a(List<ComicSectionPicInfo> list, String str, String str2) {
                        if (g.a()) {
                            g.b("QRComicReadPageDirector", g.d, "onsection pic info ");
                        }
                    }
                }, z | 0);
            }
        }).b(rx.e.d.b());
    }

    public rx.a<b> c(final com.qrcomic.activity.reader.a aVar, final boolean z) {
        return rx.a.a(new rx.a.a<b>(this) {
            final /* synthetic */ QRComicReadPageDirector c;

            public /* synthetic */ void call(Object obj) {
                a((e) obj);
            }

            public void a(final e<? super b> eVar) {
                this.c.c.a(aVar.n, aVar.j, true, aVar.J, new com.qrcomic.manager.QRComicManager.c(this) {
                    final /* synthetic */ AnonymousClass15 b;

                    public void a(com.qrcomic.entity.a aVar, int i) {
                    }

                    public void a(List<ComicSectionPicInfo> list, String str, String str2) {
                        if (list == null || list.size() <= 0) {
                            eVar.onError(new QuerySectionPicInfoException("picInfoList is null", list));
                            if (g.a()) {
                                g.a("QRComicReadPageDirector", g.d, "picInfoList is null : comicId is " + str + ", sectionId is " + str2);
                            }
                        } else {
                            b bVar = new b();
                            bVar.b = str;
                            bVar.c = str2;
                            bVar.a = list;
                            eVar.onNext(bVar);
                            eVar.onCompleted();
                        }
                        if (g.a()) {
                            g.a("QRComicReadPageDirector", g.d, " onSectionPicInfo  初始化后拉取图片信息 : comicId is " + str + ", sectionId is " + str2);
                        }
                    }
                }, z);
            }
        }).b(rx.e.d.b());
    }

    public void a(final com.qrcomic.activity.reader.a aVar, rx.a<d> aVar2, boolean z) {
        if (aVar2 == null) {
            boolean z2 = z && com.qrcomic.util.f.a(this.b.b());
            aVar2 = a(aVar, z2);
        }
        aVar2.b(new e<d>(this) {
            final /* synthetic */ QRComicReadPageDirector b;

            public /* synthetic */ void onNext(Object obj) {
                a((d) obj);
            }

            public void onCompleted() {
                if (g.a()) {
                    g.a("QRComicReadPageDirector", g.d, " userBuyInfoPacObservable onCompleted ");
                }
            }

            public void onError(Throwable th) {
                if (g.a()) {
                    g.a("QRComicReadPageDirector", g.d, "userBuyInfoPacObservable e is " + th.toString());
                }
                aVar.a(th);
            }

            public void a(d dVar) {
                this.b.a(aVar, dVar);
                if (g.a()) {
                    g.a("QRComicReadPageDirector", g.d, " loadUserBuyInfo  初始化漫画引擎，拉取用户的购买信息 islocal " + (dVar != null ? Boolean.valueOf(dVar.d) : " null"));
                }
            }
        });
    }

    public void d(final com.qrcomic.activity.reader.a aVar, boolean z) {
        if (g.a()) {
            g.a("QRComicReadPageDirector", g.d, " 书籍加载中。。。。forceNet = " + z);
        }
        boolean a = z & com.qrcomic.util.f.a(this.b.b());
        this.e = null;
        rx.a a2 = a(aVar, a);
        rx.a b = b(aVar, a);
        rx.a c = c(aVar, a & 0);
        Object obj = null;
        if (!(TextUtils.isEmpty(aVar.j) || "0".equals(aVar.j))) {
            obj = 1;
        }
        aVar.R = System.currentTimeMillis();
        final c cVar;
        if (aVar.J || aVar.Q || !this.b.f().a().a(this.b.b())) {
            rx.a a3;
            if (obj != null) {
                a3 = rx.a.a(b, c, new rx.b.e<a, b, c>(this) {
                    final /* synthetic */ QRComicReadPageDirector a;

                    {
                        this.a = r1;
                    }

                    public c a(a aVar, b bVar) {
                        c cVar = new c();
                        cVar.a = aVar;
                        cVar.c = bVar;
                        return cVar;
                    }
                });
                a(aVar, a2, a);
                a3.a(rx.e.d.b()).b(new e<c>(this) {
                    final /* synthetic */ QRComicReadPageDirector b;

                    public /* synthetic */ void onNext(Object obj) {
                        a((c) obj);
                    }

                    public void onCompleted() {
                        if (g.a()) {
                            g.a("QRComicReadPageDirector", g.d, " zipOfflineObservable onCompleted ");
                        }
                    }

                    public void onError(Throwable th) {
                        if (g.a()) {
                            g.a("QRComicReadPageDirector", g.d, "zipOfflineObservable e is " + th.toString());
                        }
                        aVar.a(th);
                    }

                    public void a(c cVar) {
                        this.b.a(aVar, cVar);
                    }
                });
                return;
            }
            cVar = new c();
            a3 = rx.a.a(b.a(new rx.b.d<a, c>(this) {
                final /* synthetic */ QRComicReadPageDirector b;

                public /* synthetic */ Object call(Object obj) {
                    return a((a) obj);
                }

                public c a(a aVar) {
                    cVar.a = aVar;
                    return cVar;
                }
            }), c.a(new rx.b.d<b, c>(this) {
                final /* synthetic */ QRComicReadPageDirector b;

                public /* synthetic */ Object call(Object obj) {
                    return a((b) obj);
                }

                public c a(b bVar) {
                    cVar.c = bVar;
                    return cVar;
                }
            }));
            a(aVar, a2, a);
            a3.a(rx.e.d.b()).b(new e<c>(this) {
                final /* synthetic */ QRComicReadPageDirector b;

                public /* synthetic */ void onNext(Object obj) {
                    a((c) obj);
                }

                public void onCompleted() {
                    if (g.a()) {
                        g.a("QRComicReadPageDirector", g.d, " zipOfflineObservable onCompleted ");
                    }
                }

                public void onError(Throwable th) {
                    if (g.a()) {
                        g.a("QRComicReadPageDirector", g.d, "zipOfflineObservable e is " + th.toString());
                    }
                    aVar.a(th);
                }

                public void a(c cVar) {
                    if (cVar != null && cVar.a != null && cVar.c != null) {
                        this.b.a(aVar, cVar);
                    }
                }
            });
        } else if (obj != null) {
            rx.a.a(a2, b, c, new rx.b.f<d, a, b, c>(this) {
                final /* synthetic */ QRComicReadPageDirector a;

                {
                    this.a = r1;
                }

                public c a(d dVar, a aVar, b bVar) {
                    c cVar = new c();
                    cVar.a = aVar;
                    cVar.b = dVar;
                    cVar.c = bVar;
                    return cVar;
                }
            }).a(rx.e.d.b()).b(new e<c>(this) {
                final /* synthetic */ QRComicReadPageDirector b;

                public /* synthetic */ void onNext(Object obj) {
                    a((c) obj);
                }

                public void onCompleted() {
                    if (g.a()) {
                        g.a("QRComicReadPageDirector", g.d, " zipOnlineObservable onCompleted ");
                    }
                }

                public void onError(Throwable th) {
                    if (g.a()) {
                        g.a("QRComicReadPageDirector", g.d, "zipOnlineObservable e is " + th.toString());
                        th.printStackTrace();
                    }
                    aVar.a(th);
                }

                public void a(c cVar) {
                    this.b.a(aVar, cVar);
                }
            });
        } else {
            cVar = new c();
            rx.a.a(a2.a(new rx.b.d<d, c>(this) {
                final /* synthetic */ QRComicReadPageDirector b;

                public /* synthetic */ Object call(Object obj) {
                    return a((d) obj);
                }

                public c a(d dVar) {
                    cVar.b = dVar;
                    return cVar;
                }
            }), b.a(new rx.b.d<a, c>(this) {
                final /* synthetic */ QRComicReadPageDirector b;

                public /* synthetic */ Object call(Object obj) {
                    return a((a) obj);
                }

                public c a(a aVar) {
                    cVar.a = aVar;
                    return cVar;
                }
            }), c.a(new rx.b.d<b, c>(this) {
                final /* synthetic */ QRComicReadPageDirector b;

                public /* synthetic */ Object call(Object obj) {
                    return a((b) obj);
                }

                public c a(b bVar) {
                    cVar.c = bVar;
                    return cVar;
                }
            })).a(rx.e.d.b()).b(new e<c>(this) {
                final /* synthetic */ QRComicReadPageDirector b;

                public /* synthetic */ void onNext(Object obj) {
                    a((c) obj);
                }

                public void onCompleted() {
                    if (g.a()) {
                        g.a("QRComicReadPageDirector", g.d, " zipOfflineObservable onCompleted ");
                    }
                }

                public void onError(Throwable th) {
                    if (g.a()) {
                        g.a("QRComicReadPageDirector", g.d, "zipOfflineObservable e is " + th.toString());
                    }
                    aVar.a(th);
                }

                public void a(c cVar) {
                    if (g.a()) {
                        g.a("QRComicReadPageDirector", g.d, "   zip on next " + cVar);
                    }
                    if (cVar != null && cVar.c != null && cVar.a != null && cVar.b != null) {
                        this.b.a(aVar, cVar);
                    }
                }
            });
        }
    }

    private synchronized void a(com.qrcomic.activity.reader.a aVar, d dVar) {
        boolean z = false;
        synchronized (this) {
            if (!(aVar == null || dVar == null)) {
                aVar.m = true;
                if (!dVar.d) {
                    aVar.l = true;
                }
                List list = dVar.a;
                if (list != null && list.size() > 0) {
                    l lVar = (l) list.get(0);
                    if (aVar.n.equals(lVar.a)) {
                        if (lVar.c == 1) {
                            z = true;
                        }
                        aVar.z = z;
                        aVar.A = com.qrcomic.util.h.a.b(aVar.n, this.b.a());
                        if (lVar.e != null) {
                            if (aVar.y == null) {
                                aVar.y = new LinkedList();
                            }
                            for (n nVar : lVar.e) {
                                if (nVar.b == 1 && !aVar.y.contains(nVar.a)) {
                                    aVar.y.add(nVar.a);
                                }
                            }
                        } else if (g.a()) {
                            g.a("QRComicReadPageDirector", g.d, "RequestPayedSectionsStep : buyInfo.sectionBuyStatusList is null");
                        }
                    } else if (g.a()) {
                        g.a("QRComicReadPageDirector", g.d, " 查询该本漫画的付费信息失败了，，，id没有匹配");
                    }
                } else if (g.a()) {
                    g.a("QRComicReadPageDirector", g.d, "RequestPayedSectionsStep : queryUserBuyInfoPac.infoList is null");
                }
            }
        }
    }

    private void a(final com.qrcomic.activity.reader.a aVar, final c cVar) {
        if (cVar != null) {
            this.e = cVar;
            if (cVar.b != null) {
                a(aVar, cVar.b);
            }
            if (!aVar.a(cVar.a.a)) {
                this.c.a(aVar.n, true);
            } else if (aVar.c()) {
                if (aVar.a(cVar.a.b) && cVar.c.c.equals(aVar.j)) {
                    aVar.r = cVar.c.a;
                    aVar.g();
                }
                aVar.k();
                b(aVar);
            } else {
                if (g.a()) {
                    g.a("QRComicReadPageDirector", g.d, " 漫画的index索引校验失败。。。");
                }
                a(aVar).a(rx.e.d.b()).b(new e<List<f>>(this) {
                    final /* synthetic */ QRComicReadPageDirector c;

                    public /* synthetic */ void onNext(Object obj) {
                        a((List) obj);
                    }

                    public void onCompleted() {
                        if (g.a()) {
                            g.a("QRComicReadPageDirector", g.d, "getSectionList onCompleted");
                        }
                    }

                    public void onError(Throwable th) {
                        if (g.a()) {
                            g.a("QRComicReadPageDirector", g.d, "getSectionList e is " + th.toString());
                        }
                        aVar.a(th);
                    }

                    public void a(List<f> list) {
                        if (aVar.a((List) list) && cVar.c.c.equals(aVar.j)) {
                            aVar.r = cVar.c.a;
                            aVar.g();
                        }
                        aVar.k();
                        this.c.b(aVar);
                    }
                });
            }
            g.a("QRComicReadPageDirector", g.d, "zipUnion " + cVar);
        }
    }

    public void b(final com.qrcomic.activity.reader.a aVar) {
        if (g.a()) {
            g.a("QRComicReadPageDirector", g.d, " 开始进行服务器校验.....");
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new QRComicBuyReqInfo(aVar.n));
        if (!aVar.l) {
            a(aVar, arrayList);
        }
        int i = aVar.E;
        String str = aVar.j;
        if (aVar.i != null) {
            this.c.a(aVar.i, str, i, 0, 1, this.b.a(), false, aVar.i.o.size(), false, new com.qrcomic.manager.QRComicManager.c(this) {
                final /* synthetic */ QRComicReadPageDirector b;

                public void a(com.qrcomic.entity.a aVar, int i) {
                    if (aVar.c(aVar)) {
                        if (aVar != null) {
                            aVar.i = aVar;
                        }
                    } else if (g.a()) {
                        g.a("QRComicReadPageDirector", g.d, "同步服务器的漫画 ..... comic 可能已经下架 " + aVar);
                    }
                }

                public void a(List<ComicSectionPicInfo> list, String str, String str2) {
                }
            }, true);
        }
    }

    public void c(com.qrcomic.activity.reader.a aVar) {
        if (this.e != null && this.e.c != null && aVar.j.equals(this.e.c.c) && aVar.a(aVar.o)) {
            aVar.r = this.e.c.a;
            aVar.g();
        }
    }
}
