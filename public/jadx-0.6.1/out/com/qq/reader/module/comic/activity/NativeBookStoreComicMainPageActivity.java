package com.qq.reader.module.comic.activity;

import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.i;
import com.tencent.feedback.proguard.R;

public class NativeBookStoreComicMainPageActivity extends NativeComicStoreBaseActivity {
    protected String f() {
        return getString(R.string.comic_store_main_title);
    }

    protected String g() {
        return "103486";
    }

    protected void onResume() {
        super.onResume();
        i.a("event_F345", null, ReaderApplication.getApplicationImp());
    }
}
