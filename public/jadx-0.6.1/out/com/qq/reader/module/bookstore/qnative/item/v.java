package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.j;
import com.qq.reader.common.utils.k;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: ListBookItem */
public class v extends z {
    private String A = "";
    private String B = "";
    private String C = "";
    private String D = "";
    private String E = "";
    private String F;
    private int G = 0;
    private long a;
    private String b;
    private String c;
    private String d;
    protected int e = 0;
    protected String f;
    protected String g;
    protected String h;
    protected double i;
    protected String j = "";
    protected String k = "";
    private int m;
    private String n;
    private int o = 75;
    private String p = null;
    private int q = 0;
    private String r;
    private long s = -1;
    private int t = 0;
    private String u = null;
    private String v = null;
    private String w = null;
    private String x = null;
    private String y = null;
    private String z = "";

    public void a(a aVar) {
        if (this.G == 4) {
            o.b(aVar.getFromActivity(), String.valueOf(this.a), null, "2");
        } else if (l() > 0) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(this.t));
            i.a("event_A126", hashMap, ReaderApplication.getApplicationImp());
            o.e(aVar.getFromActivity(), String.valueOf(l()), null);
        } else if (this.l != null) {
            this.l.a(aVar);
        }
        if (!TextUtils.isEmpty(p())) {
            hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(this.a));
            i.a("event_C81", hashMap, ReaderApplication.getApplicationImp());
        }
    }

    public void parseData(JSONObject jSONObject) {
        c.a("card", "parseData " + jSONObject.toString());
        this.a = jSONObject.optLong("bid");
        this.b = jSONObject.optString("title");
        this.c = jSONObject.optString("author");
        this.d = jSONObject.optString("categoryName");
        this.m = jSONObject.optInt("price");
        this.n = jSONObject.optString("intro");
        this.o = jSONObject.optInt("star");
        this.e = jSONObject.optInt("totalWords");
        this.D = jSONObject.optString("num");
        this.q = jSONObject.optInt("jzcount");
        this.r = jSONObject.optString("anchor");
        this.s = jSONObject.optLong("mediaBookId");
        this.i = jSONObject.optDouble("score", 0.0d);
        this.f = jSONObject.optString("catel2name");
        this.g = jSONObject.optString("catel3name");
        this.h = jSONObject.optString("totalChapter");
        this.G = jSONObject.optInt("type");
        JSONObject optJSONObject = jSONObject.optJSONObject("ext");
        if (optJSONObject != null) {
            this.B = optJSONObject.optString("extleft");
            this.C = optJSONObject.optString("extright");
            this.A = optJSONObject.optString("extrightkey");
            this.z = optJSONObject.optString("extleftkey");
            this.E = optJSONObject.optString("unit");
            this.u = optJSONObject.optString("recTitle");
            this.v = optJSONObject.optString("discount");
            this.w = optJSONObject.optString("rent");
            this.F = optJSONObject.optString("categoryInfoV4SlaveId");
            optJSONObject = optJSONObject.optJSONObject("limitPrice");
            if (optJSONObject != null) {
                this.x = optJSONObject.optString("presentPrice");
                this.y = optJSONObject.optString("originalPrice");
            }
        }
        optJSONObject = jSONObject.optJSONObject(s.STATPARAM_KEY);
        if (optJSONObject != null) {
            this.t = optJSONObject.optInt(s.ORIGIN);
        }
        this.l = new com.qq.reader.module.bookstore.qnative.c(null);
        Bundle a = this.l.a();
        a.putString("LOCAL_STORE_IN_TITLE", f());
        a.putString("KEY_JUMP_PAGENAME", "DetailPage");
        a.putLong("URL_BUILD_PERE_BOOK_ID", e());
        setStatisic(jSONObject, a);
    }

    public String a() {
        if (TextUtils.isDigitsOnly(this.D)) {
            return j.a((long) Integer.valueOf(this.D).intValue());
        }
        return this.D;
    }

    public String b() {
        return this.D;
    }

    public String c() {
        return this.E;
    }

    public String d() {
        return ao.g(this.a);
    }

    public long e() {
        return this.a;
    }

    public String f() {
        return this.b;
    }

    public String g() {
        return this.c;
    }

    public String h() {
        return this.d;
    }

    public String i() {
        return this.g;
    }

    public String j() {
        return this.n;
    }

    public String k() {
        return this.r;
    }

    public long l() {
        return this.s;
    }

    public String m() {
        return j.a((long) this.e);
    }

    public String n() {
        return this.h;
    }

    public String o() {
        return this.F;
    }

    public String p() {
        return this.u;
    }

    public String q() {
        return this.v;
    }

    public String r() {
        return this.w;
    }

    public String s() {
        return this.x;
    }

    public String t() {
        return this.y;
    }

    public void a(View view, int i, boolean z) {
        CharSequence f;
        TextView textView = (TextView) ap.a(view, R.id.concept_title);
        ImageView imageView = (ImageView) ap.a(view, R.id.concept_cover_img);
        TextView textView2 = (TextView) ap.a(view, R.id.concept_author);
        TextView textView3 = (TextView) ap.a(view, R.id.concept_content);
        TextView textView4 = (TextView) ap.a(view, R.id.concept_order);
        TextView textView5 = (TextView) ap.a(view, R.id.concept_category);
        TextView textView6 = (TextView) ap.a(view, R.id.concept_special);
        ImageView imageView2 = (ImageView) ap.a(view, R.id.concept_tingbook_tag);
        if (this.G == 4) {
            com.qq.reader.common.imageloader.c.a(view.getContext()).a(ao.h(this.a), imageView, com.qq.reader.common.imageloader.a.a().j());
        } else {
            com.qq.reader.common.imageloader.c.a(view.getContext()).a(d(), imageView, com.qq.reader.common.imageloader.a.a().j());
        }
        if (z) {
            textView.setText((i + 1) + "." + f());
            ImageView imageView3 = (ImageView) ap.a(view, R.id.rank_list_bg);
            if (i < 0 || i >= 3) {
                imageView3.setVisibility(8);
            } else {
                imageView3.setVisibility(0);
                imageView3.setImageResource(ao.d(i));
            }
        } else {
            f = f();
            if (f == null) {
                f = "";
            }
            textView.setText(f);
        }
        View a = ap.a(view, R.id.top_adv_divider);
        if (i == 0) {
            a.setVisibility(8);
        } else {
            a.setVisibility(0);
        }
        if (l() > 0) {
            imageView2.setVisibility(0);
            textView2.setCompoundDrawablesWithIntrinsicBounds(ReaderApplication.getApplicationImp().getResources().getDrawable(R.drawable.mic_icon), null, null, null);
            textView2.setText(k());
        } else {
            imageView2.setVisibility(8);
            textView2.setText(g());
        }
        textView3.setText(j());
        if ("19200".equals(this.F)) {
            textView6.setVisibility(8);
            textView5.setVisibility(8);
            textView4.setVisibility(8);
            return;
        }
        textView6.setVisibility(0);
        textView5.setVisibility(8);
        textView4.setVisibility(8);
        textView5.setText("");
        textView4.setText("");
        if (!TextUtils.isEmpty(p())) {
            textView6.setText(p());
            textView6.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.book_store_card_suggest_color));
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(this.a));
            i.a("event_C80", hashMap, ReaderApplication.getApplicationImp());
        } else if (!TextUtils.isEmpty(q())) {
            textView6.setText(q());
            textView6.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.book_store_card_price_color));
        } else if (!TextUtils.isEmpty(s())) {
            String str = t() + " " + s();
            f = new SpannableString(str);
            f.setSpan(new StrikethroughSpan(), 0, t().length(), 33);
            f.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.book_store_card_discount_color)), 0, t().length(), 33);
            f.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.book_store_card_price_color)), str.length() - s().length(), str.length(), 33);
            textView6.setText(f);
        } else if (TextUtils.isEmpty(r())) {
            textView6.setVisibility(8);
            textView5.setVisibility(0);
            textView4.setVisibility(0);
            if (TextUtils.isEmpty(h())) {
                textView5.setVisibility(8);
            } else {
                textView5.setText(h());
            }
            if (TextUtils.isEmpty(c()) || !TextUtils.isDigitsOnly(b()) || Integer.parseInt(b()) <= 0) {
                try {
                    CharSequence m = m();
                    if (this.e <= 0 || TextUtils.isEmpty(m)) {
                        textView4.setVisibility(8);
                        return;
                    }
                    textView4.setText(m() + "字");
                    textView4.setVisibility(0);
                    textView4.setBackgroundResource(R.drawable.concept_bookitem_tag_level3);
                    textView4.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.common_textcolor_secondary));
                    return;
                } catch (Exception e) {
                    c.e("Err", e.getMessage());
                    textView4.setVisibility(8);
                    return;
                }
            }
            textView4.setVisibility(0);
            if (!"time".equals(c())) {
                textView4.setText(a() + "" + c());
            } else if (TextUtils.isDigitsOnly(b())) {
                textView4.setText(k.a(Long.parseLong(b()) * 1000));
            }
        } else {
            textView6.setText(r());
            textView6.setTextColor(ReaderApplication.getApplicationImp().getResources().getColor(R.color.book_store_card_rent_color));
        }
    }
}
