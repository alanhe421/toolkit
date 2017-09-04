package com.qq.reader.common.emotion;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.Toast;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.common.utils.aj;
import com.tencent.feedback.eup.BuglyBroadcastRecevier;
import com.tencent.feedback.proguard.R;

public class ReplyView extends LinearLayout implements OnClickListener {
    private static int k;
    int a = 0;
    private final String b = ReplyView.class.getSimpleName();
    private int c = 501;
    private EditText d;
    private Button e;
    private Button f;
    private LinearLayout g;
    private a h;
    private Context i;
    private PopupWindow j;
    private ViewGroup l;
    private boolean m;
    private long n;
    private int o = 1000;
    private ViewGroup p;
    private boolean q = false;
    private int r = 100;
    private OnFocusChangeListener s = new OnFocusChangeListener(this) {
        final /* synthetic */ ReplyView a;

        {
            this.a = r1;
        }

        public void onFocusChange(View view, boolean z) {
            if (view.getId() == R.id.et_input && !z) {
                this.a.setVisibility(8);
            }
        }
    };
    private TextWatcher t = new TextWatcher(this) {
        final /* synthetic */ ReplyView a;

        {
            this.a = r1;
        }

        public void afterTextChanged(Editable editable) {
            if (this.a.h != null) {
                this.a.h.a(editable);
            }
            if (editable.toString().length() > this.a.c - 1) {
                editable.delete(this.a.c - 1, editable.toString().length());
                this.a.a(this.a.i.getString(R.string.comment_length_reach_limit));
            }
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (charSequence.toString().length() > 0) {
                this.a.e.setTextColor(this.a.getResources().getColor(R.color.reply_ui_commit_btn_enable_textcolor));
                this.a.e.setBackgroundResource(R.drawable.bookclub_reply_send_bg);
                this.a.e.setClickable(true);
                return;
            }
            this.a.e.setTextColor(this.a.getResources().getColor(R.color.reply_ui_commit_btn_disable_textcolor));
            this.a.e.setBackgroundResource(R.drawable.bookclub_reply_send_bg_disable);
            this.a.e.setClickable(false);
        }
    };
    private c u = new c(this) {
        final /* synthetic */ ReplyView a;

        {
            this.a = r1;
        }

        public void a(d dVar) {
            if (((i) dVar).d.length() + this.a.d.getText().toString().length() <= this.a.c) {
                b.a(this.a.i, this.a.d, dVar);
            }
        }

        public void a(d dVar, d dVar2, Drawable drawable) {
        }

        public boolean b(d dVar) {
            return false;
        }

        public void c(d dVar) {
        }

        public void b() {
            this.a.f();
        }
    };
    private boolean v;
    private OnGlobalLayoutListener w = new OnGlobalLayoutListener(this) {
        final /* synthetic */ ReplyView a;

        {
            this.a = r1;
        }

        public void onGlobalLayout() {
            if (this.a.l != null) {
                Rect rect = new Rect();
                this.a.l.getWindowVisibleDisplayFrame(rect);
                int height = this.a.l.getRootView().getHeight() - rect.bottom;
                if (this.a.a - height > 50) {
                    this.a.j.dismiss();
                }
                if (VERSION.SDK_INT >= 21) {
                    if (aj.a(this.a.i)) {
                        height -= com.qq.reader.common.c.a.cb;
                    }
                    this.a.r = 200;
                }
                this.a.a = height;
                if (!this.a.v) {
                    return;
                }
                if (height > this.a.r) {
                    if (!this.a.m) {
                        this.a.m = true;
                        this.a.a(height);
                        this.a.p.setVisibility(0);
                        if (this.a.h != null) {
                            this.a.h.b();
                        }
                        this.a.setVisibility(0);
                        this.a.d.requestFocus();
                    }
                } else if (this.a.m) {
                    this.a.m = false;
                    if (this.a.j.isShowing()) {
                        this.a.setVisibility(0);
                    }
                }
            }
        }
    };
    private boolean x = false;

    public interface a {
        void a(CharSequence charSequence);

        boolean a();

        void b();

        void b(CharSequence charSequence);
    }

    public void setFrom(int i) {
        if (i != 0) {
            this.o = i;
        }
    }

    public int getFrom() {
        return this.o;
    }

    public void setVisibility(int i) {
        if (i == 0) {
            this.v = true;
        } else {
            this.v = false;
        }
        super.setVisibility(i);
    }

    public ReplyView(Context context) {
        super(context);
        a(context, null, null);
    }

