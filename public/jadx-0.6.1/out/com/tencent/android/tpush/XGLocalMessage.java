package com.tencent.android.tpush;

import com.tencent.android.tpush.a.a;
import com.tencent.android.tpush.service.d.f;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import org.json.JSONObject;

/* compiled from: ProGuard */
public class XGLocalMessage {
    private static final String a = XGLocalMessage.class.getSimpleName();
    private int b = 1;
    private String c = "";
    private String d = "";
    private String e = "";
    private String f = "00";
    private String g = "00";
    private int h = 1;
    private int i = 1;
    private int j = 1;
    private int k = 0;
    private int l = 1;
    private String m = "";
    private String n = "";
    private String o = "";
    private int p = 1;
    private String q = "";
    private String r = "";
    private String s = "";
    private String t = "";
    private String u = "";
    private String v = "{}";
    private long w;
    private int x = 0;
    private int y = 2592000;
    private long z = (System.currentTimeMillis() + (((long) this.y) * 1000));

    public long getExpirationTimeMs() {
        return this.z;
    }

    public void setExpirationTimeMs(long j) {
        if (j > System.currentTimeMillis()) {
            this.y = (int) ((j - System.currentTimeMillis()) / 1000);
            if (this.y < 0) {
                this.y = Integer.MAX_VALUE;
            }
            this.z = j;
        }
    }

    public int getTtl() {
        return this.y;
    }

    public int getType() {
        return this.b;
    }

    public void setType(int i) {
        this.b = i;
    }

    public String getTitle() {
        return this.c;
    }

    public void setTitle(String str) {
        this.c = str;
    }

    public String getContent() {
        return this.d;
    }

    public void setContent(String str) {
        this.d = str;
    }

    public void setCustomContent(HashMap hashMap) {
        this.v = new JSONObject(hashMap).toString();
    }

    public String getCustom_content() {
        return this.v;
    }

    public String getHour() {
        if (this.f.length() < 1) {
            return "00";
        }
        if (this.f.length() <= 0 || this.f.length() >= 2) {
            return this.f;
        }
        return "0" + this.f;
    }

    public void setHour(String str) {
        this.f = str;
    }

    public String getMin() {
        if (this.g.length() < 1) {
            return "00";
        }
        if (this.g.length() <= 0 || this.g.length() >= 2) {
            return this.g;
        }
        return "0" + this.g;
    }

    public void setMin(String str) {
        this.g = str;
    }

    public long getBuilderId() {
        return this.w;
    }

    public void setBuilderId(long j) {
        this.w = j;
    }

    public String getDate() {
        if (!f.a(this.e)) {
            try {
                this.e = this.e.substring(0, 8);
                Long.parseLong(this.e);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                simpleDateFormat.setLenient(false);
                simpleDateFormat.parse(this.e);
            } catch (Throwable e) {
                a.c(a, "XGLocalMessage.getDate()", e);
                return new SimpleDateFormat("yyyyMMdd").format(new Date());
            } catch (Throwable e2) {
                a.c(a, "XGLocalMessage.getDate()", e2);
                return new SimpleDateFormat("yyyyMMdd").format(new Date());
            }
        }
        return this.e;
    }

    public void setDate(String str) {
        this.e = str;
    }

    public void setRing(int i) {
        this.h = i;
    }

    public int getRing() {
        return this.h;
    }

    public void setVibrate(int i) {
        this.i = i;
    }

    public int getVibrate() {
        return this.i;
    }

    public void setLights(int i) {
        this.j = i;
    }

    public int getLights() {
        return this.j;
    }

    public void setIcon_type(int i) {
        this.k = i;
    }

    public int getIcon_type() {
        return this.k;
    }

    public void setStyle_id(int i) {
        this.l = i;
    }

    public int getStyle_id() {
        return this.l;
    }

    public void setRing_raw(String str) {
        this.m = str;
    }

    public String getRing_raw() {
        return this.m;
    }

    public void setIcon_res(String str) {
        this.n = str;
    }

    public String getIcon_res() {
        return this.n;
    }

    public void setSmall_icon(String str) {
        this.o = str;
    }

    public String getSmall_icon() {
        return this.o;
    }

    public void setAction_type(int i) {
        this.p = i;
    }

    public int getAction_type() {
        return this.p;
    }

    public void setActivity(String str) {
        this.q = str;
    }

    public String getActivity() {
        return this.q;
    }

    public void setUrl(String str) {
        this.r = str;
    }

    public String getUrl() {
        return this.r;
    }

    public void setIntent(String str) {
        this.s = str;
    }

    public String getIntent() {
        return this.s;
    }

    public void setPackageDownloadUrl(String str) {
        this.t = str;
    }

    public String getPackageDownloadUrl() {
        return this.t;
    }

    public void setPackageName(String str) {
        this.u = str;
    }

    public String getPackageName() {
        return this.u;
    }

    public int getNotificationId() {
        return this.x;
    }

    public void setNotificationId(int i) {
        this.x = i;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("XGLocalMessage [type=").append(this.b).append(", title=").append(this.c).append(", content=").append(this.d).append(", date=").append(this.e).append(", hour=").append(this.f).append(", min=").append(this.g).append(", builderId=").append(this.w).append("]");
        return stringBuilder.toString();
    }
}
