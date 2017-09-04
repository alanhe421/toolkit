package com.qq.reader.view;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.appconfig.a.d;

/* compiled from: ReadModeDialog */
class y$2 implements OnClickListener {
    final /* synthetic */ y a;

    y$2(y yVar) {
        this.a = yVar;
    }

    public void onClick(View view) {
        d.w(this.a.e(), true);
        this.a.cancel();
        if (y.a(this.a) != null) {
            y.a(this.a).M();
        }
    }
}
