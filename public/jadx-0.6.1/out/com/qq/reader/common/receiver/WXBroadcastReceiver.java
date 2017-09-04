package com.qq.reader.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.qq.reader.view.af;

public class WXBroadcastReceiver extends BroadcastReceiver {
    private static WXBroadcastReceiver b;
    Context a;
    private af c;

    private WXBroadcastReceiver(Context context) {
        this.a = context;
        this.c = af.a(context.getApplicationContext(), (CharSequence) "", 1);
    }

    public static synchronized WXBroadcastReceiver a(Context context) {
        WXBroadcastReceiver wXBroadcastReceiver;
        synchronized (WXBroadcastReceiver.class) {
            if (b == null) {
                b = new WXBroadcastReceiver(context);
            }
            wXBroadcastReceiver = b;
        }
        return wXBroadcastReceiver;
    }

    public void a() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.qq.readerBROADCAST_ACTION_WX_REGISTER_NOT_INSTALLED");
        intentFilter.addAction("com.qq.readerBROADCAST_ACTION_WX_REGISTER_SDK_NOT_MATCH");
        intentFilter.addAction("com.qq.readerBROADCAST_ACTION_WX_REGISTER_FAILED");
        this.a.registerReceiver(b, intentFilter);
    }

    public void b() {
        try {
            this.a.unregisterReceiver(b);
        } catch (Exception e) {
        }
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == null) {
            return;
        }
        if ("com.qq.readerBROADCAST_ACTION_WX_REGISTER_NOT_INSTALLED".equals(action)) {
            this.c.a((CharSequence) "您尚未安装微信");
            this.c.a();
        } else if ("com.qq.readerBROADCAST_ACTION_WX_REGISTER_SDK_NOT_MATCH".equals(action)) {
            this.c.a((CharSequence) "当前微信版本和SDK不匹配，请更新");
            this.c.a();
        } else if ("com.qq.readerBROADCAST_ACTION_WX_REGISTER_FAILED".equals(action)) {
            this.c.a((CharSequence) "注册微信插件失败");
            this.c.a();
        }
    }
}
