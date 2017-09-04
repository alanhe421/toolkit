package com.tencent.imcore;

public class FriendshipProxy {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendshipProxy() {
        this(internalJNI.new_FriendshipProxy(), true);
    }

    protected FriendshipProxy(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendshipProxy friendshipProxy) {
        return friendshipProxy == null ? 0 : friendshipProxy.swigCPtr;
    }

    public void changeStatusWithoutNotify(FriendProxyStatus friendProxyStatus) {
        internalJNI.FriendshipProxy_changeStatusWithoutNotify(this.swigCPtr, this, friendProxyStatus.swigValue());
    }

    public FriendGroupVec convertToFriendGroupVec(SWIGTYPE_p_std__vectorT_imcore__FriendGroupItem_t sWIGTYPE_p_std__vectorT_imcore__FriendGroupItem_t) {
        return new FriendGroupVec(internalJNI.FriendshipProxy_convertToFriendGroupVec(this.swigCPtr, this, SWIGTYPE_p_std__vectorT_imcore__FriendGroupItem_t.getCPtr(sWIGTYPE_p_std__vectorT_imcore__FriendGroupItem_t)), true);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendshipProxy(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public IFriendshipActionCallback getAddBlackList(StrVec strVec, IFriendshipActionCallback iFriendshipActionCallback) {
        long FriendshipProxy_getAddBlackList = internalJNI.FriendshipProxy_getAddBlackList(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
        return FriendshipProxy_getAddBlackList == 0 ? null : new IFriendshipActionCallback(FriendshipProxy_getAddBlackList, false);
    }

    public IFriendshipActionCallback getAddFriend2GroupCB(byte[] bArr, StrVec strVec, IFriendshipActionCallback iFriendshipActionCallback) {
        long FriendshipProxy_getAddFriend2GroupCB = internalJNI.FriendshipProxy_getAddFriend2GroupCB(this.swigCPtr, this, bArr, StrVec.getCPtr(strVec), strVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
        return FriendshipProxy_getAddFriend2GroupCB == 0 ? null : new IFriendshipActionCallback(FriendshipProxy_getAddFriend2GroupCB, false);
    }

    public IFriendshipActionCallback getCreateFriendGroupCB(BytesVec bytesVec, StrVec strVec, IFriendshipActionCallback iFriendshipActionCallback) {
        long FriendshipProxy_getCreateFriendGroupCB = internalJNI.FriendshipProxy_getCreateFriendGroupCB(this.swigCPtr, this, BytesVec.getCPtr(bytesVec), bytesVec, StrVec.getCPtr(strVec), strVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
        return FriendshipProxy_getCreateFriendGroupCB == 0 ? null : new IFriendshipActionCallback(FriendshipProxy_getCreateFriendGroupCB, false);
    }

    public StrVec getCustom() {
        return new StrVec(internalJNI.FriendshipProxy_getCustom(this.swigCPtr, this), true);
    }

    public IFriendshipActionCallback getDelFriendCB(FriendDeleteType friendDeleteType, FriendProfileVec friendProfileVec, IFriendshipActionCallback iFriendshipActionCallback) {
        long FriendshipProxy_getDelFriendCB = internalJNI.FriendshipProxy_getDelFriendCB(this.swigCPtr, this, friendDeleteType.swigValue(), FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
        return FriendshipProxy_getDelFriendCB == 0 ? null : new IFriendshipActionCallback(FriendshipProxy_getDelFriendCB, false);
    }

    public IFriendshipActionCallback getDelFriendsFromGroupCB(byte[] bArr, StrVec strVec, IFriendshipActionCallback iFriendshipActionCallback) {
        long FriendshipProxy_getDelFriendsFromGroupCB = internalJNI.FriendshipProxy_getDelFriendsFromGroupCB(this.swigCPtr, this, bArr, StrVec.getCPtr(strVec), strVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
        return FriendshipProxy_getDelFriendsFromGroupCB == 0 ? null : new IFriendshipActionCallback(FriendshipProxy_getDelFriendsFromGroupCB, false);
    }

    public IFriendshipCallback getDeleteFriendGroup(BytesVec bytesVec, IFriendshipCallback iFriendshipCallback) {
        long FriendshipProxy_getDeleteFriendGroup = internalJNI.FriendshipProxy_getDeleteFriendGroup(this.swigCPtr, this, BytesVec.getCPtr(bytesVec), bytesVec, IFriendshipCallback.getCPtr(iFriendshipCallback), iFriendshipCallback);
        return FriendshipProxy_getDeleteFriendGroup == 0 ? null : new IFriendshipCallback(FriendshipProxy_getDeleteFriendGroup, false);
    }

    public IFriendshipActionCallback getDoResponseCB(FriendProfileVec friendProfileVec, IFriendshipActionCallback iFriendshipActionCallback) {
        long FriendshipProxy_getDoResponseCB = internalJNI.FriendshipProxy_getDoResponseCB(this.swigCPtr, this, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
        return FriendshipProxy_getDoResponseCB == 0 ? null : new IFriendshipActionCallback(FriendshipProxy_getDoResponseCB, false);
    }

    public long getFrdGrpSeq() {
        return internalJNI.FriendshipProxy_getFrdGrpSeq(this.swigCPtr, this);
    }

    public long getFrdSeq() {
        return internalJNI.FriendshipProxy_getFrdSeq(this.swigCPtr, this);
    }

    public long getFrdTime() {
        return internalJNI.FriendshipProxy_getFrdTime(this.swigCPtr, this);
    }

    public long getFriendFlags() {
        return internalJNI.FriendshipProxy_getFriendFlags(this.swigCPtr, this);
    }

    public int getFriendGroup(BytesVec bytesVec, FriendGroupVec friendGroupVec) {
        return internalJNI.FriendshipProxy_getFriendGroup(this.swigCPtr, this, BytesVec.getCPtr(bytesVec), bytesVec, FriendGroupVec.getCPtr(friendGroupVec), friendGroupVec);
    }

    public int getFriendList(FriendProfileVec friendProfileVec) {
        return internalJNI.FriendshipProxy_getFriendList(this.swigCPtr, this, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec);
    }

    public int getFriendProfile(StrVec strVec, FriendProfileVec friendProfileVec) {
        return internalJNI.FriendshipProxy_getFriendProfile(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec);
    }

    public IFriendshipActionCallback getModifyFriendGroupName(byte[] bArr, byte[] bArr2, IFriendshipActionCallback iFriendshipActionCallback) {
        long FriendshipProxy_getModifyFriendGroupName = internalJNI.FriendshipProxy_getModifyFriendGroupName(this.swigCPtr, this, bArr, bArr2, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
        return FriendshipProxy_getModifyFriendGroupName == 0 ? null : new IFriendshipActionCallback(FriendshipProxy_getModifyFriendGroupName, false);
    }

    public IFriendshipActionCallback getMove2FriendGroup(String str, String str2, StrVec strVec, IFriendshipActionCallback iFriendshipActionCallback) {
        long FriendshipProxy_getMove2FriendGroup = internalJNI.FriendshipProxy_getMove2FriendGroup(this.swigCPtr, this, str, str2, StrVec.getCPtr(strVec), strVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
        return FriendshipProxy_getMove2FriendGroup == 0 ? null : new IFriendshipActionCallback(FriendshipProxy_getMove2FriendGroup, false);
    }

    public IFriendshipActionCallback getSetSnsProfileCB(SNSProfileItemVec sNSProfileItemVec, IFriendshipActionCallback iFriendshipActionCallback) {
        long FriendshipProxy_getSetSnsProfileCB = internalJNI.FriendshipProxy_getSetSnsProfileCB(this.swigCPtr, this, SNSProfileItemVec.getCPtr(sNSProfileItemVec), sNSProfileItemVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
        return FriendshipProxy_getSetSnsProfileCB == 0 ? null : new IFriendshipActionCallback(FriendshipProxy_getSetSnsProfileCB, false);
    }

    public FriendProxyStatus getStatus() {
        return FriendProxyStatus.swigToEnum(internalJNI.FriendshipProxy_getStatus(this.swigCPtr, this));
    }

    public void init(String str, IFriendshipCallback iFriendshipCallback) {
        internalJNI.FriendshipProxy_init(this.swigCPtr, this, str, IFriendshipCallback.getCPtr(iFriendshipCallback), iFriendshipCallback);
    }

    public boolean isEnable() {
        return internalJNI.FriendshipProxy_isEnable(this.swigCPtr, this);
    }

    public void onFriendGroupSync(FriendGroupVec friendGroupVec, long j, boolean z) {
        internalJNI.FriendshipProxy_onFriendGroupSync(this.swigCPtr, this, FriendGroupVec.getCPtr(friendGroupVec), friendGroupVec, j, z);
    }

    public void onFriendshipEvent(SWIGTYPE_p_imcore__MsgNode sWIGTYPE_p_imcore__MsgNode) {
        internalJNI.FriendshipProxy_onFriendshipEvent(this.swigCPtr, this, SWIGTYPE_p_imcore__MsgNode.getCPtr(sWIGTYPE_p_imcore__MsgNode));
    }

    public void onProfileSync(FriendProfileVec friendProfileVec, long j, long j2, boolean z) {
        internalJNI.FriendshipProxy_onProfileSync(this.swigCPtr, this, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec, j, j2, z);
    }

    public void onSyncFail(int i, String str) {
        internalJNI.FriendshipProxy_onSyncFail(this.swigCPtr, this, i, str);
    }

    public void onSyncSucc() {
        internalJNI.FriendshipProxy_onSyncSucc(this.swigCPtr, this);
    }

    public GetProfileOption proxyProfileOption() {
        return new GetProfileOption(internalJNI.FriendshipProxy_proxyProfileOption(this.swigCPtr, this), true);
    }

    public void setFriendProxyConfig(FriendshipProxyConfig friendshipProxyConfig) {
        internalJNI.FriendshipProxy_setFriendProxyConfig(this.swigCPtr, this, FriendshipProxyConfig.getCPtr(friendshipProxyConfig), friendshipProxyConfig);
    }

    public void syncProxy() {
        internalJNI.FriendshipProxy_syncProxy(this.swigCPtr, this);
    }
}
