package com.tencent.qalsdk.sdk;

/* compiled from: LogToFileHelper */
class t extends Thread {
    final /* synthetic */ q a;

    t(q qVar) {
        this.a = qVar;
    }

    public void run() {
        while (true) {
            try {
                String str = (String) this.a.h.k();
                if (str != null) {
                    this.a.d(str);
                }
            } catch (InterruptedException e) {
                System.out.println("write log file error." + e);
            }
        }
    }
}
