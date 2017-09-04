package com.tencent.imsdk;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;

final class bm extends Thread {
    bm() {
    }

    public final void run() {
        Calendar instance;
        File file = new File(QLogImpl.getLogPath());
        if (file.exists()) {
            File[] listFiles = file.listFiles();
            if (listFiles != null && listFiles.length != 0) {
                Arrays.sort(listFiles, new bn(this));
                instance = Calendar.getInstance();
                instance.add(6, -7);
                long timeInMillis = instance.getTimeInMillis();
                for (File file2 : listFiles) {
                    if (QLog.isColorLevel()) {
                        QLog.d(LogManager.tag, 2, "found log file " + file2.getName());
                    }
                    if (timeInMillis > file2.lastModified()) {
                        file2.delete();
                        if (QLog.isColorLevel()) {
                            QLog.d(LogManager.tag, 2, "del expires log " + file2.getName());
                        }
                    }
                }
            } else {
                return;
            }
        }
        instance = Calendar.getInstance();
        instance.set(6, instance.get(6) - 7);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        instance.getTimeInMillis();
    }
}
