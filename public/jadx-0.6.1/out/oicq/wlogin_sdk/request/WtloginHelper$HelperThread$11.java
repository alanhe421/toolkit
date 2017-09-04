package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.request.WtloginHelper.HelperThread;

class WtloginHelper$HelperThread$11 implements Runnable {
    final /* synthetic */ HelperThread this$1;
    final /* synthetic */ int val$cancel;
    final /* synthetic */ int val$ret;

    WtloginHelper$HelperThread$11(HelperThread helperThread, int i, int i2) {
        this.this$1 = helperThread;
        this.val$cancel = i;
        this.val$ret = i2;
    }

    public void run() {
        HelperThread.access$500(this.this$1);
        if (this.val$cancel == 0) {
            async_context b = u.b(this.this$1.mUserSigInfo._seqence);
            WtloginHelper.access$100(this.this$1.mHelper).OnCheckSMSVerifyLoginAccount(this.this$1.mAppid1, this.this$1.mSubAppid1, this.this$1.mUserAccount, b._smslogin_msg, b._smslogin_msgcnt, b._smslogin_timelimit, this.this$1.mUserSigInfo, this.val$ret, b._last_err_msg);
        }
    }
}
