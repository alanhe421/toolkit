package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.l;
import android.support.v7.widget.RecyclerView.p;
import android.view.View;

/* compiled from: LayoutState */
class d {
    int a;
    int b;
    int c;
    int d;
    int e = 0;
    int f = 0;

    d() {
    }

    boolean a(p pVar) {
        return this.b >= 0 && this.b < pVar.e();
    }

    View a(l lVar) {
        View c = lVar.c(this.b);
        this.b += this.c;
        return c;
    }

    public String toString() {
        return "LayoutState{mAvailable=" + this.a + ", mCurrentPosition=" + this.b + ", mItemDirection=" + this.c + ", mLayoutDirection=" + this.d + ", mStartLine=" + this.e + ", mEndLine=" + this.f + '}';
    }
}
