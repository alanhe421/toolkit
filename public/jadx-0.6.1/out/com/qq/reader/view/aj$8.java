package com.qq.reader.view;

import android.graphics.Bitmap;
import com.qq.reader.common.c.a;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.readertask.ordinal.b;
import com.qq.reader.common.utils.ao;
import java.io.File;

/* compiled from: ShareDialog */
class aj$8 extends b {
    final /* synthetic */ aj a;

    aj$8(aj ajVar) {
        this.a = ajVar;
    }

    public void a(boolean z) {
        if (z) {
            aj.a(this.a, new File(a.dc + ao.r(aj.d(this.a))).getPath());
            Bitmap a = ao.a(aj.e(this.a), 300, 300, false);
            if (a != null) {
                aj.a(this.a, aj.a(a));
                if (aj.f(this.a) != a) {
                    a.recycle();
                }
            }
        }
        if (aj.b(this.a) != null) {
            aj.b(this.a).runOnUiThread(new Runnable(this) {
                final /* synthetic */ aj$8 a;

                {
                    this.a = r1;
                }

                public void run() {
                    try {
                        aj.b(this.a.a, aj.g(this.a.a));
                    } catch (Exception e) {
                        c.e("ShareDialog", e.getMessage());
                    }
                }
            });
        }
    }
}
