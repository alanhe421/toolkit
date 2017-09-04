package com.qq.reader.module.question.card.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.question.data.AudioData;
import com.qq.reader.module.question.data.AudioData.AskerData;
import com.tencent.feedback.proguard.R;

public class AudioListLeftUserRightTitleView extends RelativeLayout implements OnClickListener {
    AudioData a;
    LayoutInflater b;
    View c;
    ImageView d;
    ImageView e;
    TextView f;
    TextView g;
    TextView h;
    View i;
    int j = -1;

    public AudioListLeftUserRightTitleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
        a();
    }

    private void a() {
        this.d = (ImageView) findViewById(R.id.iv_asker);
        this.f = (TextView) findViewById(R.id.tv_asker_title);
        this.g = (TextView) findViewById(R.id.tv_listen_cost);
        this.h = (TextView) findViewById(R.id.tv_listen_cost_per);
        this.i = findViewById(R.id.ll_title_container);
        this.e = (ImageView) findViewById(R.id.iv_asker_month_img);
        this.c = findViewById(R.id.iv_asker_container);
    }

    private void a(Context context) {
        this.b = LayoutInflater.from(context);
        this.b.inflate(R.layout.audio_com_list_q_leftuser_righttitle_layout, this);
    }

    public AudioListLeftUserRightTitleView(Context context) {
        super(context);
        a(context);
        a();
        b();
    }

    private void b() {
        this.c.setOnClickListener(this);
    }

    public AudioListLeftUserRightTitleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_asker_container:
                if ((getContext() instanceof Activity) && this.a != null) {
                    Object l = this.a.a().l();
                    if (TextUtils.isEmpty(l)) {
                        o.j((Activity) getContext(), this.a.a().e(), null);
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

    public void a(AudioData audioData) {
        this.a = audioData;
        AskerData a = audioData.a();
        c.a(getContext()).a(a.d(), this.d, a.a().e());
        if (TextUtils.isEmpty(a.h())) {
            this.f.setText("");
        } else {
            this.f.setText(a.h());
        }
        if (a.i() > 0) {
            this.g.setText(String.format(getResources().getString(R.string.common_qa_cost), new Object[]{Integer.valueOf(a.i())}));
            this.g.setVisibility(0);
            if (this.j == 2 || this.j == 1) {
                this.h.setVisibility(0);
            } else {
                this.h.setVisibility(8);
            }
        } else {
            this.g.setVisibility(8);
            this.h.setVisibility(8);
        }
        ao.a(audioData.a().a(), this.e, false);
    }

    public void setType(int i) {
        if (this.j != i) {
            this.j = i;
            switch (i) {
                case 0:
                    this.c.setVisibility(0);
                    setAvatarLeftMargin(ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.common_dp_6));
                    return;
                case 1:
                    this.c.setVisibility(8);
                    setAvatarLeftMargin(0);
                    return;
                case 2:
                    this.g.setVisibility(8);
                    setAvatarLeftMargin(ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.common_dp_6));
                    return;
                default:
                    return;
            }
        }
    }

    public void a(int i, int i2) {
        this.f.setTextColor(i);
        this.h.setTextColor(i2);
    }

    private void setAvatarLeftMargin(int i) {
        MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.i.getLayoutParams();
        marginLayoutParams.setMargins(i, 0, 0, 0);
        this.i.setLayoutParams(marginLayoutParams);
    }
}
