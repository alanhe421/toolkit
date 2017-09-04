package com.qq.reader.module.bookstore.qnative.card.impl;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.ReaderApplication;
import com.qq.reader.common.imageloader.c;
import com.qq.reader.common.monitor.i;
import com.qq.reader.module.bookstore.qnative.item.g;
import com.qq.reader.module.bookstore.qnative.item.p;
import com.qq.reader.module.bookstore.qnative.item.s;
import com.tencent.feedback.proguard.R;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookInfoType_3_For_Discount extends LinearLayout {
    private RelativeLayout a;
    private ImageView b;
    private ImageView c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private RelativeLayout h;
    private ImageView i;
    private ImageView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private RelativeLayout o;
    private ImageView p;
    private ImageView q;
    private TextView r;
    private TextView s;
    private TextView t;
    private TextView u;
    private boolean v = false;
    private a w;

    public interface a {
        void a();
    }

    public BookInfoType_3_For_Discount(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater.from(context).inflate(R.layout.localstore_card_bookinfo_type_3, this, true);
        a();
    }

    private void a() {
        this.a = (RelativeLayout) findViewById(R.id.bookinfo_book_1);
        this.b = (ImageView) findViewById(R.id.bookinfo_cover_1);
        this.c = (ImageView) findViewById(R.id.bookinfo_listen_1);
        this.d = (TextView) findViewById(R.id.bookinfo_name_1);
        this.e = (TextView) findViewById(R.id.bookinfo_author_1);
        this.f = (TextView) findViewById(R.id.bookinfo_price_1);
        this.g = (TextView) findViewById(R.id.bookinfo_free_1);
        this.h = (RelativeLayout) findViewById(R.id.bookinfo_book_2);
        this.i = (ImageView) findViewById(R.id.bookinfo_cover_2);
        this.j = (ImageView) findViewById(R.id.bookinfo_listen_2);
        this.k = (TextView) findViewById(R.id.bookinfo_name_2);
        this.l = (TextView) findViewById(R.id.bookinfo_author_2);
        this.m = (TextView) findViewById(R.id.bookinfo_price_2);
        this.n = (TextView) findViewById(R.id.bookinfo_free_2);
        this.o = (RelativeLayout) findViewById(R.id.bookinfo_book_3);
        this.p = (ImageView) findViewById(R.id.bookinfo_cover_3);
        this.q = (ImageView) findViewById(R.id.bookinfo_listen_3);
        this.r = (TextView) findViewById(R.id.bookinfo_name_3);
        this.s = (TextView) findViewById(R.id.bookinfo_author_3);
        this.t = (TextView) findViewById(R.id.bookinfo_price_3);
        this.u = (TextView) findViewById(R.id.bookinfo_free_3);
    }

    public void setBookInfoBasic(List<g> list) {
        TextView[] textViewArr = new TextView[]{this.d, this.k, this.r};
        ImageView[] imageViewArr = new ImageView[]{this.b, this.i, this.p};
        for (int i = 0; i < textViewArr.length; i++) {
            textViewArr[i].setVisibility(8);
            imageViewArr[i].setVisibility(8);
        }
        int i2 = 0;
        while (list != null && i2 < list.size() && i2 < textViewArr.length) {
            textViewArr[i2].setVisibility(0);
            imageViewArr[i2].setVisibility(0);
            c.a(getContext()).a(((g) list.get(i2)).f(), imageViewArr[i2], com.qq.reader.common.imageloader.a.a().j());
            textViewArr[i2].setText(((g) list.get(i2)).n());
            i2++;
        }
    }

    public void setBookInfo(List<g> list, boolean z) {
        setBookInfoBasic(list);
        if (z) {
            this.e.setVisibility(8);
            this.f.setVisibility(0);
            this.f.setText(((p) list.get(0)).k());
            this.f.getPaint().setFlags(16);
            this.f.getPaint().setAntiAlias(true);
            this.g.setVisibility(0);
            this.g.setText(((p) list.get(0)).a());
            this.l.setVisibility(8);
            this.m.setVisibility(0);
            this.m.setText(((p) list.get(1)).k());
            this.m.getPaint().setFlags(16);
            this.m.getPaint().setAntiAlias(true);
            this.n.setVisibility(0);
            this.n.setText(((p) list.get(1)).a());
            this.s.setVisibility(8);
            this.t.setVisibility(0);
            this.t.setText(((p) list.get(2)).k());
            this.t.getPaint().setFlags(16);
            this.t.getPaint().setAntiAlias(true);
            this.u.setVisibility(0);
            this.u.setText(((p) list.get(2)).a());
            return;
        }
        this.e.setVisibility(0);
        if (this.v) {
            this.e.setText(b(list, 0));
        } else {
            this.e.setText(a(list, 0));
        }
        this.f.setVisibility(8);
        this.g.setVisibility(8);
        this.l.setVisibility(0);
        if (this.v) {
            this.l.setText(b(list, 1));
        } else {
            this.l.setText(a(list, 1));
        }
        this.m.setVisibility(8);
        this.n.setVisibility(8);
        this.s.setVisibility(0);
        if (this.v) {
            this.s.setText(b(list, 2));
        } else {
            this.s.setText(a(list, 2));
        }
        this.t.setVisibility(8);
        this.u.setVisibility(8);
    }

    public void setBookInfoFlexible(List<g> list) {
        setBookInfoBasic(list);
        TextView[] textViewArr = new TextView[]{this.e, this.l, this.s};
        TextView[] textViewArr2 = new TextView[]{this.f, this.m, this.t};
        TextView[] textViewArr3 = new TextView[]{this.g, this.n, this.u};
        for (int i = 0; i < textViewArr.length; i++) {
            textViewArr[i].setVisibility(8);
            textViewArr2[i].setVisibility(8);
            textViewArr3[i].setVisibility(8);
        }
        int i2 = 0;
        while (list != null && i2 < list.size() && i2 < textViewArr.length) {
            if (((p) list.get(i2)).b() < 100) {
                textViewArr[i2].setVisibility(0);
                textViewArr2[i2].setVisibility(0);
                textViewArr3[i2].setVisibility(0);
                textViewArr[i2].setVisibility(8);
                textViewArr2[i2].setVisibility(0);
                textViewArr2[i2].setText(((p) list.get(i2)).k());
                textViewArr2[i2].getPaint().setFlags(16);
                textViewArr2[i2].getPaint().setAntiAlias(true);
                textViewArr3[i2].setVisibility(0);
                textViewArr3[i2].setText(((p) list.get(i2)).a());
            } else {
                textViewArr[i2].setVisibility(8);
                textViewArr2[i2].setVisibility(8);
                textViewArr3[i2].setVisibility(8);
            }
            i2++;
        }
    }

    public void setListenBook(boolean z) {
        this.v = z;
        if (this.v) {
            this.c.setVisibility(0);
            this.j.setVisibility(0);
            this.q.setVisibility(0);
        }
    }

    public void setReportListener(a aVar) {
        this.w = aVar;
    }

    private void b() {
        Map hashMap = new HashMap();
        hashMap.put(s.ORIGIN, String.valueOf(2331));
        i.a("event_A126", hashMap, ReaderApplication.getApplicationImp());
        if (this.w != null) {
            this.w.a();
        }
    }

    public void setBookOnClickListener(List<g> list, final com.qq.reader.module.bookstore.qnative.c.a aVar) {
        RelativeLayout[] relativeLayoutArr = new RelativeLayout[]{this.a, this.h, this.o};
        int i = 0;
        while (list != null && i < list.size() && i < relativeLayoutArr.length) {
            final g gVar = (g) list.get(i);
            if (this.v) {
                relativeLayoutArr[i].setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ BookInfoType_3_For_Discount c;

                    public void onClick(View view) {
                        this.c.b();
                        if (aVar != null) {
                            gVar.b(aVar);
                        }
                    }
                });
            } else {
                relativeLayoutArr[i].setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ BookInfoType_3_For_Discount c;

                    public void onClick(View view) {
                        this.c.b();
                        if (aVar != null) {
                            gVar.a(aVar);
                        }
                    }
                });
            }
            i++;
        }
    }

    public String a(List<g> list, int i) {
        if (i < list.size()) {
            return ((g) list.get(i)).q();
        }
        return null;
    }

    public String b(List<g> list, int i) {
        if (i < list.size()) {
            return ((g) list.get(i)).h();
        }
        return null;
    }
}
