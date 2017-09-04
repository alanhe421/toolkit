package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.j;
import com.qq.reader.common.utils.k;
import com.qq.reader.common.utils.o;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: ListRankBoardItem */
public class aa extends z {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    protected String g;
    public int h = 0;
    public int i = -1;
    public boolean j = false;
    private int k = 0;
    private int m = 1;
    private int n = 2;
    private String o = "";
    private String p = "";
    private int q = 0;

    public aa(boolean z) {
        this.j = z;
    }

    public void a(View view, int i, boolean z) {
        if (this.h != 0) {
            ViewGroup viewGroup = (ViewGroup) view;
            view.findViewById(R.id.rankboard_head).setVisibility(0);
            ((TextView) view.findViewById(R.id.header_name)).setText(String.valueOf(this.h));
        } else {
            view.findViewById(R.id.rankboard_head).setVisibility(8);
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.bank_icon);
        TextView textView = (TextView) view.findViewById(R.id.bookname);
        TextView textView2 = (TextView) view.findViewById(R.id.book_intro);
        TextView textView3 = (TextView) view.findViewById(R.id.author);
        TextView textView4 = (TextView) view.findViewById(R.id.label);
        TextView textView5 = (TextView) view.findViewById(R.id.concept_order);
        if (this.j) {
            textView.setText(this.i + "." + this.c);
        } else {
            textView.setText((i + 1) + "." + this.c);
        }
        textView3.setText(this.e);
        if (TextUtils.isEmpty(this.f)) {
            textView4.setVisibility(4);
        } else {
            textView4.setVisibility(0);
            textView4.setText(this.f);
        }
        textView2.setText(this.d);
        if (!TextUtils.isEmpty(this.p) && TextUtils.isDigitsOnly(this.o) && Integer.parseInt(this.o) > 0) {
            textView5.setVisibility(0);
            if (!"time".equals(this.p)) {
                textView5.setText(a() + "" + this.p);
            } else if (TextUtils.isDigitsOnly(this.o)) {
                textView5.setText(k.a(Long.parseLong(this.o) * 1000));
            }
        } else if (TextUtils.isEmpty(this.g)) {
            textView5.setVisibility(8);
        } else {
            textView5.setText(this.g + "话");
        }
        String g = ao.g(Long.valueOf(this.a).longValue());
        if (this.q == this.n) {
            g = ao.h(Long.valueOf(this.b).longValue());
        }
        c.a(view.getContext()).a(g, imageView, a.a().j());
    }

    public void a(com.qq.reader.module.bookstore.qnative.c.a aVar, int i) {
        if (this.q == this.n) {
            o.b(aVar.getFromActivity(), this.b, null, "3");
        } else {
            super.a(aVar);
        }
        Map hashMap = new HashMap();
        hashMap.put("actionId", this.mRankInfoItem.d());
        hashMap.put("rankboard", "abtest_B");
        if (i != 0) {
            hashMap.put("year", String.valueOf(i));
        }
        i.a("event_B227", hashMap, aVar.getFromActivity());
    }

    public void parseData(JSONObject jSONObject) {
        if (this.j) {
            this.a = jSONObject.optString("bid");
            this.c = jSONObject.optString("title");
            this.d = jSONObject.optString("intro");
            this.e = jSONObject.optString("author");
            this.f = jSONObject.optString("catel3name");
        } else {
            v vVar = new v();
            vVar.parseData(jSONObject);
            this.a = String.valueOf(vVar.e());
            this.c = vVar.f();
            this.d = vVar.j();
            this.e = vVar.g();
            this.f = vVar.i();
            this.p = vVar.c();
            this.o = vVar.b();
            this.g = vVar.n();
        }
        this.b = jSONObject.optString("cmid");
        this.q = jSONObject.optInt("bookType");
        this.h = jSONObject.optInt("year");
        this.i = jSONObject.optInt("index");
        if (!TextUtils.isEmpty(this.a)) {
            this.l = new com.qq.reader.module.bookstore.qnative.c(null);
            Bundle a = this.l.a();
            a.putString("KEY_JUMP_PAGENAME", "DetailPage");
            a.putInt("function_type", 0);
            a.putLong("URL_BUILD_PERE_BOOK_ID", Long.valueOf(this.a).longValue());
        }
    }

    public String a() {
        if (TextUtils.isDigitsOnly(this.o)) {
            return j.a((long) Integer.valueOf(this.o).intValue());
        }
        return this.o;
    }
}
