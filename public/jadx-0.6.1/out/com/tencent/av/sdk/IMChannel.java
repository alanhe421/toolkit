package com.tencent.av.sdk;

import android.util.Log;
import com.tencent.av.channel.AVAppChannel;
import com.tencent.av.channel.AVAppChannel.CsCmdCallback;
import com.tencent.av.channel.AVAppChannel.IdToIdCallback;
import com.tencent.av.channel.AVChannelManager;
import com.tencent.av.logger.IMLogger;
import com.tencent.av.utils.QLog;

public class IMChannel {
    public static final int ERR_FAILED = 1;
    public static final int ERR_OK = 0;
    public static String LOGTAG = IMLogger.LOGTAG;
    public int mNativeEntity;

    public class CsCmdCallbackImpl implements CsCmdCallback {
        private int mNativeCallback;

        public CsCmdCallbackImpl(int i) {
            this.mNativeCallback = i;
        }

        public void onError(int i, String str) {
            MultiVideoResult multiVideoResult = new MultiVideoResult();
            multiVideoResult.result = i;
            multiVideoResult.errorInfo = str;
            IMChannel.this.nativeMultiVideoCallback(this.mNativeCallback, multiVideoResult);
            this.mNativeCallback = 0;
        }

        public void onSuccess(byte[] bArr) {
            MultiVideoResult multiVideoResult = new MultiVideoResult();
            multiVideoResult.result = 0;
            multiVideoResult.response = bArr;
            IMChannel.this.nativeMultiVideoCallback(this.mNativeCallback, multiVideoResult);
            this.mNativeCallback = 0;
        }
    }

    public class IdToIdCallbackImpl implements IdToIdCallback {
        private int mNativeCallback;

        public IdToIdCallbackImpl(int i) {
            this.mNativeCallback = i;
        }

        public void onError(int i, String str) {
            IdToIdResult idToIdResult = new IdToIdResult();
            idToIdResult.result = i;
            idToIdResult.errorInfo = str;
            IMChannel.this.nativeIdToIdCallback(this.mNativeCallback, idToIdResult);
            this.mNativeCallback = 0;
        }

        public void onSuccess(String[] strArr, long[] jArr) {
            IdToIdResult idToIdResult = new IdToIdResult();
            idToIdResult.result = 0;
            idToIdResult.identifierList = strArr;
            idToIdResult.tinyIdList = jArr;
            IMChannel.this.nativeIdToIdCallback(this.mNativeCallback, idToIdResult);
            this.mNativeCallback = 0;
        }
    }

    public class IdToIdResult {
        public String errorInfo;
        public String[] identifierList;
        public int result = 1;
        public long[] tinyIdList;
    }

    public class MultiVideoResult {
        public String errorInfo;
        public byte[] response;
        public int result = 1;
    }

    public class QualityReportCallback implements CsCmdCallback {
        private int mNativeCallback;

        public QualityReportCallback(int i) {
            this.mNativeCallback = i;
        }

        public void onError(int i, String str) {
            QLog.e(IMChannel.LOGTAG, 0, "QualityReport failed: " + i + " info: " + str);
            QualityReportResult qualityReportResult = new QualityReportResult();
            qualityReportResult.result = i;
            qualityReportResult.errorInfo = str;
            IMChannel.this.nativeQualityReportCallback(this.mNativeCallback, qualityReportResult);
            this.mNativeCallback = 0;
        }

        public void onSuccess(byte[] bArr) {
            QLog.d(IMChannel.LOGTAG, 0, "QualityReport succ");
            QualityReportResult qualityReportResult = new QualityReportResult();
            qualityReportResult.result = 0;
            IMChannel.this.nativeQualityReportCallback(this.mNativeCallback, qualityReportResult);
            this.mNativeCallback = 0;
        }
    }

    public class QualityReportResult {
        public String errorInfo;
        public int result = 1;
    }

    public static class ToTinyIdParam {
        public String accountType;
        public String appIdAt3rd;
        public String[] identifierList;
    }

    public native void nativeIdToIdCallback(int i, IdToIdResult idToIdResult);

    public native void nativeMultiVideoCallback(int i, MultiVideoResult multiVideoResult);

    public native void nativeQualityReportCallback(int i, QualityReportResult qualityReportResult);

    public static int getServerEnvType() {
        AVAppChannel appChannel = AVChannelManager.getAppChannel();
        if (appChannel != null) {
            return appChannel.getServerEnvType();
        }
        return -1;
    }

    public IMChannel(int i) {
        this.mNativeEntity = i;
    }

    public long getSelfTinyId() {
        long j = 0;
        AVAppChannel appChannel = AVChannelManager.getAppChannel();
        if (appChannel != null) {
            j = appChannel.getTinyId();
        }
        Log.d(LOGTAG, "GetSelfTinyId selfTinyId: " + j);
        return j;
    }

    public void identifierToTinyId(ToTinyIdParam toTinyIdParam, int i) {
        QLog.d(LOGTAG, 0, "java IMChannel identifierToTinyId");
        IdToIdCallback idToIdCallbackImpl = new IdToIdCallbackImpl(i);
        AVAppChannel appChannel = AVChannelManager.getAppChannel();
        if (appChannel != null) {
            appChannel.identifierToTinyId(toTinyIdParam.accountType, toTinyIdParam.appIdAt3rd, toTinyIdParam.identifierList, idToIdCallbackImpl);
        }
    }

    public void tinyIdToIdentifier(long[] jArr, int i) {
        IdToIdCallback idToIdCallbackImpl = new IdToIdCallbackImpl(i);
        AVAppChannel appChannel = AVChannelManager.getAppChannel();
        if (appChannel != null) {
            appChannel.tinyIdToIdentifier(jArr, idToIdCallbackImpl);
        }
    }

    public void multiVideoAppRequest(byte[] bArr, int i) {
        CsCmdCallback csCmdCallbackImpl = new CsCmdCallbackImpl(i);
        AVAppChannel appChannel = AVChannelManager.getAppChannel();
        if (appChannel != null) {
            appChannel.requestAppCmd(bArr, csCmdCallbackImpl);
        }
    }

    public void multiVideoInfoRequest(byte[] bArr, int i) {
        CsCmdCallback csCmdCallbackImpl = new CsCmdCallbackImpl(i);
        AVAppChannel appChannel = AVChannelManager.getAppChannel();
        if (appChannel != null) {
            appChannel.requestInfoCmd(bArr, csCmdCallbackImpl);
        }
    }

    public void multiVideoConfigRequest(byte[] bArr, int i) {
        QLog.i(LOGTAG, 0, "multiVideoConfigRequest length:" + bArr.length);
        CsCmdCallback csCmdCallbackImpl = new CsCmdCallbackImpl(i);
        AVAppChannel appChannel = AVChannelManager.getAppChannel();
        if (appChannel != null) {
            appChannel.requestCmd("VideoCCSvc.opensdk", bArr, csCmdCallbackImpl);
        }
    }

    public void qualityReportRequest(int i, byte[] bArr, int i2) {
        CsCmdCallback qualityReportCallback = new QualityReportCallback(i2);
        AVAppChannel appChannel = AVChannelManager.getAppChannel();
        if (appChannel != null) {
            appChannel.requestReportCmd(i, bArr, qualityReportCallback);
        }
    }
}
