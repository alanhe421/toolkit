package com.qq.reader.module.findhome;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.findhome.base.FindHomeBaseCard;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class FindHomeZoneCard extends FindHomeBaseCard {
    private List<String> mRedAdvList = new ArrayList();

    public FindHomeZoneCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.findhome_zone_item;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        int length = optJSONArray.length();
        for (int i = 0; i < length; i++) {
            s dVar = new d();
            dVar.parseData(optJSONArray.optJSONObject(i));
            if (!TextUtils.isEmpty(dVar.f())) {
                this.mRedAdvList.add(dVar.f());
            }
            addItem(dVar);
        }
        if (length > 0) {
            return true;
        }
        return false;
    }

    public void attachView() {
        List arrayList = new ArrayList();
        arrayList.add(ap.a(getRootView(), R.id.find_homepage_head_item_0));
        arrayList.add(ap.a(getRootView(), R.id.find_homepage_head_item_1));
        arrayList.add(ap.a(getRootView(), R.id.find_homepage_head_item_2));
        arrayList.add(ap.a(getRootView(), R.id.find_homepage_head_item_3));
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            if (i >= getItemList().size()) {
                bindZoneView((View) arrayList.get(i), null);
            } else {
                bindZoneView((View) arrayList.get(i), (d) getItemList().get(i));
            }
        }
    }

    private void bindZoneView(View view, final d dVar) {
        if (view != null) {
            if (dVar == null) {
                view.setVisibility(8);
                return;
            }
            view.setVisibility(0);
            ImageView imageView = (ImageView) view.findViewById(R.id.find_homepage_item_icon);
            TextView textView = (TextView) view.findViewById(R.id.find_homepage_item_title);
            try {
                c.a(getEvnetListener().getFromActivity()).a(dVar.a(), imageView, a.a().j());
            } catch (Exception e) {
                com.qq.reader.common.monitor.debug.c.e("FindHomeZoneCard", e.getMessage());
            }
            imageView.setTag(dVar.a());
            textView.setText(dVar.b());
            View findViewById = view.findViewById(R.id.find_homepage_item_redtip);
            if (com.qq.reader.cservice.adv.c.a(dVar.f())) {
                findViewById.setVisibility(0);
            } else {
                findViewById.setVisibility(8);
            }
            view.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FindHomeZoneCard b;

                public void onClick(View view) {
                    try {
                        com.qq.reader.qurl.c.a(this.b.getEvnetListener().getFromActivity(), dVar.c(), null);
                    } catch (Exception e) {
                        com.qq.reader.common.monitor.debug.c.e("FindHomeZone", e.getMessage());
                    }
                    com.qq.reader.cservice.adv.c.b(dVar.f());
                    this.b.handleClickStatistics(dVar.h());
                }
            });
            handleExposedStatistics(dVar.h());
        }
    }

    public void refresh() {
    }

    public List<String> getRedAdvList() {
        return this.mRedAdvList;
    }
}
