package com.xiaomi.e;

import com.xiaomi.channel.commonutils.b.c;
import com.xiaomi.xmpush.thrift.e;
import java.util.ArrayList;
import java.util.Iterator;

public class b implements Runnable {
    d a;
    String b;

    public b(d dVar) {
        this.a = dVar;
    }

    private void a(ArrayList<e> arrayList, e eVar, String str, String str2) {
        c.c("TinyDataManager is checking and uploading tiny data for " + str + ", the size is " + arrayList.size());
        Object arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            e eVar2 = (e) it.next();
            if (eVar.a(eVar2, str)) {
                arrayList2.add(eVar2);
            }
        }
        if (arrayList2.size() != 0) {
            eVar.a(arrayList2, str, str2);
            Iterator it2 = arrayList2.iterator();
            while (it2.hasNext()) {
                eVar2 = (e) it2.next();
                c.c("Pending Data " + eVar2.toString() + " uploaded by TinyDataManager, reason is " + this.b);
                arrayList.remove(eVar2);
            }
        }
    }

    public void run() {
        c.c("TinyDataManager is checking and uploading tiny data, reason is " + this.b);
        e b = this.a.b();
        if (b != null) {
            for (String str : this.a.d().keySet()) {
                a((ArrayList) this.a.d().get(str), b, str, str);
            }
            for (String str2 : this.a.e().keySet()) {
                a((ArrayList) this.a.e().get(str2), b, "com.xiaomi.xmsf", str2);
            }
        }
    }
}
