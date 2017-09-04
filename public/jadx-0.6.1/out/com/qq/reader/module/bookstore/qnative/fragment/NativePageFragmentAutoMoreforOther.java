package com.qq.reader.module.bookstore.qnative.fragment;

import android.os.Message;
import android.view.View;
import android.widget.HeaderViewListAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.qq.reader.module.bookstore.qnative.a.g;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qnative.page.c;
import com.qq.reader.view.IndexerSideBar;
import com.qq.reader.view.IndexerSideBar.a;
import com.qq.reader.view.pullupdownlist.XListView;
import com.tencent.feedback.proguard.R;

public class NativePageFragmentAutoMoreforOther extends NativePageFragmentforOther {
    private IndexerSideBar mIndexerSideBar;

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 500000:
            case 500001:
                this.mPullDownView.setRefreshing(false);
                try {
                    if (message.obj != null) {
                        Object obj = message.obj;
                        if (obj instanceof c) {
                            b bVar = (c) obj;
                            if (bVar.g().indexOf("nextpage") == -1) {
                                this.mHoldPage.a(bVar);
                            } else if (this.mNextPage == null || this.mCurPageStatus != 1) {
                                return true;
                            } else {
                                this.mNextPage.a(bVar);
                                this.mIndexerSideBar.setVisibility(0);
                            }
                        } else if (obj instanceof b) {
                            this.mHoldPage.a((b) obj);
                        }
                        notifyData();
                        this.mHandler.sendEmptyMessage(500005);
                    } else {
                        com.qq.reader.common.monitor.debug.c.a("LOGGER_NATIVE", "msg.obj == null");
                    }
                } catch (Exception e) {
                    com.qq.reader.common.monitor.debug.c.a("LOGGER_NATIVE", e.toString());
                }
                return true;
            case 500010:
                hideLoadingPage();
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    public int getLayoutResourceId() {
        return R.layout.authornamelist_layout;
    }

    protected void init(View view) {
        super.init(view);
        this.mIndexerSideBar = (IndexerSideBar) view.findViewById(R.id.haffoffame_author_list_sidebar);
        this.mIndexerSideBar.setTextView((TextView) view.findViewById(R.id.haffoffame_author_list_dialog));
        this.mIndexerSideBar.setOnTouchingLetterChangedListener(new a(this) {
            final /* synthetic */ NativePageFragmentAutoMoreforOther a;

            {
                this.a = r1;
            }

            public void a(String str) {
                XListView xListView = this.a.getXListView();
                if (xListView != null) {
                    HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) xListView.getAdapter();
                    if (headerViewListAdapter != null) {
                        ListAdapter wrappedAdapter = headerViewListAdapter.getWrappedAdapter();
                        if (wrappedAdapter instanceof g) {
                            int positionForSection = ((g) wrappedAdapter).getPositionForSection(str.toLowerCase().charAt(0));
                            if (positionForSection != -1) {
                                xListView.setSelection(positionForSection);
                            }
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

    public void loadNextPage() {
        super.loadNextPage();
        if (!this.mHoldPage.s()) {
            this.mHandler.sendEmptyMessage(500010);
        }
    }
}
