package com.qq.reader.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.qq.reader.ReaderApplication;
import com.qq.reader.b;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.o;
import com.tencent.feedback.proguard.R;

public class HammerEntryActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        if ("android.intent.action.SEND".equals(action) && type != null) {
            if ("text/plain".equals(type) || "text/*".equals(type) || "*/*".equals(type)) {
                i.a("event_A252", null, getApplicationContext());
                a(intent);
            } else if (type.contains("application")) {
                i.a("event_A253", null, getApplicationContext());
                b(intent);
            } else {
                finish();
            }
        }
    }

    private void a(Intent intent) {
        String stringExtra = intent.getStringExtra("android.intent.extra.TEXT");
        intent.getStringExtra("android.intent.extra.TITLE");
        o.b(this, stringExtra, "5", ReaderApplication.getApplicationImp().getResources().getString(R.string.please_input_author));
        finish();
    }

    private void b(Intent intent) {
        b.a(intent, this);
        finish();
    }
}
