package com.tencent.midas.proxyactivity;

import android.content.Intent;
import android.os.Bundle;
import com.tencent.midas.comm.APLog;
import com.tencent.midas.plugin.APPluginProxyActivity;

public class APMidasPayProxyActivity extends APPluginProxyActivity {
    protected void onActivityResult(int i, int i2, Intent intent) {
        APLog.i("APMidasPayProxyActivity", "onActivityResult requestCode:" + i + " resultCode:" + i2);
        super.onActivityResult(i, i2, intent);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }
}
