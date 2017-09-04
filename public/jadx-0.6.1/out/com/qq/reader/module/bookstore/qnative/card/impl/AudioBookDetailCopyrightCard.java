package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.text.SimpleDateFormat;
import org.json.JSONObject;

public class AudioBookDetailCopyrightCard extends a {
    private String siteName;

    public AudioBookDetailCopyrightCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_detail_copyright;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        if (jSONObject == null) {
            return false;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject("audio");
        if (optJSONObject != null) {
            this.siteName = optJSONObject.optString("siteName");
        }
        return true;
    }

    public void attachView() {
        View a = ap.a(getRootView(), R.id.detail_common_title);
        if (a != null) {
            ((TextView) a.findViewById(R.id.title_name)).setText("更多音频信息");
        }
        ((LinearLayout) ap.a(getRootView(), R.id.ll_origin_sec)).setVisibility(0);
        fillInfoIntoTextview(R.id.tv_origin_copyright, "版权：" + this.siteName);
        fillInfoIntoTextview(R.id.tv_copyright_origin, "本书由" + this.siteName + "提供录制");
        ap.a(getRootView(), R.id.copy_bottom_line).setVisibility(8);
    }

    private void fillInfoIntoTextview(int i, String str) {
        TextView textView = (TextView) ap.a(getRootView(), i);
        if (TextUtils.isEmpty(str)) {
            textView.setVisibility(8);
        } else {
            textView.setText(str);
        }
    }

    private String formatTimeString(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            str = new SimpleDateFormat("yyyy-MM-dd").format(simpleDateFormat.parse(str));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
