package android.support.v4.content;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Process;

/* compiled from: ContextCompat */
public class a {
    public static final Drawable a(Context context, int i) {
        if (VERSION.SDK_INT >= 21) {
            return b.a(context, i);
        }
        return context.getResources().getDrawable(i);
    }

    public static int a(Context context, String str) {
        if (str != null) {
            return context.checkPermission(str, Process.myPid(), Process.myUid());
        }
        throw new IllegalArgumentException("permission is null");
    }
}
