package com.tencent.imcore;

public class NewGroupInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public NewGroupInfo() {
        this(internalJNI.new_NewGroupInfo(), true);
    }

    protected NewGroupInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(NewGroupInfo newGroupInfo) {
        return newGroupInfo == null ? 0 : newGroupInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_NewGroupInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getAdd_option() {
        return internalJNI.NewGroupInfo_add_option_get(this.swigCPtr, this);
    }

    public BytesMap getBase_custom_info() {
        long NewGroupInfo_base_custom_info_get = internalJNI.NewGroupInfo_base_custom_info_get(this.swigCPtr, this);
        return NewGroupInfo_base_custom_info_get == 0 ? null : new BytesMap(NewGroupInfo_base_custom_info_get, false);
    }

    public String getFace_url() {
        return internalJNI.NewGroupInfo_face_url_get(this.swigCPtr, this);
    }

    public byte[] getGroup_id() {
        return internalJNI.NewGroupInfo_group_id_get(this.swigCPtr, this);
    }

    public NewGroupMemVec getGroup_members() {
        long NewGroupInfo_group_members_get = internalJNI.NewGroupInfo_group_members_get(this.swigCPtr, this);
        return NewGroupInfo_group_members_get == 0 ? null : new NewGroupMemVec(NewGroupInfo_group_members_get, false);
    }

    public byte[] getGroup_name() {
        return internalJNI.NewGroupInfo_group_name_get(this.swigCPtr, this);
    }

    public String getGroup_type() {
        return internalJNI.NewGroupInfo_group_type_get(this.swigCPtr, this);
    }

    public byte[] getIntroduction() {
        return internalJNI.NewGroupInfo_introduction_get(this.swigCPtr, this);
    }

    public long getMax_member_num() {
        return internalJNI.NewGroupInfo_max_member_num_get(this.swigCPtr, this);
    }

    public byte[] getNotification() {
        return internalJNI.NewGroupInfo_notification_get(this.swigCPtr, this);
    }

    public long getOwner_tiny_id() {
        return internalJNI.NewGroupInfo_owner_tiny_id_get(this.swigCPtr, this);
    }

    public boolean getSet_add_option() {
        return internalJNI.NewGroupInfo_set_add_option_get(this.swigCPtr, this);
    }

    public void setAdd_option(long j) {
        internalJNI.NewGroupInfo_add_option_set(this.swigCPtr, this, j);
    }

    public void setBase_custom_info(BytesMap bytesMap) {
        internalJNI.NewGroupInfo_base_custom_info_set(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap);
    }

    public void setFace_url(String str) {
        internalJNI.NewGroupInfo_face_url_set(this.swigCPtr, this, str);
    }

    public void setGroup_id(byte[] bArr) {
        internalJNI.NewGroupInfo_group_id_set(this.swigCPtr, this, bArr);
    }

    public void setGroup_members(NewGroupMemVec newGroupMemVec) {
        internalJNI.NewGroupInfo_group_members_set(this.swigCPtr, this, NewGroupMemVec.getCPtr(newGroupMemVec), newGroupMemVec);
    }

    public void setGroup_name(byte[] bArr) {
        internalJNI.NewGroupInfo_group_name_set(this.swigCPtr, this, bArr);
    }

    public void setGroup_type(String str) {
        internalJNI.NewGroupInfo_group_type_set(this.swigCPtr, this, str);
    }

    public void setIntroduction(byte[] bArr) {
        internalJNI.NewGroupInfo_introduction_set(this.swigCPtr, this, bArr);
    }

    public void setMax_member_num(long j) {
        internalJNI.NewGroupInfo_max_member_num_set(this.swigCPtr, this, j);
    }

    public void setNotification(byte[] bArr) {
        internalJNI.NewGroupInfo_notification_set(this.swigCPtr, this, bArr);
    }

    public void setOwner_tiny_id(long j) {
        internalJNI.NewGroupInfo_owner_tiny_id_set(this.swigCPtr, this, j);
    }

    public void setSet_add_option(boolean z) {
        internalJNI.NewGroupInfo_set_add_option_set(this.swigCPtr, this, z);
    }
}
