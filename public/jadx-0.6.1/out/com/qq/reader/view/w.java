package com.qq.reader.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import com.tencent.android.tpush.common.Constants;
import com.tencent.feedback.proguard.R;

/* compiled from: QRProgressDialog */
public class w extends BaseDialog {
    private String a = "QRProgressDialog";
    private NumberProgressBar b;
    private TextView c;
    private final int d = Constants.ERRORCODE_UNKNOWN;
    private final int e = 100;
    private double i = -1.0d;
    private final Context j;
    private double k = 0.0d;
    private Handler l = new Handler(this) {
        final /* synthetic */ w a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 800:
                    synchronized (this.a.b) {
                        if (this.a.i != 0.0d) {
                            this.a.i = 0.0d;
                        }
                    }
                    return;
                default:
                    return;
            }
        }
    };

    public w(Activity activity, String str) {
        this.j = activity;
        if (this.f == null) {
            this.k = 100.0d;
            a(activity, null, R.layout.app_update_dialog, 0, false);
            this.f.setCancelable(true);
            this.f.setOnCancelListener(new OnCancelListener(this) {
                final /* synthetic */ w a;

                {
                    this.a = r1;
                }

                public void onCancel(DialogInterface dialogInterface) {
                    this.a.g();
                }
            });
            this.b = (NumberProgressBar) this.f.findViewById(R.id.numberbar);
            this.b.setFocusable(false);
            this.c = (TextView) this.f.findViewById(R.id.infotext);
            this.c.setText(str);
        }
    }

    public void b(int i) {
        this.b.setProgress(i);
    }

    public void f_() {
        this.f.show();
    }

    private boolean g() {
        if (this.f.isShowing()) {
            this.f.cancel();
        }
        this.f.getWindow().closeAllPanels();
        return true;
    }
}
