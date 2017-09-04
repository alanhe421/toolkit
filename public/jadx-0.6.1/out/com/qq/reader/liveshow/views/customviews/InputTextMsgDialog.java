package com.qq.reader.liveshow.views.customviews;

import android.content.Context;
import android.graphics.Rect;
import android.os.Message;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.liveshow.a;
import com.qq.reader.liveshow.a.b;
import com.qq.reader.liveshow.a.d;
import com.qq.reader.liveshow.a.h;
import com.qq.reader.liveshow.b.l;
import com.qq.reader.liveshow.b.m;
import com.qq.reader.liveshow.b.n;
import com.qq.reader.liveshow.c.e;
import com.qq.reader.liveshow.c.g;
import com.qq.reader.liveshow.model.c;
import com.qq.reader.liveshow.model.im.viewdata.GiftItem;
import com.qq.reader.liveshow.utils.IMHelper;
import com.qq.reader.liveshow.utils.IMHelper.NotEnterIMException;
import com.qq.reader.liveshow.utils.SxbLog;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.TIMElem;
import com.tencent.TIMElemType;
import com.tencent.TIMMessage;
import com.tencent.TIMMessagePriority;
import com.tencent.TIMValueCallBack;
import com.tencent.imsdk.BaseConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class InputTextMsgDialog extends BaseDialog {
    private static final String c = InputTextMsgDialog.class.getSimpleName();
    private static int s;
    private Button a;
    private EditText b;
    private Context d;
    private g e;
    private e f;
    private InputMethodManager g;
    private int h = 0;
    private final String i = "[`~@#$%^&*()-_+=|{}':;,/.<>￥…（）—【】‘；：”“’。，、]";
    private Pattern j = Pattern.compile("[`~@#$%^&*()-_+=|{}':;,/.<>￥…（）—【】‘；：”“’。，、]");
    private boolean k = false;
    private TextView l;
    private String m;
    private String n;
    private int o;
    private String p = "书币";
    private LinearLayout q;
    private int r = 50;
    private OnFocusChangeListener t = new OnFocusChangeListener(this) {
        final /* synthetic */ InputTextMsgDialog a;

        {
            this.a = r1;
        }

        public void onFocusChange(View view, boolean z) {
            if (z) {
                this.a.q.setBackgroundResource(d.bookclub_input_bg_green);
            } else {
                this.a.q.setBackgroundResource(d.bookclub_input_bg_gray);
            }
            if (view.getId() == a.e.input_message && !z) {
                this.a.dismiss();
            }
        }
    };
    private TextWatcher u = new TextWatcher(this) {
        final /* synthetic */ InputTextMsgDialog a;

        {
            this.a = r1;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (charSequence.toString().trim().length() > 0) {
                this.a.a.setTextColor(this.a.d.getResources().getColor(b.ok_btn_txt));
                this.a.a.setBackgroundResource(d.bookclub_reply_send_bg);
                this.a.a.setClickable(true);
                return;
            }
            this.a.a.setTextColor(this.a.d.getResources().getColor(b.hint_textcolor));
            this.a.a.setBackgroundResource(d.bookclub_reply_send_bg_disable);
            this.a.a.setClickable(false);
        }

        public void afterTextChanged(Editable editable) {
            if (editable.toString().trim().length() >= this.a.r) {
                m.a(this.a.d, h.tip_word_count_too_much, 1);
            }
        }
    };

    public InputTextMsgDialog(Context context, int i, g gVar, e eVar) {
        super(context, i);
        this.d = context;
        this.e = gVar;
        this.f = eVar;
        setContentView(a.g.input_text_dialog);
        this.q = (LinearLayout) findViewById(a.e.ll_input_area);
        this.m = this.d.getResources().getString(h.input_pre_normal_text);
        GiftItem v = com.qq.reader.liveshow.model.a.v();
        if (v != null) {
            this.o = v.mPrice;
            this.p = v.unit;
        } else {
            this.o = 10;
        }
        this.n = this.d.getResources().getString(h.input_pre_danmaku_text, new Object[]{Integer.valueOf(this.o)}) + this.p;
        this.b = (EditText) findViewById(a.e.input_message);
        getWindow().setSoftInputMode(16);
        this.b.addTextChangedListener(this.u);
        this.b.setOnFocusChangeListener(this.t);
        this.b.setFilters(new InputFilter[]{new LengthFilter(this.r)});
        this.a = (Button) findViewById(a.e.confrim_btn);
        this.g = (InputMethodManager) this.d.getSystemService("input_method");
        this.a.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ InputTextMsgDialog a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.d();
            }
        });
        this.a.setClickable(false);
        this.k = c.a().p();
        CharSequence o = c.a().o();
        if (!TextUtils.isEmpty(o)) {
            this.b.setText(o);
        }
        this.l = (TextView) findViewById(a.e.danmaku_btn);
        c();
        this.l.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ InputTextMsgDialog a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.k = !this.a.k;
                this.a.c();
                Map hashMap = new HashMap();
                hashMap.put(s.ORIGIN, this.a.k ? "1" : "2");
                l.a("event_Z16", hashMap, this.a.d.getApplicationContext());
            }
        });
        this.b.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ InputTextMsgDialog a;

            {
                this.a = r1;
            }

            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() != 1) {
                    return false;
                }
                switch (i) {
                    case 66:
                        return true;
                    default:
                        return false;
                }
            }
        });
        ((RelativeLayout) findViewById(a.e.rl_inputdlg_view)).getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener(this) {
            final /* synthetic */ InputTextMsgDialog a;

            {
                this.a = r1;
            }

            public void onGlobalLayout() {
                Rect rect = new Rect();
                this.a.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
                int a = InputTextMsgDialog.s - rect.bottom;
                if (a <= 0 && this.a.h > 0) {
                    this.a.g.hideSoftInputFromWindow(this.a.b.getWindowToken(), 0);
                    this.a.dismiss();
                }
                this.a.h = a;
            }
        });
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        s = displayMetrics.heightPixels;
        findViewById(a.e.input_click_layout).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ InputTextMsgDialog a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.dismiss();
            }
        });
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, this.k ? "1" : "2");
        l.a("event_Z11", hashMap, this.d.getApplicationContext());
    }

    public void setMessageText(String str) {
        this.b.setText(str);
        this.b.setSelection(str.length());
    }

    public void dismiss() {
        c.a().d(this.k);
        c.a().e(this.b.getText().toString());
        super.dismiss();
    }

    public void cancel() {
        super.cancel();
    }

    private void a(String str) {
        this.a.setClickable(false);
        Map hashMap = new HashMap();
        if (this.k) {
            hashMap.put(s.ORIGIN, "1");
            this.f.a(str, new com.qq.reader.liveshow.b.a<Boolean>(this) {
                final /* synthetic */ InputTextMsgDialog a;

                {
                    this.a = r1;
                }

                public void a(Boolean bool) {
                    if (bool.booleanValue()) {
                        this.a.e();
                    }
                    this.a.a.setClickable(true);
                }
            });
        } else {
            hashMap.put(s.ORIGIN, "2");
            try {
                IMHelper.a(-1, str, TIMElemType.Text, TIMMessagePriority.Normal, new TIMValueCallBack<TIMMessage>(this) {
                    final /* synthetic */ InputTextMsgDialog a;

                    {
                        this.a = r1;
                    }

                    public /* synthetic */ void onSuccess(Object obj) {
                        a((TIMMessage) obj);
                    }

                    public void onError(int i, String str) {
                        this.a.a.setClickable(true);
                        SxbLog.e(InputTextMsgDialog.c, "发送失败：code = " + i + "  msg = " + str);
                        switch (i) {
                            case BaseConstants.ERR_REQUEST_NO_NET_ONREQ /*6200*/:
                                m.a(this.a.d, h.network_unavailable, 0);
                                return;
                            case 10006:
                                m.a(this.a.d, h.toast_operation_toofrequent, 0);
                                return;
                            case 10017:
                                m.a(this.a.d, h.toast_shutup, 0);
                                return;
                            case 80001:
                                m.a(this.a.d, h.toast_dirtyword, 0);
                                return;
                            default:
                                m.a(this.a.d, h.error_happen_try_later, 0);
                                return;
                        }
                    }

                    public void a(TIMMessage tIMMessage) {
                        this.a.a.setClickable(true);
                        this.a.e();
                        TIMElem element = tIMMessage.getElement(0);
                        if (element != null) {
                            try {
                                com.qq.reader.liveshow.model.im.a.a.a a = IMHelper.a(element);
                                Message obtain = Message.obtain();
                                obtain.what = 1000;
                                obtain.obj = a;
                                obtain.arg1 = 1;
                                this.a.f.b(obtain);
                            } catch (Exception e) {
                                SxbLog.e(InputTextMsgDialog.c, e.getMessage());
                            }
                        }
                    }
                });
            } catch (NotEnterIMException e) {
                e.printStackTrace();
                try {
                    m.a(n.a().f(), "发送失败，请重新进入后重试", 0);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        l.a("event_Z12", hashMap, this.d.getApplicationContext());
    }

    public void show() {
        super.show();
        new Timer().schedule(new TimerTask(this) {
            final /* synthetic */ InputTextMsgDialog a;

            {
                this.a = r1;
            }

            public void run() {
                ((InputMethodManager) this.a.d.getSystemService("input_method")).showSoftInput(this.a.b, 0);
            }
        }, 500);
    }

    private void c() {
        if (this.k) {
            if (this.b.getText().toString().trim().length() > 0) {
                m.a(this.d, this.n, 1);
            }
            this.b.setHint(this.n);
            this.l.setBackgroundResource(d.live_audience_input_button_open);
            return;
        }
        this.b.setHint(this.m);
        this.l.setBackgroundResource(d.live_audience_input_button_close);
    }

    private void d() {
        if (this.b.getText().toString().trim().length() > 0) {
            a("" + this.b.getText());
        } else {
            m.a(this.d, h.msg_can_not_be_null, 1);
        }
    }

    private void e() {
        this.b.setText("");
    }
}
