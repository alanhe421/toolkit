package com.xiaomi.mipush.sdk;

class ae implements Runnable {
    final /* synthetic */ e$a$a a;

    ae(e$a$a com_xiaomi_mipush_sdk_e_a_a) {
        this.a = com_xiaomi_mipush_sdk_e_a_a;
    }

    public void run() {
        if (this.a.a.size() != 0) {
            this.a.b();
        } else if (this.a.d != null) {
            this.a.d.cancel(false);
            this.a.d = null;
        }
    }
}
