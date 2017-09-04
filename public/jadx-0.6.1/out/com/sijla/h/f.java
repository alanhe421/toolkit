package com.sijla.h;

import android.content.Context;
import android.content.Intent;
import com.sijla.bean.TruthInfo;
import com.sijla.common.a;
import com.sijla.j.b;
import com.tencent.smtt.sdk.TbsDownloadConfig;
import org.json.JSONObject;

public class f implements a {
    private final String a = "scclient";
    private final String b = "scapptt";
    private Context c;
    private JSONObject d = new JSONObject();

    public f(Context context, JSONObject jSONObject) {
        this.c = context;
        this.d = jSONObject;
    }

    public void run() {
        try {
            if (b.a(this.c, "scclient", (long) TbsDownloadConfig.DEFAULT_RETRY_INTERVAL_SEC)) {
                com.sijla.j.a.b.a(b.j() + TruthInfo.class.getSimpleName() + ".csv-" + System.currentTimeMillis(), b.e(b.a(this.c, com.sijla.i.b.a(this.c, this.d))));
            }
            if (a(this.d)) {
                b.o(this.c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean a(JSONObject jSONObject) {
        return b.a(this.c, "scapptt", (long) jSONObject.optInt("scanapp", 86400));
    }

    public void a() {
    }

    public void b() {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public void a(Intent intent) {
    }
}
