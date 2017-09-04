package com.qq.reader.module.question.card.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.question.b;
import com.qq.reader.module.question.data.AudioData;
import com.tencent.feedback.proguard.R;

public class AudioListTopUserBtmTitleView extends LinearLayout implements OnClickListener {
    int a = -1;
    AudioData b;
    ImageView c;
    ImageView d;
    TextView e;
    TextView f;
    TextView g;
    TextView h;

    public AudioListTopUserBtmTitleView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        setOrientation(1);
        LayoutInflater.from(context).inflate(R.layout.audio_com_list_q_topuser_btmtitle_layout, this);
        b();
        a();
    }

    private void a() {
        this.c.setOnClickListener(this);
    }

    private void b() {
        this.e = (TextView) findViewById(R.id.username);
        this.f = (TextView) findViewById(R.id.time);
        this.g = (TextView) findViewById(R.id.bookcoins);
        this.h = (TextView) findViewById(R.id.question_title);
        this.c = (ImageView) findViewById(R.id.avatar_img);
        this.d = (ImageView) findViewById(R.id.month_icon);
    }

    public AudioListTopUserBtmTitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public AudioListTopUserBtmTitleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public void a(AudioData audioData) {
        this.b = audioData;
        this.e.setText(audioData.a().f());
        this.g.setText(String.format(getResources().getString(R.string.common_qa_cost), new Object[]{Integer.valueOf(audioData.a().i())}));
        this.h.setText(audioData.a().h());
        c.a(getContext()).a(audioData.a().d(), this.c, a.a().e());
        this.f.setText(b.a(this.b.a().c()));
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.h.getLayoutParams();
        marginLayoutParams.setMargins(0, ao.a(6.0f), 0, 0);
        this.h.setLayoutParams(marginLayoutParams);
        ao.a(audioData.a().a(), this.d, false);
    }

    public void b(AudioData audioData) {
        a(audioData);
        LayoutParams layoutParams = this.c.getLayoutParams();
        layoutParams.height = ao.a(28.0f);
        layoutParams.width = ao.a(28.0f);
        this.c.setLayoutParams(layoutParams);
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.h.getLayoutParams();
        marginLayoutParams.setMargins(0, ao.a(12.0f), 0, 0);
        this.h.setLayoutParams(marginLayoutParams);
    }

    @Deprecated
    public void setType(int i) {
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.avatar_img:
                if (this.b != null && (getContext() instanceof Activity) && this.b != null) {
                    String l = this.b.a().l();
                    if (TextUtils.isEmpty(l)) {
                        o.j((Activity) getContext(), this.b.a().e(), null);
                        return;
                    } else {
                        o.c((Activity) getContext(), l, "", "", null);
                        return;
                    }
                }
                return;
            default:
                return;
        }
    }
}
