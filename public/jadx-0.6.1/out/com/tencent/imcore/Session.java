package com.tencent.imcore;

public class Session {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Session() {
        this(internalJNI.new_Session(), true);
    }

    protected Session(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(Session session) {
        return session == null ? 0 : session.swigCPtr;
    }

    public long activeTime() {
        return internalJNI.Session_activeTime(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_Session(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public int deleteLocalMsg(IDeleteLocalMsg iDeleteLocalMsg) {
        return internalJNI.Session_deleteLocalMsg__SWIG_1(this.swigCPtr, this, IDeleteLocalMsg.getCPtr(iDeleteLocalMsg), iDeleteLocalMsg);
    }

    public int deleteLocalMsg(Msg msg, IDeleteLocalMsg iDeleteLocalMsg) {
        return internalJNI.Session_deleteLocalMsg__SWIG_0(this.swigCPtr, this, Msg.getCPtr(msg), msg, IDeleteLocalMsg.getCPtr(iDeleteLocalMsg), iDeleteLocalMsg);
    }

    protected void finalize() {
        delete();
    }

    public Draft getDraft() {
        return new Draft(internalJNI.Session_getDraft(this.swigCPtr, this), true);
    }

    public void getLocalMsgs(long j, Msg msg, IGetMsgs iGetMsgs) {
        internalJNI.Session_getLocalMsgs(this.swigCPtr, this, j, Msg.getCPtr(msg), msg, IGetMsgs.getCPtr(iGetMsgs), iGetMsgs);
    }

    public void getMsgs(long j, Msg msg, IGetMsgs iGetMsgs) {
        internalJNI.Session_getMsgs(this.swigCPtr, this, j, Msg.getCPtr(msg), msg, IGetMsgs.getCPtr(iGetMsgs), iGetMsgs);
    }

    public void getMsgsForward(long j, Msg msg, IGetMsgs iGetMsgs) {
        internalJNI.Session_getMsgsForward(this.swigCPtr, this, j, Msg.getCPtr(msg), msg, IGetMsgs.getCPtr(iGetMsgs), iGetMsgs);
    }

    public MsgVec getMsgsFromCache(long j) {
        return new MsgVec(internalJNI.Session_getMsgsFromCache__SWIG_1(this.swigCPtr, this, j), true);
    }

    public MsgVec getMsgsFromCache(long j, Msg msg) {
        return new MsgVec(internalJNI.Session_getMsgsFromCache__SWIG_0(this.swigCPtr, this, j, Msg.getCPtr(msg), msg), true);
    }

    public MsgVec getUndeletedMsgsFromCache(long j) {
        return new MsgVec(internalJNI.Session_getUndeletedMsgsFromCache__SWIG_1(this.swigCPtr, this, j), true);
    }

    public MsgVec getUndeletedMsgsFromCache(long j, Msg msg) {
        return new MsgVec(internalJNI.Session_getUndeletedMsgsFromCache__SWIG_0(this.swigCPtr, this, j, Msg.getCPtr(msg), msg), true);
    }

    public boolean hasDraft() {
        return internalJNI.Session_hasDraft(this.swigCPtr, this);
    }

    public String iconUrl() {
        return internalJNI.Session_iconUrl(this.swigCPtr, this);
    }

    public String identifier() {
        return internalJNI.Session_identifier(this.swigCPtr, this);
    }

    public int importMsg(MsgVec msgVec) {
        return internalJNI.Session_importMsg(this.swigCPtr, this, MsgVec.getCPtr(msgVec), msgVec);
    }

    public boolean isValid() {
        return internalJNI.Session_isValid(this.swigCPtr, this);
    }

    public long msgUnread() {
        return internalJNI.Session_msgUnread(this.swigCPtr, this);
    }

    public String name() {
        return internalJNI.Session_name(this.swigCPtr, this);
    }

    public void reportReaded() {
        internalJNI.Session_reportReaded__SWIG_2(this.swigCPtr, this);
    }

    public void reportReaded(Msg msg) {
        internalJNI.Session_reportReaded__SWIG_1(this.swigCPtr, this, Msg.getCPtr(msg), msg);
    }

    public void reportReaded(Msg msg, ISetReadMsg iSetReadMsg) {
        internalJNI.Session_reportReaded__SWIG_0(this.swigCPtr, this, Msg.getCPtr(msg), msg, ISetReadMsg.getCPtr(iSetReadMsg), iSetReadMsg);
    }

    public int saveMsg(Msg msg, String str, boolean z) {
        return internalJNI.Session_saveMsg(this.swigCPtr, this, Msg.getCPtr(msg), msg, str, z);
    }

    public void sendCustomMsg(Msg msg, ISendMsg iSendMsg, long j) {
        internalJNI.Session_sendCustomMsg(this.swigCPtr, this, Msg.getCPtr(msg), msg, ISendMsg.getCPtr(iSendMsg), iSendMsg, j);
    }

    public void sendLikeMsg(Msg msg, ISendMsg iSendMsg) {
        internalJNI.Session_sendLikeMsg(this.swigCPtr, this, Msg.getCPtr(msg), msg, ISendMsg.getCPtr(iSendMsg), iSendMsg);
    }

    public void sendMsg(Msg msg, ISendMsg iSendMsg) {
        internalJNI.Session_sendMsg(this.swigCPtr, this, Msg.getCPtr(msg), msg, ISendMsg.getCPtr(iSendMsg), iSendMsg);
    }

    public void sendRedPacketMsg(Msg msg, ISendMsg iSendMsg) {
        internalJNI.Session_sendRedPacketMsg(this.swigCPtr, this, Msg.getCPtr(msg), msg, ISendMsg.getCPtr(iSendMsg), iSendMsg);
    }

    public void setDraft(Draft draft) {
        internalJNI.Session_setDraft(this.swigCPtr, this, Draft.getCPtr(draft), draft);
    }

    public String sid() {
        return internalJNI.Session_sid(this.swigCPtr, this);
    }

    public SessionType type() {
        return SessionType.swigToEnum(internalJNI.Session_type(this.swigCPtr, this));
    }
}
