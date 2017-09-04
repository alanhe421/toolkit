package com.qq.reader.common.charge.voucher;

import android.text.TextPaint;
import android.text.style.RelativeSizeSpan;
import android.widget.Button;

class UserBalanceHelper$4 extends RelativeSizeSpan {
    final /* synthetic */ Button a;

    UserBalanceHelper$4(float f, Button button) {
        this.a = button;
        super(f);
    }

    public void updateDrawState(TextPaint textPaint) {
        super.updateDrawState(textPaint);
        textPaint.baselineShift = -((int) ((0.2f * this.a.getTextSize()) / 2.0f));
    }
}
