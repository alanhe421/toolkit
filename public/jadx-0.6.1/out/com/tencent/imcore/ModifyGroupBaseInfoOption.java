package com.tencent.imcore;

public class ModifyGroupBaseInfoOption {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public ModifyGroupBaseInfoOption() {
        this(internalJNI.new_ModifyGroupBaseInfoOption(), true);
    }

    protected ModifyGroupBaseInfoOption(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(ModifyGroupBaseInfoOption modifyGroupBaseInfoOption) {
        return modifyGroupBaseInfoOption == null ? 0 : modifyGroupBaseInfoOption.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_ModifyGroupBaseInfoOption(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getAdd_option() {
        return internalJNI.ModifyGroupBaseInfoOption_add_option_get(this.swigCPtr, this);
    }

    public BytesMap getCustom_info() {
        long ModifyGroupBaseInfoOption_custom_info_get = internalJNI.ModifyGroupBaseInfoOption_custom_info_get(this.swigCPtr, this);
        return ModifyGroupBaseInfoOption_custom_info_get == 0 ? null : new BytesMap(ModifyGroupBaseInfoOption_custom_info_get, false);
    }

    public byte[] getFace_url() {
        return internalJNI.ModifyGroupBaseInfoOption_face_url_get(this.swigCPtr, this);
    }

    public long getFlag() {
        return internalJNI.ModifyGroupBaseInfoOption_flag_get(this.swigCPtr, this);
    }

    public String getGroup_id() {
        return internalJNI.ModifyGroupBaseInfoOption_group_id_get(this.swigCPtr, this);
    }

    public byte[] getGroup_name() {
        return internalJNI.ModifyGroupBaseInfoOption_group_name_get(this.swigCPtr, this);
    }

    public byte[] getIntroduction() {
        return internalJNI.ModifyGroupBaseInfoOption_introduction_get(this.swigCPtr, this);
    }

    public long getMax_member_num() {
        return internalJNI.ModifyGroupBaseInfoOption_max_member_num_get(this.swigCPtr, this);
    }

    public byte[] getNotification() {
        return internalJNI.ModifyGroupBaseInfoOption_notification_get(this.swigCPtr, this);
    }

    public ComStatus getSearchable() {
        return ComStatus.swigToEnum(internalJNI.ModifyGroupBaseInfoOption_searchable_get(this.swigCPtr, this));
    }

    public ComStatus getVisible() {
        return ComStatus.swigToEnum(internalJNI.ModifyGroupBaseInfoOption_visible_get(this.swigCPtr, this));
    }

    public void setAdd_option(long j) {
        internalJNI.ModifyGroupBaseInfoOption_add_option_set(this.swigCPtr, this, j);
    }

    public void setCustom_info(BytesMap bytesMap) {
        internalJNI.ModifyGroupBaseInfoOption_custom_info_set(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap);
    }

    public void setFace_url(byte[] bArr) {
        internalJNI.ModifyGroupBaseInfoOption_face_url_set(this.swigCPtr, this, bArr);
    }

    public void setFlag(long j) {
        internalJNI.ModifyGroupBaseInfoOption_flag_set(this.swigCPtr, this, j);
    }

    public void setGroup_id(String str) {
        internalJNI.ModifyGroupBaseInfoOption_group_id_set(this.swigCPtr, this, str);
    }

    public void setGroup_name(byte[] bArr) {
        internalJNI.ModifyGroupBaseInfoOption_group_name_set(this.swigCPtr, this, bArr);
    }

    public void setIntroduction(byte[] bArr) {
        internalJNI.ModifyGroupBaseInfoOption_introduction_set(this.swigCPtr, this, bArr);
    }

    public void setMax_member_num(long j) {
        internalJNI.ModifyGroupBaseInfoOption_max_member_num_set(this.swigCPtr, this, j);
    }

    public void setNotification(byte[] bArr) {
        internalJNI.ModifyGroupBaseInfoOption_notification_set(this.swigCPtr, this, bArr);
    }

    public void setSearchable(ComStatus comStatus) {
        internalJNI.ModifyGroupBaseInfoOption_searchable_set(this.swigCPtr, this, comStatus.swigValue());
    }

    public void setVisible(ComStatus comStatus) {
        internalJNI.ModifyGroupBaseInfoOption_visible_set(this.swigCPtr, this, comStatus.swigValue());
    }
}
