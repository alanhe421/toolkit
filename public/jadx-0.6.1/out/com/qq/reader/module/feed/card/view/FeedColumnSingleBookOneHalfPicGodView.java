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
import com.qq.reader.module.feed.b.n;
import com.qq.reader.module.feed.c.b;
import com.qq.reader.module.feed.data.impl.a;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;

public class FeedColumnSingleBookOneHalfPicGodView extends LinearLayout implements a {
    private TextView a;
    private TextView b;
    private ImageView c;
    private int d;
    private ArrayList<c> e;
    private Activity f;
    private String g;
    private String h;

    public FeedColumnSingleBookOneHalfPicGodView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(R.layout.feed_column_single_book_one_half_cover_god_view, this, true);
        a();
    }

    public FeedColumnSingleBookOneHalfPicGodView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.feed_column_single_book_one_half_cover_god_view, this, true);
        a();
    }

    public void a(Activity activity) {
        this.f = activity;
    }

    private void a() {
        this.a = (TextView) findViewById(R.id.column_name);
        this.b = (TextView) findViewById(R.id.column_des);
        this.c = (ImageView) findViewById(R.id.column_cover);
        setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ FeedColumnSingleBookOneHalfPicGodView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                if (!TextUtils.isEmpty(this.a.g) && this.a.f != null) {
                    try {
                        com.qq.reader.qurl.c.a(this.a.f, this.a.g, null);
                    } catch (Exception e) {
                    }
                    b.a(this.a.h);
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
            this.h = aVar.b;
            if (this.d == 7) {
                n nVar = (n) aVar;
                if (nVar != null) {
                    this.e = nVar.a();
                    int b = nVar.b();
                    if (this.e != null && b < this.e.size()) {
                        c cVar = (c) this.e.get(b);
                        CharSequence charSequence = cVar.d;
                        if (!TextUtils.isEmpty(charSequence)) {
                            this.a.setText(charSequence);
                            setTextBold(this.a);
                        }
                        charSequence = cVar.e;
                        if (!TextUtils.isEmpty(charSequence)) {
                            this.b.setText(charSequence);
                        }
                        Object obj = cVar.c;
                        if (!TextUtils.isEmpty(obj)) {
                            com.qq.reader.common.imageloader.c.a(getContext()).a(obj, this.c, com.qq.reader.common.imageloader.a.a().n());
                        }
                        this.g = cVar.a;
                    }
                }
            }
        }
    }
}
