package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.JumpActivityParameter;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

public class SingleSubscribeAuthorCard extends com.qq.reader.module.bookstore.qnative.card.a {
    a authorItem;

    private class a extends s {
        public String a;
        public String b;
        public String c;
        public String d;
        public int e;
        final /* synthetic */ SingleSubscribeAuthorCard f;

        private a(SingleSubscribeAuthorCard singleSubscribeAuthorCard) {
            this.f = singleSubscribeAuthorCard;
        }

        public void parseData(JSONObject jSONObject) {
            if (jSONObject != null) {
                this.a = jSONObject.optString("nickname");
                this.b = jSONObject.optString(MessageKey.MSG_ICON);
                this.c = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
                this.d = jSONObject.optString("authorId");
                this.e = jSONObject.optInt("labelName");
            }
        }
    }

    public SingleSubscribeAuthorCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.single_subscribe_author_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.authorItem = new a();
        this.authorItem.parseData(jSONObject);
        return true;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_title);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.tv_content);
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.img_icon);
        ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.img_level);
        if (this.authorItem != null) {
            textView.setText(this.authorItem.a);
            textView2.setText(this.authorItem.c);
            if (TextUtils.isEmpty(this.authorItem.a)) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
            }
            if (TextUtils.isEmpty(this.authorItem.c)) {
                textView2.setVisibility(8);
            } else {
                textView2.setVisibility(0);
            }
            c.a(getEvnetListener().getFromActivity()).a(this.authorItem.b, imageView, com.qq.reader.common.imageloader.a.a().o());
            imageView2.setImageResource(ao.e(this.authorItem.e));
            getRootView().setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SingleSubscribeAuthorCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    JumpActivityParameter jumpActivityParameter = new JumpActivityParameter();
                    jumpActivityParameter.a(1);
                    o.c(this.a.getEvnetListener().getFromActivity(), this.a.authorItem.d, this.a.authorItem.a, this.a.authorItem.b, jumpActivityParameter);
                }
            });
        }
    }
}
