package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;

public class ParaiseTask extends ReaderProtocolJSONTask {
    long mBid = 0;
    String mCommendid = "";
    int mCtype = -1;

    public ParaiseTask(c cVar, long j, String str, int i) {
        super(cVar);
        this.mCommendid = str;
        this.mBid = j;
        this.mCtype = i;
        this.mUrl = e.h + "nativepage/comment/" + "agreecomment?" + "bid=" + this.mBid + "&agree=0" + "&commentid=" + this.mCommendid + "&ctype=" + this.mCtype;
        setFailedType(2);
    }

    public String generateTaskKey() {
        return String.valueOf(this.mBid) + this.mCommendid + this.mCtype;
    }
}
