package com.qq.reader.view.b;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;
import com.hmt.analytics.UpdateManager;
import com.qq.reader.appconfig.a.d;
import com.qq.reader.common.utils.aj;
import com.qq.reader.module.bookstore.qnative.c.c;
import com.qq.reader.view.BaseDialog;
import com.qq.reader.view.h;
import com.tencent.feedback.proguard.R;

/* compiled from: TTSMenuChangeTipDialog */
public class a extends BaseDialog {
    public a(Activity activity) {
        UpdateManager.b = activity;
        if (this.f == null) {
            a(activity, null, R.layout.tts_menu_change_tip_layout, 0, false);
            LayoutParams attributes = this.f.getWindow().getAttributes();
            LayoutParams attributes2 = activity.getWindow().getAttributes();
            attributes.width = attributes2.width;
            attributes.height = attributes2.height;
            this.f.getWindow().setAttributes(attributes);
            this.f.setCanceledOnTouchOutside(true);
            this.f.setCancelable(true);
            this.f.a(new h(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void a(MotionEvent motionEvent) {
                    this.a.cancel();
                }

                public boolean a(int i, KeyEvent keyEvent) {
                    if (i != 4) {
                        return false;
                    }
                    this.a.cancel();
                    return true;
                }
            });
            TextView textView = (TextView) a(R.id.tv_ack);
            if (textView != null) {
                textView.setOnClickListener(new c(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void a(View view) {
                        this.a.cancel();
                    }
                });
            }
            if (d.ao(activity.getApplicationContext())) {
                aj.a(this.f, true);
                if (!d.aq(e().getApplicationContext())) {
                    aj.b(this.f, true);
                }
            }
        }
    }

    public void f_() {
        if (this.f != null) {
            this.f.show();
        }
    }
}
