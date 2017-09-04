package com.tencent.beacon.b;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.util.SparseArray;
import com.tencent.beacon.e.a;
import com.tencent.beacon.event.i;
import java.lang.ref.WeakReference;

@TargetApi(14)
/* compiled from: ProGuard */
public final class g implements ActivityLifecycleCallbacks {
    private static SparseArray<WeakReference<Activity>> a = new SparseArray();
    private boolean b = false;

    /* compiled from: ProGuard */
    class AnonymousClass1 implements Runnable {
        private /* synthetic */ Activity a;

        AnonymousClass1(Activity activity) {
            this.a = activity;
        }

        public final void run() {
            new i(this.a.getApplicationContext()).b();
        }
    }

    public final void onActivityCreated(Activity activity, Bundle bundle) {
        a(activity);
    }

    public final void onActivityStarted(Activity activity) {
        a(activity);
    }

    public final void onActivityResumed(Activity activity) {
        a(activity);
    }

    public final void onActivityPaused(Activity activity) {
        a(activity);
    }

    public final void onActivityStopped(Activity activity) {
        a(activity);
    }

    public final void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        a(activity);
    }

    public final void onActivityDestroyed(Activity activity) {
        a(activity);
    }

    private void a(Activity activity) {
        b.b = true;
        if (!(activity == null || a == null)) {
            int hashCode = activity.hashCode();
            if (a.get(hashCode) == null) {
                a.put(hashCode, new WeakReference(activity));
            }
        }
        if (!this.b) {
            a.b("lifecycle callback recover active user.", new Object[0]);
            c.a().a(new AnonymousClass1(activity));
            this.b = true;
        }
    }

    public static SparseArray<WeakReference<Activity>> a() {
        return a;
    }
}
