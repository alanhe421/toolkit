package com.tencent.upload.network.a;

import android.text.TextUtils;
import com.tencent.upload.common.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class a implements f {
    private List<k> a;
    private List<Integer> b;
    private Iterator<k> c;
    private Iterator<Integer> d;
    private String e;
    private i f;
    private int g = hashCode();
    private String h = getClass().getSimpleName();
    private int i = 0;

    private String e() {
        Object f = j.f();
        if (!TextUtils.isEmpty(f)) {
            return f + "_" + a();
        }
        com.tencent.upload.common.a.a.a(this.h, this.g + " doLoadRecentRouteSet, unknown key");
        return null;
    }

    protected abstract String a();

    public final boolean a(k kVar) {
        String e = e();
        if (e == null) {
            return false;
        }
        if (e == null || e.length() <= 0) {
            return true;
        }
        this.f = j.a(e, kVar);
        return true;
    }

    protected abstract List<k> b();

    public final void c() {
        this.a = b();
        if (this.a == null || this.a.size() == 0) {
            throw new RuntimeException(this.h + this.g + " doInitParams, getUploadRoutes illegel");
        }
        this.b = j.a();
        if (this.b == null || this.b.size() == 0) {
            throw new RuntimeException(this.h + this.g + " doInitParams, getUploadRoutePorts illegel");
        }
        this.c = this.a.iterator();
        this.d = this.b.iterator();
        StringBuffer stringBuffer = new StringBuffer(this.a.size());
        for (k kVar : this.a) {
            stringBuffer.append(kVar.toString() + " ");
        }
        this.i = -1;
        Object obj = (this.e == null || this.e.compareToIgnoreCase(j.e()) != 0) ? 1 : null;
        if (obj != null) {
            this.e = j.e();
            String e = e();
            if (e == null) {
                com.tencent.upload.common.a.a.a(this.h, this.g + " doLoadRecentRouteSet, unknown key");
            } else {
                this.f = new j().a(e);
            }
        }
    }

    public k[] d() {
        k a;
        k kVar;
        this.i++;
        if (this.i == 0) {
            if (this.f != null) {
                a = this.f.a();
                if (a != null) {
                    kVar = a;
                } else {
                    a = this.f.b();
                    if (a != null) {
                        kVar = a;
                    }
                }
                if (kVar != null) {
                    return new k[]{kVar};
                }
            }
            kVar = null;
            if (kVar != null) {
                return new k[]{kVar};
            }
        }
        if (!this.c.hasNext()) {
            return null;
        }
        a = (k) this.c.next();
        this.d = this.b.iterator();
        if (this.d.hasNext()) {
            int intValue;
            List arrayList = new ArrayList();
            while (this.d.hasNext()) {
                intValue = ((Integer) this.d.next()).intValue();
                kVar = a.g();
                kVar.b(intValue);
                arrayList.add(kVar);
            }
            k[] kVarArr = new k[arrayList.size()];
            for (intValue = 0; intValue < arrayList.size(); intValue++) {
                kVarArr[intValue] = (k) arrayList.get(intValue);
            }
            return kVarArr;
        }
        com.tencent.upload.common.a.a.d(this.h, this.g + " there is no port.");
        return null;
    }
}
