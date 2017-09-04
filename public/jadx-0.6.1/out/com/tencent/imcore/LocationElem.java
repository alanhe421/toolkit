package com.tencent.imcore;

public class LocationElem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public LocationElem() {
        this(internalJNI.new_LocationElem(), true);
    }

    protected LocationElem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(LocationElem locationElem) {
        return locationElem == null ? 0 : locationElem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_LocationElem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public byte[] getDesc() {
        return internalJNI.LocationElem_desc_get(this.swigCPtr, this);
    }

    public double getLatitude() {
        return internalJNI.LocationElem_latitude_get(this.swigCPtr, this);
    }

    public double getLongitude() {
        return internalJNI.LocationElem_longitude_get(this.swigCPtr, this);
    }

    public void setDesc(byte[] bArr) {
        internalJNI.LocationElem_desc_set(this.swigCPtr, this, bArr);
    }

    public void setLatitude(double d) {
        internalJNI.LocationElem_latitude_set(this.swigCPtr, this, d);
    }

    public void setLongitude(double d) {
        internalJNI.LocationElem_longitude_set(this.swigCPtr, this, d);
    }
}
