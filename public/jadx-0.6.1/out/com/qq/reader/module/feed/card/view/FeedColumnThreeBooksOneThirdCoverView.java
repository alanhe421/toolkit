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
import com.qq.reader.module.feed.b.j;
import com.qq.reader.module.feed.b.k;
import com.qq.reader.module.feed.b.r;
import com.qq.reader.module.feed.c.b;
import com.qq.reader.module.feed.data.impl.a;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

public class FeedColumnThreeBooksOneThirdCoverView extends LinearLayout implements a {
    private TextView a;
    private TextView b;
    private ImageView c;
    private ImageView d;
    private ImageView e;
    private int f;
    private ArrayList<r> g;
    private String h;
    private ArrayList<String> i;
    private Activity j;
    private String k;
    private int l;
    private int m;
    private int n;

    public FeedColumnThreeBooksOneThirdCoverView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.feed_column_three_books_one_third_cover_view, this, true);
        a();
    }

    public FeedColumnThreeBooksOneThirdCoverView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.feed_column_three_books_one_third_cover_view, this, true);
        a();
    }

    private void a() {
        this.a = (TextView) findViewById(R.id.column_name);
        this.b = (TextView) findViewById(R.id.column_des);
        this.c = (ImageView) findViewById(R.id.column_cover_left);
        this.d = (ImageView) findViewById(R.id.column_cover_center);
        this.e = (ImageView) findViewById(R.id.column_cover_right);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedColumnThreeBooksOneThirdCoverView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.a.h) && this.a.j != null) {
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(this.a.h).append("?bids=").append((String) this.a.i.get(this.a.l)).append(",").append((String) this.a.i.get(this.a.m)).append(",").append((String) this.a.i.get(this.a.n));
                        c.a(this.a.j, stringBuilder.toString(), null);
                    } catch (Exception e) {
                    }
                    b.a(this.a.k);
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
            if (this.f == 4) {
                textView.setTextColor(Color.parseColor("#ff5959"));
            }
        }
    }

    public void a(com.qq.reader.module.feed.b.a aVar) {
        if (aVar != null) {
            this.f = aVar.a;
            this.k = aVar.b;
            if (this.f == 3 || this.f == 4) {
                r rVar;
                CharSequence charSequence;
                CharSequence charSequence2;
                if (this.f == 3) {
                    j jVar = (j) aVar;
                    if (jVar != null) {
                        this.g = jVar.a();
                        this.h = jVar.b();
                        this.i = jVar.c();
                        int d = jVar.d();
                        this.l = jVar.e();
                        this.m = jVar.f();
                        this.n = jVar.g();
                        if (this.g != null && d < this.g.size()) {
                            rVar = (r) this.g.get(d);
                            charSequence = rVar.d;
                            if (!TextUtils.isEmpty(charSequence)) {
                                this.a.setText(charSequence);
                                setTextBold(this.a);
                            }
                            charSequence2 = rVar.e;
                            if (!TextUtils.isEmpty(charSequence2)) {
                                this.b.setText(charSequence2);
                            }
                            if (this.i != null) {
                                if (this.l < this.i.size()) {
                                    com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) this.i.get(this.l)).longValue()), this.c, com.qq.reader.common.imageloader.a.a().j());
                                }
                                if (this.m < this.i.size()) {
                                    com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) this.i.get(this.m)).longValue()), this.d, com.qq.reader.common.imageloader.a.a().j());
                                }
                                if (this.n < this.i.size()) {
                                    com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) this.i.get(this.n)).longValue()), this.e, com.qq.reader.common.imageloader.a.a().j());
                                }
                            }
                        }
                    }
                }
                if (this.f == 4) {
                    k kVar = (k) aVar;
                    if (kVar != null) {
                        this.g = kVar.a();
                        this.h = kVar.b();
                        this.i = kVar.c();
                        int d2 = kVar.d();
                        this.l = kVar.e();
                        this.m = kVar.f();
                        this.n = kVar.g();
                        if (this.g != null && d2 < this.g.size()) {
                            rVar = (r) this.g.get(d2);
                            charSequence = rVar.d;
                            if (!TextUtils.isEmpty(charSequence)) {
                                this.a.setText(charSequence);
                                setTextBold(this.a);
                            }
                            charSequence2 = rVar.e;
                            if (!TextUtils.isEmpty(charSequence2)) {
                                this.b.setText(charSequence2);
                            }
                            if (this.i != null) {
                                if (this.l < this.i.size()) {
                                    com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) this.i.get(this.l)).longValue()), this.c, com.qq.reader.common.imageloader.a.a().j());
                                }
                                if (this.m < this.i.size()) {
                                    com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) this.i.get(this.m)).longValue()), this.d, com.qq.reader.common.imageloader.a.a().j());
                                }
                                if (this.n < this.i.size()) {
                                    com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) this.i.get(this.n)).longValue()), this.e, com.qq.reader.common.imageloader.a.a().j());
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
