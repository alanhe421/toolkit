package oicq.wlogin_sdk.request;

import android.content.Context;
import oicq.wlogin_sdk.tools.util;

/* compiled from: delete_expire_log */
public class h extends Thread {
    private Context a;

    public h(Context context) {
        this.a = context;
        setName("WtCleanThread");
    }

    public void run() {
        util.deleteExpireLog(this.a);
    }
}
