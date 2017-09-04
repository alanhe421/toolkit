package com.qq.reader.module.bookstore.qnative.page.impl;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.dynamicload.Lib.DLConstants;
import com.qq.reader.ReaderApplication;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.protocol.H5GameChargeTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.impl.ListCardCommon;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.item.v;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NativeServerTodayReadPage extends af {
    private ArrayList<Integer> a = new ArrayList();

    public class TodayReadListCard extends ListCardCommon {
        private int plan = 0;

        public TodayReadListCard(b bVar, String str) {
            super(bVar, str);
        }

        public void setPlan(int i) {
            this.plan = i;
        }

        public s createListItem() {
            return new a(NativeServerTodayReadPage.this);
        }

        public void attachView(View view) {
            Map hashMap = new HashMap();
            hashMap.put("plan", this.plan + "");
            i.a("event_F125", hashMap, ReaderApplication.getApplicationImp());
            super.attachView(view);
        }

        protected boolean parseData(JSONObject jSONObject) throws Exception {
            int i = 0;
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            if (optJSONArray == null) {
                return false;
            }
            int length = optJSONArray.length();
            getItemList().clear();
            if (length <= 0) {
                return false;
            }
            while (i < length) {
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                s createListItem = createListItem();
                jSONObject2.put("plan", this.plan);
                createListItem.parseData(jSONObject2);
                addItem(createListItem);
                i++;
            }
            return true;
        }

        public int getCardItemLayoutId() {
            return R.layout.localstore_listcard_item;
        }
    }

    class a extends v {
        final /* synthetic */ NativeServerTodayReadPage a;
        private String b;
        private int c;
        private int d;
        private String m;
        private String n;
        private boolean o = false;

        a(NativeServerTodayReadPage nativeServerTodayReadPage) {
            this.a = nativeServerTodayReadPage;
        }

        public void parseData(JSONObject jSONObject) {
            super.parseData(jSONObject);
            this.b = jSONObject.optString("date_title");
            this.c = jSONObject.optInt("form");
            this.m = jSONObject.optString("catel2name");
            this.n = jSONObject.optString("catel3name");
            this.d = jSONObject.optInt("plan");
        }

        private String u() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(e()).append(":").append(1).append(DLConstants.DEPENDENCY_PACKAGE_DIV).append(getAlg()).append(DLConstants.DEPENDENCY_PACKAGE_DIV);
            return stringBuilder.toString();
        }

        public void a(com.qq.reader.module.bookstore.qnative.c.a aVar) {
            Map hashMap = new HashMap();
            hashMap.put("plan", this.d + "");
            i.a("event_F126", hashMap, ReaderApplication.getApplicationImp());
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("uniteqqreader://nativepage/book/detail?").append("bid=").append(e()).append("&alg=").append(getAlg()).append(H5GameChargeTask.ITEMID).append(e());
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("ext_info_id", e());
                jSONObject.put("itemid", e());
                jSONObject.put(s.ALG, getAlg());
                stringBuilder.append("&statInfo=").append(URLEncoder.encode(jSONObject.toString(), "utf-8"));
                c.a(aVar.getFromActivity(), stringBuilder.toString(), null);
            } catch (Exception e) {
            }
        }

        public void a(View view, int i, boolean z) {
            super.a(view, i, z);
            View a = ap.a(view, R.id.top_time);
            if (TextUtils.isEmpty(this.b)) {
                a.setVisibility(8);
            } else {
                ap.a(view, R.id.top_adv_divider).setVisibility(0);
                a.setVisibility(0);
                ((TextView) a.findViewById(R.id.cardtitle_title)).setText(this.b);
            }
            TextView textView = (TextView) ap.a(view, R.id.concept_order);
            if (this.c == 1) {
                if (TextUtils.isEmpty(this.n)) {
                    textView.setVisibility(8);
                } else {
                    textView.setVisibility(0);
                    textView.setText(this.n);
                }
            } else if (TextUtils.isEmpty(m())) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.setText(m() + "字");
            }
            textView = (TextView) ap.a(view, R.id.concept_category);
            if (TextUtils.isEmpty(this.m)) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.setText(this.m);
            }
            if (!this.o && !TextUtils.isEmpty(u())) {
                Map hashMap = new HashMap();
                hashMap.put("event_feed_exposure", u());
                StatisticsManager.a().a("event_feed_exposure", hashMap);
                this.o = true;
            }
        }
    }

    public NativeServerTodayReadPage(Bundle bundle) {
        super(bundle);
    }

    public void b(JSONObject jSONObject) {
        super.b(jSONObject);
        JSONArray optJSONArray = jSONObject.optJSONArray("list");
        int optInt = jSONObject.optInt("plan");
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < optJSONArray.length(); i++) {
            try {
                JSONObject jSONObject2 = (JSONObject) optJSONArray.get(i);
                JSONArray optJSONArray2 = jSONObject2.optJSONArray("bookList");
                String optString = jSONObject2.optString("title");
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    if (i2 == 0 && optInt == 1) {
                        optJSONArray2.getJSONObject(i2).put("date_title", optString);
                    }
                    jSONArray.put(optJSONArray2.get(i2));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        ListCardCommon todayReadListCard = new TodayReadListCard(this, "list");
        todayReadListCard.setEventListener(l());
        todayReadListCard.fillData(jSONArray);
        ((TodayReadListCard) todayReadListCard).setPlan(optInt);
        this.k.add(todayReadListCard);
        this.l.put(todayReadListCard.getCardId(), todayReadListCard);
    }

    public String a(Bundle bundle) {
        return new com.qq.reader.module.bookstore.qnative.c(bundle).a(e.a, "common/dailyread?sex=" + d.aU(ReaderApplication.getApplicationImp()));
    }
}
