package com.tencent.imcore;

public class GroupPendencyItem {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupPendencyItem() {
        this(internalJNI.new_GroupPendencyItem(), true);
    }

    protected GroupPendencyItem(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupPendencyItem groupPendencyItem) {
        return groupPendencyItem == null ? 0 : groupPendencyItem.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupPendencyItem(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getAdd_time() {
        return internalJNI.GroupPendencyItem_add_time_get(this.swigCPtr, this);
    }

    public byte[] getApply_invite_msg() {
        return internalJNI.GroupPendencyItem_apply_invite_msg_get(this.swigCPtr, this);
    }

    public byte[] getApproval_msg() {
        return internalJNI.GroupPendencyItem_approval_msg_get(this.swigCPtr, this);
    }

    public byte[] getAuthentication() {
        return internalJNI.GroupPendencyItem_authentication_get(this.swigCPtr, this);
    }

    public String getFrom_id() {
        return internalJNI.GroupPendencyItem_from_id_get(this.swigCPtr, this);
    }

    public byte[] getFrom_user_defined_data() {
        return internalJNI.GroupPendencyItem_from_user_defined_data_get(this.swigCPtr, this);
    }

    public String getGroup_id() {
        return internalJNI.GroupPendencyItem_group_id_get(this.swigCPtr, this);
    }

    public long getHandle_result() {
        return internalJNI.GroupPendencyItem_handle_result_get(this.swigCPtr, this);
    }

    public long getHandled() {
        return internalJNI.GroupPendencyItem_handled_get(this.swigCPtr, this);
    }

    public byte[] getKey() {
        return internalJNI.GroupPendencyItem_key_get(this.swigCPtr, this);
    }

    public long getPendency_type() {
        return internalJNI.GroupPendencyItem_pendency_type_get(this.swigCPtr, this);
    }

    public byte[] getSelfIdentifier() {
        return internalJNI.GroupPendencyItem_selfIdentifier_get(this.swigCPtr, this);
    }

    public String getTo_id() {
        return internalJNI.GroupPendencyItem_to_id_get(this.swigCPtr, this);
    }

    public byte[] getTo_user_defined_data() {
        return internalJNI.GroupPendencyItem_to_user_defined_data_get(this.swigCPtr, this);
    }

    public void setAdd_time(long j) {
        internalJNI.GroupPendencyItem_add_time_set(this.swigCPtr, this, j);
    }

    public void setApply_invite_msg(byte[] bArr) {
        internalJNI.GroupPendencyItem_apply_invite_msg_set(this.swigCPtr, this, bArr);
    }

    public void setApproval_msg(byte[] bArr) {
        internalJNI.GroupPendencyItem_approval_msg_set(this.swigCPtr, this, bArr);
    }

    public void setAuthentication(byte[] bArr) {
        internalJNI.GroupPendencyItem_authentication_set(this.swigCPtr, this, bArr);
    }

    public void setFrom_id(String str) {
        internalJNI.GroupPendencyItem_from_id_set(this.swigCPtr, this, str);
    }

    public void setFrom_user_defined_data(byte[] bArr) {
        internalJNI.GroupPendencyItem_from_user_defined_data_set(this.swigCPtr, this, bArr);
    }

    public void setGroup_id(String str) {
        internalJNI.GroupPendencyItem_group_id_set(this.swigCPtr, this, str);
    }

    public void setHandle_result(long j) {
        internalJNI.GroupPendencyItem_handle_result_set(this.swigCPtr, this, j);
    }

    public void setHandled(long j) {
        internalJNI.GroupPendencyItem_handled_set(this.swigCPtr, this, j);
    }

    public void setKey(byte[] bArr) {
        internalJNI.GroupPendencyItem_key_set(this.swigCPtr, this, bArr);
    }

    public void setPendency_type(long j) {
        internalJNI.GroupPendencyItem_pendency_type_set(this.swigCPtr, this, j);
    }

    public void setSelfIdentifier(byte[] bArr) {
        internalJNI.GroupPendencyItem_selfIdentifier_set(this.swigCPtr, this, bArr);
    }

    public void setTo_id(String str) {
        internalJNI.GroupPendencyItem_to_id_set(this.swigCPtr, this, str);
    }

    public void setTo_user_defined_data(byte[] bArr) {
        internalJNI.GroupPendencyItem_to_user_defined_data_set(this.swigCPtr, this, bArr);
    }
}
