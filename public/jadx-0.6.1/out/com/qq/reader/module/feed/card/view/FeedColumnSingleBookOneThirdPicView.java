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
import com.qq.reader.module.feed.b.c;
import com.qq.reader.module.feed.b.l;
import com.qq.reader.module.feed.b.m;
import com.qq.reader.module.feed.b.q;
import com.qq.reader.module.feed.c.b;
import com.qq.reader.module.feed.data.impl.a;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

public class FeedColumnSingleBookOneThirdPicView extends LinearLayout implements a {
    private TextView a;
    private TextView b;
    private ImageView c;
    private int d;
    private ArrayList<q> e;
    private String f;
    private ArrayList<c> g;
    private Activity h;
    private String i;

    public FeedColumnSingleBookOneThirdPicView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.feed_column_single_book_one_third_pic_view, this, true);
        a();
    }

    public FeedColumnSingleBookOneThirdPicView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.feed_column_single_book_one_third_pic_view, this, true);
        a();
    }

    private void a() {
        this.a = (TextView) findViewById(R.id.column_name);
        this.b = (TextView) findViewById(R.id.column_des);
        this.c = (ImageView) findViewById(R.id.column_pic);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedColumnSingleBookOneThirdPicView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.a.f) && this.a.h != null) {
                    try {
                        com.qq.reader.qurl.c.a(this.a.h, this.a.f, null);
                    } catch (Exception e) {
                    }
                    b.a(this.a.i);
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
            this.d = aVar.a;
            this.i = aVar.b;
            if (this.d == 5 || this.d == 6) {
                int c;
                CharSequence charSequence;
                if (this.d == 5) {
                    l lVar = (l) aVar;
                    if (lVar != null) {
                        this.e = lVar.a();
                        this.f = lVar.b();
                        c = lVar.c();
                        if (this.e != null && c < this.e.size()) {
                            q qVar = (q) this.e.get(c);
                            charSequence = qVar.d;
                            if (!TextUtils.isEmpty(charSequence)) {
                                this.a.setText(charSequence);
                                setTextBold(this.a);
                            }
                            charSequence = qVar.e;
                            if (!TextUtils.isEmpty(charSequence)) {
                                this.b.setText(charSequence);
                            }
                            Object obj = qVar.c;
                            if (!TextUtils.isEmpty(obj)) {
                                com.qq.reader.common.imageloader.c.a(getContext()).a(obj, this.c, com.qq.reader.common.imageloader.a.a().n());
                            }
                        }
                    } else {
                        return;
                    }
                }
                if (this.d == 6) {
                    m mVar = (m) aVar;
                    if (mVar != null) {
                        this.g = mVar.a();
                        c = mVar.b();
                        if (this.g != null && c < this.g.size()) {
                            c cVar = (c) this.g.get(c);
                            charSequence = cVar.d;
                            if (!TextUtils.isEmpty(charSequence)) {
                                this.a.setText(charSequence);
                                setTextBold(this.a);
                            }
                            charSequence = cVar.e;
                            if (!TextUtils.isEmpty(charSequence)) {
                                this.b.setText(charSequence);
                            }
                            Object obj2 = cVar.c;
                            if (!TextUtils.isEmpty(obj2)) {
                                com.qq.reader.common.imageloader.c.a(getContext()).a(obj2, this.c, com.qq.reader.common.imageloader.a.a().n());
                            }
                            this.f = cVar.a;
                        }
                    }
                }
            }
        }
    }
}
