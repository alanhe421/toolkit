package com.qq.reader.module.bookstore.qnative.fragment;

import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.card.BaseListCard;
import com.qq.reader.module.bookstore.qnative.page.impl.ar;
import com.qq.reader.module.bookstore.qnative.page.impl.t;
import com.qq.reader.module.redpacket.a.a;
import com.qq.reader.module.redpacket.a.b;
import com.tencent.feedback.proguard.R;
import java.util.List;

public class NativePageFragmentforEmptyRefreshable extends NativePageFragmentforOther {
    protected void configEmptyView() {
        if (this.mHoldPage instanceof ar) {
            this.emptyView.a(ReaderApplication.getApplicationImp().getResources().getString(R.string.no_subscribed_author_tip)).b(R.drawable.empty12).a(true).a(3).c(ReaderApplication.getApplicationImp().getResources().getString(R.string.no_subscribed_author_btn_tip)).a(new OnClickListener(this) {
                final /* synthetic */ NativePageFragmentforEmptyRefreshable a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    o.b(this.a.getActivity(), 1, 0, null);
                }
            });
            this.configEmpty = true;
        } else if (this.mHoldPage instanceof t) {
            this.emptyView.a(ReaderApplication.getApplicationImp().getResources().getString(R.string.author_qa_list_no_data_tip)).b(R.drawable.empty03).a(true).a(0);
            this.configEmpty = true;
        } else if (this.mHoldPage instanceof b) {
            this.emptyView.b(ReaderApplication.getApplicationImp().getResources().getString(R.string.empty_page_title_content_title)).a(((b) this.mHoldPage).x()).b(R.drawable.empty01).a(true).a(1);
            this.configEmpty = true;
        } else if (this.mHoldPage instanceof a) {
            this.emptyView.a(((a) this.mHoldPage).x()).b(R.drawable.empty08).a(true).a(0);
            this.configEmpty = true;
        }
    }

    public void refresh() {
        if (this.mCurPageStatus != 1) {
            try {
                if (this.mHoldPage != null) {
                    this.mHoldPage.q();
                    List m = this.mHoldPage.m();
                    if (m != null && m.size() >= 0) {
                        BaseListCard listBookCard = getListBookCard(m);
                        if (listBookCard != null) {
                            listBookCard.notifyDataSetChanged();
                        } else if (this.mAdapter != null) {
                            this.mAdapter.a(this.mHoldPage);
                            if (this.mAdapter.b() || this.mXListView.getAdapter() == null) {
                                this.mXListView.setAdapter(this.mAdapter);
                            } else {
                                this.mAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                }
            } catch (Exception e) {
                c.a("LOGGER_NATIVE", e.toString());
            }
        }
    }
}
