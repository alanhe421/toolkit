package com.qrcomic.activity.reader.b;

import android.os.Bundle;
import com.qrcomic.activity.reader.a.c;
import com.qrcomic.e.b.d;
import com.qrcomic.entity.l;
import com.qrcomic.entity.n;
import com.qrcomic.util.g;
import com.qrcomic.util.h.a;
import java.util.LinkedList;
import java.util.List;

/* compiled from: QRRequestPayedSectionsStep */
public class b extends e {
    c a = new c(this) {
        final /* synthetic */ b a;

        {
            this.a = r1;
        }

        public void a(d dVar) {
            this.a.f.m = true;
            List list = dVar.a;
            if (list != null && list.size() > 0) {
                l lVar = (l) list.get(0);
                String str = "";
                try {
                    str = com.qrcomic.manager.b.a().b().a();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                this.a.f.A = a.b(this.a.f.n, str);
                if (lVar.e != null) {
                    if (this.a.f.y == null) {
                        this.a.f.y = new LinkedList();
                    }
                    for (n nVar : lVar.e) {
                        if (nVar.b == 1 && !this.a.f.y.contains(nVar.a)) {
                            this.a.f.y.add(nVar.a);
                        }
                    }
                } else if (g.a()) {
                    g.a("comic_reader_startup", g.d, "QRRequestPayedSectionsStep : buyInfo.sectionBuyStatusList is null");
                }
            } else if (g.a()) {
                g.a("comic_reader_startup", g.d, "QRRequestPayedSectionsStep : queryUserBuyInfoPac.infoList is null");
            }
            if (dVar.c != null && dVar.c.getBoolean("need_load_comic_data", false)) {
                e.b(1, this.a.f);
            }
        }

        public void a(com.qrcomic.e.b.c cVar) {
            if (cVar.c != null && cVar.c.getBoolean("need_load_comic_data", false)) {
                e.b(1, this.a.f);
            }
        }
    };

    public void a(Bundle bundle) {
        if (g.a()) {
            g.a("comic_reader_startup", g.d, "QRRequestPayedSectionsStep doStep");
        }
        this.h.a(this.f.n, this.a, bundle);
    }
}
