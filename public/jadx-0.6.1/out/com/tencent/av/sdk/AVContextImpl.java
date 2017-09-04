package com.tencent.av.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.util.Log;
import android.view.SurfaceHolder;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.av.logger.AVSDKLogger;
import com.tencent.av.mediacodec.AndroidCodec;
import com.tencent.av.opengl.GraphicRendererMgr;
import com.tencent.av.utils.PhoneStatusTools;
import com.tencent.av.utils.QLog;
import com.tencent.av.utils.SoUtil;
import com.tencent.xplatform.MainThreadHelp;

class AVContextImpl extends AVContext {
    private static final String TAG = "SdkJni";
    private static int sExtractSoError = 0;
    private static boolean sLoadLibrary = false;
    private Context mAppContext;
    private AVAudioCtrl mAudioCtrl;
    private int mNativeEntity;
    private AVVideoCtrl mVideoCtrl;
    private AVRoomMulti room;

    private native int nativeCreate(Context context);

    private native void nativeDestroy(int i);

    private native void nativeEnterRoom(int i, Context context, AVRoomMulti$EventListener aVRoomMulti$EventListener, AVRoomMulti$EnterParam aVRoomMulti$EnterParam);

    private native int nativeExitRoom(int i);

    private native AVCloudSpearEngineCtrl nativeGetCloudSpearEngineCtrl(int i);

    private native AVCustomSpearEngineCtrl nativeGetCustomSpearEngineCtrl(int i);

    private native AVRoomMulti nativeGetRoom(int i, AVRoomMulti aVRoomMulti);

    private static native String nativeGetVersion();

    private native void nativeInitNetType(int i, int i2);

    private native void nativeInternalEnterRoom(int i, Context context, AVRoomMulti$EventListener aVRoomMulti$EventListener, AVRoomMulti$EnterParam aVRoomMulti$EnterParam);

    private native void nativeSetAndroidAppPath(String str);

    private native void nativeSetLocalConfigDirectory(String str);

    private native void nativeSetRenderMgrAndHolder(int i, int i2, SurfaceHolder surfaceHolder);

    private native void nativeSetTwoSecondReportPath(String str);

    private native void nativeStart(int i, AVContext$StartParam aVContext$StartParam, AVCallback aVCallback);

    private native int nativeStop(int i);

    private native void nativeSwitchRoom(int i, int i2);

    private static native void nativeUpdateConfig(String str);

    private static boolean loadSdkLibrary() {
        if (!sLoadLibrary) {
            if (!SoUtil.getCopySoInfo()) {
                try {
                    System.loadLibrary("stlport_shared");
                    System.loadLibrary("xplatform");
                    System.loadLibrary("UDT");
                    System.loadLibrary("qavsdk");
                    sLoadLibrary = true;
                } catch (UnsatisfiedLinkError e) {
                    e.printStackTrace();
                }
            } else if (SoUtil.LoadExtractedSo("stlport_shared") && SoUtil.LoadExtractedSo("xplatform") && SoUtil.LoadExtractedSo("UDT") && SoUtil.LoadExtractedSo("qavsdk")) {
                sLoadLibrary = true;
            }
        }
        return sLoadLibrary;
    }

    public static String getVersion() {
        if (sLoadLibrary) {
            return nativeGetVersion();
        }
        return "";
    }

    public AVContextImpl() {
        this.room = null;
        this.mNativeEntity = 0;
        this.mAppContext = null;
        this.mAudioCtrl = null;
        this.mVideoCtrl = null;
        this.room = new AVRoomMulti();
    }

