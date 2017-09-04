package oicq.wlogin_sdk.request;

import java.util.TimerTask;
import oicq.wlogin_sdk.request.WtloginHelper.HelperThread;

class WtloginHelper$HelperThread$1 extends TimerTask {
    final /* synthetic */ HelperThread this$1;

    WtloginHelper$HelperThread$1(HelperThread helperThread) {
        this.this$1 = helperThread;
    }

    public void run() {
        this.this$1.start();
    }
}
