package com.qq.reader.common.imageloader.core.a;

import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.bumptech.glide.i;

/* compiled from: PauseOnScrollListener */
public class a implements OnScrollListener {
    private i a;
    private final boolean b;
    private final boolean c;
    private final OnScrollListener d;

    public a(i iVar, boolean z, boolean z2) {
        this(iVar, z, z2, null);
    }

    public a(i iVar, boolean z, boolean z2, OnScrollListener onScrollListener) {
        this.a = iVar;
        this.b = z;
        this.c = z2;
        this.d = onScrollListener;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        switch (i) {
            case 0:
                if (this.a != null) {
                    this.a.c();
                    break;
                }
                break;
            case 1:
                if (this.b && this.a != null) {
                    this.a.b();
                    break;
                }
            case 2:
                if (this.c && this.a != null) {
                    this.a.b();
                    break;
                }
        }
        if (this.d != null) {
            this.d.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (this.d != null) {
            this.d.onScroll(absListView, i, i2, i3);
        }
    }
}
