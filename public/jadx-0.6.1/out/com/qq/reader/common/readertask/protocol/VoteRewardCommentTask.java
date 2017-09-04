package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.a.d;
import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.common.readertask.ordinal.c;
import com.qq.reader.common.utils.ao;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;
import java.net.URLEncoder;
import java.util.HashMap;

public class VoteRewardCommentTask extends ReaderProtocolJSONTask {
    public VoteRewardCommentTask(long j, String str, String str2, c cVar) {
        super(cVar);
        this.mUrl = e.h + "reward/comment?bid=" + j + GetVoteUserIconsTask.CID + str + "&content=" + URLEncoder.encode(str2);
    }

    public HashMap<String, String> getBasicHeader() {
        int currentTimeMillis = (int) (System.currentTimeMillis() / 1000);
        this.mHeaders.put("qrsy", d.f(this.mContext, currentTimeMillis));
        this.mHeaders.put("qrtm", String.valueOf(currentTimeMillis));
        this.mHeaders.put("qrem", ao.p());
        return super.getBasicHeader();
    }
}
