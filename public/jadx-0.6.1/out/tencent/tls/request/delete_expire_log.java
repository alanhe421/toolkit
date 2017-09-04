package tencent.tls.request;

import android.content.Context;
import tencent.tls.tools.util;

public class delete_expire_log extends Thread {
    private Context _context;

    public delete_expire_log(Context context) {
        this._context = context;
        setName("WtCleanThread");
    }

    public void run() {
        util.deleteExpireLog(this._context);
    }
}
