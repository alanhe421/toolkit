package com.qq.reader.module.bookstore.qnative.c;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import java.util.Calendar;

/* compiled from: INoDoubleClickItemListener */
public abstract class b implements OnItemClickListener {
    private long a = 0;

    public abstract void a(AdapterView<?> adapterView, View view, int i, long j);

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if (timeInMillis - this.a > 1000) {
            this.a = timeInMillis;
            a(adapterView, view, i, j);
        }
    }
}
