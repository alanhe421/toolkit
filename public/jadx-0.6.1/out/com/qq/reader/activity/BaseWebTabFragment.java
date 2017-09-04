package com.qq.reader.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.web.a;
import com.qq.reader.common.web.js.JsAdEvent;
import com.qq.reader.module.bookstore.qweb.fragment.WebBrowserFragment;
import com.qq.reader.view.linearmenu.b;
import com.tencent.feedback.proguard.R;

public abstract class BaseWebTabFragment extends AbsBaseTabFragment implements a, JsAdEvent.a {
    public static final int MENU_ID_EXIT = 3;
    public static final int MENU_ID_REFRESH = 0;
    private b mReaderMenu;

    public void setTouched(boolean z) {
    }

    public void doPageAction(String str) {
    }

    public void IOnResume() {
        super.IOnResume();
        if (isNeedStatistics()) {
            StatisticsManager.a().b();
            String statisticsPageName = getStatisticsPageName();
            if (!TextUtils.isEmpty(statisticsPageName)) {
                StatisticsManager.a().a(statisticsPageName);
            }
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
    }

    protected int getLayoutResourceId() {
        return R.layout.common_web_tab_layout;
    }

    public com.qq.reader.view.linearmenu.a getMenu() {
        if (getActivity() == null) {
            return null;
        }
        this.mReaderMenu = new b(getActivity());
        this.mReaderMenu.a(0, "刷新", null);
        this.mReaderMenu.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ BaseWebTabFragment a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                this.a.mReaderMenu.cancel();
                return this.a.menuSelected(i, bundle);
            }
        });
        this.mReaderMenu.a(new OnCancelListener(this) {
            final /* synthetic */ BaseWebTabFragment a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                if (this.a.getActivity() != null) {
                    this.a.getActivity().getWindow().closeAllPanels();
                }
            }
        });
        return this.mReaderMenu;
    }

    protected boolean menuSelected(int i, Bundle bundle) {
        Fragment curFragment = getCurFragment();
        switch (i) {
            case 0:
                if (curFragment instanceof WebBrowserFragment) {
                    ((WebBrowserFragment) curFragment).refresh();
                }
                j.a(1, 2);
                return true;
            default:
                return false;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (this.mAdapter.e(this.mViewPager.getCurrentItem()) != null) {
            this.mAdapter.e(this.mViewPager.getCurrentItem()).onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 82) {
            getMenu().f_();
        } else if (i == 4) {
            upToPreUI();
        }
        return true;
    }

    public boolean isNeedStatistics() {
        return false;
    }

    public String getStatisticsPageName() {
        return null;
    }
}
