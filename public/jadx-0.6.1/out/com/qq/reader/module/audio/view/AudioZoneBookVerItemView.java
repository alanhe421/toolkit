package com.qq.reader.module.audio.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.j;
import com.qq.reader.module.audio.b.a;
import com.tencent.feedback.proguard.R;

public class AudioZoneBookVerItemView extends RelativeLayout {
    private ImageView a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private View f;

    public AudioZoneBookVerItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.audio_zone_book_ver_item, this, true);
        a();
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.cover_img);
        this.b = (TextView) findViewById(R.id.book_name);
        this.c = (TextView) findViewById(R.id.play_count);
        this.d = (TextView) findViewById(R.id.book_content);
        this.e = (TextView) findViewById(R.id.last_chapter);
        this.f = findViewById(R.id.divider);
    }

    public void setBookInfo(a aVar) {
        this.b.setText(aVar.n());
        this.d.setText(aVar.s());
        if (aVar.a() > 0) {
            this.c.setText(j.a(aVar.a()));
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
        this.e.setText(aVar.e() + "集");
        c.a(getContext()).a(aVar.f(), this.a, com.qq.reader.common.imageloader.a.a().j());
    }

    public void a(boolean z) {
        this.f.setVisibility(z ? 0 : 8);
    }
}
