package com.qq.reader.view.web;

import android.os.Handler;
import android.os.Message;
import com.qq.reader.common.offline.b;

/* compiled from: PopWebDialog */
class m$a extends Handler {
    final /* synthetic */ m a;

    public m$a(m mVar) {
        this.a = mVar;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 65539:
                this.a.dismiss();
                return;
            case 90004:
                b bVar = (b) message.obj;
                m.a(this.a).a("javascript:" + bVar.a() + "(" + bVar.b() + ")");
                return;
            default:
                return;
        }
    }
}
