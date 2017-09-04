package com.tencent;

import com.tencent.imsdk.QLog;
import java.util.List;

final class co extends af<List<TIMUserProfile>> {
    co(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMFriendshipManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(List<TIMUserProfile> list) {
        for (TIMUserProfile tIMUserProfile : list) {
            QLog.i("TIMFriendshipManager", 1, "get friend profile: " + tIMUserProfile.getIdentifier() + "nick: " + tIMUserProfile.getNickName() + " face url: " + tIMUserProfile.getFaceUrl());
        }
        this.a.onSuccess(list);
    }
}
