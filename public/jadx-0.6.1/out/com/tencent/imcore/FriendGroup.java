package com.tencent.imcore;

public class FriendGroup {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendGroup() {
        this(internalJNI.new_FriendGroup(), true);
    }

    protected FriendGroup(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendGroup friendGroup) {
        return friendGroup == null ? 0 : friendGroup.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendGroup(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getCount() {
        return internalJNI.FriendGroup_count_get(this.swigCPtr, this);
    }

    public StrVec getIdentifiers() {
        long FriendGroup_identifiers_get = internalJNI.FriendGroup_identifiers_get(this.swigCPtr, this);
        return FriendGroup_identifiers_get == 0 ? null : new StrVec(FriendGroup_identifiers_get, false);
    }

    public byte[] getName() {
        return internalJNI.FriendGroup_name_get(this.swigCPtr, this);
    }

    public FriendProfileVec getProfiles() {
        long FriendGroup_profiles_get = internalJNI.FriendGroup_profiles_get(this.swigCPtr, this);
        return FriendGroup_profiles_get == 0 ? null : new FriendProfileVec(FriendGroup_profiles_get, false);
    }

    public SWIGTYPE_p_std__vectorT_long_long_t getTinyids() {
        long FriendGroup_tinyids_get = internalJNI.FriendGroup_tinyids_get(this.swigCPtr, this);
        return FriendGroup_tinyids_get == 0 ? null : new SWIGTYPE_p_std__vectorT_long_long_t(FriendGroup_tinyids_get, false);
    }

    public void setCount(long j) {
        internalJNI.FriendGroup_count_set(this.swigCPtr, this, j);
    }

    public void setIdentifiers(StrVec strVec) {
        internalJNI.FriendGroup_identifiers_set(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec);
    }

    public void setName(byte[] bArr) {
        internalJNI.FriendGroup_name_set(this.swigCPtr, this, bArr);
    }

    public void setProfiles(FriendProfileVec friendProfileVec) {
        internalJNI.FriendGroup_profiles_set(this.swigCPtr, this, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec);
    }

    public void setTinyids(SWIGTYPE_p_std__vectorT_long_long_t sWIGTYPE_p_std__vectorT_long_long_t) {
        internalJNI.FriendGroup_tinyids_set(this.swigCPtr, this, SWIGTYPE_p_std__vectorT_long_long_t.getCPtr(sWIGTYPE_p_std__vectorT_long_long_t));
    }
}
