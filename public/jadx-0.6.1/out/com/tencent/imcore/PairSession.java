package com.tencent.imcore;

public class PairSession {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public PairSession() {
        this(internalJNI.new_PairSession__SWIG_0(), true);
    }

    protected PairSession(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public PairSession(PairSession pairSession) {
        this(internalJNI.new_PairSession__SWIG_2(getCPtr(pairSession), pairSession), true);
    }

    public PairSession(byte[] bArr, SessionType sessionType) {
        this(internalJNI.new_PairSession__SWIG_1(bArr, sessionType.swigValue()), true);
    }

    protected static long getCPtr(PairSession pairSession) {
        return pairSession == null ? 0 : pairSession.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_PairSession(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public byte[] getFirst() {
        return internalJNI.PairSession_first_get(this.swigCPtr, this);
    }

    public SessionType getSecond() {
        return SessionType.swigToEnum(internalJNI.PairSession_second_get(this.swigCPtr, this));
    }

    public void setFirst(byte[] bArr) {
        internalJNI.PairSession_first_set(this.swigCPtr, this, bArr);
    }

    public void setSecond(SessionType sessionType) {
        internalJNI.PairSession_second_set(this.swigCPtr, this, sessionType.swigValue());
    }
}
