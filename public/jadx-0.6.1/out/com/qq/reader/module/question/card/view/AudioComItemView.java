package com.qq.reader.module.question.card.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.qq.reader.module.question.card.view.AudioListAnswerView.a;
import com.qq.reader.module.question.data.AudioData;

public class AudioComItemView extends LinearLayout {
    int a = -1;
    AudioListTimeView b;
    AudioListLeftUserRightTitleView c;
    AudioListAnswerView d;
    boolean e;

    public AudioComItemView(Context context) {
        super(context);
        a(context);
    }

    public void setSupportPlay(boolean z) {
        this.e = z;
        this.d.setPlayEnable(this.e);
        if (z) {
            setOnClickListener(null);
        }
    }

    public AudioComItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public AudioComItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    private void a(Context context) {
        setOrientation(1);
        this.c = new AudioListLeftUserRightTitleView(context);
        this.d = new AudioListAnswerView(context);
        this.b = new AudioListTimeView(context);
        addView(this.c, new LayoutParams(-1, -2));
        addView(this.d, new LayoutParams(-1, -2));
        addView(this.b, new LayoutParams(-1, -2));
    }

    public void a(AudioData audioData) {
        this.b.a(audioData);
        this.c.a(audioData);
        this.d.a(audioData);
    }

    public void setType(int i) {
        if (this.a != i) {
            this.a = i;
            switch (this.a) {
                case 0:
                    this.b.setVisibility(0);
                    break;
                case 1:
                    this.b.setVisibility(8);
                    break;
                case 2:
                    this.b.setVisibility(8);
                    break;
            }
            this.b.setType(i);
            this.c.setType(i);
            this.d.setType(i);
        }
    }

    public void setLoadAudioCallback(a aVar) {
        this.d.setCallBack(aVar);
    }

    public void setOnPlayBtnClickListener(OnClickListener onClickListener) {
        if (this.d != null) {
            this.d.setOnPlayBtnClickListener(onClickListener);
        }
    }

    public void a(int i, int i2) {
        this.c.a(i, i2);
        this.d.a(i2);
    }
}
