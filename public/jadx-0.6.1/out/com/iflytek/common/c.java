package com.iflytek.common;

import android.util.Log;
import com.iflytek.common.b.a;

final class c {
    static a a;

    protected static a a() {
        if (a != null) {
            return a;
        }
        try {
            a aVar = (a) Class.forName("com.iflytek.common.push.impl.PushImpl").newInstance();
            a = aVar;
            if (aVar != null) {
                return a;
            }
        } catch (Exception e) {
            Log.e("PushFactory", "getPushInstance not found push instance.");
        }
        return null;
    }
}
