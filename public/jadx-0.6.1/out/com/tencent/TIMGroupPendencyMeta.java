package com.tencent;

import com.tencent.imcore.GroupPendencyMeta;

public class TIMGroupPendencyMeta {
    private long nextStartTimestamp;
    private long reportedTimestamp;
    private long unReadCount;

    TIMGroupPendencyMeta(GroupPendencyMeta groupPendencyMeta) {
        this.nextStartTimestamp = groupPendencyMeta.getNext_start_time();
        this.reportedTimestamp = groupPendencyMeta.getRead_time_seq();
        this.unReadCount = groupPendencyMeta.getUnread_num();
    }

    public long getNextStartTimestamp() {
        return this.nextStartTimestamp;
    }

    public long getReportedTimestamp() {
        return this.reportedTimestamp;
    }

    public long getUnReadCount() {
        return this.unReadCount;
    }

    void setNextStartTimestamp(long j) {
        this.nextStartTimestamp = j;
    }

    void setReportedTimestamp(long j) {
        this.reportedTimestamp = j;
    }

    void setUnReadCount(long j) {
        this.unReadCount = j;
    }
}
