package com.tencent.av.mediacodec;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCodec.BufferInfo;
import android.media.MediaCodecInfo;
import android.media.MediaCodecInfo.CodecCapabilities;
import android.media.MediaCodecInfo.CodecProfileLevel;
import android.media.MediaFormat;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.util.e;
import com.etrump.jni.ETConverter;
import com.tencent.av.mediacodec.AndroidCodec.BufferData;
import com.tencent.av.mediacodec.AndroidCodec.InputBufferData;
import com.tencent.av.utils.ArrayUtils;
import com.tencent.av.utils.QLog;
import com.tencent.smtt.sdk.TbsListener.ErrorCode;
import com.tencent.tinker.android.dx.instruction.Opcodes;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import qalsdk.n;

@TargetApi(16)
public class NativeCodec implements IMediaCodecCallback {
    public static String BOTTOM = "crop-bottom";
    public static String LEFT = "crop-left";
    public static String PPS = "csd-1";
    public static String RIGHT = "crop-right";
    public static String SLICEHEIGHT = "slice-height";
    public static String SPS = "csd-0";
    public static String STRIDE = "stride";
    private static final String TAG = "NativeCodec";
    public static String TOP = "crop-top";
    static AVCCaps gAVCDecoderCaps;
    static AVCCaps gAVCEncoderCaps;
    public static boolean mUseAsyncAPI = false;
    int mBitRate;
    AndroidCodec mCodec;
    AtomicBoolean mCodersExit = new AtomicBoolean(false);
    int mColorFormat;
    boolean mDebugDelay = false;
    e<Long> mDebugDelayMap = null;
    e<Long> mDebugDelayMap2 = null;
    e<Long> mDebugIndexMap = null;
    String mDebugTag = null;
    MediaFormat mFormat = null;
    int mFrameInverval = n.f;
    int mFrameRate = 20;
    int mHeight = ETConverter.ET_CONVERTER_GLYPH_CACHE_SIZE_MASK;
    long mLastEncFrameTime = 0;
    String mMime;
    private int mNativeContext = 0;
    List<InputBufferData> mPendingInputBuffers = new ArrayList();
    long mTimeStamp = 0;
    int mTryAgainLaterCount = 0;
    int mTryAgainLaterCount2 = 0;
    int mWidth = ErrorCode.ERROR_SDKENGINE_ISCOMPATIBLE;
    boolean misdecoder;
    int setBitRatePending = 0;
    boolean setIFramePending = false;

    static class AVCCaps {
        public int height = 0;
        public int profile = 3;
        public int width = 0;

        AVCCaps() {
        }
    }

    public static native int getVersion();

    public static native boolean putByteArray2ByteBuffer(ByteBuffer byteBuffer, int i, byte[] bArr, int i2, int i3, int i4, int i5);

    public static native void set_device_infos(String str);

    public native boolean attachCodec(Object obj);

    public native void detachCodec();

    public native int readOutputData(ByteBuffer byteBuffer, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10);

    public native int readOutputDataEx(ByteBuffer byteBuffer, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8);

    public native int readOutputStream(ByteBuffer byteBuffer, long j, int i, int i2, int i3);

    public native int setAVCParams(ByteBuffer byteBuffer, ByteBuffer byteBuffer2);

    public native int writeInputData(ByteBuffer byteBuffer, boolean z);

    public native int writeInputData2(ByteBuffer byteBuffer, int i, boolean z);

    @SuppressLint({"NewApi"})
    @TargetApi(16)
    public NativeCodec(String str, Map<String, Object> map, boolean z) {
        this.mMime = str;
        this.misdecoder = z;
        this.mWidth = ((Integer) map.get("width")).intValue();
        this.mHeight = ((Integer) map.get("height")).intValue();
        if (this.misdecoder) {
            try {
                int i = this.mWidth;
                int i2 = this.mHeight;
                this.mFormat = MediaFormat.createVideoFormat(str, i, i2);
                this.mFormat.setInteger("max-input-size", i * i2);
                this.mFormat.setInteger("color-format", 21);
                this.mFormat.setInteger("frame-rate", 25);
                if (str.contains(AndroidCodec.AVC_CODEC_MIME)) {
                    ByteBuffer byteBuffer = (ByteBuffer) map.get(PPS);
                    this.mFormat.setByteBuffer(SPS, (ByteBuffer) map.get(SPS));
                    this.mFormat.setByteBuffer(PPS, byteBuffer);
                }
                if (QLog.isColorLevel()) {
                    QLog.d(TAG, 0, "width =" + i + ", height =" + i2);
                }
                createDecCodec();
            } catch (Exception e) {
                e.printStackTrace();
                if (QLog.isColorLevel()) {
                    QLog.e(TAG, 0, TAG, e);
                }
                this.mCodec = null;
            }
        } else {
            try {
                this.mBitRate = ((Integer) map.get("bitrate")).intValue();
                this.mFrameRate = ((Integer) map.get("frame-rate")).intValue();
                createEncCodec();
            } catch (Exception e2) {
                e2.printStackTrace();
                if (QLog.isColorLevel()) {
                    QLog.e(TAG, 0, TAG, e2);
                }
                this.mCodec = null;
            }
        }
        this.mDebugIndexMap = new e();
        if (this.mDebugDelay) {
            this.mDebugDelayMap = new e();
            this.mDebugDelayMap2 = new e();
            this.mDebugTag = mUseAsyncAPI ? "Async" : " Sync";
            this.mDebugTag += (this.misdecoder ? "DEC" : "ENC");
        }
    }

