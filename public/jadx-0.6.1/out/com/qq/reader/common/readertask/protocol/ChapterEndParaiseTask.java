package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class ChapterEndParaiseTask extends ReaderProtocolJSONTask {
    private long mBid = 0;
    private int mCtype = -1;
    private String mReplyid = "";

    public ChapterEndParaiseTask(c cVar, long j, String str, int i) {
        super(cVar);
        this.mReplyid = str;
        this.mBid = j;
        this.mCtype = i;
        this.mUrl = e.h + "nativepage/comment/" + "agreereply?" + "bid=" + this.mBid + "&agree=0" + "&replyid=" + this.mReplyid + "&ctype=" + this.mCtype;
        setFailedType(1);
    }

    public String generateTaskKey() {
        return String.valueOf(this.mBid) + this.mReplyid + this.mCtype;
    }
}
