package com.qq.reader.common.emotion;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.e;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup.LayoutParams;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.tencent.feedback.proguard.R;

public class EmoticonRadioGroup extends RadioGroup implements e {
    boolean a;
    private ViewPager b;
    private RadioButton c = a(2);
    private boolean d;
    private final String e = EmoticonRadioGroup.class.getSimpleName();
    private int f = -1;

    public void setRecent(boolean z) {
        this.a = z;
    }

    public EmoticonRadioGroup(Context context) {
        super(context);
    }

    public EmoticonRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setViewPager(ViewPager viewPager) {
        this.b = viewPager;
        if (this.b != null) {
            this.b.setOnPageChangeListener(this);
        }
    }

    public void onPageScrollStateChanged(int i) {
    }

    public void onPageScrolled(int i, float f, int i2) {
    }

    public void onPageSelected(int i) {
        RadioButton radioButton = (RadioButton) super.getChildAt(i);
        if (radioButton != null) {
            radioButton.setChecked(true);
        }
    }

    private RadioButton a(int i) {
        RadioButton anonymousClass1 = new RadioButton(this, getContext()) {
            final /* synthetic */ EmoticonRadioGroup a;

            public boolean performClick() {
                return true;
            }
        };
        if (i == 2) {
            anonymousClass1.setButtonDrawable(R.drawable.radio_button_system);
        }
        anonymousClass1.setGravity(17);
        Resources resources = super.getContext().getResources();
        LayoutParams layoutParams = new RadioGroup.LayoutParams((int) TypedValue.applyDimension(1, 10.0f, resources.getDisplayMetrics()), (int) TypedValue.applyDimension(1, 10.0f, resources.getDisplayMetrics()));
        layoutParams.gravity = 17;
        int applyDimension = (int) TypedValue.applyDimension(1, 3.0f, resources.getDisplayMetrics());
        layoutParams.leftMargin = applyDimension;
        layoutParams.rightMargin = applyDimension;
        anonymousClass1.setLayoutParams(layoutParams);
        anonymousClass1.setClickable(true);
        if (i != 2) {
            anonymousClass1.setClickable(false);
            anonymousClass1.setFocusable(false);
        }
        return anonymousClass1;
    }

    public void a(int i, boolean z) {
        if (!z) {
            super.removeView(this.c);
            this.f = -1;
            int childCount = getChildCount();
            int i2;
            if (i > childCount) {
                for (i2 = 0; i2 < i - childCount; i2++) {
                    super.addView(a(2));
                }
            } else if (i < childCount) {
                for (i2 = childCount - 1; i2 >= i; i2--) {
                    super.removeViewAt(i2);
                }
            }
            if (i > 0 && this.b != null) {
                ((RadioButton) super.getChildAt(0)).setChecked(true);
            }
            for (childCount = 0; childCount < getChildCount(); childCount++) {
                ((RadioButton) super.getChildAt(childCount)).setVisibility(0);
            }
        }
        this.d = z;
    }
}
