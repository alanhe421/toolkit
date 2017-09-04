package com.tencent;

import java.util.List;

public class TIMGetFriendListV2Succ {
    private List<TIMUserProfile> friends;
    private TIMFriendMetaInfo metaInfo;

    public List<TIMUserProfile> getFriends() {
        return this.friends;
    }

    public TIMFriendMetaInfo getMetaInfo() {
        return this.metaInfo;
    }

    void setFriends(List<TIMUserProfile> list) {
        this.friends = list;
    }

    void setMetaInfo(TIMFriendMetaInfo tIMFriendMetaInfo) {
        this.metaInfo = tIMFriendMetaInfo;
    }
}
