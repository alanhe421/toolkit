package com.tencent;

import java.util.ArrayList;
import java.util.List;

public class TIMSNSSystemElem extends TIMElem {
    private long decideReportTimestamp;
    private List<TIMSNSChangeInfo> infoList;
    private long pendencyReportTimestamp;
    private long recommendReportTimestamp;
    private TIMSNSSystemType subType;

    public TIMSNSSystemElem() {
        this.infoList = new ArrayList();
        this.type = TIMElemType.SNSTips;
    }

    public List<TIMSNSChangeInfo> getChangeInfoList() {
        return this.infoList;
    }

    public long getDecideReportTimestamp() {
        return this.decideReportTimestamp;
    }

    public long getPendencyReportTimestamp() {
        return this.pendencyReportTimestamp;
    }

    public long getRecommendReportTimestamp() {
        return this.recommendReportTimestamp;
    }

    public TIMSNSSystemType getSubType() {
        return this.subType;
    }

    void setDecideReportTimestamp(long j) {
        this.decideReportTimestamp = j;
    }

    void setPendencyReportTimestamp(long j) {
        this.pendencyReportTimestamp = j;
    }

    void setRecommendReportTimestamp(long j) {
        this.recommendReportTimestamp = j;
    }

    void setSubType(long j) {
        this.subType = TIMSNSSystemType.getType((int) j);
    }
}
