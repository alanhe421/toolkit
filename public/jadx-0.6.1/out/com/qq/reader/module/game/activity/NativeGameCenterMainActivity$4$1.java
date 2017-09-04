package com.qq.reader.module.game.activity;

import android.content.DialogInterface;
import com.qq.reader.common.utils.t;
import com.qq.reader.cservice.adv.b;
import com.qq.reader.module.game.activity.NativeGameCenterMainActivity.4;
import com.qq.reader.view.r;

class NativeGameCenterMainActivity$4$1 extends r {
    final /* synthetic */ 4 a;

    NativeGameCenterMainActivity$4$1(4 4) {
        this.a = 4;
    }

    public t a() {
        return NativeGameCenterMainActivity.g(this.a.a).c();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        b.b = false;
    }
}
