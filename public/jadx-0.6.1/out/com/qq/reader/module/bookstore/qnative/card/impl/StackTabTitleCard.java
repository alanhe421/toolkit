package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.j;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class StackTabTitleCard extends a {
    private int mAddCount = 0;
    private int mTotalCount = 0;
    private int mType = 0;

    public StackTabTitleCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localbookstore_stacklist_title_item;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mType = jSONObject.optInt("categoryFlag");
        this.mTotalCount = jSONObject.optInt("bookCount");
        this.mAddCount = jSONObject.optInt("newBookCount");
        return true;
    }

    public void attachView() {
        CharSequence spannableStringBuilder;
        TextView textView = (TextView) ap.a(getRootView(), R.id.content);
        String str = "册";
        String str2 = "，本周新增";
        String str3 = "册";
        String str4 = "共";
        String d = j.d((long) this.mTotalCount);
        String str5 = "";
        if (this.mAddCount > 0) {
            str5 = j.d((long) this.mAddCount);
            spannableStringBuilder = new SpannableStringBuilder(str4 + d + str + str2 + str5 + str3);
        } else {
            spannableStringBuilder = new SpannableStringBuilder(str4 + d + str);
        }
        spannableStringBuilder.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c401)), str4.length(), str4.length() + d.length(), 33);
        if (this.mAddCount > 0) {
            spannableStringBuilder.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.text_color_c401)), ((str4.length() + d.length()) + str.length()) + str2.length(), str5.length() + ((str.length() + (str4.length() + d.length())) + str2.length()), 33);
        }
        textView.setText(spannableStringBuilder);
    }
}
