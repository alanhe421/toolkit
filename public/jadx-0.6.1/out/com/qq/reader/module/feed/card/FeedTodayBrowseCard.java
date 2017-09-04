package com.qq.reader.module.feed.card;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.data.impl.FeedBaseCard;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class FeedTodayBrowseCard extends FeedBaseCard {
    public static final String TYPE_LAST_BROWSE = "type_last_browse";
    public static final String TYPE_TODAY_BROWSE = "type_today_browse";

    public FeedTodayBrowseCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.concept_today_browse;
    }

    public void attachView() {
        View a = ap.a(getRootView(), R.id.content_layout);
        if (getPosition() == 1 || getPosition() == 0) {
            a.setVisibility(8);
        } else {
            a.setVisibility(0);
        }
        TextView textView = (TextView) ap.a(getRootView(), R.id.browse_text);
        if (getType().equals(TYPE_TODAY_BROWSE)) {
            textView.setText("今日推荐已浏览完，继续上滑查看历史");
        } else if (getType().equals(TYPE_LAST_BROWSE)) {
            textView.setText("上次浏览到这里");
        }
        a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedTodayBrowseCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("lastBrowseRefresh", true);
                bundle.putBoolean("fromFeedAction", true);
                this.a.getEvnetListener().doFunction(bundle);
            }
        });
    }

    public boolean parseData(JSONObject jSONObject) {
        return jSONObject == null ? true : true;
    }

    public boolean isLongClickEnable() {
        return false;
    }

    public boolean isShowDivider() {
        return false;
    }

    public boolean swipeEnable() {
        return false;
    }
}
