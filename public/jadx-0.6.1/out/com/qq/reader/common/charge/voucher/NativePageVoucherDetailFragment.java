package com.qq.reader.common.charge.voucher;

import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.CustomArrayList;
import com.qq.reader.module.bookstore.qnative.card.a;
import com.qq.reader.module.bookstore.qnative.fragment.NativePageFragmentforOther;
import com.qq.reader.module.bookstore.qnative.page.impl.o;
import com.qq.reader.module.question.card.ConfigurableEmptyCard;
import com.tencent.feedback.proguard.R;
import java.util.List;

public class NativePageVoucherDetailFragment extends NativePageFragmentforOther {
    private TextView commentV;
    private View headerView;
    private TextView voucherCountV;

    protected void init(View view) {
        super.init(view);
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
                initConfigBookCardUI(this.root, this.mHoldPage.m());
                this.emptyView.setVisibility(8);
                configHeader();
            }
        }
    }

    public void onResume() {
        super.onResume();
        i.a("event_F161", null, ReaderApplication.getApplicationImp());
    }

    public void initCardListView(View view, boolean z) {
        super.initCardListView(view, z);
        if (this.headerView == null) {
            this.headerView = View.inflate(ReaderApplication.getApplicationContext(), R.layout.voucher_detail_list_header, null);
            this.mXListView.addHeaderView(this.headerView);
            this.mXListView.setVisibility(0);
            this.voucherCountV = (TextView) this.headerView.findViewById(R.id.voucher_count);
            this.commentV = (TextView) this.headerView.findViewById(R.id.voucher_comment);
        }
        if (this.mHoldPage instanceof o) {
            List m = ((o) this.mHoldPage).m();
            if (m.size() == 1 && (((a) m.get(0)) instanceof ConfigurableEmptyCard)) {
                this.mXListView.g();
                this.mXListView.setPullLoadEnable(false);
            }
        }
    }

    private void configHeader() {
        try {
            if (this.mHoldPage instanceof o) {
                Object x = ((o) this.mHoldPage).x();
                final String y = ((o) this.mHoldPage).y();
                int z = ((o) this.mHoldPage).z();
                this.voucherCountV.setText(String.format(getResources().getString(R.string.buy_book_balance), new Object[]{z + ""}));
                CharSequence spannableStringBuilder = new SpannableStringBuilder();
                spannableStringBuilder.append(x).append(" 抵扣券规则>");
                spannableStringBuilder.setSpan(new ClickableSpan(this) {
                    final /* synthetic */ NativePageVoucherDetailFragment b;

                    public void onClick(View view) {
                        if (!TextUtils.isEmpty(y)) {
                            try {
                                com.qq.reader.qurl.c.a(this.b.getFromActivity(), y);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }

                    public void updateDrawState(TextPaint textPaint) {
                        super.updateDrawState(textPaint);
                        textPaint.linkColor = 0;
                        textPaint.bgColor = 0;
                        textPaint.setColor(this.b.getResources().getColor(R.color.text_color_c301));
                        textPaint.setUnderlineText(false);
                    }
                }, x.length(), spannableStringBuilder.length(), 33);
                this.commentV.setMovementMethod(LinkMovementMethod.getInstance());
                this.commentV.setText(spannableStringBuilder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected Boolean configCanPullLoadMore() {
        return Boolean.valueOf(true);
    }
}
