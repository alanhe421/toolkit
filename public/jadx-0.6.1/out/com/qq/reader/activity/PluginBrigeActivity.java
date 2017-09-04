package com.qq.reader.activity;

import android.content.Intent;
import android.os.Bundle;
import com.dynamicload.Lib.DLPluginManager;
import com.qq.reader.common.utils.w;

public class PluginBrigeActivity extends ReaderBaseActivity {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("pluginname");
        Intent intent = new Intent();
        intent.setComponent(w.a(stringExtra));
        intent.setAction("android.intent.action.VIEW");
        intent.putExtras(getIntent().getExtras());
        DLPluginManager.getInstance(this).startActivitySafty(this, intent);
        finish();
    }
}
