package com.qq.reader.liveshow.views.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.utils.p;
import java.util.ArrayList;
import java.util.List;

public class MultiClickNumberWidget extends LinearLayout {
    private List<ImageView> a;

    public MultiClickNumberWidget(Context context) {
        super(context);
        a(context);
    }

    public MultiClickNumberWidget(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(g.multiclick_num_layout, this);
        this.a = new ArrayList();
        this.a.add((ImageView) findViewById(e.number_0));
        this.a.add((ImageView) findViewById(e.number_1));
        this.a.add((ImageView) findViewById(e.number_2));
        ((ImageView) this.a.get(0)).setVisibility(4);
        ((ImageView) this.a.get(1)).setVisibility(4);
        ((ImageView) this.a.get(2)).setVisibility(4);
    }

    public void setCount(int i) {
        if (i > 99) {
            ((ImageView) this.a.get(0)).setImageResource(p.b(i / 100));
            ((ImageView) this.a.get(1)).setImageResource(p.b((i / 10) % 10));
            ((ImageView) this.a.get(2)).setImageResource(p.b(i % 10));
            ((ImageView) this.a.get(0)).setVisibility(0);
            ((ImageView) this.a.get(1)).setVisibility(0);
            ((ImageView) this.a.get(2)).setVisibility(0);
        } else if (i > 9) {
            ((ImageView) this.a.get(0)).setImageResource(p.b(i / 10));
            ((ImageView) this.a.get(1)).setImageResource(p.b(i % 10));
            ((ImageView) this.a.get(0)).setVisibility(0);
            ((ImageView) this.a.get(1)).setVisibility(0);
            ((ImageView) this.a.get(2)).setVisibility(4);
        } else {
            ((ImageView) this.a.get(0)).setImageResource(p.b(i));
            ((ImageView) this.a.get(0)).setVisibility(0);
            ((ImageView) this.a.get(1)).setVisibility(4);
            ((ImageView) this.a.get(2)).setVisibility(4);
        }
        a();
    }

    private void a() {
        startAnimation(p.b(getContext(), 200));
    }
}
