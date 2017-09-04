package com.qq.reader.view;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/* compiled from: ShareDialog */
class aj$1 implements OnItemClickListener {
    final /* synthetic */ aj a;

    aj$1(aj ajVar) {
        this.a = ajVar;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.a.d != null) {
            this.a.d.a();
        }
        aj.a(this.a, i);
    }
}
