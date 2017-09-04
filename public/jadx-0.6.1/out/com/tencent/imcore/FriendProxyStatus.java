package com.tencent.imcore;

public enum FriendProxyStatus {
    kFriendProxyStatusNone((String) 0),
    kFriendProxyStatusSyncing((String) 1),
    kFriendProxyStatusSynced((String) 2),
    kFriendProxyStatusSyncFailed((String) 3);
    
    private final int swigValue;

    private FriendProxyStatus(int i) {
        this.swigValue = i;
        aa.a(i + 1);
    }

    private FriendProxyStatus(FriendProxyStatus friendProxyStatus) {
        this.swigValue = friendProxyStatus.swigValue;
        aa.a(this.swigValue + 1);
    }

    public static FriendProxyStatus swigToEnum(int i) {
        FriendProxyStatus[] friendProxyStatusArr = (FriendProxyStatus[]) FriendProxyStatus.class.getEnumConstants();
        if (i < friendProxyStatusArr.length && i >= 0 && friendProxyStatusArr[i].swigValue == i) {
            return friendProxyStatusArr[i];
        }
        for (FriendProxyStatus friendProxyStatus : friendProxyStatusArr) {
            if (friendProxyStatus.swigValue == i) {
                return friendProxyStatus;
            }
        }
        throw new IllegalArgumentException("No enum " + FriendProxyStatus.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
