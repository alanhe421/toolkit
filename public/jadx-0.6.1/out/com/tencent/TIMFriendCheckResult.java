package com.tencent;

public class TIMFriendCheckResult {
    String identifier;
    String relationType;
    long status;

    TIMFriendCheckResult(TIMFriendResult tIMFriendResult) {
        this.identifier = tIMFriendResult.getIdentifer();
        this.status = tIMFriendResult.getStatus().getValue();
        this.relationType = tIMFriendResult.getRelationType();
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public TIMFriendRelationType getRelationType() {
        for (TIMFriendRelationType tIMFriendRelationType : TIMFriendRelationType.values()) {
            if (this.relationType.equals(tIMFriendRelationType.getValue())) {
                return tIMFriendRelationType;
            }
        }
        return TIMFriendRelationType.TIM_FRIEND_RELATION_TYPE_NONE;
    }

    public TIMFriendStatus getStatus() {
        for (TIMFriendStatus tIMFriendStatus : TIMFriendStatus.values()) {
            if (this.status == tIMFriendStatus.getValue()) {
                return tIMFriendStatus;
            }
        }
        return TIMFriendStatus.TIM_FRIEND_STATUS_UNKNOWN;
    }

    void setIdentifier(String str) {
        this.identifier = str;
    }

    void setRelationType(String str) {
        this.relationType = str;
    }

    void setStatus(long j) {
        this.status = j;
    }
}
