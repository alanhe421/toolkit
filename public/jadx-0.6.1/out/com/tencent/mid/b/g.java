package com.tencent.mid.b;

import android.content.Context;
import com.tencent.mid.api.MidEntity;
import com.tencent.mid.util.Util;
import com.tencent.mid.util.f;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class g {
    private static g h = null;
    Map<Integer, f> a = null;
    MidEntity b = null;
    Map<Integer, f> c = null;
    boolean d = true;
    private Map<Integer, f> e = null;
    private Context f = null;
    private f g = Util.getLogger();
    private MidEntity i = null;

    private g(Context context) {
        this.f = context.getApplicationContext();
        this.e = new HashMap(3);
        this.e.put(Integer.valueOf(1), new e(context, 3));
        this.e.put(Integer.valueOf(2), new c(context, 3));
        this.e.put(Integer.valueOf(4), new d(context, 3));
    }

    private MidEntity a(int i, Map<Integer, f> map) {
        if (this.e != null) {
            f fVar = (f) map.get(Integer.valueOf(i));
            if (fVar != null) {
                return fVar.i();
            }
        }
        return null;
    }

    public static synchronized g a(Context context) {
        g gVar;
        synchronized (g.class) {
            if (h == null) {
                h = new g(context);
            }
            gVar = h;
        }
        return gVar;
    }

    private Map<Integer, f> l() {
        if (this.a == null) {
            this.a = new HashMap(3);
            this.a.put(Integer.valueOf(1), new e(this.f, 1000001));
            this.a.put(Integer.valueOf(2), new c(this.f, 1000001));
            this.a.put(Integer.valueOf(4), new d(this.f, 1000001));
        }
        return this.a;
    }

    private Map<Integer, f> m() {
        if (this.c == null) {
            this.c = new HashMap(3);
            this.c.put(Integer.valueOf(1), new e(this.f, 0));
            this.c.put(Integer.valueOf(2), new c(this.f, 0));
            this.c.put(Integer.valueOf(4), new d(this.f, 0));
        }
        return this.c;
    }

    public MidEntity a() {
        l();
        if (!Util.isMidValid(this.b)) {
            this.b = a(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(4), Integer.valueOf(1), Integer.valueOf(2)})), this.a);
        }
        this.g.h("readNewVersionMidEntity:" + this.b);
        return this.b;
    }

    public MidEntity a(List<Integer> list) {
        return a((List) list, this.e);
    }

    public MidEntity a(List<Integer> list, Map<Integer, f> map) {
        if (list == null || list.size() == 0 || map == null || map.size() == 0) {
            return null;
        }
        for (Integer num : list) {
            f fVar = (f) map.get(num);
            if (fVar != null) {
                MidEntity i = fVar.i();
                if (i != null && i.isMidValid()) {
                    return i;
                }
            }
        }
        return null;
    }

    public void a(int i, int i2) {
        a k = k();
        if (i > 0) {
            k.c(i);
        }
        if (i2 > 0) {
            k.a(i2);
        }
        k.a(System.currentTimeMillis());
        k.b(0);
        a(k);
    }

    public void a(MidEntity midEntity) {
        a(midEntity, true);
    }

    public void a(MidEntity midEntity, boolean z) {
        if (midEntity.getTimestamps() <= 0) {
            midEntity.setTimestamps(System.currentTimeMillis());
        }
        this.g.h("writeNewVersionMidEntity midEntity:" + midEntity);
        for (Entry value : l().entrySet()) {
            ((f) value.getValue()).a(midEntity);
        }
        if (z && this.f != null) {
            Util.insertMid2Provider(this.f, this.f.getPackageName(), midEntity.toString());
        }
    }

    public void a(a aVar) {
        if (aVar.a() <= 0) {
            aVar.a(System.currentTimeMillis());
        }
        for (Entry value : this.e.entrySet()) {
            ((f) value.getValue()).b(aVar);
        }
    }

    public a b(List<Integer> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        for (Integer num : list) {
            f fVar = (f) this.e.get(num);
            if (fVar != null) {
                a j = fVar.j();
                if (j != null) {
                    return j;
                }
            }
        }
        return null;
    }

    public String b() {
        a();
        return Util.isMidValid(this.b) ? this.b.getMid() : "";
    }

    public void b(MidEntity midEntity) {
        l();
        f fVar = (f) this.a.get(Integer.valueOf(4));
        if (fVar != null) {
            fVar.a(midEntity);
        }
    }

    public void b(MidEntity midEntity, boolean z) {
        if (midEntity.getTimestamps() <= 0) {
            midEntity.setTimestamps(System.currentTimeMillis());
        }
        for (Entry value : this.e.entrySet()) {
            ((f) value.getValue()).a(midEntity);
        }
        if (z && this.f != null) {
            Util.insertMid2OldProvider(this.f, this.f.getPackageName(), midEntity.toString());
        }
    }

    public MidEntity c() {
        return a(4, l());
    }

    public void c(MidEntity midEntity) {
        l();
        f fVar = (f) this.a.get(Integer.valueOf(1));
        if (fVar != null) {
            fVar.a(midEntity);
        }
        fVar = (f) this.a.get(Integer.valueOf(2));
        if (fVar != null) {
            fVar.a(midEntity);
        }
    }

    public MidEntity d() {
        return a(1, l());
    }

    public void d(MidEntity midEntity) {
        f fVar = (f) this.e.get(Integer.valueOf(4));
        if (fVar != null) {
            fVar.a(midEntity);
        }
    }

    public MidEntity e() {
        return a(2, l());
    }

    public void e(MidEntity midEntity) {
        f fVar = (f) this.e.get(Integer.valueOf(1));
        if (fVar != null) {
            fVar.a(midEntity);
        }
        fVar = (f) this.e.get(Integer.valueOf(2));
        if (fVar != null) {
            fVar.a(midEntity);
        }
    }

    public String f() {
        try {
            g();
            if (this.i != null) {
                return this.i.getMid();
            }
        } catch (Throwable th) {
            this.g.f("readMidString " + th);
        }
        return "0";
    }

    public void f(MidEntity midEntity) {
        if (midEntity.getTimestamps() <= 0) {
            midEntity.setTimestamps(System.currentTimeMillis());
        }
        for (Entry value : this.e.entrySet()) {
            ((f) value.getValue()).a(midEntity);
        }
    }

    public MidEntity g() {
        MidEntity a;
        if (!Util.isMidValid(this.i)) {
            this.g.h("read the new one");
            this.i = a(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(4)})), this.e);
        }
        if (!Util.isMidValid(this.i)) {
            this.g.h("load from the old one");
            a = a(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(4)})), m());
            if (Util.isMidValid(a)) {
                this.g.d("copy old mid:" + a.getMid() + " to new version.");
                this.i = a;
                f(this.i);
            }
        }
        if (!Util.isMidValid(this.i)) {
            this.g.h("query other app");
            Map midsByApps = Util.getMidsByApps(this.f, 2);
            if (midsByApps != null && midsByApps.size() > 0) {
                for (Entry value : midsByApps.entrySet()) {
                    a = (MidEntity) value.getValue();
                    if (a != null && a.isMidValid()) {
                        this.i = a;
                        break;
                    }
                }
            }
        }
        if (!Util.isMidValid(this.i)) {
            this.g.h("read the new one");
            this.i = a(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(4), Integer.valueOf(1), Integer.valueOf(2)})), this.e);
        }
        if (!Util.isMidValid(this.i)) {
            this.g.h("load from the old one");
            a = a(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(2), Integer.valueOf(4)})), m());
            if (Util.isMidValid(a)) {
                this.g.d("copy old mid:" + a.getMid() + " to new version.");
                this.i = a;
                f(this.i);
            }
        }
        if (this.d) {
            this.g.h("firstRead");
            a = h();
            if (a == null || !a.isMidValid()) {
                d(this.i);
            }
            this.d = false;
        }
        return this.i != null ? this.i : new MidEntity();
    }

    public void g(MidEntity midEntity) {
        if (midEntity.getTimestamps() <= 0) {
            midEntity.setTimestamps(System.currentTimeMillis());
        }
        for (Entry value : m().entrySet()) {
            ((f) value.getValue()).a(midEntity);
        }
    }

    public MidEntity h() {
        return a(4, this.e);
    }

    public MidEntity i() {
        return a(1, this.e);
    }

    public MidEntity j() {
        return a(2, this.e);
    }

    public a k() {
        return b(new ArrayList(Arrays.asList(new Integer[]{Integer.valueOf(1), Integer.valueOf(4)})));
    }
}
