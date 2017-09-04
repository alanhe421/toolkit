package com.tencent.av.sdk;

public class AVSDKLogSetting {
    static final int DEFAULT_MAX_LOG_FILE_SIZE = 52428800;
    boolean isEnablePrintLog;
    boolean isEnableWriteLog;
    String logDir;
    LogListener logListener;
    int maxFileSize;

    public static class Builder {
        private boolean isEnablePrintLog = true;
        private boolean isEnableWriteLog = true;
        private String logDir = "";
        private LogListener logListener = null;
        private int maxFileSize = 52428800;

        public Builder isEnablePrintLog(boolean z) {
            this.isEnablePrintLog = z;
            return this;
        }

        public Builder isEnableWriteLog(boolean z) {
            this.isEnableWriteLog = z;
            return this;
        }

        public Builder logDir(String str) {
            this.logDir = str;
            return this;
        }

        public Builder maxFileSize(int i) {
            this.maxFileSize = i;
            return this;
        }

        public Builder logListener(LogListener logListener) {
            this.logListener = logListener;
            return this;
        }

        public AVSDKLogSetting build() {
            return new AVSDKLogSetting(this);
        }
    }

    AVSDKLogSetting(Builder builder) {
        this.isEnablePrintLog = builder.isEnablePrintLog;
        this.isEnableWriteLog = builder.isEnableWriteLog;
        this.logDir = builder.logDir;
        this.maxFileSize = builder.maxFileSize;
        this.logListener = builder.logListener;
    }
}
