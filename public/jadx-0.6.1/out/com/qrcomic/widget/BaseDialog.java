package com.qrcomic.widget;

import android.app.Dialog;
import android.content.Context;
import com.qrcomic.util.b;

public class BaseDialog extends Dialog {
    public BaseDialog(Context context, int i) {
        super(context, i);
    }

    public void dismiss() {
        super.dismiss();
        b.a(this);
    }
}
