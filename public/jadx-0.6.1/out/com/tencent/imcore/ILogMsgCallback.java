package com.tencent.imcore;

public class ILogMsgCallback {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public ILogMsgCallback() {
        this(internalJNI.new_ILogMsgCallback(), true);
        internalJNI.ILogMsgCallback_director_connect(this, this.swigCPtr, this.swigCMemOwn, true);
    }

    protected ILogMsgCallback(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ILogMsgCallback iLogMsgCallback) {
        return iLogMsgCallback == null ? 0 : iLogMsgCallback.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_ILogMsgCallback(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public void onLogMsg(String str, LogLevel logLevel, String str2) {
        if (getClass() == ILogMsgCallback.class) {
            internalJNI.ILogMsgCallback_onLogMsg(this.swigCPtr, this, str, logLevel.swigValue(), str2);
            return;
        }
        internalJNI.ILogMsgCallback_onLogMsgSwigExplicitILogMsgCallback(this.swigCPtr, this, str, logLevel.swigValue(), str2);
    }

    protected void swigDirectorDisconnect() {
        this.swigCMemOwn = false;
        delete();
    }

    public void swigReleaseOwnership() {
        this.swigCMemOwn = false;
        internalJNI.ILogMsgCallback_change_ownership(this, this.swigCPtr, false);
    }

    public void swigTakeOwnership() {
        this.swigCMemOwn = true;
        internalJNI.ILogMsgCallback_change_ownership(this, this.swigCPtr, true);
    }
}
