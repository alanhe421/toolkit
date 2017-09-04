package com.yuewen.ywlogin;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.SslErrorHandler;

class l implements OnClickListener {
    final /* synthetic */ SslErrorHandler a;
    final /* synthetic */ j b;

    l(j jVar, SslErrorHandler sslErrorHandler) {
        this.b = jVar;
        this.a = sslErrorHandler;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        this.a.cancel();
    }
}
