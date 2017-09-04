package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.request.WtloginHelper.HelperThread;
import oicq.wlogin_sdk.tools.ErrMsg;

class WtloginHelper$HelperThread$5 implements Runnable {
    final /* synthetic */ HelperThread this$1;
    final /* synthetic */ int val$cancel;
    final /* synthetic */ int val$ret;

    WtloginHelper$HelperThread$5(HelperThread helperThread, int i, int i2) {
        this.this$1 = helperThread;
        this.val$cancel = i;
        this.val$ret = i2;
    }

    public void run() {
        HelperThread.access$500(this.this$1);
        if (this.val$cancel == 0) {
            async_context b = u.b(this.this$1.mUserSigInfo._seqence);
            ErrMsg errMsg = b._last_err_msg;
            WtloginHelper.access$100(this.this$1.mHelper).OnRefreshSMSData(this.this$1.mUserAccount, this.this$1.mSmsAppid, this.this$1.mUserSigInfo, b._t17b.a(), b._t17b.g(), this.val$ret, errMsg);
        }
    }
}
