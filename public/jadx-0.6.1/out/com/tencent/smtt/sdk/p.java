package com.tencent.smtt.sdk;

import android.net.Uri;
import android.webkit.ValueCallback;

class p implements ValueCallback<Uri> {
    final /* synthetic */ ValueCallback a;
    final /* synthetic */ SystemWebChromeClient b;

    p(SystemWebChromeClient systemWebChromeClient, ValueCallback valueCallback) {
        this.b = systemWebChromeClient;
        this.a = valueCallback;
    }

    public void a(Uri uri) {
        this.a.onReceiveValue(uri);
    }

    public /* synthetic */ void onReceiveValue(Object obj) {
        a((Uri) obj);
    }
}
