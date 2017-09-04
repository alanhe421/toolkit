package com.qq.reader.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import com.qq.reader.common.utils.t;
import com.tencent.feedback.proguard.R;

public class AlertDialog extends Dialog implements DialogInterface {
    boolean avoiddismiss;
    private AlertController mAlert;
    private OnCancelListener mCancelListener;
    private Context mContext;
    private OnDismissListener mDialogDismissListener;
    private t mNMC;
    private OnShowListener mOnShowListener;

    public static class a {
        private final com.qq.reader.view.AlertController.a a;

        public a(Context context) {
            this.a = new com.qq.reader.view.AlertController.a(context);
        }

        public a a(int i) {
            this.a.e = this.a.a.getText(i);
            return this;
        }

        public a a(CharSequence charSequence) {
            this.a.e = charSequence;
            return this;
        }

        public a a(View view) {
            this.a.r = view;
            return this;
        }

        public a b(int i) {
            this.a.f = this.a.a.getText(i);
            return this;
        }

        public a b(CharSequence charSequence) {
            this.a.f = charSequence;
            return this;
        }

        public a c(int i) {
            this.a.c = i;
            return this;
        }

        public a a(int i, OnClickListener onClickListener) {
            this.a.g = this.a.a.getText(i);
            this.a.h = onClickListener;
            return this;
        }

        public a a(CharSequence charSequence, OnClickListener onClickListener) {
            this.a.g = charSequence;
            this.a.h = onClickListener;
            return this;
        }

        public a b(int i, OnClickListener onClickListener) {
            this.a.i = this.a.a.getText(i);
            this.a.j = onClickListener;
            return this;
        }

        public a b(CharSequence charSequence, OnClickListener onClickListener) {
            this.a.i = charSequence;
            this.a.j = onClickListener;
            return this;
        }

        public a a(boolean z) {
            this.a.m = z;
            return this;
        }

        public a a(OnCancelListener onCancelListener) {
            this.a.n = onCancelListener;
            return this;
        }

        public AlertDialog a() {
            AlertDialog alertDialog = new AlertDialog(this.a.a);
            alertDialog.setCanceledOnTouchOutside(true);
            this.a.a(alertDialog.mAlert);
            alertDialog.setCancelable(this.a.m);
            alertDialog.setOnCancelListener(this.a.n);
            if (this.a.o != null) {
                alertDialog.setOnKeyListener(this.a.o);
            }
            return alertDialog;
        }

        public AlertDialog b() {
            AlertDialog a = a();
            a.show();
            return a;
        }
    }

    protected AlertDialog(Context context) {
        this(context, R.style.popBottomDialog);
    }

    protected AlertDialog(Context context, int i) {
        super(context, i);
        this.mContext = null;
        this.mNMC = null;
        this.avoiddismiss = false;
        this.mContext = context;
        this.mAlert = new AlertController(context, this, getWindow());
        this.mNMC = new t((Dialog) this, true);
        setOnDismissListener(new 1(this));
    }

    public void a(boolean z) {
        this.mAlert.a(z);
    }

    public void c() {
        this.mAlert.a();
    }

    public void setOnShowListener(OnShowListener onShowListener) {
        this.mOnShowListener = onShowListener;
    }

    public void setOnCancelListener(OnCancelListener onCancelListener) {
        this.mCancelListener = onCancelListener;
    }

    public void setOnDismissListener(OnDismissListener onDismissListener) {
        this.mDialogDismissListener = onDismissListener;
    }

    public void a(int i) {
        this.mAlert.d(i);
    }

    public void b(int i) {
        this.mAlert.c(i);
    }

    public Button c(int i) {
        return this.mAlert.e(i);
    }

    public void a(int i, int i2) {
        this.mAlert.a(i, i2);
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.mAlert.a(charSequence);
    }

    public View findViewById(int i) {
        return this.mAlert.a(i);
    }

    public void a(View view) {
        this.mAlert.b(view);
    }

    public void a(CharSequence charSequence) {
        this.mAlert.b(charSequence);
    }

    private void a(int i, CharSequence charSequence, OnClickListener onClickListener) {
        this.mAlert.a(i, charSequence, onClickListener, null);
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mAlert.b();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.mAlert.a(i, keyEvent)) {
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        if (this.mAlert.b(i, keyEvent)) {
            return true;
        }
        return super.onKeyUp(i, keyEvent);
    }

    public void show() {
        try {
            if (this.mOnShowListener != null) {
                super.setOnShowListener(this.mOnShowListener);
            }
            if (this.mCancelListener != null) {
                super.setOnCancelListener(this.mCancelListener);
            }
            if (this.mDialogDismissListener != null) {
                super.setOnDismissListener(this.mDialogDismissListener);
            }
            this.mAlert.c();
            super.show();
            this.mNMC.b();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void dismiss() {
        if (this.avoiddismiss) {
            this.avoiddismiss = false;
            return;
        }
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            com.qq.reader.common.d.a.a(this);
        }
    }

    public void d() {
        this.avoiddismiss = true;
    }

    public t e() {
        return this.mNMC;
    }

    public void a(int i, OnClickListener onClickListener) {
        a(this.mContext.getText(i), onClickListener);
    }

    public void b(int i, OnClickListener onClickListener) {
        b(this.mContext.getText(i), onClickListener);
    }

    public void a(CharSequence charSequence, OnClickListener onClickListener) {
        a(-1, charSequence, onClickListener);
    }

    public void b(CharSequence charSequence, OnClickListener onClickListener) {
        a(-2, charSequence, onClickListener);
    }
}
