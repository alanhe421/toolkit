package com.qq.reader.view;

import android.view.View;
import android.view.View.OnClickListener;

/* compiled from: ReadModeDialog */
class y$1 implements OnClickListener {
    final /* synthetic */ y a;

    y$1(y yVar) {
        this.a = yVar;
    }

    public void onClick(View view) {
        this.a.cancel();
        if (y.a(this.a) != null) {
            y.a(this.a).L();
        }
    }
}
