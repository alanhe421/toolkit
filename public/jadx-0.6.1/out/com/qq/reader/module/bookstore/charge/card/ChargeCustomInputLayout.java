package com.qq.reader.module.bookstore.charge.card;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.monitor.debug.c;
import com.qq.reader.module.bookstore.charge.a;
import com.qq.reader.module.question.card.AudioQuestionQuizCard;
import com.qq.reader.view.af;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

public class ChargeCustomInputLayout extends LinearLayout {
    TextWatcher a;
    private final String b = "ChargeCustomInputCard";
    private double c;
    private List<a> d = new ArrayList();
    private int e;
    private String f;
    private final int g = AudioQuestionQuizCard.MAX_BOOKCOIN_COUNT;
    private String h = "";
    private Context i;
    private EditText j;

    public ChargeCustomInputLayout(Context context) {
        super(context);
        a(context);
    }

    public ChargeCustomInputLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public ChargeCustomInputLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public ChargeCustomInputLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context);
    }

    public void a(Context context) {
        this.i = context;
        this.h = ReaderApplication.getApplicationImp().getString(R.string.charge_gift_bookcoin);
        LayoutInflater.from(context).inflate(R.layout.chargecustomitem_layout, this, true);
        a();
    }

    public void setChargeItemList(List<a> list) {
        this.d.clear();
        if (list != null) {
            this.d.addAll(list);
        }
    }

    public void setUnitPrice(int i) {
        this.c = (double) i;
    }

    public void a() {
        this.j = (EditText) findViewById(R.id.charge_input);
        final TextView textView = (TextView) findViewById(R.id.charge_price);
        final TextView textView2 = (TextView) findViewById(R.id.charge_info);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.charge_price_container);
        if (this.a == null) {
            this.a = new TextWatcher(this) {
                String a = "";
                String b = "";
                int c;
                String d;
                final /* synthetic */ ChargeCustomInputLayout g;

                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                public void afterTextChanged(Editable editable) {
                    this.g.f = editable.toString().trim();
                    if (!this.a.equals(this.g.f)) {
                        this.a = this.g.f;
                        try {
                            if (this.g.f.contains(this.g.h)) {
                                this.b = this.g.h;
                            } else {
                                this.b = "";
                            }
                            this.c = this.g.f.length() - this.b.length();
                            this.g.e = Integer.valueOf(this.g.f.substring(0, this.c)).intValue();
                        } catch (Exception e) {
                            c.e("ChargeCustomInputCard", e.getMessage());
                            this.g.e = 0;
                            this.c = 0;
                        }
                        this.g.j.requestFocus();
                        this.g.j.setSelection(this.c);
                        if (this.g.e == 0) {
                            this.g.j.setText("");
                        } else {
                            if (this.g.e > AudioQuestionQuizCard.MAX_BOOKCOIN_COUNT) {
                                af.a(((com.qq.reader.module.bookstore.qnative.c.a) this.g.i).getFromActivity(), ((com.qq.reader.module.bookstore.qnative.c.a) this.g.i).getFromActivity().getString(R.string.max_coin_toast_text), 0).a();
                                this.g.e = AudioQuestionQuizCard.MAX_BOOKCOIN_COUNT;
                            }
                            this.d = this.g.e + this.g.h;
                            if (!this.d.equals(this.g.f)) {
                                this.g.j.setText(this.d);
                            }
                        }
                        this.g.a(this.g.e, textView, textView2);
                    }
                }
            };
        } else {
            this.j.removeTextChangedListener(this.a);
        }
        this.j.addTextChangedListener(this.a);
        viewGroup.setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ChargeCustomInputLayout a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("charge_action", "charge_action_charge");
                bundle.putInt("chargenum", this.a.e);
                ((com.qq.reader.module.bookstore.qnative.c.a) this.a.i).doFunction(bundle);
            }
        });
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ ChargeCustomInputLayout a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (this.a.j.requestFocus()) {
                    this.a.j.postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass3 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            InputMethodManager inputMethodManager = (InputMethodManager) this.a.a.i.getSystemService("input_method");
                            if (inputMethodManager != null) {
                                inputMethodManager.showSoftInput(this.a.a.j, 1);
                            }
                        }
                    }, 200);
                }
            }
        });
    }

    private void a(int i, TextView textView, TextView textView2) {
        if (i > 0) {
            textView.setText(b(i));
        } else {
            textView.setText("0");
        }
        CharSequence a = a(i);
        if (TextUtils.isEmpty(a)) {
            textView2.setVisibility(8);
            return;
        }
        textView2.setVisibility(0);
        textView2.setText(a);
    }

    private String a(int i) {
        String str = null;
        if (this.d != null) {
            for (a aVar : this.d) {
                String str2;
                if (i < aVar.c() || TextUtils.isEmpty(aVar.d())) {
                    str2 = str;
                } else {
                    str2 = aVar.d();
                }
                str = str2;
            }
        }
        return str;
    }

    private String b(int i) {
        if (this.c == 0.0d) {
            this.c = 100.0d;
        }
        if (((double) i) % this.c == 0.0d) {
            return String.valueOf((int) (((double) i) / this.c));
        }
        return String.valueOf(((double) i) / this.c);
    }

    public void setInputCount(int i) {
        this.j.setText(String.valueOf(i));
    }
}
