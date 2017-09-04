package com.qq.reader.module.bookstore.qnative.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.a.e;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.impl.aw;
import com.qq.reader.view.IndexerSideBar.a;
import com.qq.reader.view.YearsIndexerSideBar;
import com.qq.reader.view.pullupdownlist.XListView;
import com.qq.reader.widget.PinnedHeaderListView;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.Map;

public class NativePageFramentforTenYearsRank extends NativePageFragmentforOther {
    private YearsIndexerSideBar mIndexerSideBar;

    protected void init(View view) {
        super.init(view);
        this.mIndexerSideBar = (YearsIndexerSideBar) view.findViewById(R.id.haffoffame_author_list_sidebar);
        this.mXListView = (XListView) view.findViewById(R.id.list_layout);
        this.mIndexerSideBar.setOnTouchingLetterChangedListener(new a(this) {
            final /* synthetic */ NativePageFramentforTenYearsRank a;

            {
                this.a = r1;
            }

            public void a(String str) {
                XListView xListView = this.a.getXListView();
                if (xListView != null) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) xListView.getAdapter();
                    if (headerViewListAdapter != null) {
                        ListAdapter wrappedAdapter = headerViewListAdapter.getWrappedAdapter();
                        if (wrappedAdapter instanceof e) {
                            int b = ((e) wrappedAdapter).b(Integer.parseInt(str));
                            if (b != -1) {
                                xListView.setSelection(b);
                            }
                            Map hashMap = new HashMap();
                            hashMap.put("year", str);
                            i.a("event_B224", hashMap, this.a.getFromActivity());
                        }
                    }
                }
            }
        });
    }

    protected void showLoadingPage() {
        super.showLoadingPage();
        this.mIndexerSideBar.setVisibility(8);
    }

    public int getLayoutResourceId() {
        return R.layout.rankborad_detail_list_layout;
    }

    public void loadPageWithFilter(Bundle bundle) {
        this.mHoldPage = com.qq.reader.module.bookstore.qnative.e.a().a(bundle, this);
        super.reLoadData();
    }

    public void onLoadPageDataFirstSectionSucess(b bVar) {
        if (((aw) bVar).x()) {
            this.mIndexerSideBar.setYearsletters(((aw) bVar).y());
            this.mIndexerSideBar.setVisibility(0);
            ((PinnedHeaderListView) this.mXListView).setPinnedHeaderView(LayoutInflater.from(getApplicationContext()).inflate(R.layout.rankboard_header, this.mXListView, false));
        }
    }
}
