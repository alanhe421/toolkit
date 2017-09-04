package com.tencent.mm.performance.activitylifecycle;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.performance.util.APerformanceController;
import com.tencent.mm.performance.util.ReflectHelper;
import java.util.HashSet;
import java.util.Set;

public class ActivityMonitorController extends APerformanceController {
    public static String TYPE = "ActivityMonitorController";
    private Object mActivityThreadObj = null;
    private Set<ActivityLifeCycleCallback> mCallbacks = null;
    private boolean mCanReflect = false;
    private Instrumentation mOrigInstrumentation = null;

    public static abstract class ActivityLifeCycleCallback {
        public void onBeforeActivityCreate(Activity activity, Bundle bundle) {
        }

        public void onAfterActivityCreate(Activity activity, Bundle bundle) {
        }

        public void onBeforeActivityResume(Activity activity) {
        }

        public void onAfterActivityResume(Activity activity) {
        }

        public void onBeforeActivityPause(Activity activity) {
        }

        public void onAfterActivityPause(Activity activity) {
        }

        public void onBeforeActivityStart(Activity activity) {
        }

        public void onAfterActivityStart(Activity activity) {
        }

        public void onBeforeActivityRestart(Activity activity) {
        }

        public void onAfterActivityRestart(Activity activity) {
        }

        public void onBeforeActivityNewIntent(Activity activity, Intent intent) {
        }

        public void onAfterActivityNewIntent(Activity activity, Intent intent) {
        }

        public void onBeforeActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onAfterActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onBeforeActivityRestoreInstanceState(Activity activity, Bundle bundle) {
        }

        public void onAfterActivityRestoreInstanceState(Activity activity, Bundle bundle) {
        }

        public void onBeforeActivityStop(Activity activity) {
        }

        public void onAfterActivityStop(Activity activity) {
        }

        public void onBeforeActivityDestroy(Activity activity) {
        }

        public void onAfterActivityDestroy(Activity activity) {
        }
    }

    private class MyInstrumentation extends Instrumentation {
        private MyInstrumentation() {
        }

        public void callActivityOnCreate(Activity activity, Bundle bundle) {
            ActivityMonitorController.this.notifyActivityOnCreate(activity, bundle, true);
            ActivityMonitorController.this.mOrigInstrumentation.callActivityOnCreate(activity, bundle);
            ActivityMonitorController.this.notifyActivityOnCreate(activity, bundle, false);
        }

        public void callActivityOnResume(Activity activity) {
            ActivityMonitorController.this.notifyActivityOnResume(activity, true);
            ActivityMonitorController.this.mOrigInstrumentation.callActivityOnResume(activity);
            ActivityMonitorController.this.notifyActivityOnResume(activity, false);
        }

        public void callActivityOnPause(Activity activity) {
            ActivityMonitorController.this.notifyActivityOnPause(activity, true);
            ActivityMonitorController.this.mOrigInstrumentation.callActivityOnPause(activity);
            ActivityMonitorController.this.notifyActivityOnPause(activity, false);
        }

        public void callActivityOnStart(Activity activity) {
            ActivityMonitorController.this.notifyActivityOnStart(activity, true);
            ActivityMonitorController.this.mOrigInstrumentation.callActivityOnStart(activity);
            ActivityMonitorController.this.notifyActivityOnStart(activity, false);
        }

        public void callActivityOnRestart(Activity activity) {
            ActivityMonitorController.this.notifyActivityOnRestart(activity, true);
            ActivityMonitorController.this.mOrigInstrumentation.callActivityOnRestart(activity);
            ActivityMonitorController.this.notifyActivityOnRestart(activity, false);
        }

        public void callActivityOnNewIntent(Activity activity, Intent intent) {
            ActivityMonitorController.this.notifyActivityOnNewIntent(activity, intent, true);
            ActivityMonitorController.this.mOrigInstrumentation.callActivityOnNewIntent(activity, intent);
            ActivityMonitorController.this.notifyActivityOnNewIntent(activity, intent, false);
        }

