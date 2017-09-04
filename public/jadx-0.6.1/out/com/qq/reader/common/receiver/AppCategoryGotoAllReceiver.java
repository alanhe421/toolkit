package com.qq.reader.common.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.qq.reader.activity.BookShelfFragment;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.c.a;

public class AppCategoryGotoAllReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        d.m(context.getApplicationContext(), BookShelfFragment.CATEGORY_ALL);
        context.sendBroadcast(new Intent(a.ci));
    }
}
