package com.sina.weibo.sdk.share;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.sina.weibo.sdk.b;
import com.sina.weibo.sdk.b.e;
import com.sina.weibo.sdk.b.k;
import com.tencent.tesla.soload.SoLoadCore;

public class WbShareTransActivity extends Activity {
    boolean a = false;
    private String b;
    private Handler c = new Handler(this) {
        final /* synthetic */ WbShareTransActivity a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putInt("_weibo_resp_errcode", 1);
            intent.putExtras(bundle);
            intent.setFlags(SoLoadCore.IF_SO_CONFIG_EXIST);
            intent.setClassName(this.a, this.a.b);
            this.a.startActivity(intent);
            this.a.finish();
        }
    };

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (bundle != null) {
            this.b = bundle.getString("startActivity");
            this.a = bundle.getBoolean("resultDataFlag", false);
            return;
        }
        this.a = true;
        this.b = intent.getStringExtra("startActivity");
        intent.putExtra("startFlag", -1);
        Intent intent2 = new Intent("com.sina.weibo.sdk.action.ACTION_WEIBO_ACTIVITY");
        intent2.putExtras(intent.getExtras());
        intent2.setPackage(intent.getStringExtra("startPackage"));
        intent2.setAction(intent.getStringExtra("startAction"));
        String packageName = getPackageName();
        intent2.putExtra("_weibo_sdkVersion", "0031405000");
        intent2.putExtra("_weibo_appPackage", packageName);
        intent2.putExtra("_weibo_appKey", b.b().a());
        intent2.putExtra("_weibo_flag", 538116905);
        intent2.putExtra("_weibo_sign", e.a(k.a(this, packageName)));
        if (TextUtils.isEmpty(intent.getStringExtra("gotoActivity"))) {
            startActivityForResult(intent2, 765);
            return;
        }
        intent2.setClassName(this, intent.getStringExtra("gotoActivity"));
        startActivity(intent2);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.c.sendEmptyMessageDelayed(0, 100);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (intent.getIntExtra("startFlag", -1) != 0) {
            this.c.removeMessages(0);
            Bundle extras = intent.getExtras();
            Intent intent2 = new Intent();
            intent2.putExtras(extras);
            intent2.setFlags(SoLoadCore.IF_SO_CONFIG_EXIST);
            intent2.setClassName(this, this.b);
            startActivity(intent2);
            finish();
        }
    }

    protected void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.remove("startFlag");
        bundle.putBoolean("resultDataFlag", true);
        bundle.putString("startActivity", this.b);
    }
}
