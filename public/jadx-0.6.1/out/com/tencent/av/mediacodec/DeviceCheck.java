package com.tencent.av.mediacodec;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.Resources;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.tencent.av.mediacodec.config.CodecConfigParser;
import com.tencent.av.utils.QLog;
import java.util.List;

@SuppressLint({"NewApi"})
public class DeviceCheck implements Runnable {
    public static final String TAG = "DeviceCheck";
    int mDataSource = -1;
    Resources mRes;
    Thread mThread;

    public DeviceCheck(Resources resources) {
        this.mRes = resources;
    }

    public void setDataSource(int i) {
        this.mDataSource = i;
    }

    public void run() {
    }

    public static boolean isAVCEncWhitelistDevices() {
        return false;
    }

    public static boolean isAVCDecWhitelistDevices() {
        return false;
    }

    static boolean checkDecoderSupportColorFormat(int i) {
        switch (i) {
            case 19:
            case 21:
            case HWColorFormat.COLOR_MTK_FormatYUV420Planar /*2130706944*/:
            case HWColorFormat.COLOR_QCOM_FormatYUV420PackedSemiPlanar32m /*2141391876*/:
                return true;
            default:
                return false;
        }
    }

    static boolean checkEncoderSupportColorFormat(int i) {
        switch (i) {
            case 19:
            case 21:
                return true;
            default:
                return false;
        }
    }

    @SuppressLint({"NewApi"})
    @TargetApi(16)
    public static boolean isAVCDecSupportColorformats() {
        List decoderInfos = AndroidCodec.getDecoderInfos(AndroidCodec.AVC_CODEC_MIME);
        for (int i = 0; i < decoderInfos.size(); i++) {
            CodecCapabilities codecCapabilities = AndroidCodec.getCodecCapabilities((MediaCodecInfo) decoderInfos.get(i), AndroidCodec.AVC_CODEC_MIME);
            if (codecCapabilities == null) {
                return false;
            }
            for (int checkDecoderSupportColorFormat : codecCapabilities.colorFormats) {
                if (checkDecoderSupportColorFormat(checkDecoderSupportColorFormat)) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    @TargetApi(16)
    public static boolean isAVCEncSupportColorformats() {
        List endoderInfos = AndroidCodec.getEndoderInfos(AndroidCodec.AVC_CODEC_MIME);
        for (int i = 0; i < endoderInfos.size(); i++) {
            CodecCapabilities codecCapabilities = AndroidCodec.getCodecCapabilities((MediaCodecInfo) endoderInfos.get(i), AndroidCodec.AVC_CODEC_MIME);
            if (codecCapabilities == null) {
                return false;
            }
            for (int checkEncoderSupportColorFormat : codecCapabilities.colorFormats) {
                if (checkEncoderSupportColorFormat(checkEncoderSupportColorFormat)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean isSupportAsyncAPI() {
        if (forceSyncAPI() || VERSION.SDK_INT < 21) {
            return false;
        }
        CodecConfigParser codecConfigParser = new CodecConfigParser();
        String config = codecConfigParser.getConfig();
        if (TextUtils.isEmpty(config)) {
            return false;
        }
        codecConfigParser.setConfig(config);
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "isSupportAsyncAPI sharpConfigPayload:\n" + config);
        }
        if (!codecConfigParser.getAVCEncoderAbility() || !codecConfigParser.isEnableAsyncApi(2) || !codecConfigParser.getAVCDecoderAbility() || !codecConfigParser.isEnableAsyncApi(1)) {
            return false;
        }
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "SUPPORT Async API");
        }
        return true;
    }

    public static boolean forceSyncAPI() {
        return false;
    }
}
