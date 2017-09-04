package com.qq.reader.module.bookstore.qnative.fragment;

import android.app.Activity;
import android.os.Bundle;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.page.b;
import com.qq.reader.module.bookstore.qweb.fragment.BaseFragment;

public class NativePageFragment extends BaseFragment implements a {
    public b mHoldPage = null;

    public void notifyData() {
    }

    public void onPreLoad() {
    }

    public void onLoading() {
    }

    public void onLoadFinished() {
    }

    public void refresh() {
    }

    public void doFunction(Bundle bundle) {
    }

    public Activity getFromActivity() {
        return getActivity();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mHoldPage != null) {
            this.mHoldPage.w();
        }
    }
}
