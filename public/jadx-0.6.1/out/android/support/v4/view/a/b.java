package android.support.v4.view.a;

import android.view.accessibility.AccessibilityEvent;

/* compiled from: AccessibilityEventCompatKitKat */
class b {
    public static void a(AccessibilityEvent accessibilityEvent, int i) {
        accessibilityEvent.setContentChangeTypes(i);
    }

    public static int a(AccessibilityEvent accessibilityEvent) {
        return accessibilityEvent.getContentChangeTypes();
    }
}
