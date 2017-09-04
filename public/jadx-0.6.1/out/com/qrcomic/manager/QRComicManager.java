package com.qrcomic.manager;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.qrcomic.a.d;
import com.qrcomic.a.h;
import com.qrcomic.a.j;
import com.qrcomic.downloader.v;
import com.qrcomic.e.b.e;
import com.qrcomic.entity.ComicCollectionDao;
import com.qrcomic.entity.ComicDao.Properties;
import com.qrcomic.entity.ComicHistoryDao;
import com.qrcomic.entity.ComicReadProgressDao;
import com.qrcomic.entity.ComicRecommendPageInfo;
import com.qrcomic.entity.ComicSectionDao;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.ComicSectionReaded;
import com.qrcomic.entity.DownloadHistoryDao;
import com.qrcomic.entity.QRComicBuyInfoDao;
import com.qrcomic.entity.QRComicBuyReqInfo;
import com.qrcomic.entity.QRComicUpdateReadProgressFailDao;
import com.qrcomic.entity.i;
import com.qrcomic.entity.l;
import com.qrcomic.entity.m;
import com.qrcomic.entity.n;
import com.qrcomic.util.LRULinkedHashMap;
import com.qrcomic.util.f;
import com.qrcomic.util.g;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.query.WhereCondition;

public class QRComicManager implements d {
    public static int a = 0;
    private static final String b = QRComicManager.class.getSimpleName();
    private static final HashMap<String, Integer> c = new LRULinkedHashMap(16);
    private static boolean e = false;
    private static List<String> f = new ArrayList();
    private h d;
    private Handler g = new Handler(this, Looper.getMainLooper()) {
        final /* synthetic */ QRComicManager a;

        public synchronized void handleMessage(Message message) {
            switch (message.what) {
                case 1000:
                    b.a().a("网络异常", 2);
                    break;
            }
        }
    };

    public interface c {
        void a(com.qrcomic.entity.a aVar, int i);

        void a(List<ComicSectionPicInfo> list, String str, String str2);
    }

    public static class ComicRecommendPageInfoException extends Exception {
        public Object mInfo;

        public ComicRecommendPageInfoException(String str) {
            this(str, null);
        }

        public ComicRecommendPageInfoException(String str, Object obj) {
            super(str);
            this.mInfo = obj;
        }
    }

    class a extends d {
        Point a;
        String b;
        int c;
        int d;
        String e;
        int f;
        String g;
        c h;
        int i;
        boolean j = true;
        com.qrcomic.e.b k = null;
        com.qrcomic.e.c l = null;
        int m = 0;
        int n;
        boolean o;
        String p;
        boolean q;
        final /* synthetic */ QRComicManager r;

        a(QRComicManager qRComicManager, String str, int i, int i2, int i3, String str2, String str3, int i4, int i5, c cVar, boolean z, int i6, int i7, boolean z2, String str4, boolean z3) {
            this.r = qRComicManager;
            this.a = com.qrcomic.e.b.a(i3, i2, i);
            this.b = str;
            this.c = i2;
            this.d = i3;
            this.e = str2;
            this.g = str3;
            this.i = i4;
            this.f = i5;
            this.h = cVar;
            this.j = z;
            this.m = i6;
            this.n = i7;
            this.o = z2;
            this.p = str4;
            this.k = (com.qrcomic.e.b) qRComicManager.d.b(1);
            this.q = z3;
            if (g.a()) {
                g.a(QRComicManager.b, g.d, "CheckComicRunnable create ");
            }
        }

