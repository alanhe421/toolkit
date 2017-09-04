package oicq.wlogin_sdk.request;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import oicq.wlogin_sdk.tools.util;

/* compiled from: alert_thread */
class f implements Runnable {
    final /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    public void run() {
        if (this.a.b != null && this.a.c != null) {
            try {
                View linearLayout = new LinearLayout(this.a.b);
                linearLayout.setOrientation(1);
                linearLayout.setBackgroundColor(-7829368);
                linearLayout.setLayoutParams(new LayoutParams(-2, -2));
                View textView = new TextView(this.a.b);
                textView.getPaint().setFakeBoldText(true);
                textView.setText(this.a.c.getTitle());
                linearLayout.addView(textView, 0, new LayoutParams(-1, -2));
                textView = new TextView(this.a.b);
                textView.setText(this.a.c.getMessage());
                linearLayout.addView(textView, 1, new LayoutParams(-1, -2));
                Toast toast = new Toast(this.a.b);
                toast.setGravity(17, 0, 0);
                toast.setDuration(1);
                toast.setView(linearLayout);
                a(toast, 0);
            } catch (Exception e) {
                util.printException(e);
            }
        }
    }

    private void a(Toast toast, int i) {
        if (i <= 5) {
            toast.show();
            b(toast, i);
        }
    }

    private void b(Toast toast, int i) {
        new Timer().schedule(new g(this, toast, i), 30);
    }
}
