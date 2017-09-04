package com.qq.reader.view;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import com.qq.reader.common.utils.t;

/* compiled from: OnNightModeDialogDismissListener */
public abstract class r implements OnDismissListener {
    public abstract t a();

    public void onDismiss(DialogInterface dialogInterface) {
        t a = a();
        if (a != null) {
            a.a();
        }
    }
}
