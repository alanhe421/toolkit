package com.qq.reader.view.web;

import android.content.res.Resources;
import android.os.Handler.Callback;
import android.os.Message;
import com.qq.reader.module.rookie.presenter.a;
import com.tencent.feedback.proguard.R;

/* compiled from: PopRookieDialog */
class l$4 implements Callback {
    final /* synthetic */ l a;

    l$4(l lVar) {
        this.a = lVar;
    }

    public boolean handleMessage(Message message) {
        int i = 0;
        switch (message.what) {
            case 65552:
                if (!(this.a.f() || l.b(this.a))) {
                    Message obtainMessage = l.c(this.a).obtainMessage();
                    obtainMessage.obj = l.d(this.a);
                    obtainMessage.what = 65552;
                    l.c(this.a).sendMessage(obtainMessage);
                    a.a().b(false);
                    break;
                }
            case 65553:
                int dimensionPixelSize;
                String str = (String) message.obj;
                if (this.a.e != null) {
                    Resources resources = this.a.e.getResources();
                    dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.rookie_web_dialog_width);
                    i = resources.getDimensionPixelSize(R.dimen.rookie_web_dialog_height);
                } else {
                    dimensionPixelSize = 0;
                }
                this.a.b.loadUrl("javascript:" + str + "('" + dimensionPixelSize + ":" + i + "')");
                break;
        }
        return true;
    }
}
