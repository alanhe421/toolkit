package com.qq.reader.view;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.qq.reader.ReaderApplication;
import com.tencent.feedback.proguard.R;

/* compiled from: LongClickableSpan */
public class n extends ClickableSpan {
    public boolean b = false;
    public String c = null;

    public void onClick(View view) {
    }

    public void updateDrawState(TextPaint textPaint) {
        int color;
        textPaint.setColor(this.b ? ReaderApplication.getApplicationImp().getResources().getColor(R.color.comment_book_link_pressed_color) : ReaderApplication.getApplicationImp().getResources().getColor(R.color.comment_book_link_normal_color));
        if (this.b) {
            color = ReaderApplication.getApplicationImp().getResources().getColor(R.color.comment_book_link_bg_pressed);
        } else {
            color = 0;
        }
        textPaint.bgColor = color;
        textPaint.setUnderlineText(false);
    }
}
