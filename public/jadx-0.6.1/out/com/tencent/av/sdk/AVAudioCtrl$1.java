package com.tencent.av.sdk;

import android.util.Log;
import com.tencent.sharp.jni.TraeAudioManager;
import com.tencent.sharp.jni.TraeAudioSession.ITraeAudioCallback;

class AVAudioCtrl$1 implements ITraeAudioCallback {
    final /* synthetic */ AVAudioCtrl this$0;

    AVAudioCtrl$1(AVAudioCtrl aVAudioCtrl) {
        this.this$0 = aVAudioCtrl;
    }

    public void onServiceStateUpdate(boolean z) {
    }

    public void onDeviceListUpdate(String[] strArr, String str, String str2, String str3) {
        Log.e("SdkJni", "Connect Device:" + str);
        AVAudioCtrl.access$002(this.this$0, str);
        AVAudioCtrl.access$102(this.this$0, strArr);
        if (AVAudioCtrl.access$200(this.this$0) != null) {
            AVAudioCtrl.access$200(this.this$0).onOutputModeChange(AVAudioCtrl.access$000(this.this$0).endsWith(TraeAudioManager.DEVICE_SPEAKERPHONE) ? 1 : 0);
        }
        if (!AVAudioCtrl.access$300(this.this$0).equals(TraeAudioManager.DEVICE_NONE)) {
            Log.e("SdkJni", "!mAudioStateBeforePhoneCall.equals(TraeAudioManager.DEVICE_NONE");
            if (!str.equals(AVAudioCtrl.access$300(this.this$0))) {
                AVAudioCtrl.access$400(this.this$0).connectDevice(AVAudioCtrl.access$300(this.this$0));
            }
            AVAudioCtrl.access$302(this.this$0, TraeAudioManager.DEVICE_NONE);
        }
    }

    public void onDeviceChangabledUpdate(boolean z) {
    }

    public void onStreamTypeUpdate(int i) {
        AVAudioCtrl.access$502(this.this$0, i);
    }

    public void onGetDeviceListRes(int i, String[] strArr, String str, String str2, String str3) {
        Log.e("SdkJni", "onGetDeviceListRes" + str);
        if (i == 0) {
            AVAudioCtrl.access$102(this.this$0, strArr);
            AVAudioCtrl.access$002(this.this$0, str);
        }
    }

    public void onConnectDeviceRes(int i, String str, boolean z) {
        Log.e("SdkJni", "onConnectDeviceRes" + str);
        if (i == 0 && z && z) {
            AVAudioCtrl.access$002(this.this$0, str);
        }
    }

    public void onIsDeviceChangabledRes(int i, boolean z) {
    }

    public void onGetConnectedDeviceRes(int i, String str) {
        Log.e("SdkJni", "onGetConnectedDeviceRes" + str);
    }

    public void onGetConnectingDeviceRes(int i, String str) {
        Log.e("SdkJni", "onGetConnectingDeviceRes" + str);
    }

    public void onGetStreamTypeRes(int i, int i2) {
        AVAudioCtrl.access$502(this.this$0, i2);
    }

    public void onRingCompletion(int i, String str) {
    }

    public void onVoicecallPreprocessRes(int i) {
    }

    public void onAudioRouteSwitchStart(String str, String str2) {
    }

    public void onAudioRouteSwitchEnd(String str, long j) {
    }
}
