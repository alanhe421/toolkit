package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.login.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class UserCenterMyQuestionCard extends a {
    int bookCoinsCount = 1000;
    int mListenCount = 0;
    int mQuestionCount = 10;
    int otherListenCount = 100;

    public UserCenterMyQuestionCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.user_center_fenda_enter_admin_litle_item;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mQuestionCount = jSONObject.optInt("mQuestionCount");
        this.otherListenCount = jSONObject.optInt("qListenCount");
        this.bookCoinsCount = jSONObject.optInt("qListenReward");
        this.mListenCount = jSONObject.optInt("mListenCount");
        return true;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.state_text);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.count);
        ((TextView) ap.a(getRootView(), R.id.title)).setText(R.string.usercenter_fenda_questions);
        if (this.mQuestionCount > 0) {
            textView2.setText(String.format("(%d)", new Object[]{Integer.valueOf(this.mQuestionCount)}));
            textView2.setVisibility(0);
        } else {
            textView2.setVisibility(8);
        }
        if (this.otherListenCount > 0) {
            textView.setText(String.format(ReaderApplication.getApplicationImp().getString(R.string.usercenter_fenda_questions_state_ticket), new Object[]{Integer.valueOf(this.otherListenCount), Integer.valueOf(this.bookCoinsCount)}));
            textView.setVisibility(0);
        } else {
            textView.setVisibility(8);
        }
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterMyQuestionCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_D158", null, ReaderApplication.getApplicationImp());
                o.c(this.a.getEvnetListener().getFromActivity(), c.c().c(), "0", "我的提问");
            }
        });
        View a = ap.a(getRootView(), R.id.localstore_adv_divider);
        LayoutParams layoutParams = a.getLayoutParams();
        layoutParams.height = ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.common_dp_1);
        a.setLayoutParams(layoutParams);
    }
}
