package com.tencent.av.camera;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.Size;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.HandlerThread;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.WindowManager;
import com.dynamicload.Lib.DLConstants;
import com.etrump.jni.ETConverter;
import com.tencent.av.config.ConfigSystemImpl;
import com.tencent.av.sdk.AVUILoopProxy;
import com.tencent.av.sdk.AVVideoCtrl$AfterPreviewListener;
import com.tencent.av.sdk.AVVideoCtrl$CameraPreviewChangeCallback;
import com.tencent.av.sdk.AVVideoCtrl$VideoFrame;
import com.tencent.av.utils.PhoneStatusTools;
import com.tencent.av.utils.QLog;
import com.tencent.av.utils.VcSystemInfo;
import com.tencent.openqq.protocol.imsdk.im_common;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class VcCamera {
    static final int BACK_CAMERA = 1;
    static final int CAMERA_NONE = -1;
    static final String CAMERA_THREAD_NAME = "CAMERA";
    static final int FRONT_CAMERA = 0;
    static CameraInformation Info = new CameraInformation();
    static final String TAG = "VcCamera";
    static boolean isCameraOpened = false;
    public static WeakReference<AVVideoCtrl$CameraPreviewChangeCallback> mCameraPreviewChangeCallback = null;
    public static WeakReference<SurfaceHolder> mHolder = null;
    static volatile int nInFPS;
    volatile int CUR_CAMERA = -1;
    int CameraId = -1;
    int CompenSateRecvAngle = 0;
    int CompenSateSendAngle = 0;
    String DEV_MANUFACTURER;
    String DEV_MODEL;
    volatile int LAST_CAMERA = -1;
    int NUM_CAMERA = 0;
    int SDK_VERSION;
    CameraCaptureSettings VideoChatSettings = new CameraCaptureSettings();
    public Object afterPreviewListener;
    public final Object afterPreviewListenerLock = new Object();
    private String androidSysOorientationConfig;
    private PreviewCallback cameraCallback = new PreviewCallback() {
        @TargetApi(8)
        public void onPreviewFrame(byte[] bArr, Camera camera) {
            Exception e;
            int i = 1;
            if (VcCamera.nInFPS > 0) {
                int rotation;
                if (VcCamera.this.fitSdkVersion()) {
                    if (VcCamera.this.CUR_CAMERA == 0) {
                        rotation = (360 - (((VcCamera.this.devDisplay.getRotation() * 90) + VcCamera.this.getOrientation()) % 360)) % 360;
                    } else if (VcCamera.this.CUR_CAMERA == 1) {
                        rotation = ((VcCamera.this.getOrientation() - (VcCamera.this.devDisplay.getRotation() * 90)) + 360) % 360;
                    } else {
                        rotation = 0;
                    }
                    rotation += VcCamera.this.getRotation();
                    if (VcCamera.this.getOrientation() == im_common.WPA_QZONE || VcCamera.this.getOrientation() == 90) {
                        if (VcCamera.this.getRotation() % 180 == 0 && VcCamera.this.CUR_CAMERA == 0 && !VcCamera.this.androidSysOorientationConfig.equalsIgnoreCase("ZTE")) {
                            rotation += 180;
                        }
                    } else if (VcCamera.this.getOrientation() == 0 || VcCamera.this.getOrientation() == 180) {
                        if (VcCamera.this.getRotation() == 90 || VcCamera.this.getRotation() == im_common.WPA_QZONE) {
                            if (VcCamera.this.CUR_CAMERA == 0 && !VcCamera.this.mbIsTablet) {
                                rotation += 180;
                            }
                        } else if (VcCamera.this.CUR_CAMERA == 0 && VcCamera.this.mbIsTablet) {
                            rotation += 180;
                        }
                    }
                    if (VcCamera.this.CUR_CAMERA == 0) {
                        if (VcCamera.this.mFrontCameraAngle > 0) {
                            rotation += 360 - VcCamera.this.mFrontCameraAngle;
                        } else {
                            rotation += VcCamera.this.getRemoteAngleForFrontCamera(VcCamera.this.getRotation());
                        }
                    } else if (VcCamera.this.mBackCameraAngle > 0) {
                        rotation += VcCamera.this.mBackCameraAngle;
                    } else {
                        rotation += VcCamera.this.getRemoteAngleForBackCamera(VcCamera.this.getRotation());
                    }
                } else {
                    rotation = ((VcCamera.this.getRotation() + VcCamera.this.CompenSateRecvAngle) + 90) % 360;
                    if (VcCamera.this.getOrientation() == im_common.WPA_QZONE || VcCamera.this.getOrientation() == 90) {
                        if (VcCamera.this.CUR_CAMERA == 0) {
                            rotation += 90;
                        } else {
                            rotation += 180;
                        }
                    }
                    if (VcCamera.this.CUR_CAMERA != 0) {
                        rotation += 180;
                    } else if (!VcCamera.this.androidSysOorientationConfig.equalsIgnoreCase("ZTE")) {
                        rotation += 180;
                    }
                    if (VcCamera.this.CUR_CAMERA == 0) {
                        if (VcCamera.this.mFrontCameraAngle > 0) {
                            rotation += 360 - VcCamera.this.mFrontCameraAngle;
                        } else {
                            rotation += VcCamera.this.getRemoteAngleForFrontCamera(VcCamera.this.getRotation());
                        }
                    } else if (VcCamera.this.mBackCameraAngle > 0) {
                        rotation += VcCamera.this.mBackCameraAngle;
                    } else {
                        rotation += VcCamera.this.getRemoteAngleForBackCamera(VcCamera.this.getRotation());
                    }
                }
                int i2 = (rotation % 360) / 90;
                if (bArr == null) {
                    QLog.d(VcCamera.TAG, 0, "onPreviewFrame data null");
                    return;
                }
                if (VcCamera.this.LAST_CAMERA != VcCamera.this.CUR_CAMERA) {
                    AVUILoopProxy.postTaskToMainLooper(new CameraPreviewChangeRunnable(VcCamera.this.CUR_CAMERA));
                    VcCamera.this.LAST_CAMERA = VcCamera.this.CUR_CAMERA;
                }
                int length = bArr.length;
                if (length != ((VcCamera.this.VideoChatSettings.width * VcCamera.this.VideoChatSettings.height) * ImageFormat.getBitsPerPixel(VcCamera.this.VideoChatSettings.format)) / 8) {
                    QLog.d(VcCamera.TAG, 0, "camera param is not the same as setting, real data_width:" + VcCamera.this.VideoChatSettings.width + "data_height:" + VcCamera.this.VideoChatSettings.height + "data_format:" + VcCamera.this.VideoChatSettings.format + "real length: " + length);
                    if (VcCamera.this.mFrameSizes != null) {
                        for (Size size : VcCamera.this.mFrameSizes) {
                            if (((size.height * size.width) * ImageFormat.getBitsPerPixel(VcCamera.this.VideoChatSettings.format)) / 8 == length) {
                                QLog.d(VcCamera.TAG, 0, "get real size form support sizes, width: " + size.width + "data_height:" + size.height + "data_format:" + VcCamera.this.VideoChatSettings.format + "real length: " + length);
                                VcCamera.this.afterCapture(bArr, length, size.width, size.height, i2, VcCamera.this.VideoChatSettings.format);
                                rotation = 1;
                                break;
                            }
                        }
                    }
                    rotation = 0;
                    if (rotation == 0 && camera != null) {
                        try {
                            Parameters parameters = camera.getParameters();
                            if (parameters != null) {
                                int i3 = parameters.getPreviewSize().width;
                                int i4 = parameters.getPreviewSize().height;
                                int previewFormat = parameters.getPreviewFormat();
                                QLog.d(VcCamera.TAG, 0, "get preview camera info, data_width: " + i3 + "data_height: " + i4 + "data_format: " + previewFormat + "real length: " + length);
                                if (((i3 * i4) * ImageFormat.getBitsPerPixel(previewFormat)) / 8 == length) {
                                    try {
                                        VcCamera.this.afterCapture(bArr, length, i3, i4, i2, previewFormat);
                                    } catch (Exception e2) {
                                        e = e2;
                                        e.printStackTrace();
                                        rotation = i;
                                        if (rotation == 0) {
                                            QLog.d(VcCamera.TAG, 0, "data len is not matched");
                                        }
                                        if (VcCamera.this.mPreBuffer == null) {
                                            VcCamera.this.mCamera.addCallbackBuffer(VcCamera.this.mPreBuffer);
                                        }
                                    }
                                    rotation = i;
                                }
                            }
                        } catch (Exception e3) {
                            i = rotation;
                            e = e3;
                            e.printStackTrace();
                            rotation = i;
                            if (rotation == 0) {
                                QLog.d(VcCamera.TAG, 0, "data len is not matched");
                            }
                            if (VcCamera.this.mPreBuffer == null) {
                                VcCamera.this.mCamera.addCallbackBuffer(VcCamera.this.mPreBuffer);
                            }
                        }
                    }
                    if (rotation == 0) {
                        QLog.d(VcCamera.TAG, 0, "data len is not matched");
                    }
                } else {
                    VcCamera.this.afterCapture(bArr, length, VcCamera.this.VideoChatSettings.width, VcCamera.this.VideoChatSettings.height, i2, VcCamera.this.VideoChatSettings.format);
                }
                if (VcCamera.this.mPreBuffer == null) {
                    VcCamera.this.mCamera.addCallbackBuffer(VcCamera.this.mPreBuffer);
                }
            }
        }
    };
    boolean cameraInitialed = false;
    public final Object cameraPreviewChangeCallbackLock = new Object();
    Display devDisplay;
    public final Object holderLock = new Object();
    boolean isPreviewFpsPrint = false;
    boolean isPreviewSizePrint = false;
    int mBackCameraAngle = 0;
    Camera mCamera = null;
    private Handler mCameraHandler = null;
    HandlerThread mCameraThread = null;
    Context mContext = null;
    List<Size> mFrameSizes = null;
    int mFrontCameraAngle = 0;
    byte[] mPreBuffer = null;
    boolean mbIsTablet = false;
    int nativeObj = 0;

    static class CameraInformation {
        int facing;
        int orientation;
        int rotation;

        CameraInformation() {
        }
    }

    class CameraPreviewChangeRunnable implements Runnable {
        int cameraId = -1;

        CameraPreviewChangeRunnable(int i) {
            this.cameraId = i;
        }

        public void run() {
            QLog.d(VcCamera.TAG, 0, "onCameraPreviewChangeCallback run.");
            synchronized (VcCamera.this.cameraPreviewChangeCallbackLock) {
                AVVideoCtrl$CameraPreviewChangeCallback aVVideoCtrl$CameraPreviewChangeCallback = null;
                if (VcCamera.mCameraPreviewChangeCallback != null) {
                    aVVideoCtrl$CameraPreviewChangeCallback = (AVVideoCtrl$CameraPreviewChangeCallback) VcCamera.mCameraPreviewChangeCallback.get();
                }
                if (aVVideoCtrl$CameraPreviewChangeCallback != null) {
                    aVVideoCtrl$CameraPreviewChangeCallback.onCameraPreviewChangeCallback(this.cameraId);
                }
            }
        }
    }

    class CloseCompleteRunnable implements Runnable {
        WeakReference<VcCamera> mHost = null;

        CloseCompleteRunnable(VcCamera vcCamera) {
            this.mHost = new WeakReference(vcCamera);
        }

        public void run() {
            if (this.mHost.get() != null) {
                ((VcCamera) this.mHost.get()).onCloseCamera();
            }
        }
    }

    class FinishCompleteRunnable implements Runnable {
        WeakReference<VcCamera> mHost = null;

        FinishCompleteRunnable(VcCamera vcCamera) {
            this.mHost = new WeakReference(vcCamera);
        }

        public void run() {
            if (this.mHost.get() != null) {
                ((VcCamera) this.mHost.get()).onFinishCamera();
            }
        }
    }

    class OpenCompleteRunnable implements Runnable {
        WeakReference<VcCamera> mHost = null;
        boolean mResult = false;

        OpenCompleteRunnable(VcCamera vcCamera, boolean z) {
            this.mResult = z;
            this.mHost = new WeakReference(vcCamera);
        }

        public void run() {
            if (this.mHost.get() != null) {
                ((VcCamera) this.mHost.get()).onOpenCamera(this.mResult);
            }
        }
    }

    class SwitchCameraRunnable implements Runnable {
        int mCameraId = 0;

        SwitchCameraRunnable(int i) {
            this.mCameraId = i;
        }

        public void run() {
            if (VcCamera.this.NUM_CAMERA >= 2 && VcCamera.this.mCamera != null && this.mCameraId != VcCamera.this.CUR_CAMERA) {
                int i;
                SurfaceHolder surfaceHolder;
                VcCamera.this.closeInternal();
                if (this.mCameraId == 0) {
                    if (VcCamera.this.openFrontCamera()) {
                        VcCamera.this.setCameraPara(VcCamera.this.VideoChatSettings.width, VcCamera.this.VideoChatSettings.height);
                        i = 1;
                    }
                    i = 0;
                } else {
                    if (VcCamera.this.openBackCamera()) {
                        VcCamera.this.setCameraPara(VcCamera.this.VideoChatSettings.width, VcCamera.this.VideoChatSettings.height);
                        i = 1;
                    }
                    i = 0;
                }
                synchronized (VcCamera.this.holderLock) {
                    surfaceHolder = VcCamera.mHolder == null ? null : (SurfaceHolder) VcCamera.mHolder.get();
                }
                if (surfaceHolder == null) {
                    QLog.d(VcCamera.TAG, 0, "switchCamera holder == null");
                    i = 0;
                }
                if (i != 0) {
                    try {
                        if (VcCamera.this.mPreBuffer != null) {
                            VcCamera.this.mCamera.addCallbackBuffer(VcCamera.this.mPreBuffer);
                            VcCamera.this.mCamera.setPreviewCallbackWithBuffer(VcCamera.this.cameraCallback);
                        } else {
                            VcCamera.this.mCamera.setPreviewCallback(VcCamera.this.cameraCallback);
                        }
                        VcCamera.this.mCamera.setPreviewDisplay(surfaceHolder);
                        VcCamera.this.mCamera.startPreview();
                    } catch (Exception e) {
                        if (QLog.isColorLevel()) {
                            QLog.d(VcCamera.TAG, 0, "setPreviewDisplay error", e);
                        }
                    }
                }
            }
        }
    }

    native void onCaptureFrame(byte[] bArr, int i, int i2, int i3, int i4, int i5);

    native void onCloseCamera();

    native void onFinishCamera();

    native void onOpenCamera(boolean z);

    public static int getVersion() {
        return VERSION.SDK_INT;
    }

    public int getCompenSateSendAngle() {
        return this.CompenSateSendAngle;
    }

    public int getCompenSateRecvAngle() {
        return this.CompenSateRecvAngle;
    }

    public VcCamera(Context context) {
        if (context == null) {
            QLog.e(TAG, 0, "VcCamera initial context is null");
            return;
        }
        this.mContext = context;
        this.devDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
        this.androidSysOorientationConfig = get(this.mContext, "ro.qq.orientation");
        this.mbIsTablet = PhoneStatusTools.isTablet(context);
        this.SDK_VERSION = getVersion();
        this.DEV_MODEL = Build.MODEL;
        this.DEV_MANUFACTURER = Build.MANUFACTURER;
        Info.orientation = -1;
        Info.rotation = -1;
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "Device_Tag = " + this.DEV_MANUFACTURER + ": " + this.DEV_MODEL);
            QLog.d(TAG, 0, "Rom_Tag = " + VERSION.INCREMENTAL);
        }
    }

    private static ArrayList<Integer> splitInt(String str) {
        if (str == null) {
            return null;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        ArrayList<Integer> arrayList = new ArrayList();
        while (stringTokenizer.hasMoreElements()) {
            arrayList.add(Integer.valueOf(Integer.parseInt(stringTokenizer.nextToken())));
        }
        return arrayList;
    }

    public void setWebConfigFps(int i) {
        QLog.i(TAG, 0, "setWebConfigFps fps = " + i);
        nInFPS = i;
    }

    public void initCameraSetting(int i, int i2, int i3) {
        QLog.i(TAG, 0, "setWebConfigCameraSetting fps = " + i3 + "width = " + i + "height = " + i2);
        nInFPS = i3;
        this.VideoChatSettings.width = i;
        this.VideoChatSettings.height = i2;
    }

    private Camera trySamsungFrontCamera() {
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                camera.release();
            } catch (Exception e) {
                if (!QLog.isColorLevel()) {
                    return null;
                }
                QLog.e(TAG, 0, "trySamsungFrontCamera", e);
                return null;
            }
        }
        camera = Camera.open();
        if (camera == null) {
            return null;
        }
        Parameters parameters = camera.getParameters();
        parameters.set("camera-id", 2);
        camera.setParameters(parameters);
        this.mCamera = camera;
        return camera;
    }

    private Camera tryMotoFrontCamera() {
        Camera camera = this.mCamera;
        if (camera != null) {
            try {
                camera.release();
            } catch (Exception e) {
                if (QLog.isColorLevel()) {
                    QLog.d(TAG, 0, "tryMotoFrontCamera", e);
                }
                if (camera != null) {
                    camera.release();
                }
                return null;
            }
        }
        camera = Camera.open();
        if (camera == null) {
            return null;
        }
        Parameters parameters = (Parameters) camera.getClass().getMethod("getCustomParameters", new Class[0]).invoke(camera, new Object[0]);
        ArrayList splitInt = splitInt(parameters.get("camera-sensor-values"));
        Method method = camera.getClass().getMethod("setCustomParameters", new Class[]{parameters.getClass()});
        if (splitInt == null || splitInt.indexOf(Integer.valueOf(1)) == -1) {
            return camera;
        }
        parameters.set("camera-sensor", "1");
        method.invoke(camera, new Object[]{parameters});
        return camera;
    }

    private Camera getFrontCamera() {
        if (fitSdkVersion()) {
            return openFrontFacingCamera();
        }
        if (this.DEV_MANUFACTURER.equalsIgnoreCase("motorola")) {
            return tryMotoFrontCamera();
        }
        if (this.DEV_MANUFACTURER.equalsIgnoreCase(DLConstants.BRAND_SAMSUNG)) {
            return trySamsungFrontCamera();
        }
        return null;
    }

    private void adjustDirection(Camera camera) {
        try {
            int previewAngleForFrontCamera;
            Method method = camera.getClass().getMethod("setDisplayOrientation", new Class[]{Integer.TYPE});
            if (this.CUR_CAMERA == 0) {
                previewAngleForFrontCamera = getPreviewAngleForFrontCamera() % 360;
            } else {
                previewAngleForFrontCamera = getPreviewAngleForBackCamera() % 360;
            }
            method.invoke(camera, new Object[]{Integer.valueOf(previewAngleForFrontCamera)});
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "adjustDirection", e);
            }
        }
    }

    private static int GetNumberOfCamera() {
        try {
            return Integer.parseInt(Class.forName("android.hardware.Camera").getMethod("getNumberOfCameras", new Class[0]).invoke(null, (Object[]) null).toString());
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "GetNumberOfCamera", e);
            }
            return 1;
        }
    }

    private void setDisplayOrientation(Camera camera, int i) {
        try {
            camera.getClass().getMethod("setDisplayOrientation", new Class[]{Integer.TYPE}).invoke(camera, new Object[]{Integer.valueOf(i)});
        } catch (Exception e) {
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "setDisplayOrientation", e);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.hardware.Camera openFrontFacingCamera() {
        /*
        r14 = this;
        r2 = 0;
        r13 = 1;
        r4 = 0;
        r0 = -1;
        r14.CameraId = r0;
        r0 = "android.hardware.Camera";
        r7 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
        r0 = r14.NUM_CAMERA;	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
        if (r0 != 0) goto L_0x0017;
    L_0x0011:
        r0 = GetNumberOfCamera();	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
        r14.NUM_CAMERA = r0;	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
    L_0x0017:
        r0 = r14.NUM_CAMERA;	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
        if (r0 < r13) goto L_0x00bb;
    L_0x001b:
        r0 = "android.hardware.Camera$CameraInfo";
        r1 = java.lang.Class.forName(r0);	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
        if (r1 == 0) goto L_0x017a;
    L_0x0024:
        r0 = r1.newInstance();	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
        r6 = r0;
    L_0x0029:
        if (r6 == 0) goto L_0x0177;
    L_0x002b:
        r0 = r6.getClass();	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
        r3 = "facing";
        r0 = r0.getField(r3);	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
        r5 = r0;
    L_0x0037:
        r0 = "getCameraInfo";
        r3 = 2;
        r3 = new java.lang.Class[r3];	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
        r8 = 0;
        r9 = java.lang.Integer.TYPE;	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
        r3[r8] = r9;	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
        r8 = 1;
        r3[r8] = r1;	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
        r8 = r7.getMethod(r0, r3);	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
        if (r8 == 0) goto L_0x00bb;
    L_0x004b:
        if (r1 == 0) goto L_0x00bb;
    L_0x004d:
        if (r5 == 0) goto L_0x00bb;
    L_0x004f:
        r3 = r4;
        r1 = r2;
    L_0x0051:
        r0 = r14.NUM_CAMERA;	 Catch:{ ClassNotFoundException -> 0x0168, NoSuchMethodException -> 0x015f, NoSuchFieldException -> 0x0156, IllegalAccessException -> 0x014f, InvocationTargetException -> 0x0148, InstantiationException -> 0x0141, SecurityException -> 0x013a, Exception -> 0x0133 }
        if (r3 >= r0) goto L_0x0174;
    L_0x0055:
        r0 = 0;
        r9 = 2;
        r9 = new java.lang.Object[r9];	 Catch:{ ClassNotFoundException -> 0x0168, NoSuchMethodException -> 0x015f, NoSuchFieldException -> 0x0156, IllegalAccessException -> 0x014f, InvocationTargetException -> 0x0148, InstantiationException -> 0x0141, SecurityException -> 0x013a, Exception -> 0x0133 }
        r10 = 0;
        r11 = java.lang.Integer.valueOf(r3);	 Catch:{ ClassNotFoundException -> 0x0168, NoSuchMethodException -> 0x015f, NoSuchFieldException -> 0x0156, IllegalAccessException -> 0x014f, InvocationTargetException -> 0x0148, InstantiationException -> 0x0141, SecurityException -> 0x013a, Exception -> 0x0133 }
        r9[r10] = r11;	 Catch:{ ClassNotFoundException -> 0x0168, NoSuchMethodException -> 0x015f, NoSuchFieldException -> 0x0156, IllegalAccessException -> 0x014f, InvocationTargetException -> 0x0148, InstantiationException -> 0x0141, SecurityException -> 0x013a, Exception -> 0x0133 }
        r10 = 1;
        r9[r10] = r6;	 Catch:{ ClassNotFoundException -> 0x0168, NoSuchMethodException -> 0x015f, NoSuchFieldException -> 0x0156, IllegalAccessException -> 0x014f, InvocationTargetException -> 0x0148, InstantiationException -> 0x0141, SecurityException -> 0x013a, Exception -> 0x0133 }
        r8.invoke(r0, r9);	 Catch:{ ClassNotFoundException -> 0x0168, NoSuchMethodException -> 0x015f, NoSuchFieldException -> 0x0156, IllegalAccessException -> 0x014f, InvocationTargetException -> 0x0148, InstantiationException -> 0x0141, SecurityException -> 0x013a, Exception -> 0x0133 }
        r0 = r5.getInt(r6);	 Catch:{ ClassNotFoundException -> 0x0168, NoSuchMethodException -> 0x015f, NoSuchFieldException -> 0x0156, IllegalAccessException -> 0x014f, InvocationTargetException -> 0x0148, InstantiationException -> 0x0141, SecurityException -> 0x013a, Exception -> 0x0133 }
        if (r0 != r13) goto L_0x0171;
    L_0x006c:
        r0 = "open";
        r9 = 1;
        r9 = new java.lang.Class[r9];	 Catch:{ RuntimeException -> 0x0095 }
        r10 = 0;
        r11 = java.lang.Integer.TYPE;	 Catch:{ RuntimeException -> 0x0095 }
        r9[r10] = r11;	 Catch:{ RuntimeException -> 0x0095 }
        r0 = r7.getMethod(r0, r9);	 Catch:{ RuntimeException -> 0x0095 }
        if (r0 == 0) goto L_0x0171;
    L_0x007d:
        r9 = 0;
        r10 = 1;
        r10 = new java.lang.Object[r10];	 Catch:{ RuntimeException -> 0x0095 }
        r11 = 0;
        r12 = java.lang.Integer.valueOf(r3);	 Catch:{ RuntimeException -> 0x0095 }
        r10[r11] = r12;	 Catch:{ RuntimeException -> 0x0095 }
        r0 = r0.invoke(r9, r10);	 Catch:{ RuntimeException -> 0x0095 }
        r0 = (android.hardware.Camera) r0;	 Catch:{ RuntimeException -> 0x0095 }
        r14.CameraId = r3;	 Catch:{ RuntimeException -> 0x0095, ClassNotFoundException -> 0x016c, NoSuchMethodException -> 0x0163, NoSuchFieldException -> 0x015a, IllegalAccessException -> 0x0152, InvocationTargetException -> 0x014b, InstantiationException -> 0x0144, SecurityException -> 0x013d, Exception -> 0x0136 }
    L_0x0090:
        r1 = r3 + 1;
        r3 = r1;
        r1 = r0;
        goto L_0x0051;
    L_0x0095:
        r0 = move-exception;
        r1 = 0;
        r14.CameraId = r1;	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
        r1 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
        if (r1 == 0) goto L_0x00a9;
    L_0x009f:
        r1 = "VcCamera";
        r9 = 0;
        r10 = "openFrontFacingCamera";
        com.tencent.av.utils.QLog.e(r1, r9, r10, r0);	 Catch:{ ClassNotFoundException -> 0x00ab, NoSuchMethodException -> 0x00bc, NoSuchFieldException -> 0x00cd, IllegalAccessException -> 0x00de, InvocationTargetException -> 0x00ef, InstantiationException -> 0x0100, SecurityException -> 0x0111, Exception -> 0x0122 }
    L_0x00a9:
        r0 = r2;
        goto L_0x0090;
    L_0x00ab:
        r0 = move-exception;
    L_0x00ac:
        r1 = com.tencent.av.utils.QLog.isColorLevel();
        if (r1 == 0) goto L_0x00bb;
    L_0x00b2:
        r1 = "VcCamera";
        r3 = "openFrontFacingCamera ClassNotFoundException";
        com.tencent.av.utils.QLog.e(r1, r4, r3, r0);
    L_0x00bb:
        return r2;
    L_0x00bc:
        r0 = move-exception;
    L_0x00bd:
        r1 = com.tencent.av.utils.QLog.isColorLevel();
        if (r1 == 0) goto L_0x00bb;
    L_0x00c3:
        r1 = "VcCamera";
        r3 = "openFrontFacingCamera NoSuchMethodException";
        com.tencent.av.utils.QLog.e(r1, r4, r3, r0);
        goto L_0x00bb;
    L_0x00cd:
        r0 = move-exception;
    L_0x00ce:
        r1 = com.tencent.av.utils.QLog.isColorLevel();
        if (r1 == 0) goto L_0x00bb;
    L_0x00d4:
        r1 = "VcCamera";
        r3 = "openFrontFacingCamera NoSuchFieldException";
        com.tencent.av.utils.QLog.e(r1, r4, r3, r0);
        goto L_0x00bb;
    L_0x00de:
        r0 = move-exception;
    L_0x00df:
        r1 = com.tencent.av.utils.QLog.isColorLevel();
        if (r1 == 0) goto L_0x00bb;
    L_0x00e5:
        r1 = "VcCamera";
        r3 = "openFrontFacingCamera IllegalAccessException";
        com.tencent.av.utils.QLog.e(r1, r4, r3, r0);
        goto L_0x00bb;
    L_0x00ef:
        r0 = move-exception;
    L_0x00f0:
        r1 = com.tencent.av.utils.QLog.isColorLevel();
        if (r1 == 0) goto L_0x00bb;
    L_0x00f6:
        r1 = "VcCamera";
        r3 = "openFrontFacingCamera InvocationTargetException";
        com.tencent.av.utils.QLog.e(r1, r4, r3, r0);
        goto L_0x00bb;
    L_0x0100:
        r0 = move-exception;
    L_0x0101:
        r1 = com.tencent.av.utils.QLog.isColorLevel();
        if (r1 == 0) goto L_0x00bb;
    L_0x0107:
        r1 = "VcCamera";
        r3 = "openFrontFacingCamera InstantiationException";
        com.tencent.av.utils.QLog.e(r1, r4, r3, r0);
        goto L_0x00bb;
    L_0x0111:
        r0 = move-exception;
    L_0x0112:
        r1 = com.tencent.av.utils.QLog.isColorLevel();
        if (r1 == 0) goto L_0x00bb;
    L_0x0118:
        r1 = "VcCamera";
        r3 = "openFrontFacingCamera SecurityException";
        com.tencent.av.utils.QLog.e(r1, r4, r3, r0);
        goto L_0x00bb;
    L_0x0122:
        r0 = move-exception;
    L_0x0123:
        r1 = com.tencent.av.utils.QLog.isColorLevel();
        if (r1 == 0) goto L_0x00bb;
    L_0x0129:
        r1 = "VcCamera";
        r3 = "openFrontFacingCamera";
        com.tencent.av.utils.QLog.e(r1, r4, r3, r0);
        goto L_0x00bb;
    L_0x0133:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0123;
    L_0x0136:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x0123;
    L_0x013a:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0112;
    L_0x013d:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x0112;
    L_0x0141:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0101;
    L_0x0144:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x0101;
    L_0x0148:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00f0;
    L_0x014b:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x00f0;
    L_0x014f:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00df;
    L_0x0152:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x00df;
    L_0x0156:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00ce;
    L_0x015a:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x00ce;
    L_0x015f:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00bd;
    L_0x0163:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x00bd;
    L_0x0168:
        r0 = move-exception;
        r2 = r1;
        goto L_0x00ac;
    L_0x016c:
        r1 = move-exception;
        r2 = r0;
        r0 = r1;
        goto L_0x00ac;
    L_0x0171:
        r0 = r1;
        goto L_0x0090;
    L_0x0174:
        r2 = r1;
        goto L_0x00bb;
    L_0x0177:
        r5 = r2;
        goto L_0x0037;
    L_0x017a:
        r6 = r2;
        goto L_0x0029;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.av.camera.VcCamera.openFrontFacingCamera():android.hardware.Camera");
    }

    private CameraInformation getCameraDisplayOrientation(int i, Camera camera) {
        CameraInformation cameraInformation = new CameraInformation();
        try {
            Class cls = Class.forName("android.hardware.Camera");
            Class cls2 = Class.forName("android.hardware.Camera$CameraInfo");
            if (cls2 == null) {
                cameraInformation.rotation = -1;
                cameraInformation.orientation = -1;
                return cameraInformation;
            }
            Object newInstance = cls2.newInstance();
            if (newInstance == null) {
                cameraInformation.rotation = -1;
                cameraInformation.orientation = -1;
                return cameraInformation;
            }
            Field field = newInstance.getClass().getField("facing");
            Field field2 = newInstance.getClass().getField("orientation");
            if (field == null || field2 == null) {
                cameraInformation.rotation = -1;
                cameraInformation.orientation = -1;
                return cameraInformation;
            }
            Method method = cls.getMethod("getCameraInfo", new Class[]{Integer.TYPE, cls2});
            if (method == null) {
                cameraInformation.rotation = -1;
                cameraInformation.orientation = -1;
                return cameraInformation;
            }
            method.invoke(null, new Object[]{Integer.valueOf(i), newInstance});
            cameraInformation.facing = field.getInt(newInstance);
            cameraInformation.orientation = field2.getInt(newInstance);
            if (this.devDisplay == null) {
                cameraInformation.rotation = -1;
                return cameraInformation;
            }
            Method method2 = this.devDisplay.getClass().getMethod("getRotation", new Class[0]);
            if (method2 == null) {
                cameraInformation.rotation = -1;
                return cameraInformation;
            }
            switch (Integer.parseInt(method2.invoke(this.devDisplay, (Object[]) null).toString())) {
                case 0:
                    cameraInformation.rotation = 0;
                    break;
                case 1:
                    cameraInformation.rotation = 90;
                    break;
                case 2:
                    cameraInformation.rotation = 180;
                    break;
                case 3:
                    cameraInformation.rotation = im_common.WPA_QZONE;
                    break;
            }
            return cameraInformation;
        } catch (Exception e) {
            cameraInformation.rotation = 0;
            return cameraInformation;
        }
    }

    private int getSendAngleCompensation() {
        return 0;
    }

    private int getRecvAngleCompensation() {
        return 0;
    }

    private boolean setCameraDisplayOrientation(int i, Camera camera) {
        int orientation;
        CameraInformation cameraDisplayOrientation = getCameraDisplayOrientation(i, camera);
        this.CompenSateSendAngle = getSendAngleCompensation();
        this.CompenSateRecvAngle = getRecvAngleCompensation();
        Info.facing = cameraDisplayOrientation.facing;
        Info.orientation = cameraDisplayOrientation.orientation;
        if (cameraDisplayOrientation.facing == 1) {
            orientation = (360 - ((getOrientation() + getRotation()) % 360)) % 360;
        } else {
            orientation = ((getOrientation() - getRotation()) + 360) % 360;
        }
        if (cameraDisplayOrientation.facing == 1) {
            orientation = (orientation + getPreviewAngleForFrontCamera()) % 360;
        } else {
            orientation = (orientation + getPreviewAngleForBackCamera()) % 360;
        }
        setDisplayOrientation(camera, orientation);
        return true;
    }

    private Size getOptimalPreviewSize(List<Size> list, int i, int i2) {
        if (list == null) {
            return null;
        }
        Collections.sort(list, new CameraSizeComparator());
        Size size = null;
        for (Size size2 : list) {
            if (size2.width > i && size2.height > i2) {
                if (size == null) {
                    QLog.i(TAG, 0, "better size width: " + size2.width + "height: " + size2.height);
                    size = size2;
                }
                if (((long) size2.width) * ((long) i2) == ((long) size2.height) * ((long) i)) {
                    QLog.i(TAG, 0, "best size width: " + size2.width + "height: " + size2.height + "w*h1: " + (((long) size2.width) * ((long) i2)) + "w*h2: " + (((long) size2.height) * ((long) i)));
                    break;
                }
            }
        }
        Size size22 = null;
        if (size22 != null) {
            return size22;
        }
        if (size != null) {
            return size;
        }
        return null;
    }

    private Size getOptimalEqualPreviewSize(List<Size> list, int i, int i2) {
        int i3;
        int i4;
        if (!this.isPreviewSizePrint) {
            for (Size size : list) {
                QLog.d(TAG, 0, "previewsize ,w= " + size.width + ",h=" + size.height);
            }
            this.isPreviewSizePrint = true;
        }
        if (!(VcSystemInfo.isNormalSharp() || (i == Opcodes.AND_LONG_2ADDR && i2 == Opcodes.ADD_INT))) {
            i2 = Opcodes.ADD_INT;
            i = Opcodes.AND_LONG_2ADDR;
        }
        for (Size size2 : list) {
            if (size2.width == i && size2.height == i2) {
                if (!QLog.isColorLevel()) {
                    return size2;
                }
                QLog.d(TAG, 0, "previewsize ,w= " + i + ",h=" + i2);
                return size2;
            }
        }
        if (VcSystemInfo.isNormalSharp() || (i == ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE && i2 == ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK)) {
            i3 = i2;
            i4 = i;
        } else {
            for (Size size22 : list) {
                if (size22.width == ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE && size22.height == ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) {
                    if (!QLog.isColorLevel()) {
                        return size22;
                    }
                    QLog.d(TAG, 0, "previewsize 2, w= " + ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE + ",h=" + ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK);
                    return size22;
                }
            }
            i3 = ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK;
            i4 = ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE;
        }
        if (i4 == ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE && r0 == ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK) {
            for (Size size222 : list) {
                if (size222.width == ErrorCode.STATIC_TBS_INSTALL_MAKE_SYMBOLIC_LINK_ERR && size222.height == 480) {
                    if (!QLog.isColorLevel()) {
                        return size222;
                    }
                    QLog.d(TAG, 0, "previewsize ,w= " + ErrorCode.STATIC_TBS_INSTALL_MAKE_SYMBOLIC_LINK_ERR + ",h=" + 480);
                    return size222;
                }
            }
            i3 = 480;
            i4 = ErrorCode.STATIC_TBS_INSTALL_MAKE_SYMBOLIC_LINK_ERR;
        }
        return null == null ? getOptimalPreviewSize(list, i4, i3) : null;
    }

    private void setCameraFps(int i) throws RuntimeException {
        Parameters parameters;
        try {
            parameters = this.mCamera.getParameters();
        } catch (Exception e) {
            QLog.d(TAG, 0, "getParameters exception", e);
            parameters = null;
        }
        if (parameters != null) {
            nInFPS = getPreviewFPS(i, parameters);
            parameters.setPreviewFrameRate(nInFPS);
            QLog.i(TAG, 0, "setCameraFps fps = " + nInFPS);
            try {
                this.mCamera.setParameters(parameters);
            } catch (Exception e2) {
                QLog.i(TAG, 0, "setParameters exception", e2);
            }
        }
    }

    public void setCameraParaDynamic(final int i, final boolean z) {
        QLog.d(TAG, 0, "setCameraParaDynamic fps: " + i + "needRestartPreview: " + z);
        if (this.mCameraHandler != null) {
            this.mCameraHandler.post(new Runnable() {
                public void run() {
                    if (VcCamera.this.mCamera != null) {
                        if (z) {
                            try {
                                if (VcCamera.this.mPreBuffer != null) {
                                    VcCamera.this.mCamera.setPreviewCallbackWithBuffer(null);
                                } else {
                                    VcCamera.this.mCamera.setPreviewCallback(null);
                                }
                                VcCamera.this.mCamera.stopPreview();
                                VcCamera.this.setCameraFps(i);
                                if (VcCamera.this.mPreBuffer != null) {
                                    VcCamera.this.mCamera.addCallbackBuffer(VcCamera.this.mPreBuffer);
                                    VcCamera.this.mCamera.setPreviewCallbackWithBuffer(VcCamera.this.cameraCallback);
                                } else {
                                    VcCamera.this.mCamera.setPreviewCallback(VcCamera.this.cameraCallback);
                                }
                                VcCamera.this.mCamera.startPreview();
                                return;
                            } catch (Exception e) {
                                QLog.d(VcCamera.TAG, 0, "setCameraParaDynamic error", e);
                                e.printStackTrace();
                                return;
                            }
                        }
                        VcCamera.this.setCameraFps(i);
                    }
                }
            });
        }
    }

    public void setCameraParaDynamic(final int i, final int i2) {
        QLog.d(TAG, 0, "setCameraParaDynamic w: " + i + " h: " + i2);
        if (this.mCameraHandler != null) {
            this.mCameraHandler.post(new Runnable() {
                public void run() {
                    if (VcCamera.this.mCamera != null) {
                        try {
                            if (VcCamera.this.mPreBuffer != null) {
                                VcCamera.this.mCamera.setPreviewCallbackWithBuffer(null);
                            } else {
                                VcCamera.this.mCamera.setPreviewCallback(null);
                            }
                            VcCamera.this.mCamera.stopPreview();
                            VcCamera.this.setCameraPara(i, i2);
                            if (VcCamera.this.mPreBuffer != null) {
                                VcCamera.this.mCamera.addCallbackBuffer(VcCamera.this.mPreBuffer);
                                VcCamera.this.mCamera.setPreviewCallbackWithBuffer(VcCamera.this.cameraCallback);
                            } else {
                                VcCamera.this.mCamera.setPreviewCallback(VcCamera.this.cameraCallback);
                            }
                            VcCamera.this.mCamera.startPreview();
                        } catch (Exception e) {
                            QLog.d(VcCamera.TAG, 0, "setCameraParaDynamic error", e);
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    private void setCameraPara(int i, int i2) throws RuntimeException {
        Parameters parameters;
        if (this.mCamera == null) {
            QLog.d(TAG, 0, "openCamera camera == null");
        }
        synchronized (Info) {
            if (fitSdkVersion()) {
                setCameraDisplayOrientation(this.CameraId, this.mCamera);
            } else {
                adjustDirection(this.mCamera);
            }
        }
        try {
            parameters = this.mCamera.getParameters();
        } catch (Exception e) {
            QLog.d(TAG, 0, "getParameters exception", e);
            parameters = null;
        }
        if (parameters == null) {
            QLog.d(TAG, 0, "getParameters parameters == null ");
            if (i > this.VideoChatSettings.width && i2 > this.VideoChatSettings.height) {
                QLog.d(TAG, 0, "save parasw" + i + "h" + i2);
                this.VideoChatSettings.width = i;
                this.VideoChatSettings.height = i2;
                return;
            }
            return;
        }
        List list;
        int i3;
        int i4;
        Method method;
        Size optimalEqualPreviewSize;
        Size previewSize;
        int previewFormat;
        Parameters parameters2;
        try {
            list = (List) parameters.getClass().getMethod("getSupportedPreviewFormats", new Class[0]).invoke(parameters, (Object[]) null);
            if (list != null) {
                for (int i5 = 0; i5 < list.size(); i5++) {
                    if (QLog.isColorLevel()) {
                        QLog.d(TAG, 0, "format: " + list.get(i5));
                    }
                }
                if (list.contains(Integer.valueOf(17))) {
                    i3 = 17;
                } else if (list.contains(Integer.valueOf(16))) {
                    i3 = 16;
                } else if (list.contains(Integer.valueOf(20))) {
                    i3 = 20;
                } else if (list.contains(Integer.valueOf(842094169))) {
                    i3 = 842094169;
                } else if (list.contains(Integer.valueOf(4))) {
                    i3 = 4;
                } else if (list.contains(Integer.valueOf(17))) {
                    i3 = 17;
                } else if (list.contains(Integer.valueOf(16))) {
                    i3 = 16;
                } else if (list.contains(Integer.valueOf(3))) {
                    i3 = 3;
                } else if (list.contains(Integer.valueOf(2))) {
                    i3 = 2;
                } else if (list.contains(Integer.valueOf(4))) {
                    i3 = 4;
                } else if (list.contains(Integer.valueOf(100))) {
                    i3 = 100;
                } else if (list.contains(Integer.valueOf(101))) {
                    i3 = 101;
                } else if (list.contains(Integer.valueOf(102))) {
                    i3 = 102;
                } else if (list.contains(Integer.valueOf(103))) {
                    i3 = 103;
                } else if (list.contains(Integer.valueOf(104))) {
                    i3 = 104;
                }
                i4 = i3;
                method = parameters.getClass().getMethod("getSupportedPreviewSizes", new Class[0]);
                if (method != null) {
                    list = (List) method.invoke(parameters, (Object[]) null);
                    if (list != null) {
                        this.mFrameSizes = list;
                        optimalEqualPreviewSize = getOptimalEqualPreviewSize(list, i, i2);
                        if (optimalEqualPreviewSize == null) {
                            this.VideoChatSettings.width = optimalEqualPreviewSize.width;
                            this.VideoChatSettings.height = optimalEqualPreviewSize.height;
                        } else {
                            this.VideoChatSettings.width = ErrorCode.STATIC_TBS_INSTALL_MAKE_SYMBOLIC_LINK_ERR;
                            this.VideoChatSettings.height = 480;
                        }
                    }
                }
                if (nInFPS > 30 || nInFPS < 10) {
                    nInFPS = 10;
                }
                QLog.i(TAG, 0, "setCameraPara user setting of fps = " + nInFPS);
                nInFPS = getPreviewFPS(nInFPS, parameters);
                if (nInFPS > 30 || nInFPS < 10) {
                    nInFPS = 10;
                }
                QLog.i(TAG, 0, "setCameraPara fps = " + nInFPS);
                if ((this.DEV_MANUFACTURER.equalsIgnoreCase(DLConstants.BRAND_SAMSUNG) || !this.DEV_MODEL.equalsIgnoreCase("GT-I9003")) && !((this.DEV_MANUFACTURER.equalsIgnoreCase(DLConstants.BRAND_SAMSUNG) && this.DEV_MODEL.equalsIgnoreCase("GT-I9220")) || (this.DEV_MANUFACTURER.equalsIgnoreCase(DLConstants.BRAND_SAMSUNG) && this.DEV_MODEL.equalsIgnoreCase("GT-I7000")))) {
                    parameters.setPreviewSize(this.VideoChatSettings.width, this.VideoChatSettings.height);
                } else {
                    parameters.setPreviewSize(ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE, ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK);
                    this.VideoChatSettings.width = ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE;
                    this.VideoChatSettings.height = ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK;
                }
                if (i4 == 0) {
                    if (QLog.isColorLevel()) {
                        QLog.d(TAG, 0, "supportFormat = " + i4);
                    }
                    parameters.setPreviewFormat(i4);
                } else {
                    if (QLog.isColorLevel()) {
                        QLog.d(TAG, 0, "supportFormat = 17(default value)");
                    }
                    parameters.setPreviewFormat(17);
                }
                parameters.setPreviewFrameRate(nInFPS);
                parameters.set("Rotation", 180);
                i3 = 1;
                this.mCamera.setParameters(parameters);
                previewSize = parameters.getPreviewSize();
                previewFormat = parameters.getPreviewFormat();
                if (QLog.isColorLevel()) {
                    QLog.d(TAG, 0, "videoFormat = " + previewFormat);
                }
                this.VideoChatSettings.width = previewSize.width;
                this.VideoChatSettings.height = previewSize.height;
                this.VideoChatSettings.format = previewFormat;
                if (!this.DEV_MANUFACTURER.equalsIgnoreCase("meizu") && this.DEV_MODEL.equalsIgnoreCase("meizu_m9")) {
                    this.VideoChatSettings.format = 18;
                } else if (this.DEV_MANUFACTURER.equalsIgnoreCase("ZTE") && this.DEV_MODEL.equalsIgnoreCase("ZTE-T U880")) {
                    this.VideoChatSettings.format = 100;
                }
                if (i3 == 0) {
                    try {
                        parameters2 = this.mCamera.getParameters();
                        if (parameters2 != null) {
                            this.VideoChatSettings.width = parameters2.getPreviewSize().width;
                            this.VideoChatSettings.height = parameters2.getPreviewSize().height;
                            this.VideoChatSettings.format = parameters2.getPreviewFormat();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                if (17 == this.VideoChatSettings.format) {
                    i3 = ((this.VideoChatSettings.width * this.VideoChatSettings.height) * 3) / 2;
                    this.mPreBuffer = new byte[i3];
                    QLog.d(TAG, 0, "setCameraPara i420 format, add buffer, buffer size: " + i3);
                }
                QLog.d(TAG, 0, "setCameraPara width: " + this.VideoChatSettings.width + "height: " + this.VideoChatSettings.height + "format: " + this.VideoChatSettings.format);
            }
            i3 = 0;
            i4 = i3;
        } catch (Exception e22) {
            e22.printStackTrace();
            i4 = 0;
        }
        try {
            method = parameters.getClass().getMethod("getSupportedPreviewSizes", new Class[0]);
            if (method != null) {
                list = (List) method.invoke(parameters, (Object[]) null);
                if (list != null) {
                    this.mFrameSizes = list;
                    optimalEqualPreviewSize = getOptimalEqualPreviewSize(list, i, i2);
                    if (optimalEqualPreviewSize == null) {
                        this.VideoChatSettings.width = ErrorCode.STATIC_TBS_INSTALL_MAKE_SYMBOLIC_LINK_ERR;
                        this.VideoChatSettings.height = 480;
                    } else {
                        this.VideoChatSettings.width = optimalEqualPreviewSize.width;
                        this.VideoChatSettings.height = optimalEqualPreviewSize.height;
                    }
                }
            }
        } catch (Exception e222) {
            e222.printStackTrace();
        }
        nInFPS = 10;
        QLog.i(TAG, 0, "setCameraPara user setting of fps = " + nInFPS);
        nInFPS = getPreviewFPS(nInFPS, parameters);
        nInFPS = 10;
        QLog.i(TAG, 0, "setCameraPara fps = " + nInFPS);
        if (this.DEV_MANUFACTURER.equalsIgnoreCase(DLConstants.BRAND_SAMSUNG)) {
        }
        parameters.setPreviewSize(this.VideoChatSettings.width, this.VideoChatSettings.height);
        if (i4 == 0) {
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "supportFormat = 17(default value)");
            }
            parameters.setPreviewFormat(17);
        } else {
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "supportFormat = " + i4);
            }
            parameters.setPreviewFormat(i4);
        }
        parameters.setPreviewFrameRate(nInFPS);
        parameters.set("Rotation", 180);
        i3 = 1;
        try {
            this.mCamera.setParameters(parameters);
        } catch (Exception e2222) {
            e2222.printStackTrace();
            i3 = 0;
        }
        previewSize = parameters.getPreviewSize();
        previewFormat = parameters.getPreviewFormat();
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "videoFormat = " + previewFormat);
        }
        this.VideoChatSettings.width = previewSize.width;
        this.VideoChatSettings.height = previewSize.height;
        this.VideoChatSettings.format = previewFormat;
        if (!this.DEV_MANUFACTURER.equalsIgnoreCase("meizu")) {
        }
        this.VideoChatSettings.format = 100;
        if (i3 == 0) {
            parameters2 = this.mCamera.getParameters();
            if (parameters2 != null) {
                this.VideoChatSettings.width = parameters2.getPreviewSize().width;
                this.VideoChatSettings.height = parameters2.getPreviewSize().height;
                this.VideoChatSettings.format = parameters2.getPreviewFormat();
            }
        }
        if (17 == this.VideoChatSettings.format) {
            i3 = ((this.VideoChatSettings.width * this.VideoChatSettings.height) * 3) / 2;
            this.mPreBuffer = new byte[i3];
            QLog.d(TAG, 0, "setCameraPara i420 format, add buffer, buffer size: " + i3);
        }
        QLog.d(TAG, 0, "setCameraPara width: " + this.VideoChatSettings.width + "height: " + this.VideoChatSettings.height + "format: " + this.VideoChatSettings.format);
    }

    private int getPreviewFPS(int i, Parameters parameters) {
        try {
            Method method = parameters.getClass().getMethod("getSupportedPreviewFrameRates", new Class[0]);
            if (method != null) {
                List<Integer> list = (List) method.invoke(parameters, (Object[]) null);
                if (list != null) {
                    int i2;
                    if (!this.isPreviewFpsPrint) {
                        for (Integer num : list) {
                            QLog.i(TAG, 0, "supported fps = " + num);
                        }
                        this.isPreviewFpsPrint = true;
                    }
                    int i3 = 10;
                    int i4 = 0;
                    for (Integer num2 : list) {
                        if (num2.intValue() > i || num2.intValue() < i3) {
                            i2 = i4;
                            i4 = i3;
                        } else {
                            i4 = num2.intValue();
                            i2 = 1;
                        }
                        i3 = i4;
                        i4 = i2;
                    }
                    if (i4 == 0) {
                        QLog.i(TAG, 0, "not find valid fps = targetFPS");
                        i2 = i;
                        for (Integer num3 : list) {
                            int intValue;
                            if (num3.intValue() >= i) {
                                intValue = num3.intValue();
                                if (i2 != i) {
                                    if (intValue <= i2) {
                                    }
                                }
                                i2 = intValue;
                            }
                            intValue = i2;
                            i2 = intValue;
                        }
                    } else {
                        i2 = i3;
                    }
                    i = i2;
                }
            }
        } catch (Exception e) {
            QLog.e(TAG, 0, "getSupportedPreviewFrameRates error = ", e);
        }
        QLog.i(TAG, 0, "getPreviewFPS fps = " + i);
        return i;
    }

    private boolean fitSdkVersion() {
        return this.SDK_VERSION >= 10;
    }

    public int getOrientation() {
        if (Info.orientation == -1) {
            if (this.CUR_CAMERA == 0) {
                return im_common.WPA_QZONE;
            }
            if (this.CUR_CAMERA == 1) {
                return 90;
            }
        }
        return Info.orientation;
    }

    public void setRotation(int i) {
        Info.rotation = (this.CompenSateSendAngle + i) % 360;
    }

    public void setCameraParameter(final Object obj) {
        this.mCameraHandler.post(new Runnable() {
            public void run() {
                if (obj != null && VcCamera.this.mCamera != null && (obj instanceof Parameters)) {
                    try {
                        VcCamera.this.mCamera.setParameters((Parameters) obj);
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    public void setCameraPreviewChangeCallback(Object obj) {
        synchronized (this.cameraPreviewChangeCallbackLock) {
            if (obj == null) {
                mCameraPreviewChangeCallback = null;
            } else {
                mCameraPreviewChangeCallback = new WeakReference((AVVideoCtrl$CameraPreviewChangeCallback) obj);
            }
        }
    }

    public Object getCameraParameter() {
        if (!isCameraOpened || this.mCamera == null) {
            return null;
        }
        return this.mCamera.getParameters();
    }

    public int getRotation() {
        if (Info.rotation == -1) {
            return 0;
        }
        return Info.rotation;
    }

    public Object getCamera() {
        if (!isCameraOpened || this.mCamera == null) {
            return null;
        }
        return this.mCamera;
    }

    public Object getCameraHandler() {
        return this.mCameraHandler;
    }

    private boolean openFrontCamera() {
        this.mCamera = getFrontCamera();
        if (this.mCamera == null) {
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "openFrontCamera camera == null");
            }
            isCameraOpened = false;
            return false;
        }
        if (this.NUM_CAMERA == 0) {
            this.NUM_CAMERA = 2;
        }
        this.CUR_CAMERA = 0;
        isCameraOpened = true;
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "openFrontCamera success");
        }
        return true;
    }

    private boolean openBackCamera() {
        try {
            this.mCamera = Camera.open();
            this.CUR_CAMERA = 1;
            isCameraOpened = true;
            this.CameraId = 0;
            if (!QLog.isColorLevel()) {
                return true;
            }
            QLog.d(TAG, 0, "openBackCamera success");
            return true;
        } catch (Exception e) {
            isCameraOpened = false;
            if (this.mCamera != null) {
                this.mCamera.release();
            }
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, "openBackCamera exception" + e.getStackTrace());
            }
            return false;
        }
    }

    public void setPreviewDisplay(Object obj) {
        synchronized (this.holderLock) {
            mHolder = new WeakReference((SurfaceHolder) obj);
        }
    }

    public void open(final int i) {
        QLog.d(TAG, 0, "openCamera begin.");
        if (this.mCameraThread == null) {
            this.mCameraThread = new HandlerThread(CAMERA_THREAD_NAME);
            this.mCameraThread.start();
            this.mCameraHandler = new Handler(this.mCameraThread.getLooper());
        }
        this.mCameraHandler.post(new Runnable() {
            public void run() {
                boolean z;
                QLog.d(VcCamera.TAG, 0, "openCamera run.");
                if (VcCamera.isCameraOpened) {
                    z = true;
                } else if (VcCamera.this.mContext == null) {
                    QLog.d(VcCamera.TAG, 0, "openCamera context == null");
                    z = false;
                } else {
                    SurfaceHolder surfaceHolder;
                    synchronized (VcCamera.this.holderLock) {
                        surfaceHolder = VcCamera.mHolder == null ? null : (SurfaceHolder) VcCamera.mHolder.get();
                    }
                    if (surfaceHolder == null) {
                        QLog.d(VcCamera.TAG, 0, "openCamera holder == null");
                        z = false;
                    } else {
                        if (VcCamera.this.NUM_CAMERA == 0) {
                            VcCamera.this.NUM_CAMERA = VcCamera.GetNumberOfCamera();
                        }
                        if (i == 0) {
                            if (!(VcCamera.this.openFrontCamera() || VcCamera.this.openBackCamera())) {
                                QLog.d(VcCamera.TAG, 0, "openCamera failed");
                                z = false;
                            }
                        } else if (!(VcCamera.this.openBackCamera() || VcCamera.this.openFrontCamera())) {
                            QLog.d(VcCamera.TAG, 0, "openCamera failed");
                            z = false;
                        }
                        if (VcCamera.this.mCamera == null) {
                            QLog.d(VcCamera.TAG, 0, "openCamera camera == null");
                            z = false;
                        } else {
                            VcCamera.this.setCameraPara(VcCamera.this.VideoChatSettings.width, VcCamera.this.VideoChatSettings.height);
                            try {
                                if (VcCamera.this.mPreBuffer != null) {
                                    VcCamera.this.mCamera.addCallbackBuffer(VcCamera.this.mPreBuffer);
                                    VcCamera.this.mCamera.setPreviewCallbackWithBuffer(VcCamera.this.cameraCallback);
                                } else {
                                    VcCamera.this.mCamera.setPreviewCallback(VcCamera.this.cameraCallback);
                                }
                                VcCamera.this.mCamera.setPreviewDisplay(surfaceHolder);
                                VcCamera.this.mCamera.startPreview();
                                z = true;
                            } catch (Exception e) {
                                QLog.d(VcCamera.TAG, 0, "setPreviewDisplay error", e);
                                e.printStackTrace();
                                z = false;
                            }
                        }
                    }
                }
                VcCamera.isCameraOpened = z;
                AVUILoopProxy.postTaskToMainLooper(new OpenCompleteRunnable(VcCamera.this, z));
                QLog.d(VcCamera.TAG, 0, "openCamera end.");
            }
        });
    }

    public void switchCamera(int i) {
        this.mCameraHandler.post(new SwitchCameraRunnable(i));
    }

    void closeInternal() {
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "closeCamera begin.");
        }
        if (this.mCamera == null && !isCameraOpened && QLog.isColorLevel()) {
            QLog.d(TAG, 0, "Camera not open.");
        }
        if (this.mCamera != null) {
            if (isCameraOpened) {
                if (this.mPreBuffer != null) {
                    this.mCamera.setPreviewCallbackWithBuffer(null);
                } else {
                    this.mCamera.setPreviewCallback(null);
                }
                this.mCamera.stopPreview();
                this.mCamera.release();
            }
            this.mCamera = null;
        }
        isCameraOpened = false;
        this.CUR_CAMERA = -1;
        this.LAST_CAMERA = -1;
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "closeCamera end.");
        }
    }

    public void close() {
        if (this.mCameraHandler != null) {
            this.mCameraHandler.post(new Runnable() {
                public void run() {
                    VcCamera.this.closeInternal();
                    VcCamera.this.mCameraThread.quit();
                    VcCamera.this.mCameraThread = null;
                    AVUILoopProxy.postTaskToMainLooper(new CloseCompleteRunnable(VcCamera.this));
                }
            });
        }
    }

    public boolean finish() {
        if (this.mCameraHandler == null) {
            return false;
        }
        this.mCameraHandler.post(new Runnable() {
            public void run() {
                VcCamera.this.closeInternal();
                VcCamera.this.mCameraThread.quit();
                VcCamera.this.mCameraThread = null;
                AVUILoopProxy.postTaskToMainLooper(new FinishCompleteRunnable(VcCamera.this));
            }
        });
        return true;
    }

    private void afterCapture(byte[] bArr, int i, int i2, int i3, int i4, int i5) {
        synchronized (this.afterPreviewListenerLock) {
            byte[] bArr2;
            int i6;
            if (this.afterPreviewListener != null) {
                AVVideoCtrl$VideoFrame aVVideoCtrl$VideoFrame = new AVVideoCtrl$VideoFrame();
                aVVideoCtrl$VideoFrame.data = bArr;
                aVVideoCtrl$VideoFrame.dataLen = i;
                aVVideoCtrl$VideoFrame.height = i3;
                aVVideoCtrl$VideoFrame.width = i2;
                aVVideoCtrl$VideoFrame.videoFormat = i5;
                ((AVVideoCtrl$AfterPreviewListener) this.afterPreviewListener).onFrameReceive(aVVideoCtrl$VideoFrame);
                bArr2 = aVVideoCtrl$VideoFrame.data;
                i6 = aVVideoCtrl$VideoFrame.videoFormat;
            } else {
                i6 = i5;
                bArr2 = bArr;
            }
            onCaptureFrame(bArr2, i, i2, i3, i4, i6);
        }
    }

    public boolean isFrontCamera() {
        if (this.CUR_CAMERA == -1 || this.CUR_CAMERA == 0) {
            return true;
        }
        return false;
    }

    public static int getCameraNum() {
        return GetNumberOfCamera();
    }

    int getRemoteAngleForFrontCamera(int i) {
        byte b;
        switch (i) {
            case 0:
                b = (byte) 0;
                break;
            case Opcodes.IPUT_WIDE /*90*/:
                b = (byte) 1;
                break;
            case 180:
                b = (byte) 2;
                break;
            case im_common.WPA_QZONE /*270*/:
                b = (byte) 3;
                break;
            default:
                b = (byte) 0;
                break;
        }
        return ConfigSystemImpl.GetAngleForCamera(this.mContext, true, false, b) * 90;
    }

    int getRemoteAngleForBackCamera(int i) {
        byte b;
        switch (i) {
            case 0:
                b = (byte) 0;
                break;
            case Opcodes.IPUT_WIDE /*90*/:
                b = (byte) 1;
                break;
            case 180:
                b = (byte) 2;
                break;
            case im_common.WPA_QZONE /*270*/:
                b = (byte) 3;
                break;
            default:
                b = (byte) 0;
                break;
        }
        return ConfigSystemImpl.GetAngleForCamera(this.mContext, false, false, b) * 90;
    }

    int getPreviewAngleForFrontCamera() {
        return 360 - (ConfigSystemImpl.GetAngleForCamera(this.mContext, true, true, (byte) 0) * 90);
    }

    int getPreviewAngleForBackCamera() {
        return ConfigSystemImpl.GetAngleForCamera(this.mContext, false, true, (byte) 0) * 90;
    }

    public static String get(Context context, String str) throws IllegalArgumentException {
        String str2 = "";
        try {
            Class loadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            return (String) loadClass.getMethod("get", new Class[]{String.class}).invoke(loadClass, new Object[]{new String(str)});
        } catch (IllegalArgumentException e) {
            throw e;
        } catch (Exception e2) {
            return "";
        }
    }

    public synchronized void setAfterPreviewListener(Object obj) {
        synchronized (this.afterPreviewListenerLock) {
            this.afterPreviewListener = obj;
        }
    }

    public void setCameraAngleFix(boolean z, int i) {
        if (z) {
            this.mFrontCameraAngle = i % 360;
        } else {
            this.mBackCameraAngle = i % 360;
        }
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "mFrontCameraAngle" + this.mFrontCameraAngle + "mBackCameraAngle" + this.mBackCameraAngle);
        }
    }

    public int getUserCameraAngle(boolean z) {
        return z ? this.mFrontCameraAngle : this.mBackCameraAngle;
    }
}
