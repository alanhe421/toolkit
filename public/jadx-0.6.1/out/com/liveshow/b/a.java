package com.liveshow.b;

import android.app.Activity;
import android.content.Context;
import com.qq.reader.common.login.c;
import com.qq.reader.common.utils.o;
import com.qq.reader.liveshow.b.b;
import java.util.HashMap;
import java.util.Map;

/* compiled from: AccountImpl */
public class a implements b {
    public void a(Activity activity) {
        o.a(activity, null);
    }

    public void a(Context context, com.qq.reader.liveshow.b.a<Map<String, String>> aVar) {
        Map hashMap = new HashMap();
        if (context != null) {
            hashMap.put("user_nick", c.c().a());
            hashMap.put("user_avatar", c.c().b());
            hashMap.put("user_id", c.c().c());
            hashMap.put("user_balance", c.c().g(context) + "");
        }
        aVar.a(hashMap);
    }
}
