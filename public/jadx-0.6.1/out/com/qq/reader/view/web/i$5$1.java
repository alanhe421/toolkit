package com.qq.reader.view.web;

import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.common.login.a;
import com.qq.reader.common.login.c;
import com.qq.reader.view.web.i.5;

/* compiled from: PopNativeDialog */
class i$5$1 implements OnClickListener {
    final /* synthetic */ 5 a;

    i$5$1(5 5) {
        this.a = 5;
    }

    public void onClick(View view) {
        if (this.a.a == null) {
            return;
        }
        if (!this.a.a.p() || c.b()) {
            i.a(this.a.c, this.a.a);
            return;
        }
        AnonymousClass1 anonymousClass1 = new a(this) {
            final /* synthetic */ i$5$1 a;

            {
                this.a = r1;
            }

            public void a(int i) {
                switch (i) {
                    case 1:
                        if (this.a.a.a != null) {
                            i.a(this.a.a.c, this.a.a.a);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        };
        Message obtainMessage = this.a.b.obtainMessage();
        obtainMessage.obj = anonymousClass1;
        obtainMessage.what = 65542;
        this.a.b.sendMessage(obtainMessage);
    }
}
