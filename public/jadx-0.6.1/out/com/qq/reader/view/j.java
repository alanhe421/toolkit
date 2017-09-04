package com.qq.reader.view;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

/* compiled from: GuidePopupView */
public class j extends BaseDialog implements l {
    private GuideShadowView a;
    private Activity b;
    private OnDismissListener c;
    private b d;
    private TextView e;
    private TextView i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;
    private boolean o;
    private k p;

    /* compiled from: GuidePopupView */
    private class a implements OnDismissListener {
        final /* synthetic */ j a;

        private a(j jVar) {
            this.a = jVar;
        }

        public void onDismiss(DialogInterface dialogInterface) {
            if (this.a.c != null) {
                this.a.c.onDismiss(dialogInterface);
            }
            if (this.a.a != null) {
                ((ViewGroup) this.a.b.getWindow().getDecorView()).removeView(this.a.a);
                this.a.a = null;
            }
        }
    }

    /* compiled from: GuidePopupView */
    public interface b {
        void a();
    }

    public j(Activity activity, int i, boolean z) {
        this.b = activity;
        this.k = i;
        this.j = z;
        g();
        h();
    }

    public j(Activity activity) {
        this(activity, R.layout.new_common_tip, false);
    }

    private void g() {
        a(this.b, null, this.k, 0, false);
        this.e = (TextView) this.f.findViewById(16908308);
        this.i = (TextView) this.f.findViewById(16908309);
    }

    private void h() {
        this.f.setCanceledOnTouchOutside(this.j);
        this.f.setCancelable(false);
        this.f.getWindow().setWindowAnimations(R.style.Animation.alphaAnim);
        this.f.setOnDismissListener(new a());
        a(new OnClickListener(this) {
            final /* synthetic */ j a;

            {
                this.a = r1;
            }

            public void onClick(View view) {
                this.a.dismiss();
            }
        });
    }

    public void f_() {
        a(this.l, this.m, this.n, this.o);
    }

    public void a(CharSequence charSequence) {
        if (this.e != null) {
            this.e.setText(charSequence);
        }
    }

    public void b(int i) {
        this.f.findViewById(R.id.root).setBackgroundResource(i);
    }

    public void a(int i, int i2, int i3, boolean z) {
        LayoutParams attributes = this.f.getWindow().getAttributes();
        attributes.gravity = i;
        attributes.x = i2;
        attributes.y = i3;
        attributes.dimAmount = 0.0f;
        this.f.getWindow().setAttributes(attributes);
        this.f.show();
        a(z);
        if (this.d != null) {
            this.d.a();
        }
    }

    public void a(k kVar) {
        this.p = kVar;
    }

    public void a(boolean z) {
        if (z && this.a == null) {
            this.a = new GuideShadowView(this.b);
            this.a.setHighLightRect(this.p);
            ((ViewGroup) this.b.getWindow().getDecorView()).addView(this.a);
            this.a.requestLayout();
        }
    }

    public View a(int i) {
        return this.f.findViewById(i);
    }

    public boolean f() {
        return this.f.isShowing();
    }

    public void a(OnClickListener onClickListener) {
        if (this.i != null) {
            this.i.setOnClickListener(onClickListener);
        }
    }

    public void a(OnDismissListener onDismissListener) {
        this.c = onDismissListener;
    }

    public void dismiss(int i) {
    }

    public k getHighLightArea(int i) {
        return null;
    }

    public void f(boolean z) {
        this.o = z;
    }

    public void c(int i) {
        this.n = i;
    }

    public void d(int i) {
        this.m = i;
    }

    public void e(int i) {
        this.l = i;
    }
}
