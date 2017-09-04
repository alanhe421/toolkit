package com.tencent.widget;

class ListView$ArrowScrollFocusResult {
    private int mAmountToScroll;
    private int mSelectedPosition;

    private ListView$ArrowScrollFocusResult() {
    }

    void populate(int i, int i2) {
        this.mSelectedPosition = i;
        this.mAmountToScroll = i2;
    }

    public int getSelectedPosition() {
        return this.mSelectedPosition;
    }

    public int getAmountToScroll() {
        return this.mAmountToScroll;
    }
}
