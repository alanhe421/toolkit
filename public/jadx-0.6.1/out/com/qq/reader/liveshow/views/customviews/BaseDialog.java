package com.qq.reader.liveshow.views.customviews;

import android.app.Dialog;
import android.content.Context;
import com.qq.reader.liveshow.utils.b;

public class BaseDialog extends Dialog {
    public BaseDialog(Context context, int i) {
        super(context, i);
    }

    public void dismiss() {
        super.dismiss();
        b.a((Dialog) this);
    }
}
