package com.sijla.g.a;

import android.content.Context;
import com.sijla.h.h;
import com.sijla.j.f;

public class c implements b {
    public void a(Context context, String str) {
        try {
            if (com.sijla.d.c.f != null && com.sijla.d.c.f.contains(str) && context != null) {
                h.a(context);
                f.a("LgEvent.handle appid = [" + str + "] success");
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
