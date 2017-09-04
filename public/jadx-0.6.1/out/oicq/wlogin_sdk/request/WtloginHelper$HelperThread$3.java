package oicq.wlogin_sdk.request;

import oicq.wlogin_sdk.request.WtloginHelper.HelperThread;
import oicq.wlogin_sdk.tools.ErrMsg;
import oicq.wlogin_sdk.tools.util;

class WtloginHelper$HelperThread$3 implements Runnable {
    final /* synthetic */ HelperThread this$1;
    final /* synthetic */ int val$cancel;
    final /* synthetic */ int val$ret;

    WtloginHelper$HelperThread$3(HelperThread helperThread, int i, int i2) {
        this.this$1 = helperThread;
        this.val$cancel = i;
        this.val$ret = i2;
    }

    public void run() {
        HelperThread.access$500(this.this$1);
        if (this.val$cancel == 0) {
            if (WtloginHelper.access$100(this.this$1.mHelper) == null) {
                util.LOGW("login helper listener is null", this.this$1.mUserAccount);
                return;
            }
            async_context b = u.b(this.this$1.mUserSigInfo._seqence);
            ErrMsg errMsg = b._last_err_msg;
            this.this$1.mPictureData = b._t105.a();
            WtloginHelper.access$100(this.this$1.mHelper).OnRefreshPictureData(this.this$1.mUserAccount, this.this$1.mUserSigInfo, this.this$1.mPictureData, this.val$ret, errMsg);
        }
    }
}
