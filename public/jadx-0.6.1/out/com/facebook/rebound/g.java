package com.facebook.rebound;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SpringConfigRegistry */
public class g {
    private static final g a = new g(true);
    private final Map<f, String> b = new HashMap();

    public static g a() {
        return a;
    }

    g(boolean z) {
        if (z) {
            a(f.c, "default config");
        }
    }

    public boolean a(f fVar, String str) {
        if (fVar == null) {
            throw new IllegalArgumentException("springConfig is required");
        } else if (str == null) {
            throw new IllegalArgumentException("configName is required");
        } else if (this.b.containsKey(fVar)) {
            return false;
        } else {
            this.b.put(fVar, str);
            return true;
        }
    }

    public Map<f, String> b() {
        return Collections.unmodifiableMap(this.b);
    }
}