    public ReplyView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, null, null);
    }

    public ReplyView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, null, null);
    }

    public ReplyView(Context context, a aVar, c cVar) {
        super(context);
        a(context, aVar, cVar);
    }

    public ReplyView(Context context, a aVar) {
        super(context);
        a(context, aVar, null);
    }

    private void a(int i) {
        if ((i > 100 && i != k) || !this.x) {
            k = i;
            this.g.setLayoutParams(new LayoutParams(-1, k));
            this.x = true;
            c.e("ReplyView", "changeKeyboardHeight height = " + i);
        }
    }

    public void setParentLayout(ViewGroup viewGroup) {
        this.l = viewGroup;
        if (this.l != null) {
            this.l.getViewTreeObserver().addOnGlobalLayoutListener(this.w);
        }
    }

    public void setReplyActionListener(a aVar) {
        this.h = aVar;
    }

    public a getReplyListener() {
        return this.h;
    }

    private void a(Context context, a aVar, c cVar) {
        View systemEmoticonPanel;
        setOrientation(1);
        if (aVar != null) {
            this.h = aVar;
        }
        this.i = context;
        View inflate = LayoutInflater.from(context).inflate(R.layout.reply_ui, this, true);
        this.d = (EditText) inflate.findViewById(R.id.et_input);
        this.e = (Button) inflate.findViewById(R.id.btn_commit);
        this.f = (Button) inflate.findViewById(R.id.btn_emo_switch);
        this.g = (LinearLayout) inflate.findViewById(R.id.ll_container);
        this.g.setVisibility(8);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.e.setClickable(false);
        this.f.setOnClickListener(this);
        this.d.addTextChangedListener(this.t);
        this.d.setOnFocusChangeListener(this.s);
        if (cVar != null) {
            systemEmoticonPanel = new SystemEmoticonPanel(context, cVar);
        } else {
            systemEmoticonPanel = new SystemEmoticonPanel(context, this.u);
        }
        if (k <= 0) {
            k = (int) this.i.getResources().getDimension(R.dimen.keyboard_height);
        }
        a(k);
        this.j = new PopupWindow(systemEmoticonPanel, -1, k);
        this.j.setOnDismissListener(new OnDismissListener(this) {
            final /* synthetic */ ReplyView a;

            {
                this.a = r1;
            }

            public void onDismiss() {
                this.a.g.setVisibility(8);
            }
        });
        e();
    }

    public void setText(CharSequence charSequence) {
        this.d.setText(charSequence);
    }

    public CharSequence getText() {
        return this.d.getText();
    }

    public void setHint(CharSequence charSequence) {
        this.d.setHint(charSequence);
        this.d.requestFocus();
        ((InputMethodManager) this.i.getSystemService("input_method")).showSoftInput(this.d, 0);
    }

    public void setMaxLength(int i) {
        this.d.setFilters(new InputFilter[]{new LengthFilter(this.c)});
    }

    public void a() {
        if (this.l != null) {
            this.l.getViewTreeObserver().removeGlobalOnLayoutListener(this.w);
        }
        this.d.removeTextChangedListener(this.t);
        this.i = null;
        this.p = null;
        this.d.setOnFocusChangeListener(null);
        setVisibility(8);
    }

    public void b() {
        this.d.setText("");
        this.j.dismiss();
        this.f.setBackgroundResource(R.drawable.bookclub_emotion_gray);
    }

    public void c() {
        this.q = false;
    }

    public void setHasSendState(boolean z) {
        this.q = z;
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_input:
                e();
                this.f.setBackgroundResource(R.drawable.bookclub_emotion_gray);
                return;
            case R.id.btn_emo_switch:
                this.d.requestFocus();
                if (this.j.isShowing()) {
                    this.f.setBackgroundResource(R.drawable.bookclub_emotion_gray);
                    this.d.requestFocus();
                    ((InputMethodManager) this.i.getSystemService("input_method")).showSoftInput(this.d, 0);
                    this.j.dismiss();
                    return;
                }
                this.f.setBackgroundResource(R.drawable.bookclub_emotion_green);
                this.j.setHeight(k);
                if (this.m) {
                    this.g.setVisibility(8);
                } else {
                    this.g.setVisibility(0);
                }
                if (VERSION.SDK_INT < 21 || !aj.a(this.i)) {
                    this.j.showAtLocation(this.l, 80, 0, 0);
                    return;
                } else {
                    this.j.showAtLocation(this.l, 80, 0, com.qq.reader.common.c.a.cb);
                    return;
                }
            case R.id.btn_commit:
                if (g() && this.h != null && this.e.getText() != null && this.i.getString(R.string.reply_send).equals(this.e.getText().toString())) {
                    this.q = true;
                    if (getText() != null) {
                        this.h.b(getText());
                    }
                    ((InputMethodManager) this.i.getSystemService("input_method")).hideSoftInputFromWindow(this.d.getApplicationWindowToken(), 2);
                    return;
                }
                return;
            default:
                return;
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() != 0 || this.h == null || this.h.a()) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return true;
    }

    public void d() {
        if (this.d != null) {
            this.d.requestFocus();
        }
    }

    public void setHint(String str) {
        if (this.d != null) {
            this.d.setHint(str);
        }
    }

    private void f() {
        this.d.onKeyDown(67, new KeyEvent(0, 67));
    }

    public void e() {
        ((InputMethodManager) this.i.getSystemService("input_method")).showSoftInput(this.d, 0);
        this.j.dismiss();
        this.g.setVisibility(8);
    }

    public void setMask(ViewGroup viewGroup) {
        this.p = viewGroup;
    }

    private boolean g() {
        CharSequence text = this.d.getText();
        if (System.currentTimeMillis() - this.n < BuglyBroadcastRecevier.UPLOADLIMITED) {
            a(this.i.getString(R.string.posttopic_submit_warning_frequency_error));
            return false;
        } else if (text.toString().length() != 0) {
            return true;
        } else {
            a(this.i.getString(R.string.comment_empty));
            return false;
        }
    }

    private void a(String str) {
        Toast.makeText(this.i, str, 0).show();
    }

    public void getInputFocus() {
        this.d.requestFocus();
        e();
    }
}
