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
import com.qq.reader.module.feed.b.i;
import com.qq.reader.module.feed.b.r;
import com.qq.reader.module.feed.c.b;
import com.qq.reader.module.feed.data.impl.a;
import com.qq.reader.qurl.c;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

public class FeedColumnSingleBookOneHalfCoverView extends LinearLayout implements a {
    private TextView a;
    private TextView b;
    private TextView c;
    private ImageView d;
    private int e;
    private ArrayList<r> f;
    private String g;
    private String h;
    private ArrayList<String> i;
    private String j;
    private Activity k;
    private String l;

    public FeedColumnSingleBookOneHalfCoverView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.feed_column_single_book_one_half_cover_view, this, true);
        a();
    }

    public FeedColumnSingleBookOneHalfCoverView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.feed_column_single_book_one_half_cover_view, this, true);
        a();
    }

    private void a() {
        this.a = (TextView) findViewById(R.id.column_name);
        this.b = (TextView) findViewById(R.id.column_des);
        this.d = (ImageView) findViewById(R.id.column_cover);
        this.c = (TextView) findViewById(R.id.column_date);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedColumnSingleBookOneHalfCoverView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.a.h) && this.a.k != null) {
                    try {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(this.a.h).append("?").append("bids=").append(this.a.j);
                        c.a(this.a.k, stringBuilder.toString(), null);
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
            this.e = aVar.a;
            this.l = aVar.b;
            if (this.e == 2) {
                i iVar = (i) aVar;
                if (iVar != null) {
                    this.f = iVar.a();
                    this.h = iVar.b();
                    this.g = iVar.c();
                    this.i = iVar.d();
                    int e = iVar.e();
                    int f = iVar.f();
                    if (this.f != null && e < this.f.size()) {
                        r rVar = (r) this.f.get(e);
                        CharSequence charSequence = rVar.d;
                        if (!TextUtils.isEmpty(charSequence)) {
                            this.a.setText(charSequence);
                            setTextBold(this.a);
                        }
                        CharSequence charSequence2 = rVar.e;
                        if (!TextUtils.isEmpty(charSequence2)) {
                            this.b.setText(charSequence2);
                        }
                    }
                    if (!TextUtils.isEmpty(this.g)) {
                        this.c.setText(this.g);
                    }
                    if (this.i != null && f < this.i.size()) {
                        this.j = (String) this.i.get(f);
                        com.qq.reader.common.imageloader.c.a(getContext()).a(ao.g(Long.valueOf(this.j).longValue()), this.d, com.qq.reader.common.imageloader.a.a().j());
                    }
                }
            }
        }
    }
}
