package com.qq.reader.module.question.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.activity.ReaderBaseActivity;
import com.qq.reader.common.imageloader.a;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.UserQASettingUploadTask;
import com.qq.reader.common.utils.ao;
import com.qq.reader.common.utils.ao.e;
import com.qq.reader.view.af;
import com.qq.reader.view.c;
import com.tencent.feedback.proguard.R;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

public class QAIntroductionEditActivity extends ReaderBaseActivity implements OnClickListener {
    EditText a;
    EditText b;
    ImageView c;
    ImageView d;
    View e;
    View f;
    final int g = 100;
    final String h = "999999";
    String i;
    c j;
    boolean k;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_qa_introduction_layout);
        b();
        a();
    }

    private void a() {
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("userIcon");
        int intExtra = intent.getIntExtra("userLevel", 0);
        CharSequence stringExtra2 = intent.getStringExtra("userIntro");
        int intExtra2 = intent.getIntExtra("price", 0);
        this.i = intent.getStringExtra("authorId");
        com.qq.reader.common.imageloader.c.a(this).a(stringExtra, this.c, a.a().b());
        ((ImageView) findViewById(R.id.type_icon)).setImageResource(ao.e(intExtra));
        if (!TextUtils.isEmpty(stringExtra2)) {
            this.a.setText(stringExtra2);
            this.a.setSelection(this.a.length());
        }
        this.b.setText(String.valueOf(intExtra2));
        if (intExtra2 == 0) {
            this.e.setEnabled(false);
        }
        this.b.setFilters(new InputFilter[]{new InputFilter(this) {
            final /* synthetic */ QAIntroductionEditActivity a;

            {
                this.a = r1;
            }

            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                return TextUtils.isDigitsOnly(charSequence) ? charSequence : "";
            }
        }});
        this.b.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ QAIntroductionEditActivity a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                Object obj = editable.toString();
                if (obj.startsWith("0")) {
                    editable.delete(0, 1);
                    return;
                }
                if (obj.length() > "999999".length() || obj.compareTo("999999") > 0) {
                    editable.delete("999999".length(), editable.length());
                    af.a(this.a, ReaderApplication.getApplicationImp().getResources().getString(R.string.audio_quiz_maxbookcoin_toast), 0).a();
                }
                if (obj.length() <= 0 || !TextUtils.isDigitsOnly(obj) || Integer.valueOf(obj).intValue() == 0) {
                    this.a.e.setEnabled(false);
                } else {
                    this.a.e.setEnabled(true);
                }
            }
        });
    }

    private void b() {
        View findViewById = findViewById(R.id.rootlayout);
        findViewById.setOnFocusChangeListener(new OnFocusChangeListener(this) {
            final /* synthetic */ QAIntroductionEditActivity a;

            {
                this.a = r1;
            }

            public void onFocusChange(View view, boolean z) {
                if (z) {
                    e.a(view.getWindowToken(), this.a);
                }
            }
        });
        findViewById.setOnClickListener(this);
        this.j = new c(this);
        this.j.a(ReaderApplication.getApplicationImp().getResources().getString(R.string.saving));
        this.a = (EditText) findViewById(R.id.et_introduction);
        this.b = (EditText) findViewById(R.id.question_cost);
        this.c = (ImageView) findViewById(R.id.user_icon);
        this.d = (ImageView) findViewById(R.id.type_icon);
        this.e = findViewById(R.id.ok_btn);
        this.e.setOnClickListener(this);
        this.f = findViewById(R.id.loading_layout);
        ((TextView) this.f.findViewById(R.id.default_loading_text)).setText(ReaderApplication.getApplicationImp().getResources().getString(R.string.saving));
        this.f.setVisibility(8);
        ((TextView) findViewById(R.id.profile_header_title)).setText(R.string.author_edit_fenda_title);
        findViewById(R.id.profile_header_left_back).setOnClickListener(this);
        com.qq.reader.common.imageloader.c.a(this).a(com.qq.reader.common.login.c.c().b(), this.c, a.a().b());
        this.a.setFilters(new InputFilter[]{new InputFilter(this) {
            final /* synthetic */ QAIntroductionEditActivity a;

            {
                this.a = r1;
            }

            public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                return Pattern.compile("[\n\t\r]").matcher(charSequence).replaceAll("");
            }
        }});
        this.a.addTextChangedListener(new TextWatcher(this) {
            final /* synthetic */ QAIntroductionEditActivity a;

            {
                this.a = r1;
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString().endsWith(" ")) {
                    editable.delete(editable.length() - 1, editable.length());
                } else if (editable.length() > 100) {
                    editable.delete(100, editable.length());
                    af.a(this.a, R.string.author_edit_fenda_onmax_length, 0).a();
                }
            }
        });
    }

    protected boolean handleMessageImp(Message message) {
        return super.handleMessageImp(message);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_header_left_back:
                e.a(this.b.getWindowToken(), this);
                finish();
                return;
            case R.id.rootlayout:
                e.a(this.b.getWindowToken(), this);
                return;
            case R.id.ok_btn:
                c();
                return;
            default:
                return;
        }
    }

    private void c() {
        if (!d()) {
            af.a(this, ReaderApplication.getApplicationImp().getResources().getString(R.string.author_edit_fenda_price_error), 0).a();
        } else if (this.k) {
            af.a(this, ReaderApplication.getApplicationImp().getResources().getString(R.string.author_edit_fenda_upload_busy), 0).a();
        } else {
            e();
            g.a().a(new UserQASettingUploadTask(this.a.getText().toString().trim(), String.valueOf(Integer.valueOf(this.b.getText().toString().trim())), this.i, new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ QAIntroductionEditActivity a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    com.qq.reader.common.monitor.debug.c.e("TAG", "system return = " + str);
                    this.a.f();
                    try {
                        if (new JSONObject(str).optInt("code") == 0) {
                            this.a.runOnUiThread(new Runnable(this) {
                                final /* synthetic */ AnonymousClass6 a;

                                {
                                    this.a = r1;
                                }

                                public void run() {
                                    af.a(this.a.a, this.a.a.getString(R.string.author_edit_fenda_success), 0).a();
                                }
                            });
                            Intent intent = new Intent();
                            intent.putExtra("userIntroduce", this.a.a.getText().toString());
                            intent.putExtra("userQuestionPrice", this.a.b.getText().toString());
                            this.a.setResult(-1, intent);
                            this.a.finish();
                            return;
                        }
                        this.a.runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass6 a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                af.a(this.a.a, this.a.a.getString(R.string.author_edit_fenda_server_error), 0).a();
                            }
                        });
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                    this.a.f();
                    this.a.runOnUiThread(new Runnable(this) {
                        final /* synthetic */ AnonymousClass6 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            af.a(this.a.a, this.a.a.getString(R.string.net_not_available), 0).a();
                        }
                    });
                }
            }));
        }
    }

    private boolean d() {
        CharSequence obj = this.b.getText().toString();
        if (obj.length() <= 0 || !TextUtils.isDigitsOnly(obj)) {
            return false;
        }
        return true;
    }

    private void e() {
        this.j.f_();
        this.a.setEnabled(false);
        this.b.setEnabled(false);
        this.e.setEnabled(false);
        this.k = true;
    }

    private void f() {
        this.k = false;
        runOnUiThread(new Runnable(this) {
            final /* synthetic */ QAIntroductionEditActivity a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.j.dismiss();
                this.a.a.setEnabled(true);
                this.a.b.setEnabled(true);
                if (this.a.b.length() > 0 && TextUtils.isDigitsOnly(this.a.b.getText().toString()) && Integer.valueOf(this.a.b.getText().toString()).intValue() > 0) {
                    this.a.e.setEnabled(true);
                }
            }
        });
    }

    public void finish() {
        if (!this.k) {
            super.finish();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    protected void onStop() {
        super.onStop();
    }
}
