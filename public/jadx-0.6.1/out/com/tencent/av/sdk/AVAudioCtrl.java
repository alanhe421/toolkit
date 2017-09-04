package com.tencent.av.sdk;

import android.content.Context;
import android.util.Log;
import com.tencent.av.utils.NioUtils;
import com.tencent.av.utils.PhoneStatusMonitor;
import com.tencent.av.utils.PhoneStatusMonitor.PhoneStatusListener;
import com.tencent.sharp.jni.TraeAudioManager;
import com.tencent.sharp.jni.TraeAudioSession;
import java.nio.ByteBuffer;

public class AVAudioCtrl {
    public static final int AUDIO_CODEC_TYPE_CELT = 4103;
    public static final int AUDIO_CODEC_TYPE_SILK = 4102;
    public static final int AUDIO_DEVICE_CLOSE = 0;
    public static final int AUDIO_DEVICE_NOT_EXIST = 3;
    public static final int AUDIO_DEVICE_OPEN = 1;
    public static final int AUDIO_DEVICE_OPERATING = 2;
    public static final int OUTPUT_MODE_HEADSET = 0;
    public static final int OUTPUT_MODE_SPEAKER = 1;
    static final String TAG = "SdkJni";
    private ByteBuffer audioDataByteBuffer;
    private boolean isEnableExternalAudioDataInput;
    private boolean isSystemApp;
    private TraeAudioSession mAudioSession;
    private String mAudioSessionType;
    private String mAudioStateBeforePhoneCall;
    private Delegate mDelegate;
    private String[] mDeviceList;
    boolean mIsCalling;
    private PhoneStatusListener mPhoneStatusListener;
    private PhoneStatusMonitor mPhoneStatusMonitor;
    private String mSelectedDeviceName;
    private int mVoiceStreamType;
    int nativeObj;

    @Deprecated
    public static class AudioFrame extends AudioFrameWithoutData {
        public byte[] data;
    }

    @Deprecated
    public static class RegistAudioDataCompleteCallback {
        static final String TAG = "SdkJni";

        protected int onComplete(AudioFrame audioFrame, int i) {
            return 1;
        }
    }

    private native int nativeChangeAudioCategory(int i);

    private native int nativeFillExternalAudioFrame(ByteBuffer byteBuffer, int i, int i2, int i3, int i4);

    public native int GetAudioCategory();

    public native int GetAudioDataDBVolume(int i);

    public native int SetAudioDataDBVolume(int i, int i2);

    public native AudioFrameDesc getAudioDataFormat(int i);

    public native float getAudioDataVolume(int i);

    public native int getDynamicVolume();

    public native String getQualityTips();

    public native int getVolume();

    native boolean initNative(int i);

    native boolean nativeEnableMic(boolean z);

    native boolean nativeEnableSpeaker(boolean z);

    native int nativeGetMicState();

    native int nativeGetSpeakerState();

    native void pauseAudio();

    @Deprecated
    public native int registAudioDataCallback(int i, RegistAudioDataCompleteCallback registAudioDataCompleteCallback);

    public native int registAudioDataCallbackWithByteBuffer(int i, RegistAudioDataCompleteCallbackWithByteBuffer registAudioDataCompleteCallbackWithByteBuffer);

    native void resumeAudio();

    public native boolean setAudioDataFormat(int i, AudioFrameDesc audioFrameDesc);

    public native int setAudioDataVolume(int i, float f);

    native boolean uninitNative();

    public native int unregistAudioDataCallback(int i);

    public native int unregistAudioDataCallbackAll();

    AVAudioCtrl() {
        this.nativeObj = 0;
        this.mIsCalling = false;
        this.mAudioSessionType = TraeAudioManager.VIDEO_CONFIG;
        this.mSelectedDeviceName = "";
        this.mAudioSession = null;
        this.mVoiceStreamType = 0;
        this.mAudioStateBeforePhoneCall = TraeAudioManager.DEVICE_NONE;
        this.mDelegate = null;
        this.isSystemApp = false;
        this.audioDataByteBuffer = null;
        this.isEnableExternalAudioDataInput = false;
        this.nativeObj = 0;
    }

    public boolean enableMic(boolean z) {
        if (this.mIsCalling) {
            return false;
        }
        return nativeEnableMic(z);
    }

    public boolean enableSpeaker(boolean z) {
        if (this.mIsCalling) {
            return false;
        }
        return nativeEnableSpeaker(z);
    }

    public int getMicState() {
        return nativeGetMicState();
    }

    public int getSpeakerState() {
        return nativeGetSpeakerState();
    }

    public boolean setAudioOutputMode(int i) {
        if (i == 0) {
            if (this.mDeviceList == null) {
                return false;
            }
            boolean z = false;
            do {
                int i2;
                for (i2 = 0; i2 < this.mDeviceList.length && !r0; i2++) {
                    if (TraeAudioManager.DEVICE_WIREDHEADSET.equals(this.mDeviceList[i2])) {
                        this.mAudioSession.connectDevice(TraeAudioManager.DEVICE_WIREDHEADSET);
                        z = true;
                        break;
                    }
                }
                for (i2 = 0; i2 < this.mDeviceList.length && !r0; i2++) {
                    if (TraeAudioManager.DEVICE_BLUETOOTHHEADSET.equals(this.mDeviceList[i2])) {
                        this.mAudioSession.connectDevice(TraeAudioManager.DEVICE_BLUETOOTHHEADSET);
                        z = true;
                        break;
                    }
                }
                for (i2 = 0; i2 < this.mDeviceList.length && !r0; i2++) {
                    if (TraeAudioManager.DEVICE_EARPHONE.equals(this.mDeviceList[i2])) {
                        this.mAudioSession.connectDevice(TraeAudioManager.DEVICE_EARPHONE);
                        z = true;
                        continue;
                        break;
                    }
                }
            } while (!z);
        } else if (1 != i) {
            return false;
        } else {
            this.mAudioSession.connectDevice(TraeAudioManager.DEVICE_SPEAKERPHONE);
        }
        return true;
    }

