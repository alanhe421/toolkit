package com.tencent.mm.sdk.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.b.b;
import com.tencent.mm.sdk.b.f;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import oicq.wlogin_sdk.request.WtloginHelper;
import tencent.tls.platform.SigType;

public final class a {

    public static class a {
        public String b;
        public String c;
        public String d;
        public Bundle e;
        public int flags = -1;
    }

    public static boolean a(Context context, a aVar) {
        if (context == null || aVar == null) {
            b.a("MicroMsg.SDK.MMessageAct", "send fail, invalid argument");
            return false;
        } else if (f.c(aVar.b)) {
            b.a("MicroMsg.SDK.MMessageAct", "send fail, invalid targetPkgName, targetPkgName = " + aVar.b);
            return false;
        } else {
            if (f.c(aVar.c)) {
                aVar.c = aVar.b + ".wxapi.WXEntryActivity";
            }
            b.c("MicroMsg.SDK.MMessageAct", "send, targetPkgName = " + aVar.b + ", targetClassName = " + aVar.c);
            Intent intent = new Intent();
            intent.setClassName(aVar.b, aVar.c);
            if (aVar.e != null) {
                intent.putExtras(aVar.e);
            }
            String packageName = context.getPackageName();
            intent.putExtra(ConstantsAPI.SDK_VERSION, 570490883);
            intent.putExtra(ConstantsAPI.APP_PACKAGE, packageName);
            intent.putExtra(ConstantsAPI.CONTENT, aVar.d);
            intent.putExtra(ConstantsAPI.CHECK_SUM, com.tencent.mm.sdk.a.a.b.a(aVar.d, 570490883, packageName));
            if (aVar.flags == -1) {
                intent.addFlags(SigType.TLS).addFlags(WtloginHelper.SigType.WLOGIN_PT4Token);
            } else {
                intent.setFlags(aVar.flags);
            }
            try {
                context.startActivity(intent);
                b.c("MicroMsg.SDK.MMessageAct", "send mm message, intent=" + intent);
                return true;
            } catch (Exception e) {
                b.a("MicroMsg.SDK.MMessageAct", "send fail, ex = %s", new Object[]{e.getMessage()});
                return false;
            }
        }
    }
}
