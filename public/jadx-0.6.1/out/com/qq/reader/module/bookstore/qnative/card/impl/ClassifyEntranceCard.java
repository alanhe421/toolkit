package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.sina.weibo.sdk.exception.WeiboAuthException;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class ClassifyEntranceCard extends a {
    private String actionId = WeiboAuthException.DEFAULT_AUTH_ERROR_CODE;

    public ClassifyEntranceCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.classify_entrance_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.actionId = this.mValue;
        return true;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_entrance);
        textView.setText(this.mShowTitle);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ClassifyEntranceCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    c.a(this.a.getEvnetListener().getFromActivity(), "uniteqqreader://nativepage/category/list?actionId=" + this.a.actionId, null);
                    this.a.clickStatics();
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.e("Error", e.getMessage());
                }
            }
        });
        showStatics();
    }

    private void showStatics() {
        i.a("event_shown_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }

    private void clickStatics() {
        i.a("event_clicked_" + getCardId(), null, ReaderApplication.getApplicationImp());
    }
}
