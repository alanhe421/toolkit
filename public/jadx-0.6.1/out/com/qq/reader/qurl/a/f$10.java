package com.qq.reader.qurl.a;

import android.app.Activity;
import android.os.Message;
import android.text.TextUtils;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.view.af;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* compiled from: URLServerOfClient */
class f$10 implements c {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ f c;

    f$10(f fVar, String str, String str2) {
        this.c = fVar;
        this.a = str;
        this.b = str2;
    }

    public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
        if (!TextUtils.isEmpty(str)) {
            try {
                Message obtain = Message.obtain();
                try {
                    obtain.what = Integer.valueOf(this.a).intValue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                obtain.obj = str;
                if (!f.a(this.c, obtain)) {
                    JSONObject jSONObject = new JSONObject(str);
                    boolean optBoolean = jSONObject.optBoolean("suc", false);
                    final Object optString = jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                    Activity l = f.l(this.c);
                    if (optBoolean) {
                        if (TextUtils.isEmpty(this.b)) {
                            if (!TextUtils.isEmpty(optString) && l != null && !l.isFinishing()) {
                                l.runOnUiThread(new Runnable(this) {
                                    final /* synthetic */ f$10 b;

                                    public void run() {
                                        af.a(ReaderApplication.getApplicationImp(), optString, 0).a();
                                    }
                                });
                            }
                        } else if (l != null && !l.isFinishing()) {
                            l.runOnUiThread(new Runnable(this) {
                                final /* synthetic */ f$10 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    try {
                                        com.qq.reader.qurl.c.a(f.m(this.a.c), this.a.b);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    } else if (!TextUtils.isEmpty(optString) && l != null && !l.isFinishing()) {
                        l.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ f$10 b;

                            public void run() {
                                af.a(ReaderApplication.getApplicationImp(), optString, 0).a();
                            }
                        });
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
        Activity n = f.n(this.c);
        if (n != null && !n.isFinishing()) {
            n.runOnUiThread(new Runnable(this) {
                final /* synthetic */ f$10 a;

                {
                    this.a = r1;
                }

                public void run() {
                    af.a(ReaderApplication.getApplicationImp(), "网络异常，请稍后重试", 0).a();
                }
            });
        }
    }
}
