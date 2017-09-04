package com.tencent;

import java.util.List;

public interface TIMFriendshipProxyListener {
    void OnAddFriendGroups(List<TIMFriendGroup> list);

    void OnAddFriendReqs(List<TIMSNSChangeInfo> list);

    void OnAddFriends(List<TIMUserProfile> list);

    void OnDelFriendGroups(List<String> list);

    void OnDelFriends(List<String> list);

    void OnFriendGroupUpdate(List<TIMFriendGroup> list);

    void OnFriendProfileUpdate(List<TIMUserProfile> list);

    void OnProxyStatusChange(TIMFriendshipProxyStatus tIMFriendshipProxyStatus);
}
