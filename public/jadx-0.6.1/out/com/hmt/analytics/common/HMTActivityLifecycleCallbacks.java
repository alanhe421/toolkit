package com.hmt.analytics.common;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import com.hmt.analytics.HMTAgent;

@SuppressLint({"NewApi"})
public class HMTActivityLifecycleCallbacks implements ActivityLifecycleCallbacks {
    public void onActivityCreated(Activity activity, Bundle bundle) {
    }

    public void onActivityDestroyed(Activity activity) {
    }

    public void onActivityPaused(Activity activity) {
        HMTAgent.onPauseCallbacks(activity);
    }

    public void onActivityResumed(Activity activity) {
        HMTAgent.onResumeCallbacks(activity);
    }

    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    public void onActivityStarted(Activity activity) {
    }

    public void onActivityStopped(Activity activity) {
    }
}
