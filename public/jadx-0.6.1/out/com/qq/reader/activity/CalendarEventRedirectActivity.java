package com.qq.reader.activity;

import android.os.Bundle;
import android.text.TextUtils;
import com.qq.reader.qurl.c;

public class CalendarEventRedirectActivity extends ReaderBaseActivity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Object stringExtra = getIntent().getStringExtra("customAppUri");
        if (!TextUtils.isEmpty(stringExtra)) {
            try {
                c.a(this, stringExtra, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        finish();
    }
}
