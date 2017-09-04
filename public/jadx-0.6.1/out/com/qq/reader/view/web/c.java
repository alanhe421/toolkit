package com.qq.reader.view.web;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.offline.b;
import com.qq.reader.common.web.js.JSContent;
import com.qq.reader.common.web.js.JSOfflineInterface;
import com.qq.reader.common.web.js.JSSendSMS;
import com.qq.reader.common.web.js.JSSwitchBrowser;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.FixedWebView;

/* compiled from: CommonOrderDialog */
public class c extends BaseDialog {
    private FixedWebView a;
    private Activity b;
    private com.qq.reader.common.web.js.a.c c;
    private final a d;

    /* compiled from: CommonOrderDialog */
    private class a extends Handler {
        final /* synthetic */ c a;

        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 90004:
                    b bVar = (b) message.obj;
                    this.a.a.a("javascript:" + bVar.a() + "(" + bVar.b() + ")");
                    return;
                default:
                    return;
            }
        }
    }

    public void f_() {
        g();
        com.qq.reader.common.offline.c.a(this.b).a(this.d, "READOVER");
        super.f_();
        i.a("event_reader_bookstore", null, this.b.getApplicationContext());
    }

    public void d() {
        this.c.a();
        com.qq.reader.common.offline.c.a(this.b).a("READOVER");
    }

    private void g() {
        this.c = new com.qq.reader.common.web.js.a.c();
        this.c.b(this.a);
        this.a.getSettings().setJavaScriptEnabled(true);
        this.c.a(this.a);
        this.c.a(new JSSwitchBrowser(this.b, this), "jump");
        this.c.a(new JSContent(this.b), "JSContent");
        this.c.a(new JSSendSMS(this.b), "sendvip");
        this.c.a(new JSOfflineInterface(this.b, this.d, "READOVER"), "mclient");
    }
}
