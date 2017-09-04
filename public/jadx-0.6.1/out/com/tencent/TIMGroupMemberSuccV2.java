package com.tencent;

import java.util.List;

public class TIMGroupMemberSuccV2 {
    private List<TIMGroupMemberInfo> memberInfoList;
    private long nextSeq;

    public List<TIMGroupMemberInfo> getMemberInfoList() {
        return this.memberInfoList;
    }

    public long getNextSeq() {
        return this.nextSeq;
    }

    void setMemberInfoList(List<TIMGroupMemberInfo> list) {
        this.memberInfoList = list;
    }

    void setNextSeq(long j) {
        this.nextSeq = j;
    }
}