        public void callActivityOnSaveInstanceState(Activity activity, Bundle bundle) {
            ActivityMonitorController.this.notifyActivityOnSaveInstanceState(activity, bundle, true);
            ActivityMonitorController.this.mOrigInstrumentation.callActivityOnSaveInstanceState(activity, bundle);
            ActivityMonitorController.this.notifyActivityOnSaveInstanceState(activity, bundle, false);
        }

        public void callActivityOnRestoreInstanceState(Activity activity, Bundle bundle) {
            ActivityMonitorController.this.notifyActivityOnRestoreInstanceState(activity, bundle, true);
            ActivityMonitorController.this.mOrigInstrumentation.callActivityOnRestoreInstanceState(activity, bundle);
            ActivityMonitorController.this.notifyActivityOnRestoreInstanceState(activity, bundle, false);
        }

        public void callActivityOnStop(Activity activity) {
            ActivityMonitorController.this.notifyActivityOnStop(activity, true);
            ActivityMonitorController.this.mOrigInstrumentation.callActivityOnStop(activity);
            ActivityMonitorController.this.notifyActivityOnStop(activity, false);
        }

        public void callActivityOnDestroy(Activity activity) {
            ActivityMonitorController.this.notifyActivityOnDestroy(activity, true);
            ActivityMonitorController.this.mOrigInstrumentation.callActivityOnDestroy(activity);
            ActivityMonitorController.this.notifyActivityOnDestroy(activity, false);
        }
    }

    public synchronized void registerActivityLifeCycleCallback(ActivityLifeCycleCallback activityLifeCycleCallback) {
        if (activityLifeCycleCallback == null) {
            throw new IllegalArgumentException("callback is null");
        } else if (this.mCanReflect) {
            this.mCallbacks.add(activityLifeCycleCallback);
        }
    }

    public synchronized void unregisterActivityLifeCycleCallback(ActivityLifeCycleCallback activityLifeCycleCallback) {
        if (activityLifeCycleCallback == null) {
            throw new IllegalArgumentException("callback is null");
        } else if (this.mCanReflect) {
            this.mCallbacks.remove(activityLifeCycleCallback);
        }
    }

    private synchronized void notifyActivityOnCreate(Activity activity, Bundle bundle, boolean z) {
        for (ActivityLifeCycleCallback activityLifeCycleCallback : this.mCallbacks) {
            if (z) {
                activityLifeCycleCallback.onBeforeActivityCreate(activity, bundle);
            } else {
                activityLifeCycleCallback.onAfterActivityCreate(activity, bundle);
            }
        }
    }

    private synchronized void notifyActivityOnResume(Activity activity, boolean z) {
        for (ActivityLifeCycleCallback activityLifeCycleCallback : this.mCallbacks) {
            if (z) {
                activityLifeCycleCallback.onBeforeActivityResume(activity);
            } else {
                activityLifeCycleCallback.onAfterActivityResume(activity);
            }
        }
    }

    private synchronized void notifyActivityOnPause(Activity activity, boolean z) {
        for (ActivityLifeCycleCallback activityLifeCycleCallback : this.mCallbacks) {
            if (z) {
                activityLifeCycleCallback.onBeforeActivityPause(activity);
            } else {
                activityLifeCycleCallback.onAfterActivityPause(activity);
            }
        }
    }

    private synchronized void notifyActivityOnStart(Activity activity, boolean z) {
        for (ActivityLifeCycleCallback activityLifeCycleCallback : this.mCallbacks) {
            if (z) {
                activityLifeCycleCallback.onBeforeActivityStart(activity);
            } else {
                activityLifeCycleCallback.onAfterActivityStart(activity);
            }
        }
    }

    private synchronized void notifyActivityOnRestart(Activity activity, boolean z) {
        for (ActivityLifeCycleCallback activityLifeCycleCallback : this.mCallbacks) {
            if (z) {
                activityLifeCycleCallback.onBeforeActivityRestart(activity);
            } else {
                activityLifeCycleCallback.onAfterActivityRestart(activity);
            }
        }
    }

