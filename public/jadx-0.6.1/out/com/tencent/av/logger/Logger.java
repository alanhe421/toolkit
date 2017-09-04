package com.tencent.av.logger;

public interface Logger {
    public static final int LOG_LEVEL_FAULT = 5;
    public static final int LOG_LEVEL_INFO = 6;

    String getLogDir();

    int getWriteLogLevel();

    void init(String str);

    boolean isEnablePrintLog();

    boolean reportKeyLog(String str, int i, String str2);
}
