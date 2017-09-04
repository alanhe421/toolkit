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

public class TIMVideo {
    private static Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final String tag = TIMVideo.class.getSimpleName();
    private long businessId = 0;
    private int downloadFlag = 0;
    private long duaration;
    private String identifier = "";
    private long size;
    private String type = "";
    private List<String> urls = new ArrayList();
    private String uuid = "";

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

    public long getDuaration() {
        return this.duaration;
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

    public void getVideo(String str, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            QualityReportHelper qualityReportHelper = new QualityReportHelper(QrEventType.kEventRecvVideo.swigValue());
            if (TextUtils.isEmpty(this.uuid)) {
                IMErrInfo iMErrInfo = new IMErrInfo(BaseConstants.ERR_INVALID_SDK_OBJECT, "uuid is empty");
                tIMCallBack.onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
                qualityReportHelper.init(iMErrInfo.getCode(), iMErrInfo.getMsg());
                qualityReportHelper.report();
                return;
            }
            QLog.i(tag, 1, "download video, downloadFlag: " + this.downloadFlag);
            if (this.downloadFlag == 2) {
                IMMsfCoreProxy.get().downloadToFile(this.urls, str, tIMCallBack, qualityReportHelper);
                return;
            }
            IApplyDownloadFileCallback hlVar = new hl(this, tIMCallBack, str, qualityReportHelper);
            ApplyDownloadFileReq applyDownloadFileReq = new ApplyDownloadFileReq();
            applyDownloadFileReq.setBusiId(this.businessId);
            applyDownloadFileReq.setDownloadFlag((long) this.downloadFlag);
            applyDownloadFileReq.setType(2);
            applyDownloadFileReq.setUuid(this.uuid);
            TIMManager.getInstanceById(this.identifier).getCoreUser().applyDownloadFile(applyDownloadFileReq, hlVar);
        }
    }

    void setBusinessId(long j) {
        this.businessId = j;
    }

    void setDownloadFlag(int i) {
        this.downloadFlag = i;
    }

    public void setDuaration(long j) {
        this.duaration = j;
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
        if (str != null) {
            this.type = str;
        }
    }

    void setUrls(List<String> list) {
        this.urls = list;
    }

    void setUuid(String str) {
        if (str != null) {
            this.uuid = str;
        }
    }
}
