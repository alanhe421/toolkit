package com.tencent.imcore;

public class FriendFutureItem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendFutureItem() {
        this(internalJNI.new_FriendFutureItem(), true);
    }

    protected FriendFutureItem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendFutureItem friendFutureItem) {
        return friendFutureItem == null ? 0 : friendFutureItem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendFutureItem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getDdwAddTime() {
        return internalJNI.FriendFutureItem_ddwAddTime_get(this.swigCPtr, this);
    }

    public FutureType getEType() {
        return FutureType.swigToEnum(internalJNI.FriendFutureItem_eType_get(this.swigCPtr, this));
    }

    public BytesMap getMpRecommendTags() {
        long FriendFutureItem_mpRecommendTags_get = internalJNI.FriendFutureItem_mpRecommendTags_get(this.swigCPtr, this);
        return FriendFutureItem_mpRecommendTags_get == 0 ? null : new BytesMap(FriendFutureItem_mpRecommendTags_get, false);
    }

    public byte[] getSAddSource() {
        return internalJNI.FriendFutureItem_sAddSource_get(this.swigCPtr, this);
    }

    public byte[] getSAddWording() {
        return internalJNI.FriendFutureItem_sAddWording_get(this.swigCPtr, this);
    }

    public String getSIdentifier() {
        return internalJNI.FriendFutureItem_sIdentifier_get(this.swigCPtr, this);
    }

    public FriendProfile getStProfile() {
        long FriendFutureItem_stProfile_get = internalJNI.FriendFutureItem_stProfile_get(this.swigCPtr, this);
        return FriendFutureItem_stProfile_get == 0 ? null : new FriendProfile(FriendFutureItem_stProfile_get, false);
    }

    public void setDdwAddTime(long j) {
        internalJNI.FriendFutureItem_ddwAddTime_set(this.swigCPtr, this, j);
    }

    public void setEType(FutureType futureType) {
        internalJNI.FriendFutureItem_eType_set(this.swigCPtr, this, futureType.swigValue());
    }

    public void setMpRecommendTags(BytesMap bytesMap) {
        internalJNI.FriendFutureItem_mpRecommendTags_set(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap);
    }

    public void setSAddSource(byte[] bArr) {
        internalJNI.FriendFutureItem_sAddSource_set(this.swigCPtr, this, bArr);
    }

    public void setSAddWording(byte[] bArr) {
        internalJNI.FriendFutureItem_sAddWording_set(this.swigCPtr, this, bArr);
    }

    public void setSIdentifier(String str) {
        internalJNI.FriendFutureItem_sIdentifier_set(this.swigCPtr, this, str);
    }

    public void setStProfile(FriendProfile friendProfile) {
        internalJNI.FriendFutureItem_stProfile_set(this.swigCPtr, this, FriendProfile.getCPtr(friendProfile), friendProfile);
    }
}
