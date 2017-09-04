package com.tencent.openqq;

import android.content.Context;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.QLog;
import com.tencent.imsdk.av.MultiVideoTinyId;
import com.tencent.imsdk.av.SharpTinyId;
import com.tencent.imsdk.userid.TinyIdToUserId;
import com.tencent.imsdk.userid.UserIdToTinyId;
import java.util.List;

public class IMSdkInt extends IMSdkBase {
    static IMSdkInt sdkint = new IMSdkInt();
    private static final String tag = "openqq.IMSdkInt";

    private IMSdkInt() {
    }

    public static IMSdkInt get() {
        return sdkint;
    }

    public long getTinyId() {
        IMMsfUserInfo anyOnLineMsfUserInfo = msfCoreProxy.getAnyOnLineMsfUserInfo();
        return anyOnLineMsfUserInfo != null ? anyOnLineMsfUserInfo.getTinyid() : 0;
    }

    public long getUin() {
        return getTinyId();
    }

    public /* bridge */ /* synthetic */ void init(Context context) {
        super.init(context);
    }

    public void request(String str, byte[] bArr, IMCmdListener iMCmdListener) {
        IMMsfUserInfo anyOnLineMsfUserInfo = msfCoreProxy.getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo == null) {
            QLog.e(tag, 1, "imsdkint|request no user online!");
        } else {
            msfCoreProxy.request(anyOnLineMsfUserInfo.getUserId(), str, bArr, iMCmdListener);
        }
    }

    public void requestMultiVideoApp(byte[] bArr, IMCmdListener iMCmdListener) {
        MultiVideoTinyId.get().requestMultiVideoApp(bArr, iMCmdListener);
    }

    public void requestMultiVideoInfo(byte[] bArr, IMCmdListener iMCmdListener) {
        MultiVideoTinyId.get().requestMultiVideoInfo(bArr, iMCmdListener);
    }

    public void requestSharpSvr(long j, byte[] bArr, IMReqListener iMReqListener) {
        if (msfCoreProxy.getSdkType().equals("openim")) {
            SharpTinyId.get().requestSharpSvr(j, bArr, iMReqListener);
        } else {
            msfCoreProxy.getSdkType().equals("openqq");
        }
    }

    public void responseSharpSvr(long j, byte[] bArr, IMReqListener iMReqListener) {
        if (msfCoreProxy.getSdkType().equals("openim")) {
            SharpTinyId.get().requestSharpSvr(j, bArr, iMReqListener);
        } else {
            msfCoreProxy.getSdkType().equals("openqq");
        }
    }

    public /* bridge */ /* synthetic */ void setEnv(int i) {
        super.setEnv(i);
    }

    public void setReqTimeout(long j) {
        msfCoreProxy.setReqTimeout(j);
    }

    public /* bridge */ /* synthetic */ void setSdkType(String str) {
        super.setSdkType(str);
    }

    public void setSharpSvrPushListener(IMPushListener iMPushListener) {
        if (msfCoreProxy.getSdkType().equals("openim")) {
            SharpTinyId.get().setSharpSvrPushListener(iMPushListener);
        } else {
            msfCoreProxy.getSdkType().equals("openqq");
        }
    }

    public void setSharpSvrRspListener(IMPushListener iMPushListener) {
        if (msfCoreProxy.getSdkType().equals("openim")) {
            SharpTinyId.get().setSharpSvrRspListener(iMPushListener);
        } else {
            msfCoreProxy.getSdkType().equals("openqq");
        }
    }

    public void tinyIdToUserId(List<Long> list, IMUserIdListener iMUserIdListener) {
        if (msfCoreProxy.getSdkType().equals("openim")) {
            TinyIdToUserId.get().tinyIdToUserId(list, iMUserIdListener);
        }
    }

    public void userIdToTinyId(List<IMUserId> list, IMUserIdListener iMUserIdListener) {
        if (msfCoreProxy.getSdkType().equals("openim")) {
            UserIdToTinyId.get().userIdToTinyId(list, iMUserIdListener);
        }
    }
}
