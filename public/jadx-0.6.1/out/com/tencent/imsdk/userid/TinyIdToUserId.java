package com.tencent.imsdk.userid;

import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.QLog;
import com.tencent.openqq.IMCmdListener;
import com.tencent.openqq.IMError;
import com.tencent.openqq.IMUserId;
import com.tencent.openqq.IMUserIdListener;
import com.tencent.openqq.protocol.imsdk.tinyid_to_userid.ReqBody;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TinyIdToUserId {
    static TinyIdToUserId inst = new TinyIdToUserId();
    private static final String tag = "MSF.C.TinyIdToUserId";
    ConcurrentHashMap<Long, IMUserId> tinyIdToUserId = new ConcurrentHashMap();

    private TinyIdToUserId() {
    }

    public static TinyIdToUserId get() {
        return inst;
    }

    private void localTinyIdToUserId(List<Long> list, List<IMUserId> list2) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Long l = (Long) it.next();
            if (this.tinyIdToUserId.containsKey(l)) {
                IMUserId iMUserId = new IMUserId((IMUserId) this.tinyIdToUserId.get(l));
                iMUserId.setTinyId(l.longValue());
                list2.add(iMUserId);
                it.remove();
            }
        }
    }

    public void tinyIdToUserId(List<Long> list, IMUserIdListener iMUserIdListener) {
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo == null || !anyOnLineMsfUserInfo.isLoggedIn()) {
            iMUserIdListener.onError(IMError.FAIL, "not logged in");
            return;
        }
        IMCmdListener acVar = new ac(this, iMUserIdListener);
        List<IMUserId> arrayList = new ArrayList();
        acVar.setUserIdList(arrayList);
        localTinyIdToUserId(list, arrayList);
        if (list.isEmpty()) {
            for (IMUserId iMUserId : arrayList) {
                QLog.d(tag, 1, "local get userid: " + iMUserId + "|tinyid: " + iMUserId.getTinyId());
            }
            iMUserIdListener.onSucc(arrayList);
            return;
        }
        ReqBody reqBody = new ReqBody();
        for (Long add : list) {
            reqBody.tinyid.add(add);
        }
        IMMsfCoreProxy.get().request(anyOnLineMsfUserInfo.getUserId(), IMMsfCoreProxy.get().getSdkType() + ".pbtinyidtouserid", reqBody.toByteArray(), acVar);
    }
}
