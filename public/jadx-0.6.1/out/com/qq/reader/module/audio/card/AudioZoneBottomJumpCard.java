package com.qq.reader.module.audio.card;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class AudioZoneBottomJumpCard extends a {
    protected static final String JSON_KEY_CID = "cid";
    protected static final String JSON_KEY_MORE_QURL = "qurl";
    protected static final String JSON_KEY_TITLE = "title";
    private int mColumeId = 0;
    private int[] mRefreshIndex;
    private String moreUrl;

    public AudioZoneBottomJumpCard(b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.jump_button);
        textView.setText(this.mServerTitle);
        textView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ AudioZoneBottomJumpCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                try {
                    c.a(this.a.getEvnetListener().getFromActivity(), this.a.moreUrl);
                    i.a("event_B313", null, ReaderApplication.getApplicationImp());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public int getResLayoutId() {
        return R.layout.audio_zone_bottom_jump_card;
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        this.mServerTitle = jSONObject.optString("title");
        this.mColumeId = jSONObject.optInt(JSON_KEY_CID);
        this.moreUrl = jSONObject.optString(JSON_KEY_MORE_QURL);
        return true;
    }

    public void refresh() {
        initRandomItem(false);
        if ("新书速递".equals(this.mShowTitle) || "完本红文".equals(this.mShowTitle)) {
            i.a("event_C94", null, ReaderApplication.getApplicationImp());
            j.a(93, 2);
        } else if (this.mColumeId == 2331) {
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, String.valueOf(this.mColumeId));
            i.a("event_A100", hashMap, ReaderApplication.getApplicationImp());
        }
        attachView();
    }

    private void initRandomItem(boolean z) {
        boolean z2 = true;
        int i = 0;
        int size = getItemList().size();
        if (size != 0) {
            int i2;
            if (this.mRefreshIndex != null) {
                List arrayList = new ArrayList();
                for (i2 = 0; i2 < size; i2++) {
                    if (!contains(this.mRefreshIndex, i2)) {
                        arrayList.add(Integer.valueOf(i2));
                    }
                }
                i2 = this.mDispaly;
                size = arrayList.size();
                if (!(z && isExpired())) {
                    z2 = false;
                }
                int[] randomListIndex = getRandomListIndex(i2, size, z2);
                while (i < randomListIndex.length) {
                    this.mRefreshIndex[i] = ((Integer) arrayList.get(randomListIndex[i])).intValue();
                    i++;
                }
                return;
            }
            i2 = this.mDispaly;
            if (!(z && isExpired())) {
                z2 = false;
            }
            this.mRefreshIndex = getRandomListIndex(i2, size, z2);
        }
    }

    private boolean contains(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i == i2) {
                return true;
            }
        }
        return false;
    }
}