    private synchronized void notifyActivityOnNewIntent(Activity activity, Intent intent, boolean z) {
        for (ActivityLifeCycleCallback activityLifeCycleCallback : this.mCallbacks) {
            if (z) {
                activityLifeCycleCallback.onBeforeActivityNewIntent(activity, intent);
            } else {
                activityLifeCycleCallback.onAfterActivityNewIntent(activity, intent);
            }
        }
    }

    private synchronized void notifyActivityOnSaveInstanceState(Activity activity, Bundle bundle, boolean z) {
        for (ActivityLifeCycleCallback activityLifeCycleCallback : this.mCallbacks) {
            if (z) {
                activityLifeCycleCallback.onBeforeActivitySaveInstanceState(activity, bundle);
            } else {
                activityLifeCycleCallback.onAfterActivitySaveInstanceState(activity, bundle);
            }
        }
    }

    private synchronized void notifyActivityOnRestoreInstanceState(Activity activity, Bundle bundle, boolean z) {
        for (ActivityLifeCycleCallback activityLifeCycleCallback : this.mCallbacks) {
            if (z) {
                activityLifeCycleCallback.onBeforeActivityRestoreInstanceState(activity, bundle);
            } else {
                activityLifeCycleCallback.onAfterActivityRestoreInstanceState(activity, bundle);
            }
        }
    }

    private synchronized void notifyActivityOnStop(Activity activity, boolean z) {
        for (ActivityLifeCycleCallback activityLifeCycleCallback : this.mCallbacks) {
            if (z) {
                activityLifeCycleCallback.onBeforeActivityStop(activity);
            } else {
                activityLifeCycleCallback.onAfterActivityStop(activity);
            }
        }
    }

    private synchronized void notifyActivityOnDestroy(Activity activity, boolean z) {
        for (ActivityLifeCycleCallback activityLifeCycleCallback : this.mCallbacks) {
            if (z) {
                activityLifeCycleCallback.onBeforeActivityDestroy(activity);
            } else {
                activityLifeCycleCallback.onAfterActivityDestroy(activity);
            }
        }
    }

    public boolean startActivityLifeCycleMonitor() {
        boolean z;
        try {
            this.mActivityThreadObj = ReflectHelper.invokeMethod("android.app.ActivityThread", "currentActivityThread", null, (Class[]) null, (Object[]) null);
            if (this.mActivityThreadObj == null) {
                throw new IllegalStateException("Failed to get CurrentActivityThread.");
            }
            this.mOrigInstrumentation = (Instrumentation) ReflectHelper.getField(this.mActivityThreadObj.getClass(), "mInstrumentation", this.mActivityThreadObj);
            if (this.mOrigInstrumentation == null) {
                throw new IllegalStateException("Failed to get Instrumentation instance.");
            } else if (this.mOrigInstrumentation.getClass().equals(MyInstrumentation.class)) {
                return true;
            } else {
                if (this.mOrigInstrumentation.getClass().equals(Instrumentation.class)) {
                    ReflectHelper.setField(this.mActivityThreadObj.getClass(), "mInstrumentation", new MyInstrumentation(), this.mActivityThreadObj);
                    z = true;
                    this.mCanReflect = z;
                    return z;
                }
                throw new IllegalStateException("Not original Instrumentation instance, give up monitoring.");
            }
        } catch (Exception e) {
            z = false;
        }
    }

    public void onPerformanceOpened() {
        this.mCallbacks = new HashSet();
    }

    public void onPerformanceClosed() {
        if (this.mCallbacks != null) {
            this.mCallbacks.clear();
            this.mCallbacks = null;
        }
        if (this.mCanReflect) {
            stopActivityLifeCycleMonitor();
            this.mCanReflect = false;
        }
    }

    public String getMonitorType() {
        return TYPE;
    }

    public boolean canClosedMonitor() {
        return true;
    }

    public void onPerformanceProcess() {
    }

    private void stopActivityLifeCycleMonitor() {
        ReflectHelper.setField(this.mActivityThreadObj.getClass(), "mInstrumentation", this.mOrigInstrumentation, this.mActivityThreadObj);
    }
}
