package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;

public class GetHotDiscountBookTask extends ReaderProtocolJSONTask {
    public GetHotDiscountBookTask(String str, String str2, c cVar) {
        super(cVar);
        this.mUrl = e.a + "discount?bid=" + str + GetVoteUserIconsTask.CID + str2;
    }
}
