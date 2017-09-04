package com.qq.reader.common.web.js;

import android.app.Activity;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Message;
import com.qq.reader.common.web.js.a.a.b;

public class JSAdv extends b {
    private Handler a = null;
    private Activity b;
    private Callback c;

    public void setCallback(Callback callback) {
        this.c = callback;
    }

    public JSAdv(Handler handler) {
        this.a = handler;
    }

    public void setAct(Activity activity) {
        this.b = activity;
    }

    public void showAdv(String str) {
    }

    public void closeAdv() {
        this.a.sendEmptyMessage(65539);
    }

    public void closerefresh() {
        this.a.sendEmptyMessage(65540);
    }

    public void loadSuccess() {
        if (this.c != null) {
            Message obtain = Message.obtain();
            obtain.what = 65552;
            this.c.handleMessage(obtain);
        }
    }

    public void getDialogWH(String str) {
        if (this.c != null) {
            Message obtain = Message.obtain();
            obtain.obj = str;
            obtain.what = 65553;
            this.c.handleMessage(obtain);
        }
    }
}
