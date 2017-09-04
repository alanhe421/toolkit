package com.qq.reader.view;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: ZoomDialog */
class ay$1 implements OnClickListener {
    final /* synthetic */ ay a;

    ay$1(ay ayVar) {
        this.a = ayVar;
    }

    public void onClick(View view) {
        this.a.cancel();
        this.a.g().a();
    }
}