    public int getAudioOutputMode() {
        if (this.mSelectedDeviceName.endsWith(TraeAudioManager.DEVICE_SPEAKERPHONE)) {
            return 1;
        }
        return (this.mSelectedDeviceName.endsWith(TraeAudioManager.DEVICE_WIREDHEADSET) || this.mSelectedDeviceName.endsWith(TraeAudioManager.DEVICE_BLUETOOTHHEADSET) || !this.mSelectedDeviceName.endsWith(TraeAudioManager.DEVICE_EARPHONE)) ? 0 : 0;
    }

    public void setDelegate(Delegate delegate) {
        this.mDelegate = delegate;
    }

    public int changeAudioCategory(int i) {
        return nativeChangeAudioCategory(i);
    }

    boolean init(Context context, int i) {
        if (!initNative(i)) {
            return false;
        }
        TraeAudioManager.init(context);
        this.mAudioSession = new TraeAudioSession(context, new 1(this));
        this.mAudioSession.startService(this.mAudioSessionType);
        this.mAudioSession.getDeviceList();
        this.mPhoneStatusListener = new MyPhoneStatusListener(this);
        this.mPhoneStatusMonitor = new PhoneStatusMonitor(context, this.mPhoneStatusListener);
        return true;
    }

    void uninit() {
        if (this.mAudioSession != null) {
            this.mAudioSession.setCallback(null);
            try {
                this.mAudioSession.stopService();
                this.mAudioSession.release();
            } catch (Exception e) {
            } finally {
                this.mAudioSession = null;
            }
        }
        TraeAudioManager.uninit();
        if (this.mPhoneStatusMonitor != null) {
            this.mPhoneStatusMonitor.uninit();
            this.mPhoneStatusMonitor = null;
        }
        this.mPhoneStatusListener = null;
        uninitNative();
    }

    public void stopTRAEService() {
        if (this.mAudioSession != null) {
            Log.e(TAG, "AVAudioCtrl stopTRAEService succ");
            this.mAudioSession.stopService();
            return;
        }
        Log.e(TAG, "AVAudioCtrl stopTRAEService mAudioSession = null");
    }

    public void startTRAEService() {
        if (this.mAudioSession != null) {
            this.mAudioSession.startService(this.mAudioSessionType);
            Log.e(TAG, "AVAudioCtrl startTRAEService succ");
            return;
        }
        Log.e(TAG, "AVAudioCtrl startTRAEService mAudioSession = null");
    }

    public void setIsSystemApp(boolean z) {
        this.isSystemApp = z;
    }

    public void startTRAEServiceWhenIsSystemApp() {
        if (this.mAudioSession != null) {
            resumeAudio();
            this.mAudioSession.startService(this.mAudioSessionType);
            Log.e(TAG, "AVAudioCtrl startTRAEServiceWhenIsSystemApp succ");
            return;
        }
        Log.e(TAG, "AVAudioCtrl startTRAEServiceWhenIsSystemApp mAudioSession = null");
    }

    public void stopTRAEServiceWhenIsSystemApp() {
        if (this.mAudioSession != null) {
            Log.e(TAG, "AVAudioCtrl stopTRAEServiceWhenIsSystemApp succ");
            pauseAudio();
            this.mAudioSession.stopService();
            return;
        }
        Log.e(TAG, "AVAudioCtrl stopTRAEServiceWhenIsSystemApp mAudioSession = null");
    }

    public synchronized void enableExternalAudioDataInput() {
        if (this.audioDataByteBuffer != null) {
            NioUtils.destroyDirectByteBuffer(this.audioDataByteBuffer);
            this.audioDataByteBuffer = null;
        }
        this.isEnableExternalAudioDataInput = true;
    }

    public synchronized void disableExternalAudioDataInput() {
        if (this.audioDataByteBuffer != null) {
            NioUtils.destroyDirectByteBuffer(this.audioDataByteBuffer);
            this.audioDataByteBuffer = null;
        }
        this.isEnableExternalAudioDataInput = false;
    }

    public synchronized int fillExternalAudioFrame(byte[] bArr, int i, int i2, int i3, int i4) {
        int nativeFillExternalAudioFrame;
        if (this.isEnableExternalAudioDataInput) {
            if (!(this.audioDataByteBuffer == null || this.audioDataByteBuffer.capacity() == i)) {
                NioUtils.destroyDirectByteBuffer(this.audioDataByteBuffer);
                this.audioDataByteBuffer = null;
            }
            if (this.audioDataByteBuffer == null) {
                this.audioDataByteBuffer = NioUtils.createDirectByteBuffer(i);
            }
            this.audioDataByteBuffer.put(bArr, 0, i);
            this.audioDataByteBuffer.clear();
            nativeFillExternalAudioFrame = nativeFillExternalAudioFrame(this.audioDataByteBuffer, i, i2, i3, i4);
        } else {
            nativeFillExternalAudioFrame = 1;
        }
        return nativeFillExternalAudioFrame;
    }
}
