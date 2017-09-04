package com.tencent.av.logger;

public class AVLoggerChooser {
    private static Logger sLogger = null;

    private AVLoggerChooser() {
    }

    public static synchronized Logger getLogger() {
        Logger logger;
        Logger logger2 = null;
        synchronized (AVLoggerChooser.class) {
            if (sLogger != null) {
                logger = sLogger;
            } else {
                Class cls;
                try {
                    cls = Class.forName("com.tencent.av.logger.IMLogger");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    cls = null;
                }
                if (cls == null) {
                    logger = null;
                } else {
                    try {
                        logger2 = (Logger) cls.newInstance();
                    } catch (InstantiationException e2) {
                        e2.printStackTrace();
                    } catch (IllegalAccessException e3) {
                        e3.printStackTrace();
                    }
                    sLogger = logger2;
                    logger = sLogger;
                }
            }
        }
        return logger;
    }

    public static synchronized void setLoger(Logger logger) {
        synchronized (AVLoggerChooser.class) {
            sLogger = logger;
        }
    }
}
