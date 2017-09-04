package com.tencent.imsdk;

final class br extends Thread {
    br() {
    }

    public final void run() {
        while (true) {
            try {
                String str = (String) QLogImpl.logDeque.take();
                if (str != null) {
                    QLogImpl.writeLogToFile(str);
                }
            } catch (InterruptedException e) {
                System.out.println("write log file error." + e);
            }
        }
    }
}
