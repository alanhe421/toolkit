package com.qq.reader.view;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.qq.reader.common.utils.t;
import com.tencent.feedback.proguard.R;

public class ProgressDialogMe extends Dialog {
    private ProgressBar a;
    private TextView b;
    private int c;
    private TextView d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private Drawable j;
    private Drawable k;
    private CharSequence l;
    private CharSequence m;
    private boolean n;
    private boolean o;
    private Handler p;
    private t q;

    protected void onCreate(Bundle bundle) {
        this.q = new t(this, true);
        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.progress_dialog, null);
        this.a = (ProgressBar) inflate.findViewById(R.id.custom_progress_dialog_progressbar);
        this.b = (TextView) inflate.findViewById(R.id.custom_progress_dialog_loading_text);
        this.d = (TextView) inflate.findViewById(R.id.comm_progress_dialog_title);
        setContentView(inflate);
        if (this.e > 0) {
            c(this.e);
        }
        if (this.f > 0) {
            a(this.f);
        }
        if (this.g > 0) {
            b(this.g);
        }
        if (this.h > 0) {
            d(this.h);
        }
        if (this.i > 0) {
            e(this.i);
        }
        if (this.j != null) {
            a(this.j);
        }
        if (this.k != null) {
            b(this.k);
        }
        if (this.l != null) {
            a(this.l);
        }
        if (this.m != null) {
            setTitle(this.m);
        }
        a(this.n);
        a();
        super.onCreate(bundle);
    }

    public void onStart() {
        super.onStart();
        this.o = true;
    }

    protected void onStop() {
        super.onStop();
        this.o = false;
    }

    public void a(int i) {
        if (this.o) {
            this.a.setProgress(i);
            a();
            return;
        }
        this.f = i;
    }

    public void b(int i) {
        if (this.a != null) {
            this.a.setSecondaryProgress(i);
            a();
            return;
        }
        this.g = i;
    }

    public void c(int i) {
        if (this.a != null) {
            this.a.setMax(i);
            a();
            return;
        }
        this.e = i;
    }

    public void d(int i) {
        if (this.a != null) {
            this.a.incrementProgressBy(i);
            a();
            return;
        }
        this.h += i;
    }

    public void e(int i) {
        if (this.a != null) {
            this.a.incrementSecondaryProgressBy(i);
            a();
            return;
        }
        this.i += i;
    }

    public void a(Drawable drawable) {
        if (this.a != null) {
            this.a.setProgressDrawable(drawable);
        } else {
            this.j = drawable;
        }
    }

    public void b(Drawable drawable) {
        if (this.a != null) {
            this.a.setIndeterminateDrawable(drawable);
        } else {
            this.k = drawable;
        }
    }

    public void a(boolean z) {
        if (this.a != null) {
            this.a.setIndeterminate(z);
        } else {
            this.n = z;
        }
    }

    public void a(CharSequence charSequence) {
        if (this.b != null) {
            this.b.setText(charSequence);
        } else {
            this.l = charSequence;
        }
    }

    private void a() {
        if (this.c == 1) {
            this.p.sendEmptyMessage(0);
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (this.d == null) {
            this.m = charSequence;
        } else if (charSequence == null || charSequence.equals("")) {
            this.d.setVisibility(8);
        } else {
            this.d.setText(charSequence);
        }
    }
}
