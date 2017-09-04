package com.qq.reader.module.feed.card.view;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.feed.b.g;
import com.qq.reader.module.feed.b.h;
import com.qq.reader.module.feed.b.r;
import com.qq.reader.module.feed.c.b;
import com.qq.reader.module.feed.data.impl.a;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class FeedColumnPersonalityBooksOneHalfView extends LinearLayout implements a {
    private View a;
    private View b;
    private TextView c;
    private TextView d;
    private TextView e;
    private ImageView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private ImageView k;
    private ImageView l;
    private ImageView m;
    private Activity n;
    private com.qq.reader.module.bookstore.qnative.c.a o;
    private int p;
    private String q;
    private String r;
    private ArrayList<String> s;
    private int t;
    private int u;
    private int v;
    private final String[] w;

    public FeedColumnPersonalityBooksOneHalfView(Context context) {
        super(context);
        this.w = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        LayoutInflater.from(context).inflate(R.layout.feed_column_personality_books_view, this, true);
        a();
    }

    public FeedColumnPersonalityBooksOneHalfView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.w = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        LayoutInflater.from(context).inflate(R.layout.feed_column_personality_books_view, this, true);
        a();
    }

    public void a(com.qq.reader.module.feed.b.a aVar) {
        if (aVar != null) {
            this.p = aVar.a;
            this.q = aVar.b;
            if (this.p == 12) {
                a((g) aVar);
            }
            if (this.p == 13) {
                a((h) aVar);
            }
            setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ FeedColumnPersonalityBooksOneHalfView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (!TextUtils.isEmpty(this.a.r) && this.a.n != null) {
                        try {
                            if (this.a.p == 13) {
                                b.a(this.a.q);
                                StringBuilder stringBuilder = new StringBuilder();
                                stringBuilder.append(this.a.r);
                                if (this.a.s != null && this.a.s.size() > 0) {
                                    if (this.a.t < this.a.s.size()) {
                                        stringBuilder.append("&bids=").append((String) this.a.s.get(this.a.t));
                                    }
                                    if (this.a.u < this.a.s.size()) {
                                        stringBuilder.append(",").append((String) this.a.s.get(this.a.u));
                                    }
                                    if (this.a.v < this.a.s.size()) {
                                        stringBuilder.append(",").append((String) this.a.s.get(this.a.v));
                                    }
                                }
                                c.a(this.a.n, stringBuilder.toString(), null);
                            }
                            if (this.a.p == 12) {
                                if (com.qq.reader.common.login.c.b()) {
                                    c.a(this.a.n, this.a.r, null);
                                } else {
                                    Bundle bundle = new Bundle();
                                    bundle.putBoolean("feedGotoPersonalityBooks", true);
                                    bundle.putString("feedQurl", this.a.r);
                                    bundle.putBoolean("fromFeedAction", true);
                                    this.a.o.doFunction(bundle);
                                }
                            }
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
    }

    public void change() {
    }

    private void a() {
        this.b = findViewById(R.id.feed_column_personality_open_layout);
        this.a = findViewById(R.id.feed_column_personality_noopen_layout);
        this.c = (TextView) findViewById(R.id.column_noopen_name);
        this.d = (TextView) findViewById(R.id.column_noopen_des);
        this.e = (TextView) findViewById(R.id.column_noopen_date);
        this.f = (ImageView) findViewById(R.id.column_noopen_pic);
        this.g = (TextView) findViewById(R.id.column_open_name);
        this.h = (TextView) findViewById(R.id.column_open_des);
        this.i = (TextView) findViewById(R.id.column_open_month);
        this.j = (TextView) findViewById(R.id.column_open_day);
        this.k = (ImageView) findViewById(R.id.column_open_cover_left);
        this.l = (ImageView) findViewById(R.id.column_open_cover_center);
        this.m = (ImageView) findViewById(R.id.column_open_cover_right);
    }

    private void a(h hVar) {
        if (!(this.b == null || hVar == null)) {
            this.b.setVisibility(0);
            ArrayList a = hVar.a();
            int d = hVar.d();
            if (a != null && d < a.size()) {
                r rVar = (r) a.get(d);
                this.g.setText(rVar.d);
                setTextBold(this.g);
                this.h.setText(rVar.e);
            }
            this.s = hVar.c();
            this.t = hVar.e();
            this.u = hVar.f();
            this.v = hVar.g();
            if (this.s != null) {
                d = this.s.size();
                if (d > 0) {
                    if (this.t < d) {
                        com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) this.s.get(this.t)).longValue()), this.k, com.qq.reader.common.imageloader.a.a().j());
                    }
                    if (this.u < d) {
                        com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) this.s.get(this.u)).longValue()), this.l, com.qq.reader.common.imageloader.a.a().j());
                    }
                    if (this.v < d) {
                        com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf((String) this.s.get(this.v)).longValue()), this.m, com.qq.reader.common.imageloader.a.a().j());
                    }
                }
            }
            this.r = hVar.b();
            Object a2 = a(hVar.h());
            if (!TextUtils.isEmpty(a2)) {
                String[] split = a2.split("-");
                if (split.length == 2) {
                    String a3 = a(split[0]);
                    CharSequence a4 = a(split[1]);
                    this.i.setText(this.w[Integer.parseInt(a3) - 1]);
                    this.j.setText(a4);
                }
            }
        }
        if (this.a != null) {
            this.a.setVisibility(8);
        }
    }

    private void a(g gVar) {
        if (this.b != null) {
            this.b.setVisibility(8);
        }
        if (this.a != null && gVar != null) {
            this.a.setVisibility(0);
            this.c.setText(gVar.a());
            setTextBold(this.c);
            this.d.setText(gVar.b());
            this.r = gVar.c();
            int d = gVar.d();
            if (d == 1 || d == 3) {
                this.f.setImageDrawable(this.n.getResources().getDrawable(R.drawable.feed_column_personality_books_nologin_boy_background));
            } else {
                this.f.setImageDrawable(this.n.getResources().getDrawable(R.drawable.feed_column_personality_books_nologin_girl_background));
            }
            Object a = a(gVar.e());
            if (!TextUtils.isEmpty(a)) {
                String[] split = a.split("-");
                if (split.length == 2) {
                    this.e.setText(split[0] + "." + split[1]);
                }
            }
        }
    }

    public void a(Activity activity) {
        this.n = activity;
    }

    public void a(com.qq.reader.module.bookstore.qnative.c.a aVar) {
        this.o = aVar;
    }

    private String a(long j) {
        return new SimpleDateFormat("MM-dd").format(new Date(j));
    }

    private String a(String str) {
        if (str.startsWith("0")) {
            return str.substring(1, 2);
        }
        return str;
    }

    public void setTextBold(TextView textView) {
        if (textView != null) {
            textView.getPaint().setFakeBoldText(true);
        }
    }
}
