package com.tencent.av.logger;

import com.tencent.TIMLogLevel;
import com.tencent.TIMManager;
import com.tencent.timint.TIMIntManager;

public class IMLogger implements Logger {
    public static final String LOGTAG = "IMChannel";
    public static final String buglyAPPID = "900011370";

    public String getLogDir() {
        return TIMManager.getInstance().getLogPath();
    }

    public boolean isEnablePrintLog() {
        return TIMManager.getInstance().getIsLogPrintEnabled();
    }

    public int getWriteLogLevel() {
        return 1;
    }

    public boolean reportKeyLog(String str, int i, String str2) {
        if (6 == i) {
            TIMIntManager.getInstance().logBugly(TIMLogLevel.INFO, str, str2);
        } else if (5 == i) {
            TIMIntManager.getInstance().logBugly(TIMLogLevel.ERROR, str, str2);
        } else {
            TIMIntManager.getInstance().logBugly(TIMLogLevel.DEBUG, str, str2);
        }
        return true;
    }

    public void init(String str) {
        TIMIntManager.getInstance().setAvSDKVersionToBugly(buglyAPPID, str);
    }
}
