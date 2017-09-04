package com.tencent.smtt.sdk;

import com.tencent.smtt.utils.r;
import java.io.File;
import java.io.InputStream;
import java.util.Map;

public final class CacheManager {
    @Deprecated
    public static boolean cacheDisabled() {
        aq a = aq.a();
        if (a != null && a.b()) {
            return ((Boolean) a.c().c()).booleanValue();
        }
        Object a2 = r.a("android.webkit.CacheManager", "cacheDisabled");
        return a2 == null ? false : ((Boolean) a2).booleanValue();
    }

    public static InputStream getCacheFile(String str, boolean z) {
        aq a = aq.a();
        return (a == null || !a.b()) ? null : a.c().a(str, z);
    }

    public static Object getCacheFile(String str, Map<String, String> map) {
        aq a = aq.a();
        if (a != null && a.b()) {
            return a.c().g();
        }
        try {
            return r.a(Class.forName("android.webkit.CacheManager"), "getCacheFile", new Class[]{String.class, Map.class}, new Object[]{str, map});
        } catch (Exception e) {
            return null;
        }
    }

    @Deprecated
    public static File getCacheFileBaseDir() {
        aq a = aq.a();
        return (a == null || !a.b()) ? (File) r.a("android.webkit.CacheManager", "getCacheFileBaseDir") : (File) a.c().g();
    }
}
