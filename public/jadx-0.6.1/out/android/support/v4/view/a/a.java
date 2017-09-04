package android.support.v4.view.a;

import android.os.Build.VERSION;
import android.view.accessibility.AccessibilityEvent;

/* compiled from: AccessibilityEventCompat */
public class a {
    private static final d a;

    /* compiled from: AccessibilityEventCompat */
    interface d {
        int a(AccessibilityEvent accessibilityEvent);

        void a(AccessibilityEvent accessibilityEvent, int i);
    }

    /* compiled from: AccessibilityEventCompat */
    static class c implements d {
        c() {
        }

        public void a(AccessibilityEvent accessibilityEvent, int i) {
        }

        public int a(AccessibilityEvent accessibilityEvent) {
            return 0;
        }
    }

    /* compiled from: AccessibilityEventCompat */
    static class a extends c {
        a() {
        }
    }

    /* compiled from: AccessibilityEventCompat */
    static class b extends a {
        b() {
        }

        public void a(AccessibilityEvent accessibilityEvent, int i) {
            b.a(accessibilityEvent, i);
        }

        public int a(AccessibilityEvent accessibilityEvent) {
            return b.a(accessibilityEvent);
        }
    }

    static {
        if (VERSION.SDK_INT >= 19) {
            a = new b();
        } else if (VERSION.SDK_INT >= 14) {
            a = new a();
        } else {
            a = new c();
        }
    }

    public static l a(AccessibilityEvent accessibilityEvent) {
        return new l(accessibilityEvent);
    }

    public static void a(AccessibilityEvent accessibilityEvent, int i) {
        a.a(accessibilityEvent, i);
    }

    public static int b(AccessibilityEvent accessibilityEvent) {
        return a.a(accessibilityEvent);
    }
}
