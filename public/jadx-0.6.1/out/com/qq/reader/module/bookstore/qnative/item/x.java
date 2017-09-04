package com.qq.reader.module.bookstore.qnative.item;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.c;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.widget.ImageMaskView;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: ListHallOfFameAuthorItem */
public class x extends z {
    private int a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;

    public void a(a aVar) {
        if (this.l != null) {
            Bundle a = this.l.a();
            if (a != null) {
                String string = a.getString("KEY_JUMP_PAGENAME");
                CharSequence string2 = a.getString("AUTHORPAGE_KEY_AUTHORID");
                if ("GoodWriter_MainPage".equals(string) && !TextUtils.isEmpty(string2)) {
                    Map hashMap = new HashMap();
                    hashMap.put(s.ORIGIN, "1");
                    i.a("event_D139", hashMap, ReaderApplication.getApplicationImp());
                    hashMap = new HashMap();
                    hashMap.put("author", string2);
                    i.a("event_F323", hashMap, ReaderApplication.getApplicationImp());
                }
            }
        }
        super.a(aVar);
    }

    public void parseData(JSONObject jSONObject) {
        this.a = jSONObject.optInt("id");
        this.b = jSONObject.optString("label");
        this.c = jSONObject.optString("name");
        this.d = jSONObject.optString(SocialConstants.PARAM_IMG_URL);
        this.e = jSONObject.optString("intro");
        this.f = jSONObject.optString("value");
        if (this.f != null && !"".equalsIgnoreCase(this.f)) {
            this.l = new c(null);
            Bundle a = this.l.a();
            a.putString("LOCAL_STORE_IN_TITLE", b());
            a.putString("KEY_JUMP_PAGENAME", "GoodWriter_MainPage");
            a.putString("AUTHORPAGE_KEY_AUTHORID", e());
            a.putString("AUTHORPAGE_KEY_AVATAR_URL", this.d);
            a.putString("AUTHORPAGE_KEY_AUTHOR_NAME", this.c);
        }
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.c;
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.e;
    }

    public String e() {
        return this.f;
    }

    public void a(View view, int i, boolean z) {
        if (i == 0 && "".equalsIgnoreCase(this.c)) {
            a(view);
        } else {
            b(view);
        }
    }

    private void a(View view) {
        ((RelativeLayout) ap.a(view, R.id.author_details)).setVisibility(8);
        TextView textView = (TextView) ap.a(view, R.id.author_list_intro_title);
        textView.setVisibility(0);
        textView.setText(d());
        ap.a(view, R.id.author_list_intro_divider_line).setVisibility(0);
    }

    private void b(View view) {
        int parseInt;
        ((RelativeLayout) ap.a(view, R.id.author_details)).setVisibility(0);
        ImageMaskView imageMaskView = (ImageMaskView) ap.a(view, R.id.author_head_img);
        imageMaskView.setVisibility(0);
        com.qq.reader.common.imageloader.c.a(view.getContext()).a(a(), imageMaskView.getImageView(), com.qq.reader.common.imageloader.a.a().o());
        ImageView imageView = (ImageView) ap.a(view, R.id.author_head_level);
        imageView.setVisibility(0);
        try {
            parseInt = Integer.parseInt(c());
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e(getClass().getSimpleName(), e.getMessage());
            parseInt = 0;
        }
        parseInt = ao.e(parseInt);
        if (parseInt == 0) {
            imageView.setVisibility(8);
        } else {
            imageView.setVisibility(0);
            imageView.setBackgroundResource(parseInt);
        }
        TextView textView = (TextView) ap.a(view, R.id.author_info_title);
        textView.setVisibility(0);
        textView.setText(b());
        textView = (TextView) ap.a(view, R.id.author_info_introduction);
        textView.setVisibility(0);
        textView.setText(d());
        ((ImageView) ap.a(view, R.id.author_into_icon)).setVisibility(0);
        ap.a(view, R.id.author_intro_divider_line).setVisibility(0);
        ((TextView) ap.a(view, R.id.author_list_intro_title)).setVisibility(8);
        ap.a(view, R.id.author_list_intro_divider_line).setVisibility(8);
    }
}
