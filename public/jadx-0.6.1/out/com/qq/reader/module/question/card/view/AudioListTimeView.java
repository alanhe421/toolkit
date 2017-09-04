package com.qq.reader.module.question.card.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.module.question.b;
import com.qq.reader.module.question.data.AudioData;
import com.tencent.feedback.proguard.R;

public class AudioListTimeView extends RelativeLayout {
    TextView a;
    TextView b;
    AudioData c;
    private int d = -1;

    public AudioListTimeView(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        inflate(context, R.layout.audio_com_list_time_layout, this);
        a();
    }

    private void a() {
        this.a = (TextView) findViewById(R.id.tv_create_time);
        this.b = (TextView) findViewById(R.id.tv_listen_count);
    }

    public AudioListTimeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public AudioListTimeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public void a(AudioData audioData) {
        this.c = audioData;
        if (this.d != 2) {
            CharSequence a = b.a(this.c.a().c());
            int i = 1;
            if (TextUtils.isEmpty(a)) {
                this.a.setVisibility(4);
            } else {
                this.a.setVisibility(0);
                this.a.setText(a);
                i = 0;
            }
            int l = this.c.b().l();
            if (l > 0) {
                this.b.setText(b.b((long) l));
                this.b.setVisibility(0);
                i = 0;
            } else {
                this.b.setVisibility(4);
            }
            if (i != 0) {
                setVisibility(8);
            } else if (this.d == 0) {
                setVisibility(0);
            }
        }
    }

    public void setType(int i) {
        if (this.d != i) {
            this.d = i;
            switch (this.d) {
            }
        }
    }
}
