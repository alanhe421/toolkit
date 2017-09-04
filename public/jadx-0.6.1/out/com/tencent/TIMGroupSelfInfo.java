package com.tencent;

import com.tencent.imcore.MemberInfo;

public class TIMGroupSelfInfo extends TIMGroupMemberInfo {
    private long recvOpt;

    TIMGroupSelfInfo() {
    }

    TIMGroupSelfInfo(MemberInfo memberInfo) {
        super(memberInfo);
        setRecvOpt(memberInfo.getMsg_flag());
    }

    public TIMGroupReceiveMessageOpt getRecvOpt() {
        return this.recvOpt == TIMGroupReceiveMessageOpt.ReceiveAndNotify.getValue() ? TIMGroupReceiveMessageOpt.ReceiveAndNotify : this.recvOpt == TIMGroupReceiveMessageOpt.NotReceive.getValue() ? TIMGroupReceiveMessageOpt.NotReceive : this.recvOpt == TIMGroupReceiveMessageOpt.ReceiveNotNotify.getValue() ? TIMGroupReceiveMessageOpt.ReceiveNotNotify : null;
    }

    void setRecvOpt(long j) {
        this.recvOpt = j;
    }
}
