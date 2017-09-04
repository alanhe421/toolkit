package com.tencent.imcore;

public class GroupDetailInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupDetailInfo() {
        this(internalJNI.new_GroupDetailInfo(), true);
    }

    protected GroupDetailInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupDetailInfo groupDetailInfo) {
        return groupDetailInfo == null ? 0 : groupDetailInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupDetailInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public BytesMap getCustom_info() {
        long GroupDetailInfo_custom_info_get = internalJNI.GroupDetailInfo_custom_info_get(this.swigCPtr, this);
        return GroupDetailInfo_custom_info_get == 0 ? null : new BytesMap(GroupDetailInfo_custom_info_get, false);
    }

    public long getDdwOwnerTinyId() {
        return internalJNI.GroupDetailInfo_ddwOwnerTinyId_get(this.swigCPtr, this);
    }

    public long getDwAddOption() {
        return internalJNI.GroupDetailInfo_dwAddOption_get(this.swigCPtr, this);
    }

    public long getDwAppid() {
        return internalJNI.GroupDetailInfo_dwAppid_get(this.swigCPtr, this);
    }

    public long getDwCreateTime() {
        return internalJNI.GroupDetailInfo_dwCreateTime_get(this.swigCPtr, this);
    }

    public long getDwInfoSeq() {
        return internalJNI.GroupDetailInfo_dwInfoSeq_get(this.swigCPtr, this);
    }

    public long getDwLastInfoTime() {
        return internalJNI.GroupDetailInfo_dwLastInfoTime_get(this.swigCPtr, this);
    }

    public long getDwLastMsgTime() {
        return internalJNI.GroupDetailInfo_dwLastMsgTime_get(this.swigCPtr, this);
    }

    public long getDwMaxMemberNum() {
        return internalJNI.GroupDetailInfo_dwMaxMemberNum_get(this.swigCPtr, this);
    }

    public long getDwMemberNum() {
        return internalJNI.GroupDetailInfo_dwMemberNum_get(this.swigCPtr, this);
    }

    public long getDwNextMsgSeq() {
        return internalJNI.GroupDetailInfo_dwNextMsgSeq_get(this.swigCPtr, this);
    }

    public long getDwOnlineMemberNum() {
        return internalJNI.GroupDetailInfo_dwOnlineMemberNum_get(this.swigCPtr, this);
    }

    public ComStatus getESearchable() {
        return ComStatus.swigToEnum(internalJNI.GroupDetailInfo_eSearchable_get(this.swigCPtr, this));
    }

    public ComStatus getEVisible() {
        return ComStatus.swigToEnum(internalJNI.GroupDetailInfo_eVisible_get(this.swigCPtr, this));
    }

    public Msg getLastMsg() {
        long GroupDetailInfo_lastMsg_get = internalJNI.GroupDetailInfo_lastMsg_get(this.swigCPtr, this);
        return GroupDetailInfo_lastMsg_get == 0 ? null : new Msg(GroupDetailInfo_lastMsg_get, false);
    }

    public String getSFaceUrl() {
        return internalJNI.GroupDetailInfo_sFaceUrl_get(this.swigCPtr, this);
    }

    public String getSGroupId() {
        return internalJNI.GroupDetailInfo_sGroupId_get(this.swigCPtr, this);
    }

    public byte[] getSGroupName() {
        return internalJNI.GroupDetailInfo_sGroupName_get(this.swigCPtr, this);
    }

    public String getSGroupType() {
        return internalJNI.GroupDetailInfo_sGroupType_get(this.swigCPtr, this);
    }

    public byte[] getSIntroduction() {
        return internalJNI.GroupDetailInfo_sIntroduction_get(this.swigCPtr, this);
    }

    public byte[] getSNotification() {
        return internalJNI.GroupDetailInfo_sNotification_get(this.swigCPtr, this);
    }

    public String getSOwner() {
        return internalJNI.GroupDetailInfo_sOwner_get(this.swigCPtr, this);
    }

    public void setCustom_info(BytesMap bytesMap) {
        internalJNI.GroupDetailInfo_custom_info_set(this.swigCPtr, this, BytesMap.getCPtr(bytesMap), bytesMap);
    }

    public void setDdwOwnerTinyId(long j) {
        internalJNI.GroupDetailInfo_ddwOwnerTinyId_set(this.swigCPtr, this, j);
    }

    public void setDwAddOption(long j) {
        internalJNI.GroupDetailInfo_dwAddOption_set(this.swigCPtr, this, j);
    }

    public void setDwAppid(long j) {
        internalJNI.GroupDetailInfo_dwAppid_set(this.swigCPtr, this, j);
    }

    public void setDwCreateTime(long j) {
        internalJNI.GroupDetailInfo_dwCreateTime_set(this.swigCPtr, this, j);
    }

    public void setDwInfoSeq(long j) {
        internalJNI.GroupDetailInfo_dwInfoSeq_set(this.swigCPtr, this, j);
    }

    public void setDwLastInfoTime(long j) {
        internalJNI.GroupDetailInfo_dwLastInfoTime_set(this.swigCPtr, this, j);
    }

    public void setDwLastMsgTime(long j) {
        internalJNI.GroupDetailInfo_dwLastMsgTime_set(this.swigCPtr, this, j);
    }

    public void setDwMaxMemberNum(long j) {
        internalJNI.GroupDetailInfo_dwMaxMemberNum_set(this.swigCPtr, this, j);
    }

    public void setDwMemberNum(long j) {
        internalJNI.GroupDetailInfo_dwMemberNum_set(this.swigCPtr, this, j);
    }

    public void setDwNextMsgSeq(long j) {
        internalJNI.GroupDetailInfo_dwNextMsgSeq_set(this.swigCPtr, this, j);
    }

    public void setDwOnlineMemberNum(long j) {
        internalJNI.GroupDetailInfo_dwOnlineMemberNum_set(this.swigCPtr, this, j);
    }

    public void setESearchable(ComStatus comStatus) {
        internalJNI.GroupDetailInfo_eSearchable_set(this.swigCPtr, this, comStatus.swigValue());
    }

    public void setEVisible(ComStatus comStatus) {
        internalJNI.GroupDetailInfo_eVisible_set(this.swigCPtr, this, comStatus.swigValue());
    }

    public void setLastMsg(Msg msg) {
        internalJNI.GroupDetailInfo_lastMsg_set(this.swigCPtr, this, Msg.getCPtr(msg), msg);
    }

    public void setSFaceUrl(String str) {
        internalJNI.GroupDetailInfo_sFaceUrl_set(this.swigCPtr, this, str);
    }

    public void setSGroupId(String str) {
        internalJNI.GroupDetailInfo_sGroupId_set(this.swigCPtr, this, str);
    }

    public void setSGroupName(byte[] bArr) {
        internalJNI.GroupDetailInfo_sGroupName_set(this.swigCPtr, this, bArr);
    }

    public void setSGroupType(String str) {
        internalJNI.GroupDetailInfo_sGroupType_set(this.swigCPtr, this, str);
    }

    public void setSIntroduction(byte[] bArr) {
        internalJNI.GroupDetailInfo_sIntroduction_set(this.swigCPtr, this, bArr);
    }

    public void setSNotification(byte[] bArr) {
        internalJNI.GroupDetailInfo_sNotification_set(this.swigCPtr, this, bArr);
    }

    public void setSOwner(String str) {
        internalJNI.GroupDetailInfo_sOwner_set(this.swigCPtr, this, str);
    }
}
