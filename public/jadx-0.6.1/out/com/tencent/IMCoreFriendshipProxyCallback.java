package com.tencent;

import com.tencent.imcore.AddFriendReqVec;
import com.tencent.imcore.FriendProfileVec;
import com.tencent.imcore.FriendProxyStatus;
import com.tencent.imcore.IFriendshipProxyListener;
import com.tencent.imcore.StrVec;
import com.tencent.imsdk.IMMsfCoreProxy;
import java.util.ArrayList;
import java.util.List;

public class IMCoreFriendshipProxyCallback extends IFriendshipProxyListener {
    String identify;

    public IMCoreFriendshipProxyCallback(String str) {
        this.identify = str;
        swigReleaseOwnership();
    }

    public void onAddFriendNotify(FriendProfileVec friendProfileVec) {
        if (friendProfileVec != null) {
            long size = friendProfileVec.size();
            if (size > 0 && TIMManager.getInstanceById(this.identify).getFriendshipProxyListener() != null) {
                List arrayList = new ArrayList();
                for (int i = 0; ((long) i) < size; i++) {
                    arrayList.add(new TIMUserProfile(friendProfileVec.get(i)));
                }
                IMMsfCoreProxy.mainHandler.post(new ag(this, arrayList));
            }
        }
    }

    public void onAddFriendReq(AddFriendReqVec addFriendReqVec) {
        if (addFriendReqVec != null) {
            long size = addFriendReqVec.size();
            if (size > 0 && TIMManager.getInstanceById(this.identify).getFriendshipProxyListener() != null) {
                List arrayList = new ArrayList();
                for (int i = 0; ((long) i) < size; i++) {
                    arrayList.add(new TIMSNSChangeInfo(addFriendReqVec.get(i)));
                }
                IMMsfCoreProxy.mainHandler.post(new aj(this, arrayList));
            }
        }
    }

    public void onDeleteFriendNotify(StrVec strVec) {
        if (strVec != null) {
            long size = strVec.size();
            if (size > 0 && TIMManager.getInstanceById(this.identify).getFriendshipProxyListener() != null) {
                List arrayList = new ArrayList();
                for (int i = 0; ((long) i) < size; i++) {
                    arrayList.add(strVec.get(i));
                }
                IMMsfCoreProxy.mainHandler.post(new ah(this, arrayList));
            }
        }
    }

    public void onFriendProfileUpdate(FriendProfileVec friendProfileVec) {
        if (friendProfileVec != null) {
            long size = friendProfileVec.size();
            if (size > 0 && TIMManager.getInstanceById(this.identify).getFriendshipProxyListener() != null) {
                List arrayList = new ArrayList();
                for (int i = 0; ((long) i) < size; i++) {
                    arrayList.add(new TIMUserProfile(friendProfileVec.get(i)));
                }
                IMMsfCoreProxy.mainHandler.post(new ai(this, arrayList));
            }
        }
    }

    public void onProxyStatusChange(FriendProxyStatus friendProxyStatus) {
        if (friendProxyStatus != null && TIMManager.getInstanceById(this.identify).getFriendshipProxyListener() != null) {
            TIMFriendshipProxyStatus tIMFriendshipProxyStatus = TIMFriendshipProxyStatus.TIM_FRIENDSHIP_STATUS_NONE;
            TIMFriendshipProxyStatus[] values = TIMFriendshipProxyStatus.values();
            int length = values.length;
            int i = 0;
            while (i < length) {
                TIMFriendshipProxyStatus tIMFriendshipProxyStatus2 = values[i];
                if (tIMFriendshipProxyStatus2.getStatus() != friendProxyStatus.swigValue()) {
                    tIMFriendshipProxyStatus2 = tIMFriendshipProxyStatus;
                }
                i++;
                tIMFriendshipProxyStatus = tIMFriendshipProxyStatus2;
            }
            IMMsfCoreProxy.mainHandler.post(new af(this, tIMFriendshipProxyStatus));
        }
    }
}
