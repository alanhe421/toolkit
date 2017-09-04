package com.tencent;

import com.tencent.imcore.GroupSelfInfo;

public class TIMGroupBasicSelfInfo {
    private long joinTime;
    private TIMGroupReceiveMessageOpt recvMsgOption = TIMGroupReceiveMessageOpt.ReceiveAndNotify;
    private TIMGroupMemberRoleType role = TIMGroupMemberRoleType.NotMember;

    TIMGroupBasicSelfInfo() {
    }

    TIMGroupBasicSelfInfo(GroupSelfInfo groupSelfInfo) {
        if (groupSelfInfo != null) {
            setRole(groupSelfInfo.getRole());
            setJoinTime(groupSelfInfo.getJoinTime());
            for (TIMGroupReceiveMessageOpt tIMGroupReceiveMessageOpt : TIMGroupReceiveMessageOpt.values()) {
                if (tIMGroupReceiveMessageOpt.getValue() == groupSelfInfo.getDwMsgFalg()) {
                    setRecvMsgOption(tIMGroupReceiveMessageOpt);
                }
            }
        }
    }

    public long getJoinTime() {
        return this.joinTime;
    }

    public TIMGroupReceiveMessageOpt getRecvMsgOption() {
        return this.recvMsgOption;
    }

    public TIMGroupMemberRoleType getRole() {
        return this.role;
    }

    void setJoinTime(long j) {
        this.joinTime = j;
    }

    void setRecvMsgOption(TIMGroupReceiveMessageOpt tIMGroupReceiveMessageOpt) {
        this.recvMsgOption = tIMGroupReceiveMessageOpt;
    }

    void setRole(long j) {
        for (TIMGroupMemberRoleType tIMGroupMemberRoleType : TIMGroupMemberRoleType.values()) {
            if (j == tIMGroupMemberRoleType.getValue()) {
                this.role = tIMGroupMemberRoleType;
                return;
            }
        }
        this.role = TIMGroupMemberRoleType.NotMember;
    }
}
