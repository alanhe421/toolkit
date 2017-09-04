package com.tencent.imcore;

public class GroupTipsElem_GroupInfo {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public GroupTipsElem_GroupInfo() {
        this(internalJNI.new_GroupTipsElem_GroupInfo(), true);
    }

    protected GroupTipsElem_GroupInfo(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(GroupTipsElem_GroupInfo groupTipsElem_GroupInfo) {
        return groupTipsElem_GroupInfo == null ? 0 : groupTipsElem_GroupInfo.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_GroupTipsElem_GroupInfo(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public GroupInfoChangeType getType() {
        return GroupInfoChangeType.swigToEnum(internalJNI.GroupTipsElem_GroupInfo_type_get(this.swigCPtr, this));
    }

    public byte[] getValue() {
        return internalJNI.GroupTipsElem_GroupInfo_value_get(this.swigCPtr, this);
    }

    public void setType(GroupInfoChangeType groupInfoChangeType) {
        internalJNI.GroupTipsElem_GroupInfo_type_set(this.swigCPtr, this, groupInfoChangeType.swigValue());
    }

    public void setValue(byte[] bArr) {
        internalJNI.GroupTipsElem_GroupInfo_value_set(this.swigCPtr, this, bArr);
    }
}
