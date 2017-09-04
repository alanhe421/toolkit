package com.qq.reader.module.rookie.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.card.impl.CardTitle;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class RookieUserGeneCard extends com.qq.reader.module.bookstore.qnative.card.a {
    private a mGeneAdapter;
    List<String> mTagList = new ArrayList();

    public static class a extends BaseAdapter {
        private List<String> a = new ArrayList();

        public /* synthetic */ Object getItem(int i) {
            return a(i);
        }

        public void a(List<String> list) {
            this.a.clear();
            this.a.addAll(list);
        }

        public int getCount() {
            return this.a.size();
        }

        public String a(int i) {
            return (String) this.a.get(i);
        }

        public long getItemId(int i) {
            return 0;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rookie_gene_item, viewGroup, false);
            }
            ((TextView) view.findViewById(R.id.rookie_tag)).setText(a(i));
            return view;
        }
    }

    public RookieUserGeneCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.rookie_usergene;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        JSONArray optJSONArray = jSONObject.optJSONArray("myFavorTagsList");
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                this.mTagList.add(optJSONArray.optString(i));
            }
        }
        return true;
    }

    public void attachView() {
        CardTitle cardTitle = (CardTitle) ap.a(getRootView(), R.id.rookie_cardtitle);
        cardTitle.setCardTitle("我的喜好");
        cardTitle.setCardIntroduction(null);
        cardTitle = (CardTitle) ap.a(getRootView(), R.id.rookie_cardtitle_rec);
        cardTitle.setCardTitle("为你推荐");
        cardTitle.setCardIntroduction(null);
        this.mGeneAdapter = new a();
        this.mGeneAdapter.a(this.mTagList);
        ((GridView) ap.a(getRootView(), R.id.rookie_gene_gridview)).setAdapter(this.mGeneAdapter);
    }
}
