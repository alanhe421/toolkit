package com.tencent.qalsdk.core;

import com.tencent.qalsdk.util.ALog;
import com.tencent.qalsdk.util.QLog;
import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import qalsdk.d;

/* compiled from: LogManager */
final class g extends Thread {
    g() {
    }

    private void a(String str) {
        int i = 3;
        File file = new File(str);
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                int parseInt;
                long timeInMillis;
                Arrays.sort(listFiles, new h(this));
                Calendar instance = Calendar.getInstance();
                try {
                    String d = d.d();
                    if (d != null && d.length() > 0) {
                        parseInt = Integer.parseInt(d);
                        if (parseInt >= 1 && parseInt <= 14) {
                            i = parseInt;
                        }
                        instance.add(6, i - (i * 2));
                        timeInMillis = instance.getTimeInMillis();
                        for (File file2 : listFiles) {
                            if (QLog.isColorLevel()) {
                                QLog.d(e.a, 2, "found log file " + file2.getName());
                            }
                            if (timeInMillis > file2.lastModified()) {
                                file2.delete();
                                if (QLog.isColorLevel()) {
                                    QLog.d(e.a, 2, "del expires log " + file2.getName());
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                parseInt = 3;
                i = parseInt;
                instance.add(6, i - (i * 2));
                timeInMillis = instance.getTimeInMillis();
                for (File file22 : listFiles) {
                    if (QLog.isColorLevel()) {
                        QLog.d(e.a, 2, "found log file " + file22.getName());
                    }
                    if (timeInMillis > file22.lastModified()) {
                        file22.delete();
                        if (QLog.isColorLevel()) {
                            QLog.d(e.a, 2, "del expires log " + file22.getName());
                        }
                    }
                }
            } else {
                return;
            }
        }
        Calendar instance2 = Calendar.getInstance();
        instance2.set(6, instance2.get(6) - 7);
        instance2.set(11, 0);
        instance2.set(12, 0);
        instance2.set(13, 0);
        instance2.set(14, 0);
        instance2.getTimeInMillis();
    }

    public void run() {
        a(ALog.getFilePath());
        a(QLog.getFilePath());
    }
}
