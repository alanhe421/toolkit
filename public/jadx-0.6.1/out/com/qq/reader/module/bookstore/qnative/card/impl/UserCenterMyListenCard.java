package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
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

public class UserCenterMyListenCard extends a {
    int listenCount = 1;
    private int mInterActionCount = 0;
    private int mTotalCommentCount = 0;

    public UserCenterMyListenCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.user_center_fenda_enter_admin_litle_item;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.listenCount = jSONObject.optInt("mListenCount");
        this.mTotalCommentCount = jSONObject.optInt("totalCount");
        this.mInterActionCount = jSONObject.optInt("contentCount");
        return true;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.state_text);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.count);
        ((TextView) ap.a(getRootView(), R.id.title)).setText(ReaderApplication.getApplicationImp().getString(R.string.usercenter_fenda_listens));
        textView.setVisibility(8);
        if (this.listenCount > 0) {
            textView2.setText(String.format("(%d)", new Object[]{Integer.valueOf(this.listenCount)}));
            textView2.setVisibility(0);
        } else {
            textView2.setVisibility(8);
        }
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterMyListenCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                i.a("event_D159", null, ReaderApplication.getApplicationImp());
                o.c(this.a.getEvnetListener().getFromActivity(), c.c().c(), "1", ReaderApplication.getApplicationImp().getResources().getString(R.string.usercenter_fenda_listens));
            }
        });
        View a = ap.a(getRootView(), R.id.localstore_adv_divider);
        if (this.mTotalCommentCount == 0 && this.mInterActionCount == 0) {
            a.setVisibility(8);
        } else {
            a.setVisibility(0);
        }
    }
}
