package com.qq.reader.view;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.appconfig.a.d;

/* compiled from: ZoomDialog */
class ay$6 implements OnClickListener {
    final /* synthetic */ ay a;

    ay$6(ay ayVar) {
        this.a = ayVar;
    }

    public void onClick(View view) {
        if (ay.a(this.a) != null) {
            float I = d.I(this.a.e());
            switch (this.a.b(I)) {
                case 0:
                    ay.a(this.a).a(this.a.d(I));
                    ay.b(this.a);
                    ay.d(this.a).setEnabled(true);
                    return;
                case 1:
                    ay.a(this.a).a(this.a.d(I));
                    ay.b(this.a);
                    ay.c(this.a).setEnabled(false);
                    return;
                case 2:
                    ay.d(this.a).setEnabled(true);
                    ay.c(this.a).setEnabled(false);
                    return;
                default:
                    return;
            }
        }
    }
}
