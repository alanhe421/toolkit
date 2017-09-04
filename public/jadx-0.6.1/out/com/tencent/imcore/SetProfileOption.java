package com.tencent.imcore;

public class SetProfileOption {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public SetProfileOption() {
        this(internalJNI.new_SetProfileOption(), true);
    }

    protected SetProfileOption(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(SetProfileOption setProfileOption) {
        return setProfileOption == null ? 0 : setProfileOption.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_SetProfileOption(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public String getAdd_option() {
        return internalJNI.SetProfileOption_add_option_get(this.swigCPtr, this);
    }

    public long getBirthday() {
        return internalJNI.SetProfileOption_birthday_get(this.swigCPtr, this);
    }

    public BytesMap getCustom_info() {
        long SetProfileOption_custom_info_get = internalJNI.SetProfileOption_custom_info_get(this.swigCPtr, this);
        return SetProfileOption_custom_info_get == 0 ? null : new BytesMap(SetProfileOption_custom_info_get, false);
    }

    public byte[] getFace_url() {
        return internalJNI.SetProfileOption_face_url_get(this.swigCPtr, this);
    }

    public long getFlag() {
        return internalJNI.SetProfileOption_flag_get(this.swigCPtr, this);
    }

    public FriendGenderType getGender() {
        return FriendGenderType.swigToEnum(internalJNI.SetProfileOption_gender_get(this.swigCPtr, this));
    }

    public long getLanguage() {
        return internalJNI.SetProfileOption_language_get(this.swigCPtr, this);
    }

    public byte[] getLocation() {
        return internalJNI.SetProfileOption_location_get(this.swigCPtr, this);
    }

    public byte[] getNick() {
        return internalJNI.SetProfileOption_nick_get(this.swigCPtr, this);
    }

    public byte[] getSelf_signature() {
        return internalJNI.SetProfileOption_self_signature_get(this.swigCPtr, this);
    }

    public void setAdd_option(String str) {
        internalJNI.SetProfileOption_add_option_set(this.swigCPtr, this, str);
    }

    public void setBirthday(long j) {
        internalJNI.SetProfileOption_birthday_set(this.swigCPtr, this, j);
    }

    public void setCustom_info(BytesMap bytesMap) {
        internalJNI.SetProfileOption_custom_info_set(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap);
    }

    public void setFace_url(byte[] bArr) {
        internalJNI.SetProfileOption_face_url_set(this.swigCPtr, this, bArr);
    }

    public void setFlag(long j) {
        internalJNI.SetProfileOption_flag_set(this.swigCPtr, this, j);
    }

    public void setGender(FriendGenderType friendGenderType) {
        internalJNI.SetProfileOption_gender_set(this.swigCPtr, this, friendGenderType.swigValue());
    }

    public void setLanguage(long j) {
        internalJNI.SetProfileOption_language_set(this.swigCPtr, this, j);
    }

    public void setLocation(byte[] bArr) {
        internalJNI.SetProfileOption_location_set(this.swigCPtr, this, bArr);
    }

    public void setNick(byte[] bArr) {
        internalJNI.SetProfileOption_nick_set(this.swigCPtr, this, bArr);
    }

    public void setSelf_signature(byte[] bArr) {
        internalJNI.SetProfileOption_self_signature_set(this.swigCPtr, this, bArr);
    }
}
