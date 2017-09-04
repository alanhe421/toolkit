package com.tencent.av.sdk;

import android.util.Log;
import com.tencent.av.utils.PhoneStatusMonitor.PhoneStatusListener;

class AVAudioCtrl$MyPhoneStatusListener implements PhoneStatusListener {
    final /* synthetic */ AVAudioCtrl this$0;

    AVAudioCtrl$MyPhoneStatusListener(AVAudioCtrl aVAudioCtrl) {
        this.this$0 = aVAudioCtrl;
    }

    public void onCallStateChanged(boolean z) {
        if (AVAudioCtrl.access$600(this.this$0)) {
            Log.e("SdkJni", "onCallStateChanged isSystemApp return");
            return;
        }
        Log.e("SdkJni", "onCallStateChanged isCalling: " + z);
        this.this$0.mIsCalling = z;
        if (z) {
            this.this$0.pauseAudio();
            Log.e("SdkJni", "MyPhoneStatusListener iscalling ");
            if (AVAudioCtrl.access$400(this.this$0) != null) {
                Log.e("SdkJni", "MyPhoneStatusListener stopService ");
                AVAudioCtrl.access$400(this.this$0).stopService();
                return;
            }
            return;
        }
        Log.e("SdkJni", "MyPhoneStatusListener notcalling ");
        this.this$0.resumeAudio();
        if (AVAudioCtrl.access$400(this.this$0) != null) {
            AVAudioCtrl.access$400(this.this$0).startService(AVAudioCtrl.access$700(this.this$0));
            Log.e("SdkJni", "MyPhoneStatusListener startService ");
        }
    }
}
