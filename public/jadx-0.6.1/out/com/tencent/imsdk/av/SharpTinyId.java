package com.tencent.imsdk.av;

import com.tencent.TIMCallBack;
import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.IMMsfUserInfo;
import com.tencent.imsdk.QLog;
import com.tencent.mobileqq.pb.ByteStringMicro;
import com.tencent.openqq.IMPushListener;
import com.tencent.openqq.IMReqListener;
import com.tencent.openqq.protocol.imsdk.general.ReqBody;

public class SharpTinyId {
    static SharpTinyId inst = new SharpTinyId();
    private static final String tag = "MSF.C.SharpTinyId";

    private SharpTinyId() {
    }

    public static SharpTinyId get() {
        return inst;
    }

    public TIMValueCallBack<byte[]> getSharpSvrPushCallBack() {
        QLog.i(tag, 1, "sharp|getSharpSvrPushCallBack");
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo != null) {
            return (TIMValueCallBack) anyOnLineMsfUserInfo.getCmd2PushCallBack().get("im_open_push.msg_push_734");
        }
        QLog.e(tag, 1, "sharp|setSharpSvrRspListener no user online");
        return null;
    }

    public IMPushListener getSharpSvrPushListener() {
        QLog.i(tag, 1, "sharp|getSharpSvrPushListener");
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo != null) {
            return (IMPushListener) anyOnLineMsfUserInfo.getCmd2PushListener().get("im_open_push.msg_push_734");
        }
        QLog.e(tag, 1, "sharp|getSharpSvrPushListener no user online");
        return null;
    }

    public TIMValueCallBack<byte[]> getSharpSvrRspCallBack() {
        QLog.i(tag, 1, "sharp|getSharpSvrRspCallBack");
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo != null) {
            return (TIMValueCallBack) anyOnLineMsfUserInfo.getCmd2PushCallBack().get("OnlinePush.SharpSvr.c2sack_734");
        }
        QLog.e(tag, 1, "sharp|setSharpSvrRspListener no user online");
        return null;
    }

    public IMPushListener getSharpSvrRspListener() {
        QLog.i(tag, 1, "sharp|getSharpSvrRspListener");
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo != null) {
            return (IMPushListener) anyOnLineMsfUserInfo.getCmd2PushListener().get("OnlinePush.SharpSvr.c2sack_734");
        }
        QLog.e(tag, 1, "sharp|getSharpSvrRspListener no user online");
        return null;
    }

    public void requestSharpSvr(byte b, long j, byte[] bArr, TIMCallBack tIMCallBack, long j2) {
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo == null) {
            QLog.e(tag, 1, "sharp|req failed no user online");
            return;
        }
        ReqBody reqBody = new ReqBody();
        reqBody.msg_msg.setHasFlag(true);
        reqBody.msg_msg.msg_msg_head.setHasFlag(true);
        reqBody.msg_msg.msg_msg_head.msg_content_head.setHasFlag(true);
        reqBody.msg_msg.msg_msg_head.msg_content_head.uint32_type.set(561);
        reqBody.msg_msg.msg_msg_head.msg_content_head.uint32_subtype.set(0);
        reqBody.msg_msg.msg_msg_head.msg_content_head.uint32_seq.set(IMMsfCoreProxy.get().msgSeq.incrementAndGet() & 65535);
        reqBody.msg_msg.msg_msg_head.msg_content_head.uint32_rand.set(IMMsfCoreProxy.get().random.nextInt());
        reqBody.msg_msg.msg_msg_head.msg_content_head.uint32_client_time.set((int) (System.currentTimeMillis() / 1000));
        reqBody.msg_msg.msg_msg_head.msg_routine_head.setHasFlag(true);
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_c2c_info.setHasFlag(true);
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_c2c_info.enum_from_uin_type.set(5);
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_c2c_info.enum_to_uin_type.set(5);
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_c2c_info.uint64_from_uin.set(anyOnLineMsfUserInfo.getTinyid());
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_c2c_info.uint64_to_uin.set(j);
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_inst_ctrl.setHasFlag(true);
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_inst_ctrl.msg_from_inst.setHasFlag(true);
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_inst_ctrl.msg_from_inst.uint32_productid.set(IMMsfCoreProxy.get().getSdkAppId());
        reqBody.msg_msg.msg_msg_body.setHasFlag(true);
        reqBody.msg_msg.msg_msg_body.msg_content.setHasFlag(true);
        reqBody.msg_msg.msg_msg_body.msg_content.set(ByteStringMicro.copyFrom(bArr));
        IMMsfCoreProxy.get().request(anyOnLineMsfUserInfo.getUserId(), "im_open_msg.sharp", reqBody.toByteArray(), new ad(this, tIMCallBack), j2);
    }

    public void requestSharpSvr(byte b, long j, byte[] bArr, IMReqListener iMReqListener) {
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo == null) {
            QLog.w(tag, 1, "sharp|request srv ont online");
            anyOnLineMsfUserInfo = new IMMsfUserInfo();
        }
        ReqBody reqBody = new ReqBody();
        reqBody.msg_msg.setHasFlag(true);
        reqBody.msg_msg.msg_msg_head.setHasFlag(true);
        reqBody.msg_msg.msg_msg_head.msg_content_head.setHasFlag(true);
        reqBody.msg_msg.msg_msg_head.msg_content_head.uint32_type.set(561);
        reqBody.msg_msg.msg_msg_head.msg_content_head.uint32_subtype.set(0);
        reqBody.msg_msg.msg_msg_head.msg_content_head.uint32_seq.set(IMMsfCoreProxy.get().msgSeq.incrementAndGet() & 65535);
        reqBody.msg_msg.msg_msg_head.msg_content_head.uint32_rand.set(IMMsfCoreProxy.get().random.nextInt());
        reqBody.msg_msg.msg_msg_head.msg_content_head.uint32_client_time.set((int) (System.currentTimeMillis() / 1000));
        reqBody.msg_msg.msg_msg_head.msg_routine_head.setHasFlag(true);
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_c2c_info.setHasFlag(true);
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_c2c_info.enum_from_uin_type.set(5);
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_c2c_info.enum_to_uin_type.set(5);
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_c2c_info.uint64_from_uin.set(anyOnLineMsfUserInfo.getTinyid());
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_c2c_info.uint64_to_uin.set(j);
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_inst_ctrl.setHasFlag(true);
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_inst_ctrl.msg_from_inst.setHasFlag(true);
        reqBody.msg_msg.msg_msg_head.msg_routine_head.msg_inst_ctrl.msg_from_inst.uint32_productid.set(IMMsfCoreProxy.get().getSdkAppId());
        reqBody.msg_msg.msg_msg_body.setHasFlag(true);
        reqBody.msg_msg.msg_msg_body.msg_content.setHasFlag(true);
        reqBody.msg_msg.msg_msg_body.msg_content.set(ByteStringMicro.copyFrom(bArr));
        IMMsfCoreProxy.get().request(anyOnLineMsfUserInfo.getUserId(), "im_open_msg.sharp", reqBody.toByteArray(), new ac(this, iMReqListener));
    }

    public void requestSharpSvr(long j, byte[] bArr, TIMCallBack tIMCallBack) {
        QLog.i(tag, 1, "sharp|requestSharpSvr");
        requestSharpSvr((byte) 1, j, bArr, tIMCallBack, 0);
    }

    public void requestSharpSvr(long j, byte[] bArr, TIMCallBack tIMCallBack, long j2) {
        QLog.i(tag, 1, "sharp|requestSharpSvr");
        requestSharpSvr((byte) 1, j, bArr, tIMCallBack, j2);
    }

    public void requestSharpSvr(long j, byte[] bArr, IMReqListener iMReqListener) {
        QLog.i(tag, 1, "sharp|requestSharpSvr");
        requestSharpSvr((byte) 1, j, bArr, iMReqListener);
    }

    public void responseSharpSvr(long j, byte[] bArr, TIMCallBack tIMCallBack) {
        QLog.i(tag, 1, "sharp|responseSharpSvr");
        requestSharpSvr((byte) 4, j, bArr, tIMCallBack, 0);
    }

    public void responseSharpSvr(long j, byte[] bArr, TIMCallBack tIMCallBack, long j2) {
        QLog.i(tag, 1, "sharp|responseSharpSvr");
        requestSharpSvr((byte) 4, j, bArr, tIMCallBack, j2);
    }

    public void responseSharpSvr(long j, byte[] bArr, IMReqListener iMReqListener) {
        QLog.i(tag, 1, "sharp|responseSharpSvr");
        requestSharpSvr((byte) 4, j, bArr, iMReqListener);
    }

    public void setSharpSvrPushCallBack(TIMValueCallBack<byte[]> tIMValueCallBack) {
        QLog.i(tag, 1, "sharp|setSharpSvrPushCallBack");
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo == null) {
            QLog.e(tag, 1, "sharp|setSharpSvrRspListener no user online");
        } else {
            anyOnLineMsfUserInfo.setPushCallBack("im_open_push.msg_push_734", tIMValueCallBack);
        }
    }

    public void setSharpSvrPushListener(IMPushListener iMPushListener) {
        QLog.i(tag, 1, "sharp|setSharpSvrPushListener");
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo == null) {
            QLog.e(tag, 1, "sharp|setSharpSvrPushListener no user online");
        } else {
            anyOnLineMsfUserInfo.setPushListener("im_open_push.msg_push_734", iMPushListener);
        }
    }

    public void setSharpSvrRspCallBack(TIMValueCallBack<byte[]> tIMValueCallBack) {
        QLog.i(tag, 1, "sharp|setSharpSvrRspCallBack");
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo == null) {
            QLog.e(tag, 1, "sharp|setSharpSvrRspListener no user online");
        } else {
            anyOnLineMsfUserInfo.setPushCallBack("OnlinePush.SharpSvr.c2sack_734", tIMValueCallBack);
        }
    }

    public void setSharpSvrRspListener(IMPushListener iMPushListener) {
        QLog.i(tag, 1, "sharp|setSharpSvrRspListener");
        IMMsfUserInfo anyOnLineMsfUserInfo = IMMsfCoreProxy.get().getAnyOnLineMsfUserInfo();
        if (anyOnLineMsfUserInfo == null) {
            QLog.e(tag, 1, "sharp|setSharpSvrRspListener no user online");
        } else {
            anyOnLineMsfUserInfo.setPushListener("OnlinePush.SharpSvr.c2sack_734", iMPushListener);
        }
    }
}
