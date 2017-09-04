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

public class TIMFileElem extends TIMElem {
    private static final String tag = "TIMFileElem";
    private long businessId;
    private byte[] data;
    private int downloadFlag;
    private String fileName;
    private long fileSize;
    private String path;
    private int taskId;
    private List<String> urls;
    private String uuid;

    public TIMFileElem() {
        this.uuid = null;
        this.data = null;
        this.urls = new ArrayList();
        this.businessId = 0;
        this.downloadFlag = 0;
        this.type = TIMElemType.File;
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

    int getDownloadFlag() {
        return this.downloadFlag;
    }

    @Deprecated
    public void getFile(TIMValueCallBack<byte[]> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            QualityReportHelper qualityReportHelper = new QualityReportHelper(QrEventType.kEventRecvFile.swigValue());
            if (TextUtils.isEmpty(this.uuid)) {
                tIMValueCallBack.onError(BaseConstants.ERR_INVALID_SDK_OBJECT, "uuid is empty");
                qualityReportHelper.init(BaseConstants.ERR_INVALID_SDK_OBJECT, "uuid is empty");
                qualityReportHelper.report();
                return;
            }
            QLog.i(tag, 1, "download file, downloadFlag: " + this.downloadFlag);
            if (this.downloadFlag == 2) {
                IMMsfCoreProxy.get().downloadToBuff(this.urls, tIMValueCallBack, qualityReportHelper);
                return;
            }
            IApplyDownloadFileCallback chVar = new ch(this, tIMValueCallBack, qualityReportHelper);
            ApplyDownloadFileReq applyDownloadFileReq = new ApplyDownloadFileReq();
            applyDownloadFileReq.setBusiId(this.businessId);
            applyDownloadFileReq.setDownloadFlag((long) this.downloadFlag);
            applyDownloadFileReq.setType(1);
            applyDownloadFileReq.setUuid(this.uuid);
            TIMManager.getInstanceById(this.identifier).getCoreUser().applyDownloadFile(applyDownloadFileReq, chVar);
        }
    }

    public String getFileName() {
        return this.fileName;
    }

    public long getFileSize() {
        return this.fileSize;
    }

    public String getPath() {
        return this.path;
    }

    public int getTaskId() {
        return this.taskId;
    }

    public void getToFile(String str, TIMCallBack tIMCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper(QrEventType.kEventRecvFile.swigValue());
        if (TextUtils.isEmpty(this.uuid)) {
            tIMCallBack.onError(BaseConstants.ERR_INVALID_SDK_OBJECT, "uuid is empty");
            qualityReportHelper.init(BaseConstants.ERR_INVALID_SDK_OBJECT, "uuid is empty");
            qualityReportHelper.report();
            return;
        }
        QLog.i(tag, 1, "download file, downloadFlag: " + this.downloadFlag);
        if (this.downloadFlag == 2) {
            IMMsfCoreProxy.get().downloadToFile(this.urls, str, tIMCallBack, qualityReportHelper);
            return;
        }
        IApplyDownloadFileCallback cgVar = new cg(this, tIMCallBack, str, qualityReportHelper);
        ApplyDownloadFileReq applyDownloadFileReq = new ApplyDownloadFileReq();
        applyDownloadFileReq.setBusiId(this.businessId);
        applyDownloadFileReq.setDownloadFlag((long) this.downloadFlag);
        applyDownloadFileReq.setType(1);
        applyDownloadFileReq.setUuid(this.uuid);
        TIMManager.getInstanceById(this.identifier).getCoreUser().applyDownloadFile(applyDownloadFileReq, cgVar);
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
            this.fileSize = (long) bArr.length;
        }
    }

    void setDownloadFlag(int i) {
        this.downloadFlag = i;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    void setFileSize(long j) {
        this.fileSize = j;
    }

    public void setPath(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.path = str;
            this.fileSize = new File(str).length();
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
