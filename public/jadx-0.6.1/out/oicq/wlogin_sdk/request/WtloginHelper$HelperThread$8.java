package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.request.WtloginHelper.HelperThread;
import oicq.wlogin_sdk.tools.ErrMsg;

class WtloginHelper$HelperThread$8 implements Runnable {
    final /* synthetic */ HelperThread this$1;
    final /* synthetic */ int val$cancel;
    final /* synthetic */ int val$ret;

    WtloginHelper$HelperThread$8(HelperThread helperThread, int i, int i2) {
        this.this$1 = helperThread;
        this.val$cancel = i;
        this.val$ret = i2;
    }

    public void run() {
        HelperThread.access$500(this.this$1);
        if (this.val$cancel == 0) {
            ErrMsg errMsg = u.b(this.this$1.mUserSigInfo._seqence)._last_err_msg;
            u.c(this.this$1.mUserSigInfo._seqence);
            WtloginHelper.access$100(this.this$1.mHelper).onGetA1WithA1(this.this$1.mUserAccount, this.this$1.mAppid1, this.this$1.mDwMainSigMap, this.this$1.mSubAppid1, this.this$1.mAppName2, this.this$1.mSsoVer2, this.this$1.mAppid2, this.this$1.mSubAppid2, this.this$1.mAppVer2, this.this$1.mAppSign2, this.this$1.mUserSigInfo, this.this$1.mFastLoginInfo, this.val$ret, errMsg);
        }
    }
}
