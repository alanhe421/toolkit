package oicq.wlogin_sdk.quicklogin;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.SslErrorHandler;

/* compiled from: QuickLoginWebViewActivity */
class e implements OnClickListener {
    final /* synthetic */ SslErrorHandler a;
    final /* synthetic */ d b;

    e(d dVar, SslErrorHandler sslErrorHandler) {
        this.b = dVar;
        this.a = sslErrorHandler;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        QuickLoginWebViewActivity.a = true;
        this.a.proceed();
    }
}
