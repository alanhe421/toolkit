package com.qq.reader.common.web.js;

import com.qq.reader.common.monitor.f;
import com.qq.reader.common.web.js.a.a.b;

public class JsAdEvent extends b {
    private a a;

    public interface a {
        void setTouched(boolean z);
    }

    public JsAdEvent(a aVar) {
        this.a = aVar;
    }

    public void setStart(String str) {
        f.d("JsAdEvent", "setStart " + str);
        this.a.setTouched(true);
    }

    public void setEnd(String str) {
        f.d("JsAdEvent", "setEnd " + str);
        this.a.setTouched(false);
    }
}
