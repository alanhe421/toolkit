package com.qq.reader.view;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.tencent.feedback.proguard.R;

/* compiled from: CommProgressDialog */
public class f extends BaseDialog implements h {
    private Button a;
    private Activity b;
    private TextView c;
    private TextView d;
    private View e;

    public f(Activity activity) {
        this.b = activity;
        if (this.f == null) {
            a(activity, null, R.layout.custom_progress_dialog, 1, true);
            this.a = (Button) this.f.findViewById(R.id.comm_progress_dialog_cancel);
            this.a.setOnClickListener(new OnClickListener(this) {
                final /* synthetic */ f a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    this.a.f.cancel();
                }
            });
            this.c = (TextView) this.f.findViewById(R.id.custom_progress_dialog_loading_text);
            this.d = (TextView) this.f.findViewById(R.id.comm_progress_dialog_title);
            this.e = this.f.findViewById(R.id.comm_progress_dialog_bottompart);
            this.f.getWindow().addFlags(2);
        }
    }

    public void a(String str) {
        this.d.setText(str);
    }

    public TextView g() {
        return this.c;
    }

    public void a(MotionEvent motionEvent) {
    }

    public boolean a(int i, KeyEvent keyEvent) {
        return false;
    }
}
