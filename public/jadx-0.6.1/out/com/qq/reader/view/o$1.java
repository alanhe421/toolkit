package com.qq.reader.view;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;

/* compiled from: LuckyMoneyDialog */
class o$1 implements OnClickListener {
    final /* synthetic */ o a;

    o$1(o oVar) {
        this.a = oVar;
    }

    public void onClick(View view) {
        this.a.dismiss();
        try {
            aj ajVar = new aj(this.a.b(), e.cc + o.a(this.a), e.n + this.a.a, this.a.b, this.a.c, null, 13);
            ajVar.a(this.a.b);
            ajVar.f_();
        } catch (Exception e) {
            c.e("LuckyMoneyDialog", e.getMessage());
        }
        i.a("event_D109", null, this.a.b());
    }
}
