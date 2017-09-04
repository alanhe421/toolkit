package com.qq.reader.view;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.view.View;
import java.util.ArrayList;

/* compiled from: XBaseAdapter */
public abstract class ax {
    private DataSetObservable a;
    private ArrayList<a> b;
    private boolean c;

    /* compiled from: XBaseAdapter */
    private class a {
        View a;
        int b;
        final /* synthetic */ ax c;

        public a(ax axVar, View view, int i) {
            this.c = axVar;
            this.a = view;
            this.b = i;
        }
    }

    public abstract int a();

    public abstract View a(int i, View view, View view2);

    public abstract void a(int i, View view);

    void a(DataSetObserver dataSetObserver) {
        this.a.registerObserver(dataSetObserver);
    }

    void b(DataSetObserver dataSetObserver) {
        this.a.unregisterObserver(dataSetObserver);
    }

    final View b(int i, View view) {
        View view2;
        if (this.c) {
            int i2 = 0;
            View view3 = null;
            while (i2 < this.b.size()) {
                a aVar = (a) this.b.get(i2);
                if (aVar == null || aVar.b != i) {
                    view2 = view3;
                } else {
                    view2 = aVar.a;
                    if (view2 != null) {
                        return a(i, view, view2);
                    }
                }
                i2++;
                view3 = view2;
            }
            if (view3 == null) {
                view3 = a(i, view, null);
            }
            this.b.add(new a(this, view3, i));
            view2 = view3;
        } else {
            view2 = null;
        }
        if (view2 == null) {
            return a(i, view, null);
        }
        return view2;
    }
}
