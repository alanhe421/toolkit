package com.qq.reader.module.bookstore.qnative.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.RecyclerListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.module.bookstore.qnative.card.BaseListCard;
import com.qq.reader.module.bookstore.qnative.page.impl.m;
import com.qq.reader.view.pullupdownlist.XListView;
import com.qq.reader.view.pullupdownlist.XListViewFooter;
import com.tencent.feedback.proguard.R;
import java.util.List;

public class NativePageFragmentForSurroundingAll extends NativePageFragmentforOther {
    private View mLeftDivider;

    class a extends XListViewFooter {
        final /* synthetic */ NativePageFragmentForSurroundingAll a;

        public a(NativePageFragmentForSurroundingAll nativePageFragmentForSurroundingAll, Context context) {
            this.a = nativePageFragmentForSurroundingAll;
            super(context);
        }

        protected void a(Context context) {
            this.c = context;
            this.h = (RelativeLayout) LayoutInflater.from(this.c).inflate(R.layout.surroundall_xlistview_footer, null);
            addView(this.h);
            this.d = this.h.findViewById(R.id.xlistview_footer_content);
            this.e = this.h.findViewById(R.id.xlistview_footer_progressbar);
            this.f = (TextView) this.h.findViewById(R.id.classic_footer_hint_textview);
            this.g = findViewById(R.id.xlistview_footer_divider);
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    protected void init(View view) {
        super.init(view);
        if (view != null) {
            this.mLeftDivider = view.findViewById(R.id.surrounding_item_divider);
        }
    }

    public int getLayoutResourceId() {
        return R.layout.surroundall_fragment_layout;
    }

    public void notifyData() {
        if (!isDetached()) {
            if (this.mNextPage != null && this.mCurPageStatus == 1) {
                if (this.mNextPage.m().size() <= 0) {
                    this.mXListView.c();
                } else {
                    this.mHoldPage.addMore(this.mNextPage);
                    this.mXListView.e();
                    if (this.mAdapter != null) {
                        try {
                            int count = this.mAdapter.getCount() - 1;
                            int childCount = this.mXListView.getChildCount() - 2;
                            if (childCount < 0 || childCount >= this.mXListView.getChildCount()) {
                                childCount = 0;
                            } else {
                                View childAt = this.mXListView.getChildAt(childCount);
                                childCount = childAt == null ? 0 : childAt.getTop();
                            }
                            this.mAdapter.a(this.mHoldPage);
                            if (this.mAdapter.b() || this.mXListView.getAdapter() == null) {
                                this.mXListView.setAdapter(this.mAdapter);
                                if (count >= 0 && count < this.mAdapter.getCount()) {
                                    this.mXListView.setSelectionFromTop(count, childCount);
                                }
                            } else {
                                this.mAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            c.e(CustomArrayList.Class_NativePageFragmentforOther, e.getMessage());
                        }
                    }
                    if (!this.mHoldPage.s()) {
                        this.mXListView.c();
                    }
                }
                this.mNextPage = null;
                this.mCurPageStatus = 0;
            } else if (this.mHoldPage != null) {
                List m = this.mHoldPage.m();
                if (this.mHoldPage != null) {
                    configEmptyView();
                }
                if (m != null && m.size() > 0) {
                    BaseListCard listBookCard = getListBookCard(m);
                    if (listBookCard != null) {
                        listBookCard.setIsFromCharis(this.isFromCharts);
                        initListBookCardUI(this.root, listBookCard);
                        if (this.mHoldPage instanceof com.qq.reader.module.bookstore.qnative.page.c) {
                            checkShouldShowUpdateTime((com.qq.reader.module.bookstore.qnative.page.c) this.mHoldPage);
                        }
                    } else {
                        initConfigBookCardUI(this.root, m);
                    }
                    if (this.emptyView != null) {
                        this.emptyView.setVisibility(8);
                    }
                    if (this.mLeftDivider != null) {
                        this.mLeftDivider.setVisibility(0);
                    }
                    if (this.mXListView != null) {
                        this.mXListView.setVisibility(0);
                    }
                    onDataInitialized();
                } else if (!this.hideEmpty) {
                    configCanPullDownRefresh(true);
                    if (this.emptyView != null) {
                        this.emptyView.setVisibility(0);
                    }
                    if (this.mLeftDivider != null) {
                        this.mLeftDivider.setVisibility(8);
                    }
                    if (this.mXListView != null) {
                        this.mXListView.setVisibility(8);
                    }
                } else if (this.emptyView != null) {
                    this.emptyView.setVisibility(8);
                }
            }
        }
    }

    public void initCardListView(View view, boolean z) {
        if (this.mXListView == null) {
            this.mXListView = (XListView) view.findViewById(R.id.list_layout);
            this.mXListView.setXListFooter(new a(this, getContext()));
            if (this.enterBundle != null) {
                int i = this.enterBundle.getInt("NATIVE_LISTVIEW_FOOTER_LOADING_PROGRESSBAR_INDETEMINATE_DRAWABLE_RES_ID", 0);
                if (!(i == 0 || this.mXListView == null)) {
                    this.mXListView.setFooterProgressBarLoadingDrawable(i);
                }
            }
            this.mXListView.setCrashTag(CustomArrayList.Class_NativePageFragmentforOther);
            this.mXListView.setPullRefreshEnable(false);
            this.mXListView.setRecyclerListener(new RecyclerListener(this) {
                final /* synthetic */ NativePageFragmentForSurroundingAll a;

                {
                    this.a = r1;
                }

                public void onMovedToScrapHeap(View view) {
                }
            });
        }
        if (this.mHoldPage != null) {
            this.mXListView.setEmptyView(this.emptyView);
            this.mXListView.setVisibility(0);
            this.mXListView.setPullLoadEnable(true);
            if (!this.mHoldPage.s() || (this.mHoldPage instanceof m)) {
                this.mXListView.c();
                return;
            }
            this.mXListView.setXListViewListener(new com.qq.reader.view.pullupdownlist.XListView.a(this) {
                final /* synthetic */ NativePageFragmentForSurroundingAll a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.mHandler.sendEmptyMessage(500005);
                }
            });
            this.mXListView.setOnScrollListener(new com.qq.reader.common.imageloader.core.a.a(com.qq.reader.common.imageloader.c.a((Fragment) this).a(), true, true));
            this.mXListView.e();
        }
    }
}
