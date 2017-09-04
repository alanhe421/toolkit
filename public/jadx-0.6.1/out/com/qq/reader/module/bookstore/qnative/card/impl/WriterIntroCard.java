package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.comic.card.ComicStoreExclusiveItemCard;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class WriterIntroCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private a authorInfoItem;
    private String lastAuthorAvatarUrl;

    private class a extends s {
        public String a;
        public String b;
        public String c;
        public String d;
        public String e;
        public String f;
        public String g;
        public int h;
        public int i;
        final /* synthetic */ WriterIntroCard j;

        private a(WriterIntroCard writerIntroCard) {
            this.j = writerIntroCard;
            this.a = "";
            this.b = "";
            this.c = "";
            this.d = "";
            this.e = "";
            this.f = "";
            this.g = "";
        }

        public void parseData(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.b = jSONObject.optString(MessageKey.MSG_CONTENT);
                this.a = jSONObject.optString(MessageKey.MSG_ICON);
                this.h = jSONObject.optInt("label");
                this.i = jSONObject.optInt("partiality");
                this.e = jSONObject.optString("categoryName");
                JSONObject optJSONObject = jSONObject.optJSONObject("totalWords");
                if (optJSONObject != null) {
                    this.c = optJSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT);
                    this.d = optJSONObject.optString("unit");
                }
                optJSONObject = jSONObject.optJSONObject("fansCount");
                if (optJSONObject != null) {
                    this.f = optJSONObject.optString(ComicStoreExclusiveItemCard.NET_AD_ATTR_COUNT);
                    this.g = optJSONObject.optString("unit");
                }
            }
        }
    }

    public WriterIntroCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.author_page_writer_intro_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONObject optJSONObject = jSONObject.optJSONObject("manitoInfo");
        this.authorInfoItem = new a();
        if (optJSONObject != null) {
            this.authorInfoItem.parseData(optJSONObject.optJSONObject("info"));
        }
        return true;
    }

    public void attachView() {
        View rootView = getRootView();
        if (this.authorInfoItem != null) {
            ImageView imageView = (ImageView) ap.a(getRootView(), R.id.img_author_avatar);
            String str = this.authorInfoItem.a;
            if (!(TextUtils.isEmpty(str) || str.equals(this.lastAuthorAvatarUrl))) {
                this.lastAuthorAvatarUrl = str;
                c.a(getEvnetListener().getFromActivity()).a(str, imageView, com.qq.reader.common.imageloader.a.a().o());
            }
            TextView textView = (TextView) ap.a(rootView, R.id.tv_words_count);
            if (TextUtils.isEmpty(this.authorInfoItem.c) || "0".equals(this.authorInfoItem.c)) {
                textView.setText("暂无书籍");
            } else {
                textView.setText(spellNumberAndUnit(this.authorInfoItem.c, this.authorInfoItem.d));
            }
            View a = ap.a(rootView, R.id.ll_category);
            View a2 = ap.a(rootView, R.id.category_divider);
            if (TextUtils.isEmpty(this.authorInfoItem.e)) {
                a.setVisibility(8);
                a2.setVisibility(8);
            } else {
                a.setVisibility(0);
                a2.setVisibility(0);
                ((TextView) ap.a(rootView, R.id.tv_main_type)).setText(this.authorInfoItem.e);
            }
            ((ImageView) ap.a(rootView, R.id.img_author_level)).setImageResource(getAuthorLevelResId(this.authorInfoItem.h));
            a2 = ap.a(rootView, R.id.fans_divider);
            View a3 = ap.a(rootView, R.id.ll_fans_count);
            try {
                if (Double.parseDouble(this.authorInfoItem.f) > 0.0d) {
                    a2.setVisibility(0);
                    a3.setVisibility(0);
                    ((TextView) ap.a(rootView, R.id.tv_fans_count)).setText(spellNumberAndUnit(this.authorInfoItem.f, this.authorInfoItem.g));
                } else {
                    a2.setVisibility(8);
                    a3.setVisibility(8);
                }
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                a2.setVisibility(8);
                a3.setVisibility(8);
            }
            ((TextView) ap.a(rootView, R.id.tv_intro)).setText(this.authorInfoItem.b);
        }
    }

    private CharSequence spellNumberAndUnit(String str, String str2) {
        CharSequence spannableString = new SpannableString(str + str2);
        if (!TextUtils.isEmpty(str)) {
            spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_5)), 0, str.length(), 34);
        }
        if (!TextUtils.isEmpty(str2)) {
            spannableString.setSpan(new AbsoluteSizeSpan(ReaderApplication.getApplicationImp().getResources().getDimensionPixelOffset(R.dimen.text_size_class_2)), str.length(), str.length() + str2.length(), 34);
        }
        return spannableString;
    }

    private int getAuthorLevelResId(int i) {
        switch (i) {
            case 4:
                return R.drawable.card_platinum;
            case 5:
                return R.drawable.card_god;
            case 6:
                return R.drawable.card_star;
            case 7:
                return R.drawable.card_auther;
            default:
                return 0;
        }
    }
}
