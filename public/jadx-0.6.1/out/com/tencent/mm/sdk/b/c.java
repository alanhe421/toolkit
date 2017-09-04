package com.tencent.mm.sdk.b;

import android.util.Log;

final class c implements b$a {
    c() {
    }

    public final int b() {
        return b.a();
    }

    public final void d(String str, String str2) {
        if (b.a() <= 2) {
            Log.i(str, str2);
        }
    }

    public final void e(String str, String str2) {
        if (b.a() <= 1) {
            Log.d(str, str2);
        }
    }

    public final void f(String str, String str2) {
        if (b.a() <= 4) {
            Log.e(str, str2);
        }
    }
}
