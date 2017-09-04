package com.qq.reader.common.web.js;

import com.qq.reader.common.web.a;
import com.qq.reader.common.web.js.a.a.b;

public class AndroidJS extends b {
    private a a;

    public AndroidJS(a aVar) {
        this.a = aVar;
    }

    public void pageAction(String str) {
        if (this.a != null) {
            this.a.doPageAction(str);
        }
    }
}
