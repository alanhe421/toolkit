package com.qq.reader.module.bookstore.qnative.item;

import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.StrikethroughSpan;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.j;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: DetailPageBookItem */
public class o extends s {
    @Deprecated
    private int A = 0;
    @Deprecated
    private int B = 0;
    @Deprecated
    private int C = 0;
    @Deprecated
    private int D = 0;
    private int E = 0;
    private String F;
    private long G = 0;
    private String H;
    private boolean I = false;
    private int J;
    private float K;
    private String L;
    private String M;
    private String N;
    private String O;
    private String P;
    private List<f> Q;
    private ArrayList<a> R;
    private boolean S = false;
    private boolean T = false;
    private boolean U = false;
    private boolean V = false;
    private long a;
    private boolean b;
    private String c = null;
    private String d = null;
    private String e = null;
    private String f = null;
    private int g;
    private String h = null;
    private int i = 75;
    private int j = 0;
    private String k = null;
    private String l = null;
    private String m = null;
    private boolean n = true;
    private boolean o = false;
    private boolean p = false;
    private int q = 0;
    private int r = 0;
    private String s = null;
    private String t = null;
    private JSONObject u = null;
    private String v = null;
    private int w = 0;
    private int x = 0;
    private boolean y = false;
    private String z = null;

    /* compiled from: DetailPageBookItem */
    public static class a {
        public String a;
        public String b;
    }

