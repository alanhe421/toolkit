package android.support.v4.view.a;

import android.view.accessibility.AccessibilityRecord;

/* compiled from: AccessibilityRecordCompatIcs */
class m {
    public static Object a() {
        return AccessibilityRecord.obtain();
    }

    public static void a(Object obj, int i) {
        ((AccessibilityRecord) obj).setFromIndex(i);
    }

    public static void b(Object obj, int i) {
        ((AccessibilityRecord) obj).setItemCount(i);
    }

    public static void c(Object obj, int i) {
        ((AccessibilityRecord) obj).setScrollX(i);
    }

    public static void d(Object obj, int i) {
        ((AccessibilityRecord) obj).setScrollY(i);
    }

    public static void a(Object obj, boolean z) {
        ((AccessibilityRecord) obj).setScrollable(z);
    }

    public static void e(Object obj, int i) {
        ((AccessibilityRecord) obj).setToIndex(i);
    }
}
