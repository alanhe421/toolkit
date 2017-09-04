package com.qq.reader.plugin;

import android.os.Message;
import com.qq.reader.common.readertask.ordinal.b;

class PlugInDefaultActivity$3 extends b {
    final /* synthetic */ PlugInDefaultActivity a;

    PlugInDefaultActivity$3(PlugInDefaultActivity plugInDefaultActivity) {
        this.a = plugInDefaultActivity;
    }

    public void a(boolean z) {
        if (z) {
            Message message = new Message();
            message.what = 6002;
            this.a.p.sendMessage(message);
        }
    }
}
