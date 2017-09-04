package com.tencent.imcore;

public class BytesProfileMapParser {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public BytesProfileMapParser() {
        this(internalJNI.new_BytesProfileMapParser(), true);
    }

    protected BytesProfileMapParser(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(BytesProfileMapParser bytesProfileMapParser) {
        return bytesProfileMapParser == null ? 0 : bytesProfileMapParser.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_BytesProfileMapParser(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public void fetchMapKeys(SWIGTYPE_p_std__mapT_std__string_imcore__FriendProfile_t sWIGTYPE_p_std__mapT_std__string_imcore__FriendProfile_t) {
        internalJNI.BytesProfileMapParser_fetchMapKeys(this.swigCPtr, this, SWIGTYPE_p_std__mapT_std__string_imcore__FriendProfile_t.getCPtr(sWIGTYPE_p_std__mapT_std__string_imcore__FriendProfile_t));
    }

    protected void finalize() {
        delete();
    }

    public SWIGTYPE_p_std__string getKey(int i) {
        long BytesProfileMapParser_getKey = internalJNI.BytesProfileMapParser_getKey(this.swigCPtr, this, i);
        return BytesProfileMapParser_getKey == 0 ? null : new SWIGTYPE_p_std__string(BytesProfileMapParser_getKey, false);
    }

    public StrVec getKeys() {
        long BytesProfileMapParser_keys_get = internalJNI.BytesProfileMapParser_keys_get(this.swigCPtr, this);
        return BytesProfileMapParser_keys_get == 0 ? null : new StrVec(BytesProfileMapParser_keys_get, false);
    }

    public FriendProfile getValue(SWIGTYPE_p_std__mapT_std__string_imcore__FriendProfile_t sWIGTYPE_p_std__mapT_std__string_imcore__FriendProfile_t, int i) {
        long BytesProfileMapParser_getValue = internalJNI.BytesProfileMapParser_getValue(this.swigCPtr, this, SWIGTYPE_p_std__mapT_std__string_imcore__FriendProfile_t.getCPtr(sWIGTYPE_p_std__mapT_std__string_imcore__FriendProfile_t), i);
        return BytesProfileMapParser_getValue == 0 ? null : new FriendProfile(BytesProfileMapParser_getValue, false);
    }

    public void setKeys(StrVec strVec) {
        internalJNI.BytesProfileMapParser_keys_set(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec);
    }
}
