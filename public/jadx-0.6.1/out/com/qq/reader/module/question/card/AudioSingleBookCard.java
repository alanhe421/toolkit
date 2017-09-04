package com.qq.reader.module.question.card;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class AudioSingleBookCard extends a {
    private static final String JSON_KEY_BID = "id";
    private static final String JSON_KEY_CHANNEL_ID = "origin";
    private static final String JSON_KEY_COVER = "cover";
    private static final String JSON_KEY_DESC = "intro";
    private static final String JSON_KEY_STAT_PARAMS = "statParams";
    private static final String JSON_KEY_TITLE = "title";
    private String channelId;
    private String mBookid;
    private String mCoverUrl;
    private String mDesc;
    private String mTitle;

    public AudioSingleBookCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.audio_bookitem_layout;
    }

    public void attachView() {
        ap.a(getRootView(), R.id.divider_top).setVisibility(0);
        setImage((ImageView) ap.a(getRootView(), R.id.concept_cover_img), getCoverUrl(), null);
        TextView textView = (TextView) ap.a(getRootView(), R.id.concept_title);
        TextView textView2 = (TextView) ap.a(getRootView(), R.id.concept_content);
        textView.setText(this.mTitle);
        if (TextUtils.isEmpty(this.mDesc)) {
            textView2.setVisibility(8);
            textView.setMaxLines(2);
        } else {
            textView.setMaxLines(1);
            textView2.setVisibility(0);
            textView2.setText(this.mDesc);
        }
        getRootView().setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AudioSingleBookCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Map hashMap = new HashMap();
                hashMap.put("origin", this.a.channelId);
                i.a("event_D163", hashMap, ReaderApplication.getApplicationImp());
                o.a(this.a.getEvnetListener().getFromActivity(), this.a.mBookid, null, null, null);
            }
        });
    }

    public boolean parseData(JSONObject jSONObject) {
        this.mTitle = jSONObject.optString("title");
        this.mCoverUrl = jSONObject.optString(JSON_KEY_COVER);
        this.mBookid = jSONObject.optString("id");
        this.mDesc = jSONObject.optString(JSON_KEY_DESC);
        JSONObject optJSONObject = jSONObject.optJSONObject(JSON_KEY_STAT_PARAMS);
        if (optJSONObject != null) {
            this.channelId = optJSONObject.optString("origin");
        }
        return true;
    }

    public String getBookid() {
        return this.mBookid;
    }

    public String getCoverUrl() {
        if (TextUtils.isEmpty(this.mCoverUrl) && !TextUtils.isEmpty(this.mBookid)) {
            this.mCoverUrl = ao.g(Long.valueOf(this.mBookid).longValue());
        }
        return this.mCoverUrl;
    }
}
