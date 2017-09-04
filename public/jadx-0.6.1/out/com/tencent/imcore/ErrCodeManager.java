package com.tencent.imcore;

public class ErrCodeManager {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public ErrCodeManager() {
        this(internalJNI.new_ErrCodeManager(), true);
    }

    protected ErrCodeManager(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public static ErrCodeManager get() {
        return new ErrCodeManager(internalJNI.ErrCodeManager_get(), false);
    }

    protected static long getCPtr(ErrCodeManager errCodeManager) {
        return errCodeManager == null ? 0 : errCodeManager.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_ErrCodeManager(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public boolean transErrCode(String str, SWIGTYPE_p_unsigned_int sWIGTYPE_p_unsigned_int, SWIGTYPE_p_std__string sWIGTYPE_p_std__string) {
        return internalJNI.ErrCodeManager_transErrCode(this.swigCPtr, this, str, SWIGTYPE_p_unsigned_int.getCPtr(sWIGTYPE_p_unsigned_int), SWIGTYPE_p_std__string.getCPtr(sWIGTYPE_p_std__string));
    }
}
