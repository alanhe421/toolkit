package com.qrcomic.downloader;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.qrcomic.a.h;
import com.qrcomic.a.j;
import com.qrcomic.d.b;
import com.qrcomic.downloader.QRComicDownloadService.a;
import com.qrcomic.downloader.a.c;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.f;
import com.qrcomic.entity.i;
import com.qrcomic.f.e;
import com.qrcomic.manager.QRComicManager;
import com.qrcomic.util.g;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.open.SocialConstants;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: QRComicDownloader */
public class d {
    private static volatile d m;
    public w a;
    public p b;
    public ConcurrentLinkedQueue<u> c;
    public AtomicLong d = new AtomicLong(0);
    public ConcurrentHashMap<String, a> e = new ConcurrentHashMap();
    public ConcurrentHashMap<String, ConcurrentHashMap<String, t>> f = new ConcurrentHashMap();
    public ConcurrentHashMap<String, v> g = new ConcurrentHashMap();
    public f h;
    public AtomicLong i = new AtomicLong(0);
    public AtomicBoolean j = new AtomicBoolean(false);
    public a k;
    public ServiceConnection l = new ServiceConnection(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (g.a()) {
                g.b("QRComicDownloader", g.d, "QRComicDownloader onServiceConnected");
            }
            if (iBinder instanceof a) {
                this.a.k = (a) iBinder;
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            if (g.a()) {
                g.b("QRComicDownloader", g.d, "QRComicDownloader onServiceDisconnected");
            }
        }
    };
    private b n;
    private b o;
    private AtomicLong p = new AtomicLong(0);
    private AtomicLong q = new AtomicLong(0);
    private BroadcastReceiver r = new BroadcastReceiver(this) {
        final /* synthetic */ d a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            this.a.a(102, intent.getStringExtra("userId"));
        }
    };

    private d() {
        try {
            this.a = new w();
            this.n = new b(Looper.getMainLooper());
            this.o = j.b();
            this.c = new ConcurrentLinkedQueue();
            this.b = new p();
            this.b.start();
            this.p.set(0);
            this.q.set(0);
            this.o.a(new Runnable(this) {
                final /* synthetic */ d a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        h b = com.qrcomic.manager.b.a().b();
                        if (b != null) {
                            String a = b.a();
                            if (!TextUtils.isEmpty(a)) {
                                QRComicManager qRComicManager = (QRComicManager) b.a(1);
                                qRComicManager.d(a);
                                qRComicManager.e(a);
                                d.b().e();
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            a();
            j();
        } catch (Exception e) {
            if (g.a()) {
                g.b("QRComicDownloader", g.d, "QRComicDownloader create excepton msg=" + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    public void a() {
        h b = com.qrcomic.manager.b.a().b();
        if (b != null) {
            b.b().startService(new Intent(b.b(), QRComicDownloadService.class));
        }
    }

    public static d b() {
        if (m == null) {
            synchronized (d.class) {
                if (m == null) {
                    m = new d();
                }
            }
        }
        return m;
    }

    public static boolean a(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith("0064_")) {
            return false;
        }
        return true;
    }

    public b c() {
        return this.n;
    }

    public void a(LinkedList<ComicSectionPicInfo> linkedList, j jVar, boolean z) {
        try {
            long andIncrement = this.d.getAndIncrement();
            LinkedList linkedList2 = new LinkedList();
            while (linkedList != null && linkedList.size() > 0) {
                ComicSectionPicInfo comicSectionPicInfo = (ComicSectionPicInfo) linkedList.poll();
                long andIncrement2 = h.f.getAndIncrement();
                if (!(comicSectionPicInfo == null || TextUtils.isEmpty(comicSectionPicInfo.picUrl))) {
                    com.qrcomic.downloader.a.d.a aVar;
                    if (comicSectionPicInfo.bitmap == null || comicSectionPicInfo.bitmap.isRecycled()) {
                        aVar = new com.qrcomic.downloader.a.d.a(comicSectionPicInfo.picUrl, andIncrement, andIncrement2);
                        aVar.a(comicSectionPicInfo);
                        Bitmap a = a(aVar);
                        if (a == null || a.isRecycled()) {
                            this.q.incrementAndGet();
                            linkedList2.add(new i(andIncrement, andIncrement2, comicSectionPicInfo, jVar));
                        } else {
                            this.p.incrementAndGet();
                            if (jVar != null) {
                                comicSectionPicInfo.bitmap = a;
                                jVar.a(comicSectionPicInfo, 0, 0);
                            }
                        }
                    } else {
                        aVar = new com.qrcomic.downloader.a.d.a(comicSectionPicInfo.picUrl, andIncrement, andIncrement2);
                        aVar.a(comicSectionPicInfo);
                        comicSectionPicInfo.bitmap = a(aVar);
                        if (z && jVar != null) {
                            jVar.a(comicSectionPicInfo, 0, 0);
                        }
                    }
                }
            }
            while (linkedList2.size() > 0) {
                this.a.execute((Runnable) linkedList2.poll());
            }
        } catch (Exception e) {
            if (g.a()) {
                g.b("QRComicDownloader", g.d, "getBitmapByUrl exception msg=" + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    public void b(final String str) {
        this.o.a(new Runnable(this) {
            final /* synthetic */ d b;

            public void run() {
                try {
                    e eVar = new e();
                    eVar.a("getSectionStart", false);
                    h b = com.qrcomic.manager.b.a().b();
                    String a = b.a();
                    Object hashMap = new HashMap();
                    if (TextUtils.isEmpty(a) || TextUtils.isEmpty(str)) {
                        this.b.a(9, false, hashMap);
                        return;
                    }
                    QRComicManager qRComicManager = (QRComicManager) b.a(1);
                    List<i> b2 = qRComicManager.b(str, a);
                    eVar.a("getSectionDb", "getSectionStart", false);
                    for (Entry value : this.b.e.entrySet()) {
                        a aVar = (a) value.getValue();
                        if (aVar instanceof u) {
                            u uVar = (u) aVar;
                            f fVar = uVar.f;
                            if (TextUtils.equals(fVar.a, str) && TextUtils.equals(uVar.x, a)) {
                                i iVar = new i();
                                iVar.g = (((long) uVar.p()) * fVar.d) / 100;
                                iVar.h = uVar.q.get();
                                iVar.f = uVar.c;
                                iVar.e = uVar.d;
                                iVar.b = str;
                                iVar.c = fVar.b;
                                iVar.j = com.qrcomic.util.e.a();
                                iVar.a = a;
                                iVar.d = uVar.n();
                                iVar.i = uVar.p();
                                iVar.k = fVar.d;
                                iVar.l = fVar.f;
                                fVar.r = iVar;
                                fVar.s = true;
                                hashMap.put(fVar.a(), fVar);
                            }
                        }
                    }
                    eVar.a("getSectionMemStatus", "getSectionDb", false);
                    if (!(b2 == null || b2.isEmpty())) {
                        for (i iVar2 : b2) {
                            f fVar2 = new f();
                            fVar2.a = iVar2.b;
                            fVar2.b = iVar2.c;
                            fVar2.f = iVar2.l;
                            fVar2.d = iVar2.k;
                            fVar2.r = iVar2;
                            fVar2.s = true;
                            if (iVar2.d == 104) {
                                Object a2 = c.a(a, fVar2.a, fVar2.b);
                                if (!TextUtils.isEmpty(a2)) {
                                    File file = new File(a2);
                                    if (!file.isDirectory() || file.listFiles().length < fVar2.g()) {
                                        qRComicManager.c(iVar2);
                                    }
                                }
                            }
                            CharSequence a3 = fVar2.a();
                            if (!TextUtils.isEmpty(a3)) {
                                if (hashMap.containsKey(a3)) {
                                    fVar2 = (f) hashMap.get(a3);
                                    fVar2.r.i = Math.max(fVar2.r.i, iVar2.i);
                                    fVar2.r.g = Math.max(fVar2.r.g, iVar2.g);
                                } else {
                                    if (iVar2.d == 101 || iVar2.d == 100) {
                                        iVar2.d = 102;
                                    }
                                    hashMap.put(a3, fVar2);
                                }
                            }
                        }
                    }
                    eVar.a("getSectionUpdateHistory", "getSectionMemStatus", false);
                    this.b.a(9, true, hashMap);
                } catch (Exception e) {
                    if (g.a()) {
                        g.b("QRComicDownloader", g.d, "getDownloadComicSection exception msg=" + e.getMessage());
                    }
                    e.printStackTrace();
                }
            }
        });
    }

    private void f(String str) {
        h b = com.qrcomic.manager.b.a().b();
        if (b != null && !TextUtils.isEmpty(str) && !this.f.containsKey(str)) {
            List<i> b2 = ((QRComicManager) b.a(1)).b(str, b.a());
            ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
            if (!(b2 == null || b2.isEmpty())) {
                for (i iVar : b2) {
                    String str2 = iVar.c;
                    t tVar = new t();
                    tVar.a = str2;
                    tVar.b = iVar.d;
                    tVar.c = iVar.g;
                    tVar.d = iVar.k;
                    tVar.e = 0;
                    tVar.f = 0;
                    concurrentHashMap.put(str2, tVar);
                }
            }
            this.f.put(str, concurrentHashMap);
        }
    }

    public void a(String str, String str2, t tVar) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && tVar != null && this.f.containsKey(str)) {
            ((ConcurrentHashMap) this.f.get(str)).put(str2, tVar);
        }
    }

    public v c(String str) {
        v vVar = new v();
        vVar.a = str;
        if (!TextUtils.isEmpty(str) && this.f.containsKey(str)) {
            ConcurrentHashMap concurrentHashMap = (ConcurrentHashMap) this.f.get(str);
            if (concurrentHashMap != null) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(concurrentHashMap.values());
                long j = 0;
                long j2 = 0;
                Iterator it = arrayList.iterator();
                int i = 0;
                while (it.hasNext()) {
                    int i2;
                    t tVar = (t) it.next();
                    int i3 = tVar.b;
                    if (vVar.b == 101 || i3 == 101) {
                        vVar.b = 101;
                    } else {
                        vVar.b = vVar.b > 0 ? Math.min(vVar.b, i3) : i3;
                    }
                    long j3 = tVar.e;
                    long j4 = tVar.c;
                    long j5 = tVar.f;
                    if (j3 > 0) {
                        j += j3;
                        i2 = i + 1;
                    } else {
                        i2 = i;
                    }
                    j2 += j4;
                    switch (i3) {
                        case 100:
                            vVar.e++;
                            break;
                        case 101:
                            vVar.f++;
                            break;
                        case 102:
                            vVar.g++;
                            break;
                        case 103:
                            vVar.h++;
                            break;
                        case 104:
                            vVar.i++;
                            break;
                        default:
                            break;
                    }
                    vVar.d = Math.max(j5, vVar.d);
                    i = i2;
                }
                vVar.j = j2;
                vVar.k = i > 0 ? j / ((long) i) : 0;
                this.g.put(str, vVar);
            }
        }
        return vVar;
    }

    public v d(String str) {
        if (TextUtils.isEmpty(str) || !this.g.containsKey(str)) {
            return null;
        }
        return (v) this.g.get(str);
    }

    public void a(String str, v vVar) {
        try {
            long j = this.i.get();
            Object fVar = new f();
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.g.values());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                v vVar2 = (v) it.next();
                long j2 = vVar2.d;
                if (vVar2.e > 0 || vVar2.f > 0 || vVar2.g > 0) {
                    if (j2 > j) {
                        fVar.a = true;
                    }
                    if (vVar2.e > 0 || vVar2.f > 0) {
                        fVar.a(vVar2.a);
                    }
                    fVar.b(vVar2.a);
                }
            }
            if (!fVar.equals(this.h)) {
                a(15, true, fVar);
                f.a(fVar.a, fVar.b, fVar.c);
                this.h = fVar;
                if (!TextUtils.equals(str, "comic_finish") || vVar == null) {
                    Message message = new Message();
                    if (fVar.b > 0) {
                        this.j.set(false);
                        message.what = 1000;
                        Bundle bundle = new Bundle();
                        bundle.putString("title", "动漫正在下载中");
                        bundle.putString(MessageKey.MSG_CONTENT, "你有" + fVar.b + "部作品正在下载，点击查看");
                        message.setData(bundle);
                        if (this.k != null) {
                            this.k.a(message);
                        }
                    } else if (!this.j.get()) {
                        String str2 = "动漫下载己完成";
                        String str3 = "作品己经全部下载啦，点击查看";
                        if (fVar.c > 0) {
                            str2 = "动漫下载己暂停";
                            str3 = "你有" + fVar.c + "部作品未完成下载，点击查看";
                        }
                        message.what = 1003;
                        Bundle bundle2 = new Bundle();
                        bundle2.putString("title", str2);
                        bundle2.putString(MessageKey.MSG_CONTENT, str3);
                        message.setData(bundle2);
                        if (this.k != null) {
                            this.k.a(message);
                        }
                    }
                }
            }
        } catch (Exception e) {
            if (g.a()) {
                g.b("QRComicDownloader", g.d, "notifyComicProgress exception msg=" + e.getMessage());
            }
            e.printStackTrace();
        }
    }

    public void a(final List<f> list, final boolean z) {
        final e eVar = new e();
        this.o.a(new Runnable(this) {
            final /* synthetic */ d d;

            public void run() {
                eVar.a("startSectionBegin", false);
                try {
                    if (!(list == null || list.isEmpty())) {
                        eVar.a("startSectionTaskBegin", true);
                        HashSet hashSet = new HashSet();
                        for (f fVar : list) {
                            if (!(fVar == null || TextUtils.isEmpty(fVar.a) || TextUtils.isEmpty(fVar.b))) {
                                CharSequence a = fVar.a();
                                if (!TextUtils.isEmpty(a)) {
                                    a aVar = (a) this.d.e.get(a);
                                    if (!(aVar instanceof u)) {
                                        aVar = new u(fVar);
                                        this.d.e.put(a, aVar);
                                    }
                                    this.d.f(fVar.a);
                                    hashSet.add(fVar.a);
                                    u uVar = (u) aVar;
                                    uVar.w = System.currentTimeMillis();
                                    uVar.a(z, z);
                                }
                            }
                        }
                        eVar.a("startSectionTaskEnd", "startSectionTaskBegin", false);
                        if (!z) {
                            eVar.a("startSectionDbBegin", true);
                            ((QRComicManager) com.qrcomic.manager.b.a().b().a(1)).b(list);
                            eVar.a("startSectionDbEnd", "startSectionDbBegin", false);
                            eVar.a("startGetStatusStart", false);
                            Iterator it = hashSet.iterator();
                            while (it.hasNext()) {
                                String str = (String) it.next();
                                if (!TextUtils.isEmpty(str)) {
                                    Object c = this.d.c(str);
                                    if (c != null) {
                                        this.d.a(14, true, c);
                                    }
                                }
                            }
                            this.d.a("comic_start", null);
                            eVar.a("startGetStatusEnd", "startGetStatusStart", false);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                eVar.a("startSectionEnd", "startSectionBegin", false);
                if (!z) {
                    this.d.a(8, false, list);
                }
            }
        });
    }

    public boolean d() {
        if (this.e != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.e.values());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (aVar instanceof u) {
                    u uVar = (u) aVar;
                    int n = uVar.n();
                    if (uVar.o()) {
                        continue;
                    } else {
                        Object obj = (n == 100 || n == 101) ? 1 : null;
                        if (obj != null) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void e() {
        final e eVar = new e();
        this.o.a(new Runnable(this) {
            final /* synthetic */ d b;

            public void run() {
                eVar.a("getComicStart", false);
                h b = com.qrcomic.manager.b.a().b();
                if (b != null) {
                    String a = b.a();
                    if (TextUtils.isEmpty(a)) {
                        this.b.a(10, false, null);
                        return;
                    }
                    Object c = ((QRComicManager) b.a(1)).c(a);
                    eVar.a("getComicDb", "getComicStart", false);
                    Iterator it = c.iterator();
                    while (it.hasNext()) {
                        c cVar = (c) it.next();
                        CharSequence charSequence = cVar.a;
                        if (!(TextUtils.isEmpty(charSequence) || this.b.g.containsKey(charSequence))) {
                            int i = cVar.h.e;
                            int i2 = cVar.h.f;
                            int i3 = cVar.h.g;
                            if (i > 0 || i3 > 0 || i2 > 0) {
                                this.b.g.put(charSequence, cVar.h);
                            }
                        }
                    }
                    this.b.a("comic_init_get", null);
                    this.b.a(10, true, c);
                    eVar.a("getComicEnd", false);
                }
            }
        });
    }

    public void a(final List<String> list) {
        final e eVar = new e();
        this.o.a(new Runnable(this) {
            final /* synthetic */ d c;

            public void run() {
                eVar.a("restartComicBegin", false);
                h b = com.qrcomic.manager.b.a().b();
                if (b != null) {
                    String a = b.a();
                    Object hashMap = new HashMap();
                    if (TextUtils.isEmpty(a) || list == null || list.isEmpty()) {
                        this.c.a(12, false, hashMap);
                        return;
                    }
                    eVar.a("restartGetComicDbStart", false);
                    QRComicManager qRComicManager = (QRComicManager) b.a(1);
                    List<i> a2 = qRComicManager.a(a, list);
                    for (String str : list) {
                        if (!TextUtils.isEmpty(str)) {
                            qRComicManager.a(a, str, 100, 104);
                        }
                    }
                    eVar.a("restartGetComicDbEnd", "restartGetComicDbStart", false);
                    eVar.a("restartTaskStart", false);
                    if (a2 == null || a2.isEmpty()) {
                        this.c.a(12, false, hashMap);
                    } else {
                        for (i iVar : a2) {
                            f fVar = new f();
                            fVar.r = iVar;
                            fVar.a = iVar.b;
                            fVar.b = iVar.c;
                            fVar.f = iVar.l;
                            fVar.d = iVar.k;
                            CharSequence a3 = fVar.a();
                            if (!TextUtils.isEmpty(a3)) {
                                a aVar = (a) this.c.e.get(a3);
                                if (!(aVar instanceof u)) {
                                    aVar = new u(fVar);
                                    this.c.e.put(a3, aVar);
                                }
                                this.c.f(fVar.a);
                                ((u) aVar).a(false, false);
                            }
                        }
                        eVar.a("restartTaskEnd", "restartTaskStart", false);
                        eVar.a("restartGetStatusStart", false);
                        for (String str2 : list) {
                            if (!TextUtils.isEmpty(str2)) {
                                v c = this.c.c(str2);
                                if (c != null) {
                                    hashMap.put(str2, c);
                                }
                            }
                        }
                        this.c.a("comic_restart", null);
                        eVar.a("restartGetStatusEnd", "restartGetStatusStart", false);
                        this.c.a(12, true, hashMap);
                    }
                    eVar.a("restartComicEnd", false);
                }
            }
        });
    }

    public void a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            f fVar = new f();
            fVar.a = str;
            fVar.b = str2;
            CharSequence a = fVar.a();
            if (!TextUtils.isEmpty(a)) {
                a aVar = (a) this.e.get(a);
                if (!(aVar instanceof u)) {
                    aVar = new u(fVar);
                    this.e.put(a, aVar);
                }
                f(fVar.a);
                ((u) aVar).a(true, true);
            }
        }
    }

    public void e(String str) {
        h b = com.qrcomic.manager.b.a().b();
        if (b != null) {
            b(str, b.a());
        }
    }

    private void b(final String str, final String str2) {
        final e eVar = new e();
        this.o.a(new Runnable(this) {
            final /* synthetic */ d d;

            public void run() {
                eVar.a("pauseComicStart", false);
                h b = com.qrcomic.manager.b.a().b();
                Object hashMap = new HashMap();
                if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
                    hashMap.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_COMICID, str);
                    hashMap.put(SocialConstants.PARAM_SEND_MSG, "comicId or uin is null");
                    this.d.a(13, false, hashMap);
                    return;
                }
                u uVar;
                hashMap.put(ComicStoreExclusiveItemCard.NET_AD_ATTR_COMICID, str);
                hashMap.put(SocialConstants.PARAM_SEND_MSG, "success");
                eVar.a("pausePreloadStart", false);
                int size = this.d.c.size();
                if (!(this.d.c == null || this.d.c.isEmpty())) {
                    Iterator it = this.d.c.iterator();
                    while (it.hasNext()) {
                        uVar = (u) it.next();
                        if (uVar != null) {
                            f fVar = uVar.f;
                            if (fVar != null && TextUtils.equals(str, fVar.a)) {
                                uVar.b(true, false);
                            }
                        }
                    }
                }
                eVar.a("pausePreloadEnd" + size, "pausePreloadStart", false);
                eVar.a("pauseRunningStart", false);
                if (!(this.d.e == null || this.d.e.isEmpty())) {
                    for (Entry value : this.d.e.entrySet()) {
                        a aVar = (a) value.getValue();
                        if (aVar instanceof u) {
                            uVar = (u) aVar;
                            f fVar2 = uVar.f;
                            if (fVar2 != null && TextUtils.equals(str, fVar2.a)) {
                                int n = uVar.n();
                                if (n == 100) {
                                    uVar.b(true, false);
                                } else if (n == 101) {
                                    uVar.b(true, false);
                                }
                            }
                        }
                    }
                }
                eVar.a("pauseRunningEnd", "pauseRunningStart", false);
                eVar.a("pauseComicDbStart", false);
                if (b != null) {
                    ((QRComicManager) b.a(1)).a(str2, str, 102, 102);
                    eVar.a("pauseComicDbEnd", "pauseComicDbStart", false);
                }
                this.d.a("comic_pause", this.d.c(str));
                eVar.a("pauseComicEnd", "pauseComicStart", false);
                this.d.a(13, true, hashMap);
            }
        });
    }

    public void a(int i) {
        h b = com.qrcomic.manager.b.a().b();
        if (b != null) {
            a(i, b.a());
        }
    }

    public void a(int i, String str) {
        g.b("QRComicDownloader", g.d, "pauseAllComics reason=" + i);
        try {
            if (d()) {
                for (String str2 : this.f.keySet()) {
                    if (!TextUtils.isEmpty(str2)) {
                        b(str2, str);
                    }
                }
                this.j.set(true);
                Message message = new Message();
                message.what = 1001;
                Bundle bundle = new Bundle();
                bundle.putString("title", "漫画暂停下载");
                if (i == 100) {
                    b().a(16, true, null);
                    bundle.putString(MessageKey.MSG_CONTENT, "网络变更为非wifi网络，已为你自动暂停下载，点击查看");
                    message.setData(bundle);
                    if (this.k != null) {
                        this.k.a(message);
                    }
                } else if (i == 101) {
                    bundle.putString(MessageKey.MSG_CONTENT, "你的SD卡空间不足，已为你自动暂停下载，点击查看");
                    message.setData(bundle);
                    if (this.k != null) {
                        this.k.a(message);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Bitmap a(com.qrcomic.downloader.a.d.a aVar) {
        if (aVar == null) {
            return null;
        }
        com.qqcomic.bitmaphelper.b bVar;
        com.qrcomic.downloader.a.d i = i();
        if (i != null) {
            bVar = (com.qqcomic.bitmaphelper.b) i.a(aVar);
            if (bVar != null) {
                Bitmap e = bVar.e();
                if (!(e == null || e.isRecycled())) {
                    if (!g.a()) {
                        return e;
                    }
                    g.a("QRComicDownloader", g.d, " 找到了显示内存的图pain");
                    return e;
                }
            }
        }
        i = h();
        if (i != null) {
            bVar = (com.qqcomic.bitmaphelper.b) i.a(aVar);
            if (bVar != null) {
                if (g.a()) {
                    g.a("QRComicDownloader", g.d, " 查找lru图片");
                }
                return bVar.e();
            }
        }
        return null;
    }

    public void a(com.qrcomic.downloader.a.d.a aVar, com.qqcomic.bitmaphelper.b bVar) {
        if (aVar != null && bVar != null) {
            com.qrcomic.downloader.a.d h = h();
            if (h != null) {
                h.c(aVar, bVar);
            }
        }
    }

    public void f() {
        if (g.a()) {
            g.b("QRComicDownloader", g.d, "activity onDestroy clearBitmapCache");
        }
        if (this.p.get() + this.q.get() > 0) {
            this.p.set(0);
            this.q.set(0);
        }
        com.qrcomic.downloader.a.d h = h();
        if (h != null) {
            if (g.a()) {
                g.b("QRComicDownloader", g.d, "mComicMemCache.count = " + h.b());
            }
            h.e();
        }
        h = i();
        if (h != null) {
            if (g.a()) {
                g.b("QRComicDownloader", g.d, "comicActiveCache.count = " + h.b());
            }
            h.e();
        }
        this.o.a(new Runnable(this) {
            final /* synthetic */ d a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.a.a();
            }
        });
    }

    private com.qrcomic.downloader.a.d<com.qrcomic.downloader.a.d.a, com.qqcomic.bitmaphelper.b> h() {
        h b = com.qrcomic.manager.b.a().b();
        if (b == null) {
            return null;
        }
        return b.h;
    }

    private com.qrcomic.downloader.a.d<com.qrcomic.downloader.a.d.a, com.qqcomic.bitmaphelper.b> i() {
        h b = com.qrcomic.manager.b.a().b();
        if (b == null) {
            return null;
        }
        return b.i;
    }

    public void a(int i, boolean z, Object obj) {
        h b = com.qrcomic.manager.b.a().b();
        if (b != null) {
            synchronized (b.f) {
                for (com.qrcomic.a.a aVar : b.f) {
                    if (e.class.isAssignableFrom(aVar.getClass())) {
                        a(i, z, obj, aVar, this.n);
                    }
                }
            }
            synchronized (b.g) {
                for (com.qrcomic.a.a aVar2 : b.g) {
                    if (e.class.isAssignableFrom(aVar2.getClass())) {
                        a(i, z, obj, aVar2, this.o);
                    }
                }
            }
        }
    }

    private void a(int i, boolean z, Object obj, com.qrcomic.a.a aVar, b bVar) {
        if (bVar != null) {
            final com.qrcomic.a.a aVar2 = aVar;
            final int i2 = i;
            final boolean z2 = z;
            final Object obj2 = obj;
            bVar.a(new Runnable(this) {
                final /* synthetic */ d e;

                public void run() {
                    aVar2.a(i2, z2, obj2);
                }
            });
        }
    }

    private void j() {
        h b = com.qrcomic.manager.b.a().b();
        if (b != null) {
            b.b().registerReceiver(this.r, new IntentFilter("com.qq.reader.loginout"));
        }
    }

    public ConcurrentHashMap<String, a> g() {
        return this.e;
    }
}
