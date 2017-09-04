package com.tencent.av.sdk;

import com.tencent.av.sdk.AVQualityStats.AudioDecodeParam;
import com.tencent.av.sdk.AVQualityStats.VideoDecodeParam;
import com.tencent.av.sdk.AVQualityStats.VideoEncodeParam;
import com.tencent.av.utils.QLog;
import java.util.ArrayList;

public class AVRoomMulti {
    public static final int AUDIO_CATEGORY_MEDIA_PLAYBACK = 2;
    public static final int AUDIO_CATEGORY_MEDIA_PLAY_AND_RECORD = 1;
    public static final int AUDIO_CATEGORY_MEDIA_PLAY_AND_RECORD_HIGH_QUALITY = 3;
    public static final int AUDIO_CATEGORY_VOICECHAT = 0;
    public static final long AUTH_BITS_CREATE_ROOM = 1;
    public static final long AUTH_BITS_DEFAULT = -1;
    public static final long AUTH_BITS_JOIN_ROOM = 2;
    public static final long AUTH_BITS_RECV_AUDIO = 8;
    public static final long AUTH_BITS_RECV_CAMERA_VIDEO = 32;
    public static final long AUTH_BITS_RECV_SCREEN_VIDEO = 128;
    public static final long AUTH_BITS_SEND_AUDIO = 4;
    public static final long AUTH_BITS_SEND_CAMERA_VIDEO = 16;
    public static final long AUTH_BITS_SEND_SCREEN_VIDEO = 64;
    static final String TAG = "SdkJni";
    public static final int VIDEO_RECV_MODE_MANUAL = 0;
    public static final int VIDEO_RECV_MODE_SEMI_AUTO_RECV_CAMERA_VIDEO = 1;
    private AVQualityStats AVQualityStatsInstance;
    protected int nativeObj;

    private native boolean getAVQualityStats(AVQualityStats aVQualityStats, ArrayList<VideoEncodeParam> arrayList, ArrayList<VideoDecodeParam> arrayList2, ArrayList<AudioDecodeParam> arrayList3);

    public native void cancelAllView(AVCallback aVCallback);

    public native int cancelAudioList();

    public native void changeAVControlRole(String str, AVCallback aVCallback);

    @Deprecated
    public native void changeAuthority(long j, byte[] bArr, int i, AVCallback aVCallback);

    public native AVEndpoint getEndpointById(String str);

    public native int getEndpointCount();

    @Deprecated
    public native String getQualityParam();

    public native String getQualityTips();

    public native int getRoomId();

    public native String getStatisticsParam();

    native int nativeRequestAudioList(String[] strArr, int i);

    public native void requestViewList(String[] strArr, AVView[] aVViewArr, int i, RequestViewListCompleteCallback requestViewListCompleteCallback);

    public native void setNetType(int i);

    AVRoomMulti() {
        this.nativeObj = 0;
        this.AVQualityStatsInstance = null;
        this.nativeObj = 0;
    }

    public AVQualityStats getAVQualityStats() {
        if (this.AVQualityStatsInstance == null) {
            this.AVQualityStatsInstance = new AVQualityStats();
        }
        if (this.AVQualityStatsInstance == null || this.AVQualityStatsInstance.videoEncodeInfo == null || this.AVQualityStatsInstance.videoDecodeInfo == null) {
            QLog.e(TAG, 0, "AVQualityStatsInstance is not right to create");
            return null;
        } else if (getAVQualityStats(this.AVQualityStatsInstance, this.AVQualityStatsInstance.videoEncodeInfo, this.AVQualityStatsInstance.videoDecodeInfo, this.AVQualityStatsInstance.audioDecodeInfo)) {
            return this.AVQualityStatsInstance;
        } else {
            return null;
        }
    }

    public int requestAudioList(String[] strArr) {
        QLog.e(TAG, 0, "loki:requestAudioList");
        return nativeRequestAudioList(strArr, strArr.length);
    }
}
