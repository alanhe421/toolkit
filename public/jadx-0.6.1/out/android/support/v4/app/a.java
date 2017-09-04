package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.View;
import java.util.List;
import java.util.Map;

/* compiled from: ActivityCompat */
public class a extends android.support.v4.content.a {

    /* compiled from: ActivityCompat */
    public interface a {
        void onRequestPermissionsResult(int i, String[] strArr, int[] iArr);
    }

    /* compiled from: ActivityCompat */
    private static class b extends android.support.v4.app.b.a {
        private ag a;

        public b(ag agVar) {
            this.a = agVar;
        }

        public void a(List<String> list, List<View> list2, List<View> list3) {
            this.a.a((List) list, (List) list2, (List) list3);
        }

        public void b(List<String> list, List<View> list2, List<View> list3) {
            this.a.b(list, list2, list3);
        }

        public void a(List<View> list) {
            this.a.a((List) list);
        }

        public void a(List<String> list, Map<String, View> map) {
            this.a.a((List) list, (Map) map);
        }

        public Parcelable a(View view, Matrix matrix, RectF rectF) {
            return this.a.a(view, matrix, rectF);
        }

        public View a(Context context, Parcelable parcelable) {
            return this.a.a(context, parcelable);
        }
    }

    public static void a(Activity activity) {
        if (VERSION.SDK_INT >= 21) {
            b.a(activity);
        } else {
            activity.finish();
        }
    }

    public static void a(Activity activity, ag agVar) {
        if (VERSION.SDK_INT >= 21) {
            b.a(activity, a(agVar));
        }
    }

    public static void b(Activity activity, ag agVar) {
        if (VERSION.SDK_INT >= 21) {
            b.b(activity, a(agVar));
        }
    }

    public static void b(Activity activity) {
        if (VERSION.SDK_INT >= 21) {
            b.b(activity);
        }
    }

    public static void c(Activity activity) {
        if (VERSION.SDK_INT >= 21) {
            b.c(activity);
        }
    }

    public static void a(final Activity activity, final String[] strArr, final int i) {
        if (VERSION.SDK_INT >= 23) {
            c.a(activity, strArr, i);
        } else if (activity instanceof a) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    int[] iArr = new int[strArr.length];
                    PackageManager packageManager = activity.getPackageManager();
                    String packageName = activity.getPackageName();
                    int length = strArr.length;
                    for (int i = 0; i < length; i++) {
                        iArr[i] = packageManager.checkPermission(strArr[i], packageName);
                    }
                    ((a) activity).onRequestPermissionsResult(i, strArr, iArr);
                }
            });
        }
    }

    public static boolean a(Activity activity, String str) {
        if (VERSION.SDK_INT >= 23) {
            return c.a(activity, str);
        }
        return false;
    }

    private static android.support.v4.app.b.a a(ag agVar) {
        if (agVar != null) {
            return new b(agVar);
        }
        return null;
    }
}
