package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.question.card.view.AudioAuthorStateView;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class WriterInfoCard extends a {
    protected static final String JSON_KEY_TITLE = "title";
    private com.qq.reader.module.question.data.a mAuthorData;
    private int mCommentCount = 0;

    public WriterInfoCard(b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.answer_card_layout);
        if (this.mAuthorData != null) {
            linearLayout.setVisibility(0);
            CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.answer_title_layout);
            cardTitle.setVisibility(0);
            cardTitle.setCardTitle(37, this.mShowTitle, this.mCommentCount == 0 ? "" : this.mCommentCount + "个", null);
            ap.a(getRootView(), R.id.ask_button_bottom).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ WriterInfoCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    i.a("event_D192", null, ReaderApplication.getApplicationImp());
                    o.a(this.a.getEvnetListener().getFromActivity(), this.a.mAuthorData.d());
                }
            });
            AudioAuthorStateView audioAuthorStateView = (AudioAuthorStateView) ap.a(getRootView(), R.id.answer_info_layout);
            audioAuthorStateView.setType(5);
            audioAuthorStateView.a(this.mAuthorData);
        }
    }

    public int getResLayoutId() {
        return R.layout.author_page_writer_info_layout;
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        this.mServerTitle = jSONObject.optString("title");
        JSONObject optJSONObject = jSONObject.optJSONObject("manitoInfo");
        if (optJSONObject != null) {
            optJSONObject = optJSONObject.optJSONObject("info");
            if (optJSONObject != null) {
                this.mAuthorData = new com.qq.reader.module.question.data.a();
                this.mAuthorData.a(optJSONObject);
                return true;
            }
        }
        return false;
    }
}
