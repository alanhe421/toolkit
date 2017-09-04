package com.qq.reader.common.web.js;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.login.a;
import com.qq.reader.common.login.c;
import com.qq.reader.common.login.e;
import com.qq.reader.common.monitor.f;
import com.qq.reader.common.web.js.a.a.b;
import com.qq.reader.view.af;
import com.tencent.android.tpush.common.MessageKey;

public class JSLogin extends b implements e {
    private Activity a;
    private Handler b = null;
    private a c;
    private String d;
    private String e;
    private com.qq.reader.view.web.e f;

    public JSLogin(Activity activity) {
        this.a = activity;
        this.b = new Handler(this) {
            final /* synthetic */ JSLogin a;

            {
                this.a = r1;
            }

            public void handleMessage(Message message) {
                if (message.what == 50001) {
                    if ("1".equals(this.a.e)) {
                        Intent intent = new Intent();
                        c.a(this.a.a, 1);
                        return;
                    }
                    ((ReaderBaseActivity) this.a.a).startLogin();
                } else if (message.what == 50002) {
                    c.a();
                } else if (message.what == 50003) {
                    if (!c.b()) {
                        ((ReaderBaseActivity) this.a.a).startLogin();
                    }
                } else if (message.what == 6000002) {
                    af.a(this.a.a, (CharSequence) "操作失败，请重试", 0).a();
                } else if (message.what == 6000003) {
                    c.a();
                    this.a.login(this.a.d, this.a.e);
                }
            }
        };
    }

    public String login() {
        this.e = "0";
        f.a("JSLogin without url", MessageKey.MSG_ACCEPT_TIME_START);
        a(null);
        if (!this.b.hasMessages(50001)) {
            this.b.sendEmptyMessage(50001);
        }
        return null;
    }

    public void setNextLoginTask(a aVar) {
        this.c = aVar;
    }

    @Deprecated
    public void loginWithNextTask() {
        ((ReaderBaseActivity) this.a).setLoginNextTask(this.c);
        login();
    }

    public String login(String str) {
        this.e = "0";
        this.d = str;
        f.a("JSLogin with url", MessageKey.MSG_ACCEPT_TIME_START);
        a(str);
        this.b.sendEmptyMessage(50001);
        return null;
    }

    public String login(String str, String str2) {
        this.d = str;
        this.e = str2;
        f.a("JSLogin with url", MessageKey.MSG_ACCEPT_TIME_START);
        a(str);
        this.b.sendEmptyMessage(50001);
        return null;
    }

    private void a(String str) {
        if (!(this.f == null || str == null)) {
            this.f.setDestUrl(str);
            if (this.c == null) {
                this.c = new a(this) {
                    final /* synthetic */ JSLogin a;

                    {
                        this.a = r1;
                    }

                    public void a(int i) {
                        if (i == 1) {
                            this.a.f.reload();
                        }
                    }
                };
            }
        }
        if (this.c != null) {
            ((ReaderBaseActivity) this.a).setLoginNextTask(this.c);
        }
    }

    public int checkLogout(String str) {
        return -1;
    }

    public String open_ucenter(String str) {
        if (this.a instanceof com.qq.reader.view.web.e) {
            ((com.qq.reader.view.web.e) this.a).setDestUrl(str);
        }
        this.b.sendEmptyMessage(50003);
        return null;
    }

    public void logout() {
        this.b.sendEmptyMessage(50002);
    }

    public void toLogin() {
        this.b.sendEmptyMessage(50001);
    }

    public void onLoginSuccess(int i) {
        if (i == 3) {
            this.b.sendEmptyMessage(6000002);
        }
    }

    public void onLoginError(String str, int i, int i2) {
        if (i == 3) {
            this.b.sendEmptyMessage(6000003);
        }
    }

    public void onNeedVerifyImage(String str, byte[] bArr) {
    }

    public void setLoginListener(com.qq.reader.view.web.e eVar) {
        this.f = eVar;
    }
}
