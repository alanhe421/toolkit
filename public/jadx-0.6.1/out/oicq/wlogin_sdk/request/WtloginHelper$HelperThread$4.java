package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.request.WtloginHelper.HelperThread;
import oicq.wlogin_sdk.tools.ErrMsg;

class WtloginHelper$HelperThread$4 implements Runnable {
    final /* synthetic */ HelperThread this$1;
    final /* synthetic */ int val$cancel;
    final /* synthetic */ int val$ret;

    WtloginHelper$HelperThread$4(HelperThread helperThread, int i, int i2) {
        this.this$1 = helperThread;
        this.val$cancel = i;
        this.val$ret = i2;
    }

    public void run() {
        HelperThread.access$500(this.this$1);
        if (this.val$cancel == 0) {
            ErrMsg errMsg = u.b(this.this$1.mUserSigInfo._seqence)._last_err_msg;
            if (o.I) {
                if (this.this$1.mST == null) {
                    WtloginHelper.access$100(this.this$1.mHelper).OnCheckWebsigAndGetSt(this.this$1.mUserAccount, this.this$1.mUserInput, this.this$1.mUserSigInfo, this.val$ret, errMsg);
                } else {
                    WtloginHelper.access$100(this.this$1.mHelper).OnCheckWebsigAndGetSt(this.this$1.mUserAccount, this.this$1.mUserInput, this.this$1.mUserSigInfo, this.this$1.mST, this.val$ret, errMsg);
                }
            } else if (this.this$1.mST == null) {
                WtloginHelper.access$100(this.this$1.mHelper).OnCheckPictureAndGetSt(this.this$1.mUserAccount, this.this$1.mUserInput, this.this$1.mUserSigInfo, this.val$ret, errMsg);
            } else {
                WtloginHelper.access$100(this.this$1.mHelper).OnCheckPictureAndGetSt(this.this$1.mUserAccount, this.this$1.mUserInput, this.this$1.mUserSigInfo, this.this$1.mST, this.val$ret, errMsg);
            }
        }
    }
}