    public void parseData(JSONObject jSONObject) {
        if (jSONObject != null) {
            int i;
            JSONObject optJSONObject;
            this.P = jSONObject.optString("authoricon");
            JSONArray optJSONArray = jSONObject.optJSONArray("statisticInfo");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                this.R = new ArrayList();
                for (i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                    a aVar = new a();
                    if (optJSONObject2 != null) {
                        aVar.a = optJSONObject2.optString("name");
                        aVar.b = optJSONObject2.optString("number");
                        this.R.add(aVar);
                    }
                }
            }
            JSONObject optJSONObject3 = jSONObject.optJSONObject("book");
            this.J = jSONObject.optInt("packageid");
            if (optJSONObject3 != null) {
                this.b = optJSONObject3.optBoolean("isOrdered");
                this.H = optJSONObject3.optString("editorwds");
                this.a = optJSONObject3.optLong("id");
                this.c = optJSONObject3.optString("title");
                this.d = optJSONObject3.optString("author");
                this.e = optJSONObject3.optString("categoryname");
                this.f = optJSONObject3.optString("categoryshortname");
                this.g = optJSONObject3.optInt("price");
                this.h = optJSONObject3.optString("intro");
                this.i = optJSONObject3.optInt("star");
                this.j = optJSONObject3.optInt("totalwords");
                this.w = optJSONObject3.optInt("free");
                this.x = optJSONObject3.optInt("discount");
                this.y = optJSONObject3.optBoolean("isVip");
                this.z = optJSONObject3.optString("dismsg");
                this.G = optJSONObject3.optLong("catid");
                this.F = optJSONObject3.optString("cover");
                optJSONObject = jSONObject.optJSONObject("TMR");
                this.N = optJSONObject3.optString("centerAuthorId");
                this.O = optJSONObject3.optString("categoryInfoV4SlaveId");
                if (optJSONObject != null) {
                    this.S = optJSONObject.optBoolean("first");
                    this.T = optJSONObject.optBoolean("second");
                    this.U = optJSONObject.optBoolean("third");
                }
            }
            optJSONArray = jSONObject.optJSONArray("entryInfo");
            if (optJSONArray != null && optJSONArray.length() > 0) {
                if (this.Q != null) {
                    this.Q.clear();
                    this.Q = null;
                }
                this.Q = new ArrayList();
                for (i = 0; i < optJSONArray.length(); i++) {
                    f fVar = new f();
                    fVar.parseData(optJSONArray.optJSONObject(i));
                    this.Q.add(fVar);
                }
            }
            optJSONObject = jSONObject.optJSONObject("scoreInfo");
            if (optJSONObject != null) {
                try {
                    this.K = Float.valueOf(optJSONObject.optString("score")).floatValue();
                } catch (Exception e) {
                    c.e("DetailPageBookItem", e.getMessage());
                }
                this.L = optJSONObject.optString("scoretext");
                this.M = optJSONObject.optString("intro");
            }
            this.V = jSONObject.optBoolean("hasFanRank");
            this.E = jSONObject.optInt("fansNum");
            optJSONObject3 = jSONObject.optJSONObject("prices");
            if (optJSONObject3 != null) {
                this.k = optJSONObject3.optString("first");
                this.l = optJSONObject3.optString("second");
            }
            optJSONObject3 = jSONObject.optJSONObject("detailmsg");
            if (optJSONObject3 != null) {
                this.m = optJSONObject3.optString("detailmsg");
                this.o = optJSONObject3.optBoolean("needOpenRent", false);
                this.p = optJSONObject3.optBoolean("needOpenOnePrice", false);
                this.q = optJSONObject3.optInt("price", 0);
                this.r = optJSONObject3.optInt("day", 0);
                this.n = optJSONObject3.optBoolean("needOpenVip");
                this.I = optJSONObject3.optBoolean("needOpenPackageVip");
                this.s = optJSONObject3.optString("xmtag");
                this.t = optJSONObject3.optString("xztag");
                this.u = optJSONObject3.optJSONObject("limitLeftTime");
                if (this.u != null) {
                    i = this.u.optInt("first");
                    int optInt = this.u.optInt("second");
                    int optInt2 = this.u.optInt("third");
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(this.m);
                    stringBuffer.append(" 还剩");
                    stringBuffer.append(i);
                    stringBuffer.append("天");
                    stringBuffer.append(optInt);
                    stringBuffer.append("小时");
                    stringBuffer.append(optInt2);
                    stringBuffer.append("分钟");
                    this.v = stringBuffer.toString();
                }
            }
        }
    }

    public String a() {
        if (ao.m(this.a)) {
            return ao.g(this.a);
        }
        return this.F;
    }

    public String b() {
        return this.P;
    }

    public String c() {
        return this.N;
    }

    public String d() {
        return this.O;
    }

    public long e() {
        return this.a;
    }

    public String f() {
        return this.c;
    }

    public String g() {
        return this.d;
    }

    public String h() {
        return this.f;
    }

    public String i() {
        try {
            return Html.fromHtml(this.h).toString();
        } catch (Exception e) {
            return this.h;
        }
    }

    public String j() {
        return j.a(this.j);
    }

    public boolean k() {
        return this.n;
    }

    public boolean l() {
        return this.o;
    }

    public boolean m() {
        return this.p;
    }

    public boolean n() {
        return this.I;
    }

    public String o() {
        if (this.w == 1) {
            return "免费";
        }
        if (!ao.s(this.v) && !ao.s(this.s)) {
            return "限免";
        }
        if (this.x > 0 && this.x < 100 && !ao.s(this.t)) {
            return "特价";
        }
        if (!ao.s(this.t) && !ao.s(this.v)) {
            return "特价";
        }
        if (this.p) {
            return "特价";
        }
        if (this.w == 2) {
            return "包月";
        }
        return null;
    }

    public SpannableStringBuilder p() {
        String.valueOf(this.g) + "书币";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (ao.s(this.v)) {
            if (this.I) {
                if (ao.s(this.l)) {
                    spannableStringBuilder.append(this.k);
                } else {
                    spannableStringBuilder.append(this.z + this.l);
                }
            } else if (this.n) {
                if (ao.s(this.l) || this.y) {
                    spannableStringBuilder.append(this.k);
                } else {
                    spannableStringBuilder.append(this.z + this.l);
                }
            } else if (this.o) {
                if (ao.s(this.l)) {
                    spannableStringBuilder.append(this.k);
                } else {
                    spannableStringBuilder.append(this.z + this.l);
                }
            } else if (this.p) {
                if (ao.s(this.l)) {
                    spannableStringBuilder.append(this.k);
                } else {
                    spannableStringBuilder.append(this.z + this.l);
                }
            } else if (ao.s(this.m)) {
                if (ao.s(this.l)) {
                    spannableStringBuilder.append(this.k);
                } else {
                    spannableStringBuilder.append(this.z + this.l);
                }
            } else if (!(this.m.contains("已购买") || this.l == null)) {
                spannableStringBuilder.append(this.l);
                spannableStringBuilder.setSpan(new StrikethroughSpan(), 0, this.l.length(), 17);
            }
        } else if (!ao.s(this.l) && ao.s(this.s)) {
            spannableStringBuilder.append(this.k + " " + this.l);
            spannableStringBuilder.setSpan(new StrikethroughSpan(), 0, this.k.length(), 17);
        }
        return spannableStringBuilder;
    }

    public String q() {
        if (ao.s(this.v)) {
            if (this.o) {
                return this.m;
            }
            if (this.I) {
                return this.m;
            }
            if (this.p) {
                return this.m;
            }
            if (this.n) {
                return this.m;
            }
            return this.m;
        } else if (this.p) {
            return this.m;
        } else {
            return this.v;
        }
    }

    public long r() {
        return this.G;
    }

    public String s() {
        return this.H;
    }

    public int t() {
        return this.J;
    }

    public int u() {
        return this.q;
    }

    public int v() {
        return this.r;
    }

    public String w() {
        return this.M;
    }

    public float x() {
        return this.K;
    }

    public String y() {
        return this.L;
    }

    public List<f> z() {
        return this.Q;
    }

    public ArrayList<a> A() {
        return this.R;
    }
}
