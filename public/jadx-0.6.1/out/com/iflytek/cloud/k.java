package com.iflytek.cloud;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

class k extends Handler {
    final /* synthetic */ a a;

    k(a aVar, Looper looper) {
        this.a = aVar;
        super(looper);
    }

    public void handleMessage(Message message) {
        if (this.a.b != null) {
            switch (message.what) {
                case 1:
                    this.a.b.a();
                    return;
                case 2:
                    Bundle bundle = (Bundle) message.obj;
                    this.a.b.a(bundle.getInt("percent"), bundle.getInt("begpos"), bundle.getInt("endpos"), bundle.getString("spellinfo"));
                    return;
                case 3:
                    this.a.b.b();
                    return;
                case 4:
                    this.a.b.c();
                    return;
                case 5:
                    this.a.b.a(message.arg1, message.arg2, ((Integer) message.obj).intValue());
                    return;
                case 6:
                    this.a.b.a((SpeechError) message.obj);
                    return;
                case 7:
                    Message message2 = (Message) message.obj;
                    if (message2 != null) {
                        this.a.b.a(message2.what, message2.arg1, message2.arg2, (Bundle) message2.obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
