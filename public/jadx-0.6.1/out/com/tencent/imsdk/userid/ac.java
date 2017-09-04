package com.tencent.imsdk.userid;

import com.tencent.IMFunc;
import com.tencent.imsdk.QLog;
import com.tencent.imsdk.WrapCmdListener;
import com.tencent.openqq.IMError;
import com.tencent.openqq.IMUserId;
import com.tencent.openqq.IMUserIdListener;
import com.tencent.openqq.protocol.imsdk.im_open_common;
import com.tencent.openqq.protocol.imsdk.tinyid_to_userid.RspBody;
import java.util.List;

final class ac extends WrapCmdListener<IMUserIdListener> {
    ac(TinyIdToUserId tinyIdToUserId, IMUserIdListener iMUserIdListener) {
        super(iMUserIdListener);
    }

    public final void onError(IMError iMError, String str) {
        ((IMUserIdListener) this.listener).onError(iMError, str);
    }

    public final void onSucc(byte[] bArr) {
        RspBody rspBody = new RspBody();
        try {
            rspBody.mergeFrom(bArr);
            List<IMUserId> userIdList = getUserIdList();
            for (im_open_common.IMUserId iMUserId : rspBody.userid.get()) {
                IMUserId iMUserId2 = new IMUserId();
                iMUserId2.setUidType(iMUserId.uidtype.get().toStringUtf8());
                iMUserId2.setUserAppId(iMUserId.userappid.get());
                iMUserId2.setUserId(iMUserId.userid.get().toStringUtf8());
                iMUserId2.setTinyId(iMUserId.tinyid.get());
                userIdList.add(iMUserId2);
                UserIdToTinyId.get().userIdToTinyId.put(iMUserId2, Long.valueOf(iMUserId2.getTinyId()));
                TinyIdToUserId.get().tinyIdToUserId.put(Long.valueOf(iMUserId2.getTinyId()), iMUserId2);
            }
            for (IMUserId iMUserId3 : userIdList) {
                QLog.d("MSF.C.TinyIdToUserId", 1, "get userid: " + iMUserId3 + "|tinyid: " + iMUserId3.getTinyId());
            }
            ((IMUserIdListener) this.listener).onSucc(userIdList);
        } catch (Throwable th) {
            QLog.e("MSF.C.TinyIdToUserId", 1, IMFunc.getExceptionInfo(th));
        }
    }
}
