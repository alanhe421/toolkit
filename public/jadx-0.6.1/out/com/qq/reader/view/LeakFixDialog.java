package com.qq.reader.view;

import android.app.Dialog;
import android.content.Context;
import com.qq.reader.common.d.a;

public class LeakFixDialog extends Dialog {
    public LeakFixDialog(Context context) {
        super(context);
    }

    public LeakFixDialog(Context context, int i) {
        super(context, i);
    }

    public void dismiss() {
        try {
            super.dismiss();
            a();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.a(this);
        }
    }

    protected void a() {
    }
}
