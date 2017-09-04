package tencent.tls.request;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;
import tencent.tls.platform.TLSErrInfo;

public class alert_thread extends Thread {
    private Context context;
    private TLSErrInfo errInfo;
    Runnable myRunnable = new Runnable() {
        public void run() {
            if (alert_thread.this.context != null && alert_thread.this.errInfo != null) {
                View linearLayout = new LinearLayout(alert_thread.this.context);
                linearLayout.setOrientation(1);
                linearLayout.setBackgroundColor(-7829368);
                linearLayout.setLayoutParams(new LayoutParams(-2, -2));
                View textView = new TextView(alert_thread.this.context);
                textView.getPaint().setFakeBoldText(true);
                textView.setText(alert_thread.this.errInfo.Title);
                linearLayout.addView(textView, 0, new LayoutParams(-1, -2));
                textView = new TextView(alert_thread.this.context);
                textView.setText(alert_thread.this.errInfo.Msg);
                linearLayout.addView(textView, 1, new LayoutParams(-1, -2));
                Toast toast = new Toast(alert_thread.this.context);
                toast.setGravity(17, 0, 0);
                toast.setDuration(1);
                toast.setView(linearLayout);
                initToast(toast, 0);
            }
        }

        private void initToast(Toast toast, int i) {
            if (i <= 5) {
                toast.show();
                execToast(toast, i);
            }
        }

        private void execToast(final Toast toast, final int i) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    AnonymousClass1.this.initToast(toast, i + 1);
                }
            }, 30);
        }
    };

    public alert_thread(Context context) {
        this.context = context;
    }

    public alert_thread(Context context, TLSErrInfo tLSErrInfo) {
        this.context = context;
        setErrMsg(tLSErrInfo);
    }

    public void setErrMsg(TLSErrInfo tLSErrInfo) {
        this.errInfo = tLSErrInfo;
    }

    public TLSErrInfo getErrMsg() {
        return this.errInfo;
    }

    public void run() {
        new Handler(Looper.getMainLooper()).post(this.myRunnable);
    }
}
