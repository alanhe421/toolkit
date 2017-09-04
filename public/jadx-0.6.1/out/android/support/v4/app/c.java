package android.support.v4.app;

import android.app.Activity;

/* compiled from: ActivityCompat23 */
class c {

    /* compiled from: ActivityCompat23 */
    public interface a {
        void validateRequestPermissionsRequestCode(int i);
    }

    public static void a(Activity activity, String[] strArr, int i) {
        if (activity instanceof a) {
            ((a) activity).validateRequestPermissionsRequestCode(i);
        }
        activity.requestPermissions(strArr, i);
    }

    public static boolean a(Activity activity, String str) {
        return activity.shouldShowRequestPermissionRationale(str);
    }
}
