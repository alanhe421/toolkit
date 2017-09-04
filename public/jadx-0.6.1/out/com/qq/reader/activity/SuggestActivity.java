package com.qq.reader.activity;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.pay.http.APPluginErrorCode;
import com.qq.reader.appconfig.a;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.FeedBackTask;
import com.qq.reader.view.af;
import com.qq.reader.view.c;
import com.tencent.feedback.proguard.R;

public class SuggestActivity extends ReaderBaseActivity {
    private Button a;
    private EditText b;
    private EditText c;
    private EditText d;
    private c e;
    private int f = -1;
    private TextView g;

    protected void onResume() {
        super.onResume();
        CharSequence c = com.qq.reader.common.login.c.c().c();
        if (this.c == null) {
            return;
        }
        if (c == null || c.length() <= 0 || com.qq.reader.common.login.c.c().d() != 1) {
            this.c.setText(null);
        } else {
            this.c.setText(c);
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.suggestpage);
        this.b = (EditText) findViewById(R.id.suggestEditor);
        this.b.requestFocus();
        this.c = (EditText) findViewById(R.id.qq_input);
        this.d = (EditText) findViewById(R.id.tel_input);
        this.a = (Button) findViewById(R.id.profile_header_right_button);
        this.a.setVisibility(0);
        this.a.setText("提交");
        this.e = new c(this);
        this.e.a("正在提交...");
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SuggestActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String trim = this.a.b.getText().toString().trim();
                if (trim != null && trim.length() > 0) {
                    if (trim.length() > 400) {
                        trim = trim.substring(0, 400);
                    }
                    if (!this.a.e.f()) {
                        this.a.e.f_();
                    }
                    a.c.g = trim;
                    a.c.e = this.a.c.getText().toString();
                    a.c.f = this.a.d.getText().toString();
                    ReaderTask feedBackTask = new FeedBackTask(new com.qq.reader.common.readertask.ordinal.c(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                            if (this.a.a.mHandler != null) {
                                this.a.a.mHandler.sendMessage(this.a.a.mHandler.obtainMessage(10003));
                            }
                        }

                        public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                            if (this.a.a.mHandler != null) {
                                this.a.a.mHandler.sendMessage(this.a.a.mHandler.obtainMessage(10004));
                            }
                        }
                    });
                    feedBackTask.setPriority(1);
                    g.a().a(feedBackTask);
                }
            }
        });
        ((ImageView) findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ SuggestActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a();
            }
        });
        ((TextView) findViewById(R.id.profile_header_title)).setText("用户反馈");
        this.f = -1;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.f = extras.getInt("fromActivity");
        }
        this.g = (TextView) findViewById(R.id.welcome);
        this.g.setText(getString(R.string.feedback_qun_info, new Object[]{getString(R.string.app_name), getString(R.string.qqgroup1), getString(R.string.qqgroup2)}));
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            return super.onKeyDown(i, keyEvent);
        }
        a();
        return true;
    }

    protected boolean handleMessageImp(Message message) {
        switch (message.what) {
            case 10003:
                this.e.dismiss();
                af.a((Context) this, (CharSequence) "感谢反馈", (int) APPluginErrorCode.ERROR_APP_SYSTEM).a();
                finish();
                return true;
            case 10004:
                this.e.dismiss();
                af.a((Context) this, (CharSequence) "发送失败，请检查您的网络", (int) APPluginErrorCode.ERROR_APP_SYSTEM).a();
                return true;
            default:
                return super.handleMessageImp(message);
        }
    }

    public void a() {
        finish();
    }

    public void finish() {
        super.finish();
        try {
            if (VERSION.SDK != null && Integer.valueOf(VERSION.SDK).intValue() >= 5) {
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        } catch (Exception e) {
        }
    }
}
