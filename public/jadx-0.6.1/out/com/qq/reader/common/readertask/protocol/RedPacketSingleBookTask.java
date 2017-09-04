package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e.a;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;

public class RedPacketSingleBookTask extends ReaderProtocolJSONTask {
    public RedPacketSingleBookTask(long j, int i, long j2, long j3, int i2, c cVar) {
        super(cVar);
        StringBuilder stringBuilder = new StringBuilder(a.f + "?status=" + i2);
        if (j > 0) {
            stringBuilder.append(GetVoteUserIconsTask.BID).append(j);
        }
        if (j3 > 0) {
            stringBuilder.append("&cbid=").append(j3);
        }
        stringBuilder.append("&op=").append(i);
        if (j2 > 0) {
            stringBuilder.append("&rid=").append(j2);
        }
        this.mUrl = stringBuilder.toString();
    }
}
