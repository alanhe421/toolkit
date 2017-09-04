package android.support.v4.view;

import android.view.View;

/* compiled from: ViewCompatLollipop */
class ah {
    public static void a(View view, float f) {
        view.setElevation(f);
    }

    public static float a(View view) {
        return view.getElevation();
    }

    public static boolean b(View view) {
        return view.isNestedScrollingEnabled();
    }

    public static void c(View view) {
        view.stopNestedScroll();
    }
}
