package com.qq.reader.common.download.task;

import java.io.File;

/* compiled from: NetCommonTask */
public abstract class f extends b {
    public static final String C_PATH = (File.separator + "data" + File.separator);
    public static final String DEFAULT_DM_C_PATH = (C_PATH + "local" + File.separator);
    public static final String DEFAULT_DM_E_PATH = E_PATH;
    public static final int DEVICE_ABORTED = 952;
    public static final String DOWNLOAD_FILE_TMP = ".tmp";
    public static final String E_PATH = (File.separator + "sdcard" + File.separator);
    public static final int INSUFFICIENT_MEMORY = 901;
    public static final int INVALID_DESCRIPTOR = 906;
    public static final int LOADER_ERROR = 954;
    public static final int LOSS_OF_SERVICE = 903;
    public static final int NON_ACCEPTABLE_CONTENT = 953;
    public static final int SUCCESS = 900;
    public static final int USER_CANCELLED = 902;
    private static final long serialVersionUID = 8244557538892534072L;
    protected String bookFormat;
    private volatile long currentSize;
    private String downloadDirectory;
    protected int drmflag;
    private String failReason;
    protected String filePath = "";
    private long id;
    private volatile float mDownloadSpeed;
    private String objectURI;
    private volatile long size;
    private int statusCode = SUCCESS;

    public abstract String getFullName();

    public f(String str, String str2, String str3) {
        super(str);
        this.objectURI = str2;
        this.downloadDirectory = str3;
    }

    public float getDownloadSpeed() {
        return this.mDownloadSpeed;
    }

    public void setDownloadSpeed(float f) {
        this.mDownloadSpeed = f;
    }

    public String getBookFormat() {
        return this.bookFormat;
    }

    public void setBookFormat(String str) {
        if (str != null) {
            this.bookFormat = str;
        }
    }

    public String getObjectURI() {
        return this.objectURI;
    }

    public void setObjectURI(String str) {
        this.objectURI = str;
    }

    public long getSize() {
        return this.size;
    }

    public void setSize(long j) {
        this.size = j;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public void setStatusCode(int i) {
        this.statusCode = i;
    }

    public String getDownloadDirectory() {
        return this.downloadDirectory;
    }

    public void setDownloadDirectory(String str) {
        this.downloadDirectory = str;
    }

    public String getFilePath() {
        if (this.filePath == null || this.filePath.trim().length() <= 0) {
            this.filePath = getDownloadDirectory() + File.separator + getFullName();
        }
        return this.filePath;
    }

    public void setFilePath(String str) {
        this.filePath = str;
    }

    public String getTempFilePath() {
        return getDownloadTempFilePath();
    }

    public String getDownloadTempFilePath() {
        return getFilePath() + DOWNLOAD_FILE_TMP;
    }

    public long getCurrentSize() {
        return this.currentSize;
    }

    public void setCurrentSize(long j) {
        this.currentSize = j;
    }

    public String getFailReason() {
        return this.failReason;
    }

    public void setFailReason(String str) {
        this.failReason = str;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public int hashCode() {
        return (this.objectURI == null ? 0 : this.objectURI.hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.objectURI.equals(((f) obj).getObjectURI());
    }

    public String toString() {
        return "NetCommonTask [currentSize=" + this.currentSize + ",state=" + getState() + ",downloadDirectory=" + this.downloadDirectory + ", failReason=" + this.failReason + ", filePath=" + this.filePath + "]";
    }

    public void onReinit() {
        setCurrentSize(0);
    }

    public int getDrmflag() {
        return this.drmflag;
    }

    public void setDrmflag(int i) {
        this.drmflag = i;
    }
}