    public boolean create(Context context, boolean z) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            Log.e(TAG, "create context not in main thread");
            return false;
        }
        if (z) {
            SoUtil.setAppContext(context.getApplicationContext());
            SoUtil.setCopySoInfo(z);
            sExtractSoError = SoUtil.extractAVModulesFromAssets();
        }
        if (sExtractSoError != 0) {
            Log.e(TAG, "create context , sExtractSoError = " + sExtractSoError);
            return false;
        }
        if (loadSdkLibrary()) {
            this.mAppContext = context.getApplicationContext();
            initLogSetting();
            this.mNativeEntity = nativeCreate(this.mAppContext);
        }
        if (this.mNativeEntity != 0) {
            return true;
        }
        return false;
    }

    public void destroy() {
        if (this.mAudioCtrl != null) {
            this.mAudioCtrl.uninit();
            this.mAudioCtrl = null;
        }
        if (this.mVideoCtrl != null) {
            this.mVideoCtrl.unInit();
            this.mVideoCtrl = null;
        }
        nativeDestroy(this.mNativeEntity);
        this.mNativeEntity = 0;
        if (SoUtil.getCopySoInfo()) {
            SoUtil.releaseAppContext();
        }
        this.mAppContext = null;
    }

    public void start(AVContext$StartParam aVContext$StartParam, AVCallback aVCallback) {
        initDeviceInfos(this.mAppContext);
        nativeSetTwoSecondReportPath(AVSDKLogger.getLogDir());
        nativeSetLocalConfigDirectory(this.mAppContext.getFilesDir().getAbsolutePath() + "/");
        nativeStart(this.mNativeEntity, aVContext$StartParam, aVCallback);
    }

    public void start(AVContext$StartParam aVContext$StartParam, AVSDKLogSetting aVSDKLogSetting, AVCallback aVCallback) {
        if (aVSDKLogSetting != null) {
            AVLoggerClient.setLogSetting(aVSDKLogSetting);
        }
        initDeviceInfos(this.mAppContext);
        nativeSetTwoSecondReportPath(AVSDKLogger.getLogDir());
        nativeSetLocalConfigDirectory(this.mAppContext.getFilesDir().getAbsolutePath() + "/");
        nativeStart(this.mNativeEntity, aVContext$StartParam, aVCallback);
    }

    private void initLogSetting() {
        String version = getVersion();
        if (version == null) {
            Log.e(TAG, "getVersion() null");
            return;
        }
        Log.e(TAG, "avsdk version" + version);
        int lastIndexOf = version.lastIndexOf(46);
        if (-1 == lastIndexOf) {
            Log.e(TAG, "fullSDKVersion error");
        } else {
            AVLoggerClient.initLogSetting(version.substring(0, lastIndexOf));
        }
    }

    @SuppressLint({"NewApi"})
    private void initDeviceInfos(Context context) {
        String str = ((((((((((((((("PRODUCT=" + Build.PRODUCT + VoiceWakeuperAidl.PARAMS_SEPARATE) + "CPU_ABI=" + Build.CPU_ABI + VoiceWakeuperAidl.PARAMS_SEPARATE) + "TAGS=" + Build.TAGS + VoiceWakeuperAidl.PARAMS_SEPARATE) + "VERSION_CODES_BASE=1;") + "MODEL=" + Build.MODEL + VoiceWakeuperAidl.PARAMS_SEPARATE) + "SDK=" + VERSION.SDK_INT + VoiceWakeuperAidl.PARAMS_SEPARATE) + "VERSION_RELEASE=" + VERSION.RELEASE + VoiceWakeuperAidl.PARAMS_SEPARATE) + "DEVICE=" + Build.DEVICE + VoiceWakeuperAidl.PARAMS_SEPARATE) + "DISPLAY=" + Build.DISPLAY + VoiceWakeuperAidl.PARAMS_SEPARATE) + "BRAND=" + Build.BRAND + VoiceWakeuperAidl.PARAMS_SEPARATE) + "BOARD=" + Build.BOARD + VoiceWakeuperAidl.PARAMS_SEPARATE) + "FINGERPRINT=" + Build.FINGERPRINT + VoiceWakeuperAidl.PARAMS_SEPARATE) + "ID=" + Build.ID + VoiceWakeuperAidl.PARAMS_SEPARATE) + "MANUFACTURER=" + Build.MANUFACTURER + VoiceWakeuperAidl.PARAMS_SEPARATE) + "USER=" + Build.USER + VoiceWakeuperAidl.PARAMS_SEPARATE) + "PROCESSORS=" + Runtime.getRuntime().availableProcessors() + VoiceWakeuperAidl.PARAMS_SEPARATE;
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        str = str + "DATADIR=" + applicationInfo.dataDir + VoiceWakeuperAidl.PARAMS_SEPARATE;
        if (VERSION.SDK_INT >= 9) {
            str = str + "LIBDIR=" + applicationInfo.nativeLibraryDir + VoiceWakeuperAidl.PARAMS_SEPARATE;
        } else {
            str = str + "LIBDIR=" + applicationInfo.dataDir + "/lib" + VoiceWakeuperAidl.PARAMS_SEPARATE;
        }
        AndroidCodec.setDeviceInfos(context);
        int checkSupportMediaCodecFeature = AndroidCodec.checkSupportMediaCodecFeature();
        Log.i(TAG, "[Rson] check hardware feature: " + checkSupportMediaCodecFeature);
        if (checkSupportMediaCodecFeature > 0) {
            if ((checkSupportMediaCodecFeature & 1) == 1) {
                str = str + "HW_AVC_DEC=1;";
            } else {
                str = str + "HW_AVC_DEC=0;";
            }
            if ((checkSupportMediaCodecFeature & 2) == 2) {
                str = str + "HW_AVC_ENC=1;";
            } else {
                str = str + "HW_AVC_ENC=0;";
            }
            if ((checkSupportMediaCodecFeature & 4) == 4) {
                str = str + "HW_HEVC_DEC=1;";
            } else {
                str = str + "HW_HEVC_DEC=0;";
            }
            if ((checkSupportMediaCodecFeature & 8) == 8) {
                str = str + "HW_HEVC_ENC=1;";
            } else {
                str = str + "HW_HEVC_ENC=0;";
            }
        } else {
            str = (((str + "HW_AVC_ENC=0;") + "HW_AVC_DEC=0;") + "HW_HEVC_DEC=0;") + "HW_HEVC_ENC=0;";
        }
        nativeSetAndroidAppPath(str);
    }

    public int stop() {
        return nativeStop(this.mNativeEntity);
    }

    public void enterRoom(final AVRoomMulti$EventListener aVRoomMulti$EventListener, AVRoomMulti$EnterParam aVRoomMulti$EnterParam) {
        if (this.mAppContext != null) {
            nativeInitNetType(this.mNativeEntity, PhoneStatusTools.getNetWorkType(this.mAppContext));
        }
        Log.d(TAG, "enterRoom");
        if (aVRoomMulti$EnterParam == null) {
            MainThreadHelp.postRunnable(new Runnable() {
                public void run() {
                    if (aVRoomMulti$EventListener != null) {
                        aVRoomMulti$EventListener.onEnterRoomComplete(1004, "enter param is null");
                    }
                }
            });
        }
        if (aVRoomMulti$EnterParam instanceof InternalEnterParam) {
            QLog.i(TAG, 0, "InternalEnterRoom run.");
            nativeInternalEnterRoom(this.mNativeEntity, this.mAppContext, aVRoomMulti$EventListener, aVRoomMulti$EnterParam);
            return;
        }
        QLog.i(TAG, 0, "ExternalEnterRoom run.");
        nativeEnterRoom(this.mNativeEntity, this.mAppContext, aVRoomMulti$EventListener, aVRoomMulti$EnterParam);
    }

    public void switchRoom(int i) {
        nativeSwitchRoom(this.mNativeEntity, i);
    }

    public int exitRoom() {
        return nativeExitRoom(this.mNativeEntity);
    }

    public AVRoomMulti getRoom() {
        return nativeGetRoom(this.mNativeEntity, this.room);
    }

    public AVAudioCtrl getAudioCtrl() {
        if (this.mAudioCtrl == null) {
            this.mAudioCtrl = new AVAudioCtrl();
            this.mAudioCtrl.init(this.mAppContext, this.mNativeEntity);
        }
        return this.mAudioCtrl;
    }

    public AVVideoCtrl getVideoCtrl() {
        if (this.mVideoCtrl == null) {
            this.mVideoCtrl = new AVVideoCtrl();
        }
        this.mVideoCtrl.init(this.mNativeEntity);
        return this.mVideoCtrl;
    }

    public AVCloudSpearEngineCtrl getCloudSpearEngineCtrl() {
        return nativeGetCloudSpearEngineCtrl(this.mNativeEntity);
    }

    public AVCustomSpearEngineCtrl getCustomSpearEngineCtrl() {
        return nativeGetCustomSpearEngineCtrl(this.mNativeEntity);
    }

    public int setRenderMgrAndHolder(GraphicRendererMgr graphicRendererMgr, SurfaceHolder surfaceHolder) {
        if (getRoom() == null) {
            QLog.e(TAG, 0, "AV_ERR_ROOM_NOT_EXIST");
            return 1201;
        }
        nativeSetRenderMgrAndHolder(this.mNativeEntity, graphicRendererMgr.getRecvDecoderFrameFunctionptr(), surfaceHolder);
        return 0;
    }

    private static void configUpdate() {
        QLog.i(TAG, 0, "configUpdate");
        String str = "";
        int checkSupportMediaCodecFeature = AndroidCodec.checkSupportMediaCodecFeature();
        QLog.i(TAG, 0, "[Rson] check hardware feature: " + checkSupportMediaCodecFeature);
        if (checkSupportMediaCodecFeature > 0) {
            if ((checkSupportMediaCodecFeature & 1) == 1) {
                str = str + "HW_AVC_DEC=1;";
            } else {
                str = str + "HW_AVC_DEC=0;";
            }
            if ((checkSupportMediaCodecFeature & 2) == 2) {
                str = str + "HW_AVC_ENC=1;";
            } else {
                str = str + "HW_AVC_ENC=0;";
            }
            if ((checkSupportMediaCodecFeature & 4) == 4) {
                str = str + "HW_HEVC_DEC=1;";
            } else {
                str = str + "HW_HEVC_DEC=0;";
            }
            if ((checkSupportMediaCodecFeature & 8) == 8) {
                str = str + "HW_HEVC_ENC=1;";
            } else {
                str = str + "HW_HEVC_ENC=0;";
            }
        } else {
            str = (((str + "HW_AVC_ENC=0;") + "HW_AVC_DEC=0;") + "HW_HEVC_DEC=0;") + "HW_HEVC_ENC=0;";
        }
        nativeUpdateConfig(str);
    }

    public static int getSoExtractError() {
        return sExtractSoError;
    }
}
