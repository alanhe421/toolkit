package qalsdk;

import com.tencent.qalsdk.util.QLog;

/* compiled from: StatReporter */
class at implements Runnable {
    final /* synthetic */ as a;

    at(as asVar) {
        this.a = asVar;
    }

    public void run() {
        this.a.m = this.a.m + 1;
        this.a.h.add(this.a.g());
        QLog.d("MSF.C.StatReport", "CollectNetInfo:" + this.a.m);
        if (this.a.m >= 5) {
            this.a.l.removeCallbacks(this.a.g);
        } else {
            this.a.l.postDelayed(this, 1000);
        }
    }
}
