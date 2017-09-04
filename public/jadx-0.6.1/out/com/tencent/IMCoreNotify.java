package com.tencent;

import com.tencent.imcore.INotify;
import com.tencent.imcore.Msg;
import com.tencent.imcore.MsgReceiptVec;
import com.tencent.imcore.MsgVec;
import com.tencent.imcore.SessionUUIDVec;
import com.tencent.imcore.UserStatus;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.QLog;
import java.util.ArrayList;
import java.util.List;

class IMCoreNotify extends INotify {
    private String a;

    public IMCoreNotify(String str) {
        this.a = str;
        swigReleaseOwnership();
    }

    public void onMsgEvent(MsgVec msgVec) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; ((long) i) < msgVec.size(); i++) {
            arrayList.add(new TIMMessage(msgVec.get(i)));
        }
        IMMsfCoreProxy.mainHandler.post(new aw(this, arrayList));
    }

    public void onMsgUpdate(MsgVec msgVec) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; ((long) i) < msgVec.size(); i++) {
            arrayList.add(new TIMMessage(msgVec.get(i)));
        }
        IMMsfCoreProxy.mainHandler.post(new ax(this, arrayList));
    }

    public void onRecvMsgReceipt(MsgReceiptVec msgReceiptVec) {
        TIMMessageReceiptListener messageReceiptListener = TIMManager.getInstanceById(this.a).getMessageReceiptListener();
        if (messageReceiptListener == null) {
            QLog.d("IMCoreNotify", 1, "onRecvMsgReceipt, no listener");
            return;
        }
        List arrayList = new ArrayList();
        if (msgReceiptVec.size() > 0) {
            for (int i = 0; ((long) i) < msgReceiptVec.size(); i++) {
                TIMMessageReceipt convertFrom = TIMMessageReceipt.convertFrom(msgReceiptVec.get(i));
                if (convertFrom != null) {
                    arrayList.add(convertFrom);
                }
            }
        }
        IMMsfCoreProxy.mainHandler.post(new bc(this, messageReceiptListener, arrayList));
    }

    public void onRefresh() {
        TIMRefreshListener refreshListener = TIMManager.getInstanceById(this.a).getRefreshListener();
        if (refreshListener != null) {
            IMMsfCoreProxy.mainHandler.post(new ba(this, refreshListener));
        }
    }

    public void onRefreshConversation(SessionUUIDVec sessionUUIDVec) {
        TIMRefreshListener refreshListener = TIMManager.getInstanceById(this.a).getRefreshListener();
        if (refreshListener != null) {
            List arrayList = new ArrayList();
            if (sessionUUIDVec.size() > 0) {
                for (int i = 0; ((long) i) < sessionUUIDVec.size(); i++) {
                    TIMConversation tIMConversation = new TIMConversation(this.a);
                    tIMConversation.setType(TIMConversationType.getType(sessionUUIDVec.get(i).getType()));
                    tIMConversation.setPeer(sessionUUIDVec.get(i).getSid());
                    arrayList.add(tIMConversation);
                }
            }
            IMMsfCoreProxy.mainHandler.post(new bb(this, refreshListener, arrayList));
        }
    }

    public void onUploadProgress(Msg msg, int i, int i2, int i3) {
        TIMUploadProgressListener uploadProgressListener = TIMManager.getInstanceById(this.a).getUploadProgressListener();
        if (uploadProgressListener != null) {
            if (msg == null) {
                aa.a().a(new ay(this, uploadProgressListener, i2, i3));
                return;
            }
            aa.a().a(new az(this, uploadProgressListener, new TIMMessage(msg), i, i2, i3));
        }
    }

    public void onUserStatusChanged(UserStatus userStatus) {
        TIMUserDefinedStatusListener userDefinedStatusListener = TIMManager.getInstanceById(this.a).getUserDefinedStatusListener();
        if (userDefinedStatusListener == null) {
            QLog.d("IMCoreNotify", 1, "onUserStatusChanged, no listener");
            return;
        }
        IMMsfCoreProxy.mainHandler.post(new bd(this, userDefinedStatusListener, new TIMUserDefinedStatus(userStatus)));
    }
}
