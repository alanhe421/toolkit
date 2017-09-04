package android.support.v7.widget;

import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.p;
import android.view.View;

/* compiled from: ScrollbarHelper */
class h {
    static int a(p pVar, f fVar, View view, View view2, LayoutManager layoutManager, boolean z, boolean z2) {
        if (layoutManager.r() == 0 || pVar.e() == 0 || view == null || view2 == null) {
            return 0;
        }
        int max = z2 ? Math.max(0, (pVar.e() - Math.max(layoutManager.d(view), layoutManager.d(view2))) - 1) : Math.max(0, Math.min(layoutManager.d(view), layoutManager.d(view2)));
        if (!z) {
            return max;
        }
        return Math.round((((float) max) * (((float) Math.abs(fVar.b(view2) - fVar.a(view))) / ((float) (Math.abs(layoutManager.d(view) - layoutManager.d(view2)) + 1)))) + ((float) (fVar.c() - fVar.a(view))));
    }

    static int a(p pVar, f fVar, View view, View view2, LayoutManager layoutManager, boolean z) {
        if (layoutManager.r() == 0 || pVar.e() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(layoutManager.d(view) - layoutManager.d(view2)) + 1;
        }
        return Math.min(fVar.f(), fVar.b(view2) - fVar.a(view));
    }

    static int b(p pVar, f fVar, View view, View view2, LayoutManager layoutManager, boolean z) {
        if (layoutManager.r() == 0 || pVar.e() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return pVar.e();
        }
        return (int) ((((float) (fVar.b(view2) - fVar.a(view))) / ((float) (Math.abs(layoutManager.d(view) - layoutManager.d(view2)) + 1))) * ((float) pVar.e()));
    }
}
