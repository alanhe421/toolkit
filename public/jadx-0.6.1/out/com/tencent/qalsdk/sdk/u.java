package com.tencent.qalsdk.sdk;

import com.qq.reader.module.question.fragment.FamousAuthorSayFragment;
import com.tencent.qalsdk.util.QLog;
import java.io.File;

/* compiled from: LogToFileHelper */
class u extends Thread {
    final /* synthetic */ q a;

    u(q qVar) {
        this.a = qVar;
    }

    public void run() {
        try {
            String a = this.a.a(this.a.a(System.currentTimeMillis() - FamousAuthorSayFragment.DATA_EXPIREDTIME_WEEK));
            QLog.d("MSF.D.QLogImpl", "delete log file before:" + a);
            File file = new File(this.a.v);
            if (file.isDirectory()) {
                File[] listFiles = file.listFiles();
                if (listFiles != null) {
                    for (File file2 : listFiles) {
                        if (file2.getName().compareTo(a) < 0) {
                            file2.delete();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("delete log file error." + e);
        }
    }
}
