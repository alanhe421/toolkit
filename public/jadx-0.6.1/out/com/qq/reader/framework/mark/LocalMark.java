package com.qq.reader.framework.mark;

import com.qq.reader.common.monitor.f;
import com.qq.reader.common.utils.ao;
import java.io.Serializable;

public class LocalMark extends Mark implements Serializable {
    private static final long serialVersionUID = 4216536190742016590L;

    public LocalMark(String str, String str2, long j, int i, boolean z) {
        this.mDataStr = ao.f("-");
        if (z) {
            this.mReadTime = System.currentTimeMillis();
        } else {
            this.mReadTime = 0;
        }
        this.mOperateTime = System.currentTimeMillis();
        setBookName(str);
        this.mType = i;
        this.mFileLength = j;
        switch (i) {
            case 0:
                f.a("LocalMark", "Mark Found Role Mark Error");
                return;
            case 1:
                this.mId = str2;
                return;
            case 2:
                this.mId = str2;
                return;
            default:
                return;
        }
    }

    public LocalMark(int i, String str, long j, String str2, boolean z) {
        this.mType = i;
        this.mId = str;
        this.mFileLength = j;
        setBookName(str2);
        this.mDataStr = ao.f("-");
        if (z) {
            this.mReadTime = System.currentTimeMillis();
        } else {
            this.mReadTime = 0;
        }
        this.mOperateTime = System.currentTimeMillis();
    }

    public LocalMark(int i, String str, long j, String str2, String str3) {
        this.mType = i;
        this.mId = str;
        this.mFileLength = j;
        setBookName(str2);
        this.mDataStr = str3;
    }
}
