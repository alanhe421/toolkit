package android.support.v4.view;

import android.os.Bundle;
import android.view.View;
import android.view.View.AccessibilityDelegate;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;

/* compiled from: AccessibilityDelegateCompatJellyBean */
class c {

    /* compiled from: AccessibilityDelegateCompatJellyBean */
    public interface a {
        Object a(View view);

        void a(View view, int i);

        void a(View view, Object obj);

        boolean a(View view, int i, Bundle bundle);

        boolean a(View view, AccessibilityEvent accessibilityEvent);

        boolean a(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent);

        void b(View view, AccessibilityEvent accessibilityEvent);

        void c(View view, AccessibilityEvent accessibilityEvent);

        void d(View view, AccessibilityEvent accessibilityEvent);
    }

    public static Object a(final a aVar) {
        return new AccessibilityDelegate() {
            public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                return aVar.a(view, accessibilityEvent);
            }

            public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                aVar.b(view, accessibilityEvent);
            }

            public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilityNodeInfo) {
                aVar.a(view, (Object) accessibilityNodeInfo);
            }

            public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityEvent) {
                aVar.c(view, accessibilityEvent);
            }

            public boolean onRequestSendAccessibilityEvent(ViewGroup viewGroup, View view, AccessibilityEvent accessibilityEvent) {
                return aVar.a(viewGroup, view, accessibilityEvent);
            }

            public void sendAccessibilityEvent(View view, int i) {
                aVar.a(view, i);
            }

            public void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityEvent) {
                aVar.d(view, accessibilityEvent);
            }

            public AccessibilityNodeProvider getAccessibilityNodeProvider(View view) {
                return (AccessibilityNodeProvider) aVar.a(view);
            }

            public boolean performAccessibilityAction(View view, int i, Bundle bundle) {
                return aVar.a(view, i, bundle);
            }
        };
    }

    public static Object a(Object obj, View view) {
        return ((AccessibilityDelegate) obj).getAccessibilityNodeProvider(view);
    }

    public static boolean a(Object obj, View view, int i, Bundle bundle) {
        return ((AccessibilityDelegate) obj).performAccessibilityAction(view, i, bundle);
    }
}
