package com.qq.reader.common.charge.voucher;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;
import com.qq.reader.common.charge.voucher.a.b;
import com.qq.reader.common.charge.voucher.a.c;
import com.tencent.feedback.proguard.R;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ReaderVoucherDialogHelper */
public class a {
    private Activity a;
    private b b;
    private View c;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    private TextView l;
    private TextView m;
    private TextView n;
    private TextView o;
    private TextView p;
    private TextView q;
    private View r;
    private View s;
    private View t;
    private View u;
    private View v;
    private View w;
    private View x;
    private TextView y;
    private ScrollView z;

    public a(Activity activity) {
        a(activity);
    }

    private void a(Activity activity) {
        this.a = activity;
        this.c = View.inflate(activity, R.layout.voucher_detail_dialog_layout, null);
        this.f = (TextView) this.c.findViewById(R.id.voucher_available_a);
        this.g = (TextView) this.c.findViewById(R.id.voucher_available_b);
        this.h = (TextView) this.c.findViewById(R.id.voucher_available_c);
        this.i = (TextView) this.c.findViewById(R.id.voucher_available_d);
        this.j = (TextView) this.c.findViewById(R.id.voucher_limit_a);
        this.k = (TextView) this.c.findViewById(R.id.voucher_limit_b);
        this.l = (TextView) this.c.findViewById(R.id.voucher_limit_c);
        this.m = (TextView) this.c.findViewById(R.id.voucher_limit_d);
        this.n = (TextView) this.c.findViewById(R.id.tv_left_class_a);
        this.o = (TextView) this.c.findViewById(R.id.tv_left_class_b);
        this.p = (TextView) this.c.findViewById(R.id.tv_left_class_c);
        this.q = (TextView) this.c.findViewById(R.id.tv_left_class_d);
        this.r = this.c.findViewById(R.id.voucher_container_a);
        this.s = this.c.findViewById(R.id.voucher_container_b);
        this.t = this.c.findViewById(R.id.voucher_container_c);
        this.u = this.c.findViewById(R.id.voucher_container_d);
        this.v = this.c.findViewById(R.id.line_a);
        this.w = this.c.findViewById(R.id.line_b);
        this.x = this.c.findViewById(R.id.line_c);
        this.d = (TextView) this.c.findViewById(R.id.current_available);
        this.e = (TextView) this.c.findViewById(R.id.tv_voucher_comment);
        this.z = (ScrollView) this.c.findViewById(R.id.scroll);
        this.y = (TextView) this.c.findViewById(R.id.bottom_area);
    }

    public Context a() {
        return this.a;
    }

    public void a(b bVar) {
        this.b = bVar;
        this.d.setText(b.a(bVar.b + "", String.format(a().getResources().getString(R.string.voucher_dialog_current_available_count), new Object[]{r0}), b().getResources().getDimensionPixelSize(R.dimen.text_size_class_4), b().getResources().getDimensionPixelSize(R.dimen.text_size_class_5), a().getResources().getColor(R.color.text_color_c101), a().getResources().getColor(R.color.text_color_c301)));
        this.e.setText(bVar.a);
        List list = bVar.e;
        if (list == null) {
            list = new ArrayList();
        }
        if (list.size() > 4) {
            list.subList(4, list.size()).clear();
        }
        a(list.size());
        switch (list.size()) {
            case 0:
                this.v.setVisibility(8);
                this.w.setVisibility(8);
                this.x.setVisibility(8);
                this.u.setVisibility(8);
                this.r.setVisibility(8);
                this.s.setVisibility(8);
                this.t.setVisibility(8);
                break;
            case 1:
                a((c) bVar.e.get(0), this.f, this.j, this.n);
                this.s.setVisibility(8);
                this.t.setVisibility(8);
                this.u.setVisibility(8);
                this.v.setVisibility(8);
                this.w.setVisibility(8);
                this.x.setVisibility(8);
                break;
            case 2:
                a((c) bVar.e.get(0), this.f, this.j, this.n);
                a((c) bVar.e.get(1), this.g, this.k, this.o);
                this.t.setVisibility(8);
                this.u.setVisibility(8);
                this.w.setVisibility(8);
                this.x.setVisibility(8);
                break;
            case 3:
                a((c) bVar.e.get(0), this.f, this.j, this.n);
                a((c) bVar.e.get(1), this.g, this.k, this.o);
                a((c) bVar.e.get(2), this.h, this.l, this.p);
                this.u.setVisibility(8);
                this.x.setVisibility(8);
                break;
            case 4:
                a((c) bVar.e.get(0), this.f, this.j, this.n);
                a((c) bVar.e.get(1), this.g, this.k, this.o);
                a((c) bVar.e.get(2), this.h, this.l, this.p);
                a((c) bVar.e.get(3), this.i, this.m, this.q);
                break;
        }
        if (bVar.d > 0) {
            this.y.setText(String.format(a().getResources().getString(R.string.voucher_dialog_other_available_count), new Object[]{Integer.valueOf(bVar.d)}));
            return;
        }
        this.y.setText(b().getResources().getString(R.string.voucher_dialog_other_available_none));
    }

    private void a(int i) {
        this.z.getLayoutParams().height = (int) Math.min(a().getResources().getDimension(R.dimen.voucher_dialog_scroller_max_height), (float) ((a().getResources().getDimensionPixelSize(R.dimen.common_dp_60) * i) + (1 * (i - 1))));
        this.z.requestLayout();
    }

    private void a(c cVar, TextView textView, TextView textView2, TextView textView3) {
        textView.setText(b.a(this.a, (int) cVar.e()));
        textView2.setText(b.a(this.a, cVar.a()));
        textView3.setText(cVar.b());
    }

    public View b() {
        return this.c;
    }
}
