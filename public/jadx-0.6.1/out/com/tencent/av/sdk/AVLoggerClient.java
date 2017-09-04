package com.tencent.av.sdk;

import android.os.Environment;
import android.text.TextUtils;
import com.tencent.av.logger.AVLoggerChooser;
import com.tencent.av.logger.AVSDKLogger;
import com.tencent.av.logger.Logger;

public class AVLoggerClient extends AVSDKLogger {
    public static String BUGLYTAG = "avsdk2bug";

    public static void initLogSetting(String str) {
        boolean z = true;
        Logger logger = AVLoggerChooser.getLogger();
        if (logger != null) {
            logger.init(str);
            AVSDKLogger.setIsEnablePrintLog(logger.isEnablePrintLog());
            if (logger.getWriteLogLevel() < 1) {
                z = false;
            }
            AVSDKLogger.setIsEnableWriteLog(z);
            AVSDKLogger.setLogDir(logger.getLogDir());
        } else {
            AVSDKLogger.setIsEnablePrintLog(true);
            AVSDKLogger.setIsEnableWriteLog(true);
            AVSDKLogger.setLogDir(Environment.getExternalStorageDirectory() + "/tencent/com/tencent/mobileqq/avsdk/");
        }
        AVSDKLogger.setMaxFileSize(AVSDKLogger.DEFAULT_MAX_LOG_FILE_SIZE);
        AVSDKLogger.setLogListener(null);
    }

    public static void setLogSetting(AVSDKLogSetting aVSDKLogSetting) {
        if (aVSDKLogSetting != null) {
            AVSDKLogger.setIsEnablePrintLog(aVSDKLogSetting.isEnablePrintLog);
            AVSDKLogger.setIsEnableWriteLog(aVSDKLogSetting.isEnableWriteLog);
            if (!TextUtils.isEmpty(aVSDKLogSetting.logDir)) {
                AVSDKLogger.setLogDir(aVSDKLogSetting.logDir);
            }
            AVSDKLogger.setMaxFileSize(aVSDKLogSetting.maxFileSize);
            AVSDKLogger.setLogListener(aVSDKLogSetting.logListener);
        }
    }

    public static void log2bugly(String str, int i) {
        Logger logger = AVLoggerChooser.getLogger();
        if (logger != null) {
            logger.reportKeyLog(BUGLYTAG, i, str);
        }
    }

    public static void onLogReceived(String str, int i) {
        LogListener logListener = AVSDKLogger.getLogListener();
        if (logListener != null) {
            logListener.onLogReceived(str, i);
        }
    }

    public static boolean isLogUploadToApp() {
        return AVSDKLogger.getLogListener() != null;
    }
}
