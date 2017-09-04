package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.request.WtloginHelper.HelperThread;

class WtloginHelper$HelperThread$9 implements Runnable {
    final /* synthetic */ HelperThread this$1;
    final /* synthetic */ int val$cancel;
    final /* synthetic */ int val$ret;

    WtloginHelper$HelperThread$9(HelperThread helperThread, int i, int i2) {
        this.this$1 = helperThread;
        this.val$cancel = i;
        this.val$ret = i2;
    }

    public void run() {
        HelperThread.access$500(this.this$1);
        if (this.val$cancel == 0) {
            if (this.this$1.mReqContext.is_register_req()) {
                WtloginHelper.access$1400(this.this$1.mHelper, this.this$1.mUserAccount, this.this$1.mDwAppid, this.this$1.mRole, this.this$1.mReqContext, this.this$1.mUserSigInfo, this.val$ret);
            } else if (this.this$1.mReqContext.is_code2d_func_req()) {
                WtloginHelper.access$1500(this.this$1.mHelper, this.this$1.mUserAccount, this.this$1.mDwAppid, this.this$1.mRole, this.this$1.mReqContext, this.this$1.mUserSigInfo, this.val$ret);
            } else if (this.this$1.mReqContext.is_devlock_req()) {
                WtloginHelper.access$1600(this.this$1.mHelper, this.this$1.mUserAccount, this.this$1.mDwAppid, this.this$1.mRole, this.this$1.mReqContext, this.this$1.mUserSigInfo, this.val$ret);
            } else {
                WtloginHelper.access$100(this.this$1.mHelper).OnRequestTransport(this.this$1.mUserAccount, this.this$1.mDwAppid, this.this$1.mRole, this.this$1.mReqContext, this.this$1.mUserSigInfo, this.val$ret);
            }
        }
    }
}
