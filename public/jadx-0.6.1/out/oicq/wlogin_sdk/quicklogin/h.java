package oicq.wlogin_sdk.quicklogin;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: QuickLoginWebViewLoader */
final class h implements OnClickListener {
    final /* synthetic */ Activity a;

    h(Activity activity) {
        this.a = activity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
