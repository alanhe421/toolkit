package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RankHeadCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private List<a> mItemList = new ArrayList();

    private class a {
        public long[] a;
        public String b;
        public String c;
        public String d;
        final /* synthetic */ RankHeadCard e;

        a(RankHeadCard rankHeadCard, JSONObject jSONObject) throws JSONException {
            this.e = rankHeadCard;
            this.b = jSONObject.optString("title");
            this.d = jSONObject.optString("actionTag");
            this.c = jSONObject.optString("actionId");
            JSONArray jSONArray = jSONObject.getJSONArray("bidList");
            this.a = new long[jSONArray.length()];
            for (int i = 0; i < jSONArray.length(); i++) {
                this.a[i] = (long) ((Integer) jSONArray.get(i)).intValue();
            }
        }
    }

    public RankHeadCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.rankboard_head_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONArray jSONArray = jSONObject.getJSONArray("data");
        for (int i = 0; i < jSONArray.length(); i++) {
            this.mItemList.add(new a(this, jSONArray.getJSONObject(i)));
        }
        return true;
    }

    private void setData(ViewGroup viewGroup, final a aVar) {
        if (viewGroup != null && aVar != null) {
            ((TextView) ap.a(viewGroup, R.id.title)).setText(aVar.b);
            if (aVar.a != null) {
                ImageView[] imageViewArr = new ImageView[]{(ImageView) ap.a(viewGroup, R.id.classify_cover), (ImageView) ap.a(viewGroup, R.id.classify_cover_left), (ImageView) ap.a(viewGroup, R.id.classify_cover_right)};
                int i = 0;
                while (i < 3 && i < aVar.a.length) {
                    c.a(getEvnetListener().getFromActivity()).a(ao.g(Long.valueOf(aVar.a[i]).longValue()), imageViewArr[i], com.qq.reader.common.imageloader.a.a().n());
                    i++;
                }
            }
            viewGroup.setOnClickListener(new com.qq.reader.module.bookstore.qnative.c.c(this) {
                final /* synthetic */ RankHeadCard b;

                public void a(View view) {
                    o.b(this.b.getEvnetListener().getFromActivity(), aVar.b, aVar.c, aVar.d, null);
                    Map hashMap = new HashMap();
                    hashMap.put("rankboard", "abtest_A");
                    hashMap.put("actionId", aVar.c);
                    hashMap.put("pre", String.valueOf(d.aS(this.b.getEvnetListener().getFromActivity())));
                    i.a("event_B246", hashMap, this.b.getEvnetListener().getFromActivity());
                }
            });
        }
    }

    public void attachView() {
        ViewGroup viewGroup = (ViewGroup) ap.a(getRootView(), R.id.item1_layout);
        setData((ViewGroup) ap.a(getRootView(), R.id.item0_layout), (a) this.mItemList.get(0));
        setData(viewGroup, (a) this.mItemList.get(1));
    }
}
