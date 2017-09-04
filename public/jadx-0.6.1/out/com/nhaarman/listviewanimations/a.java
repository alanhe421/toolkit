package com.nhaarman.listviewanimations;

import android.widget.BaseAdapter;
import com.nhaarman.listviewanimations.b.d;
import com.nhaarman.listviewanimations.b.g;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ArrayAdapter */
public abstract class a<T> extends BaseAdapter implements d<T>, g {
    private final List<T> a;
    private BaseAdapter b;

    protected a() {
        this(null);
    }

    protected a(List<T> list) {
        if (list != null) {
            this.a = list;
        } else {
            this.a = new ArrayList();
        }
    }

    public void a(BaseAdapter baseAdapter) {
        this.b = baseAdapter;
    }
}
