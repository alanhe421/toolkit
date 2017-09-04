package com.qq.reader.liveshow.views.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.b.c;
import java.util.ArrayList;
import java.util.List;

public class MultiClickGiftLayout extends LinearLayout {
    private List<MultiGiftItemView> a;
    private c b;

    public MultiClickGiftLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public MultiClickGiftLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public MultiClickGiftLayout(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        LayoutInflater.from(context).inflate(g.ly_multiclickgift, this);
        this.a = new ArrayList();
        this.a.add((MultiGiftItemView) findViewById(e.multi_gift2));
        this.a.add((MultiGiftItemView) findViewById(e.multi_gift1));
    }

    public void a(com.qq.reader.liveshow.model.im.message.a.c cVar) {
        for (MultiGiftItemView multiGiftItemView : this.a) {
            if (multiGiftItemView.a()) {
                multiGiftItemView.setData(cVar);
                multiGiftItemView.a(this.b);
                return;
            }
        }
    }

    public void setPositionCallback(c cVar) {
        this.b = cVar;
    }
}
