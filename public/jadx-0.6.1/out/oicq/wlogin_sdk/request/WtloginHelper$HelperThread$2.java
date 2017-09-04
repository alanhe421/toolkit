package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.request.WtloginHelper.HelperThread;
import oicq.wlogin_sdk.tools.ErrMsg;
import oicq.wlogin_sdk.tools.util;

class WtloginHelper$HelperThread$2 implements Runnable {
    final /* synthetic */ HelperThread this$1;
    final /* synthetic */ int val$cancel;
    final /* synthetic */ int val$ret;

    WtloginHelper$HelperThread$2(HelperThread helperThread, int i, int i2) {
        this.this$1 = helperThread;
        this.val$cancel = i;
        this.val$ret = i2;
    }

    public void run() {
        HelperThread.access$500(this.this$1);
        if (this.val$cancel == 0) {
            ErrMsg errMsg = u.b(this.this$1.mUserSigInfo._seqence)._last_err_msg;
            if (WtloginHelper.access$100(this.this$1.mHelper) == null) {
                util.LOGW("login helper listener is null", this.this$1.mUserAccount);
            } else if (this.this$1.mDwSubAppidList == null) {
                if (this.this$1.mIsSmslogin) {
                    WtloginHelper.access$100(this.this$1.mHelper).OnGetStViaSMSVerifyLogin(this.this$1.mUserAccount, this.this$1.mDwAppid, this.this$1.mDwMainSigMap, this.this$1.mDwSubDstAppid, this.this$1.mUserSigInfo, this.val$ret, errMsg);
                } else {
                    WtloginHelper.access$100(this.this$1.mHelper).OnGetStWithPasswd(this.this$1.mUserAccount, this.this$1.mDwAppid, this.this$1.mDwMainSigMap, this.this$1.mDwSubDstAppid, this.this$1.mUserPasswd, this.this$1.mUserSigInfo, this.val$ret, errMsg);
                }
            } else if (this.this$1.mIsSmslogin) {
                WtloginHelper.access$100(this.this$1.mHelper).OnGetStViaSMSVerifyLogin(this.this$1.mUserAccount, this.this$1.mDwAppid, this.this$1.mDwMainSigMap, this.this$1.mDwSubDstAppid, this.this$1.mDwSubAppidList, this.this$1.mUserSigInfo, this.this$1.mST, this.val$ret, errMsg);
            } else {
                WtloginHelper.access$100(this.this$1.mHelper).OnGetStWithPasswd(this.this$1.mUserAccount, this.this$1.mDwAppid, this.this$1.mDwMainSigMap, this.this$1.mDwSubDstAppid, this.this$1.mDwSubAppidList, this.this$1.mUserPasswd, this.this$1.mUserSigInfo, this.this$1.mST, this.val$ret, errMsg);
            }
        }
    }
}
