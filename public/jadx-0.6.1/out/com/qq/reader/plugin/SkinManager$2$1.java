package com.qq.reader.plugin;

import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.readertask.ordinal.ReaderDBTask;
import com.qq.reader.common.utils.ao;
import com.sina.weibo.sdk.exception.WeiboAuthException;

class SkinManager$2$1 extends ReaderDBTask {
    final /* synthetic */ com.qq.reader.plugin.w.AnonymousClass1 this$1;
    final /* synthetic */ String val$id;
    final /* synthetic */ String val$netEnable;
    final /* synthetic */ String val$skinName;

    SkinManager$2$1(com.qq.reader.plugin.w.AnonymousClass1 anonymousClass1, String str, String str2, String str3) {
        this.this$1 = anonymousClass1;
        this.val$id = str;
        this.val$netEnable = str2;
        this.val$skinName = str3;
    }

    public void run() {
        v.b().a(this.val$id, 0, 0, this.val$netEnable, 1);
        String bS = d.bS(this.this$1.c);
        if (this.val$id.equals(bS)) {
            this.this$1.f.e(bS);
            if (WeiboAuthException.DEFAULT_AUTH_ERROR_CODE.equals(this.val$netEnable)) {
                ao.a(new Runnable(this) {
                    final /* synthetic */ SkinManager$2$1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        ao.c(this.a.val$skinName, ReaderApplication.getApplicationImp());
                    }
                });
            }
        }
    }
}
