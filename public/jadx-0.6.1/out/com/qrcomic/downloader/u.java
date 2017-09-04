package com.qrcomic.downloader;

import android.text.TextUtils;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.a;
import com.qrcomic.entity.f;
import com.qrcomic.manager.QRComicManager.c;
import com.qrcomic.manager.b;
import com.qrcomic.util.g;
import com.qrcomic.util.j;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* compiled from: QRComicSectionTask */
public class u extends a implements x, c {
    public int c = 0;
    public String d = "";
    public ArrayList<a> e = new ArrayList();
    public f f;
    public s g;
    public s h;
    public s i;
    public s j;
    public s k;
    public s l;
    public s m;
    public s n;
    public AtomicInteger o = new AtomicInteger(0);
    public AtomicInteger p = new AtomicInteger(0);
    public AtomicInteger q = new AtomicInteger(0);
    public AtomicLong r = new AtomicLong(0);
    public long s = 102400;
    public int t = 0;
    public long u = 0;
    public long v = 0;
    public long w = 0;
    public String x;

    public u(f fVar) throws IllegalArgumentException {
        if (fVar == null || TextUtils.isEmpty(fVar.a) || TextUtils.isEmpty(fVar.b)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("param error");
            if (fVar == null) {
                stringBuffer.append(" comicSection=null,");
            } else {
                stringBuffer.append(TextUtils.isEmpty(fVar.a) ? " comicId=null," : " comicId=" + fVar.a);
                stringBuffer.append(TextUtils.isEmpty(fVar.b) ? " sectionId=null," : " sectionId=" + fVar.b);
            }
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        this.f = fVar;
        this.x = b.a().b().a();
        this.h = new r(this);
        this.i = new o(this);
        this.j = new q(this);
        this.k = new k(this);
        this.l = new n(this);
        this.m = new m(this);
        this.n = new l(this);
        this.g = this.h;
        this.c = 0;
        this.d = "";
    }

    public String i() {
        if (this.f != null) {
            return this.f.a();
        }
        return null;
    }

    public boolean m() {
        Object obj = this.f.a;
        String str = this.f.b;
        if (TextUtils.isEmpty(obj) || !obj.startsWith("0064_")) {
            return false;
        }
        return true;
    }

    public int n() {
        switch (this.g.b()) {
            case 4:
                return 101;
            case 5:
                return 102;
            case 6:
                return 103;
            case 7:
                return 104;
            default:
                return 100;
        }
    }

    public boolean o() {
        if (this.v <= 0) {
            return false;
        }
        boolean z = System.currentTimeMillis() - this.v > 30000;
        int b = this.g.b();
        boolean z2;
        if (b == 3 || b == 4) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z && r3) {
            a(204, "task time out dispatch");
        }
        if (z && r3) {
            return true;
        }
        return false;
    }

    public int p() {
        int i = this.q.get();
        int i2 = this.o.get();
        if (i2 > 0) {
            return (i * 100) / i2;
        }
        return 0;
    }

