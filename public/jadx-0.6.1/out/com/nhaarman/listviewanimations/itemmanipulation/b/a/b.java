package com.nhaarman.listviewanimations.itemmanipulation.b.a;

import android.view.View;
import android.view.ViewGroup.LayoutParams;
import com.nhaarman.listviewanimations.b.e;
import com.nhaarman.listviewanimations.itemmanipulation.b.d;
import com.nineoldandroids.animation.ObjectAnimator;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/* compiled from: SwipeUndoTouchListener */
public class b extends d {
    private final c a;
    private final Collection<Integer> b = new LinkedList();
    private final Map<Integer, View> c = new HashMap();
    private final List<Integer> d = new LinkedList();
    private final Collection<View> e = new LinkedList();

    public b(e eVar, c cVar) {
        super(eVar, cVar);
        this.a = cVar;
    }

    protected boolean b(View view, int i) {
        return this.b.contains(Integer.valueOf(i));
    }

    protected void c(View view, int i) {
        if (this.b.contains(Integer.valueOf(i))) {
            this.b.remove(Integer.valueOf(i));
            this.c.remove(Integer.valueOf(i));
            d(view, i);
            d(view);
            return;
        }
        this.b.add(Integer.valueOf(i));
        this.c.put(Integer.valueOf(i), view);
        this.a.a(view, i);
        c(view);
        a(view);
    }

    protected void a(View view, int i) {
        a();
    }

    protected void d(View view, int i) {
        super.d(view, i);
        this.e.add(view);
        this.d.add(Integer.valueOf(i));
        this.a.b(view, i);
    }

    public boolean h() {
        return !this.b.isEmpty();
    }

    public void i() {
        for (Integer intValue : this.b) {
            int intValue2 = intValue.intValue();
            d((View) this.c.get(Integer.valueOf(intValue2)), intValue2);
        }
    }

    private void c(View view) {
        this.a.a(view).setVisibility(8);
        Object b = this.a.b(view);
        b.setVisibility(0);
        ObjectAnimator.ofFloat(b, "alpha", 0.0f, 1.0f).start();
    }

    private void d(View view) {
        this.a.a(view).setVisibility(0);
        this.a.b(view).setVisibility(8);
    }

    protected void a() {
        if (b() == 0 && g() == 0) {
            a(this.e);
            a(this.d);
            Collection a = d.a(this.b, this.d);
            this.b.clear();
            this.b.addAll(a);
            this.e.clear();
            this.d.clear();
        }
    }

    protected void a(View view) {
        super.a(view);
        LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = 0;
        view.setLayoutParams(layoutParams);
    }

    protected void b(int i) {
        this.d.add(Integer.valueOf(i));
        a();
    }
}
