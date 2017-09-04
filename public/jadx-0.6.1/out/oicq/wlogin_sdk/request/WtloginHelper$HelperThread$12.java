package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.request.WtloginHelper.HelperThread;

class WtloginHelper$HelperThread$12 implements Runnable {
    final /* synthetic */ HelperThread this$1;
    final /* synthetic */ int val$cancel;
    final /* synthetic */ int val$ret;

    WtloginHelper$HelperThread$12(HelperThread helperThread, int i, int i2) {
        this.this$1 = helperThread;
        this.val$cancel = i;
        this.val$ret = i2;
    }

    public void run() {
        HelperThread.access$500(this.this$1);
        if (this.val$cancel == 0) {
            WtloginHelper.access$100(this.this$1.mHelper).OnVerifySMSVerifyLoginCode(this.this$1.mUserAccount, this.this$1.mMsgCode, this.this$1.mUserSigInfo, this.val$ret, u.b(this.this$1.mUserSigInfo._seqence)._last_err_msg);
        }
    }
}
