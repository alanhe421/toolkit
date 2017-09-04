package com.tencent;

import com.tencent.imcore.Draft;
import com.tencent.imcore.IDeleteLocalMsg;
import com.tencent.imcore.IGetMsgs;
import com.tencent.imcore.IMCore;
import com.tencent.imcore.ISendMsg;
import com.tencent.imcore.ISetReadMsg;
import com.tencent.imcore.Msg;
import com.tencent.imcore.MsgVec;
import com.tencent.imcore.QrEventType;
import com.tencent.imcore.Session;
import com.tencent.imcore.SessionType;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.QLog;
import com.tencent.imsdk.util.QualityReportHelper;
import com.tencent.statistics.BeaconEvents;
import com.tencent.statistics.BeaconUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TIMConversation {
    private static final String tag = "MSF.C.IMConversation";
    private String identifer;
    private String peer;
    private TIMConversationType type;

    abstract class aa extends IDeleteLocalMsg {
        public TIMCallBack a;

        public aa(TIMConversation tIMConversation, TIMCallBack tIMCallBack) {
            swigReleaseOwnership();
            this.a = tIMCallBack;
        }

        public abstract void a();

        public abstract void a(int i, String str);

        public void done() {
            IMMsfCoreProxy.mainHandler.post(new bv(this));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new bw(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class ab extends IGetMsgs {
        public TIMValueCallBack<List<TIMMessage>> a = null;

        ab(TIMConversation tIMConversation, TIMValueCallBack<List<TIMMessage>> tIMValueCallBack) {
            this.a = tIMValueCallBack;
            swigReleaseOwnership();
        }

        public final void done(MsgVec msgVec) {
            ArrayList arrayList = new ArrayList();
            if (msgVec == null) {
                QLog.i(TIMConversation.tag, 1, "getmsgs return null");
                IMMsfCoreProxy.mainHandler.post(new bx(this, arrayList));
                swigTakeOwnership();
                return;
            }
            for (int i = 0; ((long) i) < msgVec.size(); i++) {
                TIMMessage tIMMessage = new TIMMessage(msgVec.get(i));
                if (tIMMessage.getConversation().valid()) {
                    arrayList.add(tIMMessage);
                }
            }
            IMMsfCoreProxy.mainHandler.post(new by(this, arrayList));
            swigTakeOwnership();
        }

        public final void fail(int i, String str) {
            QLog.e(TIMConversation.tag, 1, "getmsgs failed");
            IMMsfCoreProxy.mainHandler.post(new bz(this, i, str));
            swigTakeOwnership();
        }
    }

    abstract class ac extends ISendMsg {
        public TIMValueCallBack<TIMMessage> a = null;
        TIMMessage b = null;
        private QualityReportHelper c;

        ac(TIMConversation tIMConversation, TIMMessage tIMMessage, TIMValueCallBack<TIMMessage> tIMValueCallBack, QualityReportHelper qualityReportHelper) {
            this.b = tIMMessage;
            this.a = tIMValueCallBack;
            this.c = qualityReportHelper;
            swigReleaseOwnership();
            long elementCount = tIMMessage.getElementCount();
            for (int i = 0; ((long) i) < elementCount; i++) {
                TIMElemType type = tIMMessage.getElement(i).getType();
                int[] iArr = bm.a;
                type.ordinal();
            }
        }

        public final void done() {
            if (this.a != null) {
                QLog.i(TIMConversation.tag, 1, "SendMsg|5-Callback|Succ|");
                IMMsfCoreProxy.mainHandler.post(new ca(this));
            } else {
                QLog.i(TIMConversation.tag, 1, "SendMsg|5-Callback|Fail|user not set succ cb");
            }
            swigTakeOwnership();
            if (this.c != null) {
                this.c.init(QrEventType.kEventSendMsg.swigValue(), 0, "");
                this.c.report();
            }
            long elementCount = this.b.getElementCount();
            for (int i = 0; ((long) i) < elementCount; i++) {
                switch (bm.a[this.b.getElement(i).getType().ordinal()]) {
                    case 1:
                        BeaconUtil.onEvent(BeaconEvents.sendTextMsg, true, -1, -1, null, false);
                        break;
                    case 2:
                        BeaconUtil.onEvent(BeaconEvents.sendImgMsg, true, -1, -1, null, false);
                        break;
                    case 3:
                        BeaconUtil.onEvent(BeaconEvents.sendAudioMsg, true, -1, -1, null, false);
                        break;
                    default:
                        break;
                }
            }
        }

        public final void fail(int i, String str) {
            if (this.a != null) {
                QLog.i(TIMConversation.tag, 1, "SendMsg|5-Callback|Succ|code=" + i + ", msg=" + str);
                IMMsfCoreProxy.mainHandler.post(new cb(this, i, str));
            } else {
                QLog.i(TIMConversation.tag, 1, "SendMsg|5-Callback|Fail|user not set fail cb, code=" + i + ", msg=" + str);
            }
            swigTakeOwnership();
            if (this.c != null) {
                this.c.init(QrEventType.kEventSendMsg.swigValue(), i, str);
                this.c.report();
            }
            Map hashMap = new HashMap();
            hashMap.put("param_FailCode", "bindID failed|code: " + i + " desc: " + str);
            long elementCount = this.b.getElementCount();
            for (int i2 = 0; ((long) i2) < elementCount; i2++) {
                switch (bm.a[this.b.getElement(i2).getType().ordinal()]) {
                    case 1:
                        BeaconUtil.onEvent(BeaconEvents.sendTextMsg, false, -1, -1, hashMap, false);
                        break;
                    case 2:
                        BeaconUtil.onEvent(BeaconEvents.sendImgMsg, false, -1, -1, hashMap, false);
                        break;
                    case 3:
                        BeaconUtil.onEvent(BeaconEvents.sendAudioMsg, false, -1, -1, hashMap, false);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    abstract class ad extends ISetReadMsg {
        public TIMCallBack a;

        public ad(TIMConversation tIMConversation, TIMCallBack tIMCallBack) {
            swigReleaseOwnership();
            this.a = tIMCallBack;
        }

        public abstract void a();

        public abstract void a(int i, String str);

        public void done() {
            IMMsfCoreProxy.mainHandler.post(new cc(this));
            swigTakeOwnership();
        }

        public void fail(int i, String str) {
            IMMsfCoreProxy.mainHandler.post(new cd(this, i, str));
            swigTakeOwnership();
        }
    }

    public TIMConversation() {
        this.identifer = "";
        this.peer = "";
        this.type = TIMConversationType.Invalid;
        this.identifer = TIMManager.getInstance().getIdentification();
    }

    public TIMConversation(String str) {
        this.identifer = "";
        this.peer = "";
        this.type = TIMConversationType.Invalid;
        this.identifer = str;
    }

    public static String getTag() {
        return tag;
    }

    public void deleteLocalMessage(TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            if (valid()) {
                getSession().deleteLocalMsg(null, new bl(this, tIMCallBack));
            } else {
                tIMCallBack.onError(6004, "invalid conversation. user not login or peer is null");
            }
        }
    }

    public void disableStorage() {
        if (!IMCoreWrapper.get().isReady()) {
            QLog.e(tag, 1, "disableStorage, sdk not initialized or not logged in.");
        } else if (valid()) {
            IMCore.get().addIgnoreStoreSession(TIMConversationType.getType(this.type), this.peer);
        } else {
            QLog.e(tag, 1, "disableStorage, invalid conversation. user not login or peer is null");
        }
    }

    void fromSession(Session session) {
        this.type = TIMConversationType.Invalid;
        if (session != null) {
            if (session.isValid()) {
                this.type = TIMConversationType.getType(session.type());
            } else {
                this.type = TIMConversationType.Invalid;
            }
            setPeer(session.sid());
        }
    }

    public TIMMessageDraft getDraft() {
        if (!IMCoreWrapper.get().isReady()) {
            QLog.e(tag, 1, "importMsg, sdk not initialized or not logged in.");
            return null;
        } else if (valid()) {
            return TIMMessageDraft.convertFrom(getSession().getDraft());
        } else {
            QLog.e(tag, 1, "importMsg, invalid conversation. user not login or peer is null");
            return null;
        }
    }

    public String getIdentifer() {
        return this.identifer;
    }

    public List<TIMMessage> getLastMsgs(long j) {
        if (valid()) {
            List<TIMMessage> arrayList = new ArrayList();
            MsgVec undeletedMsgsFromCache = getSession().getUndeletedMsgsFromCache(j);
            if (undeletedMsgsFromCache == null) {
                return arrayList;
            }
            long size = undeletedMsgsFromCache.size();
            for (int i = 0; ((long) i) < size; i++) {
                arrayList.add(new TIMMessage(undeletedMsgsFromCache.get(i)));
            }
            return arrayList;
        }
        QLog.e(tag, 1, "getLastMsgs, invalid conversation. user not login or peer is null");
        return null;
    }

    public void getLocalMessage(int i, TIMMessage tIMMessage, TIMValueCallBack<List<TIMMessage>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (!IMCoreWrapper.get().isReady()) {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            } else if (valid()) {
                Msg msg = null;
                if (tIMMessage != null) {
                    msg = tIMMessage.getMsg();
                }
                getSession().getLocalMsgs((long) i, msg, new bu(this, tIMValueCallBack));
            } else {
                tIMValueCallBack.onError(6004, "invalid conversation. user not login or peer is null");
            }
        }
    }

    public void getMessage(int i, TIMMessage tIMMessage, TIMValueCallBack<List<TIMMessage>> tIMValueCallBack) {
        if (tIMValueCallBack != null) {
            if (!IMCoreWrapper.get().isReady()) {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            } else if (valid()) {
                Msg msg = null;
                if (tIMMessage != null) {
                    msg = tIMMessage.getMsg();
                }
                if (TIMManager.getInstanceById(this.identifer).getNetworkStatus() == TIMNetworkStatus.TIM_NETWORK_STATUS_DISCONNECTED) {
                    QLog.d(tag, 1, "network disconnected, get Msgs from local");
                    getSession().getLocalMsgs((long) i, msg, new bq(this, tIMValueCallBack));
                    return;
                }
                getSession().getMsgs((long) i, msg, new br(this, tIMValueCallBack));
            } else {
                tIMValueCallBack.onError(6004, "invalid conversation. user not login or peer is null");
            }
        }
    }

    public void getMessageForward(int i, TIMMessage tIMMessage, TIMValueCallBack<List<TIMMessage>> tIMValueCallBack) {
        if (tIMValueCallBack != null && tIMMessage != null) {
            if (!IMCoreWrapper.get().isReady()) {
                tIMValueCallBack.onError(6013, "sdk not initialized or not logged in.");
            } else if (valid()) {
                getSession().getMsgsForward((long) i, tIMMessage.getMsg(), new bs(this, tIMValueCallBack));
            } else {
                tIMValueCallBack.onError(6004, "invalid conversation. user not login or peer is null");
            }
        }
    }

    public String getPeer() {
        return this.peer;
    }

    Session getSession() {
        SessionType type = TIMConversationType.getType(this.type);
        Session session = TIMManager.getInstanceById(this.identifer).getCoreUser().getSession(type, this.peer);
        return !session.isValid() ? TIMManager.getInstanceById(this.identifer).getCoreUser().newSession(type, this.peer) : session;
    }

    public TIMConversationType getType() {
        return this.type;
    }

    public long getUnreadMessageNum() {
        if (!IMCoreWrapper.get().isReady()) {
            QLog.e(tag, 1, "sdk not initialized or not logged in.");
            return 0;
        } else if (valid()) {
            return getSession().msgUnread();
        } else {
            QLog.e(tag, 1, "invalid conversation. user not login or peer is null");
            return 0;
        }
    }

    public boolean hasDraft() {
        if (!IMCoreWrapper.get().isReady()) {
            QLog.e(tag, 1, "importMsg, sdk not initialized or not logged in.");
            return false;
        } else if (valid()) {
            return getSession().hasDraft();
        } else {
            QLog.e(tag, 1, "importMsg, invalid conversation. user not login or peer is null");
            return false;
        }
    }

    public int importMsg(List<TIMMessage> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }
        if (!IMCoreWrapper.get().isReady()) {
            QLog.e(tag, 1, "importMsg, sdk not initialized or not logged in.");
            return 6013;
        } else if (valid()) {
            MsgVec msgVec = new MsgVec();
            for (TIMMessage msg : list) {
                msgVec.pushBack(msg.getMsg());
            }
            return getSession().importMsg(msgVec);
        } else {
            QLog.e(tag, 1, "importMsg, invalid conversation. user not login or peer is null");
            return 6004;
        }
    }

    protected boolean paramCheckForSendMsg(TIMMessage tIMMessage, TIMValueCallBack<TIMMessage> tIMValueCallBack, QualityReportHelper qualityReportHelper) {
        if (tIMValueCallBack == null) {
            return false;
        }
        IMErrInfo iMErrInfo;
        if (tIMMessage == null) {
            iMErrInfo = new IMErrInfo(6017, "invalid parameters, msg is null");
            tIMValueCallBack.onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.init(QrEventType.kEventSendMsg.swigValue(), iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.report();
            return false;
        } else if (IMCoreWrapper.get().isReady()) {
            return true;
        } else {
            iMErrInfo = new IMErrInfo(6013, "sdk not initialized or not logged in.");
            tIMValueCallBack.onError(iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.init(QrEventType.kEventSendMsg.swigValue(), iMErrInfo.getCode(), iMErrInfo.getMsg());
            qualityReportHelper.report();
            return false;
        }
    }

    public int saveMessage(TIMMessage tIMMessage, String str, boolean z) {
        if (tIMMessage == null || tIMMessage.getMsg() == null || str == null || str.length() == 0) {
            return 6017;
        }
        if (!IMCoreWrapper.get().isReady()) {
            return 6013;
        }
        if (!valid()) {
            return 6004;
        }
        Session session = getSession();
        return (session == null || !session.isValid()) ? 6004 : session.saveMsg(tIMMessage.getMsg(), str, z);
    }

    public void sendLikeMessage(TIMMessage tIMMessage, TIMValueCallBack<TIMMessage> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        if (paramCheckForSendMsg(tIMMessage, tIMValueCallBack, qualityReportHelper)) {
            tIMMessage.setConversation(this);
            getSession().sendLikeMsg(tIMMessage.getMsg(), new bp(this, tIMMessage, tIMValueCallBack, qualityReportHelper));
        }
    }

    public void sendMessage(TIMMessage tIMMessage, TIMValueCallBack<TIMMessage> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        if (paramCheckForSendMsg(tIMMessage, tIMValueCallBack, qualityReportHelper)) {
            tIMMessage.setConversation(this);
            QLog.i(tag, 1, "SendMsg|1-Begin|Succ|type=" + this.type + ", conversation=" + this.peer);
            getSession().sendMsg(tIMMessage.getMsg(), new bk(this, tIMMessage, tIMValueCallBack, qualityReportHelper));
        }
    }

    public void sendOnlineMessage(TIMMessage tIMMessage, TIMValueCallBack<TIMMessage> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        if (paramCheckForSendMsg(tIMMessage, tIMValueCallBack, qualityReportHelper)) {
            tIMMessage.setConversation(this);
            getSession().sendCustomMsg(tIMMessage.getMsg(), new bn(this, tIMMessage, tIMValueCallBack, qualityReportHelper), 0);
        }
    }

    public void sendRedPacketMessage(TIMMessage tIMMessage, TIMValueCallBack<TIMMessage> tIMValueCallBack) {
        QualityReportHelper qualityReportHelper = new QualityReportHelper();
        if (paramCheckForSendMsg(tIMMessage, tIMValueCallBack, qualityReportHelper)) {
            tIMMessage.setConversation(this);
            getSession().sendRedPacketMsg(tIMMessage.getMsg(), new bo(this, tIMMessage, tIMValueCallBack, qualityReportHelper));
        }
    }

    public void setDraft(TIMMessageDraft tIMMessageDraft) {
        if (!IMCoreWrapper.get().isReady()) {
            QLog.e(tag, 1, "importMsg, sdk not initialized or not logged in.");
        } else if (valid()) {
            Draft draft = new Draft();
            if (tIMMessageDraft != null) {
                draft = tIMMessageDraft.convertTo();
            }
            getSession().setDraft(draft);
        } else {
            QLog.e(tag, 1, "importMsg, invalid conversation. user not login or peer is null");
        }
    }

    public void setIdentifer(String str) {
        this.identifer = str;
    }

    void setPeer(String str) {
        this.peer = str;
    }

    public void setReadMessage() {
        if (!IMCoreWrapper.get().isReady()) {
            QLog.e(tag, 1, "sdk not initialized or not logged in.");
        } else if (valid()) {
            getSession().reportReaded();
        } else {
            QLog.e(tag, 1, "invalid conversation. user not login or peer is null");
        }
    }

    public void setReadMessage(TIMMessage tIMMessage) {
        if (!IMCoreWrapper.get().isReady()) {
            QLog.e(tag, 1, "sdk not initialized or not logged in.");
        } else if (valid()) {
            getSession().reportReaded(tIMMessage.getMsg());
        } else {
            QLog.e(tag, 1, "invalid conversation. user not login or peer is null");
        }
    }

    public void setReadMessage(TIMMessage tIMMessage, TIMCallBack tIMCallBack) {
        if (tIMCallBack != null) {
            if (!IMCoreWrapper.get().isReady()) {
                tIMCallBack.onError(6013, "sdk not initialized or not logged in.");
            } else if (valid()) {
                getSession().reportReaded(tIMMessage.getMsg(), new bt(this, tIMCallBack));
            } else {
                tIMCallBack.onError(6004, "invalid conversation. user not login or peer is null");
            }
        }
    }

    void setType(TIMConversationType tIMConversationType) {
        this.type = tIMConversationType;
    }

    boolean valid() {
        return this.type != TIMConversationType.Invalid;
    }
}
