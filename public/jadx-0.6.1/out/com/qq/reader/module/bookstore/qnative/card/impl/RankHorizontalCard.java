package com.qq.reader.module.bookstore.qnative.card.impl;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.item.ae;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.feed.card.view.CardMoreView;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class RankHorizontalCard extends a implements OnItemClickListener {
    private List<ae> mRankItems;
    private boolean mShowDivider;
    private String mTitle;

    public RankHorizontalCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.horizontal_rankboard_card;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.mTitle = jSONObject.optString("title");
        JSONArray optJSONArray = jSONObject.optJSONArray("data");
        if (this.mRankItems == null) {
            this.mRankItems = new ArrayList();
        }
        this.mRankItems.clear();
        for (int i = 0; i < optJSONArray.length(); i++) {
            ae aeVar = new ae();
            aeVar.parseData(optJSONArray.optJSONObject(i));
            this.mRankItems.add(aeVar);
        }
        return true;
    }

    public void attachView() {
        ViewGroup viewGroup = (ViewGroup) getRootView().findViewById(R.id.rankboard_h_listview);
        viewGroup.removeAllViews();
        for (int i = 0; i < this.mRankItems.size(); i++) {
            ViewGroup viewGroup2 = (ViewGroup) LayoutInflater.from(getEvnetListener().getFromActivity()).inflate(R.layout.horizontal_rankboard_itemview, null);
            final ae aeVar = (ae) this.mRankItems.get(i);
            viewGroup2.setVisibility(0);
            TextView textView = (TextView) viewGroup2.findViewById(R.id.rankboard_name);
            TextView textView2 = (TextView) viewGroup2.findViewById(R.id.bookname);
            c.a(getEvnetListener().getFromActivity()).a(ao.g(aeVar.f()), (ImageView) viewGroup2.findViewById(R.id.book_cover), com.qq.reader.common.imageloader.a.a().j());
            textView.setText(aeVar.a());
            textView2.setText(aeVar.c().substring(0, aeVar.c().indexOf(44)));
            viewGroup2.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ RankHorizontalCard b;

                public void onClick(View view) {
                    aeVar.a(this.b.getEvnetListener());
                }
            });
            viewGroup.addView(viewGroup2);
        }
        CardTitle titleView = getTitleView();
        if (!TextUtils.isEmpty(this.mTitle)) {
            titleView.setCardTitle(37, this.mTitle);
        }
        CardMoreView moreView = getMoreView();
        moreView.setText("查看完整榜单");
        moreView.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ RankHorizontalCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.a.mTitle)) {
                    String str;
                    if (this.a.mTitle.contains("男生")) {
                        str = "1";
                    } else if (this.a.mTitle.contains("女生")) {
                        str = "2";
                    } else {
                        str = "3";
                    }
                    o.c(this.a.getEvnetListener().getFromActivity(), this.a.mTitle, str, null);
                }
            }
        });
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        if (this.mRankItems != null && this.mRankItems.size() > i) {
            ((ae) this.mRankItems.get(i)).a(getEvnetListener());
        }
    }

    public CardTitle getTitleView() {
        return (CardTitle) ap.a(getRootView(), R.id.title_btn);
    }

    public CardMoreView getMoreView() {
        return (CardMoreView) ap.a(getRootView(), R.id.more_btn);
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public void setShowDivider(boolean z) {
        this.mShowDivider = z;
    }
}
