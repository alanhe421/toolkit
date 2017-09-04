package com.tencent.imsdk.av;

import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.QLog;
import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.openqq.IMCmdListener;
import com.tencent.openqq.protocol.imsdk.relay.ReqBody;

public class MultiVideoTinyId {
    static MultiVideoTinyId inst = new MultiVideoTinyId();
    private static final String tag = "IMSdk.MultiVideoTinyId";

    private MultiVideoTinyId() {
    }

    public static MultiVideoTinyId get() {
        return inst;
    }

    public void requestMultiVideoApp(byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack) {
        QLog.i(tag, 1, "multivideo|requestMultiVideoApp");
        requestOpenImRelay(IMMsfCoreProxy.get().getSdkType() + ".pbvideoapp", bArr, tIMValueCallBack, 0);
    }

    public void requestMultiVideoApp(byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack, long j) {
        QLog.i(tag, 1, "multivideo|requestMultiVideoApp");
        requestOpenImRelay(IMMsfCoreProxy.get().getSdkType() + ".pbvideoapp", bArr, tIMValueCallBack, j);
    }

    public void requestMultiVideoApp(byte[] bArr, IMCmdListener iMCmdListener) {
        QLog.i(tag, 1, "multivideo|requestMultiVideoApp");
        requestOpenImRelay(IMMsfCoreProxy.get().getSdkType() + ".pbvideoapp", bArr, iMCmdListener);
    }

    public void requestMultiVideoInfo(byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack) {
        QLog.i(tag, 1, "multivideo|requestMultiVideoInfo");
        requestOpenImRelay(IMMsfCoreProxy.get().getSdkType() + ".pbvideoinfo", bArr, tIMValueCallBack, 0);
    }

    public void requestMultiVideoInfo(byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack, long j) {
        QLog.i(tag, 1, "multivideo|requestMultiVideoInfo");
        requestOpenImRelay(IMMsfCoreProxy.get().getSdkType() + ".pbvideoinfo", bArr, tIMValueCallBack, j);
    }

    public void requestMultiVideoInfo(byte[] bArr, IMCmdListener iMCmdListener) {
        QLog.i(tag, 1, "multivideo|requestMultiVideoInfo");
        requestOpenImRelay(IMMsfCoreProxy.get().getSdkType() + ".pbvideoinfo", bArr, iMCmdListener);
    }

    public void requestOpenImRelay(String str, byte[] bArr, TIMValueCallBack<byte[]> tIMValueCallBack, long j) {
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo == null) {
            QLog.w(tag, 1, "MutivideoTinyId|requestOpenImRelay no user online");
            anyOnLineMsfUserInfo = new IMMsfUserInfo();
        }
        ReqBody reqBody = new ReqBody();
        reqBody.reqbody.set(ByteStringMicro.copyFrom(bArr));
        reqBody.reqbody.setHasFlag(true);
        IMMsfCoreProxy.get().request(anyOnLineMsfUserInfo.getUserId(), str, reqBody.toByteArray(), new ab(this, tIMValueCallBack, str), j);
    }

    public void requestOpenImRelay(String str, byte[] bArr, IMCmdListener iMCmdListener) {
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo == null) {
            QLog.w(tag, 1, "MutivideoTinyId|requestOpenImRelay no user online");
            anyOnLineMsfUserInfo = new IMMsfUserInfo();
        }
        ReqBody reqBody = new ReqBody();
        reqBody.reqbody.set(ByteStringMicro.copyFrom(bArr));
        reqBody.reqbody.setHasFlag(true);
        IMMsfCoreProxy.get().request(anyOnLineMsfUserInfo.getUserId(), str, reqBody.toByteArray(), new aa(this, iMCmdListener, str));
    }
}
