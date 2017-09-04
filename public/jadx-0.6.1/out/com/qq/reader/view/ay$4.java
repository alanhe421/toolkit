package com.qq.reader.view;

import android.app.Activity;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.j;

/* compiled from: ZoomDialog */
class ay$4 implements OnCheckedChangeListener {
    final /* synthetic */ Activity a;
    final /* synthetic */ ay b;

    ay$4(ay ayVar, Activity activity) {
        this.b = ayVar;
        this.a = activity;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (z) {
            this.b.g().a(this.b.b);
            d.d(this.a.getApplicationContext(), this.b.b);
            j.a(71, 1);
        }
    }
}
