package com.qq.reader.module.findhome.b;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.l;
import android.support.v7.widget.RecyclerView.p;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

/* compiled from: FindHomeRecyclerLayoutManager */
public class c extends LinearLayoutManager {
    private String a = "FindHomeRecyclerLayoutManager";
    private boolean b;
    private int c;
    private int d;

    public c(Context context, int i, boolean z) {
        super(context, i, z);
    }

    public void a(l lVar, p pVar, int i, int i2) {
        int i3 = 0;
        try {
            if (!this.b) {
                this.b = true;
                MeasureSpec.getMode(i);
                int mode = MeasureSpec.getMode(i2);
                MeasureSpec.getSize(i);
                int size = MeasureSpec.getSize(i2);
                if (pVar.e() > 0) {
                    View c = lVar.c(0);
                    LayoutParams layoutParams = (LayoutParams) c.getLayoutParams();
                    c.measure(i, ViewGroup.getChildMeasureSpec(i2, v() + x(), layoutParams.height));
                    i3 = (layoutParams.topMargin + (c.getMeasuredHeight() + layoutParams.bottomMargin)) + (v() + x());
                }
                this.c = i;
                if (mode == 1073741824) {
                    i3 = size;
                }
                this.d = i3;
            }
            b(this.c, this.d);
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("FindHomeRecyclerLayoutManager", e.getMessage());
            super.a(lVar, pVar, i, i2);
        }
    }
}
