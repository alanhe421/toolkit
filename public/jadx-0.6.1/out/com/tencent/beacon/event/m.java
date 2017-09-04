package com.tencent.beacon.event;

import com.tencent.beacon.c.a.b;
import com.tencent.beacon.upload.a;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ProGuard */
public final class m extends a {
    private synchronized void g() {
    }

    private synchronized List<k> h() {
        return null;
    }

    private synchronized void i() {
    }

    public final synchronized b a() {
        return null;
    }

    public static b a(int i, List<k> list) {
        b bVar = null;
        if (list != null && list.size() > 0) {
            try {
                com.tencent.beacon.e.a.b(" current size:" + list.size(), new Object[0]);
                com.tencent.beacon.c.b.b a = a(list);
                if (a != null) {
                    byte[] a2 = a.a();
                    if (a2 != null) {
                        bVar = a(i, a2);
                    }
                }
            } catch (Throwable th) {
                com.tencent.beacon.e.a.a(th);
                com.tencent.beacon.e.a.d(" RealTimeRecordUploadDatas.encode2EventRecordPackage error}", new Object[0]);
            }
        }
        return bVar;
    }

    private static com.tencent.beacon.c.b.b a(List<k> list) {
        if (list == null || list.size() <= 0) {
            return null;
        }
        try {
            com.tencent.beacon.c.b.b bVar = new com.tencent.beacon.c.b.b();
            ArrayList arrayList = new ArrayList();
            for (k e : list) {
                com.tencent.beacon.c.b.a e2 = b.e(e);
                if (e2 != null) {
                    arrayList.add(e2);
                }
            }
            bVar.a = arrayList;
            com.tencent.beacon.e.a.b(" RealTimeRecordUploadDatas.encode2EventRecordPackage() end}", new Object[0]);
            return bVar;
        } catch (Throwable th) {
            com.tencent.beacon.e.a.a(th);
            return null;
        }
    }

    public final void b(boolean z) {
        g();
        h();
        i();
    }
}
