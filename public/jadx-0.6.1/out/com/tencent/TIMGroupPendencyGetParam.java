package com.tencent;

import com.tencent.imcore.GetGroupPendencyOption;

public class TIMGroupPendencyGetParam {
    private long numPerPage;
    private long timestamp;

    long getNumPerPage() {
        return this.numPerPage;
    }

    long getTimestamp() {
        return this.timestamp;
    }

    public void setNumPerPage(long j) {
        this.numPerPage = j;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    GetGroupPendencyOption toOption() {
        GetGroupPendencyOption getGroupPendencyOption = new GetGroupPendencyOption();
        getGroupPendencyOption.setStart_time(this.timestamp);
        getGroupPendencyOption.setMax_limited(this.numPerPage);
        return getGroupPendencyOption;
    }
}
