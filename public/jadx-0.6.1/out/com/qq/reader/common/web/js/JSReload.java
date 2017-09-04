package com.qq.reader.common.web.js;

import android.content.Context;
import com.qq.reader.common.web.c;
import com.qq.reader.common.web.js.a.a.b;

public class JSReload extends b {
    private c a;
    private Context b;

    public JSReload(Context context, c cVar) {
        this.b = context;
        this.a = cVar;
    }

    public void retry() {
        if (this.a != null) {
            this.a.retry();
        }
    }

    public void reloadUrl(String str) {
    }
}
