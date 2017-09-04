package com.tencent.imcore;

public class SdkReportItem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public SdkReportItem() {
        this(internalJNI.new_SdkReportItem(), true);
    }

    protected SdkReportItem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SdkReportItem sdkReportItem) {
        return sdkReportItem == null ? 0 : sdkReportItem.swigCPtr;
    }

    public void beginEvent() {
        internalJNI.SdkReportItem_beginEvent(this.swigCPtr, this);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_SdkReportItem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void endEvent() {
        internalJNI.SdkReportItem_endEvent(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public int getCode() {
        return internalJNI.SdkReportItem_code_get(this.swigCPtr, this);
    }

    public byte[] getDesc() {
        return internalJNI.SdkReportItem_desc_get(this.swigCPtr, this);
    }

    public long getEnd_time_ms() {
        return internalJNI.SdkReportItem_end_time_ms_get(this.swigCPtr, this);
    }

    public int getEvent() {
        return internalJNI.SdkReportItem_event_get(this.swigCPtr, this);
    }

    public long getStart_time_ms() {
        return internalJNI.SdkReportItem_start_time_ms_get(this.swigCPtr, this);
    }

    public void setCode(int i) {
        internalJNI.SdkReportItem_code_set(this.swigCPtr, this, i);
    }

    public void setDesc(byte[] bArr) {
        internalJNI.SdkReportItem_desc_set(this.swigCPtr, this, bArr);
    }

    public void setEnd_time_ms(long j) {
        internalJNI.SdkReportItem_end_time_ms_set(this.swigCPtr, this, j);
    }

    public void setEvent(int i) {
        internalJNI.SdkReportItem_event_set(this.swigCPtr, this, i);
    }

    public void setStart_time_ms(long j) {
        internalJNI.SdkReportItem_start_time_ms_set(this.swigCPtr, this, j);
    }
}
