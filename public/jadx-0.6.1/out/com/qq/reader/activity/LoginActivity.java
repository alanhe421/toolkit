package com.qq.reader.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.common.login.c;
import com.qq.reader.common.login.e;
import com.qq.reader.common.login.h;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;

public class LoginActivity extends ReaderBaseActivity {
    private EditText a;
    private EditText b;
    private Activity c;
    private TextView d;
    private ImageView e;
    private Button f;
    private boolean g = false;
    private boolean h = false;

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.login_dialog_1);
        a();
        c();
        this.c = this;
    }

    private void a() {
        this.d = (TextView) findViewById(R.id.profile_header_title);
        this.d.setText(R.string.login_profile);
        this.e = (ImageView) findViewById(R.id.profile_header_left_back);
        this.e.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d();
                this.a.e.postDelayed(new Runnable(this) {
                    final /* synthetic */ AnonymousClass1 a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.a.finish();
                    }
                }, 500);
            }
        });
        this.a = (EditText) findViewById(R.id.login_edit_account);
        this.b = (EditText) findViewById(R.id.login_edit_pwd);
        this.a.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ LoginActivity a;

            {
                this.a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (!z && !this.a.h) {
                    this.a.b();
                }
            }
        });
        this.b.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ LoginActivity a;

            {
                this.a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (z && this.a.b.length() > 0) {
                    this.a.b.selectAll();
                }
            }
        });
        this.a.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ LoginActivity a;

            {
                this.a = r1;
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String str = "[1-9][0-9]*";
                String str2 = "[1-9][0-9]{4,14}";
                if (charSequence == null || charSequence.length() == 0) {
                    this.a.h = false;
                } else if (charSequence.length() < 4) {
                    if (!charSequence.toString().matches(str)) {
                        this.a.b();
                    }
                    this.a.h = false;
                } else if (charSequence.length() >= 5) {
                    if (charSequence.toString().matches(str2)) {
                        this.a.h = true;
                    } else {
                        this.a.h = false;
                        this.a.b();
                    }
                }
                this.a.c();
            }

            public void afterTextChanged(Editable editable) {
                this.a.b.setText(null);
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
        });
        this.b.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ LoginActivity a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (charSequence == null || charSequence.length() <= 0) {
                    this.a.g = false;
                } else {
                    this.a.g = true;
                }
                this.a.c();
            }

            public void afterTextChanged(Editable editable) {
            }
        });
        this.f = (Button) findViewById(R.id.login_btn_login);
        this.f.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ LoginActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.a(false);
            }
        });
    }

    private void b() {
        af.a((Context) this, getResources().getString(R.string.login_qq_error), 0).a();
    }

    private void c() {
        if (this.h && this.g) {
            if (!this.f.isEnabled()) {
                this.f.setEnabled(true);
            }
        } else if (this.f.isEnabled()) {
            this.f.setEnabled(false);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        c.b(this);
    }

    protected void onResume() {
        super.onResume();
    }

    protected void onPause() {
        super.onPause();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
    }

    private void a(boolean z) {
        showPorgress(this.c.getResources().getString(R.string.progress_login_message));
        String obj = this.a.getText().toString();
        String obj2 = this.b.getText().toString();
        if (obj == null || obj.length() == 0) {
            progressCancel();
            af.a(this.c.getApplicationContext(), (CharSequence) "QQ号码不能为空", 0).a();
        } else if (z || !(obj2 == null || obj2.length() == 0)) {
            Bundle bundle = new Bundle();
            bundle.putString("login_qq", obj);
            bundle.putString("login_password", obj2);
            com.qq.reader.common.login.a.c.a((Context) this).a((e) this);
            com.qq.reader.common.login.a.c.a(getApplicationContext()).a((Activity) this, bundle);
        } else {
            progressCancel();
            af.a(this.c.getApplicationContext(), (CharSequence) "密码不能为空", 0).a();
        }
    }

    public void onLoginSuccess(int i) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ LoginActivity a;

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

    public void onLoginError(final String str, int i, int i2) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ LoginActivity b;

            public void run() {
                this.b.progressCancel();
                af.a(this.b.getApplicationContext(), str, 0).a();
            }
        });
    }

    public void onNeedVerifyImage(final String str, final byte[] bArr) {
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ LoginActivity c;

            public void run() {
                new h(this.c.c, bArr, str).f_();
            }
        });
    }

    private void d() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null && inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(this.a.getWindowToken(), 0);
        }
    }
}
