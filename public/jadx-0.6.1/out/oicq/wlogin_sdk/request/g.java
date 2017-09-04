package oicq.wlogin_sdk.request;

import android.widget.Toast;
import java.util.TimerTask;

/* compiled from: alert_thread */
class g extends TimerTask {
    final /* synthetic */ Toast a;
    final /* synthetic */ int b;
    final /* synthetic */ f c;

    g(f fVar, Toast toast, int i) {
        this.c = fVar;
        this.a = toast;
        this.b = i;
    }

    public void run() {
        this.c.a(this.a, this.b + 1);
    }
}
