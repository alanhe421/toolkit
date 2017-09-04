package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;

public class CommentDetailSetBanCommentTask extends ReaderProtocolJSONTask {
    public CommentDetailSetBanCommentTask(c cVar, String str, long j, boolean z) {
        super(cVar);
        if (z) {
            this.mUrl = e.cS + "userId=" + str + GetVoteUserIconsTask.BID + j + "&op=" + 1;
        } else {
            this.mUrl = e.cS + "userId=" + str + GetVoteUserIconsTask.BID + j + "&op=" + 2;
        }
    }
}