    private void resetCodec() {
        if (this.mCodec != null) {
            try {
                synchronized (this.mPendingInputBuffers) {
                    this.mCodersExit.set(true);
                    this.mPendingInputBuffers.clear();
                    this.mDebugIndexMap.c();
                    if (this.mDebugDelay) {
                        this.mDebugDelayMap.c();
                        this.mDebugDelayMap2.c();
                    }
                    this.mCodec.stop();
                    this.mCodec.release();
                    this.mCodec = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (QLog.isColorLevel()) {
                    QLog.e(TAG, 0, "resetCodec", e);
                }
                this.mCodec = null;
            }
        }
        try {
            if (this.misdecoder) {
                createDecCodec();
            } else {
                createEncCodec();
            }
            this.mTimeStamp = 0;
            this.mLastEncFrameTime = 0;
            this.mCodec.start();
            this.mCodersExit.set(false);
        } catch (Exception e2) {
            e2.printStackTrace();
            if (QLog.isColorLevel()) {
                QLog.e(TAG, 0, "resetCodec", e2);
            }
        }
    }

    void createEncCodec() {
        if (this.mCodec == null) {
            try {
                int i;
                List endoderInfos = AndroidCodec.getEndoderInfos(this.mMime);
                this.mColorFormat = 21;
                int i2 = 0;
                while (i2 < endoderInfos.size()) {
                    CodecCapabilities codecCapabilities = AndroidCodec.getCodecCapabilities((MediaCodecInfo) endoderInfos.get(i2), AndroidCodec.AVC_CODEC_MIME);
                    if (codecCapabilities == null) {
                        i = 0;
                        break;
                    } else if (ArrayUtils.contains(codecCapabilities.colorFormats, 21)) {
                        this.mColorFormat = 21;
                        i = i2;
                        break;
                    } else if (ArrayUtils.contains(codecCapabilities.colorFormats, 19)) {
                        this.mColorFormat = 19;
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                i = 0;
                this.mCodec = new AndroidCodec();
                MediaFormat createVideoFormat = MediaFormat.createVideoFormat(this.mMime, this.mWidth, this.mHeight);
                createVideoFormat.setInteger("color-format", this.mColorFormat);
                createVideoFormat.setInteger("frame-rate", this.mFrameRate);
                createVideoFormat.setInteger("bitrate", this.mBitRate);
                int i3 = 30;
                if (this.mFrameRate * 30 > 255) {
                    i3 = 255 / this.mFrameRate;
                }
                if (QLog.isColorLevel()) {
                    QLog.d(TAG, 0, "KEY_I_FRAME_INTERVAL = " + i3 + ", mFrameRate = " + this.mFrameRate);
                }
                if (VERSION.SDK_INT < 19) {
                    createVideoFormat.setInteger("i-frame-interval", i3);
                } else {
                    createVideoFormat.setInteger("i-frame-interval", i3);
                }
                CodecCapabilities codecCapabilities2 = AndroidCodec.getCodecCapabilities((MediaCodecInfo) endoderInfos.get(i), this.mMime);
                i3 = 16;
                if (codecCapabilities2 == null) {
                    this.mCodec = null;
                    return;
                }
                for (i2 = 0; i2 < codecCapabilities2.profileLevels.length; i2++) {
                    switch (codecCapabilities2.profileLevels[i2].profile) {
                        case 1:
                            createVideoFormat.setInteger("profile", 1);
                            if (i3 < codecCapabilities2.profileLevels[i2].level) {
                                i3 = codecCapabilities2.profileLevels[i2].level;
                            }
                            createVideoFormat.setInteger("level", i3);
                            break;
                        default:
                            break;
                    }
                }
                this.mFormat = createVideoFormat;
                if (this.mFrameRate > 0) {
                    this.mFrameInverval = 1000000 / this.mFrameRate;
                } else {
                    this.mFrameInverval = 40000;
                }
                this.mCodec.init(this.mFormat, ((MediaCodecInfo) endoderInfos.get(i)).getName(), (IMediaCodecCallback) this);
            } catch (Exception e) {
                e.printStackTrace();
                if (QLog.isColorLevel()) {
                    QLog.e(TAG, 0, "createEncCodec", e);
                }
                this.mCodec = null;
            }
        }
    }

    private void createDecCodec() {
        this.mCodec = new AndroidCodec();
        if (this.misdecoder) {
            List decoderInfos = AndroidCodec.getDecoderInfos(this.mMime);
            MediaCodecInfo mediaCodecInfo = (MediaCodecInfo) decoderInfos.get(0);
            int i = 0;
            while (i < decoderInfos.size()) {
                CodecCapabilities codecCapabilities = AndroidCodec.getCodecCapabilities((MediaCodecInfo) decoderInfos.get(i), this.mMime);
                if (codecCapabilities == null) {
                    break;
                } else if (ArrayUtils.contains(codecCapabilities.colorFormats, 19)) {
                    mediaCodecInfo = (MediaCodecInfo) decoderInfos.get(i);
                    this.mFormat.setInteger("color-format", 19);
                    break;
                } else if (ArrayUtils.contains(codecCapabilities.colorFormats, 21)) {
                    mediaCodecInfo = (MediaCodecInfo) decoderInfos.get(i);
                    this.mFormat.setInteger("color-format", 21);
                    break;
                } else {
                    i++;
                }
            }
            if (!this.mCodec.init(this.mFormat, mediaCodecInfo.getName(), (IMediaCodecCallback) this)) {
                this.mCodec = null;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onInputBufferAvailable(android.media.MediaCodec r9, int r10) {
        /*
        r8 = this;
        r3 = 0;
        r0 = r8.mCodec;
        r0 = r0.getInputBuffer(r10);
        if (r0 != 0) goto L_0x002a;
    L_0x0009:
        r0 = com.tencent.av.utils.QLog.isColorLevel();
        if (r0 == 0) goto L_0x0029;
    L_0x000f:
        r0 = "NativeCodec";
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "getInputBuffer null, index: ";
        r1 = r1.append(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        com.tencent.av.utils.QLog.e(r0, r3, r1);
    L_0x0029:
        return;
    L_0x002a:
        r1 = r8.mPendingInputBuffers;	 Catch:{ all -> 0x010c }
        monitor-enter(r1);	 Catch:{ all -> 0x010c }
        r2 = r8.mCodersExit;	 Catch:{ all -> 0x0109 }
        r2 = r2.get();	 Catch:{ all -> 0x0109 }
        if (r2 == 0) goto L_0x006f;
    L_0x0035:
        r0 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ all -> 0x0109 }
        if (r0 == 0) goto L_0x0045;
    L_0x003b:
        r0 = "NativeCodec";
        r2 = 0;
        r4 = "codec exit, return onInputBufferAvailable";
        com.tencent.av.utils.QLog.e(r0, r2, r4);	 Catch:{ all -> 0x0109 }
    L_0x0045:
        monitor-exit(r1);	 Catch:{ all -> 0x0109 }
        r1 = r8.mPendingInputBuffers;
        monitor-enter(r1);
        r0 = r8.mPendingInputBuffers;	 Catch:{ Exception -> 0x005a }
        r2 = 0;
        r0 = r0.get(r2);	 Catch:{ Exception -> 0x005a }
        r0 = (com.tencent.av.mediacodec.AndroidCodec.InputBufferData) r0;	 Catch:{ Exception -> 0x005a }
        r2 = 0;
        r0.processing = r2;	 Catch:{ Exception -> 0x005a }
    L_0x0055:
        monitor-exit(r1);	 Catch:{ all -> 0x0057 }
        goto L_0x0029;
    L_0x0057:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0057 }
        throw r0;
    L_0x005a:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0057 }
        r2 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ all -> 0x0057 }
        if (r2 == 0) goto L_0x0055;
    L_0x0064:
        r2 = "NativeCodec";
        r3 = 0;
        r4 = "input buffers cleared by other thread when processing=false";
        com.tencent.av.utils.QLog.e(r2, r3, r4, r0);	 Catch:{ all -> 0x0057 }
        goto L_0x0055;
    L_0x006f:
        r2 = r8.mPendingInputBuffers;	 Catch:{ all -> 0x0109 }
        r4 = new com.tencent.av.mediacodec.AndroidCodec$InputBufferData;	 Catch:{ all -> 0x0109 }
        r4.<init>(r0, r10);	 Catch:{ all -> 0x0109 }
        r2.add(r4);	 Catch:{ all -> 0x0109 }
        r0 = r8.mPendingInputBuffers;	 Catch:{ all -> 0x0109 }
        r2 = 0;
        r0 = r0.get(r2);	 Catch:{ all -> 0x0109 }
        r0 = (com.tencent.av.mediacodec.AndroidCodec.InputBufferData) r0;	 Catch:{ all -> 0x0109 }
        r2 = 1;
        r0.processing = r2;	 Catch:{ all -> 0x0109 }
        monitor-exit(r1);	 Catch:{ all -> 0x0109 }
        r0 = r8.misdecoder;	 Catch:{ Exception -> 0x014b }
        if (r0 == 0) goto L_0x0127;
    L_0x008a:
        r0 = r8.mPendingInputBuffers;	 Catch:{ Exception -> 0x014b }
        r1 = 0;
        r0 = r0.get(r1);	 Catch:{ Exception -> 0x014b }
        r0 = (com.tencent.av.mediacodec.AndroidCodec.InputBufferData) r0;	 Catch:{ Exception -> 0x014b }
        r0 = r0.buffer;	 Catch:{ Exception -> 0x014b }
        r1 = 1;
        r0 = r8.writeInputData(r0, r1);	 Catch:{ Exception -> 0x014b }
    L_0x009a:
        r3 = r0;
    L_0x009b:
        if (r3 <= 0) goto L_0x00ec;
    L_0x009d:
        r0 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ all -> 0x010c }
        if (r0 == 0) goto L_0x00be;
    L_0x00a3:
        r0 = "NativeCodec";
        r1 = 0;
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x010c }
        r2.<init>();	 Catch:{ all -> 0x010c }
        r4 = "writeInputData, SampleSize:";
        r2 = r2.append(r4);	 Catch:{ all -> 0x010c }
        r2 = r2.append(r3);	 Catch:{ all -> 0x010c }
        r2 = r2.toString();	 Catch:{ all -> 0x010c }
        com.tencent.av.utils.QLog.d(r0, r1, r2);	 Catch:{ all -> 0x010c }
    L_0x00be:
        r0 = r8.mLastEncFrameTime;	 Catch:{ all -> 0x010c }
        r2 = r8.mFrameInverval;	 Catch:{ all -> 0x010c }
        r4 = (long) r2;	 Catch:{ all -> 0x010c }
        r0 = r0 + r4;
        r8.mLastEncFrameTime = r0;	 Catch:{ all -> 0x010c }
        r0 = r8.mDebugDelay;	 Catch:{ all -> 0x010c }
        if (r0 == 0) goto L_0x00d9;
    L_0x00ca:
        r0 = r8.mDebugDelayMap;	 Catch:{ all -> 0x010c }
        r4 = r8.mLastEncFrameTime;	 Catch:{ all -> 0x010c }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x010c }
        r1 = java.lang.Long.valueOf(r6);	 Catch:{ all -> 0x010c }
        r0.b(r4, r1);	 Catch:{ all -> 0x010c }
    L_0x00d9:
        r1 = r8.mCodec;	 Catch:{ Exception -> 0x0161 }
        r0 = r8.mPendingInputBuffers;	 Catch:{ Exception -> 0x0161 }
        r2 = 0;
        r0 = r0.get(r2);	 Catch:{ Exception -> 0x0161 }
        r0 = (com.tencent.av.mediacodec.AndroidCodec.InputBufferData) r0;	 Catch:{ Exception -> 0x0161 }
        r2 = r0.index;	 Catch:{ Exception -> 0x0161 }
        r4 = r8.mLastEncFrameTime;	 Catch:{ Exception -> 0x0161 }
        r6 = 0;
        r1.queueInputBuffer(r2, r3, r4, r6);	 Catch:{ Exception -> 0x0161 }
    L_0x00ec:
        r1 = r8.mPendingInputBuffers;
        monitor-enter(r1);
        r0 = r8.mPendingInputBuffers;	 Catch:{ Exception -> 0x0177 }
        r2 = 0;
        r0 = r0.get(r2);	 Catch:{ Exception -> 0x0177 }
        r0 = (com.tencent.av.mediacodec.AndroidCodec.InputBufferData) r0;	 Catch:{ Exception -> 0x0177 }
        r2 = 0;
        r0.processing = r2;	 Catch:{ Exception -> 0x0177 }
        if (r3 <= 0) goto L_0x0103;
    L_0x00fd:
        r0 = r8.mPendingInputBuffers;	 Catch:{ Exception -> 0x0177 }
        r2 = 0;
        r0.remove(r2);	 Catch:{ Exception -> 0x0177 }
    L_0x0103:
        monitor-exit(r1);	 Catch:{ all -> 0x0106 }
        goto L_0x0029;
    L_0x0106:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0106 }
        throw r0;
    L_0x0109:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0109 }
        throw r0;	 Catch:{ all -> 0x010c }
    L_0x010c:
        r0 = move-exception;
        r1 = r0;
        r2 = r8.mPendingInputBuffers;
        monitor-enter(r2);
        r0 = r8.mPendingInputBuffers;	 Catch:{ Exception -> 0x018d }
        r4 = 0;
        r0 = r0.get(r4);	 Catch:{ Exception -> 0x018d }
        r0 = (com.tencent.av.mediacodec.AndroidCodec.InputBufferData) r0;	 Catch:{ Exception -> 0x018d }
        r4 = 0;
        r0.processing = r4;	 Catch:{ Exception -> 0x018d }
        if (r3 <= 0) goto L_0x0125;
    L_0x011f:
        r0 = r8.mPendingInputBuffers;	 Catch:{ Exception -> 0x018d }
        r3 = 0;
        r0.remove(r3);	 Catch:{ Exception -> 0x018d }
    L_0x0125:
        monitor-exit(r2);	 Catch:{ all -> 0x01a2 }
        throw r1;
    L_0x0127:
        r0 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ Exception -> 0x014b }
        if (r0 == 0) goto L_0x0137;
    L_0x012d:
        r0 = "NativeCodec";
        r1 = 0;
        r2 = "call writeInputData2 in callback";
        com.tencent.av.utils.QLog.d(r0, r1, r2);	 Catch:{ Exception -> 0x014b }
    L_0x0137:
        r0 = r8.mPendingInputBuffers;	 Catch:{ Exception -> 0x014b }
        r1 = 0;
        r0 = r0.get(r1);	 Catch:{ Exception -> 0x014b }
        r0 = (com.tencent.av.mediacodec.AndroidCodec.InputBufferData) r0;	 Catch:{ Exception -> 0x014b }
        r0 = r0.buffer;	 Catch:{ Exception -> 0x014b }
        r1 = r8.mColorFormat;	 Catch:{ Exception -> 0x014b }
        r2 = 1;
        r0 = r8.writeInputData2(r0, r1, r2);	 Catch:{ Exception -> 0x014b }
        goto L_0x009a;
    L_0x014b:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x010c }
        r1 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ all -> 0x010c }
        if (r1 == 0) goto L_0x009b;
    L_0x0155:
        r1 = "NativeCodec";
        r2 = 0;
        r4 = "input buffers cleared by other thread when writeInputData";
        com.tencent.av.utils.QLog.e(r1, r2, r4, r0);	 Catch:{ all -> 0x010c }
        goto L_0x009b;
    L_0x0161:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x010c }
        r1 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ all -> 0x010c }
        if (r1 == 0) goto L_0x00ec;
    L_0x016b:
        r1 = "NativeCodec";
        r2 = 0;
        r4 = "input buffers cleared by other thread when queueInputBuffer";
        com.tencent.av.utils.QLog.e(r1, r2, r4, r0);	 Catch:{ all -> 0x010c }
        goto L_0x00ec;
    L_0x0177:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x0106 }
        r2 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ all -> 0x0106 }
        if (r2 == 0) goto L_0x0103;
    L_0x0181:
        r2 = "NativeCodec";
        r3 = 0;
        r4 = "input buffers cleared by other thread when processing=false";
        com.tencent.av.utils.QLog.e(r2, r3, r4, r0);	 Catch:{ all -> 0x0106 }
        goto L_0x0103;
    L_0x018d:
        r0 = move-exception;
        r0.printStackTrace();	 Catch:{ all -> 0x01a2 }
        r3 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ all -> 0x01a2 }
        if (r3 == 0) goto L_0x0125;
    L_0x0197:
        r3 = "NativeCodec";
        r4 = 0;
        r5 = "input buffers cleared by other thread when processing=false";
        com.tencent.av.utils.QLog.e(r3, r4, r5, r0);	 Catch:{ all -> 0x01a2 }
        goto L_0x0125;
    L_0x01a2:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x01a2 }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.av.mediacodec.NativeCodec.onInputBufferAvailable(android.media.MediaCodec, int):void");
    }

    public void onOutputBufferAvailable(MediaCodec mediaCodec, int i, BufferInfo bufferInfo) {
        if (!this.mCodersExit.get()) {
            ByteBuffer outputBuffer = this.mCodec.getOutputBuffer(i);
            if (outputBuffer != null && bufferInfo != null) {
                if (this.misdecoder) {
                    MediaFormat outputFormat = this.mCodec.getOutputFormat(i);
                    if (outputFormat != null) {
                        calcDelay(false, bufferInfo);
                        setFrame(outputBuffer, bufferInfo.size, outputFormat);
                    } else if (QLog.isColorLevel()) {
                        QLog.e(TAG, 0, "getOutputFormat null");
                    }
                } else if (bufferInfo.flags == 1) {
                    calcDelay(false, bufferInfo);
                    readOutputStream(outputBuffer, bufferInfo.presentationTimeUs, bufferInfo.offset, bufferInfo.size, bufferInfo.flags);
                } else {
                    calcDelay(false, bufferInfo);
                    readOutputStream(outputBuffer, bufferInfo.presentationTimeUs, bufferInfo.offset, bufferInfo.size, bufferInfo.flags);
                }
                this.mCodec.releaseOutputBuffer(i);
            }
        } else if (QLog.isColorLevel()) {
            QLog.e(TAG, 0, "codec exit, return onOutputBufferAvailable");
        }
    }

    Long calcDelay(boolean z, BufferInfo bufferInfo) {
        Long l = (Long) this.mDebugIndexMap.a(bufferInfo.presentationTimeUs);
        if (this.mDebugDelay) {
            Long l2 = (Long) this.mDebugDelayMap.a(bufferInfo.presentationTimeUs);
            if (l2 != null) {
                if (QLog.isColorLevel()) {
                    QLog.d(TAG, 0, this.mDebugTag + "small, " + bufferInfo.presentationTimeUs + ", takes:" + (System.currentTimeMillis() - l2.longValue()));
                }
                this.mDebugDelayMap.c(bufferInfo.presentationTimeUs);
            }
            if (l != null) {
                l2 = (Long) this.mDebugDelayMap2.a(l.longValue());
                if (l2 != null) {
                    if (QLog.isColorLevel()) {
                        QLog.d(TAG, 0, this.mDebugTag + "big, " + bufferInfo.presentationTimeUs + ", takes:" + (System.currentTimeMillis() - l2.longValue()));
                    }
                    this.mDebugDelayMap2.c(l.longValue());
                }
            }
        }
        this.mDebugIndexMap.c(bufferInfo.presentationTimeUs);
        return l;
    }

    public void onError(MediaCodec mediaCodec, Exception exception) {
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "onError", exception);
        }
        if (this.mCodersExit.get() && QLog.isColorLevel()) {
            QLog.e(TAG, 0, "codec exit, return onError");
        }
    }

    public void onOutputFormatChanged(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "onOutputFormatChanged");
        }
        if (this.mCodersExit.get() && QLog.isColorLevel()) {
            QLog.e(TAG, 0, "codec exit, return onOutputFormatChanged");
        }
    }

    private static void initAVCEncoderCaps() {
        CodecCapabilities codecCapabilities = AndroidCodec.getCodecCapabilities((MediaCodecInfo) AndroidCodec.getDecoderInfos(AndroidCodec.AVC_CODEC_MIME).get(0), AndroidCodec.AVC_CODEC_MIME);
        gAVCEncoderCaps = new AVCCaps();
        if (codecCapabilities != null) {
            for (int i = 0; i < codecCapabilities.profileLevels.length; i++) {
                switch (codecCapabilities.profileLevels[i].profile) {
                    case 1:
                        gAVCEncoderCaps.profile = 3;
                        setLevel(codecCapabilities.profileLevels[i], gAVCEncoderCaps);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static void setLevel(CodecProfileLevel codecProfileLevel, AVCCaps aVCCaps) {
        switch (codecProfileLevel.level) {
            case 1:
                if (aVCCaps.width < Opcodes.ADD_INT_2ADDR || aVCCaps.height < Opcodes.ADD_INT) {
                    aVCCaps.width = Opcodes.ADD_INT_2ADDR;
                    aVCCaps.height = Opcodes.ADD_INT;
                    return;
                }
                return;
            case 2:
                if (aVCCaps.width < 352 || aVCCaps.height < 288) {
                    aVCCaps.width = 352;
                    aVCCaps.height = 288;
                    return;
                }
                return;
            case 4:
                if (aVCCaps.width < 352 || aVCCaps.height < 288) {
                    aVCCaps.width = 352;
                    aVCCaps.height = 288;
                    return;
                }
                return;
            case 8:
                if (aVCCaps.width < 352 || aVCCaps.height < 288) {
                    aVCCaps.width = 352;
                    aVCCaps.height = 288;
                    return;
                }
                return;
            case 16:
                if (aVCCaps.width < 352 || aVCCaps.height < 288) {
                    aVCCaps.width = 352;
                    aVCCaps.height = 288;
                    return;
                }
                return;
            case 32:
                if (aVCCaps.width < 352 || aVCCaps.height < 288) {
                    aVCCaps.width = 352;
                    aVCCaps.height = 288;
                    return;
                }
                return;
            case 64:
                if (aVCCaps.width < 352 || aVCCaps.height < 576) {
                    aVCCaps.width = 352;
                    aVCCaps.height = 576;
                    return;
                }
                return;
            case 128:
                if (aVCCaps.width < 720 || aVCCaps.height < 576) {
                    aVCCaps.width = 720;
                    aVCCaps.height = 576;
                    return;
                }
                return;
            case 256:
                if (aVCCaps.width < 720 || aVCCaps.height < 576) {
                    aVCCaps.width = 720;
                    aVCCaps.height = 576;
                    return;
                }
                return;
            case 512:
                if (aVCCaps.width < 1280 || aVCCaps.height < 720) {
                    aVCCaps.width = 1280;
                    aVCCaps.height = 720;
                    return;
                }
                return;
            case 1024:
                if (aVCCaps.width < 1280 || aVCCaps.height < 1024) {
                    aVCCaps.width = 1280;
                    aVCCaps.height = 1024;
                    return;
                }
                return;
            case 2048:
                if (aVCCaps.width < 2048 || aVCCaps.height < 1024) {
                    aVCCaps.width = 2048;
                    aVCCaps.height = 1024;
                    return;
                }
                return;
            case 4096:
                if (aVCCaps.width < 2048 || aVCCaps.height < 1024) {
                    aVCCaps.width = 2048;
                    aVCCaps.height = 1024;
                    return;
                }
                return;
            case 8192:
                if (aVCCaps.width < 2048 || aVCCaps.height < 1088) {
                    aVCCaps.width = 2048;
                    aVCCaps.height = 1088;
                    return;
                }
                return;
            case 16384:
                if (aVCCaps.width < 3680 || aVCCaps.height < 1536) {
                    aVCCaps.width = 3680;
                    aVCCaps.height = 1536;
                    return;
                }
                return;
            case 32768:
                if (aVCCaps.width < 4096 || aVCCaps.height < 2304) {
                    aVCCaps.width = 4096;
                    aVCCaps.height = 2304;
                    return;
                }
                return;
            default:
                return;
        }
    }

    private static void initAVCDecoderCaps() {
        CodecCapabilities codecCapabilities = AndroidCodec.getCodecCapabilities((MediaCodecInfo) AndroidCodec.getDecoderInfos(AndroidCodec.AVC_CODEC_MIME).get(0), AndroidCodec.AVC_CODEC_MIME);
        gAVCDecoderCaps = new AVCCaps();
        if (codecCapabilities != null) {
            for (int i = 0; i < codecCapabilities.profileLevels.length; i++) {
                switch (codecCapabilities.profileLevels[i].profile) {
                    case 1:
                        if (gAVCDecoderCaps.profile <= 3) {
                            gAVCDecoderCaps.profile = 3;
                            break;
                        }
                        break;
                    case 2:
                        if (gAVCDecoderCaps.profile <= 4) {
                            gAVCDecoderCaps.profile = 4;
                            break;
                        }
                        break;
                    case 4:
                        if (gAVCDecoderCaps.profile <= 5) {
                            gAVCDecoderCaps.profile = 5;
                            break;
                        }
                        break;
                    case 8:
                        if (gAVCDecoderCaps.profile <= 5) {
                            gAVCDecoderCaps.profile = 5;
                            break;
                        }
                        break;
                    case 16:
                        if (gAVCDecoderCaps.profile <= 5) {
                            gAVCDecoderCaps.profile = 5;
                            break;
                        }
                        break;
                    case 32:
                        if (gAVCDecoderCaps.profile <= 5) {
                            gAVCDecoderCaps.profile = 5;
                            break;
                        }
                        break;
                    case 64:
                        if (gAVCDecoderCaps.profile <= 5) {
                            gAVCDecoderCaps.profile = 5;
                            break;
                        }
                        break;
                }
                switch (codecCapabilities.profileLevels[i].level) {
                    case 1:
                        if (gAVCDecoderCaps.width >= Opcodes.ADD_INT_2ADDR && gAVCDecoderCaps.height >= Opcodes.ADD_INT) {
                            break;
                        }
                        gAVCDecoderCaps.width = Opcodes.ADD_INT_2ADDR;
                        gAVCDecoderCaps.height = Opcodes.ADD_INT;
                        break;
                        break;
                    case 2:
                        if (gAVCDecoderCaps.width >= 352 && gAVCDecoderCaps.height >= 288) {
                            break;
                        }
                        gAVCDecoderCaps.width = 352;
                        gAVCDecoderCaps.height = 288;
                        break;
                        break;
                    case 4:
                        if (gAVCDecoderCaps.width >= 352 && gAVCDecoderCaps.height >= 288) {
                            break;
                        }
                        gAVCDecoderCaps.width = 352;
                        gAVCDecoderCaps.height = 288;
                        break;
                        break;
                    case 8:
                        if (gAVCDecoderCaps.width >= 352 && gAVCDecoderCaps.height >= 288) {
                            break;
                        }
                        gAVCDecoderCaps.width = 352;
                        gAVCDecoderCaps.height = 288;
                        break;
                        break;
                    case 16:
                        if (gAVCDecoderCaps.width >= 352 && gAVCDecoderCaps.height >= 288) {
                            break;
                        }
                        gAVCDecoderCaps.width = 352;
                        gAVCDecoderCaps.height = 288;
                        break;
                    case 32:
                        if (gAVCDecoderCaps.width >= 352 && gAVCDecoderCaps.height >= 288) {
                            break;
                        }
                        gAVCDecoderCaps.width = 352;
                        gAVCDecoderCaps.height = 288;
                        break;
                        break;
                    case 64:
                        if (gAVCDecoderCaps.width >= 352 && gAVCDecoderCaps.height >= 576) {
                            break;
                        }
                        gAVCDecoderCaps.width = 352;
                        gAVCDecoderCaps.height = 576;
                        break;
                        break;
                    case 128:
                        if (gAVCDecoderCaps.width >= 720 && gAVCDecoderCaps.height >= 576) {
                            break;
                        }
                        gAVCDecoderCaps.width = 720;
                        gAVCDecoderCaps.height = 576;
                        break;
                        break;
                    case 256:
                        if (gAVCDecoderCaps.width >= 720 && gAVCDecoderCaps.height >= 576) {
                            break;
                        }
                        gAVCDecoderCaps.width = 720;
                        gAVCDecoderCaps.height = 576;
                        break;
                        break;
                    case 512:
                        if (gAVCDecoderCaps.width >= 1280 && gAVCDecoderCaps.height >= 720) {
                            break;
                        }
                        gAVCDecoderCaps.width = 1280;
                        gAVCDecoderCaps.height = 720;
                        break;
                        break;
                    case 1024:
                        if (gAVCDecoderCaps.width >= 1280 && gAVCDecoderCaps.height >= 1024) {
                            break;
                        }
                        gAVCDecoderCaps.width = 1280;
                        gAVCDecoderCaps.height = 1024;
                        break;
                    case 2048:
                        if (gAVCDecoderCaps.width >= 2048 && gAVCDecoderCaps.height >= 1024) {
                            break;
                        }
                        gAVCDecoderCaps.width = 2048;
                        gAVCDecoderCaps.height = 1024;
                        break;
                        break;
                    case 4096:
                        if (gAVCDecoderCaps.width >= 2048 && gAVCDecoderCaps.height >= 1024) {
                            break;
                        }
                        gAVCDecoderCaps.width = 2048;
                        gAVCDecoderCaps.height = 1024;
                        break;
                        break;
                    case 8192:
                        if (gAVCDecoderCaps.width >= 2048 && gAVCDecoderCaps.height >= 1088) {
                            break;
                        }
                        gAVCDecoderCaps.width = 2048;
                        gAVCDecoderCaps.height = 1088;
                        break;
                        break;
                    case 16384:
                        if (gAVCDecoderCaps.width >= 3680 && gAVCDecoderCaps.height >= 1536) {
                            break;
                        }
                        gAVCDecoderCaps.width = 3680;
                        gAVCDecoderCaps.height = 1536;
                        break;
                        break;
                    case 32768:
                        if (gAVCDecoderCaps.width >= 4096 && gAVCDecoderCaps.height >= 2304) {
                            break;
                        }
                        gAVCDecoderCaps.width = 4096;
                        gAVCDecoderCaps.height = 2304;
                        break;
                        break;
                    default:
                        break;
                }
            }
        }
    }

    private static int getIntValues(String str, String str2, boolean z) {
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "getIntValues mime: " + str + ", key: " + str2);
        }
        if (VERSION.SDK_INT < 16) {
            return 0;
        }
        if ("supportAsync".equalsIgnoreCase(str2)) {
            mUseAsyncAPI = DeviceCheck.isSupportAsyncAPI();
            if (mUseAsyncAPI) {
                return 1;
            }
            return 0;
        } else if (str.equalsIgnoreCase(AndroidCodec.AVC_CODEC_MIME) && z) {
            if (gAVCDecoderCaps == null) {
                initAVCDecoderCaps();
            }
            if (str2.equalsIgnoreCase("width")) {
                return gAVCDecoderCaps.width;
            }
            if (str2.equalsIgnoreCase("height")) {
                return gAVCDecoderCaps.height;
            }
            if (str2.equalsIgnoreCase("profile")) {
                return gAVCDecoderCaps.profile;
            }
            return 0;
        } else {
            if (gAVCEncoderCaps == null) {
                initAVCEncoderCaps();
            }
            if (str2.equalsIgnoreCase("width")) {
                return gAVCEncoderCaps.width;
            }
            if (str2.equalsIgnoreCase("height")) {
                return gAVCEncoderCaps.height;
            }
            if (str2.equalsIgnoreCase("profile")) {
                return gAVCEncoderCaps.profile;
            }
            return 0;
        }
    }

    private static boolean onAttach(String str, int i, boolean z, Map<String, Object> map) {
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "onAttach");
        }
        if (VERSION.SDK_INT < 16) {
            return false;
        }
        NativeCodec nativeCodec = new NativeCodec(str, map, z);
        if (nativeCodec.mCodec == null) {
            return false;
        }
        nativeCodec.mNativeContext = i;
        nativeCodec.attachCodec(nativeCodec);
        nativeCodec.mTimeStamp = 0;
        nativeCodec.mTryAgainLaterCount = 0;
        nativeCodec.mLastEncFrameTime = 0;
        return nativeCodec.mCodec.start();
    }

    private static void onDetach(Object obj) {
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "onDetach");
        }
        if (VERSION.SDK_INT >= 16) {
            NativeCodec nativeCodec = (NativeCodec) obj;
            if (nativeCodec != null) {
                nativeCodec.detachCodec();
                nativeCodec.mTryAgainLaterCount = 0;
                nativeCodec.mTimeStamp = 0;
                nativeCodec.mFormat = null;
                nativeCodec.mLastEncFrameTime = 0;
                synchronized (nativeCodec.mPendingInputBuffers) {
                    nativeCodec.mCodersExit.set(true);
                    nativeCodec.mPendingInputBuffers.clear();
                    nativeCodec.mDebugIndexMap.c();
                    if (nativeCodec.mDebugDelay) {
                        nativeCodec.mDebugDelayMap.c();
                        nativeCodec.mDebugDelayMap2.c();
                    }
                    if (nativeCodec.mCodec != null) {
                        try {
                            nativeCodec.mCodec.stop();
                            nativeCodec.mCodec.release();
                        } catch (Exception e) {
                        }
                        nativeCodec.mCodec = null;
                    }
                }
            }
        }
    }

    private boolean setFrame(ByteBuffer byteBuffer, int i, MediaFormat mediaFormat) {
        int integer = mediaFormat.getInteger("width");
        int integer2 = mediaFormat.getInteger("height");
        int integer3 = mediaFormat.getInteger(LEFT);
        int integer4 = mediaFormat.getInteger(RIGHT);
        int integer5 = mediaFormat.getInteger(TOP);
        int integer6 = mediaFormat.getInteger(BOTTOM);
        int integer7 = mediaFormat.getInteger(STRIDE);
        int integer8 = mediaFormat.getInteger(SLICEHEIGHT);
        int integer9 = mediaFormat.getInteger("color-format");
        if (integer8 < integer2) {
            integer8 = integer2;
        }
        if (integer7 < integer) {
            integer7 = integer;
        }
        if (integer9 == HWColorFormat.COLOR_SEC_TI_FormatYUV420PackedSemiPlanar) {
            integer8 -= integer5 / 2;
            integer5 = 0;
            integer3 = 0;
        }
        if (integer7 < integer) {
            integer7 = integer;
        }
        if (!(HWColorFormat.COLOR_QCOM_FormatYUV420PackedSemiPlanar32m == integer9 || HWColorFormat.COLOR_SEC_FormatNV12TPhysicalAddresses == integer9 || HWColorFormat.COLOR_MTK_FormatYUV420Planar == integer9) || integer2 >= r1) {
            integer8 = integer2;
        }
        if (((integer3 + integer4) + integer5) + integer6 == 0) {
            integer3 = 0;
            integer2--;
            integer5 = 0;
            integer4 = integer7;
            integer7 = integer8;
            integer8 = integer - 1;
        } else {
            integer = (integer4 - integer3) + 1;
            integer2 = (integer6 - integer5) + 1;
            if (integer7 < integer) {
                integer7 = integer;
            }
            if (integer8 >= integer2) {
                integer2 = integer8;
            }
            integer8 = integer4;
            integer4 = integer7;
            integer7 = integer2;
            integer2 = integer6;
        }
        if (integer4 <= 0 || integer7 <= 0 || integer8 <= 0 || integer2 <= 0) {
            if (QLog.isColorLevel()) {
                QLog.e(TAG, 0, "error decoderInfomations.");
            }
            return false;
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - SystemClock.elapsedRealtime();
        return readOutputDataEx(byteBuffer, i, integer4, integer7, integer3, integer5, (integer8 - integer3) + 1, (integer2 - integer5) + 1, integer9) > 0;
    }

    private void setParameters(String str, int i) {
        if (this.mCodec != null && str != null) {
            if (str.equalsIgnoreCase("frame-rate")) {
                if (i > 0) {
                    this.mFrameRate = i;
                    this.mFrameInverval = 1000000 / this.mFrameRate;
                    resetCodec();
                    if (QLog.isColorLevel()) {
                        QLog.d(TAG, 0, "HWENC setParameters mFrameRate = " + this.mFrameRate);
                    }
                } else {
                    return;
                }
            }
            if (VERSION.SDK_INT >= 19) {
                Bundle bundle = new Bundle();
                bundle.putInt(str, i);
                this.mCodec.setParameters(bundle);
            }
        }
    }

    private boolean onCalcDelay(int i) {
        if (this.mDebugDelay) {
            if (QLog.isColorLevel()) {
                QLog.d(TAG, 0, this.mDebugTag + " frameIndex:" + i);
            }
            this.mDebugDelayMap2.b((long) i, Long.valueOf(System.currentTimeMillis()));
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private int onDoCodecAsync(int r11, int r12) {
        /*
        r10 = this;
        r2 = 2;
        r1 = -1;
        r4 = 0;
        r0 = mUseAsyncAPI;
        if (r0 != 0) goto L_0x0018;
    L_0x0007:
        r0 = com.tencent.av.utils.QLog.isColorLevel();
        if (r0 == 0) goto L_0x0016;
    L_0x000d:
        r0 = "NativeCodec";
        r2 = "NOT in async mode.";
        com.tencent.av.utils.QLog.e(r0, r4, r2);
    L_0x0016:
        r0 = r1;
    L_0x0017:
        return r0;
    L_0x0018:
        r7 = r10.mPendingInputBuffers;
        monitor-enter(r7);
        r0 = r10.mCodersExit;	 Catch:{ all -> 0x00ea }
        r0 = r0.get();	 Catch:{ all -> 0x00ea }
        if (r0 == 0) goto L_0x0036;
    L_0x0023:
        r0 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ all -> 0x00ea }
        if (r0 == 0) goto L_0x0033;
    L_0x0029:
        r0 = "NativeCodec";
        r1 = 0;
        r3 = "hardware coders exit, return.";
        com.tencent.av.utils.QLog.e(r0, r1, r3);	 Catch:{ all -> 0x00ea }
    L_0x0033:
        monitor-exit(r7);	 Catch:{ all -> 0x00ea }
        r0 = r2;
        goto L_0x0017;
    L_0x0036:
        r0 = r10.mPendingInputBuffers;	 Catch:{ all -> 0x00ea }
        r0 = r0.size();	 Catch:{ all -> 0x00ea }
        if (r0 <= 0) goto L_0x0113;
    L_0x003e:
        r0 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ all -> 0x00ea }
        if (r0 == 0) goto L_0x0065;
    L_0x0044:
        r0 = "NativeCodec";
        r3 = 0;
        r5 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00ea }
        r5.<init>();	 Catch:{ all -> 0x00ea }
        r6 = "InputData pendingInputBuffers exist, size: ";
        r5 = r5.append(r6);	 Catch:{ all -> 0x00ea }
        r6 = r10.mPendingInputBuffers;	 Catch:{ all -> 0x00ea }
        r6 = r6.size();	 Catch:{ all -> 0x00ea }
        r5 = r5.append(r6);	 Catch:{ all -> 0x00ea }
        r5 = r5.toString();	 Catch:{ all -> 0x00ea }
        com.tencent.av.utils.QLog.d(r0, r3, r5);	 Catch:{ all -> 0x00ea }
    L_0x0065:
        r0 = r10.mPendingInputBuffers;	 Catch:{ all -> 0x00ea }
        r3 = 0;
        r0 = r0.get(r3);	 Catch:{ all -> 0x00ea }
        r0 = (com.tencent.av.mediacodec.AndroidCodec.InputBufferData) r0;	 Catch:{ all -> 0x00ea }
        if (r0 != 0) goto L_0x0073;
    L_0x0070:
        monitor-exit(r7);	 Catch:{ all -> 0x00ea }
        r0 = r2;
        goto L_0x0017;
    L_0x0073:
        r3 = r0.buffer;	 Catch:{ all -> 0x00ea }
        if (r3 != 0) goto L_0x008a;
    L_0x0077:
        r0 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ all -> 0x00ea }
        if (r0 == 0) goto L_0x0087;
    L_0x007d:
        r0 = "NativeCodec";
        r2 = 0;
        r3 = "inputbuffer null, return";
        com.tencent.av.utils.QLog.e(r0, r2, r3);	 Catch:{ all -> 0x00ea }
    L_0x0087:
        monitor-exit(r7);	 Catch:{ all -> 0x00ea }
        r0 = r1;
        goto L_0x0017;
    L_0x008a:
        r3 = r0.processing;	 Catch:{ all -> 0x00ea }
        if (r3 == 0) goto L_0x00a2;
    L_0x008e:
        r0 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ all -> 0x00ea }
        if (r0 == 0) goto L_0x009e;
    L_0x0094:
        r0 = "NativeCodec";
        r1 = 0;
        r3 = "curr buffer is being processed by other thread, return";
        com.tencent.av.utils.QLog.e(r0, r1, r3);	 Catch:{ all -> 0x00ea }
    L_0x009e:
        monitor-exit(r7);	 Catch:{ all -> 0x00ea }
        r0 = r2;
        goto L_0x0017;
    L_0x00a2:
        r2 = r10.misdecoder;	 Catch:{ all -> 0x00ea }
        if (r2 == 0) goto L_0x00ed;
    L_0x00a6:
        r2 = r0.buffer;	 Catch:{ all -> 0x00ea }
        r3 = 0;
        r3 = r10.writeInputData(r2, r3);	 Catch:{ all -> 0x00ea }
    L_0x00ad:
        if (r3 <= 0) goto L_0x0107;
    L_0x00af:
        r4 = r10.mLastEncFrameTime;	 Catch:{ all -> 0x00ea }
        r1 = r10.mFrameInverval;	 Catch:{ all -> 0x00ea }
        r8 = (long) r1;	 Catch:{ all -> 0x00ea }
        r4 = r4 + r8;
        r10.mLastEncFrameTime = r4;	 Catch:{ all -> 0x00ea }
        r1 = r10.mPendingInputBuffers;	 Catch:{ all -> 0x00ea }
        r2 = 0;
        r1.remove(r2);	 Catch:{ all -> 0x00ea }
        r1 = r10.mDebugDelay;	 Catch:{ all -> 0x00ea }
        if (r1 == 0) goto L_0x00d0;
    L_0x00c1:
        r1 = r10.mDebugDelayMap;	 Catch:{ all -> 0x00ea }
        r4 = r10.mLastEncFrameTime;	 Catch:{ all -> 0x00ea }
        r8 = java.lang.System.currentTimeMillis();	 Catch:{ all -> 0x00ea }
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x00ea }
        r1.b(r4, r2);	 Catch:{ all -> 0x00ea }
    L_0x00d0:
        r1 = r10.mDebugIndexMap;	 Catch:{ all -> 0x00ea }
        r4 = r10.mLastEncFrameTime;	 Catch:{ all -> 0x00ea }
        r8 = (long) r12;	 Catch:{ all -> 0x00ea }
        r2 = java.lang.Long.valueOf(r8);	 Catch:{ all -> 0x00ea }
        r1.b(r4, r2);	 Catch:{ all -> 0x00ea }
        r1 = r10.mCodec;	 Catch:{ all -> 0x00ea }
        r2 = r0.index;	 Catch:{ all -> 0x00ea }
        r4 = r10.mLastEncFrameTime;	 Catch:{ all -> 0x00ea }
        r6 = 0;
        r1.queueInputBuffer(r2, r3, r4, r6);	 Catch:{ all -> 0x00ea }
        r0 = 1;
        monitor-exit(r7);	 Catch:{ all -> 0x00ea }
        goto L_0x0017;
    L_0x00ea:
        r0 = move-exception;
        monitor-exit(r7);	 Catch:{ all -> 0x00ea }
        throw r0;
    L_0x00ed:
        r2 = com.tencent.av.utils.QLog.isColorLevel();	 Catch:{ all -> 0x00ea }
        if (r2 == 0) goto L_0x00fd;
    L_0x00f3:
        r2 = "NativeCodec";
        r3 = 0;
        r5 = "call writeInputData2 in onDoCodec";
        com.tencent.av.utils.QLog.d(r2, r3, r5);	 Catch:{ all -> 0x00ea }
    L_0x00fd:
        r2 = r0.buffer;	 Catch:{ all -> 0x00ea }
        r3 = r10.mColorFormat;	 Catch:{ all -> 0x00ea }
        r5 = 0;
        r3 = r10.writeInputData2(r2, r3, r5);	 Catch:{ all -> 0x00ea }
        goto L_0x00ad;
    L_0x0107:
        if (r3 == 0) goto L_0x010b;
    L_0x0109:
        if (r3 != r1) goto L_0x010f;
    L_0x010b:
        monitor-exit(r7);	 Catch:{ all -> 0x00ea }
        r0 = r4;
        goto L_0x0017;
    L_0x010f:
        monitor-exit(r7);	 Catch:{ all -> 0x00ea }
        r0 = r1;
        goto L_0x0017;
    L_0x0113:
        monitor-exit(r7);	 Catch:{ all -> 0x00ea }
        r0 = r2;
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.av.mediacodec.NativeCodec.onDoCodecAsync(int, int):int");
    }

    private boolean onDoCodec(int i, int i2) {
        if (this.mCodec == null) {
            return false;
        }
        if (this.mTryAgainLaterCount >= 16) {
            if (QLog.isColorLevel()) {
                QLog.e(TAG, 0, "try too many times!");
            }
            return false;
        }
        int writeInputData;
        BufferData inputBuffer = this.mCodec.getInputBuffer();
        int i3 = 0;
        while (inputBuffer == null) {
            if (QLog.isColorLevel()) {
                QLog.e(TAG, 0, "inputbuffer not available");
            }
            try {
                Thread.sleep(50);
                inputBuffer = this.mCodec.getInputBuffer();
                i3++;
                if (inputBuffer == null) {
                    if (QLog.isColorLevel()) {
                        QLog.e(TAG, 0, "inputbuffer not available");
                    }
                    if (i3 > 8) {
                        if (QLog.isColorLevel()) {
                            QLog.e(TAG, 0, "inputbuffer not available, try count = " + i3);
                        }
                        return false;
                    }
                }
            } catch (Exception e) {
                try {
                    e.printStackTrace();
                    if (QLog.isColorLevel()) {
                        QLog.e(TAG, 0, "onDoCodec InterruptedException", e);
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (QLog.isColorLevel()) {
                        QLog.e(TAG, 0, "onDoCodec Exception!", e2);
                    }
                    return false;
                }
            }
        }
        if (this.misdecoder) {
            writeInputData = writeInputData(inputBuffer.buffer, false);
        } else {
            writeInputData = writeInputData2(inputBuffer.buffer, this.mColorFormat, false);
        }
        if (writeInputData < 0) {
            if (QLog.isColorLevel()) {
                QLog.e(TAG, 0, "writeInputData, sampleSize < 0");
            }
            return false;
        }
        this.mLastEncFrameTime += (long) this.mFrameInverval;
        if (this.mDebugDelay) {
            this.mDebugDelayMap.b(this.mLastEncFrameTime, Long.valueOf(System.currentTimeMillis()));
        }
        this.mDebugIndexMap.b(this.mLastEncFrameTime, Long.valueOf((long) i2));
        this.mCodec.queueInputBuffer(inputBuffer.index, writeInputData, this.mLastEncFrameTime, 0);
        BufferData dequeueOutputBuffer = this.mCodec.dequeueOutputBuffer();
        if (dequeueOutputBuffer != null) {
            if (dequeueOutputBuffer.index < 0) {
                this.mTryAgainLaterCount++;
                if (QLog.isColorLevel()) {
                    QLog.e(TAG, 0, "dequeueOutputBuffer, try again later, count = " + this.mTryAgainLaterCount);
                }
                if (this.mTryAgainLaterCount >= 16) {
                    return false;
                }
                return true;
            }
            this.mTryAgainLaterCount = 0;
            if (!dequeueOutputBuffer.success) {
                if (QLog.isColorLevel()) {
                    QLog.e(TAG, 0, "onDoCodec err!");
                }
                return false;
            } else if (dequeueOutputBuffer.format == null || dequeueOutputBuffer.buffer == null) {
                this.mCodec.releaseOutputBuffer(dequeueOutputBuffer.index);
                return false;
            } else {
                if (!this.misdecoder) {
                    BufferData dequeueOutputBuffer2;
                    if (dequeueOutputBuffer.info.flags == 1) {
                    }
                    if (dequeueOutputBuffer.info.flags == 2) {
                        this.mCodec.releaseOutputBuffer(dequeueOutputBuffer.index);
                        readOutputStream(dequeueOutputBuffer.buffer, dequeueOutputBuffer.info.presentationTimeUs, dequeueOutputBuffer.info.offset, dequeueOutputBuffer.info.size, dequeueOutputBuffer.info.flags);
                        dequeueOutputBuffer2 = this.mCodec.dequeueOutputBuffer();
                        if (dequeueOutputBuffer2 != null) {
                            if (dequeueOutputBuffer2.index < 0) {
                                this.mTryAgainLaterCount2++;
                                if (QLog.isColorLevel()) {
                                    QLog.e(TAG, 0, "re-dequeue dequeueOutputBuffer, try again later, count = " + this.mTryAgainLaterCount2);
                                }
                                if (this.mTryAgainLaterCount2 >= 10) {
                                    return false;
                                }
                                return true;
                            }
                            this.mTryAgainLaterCount2 = 0;
                            if (!dequeueOutputBuffer2.success) {
                                if (QLog.isColorLevel()) {
                                    QLog.e(TAG, 0, "re-dequeue onDoCodec err!");
                                }
                                return false;
                            } else if (dequeueOutputBuffer2.format == null || dequeueOutputBuffer2.buffer == null) {
                                this.mCodec.releaseOutputBuffer(dequeueOutputBuffer2.index);
                                return false;
                            }
                        }
                        if (QLog.isColorLevel()) {
                            QLog.d(TAG, 0, "re-dequeue success");
                        }
                    } else {
                        dequeueOutputBuffer2 = dequeueOutputBuffer;
                    }
                    Long calcDelay = calcDelay(true, dequeueOutputBuffer2.info);
                    readOutputStream(dequeueOutputBuffer2.buffer, calcDelay.longValue(), dequeueOutputBuffer2.info.offset, dequeueOutputBuffer2.info.size, dequeueOutputBuffer2.info.flags);
                    dequeueOutputBuffer = dequeueOutputBuffer2;
                } else if (HWColorFormat.isSupportedDecodeFormats(dequeueOutputBuffer.format.getInteger("color-format"))) {
                    calcDelay(true, dequeueOutputBuffer.info);
                    setFrame(dequeueOutputBuffer.buffer, dequeueOutputBuffer.info.size, dequeueOutputBuffer.format);
                } else {
                    if (QLog.isColorLevel()) {
                        QLog.e(TAG, 0, "onDoCodec don't support format!");
                    }
                    return false;
                }
                this.mCodec.releaseOutputBuffer(dequeueOutputBuffer.index);
            }
        }
        return true;
    }

    void setParams_impl() {
        Bundle bundle;
        if (QLog.isColorLevel()) {
            QLog.d(TAG, 0, "setParams_impl");
        }
        if (this.setIFramePending) {
            bundle = new Bundle();
            bundle.putInt(AndroidCodec.ForceIFrame, 1);
            this.setIFramePending = false;
            if (this.setBitRatePending > 0) {
                bundle.putInt("bitrate", this.setBitRatePending);
            }
            this.mCodec.setParameters(bundle);
            this.setBitRatePending = 0;
        }
        if (this.setBitRatePending > 0) {
            bundle = new Bundle();
            bundle.putInt("bitrate", this.setBitRatePending);
            if (this.setIFramePending) {
                bundle.putInt(AndroidCodec.ForceIFrame, 1);
            }
            this.mCodec.setParameters(bundle);
            this.setBitRatePending = 0;
            this.setIFramePending = false;
        }
    }
}
