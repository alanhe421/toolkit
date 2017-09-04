package com.qq.reader.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RatingBar;
import android.widget.TextView;
import com.pay.http.APPluginErrorCode;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.c.a;
import com.qq.reader.common.emotion.SystemEmoticonPanel;
import com.qq.reader.common.emotion.b;
import com.qq.reader.common.emotion.c;
import com.qq.reader.common.emotion.d;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.readertask.g;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolTask;
import com.qq.reader.common.readertask.protocol.GetBookUserScoreTask;
import com.qq.reader.common.readertask.protocol.UploadBookUserScoreTask;
import com.qq.reader.common.utils.StatisticsManager;
import com.qq.reader.common.utils.aj;
import com.qq.reader.common.widget.ReaderRatingBar;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.qq.reader.view.AlertDialog;
import com.qq.reader.view.af;
import com.tencent.android.tpush.common.MessageKey;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.feedback.proguard.R;
import com.tencent.open.SocialConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class CommitCommentActivity extends ReaderBaseActivity implements OnClickListener, c {
    private static HashMap<Long, String> p = new HashMap();
    int a = 0;
    private Bundle b;
    private long c;
    private String d;
    private View e;
    private LinearLayout f;
    private Button g;
    private EditText h;
    private EditText i;
    private final int j = 3001;
    private final int k = 31;
    private TextView l;
    private long m;
    private LinearLayout n;
    private PopupWindow o;
    private ReaderRatingBar q;
    private float r;
    private int s;
    private LinearLayout t;
    private TextWatcher u = new TextWatcher(this) {
        final /* synthetic */ CommitCommentActivity a;

        {
            this.a = r1;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() > 30) {
                editable.delete(30, editable.toString().length());
                this.a.a(this.a.getString(R.string.comment_title_length_reach_limit));
            }
            if (editable.toString().length() > 0 || this.a.h.getText().toString().length() > 0) {
                this.a.l.setClickable(true);
                this.a.l.setTextColor(this.a.getResources().getColor(R.color.common_titler_title_textcolor));
                return;
            }
            this.a.l.setClickable(false);
            this.a.l.setTextColor(this.a.getResources().getColor(R.color.commit_comment_btn_bg_color));
        }
    };
    private TextWatcher v = new TextWatcher(this) {
        final /* synthetic */ CommitCommentActivity a;

        {
            this.a = r1;
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            if (editable.toString().length() > APPluginErrorCode.ERROR_APP_TENPAY) {
                editable.delete(APPluginErrorCode.ERROR_APP_TENPAY, editable.toString().length());
                this.a.a(this.a.getString(R.string.comment_length_reach_limit));
            }
            if (editable.toString().length() > 0 || this.a.i.getText().toString().length() > 0) {
                this.a.l.setClickable(true);
                this.a.l.setTextColor(this.a.getResources().getColor(R.color.common_titler_title_textcolor));
                return;
            }
            this.a.l.setClickable(false);
            this.a.l.setTextColor(this.a.getResources().getColor(R.color.commit_comment_btn_bg_color));
        }
    };
    private OnFocusChangeListener w = new OnFocusChangeListener(this) {
        final /* synthetic */ CommitCommentActivity a;

        {
            this.a = r1;
        }

        public void onFocusChange(View view, boolean z) {
            this.a.g.setClickable(true);
            switch (view.getId()) {
                case R.id.et_input:
                    if (z) {
                        this.a.g.setClickable(true);
                        return;
                    }
                    return;
                case R.id.et_input_title:
                    if (z) {
                        this.a.g.setClickable(false);
                        this.a.h();
                        this.a.o.dismiss();
                        this.a.g.setBackgroundResource(R.drawable.bookclub_emotion_gray);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };
    private OnGlobalLayoutListener x = new OnGlobalLayoutListener(this) {
        final /* synthetic */ CommitCommentActivity a;

        {
            this.a = r1;
        }

        public void onGlobalLayout() {
            int i;
            Rect rect = new Rect();
            View decorView = this.a.getWindow().getDecorView();
            decorView.getWindowVisibleDisplayFrame(rect);
            int height = decorView.getRootView().getHeight();
            if (VERSION.SDK_INT < 21 || !aj.a(this.a)) {
                i = height - rect.bottom;
            } else {
                i = (height - rect.bottom) - a.cb;
            }
            if (this.a.a - i > 50) {
                this.a.o.dismiss();
            }
            this.a.a = i;
            if (i > 100) {
                this.a.n.setVisibility(0);
                this.a.a(i);
                this.a.t.setPadding(0, 0, 0, this.a.n.getHeight());
            } else if (!this.a.o.isShowing()) {
                this.a.n.setVisibility(8);
            }
        }
    };
    private String y = "";

    public void a(int i) {
        this.s = i;
        this.o.setHeight(i);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.e = LayoutInflater.from(this).inflate(R.layout.write_topic_ui, null);
        setContentView(this.e);
        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.emotion_normal_background));
        this.e.setFocusableInTouchMode(true);
        this.b = getIntent().getExtras();
        this.c = this.b.getLong("URL_BUILD_PERE_BOOK_ID", 0);
        this.d = this.b.getString("PARA_TYPE_TOPIC_CONTENT");
        this.y = this.b.getString("KEY_TASK_KEY");
        i.a("event_C57", null, this);
        Map hashMap = new HashMap();
        hashMap.put("bid", String.valueOf(this.c));
        if (this.c == 570698) {
            hashMap.put(s.ORIGIN, "1");
            i.a("event_E4", hashMap, this);
            StatisticsManager.a().a("event_E4", hashMap);
        } else if (this.c == 614782) {
            hashMap.put(s.ORIGIN, "2");
            i.a("event_E4", hashMap, this);
            StatisticsManager.a().a("event_E4", hashMap);
        } else if (this.c == 500680) {
            hashMap.put(s.ORIGIN, "3");
            i.a("event_E4", hashMap, this);
            StatisticsManager.a().a("event_E4", hashMap);
        } else if (this.c == 612464) {
            hashMap.put(s.ORIGIN, "4");
            i.a("event_E4", hashMap, this);
            StatisticsManager.a().a("event_E4", hashMap);
        }
        d();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (keyEvent.getAction() == 0) {
            switch (i) {
                case 4:
                    a();
                    return true;
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void d() {
        Button button = (Button) this.e.findViewById(R.id.profile_header_right_button);
        button.setText(getResources().getString(R.string.reply_send));
        button.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommitCommentActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.f()) {
                    Intent intent = this.a.getIntent();
                    intent.putExtra("COMMIT_COMMENT_CONTENT", this.a.i());
                    intent.putExtra("COMMIT_COMMENT_FAKECOMMITID", "" + System.currentTimeMillis());
                    intent.putExtra("URL_BUILD_PERE_BOOK_ID", this.a.c);
                    intent.putExtra("KEY_TASK_KEY", this.a.y);
                    intent.putExtra("DELETE_COMMENT", false);
                    if (this.a.b.containsKey("SHOWCOMMENTACTIVITY") && this.a.b.getBoolean("SHOWCOMMENTACTIVITY")) {
                        intent.putExtra("HIDECOMMENTACTIVITYIMMEDIATELY", false);
                    }
                    this.a.setResult(-1, intent);
                    this.a.h.clearFocus();
                    ((InputMethodManager) this.a.getSystemService("input_method")).hideSoftInputFromWindow(this.a.getCurrentFocus().getApplicationWindowToken(), 2);
                    if (CommitCommentActivity.p != null) {
                        CommitCommentActivity.p.remove(Long.valueOf(this.a.c));
                    }
                    this.a.finish();
                }
            }
        });
        button.setVisibility(0);
        this.l = button;
        this.l.setClickable(false);
        this.l.setTextColor(getResources().getColor(R.color.commit_comment_btn_bg_color));
        TextView textView = (TextView) this.e.findViewById(R.id.profile_header_title);
        textView.setText("写书评");
        ((ImageView) this.e.findViewById(R.id.profile_header_left_back)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ CommitCommentActivity a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                CommitCommentActivity.p.put(Long.valueOf(this.a.c), this.a.i());
                this.a.a();
            }
        });
        this.f = (LinearLayout) this.e.findViewById(R.id.ll_container);
        this.t = (LinearLayout) this.e.findViewById(R.id.ll_input_area);
        this.i = (EditText) this.e.findViewById(R.id.et_input_title);
        this.g = (Button) this.e.findViewById(R.id.btn_switch);
        this.h = (EditText) this.e.findViewById(R.id.et_input);
        this.n = (LinearLayout) this.e.findViewById(R.id.ll_emotion_control_panel);
        this.g.setOnClickListener(this);
        this.h.addTextChangedListener(this.v);
        this.h.setOnClickListener(this);
        this.h.setOnFocusChangeListener(this.w);
        if (this.c > 3) {
            this.i.setHint(getString(R.string.bookclub_comment_title_hint));
            Object string = getString(R.string.bookclub_comment_content_hint);
            CharSequence spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append(string);
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(16, true), 0, 10, 18);
            spannableStringBuilder.setSpan(new AbsoluteSizeSpan(13, true), 12, string.length(), 18);
            this.h.setHint(spannableStringBuilder);
        } else {
            this.i.setHint(R.string.bookclub_comment_official_title_hint);
            this.h.setHint(R.string.bookclub_comment_official_content_hint);
        }
        this.i.addTextChangedListener(this.u);
        this.i.setOnClickListener(this);
        this.i.setOnFocusChangeListener(this.w);
        this.h.setFilters(new InputFilter[]{new LengthFilter(3001)});
        this.i.setFilters(new InputFilter[]{new LengthFilter(31)});
        this.s = (int) getResources().getDimension(R.dimen.keyboard_height);
        this.o = new PopupWindow(new SystemEmoticonPanel((Context) this, (c) this), -1, this.s);
        h();
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(this.x);
        ((InputMethodManager) getSystemService("input_method")).showSoftInput(this.h, 0);
        this.g.setClickable(false);
        this.q = (ReaderRatingBar) findViewById(R.id.bookclub_ratingbar);
        TextView textView2 = (TextView) findViewById(R.id.bookclub_ratingbar_text);
        if (this.c <= 3) {
            this.q.setVisibility(8);
            textView2.setVisibility(8);
            findViewById(R.id.bookclub_ratingbar_divider).setVisibility(8);
        } else {
            textView.setText(R.string.bookclub_bookcomment_title);
        }
        String[] stringArray = getResources().getStringArray(R.array.rating_score_intro);
        this.q.setRatingChangedDelaytime(1000);
        this.q.setRatingText(textView2, stringArray);
        this.q.setOnRatingBarDelayChangedListener(new ReaderRatingBar.a(this) {
            final /* synthetic */ CommitCommentActivity a;

            {
                this.a = r1;
            }

            public boolean a(RatingBar ratingBar, float f) {
                return false;
            }

            public void b(RatingBar ratingBar, final float f) {
                com.qq.reader.common.monitor.debug.c.e("onRatingBarDelay", f + "");
                if (f >= 1.0f) {
                    i.a("event_A181", null, ReaderApplication.getApplicationImp());
                    g.a().a(new UploadBookUserScoreTask(this.a.c, f, new com.qq.reader.common.readertask.ordinal.c(this) {
                        final /* synthetic */ AnonymousClass8 b;

                        public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                            Message obtainMessage = this.b.a.mHandler.obtainMessage();
                            obtainMessage.what = 10000502;
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                obtainMessage.obj = jSONObject.optString(SocialConstants.PARAM_SEND_MSG);
                                obtainMessage.arg1 = jSONObject.optInt("code", -1);
                                if (obtainMessage.arg1 >= 0) {
                                    this.b.a.r = f;
                                }
                            } catch (Exception e) {
                                com.qq.reader.common.monitor.debug.c.e("CommitCommentActivity", e.getMessage());
                                obtainMessage.obj = ReaderApplication.getApplicationImp().getResources().getString(R.string.login_net_exception);
                                obtainMessage.arg1 = -1;
                            }
                            this.b.a.mHandler.sendMessage(obtainMessage);
                        }

                        public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                            Message obtainMessage = this.b.a.mHandler.obtainMessage();
                            obtainMessage.what = 10000502;
                            obtainMessage.obj = ReaderApplication.getApplicationImp().getResources().getString(R.string.login_net_exception);
                            obtainMessage.arg1 = -1;
                            this.b.a.mHandler.sendMessage(obtainMessage);
                        }
                    }));
                }
            }
        });
        if (this.d != null) {
            b(this.d);
        } else {
            String str = (String) p.get(Long.valueOf(this.c));
            if (str != null) {
                b(str);
            } else {
                this.i.setText("");
                this.h.setText("");
            }
        }
        if (this.c > 3 || !(this.d == null || this.d.contains("score"))) {
            g.a().a(new GetBookUserScoreTask(this.c, new com.qq.reader.common.readertask.ordinal.c(this) {
                final /* synthetic */ CommitCommentActivity a;

                {
                    this.a = r1;
                }

                public void onConnectionRecieveData(ReaderProtocolTask readerProtocolTask, String str, long j) {
                    Message obtainMessage = this.a.mHandler.obtainMessage();
                    obtainMessage.what = 10000501;
                    obtainMessage.obj = Float.valueOf(0.0f);
                    try {
                        JSONObject optJSONObject = new JSONObject(str).optJSONObject("scoreInfo");
                        if (optJSONObject != null) {
                            obtainMessage.obj = Float.valueOf(this.a.r = (float) optJSONObject.optDouble("score", 0.0d));
                        }
                    } catch (Exception e) {
                        com.qq.reader.common.monitor.debug.c.e("CommitCommentActivity", e.getMessage());
                    }
                    this.a.mHandler.sendMessage(obtainMessage);
                }

                public void onConnectionError(ReaderProtocolTask readerProtocolTask, Exception exception) {
                }
            }));
        }
    }

    public void a() {
        try {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(this.h.getApplicationWindowToken(), 2);
            if (TextUtils.isEmpty(this.y)) {
                if (this.b.containsKey("SHOWCOMMENTACTIVITY")) {
                    Intent intent = new Intent();
                    intent.putExtra("SHOWCOMMENTACTIVITY", this.b.getBoolean("SHOWCOMMENTACTIVITY"));
                    setResult(0, intent);
                }
                finish();
                return;
            }
            e();
        } catch (Exception e) {
            finish();
        }
    }

    private void e() {
        new AlertDialog.a(this).a((int) R.string.comment_send_title).c(R.drawable.alert_dialog_icon).b((int) R.string.bookclub_comment_alertmsg).a((int) R.string.alert_dialog_ok, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ CommitCommentActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = this.a.getIntent();
                intent.putExtra("COMMIT_COMMENT_CONTENT", this.a.i());
                intent.putExtra("COMMIT_COMMENT_FAKECOMMITID", "" + System.currentTimeMillis());
                intent.putExtra("URL_BUILD_PERE_BOOK_ID", this.a.c);
                intent.putExtra("KEY_TASK_KEY", this.a.y);
                intent.putExtra("DELETE_COMMENT", true);
                this.a.setResult(-1, intent);
                if (CommitCommentActivity.p != null) {
                    CommitCommentActivity.p.remove(Long.valueOf(this.a.c));
                }
                this.a.finish();
            }
        }).b((int) R.string.alert_dialog_cancel, new DialogInterface.OnClickListener(this) {
            final /* synthetic */ CommitCommentActivity a;

            {
                this.a = r1;
            }

            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).a().show();
    }

    public void onResume() {
        super.onResume();
        h();
        this.o.dismiss();
        this.g.setBackgroundResource(R.drawable.bookclub_emotion_gray);
        this.h.requestFocus();
    }

    public void onDestroy() {
        this.h.removeTextChangedListener(this.v);
        this.i.removeTextChangedListener(this.u);
        getWindow().getDecorView().getViewTreeObserver().removeGlobalOnLayoutListener(this.x);
        this.o.dismiss();
        super.onDestroy();
    }

    protected boolean handleMessageImp(Message message) {
        if (message.what == 500009) {
            super.finish();
            return true;
        } else if (message.what == 10000502) {
            if (!isFinishing()) {
                a(message.obj.toString());
                this.q.setRating(this.r);
            }
            return true;
        } else if (message.what != 10000501) {
            return super.handleMessageImp(message);
        } else {
            if (!isFinishing()) {
                float f = 0.0f;
                if (message.obj instanceof Float) {
                    f = ((Float) message.obj).floatValue();
                } else if (message.obj instanceof Integer) {
                    f = (float) ((Integer) message.obj).intValue();
                }
                this.q.setRating(f);
            }
            return true;
        }
    }

    public void finish() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager == null || getCurrentFocus() == null) {
            super.finish();
            return;
        }
        inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 2);
        this.mHandler.sendEmptyMessageDelayed(500009, 200);
    }

    private boolean f() {
        if (this.h.getText().toString().length() == 0) {
            a(getString(R.string.comment_empty));
            return false;
        } else if (!b.a(this.h.getText())) {
            a(getString(R.string.posttopic_submit_warning_pure_emotion));
            return false;
        } else if (b.b(this.h.getText()) < 5) {
            a(getString(R.string.posttopic_submit_warning_too_short));
            return false;
        } else if (System.currentTimeMillis() - this.m >= BuglyBroadcastRecevier.UPLOADLIMITED) {
            return true;
        } else {
            a(getString(R.string.posttopic_submit_warning_frequency_error));
            return false;
        }
    }

    private void a(String str) {
        af.a((Context) this, (CharSequence) str, 0).a();
    }

    public void b() {
        g();
    }

    public void a(d dVar) {
        if (((com.qq.reader.common.emotion.i) dVar).d.length() + this.h.getText().toString().length() > APPluginErrorCode.ERROR_APP_TENPAY) {
            a(getString(R.string.comment_length_reach_limit));
        } else {
            b.a((Context) this, this.h, dVar);
        }
    }

    public boolean b(d dVar) {
        return false;
    }

    public void a(d dVar, d dVar2, Drawable drawable) {
    }

    public void c(d dVar) {
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_input:
                h();
                this.o.dismiss();
                this.g.setBackgroundResource(R.drawable.bookclub_emotion_gray);
                this.h.requestFocus();
                return;
            case R.id.et_input_title:
                h();
                this.o.dismiss();
                this.g.setBackgroundResource(R.drawable.bookclub_emotion_gray);
                this.i.requestFocus();
                return;
            case R.id.btn_switch:
                if (this.o.isShowing()) {
                    h();
                    this.o.dismiss();
                    this.g.setBackgroundResource(R.drawable.bookclub_emotion_gray);
                    this.h.requestFocus();
                    return;
                }
                this.o.showAtLocation(this.e, 80, 0, 0);
                this.g.setBackgroundResource(R.drawable.bookclub_emotion_green);
                return;
            default:
                return;
        }
    }

    private void g() {
        this.h.onKeyDown(67, new KeyEvent(0, 67));
    }

    private void h() {
        ((InputMethodManager) getSystemService("input_method")).showSoftInput(this.h, 0);
        this.f.setVisibility(8);
    }

    private String i() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("title", this.i.getText().toString().replace("\r", "").replace("\n", "").trim());
            jSONObject.put(MessageKey.MSG_CONTENT, this.h.getText().toString());
            if (this.q.getRating() >= 1.0f) {
                jSONObject.put("score", String.valueOf(this.q.getRating()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }

    private void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                this.i.setText(jSONObject.optString("title"));
                this.h.setText(b.a((Context) this, jSONObject.optString(MessageKey.MSG_CONTENT), this.h.getTextSize()));
                this.r = Float.parseFloat(jSONObject.optString("score", "0"));
                this.q.setRating(this.r);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
