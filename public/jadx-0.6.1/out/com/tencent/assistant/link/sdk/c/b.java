package com.tencent.assistant.link.sdk.c;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.text.TextUtils;
import com.tencent.assistant.link.sdk.a.a;

/* compiled from: ProGuard */
public class b extends e {
    private static volatile e a = null;
    private static final Class<?>[] b = new Class[]{a.class};

    public static synchronized e a(Context context, String str) {
        e eVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b(context, TextUtils.isEmpty(str) ? a.a() + "/" + "mobile_app_link.db" : str + "/" + "mobile_app_link.db", null, 1);
            }
            eVar = a;
        }
        return eVar;
    }

    public b(Context context, String str, CursorFactory cursorFactory, int i) {
        super(context, str, null, i);
    }

    public Class<?>[] a() {
        return b;
    }

    public int b() {
        return 1;
    }
}
