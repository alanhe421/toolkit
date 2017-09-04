package com.yuewen.ywlogin;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.SslErrorHandler;

class k implements OnClickListener {
    final /* synthetic */ SslErrorHandler a;
    final /* synthetic */ j b;

    k(j jVar, SslErrorHandler sslErrorHandler) {
        this.b = jVar;
        this.a = sslErrorHandler;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.proceed();
    }
}
