package com.qq.reader.module.feed.card.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.feed.b.d;
import com.qq.reader.module.feed.b.f;
import com.qq.reader.module.feed.b.r;
import com.qq.reader.module.feed.c.b;
import com.qq.reader.module.feed.data.impl.a;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

public class FeedColumnThreeBooksOneHalfCoverOneView extends LinearLayout implements a {
    private TextView a;
    private TextView b;
    private ImageView c;
    private ImageView d;
    private ImageView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private int i;
    private String j;
    private Activity k;
    private String l;
    private ArrayList<String> m;
    private int n;
    private int o;
    private int p;

    public FeedColumnThreeBooksOneHalfCoverOneView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.feed_column_three_books_one_half_cover_one_view, this, true);
        c();
    }

    public FeedColumnThreeBooksOneHalfCoverOneView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.feed_column_three_books_one_half_cover_one_view, this, true);
        c();
    }

    public void a(Activity activity) {
        this.k = activity;
    }

    private void c() {
        this.a = (TextView) findViewById(R.id.column_name);
        this.b = (TextView) findViewById(R.id.column_des);
        this.c = (ImageView) findViewById(R.id.column_cover_left);
        this.d = (ImageView) findViewById(R.id.column_cover_center);
        this.e = (ImageView) findViewById(R.id.column_cover_right);
        this.f = (TextView) findViewById(R.id.column_top_1_icon);
        this.g = (TextView) findViewById(R.id.column_top_2_icon);
        this.h = (TextView) findViewById(R.id.column_top_3_icon);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedColumnThreeBooksOneHalfCoverOneView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.a.j) && this.a.k != null) {
                    try {
                        if (this.a.i == 1) {
                            c.a(this.a.k, this.a.j, null);
                        }
                        if (this.a.i == 11) {
                            if (this.a.m == null || this.a.m.size() <= 0) {
                                c.a(this.a.k, this.a.j, null);
                            } else {
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(this.a.j).append("?bids=").append((String) this.a.m.get(this.a.n)).append(",").append((String) this.a.m.get(this.a.o)).append(",").append((String) this.a.m.get(this.a.p));
                                c.a(this.a.k, stringBuilder.toString(), null);
                            }
                        }
                    } catch (Exception e) {
                    }
                    b.a(this.a.l);
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
        }
    }

    public void a(com.qq.reader.module.feed.b.a aVar) {
        if (aVar != null) {
            CharSequence charSequence;
            this.i = aVar.a;
            this.l = aVar.b;
            if (this.i == 1) {
                d dVar = (d) aVar;
                if (dVar != null) {
                    ArrayList a = dVar.a();
                    int b = dVar.b();
                    if (a != null && b < a.size()) {
                        com.qq.reader.module.feed.b.b bVar = (com.qq.reader.module.feed.b.b) a.get(b);
                        charSequence = bVar.d;
                        if (!TextUtils.isEmpty(charSequence)) {
                            this.a.setText(charSequence);
                            setTextBold(this.a);
                        }
                        charSequence = bVar.e;
                        if (!TextUtils.isEmpty(charSequence)) {
                            this.b.setText(charSequence);
                        }
                        this.j = bVar.a;
                        a = bVar.b;
                        if (a != null && a.size() >= 3) {
                            com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) a.get(0)).longValue()), this.c, com.qq.reader.common.imageloader.a.a().j());
                            com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) a.get(1)).longValue()), this.d, com.qq.reader.common.imageloader.a.a().j());
                            com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) a.get(2)).longValue()), this.e, com.qq.reader.common.imageloader.a.a().j());
                        }
                        a();
                    }
                } else {
                    return;
                }
            }
            if (this.i == 11) {
                f fVar = (f) aVar;
                if (fVar != null) {
                    ArrayList a2 = fVar.a();
                    int d = fVar.d();
                    if (a2 != null && d < a2.size()) {
                        r rVar = (r) a2.get(d);
                        charSequence = rVar.d;
                        if (!TextUtils.isEmpty(charSequence)) {
                            this.a.setText(charSequence);
                            setTextBold(this.a);
                        }
                        CharSequence charSequence2 = rVar.e;
                        if (!TextUtils.isEmpty(charSequence2)) {
                            this.b.setText(charSequence2);
                        }
                        this.j = fVar.b();
                        this.m = fVar.c();
                        this.n = fVar.e();
                        this.o = fVar.f();
                        this.p = fVar.g();
                        if (this.m != null) {
                            d = this.m.size();
                            if (this.n < d) {
                                com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) this.m.get(this.n)).longValue()), this.c, com.qq.reader.common.imageloader.a.a().j());
                            }
                            if (this.o < d) {
                                com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) this.m.get(this.o)).longValue()), this.d, com.qq.reader.common.imageloader.a.a().j());
                            }
                            if (this.p < d) {
                                com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) this.m.get(this.p)).longValue()), this.e, com.qq.reader.common.imageloader.a.a().j());
                            }
                        }
                        b();
                    }
                }
            }
        }
    }

    public void a() {
        this.f.setVisibility(0);
        this.g.setVisibility(0);
        this.h.setVisibility(0);
    }

    public void b() {
        this.f.setVisibility(8);
        this.g.setVisibility(8);
        this.h.setVisibility(8);
    }
}
