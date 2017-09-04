package com.qq.reader.common.web.js;

import android.content.Context;
import com.qq.reader.appconfig.a.e;
import com.qq.reader.common.web.js.a.a.b;

public class JSLocalStorage extends b {
    private Context a;

    public JSLocalStorage(Context context) {
        this.a = context;
    }

    public void put(String str, String str2) {
        e.a(this.a, str, str2);
    }

    public void remove(String str) {
        e.a(this.a, str);
    }

    public void get(String str) {
        e.b(this.a, str);
    }
}
