package com.tencent;

import com.tencent.imsdk.QLog;
import java.util.List;

final class cl extends af<TIMUserProfile> {
    cl(TIMFriendshipManager tIMFriendshipManager, TIMValueCallBack tIMValueCallBack) {
        super(tIMFriendshipManager, tIMValueCallBack);
    }

    public final void a(int i, String str) {
        this.a.onError(i, str);
    }

    public final void a(List<TIMUserProfile> list) {
        TIMUserProfile tIMUserProfile = (TIMUserProfile) list.get(0);
        QLog.i("TIMFriendshipManager", 1, "get self profile: " + tIMUserProfile.getIdentifier() + "nick: " + tIMUserProfile.getNickName() + " face url: " + tIMUserProfile.getFaceUrl());
        this.a.onSuccess(tIMUserProfile);
    }
}
