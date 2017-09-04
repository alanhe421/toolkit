package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.item.v;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class ListCard4DiscountBook extends ListCardCommon {

    public class a extends v {
        protected String a;
        protected String b;
        protected String c;
        final /* synthetic */ ListCard4DiscountBook d;

        public a(ListCard4DiscountBook listCard4DiscountBook) {
            this.d = listCard4DiscountBook;
        }

        public void a(View view, int i, boolean z) {
            super.a(view, i, z);
            TextView textView = (TextView) ap.a(view, R.id.concept_special);
            if (textView == null || TextUtils.isEmpty(this.c) || "null".equals(this.c)) {
                TextView textView2 = (TextView) ap.a(view, R.id.concept_category);
                TextView textView3 = (TextView) ap.a(view, R.id.concept_order);
                textView.setVisibility(8);
                textView.setText("");
                String str = this.a;
                String str2 = this.b;
                if (!TextUtils.isEmpty(str2)) {
                    textView2.setVisibility(8);
                    textView3.setVisibility(8);
                    textView.setVisibility(0);
                    CharSequence spannableString = new SpannableString(str + " " + str2);
                    spannableString.setSpan(new StrikethroughSpan(), 0, str.length(), 33);
                    spannableString.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.search_icon_color_gray)), 0, str.length(), 33);
                    spannableString.setSpan(new ForegroundColorSpan(ReaderApplication.getApplicationImp().getResources().getColor(R.color.book_store_card_price_color)), str.length() + 1, (str.length() + 1) + str2.length(), 33);
                    textView.setText(spannableString);
                    return;
                }
                return;
            }
            textView.setVisibility(0);
            textView.setText(this.c);
        }

        public void parseData(JSONObject jSONObject) {
            super.parseData(jSONObject);
            if (jSONObject != null) {
                this.c = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
                this.a = jSONObject.optString("originalPrice");
                this.b = jSONObject.optString("discountPrice");
            }
        }
    }

    public ListCard4DiscountBook(b bVar, String str) {
        super(bVar, str);
    }

    public s createListItem() {
        return new a(this);
    }

    public int getCardItemLayoutId() {
        return R.layout.localstore_listcard_item;
    }
}
