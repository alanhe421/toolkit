package com.qq.reader.cservice.download.book;

import android.text.TextUtils;
import com.qq.reader.common.download.task.f;
import com.qq.reader.common.download.task.state.TaskStateEnum;
import com.qq.reader.common.imageloader.a.a.a;
import com.qq.reader.common.utils.ao;
import java.io.Serializable;

public class DownloadBookTask extends f implements Serializable, Comparable<DownloadBookTask> {
    private static final long serialVersionUID = 1;
    private String author;
    private String description;
    private volatile int iconDownloadedTime = 0;
    private String iconURI;
    protected boolean isOnlyDownLoadIcon = false;
    private String mCoverPath = null;
    private long mCreateTime;
    protected int mFromType = 0;
    private String mNetChannel = "";
    private int newVersion;
    private int version;

    public DownloadBookTask(long j, String str, String str2, String str3, String str4, int i, String str5, int i2, long j2) {
        super(str, str3, ao.g());
        initialize();
        setId(j);
        if (str != null) {
            str = ao.a(new StringBuffer(str));
        }
        setName(str);
        setAuthor(str2);
        setObjectURI(str3);
        setIconURI(str4);
        setVersion(i);
        setBookFormat(str5);
        setDrmflag(i2);
        if (j2 == -1) {
            this.mCreateTime = System.currentTimeMillis();
        } else {
            this.mCreateTime = j2;
        }
    }

    public DownloadBookTask(long j) {
        super("", "", ao.g());
        initialize();
        this.mCreateTime = System.currentTimeMillis();
    }

    public String getNetChannelId() {
        return this.mNetChannel;
    }

    public DownloadBookTask setNetChannel(String str) {
        if (str != null) {
            this.mNetChannel = str;
        } else {
            this.mNetChannel = "";
        }
        return this;
    }

    private void initialize() {
        setId(0);
        setSize(0);
        this.author = "";
        setName("");
        this.description = "";
        this.iconURI = "";
        setFilePath("");
        this.version = 0;
        setState(TaskStateEnum.Prepared);
        setProgress(0);
        setStatusCode(f.SUCCESS);
        this.bookFormat = "txt";
        this.drmflag = 0;
        this.newVersion = this.version;
        this.mCreateTime = 0;
        this.isOnlyDownLoadIcon = false;
        this.mNetChannel = "";
    }

    public void setIsOnlyDownLoadIcon(boolean z) {
        this.isOnlyDownLoadIcon = z;
    }

    public boolean getIsOnlyDownLoadIcon() {
        return this.isOnlyDownLoadIcon;
    }

    public int getFromType() {
        return this.mFromType;
    }

    public void setFromType(int i) {
        this.mFromType = i;
    }

    public long getCreateTime() {
        return this.mCreateTime;
    }

    public void setCreateTime(long j) {
        this.mCreateTime = j;
    }

    public String getAuthor() {
        if (this.author == null || this.author.length() == 0) {
            this.author = "匿名";
        }
        return this.author;
    }

    public void setAuthor(String str) {
        this.author = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public String getImageURI() {
        this.iconURI = ao.q(this.iconURI);
        return this.iconURI;
    }

    public void setIconURI(String str) {
        this.iconURI = str;
    }

    public String getDownloadDirectory() {
        String downloadDirectory = super.getDownloadDirectory();
        if (downloadDirectory == null) {
            setDownloadDirectory(ao.g());
        }
        return downloadDirectory;
    }

    public String getImagePath() {
        if (this.mCoverPath == null) {
            this.mCoverPath = this.iconURI;
            if (TextUtils.isEmpty(this.mCoverPath)) {
                this.mCoverPath = a.b(1, null);
            }
        }
        return this.mCoverPath;
    }

    public String getFullName() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getName());
        stringBuffer.append(".");
        stringBuffer.append(this.bookFormat);
        return stringBuffer.toString();
    }

    public String getTempFilePath() {
        return getFilePath() + ".zip";
    }

    public int getVersion() {
        return this.version;
    }

    public int getNewVerison() {
        return this.newVersion;
    }

    public void setVersion(int i) {
        this.version = i;
    }

    public void setNewVersion(int i) {
        this.newVersion = i;
    }

    public int getIconDownloadedTimes() {
        return this.iconDownloadedTime;
    }

    public void setIconDownloadedTimes(int i) {
        this.iconDownloadedTime = i;
    }

    public int hashCode() {
        return ((int) (getId() ^ (getId() >>> 32))) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (getId() != ((DownloadBookTask) obj).getId()) {
            return false;
        }
        return true;
    }

    public String toString() {
        return "DownloadTask [currentSize=" + getCurrentSize() + ",state=" + getState() + ",downloadDirectory=" + getDownloadDirectory() + ", failReason=" + getFailReason() + ", filePath=" + getFilePath() + ", iconURI=" + this.iconURI + ", id=" + getId() + ", format=" + this.bookFormat + ",  version=" + this.version + "objectURI=" + getObjectURI() + "]";
    }

    public int compareTo(DownloadBookTask downloadBookTask) {
        return (int) (getId() - downloadBookTask.getId());
    }

    public int getTaskType() {
        return 100;
    }
}
