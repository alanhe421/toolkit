package com.qq.reader.common.readertask.protocol;

import com.qq.reader.appconfig.e;
import com.qq.reader.common.readertask.ordinal.ReaderProtocolJSONTask;
import com.qq.reader.module.readpage.voteview.net.GetVoteUserIconsTask;
import java.util.List;

public class QueryChapterMoreInfoTask extends ReaderProtocolJSONTask {
    public QueryChapterMoreInfoTask(String str, List<Integer> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(e.ck);
        stringBuilder.append("bid=");
        stringBuilder.append(str);
        stringBuilder.append(GetVoteUserIconsTask.CID);
        stringBuilder.append(list.get(0));
        this.mUrl = stringBuilder.toString();
    }
}
