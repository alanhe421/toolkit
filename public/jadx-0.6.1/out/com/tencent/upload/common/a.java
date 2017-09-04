package com.tencent.upload.common;

import android.os.Handler;
import com.tencent.upload.b.b.a.c;
import com.tencent.upload.b.b.a.d;
import com.tencent.upload.b.b.a.e;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class a {
    private static Object a = new Object();
    private static a b = null;
    private static HashMap<String, String> e = null;
    private static HashMap<String, a> f = null;
    private String c;
    private ReadWriteLock d = new ReentrantReadWriteLock();

    public interface b {
        void a();
    }

    public static class a {
        private Handler a;
        private b b;

        public a(Handler handler, b bVar) {
            this.a = handler;
            this.b = bVar;
        }

        public static void a(g gVar) {
            com.tencent.upload.b.a aVar;
            if (gVar == null) {
                aVar = null;
            } else {
                switch (f.a[gVar.l.ordinal()]) {
                    case 1:
                        aVar = new c();
                        break;
                    case 2:
                        aVar = new com.tencent.upload.b.b.a.a();
                        break;
                    case 3:
                        aVar = new e();
                        break;
                    case 4:
                        aVar = new com.tencent.upload.b.b.a.b();
                        break;
                    default:
                        aVar = new d();
                        break;
                }
                aVar.c = gVar.d;
                aVar.h = gVar.a;
                aVar.i = gVar.c;
                aVar.m = gVar.e;
                aVar.o = gVar.b;
                aVar.b = gVar.f;
                aVar.f = gVar.g;
                aVar.g = gVar.h;
                aVar.k = gVar.i;
                aVar.l = 0;
                aVar.n = gVar.k;
            }
            com.tencent.upload.b.a.b.a(aVar);
        }

        public static void a(h hVar) {
            com.tencent.upload.b.a aVar;
            if (hVar == null) {
                aVar = null;
            } else {
                switch (f.a[hVar.a.ordinal()]) {
                    case 1:
                        aVar = new com.tencent.upload.b.b.b.c();
                        break;
                    case 2:
                        aVar = new com.tencent.upload.b.b.b.a();
                        break;
                    case 3:
                        aVar = new com.tencent.upload.b.b.b.e();
                        break;
                    case 4:
                        aVar = new com.tencent.upload.b.b.b.b();
                        break;
                    default:
                        aVar = new com.tencent.upload.b.b.b.d();
                        break;
                }
                aVar.c = hVar.d;
                aVar.h = hVar.b;
                aVar.i = hVar.c;
                aVar.m = hVar.f;
                aVar.e = hVar.g;
                aVar.b = hVar.e;
                aVar.f = hVar.h;
                aVar.g = hVar.i;
                aVar.j = hVar.n;
                aVar.k = hVar.k;
                aVar.l = hVar.o;
                aVar.o = hVar.j ? 1 : 0;
                aVar.p = (int) hVar.l;
                aVar.q = (int) hVar.m;
                aVar.n = hVar.p;
            }
            com.tencent.upload.b.a.b.a(aVar);
        }

        public static void a(String str, String str2) {
            com.tencent.upload.log.b.a(str, str2, null);
        }

        public static void b(String str, String str2) {
            com.tencent.upload.log.b.b(str, str2, null);
        }

        public static void c(String str, String str2) {
            com.tencent.upload.log.b.c(str, str2, null);
        }

        public static void d(String str, String str2) {
            com.tencent.upload.log.b.d(str, str2, null);
        }

        public final void a() {
            if (this.a != null) {
                this.a.post(new b(this));
            }
        }
    }

    private a() {
        e = new HashMap();
        f = new HashMap();
    }

    public static synchronized a a() {
        a aVar;
        synchronized (a.class) {
            if (b == null) {
                synchronized (a) {
                    if (b == null) {
                        b = new a();
                    }
                }
            }
            aVar = b;
        }
        return aVar;
    }

    private String a(String str) {
        String str2;
        this.d.readLock().lock();
        try {
            str2 = (String) e.get(str);
        } catch (Throwable th) {
            return null;
        } finally {
            this.d.readLock().unlock();
        }
        return str2;
    }

    public static boolean a(String str, Handler handler, b bVar) {
        if (str == null || handler == null || bVar == null) {
            return false;
        }
        a aVar = new a(handler, bVar);
        synchronized (f) {
            if (f.containsKey(str)) {
                return false;
            }
            f.put(str, aVar);
            return true;
        }
    }

    private static boolean c() {
        synchronized (f) {
            for (String str : f.keySet()) {
                a aVar = (a) f.get(str);
                if (aVar != null) {
                    aVar.a();
                }
            }
        }
        return true;
    }

    public final int a(String str, int i) {
        try {
            i = Integer.parseInt(a(str));
        } catch (Throwable th) {
        }
        return i;
    }

    public final long a(String str, long j) {
        try {
            return Long.parseLong(a(str));
        } catch (Throwable th) {
            return 100;
        }
    }

    public final String a(String str, String str2) {
        try {
            String a = a(str);
            return a != null ? a : str2;
        } catch (Throwable th) {
            return str2;
        }
    }

    public final void a(Map<String, String> map, String str) {
        if (map != null && map.size() > 0) {
            a.b("UploadConfig", "***************config update begin***************");
            this.d.writeLock().lock();
            try {
                this.c = str;
                e.putAll(map);
                for (Entry entry : map.entrySet()) {
                    a.b("UploadConfig", ((String) entry.getKey()) + ":" + ((String) entry.getValue()));
                }
            } catch (Throwable th) {
            } finally {
                this.d.writeLock().unlock();
            }
            a.b("UploadConfig", "***************config update end***************");
            c();
        }
    }

    public final String b() {
        return this.c;
    }
}
