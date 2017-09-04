package android.support.v4.view;

import android.view.View;
import android.view.View.AccessibilityDelegate;

/* compiled from: ViewCompatICS */
class ae {
    public static boolean a(View view, int i) {
        return view.canScrollHorizontally(i);
    }

    public static boolean b(View view, int i) {
        return view.canScrollVertically(i);
    }

    public static void a(View view, Object obj) {
        view.setAccessibilityDelegate((AccessibilityDelegate) obj);
    }
}
