package com.qq.reader.module.question.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.imageloader.core.a.a;
import com.qq.reader.common.utils.o;
import com.qq.reader.module.bookstore.qnative.e;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.qq.reader.module.question.a.d;
import com.qq.reader.module.question.card.EmptyCardOfAuthorQA;
import com.qq.reader.view.pullupdownlist.XListView$a;
import com.tencent.feedback.proguard.R;
import java.util.List;

public class NativePageFragmentforQA extends NativePageFragmentforOther {
    public void doFunction(Bundle bundle) {
        super.doFunction(bundle);
        if (bundle != null) {
            int i = bundle.getInt("function_type");
            if (7 == i) {
                if (this.mHoldPage instanceof d) {
                    ((d) this.mHoldPage).b(1);
                    changeListTab();
                }
            } else if (8 == i && (this.mHoldPage instanceof d)) {
                ((d) this.mHoldPage).b(2);
                changeListTab();
            }
        }
    }

    private void changeListTab() {
        int top = this.mXListView.getChildAt(0).getTop();
        refresh();
        this.mXListView.setSelectionFromTop(0, top);
        if (this.mHoldPage.s()) {
            this.mXListView.setPullLoadEnable(true);
            this.mXListView.setXListViewListener(new XListView$a(this) {
                final /* synthetic */ NativePageFragmentforQA a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.mHandler.sendEmptyMessage(500005);
                }
            });
            this.mXListView.setOnScrollListener(new a(c.a(this).a(), true, true));
            this.mXListView.e();
            return;
        }
        List m = this.mHoldPage.m();
        if (((com.qq.reader.module.bookstore.qnative.card.a) m.get(m.size() - 1)) instanceof EmptyCardOfAuthorQA) {
            this.mXListView.g();
            this.mXListView.setPullLoadEnable(false);
            return;
        }
        this.mXListView.c();
    }

    public void loadNextPage() {
        if (this.mCurPageStatus != 0) {
            return;
        }
        if (this.mHoldPage.s()) {
            if (this.mNextBundle == null) {
                this.mNextBundle = new Bundle(this.enterBundle);
            }
            if (this.mHoldPage instanceof d) {
                this.mNextBundle.putInt("PAGE_STATUS_KEY", ((d) this.mHoldPage).x());
            }
            long r = this.mHoldPage.r();
            if (r != 0) {
                this.mNextBundle.putLong("KEY_PAGEINDEX", r);
                this.mNextBundle.putString("URL_BUILD_PERE_SIGNAL", "nextpage");
            }
            this.mNextPage = e.a().a(this.mNextBundle, this);
            this.mCurPageStatus = 1;
            this.mNextPage.a(1001);
            com.qq.reader.module.bookstore.qnative.d.b().a(getApplicationContext(), this.mNextPage, this.mHandler, true);
        } else if (this.mXListView != null) {
            this.mXListView.c();
        }
    }

    protected Boolean configCanPullLoadMore() {
        if (this.mHoldPage instanceof d) {
            return Boolean.valueOf(((d) this.mHoldPage).y());
        }
        if (this.mHoldPage instanceof com.qq.reader.module.question.a.e) {
            return Boolean.valueOf(true);
        }
        return null;
    }

    protected void configEmptyView() {
        if (!this.configEmpty) {
            if (this.mHoldPage instanceof d) {
                this.hideEmpty = true;
            } else if (this.mHoldPage instanceof com.qq.reader.module.question.a.e) {
                String x = ((com.qq.reader.module.question.a.e) this.mHoldPage).x();
                if ("0".equals(x)) {
                    this.emptyView.a(ReaderApplication.getApplicationImp().getResources().getString(R.string.empty_page_title_content_title)).b((int) R.drawable.empty12).a(true).a(3).c(ReaderApplication.getApplicationImp().getResources().getString(R.string.qauserpage_no_question_go)).a(new OnClickListener(this) {
                        final /* synthetic */ NativePageFragmentforQA a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            o.b(this.a.getActivity(), 0, 0, null);
                        }
                    });
                } else if ("1".equals(x)) {
                    this.emptyView.a(ReaderApplication.getApplicationImp().getResources().getString(R.string.empty_page_title_content_title)).b((int) R.drawable.empty11).a(true).a(3).c(ReaderApplication.getApplicationImp().getResources().getString(R.string.qauserpage_no_listen_go)).a(new OnClickListener(this) {
                        final /* synthetic */ NativePageFragmentforQA a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            o.b(this.a.getActivity(), 0, 0, null);
                        }
                    });
                } else if ("2".equals(x)) {
                    this.hideEmpty = true;
                }
            }
            this.configEmpty = true;
        }
    }

    protected void initConfigBookCardUI(View view, List<com.qq.reader.module.bookstore.qnative.card.a> list) {
        super.initConfigBookCardUI(view, list);
        if (this.mHoldPage instanceof d) {
            List m = ((d) this.mHoldPage).m();
            if (m.size() > 0 && (((com.qq.reader.module.bookstore.qnative.card.a) m.get(m.size() - 1)) instanceof EmptyCardOfAuthorQA)) {
                this.mXListView.g();
                this.mXListView.setPullLoadEnable(false);
            }
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1006 && this.mAdapter != null) {
            if (this.mAdapter.b() || this.mXListView.getAdapter() == null) {
                this.mXListView.setAdapter(this.mAdapter);
            } else {
                this.mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void onActivityFinish() {
        super.onActivityFinish();
        if (this.mHoldPage instanceof d) {
            int z = ((d) this.mHoldPage).z();
            Intent intent = new Intent();
            intent.putExtra("waitingQACount", z);
            getFromActivity().setResult(-1, intent);
        }
    }
}
