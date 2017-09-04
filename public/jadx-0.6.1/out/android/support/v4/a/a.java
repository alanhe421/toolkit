package android.support.v4.a;

import android.os.Build.VERSION;
import android.view.View;

/* compiled from: AnimatorCompatHelper */
public abstract class a {
    static b a;

    static {
        if (VERSION.SDK_INT >= 12) {
            a = new d();
        } else {
            a = new c();
        }
    }

    public static void a(View view) {
        a.a(view);
    }
}
