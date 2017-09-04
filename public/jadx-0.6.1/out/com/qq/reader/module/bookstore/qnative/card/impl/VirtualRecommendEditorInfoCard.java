package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.widget.UserCircleImageView;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class VirtualRecommendEditorInfoCard extends a {
    private long id;
    private boolean isShowDivider = false;
    private String mEditorInfo;
    private String mEditorName;
    private String mImgUrl;

    public VirtualRecommendEditorInfoCard(b bVar, boolean z) {
        super(bVar, "");
        this.isShowDivider = z;
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_recommend_editor_info;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        this.id = jSONObject.optLong("id");
        this.mImgUrl = jSONObject.optString("avatar");
        this.mEditorInfo = jSONObject.optString(SocialConstants.PARAM_APP_DESC);
        this.mEditorName = jSONObject.optString("name");
        return true;
    }

    public void attachView() {
        c.a(getEvnetListener().getFromActivity()).a(this.mImgUrl, (UserCircleImageView) ap.a(getRootView(), R.id.iv_editor_avatar), com.qq.reader.common.imageloader.a.a().j());
        TextView textView = (TextView) ap.a(getRootView(), R.id.tv_editor_name);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.tv_editor_des);
        textView2.setMaxLines(2);
        textView.setText(this.mEditorName);
        textView2.setText(this.mEditorInfo);
        View a = ap.a(getRootView(), R.id.rl_title_bk);
        a.setBackgroundResource(R.drawable.common_card_background);
        a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ VirtualRecommendEditorInfoCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.clickStatics();
                o.a(this.a.getEvnetListener().getFromActivity(), this.a.mEditorName, this.a.id);
            }
        });
        showTitleDivider(this.isShowDivider);
    }

    private void showTitleDivider(boolean z) {
        View a = ap.a(getRootView(), R.id.divider_line);
        if (z) {
            a.setVisibility(0);
        } else {
            a.setVisibility(8);
        }
    }

    protected void clickStatics() {
        Map hashMap = new HashMap();
        hashMap.put("editor", this.mEditorName);
        i.a("event_F143", hashMap, ReaderApplication.getApplicationImp());
    }
}
