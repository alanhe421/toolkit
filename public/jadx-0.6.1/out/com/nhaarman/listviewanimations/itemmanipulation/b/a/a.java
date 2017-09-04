package com.nhaarman.listviewanimations.itemmanipulation.b.a;

import android.view.View;
import android.view.ViewGroup;
import com.nhaarman.listviewanimations.b;
import com.nhaarman.listviewanimations.b.e;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;

/* compiled from: SwipeUndoAdapter */
public abstract class a extends b {
    private b a;
    private c b;

    public void a(e eVar) {
        super.a(eVar);
        this.a = new b(eVar, this.b);
        if (!(eVar.h() instanceof DynamicListView)) {
            eVar.h().setOnTouchListener(this.a);
        }
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (c() != null) {
            return super.getView(i, view, viewGroup);
        }
        throw new IllegalArgumentException("Call setAbsListView() on this SwipeUndoAdapter before setAdapter()!");
    }
}
