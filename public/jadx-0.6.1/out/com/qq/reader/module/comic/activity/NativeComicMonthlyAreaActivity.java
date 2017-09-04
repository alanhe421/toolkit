package com.qq.reader.module.comic.activity;

import android.content.Intent;
import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.charge.PayBridgeActivity;
import com.qq.reader.common.login.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.web.js.JSPay;
import com.qq.reader.module.bookstore.qnative.card.impl.PayMonthGuide;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;

public class NativeComicMonthlyAreaActivity extends NativeComicStoreBaseActivity {
    protected String f() {
        return getString(R.string.directzone_vip);
    }

    protected String g() {
        return "103521";
    }

    public void doFunction(Bundle bundle) {
        super.doFunction(bundle);
        if (bundle.getInt("function_type") == 3) {
            this.mLoginNextTask = new a(this) {
                final /* synthetic */ NativeComicMonthlyAreaActivity a;

                {
                    this.a = r1;
                }

                public void a(int i) {
                    this.a.q();
                }
            };
            startLogin();
            return;
        }
        if ("detail_2_openvip".equals(bundle.getString("KEY_ACTION"))) {
            i();
        }
    }

    private void i() {
        if (c.b()) {
            new JSPay(this).openVip();
            return;
        }
        this.mLoginNextTask = new a(this) {
            final /* synthetic */ NativeComicMonthlyAreaActivity a;

            {
                this.a = r1;
            }

            public void a(int i) {
                this.a.q();
            }
        };
        startLogin();
    }

    private void q() {
        for (com.qq.reader.module.bookstore.qnative.card.a aVar : this.j.m()) {
            if (aVar instanceof PayMonthGuide) {
                ((PayMonthGuide) aVar).refresh();
            }
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 20002) {
            switch (i2) {
                case 0:
                case 20003:
                    o();
                    return;
                case 2:
                    return;
                default:
                    af.a(ReaderApplication.getApplicationImp(), PayBridgeActivity.a(intent), 0).a();
                    return;
            }
        }
    }
}
