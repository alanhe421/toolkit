package com.tencent.av.sdk;

import android.util.Log;

public class AVVideoCtrl$EnableCameraCompleteCallback {
    static final String TAG = "SdkJni";

    protected void onComplete(boolean z, int i) {
        Log.d(TAG, "EnableCameraCompleteCallback.OnComplete. result = " + i);
    }
}
