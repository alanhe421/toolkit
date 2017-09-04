package com.tencent.imcore;

public class BytesMemberInfoParser {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public BytesMemberInfoParser() {
        this(internalJNI.new_BytesMemberInfoParser(), true);
    }

    protected BytesMemberInfoParser(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(BytesMemberInfoParser bytesMemberInfoParser) {
        return bytesMemberInfoParser == null ? 0 : bytesMemberInfoParser.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_BytesMemberInfoParser(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void fetchMapKeys(SWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t sWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t) {
        internalJNI.BytesMemberInfoParser_fetchMapKeys(this.swigCPtr, this, SWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t.getCPtr(sWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t));
    }

    protected void finalize() {
        delete();
    }

    public SWIGTYPE_p_std__string getKey(int i) {
        long BytesMemberInfoParser_getKey = internalJNI.BytesMemberInfoParser_getKey(this.swigCPtr, this, i);
        return BytesMemberInfoParser_getKey == 0 ? null : new SWIGTYPE_p_std__string(BytesMemberInfoParser_getKey, false);
    }

    public StrVec getKeys() {
        long BytesMemberInfoParser_keys_get = internalJNI.BytesMemberInfoParser_keys_get(this.swigCPtr, this);
        return BytesMemberInfoParser_keys_get == 0 ? null : new StrVec(BytesMemberInfoParser_keys_get, false);
    }

    public NewGroupMemberInfo getValue(SWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t sWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t, int i) {
        long BytesMemberInfoParser_getValue = internalJNI.BytesMemberInfoParser_getValue(this.swigCPtr, this, SWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t.getCPtr(sWIGTYPE_p_std__mapT_std__string_imcore__NewGroupMemberInfo_t), i);
        return BytesMemberInfoParser_getValue == 0 ? null : new NewGroupMemberInfo(BytesMemberInfoParser_getValue, false);
    }

    public void setKeys(StrVec strVec) {
        internalJNI.BytesMemberInfoParser_keys_set(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec);
    }
}
