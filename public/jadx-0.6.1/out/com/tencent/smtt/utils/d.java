package com.tencent.smtt.utils;

import android.content.Context;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.utils.c.a;

class d implements a {
    final /* synthetic */ WebView a;
    final /* synthetic */ Context b;
    final /* synthetic */ RelativeLayout c;
    final /* synthetic */ String d;
    final /* synthetic */ TextView e;
    final /* synthetic */ c f;

    d(c cVar, WebView webView, Context context, RelativeLayout relativeLayout, String str, TextView textView) {
        this.f = cVar;
        this.a = webView;
        this.b = context;
        this.c = relativeLayout;
        this.d = str;
        this.e = textView;
    }

    public void a() {
        this.a.post(new e(this));
    }

    public void a(int i) {
        this.a.post(new f(this, i));
    }

    public void a(Throwable th) {
        this.a.post(new g(this));
    }
}
