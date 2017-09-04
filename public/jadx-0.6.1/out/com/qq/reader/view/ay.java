package com.qq.reader.view;

import android.app.Activity;
import android.os.Handler;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.qq.reader.appconfig.a.d;
import com.tencent.feedback.proguard.R;

@Deprecated
/* compiled from: ZoomDialog */
public class ay extends BaseDialog {
    Object a = new Object();
    public int b = 0;
    public int c = 3;
    public int d = 6;
    private Button e;
    private Button i;
    private a j;
    private TextView k;
    private RelativeLayout l;
    private TextView m;
    private RadioButton n;
    private RadioButton o;
    private RadioButton p;
    private int q = 2;
    private final int r = 0;
    private final int s = 1;
    private final int t = 2;
    private Handler u = new 8(this);

    public ay(Activity activity) {
        this.q = activity.getResources().getDimensionPixelSize(R.dimen.zoom_font_step_size);
        if (this.f == null) {
            a(activity, null, R.layout.zoomdialog, true, false, true);
            this.b = e().getResources().getDimensionPixelSize(R.dimen.line_space_small);
            this.c = e().getResources().getDimensionPixelSize(R.dimen.line_space_normal);
            this.d = e().getResources().getDimensionPixelSize(R.dimen.line_space_big);
            this.l = (RelativeLayout) this.f.findViewById(R.id.choose_font_rl_view);
            this.m = (TextView) this.f.findViewById(R.id.choose_font_tv);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("当前字体：");
            stringBuffer.append(d.p(activity.getApplicationContext()));
            this.m.setText(stringBuffer.toString());
            this.l.setOnClickListener(new 1(this));
            this.n = (RadioButton) this.f.findViewById(R.id.choose_linespace_tv_1);
            this.o = (RadioButton) this.f.findViewById(R.id.choose_linespace_tv_2);
            this.p = (RadioButton) this.f.findViewById(R.id.choose_linespace_tv_3);
            int q = d.q(activity.getApplicationContext());
            if (this.b == q) {
                this.p.setChecked(true);
            } else if (this.c == q) {
                this.o.setChecked(true);
            } else {
                this.n.setChecked(true);
            }
            this.n.setOnCheckedChangeListener(new 2(this, activity));
            this.o.setOnCheckedChangeListener(new 3(this, activity));
            this.p.setOnCheckedChangeListener(new 4(this, activity));
            this.e = (Button) this.f.findViewById(R.id.zoominButton);
            this.i = (Button) this.f.findViewById(R.id.zoomoutButton);
            this.e.setOnClickListener(new 5(this));
            this.i.setOnClickListener(new 6(this));
            this.k = (TextView) this.f.findViewById(R.id.zoominfotext);
            RelativeLayout relativeLayout = (RelativeLayout) this.f.findViewById(R.id.zoominfotextpart);
            relativeLayout.setClickable(true);
            relativeLayout.setOnClickListener(new 7(this));
        }
    }

    public void a(Activity activity) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("当前字体：");
        stringBuffer.append(d.p(activity.getApplicationContext()));
        this.m.setText(stringBuffer.toString());
    }

    public int a(float f) {
        if (c(f) > d.J(e())) {
            d.c(e(), c(f));
            return 0;
        } else if (c(f) != d.J(e())) {
            return 2;
        } else {
            d.c(e(), c(f));
            return 1;
        }
    }

    public int b(float f) {
        if (d(f) < d.K(e())) {
            d.c(e(), d(f));
            return 0;
        } else if (d(f) != d.K(e())) {
            return 2;
        } else {
            d.c(e(), d(f));
            return 1;
        }
    }

    public float c(float f) {
        return f - ((float) this.q);
    }

    public float d(float f) {
        return ((float) this.q) + f;
    }

    public void a(a aVar) {
        this.j = aVar;
    }

    public a g() {
        return this.j;
    }

    public void f_() {
        int I = (int) d.I(e());
        i();
        if (((float) I) <= d.J(e())) {
            this.e.setEnabled(false);
        } else if (((float) I) >= d.K(e())) {
            this.i.setEnabled(false);
        }
        h();
        c().a((int) R.id.zoom_panel);
        super.f_();
    }

    private void h() {
    }

    private final void i() {
        this.u.sendEmptyMessage(60002);
    }
}
