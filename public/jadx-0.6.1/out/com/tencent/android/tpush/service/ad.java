package com.tencent.android.tpush.service;

import com.tencent.android.tpush.a.a;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/* compiled from: ProGuard */
class ad extends Thread {
    InputStream a;
    String b;
    final /* synthetic */ XGWatchdog c;

    ad(XGWatchdog xGWatchdog, InputStream inputStream, String str) {
        this.c = xGWatchdog;
        this.a = inputStream;
        this.b = str;
    }

    public void run() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.a));
            while (true) {
                String readLine = bufferedReader.readLine();
                if (readLine == null) {
                    return;
                }
                if (this.b.equals("Error")) {
                    a.h(XGWatchdog.TAG, "Runtime exe return err: " + readLine);
                } else {
                    a.h(XGWatchdog.TAG, "Runtime exe return " + readLine);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
