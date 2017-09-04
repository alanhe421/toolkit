package com.qq.reader.module.findhome;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.qq.reader.cservice.adv.c;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.findhome.base.FindHomeBaseCard;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class FindHomePageCard extends FindHomeBaseCard {
    private List<FindHomeBaseCard> mCardList = new ArrayList();

    public FindHomePageCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.findhomepage;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONArray optJSONArray;
        this.mCardList.clear();
        List arrayList = new ArrayList();
        try {
            optJSONArray = jSONObject.optJSONArray("topList");
        } catch (Exception e) {
            optJSONArray = null;
        }
        if (optJSONArray != null && optJSONArray.length() > 0) {
            FindHomeZoneCard findHomeZoneCard = new FindHomeZoneCard(null, "FindHomeZoneCard");
            findHomeZoneCard.fillData(optJSONArray);
            findHomeZoneCard.setEventListener(getEvnetListener());
            if (findHomeZoneCard.isDataReady()) {
                this.mCardList.add(findHomeZoneCard);
                arrayList.addAll(findHomeZoneCard.getRedAdvList());
            }
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("list");
        if (optJSONArray2 != null && optJSONArray2.length() > 0) {
            int length = optJSONArray2.length();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject = optJSONArray2.optJSONObject(i);
                FindHomeItemCard a = e.a(null, optJSONObject.optInt("type"));
                if (a != null) {
                    a.fillData(optJSONObject);
                    a.setEventListener(getEvnetListener());
                    if (a.isDataReady()) {
                        this.mCardList.add(a);
                        if (!TextUtils.isEmpty(a.getExtRedDotId())) {
                            arrayList.add(a.getExtRedDotId());
                        }
                    }
                    if (i == length - 1) {
                        a.setDividerType(0);
                    } else {
                        a.setDividerType(1);
                    }
                }
            }
        }
        c.a(arrayList);
        try {
            ViewGroup viewGroup = (ViewGroup) getEvnetListener().getFromActivity().getWindow().getDecorView();
            int size = this.mCardList.size();
            for (int i2 = 0; i2 < size; i2++) {
                FindHomeBaseCard findHomeBaseCard = (FindHomeBaseCard) this.mCardList.get(i2);
                findHomeBaseCard.setView(findHomeBaseCard.inflateView(getEvnetListener().getFromActivity(), viewGroup));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return this.mCardList.size() > 0;
    }

    public void attachView(View view) {
        setView(view);
        super.attachView(view);
    }

    public void attachView() {
        long currentTimeMillis = System.currentTimeMillis();
        LinearLayout linearLayout = (LinearLayout) getRootView();
        linearLayout.removeAllViews();
        int size = this.mCardList.size();
        for (int i = 0; i < size; i++) {
            try {
                FindHomeBaseCard findHomeBaseCard = (FindHomeBaseCard) this.mCardList.get(i);
                findHomeBaseCard.attachView(findHomeBaseCard.getView());
                linearLayout.addView(findHomeBaseCard.getView());
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("FindHomePageCard", e.getMessage());
            }
        }
        System.out.println("ccc attach cost : " + (System.currentTimeMillis() - currentTimeMillis));
    }

    public void refreshData() {
        int size = this.mCardList.size();
        for (int i = 0; i < size; i++) {
            ((FindHomeBaseCard) this.mCardList.get(i)).refreshData();
        }
    }

    public void refresh() {
        long currentTimeMillis = System.currentTimeMillis();
        int size = this.mCardList.size();
        for (int i = 0; i < size; i++) {
            ((FindHomeBaseCard) this.mCardList.get(i)).refresh();
        }
        System.out.println("ccc refresh cost : " + (System.currentTimeMillis() - currentTimeMillis));
    }
}
