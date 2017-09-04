package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class EndPageInteractionCard extends a {
    RelativeLayout bgLayout;
    public boolean isFinish;
    TextView shareView;
    RelativeLayout showLayout;
    TextView v1;
    TextView v2;
    TextView v3;

    public EndPageInteractionCard(b bVar, String str) {
        super(bVar, str);
    }

    public boolean isExpired() {
        return false;
    }

    public int getResLayoutId() {
        return R.layout.readpage_chapter_active_;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        return true;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.text_author_word);
        if (this.isFinish) {
            textView.setText(R.string.author_word_complete);
        } else {
            textView.setText(R.string.author_word_no_complete);
        }
        initInterAction();
    }

    private void initInterAction() {
        this.v1 = (TextView) ap.a(getRootView(), R.id.interaction_v1);
        this.v1.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ EndPageInteractionCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Bundle access$000 = this.a.getInteractionBundle(0);
                if (this.a.getEvnetListener() != null) {
                    this.a.getEvnetListener().doFunction(access$000);
                }
                i.a("event_B21", null, ReaderApplication.getApplicationImp());
            }
        });
        this.v2 = (TextView) ap.a(getRootView(), R.id.interaction_v2);
        this.v2.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ EndPageInteractionCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Bundle access$000 = this.a.getInteractionBundle(1);
                if (this.a.getEvnetListener() != null) {
                    this.a.getEvnetListener().doFunction(access$000);
                }
                j.a(21, 1);
            }
        });
        if (isCanMonthlyTicket()) {
            this.v3 = (TextView) ap.a(getRootView(), R.id.interaction_v3);
            this.v3.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ EndPageInteractionCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    Bundle access$000 = this.a.getInteractionBundle(2);
                    if (this.a.getEvnetListener() != null) {
                        this.a.getEvnetListener().doFunction(access$000);
                    }
                    i.a("event_B23", null, ReaderApplication.getApplicationImp());
                }
            });
        } else {
            this.v3.setBackgroundResource(R.drawable.reword_button_disable);
        }
        this.shareView = (TextView) ap.a(getRootView(), R.id.share_view);
        this.shareView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ EndPageInteractionCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("KEY_ACTION", "show_share_dialog");
                if (this.a.getEvnetListener() != null) {
                    this.a.getEvnetListener().doFunction(bundle);
                }
                j.a(19, 1);
            }
        });
    }

    private boolean isCanMonthlyTicket() {
        return true;
    }

    private Bundle getInteractionBundle(int i) {
        Bundle bundle = new Bundle();
        bundle.putString("KEY_ACTION", "detail_2_reward");
        bundle.putInt("PARA_TYPE_REWARD_TAB_INDEX", i);
        bundle.putString("PARA_TYPE_REWARD_EXTRA_URL_PARAMS", "");
        return bundle;
    }
}
