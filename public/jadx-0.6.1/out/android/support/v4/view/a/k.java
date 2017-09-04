package android.support.v4.view.a;

import android.os.Bundle;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import java.util.List;

/* compiled from: AccessibilityNodeProviderCompatKitKat */
class k {

    /* compiled from: AccessibilityNodeProviderCompatKitKat */
    interface a {
        Object a(int i);

        List<Object> a(String str, int i);

        boolean a(int i, int i2, Bundle bundle);

        Object b(int i);
    }

    public static Object a(final a aVar) {
        return new AccessibilityNodeProvider() {
            public AccessibilityNodeInfo createAccessibilityNodeInfo(int i) {
                return (AccessibilityNodeInfo) aVar.a(i);
            }

            public List<AccessibilityNodeInfo> findAccessibilityNodeInfosByText(String str, int i) {
                return aVar.a(str, i);
            }

            public boolean performAction(int i, int i2, Bundle bundle) {
                return aVar.a(i, i2, bundle);
            }

            public AccessibilityNodeInfo findFocus(int i) {
                return (AccessibilityNodeInfo) aVar.b(i);
            }
        };
    }
}
