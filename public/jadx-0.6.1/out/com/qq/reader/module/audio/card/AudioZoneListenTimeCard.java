package com.qq.reader.module.audio.card;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class AudioZoneListenTimeCard extends a {
    protected static final String JSON_KEY_JUMP_TEXT = "action";
    protected static final String JSON_KEY_JUMP_URL = "qurl";
    protected static final String JSON_KEY_TIME = "intro";
    private String jumpText;
    private String jumpUrl;
    private String timeText;

    public AudioZoneListenTimeCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.audio_zone_listen_time_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.timeText = jSONObject.optString(JSON_KEY_TIME);
        this.jumpText = jSONObject.optString("action");
        this.jumpUrl = jSONObject.optString(JSON_KEY_JUMP_URL);
        return true;
    }

    public void refresh() {
        attachView();
    }

    public void attachView() {
        ((TextView) ap.a(getRootView(), R.id.time)).setText(this.timeText);
        TextView textView = (TextView) ap.a(getRootView(), R.id.jump);
        textView.setText(this.jumpText);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AudioZoneListenTimeCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    c.a(this.a.getEvnetListener().getFromActivity(), this.a.jumpUrl);
                    i.a("event_B311", null, ReaderApplication.getApplicationImp());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
