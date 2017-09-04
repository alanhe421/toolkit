package com.qq.reader.common.push.pushAction;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.d;
import com.qq.reader.common.monitor.debug.c;
import org.json.JSONObject;

/* compiled from: SyncAction */
public class n extends i {
    public n(Context context) {
        super(context);
    }

    public void a(JSONObject jSONObject) {
        c.a("Push", "SyncAction handle --->>");
        Intent intent = new Intent();
        intent.setAction("com.qq.reader.push.sync");
        d.a(a()).a(intent);
    }
}
