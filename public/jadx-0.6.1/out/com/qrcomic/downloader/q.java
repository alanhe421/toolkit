package com.qrcomic.downloader;

import android.text.TextUtils;
import com.qrcomic.a.h;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.entity.i;
import com.qrcomic.manager.QRComicManager;
import com.qrcomic.manager.b;
import java.util.List;

/* compiled from: QRComicSectionQueryUrlState */
public class q extends s {
    public q(u uVar) {
        super(uVar);
        this.b = 3;
        this.c = "QueryUrlState";
    }

    public void a(boolean z, boolean z2) {
    }

    public void a(List<ComicSectionPicInfo> list, String str, String str2) {
        if (list == null || list.size() <= 0) {
            this.a.a(200, "get comic section picInfo error picInfo " + (list == null ? "is null" : "size is zero"));
            return;
        }
        this.a.o.set(list.size());
        this.a.f.q = list;
        h b = b.a().b();
        i a = ((QRComicManager) b.a(1)).a(str, str2, b.a());
        if (a != null) {
            this.a.u = a.g;
            this.a.t = a.i;
        }
        this.a.e.clear();
        d b2 = d.b();
        long andIncrement = b2.d.getAndIncrement();
        long j = 0;
        this.a.a(this.a.k, true);
        for (ComicSectionPicInfo comicSectionPicInfo : list) {
            if (!(comicSectionPicInfo == null || TextUtils.isEmpty(comicSectionPicInfo.picId) || TextUtils.isEmpty(comicSectionPicInfo.picUrl))) {
                long j2 = 1 + j;
                Runnable gVar = new g(andIncrement, j, comicSectionPicInfo, this.a);
                b2.a.execute(gVar);
                this.a.e.add(gVar);
                j = j2;
            }
        }
    }

    public void b(boolean z, boolean z2) {
        this.a.a(this.a.l, z2);
        super.b(z, z2);
    }
}
