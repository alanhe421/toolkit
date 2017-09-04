package com.qq.reader.common.emotion;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

public class SystemEmoticonPanel extends RelativeLayout {
    private final String a = SystemEmoticonPanel.class.getSimpleName();
    private EmoticonRadioGroup b;
    private ViewPager c;
    private View d;
    private e e;

    public SystemEmoticonPanel(Context context) {
        super(context);
    }

    public SystemEmoticonPanel(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public SystemEmoticonPanel(Context context, c cVar) {
        super(context);
        a(context, cVar);
    }

    private void a(Context context, c cVar) {
        this.d = LayoutInflater.from(context).inflate(R.layout.emotion_ui, this);
        if (this.d != null) {
            this.b = (EmoticonRadioGroup) this.d.findViewById(R.id.emotion_radiogroup);
            this.c = (ViewPager) this.d.findViewById(R.id.emotion_viewpager);
            this.b.setViewPager(this.c);
            this.e = new e();
            List arrayList = new ArrayList(1);
            arrayList.add(new j(context, cVar, 0));
            this.e.a(arrayList);
            this.c.setAdapter(this.e);
            this.b.a(this.e.a(), false);
        }
    }
}
