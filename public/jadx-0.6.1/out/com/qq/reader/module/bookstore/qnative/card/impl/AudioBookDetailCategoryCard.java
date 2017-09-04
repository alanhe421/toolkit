package com.qq.reader.module.bookstore.qnative.card.impl;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.json.JSONObject;

public class AudioBookDetailCategoryCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private static final int STATUS_FINISHED = 2;
    private static final int STATUS_UPDATING = 1;
    private a categoryItem;

    private class a extends s {
        public int a;
        public long b;
        public String c;
        public int d;
        final /* synthetic */ AudioBookDetailCategoryCard e;

        private a(AudioBookDetailCategoryCard audioBookDetailCategoryCard) {
            this.e = audioBookDetailCategoryCard;
        }

        public void parseData(JSONObject jSONObject) {
            try {
                JSONObject optJSONObject = jSONObject.optJSONObject("audio");
                if (optJSONObject != null) {
                    this.c = optJSONObject.optString("adid");
                    this.a = optJSONObject.optInt("allAudioChapters", 0);
                    this.b = optJSONObject.optLong("lastUpdateTime", 0) * 1000;
                    this.d = optJSONObject.optInt("scheduleStatus", 1);
                }
            } catch (Exception e) {
                c.e("Error", e.getMessage());
            }
        }
    }

    public AudioBookDetailCategoryCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_audio_chapter_0;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.categoryItem = new a();
        this.categoryItem.parseData(jSONObject);
        return true;
    }

    public void attachView() {
        if (this.categoryItem != null) {
            TextView textView = (TextView) ap.a(getRootView(), R.id.chapter_0_content);
            TextView textView2 = (TextView) ap.a(getRootView(), R.id.chapter_0_updatetime);
            if (this.categoryItem.d == 1) {
                textView.setText("更新至" + this.categoryItem.a + "集");
                if (this.categoryItem.b > 0) {
                    textView2.setText("更新于" + new SimpleDateFormat("MM-dd HH:mm").format(new Date(this.categoryItem.b)));
                }
            } else if (this.categoryItem.d == 2) {
                textView.setText("共" + this.categoryItem.a + "集");
                textView2.setText("");
            }
            ap.a(getRootView(), R.id.chapter_0_root).setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ AudioBookDetailCategoryCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (this.a.getEvnetListener() != null) {
                        Bundle bundle = new Bundle();
                        try {
                            i.a("event_B252", null, ReaderApplication.getApplicationImp());
                            o.f(this.a.getEvnetListener().getFromActivity(), this.a.categoryItem.c, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
