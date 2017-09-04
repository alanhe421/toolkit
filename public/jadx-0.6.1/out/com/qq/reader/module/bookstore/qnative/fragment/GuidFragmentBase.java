package com.qq.reader.module.bookstore.qnative.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.qq.reader.ReaderApplication;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;
import com.tencent.feedback.proguard.R;

public abstract class GuidFragmentBase extends BaseFragment {
    private int mGuideFragmentCount;
    protected int mGuideImgResId;
    private int mPosition;

    public void setPosition(int i) {
        this.mPosition = i;
    }

    public int getPosition() {
        return this.mPosition;
    }

    public int getGuideImgResId() {
        return this.mGuideImgResId;
    }

    public void setGuideImgResId(int i) {
        this.mGuideImgResId = i;
    }

    public void setGuideFragmentCount(int i) {
        this.mGuideFragmentCount = i;
    }

    public int getGuideFragmentCount() {
        return this.mGuideFragmentCount;
    }

    public void addRadioButton(LinearLayout linearLayout) {
        int guideFragmentCount = getGuideFragmentCount();
        linearLayout.removeAllViews();
        int dimensionPixelOffset = ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.guide_fragment_page_position_dot_width);
        int dimensionPixelOffset2 = ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.guide_fragment_page_position_dot_height);
        for (int i = 0; i < guideFragmentCount; i++) {
            View button = new Button(getActivity());
            button.setLayoutParams(new LayoutParams(dimensionPixelOffset, dimensionPixelOffset2));
            if (i == getPosition()) {
                button.setBackgroundResource(R.drawable.guide_radio_checked);
            } else {
                button.setBackgroundResource(R.drawable.guide_radio_unchecked);
            }
            linearLayout.addView(button);
        }
    }
}
