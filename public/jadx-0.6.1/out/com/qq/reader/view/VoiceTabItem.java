package com.qq.reader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

public class VoiceTabItem extends RelativeLayout {
    TextView a;
    ImageView b;
    private View c;

    public VoiceTabItem(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void a() {
        this.a = (TextView) findViewById(R.id.voice_name);
        this.b = (ImageView) findViewById(R.id.voice_bg);
        this.b.setScaleType(ScaleType.FIT_XY);
        this.c = findViewById(R.id.divider);
    }

    public void setBackgroundRes(int i) {
        this.b.setBackgroundResource(i);
    }

    public void setText(String str) {
        this.a.setText(str);
    }

    public void setmDividerVisiable(int i) {
        this.c.setVisibility(i);
    }

    public float a(String str) {
        return this.a.getPaint().measureText(str, 0, str.length());
    }

    public void setTextColor(int i) {
        this.a.setTextColor(getResources().getColorStateList(i));
    }

    public void setSelect(boolean z, int i) {
        if (z) {
            this.a.setSelected(true);
            return;
        }
        this.b.setImageDrawable(null);
        this.a.setSelected(false);
    }
}
