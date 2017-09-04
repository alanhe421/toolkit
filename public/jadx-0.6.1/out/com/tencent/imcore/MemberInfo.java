package com.tencent.imcore;

public class MemberInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public MemberInfo() {
        this(internalJNI.new_MemberInfo(), true);
    }

    protected MemberInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(MemberInfo memberInfo) {
        return memberInfo == null ? 0 : memberInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_MemberInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public BytesMap getCustom_info() {
        long MemberInfo_custom_info_get = internalJNI.MemberInfo_custom_info_get(this.swigCPtr, this);
        return MemberInfo_custom_info_get == 0 ? null : new BytesMap(MemberInfo_custom_info_get, false);
    }

    public long getJoin_time() {
        return internalJNI.MemberInfo_join_time_get(this.swigCPtr, this);
    }

    public String getMember() {
        return internalJNI.MemberInfo_member_get(this.swigCPtr, this);
    }

    public long getMsg_flag() {
        return internalJNI.MemberInfo_msg_flag_get(this.swigCPtr, this);
    }

    public long getMsg_seq() {
        return internalJNI.MemberInfo_msg_seq_get(this.swigCPtr, this);
    }

    public byte[] getName_card() {
        return internalJNI.MemberInfo_name_card_get(this.swigCPtr, this);
    }

    public long getRole() {
        return internalJNI.MemberInfo_role_get(this.swigCPtr, this);
    }

    public long getShutup_time() {
        return internalJNI.MemberInfo_shutup_time_get(this.swigCPtr, this);
    }

    public void setCustom_info(BytesMap bytesMap) {
        internalJNI.MemberInfo_custom_info_set(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap);
    }

    public void setJoin_time(long j) {
        internalJNI.MemberInfo_join_time_set(this.swigCPtr, this, j);
    }

    public void setMember(String str) {
        internalJNI.MemberInfo_member_set(this.swigCPtr, this, str);
    }

    public void setMsg_flag(long j) {
        internalJNI.MemberInfo_msg_flag_set(this.swigCPtr, this, j);
    }

    public void setMsg_seq(long j) {
        internalJNI.MemberInfo_msg_seq_set(this.swigCPtr, this, j);
    }

    public void setName_card(byte[] bArr) {
        internalJNI.MemberInfo_name_card_set(this.swigCPtr, this, bArr);
    }

    public void setRole(long j) {
        internalJNI.MemberInfo_role_set(this.swigCPtr, this, j);
    }

    public void setShutup_time(long j) {
        internalJNI.MemberInfo_shutup_time_set(this.swigCPtr, this, j);
    }
}
