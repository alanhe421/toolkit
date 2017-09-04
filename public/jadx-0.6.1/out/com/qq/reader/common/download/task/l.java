package com.qq.reader.common.download.task;

import android.content.Context;
import com.qq.reader.cservice.download.book.b;
import com.qq.reader.cservice.download.game.c;
import com.qq.reader.plugin.audiobook.a;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/* compiled from: TaskModuleCenter */
public class l {
    private static Map<Integer, k> a = new HashMap();
    private static Map<Integer, e> b = new HashMap();
    private static Map<Integer, Set<String>> c = Collections.synchronizedMap(new HashMap());

    static synchronized k a(int i) {
        k kVar;
        synchronized (l.class) {
            if (a.containsKey(Integer.valueOf(i))) {
                kVar = (k) a.get(Integer.valueOf(i));
            } else {
                int i2;
                if (i == 1001) {
                    i2 = 20;
                } else {
                    i2 = 1;
                }
                kVar = new k(i2);
                kVar.a(g(i));
                a.put(Integer.valueOf(i), kVar);
            }
        }
        return kVar;
    }

    public static e b(int i) {
        e eVar = (e) b.get(Integer.valueOf(i));
        if (eVar == null) {
            return d(i);
        }
        return eVar;
    }

    public static j c(int i) {
        return new j();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized com.qq.reader.common.download.task.e d(int r4) {
        /*
        r1 = com.qq.reader.common.download.task.l.class;
        monitor-enter(r1);
        r0 = 0;
        r2 = b;	 Catch:{ all -> 0x002b }
        r3 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x002b }
        r2 = r2.containsKey(r3);	 Catch:{ all -> 0x002b }
        if (r2 == 0) goto L_0x001e;
    L_0x0010:
        r0 = b;	 Catch:{ all -> 0x002b }
        r2 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x002b }
        r0 = r0.get(r2);	 Catch:{ all -> 0x002b }
        r0 = (com.qq.reader.common.download.task.e) r0;	 Catch:{ all -> 0x002b }
    L_0x001c:
        monitor-exit(r1);
        return r0;
    L_0x001e:
        switch(r4) {
            case 1001: goto L_0x0034;
            case 1002: goto L_0x0021;
            case 1003: goto L_0x0021;
            case 1004: goto L_0x002e;
            case 1005: goto L_0x0021;
            case 1006: goto L_0x003a;
            default: goto L_0x0021;
        };
    L_0x0021:
        r2 = b;	 Catch:{ all -> 0x002b }
        r3 = java.lang.Integer.valueOf(r4);	 Catch:{ all -> 0x002b }
        r2.put(r3, r0);	 Catch:{ all -> 0x002b }
        goto L_0x001c;
    L_0x002b:
        r0 = move-exception;
        monitor-exit(r1);
        throw r0;
    L_0x002e:
        r0 = new com.qq.reader.plugin.audiobook.e;	 Catch:{ all -> 0x002b }
        r0.<init>(r4);	 Catch:{ all -> 0x002b }
        goto L_0x0021;
    L_0x0034:
        r0 = new com.qq.reader.cservice.download.book.e;	 Catch:{ all -> 0x002b }
        r0.<init>(r4);	 Catch:{ all -> 0x002b }
        goto L_0x0021;
    L_0x003a:
        r0 = new com.qq.reader.cservice.download.game.a;	 Catch:{ all -> 0x002b }
        r0.<init>();	 Catch:{ all -> 0x002b }
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qq.reader.common.download.task.l.d(int):com.qq.reader.common.download.task.e");
    }

    public static synchronized o a(k kVar, g gVar, Thread thread, Context context) {
        o a;
        synchronized (l.class) {
            a = p.a(kVar, gVar, thread, context);
        }
        return a;
    }

    private static synchronized d g(int i) {
        d dVar;
        synchronized (l.class) {
            dVar = null;
            switch (i) {
                case 1001:
                    dVar = new b();
                    break;
                case 1004:
                    dVar = new a();
                    break;
                case 1006:
                    dVar = new c();
                    break;
            }
        }
        return dVar;
    }

    public static void a() {
        for (e eVar : b.values()) {
            if (!(eVar == null || (eVar instanceof com.qq.reader.cservice.download.game.a))) {
                eVar.c();
            }
        }
        e eVar2 = (e) b.get(Integer.valueOf(1006));
        k kVar = (k) a.get(Integer.valueOf(1006));
        b.clear();
        a.clear();
        if (eVar2 != null) {
            b.put(Integer.valueOf(1006), eVar2);
        }
        if (kVar != null) {
            a.put(Integer.valueOf(1006), kVar);
        }
        try {
            for (Entry value : c.entrySet()) {
                Set set = (Set) value.getValue();
                if (set != null) {
                    set.clear();
                }
            }
            c.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized void e(int i) {
        synchronized (l.class) {
            Set set;
            switch (i) {
                case 1000:
                    if (c.size() > 0) {
                        try {
                            for (Entry value : c.entrySet()) {
                                set = (Set) value.getValue();
                                if (set != null) {
                                    set.clear();
                                }
                            }
                            c.clear();
                            break;
                        } catch (Exception e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                    break;
                case 1001:
                case 1002:
                case 1006:
                    set = (Set) c.get(Integer.valueOf(i));
                    if (set != null) {
                        set.clear();
                        break;
                    }
                    break;
            }
        }
    }

    public static synchronized int f(int i) {
        int i2;
        synchronized (l.class) {
            i2 = 0;
            switch (i) {
                case 1000:
                    try {
                        for (Entry value : c.entrySet()) {
                            int size;
                            Set set = (Set) value.getValue();
                            if (set != null) {
                                size = set.size() + i2;
                            } else {
                                size = i2;
                            }
                            i2 = size;
                        }
                        break;
                    } catch (Exception e) {
                        e.printStackTrace();
                        break;
                    }
                case 1001:
                case 1002:
                case 1006:
                    i2 = ((Set) c.get(Integer.valueOf(i))).size();
                    break;
            }
        }
        return i2;
    }

    public static synchronized void a(int i, String str) {
        synchronized (l.class) {
            switch (i) {
                case 1001:
                case 1002:
                case 1006:
                    Set set = (Set) c.get(Integer.valueOf(i));
                    if (set == null) {
                        set = new HashSet();
                        c.put(Integer.valueOf(i), set);
                    }
                    set.add(str);
                    break;
            }
        }
    }
}
