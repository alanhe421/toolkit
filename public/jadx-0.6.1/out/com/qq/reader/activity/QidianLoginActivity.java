package com.qq.reader.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.login.a.d;
import com.qq.reader.common.login.define.a;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.ReaderTask;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.QidianUserInfoTask;
import com.qq.reader.common.utils.networkUtil.e;
import com.qq.reader.view.af;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.proguard.R;
import com.yuewen.ywlogin.b.b;
import java.util.HashMap;
import java.util.Map;
import oicq.wlogin_sdk.request.WtloginHelper.SigType;
import org.json.JSONObject;

public class QidianLoginActivity extends ReaderBaseActivity {
    private TextView a;
    private EditText b;
    private EditText c;
    private Button d;
    private ImageView e;
    private b f;
    private ImageView g;
    private Dialog h;
    private CheckBox i;
    private boolean j = true;
    private TextView k;
    private TextView l;
    private ImageView m;
    private ImageView n;
    private long o;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.login_qidian_activity);
        d.g().h();
        a();
    }

    private void a() {
        this.a = (TextView) findViewById(R.id.profile_header_title);
        this.b = (EditText) findViewById(R.id.login_edit_account);
        this.c = (EditText) findViewById(R.id.login_edit_pwd);
        this.m = (ImageView) findViewById(R.id.login_delete_account);
        this.n = (ImageView) findViewById(R.id.login_delete_pwd);
        this.m.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ QidianLoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b.setText("");
                this.a.b.setFocusable(true);
            }
        });
        this.n.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ QidianLoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.c.setText("");
                this.a.c.setFocusable(true);
            }
        });
        this.d = (Button) findViewById(R.id.login_btn_login);
        this.i = (CheckBox) findViewById(R.id.user_agreement_checkbox);
        this.e = (ImageView) findViewById(R.id.profile_header_left_back);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ QidianLoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.b();
                this.a.finish();
            }
        });
        this.i.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ QidianLoginActivity a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.a.j = z;
                CharSequence trim = this.a.b.getText().toString().trim();
                CharSequence trim2 = this.a.c.getText().toString().trim();
                if (!z || TextUtils.isEmpty(trim) || TextUtils.isEmpty(trim2)) {
                    this.a.d.setEnabled(false);
                } else {
                    this.a.d.setEnabled(true);
                }
            }
        });
        TextWatcher anonymousClass8 = new TextWatcher(this) {
            final /* synthetic */ QidianLoginActivity a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                CharSequence trim = this.a.b.getText().toString().trim();
                CharSequence trim2 = this.a.c.getText().toString().trim();
                if (!this.a.j || TextUtils.isEmpty(trim) || TextUtils.isEmpty(trim2)) {
                    this.a.d.setEnabled(false);
                } else {
                    this.a.d.setEnabled(true);
                }
                if (TextUtils.isEmpty(trim)) {
                    this.a.m.setVisibility(8);
                } else {
                    this.a.m.setVisibility(0);
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        };
        TextWatcher anonymousClass9 = new TextWatcher(this) {
            final /* synthetic */ QidianLoginActivity a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                CharSequence trim = this.a.b.getText().toString().trim();
                CharSequence trim2 = this.a.c.getText().toString().trim();
                if (!this.a.j || TextUtils.isEmpty(trim) || TextUtils.isEmpty(trim2)) {
                    this.a.d.setEnabled(false);
                } else {
                    this.a.d.setEnabled(true);
                }
                if (TextUtils.isEmpty(trim2)) {
                    this.a.n.setVisibility(8);
                } else {
                    this.a.n.setVisibility(0);
                }
            }

            public void afterTextChanged(Editable editable) {
            }
        };
        this.b.addTextChangedListener(anonymousClass8);
        this.c.addTextChangedListener(anonymousClass9);
        this.a.setText("起点账号登录");
        this.f = new b(this) {
            final /* synthetic */ QidianLoginActivity a;

            {
                this.a = r1;
            }

            public void a(JSONObject jSONObject) {
                i.a("qidian_login_success_count", null, this.a.getApplicationContext());
                long currentTimeMillis = System.currentTimeMillis() - this.a.o;
                Map hashMap = new HashMap();
                hashMap.put("time", currentTimeMillis + "");
                i.a("qidian_login_success_time", hashMap, this.a.getApplicationContext());
                this.a.progressCancel();
                if (jSONObject != null && jSONObject.optInt("code", -1) == 0) {
                    String optString = jSONObject.optString("message");
                    if (TextUtils.isEmpty(optString)) {
                        this.a.a("登录成功");
                    } else {
                        this.a.a(optString);
                    }
                    JSONObject optJSONObject = jSONObject.optJSONObject("data");
                    if (optJSONObject != null) {
                        String optString2 = optJSONObject.optString("ywGuid");
                        optString = optJSONObject.optString("ywKey");
                        a.a(this.a.getApplicationContext(), 50);
                        a.b(this.a.getApplicationContext(), optString2);
                        a.a(this.a.getApplicationContext(), optString);
                        a.h(this.a.getApplicationContext(), optString2);
                        d.g().f();
                    }
                    this.a.c();
                }
            }

            public void a(int i, String str) {
                Map hashMap = new HashMap();
                hashMap.put("reason", str);
                i.a("qidian_login_fail_count", hashMap, this.a.getApplicationContext());
                long currentTimeMillis = System.currentTimeMillis() - this.a.o;
                Map hashMap2 = new HashMap();
                hashMap2.put("time", currentTimeMillis + "");
                i.a("qidian_login_fail_time", hashMap2, this.a.getApplicationContext());
                this.a.progressCancel();
                this.a.a(str);
            }

            public void a(String str, String str2) {
                this.a.progressCancel();
                if (!TextUtils.isEmpty(str2)) {
                    if (this.a.h == null || !(this.a.h == null || this.a.h.isShowing())) {
                        b(str, str2);
                    } else {
                        c.a(this.a).a(str2, this.a.g);
                    }
                }
            }

            private void b(final String str, String str2) {
                if (!this.a.isFinishing()) {
                    View inflate = LayoutInflater.from(this.a).inflate(R.layout.qidian_login_imageverifycode_dialog, null);
                    final EditText editText = (EditText) inflate.findViewById(R.id.verifyIpnut);
                    this.a.g = (ImageView) inflate.findViewById(R.id.verifyImage);
                    TextView textView = (TextView) inflate.findViewById(R.id.getVerifyImage);
                    final TextView textView2 = (TextView) inflate.findViewById(R.id.verify_btn_sure);
                    TextView textView3 = (TextView) inflate.findViewById(R.id.verify_btn_cancel);
                    ImageView imageView = (ImageView) inflate.findViewById(R.id.verify_dialog_close);
                    if (this.a.h == null) {
                        this.a.h = new Dialog(this.a, R.style.transparent_theme_dialog);
                    }
                    this.a.h.setContentView(inflate);
                    this.a.h.show();
                    textView2.setEnabled(false);
                    c.a(this.a).a(str2, this.a.g, com.qq.reader.common.imageloader.a.a().a((int) R.drawable.profile_default_small_avator));
                    textView.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass10 b;

                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putString("login_qq", this.b.a.b.getText().toString());
                            bundle.putString("login_password", this.b.a.c.getText().toString());
                            d.g().a(bundle, this.b.a.f);
                            editText.setFocusable(true);
                            editText.setFocusableInTouchMode(true);
                            editText.requestFocus();
                        }
                    });
                    textView2.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass10 c;

                        public void onClick(View view) {
                            Bundle bundle = new Bundle();
                            bundle.putString("login_qq", this.c.a.b.getText().toString());
                            bundle.putString("login_password", this.c.a.c.getText().toString());
                            bundle.putString("login_sessionkey", str);
                            bundle.putString("login_imgValidateCode", editText.getText().toString());
                            this.c.a.showPorgress("正在登录");
                            d.g().b(bundle, this.c.a.f);
                            if (this.c.a.h.isShowing()) {
                                this.c.a.h.dismiss();
                            }
                        }
                    });
                    textView3.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass10 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            if (this.a.a.h.isShowing()) {
                                this.a.a.h.dismiss();
                            }
                        }
                    });
                    imageView.setOnClickListener(new OnClickListener(this) {
                        final /* synthetic */ AnonymousClass10 a;

                        {
                            this.a = r1;
                        }

                        public void onClick(View view) {
                            if (this.a.a.h.isShowing()) {
                                this.a.a.h.dismiss();
                            }
                        }
                    });
                    editText.addTextChangedListener(new TextWatcher(this) {
                        final /* synthetic */ AnonymousClass10 b;

                        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                            if (charSequence.length() > 0) {
                                textView2.setEnabled(true);
                            } else {
                                textView2.setEnabled(false);
                            }
                        }

                        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                        }

                        public void afterTextChanged(Editable editable) {
                        }
                    });
                    editText.setOnEditorActionListener(new OnEditorActionListener(this) {
                        final /* synthetic */ AnonymousClass10 a;

                        {
                            this.a = r1;
                        }

                        public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                            return false;
                        }
                    });
                    editText.setOnFocusChangeListener(new OnFocusChangeListener(this) {
                        final /* synthetic */ AnonymousClass10 a;

                        {
                            this.a = r1;
                        }

                        public void onFocusChange(View view, boolean z) {
                            if (this.a.a.h != null) {
                                this.a.a.h.getWindow().setSoftInputMode(5);
                            }
                        }
                    });
                }
            }

            public void b(JSONObject jSONObject) {
            }
        };
        this.d.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ QidianLoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (e.a(this.a.getApplicationContext())) {
                    d.g().a(this.a);
                    this.a.showPorgress("正在登录");
                    i.a("qidian_login_count", null, this.a.getApplicationContext());
                    this.a.o = System.currentTimeMillis();
                    Bundle bundle = new Bundle();
                    bundle.putString("login_qq", this.a.b.getText().toString());
                    bundle.putString("login_password", this.a.c.getText().toString());
                    d.g().a(bundle, this.a.f);
                    return;
                }
                this.a.a("网络异常，请稍后重试");
            }
        });
        this.k = (TextView) findViewById(R.id.user_agreement_tv);
        this.k.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ QidianLoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("com.qq.reader.WebContent", com.qq.reader.appconfig.e.cX);
                intent.setClass(this.a, WebBrowserForContents.class);
                intent.setFlags(SigType.WLOGIN_QRPUSH);
                this.a.startActivity(intent);
            }
        });
        this.l = (TextView) findViewById(R.id.user_private_tv);
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ QidianLoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("com.qq.reader.WebContent", com.qq.reader.appconfig.e.cY);
                intent.setClass(this.a, WebBrowserForContents.class);
                intent.setFlags(SigType.WLOGIN_QRPUSH);
                this.a.startActivity(intent);
            }
        });
    }

    private void a(String str) {
        af.a((Context) this, (CharSequence) str, 0).a();
    }

    public void onLoginSuccess(int i) {
        super.onLoginSuccess(i);
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ QidianLoginActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.progressCancel();
                this.a.setResult(-1);
                this.a.finish();
            }
        });
    }

    protected void onDestroy() {
        super.onDestroy();
        b();
    }

    private void b() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null && inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(this.b.getWindowToken(), 0);
        }
    }

    private void c() {
        ReaderTask qidianUserInfoTask = new QidianUserInfoTask(new com.qq.reader.common.readertask.ordinal.c(this) {
            final /* synthetic */ QidianLoginActivity a;

            {
                this.a = r1;
            }

            public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                if (!TextUtils.isEmpty(str)) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject != null) {
                            a.d(this.a.getApplicationContext(), jSONObject.optString(MessageKey.MSG_ICON));
                            a.c(this.a.getApplicationContext(), jSONObject.optString("nick"));
                            d.g().a(50, true);
                        }
                    } catch (Exception e) {
                    }
                }
            }

            public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                d.g().a(50, -2, "网络错误，请稍后重试", exception);
            }
        });
        qidianUserInfoTask.setPriority(4);
        g.a().a(qidianUserInfoTask);
    }
}
