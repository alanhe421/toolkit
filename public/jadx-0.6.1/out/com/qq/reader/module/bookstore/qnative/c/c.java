package com.qq.reader.module.bookstore.qnative.c;

import android.view.View;
import android.view.View.OnClickListener;
import java.util.Calendar;

/* compiled from: INoDoubleOnClickListener */
public abstract class c implements OnClickListener {
    private long a = 0;

    public abstract void a(View view);

    public void onClick(View view) {
        long timeInMillis = Calendar.getInstance().getTimeInMillis();
        if (timeInMillis - this.a > 1000) {
            this.a = timeInMillis;
            a(view);
        }
    }
}
