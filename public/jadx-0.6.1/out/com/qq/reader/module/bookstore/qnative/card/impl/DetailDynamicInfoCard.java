package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class DetailDynamicInfoCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private ArrayList<a> mDynamicInfoItemList;
    private int mIsHasMore = 0;
    private String mMoreQurl = "";

    public static class a {
        public String a;
        public String b;
        public String c;
        public String d;
        public int e;
        public String f;
    }

    public DetailDynamicInfoCard(b bVar, String str) {
        super(bVar, str);
    }

    public void attachView() {
        if (this.mDynamicInfoItemList == null || this.mDynamicInfoItemList.size() < 1) {
            i.a("event_F325", null, ReaderApplication.getApplicationImp().getApplicationContext());
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < this.mDynamicInfoItemList.size(); i++) {
                a aVar = (a) this.mDynamicInfoItemList.get(i);
                if (i == 0) {
                    stringBuilder.append(aVar.e);
                } else {
                    stringBuilder.append(",").append(aVar.e);
                }
            }
            Map hashMap = new HashMap();
            hashMap.put(s.ORIGIN, stringBuilder.toString());
            i.a("event_F325", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
        }
        View a = ap.a(getRootView(), R.id.detail_common_title);
        if (a != null) {
            TextView textView = (TextView) a.findViewById(R.id.title_intro);
            ((TextView) a.findViewById(R.id.title_name)).setText("周边动态");
            textView.setVisibility(8);
            a.findViewById(R.id.title_divider).setVisibility(8);
        }
        View a2 = ap.a(getRootView(), R.id.detail_around_item_1);
        a = ap.a(getRootView(), R.id.detail_around_item_2);
        View a3 = ap.a(getRootView(), R.id.detail_around_item_3);
        View a4 = ap.a(getRootView(), R.id.detail_around_item_more);
        if (this.mDynamicInfoItemList != null) {
            int size = this.mDynamicInfoItemList.size();
            if (size == 1) {
                a2.setVisibility(0);
                a.setVisibility(8);
                a3.setVisibility(8);
                setDynamicInfo(a2, (a) this.mDynamicInfoItemList.get(0));
            } else if (size == 2) {
                a2.setVisibility(0);
                a.setVisibility(0);
                a3.setVisibility(8);
                setDynamicInfo(a2, (a) this.mDynamicInfoItemList.get(0));
                setDynamicInfo(a, (a) this.mDynamicInfoItemList.get(1));
            } else if (size >= 3) {
                a2.setVisibility(0);
                a.setVisibility(0);
                a3.setVisibility(0);
                setDynamicInfo(a2, (a) this.mDynamicInfoItemList.get(0));
                setDynamicInfo(a, (a) this.mDynamicInfoItemList.get(1));
                setDynamicInfo(a3, (a) this.mDynamicInfoItemList.get(2));
            }
            if (this.mIsHasMore == 1) {
                a4.setVisibility(0);
            } else {
                a4.setVisibility(8);
            }
            a4.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DetailDynamicInfoCard a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (!TextUtils.isEmpty(this.a.mMoreQurl)) {
                        try {
                            c.a(this.a.getEvnetListener().getFromActivity(), this.a.mMoreQurl, null);
                        } catch (Exception e) {
                        }
                    }
                }
            });
        }
    }

    public int getResLayoutId() {
        return R.layout.detail_page_around_card_layout;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        int i = 0;
        if (jSONObject == null) {
            return false;
        }
        this.mIsHasMore = jSONObject.optInt("hasMore");
        this.mMoreQurl = jSONObject.optString("morequrl");
        JSONArray optJSONArray = jSONObject.optJSONArray("dynamicInfo");
        if (optJSONArray == null || optJSONArray.length() < 1) {
            return false;
        }
        this.mDynamicInfoItemList = new ArrayList();
        while (i < optJSONArray.length()) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                a aVar = new a();
                aVar.a = optJSONObject.optString("time");
                aVar.b = optJSONObject.optString(SocialConstants.PARAM_APP_DESC);
                aVar.c = optJSONObject.optString("picUrl");
                aVar.d = optJSONObject.optString("typeIcon");
                aVar.e = optJSONObject.optInt("type");
                aVar.f = optJSONObject.optString("qurl");
                this.mDynamicInfoItemList.add(aVar);
            }
            i++;
        }
        return true;
    }

    private void setDynamicInfo(View view, a aVar) {
        if (view != null && aVar != null) {
            TextView textView = (TextView) view.findViewById(R.id.detail_around_date);
            ImageView imageView = (ImageView) view.findViewById(R.id.detail_around_pic);
            if (textView != null) {
                textView.setText(aVar.a);
            }
            if (imageView != null) {
                setImage(imageView, aVar.c, null);
            }
            final String str = aVar.f;
            final int i = aVar.e;
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ DetailDynamicInfoCard c;

                public void onClick(View view) {
                    try {
                        Map hashMap = new HashMap();
                        hashMap.put(s.ORIGIN, i + "");
                        i.a("event_F326", hashMap, ReaderApplication.getApplicationImp().getApplicationContext());
                        c.a(this.c.getEvnetListener().getFromActivity(), str, null);
                    } catch (Exception e) {
                    }
                }
            });
        }
    }
}
