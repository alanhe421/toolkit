package com.tencent.imsdk.userid;

import com.tencent.IMFunc;
import com.tencent.TIMUser;
import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.QLog;
import com.tencent.openqq.protocol.imsdk.im_open_common.IMUserId;
import com.tencent.openqq.protocol.imsdk.userid_to_tinyid.RspBody;
import java.util.List;

final class ab implements TIMValueCallBack<byte[]> {
    private List<TIMUser> a;
    private /* synthetic */ TIMValueCallBack b;

    public ab(TIMUserIdToTinyId tIMUserIdToTinyId, List list, TIMValueCallBack tIMValueCallBack) {
        this.b = tIMValueCallBack;
        this.a = list;
    }

    public final void onError(int i, String str) {
        this.b.onError(i, str);
    }

    public final /* synthetic */ void onSuccess(Object obj) {
        byte[] bArr = (byte[]) obj;
        RspBody rspBody = new RspBody();
        try {
            rspBody.mergeFrom(bArr);
            List<TIMUser> list = this.a;
            for (IMUserId iMUserId : rspBody.userid.get()) {
                TIMUser tIMUser = new TIMUser();
                tIMUser.setAccountType(iMUserId.uidtype.get().toStringUtf8());
                tIMUser.setAppIdAt3rd(String.valueOf(IMMsfCoreProxy.get().getSdkAppId()));
                tIMUser.setIdentifier(iMUserId.userid.get().toStringUtf8());
                tIMUser.setTinyId(iMUserId.tinyid.get());
                list.add(tIMUser);
                TIMUserIdToTinyId.get().userIdToTinyId.put(tIMUser, Long.valueOf(tIMUser.getTinyId()));
                TIMTinyIdToUserId.get().tinyIdToUserId.put(Long.valueOf(tIMUser.getTinyId()), tIMUser);
            }
            for (TIMUser tIMUser2 : list) {
                QLog.d("MSF.C.UserIdToTinyId", 1, "get userid: " + tIMUser2 + "|tinyid: " + tIMUser2.getTinyId());
            }
            this.b.onSuccess(list);
        } catch (Throwable th) {
            QLog.e("MSF.C.UserIdToTinyId", 1, IMFunc.getExceptionInfo(th));
            this.b.onError(6001, "parse rsp failed");
        }
    }
}
