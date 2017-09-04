package com.tencent.av.sdk;

import android.graphics.Bitmap;
import android.hardware.Camera.Parameters;
import android.os.Handler;
import android.util.Log;
import com.tencent.av.utils.QLog;
import com.tencent.av.utils.VcSystemInfo;

public class AVVideoCtrl {
    public static final int AVPresetType1280720 = 6;
    public static final int AVPresetType192144 = 7;
    public static final int AVPresetType320240 = 1;
    public static final int AVPresetType480360 = 2;
    public static final int AVPresetType640368 = 4;
    public static final int AVPresetType640480 = 3;
    public static final int AVPresetType960540 = 5;
    public static final int BACK_CAMERA = 1;
    public static final int COLOR_FORMAT_I420 = 0;
    public static final int EXTERNAL_FORMAT_ARGB = 9;
    public static final int EXTERNAL_FORMAT_I420 = 0;
    public static final int EXTERNAL_FORMAT_NV12 = 3;
    public static final int EXTERNAL_FORMAT_NV21 = 1;
    public static final int EXTERNAL_FORMAT_RGB24 = 8;
    public static final int EXTERNAL_FORMAT_RGB565 = 7;
    public static final int FRONT_CAMERA = 0;
    public static final String TAG = "AVVideoCtrl";
    public int nativeObj;

    @Deprecated
    public static class RemoteVideoPreviewCallback {
        static final String TAG = "SdkJni";

        public void onFrameReceive(VideoFrame videoFrame) {
            Log.d(TAG, "base class RemoteVideoPreviewCallback.onFrameReceive");
        }
    }

    private native void initNative(int i);

    private native int nativeAddWatermark(int i, int[] iArr, int i2, int i3);

    private native boolean nativeSetLocalVideoPreProcessCallback(LocalVideoPreProcessCallback localVideoPreProcessCallback);

    private native void unInitNative();

    public native int enableCamera(int i, boolean z, EnableCameraCompleteCallback enableCameraCompleteCallback);

    public native int enableExternalCapture(boolean z, boolean z2, EnableExternalCaptureCompleteCallback enableExternalCaptureCompleteCallback);

    public native int fillExternalCaptureFrame(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6);

    public native Object getCamera();

    public native Object getCameraHandler();

    public native int getCameraNum();

    public native Object getCameraPara();

    public native String getQualityTips();

    public native void inputBeautyParam(float f);

    public native void inputWhiteningParam(float f);

    public native void setAfterPreviewListener(AfterPreviewListener afterPreviewListener);

    public native void setCameraPara(Parameters parameters);

    public native void setCameraPreviewChangeCallback(CameraPreviewChangeCallback cameraPreviewChangeCallback);

    public native int setHwEnableFlag(int i, int i2);

    public native boolean setLocalVideoPreviewCallback(LocalVideoPreviewCallback localVideoPreviewCallback);

    public native boolean setRemoteMediaVideoPreviewCallback(RemoteMediaVideoPreviewCallback remoteMediaVideoPreviewCallback);

    public native boolean setRemoteScreenVideoPreviewCallback(RemoteScreenVideoPreviewCallback remoteScreenVideoPreviewCallback);

    @Deprecated
    public native boolean setRemoteVideoPreviewCallback(RemoteVideoPreviewCallback remoteVideoPreviewCallback);

    public native boolean setRemoteVideoPreviewCallbackWithByteBuffer(RemoteVideoPreviewCallbackWithByteBuffer remoteVideoPreviewCallbackWithByteBuffer);

    public native void setRotation(int i);

    public native int switchCamera(int i, SwitchCameraCompleteCallback switchCameraCompleteCallback);

    AVVideoCtrl() {
        this.nativeObj = 0;
        this.nativeObj = 0;
    }

    public boolean setLocalVideoPreProcessCallback(LocalVideoPreProcessCallback localVideoPreProcessCallback) {
        Object cameraHandler = getCameraHandler();
        if (cameraHandler == null || !(cameraHandler instanceof Handler)) {
            QLog.d(TAG, 0, "getCameraHandler ERROR!!!!");
            return nativeSetLocalVideoPreProcessCallback(localVideoPreProcessCallback);
        }
        QLog.d(TAG, 0, "getCameraHandler OK!!!!");
        ((Handler) cameraHandler).post(new 1(this, localVideoPreProcessCallback));
        return true;
    }

    public static boolean isEnableBeauty() {
        QLog.d(TAG, 0, "isEnable = " + VcSystemInfo.isBeautySupported());
        return VcSystemInfo.isBeautySupported();
    }

    public int addWatermark(int i, Bitmap bitmap) {
        if (i < 1 || i > 7) {
            QLog.d(TAG, 0, "AVPresetType error");
            return 1004;
        } else if (bitmap == null) {
            QLog.d(TAG, 0, "bitmap null");
            return 1004;
        } else {
            int[] iArr = new int[(bitmap.getWidth() * bitmap.getHeight())];
            bitmap.getPixels(iArr, 0, bitmap.getWidth(), 0, 0, bitmap.getWidth(), bitmap.getHeight());
            return nativeAddWatermark(i, iArr, bitmap.getWidth(), bitmap.getHeight());
        }
    }

    void init(int i) {
        initNative(i);
    }

    void unInit() {
        unInitNative();
    }
}
