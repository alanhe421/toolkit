package com.qq.reader.common.push;

import android.content.Context;
import android.text.TextUtils;
import com.qq.reader.common.push.pushAction.b;
import com.qq.reader.common.push.pushAction.c;
import com.qq.reader.common.push.pushAction.d;
import com.qq.reader.common.push.pushAction.e;
import com.qq.reader.common.push.pushAction.f;
import com.qq.reader.common.push.pushAction.g;
import com.qq.reader.common.push.pushAction.h;
import com.qq.reader.common.push.pushAction.i;
import com.qq.reader.common.push.pushAction.j;
import com.qq.reader.common.push.pushAction.l;
import com.qq.reader.common.push.pushAction.m;
import org.json.JSONObject;

/* compiled from: PushHandle */
public class a {
    public static String a = "XG_OR_XIAOMI_THROUGH";
    public static String b = "XIAOMI_ARRIVED";
    public static String c = "XIAOMI_CLICKED";

    public static void a(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            i iVar = null;
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("qqaction", "");
                if (optString.equals("bookupdate")) {
                    iVar = new d(context);
                } else if (optString.equals("bookactive")) {
                    iVar = new c(context);
                } else if (optString.equals("bookmessage")) {
                    iVar = new h(context);
                } else if (optString.equals("bookqurl")) {
                    iVar = new j(context);
                } else if (optString.equals("actupdate")) {
                    iVar = new b(context);
                } else if (optString.equals("skinlistupdate")) {
                    iVar = new m(context);
                } else if (optString.equals("chapterupdate")) {
                    iVar = new e(context);
                } else if (optString.equals("abtest")) {
                    iVar = new com.qq.reader.common.push.pushAction.a(context);
                } else if (optString.equals("delbookcover")) {
                    iVar = new f(context);
                } else if (optString.equals("richmedia")) {
                    iVar = new l(context);
                } else if (optString.equals("ipintercept")) {
                    iVar = new g(context);
                }
                if (iVar != null) {
                    iVar.b(str2);
                    iVar.a(jSONObject);
                    return;
                }
                com.qq.reader.common.monitor.j.c("ERROR：  receive action = " + optString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
