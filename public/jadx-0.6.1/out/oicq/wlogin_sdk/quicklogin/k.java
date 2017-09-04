package oicq.wlogin_sdk.quicklogin;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.SslErrorHandler;

/* compiled from: QuickLoginWebViewLoader */
class k implements OnClickListener {
    final /* synthetic */ SslErrorHandler a;
    final /* synthetic */ j b;

    k(j jVar, SslErrorHandler sslErrorHandler) {
        this.b = jVar;
        this.a = sslErrorHandler;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        QuickLoginWebViewLoader.sslErrorProcessed = true;
        this.a.proceed();
    }
}
