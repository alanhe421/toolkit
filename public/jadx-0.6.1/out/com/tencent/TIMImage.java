package com.tencent;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.tencent.imcore.QrEventType;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.QLog;
import com.tencent.imsdk.util.QualityReportHelper;
import java.util.Collections;

public class TIMImage {
    private static Handler mainHandler = new Handler(Looper.getMainLooper());
    private static final String tag = "TIMImage";
    private long height;
    private String identifier = "";
    private long size;
    private TIMImageType type;
    private String url;
    private String uuid;
    private long width;

    public long getHeight() {
        return this.height;
    }

    @Deprecated
    public void getImage(TIMValueCallBack<byte[]> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            QualityReportHelper qualityReportHelper = new QualityReportHelper(QrEventType.kEventRecvPic.swigValue());
            QLog.d(tag, 1, "getImage from url: " + this.url);
            IMMsfCoreProxy.get().downloadToBuff(Collections.singletonList(this.url), tIMValueCallBack, qualityReportHelper);
        }
    }

    public void getImage(String str, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            QualityReportHelper qualityReportHelper = new QualityReportHelper(QrEventType.kEventRecvPic.swigValue());
            QLog.d(tag, 1, "getImage from url: " + this.url);
            IMMsfCoreProxy.get().downloadToFile(Collections.singletonList(this.url), str, tIMCallBack, qualityReportHelper);
        }
    }

    public long getSize() {
        return this.size;
    }

    public TIMImageType getType() {
        return this.type;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUuid() {
        int i;
        switch (gw.a[this.type.ordinal()]) {
            case 1:
                i = 1;
                break;
            case 2:
                i = 2;
                break;
            case 3:
                i = 3;
                break;
            default:
                i = 0;
                break;
        }
        return String.format("%x_%s", new Object[]{Integer.valueOf(i), this.uuid});
    }

    public long getWidth() {
        return this.width;
    }

    void setHeight(long j) {
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

    void setType(TIMImageType tIMImageType) {
        this.type = tIMImageType;
    }

    void setUrl(String str) {
        this.url = str;
    }

    void setUuid(String str) {
        this.uuid = str;
    }

    void setWidth(long j) {
        this.width = j;
    }
}
