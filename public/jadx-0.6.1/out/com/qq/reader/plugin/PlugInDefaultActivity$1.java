package com.qq.reader.plugin;

import android.view.View;
import android.view.View.OnClickListener;

class PlugInDefaultActivity$1 implements OnClickListener {
    final /* synthetic */ PlugInDefaultActivity a;

    PlugInDefaultActivity$1(PlugInDefaultActivity plugInDefaultActivity) {
        this.a = plugInDefaultActivity;
    }

    public void onClick(View view) {
        if (this.a.a != null) {
            switch (this.a.a.e()) {
                case 7:
                    this.a.a.k();
                    this.a.a.r();
                    return;
                default:
                    return;
            }
        }
    }
}
