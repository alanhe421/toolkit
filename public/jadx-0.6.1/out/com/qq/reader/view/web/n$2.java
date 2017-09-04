package com.qq.reader.view.web;

import android.view.View;
import com.tencent.widget.AdapterView;
import com.tencent.widget.AdapterView$OnItemClickListener;

/* compiled from: QRPopupMenu */
class n$2 implements AdapterView$OnItemClickListener {
    final /* synthetic */ n a;

    n$2(n nVar) {
        this.a = nVar;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (!(n.c(this.a) == null || j == ((long) this.a.h()))) {
            n.c(this.a).b((int) j, n.d(this.a).a(i).e);
        }
        this.a.cancel();
    }
}
