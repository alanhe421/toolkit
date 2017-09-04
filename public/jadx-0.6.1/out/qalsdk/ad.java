package qalsdk;

import android.os.Handler;
import android.os.Message;
import com.tencent.qalsdk.util.QLog;

/* compiled from: WifiDetectImpl */
class ad extends Handler {
    final /* synthetic */ ac a;

    ad(ac acVar) {
        this.a = acVar;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1000:
                String str = (String) message.obj;
                QLog.d("WifiDetector", 1, "WIFI detect delayed try!");
                ac.a(this.a, str);
                return;
            default:
                return;
        }
    }
}