        public synchronized void run() {
            if (g.a()) {
                g.a(QRComicManager.b, g.d, "CheckComicRunnable start Run ");
            }
            if (f.a(this.r.d.b())) {
                this.l = new com.qrcomic.e.c(this, this.e, this.b, this.c, this.d, this.i, this.f) {
                    final /* synthetic */ a a;

                    public synchronized void i(Object obj) {
                        com.qrcomic.entity.a aVar = (com.qrcomic.entity.a) obj;
                        String str = aVar.a;
                        int i = aVar.A;
                        int i2 = aVar.B;
                        String str2 = aVar.z;
                        int i3 = aVar.C;
                        int i4 = aVar.D;
                        if (str.equals(this.d) && (((str2 == null && this.e == null) || (str2 != null && str2.equals(this.e))) && i == this.f && this.g == i2 && i3 == this.h && i4 == this.i && this.a.r.d.b(this, true))) {
                            if (this.a.j) {
                                List a = this.a.a(aVar);
                                if (a == null || a.size() == 0) {
                                    this.a.h.a(aVar, this.a.m);
                                } else {
                                    aVar.p = this.a.r.a(this.d, this.a.g, a, aVar.p);
                                    this.a.h.a(aVar, this.a.m);
                                }
                            } else {
                                this.a.h.a(aVar, this.a.m);
                            }
                            this.a.r.d.b((com.qrcomic.a.a) this);
                        }
                    }

                    public synchronized void j(Object obj) {
                        if (obj != null) {
                            try {
                                if (obj instanceof com.qrcomic.e.b.b) {
                                    com.qrcomic.e.b.b bVar = (com.qrcomic.e.b.b) obj;
                                    if (this.d.equals(bVar.a) && this.e.equals(bVar.b) && this.f == bVar.c && this.g == bVar.d && this.h == bVar.e && this.i == bVar.f && this.a.r.d.b(this, true)) {
                                        if (this.a.h != null) {
                                            this.a.h.a(null, this.a.m);
                                        }
                                        this.a.r.d.b((com.qrcomic.a.a) this);
                                    } else {
                                        if (this.a.h != null) {
                                            this.a.h.a(null, this.a.m);
                                        }
                                        this.a.r.d.b((com.qrcomic.a.a) this);
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                                if (this.a.h != null) {
                                    this.a.h.a(null, this.a.m);
                                }
                                if (this.a.r.d != null) {
                                    this.a.r.d.b((com.qrcomic.a.a) this);
                                }
                            }
                        }
                        if (this.a.h != null) {
                            this.a.h.a(null, this.a.m);
                        }
                        this.a.r.d.b((com.qrcomic.a.a) this);
                    }
                };
                if (this.o) {
                    a(true);
                } else {
                    this.r.d.a(this.l, true);
                    if ((TextUtils.isEmpty(this.b) && this.c == 2 && !TextUtils.isEmpty(this.p)) || this.q) {
                        this.k.a(this.e, this.b, this.c, this.d, this.i, this.f, true);
                    } else {
                        this.k.a(this.e, this.b, this.c, this.d, this.i, this.f, false);
                    }
                }
            } else {
                a(false);
            }
            if (g.a()) {
                g.a(QRComicManager.b, g.d, "CheckComicRunnable end run ");
            }
        }

        protected List<String> a(com.qrcomic.entity.a aVar) {
            int i = 0;
            try {
                List a = aVar.a();
                List<String> arrayList = new ArrayList();
                if (this.i != 0) {
                    if (this.i == 1) {
                        int i2;
                        if (TextUtils.isEmpty(this.b)) {
                            i2 = 0;
                        } else {
                            i2 = a.indexOf(this.b);
                        }
                        if (this.d == 1) {
                            i = this.c + i2;
                        } else if (this.d == 2) {
                            int i3 = i2;
                            i2 -= this.c;
                            i = i3;
                        } else if (this.d == 3) {
                            i = this.c + i2;
                            i2 -= this.c;
                        } else {
                            i2 = 0;
                        }
                        if (i2 >= 0 && i2 <= a.size()) {
                            arrayList.addAll(a.subList(i2, Math.min(i + 1, a.size())));
                        }
                    } else if (this.i != 2) {
                        this.r.g("error , pageType = " + this.i + " , and isChapterSplit = " + aVar.j);
                        return null;
                    }
                }
                return arrayList;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void a(boolean z) {
            boolean z2 = false;
            com.qrcomic.entity.a a = this.r.a(this.e);
            if (a != null) {
                List a2 = a(a);
                if (a2 != null) {
                    if (a2.size() <= 0) {
                        a.p = new ArrayList();
                    } else if (this.j) {
                        a.p = this.r.a(this.e, this.g, a2);
                    } else {
                        a.p = this.r.b(this.e, a2);
                    }
                    if (a.p != null && a.p.size() == a2.size()) {
                        z2 = true;
                    }
                }
            }
            if (z2) {
                a.z = this.b;
                a.A = this.c;
                a.B = this.d;
                a.C = this.i;
                a.D = this.f;
                this.h.a(a, this.m);
                if (z) {
                    this.r.d.b(this.l);
                }
            } else if (z) {
                this.r.d.a(this.l, true);
                this.k.a(this.e, this.b, this.c, this.d, this.i, this.f, true);
            } else {
                this.h.a(a, this.m);
                this.r.g("curSectionIndex = " + this.n + ", begin = " + this.b + " , count = " + this.c + " direction = " + this.d);
                if (this.a != null && this.n >= this.a.x && this.n <= this.a.y) {
                    this.r.g.sendEmptyMessage(1000);
                }
            }
        }
    }

    class b extends d {
        String a;
        String b;
        boolean c;
        boolean d;
        c e;
        com.qrcomic.e.b f = null;
        com.qrcomic.e.c g = null;
        boolean h;
        final /* synthetic */ QRComicManager i;

        b(QRComicManager qRComicManager, String str, String str2, boolean z, boolean z2, c cVar, boolean z3) {
            this.i = qRComicManager;
            this.a = str;
            this.b = str2;
            this.c = z;
            this.d = z2;
            this.e = cVar;
            this.f = (com.qrcomic.e.b) qRComicManager.d.b(1);
            this.h = z3;
            if (g.a()) {
                g.a(QRComicManager.b, g.d, "CheckSectionRunnable create ");
            }
        }

        public synchronized void run() {
            if (g.a()) {
                g.a(QRComicManager.b, g.d, "CheckSectionRunnable start run ");
            }
            if (f.a(this.i.d.b())) {
                this.g = new com.qrcomic.e.c(this, this.a, this.b) {
                    final /* synthetic */ b a;

                    public synchronized void k(Object obj) {
                        if (g.a()) {
                            g.b(QRComicManager.b, g.d, "onSectionDetailSuccess comicId = " + this.d + " , sectionId = " + this.e);
                        }
                        List list = (List) obj;
                        String str = this.d;
                        String str2 = this.e;
                        if (!(list == null || list.size() == 0)) {
                            str = ((ComicSectionPicInfo) list.get(0)).comicId;
                            str2 = ((ComicSectionPicInfo) list.get(0)).sectionId;
                        }
                        if (str.equals(this.d) && str2.equals(this.e) && this.a.i.d.b(this, true)) {
                            if (g.a()) {
                                g.b(QRComicManager.b, g.d, "onSectionDetailSuccess onSectionPicInfo realComicId=" + str + ",realSectionId=" + str2 + " Thread = " + Thread.currentThread().getName());
                            }
                            this.a.e.a(list, str, str2);
                        }
                        this.a.i.d.b((com.qrcomic.a.a) this);
                    }

                    public synchronized void l(Object obj) {
                        if (g.a()) {
                            g.b(QRComicManager.b, g.d, "onSectionDetailFailure comicId=" + this.d + ",sectionId=" + this.e);
                        }
                        if (obj == null || !(obj instanceof e)) {
                            this.a.e.a(null, this.d, this.e);
                        } else {
                            e eVar = (e) obj;
                            if (this.d.equals(eVar.a) && this.e.equals(eVar.b) && this.a.i.d.b(this, true)) {
                                if (eVar.c == 120100) {
                                    this.a.f.a(this.d, this.e, 0, 3, 1, 0, true);
                                }
                                this.a.e.a(null, this.d, this.e);
                                this.a.i.d.b((com.qrcomic.a.a) this);
                            }
                        }
                        this.a.i.d.b((com.qrcomic.a.a) this);
                    }
                };
                if (this.d) {
                    a(true);
                } else {
                    this.i.d.a(this.g, true);
                    this.f.a(this.a, this.b, this.h | 0);
                }
            } else {
                a(false);
            }
            if (g.a()) {
                g.a(QRComicManager.b, g.d, "CheckSectionRunnable stop run ");
            }
        }

        private void a(boolean z) {
            boolean z2 = false;
            com.qrcomic.entity.f a = this.i.a(this.a, this.b);
            if (!(a == null || a.q == null || a.q.size() == 0)) {
                z2 = true;
            }
            if (z2) {
                this.e.a(a.q, a.a, a.b);
                if (z) {
                    this.i.d.b(this.g);
                }
                this.i.g("section.comicId = " + a.a + ", section.sectionId = " + a.b + " section.picInfoList.size() = " + a.q.size());
            } else if (z) {
                this.i.d.a(this.g, true);
                this.f.a(this.a, this.b, true);
            } else {
                this.e.a(null, this.a, this.b);
                this.i.g("isCurrentSection = " + this.c + " , sectionId = " + this.b);
                if (this.c) {
                    this.i.g.sendEmptyMessage(1000);
                }
            }
        }
    }

    public QRComicManager(h hVar) {
        this.d = hVar;
    }

    public synchronized void a(Object obj, String str, int i, int i2, int i3, String str2, boolean z, int i4, boolean z2, c cVar, boolean z3) {
        if (cVar == null) {
            try {
                g("onComicListener is null");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (obj == null || i < 0) {
            cVar.a(null, 1);
        } else if (obj instanceof String) {
            r0 = j.a();
            r0.a(new a(this, str, i, i2, i3, (String) obj, str2, 1, 0, cVar, z, 1, i4, z2, null, z3), null, false);
        } else if (obj instanceof com.qrcomic.entity.a) {
            com.qrcomic.entity.a aVar = (com.qrcomic.entity.a) obj;
            List a = aVar.a();
            if (i >= a.size()) {
                String str3 = (String) a.get(a.size() - 1);
                if (f.a(this.d.b())) {
                    r0 = j.a();
                    r0.a(new a(this, str3, a.size() - 1, i2, 1, aVar.a, str2, 1, 0, cVar, z, 1, i4, false, str3, z3), null, false);
                } else {
                    cVar.a(null, 1);
                }
            } else {
                r0 = j.a();
                r0.a(new a(this, str, i, i2, i3, aVar.a, str2, 1, 0, cVar, z, 1, i4, z2, null, z3), null, false);
            }
        } else {
            cVar.a(null, 1);
        }
    }

    public synchronized void a(String str, String str2, boolean z, boolean z2, c cVar, boolean z3) {
        if (cVar == null) {
            g("onComicListener is null");
        } else if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            cVar.a(null, str, str2);
        } else {
            g("request comicId = " + str + " , sectionId = " + str2);
            j.a().a(new b(this, str, str2, z, z2, cVar, z3), null, false);
        }
    }

    public synchronized void a(ArrayList<QRComicBuyReqInfo> arrayList, boolean z) {
        a((ArrayList) arrayList, null, z);
    }

    public synchronized void a(ArrayList<QRComicBuyReqInfo> arrayList, Bundle bundle, boolean z) {
        try {
            com.qrcomic.e.b bVar = (com.qrcomic.e.b) this.d.b(1);
            com.qrcomic.e.b.c cVar = new com.qrcomic.e.b.c();
            cVar.a = arrayList;
            if (bundle != null) {
                cVar.c = new Bundle(bundle);
            }
            if (arrayList == null || arrayList.size() == 0) {
                cVar.b = -1;
                bVar.a(28, true, cVar);
            } else {
                bVar.a((ArrayList) arrayList, bundle, z);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized boolean a(com.qrcomic.entity.a aVar) {
        boolean z = false;
        synchronized (this) {
            try {
                if (b()) {
                    b(aVar);
                    com.qrcomic.entity.h c = c();
                    if (!(aVar == null || c == null)) {
                        if (aVar.h == null) {
                            if (aVar.j) {
                                aVar.h = a(aVar.n, 40);
                            } else {
                                aVar.h = a(aVar.o, 2);
                            }
                        }
                        if (aVar.k == 0) {
                            aVar.k = com.qrcomic.util.e.a();
                        }
                        try {
                            c.f().save(aVar);
                            z = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    public synchronized com.qrcomic.entity.a a(String str) {
        com.qrcomic.entity.a aVar;
        try {
            if (b()) {
                com.qrcomic.entity.h c = c();
                if (!(TextUtils.isEmpty(str) || c == null)) {
                    aVar = (com.qrcomic.entity.a) c.f().queryBuilder().where(Properties.b.eq(str), new WhereCondition[0]).build().unique();
                    if (aVar == null || aVar.h == null) {
                        aVar = null;
                    } else if (aVar.j) {
                        aVar.n = a(aVar.h);
                        try {
                            aVar.a();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        aVar.o = a(aVar.h);
                    }
                }
                aVar = null;
            } else {
                aVar = null;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return aVar;
    }

    public synchronized boolean a(List<com.qrcomic.entity.f> list) {
        boolean z = false;
        synchronized (this) {
            try {
                if (b()) {
                    com.qrcomic.entity.h c = c();
                    if (!(list == null || list.size() == 0 || c == null)) {
                        boolean z2;
                        try {
                            for (Object obj : list) {
                                a((com.qrcomic.entity.f) obj);
                                a("SAVE_2_b", obj);
                                c.d().save(obj);
                            }
                            z2 = true;
                        } catch (Exception e) {
                            e.printStackTrace();
                            z2 = false;
                        }
                        z = z2;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return z;
    }

    private boolean b() {
        return c() != null;
    }

    public synchronized boolean a(String str, String str2, com.qrcomic.entity.f fVar) {
        boolean z;
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || !b())) {
            try {
                com.qrcomic.entity.f fVar2 = (com.qrcomic.entity.f) c().d().queryBuilder().where(ComicSectionDao.Properties.b.eq(str), ComicSectionDao.Properties.c.eq(str2)).build().unique();
                if (fVar2 != null) {
                    fVar.a(fVar2.n());
                    fVar2.i = fVar.i == null ? a(fVar.q, 20) : fVar.i;
                    fVar2.h = com.qrcomic.util.e.a();
                    c().d().save(fVar2);
                    a("SAVE_3", "PICINFO " + fVar.toString() + " " + fVar.i + "  unique id = " + fVar2.n());
                } else {
                    if (fVar.i == null) {
                        fVar.i = a(fVar.q, 20);
                    }
                    c().d().save(fVar);
                    a("SAVE_4", "PICINFO " + fVar.toString() + " " + fVar.i + "  picInfo id = " + fVar.n());
                }
                z = true;
            } catch (Exception e) {
                g("updateComicSectionPicInfo exception :" + e.toString());
                e.printStackTrace();
            }
        }
        z = false;
        return z;
    }

    public synchronized com.qrcomic.entity.f a(String str, String str2) {
        com.qrcomic.entity.f fVar;
        try {
            if (!(!b() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
                fVar = (com.qrcomic.entity.f) c().d().queryBuilder().where(ComicSectionDao.Properties.b.eq(str), ComicSectionDao.Properties.c.eq(str2)).build().unique();
                if (!(fVar == null || fVar.i == null)) {
                    fVar.q = a(fVar.i);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        fVar = null;
        return fVar;
    }

    public synchronized i a(String str, String str2, String str3) {
        i iVar;
        try {
            if (!(!b() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3))) {
                iVar = (i) c().a().queryBuilder().where(c().a().queryBuilder().and(DownloadHistoryDao.Properties.b.eq(str3), DownloadHistoryDao.Properties.d.eq(str2), DownloadHistoryDao.Properties.c.eq(str)), new WhereCondition[0]).unique();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        iVar = null;
        return iVar;
    }

    public synchronized List<com.qrcomic.entity.f> b(String str) {
        List<com.qrcomic.entity.f> list;
        try {
            if (b() && !TextUtils.isEmpty(str)) {
                List<com.qrcomic.entity.f> list2 = c().d().queryBuilder().orderAsc(ComicSectionDao.Properties.g).where(ComicSectionDao.Properties.b.eq(str), new WhereCondition[0]).build().list();
                if (list2 != null) {
                    for (com.qrcomic.entity.f fVar : list2) {
                        if (fVar.i != null) {
                            fVar.q = a(fVar.i);
                        }
                    }
                    list = list2;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        list = null;
        return list;
    }

    public synchronized List<i> b(String str, String str2) {
        List<i> list;
        try {
            if (!(!b() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
                list = c().a().queryBuilder().where(c().a().queryBuilder().and(DownloadHistoryDao.Properties.b.eq(str2), DownloadHistoryDao.Properties.c.eq(str), new WhereCondition[0]), new WhereCondition[0]).list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        list = null;
        return list;
    }

    public synchronized List<i> a(String str, List<String> list) {
        List<i> list2;
        try {
            CharSequence simpleName = i.class.getSimpleName();
            if (!(TextUtils.isEmpty(str) || list == null || list.isEmpty() || TextUtils.isEmpty(simpleName) || !b())) {
                list2 = c().a().queryBuilder().where(DownloadHistoryDao.Properties.b.eq(str), DownloadHistoryDao.Properties.c.in((Collection) list), DownloadHistoryDao.Properties.e.notEq(Integer.valueOf(104))).orderAsc(DownloadHistoryDao.Properties.m).build().list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        list2 = null;
        return list2;
    }

    public synchronized ArrayList<com.qrcomic.downloader.c> c(String str) {
        ArrayList<com.qrcomic.downloader.c> arrayList;
        List arrayList2 = new ArrayList();
        Cursor cursor = null;
        if (!TextUtils.isEmpty(str)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("select h." + DownloadHistoryDao.Properties.c.columnName + ",h." + DownloadHistoryDao.Properties.e.columnName + ",sum(h." + DownloadHistoryDao.Properties.h.columnName + ")" + ",sum(h." + DownloadHistoryDao.Properties.l.columnName + ")," + "max(h." + DownloadHistoryDao.Properties.k.columnName + ")," + "count(h." + DownloadHistoryDao.Properties.e.columnName + ")," + "c." + Properties.c.columnName + ",c." + Properties.e.columnName + "," + "c." + Properties.j.columnName + ",c." + Properties.m.columnName + "," + "c." + Properties.n.columnName + " ");
            stringBuilder.append(" from DOWNLOAD_HISTORY as h,COMIC as c ");
            stringBuilder.append(" where h." + DownloadHistoryDao.Properties.b.columnName + "=? and c." + "" + Properties.b.columnName + "=h." + DownloadHistoryDao.Properties.c.columnName + " ");
            stringBuilder.append(" group by h." + DownloadHistoryDao.Properties.b.columnName + ",h." + DownloadHistoryDao.Properties.c.columnName + ",h." + DownloadHistoryDao.Properties.e.columnName + "");
            b.a().b();
            Database d = d();
            if (d == null) {
                if (cursor != null) {
                    cursor.close();
                }
                arrayList = arrayList2;
            } else {
                cursor = d.rawQuery(stringBuilder.toString(), new String[]{str});
                HashMap hashMap = new HashMap();
                if (cursor != null && cursor.moveToFirst()) {
                    do {
                        String string = cursor.getString(0);
                        if (!TextUtils.isEmpty(string)) {
                            com.qrcomic.downloader.c cVar;
                            int i = cursor.getInt(1);
                            long j = cursor.getLong(2);
                            long j2 = cursor.getLong(3);
                            long j3 = cursor.getLong(4);
                            int i2 = cursor.getInt(5);
                            com.qrcomic.downloader.c cVar2 = new com.qrcomic.downloader.c();
                            if (hashMap.containsKey(string)) {
                                cVar2 = (com.qrcomic.downloader.c) hashMap.get(string);
                                cVar2.g = j2 + cVar2.g;
                                cVar = cVar2;
                            } else {
                                cVar2.a = string;
                                cVar2.b = cursor.getString(6);
                                cVar2.c = cursor.getLong(7);
                                cVar2.d = cursor.getInt(8);
                                cVar2.e = cursor.getString(9);
                                cVar2.f = cursor.getInt(10);
                                cVar2.g = j2;
                                cVar2.h = new v();
                                cVar2.h.a = string;
                                cVar2.h.b = i;
                                cVar = cVar2;
                            }
                            v vVar = cVar.h;
                            if (vVar.b == 101 || i == 101) {
                                vVar.b = 101;
                            } else {
                                int min;
                                if (vVar.b > 0) {
                                    min = Math.min(vVar.b, i);
                                } else {
                                    min = i;
                                }
                                vVar.b = min;
                            }
                            vVar.j = j + vVar.j;
                            vVar.c = Math.max(j3, vVar.c);
                            switch (i) {
                                case 100:
                                    vVar.e += i2;
                                    break;
                                case 101:
                                    try {
                                        vVar.f += i2;
                                        break;
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        if (cursor != null) {
                                            cursor.close();
                                            break;
                                        }
                                    } catch (Throwable th) {
                                        if (cursor != null) {
                                            cursor.close();
                                        }
                                    }
                                    break;
                                case 102:
                                    vVar.g += i2;
                                    break;
                                case 103:
                                    vVar.h += i2;
                                    break;
                                case 104:
                                    vVar.i += i2;
                                    break;
                            }
                            hashMap.put(string, cVar);
                        }
                    } while (cursor.moveToNext());
                }
                if (!hashMap.isEmpty()) {
                    arrayList2.addAll(hashMap.values());
                    Collections.sort(arrayList2, new Comparator<com.qrcomic.downloader.c>(this) {
                        final /* synthetic */ QRComicManager a;

                        {
                            this.a = r1;
                        }

                        public /* synthetic */ int compare(Object obj, Object obj2) {
                            return a((com.qrcomic.downloader.c) obj, (com.qrcomic.downloader.c) obj2);
                        }

                        public synchronized int a(com.qrcomic.downloader.c cVar, com.qrcomic.downloader.c cVar2) {
                            return (int) (cVar2.h.c - cVar.h.c);
                        }
                    });
                }
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        List list = arrayList2;
        return arrayList;
    }

    public synchronized com.qrcomic.entity.b c(String str, String str2) {
        com.qrcomic.entity.b bVar;
        try {
            if (!(!b() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
                bVar = (com.qrcomic.entity.b) c().g().queryBuilder().where(ComicCollectionDao.Properties.b.eq(str), ComicCollectionDao.Properties.c.eq(str2)).unique();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        bVar = null;
        return bVar;
    }

    public synchronized com.qrcomic.entity.c d(String str, String str2) {
        com.qrcomic.entity.c cVar;
        try {
            if (!(!b() || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
                cVar = (com.qrcomic.entity.c) c().e().queryBuilder().where(ComicHistoryDao.Properties.c.eq(str), ComicHistoryDao.Properties.d.eq(str2)).unique();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        cVar = null;
        return cVar;
    }

    public synchronized boolean e(String str, String str2) {
        boolean z = false;
        synchronized (this) {
            com.qrcomic.entity.e f = f(str, str2);
            if (f != null) {
                try {
                    com.qrcomic.entity.c d = d(str, str2);
                    if (d == null) {
                        d = new com.qrcomic.entity.c(str, str2);
                        com.qrcomic.entity.a a = a(str2);
                        if (a != null) {
                            d.a(a);
                        }
                        a(d);
                    }
                    d.a(f);
                    d.B = 1;
                    d.x = com.qrcomic.util.e.a();
                    d.A = true;
                    c().e().update(d);
                    z = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return z;
    }

    public synchronized boolean a(com.qrcomic.entity.c cVar) {
        boolean z;
        try {
            if (b() && cVar != null) {
                b(cVar);
                c().e().save(cVar);
                z = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        z = false;
        return z;
    }

    public synchronized List<com.qrcomic.entity.f> a(String str, int i, int i2) {
        List<com.qrcomic.entity.f> list;
        try {
            if (b() && !TextUtils.isEmpty(str) && i >= 0 && i2 >= i) {
                list = c().d().queryBuilder().where(ComicSectionDao.Properties.b.eq(str), ComicSectionDao.Properties.g.ge(Integer.valueOf(i)), ComicSectionDao.Properties.g.le(Integer.valueOf(i2))).orderAsc(ComicSectionDao.Properties.g).list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        list = null;
        return list;
    }

    public synchronized List<com.qrcomic.entity.f> b(String str, List<String> list) {
        List<com.qrcomic.entity.f> list2;
        try {
            if (!(!b() || TextUtils.isEmpty(str) || list == null || list.size() == 0)) {
                list2 = c().d().queryBuilder().where(ComicSectionDao.Properties.b.eq(str), ComicSectionDao.Properties.c.in((Collection) list)).orderAsc(ComicSectionDao.Properties.g).list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        list2 = null;
        return list2;
    }

    public synchronized List<i> a(String str, String str2, String[] strArr) {
        List<i> list;
        try {
            if (b() && !TextUtils.isEmpty(str) && strArr.length > 0) {
                list = c().a().queryBuilder().where(DownloadHistoryDao.Properties.c.eq(str), DownloadHistoryDao.Properties.b.eq(str2), DownloadHistoryDao.Properties.d.in(Arrays.asList(strArr))).orderAsc(DownloadHistoryDao.Properties.m).list();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        list = null;
        return list;
    }

    public synchronized List<com.qrcomic.entity.f> a(String str, String str2, List<String> list) {
        List<com.qrcomic.entity.f> a;
        try {
            if (!(!b() || TextUtils.isEmpty(str) || list == null || list.size() == 0)) {
                List b = b(str, (List) list);
                String[] strArr = new String[list.size()];
                list.toArray(strArr);
                a = a(b, a(str, str2, strArr));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        a = null;
        return a;
    }

    public synchronized List<com.qrcomic.entity.f> a(String str, String str2, List<String> list, List<com.qrcomic.entity.f> list2) {
        try {
            if (!(!b() || TextUtils.isEmpty(str) || list == null || list.size() == 0 || list2 == null)) {
                String[] strArr = new String[list.size()];
                list.toArray(strArr);
                list2 = a((List) list2, a(str, str2, strArr));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list2;
    }

    private synchronized List<com.qrcomic.entity.f> a(List<com.qrcomic.entity.f> list, List<i> list2) {
        if (!(list == null || list2 == null)) {
            try {
                if (!(list.size() == 0 || list2.size() == 0)) {
                    for (com.qrcomic.entity.f fVar : list) {
                        Iterator it = list2.iterator();
                        while (it.hasNext()) {
                            i iVar = (i) it.next();
                            if (fVar.a.equals(iVar.b) && fVar.b.equals(iVar.c)) {
                                fVar.r = iVar;
                                fVar.s = true;
                                it.remove();
                                break;
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public synchronized boolean a(i iVar) {
        boolean z;
        try {
            g("saveDownloadHistory . history =  " + iVar);
            if (iVar != null && b()) {
                if (iVar.j == 0) {
                    iVar.j = com.qrcomic.util.e.a();
                }
                d(iVar);
                c().a().save(iVar);
                z = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        z = false;
        return z;
    }

    public synchronized boolean b(List<com.qrcomic.entity.f> list) {
        boolean z;
        Object a = b.a().b().a();
        CharSequence simpleName = i.class.getSimpleName();
        if (list == null || !b() || list.isEmpty() || TextUtils.isEmpty(simpleName) || TextUtils.isEmpty(a)) {
            z = false;
        } else {
            try {
                Iterable arrayList = new ArrayList();
                long a2 = com.qrcomic.util.e.a();
                for (com.qrcomic.entity.f fVar : list) {
                    i iVar = new i();
                    iVar.a = a;
                    iVar.b = fVar.a;
                    iVar.c = fVar.b;
                    iVar.d = 100;
                    iVar.e = "";
                    iVar.f = 0;
                    iVar.j = a2;
                    iVar.k = fVar.d;
                    iVar.l = fVar.f;
                    d(iVar);
                    arrayList.add(iVar);
                }
                c().a().insertOrReplaceInTx(arrayList);
                z = true;
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
            }
        }
        return z;
    }

    public synchronized boolean b(i iVar) {
        try {
            if (!(!b() || TextUtils.isEmpty(iVar.b) || TextUtils.isEmpty(iVar.c) || TextUtils.isEmpty(iVar.a))) {
                d(iVar);
                c().a().save(iVar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public synchronized boolean a(String str, String str2, String str3, int i, int i2, int i3, String str4) {
        if (!(!b() || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3))) {
            try {
                i iVar = (i) c().a().queryBuilder().where(DownloadHistoryDao.Properties.b.eq(str), DownloadHistoryDao.Properties.c.eq(str2), DownloadHistoryDao.Properties.d.eq(str3)).build().unique();
                iVar.l = i;
                iVar.d = i2;
                iVar.f = i3;
                iVar.e = str4;
                c().a().update(iVar);
            } catch (Exception e) {
                if (g.a()) {
                    g.b(b, g.d, "updateDownloadHistory exception msg=" + e.getMessage());
                }
                e.printStackTrace();
            }
        }
        return false;
    }

    public synchronized boolean d(String str) {
        if (b()) {
            try {
                Iterable<i> list = c().a().queryBuilder().where(DownloadHistoryDao.Properties.b.eq(str), r0.or(DownloadHistoryDao.Properties.e.eq(Integer.valueOf(101)), DownloadHistoryDao.Properties.e.eq(Integer.valueOf(100)), new WhereCondition[0])).build().list();
                for (i iVar : list) {
                    iVar.d = 102;
                }
                c().a().updateInTx((Iterable) list);
            } catch (Exception e) {
                if (g.a()) {
                    g.b(b, g.d, "repairDownloadHistoryStatus exception msg=" + e.getMessage());
                }
                e.printStackTrace();
            }
        }
        return true;
    }

    public synchronized void e(final String str) {
        j.a().a(new d(this) {
            final /* synthetic */ QRComicManager b;

            public synchronized void run() {
                try {
                    CharSequence simpleName = i.class.getSimpleName();
                    if (this.b.b() && !TextUtils.isEmpty(simpleName)) {
                        String b = com.qrcomic.downloader.a.c.b();
                        Cursor rawQuery = this.b.d().rawQuery("select count(*) from DOWNLOAD_HISTORY", null);
                        if (rawQuery != null && rawQuery.moveToFirst()) {
                            if (rawQuery.getLong(0) != 0) {
                                rawQuery.close();
                                Cursor rawQuery2 = this.b.d().rawQuery("select " + DownloadHistoryDao.Properties.c.columnName + " , " + DownloadHistoryDao.Properties.d.columnName + " from " + DownloadHistoryDao.TABLENAME + " where " + DownloadHistoryDao.Properties.b.columnName + "=? and " + DownloadHistoryDao.Properties.e.columnName + " = ?", new String[]{str, "104"});
                                HashMap hashMap = new HashMap();
                                if (rawQuery2 != null && rawQuery2.moveToFirst()) {
                                    String string;
                                    String str;
                                    HashSet hashSet;
                                    do {
                                        string = rawQuery2.getString(0);
                                        String string2 = rawQuery2.getString(1);
                                        if (com.qrcomic.downloader.d.a(string)) {
                                            break;
                                        } else if (hashMap.containsKey(string)) {
                                            ((HashSet) hashMap.get(string)).add(string2);
                                        } else {
                                            HashSet hashSet2 = new HashSet();
                                            hashSet2.add(string2);
                                            hashMap.put(string, hashSet2);
                                        }
                                    } while (rawQuery2.moveToNext());
                                    rawQuery2.close();
                                    ArrayList arrayList = new ArrayList();
                                    for (Entry entry : hashMap.entrySet()) {
                                        str = (String) entry.getKey();
                                        StringBuilder stringBuilder = new StringBuilder();
                                        stringBuilder.append(b);
                                        stringBuilder.append(str).append(File.separator);
                                        stringBuilder.append(str).append(File.separator);
                                        File[] listFiles = new File(stringBuilder.toString()).listFiles();
                                        if (listFiles == null || listFiles.length <= 0) {
                                            arrayList.add(str);
                                        } else {
                                            hashSet = (HashSet) entry.getValue();
                                            for (File file : listFiles) {
                                                if (file != null && file.isDirectory() && file.list() != null && file.list().length > 0) {
                                                    hashSet.remove(file.getName());
                                                }
                                            }
                                        }
                                    }
                                    StringBuilder stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append(" set ").append(DownloadHistoryDao.Properties.h.columnName).append(" = '0'").append(",").append(DownloadHistoryDao.Properties.e.columnName).append(" = '103'").append(",").append(DownloadHistoryDao.Properties.g.columnName).append(" = '205'").append(",").append(DownloadHistoryDao.Properties.f.columnName).append(" = 'user deleted offline file'").append(",").append(DownloadHistoryDao.Properties.i.columnName).append(" = '0'").append(",").append(DownloadHistoryDao.Properties.j.columnName).append(" = '0'");
                                    if (!arrayList.isEmpty()) {
                                        StringBuilder stringBuilder3 = new StringBuilder();
                                        String[] strArr = new String[]{str, String.valueOf(104)};
                                        Iterator it = arrayList.iterator();
                                        while (it.hasNext()) {
                                            string = (String) it.next();
                                            hashMap.remove(string);
                                            stringBuilder3.append(string);
                                            stringBuilder3.append(",");
                                        }
                                        this.b.d().execSQL("update DOWNLOAD_HISTORY" + stringBuilder2.toString() + " where " + DownloadHistoryDao.Properties.b.columnName + " = ? and " + DownloadHistoryDao.Properties.e.columnName + " = ? and " + DownloadHistoryDao.Properties.c.columnName + " in(" + stringBuilder3.substring(0, stringBuilder3.length() - 1) + ")", strArr);
                                    }
                                    for (Entry entry2 : hashMap.entrySet()) {
                                        str = (String) entry2.getKey();
                                        hashSet = (HashSet) entry2.getValue();
                                        if (!hashSet.isEmpty()) {
                                            StringBuilder stringBuilder4 = new StringBuilder();
                                            String[] strArr2 = new String[]{str, str};
                                            Iterator it2 = hashSet.iterator();
                                            while (it2.hasNext()) {
                                                stringBuilder4.append((String) it2.next());
                                                stringBuilder4.append(",");
                                            }
                                            this.b.d().execSQL("update DOWNLOAD_HISTORY" + stringBuilder2.toString() + " where " + DownloadHistoryDao.Properties.b.columnName + "=? and " + DownloadHistoryDao.Properties.e.columnName + "=? and " + DownloadHistoryDao.Properties.c.columnName + " in(" + stringBuilder4.substring(0, stringBuilder4.length() - 1) + ")", strArr2);
                                        }
                                    }
                                }
                            } else if (!(TextUtils.isEmpty(b) || TextUtils.isEmpty(str))) {
                                com.qrcomic.f.b.c(b + "/" + str);
                            }
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }, 2, null, false);
    }

    public synchronized boolean a(String str, String str2, int i, int i2) {
        try {
            if (b() && !TextUtils.isEmpty(str) && i <= i2) {
                try {
                    new ContentValues().put("status", Integer.valueOf(i));
                    d().execSQL("update DOWNLOAD_HISTORY set status = '" + i + "'" + "where " + DownloadHistoryDao.Properties.b.columnName + "=? and " + DownloadHistoryDao.Properties.c.columnName + "=? and " + DownloadHistoryDao.Properties.e.columnName + "<?", new String[]{str, str2, String.valueOf(i2)});
                } catch (Exception e) {
                    if (g.a()) {
                        g.b(b, g.d, "updateComicDbStatus exception msg=" + e.getMessage());
                    }
                    e.printStackTrace();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return false;
    }

    public synchronized boolean c(i iVar) {
        boolean z = true;
        synchronized (this) {
            try {
                CharSequence simpleName = i.class.getSimpleName();
                if (!(!b() || TextUtils.isEmpty(simpleName) || iVar == null)) {
                    c().a().delete(c().a().queryBuilder().where(DownloadHistoryDao.Properties.b.eq(iVar.a), DownloadHistoryDao.Properties.c.eq(iVar.b), DownloadHistoryDao.Properties.d.eq(iVar.c)).unique());
                }
            } catch (Exception e) {
                e.printStackTrace();
                z = false;
            }
        }
        return z;
    }

    public synchronized boolean a(com.qrcomic.entity.e eVar) {
        boolean z;
        try {
            if (b() && eVar != null) {
                if (eVar.k == null) {
                    eVar.k = a(eVar.n, 8);
                }
                c().h().save(eVar);
                z = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        z = false;
        return z;
    }

    public synchronized boolean a(String str, String str2, String str3, String str4, int i, String str5, String str6, int i2, long j, int i3, int i4, boolean z) {
        boolean z2;
        g("0x96 start updateUserLocalComicReadProgress uin=" + str + ",comicId=" + str2 + ",sectionId=" + str4 + ",picId=" + str6 + ",offsetY=" + i2 + ",readTs=" + j + ",picSeq=" + i3 + ",type=" + i4);
        try {
            if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str4) || TextUtils.isEmpty(str5) || TextUtils.isEmpty(str6) || i3 < 0 || i4 < 0) {
                z2 = false;
            } else {
                String str7;
                if (TextUtils.isEmpty(str)) {
                    str7 = "";
                } else {
                    str7 = str;
                }
                if (j <= 0 || com.qrcomic.util.e.a() < j) {
                    z2 = false;
                } else {
                    int a = a(str7, str2, str3, str4, i, str5, str6, i2, j, i3, i4);
                    e(str7, str2);
                    if (a == -1) {
                        z2 = false;
                    } else {
                        h b = b.a().b();
                        m mVar;
                        if (b == null) {
                            mVar = new m();
                            mVar.a = str7;
                            mVar.b = str2;
                            mVar.c = str4;
                            mVar.d = str6;
                            mVar.e = i2;
                            mVar.f = j;
                            mVar.h = i4;
                            a(mVar);
                            z2 = false;
                        } else {
                            com.qrcomic.e.b bVar = (com.qrcomic.e.b) b.b(1);
                            if (f.a(b.b())) {
                                if (!TextUtils.isEmpty(str7) && z) {
                                    com.qrcomic.entity.e eVar = new com.qrcomic.entity.e();
                                    eVar.b = str7;
                                    eVar.c = str2;
                                    eVar.a = str4;
                                    eVar.m = i;
                                    eVar.f = str6;
                                    eVar.g = i3;
                                    eVar.h = i2;
                                    eVar.e = str5;
                                    b.f().b().a(str2, eVar);
                                }
                                bVar.a(str2, str4, str6, i2, j, i4, z);
                                g("0x96 end updateUserLocalComicReadProgress netsend handler.saveReadProgress");
                                z2 = true;
                            } else {
                                mVar = new m();
                                mVar.a = str7;
                                mVar.b = str2;
                                mVar.c = str4;
                                mVar.d = str6;
                                mVar.e = i2;
                                mVar.f = j;
                                mVar.h = i4;
                                a(mVar);
                                z2 = false;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z2;
    }

    public synchronized int a(String str, String str2, String str3, String str4, int i, String str5, String str6, int i2, long j, int i3, int i4) {
        int i5;
        try {
            if (!(!b() || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str4) || TextUtils.isEmpty(str6))) {
                try {
                    com.qrcomic.entity.e eVar;
                    com.qrcomic.entity.e f = f(str, str2);
                    if (f == null) {
                        eVar = new com.qrcomic.entity.e();
                    } else {
                        eVar = f;
                    }
                    if (eVar.l >= j) {
                        g("0x96 db comicReadProgress is newer :" + eVar.toString() + ", new readTs: " + j);
                        i5 = -1;
                    } else {
                        eVar.b = str;
                        eVar.c = str2;
                        eVar.d = str3;
                        eVar.a = str4;
                        eVar.e = str5;
                        eVar.f = str6;
                        eVar.h = i2;
                        eVar.l = j;
                        eVar.g = i3;
                        eVar.i = i4;
                        eVar.m = i;
                        if (eVar.n == null) {
                            eVar.n = new ArrayList();
                            eVar.n.add(new ComicSectionReaded(str4, str6, i2, j, i));
                        } else {
                            int i6 = 0;
                            while (i6 < eVar.n.size()) {
                                if (((ComicSectionReaded) eVar.n.get(i6)).sectionId.equalsIgnoreCase(str4)) {
                                    eVar.n.set(i6, new ComicSectionReaded(str4, str6, i2, j, i));
                                    break;
                                }
                                i6++;
                            }
                            if (i6 == eVar.n.size()) {
                                eVar.n.add(new ComicSectionReaded(str4, str6, i2, j, i));
                            }
                        }
                        eVar.k = null;
                        if (a(eVar)) {
                            i5 = 0;
                        } else {
                            i5 = -2;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    i5 = -3;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        i5 = -4;
        return i5;
    }

    public synchronized com.qrcomic.entity.e f(String str, String str2) {
        com.qrcomic.entity.e eVar;
        try {
            if (b() && !TextUtils.isEmpty(str2)) {
                if (str == null) {
                    str = "";
                }
                eVar = (com.qrcomic.entity.e) c().h().queryBuilder().where(ComicReadProgressDao.Properties.b.eq(str), ComicReadProgressDao.Properties.c.eq(str2)).build().unique();
                if (eVar == null && !TextUtils.isEmpty(str)) {
                    eVar = (com.qrcomic.entity.e) c().h().queryBuilder().where(ComicReadProgressDao.Properties.b.eq(""), ComicReadProgressDao.Properties.c.eq(str2)).build().unique();
                }
                if (!(eVar == null || eVar.k == null)) {
                    eVar.n = a(eVar.k);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        eVar = null;
        return eVar;
    }

    public synchronized int a(com.qrcomic.entity.a aVar, String str, String str2) {
        int i;
        try {
            if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                i = 0;
            } else {
                if (aVar == null) {
                    aVar = a(str);
                }
                if (aVar != null) {
                    List a = aVar.a();
                    if (a != null && a.size() > 0) {
                        int size = a.size();
                        i = 0;
                        while (i < size) {
                            if (str2.equals(a.get(i))) {
                                break;
                            }
                            i++;
                        }
                    }
                }
                i = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public static synchronized byte[] a(List list, int i) {
        ObjectOutputStream objectOutputStream;
        IOException e;
        byte[] bArr;
        Throwable th;
        synchronized (QRComicManager.class) {
            if (list != null) {
                try {
                    if (list.size() != 0) {
                        OutputStream byteArrayOutputStream;
                        try {
                            byteArrayOutputStream = new ByteArrayOutputStream(list.size() * i);
                            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                        } catch (IOException e2) {
                            e = e2;
                            objectOutputStream = null;
                            try {
                                e.printStackTrace();
                                if (objectOutputStream != null) {
                                    try {
                                        objectOutputStream.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                bArr = null;
                                return bArr;
                            } catch (Throwable th2) {
                                th = th2;
                                if (objectOutputStream != null) {
                                    try {
                                        objectOutputStream.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            objectOutputStream = null;
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            throw th;
                        }
                        try {
                            objectOutputStream.writeObject(list);
                            objectOutputStream.flush();
                            bArr = byteArrayOutputStream.toByteArray();
                            if (objectOutputStream != null) {
                                try {
                                    objectOutputStream.close();
                                } catch (IOException e42) {
                                    e42.printStackTrace();
                                }
                            }
                        } catch (IOException e5) {
                            e3 = e5;
                            e3.printStackTrace();
                            if (objectOutputStream != null) {
                                objectOutputStream.close();
                            }
                            bArr = null;
                            return bArr;
                        }
                    }
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
            bArr = null;
        }
        return bArr;
    }

    public static synchronized List a(byte[] bArr) {
        ObjectInputStream objectInputStream;
        Exception e;
        List list;
        Throwable th;
        synchronized (QRComicManager.class) {
            if (bArr != null) {
                try {
                    if (bArr.length != 0) {
                        try {
                            objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bArr));
                        } catch (Exception e2) {
                            e = e2;
                            objectInputStream = null;
                            try {
                                e.printStackTrace();
                                if (objectInputStream != null) {
                                    try {
                                        objectInputStream.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                list = null;
                                return list;
                            } catch (Throwable th2) {
                                th = th2;
                                if (objectInputStream != null) {
                                    try {
                                        objectInputStream.close();
                                    } catch (IOException e4) {
                                        e4.printStackTrace();
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            objectInputStream = null;
                            if (objectInputStream != null) {
                                objectInputStream.close();
                            }
                            throw th;
                        }
                        try {
                            Object readObject = objectInputStream.readObject();
                            if (readObject instanceof List) {
                                list = (List) readObject;
                                if (objectInputStream != null) {
                                    try {
                                        objectInputStream.close();
                                    } catch (IOException e42) {
                                        e42.printStackTrace();
                                    }
                                }
                            } else {
                                if (objectInputStream != null) {
                                    try {
                                        objectInputStream.close();
                                    } catch (IOException e32) {
                                        e32.printStackTrace();
                                    }
                                }
                                list = null;
                            }
                        } catch (Exception e5) {
                            e = e5;
                            e.printStackTrace();
                            if (objectInputStream != null) {
                                objectInputStream.close();
                            }
                            list = null;
                            return list;
                        }
                    }
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
            list = null;
        }
        return list;
    }

    private void g(String str) {
        if (g.a()) {
            g.a(b, g.d, str);
        }
    }

    public synchronized boolean a(m mVar) {
        boolean z;
        try {
            if (!(!b() || mVar == null || TextUtils.isEmpty(mVar.a) || TextUtils.isEmpty(mVar.b))) {
                g("saveRPFailComicId,uin=" + mVar.a + ", comicId=" + mVar.b);
                if (mVar.i() == null || mVar.i().longValue() <= 0) {
                    m g = g(mVar.b, mVar.a);
                    if (g != null) {
                        mVar.a(g.i());
                    }
                }
                c().b().save(mVar);
                z = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        z = false;
        return z;
    }

    public synchronized m g(String str, String str2) {
        m mVar;
        try {
            mVar = (m) c().b().queryBuilder().where(QRComicUpdateReadProgressFailDao.Properties.b.eq(str2), QRComicUpdateReadProgressFailDao.Properties.c.eq(str)).build().unique();
        } catch (Exception e) {
            e.printStackTrace();
            mVar = null;
        }
        return mVar;
    }

    public static synchronized void a(Activity activity, String str, String str2, String str3, String str4, String str5, String str6, int i, int i2, int i3, int i4, com.qrcomic.c.b.a aVar) {
        synchronized (QRComicManager.class) {
            if (!(activity == null || str == null || str3 == null || str4 == null || str5 == null)) {
                try {
                    Bundle bundle = new Bundle();
                    bundle.putString("key_comic_id", str);
                    bundle.putString("key_section_id", str3);
                    bundle.putString("key_comic_title", str2);
                    bundle.putString("key_section_title", str4);
                    bundle.putString("key_section_cover_url", str6);
                    bundle.putInt("key_buy_type", i3);
                    bundle.putInt("key_show_pay_page_orientation", i4);
                    b.a().b().f().f().a(activity, bundle, i, i4, aVar);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static synchronized void a(Activity activity, String str, String str2, int i) {
        synchronized (QRComicManager.class) {
            b.a().b().f().f().a(activity, str, str2, i);
        }
    }

    public synchronized rx.a<ComicRecommendPageInfo> f(final String str) {
        return rx.a.a(new rx.a.a<ComicRecommendPageInfo>(this) {
            final /* synthetic */ QRComicManager b;

            public /* synthetic */ void call(Object obj) {
                a((rx.e) obj);
            }

            public synchronized void a(final rx.e<? super ComicRecommendPageInfo> eVar) {
                if (TextUtils.isEmpty(str)) {
                    eVar.onError(new ComicRecommendPageInfoException("getRecommendPageInfo param comicId error , it is " + str, str));
                } else if (f.a(this.b.d.b())) {
                    this.b.d.a(new com.qrcomic.e.c(this) {
                        final /* synthetic */ AnonymousClass4 b;

                        public synchronized void g(Object obj) {
                            this.b.b.d.b((com.qrcomic.a.a) this);
                            if (obj == null || !(obj instanceof ComicRecommendPageInfo)) {
                                eVar.onError(new ComicRecommendPageInfoException("getRecommendPageInfo data error , it is " + obj, obj));
                            } else {
                                eVar.onNext((ComicRecommendPageInfo) obj);
                                eVar.onCompleted();
                            }
                        }

                        public synchronized void h(Object obj) {
                            this.b.b.d.b((com.qrcomic.a.a) this);
                            eVar.onError(new ComicRecommendPageInfoException("onGetRecommendInfoFailure error , data is " + obj, obj));
                        }
                    }, true);
                    ((com.qrcomic.e.b) this.b.d.b(1)).a(str);
                } else {
                    eVar.onError(new ComicRecommendPageInfoException("getRecommendPageInfo not network ", str));
                }
            }
        }).b(rx.e.d.a());
    }

    public synchronized boolean a(l lVar, String str) {
        l h = h(lVar.a, str);
        if (h != null) {
            lVar.a(h.d());
        }
        lVar.d = com.qrcomic.util.c.b.a(lVar.d);
        c().c().save(lVar);
        return true;
    }

    public synchronized void a(boolean z, String str, String str2, String str3) {
        List arrayList = new ArrayList();
        arrayList.add(str3);
        a(z, str, str2, arrayList);
    }

    public synchronized void a(boolean z, String str, String str2, List<String> list) {
        int i = 1;
        synchronized (this) {
            try {
                l h = h(str2, str);
                if (h != null) {
                    if (z) {
                        if (!z) {
                            i = 2;
                        }
                        h.c = i;
                    } else {
                        List c = h.c();
                        if (c != null) {
                            for (int i2 = 0; i2 < list.size(); i2++) {
                                n nVar = new n((String) list.get(i2), 1);
                                if (!c.contains(nVar)) {
                                    c.add(nVar);
                                }
                            }
                        }
                    }
                    h.d = new Gson().toJson(h, new TypeToken<l>(this) {
                        final /* synthetic */ QRComicManager a;

                        {
                            this.a = r1;
                        }
                    }.getType());
                    a(h, str);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized l h(String str, String str2) {
        l lVar;
        try {
            if (TextUtils.isEmpty(str) || str2 == null) {
                lVar = null;
            } else {
                lVar = (l) c().c().queryBuilder().where(QRComicBuyInfoDao.Properties.c.eq(str), QRComicBuyInfoDao.Properties.b.eq(str2)).unique();
                if (lVar != null) {
                    lVar.d = com.qrcomic.util.c.b.b(lVar.d);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            lVar = null;
        }
        return lVar;
    }

    private com.qrcomic.entity.h c() {
        return b.a().b().c();
    }

    private Database d() {
        return b.a().b().d();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean b(com.qrcomic.entity.a r7) {
        /*
        r6 = this;
        r0 = 1;
        r4 = 0;
        monitor-enter(r6);
        r2 = 0;
        r1 = java.lang.Long.valueOf(r2);	 Catch:{ all -> 0x003d }
        if (r7 == 0) goto L_0x0031;
    L_0x000c:
        r1 = r7.p();	 Catch:{ all -> 0x003d }
        if (r1 == 0) goto L_0x001a;
    L_0x0012:
        r2 = r1.longValue();	 Catch:{ all -> 0x003d }
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 > 0) goto L_0x002f;
    L_0x001a:
        r1 = r7.e();	 Catch:{ all -> 0x003d }
        r1 = r6.h(r1);	 Catch:{ all -> 0x003d }
        if (r1 == 0) goto L_0x0031;
    L_0x0024:
        r2 = r1.longValue();	 Catch:{ all -> 0x003d }
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x0031;
    L_0x002c:
        r7.a(r1);	 Catch:{ all -> 0x003d }
    L_0x002f:
        monitor-exit(r6);
        return r0;
    L_0x0031:
        if (r1 == 0) goto L_0x003b;
    L_0x0033:
        r2 = r1.longValue();	 Catch:{ all -> 0x003d }
        r1 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r1 != 0) goto L_0x002f;
    L_0x003b:
        r0 = 0;
        goto L_0x002f;
    L_0x003d:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qrcomic.manager.QRComicManager.b(com.qrcomic.entity.a):boolean");
    }

    private synchronized Long h(String str) {
        com.qrcomic.entity.a aVar;
        aVar = (com.qrcomic.entity.a) c().f().queryBuilder().where(Properties.b.eq(str), new WhereCondition[0]).build().unique();
        return aVar == null ? null : aVar.p();
    }

    private synchronized boolean a(com.qrcomic.entity.f fVar) {
        boolean z;
        if (fVar.n() == null || fVar.n().longValue() <= 0) {
            com.qrcomic.entity.f fVar2 = (com.qrcomic.entity.f) c().d().queryBuilder().where(ComicSectionDao.Properties.c.eq(fVar.b), ComicSectionDao.Properties.b.eq(fVar.a)).unique();
            if (fVar2 != null) {
                a("SAVE_2_a", fVar.toString() + " unique = " + fVar2.n());
                if (fVar.i == null && fVar2.i != null) {
                    fVar.a(fVar2.i);
                }
                fVar.a(fVar2.n());
                z = true;
            } else {
                z = false;
            }
        } else {
            z = true;
        }
        return z;
    }

    private synchronized boolean b(com.qrcomic.entity.c cVar) {
        boolean z;
        if (cVar.r() == null || cVar.r().longValue() < 0) {
            com.qrcomic.entity.c cVar2 = (com.qrcomic.entity.c) c().e().queryBuilder().where(ComicHistoryDao.Properties.d.eq(cVar.s), new WhereCondition[0]).build().unique();
            if (cVar2 != null) {
                cVar.a(cVar2.r());
                z = true;
            } else {
                z = false;
            }
        } else {
            z = true;
        }
        return z;
    }

    private synchronized boolean d(i iVar) {
        boolean z;
        if (iVar.n() != null && iVar.n().longValue() >= 0) {
            z = true;
        } else if (TextUtils.isEmpty(iVar.b) || TextUtils.isEmpty(iVar.a) || TextUtils.isEmpty(iVar.c)) {
            z = false;
        } else {
            i iVar2 = (i) c().a().queryBuilder().where(DownloadHistoryDao.Properties.c.eq(iVar.b), DownloadHistoryDao.Properties.b.eq(iVar.a), DownloadHistoryDao.Properties.d.eq(iVar.c)).unique();
            if (iVar2 == null) {
                z = false;
            } else {
                iVar.a(iVar2.n());
                z = true;
            }
        }
        return z;
    }

    private void a(String str, Object obj) {
        g.c(str, g.d, obj.toString());
    }

    public synchronized void a(String str, boolean z) {
        try {
            if (b()) {
                com.qrcomic.entity.a a = a(str);
                if (a != null) {
                    c().f().delete(a);
                }
                List b = b(str);
                for (int i = 0; i < b.size(); i++) {
                    com.qrcomic.entity.f fVar = (com.qrcomic.entity.f) b.get(i);
                    c().d().delete(fVar);
                    i a2 = a(str, fVar.b, this.d.a());
                    if (a2 != null) {
                        c().a().delete(a2);
                    }
                }
                if (z) {
                    com.qrcomic.downloader.a.e.a().a(str);
                }
            }
            g.c(b, g.d, " 删除漫画成功。。。漫画id = " + str);
        } catch (Exception e) {
            e.printStackTrace();
            if (g.a()) {
                g.c(b, g.d, " 删除漫画失败。。。发生异常");
            }
        }
    }

    public synchronized void c(String str, List<String> list) {
        if (list != null) {
            try {
                if (!list.isEmpty()) {
                    if (b()) {
                        com.qrcomic.entity.a a = a(str);
                        c().f().delete(a);
                        List a2 = a.a();
                        if (!(a2 == null || a2.isEmpty())) {
                            for (int i = 0; i < list.size(); i++) {
                                String str2 = (String) list.get(i);
                                if (!TextUtils.isEmpty(str2) && a2.contains(str2)) {
                                    com.qrcomic.entity.f a3 = a(str, str2);
                                    if (a3 != null) {
                                        c().d().delete(a3);
                                    }
                                    i a4 = a(str, str2, this.d.a());
                                    if (a4 != null) {
                                        c().delete(a4);
                                    }
                                    com.qrcomic.downloader.a.e.a().a(str, str2);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void i(String str, String str2) {
        if (b()) {
            l h = h(str, str2);
            if (h != null) {
                c().c().delete(h);
            }
        }
    }
}
