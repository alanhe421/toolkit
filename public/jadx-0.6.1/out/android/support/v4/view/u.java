package android.support.v4.view;

import android.view.View;
import android.view.ViewGroup;

/* compiled from: NestedScrollingParentHelper */
public class u {
    private final ViewGroup a;
    private int b;

    public u(ViewGroup viewGroup) {
        this.a = viewGroup;
    }

    public void a(View view, View view2, int i) {
        this.b = i;
    }

    public int a() {
        return this.b;
    }

    public void a(View view) {
        this.b = 0;
    }
}
