package com.tencent.mm.performance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.qq.reader.common.monitor.f;
import com.tencent.mm.performance.activitylifecycle.ActivityMonitorController.ActivityLifeCycleCallback;
import java.util.HashMap;

public class ActivityLifeCycleTimeUse extends ActivityLifeCycleCallback {
    private static final String TAG = "MicroMsg.WxPerformace";
    private HashMap<Integer, Long> mCreateTimeUsed = new HashMap();
    private HashMap<Integer, Long> mDestroyTimeUsed = new HashMap();
    private HashMap<Integer, Long> mNewIntentTimeUsed = new HashMap();
    private HashMap<Integer, Long> mPauseTimeUsed = new HashMap();
    private HashMap<Integer, Long> mResumeTimeUsed = new HashMap();
    private HashMap<Integer, Long> mStartTimeUsed = new HashMap();
    private HashMap<Integer, Long> mStopTimeUsed = new HashMap();

    private void onActivityTimeBegin(HashMap<Integer, Long> hashMap, Activity activity) {
        if (activity != null) {
            hashMap.put(Integer.valueOf(activity.hashCode()), Long.valueOf(System.currentTimeMillis()));
        }
    }

    private void onActivityTimeEnd(HashMap<Integer, Long> hashMap, Activity activity, String str) {
        if (activity != null) {
            int hashCode = activity.hashCode();
            Long l = (Long) hashMap.get(Integer.valueOf(hashCode));
            if (l != null) {
                f.b(TAG, str + "  activity: " + hashCode + " classname:" + activity.getComponentName().getShortClassName() + " use time: " + (System.currentTimeMillis() - l.longValue()));
            }
        }
    }

    public void onBeforeActivityCreate(Activity activity, Bundle bundle) {
        super.onBeforeActivityCreate(activity, bundle);
        onActivityTimeBegin(this.mCreateTimeUsed, activity);
    }

    public void onAfterActivityCreate(Activity activity, Bundle bundle) {
        super.onAfterActivityCreate(activity, bundle);
        onActivityTimeEnd(this.mCreateTimeUsed, activity, "onActivityCreate");
    }

    public void onBeforeActivityDestroy(Activity activity) {
        super.onBeforeActivityDestroy(activity);
        onActivityTimeBegin(this.mDestroyTimeUsed, activity);
    }

    public void onAfterActivityDestroy(Activity activity) {
        super.onAfterActivityDestroy(activity);
        onActivityTimeEnd(this.mDestroyTimeUsed, activity, "onActivityDestroy");
    }

    public void onBeforeActivityNewIntent(Activity activity, Intent intent) {
        super.onBeforeActivityNewIntent(activity, intent);
        onActivityTimeBegin(this.mNewIntentTimeUsed, activity);
    }

    public void onAfterActivityNewIntent(Activity activity, Intent intent) {
        super.onAfterActivityNewIntent(activity, intent);
        onActivityTimeEnd(this.mNewIntentTimeUsed, activity, "onActivityNewIntent");
    }

    public void onBeforeActivityPause(Activity activity) {
        super.onBeforeActivityPause(activity);
        onActivityTimeBegin(this.mPauseTimeUsed, activity);
    }

    public void onAfterActivityPause(Activity activity) {
        super.onAfterActivityPause(activity);
        onActivityTimeEnd(this.mPauseTimeUsed, activity, "onActivityPause");
    }

    public void onBeforeActivityResume(Activity activity) {
        super.onBeforeActivityResume(activity);
        onActivityTimeBegin(this.mResumeTimeUsed, activity);
    }

    public void onAfterActivityResume(Activity activity) {
        super.onAfterActivityResume(activity);
        onActivityTimeEnd(this.mResumeTimeUsed, activity, "onActivityResume");
    }

    public void onBeforeActivityStart(Activity activity) {
        super.onBeforeActivityStart(activity);
        onActivityTimeBegin(this.mStartTimeUsed, activity);
    }

    public void onAfterActivityStart(Activity activity) {
        super.onAfterActivityStart(activity);
        onActivityTimeEnd(this.mStartTimeUsed, activity, "onActivityStart");
    }

    public void onBeforeActivityStop(Activity activity) {
        super.onBeforeActivityStop(activity);
        onActivityTimeBegin(this.mStopTimeUsed, activity);
    }

    public void onAfterActivityStop(Activity activity) {
        super.onAfterActivityStop(activity);
        onActivityTimeEnd(this.mStopTimeUsed, activity, "onActivityStop");
    }
}
