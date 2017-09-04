package com.tencent;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.tencent.imcore.ApplyDownloadFileReq;
import com.tencent.imcore.IApplyDownloadFileCallback;
import com.tencent.imcore.QrEventType;
import com.tencent.imsdk.BaseConstants;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.QLog;
import com.tencent.imsdk.util.QualityReportHelper;
import java.util.ArrayList;
import java.util.List;

public class TIMSnapshot {
    private static Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final String tag = TIMSnapshot.class.getSimpleName();
    private long businessId;
    private int downloadFlag;
    private long height;
    private String identifier;
    private long size;
    private String type;
    private List<String> urls;
    private String uuid;
    private long width;

    public TIMSnapshot() {
        this(TIMManager.getInstance().getIdentification());
    }

    public TIMSnapshot(String str) {
        this.uuid = "";
        this.type = "";
        this.urls = new ArrayList();
        this.businessId = 0;
        this.downloadFlag = 0;
        this.identifier = "";
        this.identifier = str;
    }

    void addUrl(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.urls.add(str);
        }
    }

    long getBusinessId() {
        return this.businessId;
    }

    int getDownloadFlag() {
        return this.downloadFlag;
    }

    public long getHeight() {
        return this.height;
    }

    public void getImage(String str, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            QualityReportHelper qualityReportHelper = new QualityReportHelper(QrEventType.kEventRecvPic.swigValue());
            if (TextUtils.isEmpty(this.uuid)) {
                IMErrInfo iMErrInfo = new IMErrInfo(BaseConstants.ERR_INVALID_SDK_OBJECT, "uuid is empty");
                tIMCallBack.onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
                qualityReportHelper.init(iMErrInfo.getCode(), iMErrInfo.getMsg());
                qualityReportHelper.report();
                return;
            }
            QLog.i(tag, 1, "download snapshot, downloadFlag: " + this.downloadFlag);
            if (this.downloadFlag == 2) {
                IMMsfCoreProxy.get().downloadToFile(this.urls, str, tIMCallBack, qualityReportHelper);
                return;
            }
            IApplyDownloadFileCallback hiVar = new hi(this, tIMCallBack, str, qualityReportHelper);
            ApplyDownloadFileReq applyDownloadFileReq = new ApplyDownloadFileReq();
            applyDownloadFileReq.setBusiId(this.businessId);
            applyDownloadFileReq.setDownloadFlag((long) this.downloadFlag);
            applyDownloadFileReq.setType(0);
            applyDownloadFileReq.setUuid(this.uuid);
            TIMManager.getInstanceById(this.identifier).getCoreUser().applyDownloadFile(applyDownloadFileReq, hiVar);
        }
    }

    public long getSize() {
        return this.size;
    }

    public String getType() {
        return this.type;
    }

    List<String> getUrls() {
        return this.urls;
    }

    public String getUuid() {
        return this.uuid;
    }

    public long getWidth() {
        return this.width;
    }

    void setBusinessId(long j) {
        this.businessId = j;
    }

    void setDownloadFlag(int i) {
        this.downloadFlag = i;
    }

    public void setHeight(long j) {
        this.height = j;
    }

    protected void setIdentifier(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.identifier = str;
        }
    }

    void setSize(long j) {
        this.size = j;
    }

    public void setType(String str) {
        this.type = str;
    }

    void setUrls(List<String> list) {
        this.urls = list;
    }

    void setUuid(String str) {
        this.uuid = str;
    }

    public void setWidth(long j) {
        this.width = j;
    }
}
