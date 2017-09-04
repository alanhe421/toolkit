package com.qq.reader.module.feed.swipe;

import android.util.SparseArray;
import android.view.View;
import android.widget.BaseAdapter;
import com.nhaarman.listviewanimations.b.e;
import com.nhaarman.listviewanimations.itemmanipulation.b.b;
import com.nhaarman.listviewanimations.itemmanipulation.b.c;

/* compiled from: FeedSwipeDismissAdapter */
public class a extends c {
    private a a;
    private int b;
    private b c;
    private int d;
    private int e;

    /* compiled from: FeedSwipeDismissAdapter */
    public class a extends b {
        final /* synthetic */ a a;
        private SparseArray<View> b = new SparseArray();
        private int c;
        private int d;
        private int e;
        private final int f = 10;

        public a(a aVar, e eVar, b bVar, int i, int i2, int i3) {
            this.a = aVar;
            super(eVar, bVar);
            this.c = i;
            this.d = i2;
            this.e = i3;
        }

        protected View a(View view) {
            if (view == null || this.c <= 0) {
                return view;
            }
            View findViewById = view.findViewById(this.c);
            if (findViewById != null) {
                return findViewById;
            }
            return view;
        }

        protected View b(View view) {
            if (view == null || this.d <= 0) {
                return view;
            }
            View findViewById = view.findViewById(this.d);
            if (findViewById != null) {
                return findViewById;
            }
            return view;
        }

        protected View c(View view) {
            if (view == null || this.e <= 0) {
                return view;
            }
            View findViewById = view.findViewById(this.e);
            if (findViewById != null) {
                return findViewById;
            }
            return view;
        }
    }

    public a(BaseAdapter baseAdapter, b bVar, int i, int i2, int i3) {
        super(baseAdapter, bVar);
        this.b = i;
        this.c = bVar;
        this.d = i2;
        this.e = i3;
    }

    public void a(e eVar) {
        super.a(eVar);
        this.a = new a(this, eVar, this.c, this.b, this.d, this.e);
        eVar.h().setOnTouchListener(this.a);
    }
}
