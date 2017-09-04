package com.qq.reader.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.web.a;
import com.qq.reader.common.web.js.JsAdEvent;
import com.qq.reader.module.bookstore.qweb.fragment.WebBrowserFragment;
import com.qq.reader.view.linearmenu.b;
import com.tencent.feedback.proguard.R;

public abstract class BaseWebTabActivity extends AbsBaseTabActivity implements a, JsAdEvent.a {
    private b k;

    public void setTouched(boolean z) {
    }

    public void doPageAction(String str) {
    }

    protected void onResume() {
        super.onResume();
        if (h()) {
            StatisticsManager.a().b();
            String i = i();
            if (!TextUtils.isEmpty(i)) {
                StatisticsManager.a().a(i);
            }
        }
    }

    protected int f() {
        return R.layout.common_web_tab_layout;
    }

    public com.qq.reader.view.linearmenu.a g() {
        this.k = new b(this);
        this.k.a(0, "刷新", null);
        this.k.a(new com.qq.reader.view.linearmenu.a.b(this) {
            final /* synthetic */ BaseWebTabActivity a;

            {
                this.a = r1;
            }

            public boolean a(int i, Bundle bundle) {
                this.a.k.cancel();
                return this.a.a(i, bundle);
            }
        });
        this.k.a(new OnCancelListener(this) {
            final /* synthetic */ BaseWebTabActivity a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.getWindow().closeAllPanels();
            }
        });
        return this.k;
    }

    protected boolean a(int i, Bundle bundle) {
        Fragment d = d();
        switch (i) {
            case 0:
                if (d instanceof WebBrowserFragment) {
                    ((WebBrowserFragment) d).refresh();
                }
                j.a(1, 2);
                return true;
            default:
                return false;
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (this.c.e(this.b.getCurrentItem()) != null) {
            this.c.e(this.b.getCurrentItem()).onActivityResult(i, i2, intent);
        }
        super.onActivityResult(i, i2, intent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 82) {
            g().f_();
        } else if (i == 4) {
            a();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean h() {
        return false;
    }

    public String i() {
        return null;
    }
}
