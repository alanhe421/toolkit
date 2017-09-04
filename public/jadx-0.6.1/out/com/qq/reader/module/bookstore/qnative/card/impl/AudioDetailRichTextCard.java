package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.common.utils.ag;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class AudioDetailRichTextCard extends a {
    private final String EXPECTED_REVENUE_KEY = "expectedRevenue";
    private final String INTRO_KEY = "albumRichIntro";
    private final String SPEAKER_INTRO_KEY = "speakerIntro";
    private final String TARGET_CLOUD_KEY = "targetCloud";
    private String richText;

    public AudioDetailRichTextCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.audio_detail_richtext_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (this.richText != null) {
            this.richText = "";
        }
        if (jSONObject == null) {
            return false;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("audio");
        if (optJSONObject != null && optJSONObject.optLong("cpid", 0) != 2000000804) {
            return false;
        }
        if ("albumRichIntro".equalsIgnoreCase(this.mType)) {
            this.richText = optJSONObject.optString("albumRichIntro");
        } else {
            optJSONObject = optJSONObject.optJSONObject("xmlyExtra");
            if (optJSONObject != null) {
                if ("expectedRevenue".equalsIgnoreCase(this.mType)) {
                    this.richText = optJSONObject.optString("expectedRevenue");
                } else if ("speakerIntro".equalsIgnoreCase(this.mType)) {
                    this.richText = optJSONObject.optString("speakerIntro");
                } else if ("targetCloud".equalsIgnoreCase(this.mType)) {
                    this.richText = optJSONObject.optString("targetCloud");
                }
            }
        }
        if (TextUtils.isEmpty(this.richText) || "null".equalsIgnoreCase(this.richText)) {
            return false;
        }
        return true;
    }

    public void attachView() {
        if (!TextUtils.isEmpty(this.richText)) {
            LinearLayout linearLayout = (LinearLayout) ap.a(getRootView(), R.id.container);
            linearLayout.removeAllViews();
            ((TextView) ap.a(getRootView(), R.id.tv_title)).setText(this.mShowTitle);
            linearLayout.addView(ag.a(getEvnetListener().getFromActivity(), ag.a(this.richText)));
        }
    }
}
