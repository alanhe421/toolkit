package com.qq.reader.plugin;

class PlugInDefaultActivity$6 implements Runnable {
    final /* synthetic */ PlugInDefaultActivity a;

    PlugInDefaultActivity$6(PlugInDefaultActivity plugInDefaultActivity) {
        this.a = plugInDefaultActivity;
    }

    public void run() {
        if (this.a.a.l()) {
            PlugInDefaultActivity.b(this.a);
        } else {
            PlugInDefaultActivity.d(this.a).postDelayed(PlugInDefaultActivity.c(this.a), 200);
        }
    }
}
