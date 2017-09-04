package com.qq.reader.view;

import android.app.Activity;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.drawable.Drawable;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

/* compiled from: BlueCircleBlackBGDialog */
public class c extends BaseDialog {
    private TextView a;
    private ProgressBar b;

    public c(Activity activity) {
        if (this.f == null) {
            a(activity, null, R.layout.login_loading_dialog, 0, false, false, true);
            c().a((int) R.id.login_loading_layout);
            this.a = (TextView) this.f.findViewById(R.id.login_loading_msg);
            this.b = (ProgressBar) this.f.findViewById(R.id.login_loading_progressBar);
            this.f.setOnKeyListener(new 1(this));
        }
    }

    public void a(String str) {
        if (this.a != null) {
            this.a.setText(str);
        }
    }

    public void b(int i) {
        if (this.a != null) {
            this.a.setText(i);
        }
    }

    public void a(Drawable drawable) {
        if (drawable != null) {
            this.b.setIndeterminateDrawable(drawable);
        }
    }

    public void a(OnKeyListener onKeyListener) {
        if (onKeyListener != null) {
            this.f.setOnKeyListener(onKeyListener);
        }
    }
}
