package com.tencent.imsdk.userid;

import com.tencent.TIMUser;
import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.QLog;
import com.tencent.openqq.protocol.imsdk.tinyid_to_userid.ReqBody;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TIMTinyIdToUserId {
    static TIMTinyIdToUserId inst = new TIMTinyIdToUserId();
    private static final String tag = "MSF.C.TinyIdToUserId";
    ConcurrentHashMap<Long, TIMUser> tinyIdToUserId = new ConcurrentHashMap();

    private TIMTinyIdToUserId() {
    }

    public static TIMTinyIdToUserId get() {
        return inst;
    }

    private void localTinyIdToUserId(List<Long> list, List<TIMUser> list2) {
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Long l = (Long) it.next();
            if (this.tinyIdToUserId.containsKey(l)) {
                TIMUser tIMUser = new TIMUser((TIMUser) this.tinyIdToUserId.get(l));
                tIMUser.setTinyId(l.longValue());
                list2.add(tIMUser);
                it.remove();
            }
        }
    }

    public void tinyIdToUserId(List<Long> list, TIMValueCallBack<List<TIMUser>> tIMValueCallBack, long j) {
        if (tIMValueCallBack != null) {
            IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
            if (anyOnLineMsfUserInfo == null || !anyOnLineMsfUserInfo.isLoggedIn()) {
                tIMValueCallBack.onError(6014, "not logged in");
                return;
            }
            List<TIMUser> arrayList = new ArrayList();
            localTinyIdToUserId(list, arrayList);
            if (list.isEmpty()) {
                for (TIMUser tIMUser : arrayList) {
                    QLog.d(tag, 1, "local get userid: " + tIMUser + "|tinyid: " + tIMUser.getTinyId());
                }
                tIMValueCallBack.onSuccess(arrayList);
                return;
            }
            ReqBody reqBody = new ReqBody();
            for (Long add : list) {
                reqBody.tinyid.add(add);
            }
            IMMsfCoreProxy.get().request(anyOnLineMsfUserInfo.getUserId(), "openim.pbtinyidtouserid", reqBody.toByteArray(), new aa(this, arrayList, tIMValueCallBack), j);
        }
    }
}
