package com.tencent.beacon.net;

import com.tencent.beacon.c.e.b;
import com.tencent.beacon.c.e.c;
import com.tencent.beacon.c.e.e;
import com.tencent.beacon.c.e.f;
import com.tencent.beacon.f.a;
import com.tencent.beacon.upload.g;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* compiled from: ProGuard */
public final class d implements g {
    public final void a(int i, byte[] bArr, boolean z) {
        if (i == 105 && bArr != null) {
            SpeedMonitorModule instance = SpeedMonitorModule.getInstance();
            if (instance != null && SpeedMonitorModule.d()) {
                try {
                    e eVar = new e();
                    eVar.a(new a(bArr));
                    List a = a(eVar);
                    if (a != null && a.size() > 0) {
                        instance.a((b[]) a.toArray(new b[0]));
                    }
                } catch (Throwable th) {
                    com.tencent.beacon.e.a.a(th);
                    com.tencent.beacon.e.a.d(" process sm strategy error: %s", new Object[]{th.toString()});
                }
            }
        }
    }

    public static List<b> a(e eVar) {
        if (eVar == null) {
            com.tencent.beacon.e.a.b("SpeedMonitorStrategy sourcePackage is null", new Object[0]);
            return null;
        }
        List<c> list = eVar.a;
        List<b> list2 = eVar.b;
        List<f> list3 = eVar.c;
        List<b> arrayList = new ArrayList();
        if (list != null) {
            com.tencent.beacon.e.a.b("ipList size:%d", new Object[]{Integer.valueOf(list.size())});
            for (c cVar : list) {
                b bVar = new b();
                bVar.b(cVar.a + ":" + cVar.b);
                bVar.a(new Date().getTime());
                bVar.a("IP");
                bVar.b(cVar.c);
                arrayList.add(bVar);
            }
        }
        if (list2 != null) {
            com.tencent.beacon.e.a.b("dnsList size:%d", new Object[]{Integer.valueOf(list2.size())});
            for (b bVar2 : list2) {
                b bVar3 = new b();
                bVar3.b(bVar2.a);
                bVar3.a(new Date().getTime());
                bVar3.a("PG");
                bVar3.b(bVar2.c);
                bVar3.a(bVar2.b);
                arrayList.add(bVar3);
            }
        }
        if (list3 != null) {
            com.tencent.beacon.e.a.b("hostList size:%d", new Object[]{Integer.valueOf(list3.size())});
            for (f fVar : list3) {
                b bVar4 = new b();
                bVar4.c(fVar.d);
                bVar4.d(fVar.a + ":" + fVar.b);
                bVar4.b(fVar.c);
                bVar4.a(new Date().getTime());
                bVar4.a("HOST");
                arrayList.add(bVar4);
                com.tencent.beacon.e.a.a(" TxHostSource: " + bVar4.toString(), new Object[0]);
            }
        }
        if (arrayList.size() > 0) {
            return arrayList;
        }
        return null;
    }
}
