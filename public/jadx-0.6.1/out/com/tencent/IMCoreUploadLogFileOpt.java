package com.tencent;

import android.text.TextUtils;
import com.tencent.imcore.UploadLogFileOpt;
import java.io.UnsupportedEncodingException;

public class IMCoreUploadLogFileOpt {
    private String filePath = "";
    private String logId = "";
    private long logSize = 0;
    private String relativePath = "";
    private String tag = "";

    public static IMCoreUploadLogFileOpt convertFrom(UploadLogFileOpt uploadLogFileOpt) {
        IMCoreUploadLogFileOpt iMCoreUploadLogFileOpt = new IMCoreUploadLogFileOpt();
        iMCoreUploadLogFileOpt.setFilePath(uploadLogFileOpt.getFilePath());
        iMCoreUploadLogFileOpt.setTag(uploadLogFileOpt.getTag());
        iMCoreUploadLogFileOpt.setLogSize(uploadLogFileOpt.getLogSize());
        try {
            iMCoreUploadLogFileOpt.setLogId(new String(uploadLogFileOpt.getLogId(), "utf-8"));
            iMCoreUploadLogFileOpt.setRelativePath(new String(uploadLogFileOpt.getRelativePath(), "utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return iMCoreUploadLogFileOpt;
    }

    public String getFilePath() {
        return this.filePath;
    }

    public String getLogId() {
        return this.logId;
    }

    public long getLogSize() {
        return this.logSize;
    }

    public String getRelativePath() {
        return this.relativePath;
    }

    public String getTag() {
        return this.tag;
    }

    public void setFilePath(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.filePath = str;
        }
    }

    public void setLogId(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.logId = str;
        }
    }

    public void setLogSize(long j) {
        if (j > 0) {
            this.logSize = j;
        } else {
            this.logSize = 10;
        }
    }

    public void setRelativePath(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.relativePath = str;
        }
    }

    public void setTag(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.tag = str;
        }
    }
}
