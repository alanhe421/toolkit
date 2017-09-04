package com.tencent;

import android.text.TextUtils;
import com.tencent.imcore.ApplyDownloadFileReq;
import com.tencent.imcore.IApplyDownloadFileCallback;
import com.tencent.imcore.QrEventType;
import com.tencent.imsdk.BaseConstants;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.QLog;
import com.tencent.imsdk.util.QualityReportHelper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TIMSoundElem extends TIMElem {
    private static final String tag = "TIMSoundElem";
    private long businessId;
    private byte[] data;
    private long dataSize;
    private int downloadFlag;
    private long duration;
    private String path;
    private int taskId;
    private List<String> urls;
    private String uuid;

    public TIMSoundElem() {
        this.duration = 0;
        this.dataSize = 0;
        this.uuid = null;
        this.data = null;
        this.path = "";
        this.urls = new ArrayList();
        this.businessId = 0;
        this.downloadFlag = 0;
        this.type = TIMElemType.Sound;
    }

    void addUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.urls.add(str);
        }
    }

    long getBusinessId() {
        return this.businessId;
    }

    byte[] getData() {
        return this.data;
    }

    public long getDataSize() {
        return this.dataSize;
    }

    int getDownloadFlag() {
        return this.downloadFlag;
    }

    public long getDuration() {
        return this.duration;
    }

    public String getPath() {
        return this.path;
    }

    @Deprecated
    public void getSound(TIMValueCallBack<byte[]> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            QualityReportHelper qualityReportHelper = new QualityReportHelper(QrEventType.kEventRecvAudio.swigValue());
            if (TextUtils.isEmpty(this.uuid)) {
                tIMValueCallBack.onError(BaseConstants.ERR_INVALID_SDK_OBJECT, "uuid is empty");
                qualityReportHelper.init(BaseConstants.ERR_INVALID_SDK_OBJECT, "uuid is empty");
                qualityReportHelper.report();
                return;
            }
            QLog.i(tag, 1, "download sound, downloadFlag: " + this.downloadFlag);
            if (this.downloadFlag == 2) {
                IMMsfCoreProxy.get().downloadToBuff(this.urls, tIMValueCallBack, qualityReportHelper);
                return;
            }
            IApplyDownloadFileCallback hkVar = new hk(this, tIMValueCallBack, qualityReportHelper);
            ApplyDownloadFileReq applyDownloadFileReq = new ApplyDownloadFileReq();
            applyDownloadFileReq.setBusiId(this.businessId);
            applyDownloadFileReq.setDownloadFlag((long) this.downloadFlag);
            applyDownloadFileReq.setType(3);
            applyDownloadFileReq.setUuid(this.uuid);
            TIMManager.getInstanceById(this.identifier).getCoreUser().applyDownloadFile(applyDownloadFileReq, hkVar);
        }
    }

    public void getSoundToFile(String str, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            QualityReportHelper qualityReportHelper = new QualityReportHelper(QrEventType.kEventRecvAudio.swigValue());
            if (TextUtils.isEmpty(this.uuid)) {
                tIMCallBack.onError(BaseConstants.ERR_INVALID_SDK_OBJECT, "uuid is empty");
                qualityReportHelper.init(BaseConstants.ERR_INVALID_SDK_OBJECT, "uuid is empty");
                qualityReportHelper.report();
                return;
            }
            QLog.i(tag, 1, "download sound, downloadFlag: " + this.downloadFlag);
            if (this.downloadFlag == 2) {
                IMMsfCoreProxy.get().downloadToFile(this.urls, str, tIMCallBack, qualityReportHelper);
                return;
            }
            IApplyDownloadFileCallback hjVar = new hj(this, tIMCallBack, str, qualityReportHelper);
            ApplyDownloadFileReq applyDownloadFileReq = new ApplyDownloadFileReq();
            applyDownloadFileReq.setBusiId(this.businessId);
            applyDownloadFileReq.setDownloadFlag((long) this.downloadFlag);
            applyDownloadFileReq.setType(3);
            applyDownloadFileReq.setUuid(this.uuid);
            TIMManager.getInstanceById(this.identifier).getCoreUser().applyDownloadFile(applyDownloadFileReq, hjVar);
        }
    }

    public int getTaskId() {
        return this.taskId;
    }

    List<String> getUrls() {
        return this.urls;
    }

    public String getUuid() {
        return this.uuid;
    }

    void setBusinessId(long j) {
        this.businessId = j;
    }

    public void setData(byte[] bArr) {
        if (bArr != null) {
            this.data = bArr;
            this.dataSize = (long) bArr.length;
        }
    }

    void setDataSize(long j) {
        this.dataSize = j;
    }

    void setDownloadFlag(int i) {
        this.downloadFlag = i;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public void setPath(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.path = str;
            this.dataSize = new File(str).length();
        }
    }

    void setTaskId(int i) {
        this.taskId = i;
    }

    void setUrls(List<String> list) {
        this.urls = list;
    }

    void setUuid(String str) {
        this.uuid = str;
    }
}
