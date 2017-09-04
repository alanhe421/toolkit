package com.qq.reader.appconfig;

import android.content.Context;
import android.content.SharedPreferences;
import com.qq.reader.common.readertask.ordinal.ReaderShortTask;

class Config$UserConfig$1 extends ReaderShortTask {
    final /* synthetic */ Context val$c;

    Config$UserConfig$1(Context context) {
        this.val$c = context;
    }

    public void run() {
        super.run();
        SharedPreferences sharedPreferences = this.val$c.getSharedPreferences("SETTING", 2);
        a.b(sharedPreferences.edit().putInt("APP_STARTOVER_TIME", sharedPreferences.getInt("APP_STARTOVER_TIME", 1) + 1));
    }
}
