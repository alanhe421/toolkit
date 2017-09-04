package com.tencent.imcore;

public class MapKeyFetcher {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public MapKeyFetcher() {
        this(internalJNI.new_MapKeyFetcher(), true);
    }

    protected MapKeyFetcher(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(MapKeyFetcher mapKeyFetcher) {
        return mapKeyFetcher == null ? 0 : mapKeyFetcher.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_MapKeyFetcher(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void fetchMapKeys(BytesMap bytesMap) {
        internalJNI.MapKeyFetcher_fetchMapKeys(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap);
    }

    protected void finalize() {
        delete();
    }

    public byte[] getKey(int i) {
        return internalJNI.MapKeyFetcher_getKey(this.swigCPtr, this, i);
    }

    public BytesVec getKeys() {
        long MapKeyFetcher_keys_get = internalJNI.MapKeyFetcher_keys_get(this.swigCPtr, this);
        return MapKeyFetcher_keys_get == 0 ? null : new BytesVec(MapKeyFetcher_keys_get, false);
    }

    public byte[] getValue(BytesMap bytesMap, int i) {
        return internalJNI.MapKeyFetcher_getValue(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap, i);
    }

    public void setKeys(BytesVec bytesVec) {
        internalJNI.MapKeyFetcher_keys_set(this.swigCPtr, this, BytesVec.getCPtr(bytesVec), bytesVec);
    }
}
