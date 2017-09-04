package com.qq.reader.module.feed.mypreference;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.bookstore.qnative.page.impl.am;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import org.json.JSONObject;

class MyFeedPreferenceActivity$4 implements c {
    final /* synthetic */ MyFeedPreferenceActivity a;

    MyFeedPreferenceActivity$4(MyFeedPreferenceActivity myFeedPreferenceActivity) {
        this.a = myFeedPreferenceActivity;
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("code", -1) == 0) {
                am amVar = new am(new Bundle());
                amVar.b(this.a);
                amVar.b(jSONObject);
                Message obtainMessage = MyFeedPreferenceActivity.g(this.a).obtainMessage();
                obtainMessage.what = 500001;
                obtainMessage.obj = amVar;
                MyFeedPreferenceActivity.h(this.a).sendMessage(obtainMessage);
                return;
            }
            final String optString = jSONObject.optString("rookieMsg");
            MyFeedPreferenceActivity.i(this.a).post(new Runnable(this) {
                final /* synthetic */ MyFeedPreferenceActivity$4 b;

                public void run() {
                    if (TextUtils.isEmpty(optString)) {
                        af.a(ReaderApplication.getApplicationImp(), R.string.not_available, 0).a();
                    } else {
                        af.a(ReaderApplication.getApplicationImp(), optString, 0).a();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            MyFeedPreferenceActivity.j(this.a).post(new Runnable(this) {
                final /* synthetic */ MyFeedPreferenceActivity$4 a;

                {
                    this.a = r1;
                }

                public void run() {
                    af.a(ReaderApplication.getApplicationImp(), R.string.not_available, 0).a();
                }
            });
        }
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
        exception.printStackTrace();
        if (MyFeedPreferenceActivity.k(this.a)) {
            MyFeedPreferenceActivity.l(this.a).post(new Runnable(this) {
                final /* synthetic */ MyFeedPreferenceActivity$4 a;

                {
                    this.a = r1;
                }

                public void run() {
                    af.a(ReaderApplication.getApplicationImp(), R.string.net_not_available, 0).a();
                }
            });
        }
    }
}
