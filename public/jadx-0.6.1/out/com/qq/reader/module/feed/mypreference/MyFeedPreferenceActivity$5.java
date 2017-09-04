package com.qq.reader.module.feed.mypreference;

import android.content.Intent;
import com.qq.reader.common.c.a;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import org.json.JSONObject;

class MyFeedPreferenceActivity$5 implements c {
    final /* synthetic */ MyFeedPreferenceActivity a;

    MyFeedPreferenceActivity$5(MyFeedPreferenceActivity myFeedPreferenceActivity) {
        this.a = myFeedPreferenceActivity;
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        try {
            if (new JSONObject(str).optInt("code", -1) == 0) {
                this.a.sendBroadcast(new Intent(a.cB));
            }
            if (MyFeedPreferenceActivity.m(this.a)) {
                c.b().a(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            MyFeedPreferenceActivity.a = true;
            this.a.sendBroadcast(new Intent(a.cC));
        }
        MyFeedPreferenceActivity.a = true;
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
        this.a.sendBroadcast(new Intent(a.cC));
        exception.printStackTrace();
        MyFeedPreferenceActivity.a = true;
    }
}
