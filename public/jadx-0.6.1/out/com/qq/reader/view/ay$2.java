package com.qq.reader.view;

import android.app.Activity;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.j;

/* compiled from: ZoomDialog */
class ay$2 implements OnCheckedChangeListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ ay b;

    ay$2(ay ayVar, Activity activity) {
        this.b = ayVar;
        this.a = activity;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.b.g().a(this.b.d);
            d.d(this.a.getApplicationContext(), this.b.d);
            j.a(69, 1);
        }
    }
}
