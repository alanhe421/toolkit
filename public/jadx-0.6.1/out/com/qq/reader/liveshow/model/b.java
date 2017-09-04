package com.qq.reader.liveshow.model;

import android.content.Context;
import com.qq.reader.liveshow.a;
import com.qq.reader.liveshow.utils.c;
import java.util.HashMap;

/* compiled from: GlobalCache */
public class b {
    private static HashMap<Integer, c> a = new HashMap();
    private static int b = -1;

    public static HashMap<Integer, c> a() {
        if (a == null) {
            a = new HashMap();
        }
        return a;
    }

    public static void b() {
        if (a != null) {
            a.clear();
            a = null;
        }
    }

    public static int a(Context context) {
        if (b == -1) {
            b = context.getResources().getDimensionPixelOffset(a.c.chat_list_gift_height);
        }
        return b;
    }
}
