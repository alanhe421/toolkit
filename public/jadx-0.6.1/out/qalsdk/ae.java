package qalsdk;

import com.tencent.qalsdk.util.QLog;
import qalsdk.w.a;

/* compiled from: WifiDetectImpl */
class ae implements a {
    final /* synthetic */ ac a;

    ae(ac acVar) {
        this.a = acVar;
    }

    public void a(int i, int i2, String str, Object obj) {
        if (str != null && str.length() > 10) {
            str = str.substring(0, 10);
        }
        if (QLog.isColorLevel()) {
            QLog.d("WifiDetector", 2, "WIFI detect onEchoResult, taskId: " + i + " result: " + i2 + " actualContent: " + str);
        }
        ac.a(this.a, ac.a(this.a, i2), (String) obj);
        ac.b(this.a, ac.a(this.a) - (1 << i));
        if (ac.a(this.a) == 0) {
            this.a.d();
        }
    }
}
