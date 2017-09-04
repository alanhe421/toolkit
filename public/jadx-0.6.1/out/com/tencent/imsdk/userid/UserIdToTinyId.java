package com.tencent.imsdk.userid;

import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.QLog;
import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.openqq.IMCmdListener;
import com.tencent.openqq.IMError;
import com.tencent.openqq.IMUserId;
import com.tencent.openqq.IMUserIdListener;
import com.tencent.openqq.protocol.imsdk.im_open_common;
import com.tencent.openqq.protocol.imsdk.userid_to_tinyid.ReqBody;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class UserIdToTinyId {
    static UserIdToTinyId inst = new UserIdToTinyId();
    private static final String tag = "MSF.C.UserIdToTinyId";
    ConcurrentHashMap<IMUserId, Long> userIdToTinyId = new ConcurrentHashMap();

    private UserIdToTinyId() {
    }

    public static UserIdToTinyId get() {
        return inst;
    }

    private void localUserIdToTinyId(List<IMUserId> list, List<IMUserId> list2) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            IMUserId iMUserId = (IMUserId) it.next();
            if (this.userIdToTinyId.containsKey(iMUserId)) {
                IMUserId iMUserId2 = new IMUserId(iMUserId);
                iMUserId2.setTinyId(((Long) this.userIdToTinyId.get(iMUserId)).longValue());
                list2.add(iMUserId2);
                it.remove();
            }
        }
    }

    public void userIdToTinyId(List<IMUserId> list, IMUserIdListener iMUserIdListener) {
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo == null || !anyOnLineMsfUserInfo.isLoggedIn()) {
            iMUserIdListener.onError(IMError.FAIL, "not logged in");
            return;
        }
        IMCmdListener adVar = new ad(this, iMUserIdListener);
        List<IMUserId> arrayList = new ArrayList();
        adVar.setUserIdList(arrayList);
        localUserIdToTinyId(list, arrayList);
        if (list.isEmpty()) {
            for (IMUserId iMUserId : arrayList) {
                QLog.d(tag, 1, "local get userid: " + iMUserId + "|tinyid: " + iMUserId.getTinyId());
            }
            iMUserIdListener.onSucc(arrayList);
            return;
        }
        ReqBody reqBody = new ReqBody();
        for (IMUserId iMUserId2 : list) {
            MessageMicro iMUserId3 = new im_open_common.IMUserId();
            iMUserId3.uidtype.set(ByteStringMicro.copyFromUtf8(iMUserId2.getUidType()));
            iMUserId3.userappid.set(iMUserId2.getUserAppId());
            iMUserId3.userid.set(ByteStringMicro.copyFromUtf8(iMUserId2.getUserId()));
            reqBody.userid.add(iMUserId3);
        }
        IMMsfCoreProxy.get().request(anyOnLineMsfUserInfo.getUserId(), IMMsfCoreProxy.get().getSdkType() + ".pbuseridtotinyid", reqBody.toByteArray(), adVar);
    }
}
