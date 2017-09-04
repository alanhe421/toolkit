package com.qq.reader.framework.mark;

import com.qq.reader.cservice.download.book.DownloadBookTask;
import com.qq.reader.readengine.model.a;
import java.io.Serializable;

public class DownloadMark extends Mark implements Serializable {
    private static final long serialVersionUID = 1372696859578877060L;
    private DownloadBookTask mDownloadTask;

    public DownloadMark(long j) {
        this.mType = 3;
        this.mBookId = j;
        this.mOperateTime = System.currentTimeMillis();
    }

    public DownloadMark(String str, String str2, boolean z) {
        this.mType = 3;
        this.mId = str;
        if (z) {
            this.mOperateTime = System.currentTimeMillis();
        }
        if (a.h(str2)) {
            setBookName(str2);
        } else {
            int lastIndexOf = this.mId.lastIndexOf(46);
            if (lastIndexOf != -1) {
                String substring = this.mId.substring(lastIndexOf, this.mId.length());
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str2);
                stringBuffer.append(substring);
                this.mBookName = stringBuffer.toString();
            } else {
                this.mBookName = str2;
            }
            setBookName(this.mBookName);
        }
        if (this.mBookName != null && this.mBookName.toLowerCase().endsWith(".txt")) {
            this.mEncoding = 4;
        }
    }

    public DownloadBookTask getDownloadTask() {
        return this.mDownloadTask;
    }

    public void setDownloadTask(DownloadBookTask downloadBookTask) {
        this.mDownloadTask = downloadBookTask;
    }
}
