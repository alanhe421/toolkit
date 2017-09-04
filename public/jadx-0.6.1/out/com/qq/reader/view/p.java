package com.qq.reader.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.monitor.i;
import com.tencent.feedback.proguard.R;

/* compiled from: NightModeDialog */
public class p extends BaseDialog implements l {
    ImageView a = ((ImageView) this.f.findViewById(R.id.nightImage));
    Context b;
    a c;
    af d = af.a(this.b.getApplicationContext(), "", 0);

    /* compiled from: NightModeDialog */
    public interface a {
        void a();
    }

    public p(Activity activity) {
        this.b = activity;
        a(activity, null, R.layout.nightdialog, 2, false);
        this.a.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ p a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    this.a.a(false);
                    return true;
                } else if (motionEvent.getAction() != 1) {
                    return false;
                } else {
                    i.a("event_B4", null, this.a.b);
                    this.a.g();
                    this.a.a(false);
                    return true;
                }
            }
        });
        this.f.setOnCancelListener(new OnCancelListener(this) {
            final /* synthetic */ p a;

            {
                this.a = r1;
            }

            public void onCancel(DialogInterface dialogInterface) {
            }
        });
        a(true);
    }

    private void a(boolean z) {
        if (z) {
            if (d.n) {
                this.a.setImageResource(R.drawable.btn_night_up);
            } else {
                this.a.setImageResource(R.drawable.btn_day_up);
            }
        } else if (d.n) {
            this.a.setImageResource(R.drawable.btn_night_down);
        } else {
            this.a.setImageResource(R.drawable.btn_day_down);
        }
    }

    public void g() {
        d.n = !d.n;
        d.i(this.b, d.n);
        this.c.a();
        if (d.n) {
            this.d.a("进入夜间模式");
        } else {
            this.d.a("退出夜间模式");
        }
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    public void f_() {
        a(true);
        this.f.show();
    }

    public void dismiss(int i) {
        dismiss();
    }

    public k getHighLightArea(int i) {
        return null;
    }
}
