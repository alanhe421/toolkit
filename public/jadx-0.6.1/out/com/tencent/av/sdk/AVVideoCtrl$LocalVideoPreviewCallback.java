package com.tencent.av.sdk;

import android.util.Log;

public class AVVideoCtrl$LocalVideoPreviewCallback {
    static final String TAG = "SdkJni";

    public void onFrameReceive(AVVideoCtrl$VideoFrame aVVideoCtrl$VideoFrame) {
        Log.d(TAG, "base class LocalVideoPreviewCallback.onFrameReceive");
    }
}
