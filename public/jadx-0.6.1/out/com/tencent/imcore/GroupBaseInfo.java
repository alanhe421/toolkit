package com.tencent.imcore;

public class GroupBaseInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupBaseInfo() {
        this(internalJNI.new_GroupBaseInfo(), true);
    }

    protected GroupBaseInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupBaseInfo groupBaseInfo) {
        return groupBaseInfo == null ? 0 : groupBaseInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupBaseInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public long getDwInfoSeq() {
        return internalJNI.GroupBaseInfo_dwInfoSeq_get(this.swigCPtr, this);
    }

    public long getDwMsgFalg() {
        return internalJNI.GroupBaseInfo_dwMsgFalg_get(this.swigCPtr, this);
    }

    public long getDwMsgSeq() {
        return internalJNI.GroupBaseInfo_dwMsgSeq_get(this.swigCPtr, this);
    }

    public long getDwReadSeq() {
        return internalJNI.GroupBaseInfo_dwReadSeq_get(this.swigCPtr, this);
    }

    public byte[] getSFaceUrl() {
        return internalJNI.GroupBaseInfo_sFaceUrl_get(this.swigCPtr, this);
    }

    public String getSGroupId() {
        return internalJNI.GroupBaseInfo_sGroupId_get(this.swigCPtr, this);
    }

    public byte[] getSGroupName() {
        return internalJNI.GroupBaseInfo_sGroupName_get(this.swigCPtr, this);
    }

    public String getSGroupType() {
        return internalJNI.GroupBaseInfo_sGroupType_get(this.swigCPtr, this);
    }

    public GroupSelfInfo getStSelfInfo() {
        long GroupBaseInfo_stSelfInfo_get = internalJNI.GroupBaseInfo_stSelfInfo_get(this.swigCPtr, this);
        return GroupBaseInfo_stSelfInfo_get == 0 ? null : new GroupSelfInfo(GroupBaseInfo_stSelfInfo_get, false);
    }

    public void setDwInfoSeq(long j) {
        internalJNI.GroupBaseInfo_dwInfoSeq_set(this.swigCPtr, this, j);
    }

    public void setDwMsgFalg(long j) {
        internalJNI.GroupBaseInfo_dwMsgFalg_set(this.swigCPtr, this, j);
    }

    public void setDwMsgSeq(long j) {
        internalJNI.GroupBaseInfo_dwMsgSeq_set(this.swigCPtr, this, j);
    }

    public void setDwReadSeq(long j) {
        internalJNI.GroupBaseInfo_dwReadSeq_set(this.swigCPtr, this, j);
    }

    public void setSFaceUrl(byte[] bArr) {
        internalJNI.GroupBaseInfo_sFaceUrl_set(this.swigCPtr, this, bArr);
    }

    public void setSGroupId(String str) {
        internalJNI.GroupBaseInfo_sGroupId_set(this.swigCPtr, this, str);
    }

    public void setSGroupName(byte[] bArr) {
        internalJNI.GroupBaseInfo_sGroupName_set(this.swigCPtr, this, bArr);
    }

    public void setSGroupType(String str) {
        internalJNI.GroupBaseInfo_sGroupType_set(this.swigCPtr, this, str);
    }

    public void setStSelfInfo(GroupSelfInfo groupSelfInfo) {
        internalJNI.GroupBaseInfo_stSelfInfo_set(this.swigCPtr, this, GroupSelfInfo.getCPtr(groupSelfInfo), groupSelfInfo);
    }
}
