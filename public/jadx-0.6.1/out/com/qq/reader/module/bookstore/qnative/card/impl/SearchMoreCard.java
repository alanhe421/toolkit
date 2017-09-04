package com.qq.reader.module.bookstore.qnative.card.impl;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.model.SearchToolItem;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import org.json.JSONObject;

public class SearchMoreCard extends a {
    private int[] conditionRes = new int[]{R.id.tv_con_1, R.id.tv_con_2, R.id.tv_con_3};
    private int mIndex;
    private SearchToolItem mItem;

    public SearchMoreCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.search_more_card_ui;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mItem = new SearchToolItem();
        this.mItem.parseData(jSONObject);
        return true;
    }

    public void attachView() {
        int i = 0;
        View rootView = getRootView();
        rootView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SearchMoreCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                o.a(this.a.getEvnetListener().getFromActivity(), this.a.mItem.getFlowText(), this.a.mItem.getConditionParam(), false, null);
            }
        });
        TextView textView = (TextView) rootView.findViewById(R.id.tv_rank);
        if (this.mItem.mIndex > 3) {
            textView.setBackgroundResource(R.drawable.search_more_normal);
        } else {
            textView.setBackgroundResource(R.drawable.search_more_top);
        }
        textView.setText(String.valueOf(this.mItem.mIndex));
        ArrayList conditionTextArray = this.mItem.getConditionTextArray();
        for (int findViewById : this.conditionRes) {
            ((TextView) rootView.findViewById(findViewById)).setText("");
        }
        while (conditionTextArray != null && i < conditionTextArray.size() && i < this.conditionRes.length) {
            ((TextView) rootView.findViewById(this.conditionRes[i])).setText((CharSequence) conditionTextArray.get(i));
            i++;
        }
    }
}
