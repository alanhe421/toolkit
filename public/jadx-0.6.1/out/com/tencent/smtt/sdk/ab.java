package com.tencent.smtt.sdk;

import android.content.Context;
import com.tencent.smtt.utils.TbsLog;
import com.tencent.smtt.utils.i;
import java.io.File;

class ab extends Thread {
    final /* synthetic */ Context a;
    final /* synthetic */ Context b;
    final /* synthetic */ z c;

    ab(z zVar, Context context, Context context2) {
        this.c = zVar;
        this.a = context;
        this.b = context2;
    }

    public void run() {
        TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp thread start");
        try {
            File e = this.c.e(this.a);
            File e2 = this.c.e(this.b);
            i.a(e, e2, new ac(this));
            i.a(e, e2, new ad(this));
            TbsLog.i("TbsInstaller", "TbsInstaller--quickDexOptForThirdPartyApp thread done");
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }
}
