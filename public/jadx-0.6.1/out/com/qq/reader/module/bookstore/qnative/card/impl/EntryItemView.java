package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.bookstore.qnative.c.a;
import com.qq.reader.module.bookstore.qnative.item.f;
import com.qq.reader.view.as;
import com.qq.reader.view.at;
import com.tencent.feedback.proguard.R;

public class EntryItemView extends RelativeLayout {
    private ImageView a;
    private TextView b;
    private View c;
    private a d;
    private f e;

    public EntryItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.entry_itme_layout, this, true);
        a();
    }

    private void a() {
        this.a = (ImageView) findViewById(R.id.entry_icon);
        this.b = (TextView) findViewById(R.id.entry_title);
        this.c = findViewById(R.id.entry_divider);
    }

    public void setEntryInfo(f fVar) {
        this.e = fVar;
        String str = "";
        switch (this.e.a()) {
            case 1:
                str = "event_C216";
                break;
            case 2:
                str = "event_C215";
                break;
        }
        if (!ao.s(str)) {
            i.a(str, null, ReaderApplication.getApplicationImp());
        }
        this.b.setText(this.e.c());
        c.a(getContext()).a(this.e.b(), this.a, com.qq.reader.common.imageloader.a.a().j());
    }

    public void a(boolean z) {
        this.c.setVisibility(z ? 0 : 8);
    }

    public void setOnClickListener(a aVar) {
        this.d = aVar;
        ((RelativeLayout) findViewById(R.id.click_layout)).setOnClickListener(new OnClickListener(this) {
            final /* synthetic */ EntryItemView a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                String str = "";
                switch (this.a.e.a()) {
                    case 1:
                        str = "event_C218";
                        break;
                    case 2:
                        str = "event_C217";
                        break;
                    case 3:
                        str = "event_C219";
                        break;
                    case 4:
                        at.b = "1";
                        as.E = "1";
                        str = "event_C220";
                        break;
                    case 5:
                        str = "event_C221";
                        break;
                    case 6:
                        str = "event_C222";
                        break;
                }
                if (!ao.s(str)) {
                    i.a(str, null, ReaderApplication.getApplicationImp());
                }
                try {
                    com.qq.reader.qurl.c.a(this.a.d.getFromActivity(), this.a.e.d());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
