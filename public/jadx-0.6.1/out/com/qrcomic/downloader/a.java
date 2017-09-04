package com.qrcomic.downloader;

import android.text.TextUtils;
import com.qrcomic.a.d;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/* compiled from: QRComicAbstractTask */
public abstract class a extends d {
    protected int a = 200;
    public HashSet<WeakReference<x>> b = new HashSet();
    private long c = 0;
    private long d = 0;
    private long e;

    /* compiled from: QRComicAbstractTask */
    public static class a implements Comparator<Object> {
        public int compare(Object obj, Object obj2) {
            if (!(obj instanceof a) || !(obj2 instanceof a)) {
                return -1;
            }
            a aVar = (a) obj;
            a aVar2 = (a) obj2;
            if (aVar.a != aVar2.a) {
                if (aVar.a > aVar2.a) {
                    return -1;
                }
                return 1;
            } else if (aVar.a == 300) {
                if (aVar.c != aVar2.c) {
                    if (aVar.c <= aVar2.c) {
                        return 1;
                    }
                    return -1;
                } else if (aVar.k() >= aVar2.k()) {
                    return 1;
                } else {
                    return -1;
                }
            } else if ((aVar.a == 200 || aVar.a == 100) && aVar.k() >= aVar2.k()) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public abstract String i();

    public abstract void l();

    public long h() {
        return this.e;
    }

    public void a(long j) {
        this.e = j;
    }

    public a() {
        b(2);
    }

    public long j() {
        return this.c;
    }

    public void b(long j) {
        this.c = j;
    }

    public void c(long j) {
        this.d = j;
    }

    public long k() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        return TextUtils.equals(i(), ((a) obj).i());
    }

    public void a(WeakReference<x> weakReference) {
        if (weakReference != null) {
            Set<WeakReference> synchronizedSet = Collections.synchronizedSet(this.b);
            synchronized (synchronizedSet) {
                Object obj;
                x xVar = (x) weakReference.get();
                for (WeakReference weakReference2 : synchronizedSet) {
                    if (weakReference2 != null) {
                        x xVar2 = (x) weakReference2.get();
                        if (!(xVar == null || xVar2 == null || !xVar.equals(xVar2))) {
                            obj = 1;
                            break;
                        }
                    }
                }
                obj = null;
                if (obj == null && xVar != null) {
                    this.b.add(weakReference);
                }
            }
        }
    }
}
