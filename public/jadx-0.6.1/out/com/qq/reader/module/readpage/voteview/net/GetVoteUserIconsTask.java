package com.qq.reader.module.readpage.voteview.net;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.readpage.voteview.VoteViewGroup.ViewType;

public class GetVoteUserIconsTask extends ReaderProtocolJSONTask {
    public static final String BID = "&bid=";
    public static final String CID = "&cid=";
    public static final String GZIP = "&gzip=1";
    public static final String TIME = "&time=";

    public GetVoteUserIconsTask(c cVar, String str, String str2, String str3, ViewType viewType) {
        super(cVar);
        this.mUrl = e.bi + BID + str + CID + str2 + TIME + str3 + GZIP;
    }

    public boolean isResponseGzip() {
        return true;
    }
}
