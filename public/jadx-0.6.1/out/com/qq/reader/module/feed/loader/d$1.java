package com.qq.reader.module.feed.loader;

import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.bookstore.qnative.storage.disk.a;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.qq.reader.module.feed.data.impl.e;
import java.lang.ref.WeakReference;
import java.util.Iterator;

/* compiled from: FeedDataStyleBLoader */
class d$1 implements a {
    final /* synthetic */ e a;
    final /* synthetic */ WeakReference b;
    final /* synthetic */ d c;

    d$1(d dVar, e eVar, WeakReference weakReference) {
        this.c = dVar;
        this.a = eVar;
        this.b = weakReference;
    }

    public void onLoadSucess(Object obj) {
        c.e("FeedTimeStyleBUtil", "loadDataWithDisk OK...");
        Iterator it;
        if (this.a.j() == 2) {
            c.e("FeedTimeStyleBUtil", "FeedDataPackage.OPT_ENTER ...... AUTO..... DOWN");
            if (this.a.h().size() > 0) {
                it = this.a.h().iterator();
                while (it.hasNext()) {
                    ((FeedBaseCard) it.next()).mIsFromNet = false;
                }
                d.a(this.c, this.b, this.a, false);
            } else {
                d.a(this.c, this.b, this.a);
            }
            f i = this.a.i();
            if (i != null) {
                String str = i.a;
                int i2 = i.b;
            }
        } else if (this.a.h().size() == 0) {
            d.a(this.c, this.b, this.a);
        } else {
            d.a(this.c, this.a, "DISK");
            it = this.a.h().iterator();
            while (it.hasNext()) {
                ((FeedBaseCard) it.next()).mIsFromNet = false;
            }
            d.a(this.c, this.b, this.a, false);
        }
    }

    public void onLoadFailed(Object obj) {
        c.e("FeedTimeStyleBUtil", "loadDataWithDisk ERROR...");
        d.a(this.c, this.b, this.a, 0);
    }
}
