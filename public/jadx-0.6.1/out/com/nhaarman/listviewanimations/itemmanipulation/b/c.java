package com.nhaarman.listviewanimations.itemmanipulation.b;

import android.widget.BaseAdapter;
import com.nhaarman.listviewanimations.a;
import com.nhaarman.listviewanimations.b;
import com.nhaarman.listviewanimations.b.e;

/* compiled from: SwipeDismissAdapter */
public class c extends b {
    private final b a;
    private d b;
    private boolean c;
    private int d;

    public c(BaseAdapter baseAdapter, b bVar) {
        super(baseAdapter);
        this.a = bVar;
    }

    public void a(e eVar) {
        super.a(eVar);
        if (a() instanceof a) {
            ((a) a()).a(this);
        }
        this.b = new d(eVar, this.a);
        if (this.c) {
            this.b.c();
        }
        if (this.d != 0) {
            this.b.c(this.d);
        }
        eVar.h().setOnTouchListener(this.b);
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        if (this.b != null) {
            this.b.d();
        }
    }
}
