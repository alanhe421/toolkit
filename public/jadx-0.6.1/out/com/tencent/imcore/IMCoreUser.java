package com.tencent.imcore;

public class IMCoreUser {
    private transient boolean swigCMemOwn;
    private transient long swigCPtr;

    protected IMCoreUser(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(IMCoreUser iMCoreUser) {
        return iMCoreUser == null ? 0 : iMCoreUser.swigCPtr;
    }

    public String a2() {
        return internalJNI.IMCoreUser_a2(this.swigCPtr, this);
    }

    public int applyDownloadFile(ApplyDownloadFileReq applyDownloadFileReq, IApplyDownloadFileCallback iApplyDownloadFileCallback) {
        return internalJNI.IMCoreUser_applyDownloadFile(this.swigCPtr, this, ApplyDownloadFileReq.getCPtr(applyDownloadFileReq), applyDownloadFileReq, IApplyDownloadFileCallback.getCPtr(iApplyDownloadFileCallback), iApplyDownloadFileCallback);
    }

    public int cancelAllPicupTask() {
        return internalJNI.IMCoreUser_cancelAllPicupTask(this.swigCPtr, this);
    }

    public void cancelTask(long j) {
        internalJNI.IMCoreUser_cancelTask(this.swigCPtr, this, j);
    }

    public void clearCookie() {
        internalJNI.IMCoreUser_clearCookie(this.swigCPtr, this);
    }

    public int compressPic(String str, String str2, int i) {
        return internalJNI.IMCoreUser_compressPic(this.swigCPtr, this, str, str2, i);
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_IMCoreUser(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    public boolean deleteGroupMsgs(String str, long j, long j2) {
        return internalJNI.IMCoreUser_deleteGroupMsgs(this.swigCPtr, this, str, j, j2);
    }

    public boolean deleteSession(SessionType sessionType, String str) {
        return internalJNI.IMCoreUser_deleteSession(this.swigCPtr, this, sessionType.swigValue(), str);
    }

    public boolean deleteSessionAndMsgs(SessionType sessionType, String str) {
        return internalJNI.IMCoreUser_deleteSessionAndMsgs(this.swigCPtr, this, sessionType.swigValue(), str);
    }

    public boolean fake() {
        return internalJNI.IMCoreUser_fake(this.swigCPtr, this);
    }

    protected void finalize() {
        delete();
    }

    public SWIGTYPE_p_std__shared_ptrT_imcore__IAvInviteCallBack_t getAvInviteCallBack() {
        return new SWIGTYPE_p_std__shared_ptrT_imcore__IAvInviteCallBack_t(internalJNI.IMCoreUser_getAvInviteCallBack(this.swigCPtr, this), true);
    }

    public FileTranser getFileTranser() {
        long IMCoreUser_getFileTranser = internalJNI.IMCoreUser_getFileTranser(this.swigCPtr, this);
        return IMCoreUser_getFileTranser == 0 ? null : new FileTranser(IMCoreUser_getFileTranser, false);
    }

    public FriendshipManager getFriendShipMgr() {
        return new FriendshipManager(internalJNI.IMCoreUser_getFriendShipMgr(this.swigCPtr, this), false);
    }

    public FriendshipProxy getFriendShipPrxy() {
        return new FriendshipProxy(internalJNI.IMCoreUser_getFriendShipPrxy(this.swigCPtr, this), false);
    }

    public GroupAssistant getGroupAssistant() {
        return new GroupAssistant(internalJNI.IMCoreUser_getGroupAssistant(this.swigCPtr, this), false);
    }

    public GroupManager getGroupMgr() {
        return new GroupManager(internalJNI.IMCoreUser_getGroupMgr(this.swigCPtr, this), false);
    }

    public SWIGTYPE_p_std__shared_ptrT_imcore__IGroupTipsEventCallback_t getGroupTipsEventCallback() {
        return new SWIGTYPE_p_std__shared_ptrT_imcore__IGroupTipsEventCallback_t(internalJNI.IMCoreUser_getGroupTipsEventCallback(this.swigCPtr, this), true);
    }

    public SWIGTYPE_p_std__shared_ptrT_imcore__IGroupUpdateCallback_t getGroupUpdateCallback() {
        return new SWIGTYPE_p_std__shared_ptrT_imcore__IGroupUpdateCallback_t(internalJNI.IMCoreUser_getGroupUpdateCallback(this.swigCPtr, this), true);
    }

    public int getImageUploadProgrss(long j) {
        return internalJNI.IMCoreUser_getImageUploadProgrss(this.swigCPtr, this, j);
    }

    public FriendProfile getSelfProfile() {
        long IMCoreUser_getSelfProfile = internalJNI.IMCoreUser_getSelfProfile(this.swigCPtr, this);
        return IMCoreUser_getSelfProfile == 0 ? null : new FriendProfile(IMCoreUser_getSelfProfile, false);
    }

    public Session getSession(long j) {
        return new Session(internalJNI.IMCoreUser_getSession__SWIG_0(this.swigCPtr, this, j), true);
    }

    public Session getSession(SessionType sessionType, String str) {
        return new Session(internalJNI.IMCoreUser_getSession__SWIG_1(this.swigCPtr, this, sessionType.swigValue(), str), true);
    }

    public PairVectorSession getSessionList() {
        return new PairVectorSession(internalJNI.IMCoreUser_getSessionList(this.swigCPtr, this), true);
    }

    public StatusManager getStatusMgr() {
        return new StatusManager(internalJNI.IMCoreUser_getStatusMgr(this.swigCPtr, this), false);
    }

    public boolean httpRequest(HttpMethod httpMethod, String str, byte[] bArr, EnvRequestClosure envRequestClosure) {
        return internalJNI.IMCoreUser_httpRequest(this.swigCPtr, this, httpMethod.swigValue(), str, bArr, EnvRequestClosure.getCPtr(envRequestClosure), envRequestClosure);
    }

    public String ip() {
        return internalJNI.IMCoreUser_ip(this.swigCPtr, this);
    }

    public void loginSyncCache() {
        internalJNI.IMCoreUser_loginSyncCache(this.swigCPtr, this);
    }

    public void loginSyncMsg() {
        internalJNI.IMCoreUser_loginSyncMsg(this.swigCPtr, this);
    }

    public int manualPush(byte[] bArr) {
        return internalJNI.IMCoreUser_manualPush(this.swigCPtr, this, bArr);
    }

    public Session newSession(SessionType sessionType, String str) {
        return new Session(internalJNI.IMCoreUser_newSession(this.swigCPtr, this, sessionType.swigValue(), str), true);
    }

    public boolean runOnIOThread(SWIGTYPE_p_std__functionT_void_fF_t sWIGTYPE_p_std__functionT_void_fF_t) {
        return internalJNI.IMCoreUser_runOnIOThread(this.swigCPtr, this, SWIGTYPE_p_std__functionT_void_fF_t.getCPtr(sWIGTYPE_p_std__functionT_void_fF_t));
    }

    public boolean runOnMainThread(SWIGTYPE_p_std__functionT_void_fF_t sWIGTYPE_p_std__functionT_void_fF_t) {
        return internalJNI.IMCoreUser_runOnMainThread(this.swigCPtr, this, SWIGTYPE_p_std__functionT_void_fF_t.getCPtr(sWIGTYPE_p_std__functionT_void_fF_t));
    }

    public boolean runOnTaskThread(SWIGTYPE_p_std__functionT_void_fF_t sWIGTYPE_p_std__functionT_void_fF_t) {
        return internalJNI.IMCoreUser_runOnTaskThread(this.swigCPtr, this, SWIGTYPE_p_std__functionT_void_fF_t.getCPtr(sWIGTYPE_p_std__functionT_void_fF_t));
    }

    public boolean sSORequest(String str, byte[] bArr, EnvRequestClosure envRequestClosure) {
        return internalJNI.IMCoreUser_sSORequest__SWIG_1(this.swigCPtr, this, str, bArr, EnvRequestClosure.getCPtr(envRequestClosure), envRequestClosure);
    }

    public boolean sSORequest(String str, byte[] bArr, EnvRequestClosure envRequestClosure, long j) {
        return internalJNI.IMCoreUser_sSORequest__SWIG_0(this.swigCPtr, this, str, bArr, EnvRequestClosure.getCPtr(envRequestClosure), envRequestClosure, j);
    }

    public void saveSelfProfile(FriendProfile friendProfile) {
        internalJNI.IMCoreUser_saveSelfProfile(this.swigCPtr, this, FriendProfile.getCPtr(friendProfile), friendProfile);
    }

    public long sdkAppId() {
        return internalJNI.IMCoreUser_sdkAppId(this.swigCPtr, this);
    }

    public void sendMsgToMultiUsers(StrVec strVec, Msg msg, IBatchOprCallback iBatchOprCallback) {
        internalJNI.IMCoreUser_sendMsgToMultiUsers(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec, Msg.getCPtr(msg), msg, IBatchOprCallback.getCPtr(iBatchOprCallback), iBatchOprCallback);
    }

    public long sessionCount() {
        return internalJNI.IMCoreUser_sessionCount(this.swigCPtr, this);
    }

    public void setA2(String str) {
        internalJNI.IMCoreUser_setA2(this.swigCPtr, this, str);
    }

    public void setAvInviteCallBack(IAvInviteCallBack iAvInviteCallBack) {
        internalJNI.IMCoreUser_setAvInviteCallBack(this.swigCPtr, this, IAvInviteCallBack.getCPtr(iAvInviteCallBack), iAvInviteCallBack);
    }

    public void setGroupTipsEventCallback(IGroupTipsEventCallback iGroupTipsEventCallback) {
        internalJNI.IMCoreUser_setGroupTipsEventCallback(this.swigCPtr, this, IGroupTipsEventCallback.getCPtr(iGroupTipsEventCallback), iGroupTipsEventCallback);
    }

    public void setGroupUpdateCallback(IGroupUpdateCallback iGroupUpdateCallback) {
        internalJNI.IMCoreUser_setGroupUpdateCallback(this.swigCPtr, this, IGroupUpdateCallback.getCPtr(iGroupUpdateCallback), iGroupUpdateCallback);
    }

    public void setIp(String str) {
        internalJNI.IMCoreUser_setIp(this.swigCPtr, this, str);
    }

    public void setSdkAppId(long j) {
        internalJNI.IMCoreUser_setSdkAppId(this.swigCPtr, this, j);
    }

    public long submitUploadTask(String str, IImageUploadCallback iImageUploadCallback) {
        return internalJNI.IMCoreUser_submitUploadTask__SWIG_1(this.swigCPtr, this, str, IImageUploadCallback.getCPtr(iImageUploadCallback), iImageUploadCallback);
    }

    public long submitUploadTask(String str, IImageUploadCallback iImageUploadCallback, int i) {
        return internalJNI.IMCoreUser_submitUploadTask__SWIG_0(this.swigCPtr, this, str, IImageUploadCallback.getCPtr(iImageUploadCallback), iImageUploadCallback, i);
    }
}
