package com.tencent.mm.sdk.a.a;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.tencent.mm.sdk.b.b;
import com.tencent.mm.sdk.b.f;
import com.tencent.mm.sdk.constants.ConstantsAPI;

public final class a {

    public static class a {
        public String d;
        public Bundle e;
        public String f;
        public String g;
    }

    public static boolean a(Context context, a aVar) {
        if (context == null || aVar == null) {
            b.a("MicroMsg.SDK.MMessage", "send fail, invalid argument");
            return false;
        } else if (f.c(aVar.g)) {
            b.a("MicroMsg.SDK.MMessage", "send fail, action is null");
            return false;
        } else {
            String str = null;
            if (!f.c(aVar.f)) {
                str = aVar.f + ".permission.MM_MESSAGE";
            }
            Intent intent = new Intent(aVar.g);
            if (aVar.e != null) {
                intent.putExtras(aVar.e);
            }
            String packageName = context.getPackageName();
            intent.putExtra(ConstantsAPI.SDK_VERSION, 570490883);
            intent.putExtra(ConstantsAPI.APP_PACKAGE, packageName);
            intent.putExtra(ConstantsAPI.CONTENT, aVar.d);
            intent.putExtra(ConstantsAPI.CHECK_SUM, b.a(aVar.d, 570490883, packageName));
            context.sendBroadcast(intent, str);
            b.c("MicroMsg.SDK.MMessage", "send mm message, intent=" + intent + ", perm=" + str);
            return true;
        }
    }
}
