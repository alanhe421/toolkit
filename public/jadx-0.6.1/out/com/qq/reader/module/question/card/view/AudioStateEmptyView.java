package com.qq.reader.module.question.card.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.tencent.feedback.proguard.R;

public class AudioStateEmptyView extends RelativeLayout {
    private TextView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private ImageView e;
    private View f;
    private Context g;
    private boolean h;
    private View i;

    public AudioStateEmptyView(Context context) {
        this(context, null);
    }

    public AudioStateEmptyView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AudioStateEmptyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.h = true;
        this.g = context;
        a();
        a(true);
        setOnClickListener(null);
    }

    private void a() {
        this.f = View.inflate(this.g, R.layout.audio_empty_layout, this);
        this.a = (TextView) findViewById(R.id.empty_page_content);
        this.b = (TextView) findViewById(R.id.empty_page_content_title);
        this.c = (TextView) findViewById(R.id.empty_page_bottom_tip);
        this.d = (TextView) findViewById(R.id.empty_page_content_second);
        this.e = (ImageView) findViewById(R.id.img_author_avatar);
        this.i = findViewById(R.id.empty_avatar_container);
    }

    public AudioStateEmptyView a(CharSequence charSequence) {
        this.a.setText(charSequence);
        return this;
    }

    public AudioStateEmptyView b(CharSequence charSequence) {
        this.b.setText(charSequence);
        return this;
    }

    public AudioStateEmptyView c(CharSequence charSequence) {
        this.c.setText(charSequence);
        return this;
    }

    public AudioStateEmptyView d(CharSequence charSequence) {
        this.d.setText(charSequence);
        return this;
    }

    public void a(String str) {
        c.a(getContext()).a(str, this.e, a.a().b());
    }

    public void setViewsVisibility(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        if (z) {
            this.i.setVisibility(0);
            this.b.setPadding(0, ao.a(15.0f), 0, 0);
        } else {
            this.i.setVisibility(8);
            this.b.setPadding(0, ao.a(74.0f), 0, 0);
        }
        if (z2) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(8);
        }
        if (z3) {
            this.a.setVisibility(0);
        } else {
            this.a.setVisibility(8);
        }
        if (z4) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
        if (z5) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
    }

    public AudioStateEmptyView a(boolean z) {
        this.h = z;
        return this;
    }
}
