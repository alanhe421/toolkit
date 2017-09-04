package com.tencent.midas.qq;

import android.app.Activity;
import android.os.Bundle;
import com.tencent.midas.comm.APLog;

public class APMidasQQWalletActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            getIntent().getStringExtra("wxpay");
            try {
                requestWindowFeature(1);
                APMidasQQWalletHelper payHelper = APMidasQQWalletHelper.getPayHelper(this);
                APLog.i("APMidasQQPayActivity", "helper:" + payHelper);
                if (payHelper != null) {
                    APMidasQQWalletHelper.getPayHelper(this).handleIntent(getIntent());
                }
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
