package com.tencent;

import com.tencent.imcore.GroupTipsElem;
import com.tencent.imcore.IGroupTipsEventCallback;
import com.tencent.imsdk.IMMsfCoreProxy;
import com.tencent.imsdk.QLog;

public class IMCoreGroupEventCallback extends IGroupTipsEventCallback {
    static String TAG = "IMCoreGroupEventCallback";
    private String identifier;

    public IMCoreGroupEventCallback(String str) {
        this.identifier = str;
        swigReleaseOwnership();
    }

    public void onGroupTipsEvent(GroupTipsElem groupTipsElem) {
        TIMGroupEventListener groupEventListener = TIMManager.getInstanceById(this.identifier).getGroupEventListener();
        if (groupEventListener == null) {
            QLog.d(TAG, 1, "no group event listener registered/" + this.identifier);
            return;
        }
        IMMsfCoreProxy.mainHandler.post(new aq(this, groupEventListener, new TIMGroupTipsElem(groupTipsElem)));
    }
}
