package com.qq.reader.liveshow.b;

import android.content.Context;
import java.util.Map;

/* compiled from: QavRDM */
public class l {
    private static f a = null;

    public static void a(String str, Map<String, String> map, Context context) {
        if (a != null) {
            a.a(str, map, context);
        }
    }

    public static void a(f fVar) {
        a = fVar;
    }
}
