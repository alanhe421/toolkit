package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.request.WtloginHelper.HelperThread;
import oicq.wlogin_sdk.tools.ErrMsg;

class WtloginHelper$HelperThread$7 implements Runnable {
    final /* synthetic */ HelperThread this$1;
    final /* synthetic */ int val$cancel;
    final /* synthetic */ int val$ret;

    WtloginHelper$HelperThread$7(HelperThread helperThread, int i, int i2) {
        this.this$1 = helperThread;
        this.val$cancel = i;
        this.val$ret = i2;
    }

    public void run() {
        HelperThread.access$500(this.this$1);
        if (this.val$cancel == 0) {
            ErrMsg errMsg = u.b(this.this$1.mUserSigInfo._seqence)._last_err_msg;
            u.c(this.this$1.mUserSigInfo._seqence);
            if (this.this$1.mDwDstAppid == WtloginHelper.access$1100(this.this$1.this$0)) {
                this.this$1.mDwDstAppid = this.this$1.mDwSubDstAppid;
                this.this$1.mDwSubDstAppid = 0;
            }
            if (this.this$1.mPromise != null) {
                errMsg.setType(this.val$ret);
                if (this.val$ret == 0) {
                    this.this$1.mPromise.Done(null);
                } else if (this.val$ret == -1000) {
                    this.this$1.mPromise.Timeout(errMsg);
                } else {
                    this.this$1.mPromise.Failed(errMsg);
                }
            } else if (WtloginHelper.access$100(this.this$1.mHelper) == null) {
            } else {
                if (this.this$1.mDwDstSubAppidList != null || WtloginHelper.access$200(this.this$1.this$0).e()) {
                    WtloginHelper.access$100(this.this$1.mHelper).OnGetStWithoutPasswd(this.this$1.mUserAccount, this.this$1.mDwAppid, this.this$1.mDwDstAppid, this.this$1.mDwMainSigMap, this.this$1.mDwSubDstAppid, this.this$1.mDwDstSubAppidList, this.this$1.mUserSigInfo, this.this$1.mST, this.val$ret, errMsg);
                } else {
                    WtloginHelper.access$100(this.this$1.mHelper).OnGetStWithoutPasswd(this.this$1.mUserAccount, this.this$1.mDwAppid, this.this$1.mDwDstAppid, this.this$1.mDwMainSigMap, this.this$1.mDwSubDstAppid, this.this$1.mUserSigInfo, this.val$ret, errMsg);
                }
            }
        }
    }
}
