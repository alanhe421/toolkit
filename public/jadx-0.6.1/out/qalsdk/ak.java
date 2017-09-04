package qalsdk;

import android.os.Handler;
import android.os.Message;

/* compiled from: PushManager */
class ak extends Handler {
    final /* synthetic */ aj a;

    ak(aj ajVar) {
        this.a = ajVar;
    }

    public synchronized void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.a.a(((Boolean) message.obj).booleanValue());
                break;
        }
    }
}
