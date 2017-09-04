package com.qq.reader.module.findhome;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ap;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.findhome.b.c;
import com.qq.reader.module.findhome.base.FindHomeBaseCard;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

public class FindHomeItemCard extends FindHomeBaseCard {
    private final int EXPAND_MIN_SIZE = 3;
    protected d findHomePageItem;
    private LayoutManager linerarHorizontal = new c(ReaderApplication.getApplicationImp(), 0, false);
    private int mDividerType;

    public FindHomeItemCard(b bVar, String str) {
        super(bVar, str);
    }

    public int getResLayoutId() {
        return R.layout.findhome_item;
    }

    protected boolean parseData(JSONObject jSONObject) throws Exception {
        this.findHomePageItem = new d();
        this.findHomePageItem.parseData(jSONObject);
        JSONObject optJSONObject = jSONObject.optJSONObject("expand");
        if (optJSONObject != null) {
            a aVar = new a();
            if (aVar.a(optJSONObject)) {
                this.findHomePageItem.a(aVar);
            }
        }
        return true;
    }

    public void refreshData() {
        a g = this.findHomePageItem.g();
        if (g != null && g.b() >= 3) {
            g.a();
        }
    }

    public void attachView() {
        long currentTimeMillis = System.currentTimeMillis();
        ImageView imageView = (ImageView) ap.a(getRootView(), R.id.find_homepage_item_icon);
        ((TextView) ap.a(getRootView(), R.id.find_homepage_item_title)).setText(this.findHomePageItem.b());
        com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(this.findHomePageItem.a(), imageView, a.a().j());
        handleExtInfo();
        ap.a(getRootView(), R.id.find_homepage_item_layout).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FindHomeItemCard a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.onItemCardClick(view);
            }
        });
        handleExpandViews();
        View a = ap.a(getRootView(), R.id.find_homepage_item_end_line);
        switch (this.mDividerType) {
            case 0:
                a.setVisibility(8);
                break;
            case 1:
                a.setVisibility(0);
                break;
            default:
                a.setVisibility(0);
                break;
        }
        handleExposedStatistics(this.findHomePageItem.h());
        System.out.println("ccc findhome item attach cost : " + (System.currentTimeMillis() - currentTimeMillis));
    }

    public void refresh() {
        handleExpandViews();
        handleExtInfo();
    }

    protected void handleExpandViews() {
        RecyclerView recyclerView = (RecyclerView) ap.a(getRootView(), R.id.find_home_expand_recyclerview);
        com.qq.reader.module.findhome.b.b bVar = (com.qq.reader.module.findhome.b.b) recyclerView.getAdapter();
        a g = this.findHomePageItem.g();
        if (g == null || g.b() < 3) {
            recyclerView.setVisibility(8);
            return;
        }
        recyclerView.setVisibility(0);
        recyclerView.setLayoutManager(this.linerarHorizontal);
        if (bVar == null) {
            bVar = new com.qq.reader.module.findhome.b.b(getEvnetListener().getFromActivity());
            recyclerView.a(new com.qq.reader.module.findhome.b.a(recyclerView.getContext(), 1, ao.a(12.0f), 0));
            recyclerView.setAdapter(bVar);
        }
        bVar.a(g.c());
        bVar.c();
    }

    protected void onItemCardClick(View view) {
        try {
            com.qq.reader.qurl.c.a(getEvnetListener().getFromActivity(), this.findHomePageItem.c(), null);
        } catch (Exception e) {
            com.qq.reader.common.monitor.debug.c.e("FindhomeItemCard", e.getMessage());
        }
        com.qq.reader.cservice.adv.c.b(this.findHomePageItem.f());
        handleClickStatistics(this.findHomePageItem.h());
    }

    protected void handleExtInfo() {
        TextView textView = (TextView) ap.a(getRootView(), R.id.find_homepage_item_extinfo_txt);
        View a = ap.a(getRootView(), R.id.find_homepage_item_extinfo_icon_layout);
        View a2 = ap.a(getRootView(), R.id.find_homepage_item_redtip);
        if (com.qq.reader.cservice.adv.c.a(this.findHomePageItem.f())) {
            if (TextUtils.isEmpty(this.findHomePageItem.d())) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.setText(this.findHomePageItem.d());
            }
            if (TextUtils.isEmpty(this.findHomePageItem.e())) {
                a.setVisibility(8);
                a2.setVisibility(0);
                return;
            }
            a.setVisibility(0);
            a2.setVisibility(8);
            com.qq.reader.common.imageloader.c.a(getEvnetListener().getFromActivity()).a(this.findHomePageItem.e(), (ImageView) ap.a(getRootView(), R.id.find_homepage_item_extinfo_icon), a.a().j());
            return;
        }
        textView.setVisibility(8);
        a.setVisibility(8);
        a2.setVisibility(8);
    }

    public String getExtRedDotId() {
        return this.findHomePageItem != null ? this.findHomePageItem.f() : null;
    }

    protected boolean isInflateViewWithParent() {
        return true;
    }

    public void setDividerType(int i) {
        this.mDividerType = i;
    }
}
