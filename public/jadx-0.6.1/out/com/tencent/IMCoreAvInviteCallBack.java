package com.tencent;

import com.tencent.av.TIMAvManager;
import com.tencent.imcore.IAvInviteCallBack;

public class IMCoreAvInviteCallBack extends IAvInviteCallBack {
    private String identifer;

    public IMCoreAvInviteCallBack(String str) {
        this.identifer = str;
        swigReleaseOwnership();
    }

    public void onAvInviteBuf(byte[] bArr) {
        TIMAvManager.getInstanceById(this.identifer).MsgNotify(bArr);
    }
}
