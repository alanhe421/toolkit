package com.qq.reader.module.bookstore.qnative.item;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: BigBookItem */
public class c extends v {
    private String a;
    private String b;
    private String c;
    private String d;
    private boolean m;
    private String n;

    public void parseData(JSONObject jSONObject) {
        boolean z = true;
        super.parseData(jSONObject);
        this.a = jSONObject.optString("honor");
        this.d = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
        this.b = jSONObject.optString("catel2name");
        this.c = jSONObject.optString("catel3name");
        if (jSONObject.optInt("form") != 1) {
            z = false;
        }
        this.m = z;
        this.n = jSONObject.optString("actionTag");
    }

    public void a(View view, int i, boolean z) {
        TextView textView = (TextView) ap.a(view, R.id.concept_title);
        TextView textView2 = (TextView) ap.a(view, R.id.concept_content);
        TextView textView3 = (TextView) ap.a(view, R.id.concept_author);
        TextView textView4 = (TextView) ap.a(view, R.id.concept_tag_1);
        TextView textView5 = (TextView) ap.a(view, R.id.concept_tag_3);
        TextView textView6 = (TextView) ap.a(view, R.id.recommend_text);
        com.qq.reader.common.imageloader.c.a(view.getContext()).a(d(), (ImageView) ap.a(view, R.id.concept_cover_img), a.a().j());
        textView.setText(f());
        textView3.setText(g());
        textView4.setText(this.b);
        if (this.m) {
            textView5.setText(this.c);
        } else {
            textView5.setVisibility(0);
            textView5.setText(m() + "字");
        }
        textView2.setText(this.a);
        textView6.setText(this.d);
    }

    public void a(com.qq.reader.module.bookstore.qnative.c.a aVar) {
        Map hashMap = new HashMap();
        hashMap.put("year", this.n);
        i.a("event_F128", hashMap, ReaderApplication.getApplicationImp());
        super.a(aVar);
    }
}
