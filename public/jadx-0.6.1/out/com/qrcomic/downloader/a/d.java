package com.qrcomic.downloader.a;

import android.text.TextUtils;
import com.qqcomic.bitmaphelper.CompactBitmapFactory;
import com.qqcomic.bitmaphelper.b;
import com.qrcomic.entity.ComicSectionPicInfo;
import com.qrcomic.util.g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

/* compiled from: QRComicMemoryCache */
public class d<K extends a, V> {
    private long a;
    private HashMap<String, V> b = new HashMap();
    private HashMap<String, K> c = new HashMap();
    private int d;
    private AtomicBoolean e = new AtomicBoolean(false);

    /* compiled from: QRComicMemoryCache */
    public static class a {
        public String a;
        public long b = 0;
        public long c = 0;
        public ComicSectionPicInfo d;

        public a(String str, long j, long j2) {
            if (TextUtils.isEmpty(str) || j < 0 || j2 < 0) {
                StringBuilder append = new StringBuilder().append("argument error key=");
                if (str == null) {
                    str = "null";
                }
                throw new IllegalArgumentException(append.append(str).append(",queueSeq=").append(j).append(",queueIndex=").append(j2).toString());
            }
            this.a = str;
            this.b = j;
            this.c = j2;
        }

        public void a(ComicSectionPicInfo comicSectionPicInfo) {
            this.d = comicSectionPicInfo;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof a)) {
                return false;
            }
            return TextUtils.equals(this.a, ((a) obj).a);
        }

        public String toString() {
            return "mKey=" + this.a + ",mQueueSeq=" + this.b + ",mQueueIndex=" + this.c;
        }
    }

    public d(long j) {
        this.a = j;
    }

    public long a() {
        return (long) this.d;
    }

    public int b() {
        return this.b.size();
    }

    protected int a(K k, V v) {
        if (v instanceof b) {
            return ((b) v).d();
        }
        return 524288;
    }

    public void c() {
        this.e.set(true);
    }

    public boolean d() {
        return this.e.get();
    }

    public void a(long j) {
        if (this.d > 0 && !this.b.isEmpty() && !this.c.isEmpty() && ((long) this.d) > j) {
            if (g.a()) {
                g.b("qqcomic.downloader.cache.QRComicMemoryCache", g.d, "vipcomic memcache trimToSize to " + j + ",current size=" + this.d + ",map size=" + this.b.size() + ",key size=" + this.c.size());
            }
            synchronized (this) {
                ArrayList arrayList = new ArrayList(this.c.values());
                Collections.sort(arrayList, new Comparator<K>(this) {
                    final /* synthetic */ d a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ int compare(Object obj, Object obj2) {
                        return a((a) obj, (a) obj2);
                    }

                    public int a(K k, K k2) {
                        if (k.b != k2.b) {
                            if (k.b > k2.b) {
                                return -1;
                            }
                            return 1;
                        } else if (k.c >= k2.c) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                });
                if (g.a()) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("[");
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        stringBuffer.append(((a) it.next()).d.index).append(",");
                    }
                    stringBuffer.append("]");
                    g.a("qqcomic.downloader.cache.QRComicMemoryCache", g.d, stringBuffer.toString());
                }
                int size = arrayList.size();
                String str = "";
                while (this.d > 0 && ((long) this.d) > j && size > 0) {
                    int i = size - 1;
                    a aVar = (a) arrayList.get(i);
                    if (aVar != null) {
                        String str2 = aVar.a;
                        Object remove = this.b.remove(str2);
                        this.c.remove(str2);
                        if (remove != null) {
                            this.d -= d(aVar, remove);
                            b(aVar, remove);
                        }
                    }
                    size = i;
                }
            }
        }
    }

    protected void b(K k, V v) {
        if (v instanceof b) {
            ((b) v).close();
            if (k.d != null) {
                k.d.bitmap = null;
            }
        }
    }

    private int d(K k, V v) {
        int a = a((a) k, (Object) v);
        if (a >= 0) {
            return a;
        }
        throw new IllegalStateException("Negative size: " + k + "=" + v);
    }

    public V a(K k) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            V v = this.b.get(k.a);
            if (v != null) {
                this.c.put(k.a, k);
                return v;
            }
            return null;
        }
    }

    public boolean c(K k, V v) {
        if (this.e.get()) {
            return false;
        }
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            this.b.put(k.a, v);
            this.d += d(k, v);
            this.c.put(k.a, k);
            a(this.a);
        }
        return true;
    }

    public boolean a(K k, boolean z) {
        if (k == null || !this.b.containsKey(k.a)) {
            return false;
        }
        synchronized (this) {
            Object remove = this.b.remove(k.a);
            if (remove != null) {
                this.c.remove(k.a);
                this.d -= d(k, remove);
            }
        }
        if (remove == null) {
            return false;
        }
        if (z) {
            b(k, remove);
        }
        return true;
    }

    public void e() {
        a(-1);
        System.gc();
        CompactBitmapFactory.a();
    }

    public long[] f() {
        long j = Long.MAX_VALUE;
        long j2 = Long.MIN_VALUE;
        synchronized (this) {
            for (a aVar : this.c.values()) {
                long j3;
                if (aVar.b < j) {
                    j = aVar.b;
                }
                if (aVar.b > j2) {
                    j3 = aVar.b;
                } else {
                    j3 = j2;
                }
                j2 = j3;
            }
        }
        return new long[]{j, j2};
    }

    public long g() {
        return this.a;
    }
}
