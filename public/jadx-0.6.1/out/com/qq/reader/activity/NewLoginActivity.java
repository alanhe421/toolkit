package com.qq.reader.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.login.a.e;
import com.qq.reader.common.login.b;
import com.qq.reader.common.login.c;
import com.qq.reader.common.login.define.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.monitor.j;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.ordinal.d;
import com.qq.reader.common.utils.o;
import com.qq.reader.qurl.JumpActivityParameter;
import com.qq.reader.view.af;
import com.qq.reader.wxapi.WXApiManager;
import com.tencent.feedback.proguard.R;
import java.io.InputStream;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;

public class NewLoginActivity extends ReaderBaseActivity {
    private static b k;
    BroadcastReceiver a = new BroadcastReceiver(this) {
        final /* synthetic */ NewLoginActivity a;

        {
            this.a = r1;
        }

        public void onReceive(Context context, Intent intent) {
            int intExtra = intent.getIntExtra("type", 0);
            String stringExtra = intent.getStringExtra("status");
            if (stringExtra.equalsIgnoreCase("success")) {
                if (intExtra == 2) {
                    String stringExtra2 = intent.getStringExtra("code");
                    if (stringExtra2 != null) {
                        e.a(ReaderApplication.getApplicationImp()).a(stringExtra2);
                    }
                    this.a.showPorgress(context.getString(R.string.accounts_loading));
                }
            } else if (stringExtra.equalsIgnoreCase("cancel") || stringExtra.equalsIgnoreCase("error")) {
                c.a();
                this.a.j = false;
            }
        }
    };
    private View b;
    private View c;
    private View d;
    private View e;
    private TextView f;
    private TextView g;
    private ImageView h;
    private LinearLayout i;
    private boolean j;
    private int l = 7;
    private CheckBox m;
    private TextView n;
    private TextView o;
    private boolean p = true;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.new_login_layout);
        a();
        b();
        registerReceiver(this.a, new IntentFilter("com.qq.reader.wxlogin.code"));
        j.a(13, 2);
        i.a("event_C14", null, this);
    }

    private void a() {
        this.l = getIntent().getIntExtra("display_login_type", 7);
    }

    private void b() {
        this.b = findViewById(R.id.login_qq);
        this.b.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NewLoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.j) {
                    if (this.a.p) {
                        this.a.c();
                        this.a.j = true;
                        return;
                    }
                    this.a.a("请先同意用户协议");
                }
            }
        });
        this.c = findViewById(R.id.login_wx);
        this.c.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NewLoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.j) {
                    if (!this.a.p) {
                        this.a.a("请先同意用户协议");
                    } else if (WXApiManager.getInstance(this.a).isWXinstalled()) {
                        this.a.d();
                        this.a.progressCancel();
                        this.a.j = true;
                    } else {
                        af.a(this.a, (CharSequence) "请先安装微信客户端", 0).a();
                    }
                }
            }
        });
        this.i = (LinearLayout) findViewById(R.id.login_other_ll);
        this.f = (TextView) findViewById(R.id.login_other);
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NewLoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!this.a.j) {
                    JumpActivityParameter jumpActivityParameter = new JumpActivityParameter();
                    jumpActivityParameter.a(4098);
                    o.y(this.a, jumpActivityParameter);
                    this.a.j = true;
                }
            }
        });
        if (a.l(this)) {
            this.i.setVisibility(0);
        } else {
            this.i.setVisibility(8);
        }
        this.d = findViewById(R.id.notice);
        switch (this.l) {
            case 1:
                this.c.setVisibility(8);
                this.d.setVisibility(8);
                this.e.setVisibility(8);
                this.f.setVisibility(8);
                break;
            case 2:
                this.b.setVisibility(8);
                this.d.setVisibility(8);
                this.e.setVisibility(8);
                this.f.setVisibility(8);
                break;
        }
        this.g = (TextView) findViewById(R.id.profile_header_title);
        this.g.setText(R.string.login_profile);
        this.h = (ImageView) findViewById(R.id.profile_header_left_back);
        this.h.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NewLoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.setResult(0);
                this.a.finish();
            }
        });
        this.m = (CheckBox) findViewById(R.id.user_agreement_checkbox);
        this.m.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ NewLoginActivity a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.a.p = z;
            }
        });
        this.n = (TextView) findViewById(R.id.user_agreement_tv);
        this.n.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NewLoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(this.a, WebBrowserForContents.class);
                intent.putExtra("com.qq.reader.WebContent", com.qq.reader.appconfig.e.cX);
                intent.setFlags(SigType.WLOGIN_QRPUSH);
                this.a.startActivity(intent);
            }
        });
        this.o = (TextView) findViewById(R.id.user_private_tv);
        this.o.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ NewLoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(this.a, WebBrowserForContents.class);
                intent.putExtra("com.qq.reader.WebContent", com.qq.reader.appconfig.e.cY);
                intent.setFlags(SigType.WLOGIN_QRPUSH);
                this.a.startActivity(intent);
            }
        });
    }

    protected void onResume() {
        super.onResume();
        this.j = false;
    }

    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.a);
        c.b(this);
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        if (i == 4098) {
            switch (i2) {
                case -1:
                    e();
                    setResult(-1);
                    finish();
                    if (k != null) {
                        k.a(1, "qq");
                        return;
                    }
                    return;
                case 0:
                    c.a();
                    return;
                default:
                    return;
            }
        } else if (i == 1201 || i == 1202) {
            switch (i2) {
                case -1:
                    com.qq.reader.common.login.a.c.a((Context) this).a((Activity) this, intent);
                    com.qq.reader.common.login.a.c.a((Context) this).a((com.qq.reader.common.login.e) this);
                    return;
                case 0:
                    progressCancel();
                    com.qq.reader.common.login.a.c.a((Context) this).a();
                    this.j = false;
                    return;
                default:
                    return;
            }
        }
    }

    private void c() {
        showPorgress(getString(R.string.accounts_loading));
        com.qq.reader.common.login.a.c.a((Context) this).a((com.qq.reader.common.login.e) this);
        com.qq.reader.common.login.a.c.a((Context) this).a((Activity) this, null);
    }

    private void d() {
        showPorgress(getString(R.string.accounts_loading));
        e.a((Context) this).a((com.qq.reader.common.login.e) this);
        e.a((Context) this).a((Activity) this, null);
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 1:
                this.j = false;
                progressCancel();
                String str = "qq";
                if (message.arg1 == 2) {
                    str = "wx";
                }
                setResult(-1);
                e();
                finish();
                if (k != null) {
                    k.a(1, str);
                    break;
                }
                break;
            case 2:
                this.j = false;
                CharSequence charSequence = (String) message.obj;
                progressCancel();
                if (!TextUtils.isEmpty(charSequence)) {
                    af.a(getApplicationContext(), charSequence, 0).a();
                    break;
                }
                break;
        }
        return super.handleMessageImp(message);
    }

    private void e() {
        ReaderTask readerProtocolTask = new ReaderProtocolTask(new d(this) {
            final /* synthetic */ NewLoginActivity a;

            {
                this.a = r1;
            }

            public void a(ReaderProtocolTask readerProtocolTask, InputStream inputStream, long j) {
                ReaderTask readerProtocolTask2 = new ReaderProtocolTask();
                readerProtocolTask2.setUrl(com.qq.reader.appconfig.e.ce);
                g.a().a(readerProtocolTask2);
            }

            public void a(ReaderProtocolTask readerProtocolTask, Exception exception) {
                ReaderTask readerProtocolTask2 = new ReaderProtocolTask();
                readerProtocolTask2.setUrl(com.qq.reader.appconfig.e.ce);
                g.a().a(readerProtocolTask2);
            }
        });
        readerProtocolTask.setUrl(com.qq.reader.appconfig.e.cd);
        g.a().a(readerProtocolTask);
    }

    public void onLoginSuccess(int i) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.arg1 = i;
        obtainMessage.what = 1;
        this.mHandler.sendMessage(obtainMessage);
        this.j = false;
    }

    public void onLoginError(String str, int i, int i2) {
        Message obtainMessage = this.mHandler.obtainMessage();
        obtainMessage.arg1 = i;
        obtainMessage.arg2 = i2;
        obtainMessage.what = 2;
        obtainMessage.obj = str;
        this.mHandler.sendMessage(obtainMessage);
        this.j = false;
    }

    public static void a(b bVar) {
        k = bVar;
    }

    private void a(String str) {
        af.a(getApplicationContext(), (CharSequence) str, 0).a();
    }
}
