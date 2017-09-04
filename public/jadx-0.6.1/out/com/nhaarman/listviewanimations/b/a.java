package com.nhaarman.listviewanimations.b;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

/* compiled from: AbsListViewWrapper */
public class a implements e {
    private final AbsListView a;

    public /* synthetic */ ViewGroup h() {
        return a();
    }

    public a(AbsListView absListView) {
        this.a = absListView;
    }

    public AbsListView a() {
        return this.a;
    }

    public View a(int i) {
        return this.a.getChildAt(i);
    }

    public int c_() {
        return this.a.getFirstVisiblePosition();
    }

    public int d_() {
        return this.a.getLastVisiblePosition();
    }

    public int d() {
        return this.a.getCount();
    }

    public int e() {
        return this.a.getChildCount();
    }

    public int f() {
        if (this.a instanceof ListView) {
            return ((ListView) this.a).getHeaderViewsCount();
        }
        return 0;
    }

    public int a(View view) {
        return this.a.getPositionForView(view);
    }

    public ListAdapter g() {
        return (ListAdapter) this.a.getAdapter();
    }

    public void a_(int i, int i2) {
        this.a.smoothScrollBy(i, i2);
    }
}
