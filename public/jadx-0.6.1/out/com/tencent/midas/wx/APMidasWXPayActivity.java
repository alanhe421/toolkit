package com.tencent.midas.wx;

import android.app.Activity;
import android.os.Bundle;

public class APMidasWXPayActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            getIntent().getStringExtra("wxpay");
            try {
                requestWindowFeature(1);
                APMidasWXPayHelper.getInstance(this).handleIntent(getIntent());
                finish();
            } catch (Throwable th) {
                th.printStackTrace();
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
            finish();
        }
    }
}
