package com.qq.reader.common.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.view.View;
import com.qq.reader.view.BaseDialog;

/* compiled from: WLoginBaseDialog */
public class g extends BaseDialog {
    public static long b = 683031601;
    public static int e = 4096;
    protected Activity a;
    protected volatile String c = null;
    protected ProgressDialog d;

    public void a(Activity activity, View view, int i, int i2, boolean z) {
        super.a(activity, null, i, i2, false);
        a(new OnCancelListener(this) {
            final /* synthetic */ g a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
                this.a.cancel();
            }
        });
    }

    public void a(String str) {
        if (this.d == null) {
            if (str == null) {
                str = "";
            }
            this.d = ProgressDialog.show(this.a, null, str, true);
            this.d.setCanceledOnTouchOutside(false);
            this.d.setCancelable(true);
            this.d.setOnKeyListener(new OnKeyListener(this) {
                final /* synthetic */ g a;

                {
                    this.a = r1;
                }

                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    switch (i) {
                        case 4:
                            this.a.a();
                            break;
                    }
                    return false;
                }
            });
            return;
        }
        this.d.show();
    }

    public void a() {
        if (this.d != null && this.d.isShowing()) {
            this.d.cancel();
            this.d = null;
        }
    }
}
