package com.tencent.av.sdk;

import android.util.Log;

public class AVVideoCtrl$SwitchCameraCompleteCallback {
    static final String TAG = "SdkJni";

    protected void onComplete(int i, int i2) {
        Log.d(TAG, "SwitchCameraCompleteCallback.OnComplete. result = " + i2);
    }
}
