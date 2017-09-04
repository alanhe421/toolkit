package com.qq.reader.module.feed.card.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.feed.b.o;
import com.qq.reader.module.feed.b.p;
import com.qq.reader.module.feed.b.r;
import com.qq.reader.module.feed.c.b;
import com.qq.reader.module.feed.data.impl.a;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

public class FeedColumnSingleBookOneFourthView extends LinearLayout implements a {
    private TextView a;
    private TextView b;
    private ImageView c;
    private int d;
    private String e;
    private ArrayList<r> f;
    private String g;
    private ArrayList<String> h;
    private int i;
    private Activity j;

    public FeedColumnSingleBookOneFourthView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.feed_column_single_book_one_fourth_view, this, true);
        a();
    }

    public FeedColumnSingleBookOneFourthView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.feed_column_single_book_one_fourth_view, this, true);
        a();
    }

    public void a(Activity activity) {
        this.j = activity;
    }

    private void a() {
        this.a = (TextView) findViewById(R.id.column_name);
        this.b = (TextView) findViewById(R.id.column_des);
        this.c = (ImageView) findViewById(R.id.column_cover);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedColumnSingleBookOneFourthView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.a.g) && this.a.j != null) {
                    b.a(this.a.e);
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(this.a.g).append("?bids=").append((String) this.a.h.get(this.a.i));
                        c.a(this.a.j, stringBuilder.toString(), null);
                    } catch (Exception e) {
                    }
                    this.a.setSelected(true);
                    this.a.postDelayed(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.setSelected(false);
                        }
                    }, 100);
                }
            }
        });
    }

    public void change() {
    }

    public void setTextBold(TextView textView) {
        if (textView != null) {
            textView.getPaint().setFakeBoldText(true);
            if (this.d == 8) {
                textView.setTextColor(Color.parseColor("#ff5959"));
            }
        }
    }

    public void a(com.qq.reader.module.feed.b.a aVar) {
        if (aVar != null) {
            this.d = aVar.a;
            this.e = aVar.b;
            if (this.d == 8 || this.d == 9) {
                r rVar;
                CharSequence charSequence;
                CharSequence charSequence2;
                if (this.d == 8) {
                    o oVar = (o) aVar;
                    if (oVar != null) {
                        this.f = oVar.a();
                        this.g = oVar.b();
                        this.h = oVar.c();
                        int d = oVar.d();
                        this.i = oVar.e();
                        if (this.f != null && d < this.f.size()) {
                            rVar = (r) this.f.get(d);
                            charSequence = rVar.d;
                            if (!TextUtils.isEmpty(charSequence)) {
                                this.a.setText(charSequence);
                                setTextBold(this.a);
                            }
                            charSequence2 = rVar.e;
                            if (!TextUtils.isEmpty(charSequence2)) {
                                this.b.setText(charSequence2);
                            }
                        }
                        if (this.h != null && this.i < this.h.size()) {
                            com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) this.h.get(this.i)).longValue()), this.c, com.qq.reader.common.imageloader.a.a().j());
                        }
                    }
                }
                if (this.d == 9) {
                    p pVar = (p) aVar;
                    if (pVar != null) {
                        this.f = pVar.a();
                        this.g = pVar.b();
                        this.h = pVar.c();
                        int d2 = pVar.d();
                        this.i = pVar.e();
                        if (this.f != null && d2 < this.f.size()) {
                            rVar = (r) this.f.get(d2);
                            charSequence = rVar.d;
                            if (!TextUtils.isEmpty(charSequence)) {
                                this.a.setText(charSequence);
                                setTextBold(this.a);
                            }
                            charSequence2 = rVar.e;
                            if (!TextUtils.isEmpty(charSequence2)) {
                                this.b.setText(charSequence2);
                            }
                        }
                        if (this.h != null && this.i < this.h.size()) {
                            com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) this.h.get(this.i)).longValue()), this.c, com.qq.reader.common.imageloader.a.a().j());
                        }
                    }
                }
            }
        }
    }
}
