package com.tencent.imcore;

public enum LogLevel {
    kOff((String) 0),
    kError,
    kWarn,
    kInfo,
    kDebug;
    
    private final int swigValue;

    private static class aa {
        private static int a;

        static {
            a = 0;
        }

        static /* synthetic */ int a() {
            int i = a;
            a = i + 1;
            return i;
        }
    }

    private LogLevel(int i) {
        this.swigValue = i;
        aa.a = i + 1;
    }

    private LogLevel(LogLevel logLevel) {
        this.swigValue = logLevel.swigValue;
        aa.a = this.swigValue + 1;
    }

    public static LogLevel swigToEnum(int i) {
        LogLevel[] logLevelArr = (LogLevel[]) LogLevel.class.getEnumConstants();
        if (i < logLevelArr.length && i >= 0 && logLevelArr[i].swigValue == i) {
            return logLevelArr[i];
        }
        for (LogLevel logLevel : logLevelArr) {
            if (logLevel.swigValue == i) {
                return logLevel;
            }
        }
        throw new IllegalArgumentException("No enum " + LogLevel.class + " with value " + i);
    }

    public final int swigValue() {
        return this.swigValue;
    }
}
