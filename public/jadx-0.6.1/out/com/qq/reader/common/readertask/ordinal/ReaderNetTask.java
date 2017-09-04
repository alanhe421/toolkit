package com.qq.reader.common.readertask.ordinal;

import com.qq.reader.common.readertask.ReaderTask;

public class ReaderNetTask extends ReaderTask {
    public static final String TASKNAME = "NetTask";
    private static final long serialVersionUID = 1;
    protected String mUrl = "";

    public String getTaskName() {
        return "NetTask";
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void setUrl(String str) {
        this.mUrl = str;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ReaderNetTask)) {
            return false;
        }
        String url = ((ReaderNetTask) obj).getUrl();
        if (this.mUrl == null || url == null) {
            return false;
        }
        if (this.mUrl.equalsIgnoreCase(url)) {
            return true;
        }
        return false;
    }

    private void initBasicHeader() {
    }
}
