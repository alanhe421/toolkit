package com.tencent.qalsdk.sdk;

import com.tencent.qalsdk.base.a;
import com.tencent.qalsdk.base.remote.ToServiceMsg;

/* compiled from: MsfMsgUtil */
public class x {
    public static ToServiceMsg a(String str) {
        ToServiceMsg toServiceMsg = new ToServiceMsg(str, "0", a.aP);
        toServiceMsg.setMsfCommand(MsfCommand.openConn);
        toServiceMsg.setNeedCallback(false);
        toServiceMsg.setTimeout(a.ap);
        return toServiceMsg;
    }

    public static boolean a(ToServiceMsg toServiceMsg) {
        if (toServiceMsg.getAttributes().get(a.bc) != null) {
            return ((Boolean) toServiceMsg.getAttributes().get(a.bc)).booleanValue();
        }
        return false;
    }
}
