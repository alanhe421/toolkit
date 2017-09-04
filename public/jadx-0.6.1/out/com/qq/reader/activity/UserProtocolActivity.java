package com.qq.reader.activity;

import android.os.Bundle;
import com.qq.reader.cservice.protocol.UserProtocolRedPointManger;

public class UserProtocolActivity extends WebBrowserForContents {
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            UserProtocolRedPointManger.a(getApplicationContext()).b();
        } catch (Exception e) {
        }
    }
}
