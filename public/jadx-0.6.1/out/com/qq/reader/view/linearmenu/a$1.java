package com.qq.reader.view.linearmenu;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/* compiled from: LinearBaseMenu */
class a$1 implements OnItemClickListener {
    final /* synthetic */ a a;

    a$1(a aVar) {
        this.a = aVar;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (j >= 0) {
            if (this.a.l != null) {
                this.a.l.a((int) j, (Bundle) view.getTag());
            }
            this.a.cancel();
        }
    }
}
