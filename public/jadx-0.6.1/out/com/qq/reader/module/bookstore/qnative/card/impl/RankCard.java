package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.ae;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONObject;

public class RankCard extends a {
    private String title;

    public RankCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.localstore_card_bankcard;
    }

    public void attachView() {
        if (!TextUtils.isEmpty(this.title)) {
            CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.title_btn);
            cardTitle.setVisibility(0);
            cardTitle.setCardTitle(37, this.title);
        }
        ViewGroup viewGroup = (ViewGroup) getRootView().findViewById(R.id.container);
        viewGroup.removeAllViews();
        for (int i = 0; i < getItemList().size(); i++) {
            final ae aeVar = (ae) getItemList().get(i);
            View rankItemLayout = new RankItemLayout(getEvnetListener().getFromActivity());
            rankItemLayout.setVisibility(0);
            rankItemLayout.setItemData(aeVar);
            rankItemLayout.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ RankCard b;
                private long c = 0;

                public void onClick(View view) {
                    long timeInMillis = Calendar.getInstance().getTimeInMillis();
                    if (timeInMillis - this.c > 300) {
                        this.c = timeInMillis;
                        aeVar.a(this.b.getEvnetListener());
                    }
                }
            });
            viewGroup.addView(rankItemLayout);
        }
    }

    public boolean parseData(JSONObject jSONObject) throws Exception {
        getItemList().clear();
        this.title = jSONObject.optString("title");
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        int i = 0;
        while (optJSONArray != null && i < optJSONArray.length()) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            s aeVar = new ae();
            aeVar.parseData(optJSONObject);
            aeVar.a(this.mSex);
            addItem(aeVar);
            i++;
        }
        return true;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
