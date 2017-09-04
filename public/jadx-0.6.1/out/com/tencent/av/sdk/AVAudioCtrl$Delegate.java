package com.tencent.av.sdk;

import android.util.Log;

public class AVAudioCtrl$Delegate {
    static final String TAG = "SdkJni";

    protected void onOutputModeChange(int i) {
        Log.d(TAG, "onOutputModeChange outputMode = " + i);
    }
}
