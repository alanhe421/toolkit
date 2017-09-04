package qalsdk;

import com.tencent.qalsdk.sdk.ac;
import com.tencent.qalsdk.sdk.e;
import com.tencent.qalsdk.util.QLog;

/* compiled from: Foreground */
class ao implements Runnable {
    final /* synthetic */ an a;

    ao(an anVar) {
        this.a = anVar;
    }

    public void run() {
        QLog.d("Foreground", 4, "onActivityPaused " + as.a());
        if (as.a() && ac.a().b) {
            e.b().p();
        }
    }
}
