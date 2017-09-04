package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;

public class CommentDetailSetTopTask extends ReaderProtocolJSONTask {
    public CommentDetailSetTopTask(c cVar, String str, long j, int i, boolean z) {
        super(cVar);
        if (z) {
            this.mUrl = e.cP + "cid=" + str + GetVoteUserIconsTask.BID + j + "&ctype=" + i + "&op=" + 1;
        } else {
            this.mUrl = e.cP + "cid=" + str + GetVoteUserIconsTask.BID + j + "&ctype=" + i + "&op=" + 2;
        }
    }
}
