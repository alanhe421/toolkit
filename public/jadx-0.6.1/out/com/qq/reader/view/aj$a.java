package com.qq.reader.view;

import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/* compiled from: ShareDialog */
class aj$a extends Handler {
    final /* synthetic */ aj a;

    private aj$a(aj ajVar) {
        this.a = ajVar;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 100005:
                if (aj.a(this.a) != null && aj.a(this.a).f()) {
                    aj.a(this.a).dismiss();
                }
                if (this.a.f != null && this.a.f.isShowing()) {
                    this.a.f.dismiss();
                    return;
                }
                return;
            case 100006:
                if (aj.a(this.a) != null && aj.a(this.a).f()) {
                    aj.a(this.a).dismiss();
                }
                if (this.a.f != null && this.a.f.isShowing()) {
                    this.a.f.dismiss();
                }
                Toast.makeText(aj.b(this.a), "网络异常，请稍后重试", 0).show();
                return;
            default:
                return;
        }
    }
}
