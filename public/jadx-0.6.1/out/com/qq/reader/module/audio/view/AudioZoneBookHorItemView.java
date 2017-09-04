package com.qq.reader.module.audio.view;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.j;
import com.qq.reader.module.audio.b.a;
import com.tencent.feedback.proguard.R;

public class AudioZoneBookHorItemView extends RelativeLayout {
    private ImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;

    public AudioZoneBookHorItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.audio_zone_book_hor_item, this, true);
        a();
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.cover_img);
        this.b = (TextView) findViewById(R.id.book_name);
        this.c = (TextView) findViewById(R.id.play_count);
        this.d = (TextView) findViewById(R.id.book_price);
        this.e = (TextView) findViewById(R.id.book_discount);
    }

    public void setBookInfo(a aVar, boolean z) {
        if (aVar.a() > 0) {
            this.c.setText(j.a(aVar.a()));
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
        c.a(getContext()).a(aVar.f(), this.a, com.qq.reader.common.imageloader.a.a().j());
        if (z) {
            this.b.setVisibility(8);
            this.d.setVisibility(8);
            this.e.setVisibility(8);
            return;
        }
        if (TextUtils.isEmpty(aVar.n())) {
            this.b.setVisibility(8);
        } else {
            this.b.setText(aVar.n());
            this.b.setVisibility(0);
        }
        if (TextUtils.isEmpty(aVar.l())) {
            this.d.setVisibility(8);
            this.e.setVisibility(8);
            return;
        }
        Object k = aVar.k();
        CharSequence spannableString = new SpannableString(k);
        spannableString.setSpan(new StrikethroughSpan(), 0, k.length(), 33);
        this.d.setText(spannableString);
        this.e.setText(aVar.l());
        this.d.setVisibility(0);
        this.e.setVisibility(0);
    }
}
