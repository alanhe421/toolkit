package com.qq.reader.common.web.js;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import com.qq.reader.activity.VIPBrowser;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.login.c;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.view.AlertDialog.a;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;

public class JSSendSMS extends b {
    private Activity a;

    public JSSendSMS(Activity activity) {
        this.a = activity;
    }

    public void send(final String str, final String str2) {
        if (this.a != null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("您将发送短信");
            stringBuffer.append(str2);
            stringBuffer.append("到");
            stringBuffer.append(str);
            stringBuffer.append(",开通书城VIP.");
            new a(this.a).a((CharSequence) "提示").b(stringBuffer.toString()).a((CharSequence) "确定", new OnClickListener(this) {
                final /* synthetic */ JSSendSMS c;

                public void onClick(DialogInterface dialogInterface, int i) {
                    try {
                        Intent intent = new Intent("android.intent.action.SENDTO", Uri.parse("smsto:" + str));
                        intent.putExtra("sms_body", str2);
                        this.c.a.startActivity(intent);
                        this.c.a.finish();
                    } catch (Exception e) {
                        af.a(this.c.a.getApplicationContext(), (CharSequence) "该设备不具备短信功能。", 0).a();
                    }
                }
            }).b((CharSequence) "取消", new OnClickListener(this) {
                final /* synthetic */ JSSendSMS a;

                {
                    this.a = r1;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).a().show();
        }
    }

    public void setvip(String str, String str2, String str3) {
        if (this.a != null && str != null && str.equals(d.R(this.a.getApplicationContext()))) {
            if (str2 != null && str2.trim().length() > 0 && c.c().a().length() == 0) {
                com.qq.reader.common.login.define.a.c(this.a.getApplicationContext(), str2.trim());
            }
            if (Boolean.parseBoolean(str3) != com.qq.reader.common.login.define.a.o(this.a.getApplicationContext())) {
                com.qq.reader.common.login.define.a.b(this.a.getApplicationContext(), Boolean.parseBoolean(str3));
            }
        }
    }

    public void tovipview() {
        this.a.runOnUiThread(new Runnable(this) {
            final /* synthetic */ JSSendSMS a;

            {
                this.a = r1;
            }

            public void run() {
                if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() > 3) {
                    this.a.a.getWindow().setWindowAnimations(16973824);
                }
            }
        });
        Intent intent = new Intent();
        intent.setClass(this.a, VIPBrowser.class);
        intent.putExtra("com.qq.reader.webbrowser.url", e.a(this.a));
        intent.putExtra("com.qq.reader.webbrowser.title", R.string.dialog_vip);
        this.a.startActivity(intent);
    }
}
