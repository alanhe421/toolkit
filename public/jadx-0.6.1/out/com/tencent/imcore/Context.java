package com.tencent.imcore;

public class Context {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public Context() {
        this(internalJNI.new_Context(), true);
    }

    protected Context(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(Context context) {
        return context == null ? 0 : context.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_Context(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getBid() {
        return internalJNI.Context_bid_get(this.swigCPtr, this);
    }

    public boolean getIsLogPrintEnabled() {
        return internalJNI.Context_isLogPrintEnabled_get(this.swigCPtr, this);
    }

    public String getLogPath() {
        return internalJNI.Context_logPath_get(this.swigCPtr, this);
    }

    public String getPicCachePath() {
        return internalJNI.Context_picCachePath_get(this.swigCPtr, this);
    }

    public long getPlatform() {
        return internalJNI.Context_platform_get(this.swigCPtr, this);
    }

    public long getSvr_time_diff() {
        return internalJNI.Context_svr_time_diff_get(this.swigCPtr, this);
    }

    public void setBid(long j) {
        internalJNI.Context_bid_set(this.swigCPtr, this, j);
    }

    public void setIsLogPrintEnabled(boolean z) {
        internalJNI.Context_isLogPrintEnabled_set(this.swigCPtr, this, z);
    }

    public void setLogPath(String str) {
        internalJNI.Context_logPath_set(this.swigCPtr, this, str);
    }

    public void setPicCachePath(String str) {
        internalJNI.Context_picCachePath_set(this.swigCPtr, this, str);
    }

    public void setPlatform(long j) {
        internalJNI.Context_platform_set(this.swigCPtr, this, j);
    }

    public void setSvr_time_diff(long j) {
        internalJNI.Context_svr_time_diff_set(this.swigCPtr, this, j);
    }
}
