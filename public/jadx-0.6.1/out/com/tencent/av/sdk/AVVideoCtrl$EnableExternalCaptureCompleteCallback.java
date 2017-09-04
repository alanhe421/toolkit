package com.tencent.av.sdk;

import android.util.Log;

public class AVVideoCtrl$EnableExternalCaptureCompleteCallback {
    static final String TAG = "SdkJni";

    protected void onComplete(boolean z, int i) {
        Log.d(TAG, "EnableExternalCaptureCompleteCallback.OnComplete. enable = " + z + "  result = " + i);
    }
}
