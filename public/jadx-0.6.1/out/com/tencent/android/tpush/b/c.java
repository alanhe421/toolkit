package com.tencent.android.tpush.b;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.common.Constants;
import com.tencent.android.tpush.common.m;
import com.tencent.android.tpush.encrypt.Rijndael;
import com.tencent.android.tpush.service.channel.security.f;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class c {
    private static String f = null;
    private long a = 0;
    private long b = 0;
    private String c = "";
    private String d = null;
    private Context e = null;
    private Intent g = null;

    public c(Context context, Intent intent) {
        this.e = context.getApplicationContext();
        this.g = intent;
    }

    private boolean a() {
        if (f == null) {
            f = m.a(this.e, ".xg.push.cm.vrf", "");
        }
        if (f.contains(this.c)) {
            return true;
        }
        f = this.c + "," + f;
        if (f.length() > 10240) {
            f = f.substring(0, 2048);
        }
        m.b(this.e, ".xg.push.cm.vrf", f);
        return false;
    }

    public boolean a(i iVar, long j, long j2, long j3) {
        a g = iVar.g();
        if (j3 > 0) {
            JSONObject jSONObject = new JSONObject(Rijndael.decrypt(this.g.getStringExtra("title")));
            a.d(Constants.LogTag, "title encry obj:" + jSONObject);
            this.c = f.a(com.tencent.android.tpush.service.channel.security.a.a(jSONObject.getString("cipher"), 0));
            String[] split = this.c.split("_");
            this.b = Long.valueOf(split[0]).longValue();
            this.d = split[1].toUpperCase();
            this.a = Long.valueOf(split[2]).longValue();
            Object obj = 1;
            if (this.b != j2) {
                obj = null;
            } else if (j2 == 0) {
                obj = j == this.a ? 1 : null;
            }
            if (obj == null || a() || j2 != this.b || !g.a().equalsIgnoreCase(this.d)) {
                return false;
            }
            return true;
        }
        String str;
        StringBuilder stringBuilder = new StringBuilder(128);
        stringBuilder.append(iVar.c()).append(j3).append(this.e.getPackageName()).append(TextUtils.isEmpty(g.e()) ? "" : g.e()).append(TextUtils.isEmpty(g.f()) ? "" : g.f());
        String g2 = g.g();
        if (TextUtils.isEmpty(g2) || new JSONObject(g2).length() == 0) {
            g2 = "";
        } else {
            g2 = new JSONObject(g2).toString();
        }
        stringBuilder.append(g2);
        if (g instanceof d) {
            e m = ((d) g).m();
            StringBuilder append = stringBuilder.append(TextUtils.isEmpty(m.f) ? "" : m.f);
            if (TextUtils.isEmpty(m.d)) {
                str = "";
            } else {
                str = m.d;
            }
            append = append.append(str);
            if (TextUtils.isEmpty(m.b)) {
                str = "";
            } else {
                str = m.b;
            }
            append.append(str);
        }
        str = stringBuilder.toString();
        g2 = Constants.LOCAL_MESSAGE_FLAG + com.tencent.android.tpush.encrypt.a.a(str);
        long a = m.a(this.e, g2, 0);
        m.b(this.e, g2);
        a -= System.currentTimeMillis();
        a.c(Constants.LogTag, str + ",localMsgTag:" + g2 + ",diff:" + a);
        if (a >= 0) {
            return true;
        }
        return false;
    }
}
