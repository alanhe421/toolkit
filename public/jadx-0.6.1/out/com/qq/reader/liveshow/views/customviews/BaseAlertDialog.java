package com.qq.reader.liveshow.views.customviews;

import android.app.AlertDialog;
import android.app.Dialog;
import com.qq.reader.liveshow.utils.b;

public class BaseAlertDialog extends AlertDialog {
    public void dismiss() {
        super.dismiss();
        b.a((Dialog) this);
    }
}
