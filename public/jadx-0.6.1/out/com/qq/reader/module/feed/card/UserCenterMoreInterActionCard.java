package com.qq.reader.module.feed.card;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.k;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class UserCenterMoreInterActionCard extends FeedBaseCard {
    private long mCreateTime;
    private String mMessage;
    private String mPic;
    private String mQurl;

    public UserCenterMoreInterActionCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.user_center_more_interaction_item;
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.user_center_interaction_more_time);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.user_center_interaction_more_message);
        c.a(getEvnetListener().getFromActivity()).a(this.mPic, (ImageView) ap.a(getRootView(), R.id.user_center_interaction_more_user_icon), a.a().b());
        textView.setText(k.c(this.mCreateTime));
        textView2.setText(this.mMessage);
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ UserCenterMoreInterActionCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.a.mQurl)) {
                    try {
                        com.qq.reader.qurl.c.a(this.a.getEvnetListener().getFromActivity(), this.a.mQurl, null, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public boolean parseData(JSONObject jSONObject) {
        this.mCreateTime = jSONObject.optLong("createTime");
        this.mMessage = jSONObject.optString("message");
        this.mPic = jSONObject.optString("pic");
        this.mQurl = jSONObject.optString("qurl");
        return true;
    }
}
