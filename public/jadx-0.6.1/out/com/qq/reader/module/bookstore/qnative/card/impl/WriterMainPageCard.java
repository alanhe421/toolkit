package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.login.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class WriterMainPageCard extends a {
    boolean isEmpty;
    int isOpenQA;

    public WriterMainPageCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.user_center_fenda_enter_admin_litle_item;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int optInt = jSONObject.optInt("owner");
        JSONObject optJSONObject = jSONObject.optJSONObject("manitoInfo");
        this.isOpenQA = optJSONObject.optInt("active");
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
            if (isOpenQA()) {
                layoutParams.height = ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.common_dp_1);
            } else {
                layoutParams.height = 0;
            }
        } else if (isOpenQA()) {
            layoutParams.height = ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.common_dp_1);
        } else {
            layoutParams.height = ReaderApplication.getApplicationImp().getResources().getDimensionPixelSize(R.dimen.common_dp_1);
        }
        a.setLayoutParams(layoutParams);
        ((TextView) ap.a(getRootView(), R.id.title)).setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.author_center_my_home_page));
        ap.a(getRootView(), R.id.count).setVisibility(8);
        ap.a(getRootView(), R.id.state_text).setVisibility(8);
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ WriterMainPageCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.d(this.a.getEvnetListener().getFromActivity(), c.c().c(), c.c().a(), c.c().b(), null);
            }
        });
    }

    private boolean isOpenQA() {
        return this.isOpenQA == 1;
    }

    private boolean isEmptyCard() {
        return this.isEmpty;
    }
}
