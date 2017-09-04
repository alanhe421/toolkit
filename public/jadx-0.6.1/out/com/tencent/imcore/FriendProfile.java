package com.tencent.imcore;

public class FriendProfile {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendProfile() {
        this(internalJNI.new_FriendProfile(), true);
    }

    protected FriendProfile(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendProfile friendProfile) {
        return friendProfile == null ? 0 : friendProfile.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendProfile(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public BytesMap getCustom_info() {
        long FriendProfile_custom_info_get = internalJNI.FriendProfile_custom_info_get(this.swigCPtr, this);
        return FriendProfile_custom_info_get == 0 ? null : new BytesMap(FriendProfile_custom_info_get, false);
    }

    public long getDdwTinyId() {
        return internalJNI.FriendProfile_ddwTinyId_get(this.swigCPtr, this);
    }

    public FriendGenderType getEGender() {
        return FriendGenderType.swigToEnum(internalJNI.FriendProfile_eGender_get(this.swigCPtr, this));
    }

    public long getResult() {
        return internalJNI.FriendProfile_result_get(this.swigCPtr, this);
    }

    public byte[] getSAddSource() {
        return internalJNI.FriendProfile_sAddSource_get(this.swigCPtr, this);
    }

    public byte[] getSAddWording() {
        return internalJNI.FriendProfile_sAddWording_get(this.swigCPtr, this);
    }

    public String getSAllowType() {
        return internalJNI.FriendProfile_sAllowType_get(this.swigCPtr, this);
    }

    public byte[] getSFaceURL() {
        return internalJNI.FriendProfile_sFaceURL_get(this.swigCPtr, this);
    }

    public BytesVec getSGroupNames() {
        long FriendProfile_sGroupNames_get = internalJNI.FriendProfile_sGroupNames_get(this.swigCPtr, this);
        return FriendProfile_sGroupNames_get == 0 ? null : new BytesVec(FriendProfile_sGroupNames_get, false);
    }

    public String getSIdentifier() {
        return internalJNI.FriendProfile_sIdentifier_get(this.swigCPtr, this);
    }

    public byte[] getSLocation() {
        return internalJNI.FriendProfile_sLocation_get(this.swigCPtr, this);
    }

    public byte[] getSNickname() {
        return internalJNI.FriendProfile_sNickname_get(this.swigCPtr, this);
    }

    public byte[] getSRemark() {
        return internalJNI.FriendProfile_sRemark_get(this.swigCPtr, this);
    }

    public String getSResponseAction() {
        return internalJNI.FriendProfile_sResponseAction_get(this.swigCPtr, this);
    }

    public byte[] getSSelfSignature() {
        return internalJNI.FriendProfile_sSelfSignature_get(this.swigCPtr, this);
    }

    public long getSet_flags() {
        return internalJNI.FriendProfile_set_flags_get(this.swigCPtr, this);
    }

    public long getUBirthDay() {
        return internalJNI.FriendProfile_uBirthDay_get(this.swigCPtr, this);
    }

    public long getULanguage() {
        return internalJNI.FriendProfile_uLanguage_get(this.swigCPtr, this);
    }

    public void setCustom_info(BytesMap bytesMap) {
        internalJNI.FriendProfile_custom_info_set(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap);
    }

    public void setDdwTinyId(long j) {
        internalJNI.FriendProfile_ddwTinyId_set(this.swigCPtr, this, j);
    }

    public void setEGender(FriendGenderType friendGenderType) {
        internalJNI.FriendProfile_eGender_set(this.swigCPtr, this, friendGenderType.swigValue());
    }

    public void setResult(long j) {
        internalJNI.FriendProfile_result_set(this.swigCPtr, this, j);
    }

    public void setSAddSource(byte[] bArr) {
        internalJNI.FriendProfile_sAddSource_set(this.swigCPtr, this, bArr);
    }

    public void setSAddWording(byte[] bArr) {
        internalJNI.FriendProfile_sAddWording_set(this.swigCPtr, this, bArr);
    }

    public void setSAllowType(String str) {
        internalJNI.FriendProfile_sAllowType_set(this.swigCPtr, this, str);
    }

    public void setSFaceURL(byte[] bArr) {
        internalJNI.FriendProfile_sFaceURL_set(this.swigCPtr, this, bArr);
    }

    public void setSGroupNames(BytesVec bytesVec) {
        internalJNI.FriendProfile_sGroupNames_set(this.swigCPtr, this, BytesVec.getCPtr(bytesVec), bytesVec);
    }

    public void setSIdentifier(String str) {
        internalJNI.FriendProfile_sIdentifier_set(this.swigCPtr, this, str);
    }

    public void setSLocation(byte[] bArr) {
        internalJNI.FriendProfile_sLocation_set(this.swigCPtr, this, bArr);
    }

    public void setSNickname(byte[] bArr) {
        internalJNI.FriendProfile_sNickname_set(this.swigCPtr, this, bArr);
    }

    public void setSRemark(byte[] bArr) {
        internalJNI.FriendProfile_sRemark_set(this.swigCPtr, this, bArr);
    }

    public void setSResponseAction(String str) {
        internalJNI.FriendProfile_sResponseAction_set(this.swigCPtr, this, str);
    }

    public void setSSelfSignature(byte[] bArr) {
        internalJNI.FriendProfile_sSelfSignature_set(this.swigCPtr, this, bArr);
    }

    public void setSet_flags(long j) {
        internalJNI.FriendProfile_set_flags_set(this.swigCPtr, this, j);
    }

    public void setUBirthDay(long j) {
        internalJNI.FriendProfile_uBirthDay_set(this.swigCPtr, this, j);
    }

    public void setULanguage(long j) {
        internalJNI.FriendProfile_uLanguage_set(this.swigCPtr, this, j);
    }
}