    public void a(boolean z) {
        d b = d.b();
        String str = this.f.a;
        String str2 = this.f.b;
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            t tVar = new t();
            tVar.a = str2;
            tVar.b = n();
            tVar.c = Math.max(this.u, this.r.get());
            tVar.d = this.f.d;
            tVar.e = this.s;
            tVar.f = this.w;
            b.a(str, str2, tVar);
        }
    }

    public void a(s sVar, boolean z) {
        if (z && g.a()) {
            int i = this.f.f;
            String str = this.f.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("setState comicId=" + str);
            stringBuilder.append(",sectionIndex=" + i);
            stringBuilder.append(",oldState=" + this.g.c());
            stringBuilder.append(",newState=" + sVar.c());
            g.b("qqcomic.downloader.QRComicSectionTask", g.d, stringBuilder.toString());
        }
        this.g = sVar;
        a(z);
    }

    public void a(boolean z, boolean z2) {
        if (z && g.a()) {
            int i = this.f.f;
            String str = this.f.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("startDownload comicId=" + str);
            stringBuilder.append(",sectionIndex=" + i);
            stringBuilder.append(",state=" + this.g.c());
            g.b("qqcomic.downloader.QRComicSectionTask", g.d, stringBuilder.toString());
        }
        this.g.a(z, z2);
    }

    public void b(boolean z, boolean z2) {
        if (z && g.a()) {
            int i = this.f.f;
            String str = this.f.a;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("pauseDownload comicId=" + str);
            stringBuilder.append(",sectionIndex=" + i);
            stringBuilder.append(",state=" + this.g.c());
            g.b("qqcomic.downloader.QRComicSectionTask", g.d, stringBuilder.toString());
        }
        this.g.b(z, z2);
    }

    public void q() {
        this.g.a();
    }

    public void r() {
        int i = this.q.get();
        int i2 = this.p.get();
        int i3 = this.o.get();
        int i4 = this.f.f;
        String str = this.f.a;
        if (g.a()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onSectionTaskProgress comicId=" + str);
            stringBuilder.append(",sectionIndex=" + i4);
            stringBuilder.append(",state=" + this.g.c());
            stringBuilder.append(",successNum" + i2);
            stringBuilder.append(",errNum=" + this.d);
            stringBuilder.append(",taskNum=" + i3);
            g.b("qqcomic.downloader.QRComicSectionTask", g.d, stringBuilder.toString());
        }
        if (i + i2 < i3) {
            i = Math.max(p(), this.t);
            this.g.a((this.f.d * ((long) i)) / 100, i);
        } else if (i2 > 0) {
            this.c = 203;
            this.d = "child task fail and taskNum=" + i3 + ",successNum=" + i + ",errNum=" + i2;
            a(this.c, this.d);
        } else {
            a(i3, i);
        }
    }

    public void a(int i, String str) {
        String str2;
        int i2 = this.f.f;
        String str3 = this.f.a;
        if (g.a()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onSectionTaskError comicId=" + str3);
            stringBuilder.append(",sectionIndex=" + i2);
            stringBuilder.append(",state=" + this.g.c());
            stringBuilder.append(",errCode=" + i);
            stringBuilder.append(",errMsg=" + str);
            g.b("qqcomic.downloader.QRComicSectionTask", g.d, stringBuilder.toString());
        }
        this.c = i;
        this.d = str;
        this.g.a(i, str);
        if (j.a() && j.b() < 10485760) {
            d.b().a(101);
        }
        if (com.qrcomic.util.f.b(b.a().b().b())) {
            str2 = "1";
        } else {
            str2 = "0";
        }
        if (d.a(str3)) {
            str2 = "video";
        } else {
            str2 = "comic";
        }
    }

    public void a(int i, int i2) {
        String str = this.f.a;
        int i3 = this.f.f;
        if (g.a()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onChildTaskError comicId=" + str);
            stringBuilder.append(",sectionIndex=" + i3);
            stringBuilder.append(",errCode=" + this.c);
            stringBuilder.append(",errMsg=" + this.d);
            stringBuilder.append(",taskNum=" + i);
            stringBuilder.append(",successNum=" + i2);
            g.b("qqcomic.downloader.QRComicSectionTask", g.d, stringBuilder.toString());
        }
        this.g.a(i, i2);
    }

    public void a(a aVar, int i) {
    }

    public void a(List<ComicSectionPicInfo> list, String str, String str2) {
        int i = this.f.f;
        if (g.a()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onSectionPicInfo comicId=" + str);
            stringBuilder.append(",sectionIndex=" + i);
            stringBuilder.append(",state=" + this.g.c());
            stringBuilder.append(",picNum=" + (list != null ? list.size() : 0));
            g.b("qqcomic.downloader.QRComicSectionTask", g.d, stringBuilder.toString());
        }
        this.g.a(list, str, str2);
    }

    public synchronized void b(a aVar) {
        this.v = System.currentTimeMillis();
        this.q.incrementAndGet();
        if (aVar instanceof g) {
            this.s = ((g) aVar).e;
        }
        r();
    }

    public synchronized void a(a aVar, int i, String str) {
        this.v = System.currentTimeMillis();
        int i2 = this.f.f;
        String str2 = this.f.a;
        if (g.a()) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("onChildTaskError comicId=" + str2);
            stringBuilder.append(",sectionIndex=" + i2);
            stringBuilder.append(",errCode=" + i);
            stringBuilder.append(",errMsg=" + str);
            stringBuilder.append(",taskType=" + aVar.a);
            g.b("qqcomic.downloader.QRComicSectionTask", g.d, stringBuilder.toString());
        }
        this.p.incrementAndGet();
        a(i, str);
    }

    public void run() {
    }

    public void l() {
    }
}
