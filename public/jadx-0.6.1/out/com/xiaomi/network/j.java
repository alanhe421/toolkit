package com.xiaomi.network;

import java.util.ArrayList;
import java.util.Iterator;

class j extends b {
    b i;
    final /* synthetic */ b j;
    final /* synthetic */ f k;

    j(f fVar, String str, b bVar) {
        this.k = fVar;
        this.j = bVar;
        super(str);
        this.i = this.j;
        this.b = this.b;
        if (this.j != null) {
            this.f = this.j.f;
        }
    }

    public synchronized ArrayList<String> a(boolean z) {
        ArrayList<String> arrayList;
        arrayList = new ArrayList();
        if (this.i != null) {
            arrayList.addAll(this.i.a(true));
        }
        synchronized (f.b) {
            b bVar = (b) f.b.get(this.b);
            if (bVar != null) {
                Iterator it = bVar.a(true).iterator();
                while (it.hasNext()) {
                    String str = (String) it.next();
                    if (arrayList.indexOf(str) == -1) {
                        arrayList.add(str);
                    }
                }
                arrayList.remove(this.b);
                arrayList.add(this.b);
            }
        }
        return arrayList;
    }

    public synchronized void a(String str, a aVar) {
        if (this.i != null) {
            this.i.a(str, aVar);
        }
    }

    public boolean b() {
        return false;
    }
}
