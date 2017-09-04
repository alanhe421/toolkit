package com.qq.reader.liveshow.c;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import java.util.ArrayList;
import java.util.List;

/* compiled from: LivePresenterDispatch */
public class j {
    List<i> a = new ArrayList();
    Handler b;
    HandlerThread c = new HandlerThread("LivePresenterDispatch");

    public j() {
        this.c.start();
        this.b = new Handler(this.c.getLooper(), new Callback(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                for (i a : this.a.a) {
                    a.a(message);
                }
                return true;
            }
        });
    }

    public void a(i iVar) {
        this.a.add(iVar);
    }

    public void a() {
        this.c.quit();
        this.c = null;
        this.a.clear();
    }

    public void a(Message message) {
        this.b.sendMessage(message);
    }

    public Message b() {
        return this.b.obtainMessage();
    }
}
