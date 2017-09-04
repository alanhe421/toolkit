package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.request.WtloginHelper.HelperThread;
import oicq.wlogin_sdk.tools.ErrMsg;
import oicq.wlogin_sdk.tools.util;

class WtloginHelper$HelperThread$17 implements Runnable {
    final /* synthetic */ HelperThread this$1;
    final /* synthetic */ int val$cancel;

    WtloginHelper$HelperThread$17(HelperThread helperThread, int i) {
        this.this$1 = helperThread;
        this.val$cancel = i;
    }

    public void run() {
        HelperThread.access$500(this.this$1);
        if (this.val$cancel == 0) {
            if (WtloginHelper.access$100(this.this$1.mHelper) == null) {
                util.LOGW("login helper listener is null", this.this$1.mUserAccount);
            } else {
                WtloginHelper.access$100(this.this$1.mHelper).OnException(new ErrMsg(), this.this$1.mReqType, this.this$1.mUserSigInfo);
            }
        }
    }
}
