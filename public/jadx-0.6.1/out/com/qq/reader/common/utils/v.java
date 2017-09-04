package com.qq.reader.common.utils;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

/* compiled from: PersonalityViewholder */
public class v {
    ImageView a;
    TextView b;
    TextView c;
    TextView d;
    TextView e;
    TextView f;
    TextView g;
    TextView h;
    ImageView i;
    ImageView j;
    View k;
    ImageView l;
    ImageView m;
    private boolean n = false;
    private TextView o;

    public v(View view, Activity activity) {
        this.o = (TextView) view.findViewById(R.id.concept_title);
        this.a = (ImageView) view.findViewById(R.id.concept_cover_img);
        this.b = (TextView) view.findViewById(R.id.concept_author);
        this.c = (TextView) view.findViewById(R.id.concept_content);
        this.d = (TextView) view.findViewById(R.id.concept_order);
        this.f = (TextView) view.findViewById(R.id.concept_tag_3);
        this.g = (TextView) view.findViewById(R.id.concept_tag_4);
        this.e = (TextView) view.findViewById(R.id.concept_category);
        this.h = (TextView) view.findViewById(R.id.concept_special);
        this.i = (ImageView) view.findViewById(R.id.concept_tingbook_tag);
        this.j = (ImageView) view.findViewById(R.id.rank_list_bg);
        this.k = view.findViewById(R.id.top_adv_divider);
        this.l = (ImageView) view.findViewById(R.id.concept_cover_tag2);
        this.m = (ImageView) view.findViewById(R.id.close_btn);
    }

    public View a(int i) {
        switch (i) {
            case R.id.concept_cover_img:
                return this.a;
            case R.id.concept_title:
                return this.o;
            case R.id.concept_content:
                return this.c;
            case R.id.concept_order:
                return this.d;
            case R.id.close_btn:
                return this.m;
            case R.id.concept_author:
                return this.b;
            case R.id.concept_special:
                return this.h;
            case R.id.concept_category:
                return this.e;
            case R.id.top_adv_divider:
                return this.k;
            case R.id.rank_list_bg:
                return this.j;
            case R.id.concept_cover_tag2:
                return this.l;
            case R.id.concept_tingbook_tag:
                return this.i;
            case R.id.concept_tag_4:
                return this.g;
            case R.id.concept_tag_3:
                return this.f;
            default:
                return null;
        }
    }

    public boolean a() {
        return this.n;
    }

    public void a(boolean z) {
        this.n = z;
    }
}
