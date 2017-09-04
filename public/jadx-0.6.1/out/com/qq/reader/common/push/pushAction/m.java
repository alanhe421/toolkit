package com.qq.reader.common.push.pushAction;

import android.content.Context;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.push.a;
import com.qq.reader.common.utils.ao;
import com.qq.reader.cservice.adv.c;
import com.qq.reader.plugin.w;
import org.json.JSONObject;

/* compiled from: SkinListUpdateAction */
public class m extends i {
    public m(Context context) {
        super(context);
    }

    public void a(JSONObject jSONObject) {
        if ((a.a.equals(this.b) || a.b.equals(this.b)) && jSONObject != null) {
            String optString = jSONObject.optString("skinlist_version");
            String optString2 = jSONObject.optString("min_version_code");
            String f = w.b().f();
            try {
                if (ao.v(ReaderApplication.getApplicationImp()) >= Integer.parseInt(optString2) && optString != null && !optString.equals(f)) {
                    c.a((Object) "TYPE_SKIN_LIST_UPDATE", true);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
