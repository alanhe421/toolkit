package com.qq.reader.plugin;

import android.widget.Toast;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.utils.ao;
import com.tencent.connect.common.Constants;
import com.tencent.feedback.proguard.R;

class SkinManager$2$2 extends ReaderDBTask {
    final /* synthetic */ com.qq.reader.plugin.w.AnonymousClass1 this$1;
    final /* synthetic */ String val$id;
    final /* synthetic */ String val$netLatestVersion;
    final /* synthetic */ x val$skinPluginData;

    SkinManager$2$2(com.qq.reader.plugin.w.AnonymousClass1 anonymousClass1, String str, String str2, x xVar) {
        this.this$1 = anonymousClass1;
        this.val$id = str;
        this.val$netLatestVersion = str2;
        this.val$skinPluginData = xVar;
    }

    public void run() {
        v.b().a(this.val$id, 0, 7, null, 2);
        v.b().a(this.val$id, null, this.val$netLatestVersion, null);
        boolean equals = this.val$id.equals(d.bS(this.this$1.c));
        boolean z = false;
        if (equals) {
            this.this$1.f.e(Constants.DEFAULT_UIN);
            d.C(this.this$1.c, Constants.DEFAULT_UIN);
            z = ao.o();
            ao.v(Constants.DEFAULT_UIN);
            ao.a(new Runnable(this) {
                final /* synthetic */ SkinManager$2$2 a;

                {
                    this.a = r1;
                }

                public void run() {
                    Toast.makeText(ReaderApplication.getApplicationImp(), ReaderApplication.getApplicationImp().getString(R.string.skin_update_not_available_tip, new Object[]{this.a.val$skinPluginData.l()}), 0).show();
                }
            });
        }
        if (ao.f(this.this$1.c) && this.val$skinPluginData.q()) {
            this.this$1.f.b(this.val$skinPluginData, true, equals, z);
        }
    }
}
