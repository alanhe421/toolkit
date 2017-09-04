package com.qq.reader.common.push.pushAction;

import android.content.Context;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import org.json.JSONObject;

/* compiled from: DelBookCoverAction */
public class f extends i {
    public f(Context context) {
        super(context);
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                String optString = jSONObject.optString("bookids");
                if (optString != null && optString.length() != 0) {
                    for (String valueOf : optString.split(",")) {
                        long longValue = Long.valueOf(valueOf).longValue();
                        if (longValue != 0) {
                            c.d(ao.g(longValue));
                        }
                    }
                }
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.a(i.a, e.toString());
            }
        }
    }
}
