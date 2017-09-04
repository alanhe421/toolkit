package com.qq.reader.plugin;

import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;
import java.lang.ref.SoftReference;

class PlugInDefaultActivity$9 extends Handler {
    final /* synthetic */ PlugInDefaultActivity a;

    PlugInDefaultActivity$9(PlugInDefaultActivity plugInDefaultActivity) {
        this.a = plugInDefaultActivity;
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 6001:
                try {
                    this.a.a.b(false);
                    return;
                } catch (Exception e) {
                    this.a.a.b(false);
                    f.a("PlugInDefaultActivity", "inner_handler " + e.toString());
                    return;
                }
            case 6002:
                try {
                    if (PlugInDefaultActivity.e(this.a) == null || PlugInDefaultActivity.e(this.a).get() == null) {
                        PlugInDefaultActivity.a(this.a, new SoftReference(ao.j(this.a.n.s())));
                    }
                    if (PlugInDefaultActivity.e(this.a).get() != null) {
                        this.a.k.setImageBitmap((Bitmap) PlugInDefaultActivity.e(this.a).get());
                    } else {
                        this.a.k.setImageResource(R.drawable.plugin_default);
                    }
                    this.a.a.a(false);
                    return;
                } catch (Exception e2) {
                    this.a.a.a(false);
                    f.a("PlugInDefaultActivity", "inner_handler " + e2.toString());
                    return;
                }
            default:
                return;
        }
    }
}
