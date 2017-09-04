package qalsdk;

import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import com.pay.http.APPluginErrorCode;

/* compiled from: Foreground */
public class an implements ActivityLifecycleCallbacks {
    private static an a;
    private static String b = an.class.getSimpleName();
    private final int c = APPluginErrorCode.ERROR_APP_SYSTEM;
    private boolean d = false;
    private boolean e = true;
    private Handler f = new Handler();
    private Runnable g;

    public static void a(Context context) {
        if (a == null) {
            a = new an();
            ((Application) context).registerActivityLifecycleCallbacks(a);
        }
    }

    public static an a() {
        return a;
    }

    private an() {
    }

    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
        this.e = true;
        if (this.g != null) {
            this.f.removeCallbacks(this.g);
        }
        Handler handler = this.f;
        Runnable aoVar = new ao(this);
        this.g = aoVar;
        handler.postDelayed(aoVar, 2000);
    }

    public void onActivityResumed(Activity activity) {
        this.e = false;
        this.d = true;
        if (this.g != null) {
            this.f.removeCallbacks(this.g);
        }
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }

    public boolean b() {
        return this.d;
    }
}
