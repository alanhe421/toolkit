package com.qq.reader.liveshow.views.customviews;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.qq.reader.liveshow.a.e;
import com.qq.reader.liveshow.a.g;
import com.qq.reader.liveshow.a.i;

public class WaitingDialog {
    BaseDialog a;
    View b;
    TextView c;
    private boolean d;

    public WaitingDialog(Context context, boolean z) {
        this.d = z;
        a(context, i.waitingDialog);
    }

    private void a(Context context, int i) {
        this.a = new BaseDialog(context, i);
        this.b = View.inflate(context, g.login_loading_dialog, null);
        if (this.a.getWindow() != null) {
            LayoutParams attributes = this.a.getWindow().getAttributes();
            attributes.flags &= 2;
            attributes.gravity = 17;
            this.a.getWindow().setAttributes(attributes);
        }
        this.a.setContentView(this.b);
        this.a.setCancelable(this.d);
        this.a.setCanceledOnTouchOutside(this.d);
        this.a.setOnKeyListener(new OnKeyListener(this) {
            final /* synthetic */ WaitingDialog a;

            {
                this.a = r1;
            }

            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return false;
                }
                if (this.a.d) {
                    this.a.a.dismiss();
                }
                return true;
            }
        });
    }

    public void setMessage(String str) {
        if (str != null) {
            if (this.c == null) {
                this.c = (TextView) this.b.findViewById(e.login_loading_msg);
            }
            this.c.setText(str);
        }
    }

    public boolean a() {
        if (this.a != null) {
            return this.a.isShowing();
        }
        return false;
    }

    public Dialog getDialog() {
        return this.a;
    }
}
