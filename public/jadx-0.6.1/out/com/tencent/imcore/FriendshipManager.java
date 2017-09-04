package com.tencent.imcore;

public class FriendshipManager {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected FriendshipManager(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    public FriendshipManager(String str) {
        this(internalJNI.new_FriendshipManager(str), true);
    }

    protected static long getCPtr(FriendshipManager friendshipManager) {
        return friendshipManager == null ? 0 : friendshipManager.swigCPtr;
    }

    public boolean addBlackList(StrVec strVec, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_addBlackList(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean addFriend(FriendProfileVec friendProfileVec, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_addFriend(this.swigCPtr, this, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean addFriendToMap(FriendProfile friendProfile) {
        return internalJNI.FriendshipManager_addFriendToMap(this.swigCPtr, this, FriendProfile.getCPtr(friendProfile), friendProfile);
    }

    public boolean addFriends2Group(byte[] bArr, StrVec strVec, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_addFriends2Group(this.swigCPtr, this, bArr, StrVec.getCPtr(strVec), strVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean checkFriend(StrVec strVec, String str, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_checkFriend(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, str, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public void clearAllData() {
        internalJNI.FriendshipManager_clearAllData(this.swigCPtr, this);
    }

    public boolean createFriendGroup(BytesVec bytesVec, StrVec strVec, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_createFriendGroup(this.swigCPtr, this, BytesVec.getCPtr(bytesVec), bytesVec, StrVec.getCPtr(strVec), strVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean delBlackList(StrVec strVec, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_delBlackList(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean delFriend(FriendDeleteType friendDeleteType, FriendProfileVec friendProfileVec, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_delFriend(this.swigCPtr, this, friendDeleteType.swigValue(), FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean delFriendToMap(FriendProfile friendProfile) {
        return internalJNI.FriendshipManager_delFriendToMap(this.swigCPtr, this, FriendProfile.getCPtr(friendProfile), friendProfile);
    }

    public boolean delFriendsFromGroup(byte[] bArr, StrVec strVec, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_delFriendsFromGroup(this.swigCPtr, this, bArr, StrVec.getCPtr(strVec), strVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendshipManager(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean deleteDecide(StrVec strVec, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_deleteDecide(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean deleteFriendGroup(BytesVec bytesVec, IFriendshipCallback iFriendshipCallback) {
        return internalJNI.FriendshipManager_deleteFriendGroup(this.swigCPtr, this, BytesVec.getCPtr(bytesVec), bytesVec, IFriendshipCallback.getCPtr(iFriendshipCallback), iFriendshipCallback);
    }

    public boolean deletePendency(PendencyType pendencyType, StrVec strVec, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_deletePendency(this.swigCPtr, this, pendencyType.swigValue(), StrVec.getCPtr(strVec), strVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean deleteRecommend(StrVec strVec, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_deleteRecommend(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean doResponse(FriendProfileVec friendProfileVec, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_doResponse(this.swigCPtr, this, FriendProfileVec.getCPtr(friendProfileVec), friendProfileVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    protected void finalize() {
        delete();
    }

    public boolean getBlackList(IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_getBlackList(this.swigCPtr, this, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean getFriendGroup(BytesVec bytesVec, boolean z, IFriendGroupCallback iFriendGroupCallback) {
        return internalJNI.FriendshipManager_getFriendGroup(this.swigCPtr, this, BytesVec.getCPtr(bytesVec), bytesVec, z, IFriendGroupCallback.getCPtr(iFriendGroupCallback), iFriendGroupCallback);
    }

    public boolean getFriendList(GetProfileOption getProfileOption, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_getFriendList__SWIG_1(this.swigCPtr, this, GetProfileOption.getCPtr(getProfileOption), getProfileOption, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean getFriendList(IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_getFriendList__SWIG_0(this.swigCPtr, this, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean getFriendListV2(long j, StrVec strVec, FriendMetaInfo friendMetaInfo, IFriendshipGetFriendV2Callback iFriendshipGetFriendV2Callback) {
        return internalJNI.FriendshipManager_getFriendListV2(this.swigCPtr, this, j, StrVec.getCPtr(strVec), strVec, FriendMetaInfo.getCPtr(friendMetaInfo), friendMetaInfo, IFriendshipGetFriendV2Callback.getCPtr(iFriendshipGetFriendV2Callback), iFriendshipGetFriendV2Callback);
    }

    public boolean getFriendProfile(StrVec strVec, GetProfileOption getProfileOption, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_getFriendProfile(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, GetProfileOption.getCPtr(getProfileOption), getProfileOption, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean getFutureFriends(long j, long j2, StrVec strVec, FutureFriendMeta futureFriendMeta, IFriendshipGetFutureCallback iFriendshipGetFutureCallback) {
        return internalJNI.FriendshipManager_getFutureFriends(this.swigCPtr, this, j, j2, StrVec.getCPtr(strVec), strVec, FutureFriendMeta.getCPtr(futureFriendMeta), futureFriendMeta, IFriendshipGetFutureCallback.getCPtr(iFriendshipGetFutureCallback), iFriendshipGetFutureCallback);
    }

    public FriendProfileVec getLocalFrdList() {
        return new FriendProfileVec(internalJNI.FriendshipManager_getLocalFrdList(this.swigCPtr, this), true);
    }

    public boolean getPendencyFromServer(FriendPendencyMeta friendPendencyMeta, PendencyType pendencyType, IFriendshipPendencyCallback iFriendshipPendencyCallback) {
        return internalJNI.FriendshipManager_getPendencyFromServer(this.swigCPtr, this, FriendPendencyMeta.getCPtr(friendPendencyMeta), friendPendencyMeta, pendencyType.swigValue(), IFriendshipPendencyCallback.getCPtr(iFriendshipPendencyCallback), iFriendshipPendencyCallback);
    }

    public boolean getProfile(StrVec strVec, GetProfileOption getProfileOption, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_getProfile__SWIG_1(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, GetProfileOption.getCPtr(getProfileOption), getProfileOption, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean getProfile(StrVec strVec, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_getProfile__SWIG_0(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean modifyFriendGroupName(byte[] bArr, byte[] bArr2, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_modifyFriendGroupName(this.swigCPtr, this, bArr, bArr2, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public boolean pendencyReport(long j, IFriendshipCallback iFriendshipCallback) {
        return internalJNI.FriendshipManager_pendencyReport(this.swigCPtr, this, j, IFriendshipCallback.getCPtr(iFriendshipCallback), iFriendshipCallback);
    }

    public boolean recommendReport(long j, IFriendshipCallback iFriendshipCallback) {
        return internalJNI.FriendshipManager_recommendReport(this.swigCPtr, this, j, IFriendshipCallback.getCPtr(iFriendshipCallback), iFriendshipCallback);
    }

    public boolean searchFriendsUseNickName(String str, long j, long j2, IFriendshipActionCallbackV2 iFriendshipActionCallbackV2) {
        return internalJNI.FriendshipManager_searchFriendsUseNickName(this.swigCPtr, this, str, j, j2, IFriendshipActionCallbackV2.getCPtr(iFriendshipActionCallbackV2), iFriendshipActionCallbackV2);
    }

    public boolean searchUserUseIdentifier(String str, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_searchUserUseIdentifier(this.swigCPtr, this, str, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public void setExpire() {
        internalJNI.FriendshipManager_setExpire(this.swigCPtr, this);
    }

    public boolean setFrdListTimestamp(long j) {
        return internalJNI.FriendshipManager_setFrdListTimestamp(this.swigCPtr, this, j);
    }

    public boolean setFrdSeq(long j) {
        return internalJNI.FriendshipManager_setFrdSeq(this.swigCPtr, this, j);
    }

    public boolean setProfile(SetProfileOption setProfileOption, IFriendshipCallback iFriendshipCallback) {
        return internalJNI.FriendshipManager_setProfile(this.swigCPtr, this, SetProfileOption.getCPtr(setProfileOption), setProfileOption, IFriendshipCallback.getCPtr(iFriendshipCallback), iFriendshipCallback);
    }

    public boolean setSnsProfile(SNSProfileItemVec sNSProfileItemVec, IFriendshipActionCallback iFriendshipActionCallback) {
        return internalJNI.FriendshipManager_setSnsProfile(this.swigCPtr, this, SNSProfileItemVec.getCPtr(sNSProfileItemVec), sNSProfileItemVec, IFriendshipActionCallback.getCPtr(iFriendshipActionCallback), iFriendshipActionCallback);
    }

    public void updateFriendGroupSeq(long j) {
        internalJNI.FriendshipManager_updateFriendGroupSeq(this.swigCPtr, this, j);
    }
}
