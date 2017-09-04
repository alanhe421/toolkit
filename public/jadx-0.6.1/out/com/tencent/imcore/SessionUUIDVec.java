package com.tencent.imcore;

public class SessionUUIDVec {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public SessionUUIDVec() {
        this(internalJNI.new_SessionUUIDVec__SWIG_0(), true);
    }

    public SessionUUIDVec(long j) {
        this(internalJNI.new_SessionUUIDVec__SWIG_1(j), true);
    }

    protected SessionUUIDVec(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SessionUUIDVec sessionUUIDVec) {
        return sessionUUIDVec == null ? 0 : sessionUUIDVec.swigCPtr;
    }

    public long capacity() {
        return internalJNI.SessionUUIDVec_capacity(this.swigCPtr, this);
    }

    public void clear() {
        internalJNI.SessionUUIDVec_clear(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_SessionUUIDVec(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean empty() {
        return internalJNI.SessionUUIDVec_empty(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public SessionUUID get(int i) {
        return new SessionUUID(internalJNI.SessionUUIDVec_get(this.swigCPtr, this, i), false);
    }

    public void pushBack(SessionUUID sessionUUID) {
        internalJNI.SessionUUIDVec_pushBack(this.swigCPtr, this, SessionUUID.getCPtr(sessionUUID), sessionUUID);
    }

    public void reserve(long j) {
        internalJNI.SessionUUIDVec_reserve(this.swigCPtr, this, j);
    }

    public void set(int i, SessionUUID sessionUUID) {
        internalJNI.SessionUUIDVec_set(this.swigCPtr, this, i, SessionUUID.getCPtr(sessionUUID), sessionUUID);
    }

    public long size() {
        return internalJNI.SessionUUIDVec_size(this.swigCPtr, this);
    }
}
