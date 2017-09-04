package com.qq.reader.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import com.tencent.feedback.proguard.R;

public class PullDownHeadView extends ViewGroup {
    static final /* synthetic */ boolean a = (!PullDownHeadView.class.desiredAssertionStatus());
    private View b;
    private View c;
    private View d;
    private View e;
    private int f;

    public PullDownHeadView(Context context, AttributeSet attributeSet, int i) {
        this(context, attributeSet);
    }

    public PullDownHeadView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = context.getResources().getDimensionPixelOffset(R.dimen.common_dp_62);
    }

    public PullDownHeadView(Context context) {
        this(context, null);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        a();
    }

    private void a() {
        getContext();
        this.b = findViewById(R.id.iv_content);
        this.c = findViewById(R.id.tv_title);
        this.d = findViewById(R.id.tv_updata_date);
        this.e = findViewById(R.id.vw_update_divider_id);
        if (!a) {
            if (this.b == null || this.c == null || this.d == null || this.e == null) {
                throw new AssertionError();
            }
        }
    }

    protected void onMeasure(int i, int i2) {
        measureChildren(i, i2);
        setMeasuredDimension(MeasureSpec.getSize(i), MeasureSpec.getSize(i2));
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int i5 = i3 - i;
        int measuredWidth = (i5 - this.c.getMeasuredWidth()) / 2;
        int measuredHeight = ((((i4 - i2) - this.c.getMeasuredHeight()) - this.d.getMeasuredHeight()) - this.c.getPaddingBottom()) / 2;
        this.c.layout(measuredWidth, measuredHeight, this.c.getMeasuredWidth() + measuredWidth, this.c.getMeasuredHeight() + measuredHeight);
        int measuredWidth2 = (i5 - this.d.getMeasuredWidth()) / 2;
        int measuredHeight2 = (this.d.getMeasuredHeight() + measuredHeight) + this.c.getPaddingBottom();
        this.d.layout(measuredWidth2, measuredHeight2, this.d.getMeasuredWidth() + measuredWidth2, this.d.getMeasuredHeight() + measuredHeight2);
        measuredWidth = (measuredWidth - this.b.getPaddingRight()) - this.b.getMeasuredWidth();
        i5 = ((i5 / 2) - this.f) - this.b.getMeasuredWidth();
        measuredWidth2 = ((((this.d.getMeasuredHeight() + measuredHeight2) - measuredHeight) / 2) - (this.b.getMeasuredHeight() / 2)) + measuredHeight;
        if (i5 <= measuredWidth) {
            this.b.layout(i5, measuredWidth2, this.b.getMeasuredWidth() + i5, this.b.getMeasuredHeight() + measuredWidth2);
        } else {
            this.b.layout(measuredWidth, measuredHeight, this.b.getMeasuredWidth() + measuredWidth, this.b.getMeasuredHeight() + measuredHeight);
        }
    }
}
