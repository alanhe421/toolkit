package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;

public class DelAudioReplyTask extends ReaderProtocolJSONTask {
    public DelAudioReplyTask(String str, String str2, String str3, int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e.cz).append("?qid=").append(str);
        stringBuilder.append(GetVoteUserIconsTask.BID).append(str2);
        stringBuilder.append("&replyid=").append(str3);
        stringBuilder.append("&ctype=").append(i);
        this.mUrl = stringBuilder.toString();
    }

    public boolean isNeedLogin() {
        return true;
    }
}
