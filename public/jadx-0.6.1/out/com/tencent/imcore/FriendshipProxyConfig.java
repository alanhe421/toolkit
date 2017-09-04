package com.tencent.imcore;

public class FriendshipProxyConfig {
    protected transient boolean swigCMemOwn;
    private transient long swigCPtr;

    public FriendshipProxyConfig() {
        this(internalJNI.new_FriendshipProxyConfig(), true);
    }

    protected FriendshipProxyConfig(long j, boolean z) {
        this.swigCMemOwn = z;
        this.swigCPtr = j;
    }

    protected static long getCPtr(FriendshipProxyConfig friendshipProxyConfig) {
        return friendshipProxyConfig == null ? 0 : friendshipProxyConfig.swigCPtr;
    }

    public synchronized void delete() {
        if (this.swigCPtr != 0) {
            if (this.swigCMemOwn) {
                this.swigCMemOwn = false;
                internalJNI.delete_FriendshipProxyConfig(this.swigCPtr);
            }
            this.swigCPtr = 0;
        }
    }

    protected void finalize() {
        delete();
    }

    public StrVec getCustom() {
        long FriendshipProxyConfig_custom_get = internalJNI.FriendshipProxyConfig_custom_get(this.swigCPtr, this);
        return FriendshipProxyConfig_custom_get == 0 ? null : new StrVec(FriendshipProxyConfig_custom_get, false);
    }

    public boolean getEnable() {
        return internalJNI.FriendshipProxyConfig_enable_get(this.swigCPtr, this);
    }

    public long getFlags() {
        return internalJNI.FriendshipProxyConfig_flags_get(this.swigCPtr, this);
    }

    public IFriendshipProxyListener getListener() {
        long FriendshipProxyConfig_listener_get = internalJNI.FriendshipProxyConfig_listener_get(this.swigCPtr, this);
        return FriendshipProxyConfig_listener_get == 0 ? null : new IFriendshipProxyListener(FriendshipProxyConfig_listener_get, false);
    }

    public void setCustom(StrVec strVec) {
        internalJNI.FriendshipProxyConfig_custom_set(this.swigCPtr, this, StrVec.getCPtr(strVec), strVec);
    }

    public void setEnable(boolean z) {
        internalJNI.FriendshipProxyConfig_enable_set(this.swigCPtr, this, z);
    }

    public void setFlags(long j) {
        internalJNI.FriendshipProxyConfig_flags_set(this.swigCPtr, this, j);
    }

    public void setListener(IFriendshipProxyListener iFriendshipProxyListener) {
        internalJNI.FriendshipProxyConfig_listener_set(this.swigCPtr, this, IFriendshipProxyListener.getCPtr(iFriendshipProxyListener), iFriendshipProxyListener);
    }
}
