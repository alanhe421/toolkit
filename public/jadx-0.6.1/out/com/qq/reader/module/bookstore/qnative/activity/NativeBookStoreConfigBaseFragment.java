package com.qq.reader.module.bookstore.qnative.activity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import com.qq.reader.activity.ReaderBaseFragment;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.widget.SwipeRefreshLayout;
import com.qq.reader.module.bookstore.qnative.a.f;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.d;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.tencent.feedback.proguard.R;
import com.tencent.upload.log.trace.TracerConfig;

public abstract class NativeBookStoreConfigBaseFragment extends ReaderBaseFragment implements a {
    boolean isupding = false;
    protected f mAdapter;
    protected ListView mCardListView;
    protected View mFailedLayout = null;
    protected b mHoldPage = null;
    protected boolean mIsNetSucess = false;
    protected long mLastUpdateTime = -1;
    protected View mLoadingProgress = null;
    protected SwipeRefreshLayout mPullDownView;

    public void notifyData() {
        if (this.mAdapter != null) {
            this.mAdapter.a(this.mHoldPage);
            if (this.mAdapter.b() || this.mCardListView.getAdapter() == null) {
                this.mCardListView.setAdapter(this.mAdapter);
            } else {
                this.mAdapter.notifyDataSetChanged();
            }
            notifyView();
        }
    }

    protected void clearData() {
        if (this.mAdapter != null) {
            this.mAdapter.a();
            this.mAdapter.notifyDataSetChanged();
        }
    }

    protected void showLoadingPage() {
        hideFailedPage();
        this.mCardListView.setVisibility(4);
        this.mLoadingProgress.setVisibility(0);
    }

    protected void hideLoadingPage() {
        hideFailedPage();
        this.mCardListView.setVisibility(0);
        this.mLoadingProgress.setVisibility(8);
    }

    protected void showFailedPage() {
        if (this.mCardListView.getVisibility() == 0 || this.mCardListView.getAdapter().getCount() > 0) {
            getApplicationContext().getResources().getString(R.string.pulldownview_failed);
            if (this.mPullDownView != null) {
                this.mPullDownView.setRefreshing(false);
                return;
            }
            return;
        }
        this.mLoadingProgress.setVisibility(8);
        this.mFailedLayout.setVisibility(0);
    }

    protected void hideFailedPage() {
        this.mFailedLayout.setVisibility(8);
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 500000:
            case 500001:
                try {
                    if (message.obj != null) {
                        this.mHoldPage.a((b) message.obj);
                    }
                    hideLoadingPage();
                    if (this.mPullDownView != null) {
                        this.mIsNetSucess = true;
                        this.mPullDownView.setRefreshing(false);
                    }
                    onUpdateEnd();
                    notifyData();
                } catch (Exception e) {
                    com.qq.reader.common.monitor.f.a("NativeBookStoreConfigBaseActivity", e.getMessage());
                }
                return true;
            case 500003:
                onUpdateEnd();
                notifyData();
                return true;
            case 500004:
                this.mIsNetSucess = false;
                onUpdateEnd();
                showFailedPage();
                return true;
            case 10000508:
                if (this.mAdapter != null) {
                    this.mAdapter.notifyDataSetChanged();
                }
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    protected void tryObtainDataWithNet(boolean z, boolean z2) {
        boolean a = d.b().a(getContext(), this.mHoldPage, this.mHandler, z);
        if (!z2) {
            if (a) {
                if (!this.isupding) {
                    notifyData();
                    this.isupding = false;
                }
                hideLoadingPage();
                return;
            }
            this.mLastUpdateTime = System.currentTimeMillis();
            clearData();
            showLoadingPage();
        }
    }

    public void notifyView() {
    }

    public void reLoadData() {
        this.mHoldPage.a(1000);
        tryObtainDataWithNet(true, false);
    }

    public void forceReLoadData() {
        for (com.qq.reader.module.bookstore.qnative.card.a expiredTime : this.mHoldPage.m()) {
            expiredTime.setExpiredTime(System.currentTimeMillis() - TracerConfig.LOG_FLUSH_DURATION);
        }
        this.mHoldPage.a(1000);
        tryObtainDataWithNet(true, false);
    }

    public void refreshPage() {
        this.mHoldPage.a(1001);
        for (com.qq.reader.module.bookstore.qnative.card.a invalidData : this.mHoldPage.m()) {
            invalidData.setInvalidData();
        }
        tryObtainDataWithNet(true, true);
    }

    public void onUpdate() {
        this.isupding = true;
        this.mHoldPage.a(1001);
        tryObtainDataWithNet(false, true);
    }

    public void onUpdateEnd() {
        this.isupding = false;
        if (this.mPullDownView != null) {
            this.mPullDownView.setRefreshing(false);
        }
        j.a(53, 2);
    }

    public void doFunction(Bundle bundle) {
        if ("PARA_TYPE_REFRESH".equals(bundle.getString("function_type")) && this.mAdapter != null) {
            this.mHandler.sendEmptyMessageDelayed(10000508, 100);
        }
    }

    public void IOnResume() {
        super.IOnResume();
    }
}
