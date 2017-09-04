package com.qq.reader.module.audio.card;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class AudioZoneEntranceCard extends com.qq.reader.module.bookstore.qnative.card.a {
    protected static final String JSON_KEY_ICON = "icon";
    protected static final String JSON_KEY_JUMP = "qurl";
    protected static final String JSON_KEY_NAME = "name";
    private List<a> mDataList = new ArrayList();
    private int[] viewIds = new int[]{R.id.icon1, R.id.icon2, R.id.icon3, R.id.icon4, R.id.icon5, R.id.name1, R.id.name2, R.id.name3, R.id.name4, R.id.name5};

    private class a {
        String a;
        String b;
        String c;
        final /* synthetic */ AudioZoneEntranceCard d;

        private a(AudioZoneEntranceCard audioZoneEntranceCard) {
            this.d = audioZoneEntranceCard;
        }
    }

    public AudioZoneEntranceCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.audio_zone_entrance_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return false;
        }
        while (i < optJSONArray.length()) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                a aVar = new a();
                aVar.a = optJSONObject.optString("icon");
                aVar.b = optJSONObject.optString(JSON_KEY_NAME);
                aVar.c = optJSONObject.optString(JSON_KEY_JUMP);
                this.mDataList.add(aVar);
            }
            i++;
        }
        return true;
    }

    public void attachView() {
        for (int i = 0; i < this.mDataList.size(); i++) {
            final a aVar = (a) this.mDataList.get(i);
            if (aVar != null) {
                ImageView imageView = (ImageView) ap.a(getRootView(), this.viewIds[i]);
                c.a(getEvnetListener().getFromActivity()).a(aVar.a, imageView, com.qq.reader.common.imageloader.a.a().j());
                imageView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ AudioZoneEntranceCard b;

                    public void onClick(View view) {
                        try {
                            com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), aVar.c);
                            Map hashMap = new HashMap();
                            hashMap.put(s.ORIGIN, aVar.b);
                            i.a("event_B306", hashMap, ReaderApplication.getApplicationImp());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                ((TextView) ap.a(getRootView(), this.viewIds[i + 5])).setText(aVar.b);
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, aVar.b);
                i.a("event_B302", hashMap, ReaderApplication.getApplicationImp());
            }
        }
    }
}
