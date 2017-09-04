package com.tencent.qalsdk.sdk;

import android.os.Process;
import android.util.Log;

/* compiled from: LogToFileHelper */
class s extends Thread {
    final /* synthetic */ r a;

    s(r rVar, String str) {
        this.a = rVar;
        super(str);
    }

    public void run() {
        int i = 0;
        if (q.c && this.a.a.C.compareAndSet(false, true)) {
            try {
                this.a.a.z = MsfSdkUtils.getProcessName(this.a.a.L);
                this.a.a.D = Process.myPid();
                Log.d("appMemory", "QLog init retry ");
                this.a.a.b(System.currentTimeMillis());
                this.a.a.m.setName("logWriteThread");
                this.a.a.m.start();
                this.a.a.K.removeCallbacks(this.a.a.j);
                this.a.a.n.start();
            } catch (Exception e) {
                this.a.a.C.set(false);
                e.printStackTrace();
                int i2 = this.a.a.J.get();
                Log.d("appMemory", "QLog init post retry " + i2 + " times, interval " + this.a.a.I[i2]);
                this.a.a.K.removeCallbacks(this.a.a.j);
                this.a.a.K.postDelayed(this.a.a.j, (long) (this.a.a.I[i2] * 60000));
                i2++;
                if (i2 < this.a.a.I.length) {
                    i = i2;
                }
                this.a.a.J.set(i);
            }
        }
    }
}
