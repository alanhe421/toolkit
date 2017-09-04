package com.qq.reader.module.bookstore.qnative.card.impl;

import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.model.SearchToolItem;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class SearchToolConditionCard extends a {
    private final int MAX_ITEM_COUNT = 3;

    public SearchToolConditionCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.search_tool_condition_ui;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONArray optJSONArray = jSONObject.optJSONArray("conditions");
        List itemList = getItemList();
        if (itemList != null) {
            itemList.clear();
        }
        int i = 0;
        while (optJSONArray != null && i < optJSONArray.length()) {
            SearchToolItem searchToolItem = new SearchToolItem();
            searchToolItem.parseData(optJSONArray.getJSONObject(i));
            getItemList().add(searchToolItem);
            i++;
        }
        return true;
    }

    public void attachView() {
        List itemList = getItemList();
        View rootView = getRootView();
        ((TextView) rootView.findViewById(R.id.tv_search_more)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchToolConditionCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.q(this.a.getEvnetListener().getFromActivity(), null);
                i.a("event_C119", null, ReaderApplication.getApplicationImp());
            }
        });
        LinearLayout linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_item_container);
        linearLayout.removeAllViewsInLayout();
        int i = 0;
        while (itemList != null && i < itemList.size() && i < 3) {
            View inflate = View.inflate(rootView.getContext(), R.layout.search_tool_condition_item_ui, null);
            final SearchToolItem searchToolItem = (SearchToolItem) itemList.get(i);
            LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            layoutParams.setMargins(0, 0, 0, (int) TypedValue.applyDimension(1, 12.0f, ReaderApplication.getApplicationImp().getResources().getDisplayMetrics()));
            inflate.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ SearchToolConditionCard c;

                public void onClick(View view) {
                    this.c.setStatics(i);
                    o.a(this.c.getEvnetListener().getFromActivity(), searchToolItem.getFlowText(), searchToolItem.getConditionParam(), false, null);
                }
            });
            fillItemView(inflate, searchToolItem);
            linearLayout.addView(inflate, layoutParams);
            i++;
        }
        linearLayout = (LinearLayout) rootView.findViewById(R.id.ll_condition);
        if (itemList == null || itemList.size() == 0) {
            linearLayout.setVisibility(8);
        } else {
            linearLayout.setVisibility(0);
        }
        ((CardTitle) rootView.findViewById(R.id.search_tool_card_title)).setCardTitle(37, "大家都在搜");
    }

    public void setStatics(int i) {
        switch (i) {
            case 0:
                i.a("event_C116", null, ReaderApplication.getApplicationImp());
                return;
            case 1:
                i.a("event_C117", null, ReaderApplication.getApplicationImp());
                return;
            case 2:
                i.a("event_C118", null, ReaderApplication.getApplicationImp());
                return;
            default:
                return;
        }
    }

    private void fillItemView(View view, SearchToolItem searchToolItem) {
        int[] iArr = new int[]{R.id.tv_tag1, R.id.tv_tag2, R.id.tv_tag3};
        TextView textView = (TextView) view.findViewById(R.id.tv_plus1);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_plus2);
        textView.setVisibility(8);
        textView2.setVisibility(8);
        ArrayList conditionTextArray = searchToolItem.getConditionTextArray();
        int i = 0;
        while (conditionTextArray != null && i < conditionTextArray.size() && i < iArr.length) {
            ((TextView) view.findViewById(iArr[i])).setText((CharSequence) conditionTextArray.get(i));
            i++;
        }
        if (conditionTextArray != null && conditionTextArray.size() >= 2) {
            textView.setVisibility(0);
        }
        if (conditionTextArray != null && conditionTextArray.size() >= 3) {
            textView2.setVisibility(0);
        }
    }
}
