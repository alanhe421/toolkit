package com.tencent.av.mediacodec;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodec.Callback;
import android.media.MediaCodec.CodecException;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecList;
import android.media.MediaFormat;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.view.Surface;
import com.iflytek.speech.VoiceWakeuperAidl;
import com.tencent.av.utils.QLog;
import com.tencent.av.utils.SoUtil;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class AndroidCodec {
    public static String AVC_CODEC_MIME = "video/avc";
    public static int DEC_CODEC = 0;
    public static int ENC_CODEC = 1;
    public static String ForceIFrame = "request-sync";
    public static final int SUPPORT_AVC_DEC = 1;
    public static final int SUPPORT_AVC_ENC = 2;
    public static final int SUPPORT_HEVC_DEC = 4;
    public static final int SUPPORT_HEVC_ENC = 8;
    public static final int SUPPORT_NONE = 0;
    public static String TAG = "AndroidCodec";
    public static int TIMEOUT_US = 33000;
    protected static boolean gfLoaded = false;
    protected ByteBuffer[] inputBuffers;
    protected int mCodecType = DEC_CODEC;
    protected MediaFormat mFormat;
    protected MediaCodec mMediaCodec;
    protected MediaFormat mOutputFormat;
    protected ByteBuffer[] outputBuffers;

    public class BufferData {
        public ByteBuffer buffer;
        public MediaFormat format;
        public int index;
        public BufferInfo info = new BufferInfo();
        public boolean success = true;
    }

    public static class InputBufferData {
        public ByteBuffer buffer;
        public int index;
        public boolean processing = false;

        public InputBufferData(ByteBuffer byteBuffer, int i) {
            this.buffer = byteBuffer;
            this.index = i;
        }
    }

    public AndroidCodec() {
        loadCodecLibrarys();
    }

    public void setSurface(Surface surface) {
    }

    public static void loadCodecLibrarys() {
        if (!gfLoaded) {
            if (!SoUtil.getCopySoInfo()) {
                try {
                    System.loadLibrary("stlport_shared");
                    System.loadLibrary("hwcodec");
                    int version = NativeCodec.getVersion();
                    if (QLog.isColorLevel() || version > 0) {
                        QLog.d(TAG, 0, "Native so version = " + version);
                    }
                    gfLoaded = true;
                } catch (UnsatisfiedLinkError e) {
                    e.printStackTrace();
                    gfLoaded = false;
                }
            } else if (SoUtil.LoadExtractedSo("stlport_shared") && SoUtil.LoadExtractedSo("hwcodec")) {
                gfLoaded = true;
            } else {
                gfLoaded = false;
            }
        }
    }

    public static void setDeviceInfos(Context context) {
        Object obj = 1;
        if (new File(context.getApplicationInfo().nativeLibraryDir + "/libhwcodec.so").exists()) {
            int i = 1;
        } else {
            Object obj2 = null;
        }
        if (!(obj2 == null && new File(context.getFilesDir().getParent() + "/txav" + "/libhwcodec.so").exists())) {
            obj = obj2;
        }
        if (obj != null && !gfLoaded) {
            loadCodecLibrarys();
            if (gfLoaded) {
                String str = (((("PRODUCT=" + Build.PRODUCT.toLowerCase() + VoiceWakeuperAidl.PARAMS_SEPARATE) + "MODEL=" + Build.MODEL.toLowerCase() + VoiceWakeuperAidl.PARAMS_SEPARATE) + "SDK=" + VERSION.SDK_INT + VoiceWakeuperAidl.PARAMS_SEPARATE) + "FINGERPRINT=" + Build.FINGERPRINT.toLowerCase() + VoiceWakeuperAidl.PARAMS_SEPARATE) + "MANUFACTURER=" + Build.MANUFACTURER.toLowerCase() + VoiceWakeuperAidl.PARAMS_SEPARATE;
                ApplicationInfo applicationInfo = context.getApplicationInfo();
                str = str + "DATADIR=" + applicationInfo.dataDir + VoiceWakeuperAidl.PARAMS_SEPARATE;
                if (VERSION.SDK_INT >= 9) {
                    str = str + "LIBDIR=" + applicationInfo.nativeLibraryDir + VoiceWakeuperAidl.PARAMS_SEPARATE;
                } else {
                    str = str + "LIBDIR=" + applicationInfo.dataDir + "/lib" + VoiceWakeuperAidl.PARAMS_SEPARATE;
                }
                NativeCodec.set_device_infos(str);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static int checkSupportMediaCodecFeature() {
        /*
        r0 = 1;
        r1 = 0;
        r2 = android.os.Build.VERSION.SDK_INT;
        r3 = 16;
        if (r2 >= r3) goto L_0x0009;
    L_0x0008:
        return r1;
    L_0x0009:
        r2 = gfLoaded;
        if (r2 == 0) goto L_0x0008;
    L_0x000d:
        r2 = com.tencent.av.mediacodec.DeviceCheck.isAVCDecWhitelistDevices();
        if (r2 == 0) goto L_0x0045;
    L_0x0013:
        r2 = com.tencent.av.utils.QLog.isColorLevel();
        if (r2 == 0) goto L_0x0021;
    L_0x0019:
        r2 = TAG;
        r3 = "checkSupportMediaCodecFeature device is in decoder white list.";
        com.tencent.av.utils.QLog.d(r2, r1, r3);
    L_0x0021:
        r2 = com.tencent.av.mediacodec.DeviceCheck.isAVCDecSupportColorformats();
        if (r2 == 0) goto L_0x0090;
    L_0x0027:
        r2 = com.tencent.av.mediacodec.DeviceCheck.isAVCEncWhitelistDevices();
        if (r2 == 0) goto L_0x0092;
    L_0x002d:
        r2 = com.tencent.av.utils.QLog.isColorLevel();
        if (r2 == 0) goto L_0x003b;
    L_0x0033:
        r2 = TAG;
        r3 = "checkSupportMediaCodecFeature device is in encoder white list.";
        com.tencent.av.utils.QLog.d(r2, r1, r3);
    L_0x003b:
        r1 = com.tencent.av.mediacodec.DeviceCheck.isAVCEncSupportColorformats();
        if (r1 == 0) goto L_0x0043;
    L_0x0041:
        r0 = r0 + 2;
    L_0x0043:
        r1 = r0;
        goto L_0x0008;
    L_0x0045:
        r2 = new com.tencent.av.mediacodec.config.CodecConfigParser;
        r2.<init>();
        r3 = r2.getConfig();
        r4 = android.text.TextUtils.isEmpty(r3);
        if (r4 != 0) goto L_0x0090;
    L_0x0054:
        r2.setConfig(r3);
        r4 = com.tencent.av.utils.QLog.isColorLevel();
        if (r4 == 0) goto L_0x0076;
    L_0x005d:
        r4 = TAG;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "checkSupportMediaCodecFeature decoder sharpConfigPayload:\n";
        r5 = r5.append(r6);
        r3 = r5.append(r3);
        r3 = r3.toString();
        com.tencent.av.utils.QLog.d(r4, r1, r3);
    L_0x0076:
        r2 = r2.getAVCDecoderAbility();
        if (r2 == 0) goto L_0x0090;
    L_0x007c:
        r2 = com.tencent.av.utils.QLog.isColorLevel();
        if (r2 == 0) goto L_0x008a;
    L_0x0082:
        r2 = TAG;
        r3 = "checkSupportMediaCodecFeature hwcodec avc decoder enabled.";
        com.tencent.av.utils.QLog.d(r2, r1, r3);
    L_0x008a:
        r2 = com.tencent.av.mediacodec.DeviceCheck.isAVCDecSupportColorformats();
        if (r2 != 0) goto L_0x0027;
    L_0x0090:
        r0 = r1;
        goto L_0x0027;
    L_0x0092:
        r2 = new com.tencent.av.mediacodec.config.CodecConfigParser;
        r2.<init>();
        r3 = r2.getConfig();
        r4 = android.text.TextUtils.isEmpty(r3);
        if (r4 != 0) goto L_0x0043;
    L_0x00a1:
        r2.setConfig(r3);
        r4 = com.tencent.av.utils.QLog.isColorLevel();
        if (r4 == 0) goto L_0x00c3;
    L_0x00aa:
        r4 = TAG;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = "checkSupportMediaCodecFeature encoder sharpConfigPayload:\n";
        r5 = r5.append(r6);
        r3 = r5.append(r3);
        r3 = r3.toString();
        com.tencent.av.utils.QLog.d(r4, r1, r3);
    L_0x00c3:
        r2 = r2.getAVCEncoderAbility();
        if (r2 == 0) goto L_0x0043;
    L_0x00c9:
        r2 = com.tencent.av.utils.QLog.isColorLevel();
        if (r2 == 0) goto L_0x00d7;
    L_0x00cf:
        r2 = TAG;
        r3 = "checkSupportMediaCodecFeature hwcodec avc encoder enabled.";
        com.tencent.av.utils.QLog.d(r2, r1, r3);
    L_0x00d7:
        r1 = com.tencent.av.mediacodec.DeviceCheck.isAVCEncSupportColorformats();
        if (r1 == 0) goto L_0x0043;
    L_0x00dd:
        r0 = r0 + 2;
        goto L_0x0043;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.av.mediacodec.AndroidCodec.checkSupportMediaCodecFeature():int");
    }

    public static List<MediaCodecInfo> getDecoderInfos(String str) {
        List<MediaCodecInfo> arrayList = new ArrayList();
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (!(codecInfoAt.isEncoder() || codecInfoAt.getName().contains(".sw.") || codecInfoAt.getName().contains(".SW.") || codecInfoAt.getName().contains("google") || codecInfoAt.getName().contains("Google") || codecInfoAt.getName().contains("GOOGLE"))) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                for (String equalsIgnoreCase : supportedTypes) {
                    if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                        arrayList.add(codecInfoAt);
                    }
                }
            }
        }
        return arrayList;
    }

    public static MediaCodecInfo getCodecInfo(String str) {
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (codecInfoAt.getName().equalsIgnoreCase(str)) {
                return codecInfoAt;
            }
        }
        return null;
    }

    public static List<MediaCodecInfo> getEndoderInfos(String str) {
        List<MediaCodecInfo> arrayList = new ArrayList();
        int codecCount = MediaCodecList.getCodecCount();
        for (int i = 0; i < codecCount; i++) {
            MediaCodecInfo codecInfoAt = MediaCodecList.getCodecInfoAt(i);
            if (!(!codecInfoAt.isEncoder() || codecInfoAt.getName().contains(".sw.") || codecInfoAt.getName().contains(".SW.") || codecInfoAt.getName().contains("google") || codecInfoAt.getName().contains("Google") || codecInfoAt.getName().contains("GOOGLE"))) {
                String[] supportedTypes = codecInfoAt.getSupportedTypes();
                for (String equalsIgnoreCase : supportedTypes) {
                    if (equalsIgnoreCase.equalsIgnoreCase(str)) {
                        arrayList.add(codecInfoAt);
                    }
                }
            }
        }
        return arrayList;
    }

    public static CodecCapabilities getCodecCapabilities(MediaCodecInfo mediaCodecInfo, String str) {
        try {
            return mediaCodecInfo.getCapabilitiesForType(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean start() {
        try {
            if (this.mMediaCodec != null) {
                this.mMediaCodec.start();
                if (VERSION.SDK_INT <= 20) {
                    synchronized (this) {
                        this.inputBuffers = this.mMediaCodec.getInputBuffers();
                        this.outputBuffers = this.mMediaCodec.getOutputBuffers();
                    }
                }
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void flush() {
        if (this.mMediaCodec != null) {
            this.mMediaCodec.flush();
        }
    }

    public void stop() {
        if (this.mMediaCodec != null) {
            this.mMediaCodec.stop();
        }
    }

    public BufferData getInputBuffer() {
        if (this.mMediaCodec == null) {
            return null;
        }
        BufferData bufferData = new BufferData();
        int dequeueInputBuffer = this.mMediaCodec.dequeueInputBuffer((long) TIMEOUT_US);
        if (dequeueInputBuffer < 0) {
            return null;
        }
        if (VERSION.SDK_INT <= 20) {
            synchronized (this) {
                bufferData.index = dequeueInputBuffer;
                bufferData.buffer = this.inputBuffers[dequeueInputBuffer];
            }
            return bufferData;
        }
        synchronized (this) {
            bufferData.index = dequeueInputBuffer;
            try {
                bufferData.buffer = this.mMediaCodec.getInputBuffer(dequeueInputBuffer);
            } catch (IllegalStateException e) {
                e.printStackTrace();
                bufferData.success = false;
            }
        }
        return bufferData;
    }

    public synchronized void queueInputBuffer(int i, int i2, long j, int i3) {
        if (this.mMediaCodec != null) {
            try {
                this.mMediaCodec.queueInputBuffer(i, 0, i2, j, i3);
            } catch (Exception e) {
                if (QLog.isColorLevel()) {
                    QLog.e(TAG, 0, "queueInputBuffer exception", e);
                }
            }
        }
    }

    public synchronized BufferData dequeueOutputBuffer() {
        BufferData bufferData;
        if (this.mMediaCodec != null) {
            bufferData = new BufferData();
            int dequeueOutputBuffer = this.mMediaCodec.dequeueOutputBuffer(bufferData.info, (long) TIMEOUT_US);
            switch (dequeueOutputBuffer) {
                case -3:
                    if (QLog.isColorLevel()) {
                        QLog.e(TAG, 0, "INFO_OUTPUT_BUFFERS_CHANGED");
                    }
                    this.outputBuffers = this.mMediaCodec.getOutputBuffers();
                    bufferData.index = -3;
                    break;
                case -2:
                    if (QLog.isColorLevel()) {
                        QLog.e(TAG, 0, "INFO_OUTPUT_FORMAT_CHANGED");
                    }
                    bufferData.index = -2;
                    this.mOutputFormat = this.mMediaCodec.getOutputFormat();
                    if (this.mOutputFormat != null) {
                        if (this.mCodecType != DEC_CODEC) {
                            if (QLog.isColorLevel()) {
                                QLog.e(TAG, 0, "EncCodec, INFO_OUTPUT_FORMAT_CHANGED");
                                break;
                            }
                        }
                        dequeueOutputBuffer = this.mOutputFormat.getInteger("color-format");
                        if (QLog.isColorLevel()) {
                            QLog.e(TAG, 0, "New color format: " + dequeueOutputBuffer + "[0x" + Integer.toHexString(dequeueOutputBuffer) + "]");
                            break;
                        }
                    }
                    break;
                case -1:
                    if (QLog.isColorLevel()) {
                        QLog.e(TAG, 0, "dequeueOutputBuffer timed out!");
                    }
                    bufferData.index = -1;
                    break;
                default:
                    if (dequeueOutputBuffer >= 0) {
                        if (VERSION.SDK_INT > 20) {
                            bufferData.index = dequeueOutputBuffer;
                            try {
                                bufferData.format = this.mMediaCodec.getOutputFormat(dequeueOutputBuffer);
                                bufferData.buffer = this.mMediaCodec.getOutputBuffer(dequeueOutputBuffer);
                                break;
                            } catch (IllegalStateException e) {
                                e.printStackTrace();
                                bufferData.success = false;
                                break;
                            }
                        }
                        bufferData.buffer = this.outputBuffers[dequeueOutputBuffer];
                        bufferData.index = dequeueOutputBuffer;
                        bufferData.format = this.mOutputFormat;
                        break;
                    }
                    bufferData.index = dequeueOutputBuffer;
                    bufferData.success = false;
                    break;
            }
        }
        bufferData = null;
        return bufferData;
    }

    public ByteBuffer getInputBuffer(int i) {
        try {
            return this.mMediaCodec.getInputBuffer(i);
        } catch (Exception e) {
            e.printStackTrace();
            if (QLog.isColorLevel()) {
                QLog.e(TAG, 0, "invoke getInputBuffer exception", e);
            }
            return null;
        }
    }

    public ByteBuffer getOutputBuffer(int i) {
        try {
            return this.mMediaCodec.getOutputBuffer(i);
        } catch (Exception e) {
            e.printStackTrace();
            if (QLog.isColorLevel()) {
                QLog.e(TAG, 0, "invoke getOutputBuffer exception", e);
            }
            return null;
        }
    }

    public MediaFormat getOutputFormat(int i) {
        try {
            return this.mMediaCodec.getOutputFormat(i);
        } catch (Exception e) {
            e.printStackTrace();
            if (QLog.isColorLevel()) {
                QLog.e(TAG, 0, "invoke getOutputFormat exception", e);
            }
            return null;
        }
    }

    public synchronized void releaseOutputBuffer(int i) {
        if (this.mMediaCodec != null) {
            this.mMediaCodec.releaseOutputBuffer(i, false);
        }
    }

    public synchronized void release() {
        this.inputBuffers = null;
        this.outputBuffers = null;
        if (this.mMediaCodec != null) {
            this.mMediaCodec.release();
            this.mMediaCodec = null;
        }
    }

    public synchronized void reset() {
    }

    public boolean init(MediaFormat mediaFormat, String str, IMediaCodecCallback iMediaCodecCallback) {
        int i;
        if (QLog.isColorLevel()) {
            QLog.e(TAG, 0, "init name: " + str);
        }
        this.mFormat = mediaFormat;
        MediaCodecInfo codecInfo = getCodecInfo(str);
        if (codecInfo == null || !codecInfo.isEncoder()) {
            i = 0;
        } else {
            this.mCodecType = ENC_CODEC;
            i = 1;
        }
        try {
            this.mMediaCodec = MediaCodec.createByCodecName(str);
            try {
                if (this.mMediaCodec != null) {
                    if (iMediaCodecCallback != null && DeviceCheck.isSupportAsyncAPI()) {
                        setCallback(iMediaCodecCallback);
                    }
                    this.mMediaCodec.configure(this.mFormat, null, null, i);
                }
                if (this.mMediaCodec != null) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                if (QLog.isColorLevel()) {
                    QLog.e(TAG, 0, "init exception", e);
                }
                return false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (QLog.isColorLevel()) {
                QLog.e(TAG, 0, "init exception", e2);
            }
            return false;
        }
    }

    public boolean init(MediaFormat mediaFormat, String str, Surface surface, IMediaCodecCallback iMediaCodecCallback) {
        if (QLog.isColorLevel()) {
            QLog.e(TAG, 0, "init name: " + str);
        }
        this.mFormat = mediaFormat;
        try {
            int i;
            this.mMediaCodec = MediaCodec.createByCodecName(str);
            MediaCodecInfo codecInfo = getCodecInfo(str);
            if (codecInfo == null || !codecInfo.isEncoder()) {
                i = 0;
            } else {
                this.mCodecType = ENC_CODEC;
                i = 1;
            }
            try {
                if (this.mMediaCodec != null) {
                    if (iMediaCodecCallback != null && DeviceCheck.isSupportAsyncAPI()) {
                        setCallback(iMediaCodecCallback);
                    }
                    this.mMediaCodec.configure(this.mFormat, surface, null, i);
                }
                if (this.mMediaCodec != null) {
                    return true;
                }
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                if (QLog.isColorLevel()) {
                    QLog.e(TAG, 0, "init exception", e);
                }
                return false;
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (QLog.isColorLevel()) {
                QLog.e(TAG, 0, "init exception", e2);
            }
            return false;
        }
    }

    public boolean init(MediaFormat mediaFormat, int i, IMediaCodecCallback iMediaCodecCallback) {
        if (QLog.isColorLevel()) {
            QLog.e(TAG, 0, "init codecType: " + i);
        }
        this.mFormat = mediaFormat;
        if (i == DEC_CODEC) {
            try {
                this.mMediaCodec = MediaCodec.createDecoderByType(this.mFormat.getString("mime"));
            } catch (Exception e) {
                e.printStackTrace();
                if (!QLog.isColorLevel()) {
                    return false;
                }
                QLog.e(TAG, 0, "init exception", e);
                return false;
            }
        }
        try {
            this.mMediaCodec = MediaCodec.createEncoderByType(this.mFormat.getString("mime"));
        } catch (Exception e2) {
            e2.printStackTrace();
            if (!QLog.isColorLevel()) {
                return false;
            }
            QLog.e(TAG, 0, "init exception", e2);
            return false;
        }
        this.mCodecType = i;
        try {
            if (this.mMediaCodec != null) {
                int i2;
                if (i == ENC_CODEC) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (iMediaCodecCallback != null && DeviceCheck.isSupportAsyncAPI()) {
                    setCallback(iMediaCodecCallback);
                }
                this.mMediaCodec.configure(this.mFormat, null, null, i2);
            }
            if (this.mMediaCodec != null) {
                return true;
            }
            return false;
        } catch (Exception e22) {
            e22.printStackTrace();
            if (!QLog.isColorLevel()) {
                return false;
            }
            QLog.e(TAG, 0, "init exception", e22);
            return false;
        }
    }

    public void setParameters(Bundle bundle) {
        if (VERSION.SDK_INT >= 19 && this.mMediaCodec != null) {
            try {
                this.mMediaCodec.setParameters(bundle);
            } catch (IllegalStateException e) {
                e.printStackTrace();
            }
        }
    }

    private void setCallback(final IMediaCodecCallback iMediaCodecCallback) {
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "setCallback");
        }
        if (VERSION.SDK_INT >= 21) {
            this.mMediaCodec.setCallback(new Callback() {
                public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
                    if (iMediaCodecCallback != null) {
                        iMediaCodecCallback.onOutputFormatChanged(mediaCodec, mediaFormat);
                    }
                }

                public void onOutputBufferAvailable(MediaCodec mediaCodec, int i, BufferInfo bufferInfo) {
                    if (iMediaCodecCallback != null) {
                        iMediaCodecCallback.onOutputBufferAvailable(mediaCodec, i, bufferInfo);
                    }
                }

                public void onInputBufferAvailable(MediaCodec mediaCodec, int i) {
                    if (iMediaCodecCallback != null) {
                        iMediaCodecCallback.onInputBufferAvailable(mediaCodec, i);
                    }
                }

                public void onError(MediaCodec mediaCodec, CodecException codecException) {
                    if (iMediaCodecCallback != null) {
                        iMediaCodecCallback.onError(mediaCodec, codecException);
                    }
                }
            });
        } else if (QLog.isColorLevel()) {
            QLog.e(TAG, 0, "setCallback api level lower 21.");
        }
    }
}
