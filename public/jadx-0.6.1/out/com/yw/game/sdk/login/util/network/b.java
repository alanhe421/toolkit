package com.yw.game.sdk.login.util.network;

import java.util.List;
import java.util.Map;

/* compiled from: HttpResponse */
public class b {
    private final Http a;
    private final int b;
    private final Map<String, List<String>> c;
    private final f d;

    b(Http http, int i, Map<String, List<String>> map, f fVar) {
        this.a = http;
        this.b = i;
        this.c = map;
        this.d = fVar;
    }

    public f a() {
        return this.d;
    }
}
