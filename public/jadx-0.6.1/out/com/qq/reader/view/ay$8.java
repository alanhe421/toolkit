package com.qq.reader.view;

import android.os.Handler;
import android.os.Message;
import com.qq.reader.appconfig.a.d;

/* compiled from: ZoomDialog */
class ay$8 extends Handler {
    final /* synthetic */ ay a;

    ay$8(ay ayVar) {
        this.a = ayVar;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 60002:
                ay.e(this.a).setText(((int) d.I(this.a.e())) + "号");
                return;
            default:
                return;
        }
    }
}
