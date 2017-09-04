package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ObtainWelfareListCard extends a {
    private long mAdId;
    private String mCoverUrl;
    private String mDesc;
    private boolean mIsLongTerm;
    private boolean mIsNeedLogin;
    private boolean mIsShowBottomDivider;
    private String mJumpUrl;
    private String mTitle;

    public ObtainWelfareListCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.obtain_welfare_list_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        boolean z;
        boolean z2 = false;
        this.mTitle = jSONObject.optString("title");
        this.mCoverUrl = jSONObject.optString(MessageKey.MSG_ICON);
        this.mJumpUrl = jSONObject.optString("activityUrl");
        this.mDesc = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
        if (jSONObject.optInt("isNeedLogin") != 0) {
            z = true;
        } else {
            z = false;
        }
        this.mIsNeedLogin = z;
        if (jSONObject.optInt("isLongTerm") != 0) {
            z2 = true;
        }
        this.mIsLongTerm = z2;
        this.mAdId = jSONObject.optLong("adId");
        return true;
    }

    public void attachView() {
        int i;
        i.a("event_F105", null, ReaderApplication.getApplicationImp());
        TextView textView = (TextView) ap.a(getRootView(), R.id.obtain_welfare_list_card_title);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.obtain_welfare_list_card_description);
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.obtain_welfare_list_card_cover);
        ImageView imageView2 = (ImageView) ap.a(getRootView(), R.id.obtain_welfare_list_card_long_term);
        if (this.mIsShowBottomDivider) {
            ap.a(getRootView(), R.id.obtain_welfare_list_card_divider).setVisibility(0);
        } else {
            ap.a(getRootView(), R.id.obtain_welfare_list_card_divider).setVisibility(8);
        }
        textView.setText(this.mTitle);
        if (TextUtils.isEmpty(this.mDesc)) {
            textView2.setVisibility(8);
        } else {
            textView2.setText(this.mDesc);
            textView2.setVisibility(0);
        }
        if (this.mIsLongTerm) {
            i = 0;
        } else {
            i = 8;
        }
        imageView2.setVisibility(i);
        c.a(getEvnetListener().getFromActivity()).a(this.mCoverUrl, imageView, com.qq.reader.common.imageloader.a.a().j());
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ObtainWelfareListCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.mIsNeedLogin || com.qq.reader.common.login.c.b()) {
                    try {
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, String.valueOf(this.a.mAdId));
                        i.a("event_F106", hashMap, ReaderApplication.getApplicationImp());
                        com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), this.a.mJumpUrl);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                }
                com.qq.reader.common.login.c.a(this.a.getEvnetListener().getFromActivity(), 7);
                this.a.getEvnetListener().getFromActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    public void setIsShowBottomDivider(boolean z) {
        this.mIsShowBottomDivider = z;
    }
}
