package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.JumpActivityParameter;
import com.tencent.feedback.proguard.R;
import org.json.JSONException;
import org.json.JSONObject;

public class WriterQAPageCard extends a {
    int answerCont;
    private boolean isEmpty;

    public WriterQAPageCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.user_center_fenda_enter_admin_litle_item;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int optInt = jSONObject.optInt("owner");
        JSONObject optJSONObject = jSONObject.optJSONObject("manitoInfo");
        this.answerCont = optJSONObject.optInt("toAnswer");
        optJSONObject = optJSONObject.optJSONObject("info");
        if (optJSONObject != null) {
            int optInt2 = optJSONObject.optInt("dynamicListCount");
            int optInt3 = optJSONObject.optInt("commentCount") + optJSONObject.optInt("replyCount");
            int optInt4 = optJSONObject.optInt("booksCount");
            if (optInt2 == 0 && optInt3 == 0 && optInt4 == 0) {
                this.isEmpty = true;
            }
        }
        if (optInt != 1) {
            return false;
        }
        return true;
    }

    public void attachView() {
        View a = ap.a(getRootView(), R.id.localstore_adv_divider);
        LayoutParams layoutParams = a.getLayoutParams();
        if (isEmptyCard()) {
            layoutParams.height = 0;
        } else {
            layoutParams.height = 0;
        }
        a.setLayoutParams(layoutParams);
        ((TextView) ap.a(getRootView(), R.id.title)).setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.author_center_my_fans_qa_page));
        ap.a(getRootView(), R.id.count).setVisibility(8);
        TextView textView = (TextView) ap.a(getRootView(), R.id.state_text);
        textView.setText(String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.author_center_waiting_answer_count), new Object[]{Integer.valueOf(this.answerCont)}));
        if (this.answerCont <= 0) {
            textView.setVisibility(8);
        }
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WriterQAPageCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                JumpActivityParameter jumpActivityParameter = new JumpActivityParameter();
                jumpActivityParameter.a(1007);
                o.b(this.a.getEvnetListener().getFromActivity(), 2, jumpActivityParameter);
            }
        });
    }

    private boolean isEmptyCard() {
        return this.isEmpty;
    }

    public void setWaitingCount(int i) {
        this.answerCont = i;
        TextView textView = (TextView) ap.a(getRootView(), R.id.state_text);
        textView.setText(String.format(ReaderApplication.getApplicationImp().getResources().getString(R.string.author_center_waiting_answer_count), new Object[]{Integer.valueOf(this.answerCont)}));
        if (this.answerCont <= 0) {
            textView.setVisibility(8);
        }
        try {
            doReSave();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean reSaveDataBuild(JSONObject jSONObject) throws JSONException {
        jSONObject.optJSONObject("manitoInfo").putOpt("toAnswer", Integer.valueOf(this.answerCont));
        return true;
    }
}
