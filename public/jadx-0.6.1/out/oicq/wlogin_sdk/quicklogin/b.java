package oicq.wlogin_sdk.quicklogin;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: QuickLoginWebViewActivity */
class b implements OnClickListener {
    final /* synthetic */ QuickLoginWebViewActivity a;

    b(QuickLoginWebViewActivity quickLoginWebViewActivity) {
        this.a = quickLoginWebViewActivity;
    }

    public void onClick(View view) {
        this.a.finish();
    }
}
