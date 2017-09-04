package com.tencent.imsdk.userid;

import com.tencent.TIMUser;
import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.QLog;
import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.mobileqq.pb.MessageMicro;
import com.tencent.openqq.protocol.imsdk.im_open_common.IMUserId;
import com.tencent.openqq.protocol.imsdk.userid_to_tinyid.ReqBody;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TIMUserIdToTinyId {
    static TIMUserIdToTinyId inst = new TIMUserIdToTinyId();
    private static final String tag = "MSF.C.UserIdToTinyId";
    ConcurrentHashMap<TIMUser, Long> userIdToTinyId = new ConcurrentHashMap();

    private TIMUserIdToTinyId() {
    }

    public static TIMUserIdToTinyId get() {
        return inst;
    }

    private void localUserIdToTinyId(List<TIMUser> list, List<TIMUser> list2) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            TIMUser tIMUser = (TIMUser) it.next();
            if (this.userIdToTinyId.containsKey(tIMUser)) {
                TIMUser tIMUser2 = new TIMUser(tIMUser);
                tIMUser2.setTinyId(((Long) this.userIdToTinyId.get(tIMUser)).longValue());
                list2.add(tIMUser2);
                it.remove();
            }
        }
    }

    public void userIdToTinyId(List<TIMUser> list, TIMValueCallBack<List<TIMUser>> tIMValueCallBack, long j) {
        if (tIMValueCallBack != null) {
            IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
            if (anyOnLineMsfUserInfo == null || !anyOnLineMsfUserInfo.isLoggedIn()) {
                tIMValueCallBack.onError(6014, "not logged in");
                return;
            }
            List<TIMUser> arrayList = new ArrayList();
            localUserIdToTinyId(list, arrayList);
            if (list.isEmpty()) {
                for (TIMUser tIMUser : arrayList) {
                    QLog.d(tag, 1, "local get userid: " + tIMUser + "|tinyid: " + tIMUser.getTinyId());
                }
                tIMValueCallBack.onSuccess(arrayList);
                return;
            }
            ReqBody reqBody = new ReqBody();
            for (TIMUser tIMUser2 : list) {
                MessageMicro iMUserId = new IMUserId();
                iMUserId.uidtype.set(ByteStringMicro.copyFromUtf8(IMMsfCoreProxy.get().getUidType()));
                iMUserId.userappid.set(IMMsfCoreProxy.get().getSdkAppId());
                iMUserId.userid.set(ByteStringMicro.copyFromUtf8(tIMUser2.getIdentifier()));
                reqBody.userid.add(iMUserId);
            }
            IMMsfCoreProxy.get().request(anyOnLineMsfUserInfo.getUserId(), "openim.pbuseridtotinyid", reqBody.toByteArray(), new ab(this, arrayList, tIMValueCallBack), j);
        }
    }
}
